package com.chenqinhao.cat.castle.aop;

import com.chenqinhao.cat.common.base.BaseResult;
import com.chenqinhao.cat.common.enums.ResultCode;
import com.chenqinhao.cat.common.generator.ResultGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * 参数验证错误处理
 * Created by chenqinhao on 2017/7/4.
 */
@Component
@Aspect
public class BindingResultAop {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 切点
     */
    @Pointcut("execution(* *..web..*.*(..))")
    public void aopMethod(){}

    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        LOGGER.info("before BindingResultAop.around()");
        BindingResult result = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                result = (BindingResult) arg;
                break;
            }
        }
        if (result != null) {
            if (result.hasErrors()) {
                String error = result.getFieldError().getDefaultMessage();
                LOGGER.info(error);
                return ResultGenerator.genFailResult(error);
            }
        }
        return joinPoint.proceed();
    }

}
