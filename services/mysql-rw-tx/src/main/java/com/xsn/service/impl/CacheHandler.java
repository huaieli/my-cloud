package com.xsn.service.impl;

import com.xsn.mapper.TestUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class CacheHandler {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private CacheHandler cacheHandler;

    public void test1() {
        testUserMapper.selectByPrimaryKey(1);
        testUserMapper.selectByPrimaryKey(1);
    }

    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TestUserMapper testUserMapper2 = sqlSession.getMapper(TestUserMapper.class);

        testUserMapper2.selectByPrimaryKey(1);
        testUserMapper2.selectByPrimaryKey(1);

        sqlSession.close();
    }

    @Transactional
    public void test3() {
        testUserMapper.selectByPrimaryKey(2);
        cacheHandler.test4();
    }

    @Transactional
    public void test4() {
        testUserMapper.selectByPrimaryKey(2);
        testUserMapper.selectByPrimaryKey(2);
    }

    @Transactional
    public void test5() {
        testUserMapper.selectByPrimaryKey(1);
        cacheHandler.test1();

        log.info("-----------------------------");

        test1();
    }

    public void test6() {
        testUserMapper.selectByPrimaryKey(3);
        cacheHandler.test7();

        log.info("-----------------------------");

        test7();
    }

    @Transactional
    public void test7() {
        testUserMapper.selectByPrimaryKey(3);
    }
}
