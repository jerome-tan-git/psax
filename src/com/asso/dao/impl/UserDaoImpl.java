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
import com.asso.model.Form;
import com.asso.model.Member;
import com.asso.model.MemberInfo;
import com.asso.model.Uploadfiles;
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
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(user);
	     s.flush();
	     s.getTransaction().commit();
	}
	@Override
	public void update(User user) {
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.update(user);
	     s.flush();
	     s.getTransaction().commit();
	}
	@Override
	public void delete(User user) {
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.delete(user);
	     s.flush();
	     s.getTransaction().commit();
	}
	
	@Override
	public User loadUserWithNamePassword(User _user) {
		 	
		User u = new User();
		Session s = sessionFactory.getCurrentSession(); 
		Query query = s.createQuery("select username,password,nickname,id from User u where u.username = :un")
		   		.setParameter("un", _user.getUsername());		    
		List<Object[]> list = query.list();		    
			    
		System.out.println("  loadUserWithNamePassword  rz="+list.size());
			    if(list.size() > 0) {
			    	for(Object[] object : list){     
			    		String passwd = (String)object[1];     
			    		String name = (String)object[0];
			    		String nickname= (String)object[2];
//			    		String id = (String)object[3];
			            System.out.println(name + " : " + passwd);
			            System.out.println(nickname + " : " );
			            u.setNickname(nickname);
			            u.setPassword(passwd);
			            u.setUsername(name);
			        }
			    }
		return u;		
	}
	
	@Override
	public User loadUser(int _userid){
		User u = new User();
		List<User> rlist = new ArrayList<User>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from User where id=?";      
        Query query = s.createQuery(hql); 
        query.setString(0, ""+_userid); 
        rlist = query.list();
        if(rlist.size()==1){
        	u = rlist.get(0);
        }else{
        	if(rlist.size()>1)
        		u = rlist.get(rlist.size()-1);
        }        
        return u;
	}
	@Override
	public List<User> loadAllUsers(){	
		
		List<User> rlist = new ArrayList<User>();
		Session s = sessionFactory.getCurrentSession();
		String hql = "from User";      
        Query query = s.createQuery(hql);
        rlist = query.list();
        return rlist;
	}
	
	
	@Override
	public int checkUserExistsWithNamePassword(User user) {
		  Session s = sessionFactory.getCurrentSession(); 
//		Session s = sessionFactory.openSession();
			
			Query query = s.createQuery("select username,password from User u where u.username = :un")
		    		.setParameter("un", user.getUsername());		    
		    List<Object[]> list = query.list();		    
//		    s.close();		    
		    System.out.println("  checkUserExistsWithNamePassword  rz="+list.size());
		    if(list.size() > 0) {
		    	for(Object[] object : list){     
		    		String passwd = (String)object[1];     
		    		String name = (String)object[0];
		            System.out.println(name + " : " + passwd);
		            if(passwd!=null && passwd.length()>0 ){
		            	if(passwd.trim().equalsIgnoreCase(user.getPassword().trim()))		            		
		            		return 1;		            	
		            }else
	            		return 3;
		            	
		        }
		    }
	        return 2;
		
	}
	@Override
	public int getUserIdWithName(User user) {
		 
		 int id = 0;
//	     Session s = sessionFactory.openSession(); 
		 Session s = sessionFactory.getCurrentSession(); 
		 Query query = s.createQuery("select id from User u where u.username = ?")
			    		.setParameter(0, user.getUsername());
        List<Object> list = query.list();
//	     s.close();    
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
//	     Session s = sessionFactory.openSession(); 
		 Session s = sessionFactory.getCurrentSession(); 
	     Query query = s.createQuery("select id from Member m where m.userid = ?")
			    		.setParameter(0, userId);
        List<Object> list = query.list();  
//	     s.close();	    
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
		
//		Session s = sessionFactory.openSession(); 
		Session s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		s.save(member);
		s.flush();
		s.getTransaction().commit();
//		s.close();
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
		s.close();
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
		
//		 Session s = sessionFactory.openSession(); 
////		Session s = sessionFactory.getCurrentSession(); 
//	     s.beginTransaction();
//	     s.update(minfo);
//	     s.getTransaction().commit();
//	     s.close();
	}
	@Override
	public List<MemberInfo> loadMemberInfoWithUserId(User user) {
		
		int mid = this.getMemberIdWithUserId(user.getId());
		List<MemberInfo> minfos = new ArrayList<MemberInfo>();
		
//		 Session s = sessionFactory.openSession(); 
		Session s = sessionFactory.getCurrentSession(); 
	     	     
	     String hql = "from MemberInfo where id=?";      
	     Query query = s.createQuery(hql); 
	     query.setString(0, ""+mid);
	     minfos = query.list();    
//	     s.close();
	     System.out.println("Get list size="+minfos.size());
	     System.out.println("member selected info----\n"+minfos.get(0).toString());
		
		return minfos;
	}
	
	@Override
	public List<Uploadfiles> loadUploadfilesByUserId(int _userid) {
		List<Uploadfiles> ufiles = new ArrayList<Uploadfiles>();
		Session s = sessionFactory.getCurrentSession(); 
		String hql = "from Uploadfiles where userid=?";      
	    Query query = s.createQuery(hql); 
	    query.setString(0, ""+_userid);
		ufiles = query.list();
		System.out.println("ufiles size="+ufiles.size());
		for(Uploadfiles uf:ufiles)
			System.out.println("-----###-----"+uf.toString());
		return ufiles;
	}
	
	@Override
	public void save(Uploadfiles _uploadfiles) {
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.save(_uploadfiles);
	     s.flush();
	     s.getTransaction().commit();
		
	}
	
	@Override
	public void delete(Uploadfiles _uploadfiles) {
		Session s = sessionFactory.getCurrentSession(); 
	     s.beginTransaction();
	     s.delete(_uploadfiles);
	     s.flush();
	     s.getTransaction().commit();
		
	}
	
	

}
