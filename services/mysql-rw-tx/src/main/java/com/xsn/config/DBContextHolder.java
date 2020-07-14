package com.xsn.config;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

@Slf4j
public class DBContextHolder {

    private static ThreadLocal<DataSourceType> threadLocal = new ThreadLocal<>();

    private static void set(DataSourceType dataSourceType) {
        threadLocal.set(dataSourceType);
    }

    public static DataSourceType get() {
        return threadLocal.get() == null ? DataSourceType.MASTER : threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void slave() {
        SecureRandom secureRandom = new SecureRandom();
        int i = secureRandom.nextInt(DataSourceType.values().length - 1) + 1;
        log.info("使用从库" +i+ "读取");
        set(DataSourceType.values()[i]);
    }
}
