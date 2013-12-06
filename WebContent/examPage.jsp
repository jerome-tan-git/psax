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

<%	int pi = 0;
	if(request.getParameter("currentpage")!=null)
		pi=Integer.parseInt( request.getParameter("currentpage") );
	if(pi==0)
		pi = (Integer)request.getSession().getAttribute("pi") ;
	System.out.println("Current Page Number="+pi);
	int lastPi = 0;
	if(pi>1)
		lastPi = pi-1;
	
	
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
	
	request.getSession().setAttribute("pi",pi);
	request.getSession().setAttribute("itemlistf",itemlistf);
	
	request.setAttribute("itemlistf", itemlistf);
	request.setAttribute("pi",pi );
	request.setAttribute("index0",index0 );
	int c1hasTitle = 1;//ÊÇ·ÇÌâ¿ªÊ¼ÐòºÅ
	int c2hasTitle = 6;//Ñ¡ÔñÌâ¿ªÊ¼ÐòºÅ
	int c3hasTitle = 11;//¶àÑ¡Ìâ¿ªÊ¼ÐòºÅ
	request.setAttribute("c1hasTitle", c1hasTitle);
	request.setAttribute("c2hasTitle", c2hasTitle);
	request.setAttribute("c3hasTitle", c3hasTitle);
%>


 <!--  
 <form method="post" action="examsubmit.action">
     <s:iterator value="#request.itemlistf" id="item" status="of">
          <s:iterator value="#item" id="map" >
          		<s:if test="%{#of.count==#request.c1hasTitle}">
    		    	<br/>ÊÇ·ÇÌâ£º
  	    		</s:if>  
	    		<s:elseif test="%{#of.count==#request.c2hasTitle}">
	    			<br/>µ¥Ñ¡Ìâ£º
	    		</s:elseif>
	    		<s:elseif test="%{#of.count==#request.c3hasTitle}">
	    			<br/>¶àÑ¡Ìâ£º
	    		</s:elseif>
          
          	 <s:if test="%{#of.count>=#request.index0}">
          		<br/>
    		    <s:property value="#of.count"/>
    		    <s:property value='key.question'/><br/>    		   
    		    
    		    <s:if test="%{key.category==1}">    	
    		    	<s:iterator value='value' id="ref" status="off">
    		    	<input type="radio" checked="" name='ANS_<s:property value="#of.count"/>' value='1_<s:property value="#ref.id"/>' />ÊÇ<br/>
	    		    <input type="radio" name='ANS_<s:property value="#of.count"/>' value='0_<s:property value="#ref.id"/>' />·ñ<br/>
	    		    </s:iterator>	    	
	    		</s:if>   
				<s:elseif test="%{key.category==2}">					
	    			<s:iterator value='value' id="ref" status="off">
    		     	<input type="radio" name='ANS_<s:property value="#of.count"/>' value='<s:property value="#ref.id"/>'/>
    		     		<s:property value="#ref.ref"/><br/>	               
	    		   	</s:iterator>
	    		</s:elseif>
	    		<s:else>		    		
					<s:iterator value='value' id="ref" status="off">
	            	    <input type="checkbox" name='ANS_<s:property value="#of.count"/>' value='<s:property value="#ref.id"/>'/>
	            	    <s:property value="#ref.ref"/><br/>	            	    	            	 
	    		   	</s:iterator>
				</s:else>
				
    		 </s:if>                        
          </s:iterator>
      </s:iterator>
      
      <br/><br/><br/>
      	<input type="submit" value="±£´æ"/>
  		<input type="reset" value="ÖØÐ´"/>
	
   </form>
 -->	
 
 <s:form action="examsubmit">   
 	<s:iterator value="#request.itemlistf" id="item" status="of">
          <s:iterator value="#item" id="map" >
          		<s:if test="%{#of.count==#request.c1hasTitle}">
    		    	<br/>ÊÇ·ÇÌâ£º
  	    		</s:if>  
	    		<s:elseif test="%{#of.count==#request.c2hasTitle}">
	    			<br/>µ¥Ñ¡Ìâ£º
	    		</s:elseif>
	    		<s:elseif test="%{#of.count==#request.c3hasTitle}">
	    			<br/>¶àÑ¡Ìâ£º
	    		</s:elseif>
          
          	 <s:if test="%{#of.count>=#request.index0}">
          		<br/>
    		    <s:property value="#of.count"/>
    		    <s:property value='key.question'/><br/>    		   
    		    
    		    
    		    <s:if test="%{key.category==1}">    	
    		    	<s:iterator value='value' id="ref" status="off">
    		    	<input type="radio" checked="" name='ANS_<s:property value="#of.count"/>' value='1_<s:property value="#ref.id"/>' />ÊÇ<br/>
	    		    <input type="radio" name='ANS_<s:property value="#of.count"/>' value='0_<s:property value="#ref.id"/>' />·ñ<br/>
	    		    </s:iterator>	    	
	    		</s:if>   
				<s:elseif test="%{key.category==2}">					
	    			<s:iterator value='value' id="ref" status="off">
    		     	<input type="radio" name='ANS_<s:property value="#of.count"/>' value='<s:property value="#ref.id"/>'/>
    		     		<s:property value="#ref.ref"/><br/>	               
	    		   	</s:iterator>
	    		</s:elseif>
	    		<s:else>		    		
					<s:iterator value='value' id="ref" status="off">
	            	    <input type="checkbox" name='ANS_<s:property value="#of.count"/>' value='<s:property value="#ref.id"/>'/>
	            	    <s:property value="#ref.ref"/><br/>	            	    	            	 
	    		   	</s:iterator>
				</s:else>
				
    		 </s:if>                        
          </s:iterator>
      </s:iterator>
      
      <br/><br/><br/><input type="submit" value="±£´æ"/><input type="reset" value="ÖØÐ´"/> 
 </s:form> 
 
 
   <br/><br/><br/>
   current page---<s:property value="#request.pi"/><br/>
   current page---<s:property value="#session.pi"/><br/>
   page index0----<s:property value="#request.index0"/><br/>  
   score----------<s:property value="#session.score"/><br/>
   progress-------<s:property value="#session.answerProgress"/><br/>
   


	  &nbsp;&nbsp;
  	  <a href="./switchexampage.action?currentpage=1">1</a>
	  <a href="./switchexampage.action?currentpage=2">2</a>
	  <a href="./switchexampage.action?currentpage=3">3</a>
	  <a href="./switchexampage.action?currentpage=4">4</a>
	  <a href="./switchexampage.action?currentpage=5">5</a>
	  &nbsp;&nbsp;

  	
  
</body>
</html>