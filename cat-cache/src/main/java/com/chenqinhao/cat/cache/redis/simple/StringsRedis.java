package com.chenqinhao.cat.cache.redis.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqinhao on 2017/7/16.
 */
@Component
public class StringsRedis<T> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setEX(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void pSetEX(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    public void setRange(String key, long offset, T value) {
        redisTemplate.opsForValue().set(key, value, offset);
    }


    public void setBit(String key, long offset, boolean value) {
        redisTemplate.opsForValue().setBit(key, offset, value);
    }


    public void setNX(String key, T value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    public T getSet(String key, T value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public List<T> mget(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public void append(String key, String value) {
        redisTemplate.opsForValue().append(key, value);
    }

    public void incr(String key) {
        incrBy(key, 1);
    }

    public void incrBy(String key, long increment) {
        redisTemplate.opsForValue().increment(key, increment);
    }

    public void incrByFloat(String key, double increment) {
        redisTemplate.opsForValue().increment(key, increment);
    }

    public void decr(String key) {
        incrBy(key, -1);
    }

    public void decrBy(String key, long increment) {
        incrBy(key, -increment);
    }

    public void decrByFloat(String key, double increment) {
        incrByFloat(key, increment);
    }

    public void mset(Map<String, T> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public void msetNX(Map<String, T> map) {
        redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    public void strlen(String key) {
        redisTemplate.opsForValue().size(key);
    }


}
