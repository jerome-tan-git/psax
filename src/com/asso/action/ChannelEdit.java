package com.asso.action;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.asso.manager.ChannelManager;
import com.asso.manager.UserManager;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.User;
import com.asso.vo.ChCatInfo;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("channeledit") 
public class ChannelEdit extends ActionSupport implements ServletRequestAware,SessionAware{
	
	private ChannelManager cm;	
	private Channel channel;
	private Category category;
	
	private HttpServletRequest request;	
	private Map session;

	public ChannelEdit(){		
		cm = (ChannelManager) SpringFactory.getObject("channelManager");
	}	
		
	public ChannelManager getCm() {
		return cm;
	}
	@Resource(name="channelManager")//直接注入，替代初始化channelManager
	public void setCm(ChannelManager cm) {
		this.cm = cm;
	}

	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}


	public String addCategory(){		
		ChCatInfo cinfo = new ChCatInfo(); 
		cinfo.setChannelid(1);
		cinfo.setCategory("协会简介");
		cinfo.setParentid(0);
		
		this.category = new Category();
		this.category.setCategory(cinfo.getCategory());
		this.category.setChannelid(cinfo.getChannelid());
		this.category.setParentid(cinfo.getParentid());
		
		try {
			cm.add(category);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public String addChannel(){
		
		ChCatInfo cinfo = new ChCatInfo();
		cinfo.setChannel("test");		
		this.channel = new Channel();
		this.channel.setChannel(cinfo.getChannel());
		
		try {
			cm.add(channel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	@Override
	public String execute(){
		return "success";
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
	}

	@Override
	public void setSession(Map session) {	  	
		 this.session=session;			
	}

}
