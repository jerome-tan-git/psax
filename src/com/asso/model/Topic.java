package com.asso.model;

import java.sql.Date;
import java.util.List;

public class Topic implements java.io.Serializable{
	
	
	private int id;
	private String content;
	private int auther;	
	private String date;
	private String title;
	private String authername;
	private String lastupdate;
	
	private User user;
	private List<Comment> comments;
	private int commentscount;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAuther() {
		return auther;
	}
	public void setAuther(int auther) {
		this.auther = auther;
	}	
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getAuthername() {
		return authername;
	}
	public void setAuthername(String authername) {
		this.authername = authername;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public int getCommentscount() {
		return commentscount;
	}
	public void setCommentscount(int commentscount) {
		this.commentscount = commentscount;
	}
	public String toString(){
		return (this.getId()+":"+this.getTitle()+":"+this.getContent()+":"+this.getAuther()+":"
					+this.authername+":"+this.getDate());
	}

}
