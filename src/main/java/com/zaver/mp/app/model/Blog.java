package com.zaver.mp.app.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Blog implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String content;
	private String categories;
	private String author;
	private Timestamp createTime;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getCategories(){
		return categories;
	}
	public void setCategories(String categories){
		this.categories = categories;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author = author;
	}
}