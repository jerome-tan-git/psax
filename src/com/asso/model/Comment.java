package com.asso.model;

public class Comment implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 5560353539326069764L;
	private int id;
	private String content;
	private int auther;	
	private String date;
	private String updatetime;
	private int topicid;	
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}		
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String toString(){
		return (this.getId()+":"+this.getContent()+":"+this.getAuther()+":"+this.getDate());
//				+":"+this.getUser().toString();
	}

}
