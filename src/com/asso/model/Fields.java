package com.asso.model;

public class Fields {

	private int fieldid;
	private int fieldtype;
	private String fieldname;
	private String groupname;
	private int formid;
	
	public int getFieldid() {
		return fieldid;
	}
	public void setFieldid(int fieldid) {
		this.fieldid = fieldid;
	}
	public int getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(int fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getFormid() {
		return formid;
	}
	public void setFormid(int formid) {
		this.formid = formid;
	}
	
	public String toString(){
		return this.fieldid+":"+this.fieldname+":"+this.formid+":"+
					this.fieldtype+":"+this.groupname;
	}
	
}
