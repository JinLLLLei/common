package com.snfq.cache.service;

public interface LockService {
	/**
     * 锁KEY前缀
     */
    String LOCK = "lock:";
    
    /**
     * 获取锁
     * @param bizCode
     * 					业务Code
     * @return
     */
    public boolean lock(String bizCode);
    
    /**
     * 获取锁
     * @param bizCode
     * 						业务Code
     * @param timeout
     * 						超时时间（单位秒）
     * @return
     */
    public boolean lock(String bizCode, Long timeout);
    
    /**
     * 释放锁
     * @param bizCode
     * 						业务Code
     * @return
     */
    public boolean unlock(String bizCode);
}
