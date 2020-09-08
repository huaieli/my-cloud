package com.xsn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsn.test.entity.User;
import com.xsn.test.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPage {

    @Autowired
    IUserService userService;

    /**
     * 表中共三条数据
     */
    @Test
    public void testPage() {
        Page<User> page = userService.page(
                new Page<>(1, 2)
                //, new QueryWrapper<>(new User("1", null, null)).eq("age", 1)
        );
        page.getRecords().forEach(System.out::println);
        // User(id=1, name=u3, age=1, email=1) User(id=2, name=2, age=2, email=2)

        System.out.println(page.getPages()); // 2
        System.out.println(page.getTotal()); // 3
        System.out.println(page.getSize()); // 2
        System.out.println(page.getCurrent()); // 1
    }
}
