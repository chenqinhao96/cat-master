package com.chenqinhao.cat.common.util.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chenqinhao on 2017/7/15.
 */
public class PropertiesReader {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static Properties properties = new Properties();

    private PropertiesReader(Resource resource) {
        try(InputStream is = resource.getInputStream()) {
            properties.load(is);
        } catch (IOException e) {
            logger.error("load resource throw IOException!", e);
        }
    }

    public static PropertiesReader loadProperties(Resource resource) {
        return new PropertiesReader(resource);
    }

    public String getString(String key) {
        return getString(key, null);
    }


    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public Integer getInteger(String key) {
        return getInteger(key, null);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        return Integer.valueOf(properties.getOrDefault(key, defaultValue).toString()) ;
    }

    public Long getLong(String key) {
        return getLong(key,  null);
    }


    public Long getLong(String key, Long defaultValue) {
        return Long.valueOf(properties.getOrDefault(key, defaultValue).toString());
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }


    public Boolean getBoolean(String key, Boolean defaultValue) {
        return Boolean.valueOf(properties.getOrDefault(key, defaultValue).toString());
    }

    public Short getShort(String key) {
        return getShort(key,null);
    }


    public Short getShort(String key, Short defaultValue) {
        return Short.valueOf(properties.getOrDefault(key, defaultValue).toString());
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    public Double getDouble(String key, Double defaultValue) {
        return Double.valueOf(properties.getOrDefault(key, defaultValue).toString());
    }

    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    public Float getFloat(String key, Float defaultValue) {
        return Float.valueOf(properties.getOrDefault(key, defaultValue).toString());
    }

}
