package com.jftt.wifi.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class ExpandParamBean implements Serializable {

	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = -5727080953397191886L;
	private int id;
	private int propertyId;
	private String propertyName;
	private int propertyType_;//字段类型
	private String propertyValue;//字段类型是下拉框的选项值
	private int  companyId;
	private String originalFiled;
	private int isEmpty;  //是否为选填项。0为必填，1为选填
	
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public int getPropertyType_() {
		return propertyType_;
	}
	public void setPropertyType_(int propertyType_) {
		this.propertyType_ = propertyType_;
	}
	public String getOriginalFiled() {
		return originalFiled;
	}
	public void setOriginalFiled(String originalFiled) {
		this.originalFiled = originalFiled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(int isEmpty) {
		this.isEmpty = isEmpty;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ReflectionToStringBuilder(this).toString();
	}
}
