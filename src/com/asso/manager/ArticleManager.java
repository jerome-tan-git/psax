package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;

public interface ArticleManager {

	void add(Article article) throws ClassNotFoundException, SQLException;

	List<Article> loadArticles(int categoryid) throws ClassNotFoundException,
			SQLException;

	List<Article> loadArticle(int articleid) throws ClassNotFoundException,
			SQLException;

}
