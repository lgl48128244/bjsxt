package com.bjsxt.util.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bjsxt.note.dao.NoteDao;
import com.bjsxt.note.vo.Note;
import com.bjsxt.user.vo.User;

public class NoteFilter implements Filter {

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		//如果User存在于Session则记录日志信息
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		//获取User
		User user = (User) request.getSession().getAttribute("user");
		//判断是否存日志信息
		if (user != null) {
			//获取请求路径
			String uri = request.getRequestURI().toLowerCase();
			//获取操作的表的名称
			String tableName = uri.substring(uri.lastIndexOf("/") + 1, uri.length()).replace("controller", "");
			//获取操作的类型
			String operName = request.getParameter("opertype");
			//获取操作表和操作类型的汉字信息
			ServletContext servletContext = request.getSession().getServletContext();
			Map<String, String> noteOperMap = (Map<String, String>) servletContext.getAttribute("noteOperMap");
			Map<String, String> noteTableMap = (Map<String, String>) servletContext.getAttribute("noteTableMap");
			String operValue = noteOperMap.get(operName);
			String tableValue = noteTableMap.get(tableName);
			if (operValue != null && tableValue != null) {
				//获取操作人的ID
				int userId = user.getId();
				//获取操作人的IP
				String userIp = request.getRemoteAddr();
				//将日志信息存放至数据库
				Note note = new Note(tableValue, operValue, userId, userIp);
				new NoteDao().doNoteInsert(note);
			}
		}
		//继续下一个过滤器或则请求路径
		filterchain.doFilter(servletrequest, servletresponse);
	}

	public void init(FilterConfig filterconfig) throws ServletException {

	}

	public void destroy() {

	}

}
