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
				
				$(".exam_wrong").click(function(){
					var targetText = $.trim($(this).text()); 
					
					if($(".right_answer").outerHeight()>41)
					{
						$(".right_answer").animate({
								height : 0 + "px"
						}, 1000, "easeOutExpo", function()
						{
							$(".exam_no").text(targetText+". ");
							var targetHright = $(".right_outer").outerHeight();
							$(".right_answer").delay(500).animate({
								height : targetHright + "px"
							}, 1000, "easeOutExpo");
						});
					}
					else
					{
						$(".exam_no").text(targetText+". ");
						var targetHright = $(".right_outer").outerHeight();
						$(".right_answer").delay(500).animate({
							height : targetHright + "px"
						}, 1000, "easeOutExpo");
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
							��ҳ
						</div>
					</div></a>

					<div class="menu_item">
						<div class="menu_text">
							��������
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<a href="./about_us.html">
							<div class="list_item intro">
								Э����
							</div></a>
							<div class="list_item pen">
								���¼�
							</div>
						</div>
					</div>

					<div class="menu_item">
						<div class="menu_text">
							���鶯̬
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<div class="list_item target">
								����ȵ�
							</div>
							<div class="list_item news">
								ʵʱ����
							</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							��������
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<div class="list_item menu">���������ѯ</div>
							<div class="list_item paper">��ҵ��׼����</div>
							<div class="list_item note">ʳƷ��Ӽ�����</div>
							<div class="list_item case">ί�мӹ�����</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							������ѵ
						</div>
						<div class="menu_list">
							<div class="menu_list_header" ></div>
							<div class="list_item meeting">
								����
							</div>

							<div class="list_item help">
								��ѵ
								<div class="sub_menu">
									<div class="list_item exam">
										���߿���
									</div>
									<div class="list_item download">
										��������
									</div>
								</div>
							</div>
							<div class="list_item meeting">
								ʵ����Ƶ
							</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							���߷���
						</div>
						<div class="menu_list">
							<div class="menu_list_header"></div>
							<div class="list_item shield">
								��ҵ����
							</div>
							<div class="list_item arrow">
								���ߵ���
							</div>
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							����ƽ̨
						</div>
					</div>
					<div class="menu_item">
						<div class="menu_text">
							BBS��̳
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
								��ҳ
							</div> </a>
							<a href="./about_us.html">
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								��������
							</div> </a>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								���鶯̬
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								�������� | ����Ʒ����
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								�������� | ��׼����
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								�������� | ��Ӽ�����
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								�������� | ί�мӹ�����
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								���߷���
							</div>
							<div class="small_menu_item">
								<div class="small_menu_image">
									<img src="./images/1382294136_playback_play.png" />
								</div>
								������ѵ
							</div>
						</div>
					</div>

				</div>
				<!-- <nav>
				<div id="myslidemenu" class="jqueryslidemenu">
				<ul>
				<li><a class="active" href="./index.html">��ҳ</a></li>
				<li><a href="./about.html">��������</a></li>
				<li><a href="./index.html">���鶯̬</a></li>

				<li><a href="./portfolio.html">��������</a>
				<ul>
				<li><a href="./portfolio_3col.html">����Ʒ����</a></li>
				<li><a href="./portfolio_3col.html">��׼����</a></li>
				<li><a href="./portfolio_2col.html">��Ӽ�����</a></li>
				<li><a href="./portfolio_1col.html">ί�мӹ�����</a></li>
				</ul>
				</li>
				<li><a href="./blog.html">���߷���</a></li>
				<li><a href="./contact.html">��ѯ����</a></li>
				</ul>
				</div>
				</nav> -->
			</div>
			<!-- Header End -->

			<div class="clear"></div>

			<!-- Flex Slider Start -->
			<div class="sub_header  exam_bg_result">

				<div class="sub_header_title">
					<h2>���߿���</h2>
					<div class="sub_header_description">
						<span><a href="./index.html">��ҳ &raquo;</a></span>
						<span class="page">��ѵ&nbsp;&raquo;</span>
						<span class="page">���߿���</span>
					</div>

				</div>
				<div class="exam_type">
					<span style="color:#000000;font-size: 20pt; font-family: '����';";>����</span>&nbsp;&nbsp;&nbsp;&nbsp;<span></span>
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">

				<div style="margin-top: 20px;" class="clearfix">
					<div>
						<div style="float: right;font-size: 33pt;padding-right: 50px;"><span>89</span><span style="font-size: 15pt">��</span></div>
						<div class="clearfix" style="padding-top: 19px;">
							<div class="exams">
								<div class="exams_title">
									��ѡ�⣺
								</div>
								<div class="clearfix exam_no_body">
									<div class="exam_right exams_no exam_right">
										1
									</div>
									<div class="exam_right exams_no exam_wrong">
										2
									</div>
									<div class="exam_right exams_no exam_right">
										3
									</div>
									<div class="exam_right exams_no exam_right">
										4
									</div>
									<div class="exam_right exams_no exam_right">
										5
									</div>
									<div class="exam_right exams_no exam_right">
										6
									</div>
									<div class="exam_right exams_no exam_right">
										7
									</div>
									<div class="exam_right exams_no exam_wrong">
										8
									</div>
									<div class="exam_right exams_no exam_right">
										9
									</div>
									<div class="exam_right exams_no exam_wrong">
										10
									</div>
									<div class="exam_right exams_no exam_right">
										11
									</div>
									<div class="exam_right exams_no exam_right">
										12
									</div>
									<div class="exam_right exams_no exam_right">
										13
									</div>
									<div class="exam_right exams_no exam_wrong">
										14
									</div>
									<div class="exam_right exams_no exam_wrong">
										15
									</div>
									<div class="exam_right exams_no exam_right">
										16
									</div>
									<div class="exam_right exams_no exam_right">
										17
									</div>
									<div class="exam_right exams_no exam_right">
										18
									</div>
									<div class="exam_right exams_no exam_right">
										19
									</div>
									<div class="exam_right exams_no exam_right">
										20
									</div>
								</div>
							</div>
							<div class="exams">
								<div class="exams_title">
									��ѡ�⣺
								</div>
								<div class="clearfix exam_no_body">
																		<div class="exams_no exam_right exam_right">
										21
									</div>
									<div class="exams_no exam_right exam_right">
										22
									</div>
									<div class="exams_no exam_right exam_right">
										23
									</div>
									<div class="exams_no exam_right exam_right">
										24
									</div>
									<div class="exams_no exam_right exam_right">
										25
									</div>
									<div class="exams_no exam_right exam_right">
										26
									</div>
									<div class="exams_no exam_right exam_right">
										27
									</div>
									<div class="exams_no exam_right exam_right">
										28
									</div>
									<div class="exams_no exam_right exam_right">
										29
									</div>
									<div class="exams_no exam_right exam_right">
										30
									</div>
									<div class="exams_no exam_right exam_right">
										31
									</div>
									<div class="exams_no exam_right exam_right">
										32
									</div>
									<div class="exams_no exam_right exam_right">
										33
									</div>
									<div class="exams_no exam_right exam_right">
										34
									</div>
									<div class="exams_no exam_right exam_right">
										35
									</div>
									<div class="exams_no exam_right exam_right">
										36
									</div>
									<div class="exams_no exam_right exam_right">
										37
									</div>
									<div class="exams_no exam_right exam_right">
										38
									</div>
									<div class="exams_no exam_right exam_right">
										39
									</div>
									<div class="exams_no exam_right exam_right">
										40
									</div>
									<div class="exams_no exam_right exam_right">
										41
									</div>
									<div class="exams_no exam_right exam_right">
										42
									</div>
									<div class="exams_no exam_right exam_right">
										43
									</div>
									<div class="exams_no exam_right exam_right">
										44
									</div>
									<div class="exams_no exam_right exam_right">
										45
									</div>
									<div class="exams_no exam_right exam_right">
										46
									</div>
									<div class="exams_no exam_right exam_right">
										47
									</div>
									<div class="exams_no exam_right exam_right">
										48
									</div>
									<div class="exams_no exam_right exam_right">
										49
									</div>
									<div class="exams_no exam_right exam_right">
										50
									</div>
									<div class="exams_no exam_right exam_right">
										51
									</div>
									<div class="exams_no exam_right exam_right">
										52
									</div>
									<div class="exams_no exam_right exam_right">
										53
									</div>
									<div class="exams_no exam_right exam_right">
										54
									</div>
									<div class="exams_no exam_right exam_right">
										55
									</div>
									<div class="exams_no exam_right exam_right">
										56
									</div>
									<div class="exams_no exam_right exam_right">
										57
									</div>
									<div class="exams_no exam_right exam_right">
										58
									</div>
									<div class="exams_no exam_right exam_right">
										59
									</div>
									<div class="exams_no exam_right exam_right">
										60
									</div>
									
									<div class="exams_no exam_right exam_right">
										61
									</div>
									<div class="exams_no exam_right exam_right">
										62
									</div>
									<div class="exams_no exam_right exam_right">
										63
									</div>
									<div class="exams_no exam_right">
										64
									</div>
									<div class="exams_no exam_right">
										65
									</div>
									<div class="exams_no exam_right">
										66
									</div>
									<div class="exams_no exam_right">
										67
									</div>
									<div class="exams_no exam_right">
										68
									</div>
									<div class="exams_no exam_right">
										69
									</div>
									<div class="exams_no exam_right">
										70
									</div>
									
									<div class="exams_no exam_right">
										71
									</div>
									<div class="exams_no exam_right">
										72
									</div>
									<div class="exams_no exam_right">
										73
									</div>
									<div class="exams_no exam_right">
										74
									</div>
									<div class="exams_no exam_right">
										75
									</div>
									<div class="exams_no exam_right">
										76
									</div>
									<div class="exams_no exam_right">
										77
									</div>
									<div class="exams_no exam_wrong">
										78
									</div>
									<div class="exams_no exam_wrong">
										79
									</div>
									<div class="exams_no exam_right">
										80
									</div>
								</div>
							</div>
							<div class="exams">
								<div class="exams_title">
									�Ƿ��⣺
								</div>
								<div class="clearfix exam_no_body">
									<div class="exams_no exam_wrong ">
										81
									</div>
									<div class="exams_no exam_right ">
										82
									</div>
									<div class="exams_no exam_right ">
										83
									</div>
									<div class="exams_no exam_right ">
										84
									</div>
									<div class="exams_no exam_right ">
										85
									</div>
									<div class="exams_no exam_right ">
										86
									</div>
									<div class="exams_no exam_right ">
										87
									</div>
									<div class="exams_no exam_wrong ">
										88
									</div>
									<div class="exams_no exam_right ">
										89
									</div>
									<div class="exams_no exam_wrong ">
										90
									</div>
									<div class="exams_no exam_right ">
										91
									</div>
									<div class="exams_no exam_right ">
										92
									</div>
									<div class="exams_no exam_right ">
										93
									</div>
									<div class="exams_no exam_right ">
										94
									</div>
									<div class="exams_no exam_wrong ">
										95
									</div>
									<div class="exams_no exam_right ">
										96
									</div>
									<div class="exams_no exam_right ">
										97
									</div>
									<div class="exams_no exam_right ">
										99
									</div>
									<div class="exams_no exam_right ">
										99
									</div>
									<div class="exams_no exam_right ">
										100
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="right_answer">
					<div class="right_outer clearfix">
					<div class="exam_section">
						<div class="exam_title">
							<span class="exam_no">3. </span><span style="color: #000">�йؾ��ܶ���׼ȷ�ȵĹ�ϵ�������У�����ȷ����(&nbsp;&nbsp;&nbsp;&nbsp;)��</span>
						</div>
						<div class="exam_options">
							<input type="radio" name="sex2" id="o_21" checked/>
							<label for="o_21" class="o_21">���ܶ��Ǳ�֤׼ȷ�ȵ��Ⱦ�����</label>
						</div>
						<div class="exam_options">
							<input type="radio" name="sex2" id="o_22" />
							<label for="o_22" class="o_22">���ܶȡ�׼ȷ�Ⱥ�����������Ŀɿ�����һ�µ�</label>
						</div>
						<div class="exam_options">
							<input type="radio" name="sex2" id="o_23" />
							<label for="o_23" class="o_23">���ܶȸߵĲⶨ�������һ����׼ȷ��</label>
						</div>
						<div class="exam_options">
							<input type="radio" name="sex2" id="o_24" />
							<label for="o_24" class="o_24">���ܶȲ�������һ�����ɿ�</label>
						</div>
					</div>
					<div class="right_title">
						��ȷ�𰸣�<span class="right_option">C,D</span>
					</div>
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
						<h3>Э�� <span>���</span></h3>
						<img src="./images/about_me_img.png" alt="img" class="alignleft" />
						<p style="font-size:12px">
							�Ϻ����ֶ�����ʳƷ������ȫ����Э����Ϊ��Ӧ���Ҳ��ϼӴ�ʳƷ��ȫ������ȡ�
							ǿ��ʳƷ������ҵ�������ε����ƶ���������ҵ�н���֯����Ա��λ�����ֶ�������Χ�ڵ�ʳƷ������ҵ��
							ʳƷ��ز�Ʒ�ͻ�ױƷ������ҵ��Э�ᳫ����ҵ������ҵ���ɺ���ҵ���ɣ�ּ��������ҵ������
							���������Դ�������ƽ̨���ٽ��ֶ�ʳƷ�����ӹ���ҵ����������ȫ����ˮƽ��������������ߣ�
							Ϊ�ȶ��������ֶ�ʳƷ������ҵ��������ȫˮƽ���г����򷢻���Ҫ���á�
						</p>
						<a href="#">-- ����</a>
					</div>
				</div>

				<div class="one_fourth">
					<div class="widget_container">
						<h3>��ϵ <span>����</span></h3>
						<!-- <p>In hac habitasse platea dictumst. Aliquam in est leo.</p> -->
						<ul class="contact">
							<li>
								<strong>�����ʼ�</strong>
								<br />
								<a href="#">XXXX@XXXXX.com</a>
							</li>
							<li>
								<strong>�绰</strong>
								<br />
								<p>
									+(86)-021-61183721/61183273
								</p>
							</li>
							<li>
								<strong>��ַ</strong>
								<br />
								<p>
									�Ϻ����ֶ���������������·2388ŪB901��
								</p>
							</li>
						</ul>
					</div>
				</div>

				<div class="one_fourth">
					<div class="widget_container">
						<h3>��ɫ <span>����</span></h3>
						<!-- <p>Learn more about the Theme template features</p> -->
						<ul class="userlinks">
							<li>
								<a href="#">����Ʒ����</a>
							</li>
							<li>
								<a href="#">��׼����</a>
							</li>
							<li>
								<a href="#">��Ӽ�����</a>
							</li>
							<li>
								<a href="#">ί�мӹ�����</a>
							</li>
							<li>
								<a href="#">��������</a>
							</li>
						</ul>
					</div>
				</div>

				<div class="one_fourth_last">
					<div class="widget_container">
						<h3>���ǵ� <span>�Ŷ�</span></h3>
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
 