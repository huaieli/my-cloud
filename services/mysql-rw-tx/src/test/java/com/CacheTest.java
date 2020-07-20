package com;

import com.xsn.service.impl.CacheHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheTest extends BaseTest {

    @Autowired
    private CacheHandler cacheHandler;

    @Test
    public void test() {
        cacheHandler.test1();
    }
}
