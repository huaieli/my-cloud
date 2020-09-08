package com.xsn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xsn.test.entity.User;
import com.xsn.test.mapper.UserMapper;
import org.junit.Assert;
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
    public void testMapper() {
        // 增
        Assert.assertEquals(1, userMapper.insert(new User("1", 1, "1")));

        // 查
        User user = userMapper.selectList(
                new QueryWrapper<>(new User("1", null, null))
        ).get(0);
        System.out.println(user);

        // 改
        Assert.assertEquals(1,
                userMapper.update(
                        user,
                        new QueryWrapper<>(new User("u2", null, null))
                )
        );

        // 删
        System.out.println(userMapper.delete(
                new QueryWrapper<>(new User("1", null, null))
        ));
    }
}
