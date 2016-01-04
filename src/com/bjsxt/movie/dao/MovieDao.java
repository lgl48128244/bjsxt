package com.bjsxt.movie.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.movie.vo.Movie;
import com.bjsxt.util.DataBaseUtil;
import com.bjsxt.util.SxtLogger;

public class MovieDao {
	/**
	 * 查询指定放映室的座位信息
	 * @param room
	 * @return
	 */
	public List<Movie> doMovieShowRoom(String room) {
		//声明一个对象
		List<Movie> movieList = new ArrayList<Movie>();
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT * FROM MOVIE WHERE ROOM = '" + room + "'";
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Movie movie = new Movie();
				resultset2bean(resultSet, movie);
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}
		return movieList;
	}

	/**
	 * 将结果集里面的信息存放只对象
	 * @param resultSet
	 * @param movie
	 * @throws SQLException 
	 */
	private void resultset2bean(ResultSet resultSet, Movie movie) throws SQLException {
		movie.setId(resultSet.getInt("id"));
		movie.setSeat(resultSet.getString("seat"));
		movie.setRoom(resultSet.getString("room"));
		movie.setStatus(resultSet.getString("status"));
	}

	/**
	 * 检查作为是否被预定
	 * @param seatId
	 * @return
	 */
	public int doMovieCheckSeat(String seatId) {
		//定义变量
		int rowCount = 0;
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT count(ID) FROM MOVIE WHERE ID  IN (" + seatId + ") AND STATUS = '1'";
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}

		return rowCount;
	}

	/**
	 * 预定座位信息
	 * @param seatId
	 */
	public int doMovieSubmitSeat(String seatId) {
		//记录被删除用户数据的条数
		int count = 0;
		//声明连接
		Connection connection = null;
		Statement statement = null;
		//拼接sql语句
		String sql = "UPDATE MOVIE SET STATUS ='1' WHERE ID  IN (" + seatId + ")";
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}
		return count;

	}

}
