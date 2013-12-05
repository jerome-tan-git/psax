package com.asso.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private ArrayList<String> chosenRefIds;
	private int scorePlus =0;
	
	
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
//		String[] ANS = new String[CONSTANT.pageNum*CONSTANT.pageSize] ;
		ArrayList<String> chosenRefIds = new ArrayList<String>();
		for(int i=0; i<CONSTANT.pageNum*CONSTANT.pageSize; i++){
			
			if ( this.request.getParameterValues("ANS_"+(i+1))!=null 
					&& this.request.getParameterValues("ANS_"+(i+1)).length>1){
				String[] multi = this.request.getParameterValues("ANS_"+(i+1));
				for(String m:multi){
//					System.out.println("ANS-["+i+"]="+m);
					chosenRefIds.add(m);
				}
				continue;
			}else if(this.request.getParameter("ANS_"+(i+1))!=null ){
				chosenRefIds.add(this.request.getParameter("ANS_"+(i+1)));
//				ANS[i] =  this.request.getParameter("ANS_"+(i+1));				
//				System.out.println("ANS-["+i+"]="+this.request.getParameter("ANS_"+(i+1)));
			}			
			
		}
		for(String refans:chosenRefIds)
			System.out.println("chosenRefIds---------"+refans);
		this.session.put("EPage"+this.dpi, chosenRefIds);
		this.chosenRefIds = chosenRefIds;
//		List<String> sss = (List<String>) this.session.get("EPage"+this.dpi);
//		for(String refans:sss)
//			System.out.println("chosenRefIds---------"+refans);
//		
	}

	private void calculatePageScore(){
		for(HashMap<ExamItem,List<ExamRef>> examitem : this.pageitemlistf){
			if( examitem!=null){
				Set<ExamItem> ks = examitem.keySet();		
				if(ks.size()>1)
					System.out.println("@@-Dirty DATA, Pls INV....");
				for(ExamItem k:ks){
					List<ExamRef> refs = examitem.get(k);
					int cat = k.getCategory();					
					if(cat==1){
						if(refs.size()==1){
							int refIsTrue = refs.get(0).getIstrue();//0|1
							int refid = refs.get(0).getId();
							for(String ans:this.chosenRefIds){
								if(ans.contains("_")){
									int a_refid = Integer.parseInt(ans.substring(ans.indexOf("_")+1,ans.length()));
									int a = Integer.parseInt(ans.substring(0,ans.indexOf("_")));
									System.out.println("--------calculatePageScore------");
									System.out.println("-----refid="+refid+"-----answer="+a);
									if(a_refid==refid){
										if(refIsTrue==a)
											this.scorePlus +=1;
									}
								}
							}
								
						}else
							System.out.println("@@-DB data ERROR! Pls INV...");
					}
				}
				
			}else
				continue;
		}
		
		System.out.println("---------Got score in this page="+this.scorePlus);
		
		
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
		this.calculatePageScore();
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
				this.request.getSession().getAttribute("itemlistf");		
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
