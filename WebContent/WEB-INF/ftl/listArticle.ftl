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
				<#include "../commons/marginmenu.ftl">
				
				<div class="col-md-8">
				<div class="page-header" style="margin-top: 20px;margin-left: 20px;border-bottom: 1px solid #D6D6D6;position: relative;">
				  <h1><small>${cateogry.category?default("")}</small></h1>
				  <div style="float: right;position: absolute;right: 20px;top: 10px;font-size: 13pt;color: #333;">
				  	<a href="./managerArt.action"><span class="glyphicon glyphicon-plus"></span></a> 新建
				  </div>
				</div>
					<div class="col-md-12">
					<#if artlist?exists>
						<#list artlist as article>
						<#if article?exists>
						<div class="panel panel-default exam_container">
						  <div class="panel-heading clearfix">
						  		<div class="exam_title_list pull-left ">${article.pubdate}&nbsp;&nbsp;&nbsp;${article.title}</div>
						  		<a href="./deleteArt.action?articleid=${article.id}"><span class="glyphicon glyphicon-remove pull-right hand deleteExam" ></span></a>
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
						  		<a href="./managerArt.action?articleid=${article.id}"><span class="glyphicon glyphicon-pencil pull-right hand deleteExam" ></span></a>
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
							  	
							</div>
						</div>
						</#if>
						</#list>
																	
					</#if>
														
						<ul class="pager">
						  <li class="previous">						  	
						  	<#if (curpage<2)>
						  		<a class="exam_pager disabled" href="#">
						  	<#else>
						  		<a class="exam_pager" href="./bklistArt.action?page=1&categoryid=${cateogry.id?default("")}">						  		
						  	</#if>						  	
						  	<span class="glyphicon glyphicon-fast-backward"></span> 首页</a>
						  </li>&nbsp;
						  <li class="previous">
						  	<#if (curpage<2)>
						  		<a class="exam_pager disabled" href="#">
						  	<#else>
							  	<a class="exam_pager" href="./bklistArt.action?page=${lastpage}&categoryid=${cateogry.id?default("")}">							  	
						  	</#if>	
						  	<span class="glyphicon glyphicon-backward"></span> 前一页</a>
						  </li>&nbsp;
						  <li class="next">
						  	<#if (curpage==endpage)>
						  	<a class="exam_pager disabled" href="#">
						  	<#else>
						  	<a class="exam_pager" href="./bklistArt.action?page=${endpage}&categoryid=${cateogry.id?default("")}">
						  	</#if>
						  	末页<span class="glyphicon glyphicon-fast-forward"></span> </a>
						  	</li>&nbsp;
						  <li class="next">
						  	<#if (curpage>(endpage-1))>
						  	<a class="exam_pager disabled" href="#">
						  	<#else>
						  	<a class="exam_pager" href="./bklistArt.action?page=${nextpage}&categoryid=${cateogry.id?default("")}">
						  	</#if>
						  	后一页<span class="glyphicon glyphicon-forward"></span> </a>
						  </li>&nbsp;						  
						</ul>
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
