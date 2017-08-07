package com.chenqinhao.cat.common.base;

import com.chenqinhao.cat.common.util.IpUtils;
import com.chenqinhao.cat.common.util.PropertiesFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * Created by chenqinhao on 2017/6/23.
 */
public abstract class BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static String jsp(String path) {
        return path.concat(".jsp");
    }

    public static String thymeleaf(String path) {
        String folder = PropertiesFileUtils.getInstance().get("app.name");
        return "/".concat(folder).concat(path).concat(".html");
    }
}
