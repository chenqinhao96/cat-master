package com.chenqinhao.cat.cache.aspect;

import com.chenqinhao.cat.cache.annotation.RedisCacheKey;
import com.chenqinhao.cat.cache.annotation.RedisCacheable;
import com.chenqinhao.cat.cache.util.ProtoStuffSerializerUtils;
import com.chenqinhao.cat.cache.util.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import static com.chenqinhao.cat.cache.annotation.RedisCacheable.KeyMode;
/**
 * Created by chenqinhao on 2017/7/16.
 */
@Aspect
@Component
public class RedisCacheableAspect {

    @Autowired
    private RedisUtils redisUtils;

    @Around("@annotation(cache)")
    public Object cached(ProceedingJoinPoint jp, RedisCacheable cache) throws Throwable {
        String key = getCacheKey(jp, cache);
        String signature = jp.getSignature().toLongString();
        String returnType = signature.split(" ")[1];

        Object value = redisUtils.get(key, Class.forName(returnType));
        if (value != null) {
            return value;
        }
        value = jp.proceed();

        if (value == null) {
            return value;
        }

        if (cache.expire() <= 0) {
            redisUtils.set(key, value);
        } else {
            redisUtils.set(key, value, cache.expire());
        }

        return value;
    }

    private String getCacheKey(ProceedingJoinPoint jp, RedisCacheable cache) {
        StringBuilder builder = new StringBuilder();
        builder.append(jp.getSignature().getDeclaringTypeName())
                .append(".")
                .append(jp.getSignature().getName());
        String keyStr = cache.key();
        if (!(cache.keyMode() == KeyMode.BEAN)) {
            if (keyStr.length() > 0) {
                builder.append(".").append(keyStr);
            }
        }

        Object[] args = jp.getArgs();
        if (cache.keyMode() == KeyMode.DEFAULT) {
            if (jp.getSignature() instanceof MethodSignature) {
                Annotation[][] ans = ((MethodSignature)jp.getSignature()).getMethod().getParameterAnnotations();
                for (int i = 0; i < ans.length; i++) {
                    for (Annotation an: ans[i]) {
                        if (an instanceof RedisCacheKey) {
                            builder.append(".").append(args[i].toString());
                            break;
                        }
                    }
                }
            } else if (cache.keyMode() == KeyMode.BASIC) {
                for(Object arg:args) {
                    if(arg instanceof String) {
                        builder.append(".").append(arg);
                    } else if(arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
                        builder.append(".").append(arg.toString());
                    } else if(arg instanceof Boolean) {
                        builder.append(".").append(arg.toString());
                    }
                }
            } else if(cache.keyMode()== KeyMode.ALL) {
                for(Object arg : args) {
                    builder.append(".").append(arg.toString());
                }
            } else if (cache.keyMode() == KeyMode.BEAN) {
                if(keyStr.length()>0) {
                    Object target = args[0];
                    Field field = ReflectionUtils.findField(target.getClass(), keyStr);
                    ReflectionUtils.makeAccessible(field);
                    Object value = ReflectionUtils.getField(field, target);
                    builder.append("#").append(value);
                }
            } else if (cache.keyMode()==KeyMode.MAP) {
                if (keyStr.length()>0) {
                    Map target = (Map)args[0];
                    builder.append("#").append(target.get(keyStr));
                }
            }
        }
        return builder.toString();
    }


}
