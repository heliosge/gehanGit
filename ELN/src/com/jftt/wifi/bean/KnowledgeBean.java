package com.jftt.wifi.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * class name:知识管理bean
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月24日
 * @author JFTT)liucaibao
 */
public class KnowledgeBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7124312780468392305L;
	
	private int knowledgeId;
	private String knowledgeName;
	private int categoryId;
	private int companyId;
	private int subCompanyId;
	private int isOpen;
	private int isDownload;
	private int isRecommend; 
	private int isShare;
	private int isPublish;
	private int uploadType;
	private int status;
	private String tags;
	private String knowledgeDesc;
	private String refuseMemo;
	private int auditTime;
	private int isDelete;
	private String fileName;
	private String fileSize;
	private String filePath;
	private String extendName; 
	private int resourceType; 
	private String knowledgeText;
	private int createUserId;
	private String createUserName;
	private Date createTime;
	private int updateUserId;
	private Date updateTime ;
	private int userId;
	private String beginTime;
	private String endTime;
	private int shareStatus;
	private Date shareTime;
	
	private int fromNum;
	private int page;
	
	private String categoryName;
	private String userName;
	private String userIds;
	
	private String categoryIds;
	
	private int  roleType ;
	
	private int flag;
	
	private int isAccident;//是否为事故案例分析  1是 0否

	public int getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(int knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Integer isDownload) {
		this.isDownload = isDownload;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Integer getUploadType() {
		return uploadType;
	}

	public void setUploadType(Integer uploadType) {
		this.uploadType = uploadType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getKnowledgeDesc() {
		return knowledgeDesc;
	}

	public void setKnowledgeDesc(String knowledgeDesc) {
		this.knowledgeDesc = knowledgeDesc;
	}

	public String getRefuseMemo() {
		return refuseMemo;
	}

	public void setRefuseMemo(String refuseMemo) {
		this.refuseMemo = refuseMemo;
	}

	public Integer getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Integer auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExtendName() {
		return extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getKnowledgeText() {
		return knowledgeText;
	}

	public void setKnowledgeText(String knowledgeText) {
		this.knowledgeText = knowledgeText;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}

	public Date getShareTime() {
		return shareTime;
	}

	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	public int getFromNum() {
		return fromNum;
	}

	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getIsAccident() {
		return isAccident;
	}

	public void setIsAccident(int isAccident) {
		this.isAccident = isAccident;
	}

	
	
}
