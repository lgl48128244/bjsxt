package com.bjsxt.teacher.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.bjsxt.teacher.dao.TeacherDao;
import com.bjsxt.teacher.vo.Teacher;
import com.bjsxt.util.PageUtil;
import com.bjsxt.util.SxtLogger;
import com.bjsxt.util.date.DateUtil;

public class TeacherController extends HttpServlet {

	private static final long serialVersionUID = -9168155134343766459L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取自定义的请求方式
		String opertype = request.getParameter("opertype");
		//获取系统的请求方式
		String method = request.getMethod().toLowerCase();
		//根据请求方式分发方法
		if (opertype != null && "add".equals(opertype) && "post".equals(method)) {
			doTeacherAdd(request, response);
		} else if (opertype != null && "loadGrid".equals(opertype) && "post".equals(method)) {
			doTeacherLoadGrid(request, response);
		} else if (opertype != null && "delete".equals(opertype) && "post".equals(method)) {
			doTeacherDelete(request, response);
		} else if (opertype != null && "updateBefore".equals(opertype) && "post".equals(method)) {
			doTeacherDelete(request, response);
		} else if (opertype != null && "loadGender".equals(opertype) && "post".equals(method)) {
			doTeacherLoadGender(request, response);
		} else {
			SxtLogger.logger.error("找不到请求方式:opertype=[" + opertype + "]=====>method=[" + method + "]");
		}
	}

	/**
	 * 处理数据统计信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doTeacherLoadGender(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//分别获取出男女分别占有的比例
		TeacherDao teacherDao = new TeacherDao();
		Map<String, Integer> map = teacherDao.doTeacherLoadGender();
		//拼接数据显示的XML语句
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<chart caption='教师性别统计饼状态' bgColor='E1E1E1,FFFFFF' pieYScale='30' plotFillAlpha='80' pieInnerfaceAlpha='60' slicingDistance='35' startingAngle='190' showBorder='1' showValues='0' showLabels='0' showLegend='1'>");
		buffer.append("<set value='" + map.get("1") + "' label='男' color='2675B4'/>");
		buffer.append("<set value='" + map.get("0") + "' label='女' color='C2780D'/>");
		buffer.append("</chart>");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(buffer.toString());
	}

	/**
	 * 处理教师删除业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doTeacherDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取被删除教师的ID
		String tnos = request.getParameter("tnos");
		//去数据库删除信息
		TeacherDao teacherDao = new TeacherDao();
		teacherDao.doTeacherDeleteByTnos(tnos);
		//返回信息
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("指定的教师信息删除成功");

	}

	/**
	 * 为EasyUi的网格加载数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doTeacherLoadGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取分页数据信息
		String pageNumString = request.getParameter("page");
		String pageSizeString = request.getParameter("rows");
		//从数据库中获取需要加载的数据
		TeacherDao teacherDao = new TeacherDao();
		//查询总共有多少条记录
		int rowCount = teacherDao.getRowCount("");
		//创建分页对象
		PageUtil<Teacher> pageUtil = new PageUtil<Teacher>(pageNumString, pageSizeString, rowCount, "TeacherController");
		teacherDao.doTeacherPaging(pageUtil);
		//返回响应信息
		response.setContentType("text/html;charset=utf-8");
		//返回响应信息
		SxtLogger.logger.info("{\"total\":" + rowCount + ",\"rows\":" + new JSONArray(pageUtil.getList()).toString() + "}");
		response.getWriter().print("{\"total\":" + rowCount + ",\"rows\":" + new JSONArray(pageUtil.getList()).toString() + "}");
	}

	/**
	 * 处理教师新增业务
	 * @param request
	 * @param response
	 */
	private void doTeacherAdd(HttpServletRequest request, HttpServletResponse response) {
		//获取教师的新增信息
		Teacher teacher = (Teacher) request.getAttribute("form2bean");
		//生成TNO
		String tno = DateUtil.DateToString(teacher.getHiredate(), "yyyy-MM-dd") + teacher.getIdnumber().substring(15);
		teacher.setTno(tno);
		//将获取的对象存放至数据库
		TeacherDao teacherDao = new TeacherDao();
		teacherDao.doTeacherInsert(teacher);
	}
}
