package io.github.opensabe.common.testcontainers;

import com.github.dockerjava.api.command.InspectContainerResponse;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CustomizedMySQLContainer extends GenericContainer<CustomizedMySQLContainer> {
    public static final int MYSQL_PORT = 3306;
    public static final String MYSQL_ROOT_PASSWORD = "123456";

    public CustomizedMySQLContainer() {
        super("mysql");
    }

    @Override
    protected void configure() {
        withEnv("MYSQL_ROOT_PASSWORD", MYSQL_ROOT_PASSWORD);
        withExposedPorts(MYSQL_PORT);
    }

    @Override
    @SneakyThrows
    protected void containerIsStarted(InspectContainerResponse containerInfo) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //加载所有 init*.sql 文件和 data*.sql 文件，先执行 init*.sql 文件，再执行 data*.sql 文件
        Resource[] init = resolver.getResources("classpath*:init*.sql");
        Resource[] data = resolver.getResources("classpath*:data*.sql");

        //执行 init*.sql 文件
        executeSql(init);
        //执行 data*.sql 文件
        executeSql(data);

        System.out.println("MySQL started at port: " + getMysqlPort());
    }

    @SneakyThrows
    private void executeSql(Resource[] resources) {
        if (resources == null || resources.length == 0) {
            return;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(resources.length);
        List<Future> futures = new ArrayList<>();
        for (Resource resource : resources) {
            futures.add(executorService.submit(() -> {
                try {
                    String content = resource.getContentAsString(Charset.defaultCharset());
                    Container.ExecResult mysql = null;
                    content = content.replace("\r\n", "\n");
                    //直到执行成功
                    while (
                            mysql == null
                                    || (
                                    mysql.getExitCode() == 1
                                            && (
                                            //需要一定时间启动
                                            mysql.getStderr().contains("connect to")
                                                    //需要一定时间设置 ROOT 密码
                                                    || mysql.getStderr().contains("Access denied")
                                    )
                            )
                    ) {
                        mysql = execInContainer("mysql", "-uroot", "-p" + MYSQL_ROOT_PASSWORD, "-e", content);
                        System.out.println(
                                "-----------------------------------------------------------------------------------------"
                                        + "\nMySQL command: " + resource
                                        + "\nMySQL init result: " + mysql.getStdout()
                                        + "\nMySQL init error: " + mysql.getStderr()
                                        + "\nMySQL init exit code: " + mysql.getExitCode()
                        );
                        TimeUnit.SECONDS.sleep(3);
                    }
                    if (mysql.getExitCode() != 0) {
                        throw new RuntimeException("MySQL init failed at " + resource);
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }));
        }
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdownNow();
    }


    @Override
    public void stop() {
        super.stop();
        System.out.println("MySQL stopped");
    }

    public int getMysqlPort() {
        return getMappedPort(MYSQL_PORT);
    }
}
