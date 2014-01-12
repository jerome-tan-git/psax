package com.asso.dao;

import java.util.HashMap;
import java.util.List;

import com.asso.model.Exam;
import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.model.MemberInfo;
import com.asso.model.User;

public interface ExamDao {
	
	public void save(Exam exam);
	public void save(ExamItem examitem);
	public void save(List<ExamRef> refs);
	public void update(ExamItem ExamItem);
	public void update(ExamRef ref);
	public void update(List<ExamRef> refs);	
	public void delete(ExamRef ref);
	public void delete(ExamItem examitem);
	public void deleteRefs(ExamItem examitem);
	public List<ExamRef> loadExamRefById(int refid);
	public List<ExamRef> loadReflistByItemId(int itemid);
	
	public HashMap<ExamItem,List<ExamRef>> loadItemsByGroupid(int groupid);
	public List<ExamItem> loadExamItemById(int itemid);
	public List<ExamItem> loadExamItemByCatId(int cid);
	public List<ExamItem> loadExamItemByExamId(int eid);
	public List<ExamItem> loadExamItemByQ(String question);
	public List<Exam> loadExams();
	Exam loadExamById(int _examid);
	
	
	
	
		
	
}
