package com.asso.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.asso.model.CategoryPath;
import com.asso.model.Channel;
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
	private Article art ;
	private List<Category> categories;
	private List<Channel> channels;
	private CategoryPath catpath;
	
	private File pic;
	private File addition;
	private String picContentType;
	private String additionContentType;
	private String picFileName;
	private String additionFileName;
	
	private List<Article> artlist = new ArrayList<Article>();//in Session
	private List<Article> pageartlist = new ArrayList<Article>();// in Session
	private List<Integer> delartids = new ArrayList<Integer>(); //in Session
	
	private int lastpage;
	private int nextpage;
	private int endpage;
	
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

	
	public int getLastpage() {
		return lastpage;
	}

	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	
	public CategoryPath getCatpath() {
		return catpath;
	}

	public void setCatpath(CategoryPath catpath) {
		this.catpath = catpath;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public Article getArt() {
		return art;
	}
	public void setArt(Article art) {
		this.art = art;
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
		System.out.println("Using GETArticle()...");
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
	
	public List<Article> getArtlist() {
		return artlist;
	}
	public void setArtlist(List<Article> artlist) {
		this.artlist = artlist;
	}
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
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("this.ainfo.getTitle()---"+this.ainfo.getTitle());
		System.out.println("this.ainfo.getArticle()---"+this.ainfo.getArticle());
		System.out.println("this.ainfo.getAbsinfo()---"+this.ainfo.getAbsinfo());
		System.out.println("this.ainfo.getCategoryid()---"+this.ainfo.getCategoryid());		
		System.out.println("this.ainfo.getPubdate()---"+this.ainfo.getPubdate());
		System.out.println("this.ainfo.getSrcdisplay()---"+this.ainfo.getSrcdisplay());
		System.out.println("this.ainfo.getPicurl()---"+this.ainfo.getPicurl());
		System.out.println("this.ainfo.getAdditionurl()---"+this.ainfo.getAdditionurl());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	
	public String addArticle(){		
		
		this.categories = cm.loadCategories();		
		this.setUploadfiles();
		
		this.article = new Article();
		System.out.println("-1-----------------------------------------------------------------------");
		System.out.println("--this.ainfo.getPicurl()--"+this.ainfo.getPicurl());
		System.out.println("--this.ainfo.getAdditionurl()--"+this.ainfo.getAdditionurl());
		System.out.println("-2-----------------------------------------------------------------------");
		String path_ = ServletActionContext.getServletContext().getRealPath(CONSTANT.uploadImagesPath);
		System.out.println("real path(imgs) = "+path_);
		System.out.println("-3-----------------------------------------------------------------------");
		
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
//				System.out.println("PIC URL---"+CONSTANT.uploadImagesPath+"/"+saveImg.getName());
			}else{
				if(this.ainfo.getPicurl()!=null){
					System.out.println("--this.ainfo.getPicurl()--"+this.ainfo.getPicurl());
					this.article.setPic(this.ainfo.getPicurl());
				}
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
			}else{
				if(this.ainfo.getAdditionurl()!=null){
					System.out.println("--this.ainfo.getAdditionurl()--"+this.ainfo.getAdditionurl());
					this.article.setAddition(this.ainfo.getAdditionurl());
				}
			}
		
			this.article.setTitle(this.ainfo.getTitle());		
			this.article.setArticle(this.ainfo.getArticle());
			this.article.setAbsinfo(this.ainfo.getAbsinfo());		
			this.article.setCategoryid(this.ainfo.getCategoryid());
			this.article.setSrcdisplay(this.ainfo.getSrcdisplay());
			if(this.ainfo.getPubdate()!=null && this.ainfo.getPubdate().trim().length()>=4)				
				this.article.setPubdate(this.ainfo.getPubdate());
			else
				this.article.setPubdate(CONSTANT.getNowTime());
			
//			System.out.println("this.article.toString()-----------"+this.article.toString());		
		System.out.println("--this.request.getRequestURI()--"+this.request.getRequestURI());	
		if(this.request.getParameter("articleid")!=null ){
			this.article.setId(Integer.parseInt(this.request.getParameter("articleid")));
			System.out.println("UPDATE---artid="+this.request.getParameter("articleid"));
			try {
				am.update(this.article);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return "update";
		}else{
			System.out.println("SAVE---article");
			try {
				am.add(article);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "save";
		}		
		
	}
	
	public String deleteArticle(){
		int articleid = 0;
		if(this.request.getParameter("articleid")!=null){									  
			articleid = Integer.parseInt(this.request.getParameter("articleid"));
			try {
				am.deleteArticle(articleid);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}else
			System.out.println("DATA ERROR, Pls INV...");
		
		System.out.println("Delete article, articleid="+articleid);
//		this.listArticleByCategoryId();//can't get catid
		return "delete";
	}
	
	public String listArticleByCategoryId(){
		this.session.put("artslist", null);
		int catid = this.ainfo.getCategoryid();
		if(this.request.getParameter("categoryid")!=null){
			catid = Integer.parseInt(this.request.getParameter("categoryid"));
			System.out.println("catid="+catid);
			this.listArticleByCatid(catid);
		}else{
			try {
				this.artlist = am.loadArticles();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		System.out.println("this.artlist.size()="+this.artlist.size());
		this.filterDate();
		this.sortArtlistByDate();
		this.cleanTxt();
		this.session.put("artslist", this.artlist);
		return "list";
	}
	private void listArticleByCatid(int _catid){
		try {
			this.artlist = am.loadArticles(_catid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	private void setYMD(String _date, Article _art){
		if(_date.length()>=10 && _art!=null){
			String y = _date.substring(0,4);
			if(y.equals(CONSTANT.getThisYear()))
				_art.setYear("");
			else
				_art.setYear(y);
			_art.setMonth(_date.substring(5, 7));
			_art.setDay(_date.substring(8,10));
		}else
			System.out.println("DATA ERROR, PLS INV...");
	}
	private void filterDate(){
		for(Article art:this.artlist){
			System.out.println("article---"+art.toString());
			if(art.getPubdate()!=null && art.getPubdate().trim().length()>=10){
				String date = art.getPubdate().trim();
				this.setYMD(date, art);		
				art.setPubdate(date);				
			}else{
				String date = CONSTANT.getNowTime();
				this.setYMD(date, art);
				art.setPubdate(date);
			}
		}
	}
	private void sortArtlistByDate(){
		List<String> toSort = new ArrayList<String>();
		for(Article art:this.artlist)
			toSort.add(art.getPubdate());
		List<Integer> seqs = new ArrayList<Integer>();
		seqs = CONSTANT.sortDatesDesc(toSort);
		List<Article> sortedArtlist = new ArrayList<Article>();
		for(Integer seq:seqs)
			sortedArtlist.add(this.artlist.get(seq));
		this.setArtlist(sortedArtlist);
	}
	
	private void cleanTxt(){
		for(Article art:this.artlist){
			String title = art.getTitle();
			if(title!=null && title.length()>0){
				title = CONSTANT.replaceHtml(title);
				art.setTitle(title);
			}				
			
			String abs = art.getAbsinfo();
			if(abs!=null && abs.length()>0){
				abs = CONSTANT.replaceHtml(abs);
				art.setAbsinfo(abs);
			}				
			
			String article = art.getArticle();
			if(article!=null && article.length()>0){
				article = CONSTANT.replaceHtml(article);
				if(article.length()>280)
					article=article.substring(0,280)+"......";
				art.setArticle(article);
			}				
		}
	}
	public String listArticles(){
		
		int catid = 0;
		if(this.request.getParameter("categoryid")!=null)
			catid = Integer.parseInt(this.request.getParameter("categoryid"));
		
		int page = 1;		
		if(this.request.getParameter("page")!=null)
			page = Integer.parseInt(this.request.getParameter("page"));
		int index0 = (page-1)*CONSTANT.pageArtSize+1;
		int index1 = page*CONSTANT.pageArtSize;
		if((page-1)>=1)
			this.lastpage = page-1;
		else
			this.lastpage = 1;
		
		
		if(catid==0){
			System.out.println("LOAD ALL articles");
			try {
				this.artlist = am.loadArticles();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
		}else{
			System.out.println("LOAD articles in the category="+catid);
			this.listArticleByCatid(catid);			
		}
		this.sortArtlistByDate();
		
		this.endpage = this.artlist.size()/CONSTANT.pageArtSize;
		if(this.endpage*CONSTANT.pageArtSize<this.artlist.size())
			this.endpage += 1;
		if((page+1)<=this.endpage)
			this.nextpage = page+1;
		else
			this.nextpage=page;
		System.out.println("page="+page);
		System.out.println("lastpage="+lastpage);
		System.out.println("nextpage="+nextpage);
		System.out.println("endpage="+endpage);
		List<Article> alist = new ArrayList<Article>();
		for(int i=1; i<=index1; i++){			
			if(i>this.artlist.size())
				break;
			else{
				if(i<index0)
					alist.add(null);	
				else
					alist.add(this.artlist.get(i-1));
			}
		}		
		if(alist!=null)
			this.setArtlist(alist);
		System.out.println("alist size="+alist.size());
		for(Article art:this.artlist){			
			String date = CONSTANT.getNowTime();
			if(art!=null){				
				if(art.getPubdate()!=null && art.getPubdate().trim().length()>=10)
					date = art.getPubdate();				
				art.setPubdate(date);
			}						
		}		
		System.out.println("this.artlist size="+this.artlist.size());
		return "list";
	}

	
	@Override
	public String execute(){
		this.categories = cm.loadCategories();
		this.channels = cm.loadChannels();
		String artID = this.request.getParameter("articleid");	
		System.out.println(this.request.getRealPath(".")); 
		if(artID!=null && artID.length()>0){
			int aid = Integer.parseInt(artID);
			List<Article> artl = new ArrayList<Article>();
			try {
				artl = am.loadArticle(aid);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(artl.size()==1){		
				this.art = new Article(); 
				this.art.setId(Integer.parseInt(artID));
				this.setArt(artl.get(0));			
//				System.out.println("__1___________________"+this.art.getTitle());
//				System.out.println("__2___________________"+this.art.getPubdate());
//				System.out.println("__3___________________"+this.art.getAbsinfo());
//				System.out.println("__4___________________"+this.art.getArticle());
//				System.out.println("__5___________________"+this.art.getCategoryid());
//				System.out.println("__6___________________"+this.art.getPic());
//				System.out.println("__7___________________"+this.art.getAddition());
//				for(Channel ch:this.channels)
//					System.out.println("chid---"+ch.getId()+", ch name---"+ch.getChannel());
				
				this.catpath = new CategoryPath();
				this.catpath.setCatId(this.art.getCategoryid());
				for(Category cat : this.categories){
					if(cat.getId()==this.art.getCategoryid()){
						this.catpath.setCatName(cat.getCategory());
						this.catpath.setChId(cat.getChannelid());
						for(Channel ch:this.channels){
							if(ch.getId()==cat.getChannelid()){
								this.catpath.setChName(ch.getChannel());
							}
						}
						if(cat.getParentid()>0){
							this.catpath.setParentCatId(cat.getParentid());
							for(Category c : this.categories){
								if(c.getId()==cat.getParentid()){
									this.catpath.setParentCatName(c.getCategory());
								}
							}
						}
					}
				}
				System.out.println("---catpath---"+this.catpath.toString());
				
			}
			else
				System.out.println("DATA ERROR, PLS INV...");
			
		}
		
			
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
