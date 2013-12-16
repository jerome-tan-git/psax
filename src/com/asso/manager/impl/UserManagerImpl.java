package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.UserDao;
import com.asso.dao.impl.UserDaoImpl;
import com.asso.manager.UserManager;
import com.asso.model.MemberInfo;
import com.asso.model.User;

@Component("userManager")
public class UserManagerImpl implements UserManager {

	//For App test
	private UserDao userDao = new UserDaoImpl();
	
	//For WEB test
//	private UserDao userDao ;
	
		
	public UserDao getUserDao() {
		
		return userDao;
	}

	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see com.maggie.ssh1.manager.impl.UserManager#exists(com.maggie.ssh1.model.User)
	 */
	@Override
	public int exists(User user) throws ClassNotFoundException, SQLException{		
		System.out.println("Into UserManagerImpl exists()...");		
		return userDao.checkUserExistsWithNamePassword(user);
	}
	@Override
	public int getUserId(User user){
		return userDao.getUserIdWithName(user);
	}
	
	/* (non-Javadoc)
	 * @see com.maggie.ssh1.manager.impl.UserManager#add(com.maggie.ssh1.model.User)
	 */
	@Override
	public void add(User user) throws ClassNotFoundException, SQLException{		
		userDao.save(user);					
	}
	
	@Override
	public void addMember(User user) throws ClassNotFoundException, SQLException{		
		System.out.println("User input info ------"+user.getUsername());
		userDao.saveMember(user);		
	}
	@Override
	public void editMember(MemberInfo minfo) throws ClassNotFoundException, SQLException{		
		System.out.println("Member input info ------"+minfo);
		userDao.updateMemberInfoWithId(minfo);		
	}
	@Override
	public List<MemberInfo> loadMember(User user) throws ClassNotFoundException, SQLException{		
		System.out.println("Member input userid ------"+user.getId());
		return userDao.loadMemberInfoWithUserId(user);		
	}
	

}
