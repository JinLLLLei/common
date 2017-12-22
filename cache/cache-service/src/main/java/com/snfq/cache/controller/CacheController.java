package com.snfq.cache.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snfq.base.dto.ResultDTO;
import com.snfq.base.exception.BizException;
import com.snfq.cache.dto.CacheDTO;
import com.snfq.cache.redis.RedisCacheManager;

@RestController
@RequestMapping("/caches")
public class CacheController implements CacheResourceDefinition {
	private static Logger logger = LoggerFactory.getLogger(CacheController.class);
    @Autowired
    private RedisCacheManager redisCacheManager;
    
    @RestController
    public class VersionControler {
        @Value("${build.version}")
        String version;

        @GetMapping("/version")
        public String version() {
            return version;
        }
    }
    
    @Override
    public ResultDTO<String> set(@RequestBody CacheDTO dto) {
        if (StringUtils.isEmpty(dto.getKey())) {
            throw new BizException("-1", "错误");
        }
        String result = redisCacheManager.set(dto.getKey(), dto.getValue());
        return ResultDTO.ok(null, result);
    }

    @Override
    public ResultDTO<String> setWithExpire(@RequestBody CacheDTO dto) {
    	String result = redisCacheManager.setex(dto.getKey(), dto.getSeconds(), dto.getValue());
        return ResultDTO.ok(null, result);
    }

    @Override
    public ResultDTO<String> setex(@RequestBody CacheDTO dto) {
    	String result = redisCacheManager.setex(dto.getKey(), dto.getSeconds(), dto.getValue());
        return ResultDTO.ok(null, result);
    }
    
    @Override
    public ResultDTO<Long> setnx(@RequestBody CacheDTO dto) {
    	Long result = redisCacheManager.setnx(dto.getKey(), dto.getValue());
        return ResultDTO.ok(null, result);
    }

    @Override
    public ResultDTO<String> get(@RequestBody CacheDTO dto) {
    	String result = redisCacheManager.get(dto.getKey());
        return ResultDTO.ok(null, result);
    }

    @Override
    public ResultDTO<Long> del(@RequestBody CacheDTO dto) {
    	Long result = redisCacheManager.del(dto.getKey());
        return ResultDTO.ok(null, result);
    }

    @Override
    public ResultDTO<Long> incr(@RequestBody CacheDTO dto) {
    	Long result = redisCacheManager.incr(dto.getKey());
        return ResultDTO.ok(null, result);
    }
    
	@Override
	public ResultDTO<Long> expire(@RequestBody CacheDTO dto) {
		Long result = redisCacheManager.expire(dto.getKey(), dto.getSeconds());
		return ResultDTO.ok(null, result);
	}

    @Override
    public ResultDTO<Long> ttl(@RequestBody CacheDTO dto) {
        return ResultDTO.ok(null, redisCacheManager.ttl(dto.getKey()));
    }
}
