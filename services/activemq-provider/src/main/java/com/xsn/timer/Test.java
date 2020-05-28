package com.xsn.timer;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @XxlJob("hello")
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("hello" + param);
        System.out.println("hello" + param);
        return ReturnT.SUCCESS;
    }
}
