package com.asso.model;

public class ExamItem implements java.io.Serializable{
	
	
	private static final long serialVersionUID = -1423792775403696553L;
	private int id;
	private int examid;
	private String question;
	private int category;
	
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
	public String toString(){
		return this.getId()+":"+this.getExamid()+":"+this.getQuestion();
	}

}
