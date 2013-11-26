<%@ page language="java" import="java.util.*" 
	
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"
	+request.getServerPort()+path+"/";
%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ÓÃ»§µÇÂ½×¢²á</title>
</head>
<body>
µÇÂ½<br>
	 <form method="post" action="userlogin.action">
	 ÓÃ»§Ãû£º<input type="text" name="username" ><br>
	 ÃÜÂë£º<input type="password" name="password" ><br>
	 È·ÈÏÃÜÂë£º<input type="password" name="password1" ><br>
	 <input type="submit" value="Ìá½»">
	</form>
×¢²á<br>
	 <form method="post" action="userbuilt.action">
	 ÓÃ»§Ãû£º<input type="text" name="username" ><br>
	 ÃÜÂë£º<input type="password" name="password" ><br>
	 È·ÈÏÃÜÂë£º<input type="password" name="password1" ><br>
	 <input type="submit" value="Ìá½»">
	</form>
</body>
</html>