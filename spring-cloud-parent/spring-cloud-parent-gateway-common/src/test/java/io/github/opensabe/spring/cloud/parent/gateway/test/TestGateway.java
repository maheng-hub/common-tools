package io.github.opensabe.spring.cloud.parent.gateway.test;

import io.github.opensabe.spring.cloud.parent.common.loadbalancer.TracedCircuitBreakerRoundRobinLoadBalancer;
import io.github.opensabe.spring.cloud.parent.common.redislience4j.CircuitBreakerExtractor;
import io.github.opensabe.spring.cloud.parent.gateway.filter.CommonLogFilter;
import io.github.opensabe.spring.cloud.parent.gateway.filter.InstanceCircuitBreakerFilter;
import io.github.opensabe.spring.cloud.parent.gateway.filter.QueryNormalizationFilter;
import io.github.opensabe.spring.cloud.parent.gateway.filter.RecordServiceNameFilter;
import io.github.opensabe.spring.cloud.parent.gateway.filter.RetryGatewayFilter;
import io.github.opensabe.spring.cloud.parent.gateway.filter.TraceIdFilter;
import io.github.opensabe.spring.cloud.parent.gateway.filter.TracedReactiveLoadBalancerClientFilter;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureObservability
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {
                "eureka.client.enabled=false",
                "spring.cloud.gateway.httpclient.connect-timeout=500",
                "spring.cloud.gateway.httpclient.response-timeout=10000",
                "spring.cloud.gateway.routes[0].id=testService",
                "spring.cloud.gateway.routes[0].uri=lb://testService",
                "spring.cloud.gateway.routes[0].predicates[0]=Path=/httpbin/**",
                "spring.cloud.gateway.routes[0].filters[0]=StripPrefix=1",
                "resilience4j.circuitbreaker.configs.default.failureRateThreshold=50",
                "resilience4j.circuitbreaker.configs.default.slidingWindowType=COUNT_BASED",
                "resilience4j.circuitbreaker.configs.default.slidingWindowSize=5",
                "resilience4j.circuitbreaker.configs.default.minimumNumberOfCalls=3",
                "resilience4j.circuitbreaker.configs.default.recordExceptions=java.lang.Exception"
        },
        classes = TestGateway.MockConfig.class
)
public class TestGateway extends CommonMicroServiceTest {
    @SpringBootApplication
    static class MockConfig {
    }

    private static final String serviceId = "testService";

    @SpyBean
    private LoadBalancerClientFactory loadBalancerClientFactory;
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;
    @Autowired
    private CircuitBreakerExtractor circuitBreakerExtractor;
    @LocalServerPort
    protected int port = 0;

    @Autowired
    private CommonLogFilter commonLogFilter;
    @Autowired
    private InstanceCircuitBreakerFilter instanceCircuitBreakerFilter;
    @Autowired
    private QueryNormalizationFilter queryNormalizationFilter;
    @Autowired
    private RecordServiceNameFilter recordServiceNameFilter;
    @Autowired
    private RetryGatewayFilter retryGatewayFilter;
    @Autowired
    private TracedReactiveLoadBalancerClientFilter reactiveLoadBalancerClientFilter;
    @Autowired
    private TraceIdFilter traceIdFilter;
    @Autowired
    protected WebTestClient webClient;

    //不同的测试方法的类对象不是同一个对象，会重新生成，保证互相没有影响
    ServiceInstance zone1Instance1 = new DefaultServiceInstance("instance1", serviceId, GOOD_HOST, GOOD_PORT, false, Map.ofEntries(Map.entry("zone", "zone1")));
    //not exists host, will cause connect time out
    ServiceInstance zone1Instance2 = new DefaultServiceInstance("instance2", serviceId, CONNECT_TIMEOUT_HOST, CONNECT_TIMEOUT_PORT, false, Map.ofEntries(Map.entry("zone", "zone1")));
    ServiceInstance zone1Instance3 = new DefaultServiceInstance("instance3", serviceId, GOOD_HOST, GOOD_PORT, false, Map.ofEntries(Map.entry("zone", "zone1")));
    ServiceInstance zone1Instance4 = new DefaultServiceInstance("instance3", serviceId, READ_TIMEOUT_HOST, READ_TIMEOUT_PORT, false, Map.ofEntries(Map.entry("zone", "zone1")));

