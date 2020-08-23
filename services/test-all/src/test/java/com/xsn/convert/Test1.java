package com.xsn.convert;

import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    class Person {
        String name;
        String age;

        public Person(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void test1() {
        System.out.println(convert(new HashMap<String, String>() {
            {
                put("name", "dd");
                put("age", "18");
            }
        }, (s) -> {
            Map map = (HashMap) s;
            return new Person(
                    map.get("name").toString(),
                    map.get("age").toString()
            );
        }));
    }

    private Object convert(Map map, Converter<Map, Person> converter) {

        return converter.convert(map);
    }


}
