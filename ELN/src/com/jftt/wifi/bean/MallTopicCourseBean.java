package com.jftt.wifi.bean;

import java.math.BigDecimal;

import com.sun.star.bridge.oleautomation.Decimal;

public class MallTopicCourseBean {
	private Integer id;
	private Integer mallCourseId;
	private Integer topicId;
	private BigDecimal price;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMallCourseId() {
		return mallCourseId;
	}
	public void setMallCourseId(Integer mallCourseId) {
		this.mallCourseId = mallCourseId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
