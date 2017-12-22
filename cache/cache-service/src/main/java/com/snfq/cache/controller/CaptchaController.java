package com.snfq.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snfq.base.dto.ResultDTO;
import com.snfq.cache.service.CaptchaService;

@RestController
@RequestMapping("/captchas")
public class CaptchaController implements CaptchaResourceDefinition {
	@Autowired
	private CaptchaService captchaService;
	
	@Override
	public ResultDTO<String> generateCode(String bizCode) {
		return ResultDTO.ok(captchaService.generateCode(bizCode));
	}

	@Override
	public ResponseEntity<byte[]> generate(String bizCode, int length, boolean includeLetter) {
		return captchaService.generate(bizCode, length, includeLetter);
	}

	@Override
	public ResultDTO<Boolean> verifyCode(String bizCode, String code) {
		return ResultDTO.ok(captchaService.verifyCode(bizCode, code));
	}

	@Override
	public ResultDTO<Boolean> verify(String bizCode, String code) {
		return ResultDTO.ok(captchaService.verify(bizCode, code));
	}
}
