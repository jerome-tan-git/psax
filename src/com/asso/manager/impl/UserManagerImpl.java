package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.UserDao;
import com.asso.dao.impl.UserDaoImpl;
import com.asso.manager.UserManager;
import com.asso.model.Article;
import com.asso.model.MemberInfo;
import com.asso.model.Uploadfiles;
import com.asso.model.User;

@Component("userManager")
public class UserManagerImpl implements UserManager {

	//For App test
//	private UserDao userDao = new UserDaoImpl();
	
	//For WEB test
	private UserDao userDao ;
	
		
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
	public void update(User user) throws ClassNotFoundException, SQLException{		
		userDao.update(user);				
	}
	@Override
	public void delete(int userid) throws ClassNotFoundException, SQLException{		
		User u = new User();
		u.setId(userid);
		userDao.delete(u);	
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
	@Override
	public List<User> loadusers() throws ClassNotFoundException, SQLException{
		return userDao.loadAllUsers();
	}
	@Override
	public User loadUser(User user) throws ClassNotFoundException, SQLException{		
		System.out.println("User input userid ------"+user.getId()+", password-----"+user.getPassword());
		int r = userDao.checkUserExistsWithNamePassword(user);
		if(r==1)
//			return userDao.loadUserWithNamePassword(user);
			return userDao.loadUser(user.getId());
		else
			return new User();
			
	}
	
	@Override
	public void addUploadfiles(Uploadfiles uploadfile) throws ClassNotFoundException, SQLException{
		userDao.save(uploadfile);
	}
	
	@Override
	public List<Uploadfiles> loadUploadedFilesByUserid(int _userid) throws ClassNotFoundException, SQLException{		
		System.out.println("loadUploadedFilesByUserid, input userid ------"+_userid);
		return userDao.loadUploadfilesByUserId(_userid);	
	}

	@Override
	public void deleteUploadedFile(int _uploadedfileid) throws ClassNotFoundException, SQLException{
		Uploadfiles uf = new Uploadfiles();
		uf.setId(_uploadedfileid);
		userDao.delete(uf);
	}
	
}
