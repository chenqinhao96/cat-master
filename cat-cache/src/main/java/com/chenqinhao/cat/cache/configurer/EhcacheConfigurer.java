package com.chenqinhao.cat.cache.configurer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by chenqinhao on 2017/7/15.
 */
@Configuration
public class EhcacheConfigurer {


    @Bean
    public EhCacheCacheManager ehCacheManager(CacheManager cm) {
        return new EhCacheCacheManager(cm);
    }

    @Bean
    public EhCacheManagerFactoryBean ehCache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        // spring会使用CacheManager.create() , 否则 new CacheManager()
        ehCacheFactoryBean.setShared(true); // 单例
        return ehCacheFactoryBean;
    }

}
