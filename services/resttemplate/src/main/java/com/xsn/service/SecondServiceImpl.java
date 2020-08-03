package com.xsn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondServiceImpl implements SecondService {

    @Autowired
    HelloService helloService;

    @Override
    public String action() {

        return helloService.hello();
    }

    @Override
    public String normalAction() {

        return "normal second service";
    }
}
