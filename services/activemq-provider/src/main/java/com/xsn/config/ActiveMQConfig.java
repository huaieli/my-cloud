package com.xsn.config;

import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

@Configuration
@EnableJms
public class ActiveMQConfig {

    /**
     * 默认queue
     * @return
     */
    @Bean
    public Queue defaultQueue() {

        return new ActiveMQQueue("default") ;
    }

    /**
     * 默认queue
     * @return
     */
    @Bean
    public Topic defaultTopic() {

        return new ActiveMQTopic("default") ;
    }
}
