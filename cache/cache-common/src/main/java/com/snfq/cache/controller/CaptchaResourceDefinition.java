package com.snfq.cache.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.snfq.base.dto.ResultDTO;

public interface CaptchaResourceDefinition {
	/**
	 * 生成验证码内容
	 * @param bizCode
	 * @return
	 */
	@RequestMapping(value="/code", method=RequestMethod.POST)
	public ResultDTO<String> generateCode(@RequestParam("bizCode") String bizCode);
	
	/**
	 * 生成验证码
	 * @param bizCode
	 * 					业务Code
	 * @param length
	 * 					验证码长度
	 * @param includeLetter
	 * 					是否包含字母
	 * @return
	 */
	@RequestMapping(value="/image", method=RequestMethod.POST)
	public ResponseEntity<byte[]> generate(@RequestParam("bizCode") String bizCode, @RequestParam("length") int length, @RequestParam("includeLetter") boolean includeLetter);
	
	/**
	 * 验证验证码（校验失败不清除）
	 * @param bizCode
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/verifyCode", method=RequestMethod.POST)
	public ResultDTO<Boolean> verifyCode(@RequestParam("bizCode") String bizCode, @RequestParam("code") String code);
	
	/**
	 * 验证验证码
	 * @param bizCode
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/verifyImage", method=RequestMethod.POST)
	public ResultDTO<Boolean> verify(@RequestParam("bizCode") String bizCode, @RequestParam("code") String code);
	
}
