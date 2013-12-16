package com.asso.model;

import java.sql.Date;

public class Score {
	
	private int id;
	private int score;
	private int userid;
	private String exameename;
	private int examid;
	private String examdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getExameename() {
		return exameename;
	}
	public void setExameename(String exameename) {
		this.exameename = exameename;
	}
	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	
	public String toString(){
		return this.id+","+this.score+","+this.userid+","+this.exameename+","+
				this.examid+","+this.examdate;
	}

}
