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
			<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet">


		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-migrate.js"></script>

		<script type="text/javascript" src="./js/jqueryslidemenu.js"></script>
		<script type="text/javascript" src="./js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="./js/custom.js"></script>
		<script type="text/javascript" src="./js/unslider.js"></script>
		<script type="text/javascript" src="./js/fancybox/jquery.fancybox-1.3.4.js"></script>
		<script type="text/javascript" src="./js/frontend.js"></script>


	<script src="js/jquery-ui-1.10.3.custom.min.js"></script>

		<script type="text/javascript" src="./js/jquery.easing.1.3.js"></script>

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
				
				$( "#dialog" ).dialog({
					autoOpen: false,
					width: 400,
					modal: true, 
					buttons: [
						{
							text: "开始",
							click: function() {
								if($('#user_name_input').val()=="")
								{
									alert('请填写姓名！');
								}
								else
								{
									$( this ).dialog( "close" );
									$('#go_exam').submit();
								}
							}
						},
						{
							text: "取消",
							click: function() {
								$( this ).dialog( "close" );
							}
						}
					]
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
			
			$(document).ready(function() {
				$('.portfolio_content').click(function()
				{
					//beginexam.action?examid=2
					$("#dialog" ).dialog( "open" );					
					$('#go_exam').attr('action','beginexam.action?examid='+$(this).attr('examid'));
					event.preventDefault();
				});
			});
		</script>
	</head>
	<body>

		<div id="page_wrap">
			<!-- Header Start -->
			<div class="header">
		    <#include "../commons/logo.ftl">
		  	<#include "../commons/menubar.ftl">
			<#include "../commons/smallmenu.ftl"> 
				<!-- <nav>
				<div id="myslidemenu" class="jqueryslidemenu">
				<ul>
				<li><a class="active" href="./index.html">首页</a></li>
				<li><a href="./about.html">关于我们</a></li>
				<li><a href="./index.html">舆情动态</a></li>

				<li><a href="./portfolio.html">公开办事</a>
				<ul>
				<li><a href="./portfolio_3col.html">肉制品报告</a></li>
				<li><a href="./portfolio_3col.html">标准备案</a></li>
				<li><a href="./portfolio_2col.html">添加剂报告</a></li>
				<li><a href="./portfolio_1col.html">委托加工备案</a></li>
				</ul>
				</li>
				<li><a href="./blog.html">政策法规</a></li>
				<li><a href="./contact.html">咨询服务</a></li>
				</ul>
				</div>
				</nav> -->
			</div>
			<!-- Header End -->

			<div class="clear"></div>

			<!-- Flex Slider Start -->
			<div class="sub_header  exam_bg">

				<div class="sub_header_title">
					<h2>站内消息</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span><a href="./page.action?categoryid=1">用户中心 &raquo;</a></span>
						<span class="page">站内消息</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">
			<#if messagelist?exists>
				<#if (messagelist?size>0) >			 
					<#list messagelist as message>
						<div class="mom_content notice_content">
							<div class="mom_left"><span class="mom_year">${message.year?default("")} </span>${message.month?default("")}/${message.day?default("")} </div>
							<div class="mom_icon">
								<div class="mom_logo_div"><img class="mom_icon_img" src="./images/notice_gray.png"/></div>
							</div>
							<div class="mom_inner_content">
								<div class="mom_contetn_header">
									<div class="mom_header_text">
										${message.title?default("")}
									</div>
									<div class="mom_date">${message.pubdate?default("")}</div>
								</div>
								<div class="mom_contetn_body clearfix">
									<div class="mom_body_text">
										${message.absinfo?default("")}
									</div>
								</div>
								<div class="mom_contetn_bottom">&nbsp;</div>
							</div>
						</div>
					</#list>
				<#else>
					<div class="mom_content notice_content">
							<div class="mom_left"></div>
							<div class="mom_icon">
								<div class="mom_logo_div"></div>							
							</div>
							<div class="mom_inner_content">
								<div class="mom_contetn_header">
								<div class="mom_header_text">
									暂无新信息
								</div>
								<div class="mom_date"></div>
								</div>
								<div class="mom_contetn_body clearfix">
									<div class="mom_body_text">
									
									</div>
								</div>
								<div class="mom_contetn_bottom">&nbsp;</div>
							</div>
						</div>
				</#if>
				
			</#if>
						
					
					<!--
						<div class="mom_content notice_content">
							<div class="mom_left"><span class="mom_year">2012 </span>10/20</div>
							<div class="mom_icon">
								<div class="mom_logo_div"><img class="mom_icon_img" src="./images/notice_gray.png"/></div>
								
							</div>
							<div class="mom_inner_content">
								<div class="mom_contetn_header">
									<div class="mom_header_text">
										手机导航、MM业务年末大促销活动
									</div>
									<div class="mom_date">2013/12/12 12:30</div>
								</div>
								<div class="mom_contetn_body clearfix">
									<div class="mom_body_text">
									
									</div>
								</div>
								<div class="mom_contetn_bottom">&nbsp;</div>
							</div>
						</div>
					-->
					
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
