package com.bjsxt.user.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bjsxt.user.vo.User;
import com.bjsxt.util.BaseDao;
import com.bjsxt.util.DataBaseUtil;
import com.bjsxt.util.PageUtil;
import com.bjsxt.util.SxtLogger;

public class UserDao extends BaseDao<User> {
	/**
	 * 将用户信息存放至数据库
	 * @param user
	 * @return
	 */
	public int doUserInsert(User user) {
		//拼接sql语句
		String sql = "INSERT INTO USER VALUES(null,'" + user.getUname() + "','" + user.getPwd() + "','" + user.getNickname() + "','" + user.getEmail() + "','300','" + user.getTeacherid() + "',NOW(),NOW())";
		return doDML(sql);
	}

	/**
	 * 验证用户名和密码是否匹配
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public User doUserLogin(String uname, String pwd) {
		//拼接sql语句
		String subSql = " WHERE UNAME = '" + uname + "' AND PWD ='" + pwd + "'";
		return doQuerySingle(subSql);
	}

	/**
	 * 一次删除多条用户记录信息
	 * @param userid
	 */
	public int doUserDeleteByIds(String userid) {
		//拼接sql语句
		String sql = "DELETE FROM USER WHERE ID IN (" + userid + ")";
		return doDML(sql);

	}

	/**
	 * 修改指定的用户信息
	 * @param user
	 * @return
	 */
	public int doUserUpdate(User user) {
		//拼接sql语句
		StringBuffer sql = new StringBuffer("UPDATE USER SET UPDATETIME = NOW() ");
		if (user != null && user.getNickname() != null && !"".equals(user.getNickname())) {
			sql.append(" ,NICKNAME = '" + user.getNickname() + "' ");
		}
		if (user != null && user.getPower() != null && !"".equals(user.getPower())) {
			sql.append(" ,POWER = '" + user.getPower() + "' ");
		}
		if (user != null && user.getTeacherid() != null && !"".equals(user.getTeacherid())) {
			sql.append(" ,TEACHERID = '" + user.getTeacherid() + "' ");
		}
		if (user != null && user.getEmail() != null && !"".equals(user.getEmail())) {
			sql.append(" ,EMAIL = '" + user.getEmail() + "' ");
		}
		if (user != null && user.getPwd() != null && !"".equals(user.getPwd())) {
			sql.append(" ,PWD = '" + user.getPwd() + "' ");
		}
		if (user != null && user.getAttachmentName() != null && !"".equals(user.getAttachmentName())) {
			sql.append(" ,attachmentName = '" + user.getAttachmentName() + "' ");
		}
		if (user != null && user.getAttachmentUuidName() != null && !"".equals(user.getAttachmentUuidName())) {
			sql.append(" ,attachmentUuidName = '" + user.getAttachmentUuidName() + "' ");
		}
		sql.append(" WHERE ID = " + user.getId());
		return doDML(sql);
	}

	/**
	 * 通过指定的条件查询用户信息
	 * @param searchUser
	 * @param pageSize 
	 * @param rowStart 
	 * @return
	 */
	public void doUserQueryPaging(User searchUser, PageUtil<User> pageUtil) {
		//拼接sql语句
		StringBuffer subSql = new StringBuffer(" WHERE 1=1 ");
		if (searchUser != null && searchUser.getUname() != null) {
			subSql.append(" AND UNAME LIKE '" + searchUser.getUname() + "%'");
		}
		subSql.append(" LIMIT " + pageUtil.getRowStart() + "," + pageUtil.getPageSize());
		//将查询到的结果存放至分页对象
		pageUtil.setList(doQueryMultiple(subSql.toString()));
	}

	/**
	 * 根据指定的条件查询符合条件的总数
	 * @param searchUser
	 * @return
	 */
	public int getRowCountByObject(User searchUser) {
		//声明一个int对象
		int rowCount = 0;
		//声明连接
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//拼接sql语句
		//拼接sql语句
		StringBuffer sql = new StringBuffer("SELECT COUNT(ID) FROM USER WHERE 1=1 ");
		if (searchUser != null && searchUser.getUname() != null) {
			sql.append(" AND UNAME LIKE '" + searchUser.getUname() + "%'");
		}
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
	 * 验证用户名是否唯一
	 * @param uname
	 * @return false 已经存在,不能使用  true  不存在,可以使用
	 */
	public User doUserCheckUnameOnly(String uname) {
		//拼接sql语句
		String subSql = " WHERE UNAME = '" + uname + "'";
		return doQuerySingle(subSql);
	}

	/**
	 * 谷歌提示
	 * @param uname
	 * @return
	 */
	public List<User> doUserGoogleSuggest(String uname) {
		//拼接sql语句
		String subSql = " WHERE UNAME LIKE '%" + uname + "%' LIMIT 10";
		return doQueryMultiple(subSql);
	}

}
