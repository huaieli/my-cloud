package com.xsn.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource master() {
        return new DruidDataSource();
    }

    @Bean("slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1() {
        return new DruidDataSource();
    }

    @Bean("slave2")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2() {
        return new DruidDataSource();
    }

    @Bean("targetDataSource")
    public DataSource targetDataSource(
            @Qualifier("master") DataSource master
            , @Qualifier("slave1") DataSource slave1
            , @Qualifier("slave2") DataSource slave2
            ) {

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.MASTER, master);
        dataSources.put(DataSourceType.SLAVE1, slave1);
        dataSources.put(DataSourceType.SLAVE2, slave2);

        RouteDataSource routeDataSource = new RouteDataSource();
        routeDataSource.setDefaultTargetDataSource(master);
        routeDataSource.setTargetDataSources(dataSources);

        return routeDataSource;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ConfigTools.encrypt("hcp"));
        System.out.println(ConfigTools.encrypt("Hcp@123_2020"));
    }
}
