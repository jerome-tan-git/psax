package com.asso.action;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CONSTANT;
import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.asso.manager.DocManager;
import com.asso.manager.FormManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;
import com.asso.model.Fields;
import com.asso.model.Form;
import com.asso.model.User;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloServlet  extends HttpServlet{
	
	
	private FormManager fm;	
	private DocManager dm;	
	private String jsonText3;
	private Form f;
	private List<Doc> doclist;
	private Doc doc;

	private HashMap<String,List<Fields>> group;//KEY-groupname, VALUE-fieldname 
	private HashSet<Integer> indexes;
	
	public HelloServlet(){		
		fm = (FormManager) SpringFactory.getObject("formManager");
		dm = (DocManager) SpringFactory.getObject("docManager");
	}	
		
	
	public FormManager getFm() {
		return fm;
	}
	public void setFm(FormManager fm) {
		this.fm = fm;
	}
	public DocManager getDm() {
		return dm;
	}
	public void setDm(DocManager dm) {
		this.dm = dm;
	}
	
	
	
	public String getJsonText3() {
		return jsonText3;
	}
	public void setJsonText3(String jsonText3) {
		this.jsonText3 = jsonText3;
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
	public Doc getDoc() {
		return doc;
	}
	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	public HashMap<String, List<Fields>> getGroup() {
		return group;
	}
	public void setGroup(HashMap<String, List<Fields>> group) {
		this.group = group;
	}
	public HashSet<Integer> getIndexes() {
		return indexes;
	}
	public void setIndexes(HashSet<Integer> indexes) {
		this.indexes = indexes;
	}
	
	private void setDocForm(int _docid){
		try {
			this.doc = dm.loadDocWithFieldValueList(_docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ got DOC ~~~~~~~~~~~~~~~~~~~~~~toString--"+this.doc.toString());

		int docid = 0;
		if(this.doc!=null){
			docid = this.doc.getFormid();
			try {
				this.f = fm.loadFormWithFieldsById(docid);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~got form~~~~~~~~~~~~~~~~~~~~~~~~toString---"+f.toString());
		}
		
		List<FieldValue> fvs = doc.getFvlist();		
		this.indexes = new HashSet<Integer>();
		if(fvs!=null){
			for(FieldValue fv : fvs)
				indexes.add(fv.getFieldvalueindex());
		}else{
			System.out.println("No List<FieldValue> in DOC, Pls INV...");
		}
				
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~GOT indexes~~~~~~~~~~~~~~~~"+indexes.toString());
		System.out.println("----Total fieldvalue size(thisdoc)----"+doc.getFvlist().size());
		
	}
	
	private int buildGroup(){
		this.group = new HashMap<String,List<Fields>>();//KEY-groupname, VALUE-fieldname 
		for(Fields field:f.getFields()){
			if(field.getGroupname()!=null && field.getGroupname().length()>0){
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
		////////////////check group data////////////
		Set<String> gnames = group.keySet(); 
		for(String gn:gnames)
			System.out.println("^^^^^  "+gn+":"+group.get(gn).toString());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ got GROUPmap ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (gnames.size()>0)
			return gnames.size();
		else
			return 0;
	}
	
	
	private void assembleNewDocJsonText(int _formid, int _userid){
		
		String time = CONSTANT.getNowTime();
		this.doc = new Doc();
		doc.setFormid(_formid);
		doc.setCreatedate(time);
		doc.setUserid(_userid);
		try {
			dm.addDoc(this.doc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.doc.setDocid(dm.getDocIdByCreateDate(time));
		
		try {
			this.f = fm.loadFormWithFieldsById(_formid);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    jmap.put("type","edit");
	    
	    for(Fields fd:this.f.getFields()){		    	
//	    	System.out.println("key---"+fd.getFieldname()+",value---");
	    	jmap.put(fd.getFieldname(),"");
		}
	    
	    this.jsonText3=JSON.toJSONString(jmap, true); 
		System.out.println(this.jsonText3);
		System.out.println("------------------------assembleNewDocJsonText over!--------------------------------------");
	}
	
	private void assembleJsonText(int _docid, String _mode){
		
		this.doc = new Doc();
		this.doc.setDocid(_docid);
		this.setDocForm(_docid);//docid=4|6 
		int groupnum = this.buildGroup();
		System.out.println("groupnum="+groupnum);
		
		List<FieldValue> fvs = this.doc.getFvlist();
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    if(_mode.equals("edit"))
	    	jmap.put("type","edit");
	    else{
	    	jmap.put("type","display");
	    	if(!_mode.equals("display"));
	    		System.out.println("No mode chosen!");
	    }
		if(groupnum==0){
			if(this.indexes.size()<=1){
				for(FieldValue fv:fvs){
					int fvfid = fv.getFieldid();
					String value = fv.getValue();
					String key = "";
					for(Fields fd:this.f.getFields()){
						if(fd.getFieldid()==fvfid)
							key = fd.getFieldname();
					}
					System.out.println("key---"+key+",value---"+value);
//					this.formDatalist.add(key);
//					this.formValuelist.add(value);
					jmap.put(key,value);
				}			
			}else{
				List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();	    		
	    		for(Integer index:indexes){
	    			System.out.println("----index="+index);
	    			HashMap<String,String> unit = new HashMap<String,String>();
	    			
	    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
					for(FieldValue fv :doc.getFvlist()){
						if(fv.getFieldvalueindex()==index)
							uniFvs.add(fv);
					}
					for(FieldValue fv:uniFvs){//show teamFieldValue with fieldname						
						int fvfid = fv.getFieldid();
						String value = fv.getValue();
						String key = "";
						for(Fields fd:this.f.getFields()){
							if(fd.getFieldid()==fvfid)
								key = fd.getFieldname();
						}
						System.out.println("key---"+key+",value---"+value);
						unit.put(key,value);	//SET the unit form data/value
					}
	    			groupmap.add(unit);
	    		}	    		
	    		jmap.put("data_1", groupmap);//set the only groupname="data_1"
			}
		}else{
			 Set<String> groupnames = group.keySet();
		    	for(String gn : groupnames){
		    		System.out.println("----into group:"+gn);
		    		List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();
		    		
		    		for(Integer index:indexes){
		    			System.out.println("----index="+index);
		    			HashMap<String,String> unit = new HashMap<String,String>();
		    			
		    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
						for(FieldValue fv :doc.getFvlist()){
							if(fv.getFieldvalueindex()==index)
								uniFvs.add(fv);
						}
						for(FieldValue v:uniFvs){//show teamFieldValue with fieldname						
							int fieldid = v.getFieldid();
							for(Fields field:group.get(gn)){
								if(field.getFieldid()==fieldid){
									System.out.println("field.getFieldname()---"+field.getFieldname());
									unit.put(field.getFieldname(),v.getValue());	//SET the unit form data/value									
								}
							}
						}
		    			groupmap.add(unit);
		    		}	    		
		    		jmap.put(gn, groupmap);
		    	}
		}
		this.jsonText3=JSON.toJSONString(jmap, true); 
		System.out.println(this.jsonText3);
		System.out.println("--------------------------------------------------------------");
	}

	private void assembleJsonTextForUpdateUserForm(int _userid, String _mode){
		
		this.doc = new Doc();
		try {
			this.doc = dm.loadLastDocWithFieldValueListByUser(_userid);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		if(this.doc!=null){			
			try {
				this.f = fm.loadFormWithFieldsById(this.doc.getFormid());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~assembleJsonTextForUpdateUserForm~got form~~~~~~~~~~~~~~~~~~~~~~~~toString---"+f.toString());
		}		
		List<FieldValue> fvs = doc.getFvlist();		
		this.indexes = new HashSet<Integer>();
		if(fvs!=null){
			System.out.println("----Total fieldvalue size(thisdoc)----"+doc.getFvlist().size());
			for(FieldValue fv : fvs)
				indexes.add(fv.getFieldvalueindex());
		}else{
			System.out.println("No List<FieldValue> in DOC, Pls INV...");
		}				
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~assembleJsonTextForUpdateUserForm~GOT indexes~~~~~~~~~~~~~~~~"+indexes.toString());
		
		
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    if(_mode.equals("edit"))
	    	jmap.put("type","edit");
	    else{
	    	jmap.put("type","display");
	    	if(!_mode.equals("display"));
	    		System.out.println("No mode chosen!");
	    }
		
			if(this.indexes.size()<=1){
				for(FieldValue fv:fvs){
					int fvfid = fv.getFieldid();
					String value = fv.getValue();
					String key = "";
					for(Fields fd:this.f.getFields()){
						if(fd.getFieldid()==fvfid)
							key = fd.getFieldname();
					}
					System.out.println("key---"+key+",value---"+value);
					jmap.put(key,value);
				}			
			}else{
				List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();	    		
	    		for(Integer index:indexes){
	    			System.out.println("----index="+index);
	    			HashMap<String,String> unit = new HashMap<String,String>();
	    			
	    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
					for(FieldValue fv :doc.getFvlist()){
						if(fv.getFieldvalueindex()==index)
							uniFvs.add(fv);
					}
					for(FieldValue fv:uniFvs){//show teamFieldValue with fieldname						
						int fvfid = fv.getFieldid();
						String value = fv.getValue();
						String key = "";
						for(Fields fd:this.f.getFields()){
							if(fd.getFieldid()==fvfid)
								key = fd.getFieldname();
						}
						System.out.println("key---"+key+",value---"+value);
						unit.put(key,value);	//SET the unit form data/value
					}
	    			groupmap.add(unit);
	    		}	    		
	    		jmap.put("data_1", groupmap);//set the only groupname="data_1"
			}
		
		this.jsonText3=JSON.toJSONString(jmap, true); 
		System.out.println(this.jsonText3);
		System.out.println("--------------------------------------------------------------");
	}

	private Configuration cfg; 
    	
	    public void init(){	 
	    
	        cfg = new Configuration();
	        
//	        System.out.println(this.getServletContext().getRealPath("WEB-INF/tmpl"));	    
//	        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/tmpl");
	        WebappTemplateLoader ftl1 = new WebappTemplateLoader(getServletContext(),"WEB-INF");
//	        WebappTemplateLoader ftl2 = new WebappTemplateLoader(getServletContext(),"WEB-INF/commons");            
	        TemplateLoader[] loaders = new TemplateLoader[] { ftl1 };
	        MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
	        cfg.setTemplateLoader(mtl);
//	        cfg.setTemplateLoader(new ClassTemplateLoader(getClass(),"WEB-INF/tmpl"));
	        cfg.setEncoding(Locale.CHINA, "UTF-8");
	    }
	    
	    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{      
	    		        
	    	 ///////////////////////////////////////////////////////////////////////////////////////////////////
	       
	    	User user = new User();
	    	if(request.getSession().getAttribute("user_")!=null){
	    		System.out.println("session-----------user="+request.getSession().getAttribute("user_").toString());
	    		user = (User) request.getSession().getAttribute("user_");
	    	}
	
	    	if(request.getParameter("formid")!=null && Integer.parseInt(request.getParameter("formid"))>0){
	    		//new doc
	    		int formid = Integer.parseInt(request.getParameter("formid"));
	    		this.assembleNewDocJsonText(formid,user.getId());	    		
	    	}else{
	    		String strdid ="";
	    		String smode = "";
	    		if(request.getParameter("docid")!=null && request.getParameter("docid").length()>0){
	    			System.out.println("request-----------docid="+request.getParameter("docid"));
	    			strdid = request.getParameter("docid");
	    		}
	    		if(request.getParameter("mode")!=null && request.getParameter("mode").length()>0){
	    			System.out.println("request-----------mode="+request.getParameter("mode"));	    		
	    			smode = request.getParameter("mode");
	    		}
	    		//display .or. edit
	    		this.assembleJsonTextForUpdateUserForm(user.getId(),smode);
	 	    	
//	    		if(strdid!=null && Integer.parseInt(strdid)>0){
//		    		//display .or. edit
//	    			this.assembleJsonText(Integer.parseInt(strdid),smode,user.getId());//for other form used, not member info form(cbi)
//	    			
//		    	}
	    	}    	
		   ///////////////////////////////////////////////////////////////////////////////////////////////////	
	    	
	        Map root = new HashMap();	        
	        root.put("jsonText3", this.jsonText3); 
	        root.put("docid", this.doc.getDocid()); 
	        // 处理模版  
	        root.put("Request", request);  
	        root.put("Session", request.getSession());  
	        //root.put("JspTaglibs", new TaglibFactory(request.getSession()  
	                //.getServletContext()));  
	     
	        response.setCharacterEncoding("utf-8");
	        Writer out = response.getWriter();
//	        Enumeration<String> e = request.getParameterNames();
//	        while(e.hasMoreElements())
//	        {
//	        	String parameterName = (e.nextElement());
//	        	String[] values = request.getParameterValues(parameterName);
//	        	if(values!=null)
//	        	{
//		        	for(int i=0;i<values.length;i++)
//		        	{
//		        		int fieldIndex = i;
//		        		String fieldName = parameterName;
//		        		String fieldValue = values[i];
//		        		
//		        		//new a field and insert into field Value table
//		        		
//		        		
//		        	}
//	        	}
//	        }

	        Template t = cfg.getTemplate("tmpl/"+this.f.getFrontendtpl());
//	        Template t = cfg.getTemplate("tmpl/chemicalManageRecord.ftl"); 
	        t.setEncoding("utf-8");
	        
	   
	        //System.out.println(t.toString());
	        //response.setContentType("text/html; charset=" + t.getEncoding());
	        
//	        ///////////////////////////////////////////////////////////////////////////////////////////////////
//	        System.out.println("request-----------docid="+request.getParameter("docid"));
//	    	System.out.println("request-----------mode="+request.getParameter("mode"));
//	
//	    	if(request.getParameter("formid")!=null && Integer.parseInt(request.getParameter("formid"))>0){
//	    		//new doc
//	    		int formid = Integer.parseInt(request.getParameter("formid"));
//	    		this.assembleNewDocJsonText(formid);	    		
//	    	}else{
//	    		String strdid = request.getParameter("docid");
//	    		String smode = request.getParameter("mode");
//	    		if(strdid!=null && Integer.parseInt(strdid)>0){
//		    		//display .or. edit
//	    			this.assembleJsonText(Integer.parseInt(strdid),smode);
//		    	}
//	    	}    	
//		   ///////////////////////////////////////////////////////////////////////////////////////////////////	        
	        
	        
	        try{
	            t.process(root, out);
	        } 
	        catch (TemplateException e1){
	            throw new ServletException("", e1);
	        }
	        out.flush();
	    }

}
