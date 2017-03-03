/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ContestListItemVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/12        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.knowledge_contest;

/**
 * @author JFTT)wangyifeng
 * class name:ContestListItemVo <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/12
 */
public class ContestListItemVo {
	/**  
	 * 主键ID
	 */
	private Integer id;
	private String status;
	/**  
	 * 大赛名称
	 */
	private String name;
	/**  
	 * 大赛宣传图片（用于列表展示）
	 */
	private String coverImageForList;
	/**  
	 * 赛程数目
	 */
	private Integer matchCounts;
	/**  
	 * 当前赛程序号（from 1）
	 */
	private Integer curMatchOrderNum;
	
	private Integer isSpread;
	
	

	public Integer getIsSpread() {
		return isSpread;
	}

	public void setIsSpread(Integer isSpread) {
		this.isSpread = isSpread;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverImageForList() {
		return coverImageForList;
	}

	public void setCoverImageForList(String coverImageForList) {
		this.coverImageForList = coverImageForList;
	}

	public Integer getMatchCounts() {
		return matchCounts;
	}

	public void setMatchCounts(Integer matchCounts) {
		this.matchCounts = matchCounts;
	}

	public Integer getCurMatchOrderNum() {
		return curMatchOrderNum;
	}

	public void setCurMatchOrderNum(Integer curMatchOrderNum) {
		this.curMatchOrderNum = curMatchOrderNum;
	}
	
}
