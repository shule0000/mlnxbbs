package com.mlnxBBS.core;

public class PageBean {

	private int totalPageCount;// 总页数
	private int pageCount = 1;// 每页显示的数量
	private int currentPageNum;// 当前页码
	private int totalCount;// 总条数

	public int getTotalPageCount() {
		if (totalCount % pageCount == 0) {
			totalPageCount = totalCount / pageCount;
		} else {
			totalPageCount = totalCount / pageCount + 1;
		}

		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {

		this.currentPageNum = currentPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
