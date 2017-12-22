package com.snfq.cache.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.snfq.base.dto.ResultDTO;

public interface SequenceResourceDefinition {
	    /**
	     * 根据两位业务码字符串,生成一个流水号,格式按照:<br/>
	     * {bizCode}yyyyMMdd{10位的自增序列号}
	     * 
	     * @param bizCode
	     *            两位,00-99
	     * @return 20位的序列号
	     * @throws ServiceException
	     */
	    @RequestMapping(method=RequestMethod.POST, value = "/generate")
	    ResultDTO<String> generate(@RequestParam("bizCode") String bizCode);
}
