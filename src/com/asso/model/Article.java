package com.asso.model;

import java.sql.Date;

public class Article implements java.io.Serializable{
	
	private static final long serialVersionUID = -7323032372076449024L;
	private int id;
	private String title;
	private String absinfo;
	private int categoryid;
	private String pic;
	private String addition;
	private String article;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbsinfo() {
		return absinfo;
	}
	public void setAbsinfo(String absinfo) {
		this.absinfo = absinfo;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	
	
	public String toString(){
		return this.getId()+":"+this.getTitle()+":"+this.getAbsinfo()+":"+this.getCategoryid()+":"+
				this.getPic()+":"+this.getAddition()+":"+this.getArticle();
	}

}