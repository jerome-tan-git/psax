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

import util.CONSTANT;
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
	private String username;
	private String password;
	private String userid;	
	private int page;
	private int nextpage;
	private int lastpage;
	private int endpage;
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNextpage() {
		return nextpage;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
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
		
		this.pagination();
		this.partUserList();
	}
	
	private void pagination(){
		this.page = 1;
		if(this.request.getParameter("page")!=null && this.request.getParameter("page").length()>0){
			this.page = Integer.parseInt(this.request.getParameter("page"));		
		}
		int listsize = this.userslist.size();
		System.out.println("-----userlist.size="+listsize);
		this.endpage = listsize/CONSTANT.pageUserSize;
		if(listsize>this.endpage*CONSTANT.pageUserSize)
			this.endpage += 1;		
		
		if(this.page<2){
			this.page = 1;
			this.lastpage = 1;
			this.nextpage = this.page+1;
		}else{
			if(this.page>=this.endpage){
				this.page = this.endpage;
				this.nextpage = this.endpage;
				this.lastpage = this.page-1;
			}else{
				this.lastpage = this.page-1;
				this.nextpage = this.page+1;
			}			
		}	
	}
	private void partUserList(){
		List<User> rlist = new ArrayList<User>();
		int b = (this.page-1)*CONSTANT.pageUserSize;
		int e = b+CONSTANT.pageUserSize;
		for(int i=0; i<this.userslist.size(); i++){
			if(i>=b && i<e)
				rlist.add(this.userslist.get(i));
		}
		this.setUserslist(rlist);
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
		this.user.setUsername(username);
		this.user.setPassword(password);
		if(this.userid!=null && this.userid.length()>0 || this.request.getParameter("userid")!=null
				&& this.request.getParameter("userid").length()>0){
			this.user.setId(Integer.parseInt(this.userid));
			try {
				um.update(this.user);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				um.add(user);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
