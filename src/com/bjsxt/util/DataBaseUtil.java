package com.bjsxt.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库JDBC公共类
 * @author Administrator
 *
 */
public class DataBaseUtil {
	//获取数据库配置信息
	private static Properties properties = new Properties();
	//定义JDBC需要的参数
	private static String datatype = null;
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;

	static {
		//可以保证只加载一次，而且调用的时候肯定已经加载完成
		try {
			//加载配置文件
			properties.load(DataBaseUtil.class.getClassLoader().getResourceAsStream("DataBaseUtil.properties"));
			//获取配置文件里的配置信息
			datatype = properties.getProperty("datatype");
			driver = properties.getProperty(datatype + "Driver");
			url = properties.getProperty(datatype + "Url");
			user = properties.getProperty(datatype + "User");
			password = properties.getProperty(datatype + "Password");
			//加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("DataBaseUtil.getConnection()" + url + ":" + user + ":" + password);
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 关闭连接
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取清单对象
	 * @param connection
	 * @return
	 */
	public static Statement getStatement(Connection connection) {
		Statement statement = null;
		try {
			//判断连接是否为空 如果为空创建一个新的
			if (connection == null) {
				connection = getConnection();
			}
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * 关闭清单对象
	 * @param statement
	 */
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取预处理清单对象
	 * @param connection
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPstmt(Connection connection, String sql) {
		PreparedStatement preparedStatement = null;
		try {
			//判断连接是否为空 如果为空创建一个新的
			if (connection == null) {
				connection = getConnection();
			}
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	/**
	 * 关闭结果集合
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放所有的资源
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeConnStmtRs(Connection connection, Statement statement, ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connection);
	}

}
