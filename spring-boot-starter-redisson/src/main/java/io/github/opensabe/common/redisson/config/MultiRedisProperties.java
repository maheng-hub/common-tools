package io.github.opensabe.common.redisson.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.data.redis")
public class MultiRedisProperties {
    /**
     * 默认连接必须配置，配置 key 为 default
     */
    public static final String DEFAULT = "default";

    private boolean enableMulti = false;
    private Map<String, RedisProperties> multi;
}
