package com.asso.action;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.asso.manager.UserManager;
import com.asso.model.MemberInfo;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("memberload") 
public class MemberLoad extends ActionSupport implements ModelDriven  {
	
	private UserRegisterInfo uInfo = new UserRegisterInfo();
	private UserManager um;
//	private ApplicationContext ctx;
	private List<MemberInfo> minfos;
	
	public MemberLoad(){
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

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.uInfo;
	}
	

	public List<MemberInfo> getMinfos() {
		return minfos;
	}

	public void setMinfos(List<MemberInfo> minfos) {
		this.minfos = minfos;
	}
	

	public UserRegisterInfo getuInfo() {
		return uInfo;
	}

	public void setuInfo(UserRegisterInfo uInfo) {
		this.uInfo = uInfo;
	}

	@Override
	public String execute(){
		
		System.out.println("TO GET Members LIST...");
		User u = new User();
		u.setId(this.uInfo.getId());
		try {
			this.minfos = um.loadMember(u);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	
	}
	

}
