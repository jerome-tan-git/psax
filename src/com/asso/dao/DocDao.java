package com.asso.dao;

import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Doc;

public interface DocDao {

	void saveDoc(Doc doc);

	void updateDoc(Doc _doc);
	

}
