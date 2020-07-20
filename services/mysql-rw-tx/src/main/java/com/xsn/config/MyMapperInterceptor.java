package com.xsn.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class
                , method = "update"
                , args = {MappedStatement.class, Object.class}
        )
        , @Signature(
                type = Executor.class
            , method = "query"
            , args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }
        )
        , @Signature(
                type = Executor.class
            , method = "close"
            , args = { boolean.class }
        )
})
@Slf4j
@Component
public class MyMapperInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info(invocation.getMethod().getName() + " be intercepted");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
