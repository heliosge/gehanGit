/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamState.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/04        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.enumtype;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.Assert;

import com.jftt.wifi.bean.exam.ExamRawStateInfo;

/**
 * class name:ExamState <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/04
 * @author JFTT)wangyifeng
 */
public enum ExamState {
	/**
	 * 全部
	 */
	ALL_STATE(0),
	/**
	 * 未发布
	 */
	NOT_PUBLISHED(1),
	/**
	 * 已发布
	 */
	PUBLISHED(2),
	/**
	 * 未开始
	 */
	BEFORE_START(3),
	/**
	 * 进行中
	 */
	PROCESSING(4),
	/**
	 * 已结束
	 */
	FINISHED(5);

	/**
	 * int值，方便sql查询条件的书写
	 */
	private int intValue;

	private ExamState(int intValue) {
		this.intValue = intValue;
	}

	/**
	 * Method name: getExamState <BR>
	 * Description: 获取ExamState实例 <BR>
	 * Remark: <BR>
	 * 
	 * @param exam
	 * @return ExamState<BR>
	 * @throws ParseException 
	 */
	public static ExamState getExamState(ExamRawStateInfo exam) throws ParseException {
		Assert.notNull(exam.getIsPublished());
		Assert.notNull(exam.getType());
		ExamState result = ALL_STATE;
		if(!exam.getIsPublished()){
			result = NOT_PUBLISHED;
		}
		if (exam.getType() == 1 && exam.getIsPublished()) {
			Assert.notNull(exam.getBeginTime());
			Assert.notNull(exam.getEndTime());
			Date curDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (curDate.before(format.parse(exam.getBeginTime()))) {
				result = BEFORE_START;
			} else if (curDate.after(format.parse(exam.getEndTime()))) {
				result = FINISHED;
			} else {
				result = PROCESSING;
			}
		}
		if (exam.getType() == 3 && exam.getIsPublished()) {
			result = PUBLISHED;
		}
		return result;
	}

	public Integer getIntValue() {
		return intValue;
	}

	/**
	 * Method name: getCanBeDeleted <BR>
	 * Description: 是否可以删除 <BR>
	 * Remark: <BR>
	 * 
	 * @return Boolean<BR>
	 */
	public Boolean getCanBeDeleted() {
		return this == ExamState.NOT_PUBLISHED;
	}

	/**
	 * Method name: getCanBeCanceled <BR>
	 * Description: 是否可以取消 <BR>
	 * Remark: <BR>
	 * 
	 * @return Boolean<BR>
	 */
	public Boolean getCanBeCanceled() {
		return this == ExamState.BEFORE_START;
	}
}
