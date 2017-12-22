package com.snfq.cache.service;

import org.springframework.http.ResponseEntity;

public interface CaptchaService {
    public static final int DEFAULT_VERIFY_CODE_LENGTH = 4;
	public static final int DEFAULT_LENGTH = 6;
	public static final int DEFAULT_EXPIRE_SEC = 600;
	public static final String CAPATCHA_PREFIX = "capatcha.";
	
	String generateCode(String bizCode);

	ResponseEntity<byte[]> generate(String bizCode, int length, boolean includeLetter);

	boolean verifyCode(String bizCode, String code);

	boolean verify(String bizCode, String code);

}
