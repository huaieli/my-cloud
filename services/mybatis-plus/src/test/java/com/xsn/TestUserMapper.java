package com.xsn;

import com.xsn.test.entity.User;
import com.xsn.test.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
    }
}
