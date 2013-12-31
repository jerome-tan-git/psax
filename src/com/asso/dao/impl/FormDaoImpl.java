package com.asso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.asso.dao.FormDao;
import com.asso.model.Fields;
import com.asso.model.Form;

@Component("formDao")
public class FormDaoImpl implements FormDao{
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	
	/*build|update|delete|select a new form----
	 * # form
	 * # fields
	 * */
	@Override
	public void saveForm(Form form) {
		 Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(form);
	     s.flush();
	     int formid = this.getNewFormId(form.getDisplayname());
	     List<Fields> fields = form.getFields(); 
	     if(fields!=null && fields.size()>=1){
	    	 for(Fields field:fields){
	    		 field.setFormid(formid);
	    		 s.save(field);
	    		 s.flush();
	    	 }
	     }else{
	    	 System.out.println("Lack of List<Fields>!!!");
	     }
	     s.getTransaction().commit();	     
	}
	
	@Override
	public void saveField(Fields field){
		Session s = sessionFactory.getCurrentSession(); 
	    s.beginTransaction();
		s.save(field);
		s.flush();
		s.getTransaction().commit();
	}
	
	private int getNewFormId(String _formname){
		int fid = 0;
		Session s = sessionFactory.getCurrentSession();
		Query query = s.createQuery("select id from Form f where f.displayname = :s1")
	    		.setParameter("s1", _formname);	    		
	    List<Object> list = query.list();
	    System.out.println("  getFormId  rz="+list.size());
	    if(list.size() == 1) {
	    	fid = (Integer) list.get(0);
	    }
	    else{
	    	if(list.size() > 1){
	    		fid = (Integer) list.get(list.size()-1);
	    	}else{
		    	System.out.println("DATA ERROR, PLS INV....");
		    }
	    }
		return fid;
	}
	
	@Override
	public List<Fields> loadFieldsByFormId(int _formid){
		List<Fields> rlist = new ArrayList<Fields>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from Fields where formid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_formid); 
        rlist = query.list();
		return rlist;
	}
	@Override
	public Form loadForm(int _formid){
		Form form = new Form();
		List<Form> rlist = new ArrayList<Form>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from Form where formid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_formid); 
        rlist = query.list();
        if(rlist.size()==1){
        	form = rlist.get(0);
        }else{
        	if(rlist.size()>1)
        		form = rlist.get(rlist.size()-1);
        }
        form.setFields(this.loadFieldsByFormId(_formid));
        return form;
	}
	
	@Override
	public void delFieldsByFormId(int _formid){
		List<Fields> dlist = this.loadFieldsByFormId(_formid);
		Session s1 = sessionFactory.getCurrentSession(); 
	    s1.beginTransaction();
		for(Fields f:dlist){
			s1.delete(f);
		    s1.flush();
		}
		s1.getTransaction().commit();
	}
	
	@Override
	public void delField(Fields _field){
		Session s1 = sessionFactory.getCurrentSession(); 
	     s1.beginTransaction();
	     s1.delete(_field);
	     s1.flush();
	     s1.getTransaction().commit();
	}
	
	@Override
	public void updateForm(Form _form) {
//		 if(_form.getFormid()!=null){
//			 
//		 }////???
	     if( _form.getFormid()>0){
	    	 Session s = sessionFactory.getCurrentSession(); 
		     s.beginTransaction();
	    	 s.update(_form);	     
		     s.flush();		     		     		     
		     s.getTransaction().commit();	
		     
		     int formid = _form.getFormid();
		     if(_form.getFields()!=null && _form.getFields().size()>0){
		    	 this.delFieldsByFormId(formid);
		    	 List<Fields> fields = _form.getFields();
		    	 for(Fields field:fields){
		    		 field.setFormid(formid);
		    		 this.saveField(field);
		    	 }
		     }
	     }else
	    	 this.saveForm(_form);
	           
	}
	

}
