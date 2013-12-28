<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Innovate Responsive HTML Website Template</title>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="./css/backend.css" />
		<link rel="stylesheet" type="text/css" href="./css/docs.css" />
		<link rel="stylesheet" type="text/css" href="./css/datepicker.css" />
		<link rel="stylesheet" type="text/css" href="./css/uploadify.css" />
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
		<script src="./js/ckeditor/ckeditor.js"></script>
		<script src="./js/ckeditor/adapters/jquery.js"></script>
		<script src="./js/backend.js"></script>
		<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
		<script src="./js/jquery.uploadify.js"></script>
		<script src="./js/addarticle.js"></script>
		<script>
			var selectIndex = -1;
			CKEDITOR.disableAutoInline = true;
			function deleteItem(instanceID, fileID) {
				//#DF6F6F
				$('#' + fileID).animate({
					borderColor : "#cc0000",
					color : "#cc0000"
				}, 200);
				$('#' + instanceID).uploadify('cancel', fileID);
			}
			$(document).ready(function() {
				$('#editor').ckeditor({
					height: '800px',
					filebrowserUploadUrl: './upload.action'
					
				});
				// Use CKEDITOR.replace() if element is <textarea>.
				//$( '#editor1' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
				var nowTemp = new Date();
				var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
				$('.date').datepicker('setValue', now)
				  .on('changeDate', function(ev){
					$('.date').datepicker('hide');
				  });
					
				
						
						$(".form-horizontal").on("submit", function() {
							var allPath = "";
							$(".realpath").each(function() {
								allPath += ($(this).val()) + "|";
							});
							$(".all_realPath").val(allPath);
							return true;
						});
			});
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

							<li>
								<a href="./AddExam.html">Exam</a>

							</li>
							<li class="active">
								<a href="#transitions">Article</a>
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
							<h3 class="panel-title">Article</h3>
						</div>
						<div class="panel-body">
						<#if art?exists>
							<form class="form-horizontal" role="form" action="addArt.action?articleid=${art.id}" method="post" enctype="multipart/form-data">
						<#else>
							<form class="form-horizontal" role="form" action="addArt.action" method="post" enctype="multipart/form-data">
						</#if>
								<div class="col-sm-12">
									<label for="exampleInputEmail1">标题</label> 									
									<#if art?exists>
										<input type="text" class="form-control" id="exampleInputEmail1" placeholder="" name="title" value="${art.title}"></input>										
									<#else>
										<input type="text" class="form-control" id="exampleInputEmail1" placeholder="标题" name="title"/>
									</#if>
									
								 </div>
								 <div class="col-sm-12">&nbsp;</div>
								 <div class="col-sm-12">
									<label for="exampleInputEmail1">摘要</label>
									<#if art?exists>
										<input type="text" class="form-control" id="exampleInputEmail1" placeholder="" name="absinfo" value="${art.absinfo}"></input>
									<#else>
										<input type="text" class="form-control" id="exampleInputEmail1" placeholder="摘要" name="absinfo">
									</#if>
								 </div>
								 <div class="col-sm-12">&nbsp;</div>
								 
								<div class="col-sm-6">
									<label for="exampleInputEmail1">类别</label>
									    <#if categories?exists>
									    <select class="form-control" name="categoryid">
									    <#list categories as category>
										    <#if art?exists>
										    	<#if (art.categoryid=category.id)>
										    	<option value="${category.id}" selected>${category.category}</option>
										    	</#if>
										    <#else>
											  <option value="${category.id}">${category.category}</option>
											</#if>										  
										</#list>				
										</select>
										</#if>
								 </div>
								 
								<div class="col-sm-6">
									  <label for="exampleInputEmail1">日期</label>
									  <div class="input-group input-append date" data-date="2012-12-02" data-date-format="yyyy-mm-dd">
									  <#if art?exists>
							          	<input type="text" class="form-control" size="16" readonly name="pubdate" value="${art.pubdate}"/>
							          <#else>
							          	<input type="text" class="form-control" size="16" readonly name="pubdate" />
							          </#if>
							          	<span class="input-group-btn add-on">
							            	<button class="btn btn-default" type="button">
							            		<i class="glyphicon glyphicon-calendar">&nbsp;</i>
							            	</button>
							            </span>
							          </div>
								 </div>
								 
								 <div class="col-sm-12">&nbsp;</div>
								 
								<div class="col-sm-6" style="height: 157px;">
									 <label for="exampleInputEmail1">图片</label>
									 
									 <input type="file" name="pic" id="image_file_uploader" />
									<!--
									<#if art?exists>
									 	<input type="hidden" name="picurl" value="${art.pic}"/>
										<div class="thumbnail"><img src="${art.pic}"></div>
									</#if>
									-->
									<#if art?exists>										
										<div id="Exist_image" class="uploadify-queue-item old_files">
											<div class="cancel">
												<a href="javascript:deleteItem('image_file_uploader', 'Exist_image')">X</a>
											</div>
											<span class="fileName"> ${decode(art.pic)}</span>
											<span class="data"> - Exists</span>
											<input type="hidden" class="image_realpath" name="picurl" value="${art.pic}">										
										</div>
									<#else>
									
									</#if>
								 </div>
								 
								 <div class="col-sm-6">
									 <label for="exampleInputEmail1">附件</label>
									 <input type="file" name="addition" id="file_upload_1"/>	
									 <#if art?exists>
									 <!--
									 	<input type="hidden" name="additionurl" value="${art.addition}"/>
										<a href="${art.addition}">${art.addition}</a>
										-->
										<#if art.attachments?exists>
											<#list art.attachments as attachment>
												<div id="SWFUpload_0_10001" class="uploadify-queue-item old_files">
												<div class="cancel">
													<a href="javascript:deleteItem('file_upload_1', 'SWFUpload_0_10001')">X</a>
												</div>
												<span class="fileName">_产品条款.rar_</span>
												<span class="data"> - Exists</span>
												<input type="hidden" class="realpath"
													value="./ckimages/516__%E4%BA%A7%E5%93%81%E6%9D%A1%E6%AC%BE.rar_">
												</div>
											</#list>
										</#if>
									 </#if>
									 
									
									<div id="SWFUpload_0_10002" class="uploadify-queue-item old_files">
										<div class="cancel">
											<a href="javascript:deleteItem('file_upload_1', 'SWFUpload_0_10002')">X</a>
										</div>
										<span class="fileName">_产品条款.rar_</span>
										<span class="data"> - Exists</span>
										<input type="hidden" class="realpath"
											value="./ckimages/516__%E4%BA%A7%E5%93%81%E6%9D%A1%E6%AC%BE.rar_">
									</div>
									<input type="hidden" class="all_realPath" name="attachments" />
								 </div>

								<div class="col-sm-12">&nbsp;</div>
								<label  class="col-sm-12">内容</label>
								<div class="col-sm-12">
																
									<#if art?exists>
										<textarea id="editor" name="article" >${art.article} </textarea>
									<#else>
										<textarea id="editor" name="article"> </textarea>
									</#if>
									
								</div>
								<div class="col-sm-12">&nbsp;</div>
								<div class="col-sm-12">
									<!--
									<button type="button" class="btn btn-primary pull-right">
									  <span class="glyphicon glyphicon-floppy-disk"></span> 保存
									</button>
									-->
									<button type="submit" class="btn btn-primary pull-right">
										<span class="glyphicon glyphicon-floppy-disk"></span> 保存
									</button>
									
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
