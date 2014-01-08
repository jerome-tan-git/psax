package com.asso.dao;

import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Message;

public interface ArticleDao {

	void save(Article article);
	List<Article> loadArticles(int categoryid);
	List<Article> loadArticle(int articleid);
	void update(Article article);
	void delete(Article article);
	List<Article> loadArticles();
	
	
	List<Message> loadMessagesByUserid(int userid);
	void save(Message message);
	void update(Message message);

}
