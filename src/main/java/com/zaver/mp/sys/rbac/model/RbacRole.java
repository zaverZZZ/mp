package com.zaver.mp.sys.rbac.model;

import java.io.Serializable;

public class RbacRole implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer createBy;
	private Long createTime;
	private String description;
	private Integer id;
	private String name;
	private String roleKey;
	private Integer roleSort;
	private Integer status;
	private Integer updateBy;
	private Long updateTime;

	public Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(Integer createBy){
		this.createBy = createBy;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getRoleKey(){
		return roleKey;
	}
	public void setRoleKey(String roleKey){
		this.roleKey = roleKey;
	}
	public Integer getRoleSort(){
		return roleSort;
	}
	public void setRoleSort(Integer roleSort){
		this.roleSort = roleSort;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getUpdateBy(){
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy){
		this.updateBy = updateBy;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Long updateTime){
		this.updateTime = updateTime;
	}
}