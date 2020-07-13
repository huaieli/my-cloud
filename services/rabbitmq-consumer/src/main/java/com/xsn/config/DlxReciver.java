package com.xsn.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DlxReciver {

    @RabbitHandler
    @RabbitListener(queues = "dlxQueue", containerFactory = "rabbitListenerContainerFactory")
    public void handle(Message message, Channel channel) throws IOException {
        System.out.println(new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
