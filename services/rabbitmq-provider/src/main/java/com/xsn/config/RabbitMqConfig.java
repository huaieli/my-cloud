package com.xsn.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            System.out.println("correlationData=" + correlationData);
            System.out.println("ack=" + ack);
            System.out.println("cause=" + cause);
        });

        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
            System.out.println("message=" + message);
            System.out.println("replyCode=" + replyCode);
            System.out.println("replyText=" + replyText);
            System.out.println("exchange=" + exchange);
            System.out.println("routingKey=" + routingKey);
        });

        return rabbitTemplate;
    }
}
