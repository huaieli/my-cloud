package com.xsn.controller;

import com.xsn.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

        try {
            rabbitTemplate.convertAndSend(
                    "testDirectExchange"
                    , "testDirectRoutingKey"
                    , map
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(e);
        }

        return ResultDTO.success();
    }

}
