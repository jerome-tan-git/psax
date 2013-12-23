package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.Category;
import com.asso.model.Channel;

public interface ChannelManager {

	void add(Channel channel) throws ClassNotFoundException, SQLException;
	List<Channel> loadChannels() throws ClassNotFoundException, SQLException;
	List<Category> loadCategories();
	List<Category> loadCategories(int channelid) throws ClassNotFoundException,SQLException;
	List<Category> loadCategoryPath(int categoryid)throws ClassNotFoundException, SQLException;
	void add(Category category) throws ClassNotFoundException, SQLException;

}
