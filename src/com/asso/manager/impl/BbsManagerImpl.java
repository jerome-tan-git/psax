package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.BbsDao;
import com.asso.manager.BbsManager;
import com.asso.model.Article;
import com.asso.model.Comment;
import com.asso.model.Message;
import com.asso.model.Topic;

@Component("bbsManager")
public class BbsManagerImpl implements BbsManager {

	//For WEB test
	private BbsDao bbsDao ;
		
	public BbsDao getBbsDao() {
		return bbsDao;
	}
	@Resource(name="bbsDao")
	public void setBbsDao(BbsDao bbsDao) {
		this.bbsDao = bbsDao;
	}
	
	
	@Override
	public void add(Topic _topic) throws ClassNotFoundException, SQLException {	
		bbsDao.save(_topic);					
	}
	@Override
	public void add(Comment _comment) throws ClassNotFoundException, SQLException {	
		bbsDao.save(_comment);		
	}
	@Override
	public void update(Topic _topic) throws ClassNotFoundException, SQLException {		
		bbsDao.update(_topic);		
	}
	@Override
	public void update(Comment _comment) throws ClassNotFoundException, SQLException {		
		bbsDao.update(_comment);		
	}
	@Override
	public void delete(Topic _topic) throws ClassNotFoundException, SQLException {		
		bbsDao.delete(_topic);		
	}
	@Override
	public void delete(Comment _comment) throws ClassNotFoundException, SQLException {		
		bbsDao.delete(_comment);		
	}
	
	@Override
	public List<Topic> loadTopics() throws ClassNotFoundException, SQLException{
		return bbsDao.loadTopics();		
	}
	@Override
	public Topic loadTopicWithCommentsByTopicId(int _topicid) throws ClassNotFoundException, SQLException{
		Topic topic = new Topic();
		topic = bbsDao.loadTopicByTopicId(_topicid).get(0);
		System.out.println("loadTopicWithCommentsByTopicId---------------topic="+topic.toString());
		List<Comment> comments = new ArrayList<Comment>();
		comments = bbsDao.loadCommentsByTopicId(_topicid);
		System.out.println("loadTopicWithCommentsByTopicId---------------comments size="+comments.size());
		for(Comment comm :comments)
			System.out.println("comment------------"+comm.toString());
		topic.setComments(comments);
		return topic;		
	}
	
	@Override
	public List<Topic> loadTopicsWithComments() throws ClassNotFoundException, SQLException{
		List<Topic> list = new ArrayList<Topic>();
		list = bbsDao.loadTopics();
		for(Topic t:list){
			t.setComments(bbsDao.loadCommentsByTopicId(t.getId()));
			System.out.println("topicid="+t.getId()+",----Comments(size)-->"+t.getComments().size());
		}
		return list;
	}
	
	@Override
	public int getCommentsCountByTopicId(int _topicid) throws ClassNotFoundException, SQLException{
		return bbsDao.loadCommentsByTopicId(_topicid).size();		
	}
	
}
