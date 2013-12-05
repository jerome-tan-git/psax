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

import util.CONSTANT;

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
//	private String[] ANS = {"","","","","","","","","",""};
	private int dpi;//dealing page index
	
	
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


	private void setANS(){	
		String[] ANS = {"","","","","","","","","",""};
		for(int i=0; i<10; i++){
			ANS[i] =  this.request.getParameter("ANS_"+(i+1));
			System.out.println("ANS-"+ANS[i]);
		}
		this.session.put("EPage"+this.dpi, ANS);
		System.out.println(""+this.session.get("EPage"+this.dpi).toString());
	}

	private void countPage(){
		System.out.println("setServletRequest----examPage="+this.request.getSession().getAttribute("pi"));
		int nextExamPage = (Integer) this.request.getSession().getAttribute("pi");		
		if(nextExamPage<CONSTANT.pageNum)
			nextExamPage += 1;
		System.out.println("setServletRequest----nextExamPage="+nextExamPage);		
		this.request.getSession().setAttribute("pi", nextExamPage);
	}

	@Override
	public String execute(){
		this.dpi = (Integer) this.request.getSession().getAttribute("pi");
//		this.session.put("elist", this.pageitemlistf);
		System.out.println("EXCUTION preparing.........");
		this.setANS();
		this.countPage();
		
		return "success";
	
	}

	
	
	
	@Override
	public void setSession(Map session) {
		this.session = session;
		this.session.put("eirlist", this.pageitemlistf);
		System.out.println("------------------------------Exam-Submit-1--setSession-----------------------------------");
		System.out.println("setSession----Session().elist----"+
				this.session.get("eirlist").toString());
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;		
		System.out.println("------------------------------Exam-Submit-2-------------------------------------");
		this.pageitemlistf = (List<HashMap<ExamItem, List<ExamRef>>>) 
				this.request.getSession().getAttribute("elist");		
//		System.out.println("setServletRequest----Session().elist----"+
//				 request.getSession().getAttribute("elist").toString());
		System.out.println("setServletRequest----this.pageitemlistf.size----"+
				this.pageitemlistf.size());		
		
	}

//	@Override
//	public Object getModel() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
