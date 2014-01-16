package com.asso.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.asso.dao.BbsDao;
import com.asso.model.Comment;
import com.asso.model.Topic;

@Component("bbsDao")
public class BbsDaoImpl implements BbsDao {
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
	@Override
	public void save(Topic _topic) {
		Session s = sessionFactory.getCurrentSession(); 
	    s.beginTransaction();
	    s.save(_topic);
	    s.flush();
	    s.getTransaction().commit();
	}
	@Override
	public void save(Comment _comment) {
		Session s = sessionFactory.getCurrentSession(); 
	    s.beginTransaction();
	    s.save(_comment);
	    s.flush();
	    s.getTransaction().commit();
	}
	@Override
	public void update(Topic _topic) {		
		Session s = sessionFactory.getCurrentSession(); 
		s.beginTransaction();
		s.update(_topic);	 
		s.flush();
		s.getTransaction().commit();
	}
	@Override
	public void update(Comment _comment) {		
		Session s = sessionFactory.getCurrentSession(); 
		s.beginTransaction();
		s.update(_comment);	 
		s.flush();
		s.getTransaction().commit();
	}
	@Override
	public void delete(Comment _comment) {
		System.out.println("DAO--->_comment="+_comment.toString());
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
//	     String hql = "delete from Article where id=?";      
//	        Query query = s.createQuery(hql); 
//	        query.setString(0, ""+article.getId()); 
//	        query.executeUpdate();	     
	     s.delete(_comment);
	     s.flush();
	     s.getTransaction().commit();
	}
	@Override
	public void delete(Topic _topic) {		
		Session s = sessionFactory.getCurrentSession(); 
		s.beginTransaction();
		s.delete(_topic);
		s.flush();
		s.getTransaction().commit();
	}

	@Override
	public List<Topic> loadTopics(){
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Topic";      
        Query query = s.createQuery(hql); 
        @SuppressWarnings("unchecked")
        List<Topic> rlist = query.list();
	    return rlist;
	}
	@Override
	public List<Comment> loadComments(){
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Comment";      
        Query query = s.createQuery(hql); 
        @SuppressWarnings("unchecked")
        List<Comment> rlist = query.list();
	    return rlist;
	}
	
	@Override
	public List<Comment> loadCommentsByTopicId(int _topicid){
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Comment where topicid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_topicid); 
        @SuppressWarnings("unchecked")
        List<Comment> rlist = query.list();        
	    return rlist;
	}
	
	@Override
	public List<Topic> loadTopicByTopicId(int _topicid){
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Topic where id=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_topicid); 
        @SuppressWarnings("unchecked")
		List<Topic> rlist = query.list();              	    
	    return rlist;
	}
	@Override
	public List<Comment> loadCommentById(int _commentid){
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Comment where id=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_commentid); 
        @SuppressWarnings("unchecked")
		List<Comment> rlist = query.list();              	    
	    return rlist;
	}

}
