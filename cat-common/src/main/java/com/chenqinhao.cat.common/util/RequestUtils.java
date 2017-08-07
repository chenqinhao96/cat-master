package com.chenqinhao.cat.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by chenqinhao on 2017/7/21.
 */
public class RequestUtils {

    public String removeParam(HttpServletRequest request, String paramName) {
        StringBuilder queryString = new StringBuilder("");
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.equals(paramName)) {
                continue;
            }
            if (queryString.equals("")) {
                queryString.append(key)
                        .append("=")
                        .append(request.getParameter(key));
            } else {
                queryString.append("&")
                        .append(key)
                        .append("=")
                        .append(request.getParameter(key));
            }
        }
        return queryString.toString();
    }
    public static String getBasePath(HttpServletRequest request) {
        StringBuffer basePath = new StringBuffer();
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        basePath.append(scheme);
        basePath.append("://");
        basePath.append(domain);
        if("http".equalsIgnoreCase(scheme) && 80 != port) {
            basePath.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            basePath.append(":").append(String.valueOf(port));
        }
        return basePath.toString();
    }


}
