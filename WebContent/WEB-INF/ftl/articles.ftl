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
		<link rel="stylesheet" type="text/css" href="./list_block.css" />

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
		<script type="text/javascript" src="js/jquery.waterfall.js"></script>
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

				$(".click_down").click(function() {
					var toHeight = $(".exam_no_container").outerHeight();
					var beginHeight = $(".exam_no_outer").outerHeight();
					if (beginHeight == 0) {
						$(".exam_no_outer").animate({
							height : toHeight + "px"
						}, 1000, "easeOutExpo", function() {
							$(".click_down").attr("src", "images/up.png");
						});
					} else {
						$(".exam_no_outer").animate({
							height : 0 + "px"
						}, 1000, "easeOutExpo", function() {
							$(".click_down").attr("src", "images/down.png");
						});
					}
				});

				var wf_page = 0;
				$('#waterfall').waterfall({
					colWidth : 330,
					marginLeft : 10,
					marginTop : 10,
					// 自定义跨域请求
					ajaxFunc : function(success, error) {
						$.ajax({
							type : 'GET',
							url : './listImgArt.action?callback=?',
							cache : false,
							data : {
								'page' : ++wf_page,
								'category':'${catpath.catId}'
							},
							dataType : 'jsonp',
							timeout : 60000,
							success : success,
							error : error
						});
					},
					createHtml : function(data) {
						return '<div onmouseover="overBlock($(this))" class="wf_item_inner clearfix">'+
						'<a href="'+data.url+'" target="_blank"><div onmouseout="outBlock($(this).parent())" class="block_cover"><div class="content_desc clearfix" style="display:none"><div>'+data.title+'</div>'
						+'<div class="content_desc_small">'+data.referer+'</div></div></div></a>' 
						+ '<a href="' + data.url + '" class="thumb" target="_blank">' + '<img class="thumb_img"  src="' + data.image + '" />' 
						+ '</a><div class="wf_caption"><div class="wf_title">'+data.title+'<div></div></div>';

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

			function overBlock(obj) {
				var boxHeight = obj.outerHeight();
				var textObj =obj.parent().find(".content_desc"); 
				obj.parent().find(".block_cover").css("height",boxHeight + "px");				
				obj.find(".wf_caption").hide();
				textObj.show();

			}

			function outBlock(obj) {
				var textObj =obj.parent().find(".content_desc"); 
				obj.find(".block_cover").css("height","0px");
				obj.parent().find(".wf_caption").show();
				textObj.hide();
				// obj.find(".block_cover").stop(true,true).animate({
					// height : "0px"
				// }, 300, "easeOutExpo",function()
				// {
					// textObj.stop(true,true).hide();
					// obj.find(".wf_caption").animate({
						// opacity : 1
					// }, 300, "easeOutExpo");
				// }
				// );
				
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
				
					<h2>${catpath.catName}</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<#if catpath.parentCatId?exists>
						<#if (catpath.parentCatId>0) >
						<span class="page">
							<a href="./listArticles.action?categoryid=${catpath.parentCatId}">
								${catpath.parentCatName}&raquo;
							</a>
						</span>	
						</#if>
						</#if>
						<span class="page">
							<a href="./listArticles.action?categoryid=${catpath.catId}">
								${catpath.catName}&raquo;
							</a>
						</span>						
					</div>
				</div>
				<div class="exam_type">

				</div>

			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section clearfix" id="content" class="tag_line" style="padding-top: 30px">

				<div id="waterfall"></div>

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
