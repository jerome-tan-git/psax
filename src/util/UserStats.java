package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.asso.manager.ArticleManager;
import com.asso.manager.FormManager;
import com.asso.model.Message;
import com.asso.model.User;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class UserStats implements TemplateMethodModel {
	
	private ArticleManager am;
	
	public UserStats(){
		am = (ArticleManager) SpringFactory.getObject("articleManager");
	}
	public ArticleManager getAm() {
		return am;
	}
	@Resource(name="articleManager")
	public void setAm(ArticleManager am) {
		this.am = am;
	}

	@Override
	public Object exec(List arg0) throws TemplateModelException {
		int unreadInfoPieces = 0;
		if (arg0 != null && arg0.size() >= 1) {
			String userid = (String) arg0.get(0);
			if(userid!=null && userid.length()>0){
				int uid = Integer.parseInt(userid);
				List<Message> ms = new ArrayList<Message>();
					
				try {
					ms = am.loadMessages(uid);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
				List<Message> mslist = new ArrayList<Message>();
				for(Message m:ms){
					if(m.getIsread()==0)
						mslist.add(m);
				}
				unreadInfoPieces = mslist.size();
			}
		}
		return unreadInfoPieces;

	}

}
