package com.snfq.cache.service;

public interface SequenceService {
	static final int DEFAULT_LENGTH = 10;
	/**
     * 序列号自增序列
     */
    String SERIAL_NUMBER = "serial.number:";
    
	String generate(String bizCode);
}
