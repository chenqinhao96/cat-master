package com.chenqinhao.cat.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenqinhao on 2017/7/21.
 */
public class CookieUtils {

    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(response, name, value, "/", maxAge);
    }

    public static void setCookie(HttpServletResponse response, String name) {
        setCookie(response, name, "", "/", 3600);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(name)) {
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        setCookie(response, name, "", "/", 0);
    }

}
