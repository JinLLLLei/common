package com.snfq.cache.client;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.snfq.base.dto.ResultDTO;
import com.snfq.base.exception.BizException;
import com.snfq.cache.dto.CacheDTO;

@Component
public class CacheResourceClientStub {
    @Autowired
    private CacheResourceClient cacheResourceClient;

    public ResultDTO<String> set(String key, Object value) {
        return cacheResourceClient.set(CacheDTO.me(key, JSON.toJSONString(value)));
    }

    public ResultDTO<String> setex(String key, int seconds, Object value) {
        return cacheResourceClient.setex(CacheDTO.me(key, JSON.toJSONString(value), seconds));
    }
    
    public ResultDTO<Long> setnx(String key, Object value) {
        return cacheResourceClient.setnx(CacheDTO.me(key, JSON.toJSONString(value)));
    }

    public <T> T get(String key, Class<T> clazz) {
        ResultDTO<String> result = cacheResourceClient.get(CacheDTO.me(key));
        if (result.isOk()) {
            if (!StringUtils.isEmpty(result.getResult())) {
                return JSON.parseObject(result.getResult(), clazz);
            }
        } else {
            throw new BizException(result);
        }
        return null;
    }

    public ResultDTO<Long> del(String key) {
        return cacheResourceClient.del(CacheDTO.me(key));
    }

    public ResultDTO<Long> incr(String key) {
        return cacheResourceClient.incr(CacheDTO.me(key));
    }
    
    public ResultDTO<Long> expire(String key, int seconds) {
        return cacheResourceClient.expire(CacheDTO.me(key, seconds));
    }

    public ResultDTO<Long> ttl(String key) {
        return cacheResourceClient.ttl(CacheDTO.me(key));
    }
}
