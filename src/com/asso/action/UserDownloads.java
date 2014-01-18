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
import com.asso.manager.FormManager;
import com.asso.model.Article;
import com.asso.model.Form;
import com.asso.model.Message;
import com.asso.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userdownloads") 
//public class UserDownloads extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware {
public class UserDownloads extends ActionSupport implements ServletRequestAware,SessionAware {
	


	private static final long serialVersionUID = 40968698194325250L;
	//	private UserRegisterInfo uInfo = new UserRegisterInfo();
//	private UserManager um;
	private FormManager fm;
	private ArticleManager am;
	private HttpServletRequest request;	
	private Map session;
	
	private User user;
	private List<Form> formlist;
	private List<Message> messagelist;
	private List<Message> readmessagelist;
//	private String message;
	private String msgtitle;
	private String msginfo;
	
//	private String[] userids;

	public UserDownloads(){
		fm = (FormManager) SpringFactory.getObject("formManager");
		am = (ArticleManager) SpringFactory.getObject("articleManager");
	}
	
	public FormManager getFm() {
		return fm;
	}
	@Resource(name="formManager")//直接注入，替代初始化userManager
	public void setFm(FormManager fm) {
		this.fm = fm;
	}	
	public ArticleManager getAm() {
		return am;
	}
	@Resource(name="articleManager")
	public void setAm(ArticleManager am) {
		this.am = am;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Form> getFormlist() {
		return formlist;
	}
	public void setFormlist(List<Form> formlist) {
		this.formlist = formlist;
	}
	public List<Message> getMessagelist() {
		return messagelist;
	}
	public void setMessagelist(List<Message> messagelist) {
		this.messagelist = messagelist;
	}
	public List<Message> getReadmessagelist() {
		return readmessagelist;
	}
	public void setReadmessagelist(List<Message> readmessagelist) {
		this.readmessagelist = readmessagelist;
	}

//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
	public String getMsgtitle() {
		return msgtitle;
	}
	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}
	public String getMsginfo() {
		return msginfo;
	}
	public void setMsginfo(String msginfo) {
		this.msginfo = msginfo;
	}
	
//	public String[] getUserids() {
//		return userids;
//	}
//	public void setUserids(String[] userids) {
//		this.userids = userids;
//	}

	public String sendNotice(){
//		System.out.println("Got message---"+this.message);
		System.out.println("Got message---"+this.msgtitle+", "+this.msginfo);
		String[] ids = this.request.getParameterValues("userids");
		if(ids!=null && ids.length>0){
			for(String userid:ids){
				System.out.println("GOT usrid-------"+userid);
				Message message = new Message();
//				message.setTitle(this.message);
				message.setTitle(this.msgtitle);
				message.setAbsinfo(this.msginfo);
				message.setUserid(Integer.parseInt(userid));
				message.setPubdate(CONSTANT.getTodayDate());
				try {
					am.add(message);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return "success";
	}


	public String getNotice(){
		System.out.println("-------------------getNotice-------------------");
		if(this.session!=null){
			User u = (User) this.session.get("user_");
			System.out.println("session user------"+u.toString());
			
			List<Message> ms = new ArrayList<Message>();
			if(u!=null){
				this.user = new User();
				this.setUser(u);
				
				try {
					ms = am.loadMessages(u.getId());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
			
			this.messagelist = new ArrayList<Message>();
			this.readmessagelist = new ArrayList<Message>();
			for(Message m:ms){
				System.out.println("------------>>>"+m.toString());		
				this.setYMD(m);
				if(m.getIsread()==1)
//					continue;
					this.readmessagelist.add(m);
				else{					
					this.messagelist.add(m);
					this.updateMessageToReaded(m);
				}
			}
			System.out.println("-suc!!!!!");
		}		
		return SUCCESS;
	}
	
	
	private void updateMessageToReaded(Message m){		
		m.setIsread(1);
		try {
			am.update(m);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void setYMD(Message _message){
		String _date = _message.getPubdate();
		if(_date.length()>=10 && _message!=null){
			String y = _date.substring(0,4);
			if(y.equals(CONSTANT.getThisYear()))
				_message.setYear("");
			else
				_message.setYear(y);
			_message.setMonth(_date.substring(5, 7));
			_message.setDay(_date.substring(8,10));
		}else
			System.out.println("Time DATA ERROR, PLS INV...");
	}
	
	private void build2DownloadFiles(){
		List<Form> flist = new ArrayList<Form>();
		try {
			flist = fm.loadForms();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.formlist = new ArrayList<Form>();
		for(Form f:flist){
			System.out.println("---------->>>"+f.toString());
			if(f.getDisplayname()!=null && f.getDisplayname().length()>0
					&& f.getFrontendtpl()!=null && f.getFrontendtpl().length()>0
					&& f.getPath()!=null && f.getPath().length()>0)
				this.formlist.add(f);
		}
	}

	
	@Override
	public String execute(){
		if(this.session!=null){
			User u = (User) this.session.get("user_");
			if(u!=null){
				this.user = new User();
				this.setUser(u);
			}
			System.out.println("session user------"+u.toString());
			
			this.build2DownloadFiles();
		}		
		return "success";
	
	}
	
//	@Override
//	public Object getModel() {		
//		return null;
//	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
