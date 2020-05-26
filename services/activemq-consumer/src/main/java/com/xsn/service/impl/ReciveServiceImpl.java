package com.xsn.service.impl;

import com.xsn.dto.CodeMap;
import com.xsn.dto.ResultDTO;
import com.xsn.service.ReciveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Session;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ReciveServiceImpl implements ReciveService {

    @Override
    @JmsListener(destination = "default", containerFactory = "jmsListenerContainerQueue")
    public void reciveQueueText(ActiveMQMessage message, Session session) {
        try {
            System.out.println("收到消息" + message);
            // message.acknowledge();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    // @JmsListener(destination = "default", containerFactory = "simpleJmsListenerContainerQueue")
    public void reciveQueueText2(ActiveMQMessage message, Session session) {
        try {
            System.out.println("收到消息" + message);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @JmsListener(destination = "default", containerFactory = "jmsListenerContainerTopic")
    public void reciveTopicText(ActiveMQMessage message, Session session) {
        try {
            System.out.println("收到消息" + message);
            message.acknowledge();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
