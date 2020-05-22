package com.xsn.service;

import com.xsn.dto.ResultDTO;
import org.apache.activemq.command.ActiveMQMessage;

import javax.jms.Session;

public interface ReciveService {

    void reciveQueueText(ActiveMQMessage message, Session session);

    void reciveTopicText(ActiveMQMessage message, Session session);
}
