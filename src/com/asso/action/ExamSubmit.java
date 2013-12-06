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
	private HashMap<ExamItem,Integer> donelist;//0-notDont|1-done&right|2-done&wrong
	
	
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

	/*Get answers from front-end, put them into chosenRefIds by refid or figurewithrefid*/
	/* Set session : pageindex(EPage_c+dpi)---chosenRefIds */
	private void setANS(){	
//		String[] ANS = new String[CONSTANT.pageNum*CONSTANT.pageSize] ;
		ArrayList<String> chosenRefIds = new ArrayList<String>();
		for(int i=0; i<CONSTANT.pageNum*CONSTANT.pageSize; i++){
			/*Multiple Choice items*/
			if ( this.request.getParameterValues("ANS_"+(i+1))!=null 
					&& this.request.getParameterValues("ANS_"+(i+1)).length>1){
				String[] multi = this.request.getParameterValues("ANS_"+(i+1));
				for(String m:multi){
//					System.out.println("ANS-["+i+"]="+m);
					chosenRefIds.add(m);
				}
				continue;
			}/*Judge items and Single Choice items*/
			else if(this.request.getParameter("ANS_"+(i+1))!=null ){
				chosenRefIds.add(this.request.getParameter("ANS_"+(i+1)));
//				ANS[i] =  this.request.getParameter("ANS_"+(i+1));				
//				System.out.println("ANS-["+i+"]="+this.request.getParameter("ANS_"+(i+1)));
			}			
			
		}
//		for(String refans:chosenRefIds)
//			System.out.println("chosenRefIds---------"+refans);
		this.session.put("EPage_c"+this.dpi, chosenRefIds);
		this.chosenRefIds = chosenRefIds;
//		List<String> sss = (List<String>) this.session.get("EPage"+this.dpi);
//		for(String refans:sss)
//			System.out.println("chosenRefIds---------"+refans);
//		
	}

	/*calculatePageScore & make donelist for the score page*/
	/* Set session : pageindex(EPage_s+dpi)---scorePlus */
	/* Set session : pageindex(EPage_d+dpi)---donelist */
	private void calculatePageScore(){
		this.donelist = new HashMap<ExamItem,Integer>();
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
//									System.out.println("--------calculatePageScore-cat1-----");
//									System.out.println("----a-refid="+a_refid+"-----answer="+a);
									if(a_refid==refid){										
										if(a==refIsTrue){
											this.scorePlus +=1;
											this.donelist.put(k, 1);
										}else
											this.donelist.put(k, 2);
									}																	
								}
							}
							if(!this.donelist.keySet().contains(k))
								this.donelist.put(k, 0);
						}else
							System.out.println("@@-DB data ERROR! Pls INV...");
					}
					if(cat==2){
						for(String ans:this.chosenRefIds){
							if(ans.contains("_"))
								continue;
							for(ExamRef ref:refs){		
//								System.out.println("--------calculatePageScore-cat2-----");
//								System.out.println("-----refid="+ref.getId()+", isTrue="+ref.getIstrue()
//										+", a_refid="+Integer.parseInt(ans));
								if(ref.getId()==Integer.parseInt(ans) ){
									if(ref.getIstrue()==1){
										this.scorePlus +=1;
										this.donelist.put(k, 1);
									}else
										this.donelist.put(k, 2);									
								}																	
							}
						}	
						if(!this.donelist.keySet().contains(k))
							this.donelist.put(k, 0);
					}
					if(cat==3){
						int shouldmatch = 0;
						int realmatch = 0;
						
						for(ExamRef ref:refs){
							if(ref.getIstrue()==1){
								shouldmatch += 1;
							}
							for(String ans:this.chosenRefIds){
								if(ans.contains("_"))
									continue;
								if(Integer.parseInt(ans)==ref.getId()){									
									if(!this.donelist.keySet().contains(k))
										this.donelist.put(k, 2);	
									if(ref.getIstrue()==1)
										realmatch +=1;																		
								}										
							}
							
//							if(ref.getIstrue()==1){
//								shouldmatch += 1;
//								for(String ans:this.chosenRefIds){
//									if(ans.contains("_"))
//										continue;
//									if(Integer.parseInt(ans)==ref.getId()){
//										realmatch +=1;
//										if(!this.donelist.keySet().contains(k))
//											this.donelist.put(k, 0);											
//									}										
//								}
//							}
						}
						if(shouldmatch==realmatch && realmatch>0){
							this.scorePlus +=2;
							this.donelist.put(k, 1);
						}
						if(!this.donelist.keySet().contains(k))
							this.donelist.put(k, 0);						
							
					}
				}
				
			}else
				continue;
		}
		
		
	}
	/* pi go forword */
	/* Set session : pi---nextExamPage */
	private void countPage(){
		System.out.println("setServletRequest----examPage="+this.request.getSession().getAttribute("pi"));
		int nextExamPage = (Integer) this.request.getSession().getAttribute("pi");		
		if(nextExamPage<CONSTANT.pageNum)
			nextExamPage += 1;
		System.out.println("setServletRequest----nextExamPage="+nextExamPage);		
		this.request.getSession().setAttribute("pi", nextExamPage);
	}
	/* Get total score in this exam */
	/* Put all the exam related info into DB*/
	public void examFinialize(){
		
	}
	private void check(){
//		this.session.put("EPage_d"+this.dpi, donelist);
//		this.session.put("EPage_s"+this.dpi, scorePlus);
		
		int score = (Integer) this.session.get("score");
		score += this.scorePlus;
		this.session.put("score", score);
		
		ArrayList<Integer> answerProgress = (ArrayList<Integer>) this.session.get("answerProgress");
		Set<ExamItem> ks = this.donelist.keySet();
		for(ExamItem k:ks)
			answerProgress.add(this.donelist.get(k));
		
		
		
		System.out.println("---------Got score in this page---"+this.scorePlus);
		System.out.println("---------DONE LIST----------------");
		for(ExamItem ei : this.donelist.keySet())
			System.out.println("----"+ei.getId()+"("+ei.getCategory()+")"+this.donelist.get(ei));
		System.out.println("---TOTAL DONE LIST----------------");
		for(Integer status:answerProgress)
			System.out.print("--"+status+"|");
		System.out.println("---------IN SESSION----------------");
		List<String> ccc = (List<String>) this.session.get("EPage_c"+this.dpi);
		for(String refans:ccc)
			System.out.println("chosenRefIds---------"+refans);
//		HashMap<ExamItem,Integer> ddd = (HashMap<ExamItem,Integer>) this.session.get("EPage_d"+this.dpi);
//		for(ExamItem ei:ddd.keySet())
//			System.out.println("ei---rzStatus---"+ei.getId()+"|"+ddd.get(ei));
//		System.out.println("scoreplus-----"+this.session.get("EPage_s"+this.dpi) ); 
		System.out.println("score-----"+this.session.get("score") ); 
	}

	@Override
	public String execute(){
		this.dpi = (Integer) this.request.getSession().getAttribute("pi");
//		this.session.put("elist", this.pageitemlistf);
		System.out.println("EXCUTION preparing.........");
		this.setANS();
		this.calculatePageScore();
		this.countPage();
		this.check();
		
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
		this.request=request;		
		System.out.println("------------------------------Exam-Submit-2-------------------------------------");
		this.pageitemlistf = (List<HashMap<ExamItem, List<ExamRef>>>) 
				this.request.getSession().getAttribute("itemlistf");	
		System.out.println("setServletRequest----this.pageitemlistf.size----"+
				this.pageitemlistf.size());		
		
	}



}
