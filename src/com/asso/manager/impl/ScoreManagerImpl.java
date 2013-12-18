package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.ScoreDao;
import com.asso.dao.UserDao;
import com.asso.dao.impl.ScoreDaoImpl;
import com.asso.dao.impl.UserDaoImpl;
import com.asso.manager.ScoreManager;
import com.asso.manager.UserManager;
import com.asso.model.MemberInfo;
import com.asso.model.Score;
import com.asso.model.ScoreExamItem;
import com.asso.model.ScoreExamRef;
import com.asso.model.User;

@Component("scoreManager")
public class ScoreManagerImpl implements ScoreManager {

	//For App test
//	private ScoreDao scoreDao = new ScoreDaoImpl();
	
	//For WEB test
	private ScoreDao scoreDao;

	public ScoreDao getScoreDao() {
		return scoreDao;
	}
	@Resource(name="scoreDao")
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	public void add(Score score) throws ClassNotFoundException, SQLException {
		scoreDao.save(score);		
	}
	@Override
	public void add(ScoreExamItem scorexamitem) throws ClassNotFoundException,
			SQLException {
		scoreDao.save(scorexamitem);
	}
	@Override
	public void add(ScoreExamRef scorexamref) throws ClassNotFoundException,
			SQLException {
		scoreDao.save(scorexamref);
	}
	@Override
	public void update(Score score) throws ClassNotFoundException, SQLException {
		scoreDao.update(score);		
	}
	@Override
	public int getScoreId(Score score){
		return scoreDao.getScoreId(score);
	}

}
