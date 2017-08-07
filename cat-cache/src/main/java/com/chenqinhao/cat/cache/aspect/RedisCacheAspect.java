package com.chenqinhao.cat.cache.aspect;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheAspectSupport;

/**
 * Created by chenqinhao on 2017/7/22.
 */
public class RedisCacheAspect extends CacheAspectSupport {


    @Override
    protected Cache.ValueWrapper doGet(Cache cache, Object key) {
        return super.doGet(cache, key);
    }

    @Override
    protected void doPut(Cache cache, Object key, Object result) {
        super.doPut(cache, key, result);
    }

    @Override
    protected void doEvict(Cache cache, Object key) {
        super.doEvict(cache, key);
    }

    @Override
    protected void doClear(Cache cache) {
        super.doClear(cache);
    }
}
