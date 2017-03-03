/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionReplyTextDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年9月8日        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseQuestionReplyTextBean;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseQuestionReplyTextDaoMapper <BR>
 * class description: 回复富文本dao <BR>
 * Remark: <BR>
 * @version 1.00 2015年9月8日
 * @author JFTT)ShenHaijun
 */
public interface CourseQuestionReplyTextDaoMapper {
	
	/**
	 * Method name: getIdByReplyId <BR>
	 * Description: 根据回复id查询id <BR>
	 * Remark: <BR>
	 * @param replyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getIdByReplyId(@Param("replyId")Integer replyId) throws DataBaseException;
	
	/**
	 * Method name: updateReplyContent <BR>
	 * Description: 更新一个回复富文本 <BR>
	 * Remark: <BR>
	 * @param replyTextBean
	 * @throws DataBaseException  void<BR>
	 */
	public void updateReplyContent(CourseQuestionReplyTextBean replyTextBean) throws DataBaseException;
}
