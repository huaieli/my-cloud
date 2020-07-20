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

    //////////////////////////////////////////////////

    @Transactional
    public void noCatchWithTx() {
        testUserMapper.insertSelective(
                TestUser.builder()
                .id(1)
                .name("noCatchWithTx")
                .age(1)
                .build()
        );

        int i = 1 / 0;
    }

    public void noCatchWithoutTx() {
        testUserMapper.insertSelective(
                TestUser.builder()
                        .id(2)
                        .name("noCatchWithoutTx")
                        .age(2)
                        .build()
        );

        int i = 1 / 0;
    }

    @Transactional
    public void catchWithTx() {
        testUserMapper.insertSelective(
                TestUser.builder()
                        .id(3)
                        .name("catchWithTx")
                        .age(3)
                        .build()
        );

        try {
            int i = 1 / 0;
        } catch (Exception e) {

        }
    }

    public void catchWithoutTx() {
        testUserMapper.insertSelective(
                TestUser.builder()
                        .id(4)
                        .name("catchWithoutTx")
                        .age(4)
                        .build()
        );

        try {
            int i = 1 / 0;
        } catch (Exception e) {

        }
    }

    /////////////////////////////////////////////////

    public void noTxnoCatchInner() {
        /**
         * 外方法没事务，且直接内部方法不然没有事务
         * 因为事务入口就在外部，此时没事务，则事务
         * AOP没有生效
         */
    }

    public void noTxWithCatchInner() {
        try {
            /**
             * 同上
             */
        } catch (Exception e) {

        }
    }

    @Transactional
    public void withTxNoCatchInner() {
        /**
         * 外部方法有事务，因为是内部调用，
         * 因此内部事务并不生效，但是异常
         * 没有被处理直接抛出，因此事务生效
         */
        // noCatchWithTx();

        /**
         * 外部方法有事务，内部方法无事务，
         * 异常没有被处理直接抛出，因此事
         * 务生效
         */
        // noCatchWithoutTx();

        /**
         * 外部方法有事务，因为是内部调用，
         * 因此内部事务并不生效，内部异常
         * 被处理，因此外部事务无察觉，事务
         * 失效
         */
        catchWithTx();

        /**
         * 外部方法有事务，内部方法无事务，
         * 内部异常被处理，因此外部事务无
         * 察觉，事务失效
         */
        catchWithoutTx();
    }

    @Transactional
    public void withTxwithCatchInner() {
        try {
            /**
             * 外部方法有事务，因为是内部调用，
             * 因此内部事务并不生效，内部异常
             * 被抛出，被外部处理，外部事务未
             * 生效
             */
            noCatchWithTx();

            /**
             * 外部方法有事务，内部方法无事务，
             * 内部异常被抛出，被外部处理，外
             * 部事务未生效
             */
            // noCatchWithoutTx();

            /**
             * 外部方法有事务，因为是内部调用，
             * 因此内部事务并不生效，内部异常
             * 被处理，因此外部事务无察觉，事务
             * 失效
             */
            // catchWithTx();

            /**
             * 外部方法有事务，内部方法无事务，
             * 内部异常被处理，因此外部事务无
             * 察觉，事务失效
             */
            // catchWithoutTx();

        } catch (Exception e) {

        }
    }

    public void noTxnoCatchOutter() {
        /**
         * 我们通过注入txHandler调用内部方法
         * 内部方法有事务且内部异常未被处理，
         * 事务生效
         */
        // txHandler.noCatchWithTx();

        /**
         * 没有事务，那肯定是不生效了
         */
        // txHandler.noCatchWithoutTx();

        /**
         * 我们通过注入txHandler调用内部方法
         * 内部方法有事务但内部异常被捕获处理，
         * 事务不生效
         */
        txHandler.catchWithTx();

        /**
         * 没有事务，那肯定是不生效了
         */
        // txHandler.catchWithoutTx();

    }

    public void noTxWithCatchOutter() {
        try {
            /**
             * 同上
             */
        } catch (Exception e) {

        }
    }

    @Transactional
    public void withTxNoCatchOutter() {
        /**
         * 内外都有事务，因为使用默认的事务传播
         * 属性，因此内部事务加入外部事务，内部
         * 异常未处理，事务生效
         */
        // txHandler.noCatchWithTx();

        /**
         * 内部方法无事务，且未处理异常
         * 异常抛出外部，外部有事务，
         * 事务生效
         */
        // txHandler.noCatchWithoutTx();

        /**
         * 内外都有事务，因为使用默认的事务传播
         * 属性，因此内部事务加入外部事务，但内
         * 部异常被捕获处理，事务不生效
         */
        // txHandler.catchWithTx();

        /**
         * 内部方法无事务，外部有事务，
         * 但内部异常被捕获处理，外部
         * 事务无感知，不生效
         */
        txHandler.catchWithoutTx();
    }

    @Transactional
    public void withTxwithCatchOutter() {
        try {
            /**
             * 内外都有事务，因为使用默认的事务传播属性，
             * 因此内部事务加入外部事务。内部抛出异常将事务标记为回滚
             * 外部处理掉抛出的异常后要提交事务产生冲突
             * 事务被回滚并抛出如下异常
             * org.springframework.transaction.UnexpectedRollbackException:
             * Transaction rolled back because it has been marked as rollback-only
             * 如何处理该问题
             * 1)修改内部事务传播为Propagation.REQUIRES_NEW（无论如何新建一个事务）
             * 2)外部方法不处理异常或者手动抛出RuntimeException
             * 3)外部方法捕获异常后手动回滚
             * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
             */
            // txHandler.noCatchWithTx();

            /**
             * 内部方法无事务，外部有事务，
             * 内部异常被抛出，外部捕获异常
             * 并处理，外部事务不生效
             */
            txHandler.noCatchWithoutTx();

            /**
             * 内外都有事务，因为使用默认的事务传播
             * 属性，因此内部事务加入外部事务，内部
             * 异常被捕获处理，外部事务无感知，不生效
             */
            // txHandler.catchWithTx();

            /**
             * 内部方法无事务，外部有事务，
             * 内部异常被捕获处理，外部
             * 事务无感知，不生效
             */
            // txHandler.catchWithoutTx();

        } catch (Exception e) {
            // throw new RuntimeException();
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public void outter() {

        testUserMapper.insertSelective(
                TestUser.builder()
                        .id(0)
                        .name("outter")
                        .age(0)
                        .build()
        );

        int i = 1 / 0;
    }

    public void inner() {

        testUserMapper.insertSelective(
                TestUser.builder()
                        .id(5)
                        .name("inner")
                        .age(5)
                        .build()
        );
    }

}
