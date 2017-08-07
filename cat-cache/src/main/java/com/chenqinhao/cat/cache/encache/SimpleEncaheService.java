package com.chenqinhao.cat.cache.encache;

import net.sf.ehcache.Cache;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

/**
 * Created by chenqinhao on 2017/7/21.
 */
@Service
public class SimpleEncaheService {

    @Autowired
    private EhCacheCacheManager cacheManager;

}
