package com.snfq.cache.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.snfq.base.dto.ResultDTO;
import com.snfq.base.exception.BizException;

@Component
public class CaptchaResourceClient {
	private static final int DEFAULT_LENGTH = 6;
	@Autowired
	private CaptchaResourceFeignClient captchaResourceFeignClient;
	
	/**
	 * 生成验证码内容
	 * @param bizCode
	 * @return
	 */
	public String generateCode(String bizCode) {
		ResultDTO<String> result = captchaResourceFeignClient.generateCode(bizCode);
		if (!result.isOk()) {
			throw new BizException(result);
		}
		return result.getResult();
	}
	
	/**
	 * 生成验证码 (只包含数字)
	 * 默认生成6位验证码
	 * @param bizCode
	 * 					业务Code
	 * @return
	 */
	public ResponseEntity<byte[]> generate(String bizCode) {
		return this.generate(bizCode, DEFAULT_LENGTH);
	}
	
	/**
	 * 生成验证码 (只包含数字)
	 * @param bizCode
	 * 					业务Code
	 * @param length
	 * 					验证码长度
	 * @return
	 */
	public ResponseEntity<byte[]> generate(String bizCode, int length) {
		return this.generate(bizCode, length, false);
	}
	
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
	public ResponseEntity<byte[]> generate(String bizCode, int length, boolean includeLetter) {
        return captchaResourceFeignClient.generate(bizCode, length, includeLetter);
	}
	
	/**
	 * 验证验证码（校验失败不清除）
	 * @param bizCode
	 * @param code
	 * @return
	 */
	public boolean verifyCode(String bizCode, String code) {
		ResultDTO<Boolean> result = captchaResourceFeignClient.verifyCode(bizCode, code);
		if (!result.isOk()) {
			throw new BizException(result);
		}
		return result.getResult();
	}
	
	/**
	 * 验证验证码
	 * @param bizCode
	 * @param code
	 * @return
	 */
	public boolean verify(String bizCode, String code) {
		ResultDTO<Boolean> result = captchaResourceFeignClient.verify(bizCode, code);
		if (!result.isOk()) {
			throw new BizException(result);
		}
		return result.getResult();
	}
}
