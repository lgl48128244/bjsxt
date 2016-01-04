package com.bjsxt.movie.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.movie.dao.MovieDao;
import com.bjsxt.movie.vo.Movie;
import com.bjsxt.util.SxtLogger;

public class MovieController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取自定义的请求方式
		String opertype = request.getParameter("opertype");
		//获取系统的请求方式
		String method = request.getMethod().toLowerCase();
		//根据请求方式分发方法
		if (opertype != null && "showRoom".equals(opertype) && "get".equals(method)) {
			doMovieShowRoom(request, response);
		} else if (opertype != null && "submitSeat".equals(opertype) && "post".equals(method)) {
			doMovieSubmitSeat(request, response);
		} else {
			SxtLogger.logger.error("找不到请求方式:opertype=[" + opertype + "]=====>method=[" + method + "]");
		}
	}

	/**
	 * 提交预定的座位信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doMovieSubmitSeat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取被选择的作为
		String seatId = request.getParameter("seatId");
		//验证选中的作为是否已经被卖出
		MovieDao movieDao = new MovieDao();
		int count = movieDao.doMovieCheckSeat(seatId);
		//判断
		response.setContentType("text/html;charset=utf-8");
		if (count == 0) {
			//可以预定
			movieDao.doMovieSubmitSeat(seatId);
			response.getWriter().print("true");
		} else {
			//不能被预定
			response.getWriter().print("false");
		}
	}

	/**
	 * 显示指定放映室的座位信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doMovieShowRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取放映室
		String room = request.getParameter("room");
		//获取Room对应的座位信息
		MovieDao movieDao = new MovieDao();
		List<Movie> movieList = movieDao.doMovieShowRoom(room);
		//将数据存放至作用域
		request.setAttribute("movieList", movieList);
		//请求转发至电影模块的首页面
		request.getRequestDispatcher("movie/index.jsp").forward(request, response);
		return;
	}
}
