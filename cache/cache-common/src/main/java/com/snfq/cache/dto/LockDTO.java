package com.snfq.cache.dto;

public class LockDTO {
	private String key;
	private long lockTime;
	public LockDTO() {}
	public LockDTO(String key) {
		this.key = key;
	}
	public LockDTO(String key, long lockTime) {
		this.key = key;
		this.lockTime = lockTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getLockTime() {
		return lockTime;
	}
	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}
	@Override
	public String toString() {
		return "LockDTO [key=" + key + ", lockTime=" + lockTime + "]";
	}
}
