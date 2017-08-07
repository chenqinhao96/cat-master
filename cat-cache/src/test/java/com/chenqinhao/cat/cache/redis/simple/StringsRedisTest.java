package com.chenqinhao.cat.cache.redis.simple;

import com.chenqinhao.cat.cache.configurer.RedisConfigurerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by chenqinhao on 2017/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfigurerTest.class)
public class StringsRedisTest {

    @Autowired
    private StringsRedis<String> stringsRedis;

    @Test
    public void set() throws Exception {
        stringsRedis.set("stringRedis", "hello string redis");
    }

    @Test
    public void get() throws Exception {
        String value = stringsRedis.get("stringRedis");
    }

}