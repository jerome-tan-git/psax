package com.asso.action;

import java.util.ArrayList;
import java.util.Collections;
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
	@SuppressWarnings("rawtypes")
	private Map session;
	private List<HashMap<ExamItem,List<ExamRef>>> pageitemlistf; //items on that page
	private ExamRef[] ansref;    //answer in the form of ExamRef to calculate the score
//	private String[] ANS = {"","","","","","","","","",""};
//	private int dpi;//dealing page index
//	private ArrayList<String> chosenRefIds;
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
	@SuppressWarnings("unchecked")
	private void setANS(){	
		ArrayList<String> chosenRefIds = (ArrayList<String>) this.session.get("chosenRefIds");
		ArrayList<String> newChosenRefIds = new ArrayList<String>(); 
		
		for(int i=0; i<CONSTANT.pageNum*CONSTANT.pageSize; i++){
			/*Multiple Choice items*/
			if ( this.request.getParameterValues("ANS_"+(i+1))!=null 
					&& this.request.getParameterValues("ANS_"+(i+1)).length>1){
				String[] multi = this.request.getParameterValues("ANS_"+(i+1));
				for(String m:multi){
//					System.out.println("ANS-["+i+"]="+m);
//					chosenRefIds.add(m);
					newChosenRefIds.add(m);
				}
				continue;
			}/*Judge items and Single Choice items*/
			else if(this.request.getParameter("ANS_"+(i+1))!=null ){
//				chosenRefIds.add(this.request.getParameter("ANS_"+(i+1)));
				newChosenRefIds.add(this.request.getParameter("ANS_"+(i+1)));
			}			
			
		}
		
		
		HashMap<String,List<String>> answerMap = (HashMap<String,List<String>>)this.session.get("answerMap");
		Set<String> answerItems = answerMap.keySet();
		HashMap<String,String> itemsRefsRelation = (HashMap<String,String>)this.session.get("itemsRefsRelation");
		Set<String> refkeys = itemsRefsRelation.keySet();
				
		for(String ansid:answerItems)
			System.out.println("items id in answerMap---------"+ansid);
		HashSet<String> toModifyItemIds = new HashSet<String>();	//新增没计算入？？？
		HashSet<String> toAddItemIds = new HashSet<String>();
		for(String rid:newChosenRefIds){/*LIST all the modified itemids*/		
			String itemid = itemsRefsRelation.get(rid);
			System.out.println("chosenRefIds:"+rid+", itemid="+itemid);
			if(answerItems.contains(itemid)){
				toModifyItemIds.add(itemid);
				System.out.println("UPDATING!!!!!!!-----To Modify Item Id-----"+itemid);
			}else
				toAddItemIds.add(itemid);
		}
		System.out.println("toModifyItemIds---"+toModifyItemIds.toString());
		System.out.println("toModifyItemIds.size="+toModifyItemIds.size());
		System.out.println("toAddItemIds.size="+toAddItemIds.size());
		System.out.println("toAddItemIds---"+toAddItemIds.toString());
	//BUG1    //BUG2--totaldonelist序列问题
		
		if(toModifyItemIds.size()>0){
			/*Update session.chosenRefIds & session.answerMap*/
			List<Integer> todelidindex = new ArrayList<Integer>();
			for(String mitemid:toModifyItemIds){
				System.out.println("toModifyItemIds==="+mitemid);
				answerMap.put(mitemid, new ArrayList<String>());
				for(String orefid:chosenRefIds){/*Del the old ones*/
					String itemid = itemsRefsRelation.get(orefid);
					if(mitemid.equals(itemid)){
						System.out.println("chosenRefIds.remove(orefid)==="+orefid);
						todelidindex.add(chosenRefIds.indexOf(orefid));						
					}						
				}				
			}	
			Collections.reverse(todelidindex);
			for(int index:todelidindex)
				chosenRefIds.remove(index);
			System.out.println("After remove==="+chosenRefIds.toString());
				
			//.OR. removeAll from answerMap
			for(String mitemid:toModifyItemIds){
				for(String nrefid:newChosenRefIds){
					/*Add the new ones*/
					String id = itemsRefsRelation.get(nrefid);
					if(id.equals(mitemid)){
						System.out.println("chosenRefIds.add(nrefid)==="+nrefid);
						chosenRefIds.add(nrefid);
						answerMap.get(id).add(nrefid);
						System.out.println("After add(orefid)==="+chosenRefIds.toString());
						System.out.println("answerMap add3==="+answerMap.get(id).toString());
					}										
				}	
			}
		}else{			
			/*SAVE session.chosenRefIds & session.answerMap*/		
			for(String mitemid:toModifyItemIds){				
				for(String rid:newChosenRefIds){
					String id = itemsRefsRelation.get(rid);
					if(id.equals(mitemid)){
						chosenRefIds.add(rid);
						if(answerItems.contains(id)){
							answerMap.get(id).add(rid);
							System.out.println("answerMap add4==="+answerMap.get(id).toString());
						}else{
							List<String> refids = new ArrayList<String>();
							refids.add(rid);
							answerMap.put(id, refids);
							System.out.println("answerMap add5==="+answerMap.get(id).toString());
						}
					}					
				}
			}
			
		}
		if(toAddItemIds.size()>0){
			for(String itemid:toAddItemIds){
				System.out.println("toAddItemId==="+itemid);
				for(String rid:newChosenRefIds){						
					String id = itemsRefsRelation.get(rid);
					if(id.equals(itemid)){
						System.out.println("chosenRefIds.add。id"+rid);
						chosenRefIds.add(rid);
						System.out.println("after.add. id"+chosenRefIds.toString());
						if(answerItems.contains(itemid)){
							answerMap.get(itemid).add(rid);
							System.out.println("answerMap add1==="+answerMap.get(itemid).toString());
						}else{
							List<String> refids = new ArrayList<String>();
							refids.add(rid);
							answerMap.put(itemid, refids);
							System.out.println("answerMap add2==="+answerMap.get(itemid).toString());
						}
					}
					
				}
			}
			
		}
		
			
//		this.chosenRefIds = chosenRefIds;
		System.out.println("-----0--------this.session.put.chosenRefIds");
		this.session.put("chosenRefIds", chosenRefIds);
		System.out.println("-----1--------this.session.put.chosenRefIds");
//		List<String> sss = (List<String>) this.session.get("EPage"+this.dpi);
//		for(String refans:sss)
//			System.out.println("chosenRefIds---------"+refans);
		
		
	}

	/*calculatePageScore & make donelist for the score page*/
	/* Set session : pageindex(EPage_s+dpi)---scorePlus */
	/* Set session : pageindex(EPage_d+dpi)---donelist */
	private void calculatePageScore(){
		this.donelist = new HashMap<ExamItem,Integer>();
		List<String> chosenlist = (List<String>) this.session.get("chosenRefIds");
		for(HashMap<ExamItem,List<ExamRef>> examitem : this.pageitemlistf){
			if( examitem!=null){
				Set<ExamItem> ks = examitem.keySet();		
				if(ks.size()>1)
					System.out.println("@@-Dirty DATA, Pls INV....");
				for(ExamItem k:ks){
					List<ExamRef> refs = examitem.get(k);
					int cat = k.getCategory();					
					if(cat==1 && refs.size()!=2)									
						System.out.println("@@-DB data ERROR! Pls INV...refs.size()="+refs.size());					
					if(cat==2|| cat==1){
						
						for(String ans:chosenlist){
							if(ans.contains("_")){
								System.out.println("ERROR, Pls INV...");
								continue;
							}
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
							for(String ans:chosenlist){
								if(ans.contains("_"))
									continue;
								if(Integer.parseInt(ans)==ref.getId()){									
									if(!this.donelist.keySet().contains(k))
										this.donelist.put(k, 2);	
									if(ref.getIstrue()==1)
										realmatch +=1;																		
								}										
							}
							
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
	@SuppressWarnings("unchecked")
	private void calculatePageScore(List<HashMap<ExamItem,List<ExamRef>>> pgItemlistf){
		this.donelist = new HashMap<ExamItem,Integer>();
		List<String> chosenlist = (List<String>) this.session.get("chosenRefIds");		
		HashMap<ExamItem,Integer> totalDoneList = (HashMap<ExamItem,Integer>) this.session.get("totalDoneList");
		for(HashMap<ExamItem,List<ExamRef>> examitem : pgItemlistf){
			if( examitem!=null){
				Set<ExamItem> ks = examitem.keySet();		
				if(ks.size()>1)
					System.out.println("@@-Dirty DATA, Pls INV....");
				for(ExamItem k:ks){
					List<ExamRef> refs = examitem.get(k);
					int cat = k.getCategory();					
					if(cat==1 && refs.size()!=2)									
						System.out.println("@@-DB data ERROR! Pls INV...refs.size()="+refs.size());					
					if(cat==2|| cat==1){
						for(String ans:chosenlist){
							if(ans.contains("_")){
								System.out.println("ERROR, Pls INV...");
								continue;
							}
							for(ExamRef ref:refs){		
								System.out.println("--------calculatePageScore-cat2-----");
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
							for(String ans:chosenlist){
								if(ans.contains("_"))
									continue;
								if(Integer.parseInt(ans)==ref.getId()){									
									if(!this.donelist.keySet().contains(k))
										this.donelist.put(k, 2);	
									if(ref.getIstrue()==1)
										realmatch +=1;																		
								}										
							}							
						}
						if(shouldmatch==realmatch && realmatch>0){
							this.scorePlus +=2;
							this.donelist.put(k, 1);
						}
						if(!this.donelist.keySet().contains(k))
							this.donelist.put(k, 0);	
					}
					
						
//					totalDoneList.put(k, this.donelist.get(k));//顺序会错误
				}
				
			}else
				continue;
		}
		//////////////////////////////////////////////////////////////////
		Set<ExamItem> keys=this.donelist.keySet();
		for(ExamItem key:keys)
			System.out.println("this.donelist---key-"+key.getId()+", status-"+this.donelist.get(key));
		for(HashMap<ExamItem,List<ExamRef>> examitem : pgItemlistf){
			Set<ExamItem> keys1=examitem.keySet();
			for(ExamItem key1:keys1){
				System.out.println("pgItemlistf===key="+key1.getId());
				totalDoneList.put(key1, this.donelist.get(key1));
			}				
		}
		Set<ExamItem> keys2=totalDoneList.keySet();
		for(ExamItem key:keys2)
			System.out.println("totalDoneList---key-"+key.getId()+", status-"+totalDoneList.get(key));
////////////////////////////////////////////////////////////////
		
		/*SET session.subcore*/
		HashMap<String,Integer> subScore = (HashMap<String, Integer>) this.session.get("subscore");
		subScore.put(""+this.session.get("pi"),this.scorePlus);
		/*SET session.answerProgress*/
		ArrayList<Integer> answerProgress = new ArrayList<Integer>();		
		Set<ExamItem> totalDoneListKeys = totalDoneList.keySet();
		for(HashMap<ExamItem, List<ExamRef>> map:this.pageitemlistf)
		{
			Set<ExamItem> mkeys = map.keySet();
			for(ExamItem key:mkeys){
				if(totalDoneListKeys.contains(key))
					answerProgress.add(totalDoneList.get(key));
			}			
		}
			
//		for(HashMap<ExamItem,List<ExamRef>> examitem : pgItemlistf){
//			if(!totalDoneListKeys.contains(examitem)){
//				System.out.println("this.donelist---key-"+);
//				answerProgress.add(this.donelist.get(examitem));
//				System.out.println("answerProgress0===="+answerProgress.toString());
//			}else{
//				Integer index = answerProgress.indexOf(examitem);
//				answerProgress.add(index, this.donelist.get(examitem));
//				System.out.println("answerProgress1===="+answerProgress.toString());
//			}
//		}
		this.session.put("answerProgress",answerProgress);
		
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
//	private void checkAnswerProgress(){
//		ArrayList<Integer> answerProgress = (ArrayList<Integer>) this.session.get("answerProgress");
//		
//		for(HashMap<ExamItem, List<ExamRef>> map:this.pageitemlistf){
//			Set<ExamItem> keys = map.keySet();			
//			if(keys.size()!=1)
//				System.out.println("DATA ERROR, Pls INV...");
//			else{
//				for(ExamItem ei:keys){
//					for()
//				}
//					
//			}
//				
//		}
//	}
	@SuppressWarnings("unchecked")
	private void calculateTotalScore(){
		HashMap<String,Integer> subScore = (HashMap<String, Integer>) this.session.get("subscore");
		int score = 0;
		Set<String> keys = subScore.keySet();
		for(String key:keys)
			score += subScore.get(key);
		System.out.println("EXAM Total score="+score);
		this.session.put("score", score);
		System.out.println("EXAM Total score="+this.session.get("score"));
	}
	/* Get total score in this exam */
	/* Put all the exam related info into DB*/
	public String finalizeExam(){			
		this.dealThisPage();		
		this.check();						
		this.calculateTotalScore();
		this.clearSession();
		return "final";
	}
	
	@SuppressWarnings("unchecked")
	public String showError(){
		String seqid ="";
		String correct = "";
//		System.out.println();
		seqid = this.request.getParameter("seqid");
		if(seqid!=null){			
			this.session.put("summaryseqid", seqid);
			int id= Integer.parseInt(seqid);
			/*GET correct answer (String) to return in frontend*/
			if(id>0 && id<=CONSTANT.pageNum*CONSTANT.pageSize){
				HashMap<ExamItem, List<ExamRef>> eimap = ((List<HashMap<ExamItem,List<ExamRef>>>) this.session.get("elist")).get(id-1);
				Set<ExamItem> keys = eimap.keySet();				
				for(ExamItem key:keys){
					List<ExamRef> refs = eimap.get(key);
					for(int i=0; i<refs.size(); i++){
						if(refs.get(i).getIstrue()==1){
							correct += "<li class='right_item' style='font-size: 11pt;color: #1CA527;'>"+refs.get(i).getRef()+"</li>"; 
						}
					}
//					if(correct.length()>0 && correct.endsWith(","))
//						correct = correct.substring(0,correct.length()-1);
				}
			}
			this.session.put("correct", correct);
		}else
			System.out.println("No seqid input, PLS INV...");
		System.out.println("Request seq id = "+seqid);
		System.out.println("Request seq correct = "+correct);
		return "show";
	}
	
	private void clearSession(){
		Set<String> sessionKeys = this.session.keySet();
		for(String key:sessionKeys)
			System.out.println("key---(session)---"+key);
	}
	
	@SuppressWarnings("unchecked")
	private void check(){
		
		int score = (Integer) this.session.get("score");
		score += this.scorePlus;
		this.session.put("score", score);
		
		ArrayList<Integer> answerProgress = (ArrayList<Integer>) this.session.get("answerProgress");
		System.out.println("---------Got score in this page---"+this.scorePlus);
		System.out.println("---------DONE LIST-(THIS)--------------size="+this.donelist.keySet().size());
		for(ExamItem ei : this.donelist.keySet())
			System.out.println("----itemid="+ei.getId()+"(cat="+ei.getCategory()+")donestatus="+this.donelist.get(ei));
		System.out.println("---------DONE LIST-(TOTAL)-------------size="+answerProgress.size());
		for(Integer status:answerProgress)
			System.out.print("---"+status+"|");
		System.out.println("---------IN SESSION----------------");		
		List<String> ccc = (List<String>) this.session.get("chosenRefIds");
		for(String refans:ccc)
			System.out.println("chosenRefIds---------"+refans);

//		System.out.println("score-----"+this.session.get("score") ); 
	}

	@Override
	public String execute(){
//		this.dpi = (Integer) this.request.getSession().getAttribute("pi");
//		this.session.put("elist", this.pageitemlistf);
		System.out.println("EXCUTION preparing.........");
		this.setANS();
		this.calculatePageScore();
		this.countPage();
		this.check();
		
		return "success";
	
	}
	private List<HashMap<ExamItem,List<ExamRef>>> getPageItemslist(){
		List<HashMap<ExamItem,List<ExamRef>>> pislist = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
		int pi = (Integer) this.request.getSession().getAttribute("pi");
		int index0 = (pi-1)*CONSTANT.pageSize;
		for(int i=0;i<this.pageitemlistf.size(); i++){
			if(i>=index0 && i<index0+CONSTANT.pageSize)
				pislist.add(this.pageitemlistf.get(i));
		}
		return pislist;
	}
	private void nextPage(){
		System.out.println(">>>>>>>>>>>>----------pageSubmit-2, elist.size="
				+this.pageitemlistf.size());
		int pi = (Integer) this.request.getSession().getAttribute("pi");		
		int index0 = (pi-1)*CONSTANT.pageSize;
		System.out.println(">>>>>>>>>>>>----------pageSubmit-3, pi="+pi+", index0="+index0);
		
		this.request.getSession().setAttribute("index0",index0);		
		
		List<HashMap<String,List<ExamRef>>> ilf = new ArrayList<HashMap<String,List<ExamRef>>>();
		for(int i=0; i<index0+CONSTANT.pageSize; i++){			
			HashMap<String,List<ExamRef>> map = new HashMap<String,List<ExamRef>>();
			if(i>=index0)
				for(int n=0; n<this.pageitemlistf.size(); n++){
					HashMap<ExamItem,List<ExamRef>> il = this.pageitemlistf.get(n);
					Set<ExamItem> e = il.keySet();
					if(i==n){
						if(e.size()==1){
							for(ExamItem e1:e){
								map.put(e1.getQuestion(), il.get(e1));
								ilf.add(map);
							}
						}
					}						
				}				
			else{
				map.put(""+i,null);
				ilf.add(map);
			}
				
		}		
		System.out.println("New itemlistf size="+ilf.size());
		this.request.getSession().setAttribute("pageilf",ilf);
		for(int i=0; i<ilf.size(); i++){
			HashMap<String,List<ExamRef>> qmap = ilf.get(i);
			Set<String> qs = qmap.keySet();
			for(String q:qs){
				if(qmap.get(q)!=null){
					System.out.println(i+":Q------"+q+", ilf--List<ExamRef>---"+qmap.get(q).toString());
				}else
					System.out.println(i+":Q------"+q);
			}
			
		}
			
			
		System.out.println(">>>>>>>>>>>>----------pageSubmit-nextPage()over!");
	}
	private void syncPageInfoInDB(){
		
	}
	private void dealThisPage(){
		System.out.println(">>>>>>>>>>>>>>>0。。。。。。。。。");
		this.setANS();
		System.out.println(">>>>>>>>>>>>>>>1。。。。。。。。。setANS()");
		this.calculatePageScore(this.getPageItemslist());
		System.out.println(">>>>>>>>>>>>>>>2。。。。。。。。。calculatePageScore");
		this.syncPageInfoInDB();
		System.out.println(">>>>>>>>>>>>>>>3。。。。。。。。。syncPageInfoInDB");
	}

	public String pageSubmit(){
		String submit = "下一页";
		if(this.request.getParameter("next")!=null){
			submit = this.request.getParameter("next");
			System.out.println("name=next, value=?---"+this.request.getParameter("next"));
			if(submit.equals("提交") || submit.equals("结束考试")){
				this.dealThisPage();	
				this.check();	
				this.calculateTotalScore();
				return "over";
			}
		}
			
		this.dealThisPage();		
		this.countPage();
		this.nextPage();
		this.check();
		return "next";
	}

	public String pageSwitch(){
		int switchPageNum = 0;
		if(this.request.getParameter("pagenumber")!=null)
			switchPageNum = Integer.parseInt(this.request.getParameter("pagenumber"));
		System.out.println("----request page number="+this.request.getParameter("pagenumber"));
		if( switchPageNum != 0)
			this.request.getSession().setAttribute("pi", switchPageNum);
		this.nextPage();
		
		return "switch";
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void setSession(Map session) {
		this.session = session;
//		this.session.put("eirlist", this.pageitemlistf);
//		System.out.println("------------------------------Exam-Submit-1--setSession-----------------------------------");
//		System.out.println("setSession----Session().elist----"+
//				this.session.get("eirlist").toString());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;		
		System.out.println("------------------------------Exam-Submit-2-------------------------------------");
//		this.pageitemlistf = (List<HashMap<ExamItem, List<ExamRef>>>) 
//				this.request.getSession().getAttribute("itemlistf");	
		this.pageitemlistf = (List<HashMap<ExamItem, List<ExamRef>>>) 
				this.request.getSession().getAttribute("elist");
		System.out.println("setServletRequest----this.pageitemlistf.size----"+
				this.pageitemlistf.size());		
		
	}



}
