package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.MemberInfo;
import com.asso.model.Uploadfiles;
import com.asso.model.User;

public interface UserManager {

	public abstract int exists(User user) throws ClassNotFoundException,
			SQLException;

	public abstract void add(User user) throws ClassNotFoundException,
			SQLException;

	public void addMember(User user) throws ClassNotFoundException, SQLException;

	public void editMember(MemberInfo minfo) throws ClassNotFoundException,
			SQLException;

	public List<MemberInfo> loadMember(User user) throws ClassNotFoundException, SQLException;

	public int getUserId(User user);

	User loadUser(User user) throws ClassNotFoundException, SQLException;

	void addUploadfiles(Uploadfiles uploadfile) throws ClassNotFoundException,
			SQLException;

	List<Uploadfiles> loadUploadedFilesByUserid(int _userid)
			throws ClassNotFoundException, SQLException;

	void deleteUploadedFile(int _uploadedfileid) throws ClassNotFoundException,
			SQLException;


}