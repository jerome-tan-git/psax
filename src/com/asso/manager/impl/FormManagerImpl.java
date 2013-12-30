package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.FormDao;
import com.asso.manager.FormManager;
import com.asso.model.Article;
import com.asso.model.Form;

@Component("formManager")
public class FormManagerImpl implements FormManager {

	//For WEB test
	private FormDao formDao ;
		
	
	public FormDao getFormDao() {
		return formDao;
	}
	@Resource(name="formDao")
	public void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}
	
	@Override
	public void addForm(Form _form) throws ClassNotFoundException, SQLException {		
		formDao.saveForm(_form);				
	}
	@Override
	public void updateForm(Form _form) throws ClassNotFoundException, SQLException {		
		formDao.updateForm(_form);		
	}
//	
//	@Override
//	public List<Article> loadArticles(int categoryid) throws ClassNotFoundException, SQLException{
//		return articleDao.loadArticles(categoryid);		
//	}
//	
//	@Override
//	public List<Article> loadArticles() throws ClassNotFoundException, SQLException{
//		return articleDao.loadArticles();		
//	}
//
//	@Override
//	public List<Article> loadArticle(int articleid) throws ClassNotFoundException, SQLException{
//		return articleDao.loadArticle(articleid);	
//	}
//	
//	@Override
//	public void deleteArticle(int articleid) throws ClassNotFoundException, SQLException{
//		Article art = new Article();
//		art.setId(articleid);
//		articleDao.delete(art);
//	}
//	
	
}
