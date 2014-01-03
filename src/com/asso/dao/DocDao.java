package com.asso.dao;

import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Doc;
import com.asso.model.FieldValue;

public interface DocDao {

	void saveDoc(Doc doc);

	void updateDoc(Doc _doc);

	List<FieldValue> loadFieldValueListByDocId(int _docid);

	void delFieldValueListByDocId(int _docid);

	void delFieldValue(FieldValue _fieldValue);

	void saveFieldValue(FieldValue fv);

	int maxFieldValueIndex(int _docid);

	void updateFieldValue(FieldValue _fv);

	int getNewDocId(String _date);

	List<Doc> loadDocs(int _formid);

	Doc loadDoc(int _docid);

	List<Doc> loadDocsByUser(int _userid);

	List<Doc> loadDocs(int _userid, int _formid);

	List<FieldValue> loadFieldValue(int _docid, int _fieldid, int _fvindex);

	List<FieldValue> loadFieldValue(int _docid, int _fieldid);
	

}
