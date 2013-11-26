package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import util.CONSTANT;

import com.asso.manager.ExamManager;
import com.asso.model.Exam;
import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.vo.ExamBuiltInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("examitemslist") 
public class ExamItemsList extends ActionSupport implements ModelDriven {
	
	private ExamBuiltInfo eInfo = new ExamBuiltInfo();
	private ExamManager em;
	private ApplicationContext ctx;	

	public ExamItemsList(){
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		em = (ExamManager) ctx.getBean("examManager");
		CONSTANT.seq = new HashMap<String, Integer>();
		CONSTANT.seq.put("a",1);
		CONSTANT.seq.put("b",2);
		CONSTANT.seq.put("c",3);
		CONSTANT.seq.put("d",4);
		CONSTANT.seq.put("e",5);
		CONSTANT.seq.put("f",6);
		CONSTANT.seq.put("g",7);
		CONSTANT.seq.put("h",8);
		CONSTANT.seq.put("i",9);
		CONSTANT.seq.put("j",10);
		CONSTANT.seq.put("k",11);
		CONSTANT.seq.put("l",12);
		CONSTANT.seq.put("m",13);
		CONSTANT.seq.put("n",14);
	}
		
	public ExamManager getEm() {
		return em;
	}
	@Resource(name="examManager")//直接注入，替代初始化userManager
	public void setEm(ExamManager em) {
		this.em = em;
	}

	public ExamBuiltInfo geteInfo() {
		return eInfo;
	}
	public void seteInfo(ExamBuiltInfo eInfo) {
		this.eInfo = eInfo;
	}

	private ExamRef ref;
	private List<ExamRef> reflist;
	private ExamItem item;
	private List<ExamItem> itemlist;
	private HashMap<ExamItem,List<ExamRef>> itemf;
	private List<HashMap<ExamItem,List<ExamRef>>> itemlistf;
	public ExamRef getRef() {
		return ref;
	}
	public void setRef(ExamRef ref) {
		this.ref = ref;
	}
	public List<ExamRef> getReflist() {
		return reflist;
	}
	public void setReflist(List<ExamRef> reflist) {
		this.reflist = reflist;
	}
	public ExamItem getItem() {
		return item;
	}
	public void setItem(ExamItem item) {
		this.item = item;
	}
	public List<ExamItem> getItemlist() {
		return itemlist;
	}
	public void setItemlist(List<ExamItem> itemlist) {
		this.itemlist = itemlist;
	}
	public HashMap<ExamItem, List<ExamRef>> getItemf() {
		return itemf;
	}
	public void setItemf(HashMap<ExamItem, List<ExamRef>> itemf) {
		this.itemf = itemf;
	}
	public List<HashMap<ExamItem, List<ExamRef>>> getItemlistf() {
		return itemlistf;
	}
	public void setItemlistf(List<HashMap<ExamItem, List<ExamRef>>> itemlistf) {
		this.itemlistf = itemlistf;
	}

	public String addExam() throws ClassNotFoundException, SQLException{
		System.out.println("GET examname--->"+this.eInfo.getExamname());
		Exam e = new Exam();
		e.setName(this.eInfo.getExamname());
		e.setGroupid(this.eInfo.getGroupid());		
		em.add(e);
		return "save";
	}
	
