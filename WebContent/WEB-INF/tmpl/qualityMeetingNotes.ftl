
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
		<link rel="stylesheet" type="text/css" href="./css/960_24_col.css" />


		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-migrate.js"></script>

		<script type="text/javascript" src="./js/jqueryslidemenu.js"></script>
		<script type="text/javascript" src="./js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="./js/custom.js"></script>
		<script type="text/javascript" src="./js/unslider.js"></script>
		<script type="text/javascript" src="./js/fancybox/jquery.fancybox-1.3.4.js"></script>

		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>

		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/form.js"></script>

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
		
		<script>
			var form_data= eval(${jsonText3});
		</script>
		<style>
			table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#fff;}
			th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
			th{font-weight:bold;background:#ccc;}
		</style>
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
					<h2>用户登录</h2>
					<div class="sub_header_description">
						<span><a href="./index.html">首页 &raquo;</a></span>
						<span class="page">用户登录</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">

				
				<!-- <table style="width:100%">
					<tr>						
						<td><div class="display" form_data="options">333display</div>
							<input class="editor" form_data="options"/></td>
						<td colspan="4"><div class="display" form_data="title">化学物品管理记录display</div>
							<input class="editor" form_data="title"/></td>						
						<td><div class="display" form_data="options">333display</div>
							<input class="editor" form_data="options"/></td>
					</tr>
				
					<tbody id='data_1_container'>
					<tr form_data="_loop" target_data = "data_1">
						<td><div class="display" form_data="field_1">太阳d</div>
							<input class="editor" form_data="field_1" value="太阳e" />
						</td>
						<td><div class="display" form_data="field_2">月亮d</div>
							<input class="editor" form_data="field_2" value="月亮e" />
						</td>
						<td colspan="3"><div class="display" form_data="field_3">星星d</div>
							<input class="editor" form_data="field_3" value="星星e" />
						</td>
					</tr>
					</tbody>
					
				</table>
				-->
				
				<form action="saveDoc.action" method="post" >
				<table style="border:1px solid black;" width="534" height="559" border="1" cellpadding="1" cellspacing="1" bordercolor="#666666">
				
				<tbody id='data_1_container'>
				  <tr>
				    <td height="40" colspan="2"><div align="center">
				      <h2>质量会议纪要</h2>
				    </div></td>
				  </tr>
				  <tr>
				    <td width="266" > <div align="justify"></div></td>
				    <td width="265" align="center">No:
				    	<div class="display" form_data="qmn_no" ></div>
					    <input class="editor" form_data="qmn_no" name="qmn_no" 
					    	type="text" style="width:80px;height:20px" ></input>
				    </td>
				  </tr>				  
				  <tr>
				    <td height="24">会议时间：
				    	<div class="display" form_data="qmn_meetingtime" ></div>
					    <input class="editor" form_data="qmn_meetingtime" name="qmn_meetingtime" 
					    	type="text" style="width:80px;height:20px" ></input>
				    </td>
				    <td>会议地点：
					    <div class="display" form_data="qmn_meetingplace" ></div>
						<input class="editor" form_data="qmn_meetingplace" name="qmn_meetingplace" 
					    	type="text" style="width:80px;height:20px" ></input>
				    </td>
				  </tr>
				  <tr>
				    <td height="58" colspan="2">参会人员：
				    	<div class="display" form_data="qmn_members" ></div>
						<input class="editor" form_data="qmn_members" name="qmn_members" 
					    	type="textarea" style="" ></input>
				    </td>
				  </tr>
				  <tr>
				    <td height="211" colspan="2" valign="top">会议主要内容：
				    	<div class="display" form_data="qmn_meetingguideline" ></div>
						<input class="editor" form_data="qmn_meetingguideline" name="qmn_meetingguideline" 
					    	type="textarea" style="" ></input>
				    </td>
				  </tr>
				  
				  <tr>
				    <td height="111" colspan="2" valign="top" bordercolor="#666666">备注：
				    	<div class="display" form_data="qmn_remarks" ></div>
						<input class="editor" form_data="qmn_remarks" name="qmn_remarks" 
					    	type="textarea" style="width:80px;height:20px" ></input>
				    </td>
				  </tr>
				  
				  <tr>
				    <td>审核：
				    	<div class="display" form_data="qmn_checker" ></div>
						<input class="editor" form_data="qmn_checker" name="qmn_checker" 
					    	type="text" style="width:80px;height:20px" ></input>				    
				    </td>
				    <td bordercolor="#666666">记录：
				    	<div class="display" form_data="qmn_recorder" ></div>
						<input class="editor" form_data="qmn_recorder" name="qmn_recorder" 
					    	type="text" style="width:80px;height:20px" ></input>				    
				    </td>
				  </tr>
				  </tbody>
				
				</table>
				
					<input type="hidden" value="${docid}" name="docid" />
 					<input type="submit" value="保存" />
				</form>
				
				
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
