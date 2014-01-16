package com.asso.dao;

import java.util.List;

import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.Comment;
import com.asso.model.Message;
import com.asso.model.Topic;

public interface BbsDao {

	void save(Topic _topic);

	void save(Comment _comment);

	void update(Topic _topic);

	void update(Comment _comment);

	void delete(Comment _comment);

	void delete(Topic _topic);

	List<Topic> loadTopics();

	List<Comment> loadComments();

	List<Comment> loadCommentsByTopicId(int _topicid);

	List<Topic> loadTopicByTopicId(int _topicid);

	List<Comment> loadCommentById(int _commentid);

	

}
