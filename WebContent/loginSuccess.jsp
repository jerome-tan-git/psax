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
¹§Ï²Äú£¬µÇÂ½³É¹¦£¡
	<!-- <s:iterator value="users">  -->
		<s:property value="username"/>
	<!-- </s:iterator> -->
	
	<br>
	<a href="checksession.action">ä¯ÀÀÒ³Ãæ</a>
	<br><br>
	<a href="inputExam.jsp?examsave=0">¹ÜÀíÔ±Ò³Ãæ</a>
	
	
	 <form method="post" action="managerexamcontext.action">
		 <input type="submit" value="¹ÜÀíÔ±Ò³Ãæ">
	 </form>
	 <form method="post" action="userlogin.action">
		 <input type="submit" value="É¾³ý">
	 </form>
	

	<br>
	<a href="beginexam.action">¿ªÊ¼¿¼ÊÔ</a>
	<br>
	<form method="post" action="userlogout.action">
		 <input type="submit" value="µÇ³ö">
	</form>
</body>
</html>