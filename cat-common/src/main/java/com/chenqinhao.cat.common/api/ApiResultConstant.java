package com.chenqinhao.cat.common.api;

/**
 * Created by chenqinhao on 2017/9/3.
 */
public enum ApiResultConstant {

    SUCCESS(1, "success");

    private int code;

    private String message;

    ApiResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
