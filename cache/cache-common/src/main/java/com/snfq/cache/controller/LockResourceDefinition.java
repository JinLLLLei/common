package com.snfq.cache.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.snfq.base.dto.ResultDTO;
import com.snfq.cache.dto.CacheDTO;
import com.snfq.cache.dto.LockDTO;

public interface LockResourceDefinition {
	/**
	 * 请求锁
	 * @param key
	 * 					锁KEY
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/lock")
	public ResultDTO<Boolean> lock(@RequestBody LockDTO dto);
	
	/**
	 * 请求锁
	 * @param key
	 * 					锁KEY
	 * @param timeout
	 * 					锁超时
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/lockWithTimeout")
	public ResultDTO<Boolean> lockWithTimeout(@RequestBody LockDTO dto);
	
	/**
	 * 释放锁
	 * @param key
	 * 					锁KEY
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/unlock")
	public ResultDTO<Boolean> unlock(@RequestBody LockDTO dto);
}
