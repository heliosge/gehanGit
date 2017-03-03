/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: AskDetailBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.ask;

import java.util.Date;

import com.jftt.wifi.bean.questionnaire.UserBaseBean;

/**
 * class name:AskDetailBean <BR>
 * class description: 问问详情bean <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)ShenHaijun
 */
public class AskDetailBean extends UserBaseBean{
	private Integer id;//问问id
	private Integer typeId;//分类id
	private String typeName;//分类名称
	private String title;//问问标题
	private String content;//问问内容
	private Integer askerId;//提问人id
	private String askerName;//提问人姓名
	private String createTime;//提问时间
	private String addPics;//补充图片（存放多张图片的地址，以逗号分割）
	private String label;//标签（逗号分割，最多输入5个）
	private Date effectiveTime;//有效期（问题有效截止时间）
	private String toWhom;//向谁提问（1:普联运营商 2：企业管理员（默认））
	private Integer isAnonymous;//是否匿名（1:匿名 2：不匿名（默认））
	private Integer isTop;//是否置顶（1：置顶 2：不置顶（默认））
	private Date topTime;//置顶时间
	private Integer isDelete;//是否删除（1:删除 2：未删除（默认））
	private Integer isThematic;//是否专题回答（1：普通问问 2：专题问答）
	private String initialAnswer;//初始答案（专题使用）
	private String coverPic;//封面图片（专题使用）
	private Integer rewardPoints;//奖励积分
	private Integer companyId;//公司id
	private Integer subCompanyId;//子公司id
	
	private Integer answerCount;//回答条数
	private Integer myAnswerCount;//我的回答条数
	private Integer satisfactoryAnswerCount;//满意答案条数
	
	public Integer getSatisfactoryAnswerCount() {
		return satisfactoryAnswerCount;
	}
	public void setSatisfactoryAnswerCount(Integer satisfactoryAnswerCount) {
		this.satisfactoryAnswerCount = satisfactoryAnswerCount;
	}
	public Integer getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAskerId() {
		return askerId;
	}
	public void setAskerId(Integer askerId) {
		this.askerId = askerId;
	}
	public String getAskerName() {
		return askerName;
	}
	public void setAskerName(String askerName) {
		this.askerName = askerName;
	}
	public String getAddPics() {
		return addPics;
	}
	public void setAddPics(String addPics) {
		this.addPics = addPics;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getToWhom() {
		return toWhom;
	}
	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}
	public Integer getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Date getTopTime() {
		return topTime;
	}
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsThematic() {
		return isThematic;
	}
	public void setIsThematic(Integer isThematic) {
		this.isThematic = isThematic;
	}
	public String getInitialAnswer() {
		return initialAnswer;
	}
	public void setInitialAnswer(String initialAnswer) {
		this.initialAnswer = initialAnswer;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Integer getMyAnswerCount() {
		return myAnswerCount;
	}
	public void setMyAnswerCount(Integer myAnswerCount) {
		this.myAnswerCount = myAnswerCount;
	}
	
	
}
