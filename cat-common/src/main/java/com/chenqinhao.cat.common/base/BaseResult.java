package com.chenqinhao.cat.common.base;

import com.chenqinhao.cat.common.enums.ResultCode;

/**
 * 返回结果类
 * Created by chenqinhao on 2017/6/23.
 */
public class BaseResult {

    // 1为成功, 其他为失败
    public int code;

    // 成功时为success, 其他为失败原因
    public String message;

    // 数据结果集
    public Object data;

    public BaseResult setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public BaseResult() {
    }

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public BaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseResult setData(Object data) {
        this.data = data;
        return this;
    }
}
