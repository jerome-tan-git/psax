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
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.asso.manager.DocManager;
import com.asso.manager.FormManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;
import com.asso.model.Fields;
import com.asso.model.Form;
import com.asso.vo.FormInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("formedit") 
public class FormEdit extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,SessionAware{
	
	private FormManager fm;	
	private DocManager dm;	
	
	private String jsonText3;
	private Form f;
	private List<Doc> doclist;
	private Doc doc;
	private Map<String, List<String>> formDataMap;
	private Map<String, List<String>> formValueMap;
	private Map<String, List<String>> formDataMapModel;
	
	private HttpServletRequest request;	
	private Map session;
	private FormInfo finfo;

	public FormEdit(){		
		fm = (FormManager) SpringFactory.getObject("formManager");
		dm = (DocManager) SpringFactory.getObject("docManager");
	}	
		
	
	public FormManager getFm() {
		return fm;
	}
	@Resource(name="formManager")
	public void setFm(FormManager fm) {
		this.fm = fm;
	}
	public DocManager getDm() {
		return dm;
	}
	@Resource(name="docManager")
	public void setDm(DocManager dm) {
		this.dm = dm;
	}
	
	
	public FormInfo getFinfo() {
		return finfo;
	}
	public void setFinfo(FormInfo finfo) {
		this.finfo = finfo;
	}

	public Map<String, List<String>> getFormDataMap() {
		return formDataMap;
	}	
	public Map<String, List<String>> getFormValueMap() {
		return formValueMap;
	}	
	public Map<String, List<String>> getFormDataMapModel() {
		return formDataMapModel;
	}
	public void setFormDataMapModel(Map<String, List<String>> formDataMapModel) {
		this.formDataMapModel = formDataMapModel;
	}
	
	public void setFormDataMap(Map<String, List<String>> formDataMap) {
		this.formDataMap = formDataMap;
	}
	public void setFormValueMap(Map<String, List<String>> formValueMap) {
		this.formValueMap = formValueMap;
	}


