package com.xsn.config;

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
     * topic模式的ListenerContainer
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * Queue模式的ListenerContainer
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}
