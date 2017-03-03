/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ContestNewsListItemVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.knowledge_contest;

import java.util.Date;

/**
 * @author JFTT)wangyifeng
 * class name:ContestNewsListItemVo <BR>
 * class description: 大赛资讯列表项 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public class ContestNewsListItemVo {
	/**  
	 * 资讯ID
	 */
	private Integer id;
	/**  
	 * 资讯标题
	 */
	private String title;
	/**  
	 * 创建日期
	 */
	private Date createTime;
	/**  
	 * 是否发布
	 */
	private Boolean isPublished;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}
}
