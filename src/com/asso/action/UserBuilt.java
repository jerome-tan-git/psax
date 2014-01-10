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

import com.asso.manager.UserManager;
import com.asso.model.Uploadfiles;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userbuilt") 
public class UserBuilt extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware {
	
	private UserRegisterInfo uInfo = new UserRegisterInfo();
	private UserManager um;
	private HttpServletRequest request;	
	private Map session;
	
	private User user;
	private List<Uploadfiles> upflist;

	public UserBuilt(){
		um = (UserManager) SpringFactory.getObject("userManager");
	}
	
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")//直接注入，替代初始化userManager
	public void setUm(UserManager um) {
		this.um = um;
	}
	public UserRegisterInfo getuInfo() {
		return uInfo;
	}
	public void setuInfo(UserRegisterInfo uInfo) {
		this.uInfo = uInfo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Uploadfiles> getUpflist() {
		return upflist;
	}
	public void setUpflist(List<Uploadfiles> upflist) {
		this.upflist = upflist;
	}

	public String manager(){
		if(this.session!=null){
			User u = (User) this.session.get("user_");
			if(u!=null){
				this.user = new User();
				this.setUser(u);
			}
			System.out.println("session user------"+u.toString());
			
			String upldfid= this.request.getParameter("upfid");
			if(upldfid!=null && upldfid.length()>0){
				int id = Integer.parseInt(upldfid);
				try {
					um.deleteUploadedFile(id);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			this.buildLoadedFiles(u.getId());
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
	
	private void buildLoadedFiles(int _userid){
		this.upflist = new ArrayList<Uploadfiles>();
		try {
			this.setUpflist(um.loadUploadedFilesByUserid(_userid));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Uploadfiles uf:this.upflist){
			String time = uf.getUploadtime();
			time = time.substring(0,10);
			uf.setUploadtime(time);
			System.out.println("---------->>>"+uf.toString());
		}
	}

	
	@Override
	public String execute(){
		System.out.println("GET username--->"+this.uInfo.getUsername());
		System.out.println("GET password--->"+this.uInfo.getPassword());
		System.out.println("GET password1--->"+this.uInfo.getPassword1());
		User u = new User();
		u.setPassword(this.uInfo.getPassword());
		u.setUsername(this.uInfo.getUsername());

		try {
			um.add(u);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	
	}
	
	@Override
	public Object getModel() {		
		return this.uInfo;
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
