package com.zaver.mp.sys.rbac.model;

import java.io.Serializable;

public class RbacPermission implements Serializable{

    private static final long serialVersionUID = 1L;

	private String createBy;
	private Long createdAt;
	private String icon;
	private Integer id;
	private String menuName;
	private Integer menuType;
	private Integer orderNum;
	private Integer parentId;
	private String perms;
	private String remark;
	private String updateBy;
	private Long updatedAt;
	private String url;
	private Integer visible;

	public String getCreateBy(){
		return createBy;
	}
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	public Long getCreatedAt(){
		return createdAt;
	}
	public void setCreatedAt(Long createdAt){
		this.createdAt = createdAt;
	}
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getMenuName(){
		return menuName;
	}
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public Integer getMenuType(){
		return menuType;
	}
	public void setMenuType(Integer menuType){
		this.menuType = menuType;
	}
	public Integer getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	public Integer getParentId(){
		return parentId;
	}
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	public String getPerms(){
		return perms;
	}
	public void setPerms(String perms){
		this.perms = perms;
	}
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getUpdateBy(){
		return updateBy;
	}
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	public Long getUpdatedAt(){
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt){
		this.updatedAt = updatedAt;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public Integer getVisible(){
		return visible;
	}
	public void setVisible(Integer visible){
		this.visible = visible;
	}
}