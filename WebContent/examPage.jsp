<%@ page language="java" import="java.util.*, java.io.*, com.asso.model.*" 
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>×¢²á</title>
</head>
<body>

<%	int pi = Integer.parseInt( request.getParameter("currentpage") );
	System.out.println("Current Page Number="+pi);
	System.out.println("itemlistf="+request.getSession().getAttribute("elist").toString());
	System.out.println("CONSTANT.pageSize="+3);////3£¿£¿£¿£¿´«Öµ£¿
	
	List<HashMap<ExamItem,List<ExamRef>>> itemlistf = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
	List<HashMap<ExamItem,List<ExamRef>>> sessionlist = (List<HashMap<ExamItem,List<ExamRef>>>)request.getSession().getAttribute("elist");
	int index0 = (pi-1)*3;////3£¿£¿£¿£¿
	for(int i=0; i<index0+3; i++){		
		if(i>=index0)
			itemlistf.add(sessionlist.get(i));
		else
			//itemlistf.add(new HashMap<ExamItem,List<ExamRef>>());
			itemlistf.add(null);
	}
	System.out.println("New itemlistf size="+itemlistf.size());
	request.setAttribute("itemlistf", itemlistf);
	request.setAttribute("pi",pi );
	request.setAttribute("index0",index0 );
%>
 <!-- 
  <table border="1">
     <s:iterator value="#request.itemlistf" id="item" status="of">
          <s:iterator value="#item" id="map" >
    		   <s:property value="#of.count"/>.
    		   <s:property value='key.question'/><br>    		   
    		   <s:iterator value='value' id="ref">
            	    <s:property value="#ref.ref"/>
            	    <s:property value="#ref.istrue"/><br>
    		   </s:iterator>                        
          </s:iterator><br>
      </s:iterator>
  </table>
  -->
  <table border="1">
     <s:iterator value="#request.itemlistf" id="item" status="of">
          <s:iterator value="#item" id="map" >
          	<s:if test="%{#of.count>=#request.index0}">
          		<br>
    		   <s:property value="#of.count"/>.
    		   <s:property value='key.question'/><br>    		   
    		   <s:iterator value='value' id="ref">
            	    <s:property value="#ref.ref"/>
            	    <s:property value="#ref.istrue"/><br>
    		   </s:iterator>
    		 </s:if>                        
          </s:iterator>
      </s:iterator>
  </table>
   <br><br><br>
   current page---<s:property value="#request.pi"/><br>
   page index0----<s:property value="#request.index0"/><br>
   page i0----<s:property value="#i0"/><br>
 	 
	  <form method="post" action="userlogin.action">		 
		 <input type="reset" value="ÖØÐ´"/>
		 <input type="submit" value="Ìá½»">
	  </form>  
	  &nbsp;&nbsp;
  	  <a href="./switchexampage.action?currentpage=1">1</a>
	  <a href="./switchexampage.action?currentpage=2">2</a>
	  <a href="./switchexampage.action?currentpage=3">3</a>
	  <a href="./switchexampage.action?currentpage=4">4</a>
	  <a href="./switchexampage.action?currentpage=5">5</a>
	  &nbsp;&nbsp;
  	  <form method="post" action="userlogin.action">
		 <input type="submit" value="½áÊø¿¼ÊÔ£¬²é¿´·ÖÊý.">		 
	  </form>
  	
  <br><br><br>	
  
</body>
</html>