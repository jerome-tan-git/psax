<#--
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
 -->
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
				jQuery("a#example6").fancybox({
					'titlePosition' : 'outside',
					'overlayColor' : '#000',
					'overlayOpacity' : 0.9
				});
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
				<div class="menubar clearfix">

					<a href="./index.html">
					<div class="menu_item">
						<div class="menu_text">
							首页
						</div>
					</div></a>

					<div class="menu_item">
						<div class="menu_text">
							关于我们
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<a href="./about_us.html">
							<div class="list_item intro">
								协会简介
							</div></a>
							<div class="list_item pen">
								大事记
							</div>
						</div>
					</div>

					<div class="menu_item">
						<div class="menu_text">
							舆情动态
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<div class="list_item target">
								社会热点
							</div>
							<div class="list_item news">
								实时新闻
							</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							公开办事
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<div class="list_item menu">生产许可咨询</div>
							<div class="list_item paper">企业标准备案</div>
							<div class="list_item note">食品添加剂备案</div>
							<div class="list_item case">委托加工备案</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							网上培训
						</div>
						<div class="menu_list">
							<div class="menu_list_header" ></div>
							<div class="list_item meeting">
								会议
							</div>

							<div class="list_item help">
								培训
								<div class="sub_menu">
									<div class="list_item exam">
										在线考试
									</div>
									<div class="list_item download">
										资料下载
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							政策法规
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<div class="list_item shield">
								行业法规
							</div>
							<div class="list_item arrow">
								政策导向
							</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							供需平台
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							BBS论坛
						</div>
					</div>
				</div>
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
					<h2>在线考试</h2>
					<div class="sub_header_description">
						<span><a href="./index.html">首页 &raquo;</a></span>
						<span class="page">培训&nbsp;&raquo;</span>
						<span class="page">在线考试</span>
					</div>

				</div>
				<div class="exam_type">
					<span style="color:#000000;font-size: 20pt; font-family: '宋体';";>
					<#if Session.user_name_input?exists>
						${Session.user_name_input}
					<#elseif Session.user_?exists>
						<#assign user=Session.user_>
						${user.name}
					<#else>
						未命名
					</#if>
					</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><img class="click_down" src="./images/down.png" /></span>
				</div>
				<div class="clearfix">
					<div class="exam_no_outer">
						<div class="exam_no_container clearfix">
						<#if Session.elist?exists>
							<#if Session.answerProgress?exists>
								<#assign answerProgress=Session.answerProgress?size>
								<div class="exams">
									<#list Session.elist as item>
										<#assign seqitem=item_index+1>
										<#if (seqitem==Session.c1hasTitle) >	
											<div class="exams_title">
												是非题：
											</div>
											<div class="clearfix exam_no_body">
												<#list Session.elist as item1>
													<#assign seqitem1=item1_index+1>
													<#if (seqitem1>=Session.c1hasTitle && seqitem1<Session.c2hasTitle ) >
														<#if (seqitem1<=answerProgress) >
															<#list Session.answerProgress as isdone>  <#-- 可优化 -->
																<#assign anseq=isdone_index+1>
																<#if (anseq==seqitem1) >
																	<#if (isdone==0) >
																		<div class="exams_no">
																	<#else>
																		<div class="exam_done exams_no">		
																	</#if>
																	<#break>
																</#if>						
															</#list>
														<#else>
															<div class="exams_no">
														</#if>
																${seqitem1}
															</div>	
													</#if>
												</#list>
											</div>
										<#elseif (seqitem==Session.c2hasTitle)>
											<div class="exams_title">
												单选题：
											</div>
											<div class="clearfix exam_no_body">
												<#list Session.elist as item1>
													<#assign seqitem1=item1_index+1>
													<#if (seqitem1>=Session.c2hasTitle && seqitem1<Session.c3hasTitle ) >
														<#if (seqitem1<=answerProgress) >
															<#list Session.answerProgress as isdone>  <#-- 可优化 -->
																<#assign anseq=isdone_index+1>
																<#if (anseq==seqitem1) >
																	<#if (isdone==0) >
																		<div class="exams_no">
																	<#else>
																		<div class="exam_done exams_no">		
																	</#if>
																	<#break>
																</#if>						
															</#list>
														<#else>
															<div class="exams_no">
														</#if>
																${seqitem1}
															</div>		
													</#if>
												</#list>
											</div>
										<#elseif (seqitem==Session.c3hasTitle)>
											<div class="exams_title">
												复选题：
											</div>
											<div class="clearfix exam_no_body">
												<#list Session.elist as item1>
													<#assign seqitem1=item1_index+1>
													<#if (seqitem1>=Session.c3hasTitle) >
														<#if (seqitem1<=answerProgress) >
															<#list Session.answerProgress as isdone>  <#-- 可优化 -->
																<#assign anseq=isdone_index+1>
																<#if (anseq==seqitem1) >
																	<#if (isdone==0) >
																		<div class="exams_no">
																	<#else>
																		<div class="exam_done exams_no">		
																	</#if>
																	<#break>
																</#if>						
															</#list>
														<#else>
															<div class="exams_no">
														</#if>
																${seqitem1}
															</div>	
													</#if>
												</#list>
											</div>
										</#if>					
									</#list>

								</div>
								
							</#if>
						</#if>	
						</div>
					</div>
				</div>
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">

				<div class="three_third">
					<div style="text-align: right">
						<span class="exam_page">${Session ["pi"]} </span>/${Session ["totalpi"]} 
					</div>
					<form method="post" action="submit.action">					
					<#if Session.pageilf?exists>
						<#list Session.pageilf as map>
							<#assign keys=map?keys>	
							<#assign seq=map_index+1>												
							<#list keys as key>
								<#if map[key]?exists>
									<div class="exam_section">	
										<div class="exam_title">
											<span class="exam_no">${seq}. </span>
											<span style="color: #000">${key}</span>
										</div>
										
										<#assign refs=map[key]>
										<#list refs as ref>
											<div class="exam_options">			
												<#if (seq<Session.c3hasTitle) >
													<#if Session.chosenRefIds?exists>
														<#if (Session.chosenRefIds?seq_index_of(ref.id?c)>=0)>							
															<input type="radio" name="ANS_${seq}" value="${ref.id}" id="o_${ref.id}" checked/>
														<#else>	
															<input type="radio" name="ANS_${seq}" value="${ref.id}" id="o_${ref.id}" />
														</#if>														
													<#else>
														<input type="radio" name="ANS_${seq}" value="${ref.id}" id="o_${ref.id}" />
													</#if>
												<#else>
													<#if Session.chosenRefIds?exists>
														<#if (Session.chosenRefIds?seq_index_of(ref.id?c)>=0)>
															<input type="checkbox" name="ANS_${seq}" value="${ref.id}" id="o_${ref.id}" checked/>	
														<#else>	
															<input type="checkbox" name="ANS_${seq}" value="${ref.id}" id="o_${ref.id}" />
														</#if>	
													<#else>
														<input type="checkbox" name="ANS_${seq}" value="${ref.id}" id="o_${ref.id}" />
													</#if>
												</#if>
												<label for="o_${ref.id}" class="o_${ref.id}">${ref.ref}</label>
											</div>
										</#list>								
									</div>
								</#if>
							</#list>
						</#list>
					</#if>
					<#if (Session.pi<Session.totalpi) >
					   	<div class="exam_submit"><input type="submit" name="next" value="下一页"/></div>
					<#else>
					   	<div class="exam_submit"><input type="submit" name="next" value="提交"/></div>
					</#if>
  						<div class="exam_submit"><input type="reset" value="重写"/></div>
  						<!-- <a href="./finalizexam.action"><div class="exam_submit">结束考试</div></a> -->
  						<div class="exam_submit"><input type="submit" name="next" value="结束考试"/></div>
					</form>
					<div class="exam_submit">
						<a href="./switchpage.action?pagenumber=1">1</a>
						<a href="./switchpage.action?pagenumber=2">2</a>
						<a href="./switchpage.action?pagenumber=3">3</a>
						<a href="./switchpage.action?pagenumber=4">4</a>
						<a href="./switchpage.action?pagenumber=5">5</a>
					</div>
				</div>

			</div>
			<!-- Teaser End -->

			<!-- Content Start -->

			<!-- Content End -->

			<!-- Bottom Section Start -->
			<div class="footer">
				<div class="one_fourth">
					<div class="widget_container">
						<h3>协会 <span>简介</span></h3>
						<img src="./images/about_me_img.png" alt="img" class="alignleft" />
						<p style="font-size:12px">
							上海市浦东新区食品生产安全管理协会是为适应国家不断加大食品安全监管力度、
							强化食品生产企业主体责任的形势而建立的行业中介组织，会员单位包含浦东新区范围内的食品生产企业、
							食品相关产品和化妆品生产企业。协会倡导行业管理、行业自律和企业自律，旨在整合行业力量、
							借助社会资源、搭建服务平台，促进浦东食品生产加工行业整体质量安全管理水平与生产能力的提高，
							为稳定和提升浦东食品生产行业的质量安全水平和市场秩序发挥重要作用。
						</p>
						<a href="#">-- 更多</a>
					</div>
				</div>

				<div class="one_fourth">
					<div class="widget_container">
						<h3>联系 <span>我们</span></h3>
						<!-- <p>In hac habitasse platea dictumst. Aliquam in est leo.</p> -->
						<ul class="contact">
							<li>
								<strong>电子邮件</strong>
								<br />
								<a href="#">XXXX@XXXXX.com</a>
							</li>
							<li>
								<strong>电话</strong>
								<br />
								<p>
									+(86)-021-61183721/61183273
								</p>
							</li>
							<li>
								<strong>地址</strong>
								<br />
								<p>
									上海市浦东新区康桥镇秀浦路2388弄B901室
								</p>
							</li>
						</ul>
					</div>
				</div>

				<div class="one_fourth">
					<div class="widget_container">
						<h3>特色 <span>服务</span></h3>
						<!-- <p>Learn more about the Theme template features</p> -->
						<ul class="userlinks">
							<li>
								<a href="#">肉制品报告</a>
							</li>
							<li>
								<a href="#">标准备案</a>
							</li>
							<li>
								<a href="#">添加剂报告</a>
							</li>
							<li>
								<a href="#">委托加工备案</a>
							</li>
							<li>
								<a href="#">资料下载</a>
							</li>
						</ul>
					</div>
				</div>

				<div class="one_fourth_last">
					<div class="widget_container">
						<h3>我们的 <span>团队</span></h3>
						<div class="flickr_widget">
							<img src="./images/flickr_img_1.png" alt="img" />
							<img src="./images/flickr_img_2.png" alt="img" />
							<img src="./images/flickr_img_3.png" alt="img" />
							<img src="./images/flickr_img_4.png" alt="img" />
							<img src="./images/flickr_img_5.png" alt="img" />
							<img src="./images/flickr_img_6.png" alt="img" />
							<img src="./images/flickr_img_7.png" alt="img" />
							<img src="./images/flickr_img_8.png" alt="img" />
							<img src="./images/flickr_img_9.png" alt="img" />
						</div>
					</div>
				</div>

				<div class="clear"></div>

			</div>
			<!-- Bottom Section End -->

		</div>
	</body>

</html>
