/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: KlEvaluateBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-14        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;
/**
 * 
 * class name:KlEvaluateBean <BR>
 * class description: 知识评价 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-14
 * @author JFTT)chenrui
 */
public class KlEvaluateBean {
    private Integer id;//主键id

    private Integer resourceId;//资源id

    private Integer fromUserId;//评价人id

    private Integer scoreLevel;//评分等级

    private Date createTime;//评分时间

    private String evaluateContent;//评论内容

    private Date updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getScoreLevel() {
        return scoreLevel;
    }

    public void setScoreLevel(Integer scoreLevel) {
        this.scoreLevel = scoreLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}