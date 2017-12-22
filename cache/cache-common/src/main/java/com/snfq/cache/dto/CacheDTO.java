package com.snfq.cache.dto;

public class CacheDTO {
	private String key;
	private String value;
	private int seconds;
	public CacheDTO() {}
	
	public CacheDTO(String key) {
		this.key = key;
	}
	
	public CacheDTO(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public CacheDTO(String key, String value, int seconds) {
		this.key = key;
		this.value = value;
		this.seconds = seconds;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public static CacheDTO me(String key) {
		return new CacheDTO(key);
	}
	
	public static CacheDTO me(String key, int seconds) {
		return new CacheDTO(key, null, seconds);
	}
	
	public static CacheDTO me(String key, String value) {
		return new CacheDTO(key, value);
	}
	
	public static CacheDTO me(String key, String value, int seconds) {
		return new CacheDTO(key, value, seconds);
	}
	
	@Override
	public String toString() {
		return "CacheDTO [key=" + key + ", value=" + value + ", seconds=" + seconds + "]";
	}
}
