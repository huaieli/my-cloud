package com.xsn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsn.test.entity.User;
import com.xsn.test.entity.UserDTO;
import com.xsn.test.mapper.UserMapper;
import com.xsn.test.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    IUserService userService;

    @Test
    public void testSave() {
        // save
        Assert.assertTrue(userService.save(new User("1", 1, "1")));

        // 批量 save
        Assert.assertTrue(userService.saveBatch(new ArrayList<User>() {
            {
                add(new User("1", 1, "1"));
                add(new User("1", 1, "1"));
            }
        }));
    }

    @Test
    public void testGet() {

        // getById
        User user = userService.getById(1);
        Assert.assertNotNull(user);

        // getOne
        Assert.assertNotNull(userService.getOne(
                new QueryWrapper<>(new User("1", 1, "1"))
                        // 我们可以 wapper.last(" LIMIT 1") 限制只取一条
                        .last(" LIMIT 1"),

                // 也可以指定的第二个参数为 false，结果超过一条时不抛出异常
                false)
        );

        // getMap
        // 结果： name:1
        userService.getMap(
                new QueryWrapper<>(new User(null, 1, null),
                        "name")

                // 注意如果没有满足条件的则 getMap 返回 null，此处抛出 NPE
        ).forEach((k, v) -> System.out.println(k +":"+ v));

        // getObj
        // 结果；1
        System.out.println(userService.getObj(
                new QueryWrapper<>(new User(null, 1, null)),
                Function.identity()
        ));

    }

    @Test
    public void testList() {

        // 根据 map 条件过滤
        Assert.assertEquals(3, userService.listByMap(
                new HashMap<String, Object>() {
                    {
                        put("name", "1");
                    }
                }
        ).size());

        // 查询所有
        Assert.assertEquals(3, userService.list().size());

        // 以 QueryWrapper 过滤
        // 默认 Function.identity() 映射
        userService.listObjs(
                new QueryWrapper<>(new User("1", null, null))
        ).forEach(System.out::println);

    }

    @Test
    public void testUpdate() {
        User u7 = userService.getById(7);
        User u8 = userService.getById(8);

        // 单个修改
        u7.setName("u1");
        Assert.assertTrue(userService.updateById(u7));

        // 批量修改
        u7.setName("u2");
        u8.setName("u2");
        Assert.assertTrue(userService.updateBatchById(new ArrayList<User>(){
            {
                add(u7);
                add(u8);
            }
        }));

        // UpdateWrapper 必须设置 sqlSet
        // 即 entity(u7) 定义 where，sqlSet 定义 set
        Assert.assertTrue(
                userService.update(
                        new UpdateWrapper<>(u7)
                            .setSql("name = 'u3'")
                            .setSql("age = 3")
                )
        );

        // entity(new User) 定义 where，paramEntity(u7) 定义 set
        Assert.assertTrue(
                userService.update(u7,
                        new UpdateWrapper<>(new User("1", null, null))
                )
        );
    }

    @Test
    public void testSaveOrUpdate() {
        User u7 = userService.getById(7);

        // 单个 saveOrUpdate
        u7.setName("sou1");
        Assert.assertTrue(userService.saveOrUpdate(u7));

        // 批量 saveOrUpdate，其中 u7 update, u save
        u7.setName("sou2");
        Assert.assertTrue(userService.saveOrUpdateBatch(new ArrayList<User>() {
            {
                add(u7);
                User u = new User("sou2", 4, "sou2");
                add(u);
            }
        }));

        // 先执行 update(u7, updateWrapper) u7 定义 sqlSet, new User 定义 where
        // 如果执行失败，则 saveOrUpdate(u7)
        u7.setName("sou4");
        Assert.assertTrue(userService.saveOrUpdate(
                u7,
                new UpdateWrapper<>(new User("sou2", null, null))
                // new UpdateWrapper<>(new User("AnOther", null, null))
        ));
    }

    @Test
    public void testRemove() {

        // 根据 id 删除
        Assert.assertTrue(userService.removeById(9));

        // 批量删除，不存在的无视
        Assert.assertTrue(userService.removeByIds(new ArrayList<Integer>() {
            {
                add(8);
                add(9);
            }
        }));

        // QueryWrapper.entity 定义 where 条件
        Assert.assertTrue(userService.remove(
                new QueryWrapper<>(
                        new User("sou4", null, null)
                )
        ));

        // Map<String, Object> 定义 where 条件
        Assert.assertTrue(userService.removeByMap(
                new HashMap<String, Object>() {
                    {
                        put("name", "1");
                    }
                }
        ));
    }

    @Test
    public void testPage() {
        Page page = new Page<User>(0, 2);

        Page page1 = userService.page(page);
        System.out.println(page1.getCurrent()); // 1
        System.out.println(page.getMaxLimit());// null
        System.out.println(page1.getSize()); // 2
        System.out.println(page1.getTotal()); // 0
        System.out.println(page1.getPages()); // 0
        page1.getRecords().forEach(System.out::println);
    }
}
