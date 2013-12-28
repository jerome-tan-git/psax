package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class PicURLparse  implements TemplateMethodModel {

	@Override
	public Object exec(List arg0) throws TemplateModelException {
		String str = "";
		if(arg0!=null && arg0.size()>=1){			
			try {
				String path = (String)arg0.get(0);
				if(path.indexOf("_")!=-1)
				path = path.substring(path.indexOf("_")+1);
				str = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return str;
		
	}

}
