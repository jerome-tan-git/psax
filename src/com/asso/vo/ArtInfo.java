package com.asso.vo;

import java.io.File;

public class ArtInfo {
	
	private String title;
	private String absinfo;
//	private String pic;
//	private String addition;
	private String article;
	private String pubdate;
	private String srcdisplay;
	
	private int categoryid;
	private File pic;
	private File addition;
	private String picContentType;
	private String additionContentType;
	private String picFileName;
	private String additionFileName;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbsinfo() {
		return absinfo;
	}

	public void setAbsinfo(String absinfo) {
		this.absinfo = absinfo;
	}

//	public String getPic() {
//		return pic;
//	}
//
//	public void setPic(String pic) {
//		this.pic = pic;
//	}
//
//	public String getAddition() {
//		return addition;
//	}
//
//	public void setAddition(String addition) {
//		this.addition = addition;
//	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public File getAddition() {
		return addition;
	}

	public void setAddition(File addition) {
		this.addition = addition;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getSrcdisplay() {
		return srcdisplay;
	}

	public void setSrcdisplay(String srcdisplay) {
		this.srcdisplay = srcdisplay;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getAdditionContentType() {
		return additionContentType;
	}

	public void setAdditionContentType(String additionContentType) {
		this.additionContentType = additionContentType;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getAdditionFileName() {
		return additionFileName;
	}

	public void setAdditionFileName(String additionFileName) {
		this.additionFileName = additionFileName;
	}
	
	
}
