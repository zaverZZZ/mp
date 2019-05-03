package com.zaver.mp.rbac.model;

import java.io.Serializable;

public class RbacPermission implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String permissionId;
	private String name;
	private String description;
	private String url;
	private String perms;
	private Integer parentId;
	private Integer type;
	private Integer orderNum;
	private String icon;
	private Integer status;
	private Long createTime;
	private Long updateTime;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getPermissionId(){
		return permissionId;
	}
	public void setPermissionId(String permissionId){
		this.permissionId = permissionId;
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
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getPerms(){
		return perms;
	}
	public void setPerms(String perms){
		this.perms = perms;
	}
	public Integer getParentId(){
		return parentId;
	}
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
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