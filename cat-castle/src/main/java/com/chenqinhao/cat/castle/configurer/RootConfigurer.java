package com.chenqinhao.cat.castle.configurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by chenqinhao on 2017/7/2.
 */
@Configuration
@ComponentScan(basePackages = {"com.chenqinhao.cat"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
    }
)
public class RootConfigurer {
}
