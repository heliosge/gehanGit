/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: InstationMessageConstant.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-9-9        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JFTT)chenrui
 * class name:InstationMessageConstant <BR>
 * class description: 站内信常量 <BR>
 * Remark: <BR>
 * @version 1.00 2015-9-9
 * @author JFTT)chenrui
 */
public class InstationMessageConstant {
	
	public static String applyInform="参加报名项目通知";
	
	public static String getApplyContent(String userName,String itemName,Date startTime,Date endTime){
		return "尊敬的"+userName+"：<em>《"+itemName+"》从："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime)+"到："
				+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime)+"内可以进行报名了，希望您能够尽快来报名。</em>";
	}
}
