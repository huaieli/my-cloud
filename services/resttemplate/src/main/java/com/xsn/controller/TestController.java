package com.xsn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test(@RequestParam(name = "name") String name) throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        return "hello " + name;
    }
}
