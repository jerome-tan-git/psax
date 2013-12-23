package com.asso.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CONSTANT;
import util.SpringFactory;

import com.asso.manager.ArticleManager;
import com.asso.manager.ChannelManager;
import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.vo.ArtInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("artedit") 
public class ArtEdit extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,SessionAware{
	
	private ArticleManager am;	
	private ChannelManager cm;	
	private ArtInfo ainfo = new ArtInfo();
	private Article article;
	private List<Category> categories;
//	private File fileTest; 
	private File pic;
	private File addition;
	private String picContentType;
	private String additionContentType;
	private String picFileName;
	private String additionFileName;
	
	private List<Article> artlist = new ArrayList<Article>();
	
	private HttpServletRequest request;	
	private Map session;

	public ArtEdit(){		
		am = (ArticleManager) SpringFactory.getObject("articleManager");
		cm = (ChannelManager) SpringFactory.getObject("channelManager");
	}	
			
	public ArticleManager getAm() {
		return am;
	}
	@Resource(name="articleManager")
	public void setAm(ArticleManager am) {
		this.am = am;
	}

	public ChannelManager getCm() {
		return cm;
	}
	@Resource(name="channelManager")
	public void setCm(ChannelManager cm) {
		this.cm = cm;
	}

	
	public ArtInfo getAinfo() {
		return ainfo;
	}

	public void setAinfo(ArtInfo ainfo) {
		this.ainfo = ainfo;
	}

	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public File getAddition() {
		return addition;
	}
	public void setAddition(File addition) {
		this.addition = addition;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
//	public File getFileTest() {  
//        return fileTest;  
//    }  
//    public void setFileTest(File fileTest) {  
//        this.fileTest = fileTest;  
//    }  
	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getAdditionContentType() {
		return additionContentType;
	}

	public void setAdditionContentType(String additionContentType) {
		this.additionContentType = additionContentType;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getAdditionFileName() {
		return additionFileName;
	}

	public void setAdditionFileName(String additionFileName) {
		this.additionFileName = additionFileName;
	}
	
	private void setUploadfiles(){
		if(this.ainfo.getPic()!=null){
			this.setPic(this.ainfo.getPic());
			this.setPicContentType(this.ainfo.getPicContentType());
			this.setPicFileName(this.ainfo.getPicFileName());
			System.out.println("this.ainfo.getPic()---"+this.ainfo.getPic());
			System.out.println("this.ainfo.getPicContentType()---"+this.ainfo.getPicContentType());
			System.out.println("this.ainfo.getPicFilename()---"+this.ainfo.getPicFileName());
		}
		if(this.ainfo.getAddition()!=null){
			this.setAddition(this.ainfo.getAddition());
			this.setAdditionContentType(this.ainfo.getAdditionContentType());
			this.setAdditionFileName(this.ainfo.getAdditionFileName());
			System.out.println("this.ainfo.getAddition()---"+this.ainfo.getAddition());
			System.out.println("this.ainfo.getAdditionContentType()---"+this.ainfo.getAdditionContentType());
			System.out.println("this.ainfo.getAdditionFilename()---"+this.ainfo.getAdditionFileName());
		}
		if(this.pic==null || this.pic.length()>4194304 ){  
            System.out.println("!!@@!!imageError");   
        } 
        if(this.addition==null || this.addition.length()>4194304){
        	System.out.println("!!@@!!additionError"); 
        }
        
		System.out.println("this.ainfo.getTitle()---"+this.ainfo.getTitle());
		System.out.println("this.ainfo.getArticle()---"+this.ainfo.getArticle());
		System.out.println("this.ainfo.getAbsinfo()---"+this.ainfo.getAbsinfo());
		System.out.println("this.ainfo.getCategoryid()---"+this.ainfo.getCategoryid());
		
		System.out.println("this.ainfo.getPubdate()---"+this.ainfo.getPubdate());
		System.out.println("this.ainfo.getSrcdisplay()---"+this.ainfo.getSrcdisplay());
	}
	
	
	public String addArticle(){		
		
		this.categories = cm.loadCategories();		
		this.setUploadfiles();
		
		this.article = new Article();
		if(this.ainfo.getPic()!=null){
			String path_i = ServletActionContext.getServletContext().getRealPath(CONSTANT.uploadImagesPath);
			System.out.println("real path(imgs) = "+path_i);
			String newImgName = System.currentTimeMillis()+"_"+this.picFileName;
			File saveImg = new File(new File(path_i),newImgName);
			if(!saveImg.getParentFile().exists())
		    	saveImg.getParentFile().mkdirs();
			try {
				FileUtils.copyFile(this.pic, saveImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.article.setPic(CONSTANT.uploadImagesPath+"/"+saveImg.getName());
//			System.out.println("PIC URL---"+CONSTANT.uploadImagesPath+"/"+saveImg.getName());
		}
		if(this.ainfo.getAddition()!=null){
			String path_f = ServletActionContext.getServletContext().getRealPath(CONSTANT.uploadFilesPath);		
			System.out.println("real path(docs) = "+path_f);		
			String newDocName = System.currentTimeMillis()+"_"+this.additionFileName;		
			File saveDoc = new File(new File(path_f), newDocName);
			if(!saveDoc.getParentFile().exists())
				saveDoc.getParentFile().mkdirs();
			try {			
				FileUtils.copyFile(this.addition, saveDoc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.article.setAddition(CONSTANT.uploadFilesPath+"/"+saveDoc.getName());
//			System.out.println("DOC URL---"+CONSTANT.uploadFilesPath+"/"+saveDoc.getName());
		}
		
			this.article.setTitle(this.ainfo.getTitle());		
			this.article.setArticle(this.ainfo.getArticle());
			this.article.setAbsinfo(this.ainfo.getAbsinfo());		
			this.article.setCategoryid(this.ainfo.getCategoryid());			
			this.article.setPubdate(this.ainfo.getPubdate());
			this.article.setSrcdisplay(this.ainfo.getSrcdisplay());
			System.out.println("this.article.toString()-----------"+this.article.toString());
			
			
		if(this.request.getParameter("articleid")!=null ){
			this.article.setId(Integer.parseInt(this.request.getParameter("articleid")));
			try {
				am.update(this.article);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}else{
			try {
				am.add(article);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return "success";
	}
	
	public String updateArticle(){
		int articleid = 0;
		if(this.request.getParameter("articleid")!=null){
			articleid = Integer.parseInt(this.request.getParameter("articleid"));
			try {
				this.artlist = am.loadArticle(articleid);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(this.artlist.size()==1)
				this.article = this.artlist.get(0);
			else
				System.out.println("DATA ERROR, Pls INV...");
		}else
			System.out.println("DATA ERROR, Pls INV...");
		
		System.out.println("TO UPDATE this.article="+this.article);
		return "update";
	}
	
	public String listArticleByCategoryId(){
		int catid = this.ainfo.getCategoryid();
		if(this.request.getParameter("categoryid")!=null)
			catid = Integer.parseInt(this.request.getParameter("categoryid"));
		try {
			this.artlist = am.loadArticles(catid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Article art:this.artlist){
			System.out.println("article---"+art.toString());
		}
		return "list";
	}


	@Override
	public String execute(){

		this.categories = cm.loadCategories();
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
		return this.ainfo;
	}

}
