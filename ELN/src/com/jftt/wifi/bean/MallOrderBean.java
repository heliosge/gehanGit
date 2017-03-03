package com.jftt.wifi.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class MallOrderBean {
    private Integer id;

    private String code;

    private Integer userId;

    private Integer commodityType;

    private BigDecimal price;

    private String userRealName;

    private String invoiceTitle;

    private String invoicePhone;

    private String invoiceAddress;

    private String invoicePostcode;

    private Integer type;

    private Integer status;

    private Integer invoiceStatus;

    private Integer payStatus;

    private Integer gatheringStatus;

    private String postUserName;

    private Integer postUserId;

    private Integer invoiceUserId;

    private Integer logisticsCompanyId;

    private String logisticsNumber;

    private Integer logisticsUserId;

    private String payUserName;

    private Integer payUserId;
    
    private Date payTime;

    private String companyName;

    private String contactWay;

    private Integer isNeedInvoice;

    private String evaluation;

    private String gatheringUserName;

    private Integer gatheringUserId;
    
    private String remarks;
    
    private Date invalidTime;
    
    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
    
    private String productIds;
    
    private String productNames;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoicePhone() {
        return invoicePhone;
    }

    public void setInvoicePhone(String invoicePhone) {
        this.invoicePhone = invoicePhone;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoicePostcode() {
        return invoicePostcode;
    }

    public void setInvoicePostcode(String invoicePostcode) {
        this.invoicePostcode = invoicePostcode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getGatheringStatus() {
        return gatheringStatus;
    }

    public void setGatheringStatus(Integer gatheringStatus) {
        this.gatheringStatus = gatheringStatus;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public Integer getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(Integer postUserId) {
        this.postUserId = postUserId;
    }

    public Integer getInvoiceUserId() {
        return invoiceUserId;
    }

    public void setInvoiceUserId(Integer invoiceUserId) {
        this.invoiceUserId = invoiceUserId;
    }

    public Integer getLogisticsCompanyId() {
        return logisticsCompanyId;
    }

    public void setLogisticsCompanyId(Integer logisticsCompanyId) {
        this.logisticsCompanyId = logisticsCompanyId;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public Integer getLogisticsUserId() {
        return logisticsUserId;
    }

    public void setLogisticsUserId(Integer logisticsUserId) {
        this.logisticsUserId = logisticsUserId;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    public Integer getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(Integer payUserId) {
        this.payUserId = payUserId;
    }

    public String getGatheringUserName() {
        return gatheringUserName;
    }

    public void setGatheringUserName(String gatheringUserName) {
        this.gatheringUserName = gatheringUserName;
    }

    public Integer getGatheringUserId() {
        return gatheringUserId;
    }

    public void setGatheringUserId(Integer gatheringUserId) {
        this.gatheringUserId = gatheringUserId;
    }

    
    public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public Integer getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public void setIsNeedInvoice(Integer isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProductNames() {
		return productNames;
	}

	public void setProductNames(String productNames) {
		this.productNames = productNames;
	}

}