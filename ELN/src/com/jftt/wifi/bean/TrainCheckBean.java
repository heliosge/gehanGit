package com.jftt.wifi.bean;

import java.util.Date;

public class TrainCheckBean {
    private Integer id;

    private Integer trainId;

    private Integer checkUserId;
    
    private String checkUserName;

    private Integer type;

    private Integer status;

    private String refuseReason;

    private Date createTime;

    private Date updateTime;
    
    private TrainPlanBean plan;
    
    private TrainArrangeBean arrange;

    
    public TrainArrangeBean getArrange() {
		return arrange;
	}

	public void setArrange(TrainArrangeBean arrange) {
		this.arrange = arrange;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public TrainPlanBean getPlan() {
		return plan;
	}

	public void setPlan(TrainPlanBean plan) {
		this.plan = plan;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Integer checkUserId) {
        this.checkUserId = checkUserId;
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

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
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
}