    TracedCircuitBreakerRoundRobinLoadBalancer loadBalancerClientFactoryInstance = spy(TracedCircuitBreakerRoundRobinLoadBalancer.class);
    ServiceInstanceListSupplier serviceInstanceListSupplier = spy(ServiceInstanceListSupplier.class);

    @BeforeEach
    void setup() {
        webClient = webClient.mutate().responseTimeout(Duration.ofSeconds(30)).build();
        //初始化 loadBalancerClientFactoryInstance 负载均衡器
        loadBalancerClientFactoryInstance.setCircuitBreakerRegistry(circuitBreakerRegistry);
        loadBalancerClientFactoryInstance.setCircuitBreakerExtractor(circuitBreakerExtractor);
        loadBalancerClientFactoryInstance.setServiceInstanceListSupplier(serviceInstanceListSupplier);
    }

    @Test
    public void testLevelOfCircuit() {
        when(loadBalancerClientFactory.getInstance(serviceId, ReactorServiceInstanceLoadBalancer.class)).thenReturn(loadBalancerClientFactoryInstance);
        when(serviceInstanceListSupplier.get()).thenReturn(Flux.just(Lists.newArrayList(zone1Instance1, zone1Instance2)));

        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        reset(loadBalancerClientFactoryInstance);
        //调用 10 次，超过 5 次肯定会触发熔断器打开
        //对于 zone1Instance2 会触发 connect time out，导致熔断器打开
        //对于 zone1Instance1 会触发 500，这个不会触发熔断器打开，因为内部调用是我们可控的，某个接口 4XX 或者 5XX 可能确实出了什么问题，但是外部调用不可控。
        //不像公共依赖中对于 WebClient 我们针对 4XX 和 5XX 的响应码也断路
        //恶意调用可能一直触发我们某个接口 4XX 或者 5XX，但是正常调用不会。
        //所以，这里我们使用默认的，不看响应码，只对于异常进行断路（例如 IOException，连接超时，读取超时等等）
        for (int i = 0; i < 10; i++) {
            webClient.get().uri("/httpbin/status/500").exchange()
                    .expectStatus().isEqualTo(500)
                    .expectBody(String.class).consumeWith(s -> {
                        System.out.println("=================");
                        System.out.println(s);
                        System.out.println("=================");
                    });
        }

        //验证断路器有两个，并且一个是打开，另一个是关闭
        var circuitBreakers = circuitBreakerRegistry.getAllCircuitBreakers();
        circuitBreakers = circuitBreakers.stream().filter(circuitBreaker -> {
            return circuitBreaker.getName().contains(zone1Instance1.getHost()) || circuitBreaker.getName().contains(zone1Instance2.getHost());
        }).collect(Collectors.toSet());
        assertEquals(3, circuitBreakers.size());
        var openCircuitBreaker = circuitBreakers.stream().filter(c -> c.getState() == CircuitBreaker.State.OPEN).findFirst().get();
        var closeCircuitBreaker = circuitBreakers.stream().filter(c -> c.getState() == CircuitBreaker.State.CLOSED).findFirst().get();
        assertTrue(openCircuitBreaker.getName().contains(zone1Instance2.getHost()));
        assertTrue(closeCircuitBreaker.getName().contains(zone1Instance1.getHost()));
    }

