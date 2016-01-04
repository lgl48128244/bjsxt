<%@ page language="java" import="java.util.*" session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<base href="<%=basePath%>">
		<title>北京尚学堂后台登陆</title>
		<%@include file="util/rapid.jsp" %>
		<link rel="stylesheet" href="css/login.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<style type="text/css">
			.validation-advice {
				display:block;		
			}
		</style>
		<script type="text/javascript">
			$(function(){
				generateCode();
				new Validation('loginFrm',{immediate:true});
			});
			
			function generateCode(){
				$("#codeImg").attr("src","CheckCodeServlet?opertype=generateCode&random="+Math.random());
			}
			function validateCode(){
				var checkCode = $("#checkCode").val();
				$.post("CheckCodeServlet","opertype=validateCode&clientCode="+checkCode,function(result){
					alert(result);
				});
			}
		</script>
	</head>
	<body>
		<div id="login_top">
			<div id="welcome">
				欢迎使用北京尚学堂学生管理系统
			</div>
			<div id="back">
				<a href="#">返回首页</a>&nbsp;&nbsp; | &nbsp;&nbsp;
				<a href="#">帮助</a>
			</div>
		</div>
		<div id="login_center">
			<div id="login_area">
				<div id="login_form">
					<form action="UserController" method="post" id="loginFrm">
						<input type="hidden" name="opertype" value="login"/>
						<div id="login_tip">
							用户登录&nbsp;&nbsp;UserLogin
						</div>
						<div>
							<input type="text" value="zhangsan" class="username required validate-alphanum length-range-6-18" autocomplete="false" name="uname">
						</div>
						<div>
							<input type="password" value="123456" class="pwd required validate-alphanum length-range-6-12" name="pwd">
						</div>
						<div id="btn_area">
							<input type="submit" name="submit" id="sub_btn" value="登&nbsp;&nbsp;录">&nbsp;&nbsp;
							<input type="text" class="verify" maxlength="4" onblur="validateCode();" id="checkCode">
							<img id="codeImg" onclick="generateCode();" title="看不清楚,请单击刷新" width="80" height="40">
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="login_bottom">
			版权所有@bjsxt2014-2015
		</div>
	</body>
	<script type="text/javascript">
		<c:choose>
			<c:when test="${param.type == 1}">
				alert('用户名和密码不匹配,请重新登录');
			</c:when>
			<c:when test="${param.type == 2}">
				alert('用户注册成功,请登录');
			</c:when>
			<c:when test="${param.type == 3}">
				alert('注销成功,欢迎再来');
			</c:when>
			<c:when test="${param.type == 4}">
				alert('密码修改成功,请重新登录');
			</c:when>
			<c:when test="${param.type == 5}">
				alert('请您先登录系统然后再进行数据操作');
			</c:when>
		</c:choose>
	</script>
</html>