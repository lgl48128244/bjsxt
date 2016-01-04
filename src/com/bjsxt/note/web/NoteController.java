package com.bjsxt.note.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjsxt.note.dao.NoteDao;
import com.bjsxt.note.vo.Note;
import com.bjsxt.util.PageUtil;
import com.bjsxt.util.SxtLogger;

public class NoteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取自定义的请求方式
		String opertype = request.getParameter("opertype");
		//获取系统的请求方式
		String method = request.getMethod().toLowerCase();
		//根据请求方式分发方法
		if (opertype != null && "showAll".equals(opertype) && "get".equals(method)) {
			doNoteShowAll(request, response);
		} else if (opertype != null && "paging".equals(opertype) && "get".equals(method)) {
			doNotePaging(request, response);
		} else {
			SxtLogger.logger.error("找不到请求方式:opertype=[" + opertype + "]=====>method=[" + method + "]");
		}
	}

	/**
	 * 处理分页业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doNotePaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//刷新显示数据
		resetList(request, response);
		//请求转发至用户模块首页面
		request.getRequestDispatcher("note/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 显示所有的日志信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doNoteShowAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清空session中默认的查询条件
		request.getSession().setAttribute("searchNote", null);
		//刷新显示数据
		resetList(request, response);
		//请求转发至用户模块首页面
		request.getRequestDispatcher("note/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 刷新要显示的用户信息
	 * @param request
	 * @param response
	 */
	private void resetList(HttpServletRequest request, HttpServletResponse response) {
		//获取Dao对象
		NoteDao noteDao = new NoteDao();
		//获取当前用户的session
		HttpSession session = request.getSession();
		//从session中取出要查询的用户条件
		Note searchNote = (Note) session.getAttribute("searchNote");
		//获取客户端的请求参数
		String pageNumString = request.getParameter("pageNum");
		String pageSizeString = request.getParameter("pageSize");
		int rowCount = noteDao.getRowCountByObject(searchNote);
		//准备分页所需要的数据
		PageUtil<Note> pageUtil = new PageUtil<Note>(pageNumString, pageSizeString, rowCount, "NoteController");
		//查询出所有的用户信息
		noteDao.doNoteQueryPaging(searchNote, pageUtil);
		//将用户信息存放至request作用域
		request.setAttribute("pageUtil", pageUtil);
	}
}
