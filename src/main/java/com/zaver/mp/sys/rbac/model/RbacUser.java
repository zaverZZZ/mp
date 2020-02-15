package com.zaver.mp.sys.rbac.model;

import java.io.Serializable;

public class RbacUser implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String phone;
	private Integer sex;
	private Integer age;
	private Integer status;
	private Long createTime;
	private Long updateTime;
	private Long lastLoginTime;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getSalt(){
		return salt;
	}
	public void setSalt(String salt){
		this.salt = salt;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public Integer getSex(){
		return sex;
	}
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public Integer getAge(){
		return age;
	}
	public void setAge(Integer age){
		this.age = age;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Long updateTime){
		this.updateTime = updateTime;
	}
	public Long getLastLoginTime(){
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
}