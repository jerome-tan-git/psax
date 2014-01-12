package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CONSTANT;
import util.SpringFactory;

import com.asso.manager.ArticleManager;
import com.asso.manager.ChannelManager;
import com.asso.manager.UserManager;
import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.User;
import com.asso.vo.ChCatInfo;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("channeledit") 
public class ChannelEdit extends ActionSupport implements ServletRequestAware,SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChannelManager cm;	
	private Channel channel;
	private Category category;
	private List<Article> artlist = new ArrayList<Article>();
	private List<Article> newslist = new ArrayList<Article>();
	private ArticleManager am;	
	
	private HttpServletRequest request;	
	private Map session;

	public ChannelEdit(){		
		cm = (ChannelManager) SpringFactory.getObject("channelManager");
		am = (ArticleManager) SpringFactory.getObject("articleManager");
	}	
		
	public ChannelManager getCm() {
		return cm;
	}
	@Resource(name="channelManager")//直接注入，替代初始化channelManager
	public void setCm(ChannelManager cm) {
		this.cm = cm;
	}
	public ArticleManager getAm() {
		return am;
	}
	@Resource(name="articleManager")
	public void setAm(ArticleManager am) {
		this.am = am;
	}
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Article> getArtlist() {
		return artlist;
	}
	public void setArtlist(List<Article> artlist) {
		this.artlist = artlist;
	}
	public List<Article> getNewslist() {
		return newslist;
	}
	public void setNewslist(List<Article> newslist) {
		this.newslist = newslist;
	}

	public String addCategory(){		
		ChCatInfo cinfo = new ChCatInfo(); 
		cinfo.setChannelid(1);
		cinfo.setCategory("协会简介");
		cinfo.setParentid(0);
		
		this.category = new Category();
		this.category.setCategory(cinfo.getCategory());
		this.category.setChannelid(cinfo.getChannelid());
		this.category.setParentid(cinfo.getParentid());
		
		try {
			cm.add(category);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public String addChannel(){
		
		ChCatInfo cinfo = new ChCatInfo();
		cinfo.setChannel("test");		
		this.channel = new Channel();
		this.channel.setChannel(cinfo.getChannel());
		
		try {
			cm.add(channel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public String index(){
		return "success";
	}
	
	@Override
	public String execute(){
		String cat = this.request.getParameter("categoryid");
		System.out.println("cat-----------"+cat);
		if(cat!=null && cat.length()>0){
			int c = Integer.parseInt(cat);
		
			if(c==0){
				this.loadArticles();
				return "cat0";
			}
			if(c==1)
				return "cat1";
			if(c==2)
				return "cat2";
			if(c==15)
				return "cat15";
			
			if(c==7)
				return "cat7";
			if(c==8)
				return "cat8";
			if(c==9)
				return "cat9";
			if(c==10)
				return "cat10";
			if(c==11)
				return "cat11";
			if(c==12)
				return "cat12";
						
		}
		this.loadArticles();
		return "cat0";
	}

	private void sortArtlistByDate(List<Article> _list){
		List<String> toSort = new ArrayList<String>();
		for(Article art:_list)
			toSort.add(art.getPubdate());
		List<Integer> seqs = new ArrayList<Integer>();
		seqs = CONSTANT.sortDatesDesc(toSort);
		List<Article> sortedArtlist = new ArrayList<Article>();
		for(Integer seq:seqs)
			sortedArtlist.add(_list.get(seq));
		this.setArtlist(sortedArtlist);
	}
	private void cleanTxt(int _maxlength1,int _maxlength2, List<Article> _list){
		for(Article art:_list){
			String title = art.getTitle();
			if(title!=null && title.length()>0){
				title = CONSTANT.replaceHtml(title);				
			}else{
				title = CONSTANT.noContent;
			}
			art.setTitle(title);
			
			String article = art.getArticle();
			if(article!=null && article.length()>0){
				article = CONSTANT.replaceHtml(article);
				if(article.length()>_maxlength1)
					article=article.substring(0 , _maxlength1)+"......";				
			}else{
				article = CONSTANT.noContent;
			}
			art.setArticle(article);
			
			String abs = art.getAbsinfo();
			if(abs!=null && abs.length()>0){
				abs = CONSTANT.replaceHtml(abs);
				if(abs.length()>_maxlength2)
					abs=abs.substring(0 , _maxlength2)+"......";
				art.setAbsinfo(abs);
			}else{
				art.setAbsinfo(article);
			}				
		}
	}
	private void loadArticles(){
		try {
			this.artlist = am.loadArticles(1);//moment, cat=1
			this.newslist = am.loadArticles(3);//news, cat=3
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.sortArtlistByDate(this.artlist);
		this.cleanTxt(CONSTANT.momentMaxLength,CONSTANT.momentMaxLength,this.artlist);		
		if(this.artlist.size()>=3){
			List<Article> rlist = new ArrayList<Article>();
			rlist.add(this.artlist.get(0));rlist.add(this.artlist.get(1));rlist.add(this.artlist.get(2));
			this.setArtlist(rlist);
		}
		
		this.sortArtlistByDate(this.newslist);
		this.cleanTxt(CONSTANT.momentMaxLength,CONSTANT.momentMaxLength,this.newslist);
		
		if(this.newslist.size()>=3){
			List<Article> rlist = new ArrayList<Article>();
			rlist.add(this.newslist.get(0));rlist.add(this.newslist.get(1));rlist.add(this.newslist.get(2));
			this.setArtlist(rlist);
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
		  System.out.println("request.getSession()----"+request.getSession());
	}

	@Override
	public void setSession(Map<String, Object> _s) {
		this.session = _s;
		System.out.println("setSESSION----after--"+_s.toString());
	}


}
