package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Form;

public interface FormManager {

	void addForm(Form _form) throws ClassNotFoundException, SQLException;

	void updateForm(Form _form) throws ClassNotFoundException, SQLException;

	Form loadFormById(int _formid) throws ClassNotFoundException, SQLException;

	Form loadFormWithFieldsById(int _formid) throws ClassNotFoundException,
			SQLException;
	

}
