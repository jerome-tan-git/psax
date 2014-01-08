package com.asso.model;

public class Uploadfiles {
	
	private int id;
	private int userid;
	private String file;
	private String uploadtime;
	private String fname;
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	public String toString(){
		return this.id+":"+this.userid+":"+this.file+":"+this.uploadtime+":"+this.fname;
	}

}
