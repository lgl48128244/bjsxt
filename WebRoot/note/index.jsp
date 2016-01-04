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
			});
			/**
			 * 全选或则全否所有的checkBOX
			 * @param {Object} flag
			 */
			function chooseAllUser(flag) {
				var ckUsers = document.getElementsByName("ckUser");
				for ( var i = 0; i < ckUsers.length; i++) {
					ckUsers[i].checked = flag;
				}
			}
			/**
			 * 根据条件查询指定的用户信息
			 */
			function doUserSearch(){
				//获取查询条件
				var searchUname = document.getElementById("searchUname").value;
				//发送请求
				window.location.href = "UserController?opertype=search&searchUname="+searchUname;
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">用户信息管理>>查看用户信息</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								用户名
							</td>
							<td>
								<input type="text" name="searchUname" id="searchUname" value="${searchUser.uname}" class="input-text lh25" size="30">
								<input type="button" name="button" class="btn btn82 btn_search" value="查询" onclick="doUserSearch();">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="button" class="mt10">
			<input type="button" name="button" class="btn btn82 btn_config" value="修改" onclick="doUserUpdate();">
			<input type="button" name="button" class="btn btn82 btn_del" value="删除" onclick="doUserDelete();">
			<input type="button" name="button" class="btn btn82 btn_export" value="导出">
			<input type="button" name="button" class="btn btn82 btn_checked" value="全选" onclick="chooseAllUser(true);">
			<input type="button" name="button" class="btn btn82 btn_nochecked" value="取消" onclick="chooseAllUser(false);">
		</div>
		<div id="table" class="mt10">
			<div class="box span10 oh">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
					<tr>
						<th width="50">
							序号
						</th>
						<th width="120">
							表名称
						</th>
						<th width="120">
							操作类型
						</th>
						<th>
							操作人ID
						</th>
						<th>
							操作人IP
						</th>
						<th>
							操作时间
						</th>
					</tr>
					<c:forEach items="${pageUtil.list}" var="temp" varStatus="vars">
						<tr class="tr">
							<td>
								${vars.count+pageUtil.rowStart}
							</td>
							<td>
								${temp.operTable}
							</td>
							<td>
								${temp.operType}
							</td>
							<td>
								${temp.userId}
							</td>
							<td>
								${temp.userIp}
							</td>
							<td>
								<fmt:formatDate value="${temp.createTime}" pattern="yyyy年MM月dd日 hh时mm分ss秒"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@include file="../util/pageUtil.jsp" %>
			</div>
		</div>
	</body>
</html>