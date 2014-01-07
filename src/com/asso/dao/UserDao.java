package com.asso.dao;

import java.util.List;

import com.asso.model.MemberInfo;
import com.asso.model.User;

public interface UserDao {
	public void save(User user);
//	public List<User> loadAllUsers();
//	public boolean checkUserExistsWithName(String username);
	public int checkUserExistsWithNamePassword(User user);
	/**Results---
	 * 1.exists
	 * 2.name wrong
	 * 3.only password wrong
	 * */
	public void saveMember(User user);	
	public int getUserIdWithName(User user);
	public void saveMemberInfo(int memid, User user);
	public int getMemberIdWithUserId(int userid);
	public void updateMemberInfoWithId(MemberInfo minfo);
	public List<MemberInfo> loadMemberInfoWithUserId(User user);
	User loadUserWithNamePassword(User _user);
	
	
	
}
