package com.asso.vo;

import java.util.HashMap;
import java.util.List;

public class ExamBuiltInfo {
	
	private int examid;
	private String examname;
	private int groupid;
	
	private int examitemid;	
	private int category;
	private String question;
	
	private int answeristrue;//0|1
	private int refsnum;
	private String refs;
	private String answers;
	
	private int refid;
	private String ref;
	private int istrue;
	
	

	public int getExamid() {
		return examid;
	}

	public void setExamid(int examid) {
		this.examid = examid;
	}

	public String getExamname() {
		return examname;
	}

	public void setExamname(String examname) {
		this.examname = examname;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getExamitemid() {
		return examitemid;
	}

	public void setExamitemid(int examitemid) {
		this.examitemid = examitemid;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getAnsweristrue() {
		return answeristrue;
	}

	public void setAnsweristrue(int answeristrue) {
		this.answeristrue = answeristrue;
	}

	public int getRefsnum() {
		return refsnum;
	}

	public void setRefsnum(int refsnum) {
		this.refsnum = refsnum;
	}

	public String getRefs() {
		return refs;
	}

	public void setRefs(String refs) {
		this.refs = refs;
	}

	
	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	public int getRefid() {
		return refid;
	}

	public void setRefid(int refid) {
		this.refid = refid;
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
		return this.getExamid()+":"+this.getExamname()+":"+this.getGroupid()+":"
				+this.getExamitemid()+":"+this.getQuestion()+":"+this.getRefsnum()+":"
				+this.getCategory()+":"+this.getRefsnum()+":"+this.getRefs()+":"
				+this.getAnsweristrue()+":"+this.getAnswers()+":"
				+this.getRefid()+":"+this.getRef()+":"+this.getIstrue();
	}
	
	

}
