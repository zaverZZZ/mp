package com.zaver.mp.sys.support.model;

import java.io.Serializable;

public class SysLog implements Serializable{

    private static final long serialVersionUID = 1L;

	private Long createDate;
	private Integer id;
	private String ip;
	private String method;
	private String operation;
	private String params;
	private Long time;
	private Integer userId;
	private String username;

	public Long getCreateDate(){
		return createDate;
	}
	public void setCreateDate(Long createDate){
		this.createDate = createDate;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getMethod(){
		return method;
	}
	public void setMethod(String method){
		this.method = method;
	}
	public String getOperation(){
		return operation;
	}
	public void setOperation(String operation){
		this.operation = operation;
	}
	public String getParams(){
		return params;
	}
	public void setParams(String params){
		this.params = params;
	}
	public Long getTime(){
		return time;
	}
	public void setTime(Long time){
		this.time = time;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
}