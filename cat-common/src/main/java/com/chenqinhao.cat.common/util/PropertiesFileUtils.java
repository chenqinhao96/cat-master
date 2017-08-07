package com.chenqinhao.cat.common.util;

import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenqinhao on 2017/7/21.
 */
public class PropertiesFileUtils {

    private static final ConcurrentHashMap<String, PropertiesFileUtils> configMap = new ConcurrentHashMap<>();

    private Date loadTime = null;

    private ResourceBundle resourceBundle = null;

    private static final String NAME = "config";

    private static final Integer TIME_OUT = 60000;

    private PropertiesFileUtils(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesFileUtils getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesFileUtils getInstance(String name) {
        PropertiesFileUtils conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesFileUtils(name);
            configMap.put(name, conf);
        }
        if (new Date().getTime() - conf.getLoadTime().getTime() > TIME_OUT) {
            conf = new PropertiesFileUtils(name);
            configMap.put(name, conf);
        }
        return conf;
    }

    public String get(String key) {
        try {
            String value = resourceBundle.getString(key);
            return value;
        } catch (MissingResourceException e) {
            return "";
        }
    }

    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    public boolean getBool(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        }catch (MissingResourceException e) {
            return false;
        }
    }


    public Date getLoadTime() {
        return loadTime;
    }

}
