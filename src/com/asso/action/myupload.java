package com.asso.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.CONSTANT;

/**
 * Servlet implementation class myupload
 */
@WebServlet("/myupload")
public class myupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myupload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("aaaa1");
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success=false;
		Map map = new HashMap();
        request.setCharacterEncoding("utf-8");
        String imagePath = " ";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        PrintWriter out =  response.getWriter();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {  
            List items = upload.parseRequest(request);  
            Iterator itr = items.iterator();  
            while (itr.hasNext()) {  
                FileItem item = (FileItem) itr.next();  
                if (item.isFormField()) {  

                } else {  
                    if (item.getName() != null && !item.getName().equals("")) {  
                    	String uploadFileName = item.getName();
                    	String fileN = uploadFileName;
                		String fileSuffix = "";
                    	if (uploadFileName.indexOf('.')!=-1)
                		{
                			fileN = uploadFileName.substring(0,uploadFileName.lastIndexOf('.'));
                			fileSuffix = uploadFileName.substring(uploadFileName.lastIndexOf('.'));
                		}
                    	
                    	
                        // item.getName()�����ϴ��ļ��ڿͻ��˵�����·�����  
                        String newFileName = new Random().nextInt(1000) + "_" + CONSTANT.encodeStr(fileN)+fileSuffix;
                        
                        File tempFile = new File(newFileName);
                        imagePath = "./ckimages/"+ newFileName;
                        String savePath = this.getServletContext().getRealPath("./ckimages");
                        System.out.println(savePath);
                        if(!new File(savePath).exists())
                        {
                        	new File(savePath).mkdirs(); 
                        }
                        File file = new File(savePath, tempFile.getName());  
                        item.write(file);  
                        success = true;
                        
                    } 
                }  
            }  
        } catch (FileUploadException e) {  
            e.printStackTrace();
//            success = false;
        } catch (Exception e) {  
            e.printStackTrace();  
//            success = false;
            //out.write("上传失败");
        }  
        finally
        {
        	if(success)
        	{
        		out.write("{\"type\":\"success\",\"path\":\""+imagePath+"\"}");
        	}
        	else
        	{
        		out.write("{\"type\":\"fail\",\"path\":\""+imagePath+"\"}");
        	}
        	out.flush();
        }
            
		// TODO Auto-generated method stub
	}

}
