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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		String cat = this.request.getParameter("categoryid");
		System.out.println("cat-----------"+cat);
		if(cat!=null && cat.length()>0){
			int c = Integer.parseInt(cat);
		
			if(c==0)
				return "cat0";
			if(c==1)
				return "cat1";
			if(c==2)
				return "cat2";
			if(c==15)
				return "cat15";
			
			if(c==7)
				return "cat7";
			if(c==8)
				return "cat8";
			if(c==9)
				return "cat9";
			if(c==10)
				return "cat10";
			if(c==11)
				return "cat11";
			if(c==12)
				return "cat12";
						
		}
		return "success";
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
		  System.out.println("request.getSession()----"+request.getSession());
	}

	@Override
	public void setSession(Map<String, Object> _s) {
		this.session = _s;
		System.out.println("setSESSION----after--"+_s.toString());
	}


}
