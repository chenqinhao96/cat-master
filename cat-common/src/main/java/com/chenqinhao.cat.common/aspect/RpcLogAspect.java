package com.chenqinhao.cat.common.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenqinhao on 2017/7/21.
 */
public class RpcLogAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private long startTime = 0L;
    private long endTime = 0L;

    @Before("execution(* *..rpc..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        logger.debug("doBeforeInServiceLayer");
        startTime = System.currentTimeMillis();
    }

    @After("execution(* *..rpc..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        logger.debug("doAfterInServiceLayer");
        endTime = System.currentTimeMillis();
    }

    @Around("execution(* *..rpc..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        endTime = System.currentTimeMillis();
        // 是否消费端
        boolean consumerSide = RpcContext.getContext().isConsumerSide();
        String ip = RpcContext.getContext().getRemoteHost();
        String rpcUrl = RpcContext.getContext().getUrl().getParameter("application");
        logger.info("cosumerSide={}, ip={}, url={}, process- {}", consumerSide, ip, rpcUrl, endTime-startTime);
        return result;
    }


}
