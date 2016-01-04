package com.bjsxt.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjsxt.user.vo.User;
import com.bjsxt.util.SxtLogger;

public class IsLoginFilter implements Filter {

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		//首先将servletRequest向下转型
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		//判断当前是否有用户登录
		HttpSession session = request.getSession();
		//是否在session中有user
		User user = (User) session.getAttribute("user");
		//获取项目名称
		String contextPath = request.getContextPath();
		//获取用户请求的URI
		String uri = request.getRequestURI();
		//请求参数
		String opertype = request.getParameter("opertype");
		/**
		 * 进行路径的筛选
		 * 		/bjsxt/
		 * 		/login.jsp
		 * 		/UserController?opertype=login
		 * 		以.css结尾
		 * 		以.js结尾
		 * 		中间包含有/images/
		 * 		如果有session不拦截
		 */
		if ((contextPath + "/").equals(uri)) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (uri.toLowerCase().endsWith("/login.jsp")) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (uri.endsWith("/UserController") && "login".equals(opertype)) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (uri.toLowerCase().endsWith(".js")) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (uri.toLowerCase().endsWith(".css")) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (uri.toLowerCase().contains("/images/")) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (uri.contains("CheckCodeServlet")) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (user != null) {
			filterchain.doFilter(servletrequest, servletresponse);
		} else if (user == null) {
			//如果不存在User 重定向至登录界面
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			response.sendRedirect(basePath + "login.jsp?type=5");
			return;
		} else {
			SxtLogger.logger.error("验证用户登录路径遗漏" + request.getRequestURL() + "?" + request.getQueryString());
			filterchain.doFilter(servletrequest, servletresponse);
		}
	}

	public void init(FilterConfig filterconfig) throws ServletException {

	}

	public void destroy() {

	}
}
