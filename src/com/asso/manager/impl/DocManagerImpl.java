package com.asso.manager.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.DocDao;
import com.asso.manager.DocManager;
import com.asso.model.Doc;

@Component("docManager")
public class DocManagerImpl implements DocManager {

	//For WEB test
	private DocDao docDao;
	
	public DocDao getDocDao() {
		return docDao;
	}
	@Resource(name="docDao")
	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}
	
	
	@Override
	public void addDoc(Doc _doc) throws ClassNotFoundException, SQLException {		
		docDao.saveDoc(_doc);
	}
	@Override
	public void updateDoc(Doc _doc) throws ClassNotFoundException, SQLException {		
		docDao.updateDoc(_doc);		
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
