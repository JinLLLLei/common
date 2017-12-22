package com.snfq.cache.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snfq.base.dto.ResultDTO;
import com.snfq.base.exception.BizException;
import com.snfq.cache.dto.LockDTO;

@Component
public class LockResourceClientStub {
	@Autowired
	private LockResourceClient lockResourceClient;
	
	/**
	 * 获取锁，不等待
	 * @param key
	 * @return
	 */
	public boolean lock(String key) {
		ResultDTO<Boolean> result = lockResourceClient.lock(new LockDTO(key));
		if (!result.isOk()) {
			throw new BizException(result);
		}
		if (result.isOk() && result.getResult()!=null) {
			return result.getResult();
		}
		return false;
	}
	
	/**
	 * 获取锁，如果当前锁已被占用则等待
	 * @param key
	 * 						锁KEY
	 * @param expireTime
	 * 						锁过期时间
	 * @return
	 */
	public boolean acquireLock(String key, long expireTime) {
		//自旋等待
		while(true) {
			if (lock(key, expireTime)) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean lock(String key, long timeout) {
		ResultDTO<Boolean> result = lockResourceClient.lock(new LockDTO(key, timeout));
		if (!result.isOk()) {
			throw new BizException(result);
		}
		if (result.isOk() && result.getResult()!=null) {
			return result.getResult();
		}
		return false;
	}
	
	public boolean unlock(String key) {
		ResultDTO<Boolean> result = lockResourceClient.unlock(new LockDTO(key));
		if (!result.isOk()) {
			throw new BizException(result);
		}
		if (result.isOk() && result.getResult()!=null) {
			return result.getResult();
		}
		return false;
	}
}
