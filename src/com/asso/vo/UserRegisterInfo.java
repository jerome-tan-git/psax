package com.asso.vo;

import java.io.File;

public class UserRegisterInfo {
	
	private String username;
	private String password;
	private String password1;
	private int id;
	private String nickname;
	
	private File portrait;	
	private String portraitContentType;	
	private String portraitFileName;


	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	public File getPortrait() {
		return portrait;
	}
	public void setPortrait(File portrait) {
		this.portrait = portrait;
	}
	public String getPortraitContentType() {
		return portraitContentType;
	}
	public void setPortraitContentType(String portraitContentType) {
		this.portraitContentType = portraitContentType;
	}
	public String getPortraitFileName() {
		return portraitFileName;
	}
	public void setPortraitFileName(String portraitFileName) {
		this.portraitFileName = portraitFileName;
	}
	

}
