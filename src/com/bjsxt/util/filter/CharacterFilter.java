package com.bjsxt.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter {

	private FilterConfig filterConfig = null;

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		//从配置文件中获取编码格式
		String character = this.filterConfig.getInitParameter("character");
		//设置编码格式
		servletrequest.setCharacterEncoding(character);
		servletresponse.setCharacterEncoding(character);
		//继续下一个过滤器或者要访问的目标路径
		filterchain.doFilter(servletrequest, servletresponse);
		//设置页面不保留缓存
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		response.setHeader("Cache", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.flushBuffer();
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		this.filterConfig = filterconfig;
	}

	public void destroy() {

	}
}
