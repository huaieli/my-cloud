package com.xsn.controller;

import com.alibaba.fastjson.JSONObject;
import com.xsn.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "rabbit send message")
@Slf4j
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendDirectMessage", method = RequestMethod.POST)
    @ApiOperation(value = "sendDirectMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO sendDirectMessage(
        @RequestParam(value = "message") String message
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);

        Message messages = MessageBuilder.withBody(JSONObject.toJSONString(map).getBytes())
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setExpiration("10000")
                .build();

        try {
            rabbitTemplate.convertAndSend(
                    "testDirectExchange"
                    , "testDirectRoutingKey"
                    , messages
                    /*, (m) -> {
                        m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        m.getMessageProperties().setExpiration("10000");
                        return m;
                    }*/);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return ResultDTO.success();
    }

    @RequestMapping(value = "/sendTopicAMessage", method = RequestMethod.POST)
    @ApiOperation(value = "sendTopicAMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO sendTopicAMessage(
            @RequestParam(value = "message") String message
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);

        try {
            rabbitTemplate.convertAndSend(
                    "topicExchange"
                    , "topic.QueueA"
                    , map
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return ResultDTO.success();
    }

    @RequestMapping(value = "/sendTopicRMessage", method = RequestMethod.POST)
    @ApiOperation(value = "sendTopicRMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO sendTopicRMessage(
            @RequestParam(value = "message") String message
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);

        try {
            rabbitTemplate.convertAndSend(
                    "topicExchange"
                    , "topic.Random"
                    , map
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return ResultDTO.success();
    }

    @RequestMapping(value = "/noExchangeMessage", method = RequestMethod.POST)
    @ApiOperation(value = "noExchangeMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO noExchangeMessage(
            @RequestParam(value = "message") String message
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);

        try {
            rabbitTemplate.convertAndSend(
                    "noExchange"
                    , "topic.Random"
                    , map
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return ResultDTO.success();
    }

    @RequestMapping(value = "/noBindingMessage", method = RequestMethod.POST)
    @ApiOperation(value = "noBindingMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息", required = true)
    })
    public ResultDTO noBindingMessage(
            @RequestParam(value = "message") String message
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);

        try {
            rabbitTemplate.convertAndSend(
                    "noBindingExchange"
                    , "topic.Random"
                    , map
                    , (msg) -> {
                        msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        msg.getMessageProperties().setExpiration("10000");
                        return msg;
                    }
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return ResultDTO.success();
    }
}
