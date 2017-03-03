package com.jftt.wifi.bean.vo;

import java.util.Date;
import java.util.List;

public class MallCourseCategoryVo {

	 private Integer id;

	    private String name;

	    private Integer parentId;

	    private String descr;

	    private String createTime;

	    private Integer createUserId;

	    private String updateTime;

	    private Integer updateUserId;
	    
	    private Integer orderNum;

	    private Integer isDelete;
	    
	    private List<MallCourseCategoryVo> children;
	    private Integer type;

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

		public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

		public String getDescr() {
			return descr;
		}

		public void setDescr(String descr) {
			this.descr = descr;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public Integer getCreateUserId() {
			return createUserId;
		}

		public void setCreateUserId(Integer createUserId) {
			this.createUserId = createUserId;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public Integer getUpdateUserId() {
			return updateUserId;
		}

		public void setUpdateUserId(Integer updateUserId) {
			this.updateUserId = updateUserId;
		}

		public Integer getIsDelete() {
			return isDelete;
		}

		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}

		public List<MallCourseCategoryVo> getChildren() {
			return children;
		}

		public void setChildren(List<MallCourseCategoryVo> children) {
			this.children = children;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
		}
	    
	    

}
