package com.xsn.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectConfig {

    @Pointcut("execution(* com.xsn.service.impl.AopMechanisms())")
    public void pointCut1() {

    }

    @Before("pointCut1()")
    public void beforeAction() {
        System.out.println("proxy");
    }
}
