package com.jftt.wifi.bean;

import java.util.Date;

public class TrainConfigBean {
    private Integer id;

    private Integer isCheck;//是否需要审批1：是；2：否

    private String checkUserId;

    private String checkUserName;

    private String checkAdvice;//审批建议

    private Double joinEndTime;

    private Integer isQueryJoinDetail;//是否可以查看报名明细1：是；2：否；

    private Date createTime;

    private Date updateTime;

    private Integer companyId;

    private Integer subCompanyId;

    private String checkReason;//审批理由模板已“@”分割

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public String getCheckAdvice() {
        return checkAdvice;
    }

    public void setCheckAdvice(String checkAdvice) {
        this.checkAdvice = checkAdvice;
    }

    public Double getJoinEndTime() {
		return joinEndTime;
	}

	public void setJoinEndTime(Double joinEndTime) {
		this.joinEndTime = joinEndTime;
	}

	public Integer getIsQueryJoinDetail() {
		return isQueryJoinDetail;
	}

	public void setIsQueryJoinDetail(Integer isQueryJoinDetail) {
		this.isQueryJoinDetail = isQueryJoinDetail;
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

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }
}