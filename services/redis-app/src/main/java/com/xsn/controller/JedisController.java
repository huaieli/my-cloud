package com.xsn.controller;

import com.xsn.config.JedisUtils;
import com.xsn.config.RedisProperties;
import com.xsn.dto.CodeMap;
import com.xsn.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "redis-app")
@Slf4j
public class JedisController {

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private RedisProperties redisProperties;

    @RequestMapping(value = "/saveString", method = RequestMethod.POST)
    @ApiOperation(value = "保存KV String")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "K", required = true)
            , @ApiImplicitParam(name = "key", value = "V", required = true)
    })
    public ResultDTO saveString(
        @RequestParam(value = "key") String key
        , @RequestParam(value = "value") String value
    ) {

        try {
            jedisUtils.set(key, value, redisProperties.getDatabase());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(CodeMap.STATE_CODE_THROWABLE);
        }

        return ResultDTO.success();
    }

    @RequestMapping(value = "/getString", method = RequestMethod.GET)
    @ApiOperation(value = "获取String")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "K", required = true)
    })
    public ResultDTO getString(
            @RequestParam(value = "key") String key
    ) {

        String value = "";

        try {
            value = jedisUtils.get(key, redisProperties.getDatabase());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(CodeMap.STATE_CODE_THROWABLE);
        }

        return ResultDTO.success(value);
    }

}
