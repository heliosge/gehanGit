/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamWrongCardBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-14        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean;

import java.util.Date;

import com.jftt.wifi.bean.exam.ExamUserAnswerBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
/**
 * 
 * class name:ExamWrongCardBean <BR>
 * class description: 错题集记录 <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-14
 * @author JFTT)chenrui
 */
/**
 * @author JFTT)zhangchen<BR>
 * class name:ExamWrongCardBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-10-19
 */
public class ExamWrongCardBean {
    private Integer id;//主键id

    private Integer fromUserId;//考试人id

    private Integer questionId;//试题id
    
    private Integer examResultId;//考试记录ID

    private Date joinTime;//加入时间

    private String evaluation;//评语
    
    private Date updateTime;//更新时间
    
    private String userAnswer;//用户答案
    
    private Integer type;//错题类型 1、正式考试 2、模拟考试 3、练习
    
    
    public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(Integer examResultId) {
		this.examResultId = examResultId;
	}
    
}