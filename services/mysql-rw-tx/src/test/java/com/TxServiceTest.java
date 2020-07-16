package com;

import com.xsn.MysqlRWApplication;
import com.xsn.service.impl.TxHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

public class TxServiceTest extends BaseTest {

    @Autowired
    private TxHandler txHandler;

    @Test
    public void test1() {
        txHandler.withTxwithCatchInner();
    }
}
