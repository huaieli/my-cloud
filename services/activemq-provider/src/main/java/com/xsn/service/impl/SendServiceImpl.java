package com.xsn.service.impl;

import com.xsn.dto.CodeMap;
import com.xsn.dto.ResultDTO;
import com.xsn.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;

@Service
@Slf4j
public class SendServiceImpl implements SendService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier(value = "defaultQueue")
    private Queue defaultQueue;

    @Autowired
    @Qualifier(value = "defaultTopic")
    private Topic defaultTopic;

    @Override
    public ResultDTO sendQueueText(String message) {
        try {
            jmsMessagingTemplate.convertAndSend(defaultQueue, message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(CodeMap.STATE_CODE_THROWABLE);
        }

        return new ResultDTO(CodeMap.STATE_CODE_SUCCESS);
    }

    @Override
    public ResultDTO sendTopicText(String message) {
        try {
            jmsMessagingTemplate.convertAndSend(defaultTopic, message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(CodeMap.STATE_CODE_THROWABLE);
        }

        return new ResultDTO(CodeMap.STATE_CODE_SUCCESS);
    }
}