	public Form getF() {
		return f;
	}
	public void setF(Form f) {
		this.f = f;
	}
	public List<Doc> getDoclist() {
		return doclist;
	}
	public void setDoclist(List<Doc> doclist) {
		this.doclist = doclist;
	}
	public String getJsonText3() {
		return jsonText3;
	}
	public void setJsonText3(String jsonText3) {
		this.jsonText3 = jsonText3;
	}
	public Doc getDoc() {
		return doc;
	}
	public void setDoc(Doc doc) {
		this.doc = doc;
	}
	
	
	private void setDocForm(int _docid){
//		this.doclist = new ArrayList<Doc>();
//		try {
//			doclist = dm.loadDocsWithFieldValueList(formid);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		for(Doc d :doclist){
//			System.out.println("LOAD doc---"+d.toString());
//		}
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LOAD doclist.size---"+doclist.size());	
//		Doc doc = new Doc();		
//		if(docid>0 && doclist.size()>0){
//			for(Doc d:doclist){
//				if(docid==d.getDocid()){
//					doc=d;
//					break;
//				}					
//			}
//		}else{
//			System.out.println("DATA ERROR, PLS INV...");
//		}
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~ chosen DOC ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		try {
			this.doc = dm.loadDocWithFieldValueList(_docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ got DOC ~~~~~~~~~~~~~~~~~~~~~~toString--"+this.doc.toString());

		int docid = 0;
		if(this.doc!=null)
			docid = this.doc.getFormid();
		else{
			if(this.request.getParameter("formid")!=null)
				docid = Integer.parseInt(this.request.getParameter("formid"));
		}
		try {
			f = fm.loadFormWithFieldsById(docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LOAD form---"+f.toString());
	}


	public String listJsonDocById(){
		int formid = 1;
		if(this.request.getParameter("formid")!=null)
			formid = Integer.parseInt(this.request.getParameter("formid"));
		this.setDocForm(formid);
		
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
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ got GROUPmap ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");		
		
		List<FieldValue> fvs = doc.getFvlist();		
		HashSet<Integer> indexes = new HashSet<Integer>();
		for(FieldValue fv : fvs)
			indexes.add(fv.getFieldvalueindex());		
		System.out.println("----GOT indexes----"+indexes.toString());
		System.out.println("----Total fieldvalue size(thisdoc)----"+doc.getFvlist().size());
		
		this.formDataMap = new HashMap<String,List<String>>();
		this.formValueMap = new HashMap<String,List<String>>();
		this.formDataMapModel = new HashMap<String,List<String>>();
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    jmap.put("type","edit");
	    
	   
	    if(group!=null && group.keySet().size()>0){//has group	   
	    	 Set<String> groupnames = group.keySet();
	    	for(String gn : groupnames){
	    		System.out.println("----into group:"+gn);
	    		List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();
	    		
	    		for(Integer index:indexes){
	    			System.out.println("----index="+index);
	    			HashMap<String,String> unit = new HashMap<String,String>();
	    			
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
								unit.put(field.getFieldname(),v.getValue());	//SET the unit form data/value
								if(this.formDataMap.keySet().contains(gn))		//SET data for .ftl <table>
									this.formDataMap.get(gn).add(field.getFieldname());
								else{
									List<String> data = new ArrayList<String>();
									data.add(field.getFieldname());
									this.formDataMap.put(gn, data);								
								}
								if(this.formValueMap.keySet().contains(gn))
									this.formValueMap.get(gn).add(v.getValue());
								else{
									List<String> value = new ArrayList<String>();
									value.add(v.getValue());
									this.formValueMap.put(gn, value);
								}
							}
						}
					}
	    			groupmap.add(unit);
	    		}	    		
	    		jmap.put(gn, groupmap);
	    	}
	    		
	    }else{//no group
	    	
//	    	List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();
//    		String gn = "data_1"; 
//    		for(Integer index:indexes){
//    			System.out.println("----index="+index);
//    			HashMap<String,String> unit = new HashMap<String,String>();
//    			
//    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
//				for(FieldValue fv :fvs){
//					if(fv.getFieldvalueindex()==index)
//						uniFvs.add(fv);
//				}
//				for(FieldValue v:uniFvs){//show teamFieldValue with fieldname						
//					int fieldid = v.getFieldid();
//					for(Fields field:group.get(gn)){
//						if(field.getFieldid()==fieldid){
//							System.out.println("field.getFieldname()---"+field.getFieldname());
//							unit.put(field.getFieldname(),v.getValue());	//SET the unit form data/value
//							if(this.formDataMap.keySet().contains(gn))		//SET data for .ftl <table>
//								this.formDataMap.get(gn).add(field.getFieldname());
//							else{
//								List<String> data = new ArrayList<String>();
//								data.add(field.getFieldname());
//								this.formDataMap.put(gn, data);								
//							}
//							if(this.formValueMap.keySet().contains(gn))
//								this.formValueMap.get(gn).add(v.getValue());
//							else{
//								List<String> value = new ArrayList<String>();
//								value.add(v.getValue());
//								this.formValueMap.put(gn, value);
//							}
//						}
//					}
//				}
//    			groupmap.add(unit);
//    		}
//    		jmap.put(gn, groupmap);
	    }
		System.out.println("FINAL JSONTEXT------");
		this.jsonText3=JSON.toJSONString(jmap, true); 
		System.out.println(this.jsonText3);
		System.out.println("--------------------------------------------------------------");
		
		for(String data:this.formDataMap.keySet()){
			System.out.println("1) KEY|groupname--"+data+",VALUE|form_Data"+this.formDataMap.get(data).toString());
			HashSet<String> datamodel = new HashSet<String>();
			List<String> list = this.formDataMap.get(data);
			for(String l:list)
				datamodel.add(l);	
			List<String> modelist = new ArrayList<String>();
			for(String l:datamodel)
				modelist.add(l);			
			this.formDataMapModel.put(data, modelist);
			System.out.println("2) KEY|groupname--"+data+",VALUE|form_data_model"+this.formDataMapModel.get(data).toString());
		}
		for(String value:this.formValueMap.keySet())
			System.out.println("3) KEY|groupname--"+value+",VALUE|form_value"+this.formValueMap.get(value).toString());
		
		return SUCCESS;
	}
	
	
	public String updateDoc(){
		int docid = 0;
		int fvindex = 0;
		if(this.request.getParameter("docid")!=null && this.request.getParameter("docid").length()>0){
			docid = Integer.parseInt(this.request.getParameter("docid"));
			System.out.println("docid--->"+this.request.getParameter("docid"));
			
		}
		
		System.out.println("GOT finfo----");		
		Map map =  this.request.getParameterMap();
		Set<Object> reqkeys = (Set<Object>) map.keySet();
		for(Object reqo :reqkeys){
			System.out.println("KEY---"+reqo.toString());
			if(this.request.getParameter(reqo.toString())!=null){
				System.out.println(this.request.getParameter(reqo.toString()));
				fvindex = 1;//仅提交一条数据
			}				
			else{
				System.out.println(this.request.getParameterValues(reqo.toString()).toString());
				fvindex = this.request.getParameterValues(reqo.toString()).length;//提交多条数据，需要清空旧数据，重新给index
			}				
		}
		
		if(fvindex>0){			
			System.out.println("fvindex--->"+fvindex);
			this.dm.deleteFieldValueListByDocId(docid);
			
			if(fvindex==1){//没有group
				for(Object reqo :reqkeys){
					if(reqo.toString().equals("docid"))
						continue;
					System.out.println("To UPDATE---fieldname="+reqo.toString()+", fieldvalue="+this.request.getParameter(reqo.toString())
							+", docid="+docid+", fvindex="+fvindex);					
					this.dm.updateSingleFieldValueByFieldName(reqo.toString(), 
							this.request.getParameter(reqo.toString()), docid, -1);
				}
			}else{
				//update multi fieldvalue				
				for(int i=0;i<fvindex;i++){
					System.out.println("Adding No."+i+" line data...");
					for(Object reqo :reqkeys){
						if(reqo.toString().equals("docid"))
							continue;
						System.out.println("GETting "+reqo.toString()+", value="+
								this.request.getParameterValues(reqo.toString())[i]);
						String fv= this.request.getParameterValues(reqo.toString())[i];
						if( fv!=null &&fv.length()>0 ){
							fv = fv.trim();
						}else
							fv = "";
						this.dm.updateSingleFieldValueByFieldName(reqo.toString(),fv,docid,fvindex);
					}
				}
			}
				
		}
		
		
		
//		this.dm.updateFieldValueByFieldName(_fn, _value, _docid, _fvindex);
		
		return SUCCESS;
	}

	@Override
	public String execute(){
		
		this.jsonText3 = "andy";
			
		return "success";
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		 this.session=session;	
	}


	@Override
	public Object getModel() {
		return this.finfo;
	}
	


}
