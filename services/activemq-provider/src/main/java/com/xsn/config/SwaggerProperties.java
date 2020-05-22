package com.xsn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "com.xsn.activemq.swagger")
@RefreshScope
@Component
public class SwaggerProperties {
    private String scan;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String version;
}
