package com.bjsxt.util.lis;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bjsxt.noteoper.dao.NoteOperDao;
import com.bjsxt.notetable.dao.NoteTableDao;

public class ApplicationLis implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletcontextevent) {
		//获取ServletContext对象
		ServletContext servletContext = servletcontextevent.getServletContext();
		//服务器启动的时候加载日志需要的信息
		noteMap(servletContext);
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent) {

	}

	/**
	 * 服务器启动的时候加载日志需要的信息
	 * @param servletContext
	 */
	private void noteMap(ServletContext servletContext) {
		//获取日志表的对应数据
		NoteTableDao noteTableDao = new NoteTableDao();
		Map<String, String> noteTableMap = noteTableDao.queryAllForMap();
		//获取日志操作的对应数据
		NoteOperDao noteOperDao = new NoteOperDao();
		Map<String, String> noteOperMap = noteOperDao.queryAllForMap();
		//将查询到的结果存放至作用域
		servletContext.setAttribute("noteTableMap", noteTableMap);
		servletContext.setAttribute("noteOperMap", noteOperMap);
	}

}
