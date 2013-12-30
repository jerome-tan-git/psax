package com.asso.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.asso.dao.DocDao;
import com.asso.model.Doc;
import com.asso.model.FieldValue;
import com.asso.model.Fields;

@Component("docDao")
public class DocDaoImpl implements DocDao{
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	
	/*build|update|delete|select a new doc----
	 * # build|update|delete|select doc
	 * # build|update|delete|select fieldvalue
	 * # select form
	 * # select fields
	 * */
	@Override
	public void saveDoc(Doc doc) {
		 Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(doc);
	     s.flush();
	     int docid = this.getNewDocId(doc.getCreatedate());
	     List<FieldValue> fvs = doc.getFvlist(); 
	     if(fvs!=null && fvs.size()>=1){
	    	 for(FieldValue fv:fvs){
	    		 fv.setDocid(docid);
	    		 s.save(fv);
	    		 s.flush();
	    	 }
	     }else{
	    	 System.out.println("Lack of List<FieldValue>!!!");
	     }
	     s.getTransaction().commit();	     
	}
	@Override
	public void updateDoc(Doc _doc) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void saveField(Fields field){
//		Session s = sessionFactory.getCurrentSession(); 
//	    s.beginTransaction();
//		s.save(field);
//		s.flush();
//		s.getTransaction().commit();
//	}
//	
	private int getNewDocId(String _date){
		int did = 0;
		Session s = sessionFactory.getCurrentSession();
		Query query = s.createQuery("select docid from Doc d where d.createdate = :s1")
	    		.setParameter("s1", _date);	    		
	    List<Object> list = query.list();
	    System.out.println("  getFormId  rz="+list.size());
	    if(list.size() == 1) {
	    	did = (Integer) list.get(0);
	    }
	    else{
	    	if(list.size() > 1){
	    		did = (Integer) list.get(list.size()-1);
	    	}else{
		    	System.out.println("DATA ERROR, PLS INV....");
		    }
	    }
		return did;
	}
//	
//	@Override
//	public List<Fields> loadFieldsByFormId(int _formid){
//		List<Fields> rlist = new ArrayList<Fields>();
//		Session s = sessionFactory.getCurrentSession();
//		String hql = "from Fields where formid=?";      
//        Query query = s.createQuery(hql); 
//        query.setString(0, ""+_formid); 
//        rlist = query.list();
//		return rlist;
//	}
//	@Override
//	public Form loadForm(int _formid){
//		Form form = new Form();
//		List<Form> rlist = new ArrayList<Form>();
//		Session s = sessionFactory.getCurrentSession();
//		String hql = "from form where formid=?";      
//        Query query = s.createQuery(hql); 
//        query.setString(0, ""+_formid); 
//        rlist = query.list();
//        if(rlist.size()==1){
//        	form = rlist.get(0);
//        }else{
//        	if(rlist.size()>1)
//        		form = rlist.get(rlist.size()-1);
//        }
//        form.setFields(this.loadFieldsByFormId(_formid));
//        return form;
//	}
//	
//	@Override
//	public void delFieldsByFormId(int _formid){
//		List<Fields> dlist = this.loadFieldsByFormId(_formid);
//		Session s1 = sessionFactory.getCurrentSession(); 
//	    s1.beginTransaction();
//		for(Fields f:dlist){
//			s1.delete(f);
//		    s1.flush();
//		}
//		s1.getTransaction().commit();
//	}
//	
//	@Override
//	public void delField(Fields _field){
//		Session s1 = sessionFactory.getCurrentSession(); 
//	     s1.beginTransaction();
//	     s1.delete(_field);
//	     s1.flush();
//	     s1.getTransaction().commit();
//	}
//	
//	@Override
//	public void updateForm(Form _form) {
////		 if(_form.getFormid()!=null){
////			 
////		 }////???
//	     if( _form.getFormid()>0){
//	    	 Session s = sessionFactory.getCurrentSession(); 
//		     s.beginTransaction();
//	    	 s.update(_form);	     
//		     s.flush();		     		     		     
//		     s.getTransaction().commit();	
//		     
//		     int formid = _form.getFormid();
//		     if(_form.getFields()!=null && _form.getFields().size()>0){
//		    	 this.delFieldsByFormId(formid);
//		    	 List<Fields> fields = _form.getFields();
//		    	 for(Fields field:fields){
//		    		 field.setFormid(formid);
//		    		 this.saveField(field);
//		    	 }
//		     }
//	     }else
//	    	 this.saveForm(_form);
//	           
//	}
//	
	


}
