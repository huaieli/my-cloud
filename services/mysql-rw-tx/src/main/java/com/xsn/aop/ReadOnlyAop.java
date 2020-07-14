package com.xsn.aop;

import com.xsn.config.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-100)
@Slf4j
public class ReadOnlyAop {

    @Pointcut(value = "@annotation(com.xsn.anno.ReadOnly)")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public  Object changeDb(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            DBContextHolder.slave();
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            DBContextHolder.remove();
        }

        return null;
    }
}
