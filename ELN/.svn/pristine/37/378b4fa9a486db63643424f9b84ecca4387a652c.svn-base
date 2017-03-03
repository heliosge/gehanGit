/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: ExamType.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/06        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.enumtype;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * class name:ExamType <BR>
 * class description: 考试类型 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/06
 * @author JFTT)wangyifeng
 */
public enum ExamType {
	/**
	 * 正式考试
	 */
	OFFICIAL(1),
	/**
	 * 竞赛考试
	 */
	CONTEST(2),
	/**
	 * 模拟考试
	 */
	SIM(3),
	/**
	 * 学习地图测试
	 */
	LEARN_MAP_TEST(4),
	/**
	 * 课程测试
	 */
	COURSE_TEST(5);
	private int intValue;
	private static final Map<Integer, ExamType> INSTANCE_MAP = new HashMap<Integer, ExamType>();

	private ExamType(int i) {
		this.intValue = i;
	}

	/**
	 * Method name: getIntValue <BR>
	 * Description: 获得考试类型对应的Int值 <BR>
	 * Remark: <BR>
	 * 
	 * @return int<BR>
	 */
	public int getIntValue() {
		return intValue;
	}

	static {
		for (ExamType t : ExamType.values()) {
			INSTANCE_MAP.put(t.getIntValue(), t);
		}
	}

	/**
	 * Method name: getExamType <BR>
	 * Description: 根据Int值获得考试类型枚举实例 <BR>
	 * Remark: <BR>
	 * 
	 * @param type
	 * @return ExamType<BR>
	 */
	public static ExamType getExamType(Integer type) {
		Assert.notNull(type);
		Assert.isTrue(type >= OFFICIAL.getIntValue());
		Assert.isTrue(type <= COURSE_TEST.getIntValue());
		return INSTANCE_MAP.get(type);
	}

	/**
	 * Method name: getNeedBeginEndTime <BR>
	 * Description: 是否需要考试开始时间、考试结束时间 <BR>
	 * Remark: <BR>
	 * 
	 * @return boolean<BR>
	 */
	public boolean getNeedBeginEndTime() {
		return (this == OFFICIAL || this == CONTEST);
	}
}
