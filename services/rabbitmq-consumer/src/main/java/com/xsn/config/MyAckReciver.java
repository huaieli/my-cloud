package com.xsn.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyAckReciver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliverTag = message.getMessageProperties().getDeliveryTag();

        try {
            System.out.println(message);
            channel.basicAck(deliverTag, true);
        } catch (Exception e) {
            // channel.basicReject(deliverTag, false);
            log.error(e.getMessage(), e);
        }

    }
}
