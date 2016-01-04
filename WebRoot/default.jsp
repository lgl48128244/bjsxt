<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<base href="<%=basePath%>">
		<title>Document</title>
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			$(function() {
				$(".list_table").colResizable( {
					liveDrag : true,
					gripInnerHtml : "<div class='grip'></div>",
					draggingClass : "dragging",
					minWidth : 30
				});
			
			});
		</script>
	</head>
	<body>
		<div id="forms">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">用户个人信息</b>
					</div>
					<div class="box_center">
						<form action="" class="jqtransform">
							<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">
										用户名：
									</td>
									<td class="">
										${user.uname}
									</td>
									<td rowspan="8">
										<img src="images/photo.gif"/>
									</td>
									<td rowspan="8" style="width: 500px;">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td class="td_right">
										网络昵称：
									</td>
									<td>
										${user.nickname}
									</td>
								</tr>
								<tr>
									<td class="td_right">
										电子邮箱：
									</td>
									<td>
										${user.email}
									</td>
								</tr>
								<tr>
									<td class="td_right">
										注册时间：
									</td>
									<td>
										${user.createTime}
									</td>
								</tr>
								<tr>
									<td class="td_right">
										修改时间：
									</td>
									<td>
										${user.updateTime}
									</td>
								</tr>
								<tr>
									<td class="td_right">
										登录Ip地址：
									</td>
									<td>
										192.168.1.1
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