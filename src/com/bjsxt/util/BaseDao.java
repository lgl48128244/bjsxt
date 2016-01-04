package com.bjsxt.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public abstract class BaseDao<T> {
	protected Class<T> cls = null;

	public BaseDao() {
		//获取BaseDao泛型
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.cls = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	/**
	 * 处理所有的DQL查询
	 * @param subSql
	 * @return
	 */
	protected List<T> doDQL(CharSequence subSql) {
		//声明一个List对象
		List<T> list = new ArrayList<T>();
		//声明连接
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT * FROM " + this.cls.getSimpleName() + subSql;
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			pstmt = DataBaseUtil.getPstmt(connection, sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				//通过反射创建对象
				T bean = this.cls.getConstructor(null).newInstance(null);
				resultset2bean(resultSet, bean);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, pstmt, null);
		}
		return list;
	}

	/**
	 * 处理所有的增删改的操作
	 * @return
	 */
	protected int doDML(CharSequence sql) {
		//受影响的行数
		int count = 0;
		//声明连接
		Connection connection = null;
		PreparedStatement pstmt = null;
		//拼接sql语句
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			pstmt = DataBaseUtil.getPstmt(connection, sql.toString());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, pstmt, null);
		}
		return count;
	}

	/**
	 * 通过ID获取被修改人信息
	 * @param id
	 * @return
	 */
	public T doQueryById(String pk, String pkValue) {
		//拼接sql语句
		String subSql = " WHERE " + pk + " = " + pkValue;
		return doQuerySingle(subSql);
	}

	/**
	 * 查询单条数据信息
	 * @return
	 */
	protected T doQuerySingle(String subSql) {
		List<T> list = doDQL(subSql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询出所有的用户信息
	 * @return
	 */
	protected List<T> doQueryMultiple(String subSql) {
		return doDQL(subSql);
	}

	/**
	 * 获取数据库中的总记录数
	 * @return
	 */
	public int getRowCount(CharSequence subsql) {
		//声明一个int对象
		int rowCount = 0;
		//声明连接
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT count(1) FROM " + this.cls.getSimpleName() + subsql;
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			pstmt = DataBaseUtil.getPstmt(connection, sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, pstmt, null);
		}
		return rowCount;
	}

	/**
	 * 将结果集里面的数据存放至javabean对象
	 * @param resultSet
	 * @param bean
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void resultset2bean(ResultSet resultSet, T bean) throws SQLException, IllegalAccessException, InvocationTargetException {
		//如何获取本次查询所有的列的信息
		ResultSetMetaData rsmd = resultSet.getMetaData();
		//遍历所有的列
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			//获取列的名字
			String fieldName = rsmd.getColumnLabel(i + 1);
			//获取列对应的值
			Object fieldValue = resultSet.getObject(fieldName);
			//将结果注入到javabean
			BeanUtils.setProperty(bean, fieldName, fieldValue);
		}
	}

}
