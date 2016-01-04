package com.bjsxt.noteoper.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.bjsxt.util.DataBaseUtil;
import com.bjsxt.util.SxtLogger;

public class NoteOperDao {

	public Map<String, String> queryAllForMap() {
		//声明一个Map对象
		Map<String, String> map = new HashMap<String, String>();
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT * FROM NOTEOPER";
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				map.put(resultSet.getString("operName"), resultSet.getString("operValue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}
		return map;
	}

}
