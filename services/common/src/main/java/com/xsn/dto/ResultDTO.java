package com.xsn.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultDTO(Integer code) {
        this.code = code;
        String message = CodeMap.STATE_CODE_LABEL.get(code);
        this.message = message == null ? "" : message;
    }

    public ResultDTO(Integer code, T data) {
        this.code = code;
        String message = CodeMap.STATE_CODE_LABEL.get(code);
        this.message = message == null ? "" : message;
        this.data = data;
    }

    public ResultDTO(Throwable e) {
        this.code = CodeMap.STATE_CODE_THROWABLE;
        this.message = e.getMessage();
    }

    public ResultDTO(Throwable e, String message) {
        this.code = CodeMap.STATE_CODE_THROWABLE;
        this.message = message + ":" + e.getMessage();
    }

    public boolean isSuccess() {
        return CodeMap.STATE_CODE_SUCCESS.equals(this.code);
    }

    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<T>(CodeMap.STATE_CODE_SUCCESS, data);
    }

    public static <T> ResultDTO<T> success() {
        return success(null);
    }
}
