
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
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span class="page">用户登录</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px" bordercolor="#666666">
			
				<form action="saveDoc.action" method="post" >

				<h2 align="center">上海市浦东新区食品生产安全管理协会</h2>
				<h2 align="center">企业基本信息情况</h2>
				<p class="STYLE1">填写请参见背面样张，填写完整后请于20130130	前寄往背面地址表或电子版邮箱至pdspsax@163.com(未来电可索要电子版)</p>
				
				<table width="1033" border="0" cellpadding="0" cellspacing="1" bgcolor="#666666">
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF"><p>厂(公司)名<br />
				      （生产许可证所注）</p>    </td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpName" ></div>			    
					    <input class="editor" form_data="cbi_corpName" name="cbi_corpName" type="text"></input>
				    </td>
				    <td bgcolor="#FFFFFF">企业人数</td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
					    <div class="display" form_data="cbi_corpHeadCount" ></div>			    
						<input class="editor" form_data="cbi_corpHeadCount" name="cbi_corpHeadCount" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF"><p>生产地址<br />（生产许可证所注）</p></td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
					    <div class="display" form_data="cbi_corpAddress" ></div>			    
						<input class="editor" form_data="cbi_corpAddress" name="cbi_corpAddress" type="text"></input>
				    </td>
				    <td bgcolor="#FFFFFF">邮编</td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
					    <div class="display" form_data="cbi_corpCode" ></div>			    
						<input class="editor" form_data="cbi_corpCode" name="cbi_corpCode" type="text"></input>				    
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">通讯地址</td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				        <div class="display" form_data="cbi_corpContactAddress" ></div>			    
						<input class="editor" form_data="cbi_corpContactAddress" name="cbi_corpContactAddress" type="text"></input>
				    </td>
				    <td bgcolor="#FFFFFF">邮编</td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				        <div class="display" form_data="cbi_corpContactCode" ></div>			    
						<input class="editor" form_data="cbi_corpContactCode" name="cbi_corpContactCode" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">生产场所属性</td>
				    	
					    <td colspan="4" bgcolor="#FFFFFF">
					        <div class="display" form_data="cbi_productionPlaceAttribute" ></div>
						    <p>1. 自有产权工业用地
						    <input type="radio" class="editor" form_data="cbi_productionPlaceAttribute" name="cbi_productionPlaceAttribute" value="自有产权工业用地" />
						    </p>
						    <div class="display" form_data="cbi_productionPlaceAttribute" ></div>
						    <p>2. 租用产权工业用地
						    <input type="radio" class="editor" form_data="cbi_productionPlaceAttribute" name="cbi_productionPlaceAttribute" value="租用产权工业用地" />
						    </p>
						        <div class="display" form_data="cbi_productionPlaceAttribute" ></div>
						    <p>3. 其它
						    <input type="radio" class="editor" form_data="cbi_productionPlaceAttribute" name="cbi_productionPlaceAttribute" value="其它" />
						    </p>
					    </td>
				    <td bgcolor="#FFFFFF"><p>生产场所面积<br />（平方米）</p></td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_productionPlaceArea" ></div>			    
						<input class="editor" form_data="cbi_productionPlaceArea" name="cbi_productionPlaceArea" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">QS获证号</td>
				    <td colspan="4" bgcolor="#FFFFFF">到期日</td>
				    <td colspan="5" bgcolor="#FFFFFF">QS获证产品名称（明细）</td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">1.
				    	<div class="display" form_data="cbi_QSnumber1" ></div>			    
						<input class="editor" form_data="cbi_QSnumber1" name="cbi_QSnumber1" type="text"></input>
				    </td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_QSexpireDate1" ></div>			    
						<input class="editor" form_data="cbi_QSexpireDate1" name="cbi_QSexpireDate1" type="text"></input>				    
				    </td>
				    <td colspan="5" bgcolor="#FFFFFF">1.
				    	<div class="display" form_data="cbi_QSproductname1" ></div>			    
						<input class="editor" form_data="cbi_QSproductname1" name="cbi_QSproductname1" type="text"></input>				    
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">2.
				    	<div class="display" form_data="cbi_QSnumber2" ></div>			    
						<input class="editor" form_data="cbi_QSnumber2" name="cbi_QSnumber2" type="text"></input>				    
				    </td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_QSexpireDate2" ></div>			    
						<input class="editor" form_data="cbi_QSexpireDate2" name="cbi_QSexpireDate2" type="text"></input>				    
				    </td>
				    <td colspan="5" bgcolor="#FFFFFF">2.
				    	<div class="display" form_data="cbi_QSproductname2" ></div>			    
						<input class="editor" form_data="cbi_QSproductname2" name="cbi_QSproductname2" type="text"></input>		
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">3.
				    	<div class="display" form_data="cbi_QSnumber3" ></div>			    
						<input class="editor" form_data="cbi_QSnumber3" name="cbi_QSnumber3" type="text"></input>
				    </td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_QSexpireDate3" ></div>			    
						<input class="editor" form_data="cbi_QSexpireDate3" name="cbi_QSexpireDate3" type="text"></input>
				    </td>
				    <td colspan="5" bgcolor="#FFFFFF">3.
				    	<div class="display" form_data="cbi_QSproductname3" ></div>			    
						<input class="editor" form_data="cbi_QSproductname3" name="cbi_QSproductname3" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">4.
				    	<div class="display" form_data="cbi_QSnumber4" ></div>			    
						<input class="editor" form_data="cbi_QSnumber4" name="cbi_QSnumber4" type="text"></input>
				    </td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_QSexpireDate4" ></div>			    
						<input class="editor" form_data="cbi_QSexpireDate4" name="cbi_QSexpireDate4" type="text"></input>
				    </td>
				    <td colspan="5" bgcolor="#FFFFFF">4.
				    	<div class="display" form_data="cbi_QSproductname4" ></div>			    
						<input class="editor" form_data="cbi_QSproductname4" name="cbi_QSproductname4" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">5.
				    	<div class="display" form_data="cbi_QSnumber5" ></div>			    
						<input class="editor" form_data="cbi_QSnumber5" name="cbi_QSnumber5" type="text"></input>
				    </td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_QSexpireDate5" ></div>			    
						<input class="editor" form_data="cbi_QSexpireDate5" name="cbi_QSexpireDate5" type="text"></input>
				    </td>
				    <td colspan="5" bgcolor="#FFFFFF">5.
				    	<div class="display" form_data="cbi_QSproductname5" ></div>			    
						<input class="editor" form_data="cbi_QSproductname5" name="cbi_QSproductname5" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" rowspan="5" bgcolor="#FFFFFF">对应产品执行标准</td>
				    <td colspan="4" bgcolor="#FFFFFF">1.
				    	<div class="display" form_data="cbi_exeStandard1" ></div>			    
						<input class="editor" form_data="cbi_exeStandard1" name="cbi_exeStandard1" type="text"></input>				    
				    </td>
				    <td width="160" rowspan="5" bgcolor="#FFFFFF">主导产品</td>
				    <td colspan="3" bgcolor="#FFFFFF">1.
				      	<div class="display" form_data="cbi_mainProduct1" ></div>			    
						<input class="editor" form_data="cbi_mainProduct1" name="cbi_mainProduct1" type="text"></input>
				    </td>
				    <td width="97" rowspan="3" bgcolor="#FFFFFF"><p>年产值（万）</p>    </td>
				  </tr>
				  <tr>
				    <td colspan="4" bgcolor="#FFFFFF">2.
				      	<div class="display" form_data="cbi_exeStandard2" ></div>			    
						<input class="editor" form_data="cbi_exeStandard2" name="cbi_exeStandard2" type="text"></input>
				    </td>
				    <td colspan="3" bgcolor="#FFFFFF">2.
				      	<div class="display" form_data="cbi_mainProduct2" ></div>			    
						<input class="editor" form_data="cbi_mainProduct2" name="cbi_mainProduct2" type="text"></input>				    
				    </td>
				  </tr>
				  <tr>
				    <td colspan="4" bgcolor="#FFFFFF">3.
				      	<div class="display" form_data="cbi_exeStandard3" ></div>			    
						<input class="editor" form_data="cbi_exeStandard3" name="cbi_exeStandard3" type="text"></input>
				    </td>
				    <td colspan="3" bgcolor="#FFFFFF">3.
				      	<div class="display" form_data="cbi_mainProduct3" ></div>			    
						<input class="editor" form_data="cbi_mainProduct3" name="cbi_mainProduct3" type="text"></input>				    
				    </td>
				  </tr>
				  <tr>
				    <td colspan="4" bgcolor="#FFFFFF">4.
				      	<div class="display" form_data="cbi_exeStandard4" ></div>			    
						<input class="editor" form_data="cbi_exeStandard4" name="cbi_exeStandard4" type="text"></input>
				    </td>
				    <td colspan="3" bgcolor="#FFFFFF">4.
				      	<div class="display" form_data="cbi_mainProduct4" ></div>			    
						<input class="editor" form_data="cbi_mainProduct4" name="cbi_mainProduct4" type="text"></input>				    
				    </td>
				    <td rowspan="2" bgcolor="#FFFFFF">&nbsp;
				      	<div class="display" form_data="cbi_annuslProductionValue" ></div>			    
						<input class="editor" form_data="cbi_annuslProductionValue" name="cbi_annuslProductionValue" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="4" bgcolor="#FFFFFF">5.
				      	<div class="display" form_data="cbi_exeStandard5" ></div>			    
						<input class="editor" form_data="cbi_exeStandard5" name="cbi_exeStandard5" type="text"></input>
				    </td>
				    <td colspan="3" bgcolor="#FFFFFF">5.
				      	<div class="display" form_data="cbi_mainProduct5" ></div>			    
						<input class="editor" form_data="cbi_mainProduct5" name="cbi_mainProduct5" type="text"></input>				    
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">法定代表人姓名</td>
				    <td width="57" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_legalRepresentativeName" ></div>			    
						<input class="editor" form_data="cbi_legalRepresentativeName" name="cbi_legalRepresentativeName" type="text"></input>
				    </td>
				    <td bgcolor="#FFFFFF">性别</td>
				    <td colspan="2" bgcolor="#FFFFFF">&nbsp;
						<div class="display" form_data="cbi_legalRepresentativeGender" ></div>			    
						<input class="editor" form_data="cbi_legalRepresentativeGender" name="cbi_legalRepresentativeGender" type="text"></input>				    
				    </td>
				    <td bgcolor="#FFFFFF">出生年月</td>
				    <td width="132" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_legalRepresentativeBirthday" ></div>			    
						<input class="editor" form_data="cbi_legalRepresentativeBirthday" name="cbi_legalRepresentativeBirthday" type="text"></input>
				    </td>
				    <td width="73" bgcolor="#FFFFFF">学历</td>
				    <td colspan="2" bgcolor="#FFFFFF">&nbsp;
						<div class="display" form_data="cbi_legalRepresentativeEdu" ></div>			    
						<input class="editor" form_data="cbi_legalRepresentativeEdu" name="cbi_legalRepresentativeEdu" type="text"></input>				    
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF">联系电话</td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_legalRepresentativeTelephone" ></div>			    
						<input class="editor" form_data="cbi_legalRepresentativeTelephone" name="cbi_legalRepresentativeTelephone" type="text"></input>
				    </td>
				    <td bgcolor="#FFFFFF">手机</td>
				    <td colspan="4" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_legalRepresentativeMPhone" ></div>			    
						<input class="editor" form_data="cbi_legalRepresentativeMPhone" name="cbi_legalRepresentativeMPhone" type="text"></input>
				    </td>
				  </tr>
				  <tr>
				    <td width="103" bgcolor="#FFFFFF">人员</td>
				    <td width="54" bgcolor="#FFFFFF">姓名</td>
				    <td bgcolor="#FFFFFF">性别</td>
				    <td width="85" bgcolor="#FFFFFF">出生年月</td>
				    <td width="46" bgcolor="#FFFFFF">学历</td>
				    <td width="115" bgcolor="#FFFFFF"><p>专业技术<br />
				      （职称等级）</p>    </td>
				    <td bgcolor="#FFFFFF"><p>质量获证情况<br />
				    （等级）</p>    </td>
				    <td bgcolor="#FFFFFF">手机</td>
				    <td colspan="3" bgcolor="#FFFFFF">QQ号/邮箱</td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF">企业负责人</td>
				    <td bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeName" ></div>			    
						<input class="editor" form_data="cbi_corpChargeName" name="cbi_corpChargeName" type="text"  style="width:40px;height:20px" ></input>
				    </td>
				    <td bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeGender" ></div>			    
						<input class="editor" form_data="cbi_corpChargeGender" name="cbi_corpChargeGender" type="text"  style="width:20px;height:20px" ></input>
				    </td>
				    <td bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeBirthday" ></div>			    
						<input class="editor" form_data="cbi_corpChargeBirthday" name="cbi_corpChargeBirthday" type="text"  style="width:50px;height:20px" ></input>
				    </td>
				    <td bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeEdu" ></div>			    
						<input class="editor" form_data="cbi_corpChargeEdu" name="cbi_corpChargeEdu" type="text"  style="width:50px;height:20px" ></input>
				    </td>
				    <td bgcolor="#FFFFFF">&nbsp;
					    <div class="display" form_data="cbi_corpChargeGrade" ></div>			    
						<input class="editor" form_data="cbi_corpChargeGrade" name="cbi_corpChargeGrade" type="text"  style="width:50px;height:20px" ></input>
				    </td>
				    <td bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeCertGrade" ></div>			    
						<input class="editor" form_data="cbi_corpChargeCertGrade" name="cbi_corpChargeCertGrade" type="text"  style="width:30px;height:20px" ></input>
				    </td>
				    <td bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeMPhone" ></div>			    
						<input class="editor" form_data="cbi_corpChargeMPhone" name="cbi_corpChargeMPhone" type="text"  style="width:50px;height:20px" ></input>
				    </td>
				    <td colspan="3" bgcolor="#FFFFFF">&nbsp;
				    	<div class="display" form_data="cbi_corpChargeEmail" ></div>			    
						<input class="editor" form_data="cbi_corpChargeEmail" name="cbi_corpChargeEmail" type="text"  style="width:50px;height:20px" ></input>
				    </td>
				  </tr>
				  <tr>
				    <td height="19" bgcolor="#FFFFFF"><p>生产负责人</p>    </td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF">质量负责人</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
				  </tr>
				  <tr>
				    <td rowspan="3" bgcolor="#FFFFFF">化验员</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
				  </tr>
				  <tr>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td bgcolor="#FFFFFF">&nbsp;</td>
				    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
				  </tr>
				  <tr>
				    <td colspan="2" bgcolor="#FFFFFF"><p>企业其它获证情况</p>    </td>
				    <td colspan="9" bgcolor="#FFFFFF">&nbsp;</td>
				  </tr>
				</table>
				<p class="STYLE1">填表日期：
				<div class="display" form_data="cbi_formMadeDate" ></div>
				<input class="editor" form_data="cbi_formMadeDate" name="cbi_formMadeDate" type="text" ></input>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				填表人：
				<div class="display" form_data="cbi_formMaker" ></div>
				<input class="editor" form_data="cbi_formMaker" name="cbi_formMaker" type="text"></input>
				</p>
				
					<input type="button" value="   b   " onclick="addNewLine('data_1','edit')"/>
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
