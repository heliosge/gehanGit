/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseNoteService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service;

import java.util.Date;
import java.util.List;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.vo.MyNoteDetailVo;
import com.jftt.wifi.bean.vo.MyNoteVo;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseNoteService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)HeTianrui
 */
public interface CourseNoteService {
    /**
     * Method name: delMyNote <BR>
     * Description: 删除我的笔记 <BR>
     * Remark: <BR>
     * @param id 笔记id
     * @throws Exception  void<BR>
     */
	void delMyNote(long id)throws Exception;
    /**
     * Method name: editMyNote <BR>
     * Description: 修改我的笔记 <BR>
     * Remark: <BR>
     * @param note 笔记Bean
     * @throws Exception  void<BR>
     */
	void editMyNote(CourseNoteBean note)throws Exception;
    /**
     * Method name: addMyNote <BR>
     * Description: 添加我的笔记 <BR>
     * Remark: <BR>
     * @param note 笔记Bean
     * @throws Exception  void<BR>
     */
	void addMyNote(CourseNoteBean note)throws Exception;
	/**
	 * Method name: queryMyNoteByCondition <BR>
	 * Description: 根据条件查询我的笔记 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @return
	 * @throws Exception  Pagination<CourseNoteBean><BR>
	 */
	Pagination<CourseNoteBean> queryMyNoteByCondition(CourseNoteBean note)throws Exception;
	/**
	 * Method name: queryMyNoteDetails <BR>
	 * Description: 查询笔记详情 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @return
	 * @throws Exception  Pagination<CourseNoteBean><BR>
	 */
	Pagination<CourseNoteBean> queryMyNoteDetails(CourseNoteBean note)throws Exception;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getMyNotesCount <BR>
	 * Description: 获取我的笔记的数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param courseName 课程名称
	 * @param createTimeStart 查询开始时间
	 * @param createTimeEnd 查询结束时间
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	Integer getMyNotesCount(Integer userId, Integer companyId,
			Integer subCompanyId, String courseName, Date createTimeStart,
			Date createTimeEnd) throws DataBaseException;
	
	/**
	 * Method name: getMyNotes <BR>
	 * Description: 获取我的笔记 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param courseName 课程名称
	 * @param createTimeStart 查询开始时间
	 * @param createTimeEnd 查询结束时间
	 * @param sortName 按什么排序
	 * @param sortOrder 升序降序
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return
	 * @throws DataBaseException  List<MyNoteVo><BR>
	 */
	List<MyNoteVo> getMyNotes(Integer userId, Integer companyId,
			Integer subCompanyId, String courseName, Date createTimeStart,Date createTimeEnd, 
			String sortName,String sortOrder,Integer fromNum, Integer pageSize) throws DataBaseException;
	
	/**
	 * Method name: getNotesDetailCount <BR>
	 * Description: 获取笔记详情数量 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return
	 * @throws DataBaseException  Integer<BR>
	 */
	Integer getNotesDetailCount(Integer courseId, Integer userId,
			Integer companyId, Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getNotesDetail <BR>
	 * Description: 获取笔记详情 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return
	 * @throws Exception  List<MyNoteDetailVo><BR>
	 */
	List<MyNoteDetailVo> getNotesDetail(Integer courseId, Integer userId,
			Integer companyId, Integer subCompanyId, Integer fromNum,
			Integer pageSize) throws Exception;
	
	/**
	 * Method name: getCourseDetail <BR>
	 * Description: 获取课程详情 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return
	 * @throws DataBaseException  ResCourseBean<BR>
	 */
	ResCourseBean getCourseDetail(Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: deleteNote <BR>
	 * Description: 删除一条笔记 <BR>
	 * Remark: <BR>
	 * @param noteId 笔记id
	 * @throws DataBaseException  void<BR>
	 */
	void deleteNote(Integer noteId) throws DataBaseException;
	
	/**
	 * Method name: getEditDialog <BR>
	 * Description: 根据id获取笔记详情 <BR>
	 * Remark: <BR>
	 * @param noteId 笔记id
	 * @return
	 * @throws DataBaseException  CourseNoteBean<BR>
	 */
	CourseNoteBean getEditDialog(Integer noteId) throws DataBaseException;
	
	/**
	 * Method name: updateNote <BR>
	 * Description: 修改一条笔记 <BR>
	 * Remark: <BR>
	 * @param noteId 笔记id
	 * @param content 内容
	 * @throws DataBaseException  void<BR>
	 */
	void updateNote(Integer noteId,String content) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
