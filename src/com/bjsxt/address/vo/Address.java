package com.bjsxt.address.vo;

public class Address {
	private Integer id;
	private String name;
	private Integer parentid;
	private Integer orderfield;

	public Address() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getOrderfield() {
		return orderfield;
	}

	public void setOrderfield(Integer orderfield) {
		this.orderfield = orderfield;
	}

}
