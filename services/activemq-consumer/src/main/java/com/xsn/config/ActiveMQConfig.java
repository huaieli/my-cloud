package com.xsn.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import static org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE;

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

        // topic持久化
        bean.setSubscriptionDurable(true);
        bean.setClientId("A");

        // 手动签收
        bean.setSessionTransacted(false);
        bean.setSessionAcknowledgeMode(INDIVIDUAL_ACKNOWLEDGE);

        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * Queue模式的DefaultJmsListenerContainerFactory
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();

        // 手动签收
        bean.setSessionTransacted(false);
        bean.setSessionAcknowledgeMode(INDIVIDUAL_ACKNOWLEDGE);

        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * Queue模式的SimpleJmsListenerContainerFactory
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public SimpleJmsListenerContainerFactory simpleJmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        SimpleJmsListenerContainerFactory bean = new SimpleJmsListenerContainerFactory ();

        // 手动签收
        bean.setSessionTransacted(false);
        bean.setSessionAcknowledgeMode(INDIVIDUAL_ACKNOWLEDGE);

        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}
