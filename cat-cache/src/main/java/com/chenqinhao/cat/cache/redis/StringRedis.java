package com.chenqinhao.cat.cache.redis;

import com.chenqinhao.cat.cache.util.ProtoStuffSerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by chenqinhao on 2017/7/16.
 */
@Component
public class StringRedis implements BaseRedis<String>{

    public final static String CACHE_NAME = "cache:redis:str";
    public final static long TIMEOUT = 7200;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public <T> boolean put(String key, T object) {
        byte[] bkey = key.getBytes();
        byte[] bvalue = ProtoStuffSerializerUtils.serialize(object);
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
             return connection.setNX(bkey, bvalue);
        });
    }

    public <T> boolean put(String key, T object, long timeout) {
        byte[] bkey = key.getBytes();
        byte[] bvalue = ProtoStuffSerializerUtils.serialize(object);
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(bkey, timeout, bvalue);
            return true;
        });
    }

    public <T> boolean putList(String key, List<T> list) {
        byte[] bkey = key.getBytes();
        byte[] bvalue = ProtoStuffSerializerUtils.serializeList(list);
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.setNX(bkey, bvalue);
        });
    }

    public <T> boolean putList(String key, List<T> list, long timeout) {
        byte[] bkey = key.getBytes();
        byte[] bvalue = ProtoStuffSerializerUtils.serializeList(list);
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(bkey, timeout, bvalue);
            return true;
        });
    }

    public <T> T get(String key, Class<T> clazz) {
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
            return connection.get(key.getBytes());
        });
        if (result == null) {
            return null;
        }
        return ProtoStuffSerializerUtils.deserialize(result, clazz);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
           return connection.get(key.getBytes());
        });
        if (result == null) {
            return null;
        }
        return ProtoStuffSerializerUtils.deserializeList(result, clazz);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteWithPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    public void clear() {
        deleteWithPattern(StringRedis.CACHE_NAME + "|*");
    }

}
