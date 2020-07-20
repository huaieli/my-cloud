package com.xsn.controller;

import com.xsn.service.impl.CacheHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "mybatis cache")
@Slf4j
public class CacheController {

    @Autowired
    private CacheHandler cacheHandler;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation(value = "测试缓存")
    public void test () {

        cacheHandler.test1();

        log.info("=============================");

        cacheHandler.test2();

        log.info("=============================");

        cacheHandler.test3();

        log.info("=============================");

        cacheHandler.test4();

        log.info("=============================");

        cacheHandler.test5();

        log.info("=============================");

        cacheHandler.test6();
    }
}
