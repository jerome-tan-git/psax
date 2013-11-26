package com.asso.model;

public class MemberInfo {
	
	private int id;
	private String c_name;
	private String c_tel;
	private String c_addr;
	private String c_email;
	private String contactperson;
	private String p_mp;
	private String p_email;
	private String p_tel;
	private String c_logo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	public String getC_addr() {
		return c_addr;
	}
	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}
	public String getC_email() {
		return c_email;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getP_mp() {
		return p_mp;
	}
	public void setP_mp(String p_mp) {
		this.p_mp = p_mp;
	}
	public String getP_email() {
		return p_email;
	}
	public void setP_email(String p_email) {
		this.p_email = p_email;
	}
	public String getP_tel() {
		return p_tel;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	public String getC_logo() {
		return c_logo;
	}
	public void setC_logo(String c_logo) {
		this.c_logo = c_logo;
	}
	
	public String toString(){
		return "MEMBERINFO-----\nid="+this.getId()+",\nc_name="+this.getC_name()+",\nc_tel="+this.getC_tel()
				+",\nc_addr="+this.getC_addr()+",\nc_email="+this.c_email+",\ncontactPerson="+this.getContactperson()
				+",\np_mp="+this.getP_mp()+",\np_email="+this.getP_email()+",\np_tel="+this.getP_tel()
				+",\nc_logo"+this.getC_logo();
	}
		
	
}
