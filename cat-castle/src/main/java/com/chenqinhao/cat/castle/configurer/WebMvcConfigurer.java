package com.chenqinhao.cat.castle.configurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.chenqinhao.cat.castle.resovler.CentralizeHandlerExceptionResolver;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * springMVC配置
 * Created by chenqinhao on 2017/7/2.
 */
@Configuration
@EnableWebMvc
@PropertySource(value = "classpath:config/application.properties", ignoreResourceNotFound = true, encoding = "utf-8")
@ComponentScan(basePackages = "com.chenqinhao.cat",
        includeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
    }
)
public class WebMvcConfigurer extends WebMvcConfigurerAdapter{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${spring.async.timeout}")
    private Long asyncTimeout;

    /**
     * 路径匹配
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 允许有后缀
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    /**
     * 消息转换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    /**
     * 异常解析
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new CentralizeHandlerExceptionResolver());
    }

    /**
     * 异步支持
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(asyncTimeout);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(cnm);
        return resolver;
    }

    @Bean
    public ViewResolver beanNameResolver() {
        return new BeanNameViewResolver();
    }


    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

}
