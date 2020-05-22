package com.xsn.service;

import com.xsn.dto.ResultDTO;

public interface SendService {

    ResultDTO sendQueueText(String message);

    ResultDTO sendTopicText(String message);
}
