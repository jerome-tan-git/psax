package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.Doc;
import com.asso.model.FieldValue;

public interface DocManager {

	void addDoc(Doc _doc) throws ClassNotFoundException, SQLException;

	void updateDoc(Doc _doc) throws ClassNotFoundException, SQLException;

	int getMaxFVIndex(int _formid);

	void addFieldValue(List<FieldValue> _fvl);

	void updateFieldValue(List<FieldValue> _fvl);

	void addFieldValue(Doc _doc);

	List<Doc> loadDocs(int _formid) throws ClassNotFoundException, SQLException;

	List<Doc> loadDocsWithFieldValueList(int _formid)
			throws ClassNotFoundException, SQLException;

	Doc loadDoc(int _docid) throws ClassNotFoundException, SQLException;

	Doc loadDocWithFieldValueList(int _docid) throws ClassNotFoundException,
			SQLException;

	List<Doc> loadDocByFormidUserid(int _docid, int _formid)
			throws ClassNotFoundException, SQLException;

	void updateFieldValue(FieldValue _fv);

	void updateFieldValueByFieldName(String _fn, String _value, int _docid,
			int _fvindex);

	void updateSingleFieldValueByFieldName(String _fn, String _value,
			int _docid, int _fvindex);

	void deleteFieldValueListByDocId(int _docid);

	int getDocIdByCreateDate(String _date);

	Doc loadLastDocWithFieldValueListByUser(int _userid)
			throws ClassNotFoundException, SQLException;

}
