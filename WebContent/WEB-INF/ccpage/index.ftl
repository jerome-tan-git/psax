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
		<!-- <link rel="stylesheet" type="text/css" href="./css/responsive.css" /> -->

		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-migrate.js"></script>
		
		<script type="text/javascript" src="./js/jqueryslidemenu.js"></script>
		<script type="text/javascript" src="./js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="./js/custom.js"></script>
		<script type="text/javascript" src="./js/unslider.js"></script>
		<script type="text/javascript" src="./js/fancybox/jquery.fancybox-1.3.4.js"></script>

		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
		<script type="text/javascript" src="js/jquery.nailthumb.1.1.min.js"></script>
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
			
				 jQuery('.nailthumb-container').nailthumb({width:326,height:218});
				
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
		    
		</div>
		<!-- Header End -->
		
		<div class="clear"></div>
		
		<!-- Flex Slider Start -->
		<div class="flexslider">
		    <ul class="slides">
		        <li>
		            <div class="slider_content">
		                <div class="thumb_img" style="background: url('./images/slider_img_1.png') no-repeat;">&nbsp;</div>
		                <div class="description">
		                    <h1>公开办事</h1>
		                    <div class="slide_caption"><span>品质</span>和<span>服务</span>是我们最好的包装</div>
		                    <p>品质和服务是我们最好的包装 | 品质和服务是我们最好的包装 | 品质和服务是我们最好的包装</p>
		            	</div>
		            </div>
		        </li>
		        <li> 
		            <div class="slider_content">
		                <div class="thumb_img" style="background: url('./images/slider_img_2.png') no-repeat;">&nbsp;</div>
		                <div class="description">
		                    <h1>实时动态</h1>
		                    <div class="slide_caption">致力于为您不断提供<span>更好更新的</span>服务</div>
		                    <p>致力于为您不断提供更好更新的服务 | 致力于为您不断提供更好更新的服务 </p>
		                </div>
		            </div>
		        </li>
		        <li>
		            <div class="slider_content">
		                <div class="thumb_img" style="background: url('./images/slider_img_3.png') no-repeat;">&nbsp;</div>
		                <div class="description">
		                    <h1>最新政策</h1>
		                    <div class="slide_caption">我们提供<span>准确</span>和<span>最新</span>的信息</div>
		                    <p>我们提供准确和最新的信息 | 我们提供准确和最新的信息 | 我们提供准确和最新的信息</p>
		                </div>
		            </div>
		        </li>
		    </ul>
		</div>
		<!-- Flex Slider End -->
		<!-- Content Start -->
		<div id="content">
		
		<div class="teaser_container clearfix">
		<div class="one_third">
		    <div class="teaser_box">
		        <div style="margin-bottom: 20px">
		        	<img src="./images/services_icon_1.png" alt="img" />
		        </div>
		        <h3>资料下载</h3>
		        <p>In hac habitasse platea dictumst. Aliquam in est leo. Aliquam ut 
		        urna pulvinar ipsum ultricies fringilla et sed magna. Duis faucibus 
		        lorem at eros cursus fermentum.</p><br />
		        <a href="#" class="button">更多</a>
		    </div>
		</div>
		    
		<div class="one_third">
		    <div class="teaser_box">
		        <div style="margin-bottom: 20px">
		        	<img src="./images/services_icon_2.png" alt="img" />
		        </div>
		        <h3>我们的服务</h3>
		        <p>In hac habitasse platea dictumst. Aliquam in est leo. Aliquam ut 
		        urna pulvinar ipsum ultricies fringilla et sed magna. Duis faucibus 
		        lorem at eros cursus fermentum.</p><br />
		        <a href="#" class="button">更多</a>
		    </div>
		</div>
		
		<div class="one_third_last">
		    <div class="teaser_box">
		        <div style="margin-bottom: 20px">
		        	<img src="./images/services_icon_3.png" alt="img" />
		        </div>
		        <h3>我们的团队</h3>
		        <p>In hac habitasse platea dictumst. Aliquam in est leo. Aliquam ut 
		        urna pulvinar ipsum ultricies fringilla et sed magna. Duis faucibus 
		        lorem at eros cursus fermentum.</p><br />
		        <a href="#" class="button">更多</a>
		    </div>
		</div>
		</div>
		
		
		
		<div class="v_space"></div>
		
		<div class="title_holder">
		    <h3><span>最新资料</span></h3>
		</div>
		
		<div class="demo">
		<ul id="list" class="image-grid_3col">
		 	<#if newslist?exists>
		    	<#list newslist as news>
		    	 <li><a href="./detailArt.action?articleid=${news.id}">
			        <div class="portfolio_content">
			        <div class="nailthumb-container">
			            <img src="${news.pic}" alt="img" />
			            </div>
			            <h4>${news.title}</h4>
			            
			            <div class="link_btn">
			                <a id="example6" href="${news.pic}" class="zoom"></a>
			                
			                <div class="overlay"></div>
			            </div>
			            
			        </div></a>
			    </li>
		    	</#list>
		    <#else>	
		    <li>
		        <div class="portfolio_content">
		            <img src="./images/portfolio_3col_img_1.png" alt="img" />
		            <h4>Graphic Card</h4>
		            <p><a href="./portfolio_single_page.html">gravida vulputate</a></p>
		            <div class="link_btn">
		                <a id="example6" href="./images/portfolio_2col_img_1.png" class="zoom"></a>
		                <a href="./portfolio_single_page.html" class="link_post"></a>
		                <div class="overlay"></div>
		            </div>
		        </div>
		    </li>
		    <li>
		        <div class="portfolio_content">
		            <img src="./images/portfolio_3col_img_2.png" alt="img" />
		            <h4>Flickr Visualisation</h4>
		            <p><a href="./portfolio_single_page.html">hendrerit cursus</a></p>
		            <div class="link_btn">
		                <a id="example6" href="./images/portfolio_2col_img_2.png" class="zoom"></a>
		                <a href="./portfolio_single_page.html" class="link_post"></a>
		                <div class="overlay"></div>
		            </div>
		        </div>
		    </li>
		    <li>
		        <div class="portfolio_content">
		            <img src="./images/portfolio_3col_img_3.png" alt="img" />
		            <h4>Media Identity</h4>
		            <p><a href="./portfolio_single_page.html">Tristique</a></p>
		            <div class="link_btn">
		                <a id="example6" href="./images/portfolio_2col_img_3.png" class="zoom"></a>
		                <a href="./portfolio_single_page.html" class="link_post"></a>
		                <div class="overlay"></div>
		            </div>
		        </div>
		    </li>
			</#if>
		</ul>
		</div>
		<div class="v_space clear"></div>
		
		<div class="title_holder">
		    <h3><span>今日头条</span></h3>
		</div>
		<div class="banner">
			
		    <ul>
		    <#if artlist?exists>
		    	<#list artlist as article>		    
		    	 <li>
		        	<div class="row_news">
			        	<p>${article.absinfo}</p>
			        	<span></span>
		        	</div>
		        </li>
		       </#list>
		    <#else>
		        <li>
		        	<div class="row_news">
			        	<p>【食源性疾病成我国头号食品安全问题】卫生部食品安全风险评估重点实验室抽样调查表明，
			        	食源性疾病是目前我国头号食品安全问题。调查人群的食源性疾病发病次数为0.157次每人
			        	每年，即每6人中有1人在过去一年中曾发生食源性疾病。食源性疾病高峰在夏季，主要临床
			        	症状是急性胃肠炎</p>
			        	<span>-- 新华网</span>
		        	</div>
		        </li>
		        <li>
		        	<div class="row_news">
			        	 <p>不久前有经济学者在电视访谈节目中称，转基因玉米让老鼠长肿瘤、美国连牲畜都被禁止喂食
			        	转基因饲料。转基因食品(行情 专区)的安全性问题再度成为社会焦点。转基因食品致癌或让
			        	人“绝育无后”？老外不吃转基因食品？对此，记者采访了部分国内外生物技术专家及业内人士，
			        	求证有关热点问题。</p>
			        	<span>-- 东方财富网</span>
		        	</div>
				</li>
		        <li>
		        	<div class="row_news">
			        	【深圳商报讯】（记者 黄青山 实习生 邹鹏）福田皇冠配餐中心的经营模式，为解决都市人用餐
			        	的“老大难”问题提供了有力参考，其食品安全管理也是一大亮点。18日，皇冠配餐中心举行“金
			        	百味食品安全内部培训月”活动，对100多名金百味员工进行专业的食品安全培训，周明明、唐雪
			        	梅两位深圳市人大代表和深圳市市场监督管理局福田分局相关工作人员，也利用此次机会进行实
			        	地参观和考察</p>
			        	<span>-- 网易</span>
		        	</div>
				</li>
			</#if>
		    </ul>

		</div>
		</div>
		
		<!-- Content End -->
		
		<!-- Bottom Section Start -->
		<#include "../commons/footertest.ftl">
		<!-- Bottom Section End -->
		
		
		</div>
	</body>
</html>
