package com.xsn.timer;

import com.xsn.service.SendService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Autowired
    private SendService sendService;

    @XxlJob("sendQueueText")
    public ReturnT<String> sendQueueText(String param) {
        sendService.sendQueueText("queue text: " + param);
        return ReturnT.SUCCESS;
    }

    @XxlJob("sendTopicText")
    public ReturnT<String> sendTopicText(String param) {
        sendService.sendTopicText("topic text: " + param);
        return ReturnT.SUCCESS;
    }
}
