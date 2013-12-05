package com.asso.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.opensymphony.xwork2.ActionSupport;
//import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("examsubmit") 
//public class ExamSubmit extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware{
public class ExamSubmit extends ActionSupport implements ServletRequestAware,SessionAware{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3441931443597405554L;
	private HttpServletRequest request;	
	private Map session;
	private List<HashMap<ExamItem,List<ExamRef>>> pageitemlistf; //items on that page
	private ExamRef[] ansref;    //answer in the form of ExamRef to calculate the score
	
	
	public List<HashMap<ExamItem, List<ExamRef>>> getPageitemlistf() {
		return pageitemlistf;
	}
	public void setPageitemlistf(
			List<HashMap<ExamItem, List<ExamRef>>> pageitemlistf) {
		this.pageitemlistf = pageitemlistf;
	}
	public ExamRef[] getAnsref() {
		return ansref;
	}
	public void setAnsref(ExamRef[] ansref) {
		this.ansref = ansref;
	}




	@Override
	public String execute(){
		this.session.put("elist", this.pageitemlistf);
		System.out.println("EXCUTION preparing.........");
		String a1 = this.request.getParameter("ANS_1");
		String a2 = this.request.getParameter("ANS_2");
		String a3 = this.request.getParameter("ANS_3");
		System.out.println("a1="+a1+", a2="+a2+", a3="+a3);
//		HashSet<Object> keys = (HashSet<Object>) params.keySet();
//		for(Object key:keys)
//			System.out.println(""+params.get(key).toString());
//		for(ExamRef an:ansref)
//			System.out.println("------ansref--"+an.toString());
		return "success";
	
	}

	
	
	
	@Override
	public void setSession(Map session) {
		this.session = session;
		System.out.println("------------------------------Exam-Submit-1--setSession-----------------------------------");
//		System.out.println("setSession----Session().elist----"+
//				 request.getSession().getAttribute("elist").toString());
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;		
		System.out.println("------------------------------Exam-Submit-2-------------------------------------");
		this.pageitemlistf = (List<HashMap<ExamItem, List<ExamRef>>>) this.request.getSession().getAttribute("elist");		
		System.out.println("setServletRequest----Session().elist----"+
				 request.getSession().getAttribute("elist").toString());
		 
	}

//	@Override
//	public Object getModel() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
