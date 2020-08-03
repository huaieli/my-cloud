package com.xsn.test;

import com.xsn.service.HelloService;
import com.xsn.service.SecondService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MockServletTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    HelloService helloService;

    @Autowired
    SecondService secondService;

    @Test
    public void testTestController() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get("/test?name=dd"))
                .andExpect(status().isOk())
                //.andExpect(content().string("hello dd"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test2() throws Exception {
        given(helloService.hello())
                .willReturn("helloService");

        MvcResult mvcResult = mockMvc.perform(post("/helloTest"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test3() throws Exception {
        /*given(secondService.normalAction())
                .willReturn("normalAction");*/

        MvcResult mvcResult = mockMvc.perform(post("/secondTest"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
