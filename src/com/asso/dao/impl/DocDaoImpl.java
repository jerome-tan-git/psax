package com.asso.dao.impl;

import java.util.ArrayList;
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
//	     int docid = this.getNewDocId(doc.getCreatedate());
//	     List<FieldValue> fvs = doc.getFvlist(); 
//	     if(fvs!=null && fvs.size()>=1){
//	    	 for(FieldValue fv:fvs){
//	    		 fv.setDocid(docid);
//	    		 s.save(fv);
//	    		 s.flush();
//	    	 }
//	     }else{
//	    	 System.out.println("Lack of List<FieldValue>!!!");
//	     }
	     s.getTransaction().commit();	     
	}
	@Override
	public void updateDoc(Doc _doc) {
		int docid = _doc.getDocid();
		if(docid>0){
			 Session s = sessionFactory.getCurrentSession(); 
		     s.beginTransaction();
		     s.update(_doc);
		     s.flush();
		     s.getTransaction().commit();		     		     
//		     List<FieldValue> fvs = _doc.getFvlist(); 
//		     if(fvs!=null && fvs.size()>=1){
//		    	 this.delFieldValueListByDocId(docid);
//		    	 List<FieldValue> fvl = _doc.getFvlist();
//		    	 for(FieldValue fv:fvl){
//		    		 fv.setDocid(docid);		    		 
//		    		 this.saveFieldValue(fv);
//		    	 }
//		     }else{
//		    	 System.out.println("Lack of List<FieldValue>!!!");
//		     } 
			
		}else{
			this.saveDoc(_doc);
		}
		 
	}
	
	@Override
	public void updateFieldValue(FieldValue _fv) {
		Session s = sessionFactory.getCurrentSession(); 
	    s.beginTransaction();
		s.update(_fv);
		s.flush();
		s.getTransaction().commit();
	}
	
	@Override
	public void saveFieldValue(FieldValue fv){
		Session s = sessionFactory.getCurrentSession(); 
	    s.beginTransaction();
		s.save(fv);
		s.flush();
		s.getTransaction().commit();
	}
	
	@Override
	public int getNewDocId(String _date){
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

	@Override
	public List<FieldValue> loadFieldValue(int _docid, int _fieldid, int _fvindex){
		List<FieldValue> rlist = new ArrayList<FieldValue>();
		Session s = sessionFactory.getCurrentSession();
		
		Query query = s.createQuery("from FieldValue fv where fv.docid = :f1 and fv.fieldid = :f2"
				+ " and fv.fieldvalueindex = :f3")
	    		.setParameter("f1", _docid)
	    		.setParameter("f2", _fieldid)
	    		.setParameter("f3", _fvindex);	   		
	   
        rlist = query.list();
        System.out.println("-------------------DocDaoImpl. loadFieldValue rz.size="+rlist.size());
		return rlist;
	}
	
	@Override
	public List<FieldValue> loadFieldValue(int _docid, int _fieldid){
		List<FieldValue> rlist = new ArrayList<FieldValue>();
		Session s = sessionFactory.getCurrentSession();
		
		Query query = s.createQuery("from FieldValue fv where fv.docid = :f1 and fv.fieldid = :f2")
	    		.setParameter("f1", _docid)
	    		.setParameter("f2", _fieldid);	   		
	   
        rlist = query.list();
        System.out.println("-------------------DocDaoImpl. loadFieldValue rz.size="+rlist.size());
		return rlist;
	}
	
	
	@Override
	public List<FieldValue> loadFieldValueListByDocId(int _docid){
		List<FieldValue> rlist = new ArrayList<FieldValue>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from FieldValue where docid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_docid); 
        rlist = query.list();
        System.out.println("-------------------DocDaoImpl. loadFieldValueListByDocId rz.size="+rlist.size());
		return rlist;
	}
	
	@Override
	public List<Doc> loadDocs(int _formid){		
		List<Doc> rlist = new ArrayList<Doc>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from Doc where formid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_formid); 
        rlist = query.list();    
        System.out.println("-------------------DocDaoImpl. loadDocs rz.size="+rlist.size());
        return rlist;
	}
	
	@Override
	public Doc loadDoc(int _docid){	
		Doc doc = new Doc();
		List<Doc> rlist = new ArrayList<Doc>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from Doc where docid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_docid); 
        rlist = query.list();
        if(rlist!=null && rlist.size()==1)
        	doc = rlist.get(0);
        else
        	System.out.println("NO doc selected!!!");
//        System.out.println("-------------------DocDaoImpl. loadDocs rz.size="+rlist.size());
        System.out.println("DocDao-------"+doc.toString());
        return doc;
	}
	
	@Override
	public List<Doc> loadDocsByUser(int _userid){
		List<Doc> rlist = new ArrayList<Doc>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from Doc where userid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_userid); 
        rlist = query.list();
        return rlist;
	}
	
	@Override
	public List<Doc> loadDocs(int _userid, int _formid){
		List<Doc> rlist = new ArrayList<Doc>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from Doc where userid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_userid); 
        rlist = query.list();
        return rlist;
	}
	
	@Override
	public void delFieldValueListByDocId(int _docid){
		List<FieldValue> dlist = this.loadFieldValueListByDocId(_docid);
		Session s1 = sessionFactory.getCurrentSession(); 
	    s1.beginTransaction();
		for(FieldValue f:dlist){
			s1.delete(f);
		    s1.flush();
		}
		s1.getTransaction().commit();
	}
	
	@Override
	public void delFieldValue(FieldValue _fieldValue){
		Session s1 = sessionFactory.getCurrentSession(); 
	     s1.beginTransaction();
	     s1.delete(_fieldValue);
	     s1.flush();
	     s1.getTransaction().commit();
	}
	
	@Override
	public int maxFieldValueIndex(int _docid){
		Session s = sessionFactory.getCurrentSession(); 
		String hql = "select max(fv.fieldvalueindex) from FieldValue fv"
				+ " where fv.docid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_docid); 
		Integer c = 0;
		if(query.list().size()>0){
			Object o = query.list().get(0);
			c = (Integer)o;
		}			 
		System.out.println("-----------------docid="+_docid+", maxindex="+c);
		return c;
	}



}
