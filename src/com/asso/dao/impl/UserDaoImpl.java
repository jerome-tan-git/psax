package com.asso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import util.ObjectToClass;

import com.asso.dao.UserDao;
import com.asso.model.Member;
import com.asso.model.MemberInfo;
import com.asso.model.User;

@Component("userDao")//≥ı ºªØuserDao
public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
	@Override
	public void save(User user) {
		 Session s = sessionFactory.openSession(); 
//		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(user);
	     s.getTransaction().commit();
	}
	
	@Override
	public int checkUserExistsWithNamePassword(User user) {
//		  Session s = sessionFactory.getCurrentSession(); 
		Session s = sessionFactory.openSession(); 
		  s.beginTransaction();
		  
		  System.out.println("Into checkUserExistsWithNamePassword...");
	  
		    Query query = s.createQuery("select username,password from User u where u.username = ?")
		    		.setParameter(0, user.getUsername());
		    List<Object[]> list = query.list();  
		    s.getTransaction().commit();
		    System.out.println("  checkUserExistsWithNamePassword  rz="+list.size());
		    if(list.size() > 0) {
		    	for(Object[] object : list){     
		    		String passwd = (String)object[1];     
		    		String name = (String)object[0];
		            System.out.println(name + " : " + passwd);
		            if(passwd!=null && passwd.length()>0 
		            		&& passwd.trim().equalsIgnoreCase(user.getPassword().trim()))
		            	return 1;
		            else
		            	return 3;
		        }
		    }
	        return 2;
		
	}
	@Override
	public int getUserIdWithName(User user) {
		 
		 int id = 0;
	     Session s = sessionFactory.openSession(); 
//			Session s = sessionFactory.getCurrentSession(); 
		 s.beginTransaction();
		
	     Query query = s.createQuery("select id from User u where u.username = ?")
			    		.setParameter(0, user.getUsername());
        List<Object> list = query.list();  
	     s.getTransaction().commit();
			    
	     if(list.size() > 0) {
			  id = (Integer) list.get(0);
			  System.out.println(user.getUsername() + " : " + id);
	     }else
	    	 System.out.println("No user selected...");
		 
		 return id;
	}
	@Override
	public int getMemberIdWithUserId(int userId) {
		 
		 int id = 0;
	     Session s = sessionFactory.openSession(); 
//			Session s = sessionFactory.getCurrentSession(); 
		 s.beginTransaction();
		
	     Query query = s.createQuery("select id from Member m where m.userid = ?")
			    		.setParameter(0, userId);
        List<Object> list = query.list();  
	     s.getTransaction().commit();
			    
	     if(list.size() > 0) {
			  id = (Integer) list.get(0);
			  System.out.println(id + " : " + userId);
	     }else
	    	 System.out.println("No member selected...");
		 
		 return id;
	}
	
	@Override
	public void saveMember(User user) {
		
		System.out.println("User input info ------"+user.getUsername());
		
		this.save(user);
		int id= this.getUserIdWithName(user);
		Member member  = new Member();
		member.setMlevel(2);
		member.setUserid(id);
		
		
		Session s = sessionFactory.openSession(); 
//			Session s = sessionFactory.getCurrentSession(); 
		s.beginTransaction();
		s.save(member);
		s.getTransaction().commit();
		int memid = this.getMemberIdWithUserId(id);
	    this.saveMemberInfo(memid,user);
	}
	@Override
	public void saveMemberInfo(int memid,User user) {
		
		MemberInfo memberinfo = new MemberInfo();
		memberinfo.setId(memid);
		memberinfo.setC_name(user.getUsername());
		
		Session s = sessionFactory.openSession(); 
//			Session s = sessionFactory.getCurrentSession(); 
		s.beginTransaction();
		s.save(memberinfo);
		s.getTransaction().commit();	    
	}
	@Override
	public void updateMemberInfoWithId(MemberInfo minfo) {
		System.out.println("Member input infomation c_Addr------"+minfo.getC_addr());
		System.out.println("Member input infomation c_email------"+minfo.getC_email());
		System.out.println("Member input infomation c_logo------"+minfo.getC_logo());
		System.out.println("Member input infomation c_name------"+minfo.getC_name());
		System.out.println("Member input infomation c_tel------"+minfo.getC_tel());
		System.out.println("Member input infomation c_contactperson------"+minfo.getContactperson());
		System.out.println("Member input infomation p_email------"+minfo.getP_email());
		System.out.println("Member input infomation p_mp------"+minfo.getP_mp());
		System.out.println("Member input infomation c_tel------"+minfo.getP_tel());
		
		 Session s = sessionFactory.openSession(); 
//		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.update(minfo);
	     s.getTransaction().commit();
		
	}
	@Override
	public List<MemberInfo> loadMemberInfoWithUserId(User user) {
		
		int mid = this.getMemberIdWithUserId(user.getId());
		List<MemberInfo> minfos = new ArrayList<MemberInfo>();
		
		 Session s = sessionFactory.openSession(); 
//		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     
	     String hql = "from MemberInfo where id=?";      
	     Query query = s.createQuery(hql); 
	     query.setString(0, ""+mid); 
			
	     minfos = query.list();    
	     s.getTransaction().commit();
		 
	     System.out.println("Get list size="+minfos.size());
	     System.out.println("member selected info----\n"+minfos.get(0).toString());
		
		return minfos;
	}
	
	

}
