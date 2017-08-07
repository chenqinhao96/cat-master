package com.chenqinhao.cat.cache.configurer;

import com.chenqinhao.cat.cache.resource.RedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 配置
 * Created by chenqinhao on 2017/7/15.
 *
 *
 *
 */
@Configuration
@CacheConfig
public class RedisConfigurer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public RedisConfigurer() {
        super();
    }

    @Bean(initMethod = "loadProperties")
    public RedisProperties redisPropertiesBean() {
        logger.debug("registry redisPropertiesBean...");
        return new RedisProperties();
    }

    @Bean("jedisPoolSingleConfig")
    public JedisPoolConfig jedisPoolSingleConfigBean(RedisProperties properties) {
        logger.debug("registry jedisPoolSingleConfigBean...");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        config.setBlockWhenExhausted(properties.getBlockWhenExhausted());
        config.setTestWhileIdle(properties.getTestWhileIdle());
        config.setMaxWaitMillis(properties.getMaxWaitMillis());
        config.setMaxTotal(properties.getMaxTotal());
        config.setMaxIdle(properties.getMaxIdle());
        config.setMinIdle(properties.getMinIdle());
        config.setNumTestsPerEvictionRun(properties.getNumTestsPerEvictionRun());
        config.setTestOnBorrow(properties.getTestOnBorrow());
        config.setSoftMinEvictableIdleTimeMillis(properties.getSoftMinEvictableTimeMillis());
        config.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        return config;
    }

    @Bean("redisSingleConnection")
    public RedisConnectionFactory redisSingleConnectionFactoryBean(JedisPoolConfig poolConfig, RedisProperties properties) {
        logger.debug("registry redisSingleConnectionFactoryBean...");
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setPassword(properties.getPass());
        cf.setHostName(properties.getHost());
        cf.setPort(properties.getPort());
        cf.setTimeout(properties.getTimeout());
        cf.setUsePool(properties.getUsePool());
        cf.setPoolConfig(poolConfig);
        return cf;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setEnableDefaultSerializer(false);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory cf) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(cf);
        redisTemplate.setEnableDefaultSerializer(false);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }
}
