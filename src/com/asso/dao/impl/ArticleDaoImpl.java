package com.asso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import util.HibernateUtil;

import com.asso.dao.ArticleDao;
import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;

@Component("articleDao")
public class ArticleDaoImpl implements ArticleDao {
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
	@Override
	public void save(Article article) {
//		 Session s = sessionFactory.openSession(); 
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(article);
	     s.getTransaction().commit();
//	     s.close();
	}
	@Override
	public void update(Article article) {
//		Article delart = new Article();
//		delart.setId(article.getId());
//		this.delete(delart);
//		this.save(article);
//		Session s = sessionFactory.openSession(); 
			Session s = sessionFactory.getCurrentSession(); 
		s.beginTransaction();
		s.update(article);	 
		s.flush();
		s.getTransaction().commit();
//		s.close();
	}
	@Override
	public void delete(Article article) {
//		 Session s = sessionFactory.openSession(); 
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.delete(article);	     
	     s.getTransaction().commit();
//	     s.close();
	}
	
	@Override
	public List<Article> loadArticles(){
//		Session s = sessionFactory.openSession();
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Article";      
        Query query = s.createQuery(hql); 
        List<Article> rlist = query.list();
//        s.close();	    
	    return rlist;
	}
	
	@Override
	public List<Article> loadArticles(int categoryid){
//		Session s = sessionFactory.openSession();
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Article where categoryid=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+categoryid); 
        List<Article> rlist = query.list();
//        s.close();	
        
	    return rlist;
	}
	
	@Override
	public List<Article> loadArticle(int articleid){
//		Session s = sessionFactory.openSession();
		Session s = sessionFactory.getCurrentSession();
	    String hql = "from Article where id=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+articleid); 
        List<Article> rlist = query.list();
//        s.close();	    
	    return rlist;
	}



//	@Override
//	public List<Category> loadCategoryPath(int categoryid){		
//        List<Category> rlist = new ArrayList<Category>();
//        Category cat = this.loadCategory(categoryid);
//        rlist.add(cat);
//        while(cat.getParentid()>0){        	
//        	cat = this.loadCategory(cat.getParentid());
//        	rlist.add(cat);
//        }        	
//	    return rlist;
//	}

	

}
