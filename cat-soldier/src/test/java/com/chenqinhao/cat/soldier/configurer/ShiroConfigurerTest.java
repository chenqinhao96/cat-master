package com.chenqinhao.cat.soldier.configurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by chenqinhao on 2017/7/17.
 */
@Configuration
@ComponentScan("com.chenqinhao.cat")
@PropertySource(value = "application", ignoreResourceNotFound = true, encoding = "utf-8")
public class ShiroConfigurerTest {
}