package com.chenqinhao.cat.common.enums;

/**
 * 响应码
 * Created by chenqinhao on 2017/6/30.
 */
public enum ResultCode {
    SUCCESS(200), // 成功
    FAIL(400), // 失败
    UNAUTHORIZED(401), // 未认证
    NOT_FOUND(404), // 未找到
    INTERNAL_SERVER_ERROR(500); // 服务器内部错误

    public int code;

    ResultCode(int code) {
        this.code = code;
    }

}
