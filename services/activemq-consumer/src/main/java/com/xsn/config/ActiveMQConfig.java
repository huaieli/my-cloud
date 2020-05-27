package com.xsn.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
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

    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次   这里设置为5次
        redeliveryPolicy.setMaximumRedeliveries(5);
        //重发时间间隔,默认为1000ms
        redeliveryPolicy.setInitialRedeliveryDelay(500);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;
    }

    /**
     * topic模式的ListenerContainer
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);

        // topic持久化
        bean.setSubscriptionDurable(true);
        bean.setClientId("A");

        // 手动签收
        bean.setSessionTransacted(false);
        bean.setSessionAcknowledgeMode(INDIVIDUAL_ACKNOWLEDGE);

        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * Queue模式的DefaultJmsListenerContainerFactory
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();

        // 手动签收
        bean.setSessionTransacted(false);
        bean.setSessionAcknowledgeMode(INDIVIDUAL_ACKNOWLEDGE);

        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
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
