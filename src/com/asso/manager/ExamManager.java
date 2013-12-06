package com.asso.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.asso.model.Exam;
import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.model.MemberInfo;
import com.asso.model.User;

public interface ExamManager {

	public void add(Exam exam) throws ClassNotFoundException,SQLException;
	public void add(ExamItem examitem) throws ClassNotFoundException,SQLException;
//	public void add(ExamRef examref) throws ClassNotFoundException,SQLException;
	public void add(List<ExamRef> examrefs) throws ClassNotFoundException,SQLException;
	
	public void edit(ExamItem examitem) throws ClassNotFoundException,SQLException;
	public void edit(ExamRef examref) throws ClassNotFoundException,SQLException;
//	public void edit(List<ExamRef> refs) throws ClassNotFoundException,SQLException;
	
	public void delete(ExamItem examitem) throws ClassNotFoundException,SQLException;
	public void delete(ExamRef examref) throws ClassNotFoundException, SQLException;	
	public void deleteRefsByItem(ExamItem examitem) throws ClassNotFoundException,SQLException;
	
	public ExamRef loadRef(int refid) throws ClassNotFoundException, SQLException;
	public List<ExamRef> loadRefs(int itemid) throws ClassNotFoundException,SQLException;
	
	public HashMap<ExamItem, List<ExamRef>> loadItemsByExamId(User user) throws ClassNotFoundException, SQLException;
	public ExamItem loadItem(int itemid) throws ClassNotFoundException, SQLException;
	public ExamItem loadItemByQ(String question) throws ClassNotFoundException, SQLException;
	public List<ExamItem> loadItemlistByCatid(int catid) throws ClassNotFoundException, SQLException;
	public List<ExamItem> loadItemlistByExamid(int examid) throws ClassNotFoundException, SQLException;
	public List<Exam> loadExams() throws ClassNotFoundException, SQLException;
	
	
	
	


}