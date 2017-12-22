package com.snfq.cache.redis.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snfq.cache.redis.RedisCacheManager;
import com.snfq.cache.redis.impl.RedisCacheManagerImpl;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;


/**
 * jedis自动配置
 * @author zenghua
 *
 */
@Configuration
@EnableConfigurationProperties(JedisProperties.class)
public class BaseJedisConfig {
	@Bean
	public JedisPool jedisPool(JedisPoolConfig config, JedisProperties jedisProperties){
		JedisPool jedisPool = null;
		if (jedisProperties.getTimeout() != null && jedisProperties.getRequirePass() != null) {
			jedisPool = new JedisPool(config, jedisProperties.getHost(), 
					jedisProperties.getPort(), jedisProperties.getTimeout(), jedisProperties.getRequirePass());
			return jedisPool;
		}
		if (jedisProperties.getRequirePass() != null) {
			jedisPool = new JedisPool(config, jedisProperties.getHost(), jedisProperties.getPort(), Protocol.DEFAULT_TIMEOUT, jedisProperties.getRequirePass());
			return jedisPool;
		}
		if (jedisProperties.getTimeout() != null) {
			jedisPool = new JedisPool(config, jedisProperties.getHost(), jedisProperties.getPort(), jedisProperties.getTimeout());
			return jedisPool;
		}
		return jedisPool;
	}
	
	@Bean
	public JedisPoolConfig jedisConfig(JedisProperties jedisProperties) {
		JedisPoolConfig config = new JedisPoolConfig();
		if (jedisProperties.getMaxTotal() != null) {
			config.setMaxTotal(jedisProperties.getMaxTotal());
		}
		if (jedisProperties.getMaxIdle() != null) {
			config.setMaxIdle(jedisProperties.getMaxIdle());
		}
		if (jedisProperties.getMinIdle() != null) {
			config.setMinIdle(jedisProperties.getMinIdle());
		}
		
		return config;
	}
	
	@Bean
	public RedisCacheManager redisCacheManager(JedisPool jedisPool) {
		return new RedisCacheManagerImpl(jedisPool);
	}
}
