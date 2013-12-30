package com.asso.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class test extends ActionSupport implements ServletRequestAware,SessionAware{
	private HttpServletRequest request;
	
	public String test(){
		
		Map root = new HahsMap();
		root.put("message", "Hello FreeMarker!");
		
		
		
		return "test";
	}

	@Override
	public String execute(){
		System.out.println("Abs path: " +this.request.getRealPath("./timages"));		
		//,"./images/lkgfsdajflkasjdlf;jasl;dfasdf.gif"
		for(String key: request.getParameterMap().keySet())
		{
			System.out.println(request.getParameterValues(key).length);
		}
		
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		session.put("a", "a");
		// TODO Auto-generated method stub		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		// TODO Auto-generated method stub
		
	}
}
