/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PromotionPathDetailVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月13日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:PromotionPathDetailVo <BR>
 * class description: 晋升路径详情vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月13日
 * @author JFTT)ShenHaijun
 */
public class PromotionPathDetailVo {
	private Integer id;//晋升路径id
	private String name;//晋升路径名称
	private Integer promotionState;//晋升状态(1：晋升完毕，2：晋升中)
	private Integer orderNum;//当前岗位在该路径中的阶段序号
	private Integer promotionPercent;//晋升百分比（0到100之间的整数）
	private Integer stageId;//当前岗位所处的阶段id
	
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
	public Integer getPromotionState() {
		return promotionState;
	}
	public void setPromotionState(Integer promotionState) {
		this.promotionState = promotionState;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getPromotionPercent() {
		return promotionPercent;
	}
	public void setPromotionPercent(Integer promotionPercent) {
		this.promotionPercent = promotionPercent;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
}
