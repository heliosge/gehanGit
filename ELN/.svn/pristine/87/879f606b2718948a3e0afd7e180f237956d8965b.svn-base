/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseNoteDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-15        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.vo.CourseNoteVoForStudy;
import com.jftt.wifi.bean.vo.MyNoteDetailVo;
import com.jftt.wifi.bean.vo.MyNoteVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:CourseNoteDaoMapper <BR>
 * class description: 课程笔记dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-15
 * @author JFTT)ShenHaijun
 */
public interface CourseNoteDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查询课程笔记 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  CourseNoteBean<BR>
	 */
	public CourseNoteBean getById(@Param("id")Integer id) throws DataBaseException;
    /**
     * Method name: queryMyNoteByCondition <BR>
     * Description: 根据条件查询我的笔记 <BR>
     * Remark: <BR>
     * @param note
     * @return
     * @throws DataBaseException  List<CourseNoteBean><BR>
     */
	public List<CourseNoteBean> queryMyNoteByCondition(CourseNoteBean note)throws DataBaseException;
	/**
	 * Method name: addMyNote <BR>
	 * Description: addMyNote <BR>
	 * Remark: <BR>
	 * @param note
	 * @throws DataBaseException  void<BR>
	 */
	public void addMyNote(CourseNoteBean note)throws DataBaseException;
	/**
	 * Method name: delMyNote <BR>
	 * Description: delMyNote <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws DataBaseException  void<BR>
	 */
	public void delMyNote(long id)throws DataBaseException;
	/**
	 * Method name: editMyNote <BR>
	 * Description: editMyNote <BR>
	 * Remark: <BR>
	 * @param note
	 * @throws DataBaseException  void<BR>
	 */
	public void editMyNote(CourseNoteBean note)throws DataBaseException;
	/**
	 * Method name: queryMyNoteDetails <BR>
	 * Description: queryMyNoteDetails <BR>
	 * Remark: <BR>
	 * @param note
	 * @return
	 * @throws DataBaseException  List<CourseNoteBean><BR>
	 */
	public List<CourseNoteBean> queryMyNoteDetails(CourseNoteBean note)throws DataBaseException;
	/**
	 * Method name: countByCondition <BR>
	 * Description: countByCondition <BR>
	 * Remark: <BR>
	 * @param note
	 * @return
	 * @throws DataBaseException  int<BR>
	 */
	public int countByCondition(CourseNoteBean note)throws DataBaseException;
	/**
	 * Method name: countFromNoteDetails <BR>
	 * Description: countFromNoteDetails <BR>
	 * Remark: <BR>
	 * @param note
	 * @return
	 * @throws DataBaseException  int<BR>
	 */
	public int countFromNoteDetails(CourseNoteBean note)throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCourseNotesCount <BR>
	 * Description: 获取课程笔记数目 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCourseNotesCount(@Param("courseId")Integer courseId, @Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getCourseNotes <BR>
	 * Description: 获取课程所有笔记 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<CourseNoteVoForStudy><BR>
	 */
	public List<CourseNoteVoForStudy> getCourseNotes(@Param("courseId")Integer courseId,
			@Param("companyId")Integer companyId, @Param("subCompanyId")Integer subCompanyId, 
			@Param("fromNum")Integer fromNum,@Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getMyNotesCount <BR>
	 * Description: 获取我的笔记的数量 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param companyId
	 * @param subCompanyId
	 * @param courseName
	 * @param createTimeStart
	 * @param createTimeEnd
	 * @return  Integer<BR>
	 */
	public Integer getMyNotesCount(@Param("userId")Integer userId, @Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId, @Param("courseName")String courseName, @Param("createTimeStart")Date createTimeStart,
			@Param("createTimeEnd")Date createTimeEnd) throws DataBaseException;
	
	/**
	 * Method name: getMyNotes <BR>
	 * Description: 获取我的笔记 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param companyId
	 * @param subCompanyId
	 * @param courseName
	 * @param createTimeStart
	 * @param createTimeEnd
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<MyNoteVo><BR>
	 */
	public List<MyNoteVo> getMyNotes(@Param("userId")Integer userId, @Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId, @Param("courseName")String courseName, 
			@Param("createTimeStart")Date createTimeStart,@Param("createTimeEnd")Date createTimeEnd, 
			@Param("sortName")String sortName,@Param("sortOrder")String sortOrder,
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getNotesDetailCount <BR>
	 * Description: 获取笔记详情数量 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getNotesDetailCount(@Param("courseId")Integer courseId, @Param("userId")Integer userId,
			@Param("companyId")Integer companyId, @Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getNotesDetail <BR>
	 * Description: 获取笔记详情 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @param companyId
	 * @param subCompanyId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<MyNoteDetailVo><BR>
	 */
	public List<MyNoteDetailVo> getNotesDetail(@Param("courseId")Integer courseId,
			@Param("userId")Integer userId, @Param("companyId")Integer companyId, @Param("subCompanyId")Integer subCompanyId,
			@Param("fromNum")Integer fromNum, @Param("pageSize")Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: deleteNote <BR>
	 * Description: 删除一条笔记 <BR>
	 * Remark: <BR>
	 * @param noteId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteNote(@Param("id")Integer id) throws DataBaseException;
	
	/**
	 * Method name: deleteRecord <BR>
	 * Description: 删除学员该课程所有笔记 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @throws DataBaseException  void<BR>
	 */
	public void deleteRecord(@Param("courseId")String courseId, @Param("userId")String userId) throws DataBaseException;
	
	/**
	 * Method name: getCourseNoteCountByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记数量 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	public Integer getCourseNoteCountByUserIdCourseId(@Param("userId")String userId,
			@Param("courseId")String courseId) throws DataBaseException;
	
	/**
	 * Method name: getCourseNotesByUserIdCourseId <BR>
	 * Description: 根据用户id和课程id查询笔记 <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @param fromNum
	 * @param pageSize
	 * @return
	 * @throws DataBaseException  List<CourseNoteBean><BR>
	 */
	public List<CourseNoteBean> getCourseNotesByUserIdCourseId(
			@Param("userId")String userId,@Param("courseId")String courseId, 
			@Param("fromNum")Integer fromNum, @Param("pageSize")String pageSize) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
