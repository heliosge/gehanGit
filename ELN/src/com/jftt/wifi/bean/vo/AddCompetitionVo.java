/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AddCompetitionVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-28        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

/**
 * @author JFTT)chenrui
 * class name:AddCompetitionVo <BR>
 * class description: 大赛新增页面参数vo <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-28
 * @author JFTT)chenrui
 */
public class AddCompetitionVo {
	private Integer id;//大赛id
	private String userId;
	private String companyId;//公司id
	private String subCompanyId;//子公司id
	private String megagameName;// 大赛名称
	private String qualification;// 参赛流程
	private String qualificationType;// 参赛资格类型
	private String detail;// 比赛详情
	private String needApproval;//是否需要报名
	private String processDescription;//赛程流程
	private String competitionImgPath;//大赛宣传图片
	private String postId;//选择岗位id
	private String operateType;//1新增操作2修改操作
	private String[] awardsNameStrs;//奖项名称
	private String[] awardsCountsStrs;//奖项数
	private String[] prizeNameStrs;//奖品名称
	private String[] awardsPathStrs;//奖品图片
	private String[] awardsNumsStrs;//奖项序号
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(String subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getMegagameName() {
		return megagameName;
	}
	public void setMegagameName(String megagameName) {
		this.megagameName = megagameName;
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
	public String getNeedApproval() {
		return needApproval;
	}
	public void setNeedApproval(String needApproval) {
		this.needApproval = needApproval;
	}
	public String getProcessDescription() {
		return processDescription;
	}
	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}
	public String getCompetitionImgPath() {
		return competitionImgPath;
	}
	public void setCompetitionImgPath(String competitionImgPath) {
		this.competitionImgPath = competitionImgPath;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String[] getAwardsNameStrs() {
		return awardsNameStrs;
	}
	public void setAwardsNameStrs(String[] awardsNameStrs) {
		this.awardsNameStrs = awardsNameStrs;
	}
	public String[] getAwardsCountsStrs() {
		return awardsCountsStrs;
	}
	public void setAwardsCountsStrs(String[] awardsCountsStrs) {
		this.awardsCountsStrs = awardsCountsStrs;
	}
	public String[] getPrizeNameStrs() {
		return prizeNameStrs;
	}
	public void setPrizeNameStrs(String[] prizeNameStrs) {
		this.prizeNameStrs = prizeNameStrs;
	}
	public String[] getAwardsPathStrs() {
		return awardsPathStrs;
	}
	public void setAwardsPathStrs(String[] awardsPathStrs) {
		this.awardsPathStrs = awardsPathStrs;
	}
	public String[] getAwardsNumsStrs() {
		return awardsNumsStrs;
	}
	public void setAwardsNumsStrs(String[] awardsNumsStrs) {
		this.awardsNumsStrs = awardsNumsStrs;
	}
	public String getQualificationType() {
		return qualificationType;
	}
	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}
	
}
