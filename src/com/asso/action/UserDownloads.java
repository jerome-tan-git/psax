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

import util.SpringFactory;

import com.asso.manager.ArticleManager;
import com.asso.manager.FormManager;
import com.asso.model.Form;
import com.asso.model.Message;
import com.asso.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userdownloads") 
public class UserDownloads extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware {
	
//	private UserRegisterInfo uInfo = new UserRegisterInfo();
//	private UserManager um;
	private FormManager fm;
	private ArticleManager am;
	private HttpServletRequest request;	
	private Map session;
	
	private User user;
	private List<Form> formlist;
	private List<Message> messagelist;

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

	public String getNotice(){
		System.out.println("-------------------getNotice-------------------");
		if(this.session!=null){
			User u = (User) this.session.get("user_");
			System.out.println("session user------"+u.toString());
			
			if(u!=null){
				this.user = new User();
				this.setUser(u);
				
				try {
					this.setMessagelist(am.loadMessages(u.getId()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		
			for(Message m:this.messagelist){
				System.out.println("---------->>>"+m.toString());
				//to update to isread=1
			}
			
		}		
		return SUCCESS;
	}
//	public String upload(){
//		System.out.println("---------------upload---------------");
//		
//		String userid = this.uInfo.getUserid();
//		System.out.println("GET userid--->"+userid);
//		
//		String[] upfileNames = this.uInfo.getUploadfilenames();
//		if(upfileNames!=null){
//			for(String ufn:upfileNames){
//				System.out.println("GET uploadfile name--->"+ufn);
//			}
//			
//			String[] upfiles = this.uInfo.getUploadfiles();
//			if(upfileNames.length!=upfiles.length)
//				System.out.println("ERROR!!!!---->upfileNames.length!=upfiles.length");
//			for(int i=0;i<upfiles.length; i++){
//				String uf = upfiles[i];
//				String ufn = upfileNames[i];
//				System.out.println("GET uploadfile--->"+uf);
//				Uploadfiles uploadfiles = new Uploadfiles();
//				uploadfiles.setFile(uf);
//				uploadfiles.setFname(ufn);
//				uploadfiles.setUserid(Integer.parseInt(userid));
//				uploadfiles.setUploadtime(CONSTANT.getNowTime());
//				try {
//					um.addUploadfiles(uploadfiles);
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}		
//		}		
//		
//		this.buildLoadedFiles(Integer.parseInt(userid));
//		return "upload";
//	}
	
	private void build2DownloadFiles(){
		this.formlist = new ArrayList<Form>();
		try {
			this.setFormlist(fm.loadForms());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Form f:this.formlist){
			System.out.println("---------->>>"+f.toString());
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
	
	@Override
	public Object getModel() {		
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
