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
		<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
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
			   
    		.iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    		.iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
		</style>
		<script type="text/javascript">
			function enterSite() {
				$("#page_wrap").show();
				var dis = $("#cover").outerHeight(true);
				$("#cover").animate({
					top : "-2000px"
				}, 2000, 'easeInQuart', function() {
					$("#cover").remove();
					$(document.body).css("overflow", "auto");
					$("html").css("overflow", "auto");
				});
				var curl =window.location.href;
				if (curl.indexOf("#enter")== -1)
				{
					window.location.href +="#enter";
				} 
				
			}


			$(document).ready(function() {
				
				// bancy bo=x
				jQuery("a#example6").fancybox({
					'titlePosition'		: 'outside',
					'overlayColor'		: '#000',
					'overlayOpacity'	: 0.9
				});
				//slider
				// var sudoSlider = jQuery("#testimonail").sudoSlider({
		           // continuous:true,
		           // numeric:false
		        // });
				  $('.banner').unslider();
				
				//menu_item 
				$(".menu_item").hover(function(){
					$(this).addClass("selected");
					var myList =$(this).find(".menu_list"); 
					myList.show();
					
						
				},function(){
					
					$(this).removeClass("selected");
					//alert(".menu_list .sub_"+$(this).attr("id"));
					var myList =$(this).find(".menu_list"); 
					myList.hide();
					
				});
				
				$(".small_menu_title").bind("click",function()
				{
					var newHeight= $(".small_menu_list").find(".clearfix").outerHeight();
					if ($(".small_menu_list").outerHeight()==0)
					{
						$(".small_menu_list").animate({
							height:newHeight+"px"
						},1000,"easeOutExpo"
						);
					}
					else
					{
						$(".small_menu_list").animate({
							height:0+"px"
						},1000,"easeOutExpo"
						);
					}
				});
				
				$(".small_menu_item").hover(function()
				{
					$(this).addClass("small_menu_hover");
				},
				function()
				{
					$(this).removeClass("small_menu_hover");
				});
				
				$(".list_item").hover(function()
				{
					
					$(this).find(".sub_menu").css("display","block");
					$( this ).animate({
          				backgroundColor: "#FDFFD0"}, 500 );
				},
				function()
				{
						$( this ).animate({
          				backgroundColor: "#fff"}, 500 );
					$(this).find(".sub_menu").hide();
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
	<div class="sub_header">
	
	<div class="sub_header_title">
	    <h2>关于我们</h2>
	    <div class="sub_header_description">
	        <span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
	        <span class="page">关于我们</span>
		</div>
	</div>
	

	</div>
		<!-- Flex Slider End -->
		
		<!-- Teaser Start -->
		<div class="section" id="content" class="tag_line">

			<div class="two_third">
			    <h3>上海市浦东新区食品生产安全管理协会（简 介）</h3>
			    <img src="./images/about_img_1.png" alt="img"  style="float:left;margin-right:12px;"/>
			   <p class="about_us">&nbsp;&nbsp;&nbsp;&nbsp;上海市浦东新区食品生产安全管理协会（以下简称协会）是为适应国家不断加大食品安全监管力度、强化食品生产企业主体责任的形势而建立的行业中介组织，
			    	会员单位包含浦东新区范围内的食品生产企业、食品相关产品和化妆品生产企业。协会倡导行业管理、行业自律和企业自律，旨在整合行业力量、借助社会
			    	资源、搭建服务平台，促进浦东食品生产加工行业整体质量安全管理水平与生产能力的提高，为稳定和提升浦东食品生产行业的质量安全水平和市场秩序发
			    	挥重要作用。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;协会拥有一支精通食品及食品相关产品的质量安全、标准、计量、法律法规的资深专家队伍，
			    	对内为会员单位提供优质诚信服务，以期提升食
			    	品生产企业与浦东食品行业整体的市场竞争力；对外作为浦东食品生产行业的代言人，成为政府和企业沟通的桥梁。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;在国家大部制改革的大环境下，充分发
			    	挥在加强行业信息通报、信用体系建设、企业标准拟定等方面的作用，并配合建立对行业内违规失信食品生产经营者的批评通报制度。</p>
			</div>
			<div class="one_third_last">
			    <h3>我们的位置</h3>
			    <iframe id="dituContent" src="./we.html" frameborder="0"></iframe> 
			    
			</div>
			
			<div class="v_space clear">&nbsp;</div>
			
			<div class="two_third">
			    <h3>我们的任务</h3>
			    
			    <div class="description">
			       <p class="about_us with_arrow">引导食品及相关产品生产企业树立质量安全主体责任意识、增强食品安全法律意识，积极主动配合政府监管部门的各项工作；</p>	
			        <p class="about_us with_arrow">帮助、指导食品及相关产品生产企业推行现代企业质量安全管理制度、提高质量管理水平；</p>
			        <p class="about_us with_arrow">协调食品及相关产品生产企业与食品安全相关部门、行业之间的关系，推动食品及相关产品生产企业的整体发展；</p>
			        <p class="about_us with_arrow">参与食品及相关产品质量安全相关标准的制定，帮助企业完善标准、计量等基础管理工作；</p>
			        <p class="about_us with_arrow">办好食品及相关产品质量安全简报，普及食品及相关产品安全知识、相关法律法规，发布食品及相关产品安全信息和预警信息，加强风险信息管理；</p>
			        <p class="about_us with_arrow">开展各地区间食品及相关产品质量安全活动的交流，发展会员单位与国内外相关团体的联系与合作，促进企业参与国际市场竞争；</p>
			        <p class="about_us with_arrow">举办食品及相关产品质量安全相关的讲座、培训和咨询服务活动。</p><br />

				</div>
			    <br />
			     <h3>我们的服务</h3>
			    
			    <div class="description">
			        <p class="about_us">&nbsp;&nbsp;&nbsp;&nbsp;为满足会员单位及一部分新开办的食品企业、食品相关产品生产企业日益增长的咨询需求，协会由常设办公室牵头组织相关资深专家，提供一系列咨询服务，面向会员单位和新开办的食品生产企业，优先为会员单位服务。协会提供的咨询服务范围为：</p><br />
			        <p class="about_us with_arrow">食品生产许可证的新办、变更、年度报告、延续；</p>
			        <p class="about_us with_arrow">企业食品标准的编制、修订；</p>
			        <p class="about_us with_arrow">HACCP体系及ISO质量管理体系的相关咨询；</p>
			        <p class="about_us with_arrow">接受企业及管理部门委托开展的相关管理创新、技术服务、咨询培训等活动；</p>
			        <p class="about_us with_arrow">详见上海市浦东新区食品生产安全管理协会咨询服务项目。</p>
			        
				</div>
			    
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
