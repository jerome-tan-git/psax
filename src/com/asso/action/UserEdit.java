package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.asso.manager.UserManager;
import com.asso.model.User;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("useredit") 
public class UserEdit extends ActionSupport implements ServletRequestAware,SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserManager um;	
	private User user;
	private List<User> userslist;
	
	
	private HttpServletRequest request;	
	private Map session;

	public UserEdit(){		
		um = (UserManager) SpringFactory.getObject("userManager");
	}	
		
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")//直接注入，替代初始化channelManager
	public void setCm(UserManager um) {
		this.um = um;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserslist() {
		return userslist;
	}
	public void setUserslist(List<User> userslist) {
		this.userslist = userslist;
	}
	public void setUm(UserManager um) {
		this.um = um;
	}
	
	private void buildUsersList(){
		this.userslist = new ArrayList<User>();		
		try {
			this.userslist = um.loadusers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("-----userlist.size="+this.userslist.size());
	}
	
	public String deleteUser(){
		String userid = this.request.getParameter("userid");
		if(userid!=null){
			int id = Integer.parseInt(userid);
			try {
				um.delete(id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "delete";
	}
	public String updateUser(){
		String userid = this.request.getParameter("userid");
		if(userid!=null){
			
			
		}
		return "update";
	}
	
	public String addUser(){
		this.user = new User();
		if(this.request.getParameter("un")!=null)
			this.user.setUsername(this.request.getParameter("n"));
		if(this.request.getParameter("pw")!=null)
			this.user.setPassword(this.request.getParameter("pw"));
		try {
			um.add(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "add";
	}

	@Override
	public String execute(){
		this.buildUsersList();
		return "list";		
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
