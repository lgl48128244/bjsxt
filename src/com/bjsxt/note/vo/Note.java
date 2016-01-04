package com.bjsxt.note.vo;

import java.util.Date;

public class Note {
	private Integer id;
	private String operTable;
	private String operType;
	private Integer userId;
	private String userIp;
	private Date createTime;

	public Note() {
		// TODO Auto-generated constructor stub
	}

	public Note(String operTable, String operType, Integer userId, String userIp) {
		this.operTable = operTable;
		this.operType = operType;
		this.userId = userId;
		this.userIp = userIp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperTable() {
		return operTable;
	}

	public void setOperTable(String operTable) {
		this.operTable = operTable;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
