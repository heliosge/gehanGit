/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ContestBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/12        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.knowledge_contest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jftt.wifi.bean.KnowledgeContestApplyQualificationBean;
import com.jftt.wifi.bean.KnowledgeContestAwardBean;

/**
 * @author JFTT)wangyifeng
 * class name:ContestBean <BR>
 * class description: 管理用大赛Bean <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/12
 */
public class ContestBean {
	/**  
	 * 主键ID
	 */
	private Integer id;
	/**  
	 * 大赛名称
	 */
	private String name;
	/**  
	 * 宣传图片路径，用于比赛列表页面
	 */
	private String coverImageForList;
	/**  
	 * 宣传图片路径，用于比赛浏览页面（查看大赛）
	 */
	private String coverImageForDetail;
	/**  
	 * 是否需要报名
	 */
	private Boolean needApproval;
	/**  
	 * 参赛资格
	 */
	private String qualification;
	/**  
	 * 比赛详情
	 */
	private String detail;
	/**  
	 * 参赛流程
	 */
	private String processDescription;
	/**  
	 * 大赛状态
	 */
	private Integer status;
	/**  
	 * 当前进行中赛程ID
	 */
	private Integer curMatchId;
	/**  
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**  
	 * 创建时间
	 */
	private Date createTime;
	/**  
	 * 更新时间
	 */
	private Date updateTime;
	/**  
	 * 大赛开始时间
	 */
	private Date beginTime;
	/**  
	 * 分公司ID
	 */
	private Integer subCompanyId;
	/**  
	 * 公司ID
	 */
	private Integer companyId;
	/**  
	 * 是否推广
	 */
	private Boolean isSpread;
	/**  
	 * 参数资格列表
	 */
	private List<KnowledgeContestApplyQualificationBean> applyQualificationList = new ArrayList<KnowledgeContestApplyQualificationBean>();
	/**  
	 * 奖项列表
	 */
	private List<KnowledgeContestAwardBean> awardList = new ArrayList<KnowledgeContestAwardBean>();

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

	public String getCoverImageForList() {
		return coverImageForList;
	}

	public void setCoverImageForList(String coverImageForList) {
		this.coverImageForList = coverImageForList;
	}

	public String getCoverImageForDetail() {
		return coverImageForDetail;
	}

	public void setCoverImageForDetail(String coverImageForDetail) {
		this.coverImageForDetail = coverImageForDetail;
	}

	public Boolean getNeedApproval() {
		return needApproval;
	}

	public void setNeedApproval(Boolean needApproval) {
		this.needApproval = needApproval;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCurMatchId() {
		return curMatchId;
	}

	public void setCurMatchId(Integer curMatchId) {
		this.curMatchId = curMatchId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Boolean getIsSpread() {
		return isSpread;
	}

	public void setIsSpread(Boolean isSpread) {
		this.isSpread = isSpread;
	}

	public List<KnowledgeContestApplyQualificationBean> getApplyQualificationList() {
		return applyQualificationList;
	}

	public void setApplyQualificationList(
			List<KnowledgeContestApplyQualificationBean> applyQualificationList) {
		this.applyQualificationList = applyQualificationList;
	}

	public List<KnowledgeContestAwardBean> getAwardList() {
		return awardList;
	}

	public void setAwardList(List<KnowledgeContestAwardBean> awardList) {
		this.awardList = awardList;
	}
}
