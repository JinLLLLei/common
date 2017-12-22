package com.snfq.cache.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snfq.base.util.DateUtils;
import com.snfq.cache.redis.RedisCacheManager;
import com.snfq.cache.service.SequenceService;

/**
 * 序列号生成服务
 * @author zenghua
 *
 */
@Service
public class SequenceServiceImpl implements SequenceService {
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Override
	public String generate(String bizCode) {
		/** 获取今天的日期:yyyyMMdd */
        String date = DateUtils.getToday();
        /** 构造redis的key */
        String key = SERIAL_NUMBER + bizCode + date;
        /** 自增 */
        long sequence = redisCacheManager.incr(key);
        if (sequence == 1L ) {
        	//超时 1天，防止长时间运行积累过多的缓存
        	redisCacheManager.expire(key, (int) TimeUnit.SECONDS.convert(1, TimeUnit.DAYS));
        }
        String seq = getSequence(sequence);
        StringBuilder sb = new StringBuilder();
        sb.append(bizCode).append(date).append(seq);
        String serial = sb.toString();
        return serial;
	}
	
	/**
     * 得到10位的序列号,长度不足10位,前面补0
     * 
     * @param seq
     * @return
     */
    public static String getSequence(long seq) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= DEFAULT_LENGTH) {// 取决于业务规模,应该不会到达10
            return str;
        }
        int rest = DEFAULT_LENGTH - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
}
