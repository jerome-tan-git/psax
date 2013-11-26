<%@ page language="java" 
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>¿¼Ìâ±à¼­</title>
</head>
<body>
	<!-- <s:iterator value="users">  -->
	User:	<s:property value="username"/>
	<!-- </s:iterator> -->
	<form method="post" action="userlogout.action">
		 <input type="submit" value="µÇ³ö">
	</form>
	
	<br><br>	
	Ñ¡ÖÐ¿¼Ìâ£º	
	 <br>
	 <form method="post" action="userlogin.action">
		 ÎÊÌâ£º<input type="password" name="password" ><br> 
		 ÀàÐÍ£º<input type="text" name="username" ><br>
		´ð°¸Ñ¡Ïî£º
		 ²Î¿¼´ð°¸£º<input type="password" name="password1" ><br>
		 <input type="submit" value="¸üÐÂ">
	 </form>
	 <form method="post" action="userlogin.action">
		 <input type="submit" value="É¾³ý">
	 </form>	
	<br>	
	
</body>
</html>