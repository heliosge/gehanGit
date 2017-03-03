/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: TeacherBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月22日        | JFTT)liucaibao    | original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * class name:TeacherBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月22日
 * @author JFTT)liucaibao
 */
public class TeacherBean implements Serializable {

	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = 1L;

	private int teacherId;
	
	/**  
	 * define a field companyId which type is int
	 */
	private int companyId;
	
	/**  
	 * 
	 */
	private String userName;
	

	/**  
	 * define a field teacher_name which type is String
	 */
	private String teacherName;
	/**  
	 * define a field sex which type is int
	 */
	private int sex;
	/**  
	 * define a field teacher_category which type is int
	 */
	private int teacherCategory;
	/**  
	 * define a field e_mail which type is String
	 */
	private String eMail;
	/**  
	 * define a field card_id which type is int
	 */
	private String cardId;
	/**  
	 * define a field education which type is String
	 */
	private String education;
	/**  
	 * define a field phone_num which type is int
	 */
	private String phoneNum;
	/**  
	 * define a field icon_path which type is String
	 */
	private String iconPath;
	/**  
	 * define a field description which type is String
	 */
	private String description;
	/**  
	 * define a field create_user_id which type is int
	 */
	private int createUserId;
	/**  
	 * define a field create_time which type is String
	 */
	private String createTime;
	/**  
	 * define a field update_user_id which type is int
	 */
	private int updateUserId;
	/**  
	 * define a field update_time which type is String
	 */
	private String updateTime;
	
	private int userId;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getTeacherCategory() {
		return teacherCategory;
	}
	public void setTeacherCategory(int teacherCategory) {
		this.teacherCategory = teacherCategory;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
 
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ReflectionToStringBuilder(this).toString();
	}
}
