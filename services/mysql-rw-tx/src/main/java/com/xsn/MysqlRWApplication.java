package com.xsn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.xsn"})
@EnableDiscoveryClient
@MapperScan("com.xsn.mapper")
public class MysqlRWApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlRWApplication.class, args);
    }
}
