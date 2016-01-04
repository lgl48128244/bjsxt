<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'fusionchar.jsp' starting page</title>
		<%@include file="../util/fusionchart.jsp" %>
		<style type="text/css">
			div{
				float: left;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				$.post("TeacherController","opertype=loadGender",function(result){
					var chart = new FusionCharts("fusionchart/Charts/Doughnut3D.swf", "ChartId", "560", "400", "0", "0");
					chart.setXMLData(result);
					chart.render("chartdiv1");
				});
				
				$.post("TeacherController","opertype=loadGender",function(result){
					var chart = new FusionCharts("fusionchart/Charts/Column3D.swf", "ChartId", "560", "400", "0", "0");
					chart.setXMLData(result);
					chart.render("chartdiv2");
				});
			});
		</script>
	</head>
	<body>
		<h3 class="chart-title">教师性别信息统计</h3>
        <p>&nbsp;</p>
        <div id="chartdiv1" align="center">Chart will load here</div>
        <div id="chartdiv2" align="center">Chart will load here</div>
	</body>
</html>
