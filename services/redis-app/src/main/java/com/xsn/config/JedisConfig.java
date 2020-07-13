package com.xsn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private JedisPoolProperties jedisPoolProperties;


    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(jedisPoolProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(jedisPoolProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(jedisPoolProperties.getMaxWait());
        jedisPoolConfig.setMaxTotal(jedisPoolProperties.getMaxActive());

        JedisPool jedisPool = new JedisPool(
                jedisPoolConfig
                , redisProperties.getHost()
                , redisProperties.getPort()
                // , redisProperties.getTimeout()
                // , redisProperties.getPassword()
                // , redisProperties.getDatabase()
        );

        return jedisPool;
    }

}
