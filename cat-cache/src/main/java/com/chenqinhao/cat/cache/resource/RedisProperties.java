package com.chenqinhao.cat.cache.resource;


import com.chenqinhao.cat.common.util.resource.PropertiesReader;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by chenqinhao on 2017/7/15.
 */
public class RedisProperties {

    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private int timeout;
    private int numTestsPerEvictionRun;
    private int minEvictableIdleTimeMillis;
    private int timeBetweenEvictionRunsMillis;
    private int softMinEvictableTimeMillis;
    private int maxWaitMillis;
    private boolean testOnBorrow;
    private boolean testWhileIdle;
    private boolean blockWhenExhausted;
    private boolean usePool;
    private String host;
    private int port;
    private String pass;

    public void loadProperties() {
        PropertiesReader reader = PropertiesReader.loadProperties(new ClassPathResource("redis/redis.properties"));
        setHost(reader.getString("redis.host"));
        setPass(reader.getString("redis.pass"));
        setPort(reader.getInteger("redis.port"));
        setBlockWhenExhausted(reader.getBoolean("redis.pool.blockWhenExhausted"));
        setMaxIdle(reader.getInteger("redis.pool.maxIdle"));
        setMaxTotal(reader.getInteger("redis.pool.maxTotal"));
        setMinIdle(reader.getInteger("redis.pool.minIdle"));
        setMaxWaitMillis(reader.getInteger("redis.pool.maxWaitMillis"));
        setMinEvictableIdleTimeMillis(reader.getInteger("redis.pool.minEvictableIdleTimeMillis"));
        setNumTestsPerEvictionRun(reader.getInteger("redis.pool.numTestsPerEvictionRun"));
        setSoftMinEvictableTimeMillis(reader.getInteger("redis.pool.softMinEvictableTimeMillis"));
        setTestOnBorrow(reader.getBoolean("redis.pool.testOnBorrow"));
        setTestWhileIdle(reader.getBoolean("redis.pool.testWhileIdle"));
        setTimeBetweenEvictionRunsMillis(reader.getInteger("redis.pool.timeBetweenEvictionRunsMillis"));
        setTimeout(reader.getInteger("redis.pool.timeout"));
        setUsePool(reader.getBoolean("redis.pool.usePool"));
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getSoftMinEvictableTimeMillis() {
        return softMinEvictableTimeMillis;
    }

    public void setSoftMinEvictableTimeMillis(int softMinEvictableTimeMillis) {
        this.softMinEvictableTimeMillis = softMinEvictableTimeMillis;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public boolean getUsePool() {
        return usePool;
    }

    public void setUsePool(boolean usePool) {
        this.usePool = usePool;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
