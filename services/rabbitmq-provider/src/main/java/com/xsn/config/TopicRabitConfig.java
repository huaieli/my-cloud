package com.xsn.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabitConfig {

    private final String topicQueueA = "topic.QueueA";
    private final String topicQueueB = "topic.QueueB";

    @Bean
    public Queue topicQueueA() {

        return new Queue("topicQueueA", true, false, false);
    }

    @Bean
    public Queue topicQueueB() {

        return new Queue("topicQueueB", true, false, false);
    }

    @Bean
    public TopicExchange topicExchange() {

        return new TopicExchange("topicExchange", true, false);
    }

    @Bean
    Binding topicBindingA() {

        return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with(topicQueueA);
    }

    @Bean
    Binding topicBindingB() {

        return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with("topic.#");
    }
}
