package com.asso.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.asso.manager.UserManager;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userlogin") 
public class UserLogin extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware{
	
	private UserRegisterInfo uInfo = new UserRegisterInfo();
	private UserManager um;	
	private User user; 
	
	private File ptrt;
	private String ptrtContentType;
	private String ptrtFileName;
	
	
	private HttpServletRequest request;	
	private Map session;


	public UserLogin(){
		um = (UserManager) SpringFactory.getObject("userManager");
	}
	
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")//直接注入，替代初始化userManager
	public void setUm(UserManager um) {
		this.um = um;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
	public UserRegisterInfo getuInfo() {
		return uInfo;
	}
	public void setuInfo(UserRegisterInfo uInfo) {
		this.uInfo = uInfo;
	}
	public File getPtrt() {
		return ptrt;
	}
	public void setPtrt(File ptrt) {
		this.ptrt = ptrt;
	}
	public String getPtrtContentType() {
		return ptrtContentType;
	}
	public void setPtrtContentType(String ptrtContentType) {
		this.ptrtContentType = ptrtContentType;
	}
	public String getPtrtFileName() {
		return ptrtFileName;
	}
	public void setPtrtFileName(String ptrtFileName) {
		this.ptrtFileName = ptrtFileName;
	}


//	private void setUploadfiles(){
//		
//		System.out.println("GET username--->"+this.uInfo.getUsername());
//		System.out.println("GET password--->"+this.uInfo.getPassword());
//		System.out.println("GET nickname--->"+this.uInfo.getNickname());
//		System.out.println("GET portrait--->"+this.uInfo.getPortrait());
//		
//		
//		if(this.uInfo.getPortrait()!=null){
//			this.setPtrt(this.uInfo.getPortrait());
//			this.setPtrtContentType(this.uInfo.getPortraitContentType());
//			this.setPtrtFileName(this.uInfo.getPortraitFileName());
//			
//			System.out.println("this.uinfo.getPortrait()---"+this.uInfo.getPortrait());
//			System.out.println("this.uinfo.getPortraitContentType()---"+this.uInfo.getPortraitContentType());
//			System.out.println("this.uinfo.getPortraitFileName()---"+this.uInfo.getPortraitFileName());
//			
//			if(this.ptrt==null || this.ptrt.length()>4194304 ){  
//	            System.out.println("!!@@!!imageError");   
//	        } 
//		}else{
//			System.out.println("No portrait upload!!!");
//		}
//	}
//	
//	public String updateUserInfo() throws IOException{  
//		this.setUploadfiles();
//            String realpath = ServletActionContext.getServletContext().getRealPath("/ckimages");  
//            if(this.ptrt!=null)  
//            {  
//            	String newImgName = System.currentTimeMillis()+"_"+this.ptrtFileName;  
//                File savefile = new File(realpath,newImgName);  
//                if(!savefile.getParentFile().exists())  
//                    savefile.getParentFile().mkdirs();  
//  
//                FileUtils.copyFile(this.ptrt,savefile);  
//                System.out.println("realpath="+realpath+", name="+savefile.getName()); 
//            }  
//            return "success";  
//     }  
//	
	
	private boolean isEmpty(String _str){
		if(_str!=null && _str.length()>0)
			return false;
		else
			return true;
	}
	public String updateInfo() throws IOException{
		this.user = new User();
		this.user = (User)this.request.getSession().getAttribute("user_");
		System.out.println("updateInfo---session user"+this.user.toString());//null point
		System.out.println("updateInfo---this.uInfo.getIssave()---"+this.uInfo.getIssave());
		if(this.uInfo.getIssave()!=null){
			if(this.uInfo.getIssave().equals("保存")){
				if(!this.isEmpty(this.uInfo.getUsername()))
					this.user.setUsername(this.uInfo.getUsername());
				if(!this.isEmpty(this.uInfo.getPassword()))
					this.user.setPassword(this.uInfo.getPassword());
				if(!this.isEmpty(this.uInfo.getNickname()))
					this.user.setNickname(this.uInfo.getNickname());
				if(!this.isEmpty(this.uInfo.getEmail()))
					this.user.setEmail(this.uInfo.getEmail());
				if(!this.isEmpty(this.uInfo.getPhone()))
					this.user.setPhone(this.uInfo.getPhone());
				if(!this.isEmpty(this.uInfo.getPortrait()))
					this.user.setPortrait(this.uInfo.getPortrait());
				
				try {
					um.update(user);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.request.getSession().setAttribute("user_", this.user);			
			}	
		}else{
			//
		}
		
		
		this.userCenter();
		return "success";  
    }  


	public String gologin(){
		String referer = request.getHeader("referer");
		System.out.println("referer="+referer);
		this.request.getSession().setAttribute("returnURL", referer);
		return "go";
	}

	@Override
	public String execute(){
		
		System.out.println("GET username--->"+this.uInfo.getUsername());
		System.out.println("GET password--->"+this.uInfo.getPassword());
		
		User u = new User();
		u.setPassword(this.uInfo.getPassword());
		u.setUsername(this.uInfo.getUsername());
		
//		this.user.setUsername(this.uInfo.getUsername());
//		this.user.setPassword(this.uInfo.getPassword());
		
		int rz =0;
		try {
			rz= um.exists(u);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("rz="+rz);		
		
		if(rz==1){
			this.setSession2(this.request.getSession());
			  
			return "success";
		}
		return "failure";

	}

	@Override
	public Object getModel() {
		System.out.println("uInfo----"+uInfo.toString());
		return this.uInfo;
	}
	
	public String save(){
		
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
		return "save";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;		
		  System.out.println("Struts REQUEST info [request.getRequestedSessionId]----"+
				  this.request.getRequestedSessionId()+"   $$$   "+this.request.getRequestURI()); 
		  System.out.println("request.getSession()----"+request.getSession());
	}

	@Override
	public void setSession(Map session) {	  	

//		  User u = new User();
//		  u.setId(10);
//		  u.setUsername("ggg");
//		  u.setLevel(3);
//		  request.getSession().setAttribute("user", u);		  
//		  System.out.println("setSESSION_1----after----"+request.getSession().getAttribute("user").toString());
		if(request.getSession().getAttribute("user_")!=null){
		  System.out.println("setSESSION_1----after----"+request.getSession().getAttribute("user_").toString());
		  User user = (User) request.getSession().getAttribute("user_");
		  System.out.println("setSESSION_1----username----"+user.getUsername());
		}
		else
			System.out.println("setSESSION_1----after--session.getuser=null");
		  
	}

	public void setSession2(HttpSession session) {
		System.out.println("this.uInfo.getUsername()"+this.uInfo.getUsername());
		 User u = new User();
		 u.setUsername(this.uInfo.getUsername());
		 u.setPassword(this.uInfo.getPassword());
		 int userid = 0;
		 userid= um.getUserId(u);			
		 u.setId(userid);
		 request.getSession().setAttribute("user_", u);
		 System.out.println("setSession_2----Session().user_----"+
				 request.getSession().getAttribute("user_").toString());
		 
		  
	}
	
	public String userCenter(){
		
		User u = new User();
//		u = (User) this.session.get("user_");
		u = (User) request.getSession().getAttribute("user_");
		System.out.println("Session user----"+u.toString());
		
		if(u!=null){
			
			User user = new User();
			try {
				user = um.loadUser(u);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
//			if(user.getNickname()!=null){
			if(user!=null){
				System.out.println("Load user----"+user.toString());
//				u.setNickname(user.getNickname());
//				request.getSession().setAttribute("user_", u);
//				System.out.println("Session user----"+this.session.get("user_").toString());
				request.getSession().setAttribute("user_", user);
				System.out.println("Session user----"+this.request.getSession().getAttribute("user_").toString());
			}
			
		}
		
		return SUCCESS;
	}


}
