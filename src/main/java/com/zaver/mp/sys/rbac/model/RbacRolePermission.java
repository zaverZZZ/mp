package com.zaver.mp.sys.rbac.model;

import java.io.Serializable;

public class RbacRolePermission implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer roleId;
	private Integer permissionId;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public Integer getPermissionId(){
		return permissionId;
	}
	public void setPermissionId(Integer permissionId){
		this.permissionId = permissionId;
	}
}