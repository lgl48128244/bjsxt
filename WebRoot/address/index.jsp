<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户信息</title>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/main.css" />
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/bjsxt.js"></script>
		<script type="text/javascript">
			$(function() {
				//让table的列宽可以随意拖动
				$(".list_table").colResizable( {
					liveDrag : true,
					gripInnerHtml : "<div class='grip'></div>",
					draggingClass : "dragging",
					minWidth : 30
				});
				//给查询选项性别赋值查询条件
				initPage();
			});
			
			function href2Address(){
				alert(encodeURIComponent("中国"));
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">区划信息管理>>查看省级信息</b>
				</div>
			</div>
		</div>
		<div id="table">
			<div class="box span10 oh">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
					<tr>
						<th width="50">
							序号
						</th>
						<th>
							ID
						</th>
						<th>
							区划名称
						</th>
						<th>
							所属区划名称
						</th>
						<th width="100">
							区划编号
						</th>
						<th width="100">
							操作
						</th>
					</tr>
					<c:forEach items="${addressList}" var="temp" varStatus="vars">
						<tr class="tr" align="center">
							<td>
								${vars.count}
							</td>
							<td>
								${temp.id}
							</td>
							<td>
								${temp.name}
							</td>
							<td>
								${parentName}
							</td>
							<td>
								${temp.orderfield}
							</td>
							<td>
								<!-- <input type="button" value="href2Address();" onclick="href2Address();"/>-->
								<c:if test="${temp.id<10000}">
									<a href="AddressController?opertype=search&parentid=${temp.id}&parentName=${temp.name}">所辖区划</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>