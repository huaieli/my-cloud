package com.xsn.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
@RabbitListener(queues = "topicQueueB")
@Component
public class TopicReciverB {

    @RabbitHandler
    public void handle(Map<String, Object> map) {

        System.out.println("=======topicQueueB=====");
        System.out.println(map);
    }
}
