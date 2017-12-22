package com.snfq.cache.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.snfq.base.dto.ResultDTO;
import com.snfq.cache.dto.CacheDTO;


public interface CacheResourceDefinition {
    /**
     * 设置缓存
     * @param key
     * 					缓存Key
     * @param value
     * 					缓存值
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/set")
    public ResultDTO<String> set(CacheDTO dto);

    /**
     * 设置缓存
     * @param key
     * 					缓存Key
     * @param value
     * 					缓存值
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/setWithExpire")
    public ResultDTO<String> setWithExpire(CacheDTO dto);

    /**
     * 设置缓存
     * @param key
     * 					缓存Key
     * @param seconds
     * 					过期时间（单位秒）
     * @param value
     * 					缓存值
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/setex")
    public ResultDTO<String> setex(CacheDTO dto);
    
    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/setnx")
    public ResultDTO<Long> setnx(CacheDTO dto);

    /**
     * 获取缓存
     * @param key
     * 					缓存key
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/get")
    public ResultDTO<String> get(CacheDTO dto);

    /**
     * 删除缓存
     * @param key
     * 					缓存key
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    public ResultDTO<Long> del(CacheDTO dto);

    /**
     * 自增INC
     * @param key
     * 					缓存key
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/incr")
    public ResultDTO<Long> incr(CacheDTO dto);
    
    /**
     * 设置缓存过期时间
     * @
     */
    @RequestMapping(method = RequestMethod.POST, value = "/expire")
    public ResultDTO<Long> expire(CacheDTO dto);

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     */
    @RequestMapping(method = RequestMethod.POST, value = "/ttl")
    public ResultDTO<Long> ttl(CacheDTO dto);
}
