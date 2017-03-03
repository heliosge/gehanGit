package com.jftt.wifi.bean;

import java.util.Date;

/**
 * @author JFTT)wangyifeng
 * class name:KnowledgeContestApplyQualificationBean <BR>
 * class description: 参赛资格Bean <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/13
 */
public class KnowledgeContestApplyQualificationBean {
    /**  
     * 主键ID
     */
    private Integer id;

    /**  
     * 大赛ID
     */
    private Integer contestId;

    /**  
     * 参赛资格类型
     */
    private Integer qualificationType;

    /**  
     * 参赛资格ID
     */
    private Integer qualificationId;

    /**  
     * 参赛资格名称
     */
    private String qualificationName;

    /**  
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(Integer qualificationType) {
        this.qualificationType = qualificationType;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
}
