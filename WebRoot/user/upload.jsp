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
							<b class="pl15">用户信息管理>>上传用户简历</b>
						</div>
						<div class="box_center">
							<form action="UserController?opertype=upload&pageNum=${param.pageNum }&pageSize=${param.pageSize}" id="registerFrm" class="jqtransform required-validate" enctype="multipart/form-data" method="post">
								<input type="hidden" name="id" value="${param.id }"/>
								<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_right">
											简历：
										</td>
										<td class="">
											<input type="file" name="attachment" class="input-text lh30 required validate-file-doc-ppt" size="10">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											&nbsp;
										</td>
										<td class="">
											<input type="submit" name="button" class="btn btn82 btn_save2" value="上传">
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
