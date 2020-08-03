package com.xsn.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {

        return "hello world";
    }

    @Override
    public String hello2() {

        return "hello2 world";
    }
}
