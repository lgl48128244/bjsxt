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
		<%@include file="../util/rapid.jsp" %>
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
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
							<form action="UserController" id="registerFrm" class="jqtransform required-validate" method="post">
								<input type="hidden" name="opertype" value="register"/>
								<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_right">
											用户名：
										</td>
										<td class="">
											<input type="text" name="uname" class="input-text lh30 required validate-alphanum length-range-6-18 validate-ajax" validateUrl="UserController?opertype=checkUnameOnly" validateFailedMessage="用户名已经存在"  size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											密码：
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
											网络昵称：
										</td>
										<td class="">
											<input type="text" name="nickname" class="input-text lh30 required validate-chinese length-range-2-6" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											邮箱：
										</td>
										<td class="">
											<input type="text" name="email" class="input-text lh30 required validate-email" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											&nbsp;
										</td>
										<td class="">
											<input type="submit" name="button" class="btn btn82 btn_save2" value="保存">
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
