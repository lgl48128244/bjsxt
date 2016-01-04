<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>教师模块首页面</title>
		<%@include file="../util/easyui.jsp" %>
		<script type="text/javascript">
			$(function(){
				loadTeacherInfo();
				customPager();
			});
			
			/**
			 * 加载教师信息
			 */
			function loadTeacherInfo(){
				$('#teachers').datagrid({
					title:"教师信息列表",//标题
					iconCls:'icon-save',//图标
					collapsible:true,//是否可折叠的
					fitColumns:true,//自动填充
					striped:true,//隔行变色
					url:'TeacherController?opertype=loadGrid',//资源路径
					method:"post",//请求方式
					loadMsg:"我们正在玩命的为您加载数据...",
					pagination:true,//分页配置
					rownumbers:true,//显示行号
					singleSelect:false,//选择多行
					pageNumber:1,//默认显示页码数
					pageSize:5,//每页显示行数
					pageList:[10,20,30,40,50],
					queryParams:""//额外的请求参数
				});
			}
			/**
			 * 分页信息出添加按钮
			 */
			function customPager(){
				var pager = $('#teachers').datagrid().datagrid('getPager');	// get the pager of datagrid
				pager.pagination({
					buttons:[{
						iconCls:'icon-add',
						handler:function(){
							window.location.href="teacher/add.jsp"
						}
					},{
						iconCls:'icon-remove',
						handler:doTeacherDelete
					},{
						iconCls:'icon-edit',
						handler:doTeacherUpdate
					}]
				});		
			}
			/**
			 * 教师的删除操作
			 */
			function doTeacherDelete(){
				//获取被删除的对象
				var rows = $('#teachers').datagrid('getSelections');
				if(rows.length==0){
					$.messager.alert("提示信息","请选择要删除的教师信息","warning");
				}else{
					var tnos = "";
					for(var i=0;i<rows.length;i++){
						tnos +=",'"+rows[i].tno+"'";
					}
					//通过AJAX发送要删除的教师ID
					$.post("TeacherController","opertype=delete&tnos="+tnos.substring(1),function(result){
						//刷新数据
						$('#teachers').datagrid('reload');
						//显示成功信息
						$.messager.alert("提示信息",result,"info");
					});
				}
			}
			/**
			 * 教师的修改操作
			 */
			function doTeacherUpdate(){
				//获取被删除的对象
				var rows = $('#teachers').datagrid('getSelections');
				if(rows.length==0){
					$.messager.alert("提示信息","请选择要删除的教师信息","warning");
				}else if(rows.length>1){
					$.messager.alert("提示信息","每次只能修改一条教师信息","warning");
				}else{
					//获取被修改人的TNO
					var tno = rows[0].tno;
					//修改信息
					window.location.href = "TeacherController?opertype=updateBefor&tno="+tno;
				}
			}
			
			function showGender(value,rowData,rowIndex){
				//alert(value+"--->"+rowData+"---"+rowIndex);
				return value==="1"?"男":"女";
			}
		</script>
	</head>
	<body>
		<table id="teachers" class="easyui-datagrid">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'tno',width:100">教师编号</th>
					<th data-options="field:'tname',width:50">教师姓名</th>
					<th data-options="field:'idnumber',width:100,align:'right'">身份证号</th>
					<th data-options="field:'gender',width:50,align:'right',formatter:showGender">教师性别</th>
					<th data-options="field:'birthday',width:150">出生日期</th>
					<th data-options="field:'phone',width:100,align:'center'">手机号码</th>
					<th data-options="field:'qq',width:100,align:'center'">QQ号码</th>
					<th data-options="field:'grjs',width:150,align:'left'">个人介绍</th>
					<th data-options="field:'gzlb',width:100,align:'center'">工作类别</th>
				</tr>
			</thead>
		</table>
	</body>
</html>
