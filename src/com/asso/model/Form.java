package com.asso.model;

import java.util.List;

public class Form {

	private int formid;
	private String displayname;
	private String frontendtpl;
	private List<Fields> fields;
	private String path;
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
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
	public List<Fields> getFields() {
		return fields;
	}
	public void setFields(List<Fields> fields) {
		this.fields = fields;
	}
	
	public String toString(){
		if(this.fields!=null)
			return this.formid+":"+this.displayname+":"+this.frontendtpl+":"+this.path+",\n"
				+this.fields.toString();
		else
			return this.formid+":"+this.displayname+":"+this.frontendtpl+":"+this.path;
	}
	
}
