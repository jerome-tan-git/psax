package com.asso.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asso.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Scope("prototype")
@Component("userlogout") 
public class UserLogout extends ActionSupport implements ServletRequestAware,SessionAware {
	
	private Map session;
	private HttpServletRequest request;	
		
	@Override
	public void setSession(Map session) {
		  this.session=session;				
		  System.out.println("UserLogout---------Struts SESSION MAP info_1----"+this.session.toString());
	}
	public void setSession2(HttpSession session) {		
		 request.getSession().setAttribute("user_", "");
		 System.out.println("UserLogout---------setSession2----Session().user_----"+
				 request.getSession().getAttribute("user_").toString());
		 }
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
	}
	private void clearSession(){
//		this.request.getSession().setAttribute("returnURL", referer);
		this.session.clear();
	}
	
	@Override
	public String execute(){	
		this.setSession2(this.request.getSession());
		this.clearSession();
		return "success";	
	}

}
