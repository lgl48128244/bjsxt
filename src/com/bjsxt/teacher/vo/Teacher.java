package com.bjsxt.teacher.vo;

import java.util.Date;

public class Teacher {

	private String tno;
	private String tname;
	private String idnumber;
	private String gender;
	private Date birthday;
	private String phone;
	private Integer qq;
	private String grjs;
	private String gzlb;
	private Date hiredate;
	private Integer userid;
	private String classid;

	public Teacher() {

	}

	public Teacher(String tno, String tname, String idnumber, String gender, Date birthday, String phone, Integer qq, String grjs, String gzlb, Date hiredate) {
		this.tno = tno;
		this.tname = tname;
		this.idnumber = idnumber;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.qq = qq;
		this.grjs = grjs;
		this.gzlb = gzlb;
		this.hiredate = hiredate;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getGrjs() {
		return grjs;
	}

	public void setGrjs(String grjs) {
		this.grjs = grjs;
	}

	public String getGzlb() {
		return gzlb;
	}

	public void setGzlb(String gzlb) {
		this.gzlb = gzlb;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

}
