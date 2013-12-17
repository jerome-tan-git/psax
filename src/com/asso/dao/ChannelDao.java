package com.asso.dao;

import java.util.List;

import com.asso.model.Category;
import com.asso.model.Channel;

public interface ChannelDao {

	void save(Channel channel);
	List<Channel> loadChannels();
	List<Category> loadCategories(int channelid);
	List<Category> loadCategories();
	List<Category> loadCategoryPath(int categoryid);

}
