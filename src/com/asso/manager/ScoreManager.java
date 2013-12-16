package com.asso.manager;

import java.sql.SQLException;

import com.asso.model.Score;
import com.asso.model.ScoreExamItem;
import com.asso.model.ScoreExamRef;

public interface ScoreManager {

	public void add(Score Score) throws ClassNotFoundException,SQLException;
	public void add(ScoreExamItem scorexamitem) throws ClassNotFoundException, SQLException;
	public void add(ScoreExamRef scorexamref) throws ClassNotFoundException, SQLException;
	void update(Score score) throws ClassNotFoundException, SQLException;
	int getScoreId(Score score);


}