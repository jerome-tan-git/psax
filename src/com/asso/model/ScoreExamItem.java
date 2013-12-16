package com.asso.model;

public class ScoreExamItem {

	private int id;
	private int examitemid;
	private int seqid;
	private int scoreid;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExamitemid() {
		return examitemid;
	}
	public void setExamitemid(int examitemid) {
		this.examitemid = examitemid;
	}
	public int getSeqid() {
		return seqid;
	}
	public void setSeqid(int seqid) {
		this.seqid = seqid;
	}
	public int getScoreid() {
		return scoreid;
	}
	public void setScoreid(int scoreid) {
		this.scoreid = scoreid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toString(){
		return this.id+","+this.examitemid+","+this.seqid+","+this.scoreid+","+this.status;
	}

}
