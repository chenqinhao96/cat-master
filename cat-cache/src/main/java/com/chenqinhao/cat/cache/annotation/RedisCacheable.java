package com.chenqinhao.cat.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chenqinhao on 2017/7/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RedisCacheable {

    enum KeyMode {
        DEFAULT, // 只有加了@CacheKey的参数,才加入key后缀中
        BASIC, // 只有基本类型参数,才加入key后缀中,如:String,Integer,Long,Short,Boolean
        ALL, // 所有参数都加入key后缀
        BEAN, // bean的属性加入KEY后缀
        MAP; // Map的属性加入KEY后缀
    }
    String key() default "";
    KeyMode keyMode() default KeyMode.DEFAULT;
    int expire() default 7200;
}
