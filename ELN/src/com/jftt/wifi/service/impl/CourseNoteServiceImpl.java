/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseNoteServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.service.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.CourseNoteBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.vo.MyNoteDetailVo;
import com.jftt.wifi.bean.vo.MyNoteVo;
import com.jftt.wifi.dao.CourseNoteDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseNoteService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.Pagination;

/**
 * class name:CourseNoteServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 * @author JFTT)HeTianrui
 */
@Service("courseNoteService")
public class CourseNoteServiceImpl implements CourseNoteService{
   
	@Resource(name="courseNoteDaoMapper")
	private CourseNoteDaoMapper courseNoteDaoMapper;
	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	@Resource(name="resCourseDaoMapper")
	private ResCourseDaoMapper resCourseDaoMapper;
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#addMyNote(com.jftt.wifi.bean.CourseNoteBean) <BR>
	 * Method name: addMyNote <BR>
	 * Description: 添加笔记 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addMyNote(CourseNoteBean note) throws Exception {
		courseNoteDaoMapper.addMyNote(note);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#delMyNote(long) <BR>
	 * Method name: delMyNote <BR>
	 * Description: 删除笔记 <BR>
	 * Remark: <BR>
	 * @param id 笔记id
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delMyNote(long id) throws Exception {
		courseNoteDaoMapper.delMyNote(id);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#editMyNote(com.jftt.wifi.bean.CourseNoteBean) <BR>
	 * Method name: editMyNote <BR>
	 * Description: 修改笔记 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void editMyNote(CourseNoteBean note) throws Exception {
		courseNoteDaoMapper.editMyNote(note);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#queryMyNoteByCondition(com.jftt.wifi.bean.CourseNoteBean) <BR>
	 * Method name: queryMyNoteByCondition <BR>
	 * Description: 查询笔记 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @return Pagination<CourseNoteBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public Pagination<CourseNoteBean> queryMyNoteByCondition(CourseNoteBean note)
			throws Exception {
		int total = courseNoteDaoMapper.countByCondition(note);
		int pageStartSize = (note.getPageIndex()-1) * note.getPageSize();
		note.setPageStartSize(pageStartSize);
		List<CourseNoteBean> list =courseNoteDaoMapper.queryMyNoteByCondition(note);
		Pagination<CourseNoteBean>  page = new Pagination<CourseNoteBean>(list, total, note.getPageSize(),note.getPageIndex());
		return page;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#queryMyNoteDetails(com.jftt.wifi.bean.CourseNoteBean) <BR>
	 * Method name: queryMyNoteDetails <BR>
	 * Description: 查询笔记详情 <BR>
	 * Remark: <BR>
	 * @param note 笔记Bean
	 * @return Pagination<CourseNoteBean>
	 * @throws Exception  <BR>
	 */
	@Override
	public Pagination<CourseNoteBean> queryMyNoteDetails(CourseNoteBean note)
			throws Exception {
		int total = courseNoteDaoMapper.countFromNoteDetails(note);
		int pageStartSize = (note.getPageIndex()-1) * note.getPageSize();
		note.setPageStartSize(pageStartSize);
		List<CourseNoteBean> list =courseNoteDaoMapper.queryMyNoteDetails(note);
		Pagination<CourseNoteBean>  page = new Pagination<CourseNoteBean>(list, total, note.getPageSize(),note.getPageIndex());
		return page;
	}
	
	/**shenhaijun start*/
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#getMyNotesCount(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date) <BR>
	 * Method name: getMyNotesCount <BR>
	 * Description: 获取我的笔记的数量 <BR>
	 * Remark: <BR>
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param courseName 课程名称
	 * @param createTimeStart 查询开始时间
	 * @param createTimeEnd 查询结束时间
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getMyNotesCount(Integer userId, Integer companyId,
			Integer subCompanyId, String courseName, Date createTimeStart,
			Date createTimeEnd) throws DataBaseException {
		return courseNoteDaoMapper.getMyNotesCount(
				userId,companyId,subCompanyId,courseName,createTimeStart,createTimeEnd);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#getMyNotes(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) <BR>
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
	 * @return List<MyNoteVo>
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public List<MyNoteVo> getMyNotes(Integer userId, Integer companyId,
			Integer subCompanyId, String courseName, Date createTimeStart,Date createTimeEnd, 
			String sortName,String sortOrder,Integer fromNum, Integer pageSize)
			throws DataBaseException {
		return courseNoteDaoMapper.getMyNotes(
				userId,companyId,subCompanyId,courseName,createTimeStart,createTimeEnd,
				sortName,sortOrder,fromNum,pageSize);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#getNotesDetailCount(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getNotesDetailCount <BR>
	 * Description: 获取笔记详情数量 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @return Integer
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public Integer getNotesDetailCount(Integer courseId, Integer userId,
			Integer companyId, Integer subCompanyId) throws DataBaseException {
		return courseNoteDaoMapper.getNotesDetailCount(courseId,userId,companyId,subCompanyId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#getNotesDetail(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer) <BR>
	 * Method name: getNotesDetail <BR>
	 * Description: 获取笔记详情 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @param companyId 公司id
	 * @param subCompanyId 子公司id
	 * @param fromNum 起始条数
	 * @param pageSize 每页大小
	 * @return List<MyNoteDetailVo>
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MyNoteDetailVo> getNotesDetail(Integer courseId,
			Integer userId, Integer companyId, Integer subCompanyId,
			Integer fromNum, Integer pageSize) throws Exception {
		List<MyNoteDetailVo> noteDetails = courseNoteDaoMapper.getNotesDetail(
				courseId,userId,companyId,subCompanyId,fromNum,pageSize);
		//将用户姓名和用户头像放入到笔记详情中
		if(noteDetails != null && noteDetails.size() > 0){
			String userName = "";
			String userPhoto = "";
			for (int i = 0; i < noteDetails.size(); i++) {
				userName = manageUserService.findUserById(String.valueOf(noteDetails.get(i).getUserId())).getName();
				noteDetails.get(i).setUserName(userName);
				userPhoto = manageUserService.findUserById(String.valueOf(noteDetails.get(i).getUserId())).getHeadPhoto();
				noteDetails.get(i).setUserPhoto(userPhoto);
			}
		}
		return noteDetails;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#getCourseDetail(java.lang.Integer) <BR>
	 * Method name: 获取课程详情 <BR>
	 * Remark: <BR>
	 * @param courseId 课程id
	 * @return ResCourseBean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public ResCourseBean getCourseDetail(Integer courseId)
			throws DataBaseException {
		return resCourseDaoMapper.getById(courseId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#deleteNote(java.lang.Integer) <BR>
	 * Method name: deleteNote <BR>
	 * Description: 删除一条笔记 <BR>
	 * Remark: <BR>
	 * @param noteId 笔记id
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteNote(Integer noteId) throws DataBaseException {
		courseNoteDaoMapper.deleteNote(noteId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#getEditDialog(java.lang.Integer) <BR>
	 * Method name: getEditDialog <BR>
	 * Description: 根据id获取笔记详情 <BR>
	 * Remark: <BR>
	 * @param noteId
	 * @return CourseNoteBean
	 * @throws DataBaseException  <BR>
	 */
	@Override
	public CourseNoteBean getEditDialog(Integer noteId) throws DataBaseException {
		return courseNoteDaoMapper.getById(noteId);
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.CourseNoteService#updateNote(java.lang.Integer, java.lang.String) <BR>
	 * Method name: updateNote <BR>
	 * Description: 修改一条笔记 <BR>
	 * Remark: <BR>
	 * @param noteId 笔记id
	 * @param content 内容
	 * @throws DataBaseException  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateNote(Integer noteId, String content) throws DataBaseException {
		CourseNoteBean noteBean = new CourseNoteBean();
		noteBean.setId(noteId);
		noteBean.setContent(content);
		courseNoteDaoMapper.editMyNote(noteBean);
	}
	
	/**shenhaijun end*/
	
}
