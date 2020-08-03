package com.xsn.test;

import com.xsn.service.HelloService;
import com.xsn.service.SecondService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MockServiceTest {

    // @MockBean
    @SpyBean
    HelloService helloService;

    @Autowired
    SecondService secondService;

    @Test
    public void test1() {

        given(helloService.hello())
                .willReturn("helloService");
        String result = secondService.action();
        System.out.println(result);
    }

    @Test
    public void test2() {
        when(helloService.hello())
                .thenReturn("helloService");
        System.out.println(helloService.hello());
        System.out.println(helloService.hello2());
    }

    @Test
    public void test3() {

        given(secondService.action())
                .willReturn("secondService");
        when(secondService.action())
                .thenReturn("secondService");

        System.out.println(secondService.action());
        System.out.println(secondService.normalAction());
    }
}
