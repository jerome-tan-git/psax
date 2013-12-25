package com.asso.dao;

import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;

public interface ArticleDao {

	void save(Article article);
	List<Article> loadArticles(int categoryid);
	List<Article> loadArticle(int articleid);
	void update(Article article);
	void delete(Article article);
	List<Article> loadArticles();

}
