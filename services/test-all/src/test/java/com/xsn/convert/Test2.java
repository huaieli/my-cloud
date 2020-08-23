package com.xsn.convert;

import org.junit.Test;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;

import java.util.HashMap;
import java.util.Map;

public class Test2<T, S> {

    public Test2() {
    }

    public Map<String, Map<String, Object>> map =
            new HashMap<>();

    T a (String a, int b) {

        return null;
    }

    @Test
    public void test1() throws NoSuchFieldException {
        ResolvableType resolvableType
                = ResolvableType.forField(getClass().getField("map"));

        for (Class<?> aClass : resolvableType.resolveGenerics()) {
            System.out.println(aClass);
        }

        resolvableType = ResolvableType.forClass(getClass());
        System.out.println(resolvableType.getSource());
        System.out.println(resolvableType.asMap());

        resolvableType = ResolvableType.forInstance(new Test2<String, String>());
        for (Class<?> aClass : resolvableType.resolveGenerics()) {
            System.out.println(aClass);
        }

    }

}
