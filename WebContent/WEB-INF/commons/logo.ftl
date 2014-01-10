 <#--
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
 -->
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
	</head>
	<body>
 			<div class="logo"> 		
 			<div style="margin-top: 7px;float: right;margin-right: 15px;">	
 				<#if Session.user_?exists>
 					<a href="./userlogout.action">登出</a>
 				<#else>
 					<a href="./gologin.action">登录</a>
 				</#if>
 				<!--<a href="./usercenter.action">用户中心</a>-->
 				<a href="./page.action?categoryid=1">用户中心</a>
 			</div>
 				
		    	<div class="logo_container">
		    		<a href="./page.action?categoryid=0"><img src="./images/logo_new.png" alt="img" /></a>
		    	</div>
		    	<div class="logo_search">
		    		<div class="search_box">
					    <form action="#" method="get">
					    <div style="width:210px" class="clearfix">
					    <div style="width:150px; float: left">	
					    	<input id="error_search" type="text" name="s">
					    </div>
				    	<input  type="submit" value="" style="float: right">
					    </div>
					    </form>
					</div>
				</div>
		    </div>
	</body>    
</html>