/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: QuestionType.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/07/27        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.enumtype;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.jftt.wifi.bean.exam.QuestionRawTypeInfo;

/**
 * class name:QuestionType <BR>
 * class description: 试题类型Enum <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/07/27
 * @author JFTT)wangyifeng
 */
public enum QuestionDisplayType {
	/**
	 * 主观题
	 */
	SUBJECTIVE(1),
	/**
	 * 单选题
	 */
	SINGLE_CHOICE(2),
	/**
	 * 多选题
	 */
	MULTI_CHOICE(3),
	/**
	 * 判断题
	 */
	DETERMINE(4),
	/**
	 * 填空题
	 */
	FILL_BLANK(5),
	/**
	 * 组合题
	 */
	GROUP(6),
	/**
	 * 多媒体题
	 */
	MULTIMEDIA(7);
	private int type;
	private static final Map<Integer, QuestionDisplayType> typeMap = new HashMap<Integer, QuestionDisplayType>();

	/**
	 * Method name: QuestionType<BR>
	 * Description: 内部构造函数<BR>
	 * Remark: <BR>
	 * 
	 * @param type
	 * <BR>
	 */
	private QuestionDisplayType(int type) {
		this.type = type;
	}

	/**
	 * Method name: getIntValue <BR>
	 * Description: 获取int值 <BR>
	 * Remark: <BR>
	 * 
	 * @return int<BR>
	 */
	public int getIntValue() {
		return type;
	}

	static {
		for (QuestionDisplayType type : QuestionDisplayType.values()) {
			typeMap.put(type.getIntValue(), type);
		}
	}

	/**
	 * Method name: getQuestionType <BR>
	 * Description: 获取试题类型（包含多媒体题） <BR>
	 * Remark: <BR>
	 * 
	 * @param q
	 * @return QuestionType<BR>
	 */
	public static QuestionDisplayType getDisplayQuestionType(
			QuestionRawTypeInfo q) {
		Assert.notNull(q);
		if (q.getIsMultimedia() != null && q.getIsMultimedia()) {
			return MULTIMEDIA;
		}
		Assert.notNull(q.getType());
		return typeMap.get(q.getType());
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: checkValidQuestion <BR>
	 * Description: 判断是否能根据试题对象获取试题展示类型 <BR>
	 * Remark: <BR>
	 * @param q
	 * @return  boolean<BR>
	 */
	public static boolean checkValidQuestion(QuestionRawTypeInfo q) {
		return q.getIsMultimedia() != null && q.getType() != null;
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getDisplayQuestionType <BR>
	 * Description: 获取试题类型（包含多媒体题） <BR>
	 * Remark: <BR>
	 * @param displayTypeIntValue
	 * @return  QuestionDisplayType<BR>
	 */
	public static QuestionDisplayType getDisplayQuestionType(
			Integer displayTypeIntValue) {
		Assert.notNull(displayTypeIntValue);
		return typeMap.get(displayTypeIntValue);
	}

	public Boolean getIsMultimedia() {
		if (this == MULTIMEDIA) {
			return true;
		}
		return null;
	}

	public Integer getRawType() {
		if (this != MULTIMEDIA) {
			return this.getIntValue();
		}
		return null;
	}
}
