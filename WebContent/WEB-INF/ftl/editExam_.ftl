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
			
		} );
		</script>
	</head>
	<body>

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
				<div class="col-md-3">
					<div class="bs-sidebar hidden-print affix" style="margin-top: 20px;">
						<ul class="nav bs-sidenav">

							<li class="active">
								<a href="#">Exam</a>

							</li>
							<li>
								<a href="./AddArticle.html">Article</a>
							</li>
							<li>
								<a href="#modals">workflow</a>

							</li>
							<li>
								<a href="#dropdowns">Dropdown</a>

							</li>
							<li>
								<a href="#scrollspy">Scrollspy</a>

							</li>
							<li>
								<a href="#tabs">Tab</a>

							</li>
							<li>
								<a href="#tooltips">Tooltip</a>

							</li>
							<li>
								<a href="#popovers">Popover</a>

							</li>
							<li>
								<a href="#alerts">Alert</a>
								<ul class="nav">
									<li>
										<a href="#alerts-examples">Examples</a>
									</li>
									<li>
										<a href="#alerts-usage">Usage</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>

				</div>
				<div class="col-md-8">
					<div class="panel panel-info">
					  <div class="panel-heading">
					    <h3 class="panel-title">Exam</h3>
					  </div>
					  <div class="panel-body">
					    <!--<form class="form-horizontal" role="form" method="post" action="newitem1.action">-->
					    <form class="form-horizontal" method="post" action="newitem1.action">
					    	<textarea id="editor"> </textarea>
					    	 <div class="form-group">
						    <div class="bt_group">
						      <div class="btn-group pull-right">
								  <button type="button" class="btn btn-primary" id="changeTitlebt" onclick="read_editor_title()">Modify exam</button>
								  <button type="button" class="btn btn-primary" id="changeOptionbt" onclick="update_data()">Modify options</button>
								  <button type="button" class="btn btn-primary" id="addOptionbt" onclick="read_editor()">Add option</button>
								</div>
							</div>						    
						  </div>
						  <div class="form-group">
						  	<label  class="col-sm-12">类型</label>
						    <div class="col-sm-12">
								
								<label class="radio-inline">
								  <input type="radio" id="inlineCheckbox1" class="exam_type" value="2" name="category" checked> 单选题
								</label>
								<label class="radio-inline">
								  <input type="radio" id="inlineCheckbox2" class="exam_type" value="3" name="category"> 复选题
								  </label>
								  <label class="radio-inline">
								  <input type="radio" id="inlineCheckbox3" class="exam_type" value="1" name="category"> 是非题
								</label>
								</div>
						  </div>
						  <div class="form-group">
						  	<label  class="col-sm-12">考试</label>
						    <div class="col-sm-12">
						    <#if Session.exams?exists>
						    	<#list Session.exams as exam>
								<label class="radio-inline">
								  <input type="radio" id="inlineCheckbox_${exam.id}" value="${exam.id}" name="examid">${exam.name}
								</label>								
							    </#list>
							</#if>
							</div>
						  </div>
						  <div class="form-group exam_title_input">
						  	
						    <label  class="col-sm-12 ">题目</label>
						    <div class="col-sm-12">
						      <div class="panel panel-default">
								  <div class="panel-body">
								   请输入题目。。。
								  </div>
								</div>
						    </div>
						  </div>
						  <div class="form-group all_options">
						  	
						    <label  class="col-sm-12">选项</label>


						  </div>


						  
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						    	<!--
						    	<button type="button" class="btn btn-primary pull-right">
									  <span class="glyphicon glyphicon-floppy-disk"></span> 保存
								</button>
								-->
								<input type="submit" value="保存" />
						    </div>
						  </div>
						</form>
					  </div>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">Modal title</h4>
					</div>
					<div class="modal-body">
						...
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							Close
						</button>
						<button type="button" class="btn btn-primary">
							Save changes
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>

</html>