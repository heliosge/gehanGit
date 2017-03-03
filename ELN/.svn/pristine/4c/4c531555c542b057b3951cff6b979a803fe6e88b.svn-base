/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: Pagination.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.util;

import java.util.List;

import com.jftt.wifi.common.Constant;


/**
 * class name:Pagination <BR>
 * class description: 通用分页工具类<BR>
 * Remark: <BR>
 * @version 1.00 2015-7-30
 * @author JFTT)HeTianrui
 */
public class Pagination<T> {

	private List<T> list; // 对象记录结果集
	private int totalRows = 0; // 总记录数
	private int pageSize = Constant.PAGE_SIZE; // 每页显示记录数
	private int totalPage = 1; // 总页数
	private int pageIndex = 1; // 当前页

	private boolean isFirstPage = false; // 是否为第一页
	private boolean isLastPage = false; // 是否为最后一页
	private boolean isPreviousPage = false; // 是否有前一页
	private boolean isNextPage = false; // 是否有下一页
	// private boolean isHasPreviousPage = false; // 是否有前一页
	// private boolean isHasNextPage = false; // 是否有下一页

	private int navigatePages = 6; // 导航页码数
	private int[] navigatePageNumbers; // 所有导航页号

	public Pagination(int totalRows, int pageIndex) {
		init(totalRows, pageIndex, pageSize);
	}

	public Pagination(int totalRows, int pageIndex, int pageSize) {
		init(totalRows, pageIndex, pageSize);
	}

	public Pagination(List<T> list, int totalRows, int pageIndex) {
		super();
		init(totalRows, pageIndex, pageSize);
		this.list = list;
		this.totalRows = totalRows;
		this.pageIndex = pageIndex;
	}

	public Pagination(List<T> list, int totalRows, int pageSize, int pageIndex) {
		super();
		init(totalRows, pageIndex, pageSize);
		this.list = list;
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	private void init(int totalRows, int pageIndex, int pageSize) {
		// 设置基本参数
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		this.totalPage = (this.totalRows - 1) / this.pageSize + 1;

		// 根据输入可能错误的当前号码进行自动纠正
		if (pageIndex < 1) {
			this.pageIndex = 1;
		} else if (pageIndex > this.totalPage) {
			this.pageIndex = this.totalPage;
		} else {
			this.pageIndex = pageIndex;
		}
		// 基本参数设定之后进行导航页面的计算
		calcNavigatePageNumbers();
		// 以及页面边界的判定
		judgePageBoudary();
	}

	/**
	 * 计算导航页
	 */
	private void calcNavigatePageNumbers() {
		// 当总页数小于或等于导航页码数时
		if (totalPage <= navigatePages) {
			navigatePageNumbers = new int[totalPage];
			for (int i = 0; i < totalPage; i++) {
				navigatePageNumbers[i] = i + 1;
			}
		} else { // 当总页数大于导航页码数时
			navigatePageNumbers = new int[navigatePages];
			int startNum = pageIndex - navigatePages / 2;
			int endNum = pageIndex + navigatePages / 2;

			if (startNum < 1) {
				startNum = 1;
				// (最前navPageCount页
				for (int i = 0; i < navigatePages; i++) {
					navigatePageNumbers[i] = startNum++;
				}
			} else if (endNum > totalPage) {
				endNum = totalPage;
				// 最后navPageCount页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatePageNumbers[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatePageNumbers[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void judgePageBoudary() {
		isFirstPage = (1 == pageIndex);
		isLastPage = (pageIndex == totalPage && 1 != pageIndex);
		isPreviousPage = (1 != pageIndex);
		isNextPage = (pageIndex != totalPage);
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 得到当前页的内容
	 * 
	 * @return {List}
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 得到记录总数
	 * 
	 * @return {int}
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * 得到每页显示多少条记录
	 * 
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 得到页面总数
	 * 
	 * @return {int}
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 得到当前页号
	 * 
	 * @return {int}
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 得到所有导航页号
	 * 
	 * @return {int[]}
	 */
	public int[] getNavigatePageNumbers() {
		return navigatePageNumbers;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public boolean isPreviousPage() {
		return isPreviousPage;
	}

	public boolean isNextPage() {
		return isNextPage;
	}

	public String toString() {
		String str = new String();
		str = "[" + "totalRows=" + totalRows + ",totalPage=" + totalPage + ",pageIndex=" + pageIndex + ",pageSize="
				+ pageSize + ",isFirstPage=" + isFirstPage + ",isLastPage=" + isLastPage + ",isPreviousPage="
				+ isPreviousPage + ",hasNextPage=" + isNextPage + ",navigatePageNumbers=";
		int len = navigatePageNumbers.length;
		if (len > 0)
			str += (navigatePageNumbers[0]);
		for (int i = 1; i < len; i++) {
			str += (" " + navigatePageNumbers[i]);
		}
		str += "]";
		return str;
	}
}
