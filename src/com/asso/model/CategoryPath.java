package com.asso.model;

public class CategoryPath {
	
	private int catId;
	private String catName;
	private int parentCatId;
	private String parentCatName;
	private int chId;
	private String chName;
	
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(int parentCatId) {
		this.parentCatId = parentCatId;
	}
	public String getParentCatName() {
		return parentCatName;
	}
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}
	public int getChId() {
		return chId;
	}
	public void setChId(int chId) {
		this.chId = chId;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	
    public String toString(){
    	return this.chId+":"+this.chName+":"+this.catId+":"+this.catName+":"+this.parentCatId+":"+this.parentCatName;
    }

}
