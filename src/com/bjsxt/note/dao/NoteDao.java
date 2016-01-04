package com.bjsxt.note.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.note.vo.Note;
import com.bjsxt.util.DataBaseUtil;
import com.bjsxt.util.PageUtil;
import com.bjsxt.util.SxtLogger;

public class NoteDao {
	/**
	 * 将日志信息存放至数据库
	 * @param note
	 * @return
	 */
	public boolean doNoteInsert(Note note) {
		//声明连接
		Connection connection = null;
		Statement statement = null;
		//拼接sql语句
		String sql = "INSERT INTO NOTE VALUES(null,'" + note.getOperTable() + "','" + note.getOperType() + "','" + note.getUserId() + "','" + note.getUserIp() + "',NOW())";
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			int count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}
		return false;
	}

	/**
	 * 根据指定的条件查询符合条件的总数
	 * @param searchNote
	 * @return
	 */
	public int getRowCountByObject(Note searchNote) {
		//声明一个int对象
		int rowCount = 0;
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		//拼接sql语句
		StringBuffer sql = new StringBuffer("SELECT COUNT(ID) FROM NOTE WHERE 1=1 ");
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			resultSet = statement.executeQuery(sql.toString());
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
	 * 进行日志分页查询
	 * @param searchNote
	 * @param pageUtil
	 */
	public void doNoteQueryPaging(Note searchNote, PageUtil<Note> pageUtil) {
		//声明一个List<User>对象
		List<Note> noteList = new ArrayList<Note>();
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		StringBuffer sql = new StringBuffer("SELECT * FROM NOTE WHERE 1=1 ");
		sql.append(" LIMIT " + pageUtil.getRowStart() + "," + pageUtil.getPageSize());
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			resultSet = statement.executeQuery(sql.toString());
			while (resultSet.next()) {
				Note note = new Note();
				resultset2bean(resultSet, note);
				noteList.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}
		//将查询到的结果存放至分页对象
		pageUtil.setList(noteList);
	}

	/**
	 * 将结果集里面的数据存放至javabean对象
	 * @param resultSet
	 * @param note
	 * @throws SQLException 
	 */
	private void resultset2bean(ResultSet resultSet, Note note) throws SQLException {
		note.setId(resultSet.getInt("id"));
		note.setOperTable(resultSet.getString("operTable"));
		note.setOperType(resultSet.getString("operType"));
		note.setUserId(resultSet.getInt("userId"));
		note.setUserIp(resultSet.getString("userIp"));
		note.setCreateTime(resultSet.getTimestamp("createTime"));
	}
}
