/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ContestApplicationListItemVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/13        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.knowledge_contest;

import java.util.Date;

/**
 * @author JFTT)wangyifeng
 * class name:ContestApplicationListItemVo <BR>
 * class description: 大赛申请列表项 <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public class ContestApplicationListItemVo {
	/**  
	 * 报名申请主键ID
	 */
	private Integer id;
	/**  
	 * 大赛ID
	 */
	private Integer contestId;
	/**  
	 * 大赛名称
	 */
	private String contestName;
	/**  
	 * 大赛创建时间
	 */
	private String contestCreateTime;
	/**  
	 * 用户ID
	 */
	private Integer userId;
	/**  
	 * 用户姓名
	 */
	private String userName;
	/**  
	 * 申请时间
	 */
	private String applyTime;
	/**  
	 * 审批状态
	 */
	private Integer approvalStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public String getContestCreateTime() {
		return contestCreateTime;
	}

	public void setContestCreateTime(String contestCreateTime) {
		this.contestCreateTime = contestCreateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}
