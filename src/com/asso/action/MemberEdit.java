package com.asso.action;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.asso.manager.UserManager;
import com.asso.model.MemberInfo;
import com.asso.model.User;
import com.asso.vo.MemberUpdateInfo;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Scope("prototype")
@Component("memberedit") 
public class MemberEdit extends ActionSupport implements ModelDriven {
	
	private MemberInfo memberinfo = new MemberInfo();
	private MemberUpdateInfo muinfo = new MemberUpdateInfo();
	
	private UserManager um;
	private ApplicationContext ctx;
	
	public MemberEdit(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		um = (UserManager) ctx.getBean("userManager");
	}
	
	public UserManager getUm() {
		return um;
	}

	@Resource(name="userManager")//直接注入，替代初始化userManager
	public void setUm(UserManager um) {
		this.um = um;
	}

	
	public MemberInfo getMemberinfo() {
		return memberinfo;
	}

	public void setMemberinfo(MemberInfo memberinfo) {
		this.memberinfo = memberinfo;
	}

	public MemberUpdateInfo getMuinfo() {
		return muinfo;
	}

	public void setMuinfo(MemberUpdateInfo muinfo) {
		this.muinfo = muinfo;
	}

	@Override
	public String execute(){
		System.out.println("GET id from this.memberinfo--->"+this.memberinfo.getId());
		System.out.println("GET tel from this.memberinfo--->"+this.memberinfo.getC_tel());
		System.out.println("GET addr from this.memberinfo--->"+this.memberinfo.getC_addr());
		System.out.println("GET email from this.memberinfo--->"+this.memberinfo.getC_email());
		
		System.out.println("GET id from this.muinfo--->"+this.muinfo.getId());
		System.out.println("GET tel from this.muinfo--->"+this.muinfo.getC_tel());
		System.out.println("GET addr from this.muinfo--->"+this.muinfo.getC_addr());
		System.out.println("GET email from this.muinfo--->"+this.muinfo.getC_email());
		
		
		try {
			um.editMember(memberinfo);
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
		return this.memberinfo;
	}


}
