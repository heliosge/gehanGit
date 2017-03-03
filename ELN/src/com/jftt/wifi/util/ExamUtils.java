/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/08/12        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.util;

/**
 * @author JFTT)wangyifeng
 * class name:ExamUtils <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015/08/12
 */
public class ExamUtils {
	public static int calculatePassScore(int totalScore, int passScorePercent) {
		return (int) Math.round(totalScore * (passScorePercent / 100.0));
	}
}
