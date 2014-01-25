package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.DocDao;
import com.asso.dao.FormDao;
import com.asso.manager.DocManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;

@Component("docManager")
public class DocManagerImpl implements DocManager {

	//For WEB test
	private DocDao docDao;
	private FormDao fmDao;
	
	public DocDao getDocDao() {
		return docDao;
	}
	@Resource(name="docDao")
	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}
	
	
	public FormDao getFmDao() {
		return fmDao;
	}
	@Resource(name="formDao")
	public void setFmDao(FormDao fmDao) {
		this.fmDao = fmDao;
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
	public void updateFieldValue(FieldValue _fv){		 
	     if(_fv!=null && _fv.getFieldvalueid()>=1){	    	 
	    	 this.docDao.updateFieldValue(_fv);	
	     }else{
	    	 System.out.println("Lack of FieldValue!!!");
	     } 		
	}
	
	private int getFieldValueId(int _docid, int _fieldid, int _fvindex){
		int fvid = 0;
		List<FieldValue> rlist = this.docDao.loadFieldValue(_docid, _fieldid, _fvindex);
		if(rlist!=null && rlist.size()>0){
			if(rlist.size()==1)
				fvid = rlist.get(0).getFieldvalueid();
			else
				fvid = rlist.get(rlist.size()-1).getFieldvalueid();
		}else
			System.out.println("No fieldvalueid picked! New add!");
				
		return fvid;
	}
	private int getFieldValueId(int _docid, int _fieldid){
		int fvid = 0;
		List<FieldValue> rlist = this.docDao.loadFieldValue(_docid, _fieldid);
		if(rlist!=null && rlist.size()>0){
			if(rlist.size()==1)
				fvid = rlist.get(0).getFieldvalueid();
			else
				fvid = rlist.get(rlist.size()-1).getFieldvalueid();
		}else
			System.out.println("No fieldvalueid picked! New add!");
				
		return fvid;
	}
	
	@Override
	public void updateFieldValueByFieldName(String _fn, String _value, int _docid, int _fvindex){
		if(_fn!=null && _fn.length()>0){
			int fid = this.fmDao.getFieldIdByName(_fn).getFieldid();
			int fvid = this.getFieldValueId(_docid, fid, _fvindex);
			FieldValue fv = new FieldValue();
			fv.setFieldid(fid);
			fv.setFieldvalueid(fvid);
			fv.setValue(_value);
			fv.setDocid(_docid);			
			fv.setFieldvalueindex(_fvindex);
			this.docDao.updateFieldValue(fv);			
		}	     		
	}
	
	@Override
	public void updateSingleFieldValueByFieldName(String _fn, String _value, int _docid, int _fvindex){
		if(_fn!=null && _fn.length()>0){			
			System.out.println("this.fmDao.getFieldIdByName(_fn)---"+this.fmDao.getFieldIdByName(_fn).toString());
			int fid = this.fmDao.getFieldIdByName(_fn).getFieldid();
				
//			int fvid = 0 ;
//			if(_fvindex==-1)
//				fvid = this.getFieldValueId(_docid, fid);
//			else
//				fvid = this.getFieldValueId(_docid, fid, _fvindex);
			System.out.println("updateSingleFieldValueByFieldName  fid="+fid);
			FieldValue fv = new FieldValue();	
			if( _fvindex<0)
				fv.setFieldvalueindex(0);
			else
				fv.setFieldvalueindex(_fvindex);
			fv.setValue(_value);
			fv.setDocid(_docid);
			fv.setFieldid(fid);	
//			if(fvid==0){				
				this.docDao.saveFieldValue(fv);
//			}else{
//				fv.setFieldvalueid(fvid);				
//				this.docDao.updateFieldValue(fv);
//			}						
		}else{
			System.out.println("field name data ERROR, pls INV...");
		}
	}
	@Override
	public void deleteFieldValueListByDocId(int _docid){
		this.docDao.delFieldValueListByDocId(_docid);
	}
	
	
	@Override
	public int getMaxFVIndex(int _docid){
		return docDao.maxFieldValueIndex(_docid);
	}
	@Override
	public int getDocIdByCreateDate(String _date){
		return docDao.getNewDocId(_date);
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
	public Doc loadLastDocWithFieldValueListByUser(int _userid) throws ClassNotFoundException, SQLException{
		List<Doc> doclist = new ArrayList<Doc>();
		doclist = docDao.loadDocsByUser(_userid);
		Doc rzdoc = new Doc();
		if(doclist.size()>0){
			rzdoc = doclist.get(doclist.size()-1);
			System.out.println("DAO-----loadLastDocWithFieldValueListByUser------rzdoc.getDocid()="+rzdoc.getDocid());
			List<FieldValue> fvl = docDao.loadFieldValueListByDocId(rzdoc.getDocid());
			System.out.println("DAO-----loadLastDocWithFieldValueListByUser------fvl.size()="+fvl.size());
			rzdoc.setFvlist(fvl);
		}
					
		return rzdoc;		
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
	
	@Override
	public List<Doc> loadDocByFormidUserid(int _docid, int _formid) throws ClassNotFoundException,
			SQLException {
		List<Doc> rlist = null;
		return rlist;
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
