package com.xsn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DLXRabbitConfig {

    @Bean
    public Queue dlxQueue() {

        return new Queue("dlxQueue", true, false, false);
    }

    @Bean
    public DirectExchange dlxExchange() {

        return new DirectExchange("dlxExchange", true, false);
    }

    @Bean
    public Binding dlxBinding() {

        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("dlxRoutingKey");
    }
}
