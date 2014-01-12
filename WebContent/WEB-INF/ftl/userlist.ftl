<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Innovate Responsive HTML Website Template</title>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="./css/backend.css" />
		<link rel="stylesheet" type="text/css" href="./css/docs.css" />
		
		
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
		<script src="./js/ckeditor/ckeditor.js"></script>
		<script src="./js/ckeditor/adapters/jquery.js"></script>
		<script src="./js/backend.js"></script>
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
		<script>
		var selectIndex = -1;
		CKEDITOR.disableAutoInline = true;
		CKEDITOR.editorConfig = function( config ) {
			// Define changes to default configuration here. For example:
			// config.language = 'fr';
			// config.uiColor = '#AADC6E';
			config.toolbar =
			[
			    ['Bold','Italic','Underline','Strike','-','EqnEditor'],
			    ['TextColor','BGColor'],
			 
			];

		    config.height = 80;
		};
		$( document ).ready( function() {
			$( '#editor' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
			//$( '#editor1' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
			$('.date').datepicker();
			
		} );
		</script>
	</head>
	<body>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">新建/编辑用户</h4>
		      </div>
		      <form name="userform" method="post" action="adduser.action">
		      <div class="modal-body clearfix">
		        <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label" style="padding-top: 10px;">用户名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control"placeholder="用户名" id="userNameInput" name="username"/>
			    </div>
			  </div>
			  <div>&nbsp;</div>
			  <div class="form-group" >
			    <label for="inputPassword3" class="col-sm-2 control-label" style="padding-top: 10px;">密码</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" placeholder="密码" id="userPasswordInput" name="password"/>
			    </div>
			  </div>
		        <input type="hidden" id="userIDInput" name="userid"/>
		        
		      </div>
		      <div class="modal-footer">
		        <button type="submit" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="submit" class="btn btn-primary">保存</button>
		      </div>
		      </form>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="navbar-fixed-top" role="banner" style="background-color: #004A8F; width: 100%;">
			<div class="container">
				<div class="navbar-header">
					<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="#" class="navbar-brand" style="color:#fff">&nbsp;</a>
				</div>

			</div>
		</div>
		<div style="margin-top: 50px">
			<div class="row" style="width: 100%;">
				<#include "../commons/marginmenu.ftl">
				<div class="col-md-8">
					<div style="padding-right: 28px;margin-bottom: 23px;">
						<span class="glyphicon glyphicon-plus pull-right hand deleteExam" onclick="showUserDialog('','','')">新建用户</span>
					</div>
					<div class="col-md-12">
					<form method="post" action="sendmessage.action">
					<#if userslist?exists>
						<#list userslist as user>
							<div class="panel panel-default exam_container">
								<div class="panel-heading clearfix">						  	
								  	<div class="exam_title_list pull-left ">
								  	<span><input type="checkbox" name="userids" value="${user.id}"/></span>${user.username}</div>
								  	<a href="./deleteuser.action?userid=${user.id}">
								  		<span class="glyphicon glyphicon-remove pull-right hand deleteExam" ></span></a>
								  	<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
								  	<!--<a href="./manageruser.action?userid=${user.id}">-->
								  		<span class="glyphicon glyphicon-pencil pull-right hand deleteExam" onclick="showUserDialog('${user.username}','${user.password}','${user.id}')"></span>
								  	<!--</a>-->								  		
								  	<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
								</div>
							</div>
						</#list>
					</#if>
						
												
																
						<ul class="pager">
						  <li class="previous"><a class="exam_pager" href="./manageruser.action?page=1"><span class="glyphicon glyphicon-fast-backward"></span> 首页</a></li>&nbsp;
						  <li class="previous"><a class="exam_pager" href="./manageruser.action?page=${lastpage}"><span class="glyphicon glyphicon-backward"></span> 前一页</a></li>&nbsp;
						  <li class="next"><a class="exam_pager" href="./manageruser.action?page=${endpage}">末页 <span class="glyphicon glyphicon-fast-forward"></span> </a></li>&nbsp;
						  <li class="next"><a class="exam_pager" href="./manageruser.action?page=${nextpage}">后一页 <span class="glyphicon glyphicon-forward"></span> </a></li>&nbsp;
						  
						</ul>
						
						
						<div class="panel panel-default exam_container">
							<div class="panel-heading clearfix">
						  		<div class="exam_title_list pull-left ">站内信：
						  			<!--<textarea> </textarea>-->
						  			<input type="textarea" name="message"></input>
						  			<input type="submit" value="发送"/>
						  		</div>
						  		<a href="#"><span class="glyphicon glyphicon-remove pull-right hand deleteExam" ></span></a>
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
						  		<a href="#"><span class="glyphicon glyphicon-pencil pull-right hand deleteExam" ></span></a>
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>							  	
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>


		
	</body>

</html>
