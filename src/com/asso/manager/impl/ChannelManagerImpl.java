package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.ChannelDao;
import com.asso.manager.ChannelManager;
import com.asso.model.Category;
import com.asso.model.Channel;

@Component("channelManager")
public class ChannelManagerImpl implements ChannelManager {
//
//	//For App test
////	private ChannelDao channelDao = new ChannelDaoImpl();
//	
//	//For WEB test
//	private ChannelDao channelDao ;
//		
//	public ChannelDao getChannelDao() {
//		return channelDao;
//	}
//	@Resource(name="channelDao")
//	public void setChannelDao(ChannelDao channelDao) {
//		this.channelDao = channelDao;
//	}
//
//	@Override
//	public void add(Channel channel) throws ClassNotFoundException, SQLException{		
//		channelDao.save(channel);					
//	}
//
//	@Override
//	public List<Channel> loadChannels() throws ClassNotFoundException, SQLException{
//		return channelDao.loadChannels();		
//	}
//	
//	@Override
//	public List<Category> loadCategories() throws ClassNotFoundException, SQLException{
//		return channelDao.loadCategories();		
//	}
//	
//	@Override
//	public List<Category> loadCategories(int channelid) throws ClassNotFoundException, SQLException{
//		return channelDao.loadCategories(channelid);		
//	}
//
//	@Override
//	public List<Category> loadCategoryPath(int categoryid) throws ClassNotFoundException, SQLException{
//		return channelDao.loadCategoryPath(categoryid);	
//	}
	
}
