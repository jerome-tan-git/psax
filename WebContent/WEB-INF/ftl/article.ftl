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
		    <div class="logo">
		    	<div class="logo_container">
		    		<a href="./index.html"><img src="./images/logo_new.png" alt="img" /></a>
		    	</div>
		    	<div class="logo_search">
		    		<div class="search_box">
					    <form action="#" method="get">
					    <div style="width:210px" class="clearfix">
					    <div style="width:150px; float: left">	
					    	<input id="error_search" type="text" name="s">
					    </div>
				    	<input  type="submit" value="" style="float: right">
					    </div>
					    </form>
					</div>
				</div>
		    </div>
				<#include "./menubar.ftl">
				<div class="small_menu">
					<div class="small_menu_title clearfix">
						<img src="./images/1382292132_menu-alt.png"  />
					</div>
					<div class="small_menu_list clearfix">
						<div class="clearfix">
							<a href="./index.html">
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								首页
							</div> </a>
							<a href="./about_us.html">
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								关于我们
							</div> </a>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								舆情动态
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								公开办事 | 肉制品报告
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								公开办事 | 标准备案
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								公开办事 | 添加剂报告
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								公开办事 | 委托加工备案
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								政策法规
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								会议培训
							</div>
						</div>
					</div>

				</div>
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
					<h2>大事记</h2>
					<div class="sub_header_description">
						<span><a href="./index.html">首页 &raquo;</a></span>
						<span class="page">大事记&raquo;</span>
						<!--span>首页</span-->
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">
				<div class="article_body">
				<div class="article_detail_title">				
					${art.title?default("")}
				</div>
				<div class="article_detail_info">
					<div class="article_detail_date">					
					${art.pubdate?default("")}
					</div>
				</div>
				<div class="article_detail_body">					
					${art.pubdate?default("")}
				</div>
				<div class="article_detail_attach">
					<div class="article_detail_attach_title">附件</div>
					<div class="article_detail_attach_block">
						<#if art.addition?exists>
						<a href="${art.addition}">${art.addition}</a>
						<#else>
							无
						</#if>
					</div>
				</div>
			</div>
			</div>
			<!-- Teaser End -->

			<!-- Content Start -->

			<!-- Content End -->

			<!-- Bottom Section Start -->
			<#include "./footertest.ftl">
			<!-- Bottom Section End -->

		</div>
	</body>

</html>
