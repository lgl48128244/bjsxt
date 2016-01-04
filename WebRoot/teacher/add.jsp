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
		<title>添加教师信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="../util/rapid.jsp" %>
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	</head>
	</head>
	<body class="right_body">
		<div id="forms">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">教师信息管理>>新增教师信息</b>
						</div>
						<div class="box_center">
							<form action="TeacherController" id="registerFrm" class="jqtransform required-validate" method="post">
								<input type="hidden" name="opertype" value="add"/>
								<input type="hidden" name="form2bean" value="com.bjsxt.teacher.vo.Teacher"/>
								<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_right">
											教师姓名：
										</td>
										<td class="">
											<input type="text" value="张三丰" name="tname" class="input-text lh30 required validate-chinese length-range-2-6" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											身份证号：
										</td>
										<td class="">
											<input type="text" value="130622198307168809" name="idnumber" class="input-text lh30 required validate-id-number" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											出生日期：
										</td>
										<td class="">
											<input type="text" name="birthday" onClick="WdatePicker()" class="input-text lh30 required validate-date-yyyy-MM-dd" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											手机号码：
										</td>
										<td class="">
											<input type="text" value="13812345678" name="phone" class="input-text lh30 required validate-mobile-phone" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											QQ号码：
										</td>
										<td class="">
											<input type="text" value="12345678" name="qq" class="input-text lh30 required validate-qq" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											入职时间：
										</td>
										<td class="">
											<input type="text" name="hiredate" onClick="WdatePicker()" class="input-text lh30 required validate-date-yyyy-MM-dd" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											工作类别：
										</td>
										<td class="">
											<select name="gzlb" class="select"> 
						                        <option value="gl">管理</option> 
						                        <option value="sk">授课</option> 
					                        </select> 
										</td>
									</tr>
									<tr>
										<td class="td_right">
											性别：
										</td>
										<td class="">
											<input type="radio" name="gender" value="1" checked="checked"> 男
                    						<input type="radio" name="gender" value="0"> 女
										</td>
									</tr>
									<tr>
										<td class="td_right">
											个人介绍：
										</td>
										<td class="">
											<textarea name="grjs" id="grjs" cols="30" rows="10" class="textarea required length-range-5-20">我是一个好人</textarea>
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
