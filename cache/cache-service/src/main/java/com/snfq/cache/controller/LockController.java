package com.snfq.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snfq.base.dto.ResultDTO;
import com.snfq.cache.dto.LockDTO;
import com.snfq.cache.service.LockService;

@RestController
@RequestMapping("/locks")
public class LockController implements LockResourceDefinition {
	
	@Autowired
	private LockService lockService;
	
	@Override
	public ResultDTO<Boolean> lock(@RequestBody LockDTO dto) {
		return ResultDTO.ok(null, lockService.lock(dto.getKey()));
	}

	@Override
	public ResultDTO<Boolean> lockWithTimeout(@RequestBody LockDTO dto) {
		return ResultDTO.ok(null, lockService.lock(dto.getKey(), dto.getLockTime()));
	}

	@Override
	public ResultDTO<Boolean> unlock(@RequestBody LockDTO dto) {
		return ResultDTO.ok(null, lockService.unlock(dto.getKey()));
	}
}
