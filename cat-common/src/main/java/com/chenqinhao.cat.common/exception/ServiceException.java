package com.chenqinhao.cat.common.exception;

/**
 * 业务异常
 * Created by chenqinhao on 2017/7/1.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
