package com.xsn.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@RabbitListener(queues = "topicQueueA")
@Component
public class TopicReciverA {

    @RabbitHandler
    public void handle(Map<String, Object> map) {

        System.out.println("=======topicQueueA=====");
        System.out.println(map);
    }
}
