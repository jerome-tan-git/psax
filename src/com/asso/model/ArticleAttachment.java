package com.asso.model;

public class ArticleAttachment {
	
	private String seq;
	private String filename;
	private String urlPath;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	
	public String toString(){
		return this.seq+":"+this.filename+":"+this.urlPath;
	}
	

}
