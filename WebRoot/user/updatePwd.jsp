<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<head>
		<base href="<%=basePath%>">
		<title>修改用户信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="../util/rapid.jsp" %>
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			$(function(){
				new Validation('updateFrm',{immediate:true});
			});
			function checkOldPwd(){
				var oldPwd = document.getElementById("oldPwd").value;
				var pwd = '${user.pwd}';
				if(oldPwd==pwd){
					return true;
				}else{
					alert("原始密码错误,请查证后再进行修改");
					return false;
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
							<b class="pl15">用户信息管理>>修改密码信息</b>
						</div>
						<div class="box_center">
							<form action="UserController" class="jqtransform" id="updateFrm" method="post" onsubmit="return checkOldPwd();" target="_parent">
								<input type="hidden" name="opertype" value="updatePwd"/>
								<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_right">
											用户名：
										</td>
										<td class="">
											<input type="text" name="uname" value="${user.uname }" disabled="disabled" class="input-text lh30" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											原始密码：
										</td>
										<td class="">
											<input type="password" id="oldPwd" name="oldPwd" class="input-text lh30 required validate-alphanum length-range-6-12" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											新密码：
										</td>
										<td class="">
											<input type="password" id="pwd" name="pwd" class="input-text lh30 required validate-alphanum length-range-6-12" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											重复密码：
										</td>
										<td class="">
											<input type="password" class="input-text lh30 required equals-pwd" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											&nbsp;
										</td>
										<td class="">
											<input type="submit" name="button" class="btn btn82 btn_save2" value="修改">
											<input type="reset" name="button" class="btn btn82 btn_res" value="重置">
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
	</body>
</html>
