package com.snfq.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snfq.cache.redis.RedisCacheManager;
import com.snfq.cache.service.LockService;

/**
 * 锁服务
 * @author zenghua
 *
 */
@Service
public class LockServiceImpl implements LockService {
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Override
	public boolean lock(String bizCode) {
		String key = new StringBuilder(LOCK).append(bizCode).toString();
		if ( redisCacheManager.incr(key).longValue() == 1L ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean lock(String bizCode, Long timeout) {
		String key = new StringBuilder(LOCK).append(bizCode).toString();
		if ( redisCacheManager.incr(key).longValue() == 1L ) {
			redisCacheManager.expire(key, timeout.intValue());
			return true;
		}
		return false;
	}

	@Override
	public boolean unlock(String bizCode) {
		String key = new StringBuilder(LOCK).append(bizCode).toString();
		return redisCacheManager.del(key).longValue() == 1L;
	}

}
