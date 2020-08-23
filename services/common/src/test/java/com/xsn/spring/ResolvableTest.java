package com.xsn.spring;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.Map;

public class ResolvableTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        ResolvableType resolvableType = ResolvableType.forInstance(map);
        System.out.println(resolvableType.resolve());
    }
}
