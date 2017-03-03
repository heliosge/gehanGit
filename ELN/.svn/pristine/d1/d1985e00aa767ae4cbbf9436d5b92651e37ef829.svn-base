/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseQuestionReplyDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseQuestionReplyBean;
import com.jftt.wifi.bean.CourseQuestionReplyTextBean;
import com.jftt.wifi.bean.CourseQuestionTopicBean;
import com.jftt.wifi.bean.vo.CourseReplyVoForStudy;
import com.jftt.wifi.bean.vo.CourseWareIdTypeVo;
import com.jftt.wifi.bean.vo.MyQuestionVo;
import com.jftt.wifi.bean.vo.QuestionReplyVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseQuestionReplyDaoMapper <BR>
 * class description: 课程问答回答dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public interface CourseQuestionReplyDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课程问答回答 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  CourseQuestionReplyBean<BR>
	 */
	public CourseQuestionReplyBean getById(@Param("id")Integer id) throws DataBaseException;
    /**
     * Method name: queryMyReplyByCondition <BR>
     * Description: 根据查询条件查询回答的问题 <BR>
     * Remark: <BR>
     * @param reply
     * @return  List<CourseQuestionTopicBean><BR>
     */
	public List<CourseQuestionTopicBean> queryMyReplyByCondition(CourseQuestionReplyBean reply)throws DataBaseException;
	/**
     * Method name: addMyReply <BR>
     * Description: 新增我的回复 <BR>
     * Remark: <BR>
     * @param reply
     */
	public void addMyReply(CourseQuestionReplyBean reply)throws DataBaseException;
	/**
	 * Method name: editMyReply <BR>
	 * Description: 编辑回复 <BR>
	 * Remark: <BR>
	 * @param reply
	 * @throws DataBaseException  void<BR>
	 */
	public void editMyReply(CourseQuestionReplyBean reply)throws DataBaseException;
	/**
	 * Method name: editMyTopic <BR>
	 * Description: 编辑提出的问题 <BR>
	 * Remark: <BR>
	 * @param reply
	 * @throws DataBaseException  void<BR>
	 */
	public void editMyTopic(CourseQuestionReplyBean reply)throws DataBaseException;
	/**
	 * Method name: delMyTopic <BR>
	 * Description: 删除Topic <BR>
	 * Remark: <BR>
	 * @param reply
	 * @throws DataBaseException  void<BR>
	 */
	public void delMyTopic(CourseQuestionReplyBean reply)throws DataBaseException;
	/**
	 * Method name: delMyReply <BR>
	 * Description: 删除MyReply <BR>
	 * Remark: <BR>
	 * @param reply
	 * @throws DataBaseException  void<BR>
	 */
	public void delMyReply(CourseQuestionReplyBean reply)throws DataBaseException;
	/**
	 * Method name: countByCondition <BR>
	 * Description: countByCondition <BR>
	 * Remark: <BR>
	 * @param reply
	 * @return
	 * @throws Exception  int<BR>
	 */
	public int countByCondition(CourseQuestionReplyBean reply)throws Exception;
	/**
	 * Method name: queryCountByTopicId <BR>
	 * Description: queryCountByTopicId <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return  int<BR>
	 */
	public int queryCountByTopicId(CourseQuestionReplyBean reply);
	/**
	 * Method name: editReplyContent <BR>
	 * Description: editReplyContent <BR>
	 * Remark: <BR>
	 * @param reply  void<BR>
	 */
	public void editReplyContent(CourseQuestionReplyBean reply);
	/**
	 * Method name: addReplyContent <BR>
	 * Description: addReplyContent <BR>
	 * Remark: <BR>
	 * @param reply  void<BR>
	 */
	public void addReplyContent(CourseQuestionReplyTextBean replyText);
	/**
	 * Method name: delReplyContent <BR>
	 * Description: delReplyContent <BR>
	 * Remark: <BR>
	 * @param reply  void<BR>
	 */
	public void delReplyContent(CourseQuestionReplyBean reply);
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getReplys <BR>
	 * Description: 获取该问题所有回答 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws DataBaseException  List<CourseReplyVoForStudy><BR>
	 */
	public List<CourseReplyVoForStudy> getReplys(@Param("topicId")Integer topicId) throws DataBaseException;
	
	/**
	 * Method name: getReplyQuestionsCount <BR>
	 * Description: 获取回答问题的课程数量 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseName
	 * @param asktimeStartDate
	 * @param asktimeEndDate
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getReplyQuestionsCount(@Param("userId")Integer userId, @Param("courseName")String courseName,
			@Param("asktimeStartDate")Date asktimeStartDate, @Param("asktimeEndDate")Date asktimeEndDate) throws DataBaseException;
	
	/**
	 * Method name: getReplyQuestions <BR>
	 * Description: 获取回答问题的课程 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseName
	 * @param asktimeStartDate
	 * @param asktimeEndDate
	 * @param sortName
	 * @param sortOrder
	 * @param fromNum
	 * @param pageSize
	 * @return  List<MyQuestionVo><BR>
	 */
	public List<MyQuestionVo> getReplyQuestions(@Param("userId")Integer userId, @Param("courseName")String courseName, 
			@Param("asktimeStartDate")Date asktimeStartDate, @Param("asktimeEndDate")Date asktimeEndDate,
			@Param("sortName")String sortName, @Param("sortOrder")String sortOrder, 
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getReplyDoubleQuestions <BR>
	 * Description: 获取这门课程的本学员回答所在的前两个问题  <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  List<String><BR>
	 */
	public List<String> getReplyDoubleQuestions(@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getAskWareIdType <BR>
	 * Description: 获取该课该学员提问的最近问题的courseWareId和courseWareType <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  CourseWareIdTypeVo<BR>
	 */
	public CourseWareIdTypeVo getAskWareIdType(@Param("courseId")Integer courseId, @Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: getReplyWareIdType <BR>
	 * Description: 获取该课该学员回答的最近问题的courseWareId和courseWareType <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws DataBaseException  CourseWareIdTypeVo<BR>
	 */
	public CourseWareIdTypeVo getReplyWareIdType(@Param("courseId")Integer courseId,@Param("userId")Integer userId) throws DataBaseException;
	
	/**
	 * Method name: updateReply <BR>
	 * Description: 修改该回复的内容 <BR>
	 * Remark: <BR>
	 * @param replyBean
	 * @throws DataBaseException  void<BR>
	 */
	public void updateReply(CourseQuestionReplyBean replyBean) throws DataBaseException;
	
	/**
	 * Method name: getQuestionReplys <BR>
	 * Description: 获取回复的内容 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws DataBaseException  List<QuestionReplyVo><BR>
	 */
	public List<QuestionReplyVo> getQuestionReplys(@Param("topicId")Integer topicId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
