/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: SimulationExamResultBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-22        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;
/**
 * 
 * class name:SimulationExamResultBean <BR>
 * class description: 模拟考试记录表 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-22
 * @author JFTT)chenrui
 */
public class SimulationExamResultBean {
    private Integer id;

    private Integer examId;//试卷id

    private Integer fromUserId;//考试人id

    private Integer count;//考试次数

    private Date updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}