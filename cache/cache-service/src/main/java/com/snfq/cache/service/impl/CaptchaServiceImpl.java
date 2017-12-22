package com.snfq.cache.service.impl;

import java.io.ByteArrayOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.snfq.base.common.ErrorCodeEnum;
import com.snfq.base.exception.BizException;
import com.snfq.cache.captcha.VerifyCodeUtil;
import com.snfq.cache.redis.RedisCacheManager;
import com.snfq.cache.service.CaptchaService;

/**
 * 验证码服务
 * @author zenghua
 *
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
	@Autowired
	private RedisCacheManager redisCacheManager;
	@Override
	public String generateCode(String bizCode) {
		String code = redisCacheManager.get(CAPATCHA_PREFIX+bizCode);
		if (code == null) {
			code = VerifyCodeUtil.generateVerifyCode(DEFAULT_VERIFY_CODE_LENGTH, false);
		}
		redisCacheManager.setex(CAPATCHA_PREFIX+bizCode, DEFAULT_EXPIRE_SEC, code);
		return code;
	}

	@Override
	public ResponseEntity<byte[]> generate(String bizCode, int length, boolean includeLetter) {
		String code = VerifyCodeUtil.generateVerifyCode(length, includeLetter);
		String result = redisCacheManager.setex(CAPATCHA_PREFIX+bizCode, DEFAULT_EXPIRE_SEC, code);
		if (!RedisCacheManager.OK.equals(result)) {
			throw new BizException(ErrorCodeEnum.CACHE_ERROR);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int w = 45, h = 80;
		try {
			VerifyCodeUtil.outputImage(w * code.length(), h, baos, code);
		} catch (Exception e) {
		}
		HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/jpeg"));
        //禁止图像缓存。  
        headers.setPragma("no-cache");  
        headers.setCacheControl("no-cache");  
        headers.setDate("Expires", 0);  
        return new ResponseEntity<byte[]>(baos.toByteArray(),headers,HttpStatus.OK);//
	}

	@Override
	public boolean verifyCode(String bizCode, String code) {
		String captcha = redisCacheManager.get(CAPATCHA_PREFIX+bizCode);
		if (StringUtils.equals(code, captcha)) {
			redisCacheManager.del(CAPATCHA_PREFIX+bizCode);
			return true;
		} 
		return false;
	}

	@Override
	public boolean verify(String bizCode, String code) {
		String captcha = redisCacheManager.get(CAPATCHA_PREFIX+bizCode);
		redisCacheManager.del(CAPATCHA_PREFIX+bizCode);
		if (StringUtils.equals(code, captcha)) {
			return true;
		}
		return false;
	}

}
