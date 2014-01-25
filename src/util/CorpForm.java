package util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.asso.manager.DocManager;
import com.asso.model.Doc;
import com.asso.model.Message;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class CorpForm implements TemplateMethodModel {
	
	private DocManager dm;
	
	public CorpForm(){
		dm = (DocManager) SpringFactory.getObject("docManager");
	}
	public DocManager getDm() {
		return dm;
	}
	@Resource(name="articleManager")
	public void setDm(DocManager dm) {
		this.dm = dm;
	}

	@Override
	public Object exec(List arg0) throws TemplateModelException {
		int hasItsCorpInfoFrom = 0;//false
		if (arg0 != null && arg0.size() >= 1) {
			String userid = (String) arg0.get(0);
			if(userid!=null && userid.length()>0){
				int uid = Integer.parseInt(userid);
				List<Doc> docs = new ArrayList<Doc>();
					
				try {
					docs = dm.loadDocsByUser(uid);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
				if(docs.size()>0)
					hasItsCorpInfoFrom = 1;//true
			}
		}
		System.out.println("CorpForm-------hasItsCorpInfoFrom"+hasItsCorpInfoFrom);
		return hasItsCorpInfoFrom;

	}

}
