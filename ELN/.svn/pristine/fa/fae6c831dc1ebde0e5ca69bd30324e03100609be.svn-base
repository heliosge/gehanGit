/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseDurationUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2016年2月1日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.util;

import java.util.List;

/**
 * class name:CourseDurationUtil <BR>
 * class description: 课程时长工具类 <BR>
 * Remark: <BR>
 * @version 1.00 2016年2月1日
 * @author JFTT)ShenHaijun
 */
public class CourseDurationUtil {
	/**
	 * Method name: getDurationStrArr <BR>
	 * Description: 将获取的所有时长（以秒计）相加并转换为时分秒字符串数组的形式 <BR>
	 * Remark: <BR>
	 * @param durations
	 * @return  String[]<BR>
	 */
	public static String[] getDurationStrArr(List<Integer> durations) {
		String[] totalDurationArr = new String[3];//返回的时分秒数组
		if(durations != null && durations.size() > 0){
			Integer totalDuration = 0;//课程的时长总和
			for (int i = 0; i < durations.size(); i++) {
				if(durations.get(i) != null){
					totalDuration += durations.get(i);
				}
			}
			//如果有课程时长
			if(totalDuration > 0){
				Integer hourNum = totalDuration/3600;
				Integer minNum = totalDuration/60 - hourNum*60;
				Integer secNUm = totalDuration - minNum*60 - hourNum*3600;
				//小时
				if(hourNum < 10){
					totalDurationArr[0] = "0" + hourNum;
				}else{
					totalDurationArr[0] = "" + hourNum;
				}
				//分钟
				if(minNum < 10){
					totalDurationArr[1] = "0" + minNum;
				}else{
					totalDurationArr[1] = "" + minNum;
				}
				//秒
				if(secNUm < 10){
					totalDurationArr[2] = "0" + secNUm;
				}else{
					totalDurationArr[2] = "" + secNUm;
				}
			}else{
				totalDurationArr[0] = "00";
				totalDurationArr[1] = "00";
				totalDurationArr[2] = "00";
			}
		}else{
			totalDurationArr[0] = "00";
			totalDurationArr[1] = "00";
			totalDurationArr[2] = "00";
		}
		return totalDurationArr;
	}
	
	/**
	 * Method name: getDurationStrArr <BR>
	 * Description: 将获取的所有时长（以秒计）相加并转换为时分秒字符串数组的形式 <BR>
	 * Remark: <BR>
	 * @param durations
	 * @return  String[]<BR>
	 */
	public static String[] getDurationStrArrOfLong(List<Long> durations) {
		String[] totalDurationArr = new String[3];//返回的时分秒数组
		if(durations != null && durations.size() > 0){
			Long totalDuration = 0L;//课程的时长总和
			for (int i = 0; i < durations.size(); i++) {
				if(durations.get(i) != null){
					totalDuration += durations.get(i);
				}
			}
			//如果有课程时长
			if(totalDuration > 0){
				Long hourNum = totalDuration/3600;
				Long minNum = totalDuration/60 - hourNum*60;
				Long secNUm = totalDuration - minNum*60 - hourNum*3600;
				//小时
				if(hourNum < 10){
					totalDurationArr[0] = "0" + hourNum;
				}else{
					totalDurationArr[0] = "" + hourNum;
				}
				//分钟
				if(minNum < 10){
					totalDurationArr[1] = "0" + minNum;
				}else{
					totalDurationArr[1] = "" + minNum;
				}
				//秒
				if(secNUm < 10){
					totalDurationArr[2] = "0" + secNUm;
				}else{
					totalDurationArr[2] = "" + secNUm;
				}
			}else{
				totalDurationArr[0] = "00";
				totalDurationArr[1] = "00";
				totalDurationArr[2] = "00";
			}
		}else{
			totalDurationArr[0] = "00";
			totalDurationArr[1] = "00";
			totalDurationArr[2] = "00";
		}
		return totalDurationArr;
	}
	
	/**
	 * Method name: getDurationStr <BR>
	 * Description: 将时分秒数组转换为字符串 <BR>
	 * Remark: <BR>
	 * @param durationArr
	 * @return  String<BR>
	 */
	public static String getDurationStrFromArr(String[] durationArr){
		String durationStr = "";
		if(durationArr != null && durationArr.length > 0){
			StringBuffer buffer = new StringBuffer();
			buffer.append(durationArr[0]).append("小时").append(durationArr[1]).append("分").append(durationArr[2]).append("秒");
			durationStr = buffer.toString();
		}else{
			durationStr = "00小时00分00秒";
		}
		return durationStr;
	}
	
	/**
	 * Method name: getDurationStr <BR>
	 * Description: 将课程时长列表转换为字符串 <BR>
	 * Remark: <BR>
	 * @param durations
	 * @return  String<BR>
	 */
	public static String getDurationStr(List<Integer> durations){
		String durationStr = "";
		if(durations != null && durations.size() > 0){
			String[] durationArr = getDurationStrArr(durations);
			durationStr = getDurationStrFromArr(durationArr);
		}else{
			durationStr = "00小时00分00秒";
		}
		return durationStr;
	}
	
	/**
	 * Method name: getDurationStrOfLong <BR>
	 * Description: 将课程时长列表转换为字符串（Long型）<BR>
	 * Remark: <BR>
	 * @param durations
	 * @return  String<BR>
	 */
	public static String getDurationStrOfLong(List<Long> durations){
		String durationStr = "";
		if(durations != null && durations.size() > 0){
			String[] durationArr = getDurationStrArrOfLong(durations);
			durationStr = getDurationStrFromArr(durationArr);
		}else{
			durationStr = "00小时00分00秒";
		}
		return durationStr;
	}
}
