package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.DocDao;
import com.asso.manager.DocManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;

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
	@Override
	public void addFieldValue(List<FieldValue> _fvl){//withDocID
		for(FieldValue fv : _fvl)
			this.docDao.saveFieldValue(fv);
	}
	@Override
	public void addFieldValue(Doc _doc){//withoutDocID
		int docid = this.docDao.getNewDocId(_doc.getCreatedate());
		_doc.setDocid(docid);
	    List<FieldValue> fvl = _doc.getFvlist();
	    if(fvl!=null && fvl.size()>=1){
		    for(FieldValue fv : fvl){
		    	fv.setDocid(docid);
		    	this.docDao.saveFieldValue(fv);
		    }
	    }else{
	    	 System.out.println("Lack of List<FieldValue>!!!");
	    }			
	}
	@Override
	public void updateFieldValue(List<FieldValue> _fvl){		 
	     if(_fvl!=null && _fvl.size()>=1){
	    	 int docid=_fvl.get(0).getDocid();
	    	 this.docDao.delFieldValueListByDocId(docid);	    	 
	    	 this.addFieldValue(_fvl);
	     }else{
	    	 System.out.println("Lack of List<FieldValue>!!!");
	     } 		
	}
	
	@Override
	public int getMaxFVIndex(int _docid){
		return docDao.maxFieldValueIndex(_docid);
	}
	
	
	@Override
	public List<Doc> loadDocsWithFieldValueList(int _formid) throws ClassNotFoundException, SQLException{
		List<Doc> docs = docDao.loadDocs(_formid);
		for(Doc d:docs){
			List<FieldValue> fvl = docDao.loadFieldValueListByDocId(d.getDocid());
			d.setFvlist(fvl);
		}			
		return docs;		
	}

	@Override
	public List<Doc> loadDocs(int _formid) throws ClassNotFoundException,
			SQLException {
		return docDao.loadDocs(_formid);
	}
	@Override
	public Doc loadDoc(int _docid) throws ClassNotFoundException,
			SQLException {
		System.out.println("-----------docManagerImpl-------docid="+_docid);
		return docDao.loadDoc(_docid);
	}
	@Override
	public Doc loadDocWithFieldValueList(int _docid) throws ClassNotFoundException,
			SQLException {
		Doc doc = new Doc();
		doc = docDao.loadDoc(_docid);
		doc.setFvlist(docDao.loadFieldValueListByDocId(_docid));
		return doc;
	}

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
