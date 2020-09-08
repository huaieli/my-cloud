package com.xsn;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xsn.test.entity.User;
import com.xsn.test.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCondition {
    @Autowired
    IUserService userService;

    @Test
    public void testAllEq() {

        // name = '1' and age is null
        userService.query()
                .allEq(new HashMap<String, Object>() {
                    {
                        put("name", "1");
                        put("age", null);
                    }
                }, true)
                .list()
                .forEach(System.out::println);

        System.out.println("=========================");

        // name = '1'
        userService.query()
                .allEq(new HashMap<String, Object>() {
                    {
                        put("name", "1");
                        put("age", null);
                    }
                }, false)
                .list()
                .forEach(System.out::println);

        System.out.println("=========================");

        // age is null
        userService.query()
                .allEq((k, v) -> k.length() == 3,
                        new HashMap<String, Object>() {
                            {
                                put("name", "1");
                                put("age", null);
                            }
                        })
                .list()
                .forEach(System.out::println);

    }

    @Test
    public void testEq() {
        // name = '1'
        userService.query()
                .eq("name", "1")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testNe() {

        // name <> '1'
        userService.query()
                .ne("name", "1")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testGtGe() {

        // age > 1
        userService.query()
                .gt("age", 1)
                .list()
                .forEach(System.out::println);

        // age >= 1
        userService.query()
                .ge("age", 1)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testLtLe() {

        // age < 1
        userService.query()
                .lt("age", 1)
                .list()
                .forEach(System.out::println);

        // age <= 1
        userService.query()
                .le("age", 1)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testBetween() {
        // age between 1 and 2
        userService.query()
                .between("age", 1, 2)
                .list()
                .forEach(System.out::println);

        System.out.println("================");

        // age not between 1 and 2
        userService.query()
                .notBetween("age", 1, 2)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testLike() {
        // name like '%1%'
        userService.query()
                .like("name", "1")
                .list()
                .forEach(System.out::println);

        // name not like '%1%'
        userService.query()
                .notLike("name", "1")
                .list()
                .forEach(System.out::println);

        // name like '%1'
        userService.query()
                .likeLeft("name", "1")
                .list()
                .forEach(System.out::println);

        // name like '1%'
        userService.query()
                .likeRight("name", "1")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testIsNull() {
        // name is null
        userService.query()
                .isNull("name")
                .list()
                .forEach(System.out::println);

        // name is not null
        userService.query()
                .isNotNull("name")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testIn() {
        // name in (1, 2, 3)
        userService.query()
                .in("name", 1, 2, 3)
                .list()
                .forEach(System.out::println);

        // name not in (1, 2, 3)
        userService.query()
                .notIn("name", 1, 2, 3)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testInSql() {
        // name in (select name from user where age = 1)
        userService.query()
                .inSql("name", "select name from user where age = 1")
                .list()
                .forEach(System.out::println);

        System.out.println("==================");

        // name not in (select name from user where age = 1)
        userService.query()
                .notInSql("name", "select name from user where age = 1")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testGroupByHaving() {
        // group by name, age having name > '1' and age > 1
        userService.query()
                .groupBy("name", "age")
                .having("name > '0'")
                .having("age > {0}", 0)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testOrderBy() {
        // order by name ASC, age ASC
        userService.query()
                .orderBy(true, true, "name", "age")
                .list()
                .forEach(System.out::println);

        // order by name ASC, age ASC
        userService.query()
                .orderByAsc("name", "age")
                .list()
                .forEach(System.out::println);

        // order by name DESC, age DESC
        userService.query()
                .orderByDesc("name", "age")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testFunc() {
        boolean condition = true;

        userService.query()
                .func(t -> {
                    if (condition) {
                        t.eq("name", "1");
                    } else {
                        t.eq("name", "2");
                    }
                })
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testOrAnd() {
        // name = '1' or name = '2'
        userService.query()
                .eq("name", "1")
                .or()
                .eq("name", "2")
                .list()
                .forEach(System.out::println);

        // name = '1' or name = '2'
        userService.query()
                .eq("name", "1")
                .or(i -> i.eq("name", "2"))
                .list()
                .forEach(System.out::println);

        // name = '1' and age = 1
        userService.query()
                .eq("name", "1")
                .and(i -> i.eq("age", 1))
                .list()
                .forEach(System.out::println);

    }

    @Test
    public void testApply() {
        // select name from user where age = 1
        userService.query()
                .apply("age = {0}", 1)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testLast() {
        // select * from user limit 1
        userService.query()
                .last("limit 1")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testExist() {
        // 拼接 exits
        userService.query()
                .exists("select name from user")
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testSelect() {
        // select name, age from user
        userService.query()
                .select("name", "age")
                .list()
                .forEach(System.out::println);

        System.out.println("===================");

        // select name from user
        userService.query()
                // 自行指定 实体
                .setEntity(new User())
                .select(i -> i.getColumn().equals("name"))
                .list()
                .forEach(System.out::println);

        System.out.println("===================");

        // select name from user
        userService.query()
                .select(User.class, i -> i.getColumn().equals("name"))
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void testSetSql() {
        User one = userService.query()
                .last("limit 1")
                .one();

        Assert.assertTrue(userService.update()
                .set("name", "u1")
                .setEntity(one)
                .update()
        );

        Assert.assertTrue(userService.update()
                .setSql("name = 'u2'")
                .setEntity(one)
                .update()
        );
    }

    @Test
    public void testLambda() {
        userService.lambdaQuery()
                .eq(User::getId, 1)
                .list()
                .forEach(System.out::println);

        Assert.assertTrue(
                userService.lambdaUpdate()
                        .last("limit 1")
                        .set(User::getName, "u3")
                        .update()
        );
    }
}
