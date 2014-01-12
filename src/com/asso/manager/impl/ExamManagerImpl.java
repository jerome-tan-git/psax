package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.ExamDao;
import com.asso.dao.UserDao;
import com.asso.dao.impl.ExamDaoImpl;
import com.asso.dao.impl.UserDaoImpl;
import com.asso.manager.ExamManager;
import com.asso.manager.UserManager;
import com.asso.model.Exam;
import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.model.MemberInfo;
import com.asso.model.User;

@Component("examManager")
public class ExamManagerImpl implements ExamManager {

	//For App test
//	private ExamDao examDao = new ExamDaoImpl();
	
	//For WEB test
	private ExamDao examDao;
	
		
	public ExamDao getExamDao() {
		
		return this.examDao;
	}

	@Resource(name="examDao")
	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}


	@Override
	public void add(Exam exam) throws ClassNotFoundException, SQLException {
		System.out.println("EXAMmanagerImpl----addExam---"+exam.toString());
		examDao.save(exam);		
	}

//	@Override
//	public void checkIfNeedUpdate(ExamItem examitem){
//		
//	}
	@Override
	public void add(ExamItem examitem) throws ClassNotFoundException,
			SQLException {
		System.out.println("EXAMmanagerImpl----addExamItem---"+examitem.toString());
		examDao.save(examitem);//SAVEorUPDATE
	}

	@Override
	public void add(List<ExamRef> examrefs) throws ClassNotFoundException,
			SQLException {
		System.out.println("EXAMmanagerImpl----addExamRef size---"+examrefs.size());
		examDao.save(examrefs);//SAVEorUPDATE
	}

	@Override
	public void edit(ExamItem examitem) throws ClassNotFoundException,
			SQLException {
		System.out.println("EXAMmanagerImpl----editExamItem---"+examitem.toString());
		examDao.update(examitem);
	}

	@Override
	public void edit(ExamRef examref) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----editExamRef---"+examref.toString());
		examDao.update(examref);
	}

//	@Override
//	public void edit(List<ExamRef> refs) throws ClassNotFoundException,
//			SQLException {
//		// TODO Auto-generated method stub
//		
//	}

		
	@Override
	public void delete(ExamRef examref) throws ClassNotFoundException,
			SQLException {
		System.out.println("EXAMmanagerImpl----delExamRef---"+examref.toString());
		examDao.delete(examref);		
	}

	@Override
	public void delete(ExamItem examitem) throws ClassNotFoundException,
			SQLException {
		System.out.println("EXAMmanagerImpl----delExamItem---"+examitem.toString());
		examDao.delete(examitem);
	}
	
	@Override
	public Exam loadExam(int _examid) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loadExamById-----"+_examid);
		return examDao.loadExamById(_examid);
	}	

	@Override
	public List<Exam> loadExams() throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loadExams-----");
		return examDao.loadExams();
	}
	
	@Override
	public ExamRef loadRef(int refid) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loadref---"+refid);
		return examDao.loadExamRefById(refid).get(0);
	}
	
	@Override
	public List<ExamRef> loadRefs(int itemid) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loadrefs---"+itemid);
		return examDao.loadReflistByItemId(itemid);
	}
	@Override
	public ExamItem loadItem(int itemid) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loaditem---"+itemid);
		return examDao.loadExamItemById(itemid).get(0);
	}
	@Override
	public ExamItem loadItemByQ(String question) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loaditemByQuestion---"+question);
		return examDao.loadExamItemByQ(question).get(0);
	}
	@Override
	public List<ExamItem> loadItemlistByCatid(int catid) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loaditemlist---"+catid);
		return examDao.loadExamItemByCatId(catid);
	}
	@Override
	public List<ExamItem> loadItemlistByExamid(int examid) throws ClassNotFoundException,SQLException {
		System.out.println("EXAMmanagerImpl----loaditemlist---"+examid);
		return examDao.loadExamItemByExamId(examid);
	}

	@Override
	public HashMap<ExamItem, List<ExamRef>> loadItemsByExamId(User user)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRefsByItem(ExamItem examitem)
			throws ClassNotFoundException, SQLException {
		System.out.println("EXAMmanagerImpl----deleteRefsByItem---"+examitem.toString());
		examDao.deleteRefs(examitem);		
	}


}
