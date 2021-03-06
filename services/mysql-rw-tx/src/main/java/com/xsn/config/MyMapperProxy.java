package com.xsn.config;

import com.xsn.entity.TestUser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyMapperProxy<T> implements InvocationHandler {

    T t;

    public <T> T newInstance(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[] {clazz},
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {

            return method.invoke(this, args);
        }

        // pre t.action
        // t.action
        // post t.action

        return new TestUser(1, "dd", 1);
    }
}
