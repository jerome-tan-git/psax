<%@ page language="java" 
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>×¢²á</title>
</head>
<body>


  <table border="1">
     <s:iterator value="itemlistf" id="item" status="of">
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
  
  <br><br><br>
<!-- 
  <table border="1">
     <s:iterator value="itemlistf" id="item">
          <s:iterator value="#item" id="map">
    		   <s:property value='key'/><br>
    		   <s:property value='key.id'/><br>
    		   <s:property value='key.examid'/><br>
    		   <s:property value='key.question'/><br>
    		   <s:property value='key.category'/><br>
    		   <s:iterator value='value' id="ref">
            	    <s:property value="#ref"/><br>
            	    <s:property value="#ref.id"/><br>
            	    <s:property value="#ref.itemid"/><br>
            	    <s:property value="#ref.ref"/><br>
            	    <s:property value="#ref.istrue"/><br>
    		   </s:iterator>                        
          </s:iterator><br>
      </s:iterator>
  </table>
 -->  
  <br><br><br>	
  
  <br><br><br>	
  <a href="./switchexampage.action?currentpage=1">1</a>
  <a href="./switchexampage.action?currentpage=2">2</a>
  <a href="./switchexampage.action?currentpage=3">3</a>
  <a href="./switchexampage.action?currentpage=4">4</a>
  <a href="./switchexampage.action?currentpage=5">5</a>
  
  
	 <form method="post" action="userlogin.action">
		 <input type="submit" value="±£´æ">
	 </form>
	
	
</body>
</html>