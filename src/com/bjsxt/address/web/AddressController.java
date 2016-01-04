package com.bjsxt.address.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.address.dao.AddressDao;
import com.bjsxt.address.vo.Address;
import com.bjsxt.util.SxtLogger;

public class AddressController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取自定义的请求方式
		String opertype = request.getParameter("opertype");
		//获取系统的请求方式
		String method = request.getMethod().toLowerCase();
		//根据请求方式分发方法
		if (opertype != null && "search".equals(opertype) && "get".equals(method)) {
			doAddressSearch(request, response);
		} else if (opertype != null && "loadAddress".equals(opertype) && "post".equals(method)) {
			doLoadAddress(request, response);
		} else {
			SxtLogger.logger.error("找不到请求方式:opertype=[" + opertype + "]=====>method=[" + method + "]");
		}
	}

	/**
	 * 加载地址信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doLoadAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取parentid
		String parentid = request.getParameter("parentid");
		//加载地址信息
		AddressDao addressDao = new AddressDao();
		List<Address> addressList = addressDao.doAddressSearch(parentid);
		//声明一个变量用于存放便利后的数据[{id:1234,name:'beijing'},{id:1234,name:'beijing'},{id:1234,name:'beijing'}]
		StringBuffer backBuffer = new StringBuffer("");
		for (int i = 0; i < addressList.size(); i++) {
			backBuffer.append(",{id:" + addressList.get(i).getId() + ",name:'" + addressList.get(i).getName() + "'}");
		}
		//将信息写回至客户端
		response.setContentType("text/html;charset=utf-8");
		if (backBuffer.length() == 0) {
			response.getWriter().print("");
		} else {
			response.getWriter().print("[" + backBuffer.substring(1) + "]");
		}

	}

	/**
	 * 处理查询省数据的业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doAddressSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所属区划的名称
		String parentName = request.getParameter("parentName");
		//获取搜书区划的id
		String parentid = request.getParameter("parentid");
		//从数据库查询数据
		AddressDao addressDao = new AddressDao();
		List<Address> addressList = addressDao.doAddressSearch(parentid);
		//存放至request作用域
		request.setAttribute("addressList", addressList);
		request.setAttribute("parentName", parentName);
		//请求转发至区划模块首页面
		request.getRequestDispatcher("address/index.jsp").forward(request, response);
		return;

	}
}
