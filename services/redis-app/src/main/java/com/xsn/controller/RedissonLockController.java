package com.xsn.controller;

import com.xsn.config.RedissonLock;
import com.xsn.dto.CodeMap;
import com.xsn.dto.ResultDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class RedissonLockController {

    @Autowired
    private RedissonLock redissonLock;

    @RequestMapping(value = "/testLock", method = RequestMethod.GET)
    @ApiOperation(value = "testLock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lockName", value = "锁名")
    })
    public ResultDTO testLock(
            @RequestParam(value = "lockName", required = false, defaultValue = "defaultLock")
            String lockName
    ) {

        try {

            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    action(lockName);
                }, "lock-thread-" + i).start();
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultDTO(CodeMap.STATE_CODE_THROWABLE);
        }

        return ResultDTO.success();
    }

    private void action(String lockName) {
        redissonLock.lock(lockName);

        try {
            log.info(Thread.currentThread().getName() + "拿到了锁对象，开始处理业务");
            TimeUnit.SECONDS.sleep(5);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redissonLock.unlock(lockName);
            log.info(Thread.currentThread().getName() + "释放锁");
        }
    }
}
