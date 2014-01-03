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
import org.apache.struts2.interceptor.SessionAware;

import test.FieldObject;
import test.ParseObjects;
import util.CONSTANT;
import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.asso.manager.DocManager;
import com.asso.manager.FormManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;
import com.asso.model.Fields;
import com.asso.model.Form;
import com.opensymphony.xwork2.ActionSupport;

public class test extends ActionSupport implements ServletRequestAware,SessionAware{
	
	private HttpServletRequest request;
	private FormManager fm;	
	private DocManager dm;	
	
	public test(){
		fm = (FormManager) SpringFactory.getObject("formManager");
		dm = (DocManager) SpringFactory.getObject("docManager");
	}
	
	
	public DocManager getDm() {
		return dm;
	}
	@Resource(name="docManager")
	public void setDm(DocManager dm) {
		this.dm = dm;
	}
	public FormManager getFm() {
		return fm;
	}
	@Resource(name="formManager")
	public void setFm(FormManager fm) {
		this.fm = fm;
	}


	public String test(){
		
		Map root = new HashMap();
		root.put("message", "Hello FreeMarker!");
		
		
		
		return "test";
	}
	
	public String addform(){
		Form form  = new Form();		
		form.setDisplayname("采购清单");
		form.setFrontendtpl("buyingListing.ftl");
		
		List<Fields> flist = new ArrayList<Fields>();
		//1
		Fields fs = new Fields();
		fs.setFieldname("No:");
		fs.setFieldtype(1);//?
		flist.add(fs);
		//2
		fs = new Fields();
		fs.setFieldname("采购时间：");
		fs.setFieldtype(1);
		flist.add(fs);
		//3
		fs = new Fields();
		fs.setFieldname("原辅材料");
		fs.setFieldtype(1);
		flist.add(fs);
		//4
		fs = new Fields();
		fs.setFieldname("数量");
		fs.setFieldtype(1);		
		flist.add(fs);
		//5
		fs = new Fields();
		fs.setFieldname("供应商");
		fs.setFieldtype(1);
		flist.add(fs);
		//6
		fs = new Fields();
		fs.setFieldname("单价");
		fs.setFieldtype(1);
		flist.add(fs);
		//7
		fs = new Fields();
		fs.setFieldname("金额");
		fs.setFieldtype(1);
		flist.add(fs);	
		//8
		fs = new Fields();
		fs.setFieldname("审核： ");
		fs.setFieldtype(1);
		flist.add(fs);
		//9
		fs = new Fields();
		fs.setFieldname("申请：");
		fs.setFieldtype(1);
		flist.add(fs);
		
		
		form.setFields(flist);
		try {
			fm.addForm(form);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String updateform(){
		Form form  = new Form();
		form.setFormid(3);
		form.setDisplayname("化学物品管理记录--修订2");
		form.setFrontendtpl("chemicalManageRecord.ftl");
		
		List<Fields> flist = new ArrayList<Fields>();
		//1
		Fields fs = new Fields();
		fs.setFieldname("日期");
		fs.setFieldtype(1);//?
		flist.add(fs);
		//2
		fs = new Fields();
		fs.setFieldname("化学物品名称");
		fs.setFieldtype(1);
		flist.add(fs);
		//3
		fs = new Fields();
		fs.setFieldname("规格");
		fs.setFieldtype(1);
		flist.add(fs);
		//4
		fs = new Fields();
		fs.setFieldname("数量");
		fs.setFieldtype(1);
		fs.setGroupname("进货情况");
		flist.add(fs);
		//5
		fs = new Fields();
		fs.setFieldname("批号");
		fs.setFieldtype(1);
		fs.setGroupname("进货情况");
		flist.add(fs);
		//6
		fs = new Fields();
		fs.setFieldname("数量");
		fs.setFieldtype(1);
		fs.setGroupname("领用情况");
		flist.add(fs);
		//7
		fs = new Fields();
		fs.setFieldname("批号");
		fs.setFieldtype(1);
		fs.setGroupname("领用情况");
		flist.add(fs);
		//8
		fs = new Fields();
		fs.setFieldname("领用人");
		fs.setFieldtype(1);
		fs.setGroupname("领用情况");
		flist.add(fs);
		//9
		fs = new Fields();
		fs.setFieldname("库存数量");
		fs.setFieldtype(1);
		flist.add(fs);
		//10
		fs = new Fields();
		fs.setFieldname("经手人");
		fs.setFieldtype(1);
		flist.add(fs);
		
		form.setFields(flist);
		try {
			fm.updateForm(form);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String loadForm(){
		int formid = 3;
		String jsontext = "";
		Form f = new Form();
		try {
			f = fm.loadFormById(formid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("LOAD form---"+f.toString());
		
		return SUCCESS;
	}
	public String loadDocs(){
		int formid = 4;
		String jsontext = "";
		Form f = new Form();
		try {
//			f = fm.loadFormById(formid);
			f = fm.loadFormWithFieldsById(formid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("LOAD form---"+f.toString());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		List<Doc> doclist = new ArrayList<Doc>();
		try {
			doclist = dm.loadDocsWithFieldValueList(formid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Doc d :doclist){
			System.out.println("LOAD doc---"+d.toString());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		HashMap<String,List<Fields>> group = new HashMap<String,List<Fields>>();//KEY-groupname, VALUE-fieldname 
		for(Fields field:f.getFields()){
			if(field.getGroupname()!=null){
				String gname = field.getGroupname(); 
				if(group.keySet().contains(gname)){
					group.get(gname).add(field);
				}else{
					List<Fields> fl = new ArrayList<Fields>();
					fl.add(field);
					group.put(gname, fl);
				}					
			}
		}
		////////////////check group data
		Set<String> gnames = group.keySet(); 
		for(String gn:gnames)
			System.out.println("^^^^^  "+gn+":"+group.get(gn).toString());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ show DOC_1 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		Doc doc = doclist.get(0);
		int docid = doc.getDocid();
		List<FieldValue> fvs = doc.getFvlist();
		
		HashSet<Integer> indexes = new HashSet<Integer>();
		for(FieldValue fv :doc.getFvlist()){
			indexes.add(fv.getFieldvalueindex());
		}
		System.out.println("----GOT indexes----"+indexes.toString());
		System.out.println("----Total fieldvalue size(thisdoc)----"+doc.getFvlist().size());
//		
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    jmap.put("type","edit");
	    
	    Set<String> groupnames = group.keySet();
	    if(group!=null && groupnames.size()>0){//has group	    	
	    	for(String gn : groupnames){
	    		System.out.println("----into group:"+gn);
	    		List<Map> groupmap = new ArrayList<Map>();
	    		Map fmap = new HashMap();
	    		for(Integer index:indexes){
	    			System.out.println("----index="+index);
	    			HashMap unit = new HashMap();
	    			
	    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
					for(FieldValue fv :fvs){
						if(fv.getFieldvalueindex()==index)
							uniFvs.add(fv);
					}
					for(FieldValue v:uniFvs){//show teamFieldValue with fieldname						
						int fieldid = v.getFieldid();
						for(Fields field:group.get(gn)){
							if(field.getFieldid()==fieldid){
								System.out.println("field.getFieldname()---"+field.getFieldname());
								unit.put(field.getFieldname(),v.getValue());								
							}
						}
					}
	    			groupmap.add(unit);
	    		}	    		
	    		jmap.put(gn, groupmap);
	    	}
	    		
	    }else{//no group
	    	
	    }
		System.out.println("FINAL JSONTEXT------");
		jsontext=JSON.toJSONString(jmap, true); 
		System.out.println(jsontext);
		
//		ParseObjects po = new ParseObjects("edit");
//		FieldObject fo = new FieldObject();
//		fo.setFieldName("options");
//		fo.setFieldValue("options_");
//		po.addField(fo);
//		FieldObject fo_t = new FieldObject();
//		fo_t.setFieldName("titel");
//		fo_t.setFieldValue(f.getDisplayname());
//		po.addField(fo_t);

//		Doc doc = doclist.get(0);
//		int docid = doc.getDocid();
////		List<FieldValue> fvs = doc.getFvlist();
//		
//		HashSet<Integer> indexes = new HashSet<Integer>();
//		for(FieldValue fv :doc.getFvlist()){
//			indexes.add(fv.getFieldvalueindex());
//		}
//		System.out.println("----GOT indexes----"+indexes.toString());
//		System.out.println("----Total fieldvalue size(thisdoc)----"+doc.getFvlist().size());
//		
//		Map gmap = new HashMap();
//		for(String gname:group.keySet()){//sep by group
//		
//			System.out.println("---gname="+gname);
//			List<Fields> fs = group.get(gname);			
//			List<FieldValue> fvs = doc.getFvlist();
//			
//			System.out.println("fs---"+fs.toString());
//			System.out.println("fvs---"+fvs.toString());
//			for(Fields field:fs)
//				System.out.println("---fieldname----"+field.getFieldname());
//			
//			List<FieldValue> fvs_show = new ArrayList<FieldValue>();
//			for(FieldValue v:fvs){
//				for(Fields field:fs){
//					if(field.getFieldid()==v.getFieldid()){
//						System.out.println("----------------------------get "+v.toString());
//						fvs_show.add(v);
//					}						
//				}
//			}
//			System.out.println("---fvs_show(size)---"+fvs_show.size());
//			
//			List<FieldObject> fos = new ArrayList<FieldObject>();
//			for(Integer i:indexes){//sep by index
//				System.out.println("---index="+i);
//				List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
//				for(FieldValue fv :fvs_show){
//					if(fv.getFieldvalueindex()==i)
//						uniFvs.add(fv);
//				}
//				for(FieldValue v:uniFvs){//show teamFieldValue with fieldname
//					FieldObject fo0 = new FieldObject();
//					int fieldid = v.getFieldid();
//					for(Fields field:fs){
//						if(field.getFieldid()==fieldid){
//							System.out.println("field.getFieldname()---"+field.getFieldname());
//							fo0.setFieldName(field.getFieldname());
//							break;
//						}
//					}					
//					fo0.setFieldValue(v.getValue());					
////					fo0.setFieldIndex(i);
////					fo0.setFieldGroup(gname);
//					fos.add(fo0);
//				}
//				gmap.put(gname, fos);
//				
//			}
//		}
		
//		po.addField(gmap);
//		
//		System.out.println(po.getJSON());
		

		return SUCCESS;
	}

	public String addDoc(){
		Doc doc = new Doc();
		doc.setFormid(5);
		doc.setCreatedate(CONSTANT.getNowTime());
		doc.setUserid(2);
		doc.setStep(1);
		
		int maxindex = 0;
		int docid = 1;
		List<FieldValue> fvlist = new ArrayList<FieldValue>();
		
		FieldValue fv = new FieldValue();
		fv.setFieldid(37);
		fv.setValue(CONSTANT.getTodayDate());
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(38);
		fv.setValue("乙醚");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(39);
		fv.setValue("50ml");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(40);
		fv.setValue("200");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(41);
		fv.setValue("bkjm-20131110-dvnwioeh-439");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(42);
		fv.setValue("30");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(43);
		fv.setValue("fgkg-20120924-dgadjgio-453");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(44);
		fv.setValue("苏三");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(45);
		fv.setValue("150");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(46);
		fv.setValue("阿斗");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		doc.setFvlist(fvlist);
		
		try {
			dm.addDoc(doc);
			dm.addFieldValue(doc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateDoc(){
		Doc doc = new Doc();
		int docid = 5;//keep the same as addDoc()
		doc.setDocid(docid);
		doc.setFormid(4);
		doc.setCreatedate(CONSTANT.getNowTime());
		doc.setUserid(1);
		doc.setStep(2);
		
		int maxindex = dm.getMaxFVIndex(doc.getDocid());
//		int maxindex = 1;
		
		List<FieldValue> fvlist = new ArrayList<FieldValue>();
		
		FieldValue fv = new FieldValue();
		fv.setFieldid(37);
		fv.setValue(CONSTANT.getTodayDate());
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(38);
		fv.setValue("乙醚--");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(39);
		fv.setValue("50ml");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(40);
		fv.setValue("200");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(41);
		fv.setValue("bkjm-20131110-dvnwioeh-439---");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(42);
		fv.setValue("30");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(43);
		fv.setValue("fgkg-20120924-dgadjgio-453--");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(44);
		fv.setValue("苏三大妈");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(45);
		fv.setValue("150");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(46);
		fv.setValue("阿斗他爹");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		doc.setFvlist(fvlist);
		
		try {
			dm.updateDoc(doc);
			dm.addFieldValue(doc);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateFieldValues(){
		
		List<FieldValue> fvlist = new ArrayList<FieldValue>();
		int docid=4;
		
		FieldValue fv = new FieldValue();
		fv.setFieldid(37);
		fv.setValue(CONSTANT.getTodayDate());
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(38);
		fv.setValue("h乙醚--");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(39);
		fv.setValue("&^50ml");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(40);
		fv.setValue("200");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(41);
		fv.setValue("bk_jm-20131110-dvnwioeh-439---");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(42);
		fv.setValue("30");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(43);
		fv.setValue("fg_kg-20120924-dgadjgio-453--");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(44);
		fv.setValue("h苏三大妈");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(45);
		fv.setValue("150");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(46);
		fv.setValue("h阿斗他爹");
		fv.setFieldvalueindex(1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		dm.updateFieldValue(fvlist);
		
		return SUCCESS;
	}
	public String addFieldValues(){
		Doc doc = new Doc();
		int docid = 4;//keep the same as addDoc()
		doc.setDocid(docid);
		doc.setFormid(4);
		doc.setCreatedate(CONSTANT.getNowTime());
		doc.setUserid(1);
		doc.setStep(2);
		
		int maxindex = dm.getMaxFVIndex(doc.getDocid());
		List<FieldValue> fvlist = new ArrayList<FieldValue>();
		
		FieldValue fv = new FieldValue();
		fv.setFieldid(37);
		fv.setValue(CONSTANT.getTodayDate());
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(38);
		fv.setValue("甲醇--");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(39);
		fv.setValue("150ml");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(40);
		fv.setValue("800");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(41);
		fv.setValue("oh-I-believe-in-yesterdaty---木子");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(42);
		fv.setValue("30");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(43);
		fv.setValue("salad-de-fruit-jolie-jolie");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(44);
		fv.setValue("小野丽莎");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(45);
		fv.setValue("150");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		fv = new FieldValue();
		fv.setFieldid(46);
		fv.setValue("希望");
		fv.setFieldvalueindex(maxindex+1);
		fv.setDocid(docid);
		fvlist.add(fv);
		
		doc.setFvlist(fvlist);
//		dm.addFieldValue(doc);
		dm.addFieldValue(fvlist);
	
		return SUCCESS;
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
