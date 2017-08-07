package com.chenqinhao.cat.cache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqinhao on 2017/7/16.
 */
@Component
public class RedisUtils<T> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, T object) {
        set(key, object, -1);
    }

    public void set(String key, T object, long timeout) {
        byte[] bytes = ProtoStuffSerializerUtils.serialize(object);
        redisTemplate.boundValueOps(key).set(bytes, timeout, TimeUnit.SECONDS);
    }

    public T get(String key, Class<T> clazz) {
        byte[] bytes = (byte[]) redisTemplate.boundValueOps(key).get();
        return ProtoStuffSerializerUtils.deserialize(bytes, clazz);
    }

    public void sadd(String key, T... objects) {
        byte[][] rawValues = rawValues(objects);
        redisTemplate.boundSetOps(key).add(rawValues);
    }

    public Set<T> smembers(String key, Class<T> clazz) {
        Set<T> values = null;
        Set<Object> sets = redisTemplate.boundSetOps(key).members();
        if (sets != null) {
            values = new LinkedHashSet<T>(sets.size());
            for (Object s: sets) {
                if (s instanceof byte[]) {
                    byte[] bytes = (byte[]) s;
                    values.add(ProtoStuffSerializerUtils.deserialize(bytes, clazz));
                }
            }
        }
        return values;
    }

    private byte[] rawValue(T value) {
        return ProtoStuffSerializerUtils.serialize(value);
    }

    private byte[][] rawValues(T... values) {
        byte[][] rawValues = new byte[values.length][];
        int i = 0;
        for (T value: values) {
            rawValues[i++] = rawValue(value);
        }
        return rawValues;
    }
}
