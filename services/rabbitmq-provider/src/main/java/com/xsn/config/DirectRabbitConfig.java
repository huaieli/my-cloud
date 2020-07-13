package com.xsn.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue testQueue() {

        /**
         * durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
         * exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
         */
        return new Queue("test", true, false, false
                /**
                 * 当消息
                 * 1）ttl过期
                 * 2）被手动丢弃（basicNack, basicReject)
                 * 3)队列满
                 */
                , new HashMap<String, Object>(2) {
                    {
                        put("x-dead-letter-exchange", "dlxExchange");
                        put("x-dead-letter-routing-key", "dlxRoutingKey");
                    }
                });
    }

    @Bean
    public DirectExchange testDirectExchange() {

        return new DirectExchange("testDirectExchange", true, false);
    }

    @Bean
    public DirectExchange noBindingExchange() {

        return new DirectExchange("noBindingExchange", true, false);
    }

    @Bean
    public Binding testBinding() {

        return BindingBuilder.bind(testQueue()).to(testDirectExchange()).with("testDirectRoutingKey");
    }
}
