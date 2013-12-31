package com.asso.action;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import freemarker.template.*;

public class HelloServlet  extends HttpServlet{
	
	 private Configuration cfg; 
	    public void init(){
	        //��ʼ��FreeMarker����
	        //����һ��Configurationʵ��
	        cfg = new Configuration();
	        //����FreeMarker��ģ���ļ�λ��
	        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/tmpl");
	    }
	    
	    public void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{        
	        //��������ģ��
	        Map root = new HashMap();
	        root.put("message", "Hello FreeMarker!");     
	        //ȡ��ģ���ļ�
	        Template t = cfg.getTemplate("chemicalManageRecord.ftl");        
	        // ��ʼ׼���������
	        // - ʹ��ģ���ļ���charset��Ϊ��ҳ���charset
	        // - ʹ��text/html MIME-type
	        response.setContentType("text/html; charset=" + t.getEncoding());
	        Writer out = response.getWriter();
	        
	        //�ϲ�����ģ�ͺ�ģ�棬������������out��
	        try{
	            t.process(root, out);
	        } 
	   catch (TemplateException e){
	            throw new ServletException("����Templateģ���г��ִ���", e);
	        }
	    }

}
