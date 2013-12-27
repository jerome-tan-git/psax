package com.asso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import util.ObjectToClass;

import com.asso.dao.ChannelDao;
import com.asso.dao.UserDao;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Exam;
import com.asso.model.ExamRef;
import com.asso.model.Member;
import com.asso.model.MemberInfo;
import com.asso.model.User;

@Component("channelDao")
public class ChannelDaoImpl implements ChannelDao {
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
	@Override
	public void save(Channel channel) {
		 Session s = sessionFactory.openSession(); 
//		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(channel);
	     s.getTransaction().commit();
	     s.close();
	}
	@Override
	public void save(Category category) {
		 Session s = sessionFactory.openSession(); 
//		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(category);
	     s.getTransaction().commit();
	     s.close();
	}
	@Override
	public List<Channel> loadChannels() {
		List<Channel> list = new ArrayList<Channel>();
//		Session s = sessionFactory.openSession();
////		Session s = sessionFactory.getCurrentSession(); 
//	    String hql = "select * from Channel";      
//        Query query = s.createQuery(hql);        
//        
//        List<Object[]> chs = query.list();		 
//        System.out.println("  check channels rz="+chs.size());
//        for(Object[] object : chs){     
//    		String id = object[0].toString();     
//    		String name = object[1].toString();
////            System.out.println(id + " : " + name);  
//    		Channel ch = new Channel();
//            ch.setId(Integer.parseInt(id));
//            ch.setChannel(name);
//            list.add(ch);
//        }
//        s.close();
		Session s = sessionFactory.openSession();		 
	    String hql = "from Channel";      
        Query query = s.createQuery(hql);
        list = query.list();
        
	    return list;
	}
	
	@Override
	public List<Category> loadCategories(int channelid){
		Session s = sessionFactory.openSession();		 
	    String hql = "from Category where channelid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+channelid); 
        List<Category> rlist = query.list();
        s.close();	    
	    return rlist;
	}
	
	@Override
	public List<Category> loadCategories() {
		Session s = sessionFactory.openSession();		 
	    String hql = "from Category ";      
        Query query = s.createQuery(hql);
        List<Category> rlist = query.list();
        s.close();	    
	    return rlist;
//		ArrayList<Category> rlist = new ArrayList<Category>();
//		Session s = sessionFactory.openSession();
////		Session s = sessionFactory.getCurrentSession(); 
//		Query query = s.createQuery("select * from Category");
//
//	    List<Object[]> cats = query.list();		    
//	    s.close();		    
//	    System.out.println("  loadCategoriesByChannelId  rz="+cats.size());
//	    if(cats.size() > 0) {
//	    	  for(Object[] object : cats){
//	    		  Category cat = new Category();
//	    		  if(object.length==4){
//	    			  String id = object[0].toString();     
//	  	      		  String category = object[1].toString();
//	  	      		  String parentid = object[2].toString();
//	  	     		  String chid = object[3].toString();
//	  	      		System.out.println("GET category-----"+id+","+category+","+parentid);
//		              cat.setId(Integer.parseInt(id));
//		              cat.setCategory(category);
//		              cat.setParentid(Integer.parseInt(parentid));
//		              cat.setChannelid(Integer.parseInt(chid));
//	    		  }else
//	    			  System.out.println("DATA ERROR, PLS INV...");	      		
//	                
//	              rlist.add(cat);
//	          }
//	    }            
//	    return rlist;
	}

	@Override
	public Category loadCategory(int categoryid){
		Category cat = new Category();		
		Session s = sessionFactory.openSession();		 
	    String hql = "from Category where id=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+categoryid); 
        List<Category> rlist = query.list();
        if(rlist.size()==1){
        	cat = rlist.get(0);        	
        }        	
        s.close();	    
	    return cat;
	}
	@Override
	public List<Category> loadCategoryPath(int categoryid){		
        List<Category> rlist = new ArrayList<Category>();
        Category cat = this.loadCategory(categoryid);
        rlist.add(cat);
        while(cat.getParentid()>0){        	
        	cat = this.loadCategory(cat.getParentid());
        	rlist.add(cat);
        }        	
	    return rlist;
	}

	

}
