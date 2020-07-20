package com;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Demo extends BaseTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
    }
}
