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
		<title>谷歌提示</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="../util/cssUtil.jsp" %>
		<link href="jqueryui/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
		<script src="jqueryui/js/jquery-ui-1.10.3.custom.js"></script>
		<script type="text/javascript">
			function ac(){
				var uname = $("#uname").val();
				$.post("UserController","opertype=googlesuggest&uname="+uname,function(result){
					eval("var availableTags = "+result);
					$( "#uname" ).autocomplete({
						source: availableTags
					});
				});
			}
		</script>
	</head>
	</head>
	<body class="right_body">
		<div id="forms">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">局部刷新技术>>谷歌提示</b>
						</div>
						<div class="box_center">
							<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">
										用户名：
									</td>
									<td class="">
										<input type="text" id="uname" class="input-text lh30" size="40" onkeyup="ac();">
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
	</body>
</html>
