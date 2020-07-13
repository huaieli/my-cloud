package com.xsn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActiveMQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMQConsumerApplication.class, args);
    }

}
