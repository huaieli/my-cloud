package com.xsn.service.impl;

import com.xsn.config.AspectConfig;
import com.xsn.config.MyAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class AopMechanisms implements AopMechanismsIntf {

    @Override
    public void a() {

        System.out.println("target a");
        System.out.println("=============================");

        this.b();
    }

    @Override
    public void b() {

        System.out.println("target b");
        System.out.println("=============================");
    }

    public static void main(String[] args) {
        AopMechanismsIntf aopMechanisms = new AopMechanisms();

        ProxyFactory proxyFactory = new ProxyFactory(aopMechanisms);

        proxyFactory.addInterface(AopMechanismsIntf.class);
        proxyFactory.addAdvice(new MyAdvice());

        AopMechanismsIntf proxy = (AopMechanismsIntf) proxyFactory.getProxy();
        proxy.a();
    }
}
