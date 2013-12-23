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
	public void add(Article article) throws ClassNotFoundException, SQLException{		
		articleDao.save(article);					
	}

	
	@Override
	public List<Article> loadArticles(int categoryid) throws ClassNotFoundException, SQLException{
		return articleDao.loadArticles(categoryid);		
	}

	@Override
	public List<Article> loadArticle(int articleid) throws ClassNotFoundException, SQLException{
		return articleDao.loadArticle(articleid);	
	}
	
}
