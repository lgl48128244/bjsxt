package com.bjsxt.user.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bjsxt.user.dao.UserDao;
import com.bjsxt.user.vo.User;
import com.bjsxt.util.Constants;
import com.bjsxt.util.PageUtil;
import com.bjsxt.util.SxtLogger;

public class UserController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取自定义的请求方式
		String opertype = request.getParameter("opertype");
		//获取系统的请求方式
		String method = request.getMethod().toLowerCase();
		//根据请求方式分发方法
		if (opertype != null && "register".equals(opertype) && "post".equals(method)) {
			doUserRegister(request, response);
		} else if (opertype != null && "login".equals(opertype) && "post".equals(method)) {
			doUserLogin(request, response);
		} else if (opertype != null && "logout".equals(opertype) && "get".equals(method)) {
			doUserLogout(request, response);
		} else if (opertype != null && "showAll".equals(opertype) && "get".equals(method)) {
			doUserShowAll(request, response);
		} else if (opertype != null && "delete".equals(opertype) && "get".equals(method)) {
			doUserDelete(request, response);
		} else if (opertype != null && "updateBefore".equals(opertype) && "get".equals(method)) {
			doUserUpdateBefore(request, response);
		} else if (opertype != null && "update".equals(opertype) && "post".equals(method)) {
			doUserUpdate(request, response);
		} else if (opertype != null && "updatePwd".equals(opertype) && "post".equals(method)) {
			doUserUpdatePwd(request, response);
		} else if (opertype != null && "search".equals(opertype) && "get".equals(method)) {
			doUserSearch(request, response);
		} else if (opertype != null && "paging".equals(opertype) && "get".equals(method)) {
			doUserPaging(request, response);
		} else if (opertype != null && "checkUnameOnly".equals(opertype) && "post".equals(method)) {
			doUserCheckUnameOnly(request, response);
		} else if (opertype != null && "flushPage".equals(opertype) && "post".equals(method)) {
			doUserFlushPage(request, response);
		} else if (opertype != null && "googlesuggest".equals(opertype) && "post".equals(method)) {
			doUserGoogleSuggest(request, response);
		} else if (opertype != null && "download".equals(opertype) && "get".equals(method)) {
			doUserDownload(request, response);
		} else if (opertype != null && "upload".equals(opertype) && "post".equals(method)) {
			doUserUpload(request, response);
		} else {
			SxtLogger.logger.error("找不到请求方式:opertype=[" + opertype + "]=====>method=[" + method + "]");
		}
	}

	/**
	 * 上传简历
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建一个User对象存放上传信息
		User user = new User();
		try {
			//获取webroot根目录
			String baseDirectory = this.getServletContext().getRealPath("/");
			String tempDirectory = baseDirectory + Constants.UPLOAD_TEMP_PATH; //要在最后加上斜杠:temp/
			String fileDirectory = baseDirectory + Constants.UPLOAD_FILE_PATH;
			//内存中的数据够64K向硬盘执行一次物理IO
			int sizeThreshold = 1024 * 64;
			//创建临时仓库文件目录
			File repositoryFile = new File(tempDirectory);
			//工厂每生产64K数据就将数据存放只临时仓库
			FileItemFactory factory = new DiskFileItemFactory(sizeThreshold, repositoryFile);
			//文件上传借助于工厂
			ServletFileUpload upload = new ServletFileUpload(factory);
			//设置上传文件的大小500M
			upload.setSizeMax(5 * 1024 * 1024);
			//开始解析上传数据
			List items = upload.parseRequest(request);
			//通过迭代器遍历LIst
			Iterator iter = items.iterator();
			//开始遍历
			while (iter.hasNext()) {
				//获取每一个
				FileItem item = (FileItem) iter.next();
				//判断Item是普通参数还是上传数据
				if (item.isFormField()) {
					//获取表单域的名字
					String fieldName = item.getFieldName();
					//获取表单与对应的值
					if (fieldName != null && "id".equals(fieldName)) {
						user.setId(Integer.parseInt(item.getString("utf-8")));
					}
				} else {
					//获取表单域name属性的值
					String fieldName = item.getFieldName();
					//获取上传文件的原名
					String fileName = item.getName();
					//获取文件的类型（获取的文件后缀名）
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					//生成新的文件名字
					String newFileName = UUID.randomUUID().toString().replace("-", "") + fileType;
					//打印数据
					//创建文件
					File uploadedFile = new File(fileDirectory + newFileName);
					//将临时文件的内容写入至新文件
					item.write(uploadedFile);
					//将文件名存放至User对象
					user.setAttachmentName(fileName);
					user.setAttachmentUuidName(newFileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将修改之后的数据存放至数据库
		UserDao userDao = new UserDao();
		userDao.doUserUpdate(user);
		//刷新用户信息
		resetList(request, response);
		//跳转至用户模块首页面
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;

	}

	/**
	 * 下载简历
	 * @param request
	 * @param response
	 */
	private void doUserDownload(HttpServletRequest request, HttpServletResponse response) {
		try {
			//获取目标用户的ID
			String id = request.getParameter("id");
			//获取id对应的用户
			UserDao userDao = new UserDao();
			User user = userDao.doQueryById("id", id);
			//获取文件
			String path = this.getServletContext().getRealPath("/upload/" + user.getAttachmentUuidName());
			//获取简历
			File attachment = new File(path);
			BufferedInputStream bis;
			bis = new BufferedInputStream(new FileInputStream(attachment));
			//将输入流存放至数组
			byte[] buffer = new byte[bis.available()];
			bis.read(buffer);
			//管理输入流
			bis.close();
			//刷新一个响应对象
			response.reset();
			//设置响应格式
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(user.getAttachmentName().getBytes("utf-8"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + attachment.length());
			response.setContentType("application/octet-stream");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 谷歌提示
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doUserGoogleSuggest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取用户名
		String uname = request.getParameter("uname");
		//查询数据
		List<User> userList = new UserDao().doUserGoogleSuggest(uname);
		//拼接字符串
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < userList.size(); i++) {
			buffer.append(",'" + userList.get(i).getUname() + "'");
		}
		SxtLogger.logger.info(buffer);
		//响应数据
		response.setContentType("text/html;charset=utf-8");
		if (buffer.length() == 0) {
			response.getWriter().print("[]");
		} else {
			response.getWriter().print("[" + buffer.substring(1) + "]");
		}
	}

	/**
	 * 通过Ajax刷新页面数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserFlushPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SxtLogger.logger.info("我来刷新页面数据了(Ajax)");
		//刷新数据
		resetList(request, response);
		//刷新页面数据
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("少年，放弃吧，ajax是不会刷新页面数据的!!!");
		//		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		//		return;

	}

	/**
	 * 验证用户名是否唯一
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doUserCheckUnameOnly(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取客户端的请求信息
		String uname = request.getParameter("uname");
		//去数据库进行唯一验证
		UserDao userDao = new UserDao();
		User user = userDao.doUserCheckUnameOnly(uname);
		//将信息返回值客户端
		response.setContentType("text/html;charset=utf-8");
		if (user != null) {
			response.getWriter().print("false");
		} else {
			response.getWriter().print("true");
		}
	}

	/**
	 * 处理用户信息分页业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserPaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//刷新显示数据
		resetList(request, response);
		//请求转发至用户模块首页面
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 处理用户查询业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面信息
		String searchUname = request.getParameter("searchUname");
		//创建User对象
		User searchUser = new User();
		searchUser.setUname(searchUname);
		//将查询条件存放只session作用域
		request.getSession().setAttribute("searchUser", searchUser);
		//显示显示列表
		resetList(request, response);
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 修改用户密码信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doUserUpdatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取到客户端提交的密码信息
		String pwd = request.getParameter("pwd");
		User user = (User) request.getSession().getAttribute("user");
		user.setPwd(pwd);
		//将密码修改到数据库
		UserDao userDao = new UserDao();
		int count = userDao.doUserUpdate(user);
		//修改完成密码之后需要进行重新登录
		response.sendRedirect("login.jsp?type=4");
		return;
	}

	/**
	 * 处理用户修改业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端的修改信息
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		//创建User对象	
		User user = new User(Integer.parseInt(id), null, null, nickname, email, null, null);
		//保存修改信息至数据库
		UserDao userDao = new UserDao();
		int count = userDao.doUserUpdate(user);
		//刷新显示的数据
		resetList(request, response);
		//跳转至用户模块的首页面
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 修改用户信息前 获取被修改用户信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserUpdateBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取去被修改人的ID
		String id = request.getParameter("id");
		//获取被修改人信息
		UserDao userDao = new UserDao();
		User updateUser = userDao.doQueryById("id", id);
		//将被修改人信息存放只作用域
		request.setAttribute("updateUser", updateUser);
		//跳转至修改页面
		request.getRequestDispatcher("user/update.jsp").forward(request, response);
		return;
	}

	/**
	 * 处理用户删除业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取被删除的用户ID
		String userid = request.getParameter("userid");
		//去数据库将数据信息删除
		UserDao userDao = new UserDao();
		int count = userDao.doUserDeleteByIds(userid);
		//重新刷新显示界面数据
		resetList(request, response);
		//然后重新跳转至用户模块首页面
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 显示所有的用户信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserShowAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清空session中默认的查询条件
		request.getSession().setAttribute("searchUser", null);
		//刷新显示数据
		resetList(request, response);
		//请求转发至用户模块首页面
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;
	}

	/**
	 * 处理用户注销业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doUserLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//注销session
		request.getSession().invalidate();
		//重定向至登录界面
		response.sendRedirect("login.jsp?type=3");
		return;

	}

	/**
	 * 处理用户登录业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取输入的用户名和密码
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		//检验用户名和密码是否匹配
		UserDao userDao = new UserDao();
		User user = userDao.doUserLogin(uname, pwd);
		//根据匹配结果返回不同页面
		if (user != null) {
			//首相将user存放至Session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//页面跳转至欢迎界面(系统首页面)
			response.sendRedirect("index.jsp");
			return;
		} else {
			//跳转至登录界面
			response.sendRedirect("login.jsp?type=1");
			return;
		}

	}

	/**
	 * 处理用户的注册业务
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doUserRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取注册信息
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String power = request.getParameter("power");
		String teacherid = request.getParameter("teacherid");
		//创建User对象
		User user = new User(null, uname, pwd, nickname, email, power, teacherid);
		//将获取的注册信息存放至数据库
		UserDao userDao = new UserDao();
		int count = userDao.doUserInsert(user);
		//刷新显示的数据
		resetList(request, response);
		//跳转至用户模块首页面
		request.getRequestDispatcher("user/index.jsp").forward(request, response);
		return;

	}

	/**
	 * 刷新要显示的用户信息
	 * @param request
	 * @param response
	 */
	private void resetList(HttpServletRequest request, HttpServletResponse response) {
		//获取Dao对象
		UserDao userDao = new UserDao();
		//获取当前用户的session
		HttpSession session = request.getSession();
		//从session中取出要查询的用户条件
		User searchUser = (User) session.getAttribute("searchUser");
		//获取客户端的请求参数
		String pageNumString = request.getParameter("pageNum");
		String pageSizeString = request.getParameter("pageSize");
		int rowCount = userDao.getRowCountByObject(searchUser);
		//准备分页所需要的数据
		PageUtil<User> pageUtil = new PageUtil<User>(pageNumString, pageSizeString, rowCount, "UserController");
		//查询出所有的用户信息
		userDao.doUserQueryPaging(searchUser, pageUtil);
		//将用户信息存放至request作用域
		request.setAttribute("pageUtil", pageUtil);
	}
}
