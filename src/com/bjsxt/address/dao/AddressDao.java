package com.bjsxt.address.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.address.vo.Address;
import com.bjsxt.util.DataBaseUtil;
import com.bjsxt.util.SxtLogger;

public class AddressDao {
	/**
	 * 加载区划信息
	 * @param parentid
	 * @return
	 */
	public List<Address> doAddressSearch(String parentid) {
		//声明一个List<Address>对象
		List<Address> addressList = new ArrayList<Address>();
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT * FROM ADDRESS WHERE PARENTID = " + parentid;
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			statement = DataBaseUtil.getStatement(connection);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Address address = new Address();
				resultset2bean(resultSet, address);
				addressList.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, statement, null);
		}
		return addressList;
	}

	/**
	 * 将结果集里面的数据存放至javabean对象
	 * @param resultSet
	 * @param address
	 * @throws SQLException 
	 */
	private void resultset2bean(ResultSet resultSet, Address address) throws SQLException {
		address.setId(resultSet.getInt("id"));
		address.setName(resultSet.getString("name"));
		address.setParentid(resultSet.getInt("parentid"));
		address.setOrderfield(resultSet.getInt("orderfield"));
	}

}
