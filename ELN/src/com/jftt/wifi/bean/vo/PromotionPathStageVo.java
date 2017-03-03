/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PromotionPathStageVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月10日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.bean.vo;

/**
 * class name:PromotionPathStageVo <BR>
 * class description: 晋升路径阶段vo <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月10日
 * @author JFTT)ShenHaijun
 */
public class PromotionPathStageVo {
	private Integer id;//阶段id
	private Integer postId;//阶段对应的岗位id
	private String postName;//阶段对应的岗位名字
	private Integer orderNum;//阶段序号
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
