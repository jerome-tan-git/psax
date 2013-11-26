package com.asso.action;

import java.sql.SQLException;
import java.util.Map;

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
@Component("checksession") 
public class CheckSession extends ActionSupport implements SessionAware {
	
	private Map session;
	
	@Override
	public void setSession(Map session) {
		  this.session=session;				
		  System.out.println("Struts SESSION info_1----"+this.session.toString());
//		  System.out.println(this.session);
		  
		  
	}
	
//	@RequestMapping
//	public void hello(HttpSession session){
//		User user = (User)session.getAttribute("currentUser");
//		System.out.println("hello---------user.name="+user.getUsername());
//		System.out.println("hello---------user.psd="+user.getPassword());
//	}
	

	@Override
	public String execute(){
//	this.hello(session);
			return "success";		
//		return "failure";

	}

}
