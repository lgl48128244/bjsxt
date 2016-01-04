<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'pct.jsp' starting page</title>
		<%@include file="../util/cssUtil.jsp" %>
		<script type="text/javascript">
			$(document).ready(function(){
				loadProvince();
			});
			/**
			 * 加载省的区划信息
			 */
			function loadProvince(){
				loadAddress(0,"province");
			}
			
			/**
			 * 加载市的数据信息
			 */
			function loadCity(){
				//获取父节点的iD
				var parentid = $("#province").val();
				//清空市原有的数据
				$("#city")[0].length=1;
				$("#town")[0].length=1;
				//document.getElementById("city").length=1;
				if(parentid!=999){
					loadAddress(parentid,"city");
				}
			}
			/**
			 * 加载县的数据信息
			 */
			function loadTown(){
				//获取父节点的iD
				var parentid = $("#city").val();
				//清空市原有的数据
				$("#town")[0].length=1;
				if(parentid!=999){
					loadAddress(parentid,"town");
				}
			}
			/**
			 * 通过AJAX加载地址信息
			 */
			function loadAddress(parentid,selectId){
				$.post("AddressController","opertype=loadAddress&parentid="+parentid,function(result){
					//转换结果类型
					eval("var addrs = "+result);
					//获取js的对象
					var obj = $("#"+selectId).get(0);
					//遍历并添加option
					for(var i=0;i<addrs.length;i++){
						obj.add(new Option(addrs[i].name,addrs[i].id));
					}
				});
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">局部刷新>>区划三级联动</b>
				</div>
			</div>
		</div>
		<select id="province" class="select" style="width: 150px;" onchange="loadCity();"> 
			<option value="999">--请选择--</option> 
		</select>省
		<select id="city" class="select" style="width: 150px;" onchange="loadTown();"> 
			<option value="999">--请选择--</option> 
		</select>市
		<select id="town" class="select" style="width: 150px;"> 
			<option value="999">--请选择--</option> 
		</select>县
	</body>
</html>
