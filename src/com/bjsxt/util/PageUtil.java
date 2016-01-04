package com.bjsxt.util;

import java.util.List;

/**
 * 分页数据的javabean
 * @author Administrator
 *
 */
public class PageUtil<T> {
	//当前页码数
	private Integer pageNum;
	//每页显示的数量
	private Integer pageSize;
	//数据库可查询的总记录数
	private Integer rowCount;
	//总页码数
	private Integer pageCount;
	//本次查询的时候其实记录数
	private Integer rowStart;

	//每页显示的页码数总数
	private Integer everyPageCount;
	//每页显示的页码数的起始数
	private Integer everyPageStart;
	//每页显示的页码数的结束数
	private Integer everyPageEnd;

	//是否有上一页
	private Boolean hasPrevious = false;
	//首页
	private Integer firstPageNum;
	//上一页
	private Integer previousPageNum;

	//是否有下一页
	private Boolean hasNext = false;
	//下一页
	private Integer nextPageNum;
	//尾页
	private Integer lastPageNum;
	//将查询到的结果存放至分页对象
	private List<T> list;
	//分页请求地址
	private String linkAddr;

	/**
	 * 通过构造器，在创建对象的时候就默认计算出其他属性对应的值
	 * @param pageNum
	 * @param pageSize
	 * @param rowCount
	 */
	public PageUtil(String pageNumString, String pageSizeString, Integer rowCount, String linkAddr) {
		//每页显示的数量
		this.pageSize = pageSizeString == null ? 10 : Integer.parseInt(pageSizeString);
		//可查询的总记录数
		this.rowCount = rowCount;
		//总页码数
		this.pageCount = (int) Math.ceil(rowCount * 1.0 / pageSize);
		//当前页码数
		this.pageNum = pageNumString == null ? 1 : Integer.parseInt(pageNumString);
		if (this.pageNum > this.pageCount && this.pageCount > 0) {
			this.pageNum = this.pageCount;
		}
		//每次查询开始的行数
		this.rowStart = (this.pageNum - 1) * this.pageSize;
		//设置每页显示的页码数量
		this.everyPageCount = 10;
		//每页显示的页码数的起始数
		this.everyPageStart = (this.pageNum - this.everyPageCount / 2) >= 1 ? (this.pageNum - this.everyPageCount / 2) : 1;
		//每页显示的页码数的结束数
		this.everyPageEnd = (this.pageNum + this.everyPageCount / 2) <= this.pageCount ? (this.pageNum + this.everyPageCount / 2) : this.pageCount;
		//是否有上一页  首页
		if (this.pageNum > 1) {
			this.hasPrevious = true;
			this.firstPageNum = 1;
			this.previousPageNum = this.pageNum - 1;
		}
		//是否有下一页 尾页
		if (this.pageNum < this.pageCount) {
			this.hasNext = true;
			this.lastPageNum = this.pageCount;
			this.nextPageNum = this.pageNum + 1;
		}
		//分页请求地址
		this.linkAddr = linkAddr;
	}

	public PageUtil() {

	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getRowStart() {
		return rowStart;
	}

	public void setRowStart(Integer rowStart) {
		this.rowStart = rowStart;
	}

	public Integer getEveryPageCount() {
		return everyPageCount;
	}

	public void setEveryPageCount(Integer everyPageCount) {
		this.everyPageCount = everyPageCount;
	}

	public Integer getEveryPageStart() {
		return everyPageStart;
	}

	public void setEveryPageStart(Integer everyPageStart) {
		this.everyPageStart = everyPageStart;
	}

	public Integer getEveryPageEnd() {
		return everyPageEnd;
	}

	public void setEveryPageEnd(Integer everyPageEnd) {
		this.everyPageEnd = everyPageEnd;
	}

	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public Integer getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(Integer firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public Integer getPreviousPageNum() {
		return previousPageNum;
	}

	public void setPreviousPageNum(Integer previousPageNum) {
		this.previousPageNum = previousPageNum;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Integer getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(Integer nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public Integer getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(Integer lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

}
