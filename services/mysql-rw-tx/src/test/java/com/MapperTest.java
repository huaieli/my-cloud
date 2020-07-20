package com;

import com.xsn.entity.TestUser;
import com.xsn.mapper.TestUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperTest extends BaseTest {

    @Autowired
    private TestUserMapper testUserMapper;

    @Test
    public void test1() {
        testUserMapper.insert(
                TestUser.builder()
                        .id(1)
                        .name("1")
                        .age(1)
                        .build()
        );
    }

    @Test
    public void test2() {
        testUserMapper.selectByPrimaryKey(1);
    }
}
