package com.xsn.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MyAckReciver myAckReciver;

    // @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer
                = new SimpleMessageListenerContainer(connectionFactory);

        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(1);
        simpleMessageListenerContainer.setQueueNames("test", "dlxQueue");

        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        simpleMessageListenerContainer.setMessageListener(myAckReciver);

        return simpleMessageListenerContainer;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
