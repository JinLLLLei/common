package com.snfq.cache.redis.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JedisProperties.JEDIS_PREFIX)
public class JedisProperties {
    public static final String JEDIS_PREFIX = "jedis";
    //    public static final String DEFAULT_HOST = "10.20.2.11";
    //    public static final int    DEFAULT_PORT = 6379;

    private String             host;
    private Integer            port;
    private Integer            maxTotal;
    private Integer            maxIdle;
    private Integer            minIdle;
    private Integer            timeout;
    private String             requirePass;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getRequirePass() {
        return requirePass;
    }

    public void setRequirePass(String requirePass) {
        this.requirePass = requirePass;
    }

    @Override
    public String toString() {
        return "JedisProperties [host=" + host + ", port=" + port + ", maxTotal=" + maxTotal
               + ", maxIdle=" + maxIdle + ", minIdle=" + minIdle + ", timeout=" + timeout
               + ", requirePass=" + requirePass + "]";
    }
}
