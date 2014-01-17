<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<title>jQa</title>
		<link rel="stylesheet" type="text/css" href="./css/reset.css" />
		<link rel="stylesheet" type="text/css" href="./css/layout.css" />
		<link rel="stylesheet" type="text/css" href="./css/flexslider.css" />
		<link rel="stylesheet" type="text/css" href="./css/sudo.css" />
		<link rel="stylesheet" type="text/css" href="./css/jqueryslidemenu.css" />
		<link rel="stylesheet" type="text/css" href="./style.css" />
		<link rel="stylesheet" type="text/css" href="./css/blog.css" />

		<link rel="stylesheet" type="text/css" href="./js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="./css/portfolio.css" />
		<link rel="stylesheet" type="text/css" href="./css/quicksand.css" />
		<link rel="stylesheet" type="text/css" href="./css/skin.css" />


		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-migrate.js"></script>

		<script type="text/javascript" src="./js/jqueryslidemenu.js"></script>
		<script type="text/javascript" src="./js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="./js/custom.js"></script>
		<script type="text/javascript" src="./js/unslider.js"></script>
		<script type="text/javascript" src="./js/fancybox/jquery.fancybox-1.3.4.js"></script>

		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>

		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="./js/ckeditor/ckeditor.js"></script>
		<script src="./js/ckeditor/adapters/jquery.js"></script>
		<style type="text/css">
			html, body, p {
				margin: 0;
				padding: 0;
			}

			body {
				/*overflow: hidden;*/
			}
			.warp {
				filter: alpha(opacity=20);
			}

			.img24 {
				background: none;
				filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='./images/test.png"');
				width: 100px;
				height: 100px;
			}

		</style>
		<script type="text/javascript">
			$(document).ready(function() {
				
				$('#reply').ckeditor(
				{
					toolbar:[											
																				
							['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
							['Styles','Format','Font','FontSize'],							
							['TextColor','BGColor']
														
							
						],
						height:"130px",
						width:"868px",
						filebrowserUploadUrl : './upload.action'
					}
				
				);
				
				
				$('.bbs_thread_use').each(function()
				{
					var toHeight = $(this).parent().outerHeight();
					if(toHeight>230)
					{
						$(this).css({height: toHeight+'px'});
					}
					//$(this).css({height: toHeight+'px'});
				});
				$('.bbs_thread_bottom_line').each(function()
				{
					var parentHeight = ($(this).parent().outerHeight());
					if(parentHeight<230)
					{
						parentHeight = 230;
					}
					$(this).css({top:(parentHeight-30)+"px"});
				});
				// bancy bo=x
				// jQuery("a#example6").fancybox({
					// 'titlePosition' : 'outside',
					// 'overlayColor' : '#000',
					// 'overlayOpacity' : 0.9
				// });
				//slider
				// var sudoSlider = jQuery("#testimonail").sudoSlider({
				// continuous:true,
				// numeric:false
				// });
				$('.banner').unslider();

				//menu_item
				$(".menu_item").hover(function() {
					$(this).addClass("selected");
					var myList = $(this).find(".menu_list");
					myList.show();

				}, function() {

					$(this).removeClass("selected");
					//alert(".menu_list .sub_"+$(this).attr("id"));
					var myList = $(this).find(".menu_list");
					myList.hide();

				});

				$(".small_menu_title").bind("click", function() {
					var newHeight = $(".small_menu_list").find(".clearfix").outerHeight();
					if ($(".small_menu_list").outerHeight() == 0) {
						$(".small_menu_list").animate({
							height : newHeight + "px"
						}, 1000, "easeOutExpo");
					} else {
						$(".small_menu_list").animate({
							height : 0 + "px"
						}, 1000, "easeOutExpo");
					}
				});

				$(".small_menu_item").hover(function() {
					$(this).addClass("small_menu_hover");
				}, function() {
					$(this).removeClass("small_menu_hover");
				});

				$(".list_item").hover(function() {

					$(this).find(".sub_menu").css("display", "block");
					$(this).animate({
						backgroundColor : "#FDFFD0"
					}, 500);
				}, function() {
					$(this).animate({
						backgroundColor : "#fff"
					}, 500);
					$(this).find(".sub_menu").hide();
				});

				$(".exam_options").each(checkOptions);
				$(".exam_options").change(function() {
					$(".exam_options").each(checkOptions);
				});
				$(".exam_submit").hover(function() {
					$(this).animate({
						backgroundColor : "#F5FAFF"
					}, 500);
				}, function() {
					$(this).animate({
						backgroundColor : "#fff"
					}, 500);
				});
				
				$(".click_down").click(function(){
					var toHeight = $(".exam_no_container").outerHeight();
					var beginHeight = $(".exam_no_outer").outerHeight();
					if(beginHeight == 0)
					{
						   $(".exam_no_outer").animate({
								height : toHeight + "px"
							}, 1000, "easeOutExpo",
							function()
							{
								$(".click_down").attr("src","images/up.png");
							});
					}
					else
					{
							$(".exam_no_outer").animate({
								height : 0 + "px"
							}, 1000, "easeOutExpo",
							function()
							{
								$(".click_down").attr("src","images/down.png");
							});
					}
				});
			});
			function checkOptions() {
				var op = $(this).find("input");
				var label = $(this).find("." + (op.attr("id")));

				if (op.attr("checked")) {
					label.addClass("exam_select");
				} else {
					if (label.hasClass("exam_select")) {
						label.removeClass("exam_select");
					}
				}
			}
		</script>
	</head>
	<body>
		<div id="page_wrap">
			<!-- Header Start -->
			<div class="header">
		   		<#include "../commons/logo.ftl">
			  	<#include "../commons/menubar.ftl">
				<#include "../commons/smallmenu.ftl"> 		
			</div>
			<!-- Header End -->

			<div class="clear"></div>

			<!-- Flex Slider Start -->
			<div class="sub_header  exam_bg">

				<div class="sub_header_title">
					<h2>BBS论坛</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span class="page">BBS论坛</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">
				<div class="bbs_thread_title">${topic.title?default("暂无")}</div>
				<div class="bbs_thread_body clearfix">
					<#assign usr=topic.user >
					<div class="bbs_thread_use">
						<div class="bbs_thread_avatar">
							<img src=${usr.portrait?default("./images/a_1.png")} />
						</div>
						<div class="bbs_thread_name">${usr.username}</div>
					</div>
					<div class="bbs_thread_content">
						<div>${topic.content}</div>
						<div>&nbsp;</div>
					</div>
					<div class="bbs_thread_bottom_line">
						<a href="#001"><div class="bbs_thread_bottom_button">我要评论</div></a>
					</div>
				</div>	
				<#if topic.comments?exists>
					<#if (topic.comments?size>0)>
						<#list topic.comments as comment>
							<div class="bbs_thread_body clearfix">
								<div class="bbs_thread_use">
								<#if comment.user?exists>
								<#assign usr=comment.user>
									<div class="bbs_thread_avatar">
										<img src=${usr.portrait?default("./images/a_1.png")} />
									</div>
									<div class="bbs_thread_name">${usr.username}</div>
								</#if>
								</div>
								<div class="bbs_thread_content">
									<div>${comment.content}</div>
									<div>&nbsp;</div>
								</div>
								<div class="bbs_thread_bottom_line">
									<div class="bbs_thread_bottom_button">发表于：${comment.date}</div>
								</div>
							</div>			
						</#list>					
					</#if>				
				</#if>			
					
				<div style="margin-left: 85px;margin-top: 30px;margin-right: 85px;">
					<a name="001" id="001">
						<div class="bbs_reply_label">回 复</div>
						<form method="post" action="bbs_commentsave.action">
						<input type="hidden" name="id" value="${topic.id}" />
						<div><textarea id="reply" name="commentcontent"></textarea></div>
						<div style="float: right;padding-top: 10px;">
							<button type="submit" style="padding: 5px;padding-left: 27px;padding-right: 27px;">提 交</button>
						</div>
						<form>
					</a>
				</div>
				
			</div>
			<!-- Teaser End -->

			<!-- Content Start -->

			<!-- Content End -->

			<!-- Bottom Section Start -->
			<#include "../commons/footertest.ftl">
			<!-- Bottom Section End -->

		</div>
	</body>

</html>
