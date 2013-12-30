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
					<div class="col-md-12">
					<#if itemlistSeq?exists>
						<#list itemlistSeq as map>
							<#assign keys=map?keys>							
							<#list keys as key>
								<#assign seq=refQlist?seq_index_of(key)+1>
							<div class="panel panel-default exam_container exam_block">
								  <div class="panel-heading clearfix">
									  	<div class="exam_title_list pull-left ">									  		
									  		${seq}. ${key}
									  	</div>
									  	<div class="exam_list_button">
										<#if itemIds4Ilist?exists>
								  			<#list itemIds4Ilist as id>
									  			<#if (id_index+1)=seq>		  					
									  				<a href="./delitem.action?itemid=${id}&examid=${examid}&page=${thispage}">
								  				</#if>
								  			</#list>
								  		<#else>
								  				<a href="#">
								  		</#if>
								  		<span class="glyphicon glyphicon-remove pull-right hand deleteExam" ></span></a>
								  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
								  		
								  		<#if itemIds4Ilist?exists>
								  			<#list itemIds4Ilist as id>
									  			<#if (id_index+1)==seq>									  					
									  				<a href="./managerexam.action?itemid=${id}">
								  				</#if>
								  			</#list>
								  		<#else>
								  				<a href="#">
								  		</#if>
								  		<span class="glyphicon glyphicon-pencil pull-right hand deleteExam" ></span></a>
								  		
								  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>
									  	<span class="glyphicon glyphicon-chevron-down pull-right hand expandOption"></span>
									  	</div>
			 						  </div>
									  <div class="options">
									  	<div class="option_list">
									  		<ol>
									  		<#assign refs=map[key]>
											<#list refs as ref>
										  		<li><div>${ref.ref}</div></li>
											</#list>
									  		</ol>
									  	</div>
								  </div>
							</div>
							</#list>
						</#list>
					</#if>
									

												
																	
						<ul class="pager">
						  <li class="previous"><a class="exam_pager" href="./listitem.action?examid=${examid}&page=1">
						  	<span class="glyphicon glyphicon-fast-backward"></span> 首页</a></li>&nbsp;
						  <li class="previous"><a class="exam_pager" href="./listitem.action?examid=${examid}&page=${lastpage}">
						  	<span class="glyphicon glyphicon-backward"></span> 前一页</a></li>&nbsp;
						  <li class="next"><a class="exam_pager" href="./listitem.action?examid=${examid}&page=${endpage}">
						  	末页 <span class="glyphicon glyphicon-fast-forward"></span> </a></li>&nbsp;
						  <li class="next"><a class="exam_pager" href="./listitem.action?examid=${examid}&page=${nextpage}">
						  	后一页 <span class="glyphicon glyphicon-forward"></span> </a></li>&nbsp;
						  
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
