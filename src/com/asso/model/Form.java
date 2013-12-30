package com.asso.model;

public class Form {

	private int formid;
	private String displayname;
	private String frontendtpl;
	
	public int getFormid() {
		return formid;
	}
	public void setFormid(int formid) {
		this.formid = formid;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getFrontendtpl() {
		return frontendtpl;
	}
	public void setFrontendtpl(String frontendtpl) {
		this.frontendtpl = frontendtpl;
	}
	
	public String toString(){
		return this.formid+":"+this.displayname+":"+this.frontendtpl;
	}
	
}
