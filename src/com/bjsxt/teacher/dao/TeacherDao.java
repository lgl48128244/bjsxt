package com.bjsxt.teacher.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjsxt.teacher.vo.Teacher;
import com.bjsxt.util.BaseDao;
import com.bjsxt.util.DataBaseUtil;
import com.bjsxt.util.PageUtil;
import com.bjsxt.util.SxtLogger;
import com.bjsxt.util.date.DateUtil;

public class TeacherDao extends BaseDao<Teacher> {
	/**
	 * 插入教师信息
	 * @param teacher
	 */
	public int doTeacherInsert(Teacher teacher) {
		//拼接sql语句
		String sql = "INSERT INTO TEACHER VALUES('" + teacher.getTno() + "','" + teacher.getTname() + "','" + teacher.getIdnumber() + "','" + teacher.getGender() + "','" + DateUtil.DateToString(teacher.getBirthday(), "yyyy-MM-dd") + "','" + teacher.getPhone() + "'," + teacher.getQq() + ",'" + teacher.getGrjs() + "','" + teacher.getGzlb() + "','" + DateUtil.DateToString(teacher.getHiredate(), "yyyy-MM-dd") + "',null,null);";
		return doDML(sql);

	}

	/**
	 * 用户分页查询
	 * @param pageUtil
	 * @return
	 */
	public void doTeacherPaging(PageUtil<Teacher> pageUtil) {
		//拼接sql语句
		String subSql = " LIMIT " + pageUtil.getRowStart() + "," + pageUtil.getPageSize();
		pageUtil.setList(doQueryMultiple(subSql));
	}

	public static void main(String[] args) {
		TeacherDao teacherDao = new TeacherDao();
		for (int i = 1000; i <= 9999; i++) {
			Teacher teacher = new Teacher("20111111" + i, "嫦娥" + i, "130622197604276940", "1", new Date(), "1381234" + i, Integer.parseInt("8888" + i), "嫦娥应悔偷灵药,碧海青天夜夜心", "gl", new Date());
			teacherDao.doTeacherInsert(teacher);
		}
	}

	/**
	 * 删除教师信息
	 * @param tnos
	 */
	public int doTeacherDeleteByTnos(String tnos) {
		//拼接sql语句
		String sql = "DELETE FROM TEACHER WHERE tno IN (" + tnos + ")";
		return doDML(sql);
	}

	/**
	 * 加载性别数据信息
	 * @return
	 */
	public Map<String, Integer> doTeacherLoadGender() {
		//声明一个MAP对象
		Map<String, Integer> map = new HashMap<String, Integer>();
		//声明连接
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		//拼接sql语句
		String sql = "SELECT GENDER,COUNT(*) FROM TEACHER GROUP BY GENDER";
		SxtLogger.logger.info(sql);
		try {
			//获取连接
			connection = DataBaseUtil.getConnection();
			pstmt = DataBaseUtil.getPstmt(connection, sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				map.put(resultSet.getString(1), resultSet.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(connection, pstmt, null);
		}
		return map;
	}
}
