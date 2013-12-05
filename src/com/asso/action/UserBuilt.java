package com.asso.action;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.asso.manager.UserManager;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userbuilt") 
public class UserBuilt extends ActionSupport implements ModelDriven {
	private UserRegisterInfo uInfo = new UserRegisterInfo();

	private UserManager um;
//	private ApplicationContext ctx;
	

	public UserBuilt(){
//		ctx = new ClassPathXmlApplicationContext("beans.xml");
//		um = (UserManager) ctx.getBean("userManager");
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
		// TODO Auto-generated method stub
		return this.uInfo;
	}

}
