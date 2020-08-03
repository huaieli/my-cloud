package com.xsn.service.impl;

import com.xsn.entity.TestUser;
import com.xsn.mapper.TestUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class TxHandler {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    TxHandler txHandler;

    @Transactional
    public void outter() {

        try {
            txHandler.inner();
        } catch (Exception e) {

        }

        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public void inner() {

        testUserMapper.insertSelective(
                TestUser.builder()
                        .age(1)
                        .name("dd1")
                        .build()
        );

        int i = 1 / 0;
    }
}