	public String addItem(){
		try {
			this.addExamItem();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int newItemId = 0;
		try {
			newItemId = this.loadItemByQ();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("----- New added ItemId-----"+newItemId);
		System.out.println("----- New added refs-----"+this.eInfo.getRefs());
		this.addExamRefs(newItemId, this.eInfo.getRefs(),this.eInfo.getAnswers());
		
		return "save";
	}
	
	private void addExamItem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setExamid(this.eInfo.getExamid());
		ei.setCategory(this.eInfo.getCategory());
		ei.setQuestion(this.eInfo.getQuestion());
		em.add(ei);
	}
	
	private HashSet<Integer> getRefAnsByInputString(){
		String refans = this.eInfo.getAnswers().trim();
		HashSet<Integer> ans = new HashSet<Integer>();
		for (int i=0; i<refans.length(); i++) {
			String x = (String) refans.subSequence(i, i+1);
			ans.add(CONSTANT.seq.get(x));
			System.out.println("--1--x="+x+"::i="+CONSTANT.seq.get(x));
		}
		return ans;
	}
	private HashSet<Integer> getRefAnsByInputString(String _answers){
		String refans = _answers.trim();
		HashSet<Integer> ans = new HashSet<Integer>();
		for (int i=0; i<refans.length(); i++) {
			String x = (String) refans.subSequence(i, i+1);
			ans.add(CONSTANT.seq.get(x));
			System.out.println("--1--x="+x+"::i="+CONSTANT.seq.get(x));
		}
		return ans;
	}
	
	private List<String> getRefQsByInputString(){
		String refStr = this.eInfo.getRefs().trim();		
		String[] s_ref = refStr.split(",");		
		List<String> refQs = new ArrayList<String>();
		for(int i=0; i<s_ref.length;i++){
			String x = s_ref[i];
			String y = x.substring(x.indexOf(")")+1,x.length());
			refQs.add(y);			
		}
		return refQs;
	}
	private List<String> getRefQsByInputString(String _refstr){
		String refStr = _refstr.trim();		
		String[] s_ref = refStr.split(",");		
		List<String> refQs = new ArrayList<String>();
		for(int i=0; i<s_ref.length;i++){
			String x = s_ref[i];
			String y = x.substring(x.indexOf(")")+1,x.length());
			refQs.add(y);			
			System.out.println("--2--y="+y);
		}
		return refQs;
	}
	private void addExamRefs(){
		
		HashSet<Integer> ans = this.getRefAnsByInputString();
		List<ExamRef> refs = new ArrayList<ExamRef>();
		List<String> refQs = this.getRefQsByInputString();
		for(int i=0; i<refQs.size(); i++){
			ExamRef e_ref = new ExamRef();
			e_ref.setRef(refQs.get(i));
			e_ref.setItemid(this.eInfo.getExamitemid());
			if(ans.contains(i+1))
				e_ref.setIstrue(1);
			else
				e_ref.setIstrue(0);
			refs.add(e_ref);			
		}

		try {
			em.add(refs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void addExamRefs(int _itemid, String _refstring,String _answers){
		System.out.println("_itemid, _refstring,_answer="+_itemid+":"+_refstring+":"+_answers);
		HashSet<Integer> ans = this.getRefAnsByInputString(_answers);
		List<ExamRef> refs = new ArrayList<ExamRef>();
//		List<String> refQs = this.getRefQsByInputString();
		List<String> refQs = this.getRefQsByInputString(_refstring);
		for(int i=0; i<refQs.size(); i++){
			ExamRef e_ref = new ExamRef();
			e_ref.setRef(refQs.get(i));
			e_ref.setItemid(_itemid);
			if(ans.contains(i+1))
				e_ref.setIstrue(1);
			else
				e_ref.setIstrue(0);
			refs.add(e_ref);			
		}

		try {
			em.add(refs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void editExamItem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setId(this.eInfo.getExamitemid());
		ei.setExamid(this.eInfo.getExamid());
		ei.setCategory(this.eInfo.getCategory());
		ei.setQuestion(this.eInfo.getQuestion());
		em.edit(ei);		
	}
	
	private void editExamref() throws ClassNotFoundException, SQLException{
		ExamRef ref = new ExamRef();
		ref.setId(this.eInfo.getRefid());
		ref.setRef(this.eInfo.getRef());
		ref.setItemid(this.eInfo.getExamitemid());
		ref.setIstrue(this.eInfo.getIstrue());
		em.edit(ref);		
	}
	
	private void deleteExamitem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setId(this.eInfo.getExamitemid());
//		ei.setExamid(this.eInfo.getExamid());
//		ei.setCategory(this.eInfo.getCategory());
//		ei.setQuestion(this.eInfo.getQuestion());
		em.delete(ei);
	}
	
	private void deleteExamref() throws ClassNotFoundException, SQLException{
		ExamRef ref = new ExamRef();
		ref.setId(this.eInfo.getRefid());
//		ref.setRef(this.eInfo.getRef());
//		ref.setItemid(this.eInfo.getExamitemid());
//		ref.setIstrue(this.eInfo.getIstrue());
		em.delete(ref);
	}
	
	private void deleteExamrefsByitemid() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setId(this.eInfo.getExamitemid());
		em.deleteRefsByItem(ei);	
	}
	
	private void loadRef() throws ClassNotFoundException, SQLException{
//		ExamRef ref = new ExamRef();
//		ref = em.loadRef(this.eInfo.getRefid());
		this.ref = em.loadRef(this.eInfo.getRefid());		
	}
	
	private void loadReflist() throws ClassNotFoundException, SQLException{
//		List<ExamRef> reflist = new ArrayList<ExamRef>();		
//		reflist = em.loadRefs(this.eInfo.getExamitemid());
		this.reflist = em.loadRefs(this.eInfo.getExamitemid());
	}
	private List<ExamRef> loadReflistByItemid(int itemid) throws ClassNotFoundException, SQLException{
		List<ExamRef> reflist = new ArrayList<ExamRef>();
		reflist = em.loadRefs(itemid);		
		return reflist;
	}
	private List<ExamRef> addSeq4Reflist(List<ExamRef> _erlist) {		
		for(int i=0; i<_erlist.size(); i++){
			String ref =CONSTANT.alphas[i]+") "+ _erlist.get(i).getRef();
			System.out.println("-----Updated REF="+ref); 
			_erlist.get(i).setRef(ref);
		}
		return _erlist;
	}
	
	private void loadItem() throws ClassNotFoundException, SQLException{
		this.setItem(em.loadItem(this.eInfo.getExamitemid()));		
	}
	private int loadItemByQ() throws ClassNotFoundException, SQLException{
		return em.loadItemByQ(this.eInfo.getQuestion()).getId();		
	}
	
	private void loadItemlistByCatid() throws ClassNotFoundException, SQLException{
		this.setItemlist(em.loadItemlistByCatid(this.eInfo.getCategory()));
	}
	private void loadItemlistByExamid() throws ClassNotFoundException, SQLException{
		this.setItemlist(em.loadItemlistByExamid(this.eInfo.getExamitemid()));		
	}
	
	private void loadItemf() throws ClassNotFoundException, SQLException{	
		this.setItem(em.loadItem(this.eInfo.getExamitemid()));
		HashMap<ExamItem,List<ExamRef>> family = new HashMap<ExamItem,List<ExamRef>>();
		family.put(this.getItem(),this.loadReflistByItemid(this.item.getId()));
		this.setItemf(family);		
	}
	private HashMap<ExamItem,List<ExamRef>> loadItemfWithId(int _itemid) throws ClassNotFoundException, SQLException{
		HashMap<ExamItem,List<ExamRef>> family = new HashMap<ExamItem,List<ExamRef>>();
		ExamItem i = new ExamItem();
		i = em.loadItem(_itemid);
		family.put(i,this.loadReflistByItemid(i.getId()));
		return family;
	}
	private HashMap<ExamItem,List<ExamRef>> loadItemfWithItem(ExamItem _item) throws ClassNotFoundException, SQLException{
		HashMap<ExamItem,List<ExamRef>> family = new HashMap<ExamItem,List<ExamRef>>();
		
		//family.put(_item,this.loadReflistByItemid(_item.getId()));
		family.put(_item,this.addSeq4Reflist(this.loadReflistByItemid(_item.getId())) );
		return family;
	}
	private void loadItemlistf() throws ClassNotFoundException, SQLException{
		List<HashMap<ExamItem,List<ExamRef>>> list = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
		List<ExamItem> ilist = new ArrayList<ExamItem>();
		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
		System.out.println("after em.loadItemlistByExamid, size="+ilist.size());
		for(ExamItem i:ilist){			
			list.add(this.loadItemfWithItem(i));
		}		
		this.setItemlistf(list);
	}
	public String loadItemlistFByExamId() throws ClassNotFoundException, SQLException{
		System.out.println("---_examid---"+this.eInfo.getExamid());
		List<HashMap<ExamItem,List<ExamRef>>> list = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
		List<ExamItem> ilist = new ArrayList<ExamItem>();
		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
		System.out.println("after em.loadItemlistByExamid, size="+ilist.size());
		for(ExamItem i:ilist){
//			list.add(this.loadItemfWithId(i.getId()));
			list.add(this.loadItemfWithItem(i));
		}		
		this.setItemlistf(list);
		return "list";
	}
	
	@Override
	public String execute(){
		
		System.out.println("GET exam input info--->"+this.eInfo.toString());
		
		try {
			this.addExam();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		this.addExamItem();
//		this.addExamRefs();
//		this.editExamItem();
//		this.editExamref();
//		this.deleteExamref();
//		this.deleteExamitem();
//		this.deleteExamrefsByitemid();
//		this.loadRef();
//		this.loadReflist();
//		this.loadItem();
//		this.loadItemlistByCatid();
//		this.loadItemlistByExamid();
//		this.loadItemf();
		
//		try {
//			this.loadItemlistf();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		User u = new User();
//		u.setPassword(this.uInfo.getPassword());
//		u.setUsername(this.uInfo.getUsername());
//
//		try {
//			um.add(u);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return "success";
	
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.eInfo;
	}

}
