<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<title>上海市浦东新区食品生产安全管理协会</title>
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
					<h2>基本信息</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span><a href="./page.action?categoryid=1">用户中心 &raquo;</a></span>
						<span class="page">基本信息</span>
					</div>

				</div>
				<div class="exam_type">
					<#if Session.user_?exists>
					<#assign user=Session.user_>						
						你好，${user.nickname?default("")}
	 				<#else> 					
	 					你好！
	 				</#if>
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<form method="post" action="userupdateinfo.action">
			<#if Session.user_?exists>
					<#assign user=Session.user_>
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">
				<div class="user_info_left">
					<div class="avator_container clearfix"><img align="middle" src="${user.portrait?default('./images/a_1.png')}" /></div>
				</div>
				<div class="user_info_right">
				
					<div class="userinfo_title">
						<div>用户名</div>
						<div>
							<input class="userinfo" name="username" value="${user.username?default("")}"></input>
						</div>
					</div>
					<div class="userinfo_title">
						<div>密码</div>
						<div>
							<input class="userinfo" type="password" placeholder="" name="password" 
							value="${user.password?default("")}"></input>
						</div>
					</div>
					<div class="userinfo_title">
						<div>昵称</div>
						<div>
							<input class="userinfo" type="text" placeholder="" name="nickname" 
							value="${user.nickname?default("")}"></input>
						</div>
					</div>
					<div class="userinfo_title">
						<div>邮箱</div>
						<div>
							<input class="userinfo"  type="email" name="email" placeholder="" name="email"
							value="${user.email?default("")}"></input>
						</div>
					</div>
					<div class="userinfo_title">
						<div>联系电话</div>
						<div>
							<input class="userinfo" type="text" placeholder="" name="phone" 
							value="${user.phone?default("")}"></input>
						</div>
					</div>					
					<div class="userinfo_title">
						<div>头像选择</div>
						<div>
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_1.png"  
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_1.png")>checked</#if>
							<#else>checked
							</#if> />
								<img src="./img/tx_1.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_2.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_2.png")>checked</#if>
							</#if> />
								<img src="./img/tx_2.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_3.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_3.png")>checked</#if>
							</#if> />
								<img src="./img/tx_3.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_4.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_4.png")>checked</#if>
							</#if> />
								<img src="./img/tx_4.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_5.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_5.png")>checked</#if>
							</#if> />
								<img src="./img/tx_5.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_6.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_6.png")>checked</#if>
							</#if> />
								<img src="./img/tx_6.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_7.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_7.png")>checked</#if>
							</#if> />
								<img src="./img/tx_7.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_8.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_8.png")>checked</#if>
							</#if> />
								<img src="./img/tx_8.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_9.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_9.png")>checked</#if>
							</#if> />
								<img src="./img/tx_9.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_10.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_10.png")>checked</#if>
							</#if> />
								<img src="./img/tx_10.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_11.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_11.png")>checked</#if>
							</#if> />
								<img src="./img/tx_11.png" >
							<input class="userinfo" type="radio" name="portrait" value="./img/tx_12.png" 
							<#if user.portrait?exists>
								<#if (user.portrait="./img/tx_12.png")>checked</#if>
							</#if> />
								<img src="./img/tx_12.png" >
						</div>
					</div>	
					<div class="userinfo_title">				
						<input type="submit" name="issave" value="保存"></input>
						<input type="submit" name="issave" value="取消"></input>						
					</div>	
				
				</div>				
			</div>
			</#if>
			</form>
			<!-- Teaser End -->

			<!-- Content Start -->

			<!-- Content End -->

			<!-- Bottom Section Start -->
			<#include "../commons/footertest.ftl">
			<!-- Bottom Section End -->

		</div>
	</body>

</html>
