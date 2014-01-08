package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.ArticleDao;
import com.asso.dao.ChannelDao;
import com.asso.manager.ArticleManager;
import com.asso.manager.ChannelManager;
import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Message;

@Component("articleManager")
public class ArticleManagerImpl implements ArticleManager {

	//For WEB test
	private ArticleDao articleDao ;
		
	
	public ArticleDao getArticleDao() {
		return articleDao;
	}
	@Resource(name="articleDao")
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	@Override
	public void add(Article article) throws ClassNotFoundException, SQLException {		
		articleDao.save(article);					
	}
	@Override
	public void update(Article article) throws ClassNotFoundException, SQLException {		
		articleDao.update(article);		
	}
	
	@Override
	public List<Article> loadArticles(int categoryid) throws ClassNotFoundException, SQLException{
		return articleDao.loadArticles(categoryid);		
	}
	
	@Override
	public List<Article> loadArticles() throws ClassNotFoundException, SQLException{
		return articleDao.loadArticles();		
	}

	@Override
	public List<Article> loadArticle(int articleid) throws ClassNotFoundException, SQLException{
		return articleDao.loadArticle(articleid);	
	}
	
	@Override
	public void deleteArticle(int articleid) throws ClassNotFoundException, SQLException{
		Article art = new Article();
		art.setId(articleid);
		articleDao.delete(art);
	}
	
	@Override
	public List<Message> loadMessages(int userid) throws ClassNotFoundException, SQLException{
		return articleDao.loadMessagesByUserid(userid);		
	}
	
	
}
