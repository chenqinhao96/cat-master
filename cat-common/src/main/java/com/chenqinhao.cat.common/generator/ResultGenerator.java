package com.chenqinhao.cat.common.generator;

import com.chenqinhao.cat.common.base.BaseResult;
import com.chenqinhao.cat.common.enums.ResultCode;

/**
 * Created by chenqinhao on 2017/6/30.
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    public static BaseResult genSuccessResult() {
        return new BaseResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static BaseResult genSuccessResult(Object data) {
        return new BaseResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static BaseResult genFailResult(String message) {
        return new BaseResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

}
