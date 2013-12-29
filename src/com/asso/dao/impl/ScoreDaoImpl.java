package com.asso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import util.ObjectToClass;

import com.asso.dao.ScoreDao;
import com.asso.dao.UserDao;
import com.asso.model.Member;
import com.asso.model.MemberInfo;
import com.asso.model.Score;
import com.asso.model.ScoreExamItem;
import com.asso.model.ScoreExamRef;
import com.asso.model.User;

@Component("scoreDao")//≥ı ºªØuserDao
public class ScoreDaoImpl implements ScoreDao {
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


	@Override
	public void save(Score score) {
		System.out.println("score---"+score.toString());
//		 Session s = sessionFactory.openSession();
		 Session s = sessionFactory.getCurrentSession();		 
	     s.beginTransaction();
	     s.save(score);
	     s.flush();
	     s.getTransaction().commit();
//	     s.close();
	}
	@Override
	public void save(ScoreExamItem scoreexamitem) {
		Session s = sessionFactory.getCurrentSession();
//		Session s = sessionFactory.openSession(); 		 
	     s.beginTransaction();
	     s.save(scoreexamitem);
	     s.flush();
	     s.getTransaction().commit();
//	     s.close();
	}
	@Override
	public void save(ScoreExamRef scoreexamref) {
//		Session s = sessionFactory.openSession();
		Session s = sessionFactory.getCurrentSession();
	    s.beginTransaction();
	    s.save(scoreexamref);
	    s.flush();
	    s.getTransaction().commit();
//	    s.close();
	}
	@Override
	public void update(Score score) {
		System.out.println("score---"+score.toString());
//		 Session s = sessionFactory.openSession();
		Session s = sessionFactory.getCurrentSession();
		 System.out.println("s----"+s);
	     s.beginTransaction();
	     s.saveOrUpdate(score);
	     s.flush();
	     s.getTransaction().commit();
//	     s.close();
	}
	@Override
	public int getScoreId(Score score) {
		int sid = 0;
		System.out.println("score---"+score.toString());
//		Session s = sessionFactory.openSession();
		Session s = sessionFactory.getCurrentSession();
		Query query = s.createQuery("select id from Score sc where sc.score = :s1 and sc.examid = :s2"
				+ " and sc.userid = :s3 and sc.examdate = :s4")
	    		.setParameter("s1", score.getScore())
	    		.setParameter("s2", score.getExamid())
	    		.setParameter("s3", score.getUserid())
	    		.setParameter("s4", score.getExamdate());
	    		
	    List<Object> list = query.list();		    
//	    s.close();		    
	    System.out.println("  getScoreId  rz="+list.size());
	    if(list.size() == 1) {
	    	sid = (Integer) list.get(0);
	    }else{
	    	System.out.println("DATA ERROR, PLS INV....");
	    }
	    return sid;
	}

}
