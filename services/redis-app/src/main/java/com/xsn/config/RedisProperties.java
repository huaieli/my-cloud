package com.xsn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis")
@Data
@Component
public class RedisProperties {
    private String host;
    private int port;
    private int database;
    private String password;
    private int timeout;
}
