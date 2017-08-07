package com.chenqinhao.cat.cache.configurer;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * #root.args 传递给缓存的参数, 形式为数组
 * #root.caches 该方法所执行所对应的缓存, 形式为数组
 * #root.target 目标对象
 * #root.targetClass 目标对象的类, 即#root.target.class
 * #root.method 缓存方法
 * #root.methodName 缓存方法的名字, 即#root.method.name
 * #result 方法调用返回值
 * #Argument 任意方法参数名, 或者参数索引(#a #p0)
 *
 *
 * @Cacheale
 * @CachePut
 * @CacheEvict
 * @Caching 分组的注解
 *
 *  value       String[] 缓存 名称
 *  condition   String 是否使用缓存
 *  key         String 计算自定义的缓存key
 *  unless      String 如果为true, 返回值不放缓存
 *
 * Created by chenqinhao on 2017/7/16.
 */
@Configuration
@ComponentScan(basePackages = "com.chenqinhao.cat.cache")
@PropertySource(value = "application", ignoreResourceNotFound = true, encoding = "utf-8")
@EnableCaching
public class RedisConfigurerTest {


}