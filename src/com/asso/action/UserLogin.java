package com.asso.action;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.asso.manager.UserManager;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userlogin") 
public class UserLogin extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware{
	
	private UserRegisterInfo uInfo = new UserRegisterInfo();
	private UserManager um;	
	private User user; 
//	private ApplicationContext ctx;
	private HttpServletRequest request;	
	private Map session;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLogin(){
//		ctx = new ClassPathXmlApplicationContext("beans.xml");
//		um = (UserManager) ctx.getBean("userManager");
		um = (UserManager) SpringFactory.getObject("userManager");
	}
	
	public UserManager getUm() {
		return um;
	}

	@Resource(name="userManager")//直接注入，替代初始化userManager
	public void setUm(UserManager um) {
		this.um = um;
	}
	
		
	public UserRegisterInfo getuInfo() {
		return uInfo;
	}

	public void setuInfo(UserRegisterInfo uInfo) {
		this.uInfo = uInfo;
	}
	


	@Override
	public String execute(){
		
		System.out.println("GET username--->"+this.uInfo.getUsername());
		System.out.println("GET password--->"+this.uInfo.getPassword());
		
		User u = new User();
		u.setPassword(this.uInfo.getPassword());
		u.setUsername(this.uInfo.getUsername());
		
//		this.user.setUsername(this.uInfo.getUsername());
//		this.user.setPassword(this.uInfo.getPassword());
		
		int rz =0;
		try {
			rz= um.exists(u);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("rz="+rz);
		if(rz==1){
			this.setSession2(this.request.getSession());
			return "success";
		}
		return "failure";

	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.uInfo;
	}
	
	public String save(){
		
		System.out.println("GET username--->"+this.uInfo.getUsername());
		System.out.println("GET password--->"+this.uInfo.getPassword());
		System.out.println("GET password1--->"+this.uInfo.getPassword1());
		User u = new User();
		u.setPassword(this.uInfo.getPassword());
		u.setUsername(this.uInfo.getUsername());

		try {
			um.add(u);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "save";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;		
		  System.out.println("Struts REQUEST info----"+this.request.getRequestedSessionId()			
				  +"   $$$   "+this.request.getRequestURI()); 
		  System.out.println("request.getSession()----"+request.getSession());
	}

	@Override
	public void setSession(Map session) {	  	

		  User u = new User();
		  u.setId(10);
		  u.setUsername("ggg");
		  u.setLevel(3);
		  request.getSession().setAttribute("user", u);		  
		  System.out.println("setSESSION1----after----"+request.getSession().getAttribute("user").toString());
		  
	}

	public void setSession2(HttpSession session) {
		System.out.println("this.uInfo.getUsername()"+this.uInfo.getUsername());
		 User u = new User();
		 u.setUsername(this.uInfo.getUsername());
		 request.getSession().setAttribute("user_", u);
		 System.out.println("setSession2----Session().user_----"+
				 request.getSession().getAttribute("user_").toString());
		 
		  
	}
	


}
