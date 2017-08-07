package com.chenqinhao.cat.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqinhao on 2017/7/15.
 */
public class ThreadLocalUtils {

    private static final ThreadLocal<Map> ctx = new ThreadLocal();

    public static void put(Object key, Object value) {
        Map map = ctx.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(key, value);
        ctx.set(map);
    }

    public static Object get(Object key) {
        Map map = ctx.get();
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

}
