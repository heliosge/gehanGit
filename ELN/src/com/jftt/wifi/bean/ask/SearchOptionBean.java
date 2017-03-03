/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SearchOptionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-15        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean.ask;

/**
 * @author JFTT)zhangchen<BR>
 * class name:SearchOptionBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-15
 */
public class SearchOptionBean {
	/**  
	 * 用户ID
	 */
	private Integer userId;
	/**  
	 * 提问人ID
	 */
	private Integer askerId;
	/**  
	 * 子公司ID
	 */
	private Integer companyId;
	/**  
	 * 公司ID
	 */
	private Integer subCompanyId;
	/**  
	 * 问问ID
	 */
	private Integer questionId;
	/**  
	 * 1:普联管理员回答 2：企业管理员回答
	 */
	private Integer replyerType;
	/**  
	 * 回答人ID
	 */
	private Integer replyerId;
	/**  
	 * 1：满意答案 2：不是满意答案（默认）
	 */
	private Integer isSatisfactory;
	/**  
	 * 1：普通回答 2：专题回答
	 */
	private Integer isThematic;
	/**  
	 * 向谁提问 
	 */
	private Integer toWhom;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getReplyerType() {
		return replyerType;
	}
	public void setReplyerType(Integer replyerType) {
		this.replyerType = replyerType;
	}
	public Integer getIsSatisfactory() {
		return isSatisfactory;
	}
	public void setIsSatisfactory(Integer isSatisfactory) {
		this.isSatisfactory = isSatisfactory;
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
	public Integer getIsThematic() {
		return isThematic;
	}
	public void setIsThematic(Integer isThematic) {
		this.isThematic = isThematic;
	}
	public Integer getToWhom() {
		return toWhom;
	}
	public void setToWhom(Integer toWhom) {
		this.toWhom = toWhom;
	}
	public Integer getReplyerId() {
		return replyerId;
	}
	public void setReplyerId(Integer replyerId) {
		this.replyerId = replyerId;
	}
	public Integer getAskerId() {
		return askerId;
	}
	public void setAskerId(Integer askerId) {
		this.askerId = askerId;
	}
	

}
