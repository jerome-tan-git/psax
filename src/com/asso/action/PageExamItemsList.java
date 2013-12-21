package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CONSTANT;
import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asso.manager.ExamManager;
import com.asso.manager.ScoreManager;
import com.asso.model.Exam;
import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.model.JSExamRef;
import com.asso.model.Score;
import com.asso.model.User;
import com.asso.vo.ExamBuiltInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("pageexamitemslist") 
public class PageExamItemsList extends ActionSupport implements ServletRequestAware {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 725501200728064112L;
	
	private ExamManager em;		
	private HttpServletRequest request;	
	
	
	public PageExamItemsList(){		
		em = (ExamManager) SpringFactory.getObject("examManager");
	}
		
	public ExamManager getEm() {
		return em;
	}
	@Resource(name="examManager")//直接注入，替代初始化userManager
	public void setEm(ExamManager em) {
		this.em = em;
	}
	
	private ExamBuiltInfo eInfo = new ExamBuiltInfo();
	private List<ExamRef> reflist;
	private List<String> refQlist;
	private List<HashMap<ExamItem,List<ExamRef>>> itemlistf;
	private List<HashMap<String,List<ExamRef>>> itemlistSeq;
	private String examid;
	private int thispage;
	private int lastpage;
	private int nextpage;
	private int endpage;

	
	
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public int getThispage() {
		return thispage;
	}
	public void setThispage(int thispage) {
		this.thispage = thispage;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public int getNextpage() {
		return nextpage;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public ExamBuiltInfo geteInfo() {
		return eInfo;
	}
	public void seteInfo(ExamBuiltInfo eInfo) {
		this.eInfo = eInfo;
	}
	
	public List<String> getRefQlist() {
		return refQlist;
	}

	public void setRefQlist(List<String> refQlist) {
		this.refQlist = refQlist;
	}

	public List<HashMap<String, List<ExamRef>>> getItemlistSeq() {
		return itemlistSeq;
	}

	public void setItemlistSeq(List<HashMap<String, List<ExamRef>>> itemlistSeq) {
		this.itemlistSeq = itemlistSeq;
	}

	public List<ExamRef> getReflist() {
		return reflist;
	}
	public void setReflist(List<ExamRef> reflist) {
		this.reflist = reflist;
	}


	public List<HashMap<ExamItem, List<ExamRef>>> getItemlistf() {
		return itemlistf;
	}
	public void setItemlistf(List<HashMap<ExamItem, List<ExamRef>>> itemlistf) {
		this.itemlistf = itemlistf;
	}


//	private void loadItemlistf() throws ClassNotFoundException, SQLException{
//		List<HashMap<ExamItem,List<ExamRef>>> list = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
//		List<ExamItem> ilist = new ArrayList<ExamItem>();
//		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
//		System.out.println("after em.loadItemlistByExamid, size="+ilist.size());
//		for(ExamItem i:ilist){			
//			list.add(this.loadItemfWithItem(i));
//		}		
//		this.setItemlistf(list);
//	}
	private List<ExamRef> loadReflistByItemid(int itemid) throws ClassNotFoundException, SQLException{
		List<ExamRef> reflist = new ArrayList<ExamRef>();
		reflist = em.loadRefs(itemid);		
		return reflist;
	}

	private void calculatePage(int _ilistsize){
		
		int page = 1;
		String requestPage = (String)this.request.getParameter("page");
		if(requestPage!=null)
			page = Integer.parseInt(requestPage);
		
		int pagenum = _ilistsize/CONSTANT.pageNum;
		if(_ilistsize>pagenum*CONSTANT.pageNum)
			pagenum += 1;
		if(page>pagenum && page>0 && pagenum>0)
			page = pagenum;
		
		this.thispage = page;
		this.endpage = pagenum;
		if((pagenum-page)>=1)
			this.nextpage = page+1;
		else
			this.nextpage = page;
		
		if((page-1)>=1)
			this.lastpage = page-1;
		else
			this.lastpage = page;		
	}
	
	public String loadPageItemlistFByExamId() throws ClassNotFoundException, SQLException{
		
		this.examid = (String) this.request.getParameter("examid");
		System.out.println("GOT input examid="+this.examid);
		if(this.examid!=null)
			this.eInfo.setExamid(Integer.parseInt(this.examid));
		System.out.println("---_examid---"+this.eInfo.getExamid());
		
		List<ExamItem> ilist = new ArrayList<ExamItem>();
		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
		System.out.println("---------before dedupe, ilist size="+ilist.size());
		ilist = this.dedupeEIlist(ilist);
		System.out.println("---------after dedupe. ilist size="+ilist.size());
		this.refQlist = new ArrayList<String>();
		for(ExamItem i:ilist){
			if(i!=null)			
				this.refQlist.add(i.getQuestion());
			else
				System.out.println("DATA ERROR, PLS INV...");			
		}
		this.calculatePage(ilist.size());
		
		this.itemlistSeq = new ArrayList<HashMap<String,List<ExamRef>>>();
		int index0 = CONSTANT.pageNum*(this.thispage-1);
		int index1 = CONSTANT.pageNum*this.thispage-1;
		System.out.println("page="+this.thispage+",index0="+index0+",index1"+index1);

		
		if(ilist.size()>=CONSTANT.pageSize){
			if(ilist.size()>index0){
				if(ilist.size()>=index1){					
					for(int i=index0; i<=index1; i++){
						System.out.println("1) item id="+ilist.get(i).getId()+", No. "+i);
						List<ExamRef> refs = this.loadReflistByItemid(ilist.get(i).getId());
						for(ExamRef ref:refs)
							System.out.println("ref----"+ref.getRef());
						
						HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
						seqmap.put(ilist.get(i).getQuestion(), this.loadReflistByItemid(ilist.get(i).getId()));
						this.itemlistSeq.add(seqmap);
						System.out.println("1) itemlistSeq SIZE="+this.itemlistSeq.size());
					}
				}else{
					for(int i=index0; i<ilist.size(); i++){
						System.out.println("2) item id="+ilist.get(i).getId());
						List<ExamRef> refs = this.loadReflistByItemid(ilist.get(i).getId());
						for(ExamRef ref:refs)
							System.out.println("ref----"+ref.getRef());
						
						HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
//						refs = this.loadReflistByItemid(ilist.get(i).getId());
						seqmap.put(ilist.get(i).getQuestion(), refs);
						this.itemlistSeq.add(seqmap);
					}
				}				
			}
		}else{
			for(int i=0; i<ilist.size(); i++){
				System.out.println("3) item id="+ilist.get(i).getId());
				List<ExamRef> refs = this.loadReflistByItemid(ilist.get(i).getId());
				for(ExamRef ref:refs)
					System.out.println("ref----"+ref.getRef());
				
				HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
				seqmap.put(ilist.get(i).getQuestion(), this.loadReflistByItemid(ilist.get(i).getId()));
				this.itemlistSeq.add(seqmap);
			}
		}
		
		System.out.println("! loadPageItemlistFByExamId over, !!! itemlistSeq.size="+this.itemlistSeq.size());
		return "list";
	}

	
	private List<ExamItem> dedupeEIlist(List<ExamItem> _eil){
		System.out.println("------Before dedupe, size="+_eil.size());
		List<ExamItem> eil = new ArrayList<ExamItem>();
		List<Integer> seq = new ArrayList<Integer>();
		HashSet<Integer> itemidList = new HashSet<Integer>();
		HashSet<String> itemQuestionList = new HashSet<String>();
		for(ExamItem ei:_eil){
			int size1 = itemidList.size();
			itemidList.add(ei.getId());
			int size2 = itemidList.size();
			
			int s3 = itemQuestionList.size();
			itemQuestionList.add(ei.getQuestion());
			int s4 = itemQuestionList.size();
			
			if(size2>size1 && s4>s3)
				eil.add(ei);
		}
		System.out.println("-------After dedupe, size="+eil.size());
		return eil;
	}
	

	@Override
	public String execute(){	
		return "success";	
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 this.request=request;		
		 
	}

}
