package com.asso.manager;

import java.sql.SQLException;

import com.asso.model.Doc;

public interface DocManager {

	void addDoc(Doc _doc) throws ClassNotFoundException, SQLException;

	void updateDoc(Doc _doc) throws ClassNotFoundException, SQLException;

}
