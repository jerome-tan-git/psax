package com.asso.dao;

import java.util.List;

import com.asso.model.Fields;
import com.asso.model.Form;

public interface FormDao {

	void saveForm(Form form);
	void updateForm(Form form);

	
	Form loadForm(int _formid);
	List<Fields> loadFieldsByFormId(int _formid);

	void delFieldsByFormId(int _formid);
	void delField(Fields _field);
	void saveField(Fields field);
	Fields getFieldIdByName(String _name);
	List<Form> loadAllForms();
	
}
