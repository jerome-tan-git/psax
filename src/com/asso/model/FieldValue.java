package com.asso.model;

public class FieldValue {
	
	private int fieldvalueid;
	private int docid;
	private int fieldid;
	private String value;
	private int fieldvalueindex;
	public int getFieldvalueid() {
		return fieldvalueid;
	}
	public void setFieldvalueid(int fieldvalueid) {
		this.fieldvalueid = fieldvalueid;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public int getFieldid() {
		return fieldid;
	}
	public void setFieldid(int fieldid) {
		this.fieldid = fieldid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getFieldvalueindex() {
		return fieldvalueindex;
	}
	public void setFieldvalueindex(int fieldvalueindex) {
		this.fieldvalueindex = fieldvalueindex;
	}
	
	public String toString(){
		return this.fieldvalueid+":"+this.value+":"+this.fieldid+":"
				+this.docid+":"+this.fieldvalueindex;
	}

}
