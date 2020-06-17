package com.xsn.dto;

import java.util.HashMap;
import java.util.Map;

public class CodeMap {
    /**操作成功*/
    public static final Integer STATE_CODE_SUCCESS = 200;

    /**出现异常*/
    public static final Integer STATE_CODE_THROWABLE = 500;

    /**参数异常*/
    public static final Integer PARAM_CHECK_ERROR = 210;

    /**数据已存在*/
    public static final Integer ALREADY_EXIST_DATA = 211;

    /**状态码描述静态map*/
    public final static Map<Integer, String> STATE_CODE_LABEL = new HashMap<Integer, String>();

    static {
        STATE_CODE_LABEL.put(STATE_CODE_SUCCESS, "操作成功");
        STATE_CODE_LABEL.put(STATE_CODE_THROWABLE, "系统异常");

        STATE_CODE_LABEL.put(PARAM_CHECK_ERROR, "参数异常");
        STATE_CODE_LABEL.put(ALREADY_EXIST_DATA, "数据已存在");
    }
}
