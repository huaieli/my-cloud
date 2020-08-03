package com.xsn.controller;

import com.xsn.service.HelloService;
import com.xsn.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    HelloService helloService;

    @Autowired
    SecondService secondService;

    @GetMapping(value = "/test")
    public String test(@RequestParam(name = "name") String name) throws InterruptedException {

        // TimeUnit.SECONDS.sleep(2);
        return "hello " + name;
    }

    @PostMapping(value = "/helloTest")
    public String helloTest() {

        return helloService.hello();
    }

    @PostMapping(value = "/secondTest")
    public String secondTest() {

        return secondService.normalAction();
    }
}
