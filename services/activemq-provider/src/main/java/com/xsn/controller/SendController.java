package com.xsn.controller;

import com.xsn.dto.ResultDTO;
import com.xsn.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activemq")
@Api(tags = "activemq整合")
public class SendController {

    @Autowired
    private SendService sendService;

    @RequestMapping(value = "/sendQueueText", method = RequestMethod.GET)
    @ApiOperation(value = "queue消息发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO sendQueueText(@RequestParam(name = "message") String message)
    {
        return sendService.sendQueueText(message);
    }

    @RequestMapping(value = "/sendTopicText", method = RequestMethod.GET)
    @ApiOperation(value = "topic消息发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO sendTopicText(@RequestParam(name = "message") String message)
    {
        return sendService.sendTopicText(message);
    }
}
