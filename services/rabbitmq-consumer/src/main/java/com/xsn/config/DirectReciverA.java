package com.xsn.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class DirectReciverA {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    @RabbitListener(queues = "test"
            , containerFactory = "rabbitListenerContainerFactory"
    )
    public void handle(Message message, Channel channel) throws IOException {
        System.out.println(new String(message.getBody()));
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

        /*rabbitTemplate.convertAndSend(
                "dlxExchange"
                , "dlxRoutingKey"
                , message);*/
    }
}
