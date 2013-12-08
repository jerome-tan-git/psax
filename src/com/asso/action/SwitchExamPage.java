package com.asso.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.mapping.Set;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Scope("prototype")
@Component("switchexampage") 
public class SwitchExamPage extends ActionSupport implements ServletRequestAware,SessionAware {
	

	private static final long serialVersionUID = -3599357601671496317L;
	private HttpServletRequest request;	
	private Map session;
	private List<HashMap<ExamItem,List<ExamRef>>> itemlistf;
	
	public List<HashMap<ExamItem, List<ExamRef>>> getItemlistf() {
		return itemlistf;
	}
	public void setItemlistf(List<HashMap<ExamItem, List<ExamRef>>> itemlistf) {
		this.itemlistf = itemlistf;
	}
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.itemlistf = (List<HashMap<ExamItem, List<ExamRef>>>) 
				this.request.getSession().getAttribute("elist");
		System.out.println("-------------|-switchexampage-2-|--------------------");
		System.out.println("setSession3----Session().elist----"+
				 request.getSession().getAttribute("elist").toString());
		
	}

	@Override
	public String execute(){
//	this.hello(session);
		System.out.println("-------------SwitchExamPage EXECUTION----------------");
		this.session.put("elist", this.itemlistf);
		System.out.println("EXECUTION|REAL-----Session().elist----"+
				this.session.get("elist").toString());
			return "success";	
	}
	@Override
	public void setSession(Map session) {
		this.session = session;
		System.out.println("----------------------------switchexampage-1-------------------------------------");
//		System.out.println("setSESSION----after----"+request.getSession().getAttribute("user").toString());
//		this.session.put("elist", (List<HashMap<ExamItem, List<ExamRef>>>) this.request.getSession().getAttribute("elist"));
		System.out.println("REAL-setSession----Session().elist----"+
				this.session.get("elist").toString());
	}

}