    @Test
    public void testRetryOnResponceCode() {
        //返回正常实例
        when(loadBalancerClientFactory.getInstance(serviceId, ReactorServiceInstanceLoadBalancer.class)).thenReturn(loadBalancerClientFactoryInstance);
        when(serviceInstanceListSupplier.get()).thenReturn(Flux.just(Lists.newArrayList(zone1Instance1, zone1Instance3)));

        //1.GET 返回 4XX 不重试
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        webClient.get().uri("/httpbin/status/400").exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(String.class).consumeWith(s -> {
                    System.out.println("=================");
                    System.out.println(s);
                    System.out.println("=================");
                });
        verify(loadBalancerClientFactoryInstance, times(1)).choose(any());

        //2.测试 GET 返回 5XX,重试
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        webClient.get().uri("/httpbin/status/500").exchange()
                .expectStatus().isEqualTo(500)
                .expectBody(String.class).consumeWith(s -> {
                    System.out.println("=================");
                    System.out.println(s);
                    System.out.println("=================");
                });
        verify(loadBalancerClientFactoryInstance, times(3)).choose(any());


        //3.测试 非 GET 返回 4XX,不重试
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        webClient.post().uri("/httpbin/status/400").exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(String.class).consumeWith(s -> {
                    System.out.println("=================");
                    System.out.println(s);
                    System.out.println("=================");
                });
        verify(loadBalancerClientFactoryInstance, times(1)).choose(any(Request.class));

        //4.测试 非 GET 返回 5XX,不重试
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        webClient.post().uri("/httpbin/status/500").exchange()
                .expectStatus().isEqualTo(500)
                .expectBody(String.class).consumeWith(s -> {
                    System.out.println("=================");
                    System.out.println(s);
                    System.out.println("=================");
                });
        verify(loadBalancerClientFactoryInstance, times(1)).choose(any(Request.class));

    }

    @Test
    public void testRetryOnConnectionTimeout() {
        //返回一个正常实例，一个 connect time out 实例
        when(loadBalancerClientFactory.getInstance(serviceId, ReactorServiceInstanceLoadBalancer.class)).thenReturn(loadBalancerClientFactoryInstance);
        when(serviceInstanceListSupplier.get()).thenReturn(Flux.just(Lists.newArrayList(zone1Instance1, zone1Instance2)));
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        //测试 GET 请求，由于可能有一个实例 connect time out，所以会重试
        for (int i = 0; i < 10; i++) {
            webClient.get().uri("/httpbin/anything").exchange()
                    .expectStatus().isEqualTo(200)
                    .expectBody(String.class).consumeWith(s -> {
                        System.out.println("=================");
                        System.out.println(s);
                        System.out.println("=================");
                    });
        }
        verify(loadBalancerClientFactoryInstance, atLeast(11)).choose(any());
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        //测试 POST 请求，由于可能有一个实例 connect time out，请求还没发出去，即使是 POST 也会重试
        for (int i = 0; i < 10; i++) {
            webClient.post().uri("/httpbin/anything").exchange()
                    .expectStatus().isEqualTo(200)
                    .expectBody(String.class).consumeWith(s -> {
                        System.out.println("=================");
                        System.out.println(s);
                        System.out.println("=================");
                    });
        }
        verify(loadBalancerClientFactoryInstance, atLeast(11)).choose(any());

    }

    @Test
    public void testRetryOnReadTimeout() {
        //返回正常实例
        when(loadBalancerClientFactory.getInstance(serviceId, ReactorServiceInstanceLoadBalancer.class)).thenReturn(loadBalancerClientFactoryInstance);
        when(serviceInstanceListSupplier.get()).thenReturn(Flux.just(Lists.newArrayList(zone1Instance4)));
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        // GET 读取超时，会重试
        webClient.get().uri("/httpbin/anything").exchange()
                .expectBody(String.class).consumeWith(s -> {
                    System.out.println("=================");
                    System.out.println(s);
                    System.out.println("=================");
                });
        verify(loadBalancerClientFactoryInstance, times(3)).choose(any());
        //重置断路器
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(c -> c.reset());
        //重置统计
        reset(loadBalancerClientFactoryInstance);
        // POST 读取超时，不会重试
        webClient.post().uri("/httpbin/anything").exchange()
                .expectBody(String.class).consumeWith(s -> {
                    System.out.println("=================");
                    System.out.println(s);
                    System.out.println("=================");
                });
        verify(loadBalancerClientFactoryInstance, times(1)).choose(any());

    }

}
