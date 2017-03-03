package com.jftt.wifi.bean;

import java.math.BigDecimal;
import java.util.Date;

public class MallCourseBean {
    private Integer id;

    private Integer courseId;

    private Integer categoryId;

    private BigDecimal price;

    private BigDecimal cheapPrice;

    private Integer discount;

    private Date putawayTime;

    private Integer putawayUserId;

    private Integer status;

    private Integer isCopy;

    private Integer copyCourseId;

    private Integer isDelete;

    
    private String courseName;
    private String courseImage;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCheapPrice() {
        return cheapPrice;
    }

    public void setCheapPrice(BigDecimal cheapPrice) {
        this.cheapPrice = cheapPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getPutawayTime() {
        return putawayTime;
    }

    public void setPutawayTime(Date putawayTime) {
        this.putawayTime = putawayTime;
    }

    public Integer getPutawayUserId() {
        return putawayUserId;
    }

    public void setPutawayUserId(Integer putawayUserId) {
        this.putawayUserId = putawayUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(Integer isCopy) {
        this.isCopy = isCopy;
    }

    public Integer getCopyCourseId() {
        return copyCourseId;
    }

    public void setCopyCourseId(Integer copyCourseId) {
        this.copyCourseId = copyCourseId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}
    
    
}