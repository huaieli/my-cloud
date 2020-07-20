package com.xsn.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SqlSessionFactoryConfig {

    @Autowired
    @Qualifier("targetDataSource")
    private DataSource targetDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(targetDataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 实体类对应的位置
        bean.setTypeAliasesPackage("com.xsn.entity");
        // mybatis的XML的配置
        bean.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(targetDataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
    }
}
