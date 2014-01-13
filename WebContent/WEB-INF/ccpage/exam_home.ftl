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
<div id="dialog" title="请填写姓名">
	<form name="go_to_exam" id="go_exam" method="post" >
		<div class='exam_user_name'>姓名 : &nbsp;&nbsp;<input id='user_name_input' type="text" class="exam_username_input" name="user_name" placeholder="姓名" /></div>
	</form>
</div>
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
					<h2>在线考试</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span class="page">培训&nbsp;&raquo;</span>
						<span class="page">在线考试</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">

				<div class="demo">
		<ul id="list" class="image-grid_3col">
		    <li data-id="id-1" class="photo">
		        <div class="portfolio_content" examid="1">
		        <img src="./images/portfolio_3col_img_1.png" alt="img" style="opacity: 1;">
		        <h4 style="color: rgb(86, 86, 86);">考题完善中。。。。</h4>
		        
		        <div class="link_btn" style="display: block;">
		            <a id="example6" href="#" class="zoom" style="opacity: 0;"></a>
		            <div class="exam_desc" style="opacity: 0;">单选题：10题<br />复选题：80题<br/>是非题：10题</div>
		            <div class="overlay" style="opacity: 0;"></div>
		        </div>
		        </div>
		    </li>
		    <li data-id="id-2" class="scenery">
		        <div class="portfolio_content"  examid="2">
		        <img src="./images/portfolio_3col_img_2.png" alt="img" style="opacity: 1;">
		        <h4 style="color: rgb(86, 86, 86);">考题完善中。。。。</h4>
		        
		        <div class="link_btn" style="display: block;">
		            <a id="example6" href="#" class="zoom" style="opacity: 0;"></a>
		            <div class="exam_desc" style="opacity: 0;">单选题：10题<br />复选题：80题<br/>是非题：10题</div>
		            <div class="overlay" style="opacity: 0;"></div>
		        </div>
		        </div>
		    </li>
		    <li data-id="id-3" class="photo">
		        <div class="portfolio_content"  examid="3">
		        <img src="./images/portfolio_3col_img_3.png" alt="img">
		        <h4>中级食品检验工</h4>
		        
		        <div class="link_btn">
		            <a id="example6" href="#" class="zoom" style="opacity: 0;"></a>
		            <div class="exam_desc" style="opacity: 0;">单选题：10题<br />复选题：80题<br/>是非题：10题</div>
		            <div class="overlay" style="opacity: 0;"></div>
		        </div>
		        </div>
		    </li>
		    
		</ul>
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
