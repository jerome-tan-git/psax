package com.asso.model;

public class ScoreExamRef {
	
	private int id;
	private int chosenrefid;
	private int itemid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChosenrefid() {
		return chosenrefid;
	}
	public void setChosenrefid(int chosenrefid) {
		this.chosenrefid = chosenrefid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public String toString(){
		return this.id+","+this.chosenrefid+","+this.itemid;
	}

}
