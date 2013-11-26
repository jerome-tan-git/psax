package com.asso.model;

import java.sql.Date;

public class Exam {
	
	private int id;
	private String name;
	private int groupid;
	private Date targetdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public Date getTargetdate() {
		return targetdate;
	}
	public void setTargetdate(Date targetdate) {
		this.targetdate = targetdate;
	}
	
	public String toString(){
		return this.getId()+":"+this.getName()+":"+this.getGroupid()+":"+this.getTargetdate();
	}

}
