package com.chenqinhao.cat.rpc.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

/**
 * Created by chenqinhao on 2017/7/22.
 */
@Configuration
public class RpcConfigurer {

    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        return exporter;
    }

    @Bean
    public HandlerMapping handlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/rpc.service", "httpInvokerServiceExporter");
        mapping.setMappings(mappings);
        return mapping;
    }

}
