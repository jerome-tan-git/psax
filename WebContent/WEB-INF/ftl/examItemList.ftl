<#--  
<#assign s=JspTaglibs["struts-tags.tld"]>  
 -->  
 <meta http-equiv="Content-type" content="text/html; charset=utf-8">
<html>  
    <body>  
    ��ӭ<@s.property value="username"/>��¼��   
    <br>
    <#assign colors = ["red", "green", "blue"]>
    <#assign b = "blue">
${colors?seq_index_of("blue")}:${colors?seq_index_of(b)}

<br>

<#if colors?seq_index_of(b)!=-1>

	<#assign colors = ["red", "green", "blue"]>
	${colors?seq_index_of("white")}<br>
</#if>

<#assign xy = ["123","234","345"]>
<#assign a = 234>
<#if xy?seq_index_of(a?c)!=-1>
	${a}<br>
</#if>

    <#assign t1 = "56">
    <#assign t2 = 56>
    <#if (t1?number)==t2>
		test pass!<br>
	</#if>



    </body>  
</html>  