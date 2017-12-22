package com.snfq.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snfq.base.dto.ResultDTO;
import com.snfq.cache.service.SequenceService;

@RestController
@RequestMapping("/sequences")
public class SequenceController implements SequenceResourceDefinition {
	@Autowired
	private SequenceService sequenceService;
	@Override
	public ResultDTO<String> generate(String bizCode) {
		return ResultDTO.ok(null, sequenceService.generate(bizCode));
	}
}
