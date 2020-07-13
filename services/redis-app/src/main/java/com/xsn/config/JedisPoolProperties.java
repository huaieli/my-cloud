package com.xsn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis.jedis.pool")
@Data
@Component
public class JedisPoolProperties {
    private int maxIdle;
    private int minIdle;
    private int maxActive;
    private int maxWait;
}
