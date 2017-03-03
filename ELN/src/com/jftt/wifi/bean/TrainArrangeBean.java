package com.jftt.wifi.bean;

import java.util.Date;
import java.util.List;

/**
 * class name:TrainArrangeBean <BR>
 * class description: 培训安排 <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月20日
 * @author JFTT)Lu Yunlong
 */
public class TrainArrangeBean {
    private Integer id;
    private String name;
    private Integer timeType;//1:年度；2：月度
    private Integer trainPlanId;
    private String trainPlanName;//培训计划名称
    private String beginTime;
    private Date beginTimeDate;
    private Date endTimeDate;
    private String endTime;
    private Integer isJoin;//1：是；2：否
    private Integer maxJoinNum;
    private Integer joinedNum;
    private Integer isRecommend;//1：是；2：否
    private String fitCrowd;
    private String descr;
    private String cover;
    private Integer createUserId;
    private String createUserName;
    private Date createTime;
    private Date updateTime;
    private Integer companyId;
    private Integer subCompanyId;
    private Integer status;//1:编辑中；2：待审批；3：审批通过；4：审批拒绝；5:取消审批
    private String submitUserName;//提交人
    private Integer isClose;//是否关闭；1：关闭；2：开启
    private Integer allPeriod;//学时
    private Integer isRelease;//成绩是否发布
    private List<TrainArrangeOpenCrowdBean> openCrowd;//开放人群
    private List<TrainArrangeContentBean> contents;//培训内容
    private Integer getPeriod;//获得学时
    private Integer passPercent;//通过比例
    private Integer afterClassExam;//通过考试id
    private String afterClassExamName;
    private Integer aceDuration;//通过考试时长
    private Integer aceAllowTimes;//允许考试次数
    private Integer isPass;//培训是否通过1：通过；2不通过
    private Integer score;//通过考试成绩

    private Integer joinNum;
    private Integer realJoinNum;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
    
    public Integer getPassPercent() {
		return passPercent;
	}

	public void setPassPercent(Integer passPercent) {
		this.passPercent = passPercent;
	}

	public Integer getAfterClassExam() {
		return afterClassExam;
	}

	public void setAfterClassExam(Integer afterClassExam) {
		this.afterClassExam = afterClassExam;
	}

	public String getAfterClassExamName() {
		return afterClassExamName;
	}

	public void setAfterClassExamName(String afterClassExamName) {
		this.afterClassExamName = afterClassExamName;
	}

	public Integer getAceDuration() {
		return aceDuration;
	}

	public void setAceDuration(Integer aceDuration) {
		this.aceDuration = aceDuration;
	}

	public Integer getAceAllowTimes() {
		return aceAllowTimes;
	}

	public void setAceAllowTimes(Integer aceAllowTimes) {
		this.aceAllowTimes = aceAllowTimes;
	}

	public String getTrainPlanName() {
		return trainPlanName;
	}

	public void setTrainPlanName(String trainPlanName) {
		this.trainPlanName = trainPlanName;
	}

	public Integer getGetPeriod() {
		return getPeriod;
	}

	public void setGetPeriod(Integer getPeriod) {
		this.getPeriod = getPeriod;
	}

	public Integer getIsRelease() {
  		return isRelease;
  	}

  	public void setIsRelease(Integer isRelease) {
  		this.isRelease = isRelease;
  	}


    public Integer getAllPeriod() {
		return allPeriod;
	}

	public void setAllPeriod(Integer allPeriod) {
		this.allPeriod = allPeriod;
	}

	public Integer getJoinedNum() {
		return joinedNum;
	}

	public void setJoinedNum(Integer joinedNum) {
		this.joinedNum = joinedNum;
	}

	public Date getBeginTimeDate() {
		return beginTimeDate;
	}

	public void setBeginTimeDate(Date beginTimeDate) {
		this.beginTimeDate = beginTimeDate;
	}

	public Date getEndTimeDate() {
		return endTimeDate;
	}

	public void setEndTimeDate(Date endTimeDate) {
		this.endTimeDate = endTimeDate;
	}

	public Integer getIsClose() {
		return isClose;
	}

	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}

	public String getSubmitUserName() {
		return submitUserName;
	}

	public void setSubmitUserName(String submitUserName) {
		this.submitUserName = submitUserName;
	}

	public List<TrainArrangeContentBean> getContents() {
		return contents;
	}

	public void setContents(List<TrainArrangeContentBean> contents) {
		this.contents = contents;
	}

	public List<TrainArrangeOpenCrowdBean> getOpenCrowd() {
		return openCrowd;
	}

	public void setOpenCrowd(List<TrainArrangeOpenCrowdBean> openCrowd) {
		this.openCrowd = openCrowd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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

    public Integer getTrainPlanId() {
        return trainPlanId;
    }

    public void setTrainPlanId(Integer trainPlanId) {
        this.trainPlanId = trainPlanId;
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

    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }

    public Integer getMaxJoinNum() {
        return maxJoinNum;
    }

    public void setMaxJoinNum(Integer maxJoinNum) {
        this.maxJoinNum = maxJoinNum;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getFitCrowd() {
        return fitCrowd;
    }

    public void setFitCrowd(String fitCrowd) {
        this.fitCrowd = fitCrowd;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public Integer getRealJoinNum() {
        return realJoinNum;
    }

    public void setRealJoinNum(Integer realJoinNum) {
        this.realJoinNum = realJoinNum;
    }
}