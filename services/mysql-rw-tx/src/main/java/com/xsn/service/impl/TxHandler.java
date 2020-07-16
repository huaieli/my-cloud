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
         * errorWithTx的事务不会生效
         * 因为noTxnoCatchInner并没有事务
         * AOP失效
         */
        // noCatchWithTx();

        /**
         * 都没事务，那肯定是不生效了
         */
        // noCatchWithoutTx();

        /**
         * 本来事务就失效，异常又被处理
         * 肯定是不生效了
         */
        catchWithTx();

        /**
         * 都没事务，那肯定是不生效了
         */
        catchWithoutTx();

    }

    public void noTxWithCatchInner() {
        try {
            /**
             * 这个地方是否catch已经不重要了
             * 因为我们发现该方法没有事务内部调用其他方法
             * 即便调用有事务的方法，事务也会失效
             */
        } catch (Exception e) {

        }
    }

    @Transactional
    public void withTxNoCatchInner() {
        /**
         * 都有事务，必然生效
         */
        // noCatchWithTx();

        /**
         * 内部方法无事务，且未处理异常
         * 异常抛出外部事务生效
         */
        // noCatchWithoutTx();

        /**
         * 内部方法处理了异常
         * 因此事务失效，数据被插入
         */
        catchWithTx();

        /**
         * 虽然内部方法被事务切入，但处理了异常
         * 因此事务失效，数据被插入
         */
        catchWithoutTx();
    }

    @Transactional
    public void withTxwithCatchInner() {
        try {
            /**
             * 都有事务，但是内部抛出的异常被外部处理
             * 事务不生效，数据被插入
             */
            noCatchWithTx();

            /**
             * 外部事务切入，但因为内部异常抛出后被外部处理
             * 事务不生效，数据被插入
             */
            // noCatchWithoutTx();

            /**
             * 都有事务都处理了异常
             * 事务肯定失效
             */
            // catchWithTx();

            /**
             * 都外部事务切入内部、都处理了异常
             * 事务肯定失效
             */
            // catchWithoutTx();

        } catch (Exception e) {

        }
    }

    public void noTxnoCatchOutter() {
        /**
         * 内部方法有事务且未捕获，
         * 外部方法虽然无事务但并没有入侵内部方法
         * 内部事务依然生效
         */
        // txHandler.noCatchWithTx();

        /**
         * 都没事务，那肯定是不生效了
         */
        // txHandler.noCatchWithoutTx();

        /**
         * 内部存在事务，但是异常被捕获
         * 事务不生效
         */
        txHandler.catchWithTx();

        /**
         * 都没事务，那肯定是不生效了
         */
        // txHandler.catchWithoutTx();

    }

    public void noTxWithCatchOutter() {
        try {
            /**
             * 与之前的场景一样，外部方法无事务，
             * 事务是否生效取决于内部方法
             * 因此外方法是否catch无关紧要
             */
        } catch (Exception e) {

        }
    }

    @Transactional
    public void withTxNoCatchOutter() {
        /**
         * 内外都有事务，内部方法抛出异常
         * 外部也并未处理
         * 事务生效，被回滚
         */
        // txHandler.noCatchWithTx();

        /**
         * 内部方法无事务，且未处理异常
         * 异常抛出外部事务生效
         */
        // txHandler.noCatchWithoutTx();

        /**
         * 内外部都有事务
         * 内部方法处理了异常
         * 因此事务失效，数据被插入
         */
        // txHandler.catchWithTx();

        /**
         * 内部没有事务且处理了异常，
         * 外部事务也失效，数据被插入
         */
        txHandler.catchWithoutTx();
    }

    @Transactional
    public void withTxwithCatchOutter() {
        try {
            /**
             * 都有事务，但是内部抛出异常将事务标记为回滚
             * 外部处理掉抛出的异常后要提交事务
             * 事务生效被回滚并抛出如下异常
             * org.springframework.transaction.UnexpectedRollbackException:
             * Transaction rolled back because it has been marked as rollback-only
             * 如何处理该问题
             * 1)修改内部事务传播为Propagation.REQUIRES_NEW（无论如何新建一个事务）
             * 2)外部方法不处理异常或者手动抛出RuntimeException
             * 3)外部方法捕获异常后手动回滚
             * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
             */
            txHandler.noCatchWithTx();

            /**
             * 内部方法无事务，且此时外部方法不会切入内部方法
             * 内部方法抛出异常被外部处理
             * 事务失效
             */
            // txHandler.noCatchWithoutTx();

            /**
             * 都有事务，但是内部方法处理了异常，没有标记事务为回滚
             * 因此事务失效，数据被插入
             */
            // txHandler.catchWithTx();

            /**
             * 内部没有事务，且异常被捕获
             * 因此虽然外部有事务，但是事务被正常提交
             * 数据被插入
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
