package com.chenqinhao.cat.cache.redis;

import java.util.List;

/**
 * Created by chenqinhao on 2017/7/16.
 */
public interface BaseRedis<T> {

    <T> boolean put(String key, T object);

    <T> boolean put(String key, T object, long timeout);

    <T> boolean putList(String key, List<T> list);

    <T> boolean putList(String key, List<T> list, long timeout);

    <T> T get(String key, Class<T> clazz);

    <T> List<T> getList(String key, Class<T> clazz);

    void delete(String key);

    void deleteWithPattern(String pattern);

    void clear();
}