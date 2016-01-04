<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.bjsxt.com" prefix="sxt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<base href="<%=basePath%>">
		<title>北京尚学堂后台首页</title>
		<link rel="stylesheet" href="css/common.css">
	    <link rel="stylesheet" href="css/style.css">
	    <script type="text/javascript" src="js/jquery.min.js"></script>
	    <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".sideMenu").slide( {
					titCell : "h3",
					targetCell : "ul",
					defaultIndex : 0,
					effect : 'slideDown',
					delayTime : '500',
					trigger : 'click',
					triggerTime : '150',
					defaultPlay : true,
					returnDefault : false,
					easing : 'easeInQuint',
					endFun : function() {
						scrollWW();
					}
				});
				$(window).resize(function() {
					scrollWW();
				});
				
				$(".classon").click(function(){
					$("li").removeClass("on");
					this.parentNode.className="on";
				});
			});
			function scrollWW() {
				if ($(".side").height() < $(".sideMenu").height()) {
					$(".scroll").show();
					var pos = $(".sideMenu ul:visible").position().top - 38;
					$('.sideMenu').animate( {
						top : -pos
					});
				} else {
					$(".scroll").hide();
					$('.sideMenu').animate( {
						top : 0
					});
					n = 1;
				}
			}
			
			var n = 1;
			function menuScroll(num) {
				var Scroll = $('.sideMenu');
				var ScrollP = $('.sideMenu').position();
				/*alert(n);
				alert(ScrollP.top);*/
				if (num == 1) {
					Scroll.animate( {
						top : ScrollP.top - 38
					});
					n = n + 1;
				} else {
					if (ScrollP.top > -38 && ScrollP.top != 0) {
						ScrollP.top = -38;
					}
					if (ScrollP.top < 0) {
						Scroll.animate( {
							top : 38 + ScrollP.top
						});
					} else {
						n = 1;
					}
					if (n > 1) {
						n = n - 1;
					}
				}
			}
			/**
			 * 退出系统
			 */
			function doUserLogout(){
				var flag = window.confirm("请问您要退出系统吗?");
				if(flag){
					window.location.href="UserController?opertype=logout";
				}
			}
		</script>
	</head>
	<body>
		<div class="top">
			<div id="top_t">
				<div id="logo" class="fl"></div>
				<div id="photo_info" class="fr">
					<div id="photo" class="fl">
						<a href="default.jsp" target="right"><img src="images/a.png" alt="" width="60" height="60"> </a>
					</div>
					<div id="base_info" class="fr">
						<div class="help_info">
							<a href="javascript:void(0);" id="hp">&nbsp;</a>
							<a href="javascript:void(0);" id="gy">&nbsp;</a>
							<a href="javascript:void(0);" onclick="doUserLogout();" id="out">&nbsp;</a>
						</div>
						<div class="info_center">
							${user.nickname}
							<span id="nt">通知</span><span><a href="#" id="notice">3</a> </span>
						</div>
					</div>
				</div>
			</div>
			<div id="side_here">
				<div id="side_here_l" class="fl"></div>
				<div id="here_area" class="fl">
					当前位置：<span id="dqwzSpan"></span>
				</div>
			</div>
		</div>
		<div class="side">
			<div class="sideMenu" style="margin: 0 auto">
				<sxt:power powerNum="100">
					<h3>
						用户信息管理
					</h3>
					<ul>
						<li>
							<a href="UserController?opertype=showAll" target="right" class="classon">用户信息</a>
						</li>
						<li>
							<a href="user/register.jsp" target="right" class="classon">用户新增</a>
						</li>
						<li>
							<a href="user/updatePwd.jsp" target="right" class="classon">修改密码</a>
						</li>
					</ul>
				</sxt:power>
				<sxt:power powerNum="100200">
					<h3>
						区划信息管理
					</h3>
					<ul>
						<li>
							<a href="AddressController?opertype=search&parentid=0&parentName=中国" target="right" class="classon">省级信息</a>
						</li>
					</ul>
					<h3>
						班级信息管理
					</h3>
				</sxt:power>
				<sxt:power powerNum="100200">
					<ul>
						<li>
							<a href="UserController?opertype=showAll" target="right" class="classon">班级信息</a>
						</li>
					</ul>
				</sxt:power>
				<sxt:power powerNum="100200">
					<h3>
						教师信息管理
					</h3>
					<ul>
						<li>
							<a href="teacher/index.jsp" target="right" class="classon">教师信息</a>
						</li>
						<li>
							<a href="teacher/add.jsp" target="right" class="classon">教师新增</a>
						</li>
						<li>
							<a href="teacher/fusionchar.jsp" target="right" class="classon">数据统计</a>
						</li>
					</ul>
				</sxt:power>
				<sxt:power powerNum="100200300">
					<h3>
						学生信息管理
					</h3>
					<ul>
						<li>
							<a href="UserController?opertype=showAll" target="right" class="classon">学生信息</a>
						</li>
					</ul>
				</sxt:power>
				<sxt:power powerNum="100">
					<h3>
						日志信息管理
					</h3>
					<ul>
						<li>
							<a href="NoteController?opertype=showAll" target="right" class="classon">日志信息</a>
						</li>
					</ul>
				</sxt:power>
				<sxt:power powerNum="100">
					<h3>
						局部刷新技术
					</h3>
					<ul>
						<li>
							<a href="user/add.jsp" target="right" class="classon">唯一验证</a>
						</li>
						<li>
							<a href="MovieController?opertype=showRoom&room=a" target="right" class="classon">售票系统</a>
						</li>
						<li>
							<a href="address/pct.jsp" target="right" class="classon">三级联动</a>
						</li>
						<li>
							<a href="user/gs.jsp" target="right" class="classon">谷歌提示</a>
						</li>
					</ul>
				</sxt:power>
			</div>
		</div>
		<div class="main">
			<iframe name="right" id="rightMain" src="default.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>
		</div>
		<div class="bottom">
	      <div id="bottom_bg">版权所有@bjsxt2014-2015</div>
	    </div>
		<div class="scroll">
			<a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(1);"></a>
			<a href="javascript:;" class="next" title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(2);"></a>
		</div>
	</body>

</html>