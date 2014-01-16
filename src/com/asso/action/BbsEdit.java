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

import com.asso.manager.BbsManager;
import com.asso.manager.UserManager;
import com.asso.model.Comment;
import com.asso.model.Topic;
import com.asso.model.User;
import com.asso.vo.BbsInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("bbsedit") 
public class BbsEdit extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,SessionAware{
	
	private BbsManager bm;	
	private UserManager um;	
	private BbsInfo binfo = new BbsInfo();
	private Topic topic;
	private Comment comment;	
	private List<Comment> comments;
	private List<Topic> topiclist;
	private User user;
	
	private HttpServletRequest request;	
	private Map session;

	public BbsEdit(){		
		bm = (BbsManager) SpringFactory.getObject("bbsManager");
		um = (UserManager) SpringFactory.getObject("userManager");
	}	
		
	
	public BbsManager getBm() {
		return bm;
	}
	@Resource(name="bbsManager")
	public void setBm(BbsManager bm) {
		this.bm = bm;
	}
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")
	public void setUm(UserManager um) {
		this.um = um;
	}
	public BbsInfo getBinfo() {
		return binfo;
	}
	public void setBinfo(BbsInfo binfo) {
		this.binfo = binfo;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}	
	public List<Topic> getTopiclist() {
		return topiclist;
	}
	public void setTopiclist(List<Topic> topiclist) {
		this.topiclist = topiclist;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public String toinput(){	
		System.out.println("--------------toinput-----------");
		return "input";
	}

	public String topicbuilt(){
		System.out.println("--------------topicbuilt-----------");
		User u = new User();
		u = (User) this.request.getSession().getAttribute("user_");
		this.topic = new Topic();
		this.topic.setTitle(this.binfo.getTopictitle());
		this.topic.setContent(this.binfo.getTopiccontent());
		this.topic.setDate(CONSTANT.getNowTime());
		this.topic.setAuther(u.getId());		
		this.topic.setAuthername(u.getUsername());
		System.out.println("topic---------------"+this.topic.toString());
		try {
			bm.add(this.topic);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.alltopiclist();
		return "built";
	}
	
	public String topicdetail(){
		int tid = 0;
		String topicid = this.request.getParameter("id");
		if(topicid!=null && topicid.length()>0)
			tid = Integer.parseInt(topicid);
		this.topic = new Topic();
		try {
			this.topic = bm.loadTopicWithCommentsByTopicId(tid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User u = new User();
		try {
			u = um.loadUserByid(this.topic.getAuther());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("user----------"+u.toString());
		
		this.topic.setUser(u);
		return "detail";
	}
	
	public String alltopiclist(){
		System.out.println("--------------topicbuilt-----------");
		this.topiclist = new ArrayList<Topic>();
		try {
			this.topiclist = bm.loadTopics();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("list size = "+this.topiclist.size());
		for(Topic o : this.topiclist)
			System.out.println("topic id = "+o.getId());
		return "list";
	}
	
	@Override
	public String execute(){
		
		System.out.println("------------------over-----------");
			
		return "success";
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
	}

	@Override
	public void setSession(Map session) {	  	
		 this.session=session;			
	}
	
	@Override
	public Object getModel() {	
		return this.binfo;
	}

}
