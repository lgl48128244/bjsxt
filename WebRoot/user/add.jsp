<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<head>
		<base href="<%=basePath%>">
		<title>添加用户信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript">
			function checkUnameOnly(){
				//获取用户输入的用户名
				var uname = document.getElementById("uname").value;
				//验证用户名是否符合规范
				if(uname!=null&&/^\w{6,18}$/ig.test(uname)){
					//如果符合规范，发送Ajax请求验证唯一性
					var request;
					//创建对象
					if(window.XMLHttpRequest){
						request = new XMLHttpRequest();
					}else if(window.ActiveXObject){
						request = new ActiveXObject("Msxml2.XMLHTTP");
					}
					//初始化对象连接
					request.open("post","UserController");
					//监听响应数据
					request.onreadystatechange=function(){
						if(request.readyState==4){
							if(request.status==200){
								var result = request.responseText;
								if(result=="false"){
									document.getElementById("unameSpan").innerHTML="该用户名已存在,请更换一个新的";
								}else{
									document.getElementById("unameSpan").innerHTML="";
								}
							}
						}
					}
					//发送请求信息
					request.setRequestHeader("content-type","application/x-www-form-urlencoded");
					request.send("opertype=checkUnameOnly&uname="+uname);
				}
			}
			function checkUnameOnly(){
				//获取用户输入的用户名
				var uname = document.getElementById("uname").value;
				//验证用户名是否符合规范
				if(uname!=null&&/^\w{6,18}$/ig.test(uname)){
					sendPost("UserController","opertype=checkUnameOnly&uname="+uname,function(result){
						if(result=="false"){
							document.getElementById("unameSpan").innerHTML="该用户名已存在,请更换一个新的";
						}else{
							document.getElementById("unameSpan").innerHTML="";
						}
					})
				}
			}
		</script>
	</head>
	</head>
	<body class="right_body">
		<div id="forms">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">用户信息管理>>新增用户信息</b>
						</div>
						<div class="box_center">
							<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">
										用户名：
									</td>
									<td class="">
										<input type="text" id="uname" name="uname" class="input-text lh30" onblur="checkUnameOnly();" size="40">
										<span id="unameSpan"></span>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
	</body>
</html>
