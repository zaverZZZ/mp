package com.zaver.mp.rbac.model;

import java.io.Serializable;

public class RbacRole implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String roleId;
	private String name;
	private String description;
	private Integer status;
	private Long createTime;
	private Long updateTime;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
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
}