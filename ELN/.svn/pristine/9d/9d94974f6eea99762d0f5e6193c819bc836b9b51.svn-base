/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2015
 * FileName: DifficultyLevelCountVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/04        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.bean.exam.vo;

import org.apache.commons.lang.StringUtils;

/**
 * class name:DifficultyLevelCountVo <BR>
 * class description: 试题难度数量对应关系 <BR>
 * Remark: <BR>
 * 
 * @version 1.00 2015/08/04
 * @author JFTT)wangyifeng
 */
public class DifficultyLevelCountVo {
	/**
	 * 难度级别
	 */
	private Integer difficultyLevel;
	/**
	 * 指定难度对应的试题数目
	 */
	private Integer count;
	/**  
	 * 分值
	 */
	private Integer score;

	/**
	 * @author JFTT)wangyifeng
	 * Method name: getInstance <BR>
	 * Description: 根据规则项得到实例 <BR>
	 * Remark: <BR>
	 * @param ruleItem
	 * @return  DifficultyLevelCountVo<BR>
	 */
	public static DifficultyLevelCountVo getInstance(String ruleItem) {
		DifficultyLevelCountVo result = new DifficultyLevelCountVo();
		String[] ruleDetail = StringUtils.split(ruleItem, ':');
		result.setDifficultyLevel(Integer.parseInt(ruleDetail[0]));
		result.setCount(Integer.parseInt(ruleDetail[1]));
		result.setScore(Integer.parseInt(ruleDetail[2]));
		return result;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
