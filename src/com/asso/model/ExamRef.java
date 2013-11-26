package com.asso.model;

public class ExamRef {
	
	private int id;
	private int itemid;
	private String ref;
	private int istrue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public int getIstrue() {
		return istrue;
	}
	public void setIstrue(int istrue) {
		this.istrue = istrue;
	}
	
	public String toString(){
		return this.getId()+":"+this.getItemid()+":"+this.getRef()+":"+this.getIstrue();
	}
	

}
