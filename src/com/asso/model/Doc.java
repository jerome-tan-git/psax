package com.asso.model;

import java.util.List;

public class Doc {

	private int docid;
	private String createdate;
	private int step;
	private int userid;
	private int formid;
	private List<FieldValue> fvlist;
	
	
	public List<FieldValue> getFvlist() {
		return fvlist;
	}
	public void setFvlist(List<FieldValue> fvlist) {
		this.fvlist = fvlist;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFormid() {
		return formid;
	}
	public void setFormid(int formid) {
		this.formid = formid;
	}
	
	public String toString(){
		return this.docid+":"+this.createdate+":"+this.step+":"+this.userid+":"+this.formid;
	}
	
}
