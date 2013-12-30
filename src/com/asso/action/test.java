package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import util.CONSTANT;
import util.SpringFactory;

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
		form.setDisplayname("化学物品管理记录");
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

	public String addDoc(){
		Doc doc = new Doc();
		doc.setFormid(4);
		doc.setCreatedate(CONSTANT.getNowTime());
		doc.setUserid(3);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
