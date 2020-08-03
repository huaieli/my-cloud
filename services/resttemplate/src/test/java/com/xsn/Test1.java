package com.xsn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test1() {
        Object obj = restTemplate.getForObject(
                "https://www.baidu.com"
        , String.class);
        System.out.println(obj);
    }

    @Test
    public void test2() {
        Object obj = restTemplate.getForObject(
                "http://localhost:10601/test?name=dd"
                , String.class);
        System.out.println(obj);
    }
}
