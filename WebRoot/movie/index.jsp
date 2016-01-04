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
		<script type="text/javascript" src="js/ajax.js"></script>
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
			
			var seatId = "";
			function chooseSeat(id,obj){
				seatId+=","+id;
				obj.style.backgroundColor="red";
			}
			
			function submitSeat(){
				if(seatId.length==0){
					alert("请选择要预定的座位");
				}else{
					sendPost("MovieController","opertype=submitSeat&seatId="+seatId.substring(1),function(result){
						if(result=="true"){
							var arr = seatId.substring(1).split(",");
							for(var i=0;i<arr.length;i++){
								document.getElementById("td"+arr[i]).style.backgroundColor="gray";
							}
							alert("预定成功");
						}else{
							var arr = seatId.substring(1).split(",");
							for(var i=0;i<arr.length;i++){
								document.getElementById("td"+arr[i]).style.backgroundColor="white";
							}
							alert("预定失败");
						}
						seatId="";
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">局部刷新技术>>售票系统</b>
				</div>
			</div>
		</div>
		<div id="button" class="mt10">
			<input type="button" name="button" class="btn btn82 btn_config" value="预定" onclick="submitSeat();">
			<input type="button" name="button" class="btn btn82 btn_del" value="删除" onclick="doUserDelete();">
			<input type="button" name="button" class="btn btn82 btn_checked" value="全选" onclick="chooseAllUser(true);">
			<input type="button" name="button" class="btn btn82 btn_nochecked" value="取消" onclick="chooseAllUser(false);">
		</div>
		<div id="table" class="mt10">
			<div class="box span10 oh">
				<table width="100%" border="0" style="text-align: center;" cellpadding="0" cellspacing="0" class="list_table">
					<tr>
						<c:forEach items="${movieList}" var="temp" varStatus="vars">
							<c:choose>
								<c:when test="${temp.status==1}">
									<td style="height: 80px;background-color: gray;" id="td${temp.id}">
										${temp.seat}
									</td>
								</c:when>
								<c:otherwise>
									<td style="height: 80px;" ondblclick="chooseSeat('${temp.id}',this);" id="td${temp.id}">
										${temp.seat}
									</td>
								</c:otherwise>
							</c:choose>
							<c:if test="${vars.count%6==0}">
								</tr>
								<tr>
							</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>