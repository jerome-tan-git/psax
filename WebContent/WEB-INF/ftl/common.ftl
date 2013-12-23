<#assign basePath = request.contextPath />
<#macro htmlHead title charset="utf-8" lang="zh-CN">
 <html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=${charset}" />
    <meta http-equiv="Content-Language" content="${lang}"/>
    <title>${title}</title>
    <#nested>
 </head>
 </#macro>
 
 <#macro htmlBody>
 <body>
    <#nested>
 </body>
 </html>
 </#macro>
 
 
 <#macro htmlBody>
 
 <body>
 
 
    
 ${rand(1)}
    <#nested>
 </body>
 </html>
 </#macro>