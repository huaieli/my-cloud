package com.xsn.service;

import org.apache.activemq.command.ActiveMQMessage;

import javax.jms.Session;

public interface ReciveService {

    void reciveQueueText(ActiveMQMessage message, Session session);

    void reciveQueueText2(ActiveMQMessage message, Session session);

    void reciveDLQDefault(ActiveMQMessage message, Session session);

    void reciveTopicText(ActiveMQMessage message, Session session);
}
