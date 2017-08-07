package com.chenqinhao.cat.royal.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.io.InputStream;
import java.util.Properties;

import static com.chenqinhao.cat.royal.constant.RoyalConstant.*;

/**
 * mybatis 配置
 * Created by chenqinhao on 2017/7/2.
 */
@Configuration
public class MybatisConfigurer {

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceBean() throws Exception{
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Properties properties = new Properties();
        try(InputStream is = resolver.getResource("classpath:database/druid.properties").getInputStream()) {
            properties.load(is);
        }
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        return dataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage(MODEL_PACKAGE);

        Properties confProperties = new Properties();
        try(InputStream confIs = resolver.getResource("classpath:mybatis/mybatis-config.properties").getInputStream()) {
            confProperties.load(confIs);
        }
        factory.setConfigurationProperties(confProperties);
        // 配置分页插件
        PageInterceptor pageHelper = new PageInterceptor();

        Properties pageProperties = new Properties();
        try(InputStream pageIs = resolver.getResource("classpath:mybatis/pagehelper.properties").getInputStream()){
            pageProperties.load(pageIs);
        }

        pageHelper.setProperties(pageProperties);

        // 添加插件
        factory.setPlugins(new Interceptor[]{pageHelper});

        factory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        return factory.getObject();
    }

    @Bean("mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage(MAPPER_PACKAGE);
        return configurer;
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManagerBean(DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

}
