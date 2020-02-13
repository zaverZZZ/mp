package com.zaver.mp.wechat;

/**
 * wx js 所需配置
 * @author hamlt
 * @date 2017年7月18日
 */
public class WxJsConfig {
	private String signature;
	private long timestamp;
	private String noncestr;
	private long expireTime;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
}
