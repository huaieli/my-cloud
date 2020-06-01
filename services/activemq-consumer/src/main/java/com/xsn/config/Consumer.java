package com.xsn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "system.activemq.consumer")
public class Consumer {
    private String clientId;
}
