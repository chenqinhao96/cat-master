package com.chenqinhao.cat.castle.resovler;

import com.alibaba.fastjson.JSON;
import com.chenqinhao.cat.common.base.BaseResult;
import com.chenqinhao.cat.common.enums.ResultCode;
import com.chenqinhao.cat.common.exception.ServiceException;
import com.chenqinhao.cat.common.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常集中处理
 * Created by chenqinhao on 2017/6/30.
 */
public class CentralizeHandlerExceptionResolver implements HandlerExceptionResolver{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.error("Correct unified exception handling, ip={}",  IpUtils.getIpAddress(request));
        BaseResult result = new BaseResult();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (ex instanceof ServiceException) { // 业务异常
                result.setCode(ResultCode.FAIL)
                        .setMessage(ex.getMessage());
                LOGGER.info(ex.getMessage());
            } else {
                result.setCode(ResultCode.INTERNAL_SERVER_ERROR)
                        .setMessage("接口 [" + request.getRequestURI() + "] 出错");
                String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        ex.getMessage());
                LOGGER.error(message, ex);
            }
        } else {
            if (ex instanceof NoHandlerFoundException) {
                result.setCode(ResultCode.NOT_FOUND)
                        .setMessage("接口 [" + request.getRequestURI() + "] 不存在");
            } else {
                result.setCode(ResultCode.INTERNAL_SERVER_ERROR)
                        .setMessage(ex.getMessage());
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        reponseRessult(response, result);
        return new ModelAndView();
    }

    private void reponseRessult(HttpServletResponse response, BaseResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.flush();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

}
