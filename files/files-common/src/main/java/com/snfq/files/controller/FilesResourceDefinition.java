package com.snfq.files.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.snfq.base.dto.ResultDTO;

public interface FilesResourceDefinition {
	@RequestMapping(value="/{id}/path", method=RequestMethod.GET)
	public ResultDTO<String> getFilePath(@PathVariable("id") String id);
	@RequestMapping(value="/temp/path", method=RequestMethod.GET)
	public ResultDTO<String> getTempSavePath();
}
