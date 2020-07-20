package com;

import com.xsn.config.MyMapperProxy;
import com.xsn.entity.TestUser;
import com.xsn.mapper.TestUserMapper;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.lang.reflect.InvocationTargetException;

public class ProxyTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MyMapperProxy<TestUserMapper> myMapperProxy = new MyMapperProxy();

        TestUserMapper testUserMapper = myMapperProxy.newInstance(TestUserMapper.class);
        TestUser testUser = testUserMapper.selectByPrimaryKey(null);

        System.out.println(testUser);

        /*ObjectFactory objectFactory = new DefaultObjectFactory();

        TestUser testUser11 = objectFactory.create(TestUser.class);

        Reflector reflector = new Reflector(TestUser.class);
        Invoker invoker = reflector.getSetInvoker("id");
        invoker.invoke(testUser11, new Object[] {2});
        invoker = reflector.getGetInvoker("id");
        System.out.println(invoker.invoke(testUser11, null));*/

    }
}
