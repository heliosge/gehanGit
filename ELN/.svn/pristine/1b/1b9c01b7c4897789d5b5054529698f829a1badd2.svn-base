/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ResCourseCategoryDaoMapper.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-21        | JFTT)shenhaijun    | original version
 */
package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ResCourseCategoryBean;
import com.jftt.wifi.bean.ResCourseTypeBean;
import com.jftt.wifi.bean.vo.CourseNextTypeVo;
import com.jftt.wifi.bean.vo.CourseTypeVo;
import com.jftt.wifi.exception.database.DataBaseException;

/**
 * class name:ResCourseCategoryDaoMapper <BR>
 * class description: 课程体系dao <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-21
 * @author JFTT)ShenHaijun
 */
public interface ResCourseCategoryDaoMapper {
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取课程体系 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  ResCourseCategoryBean<BR>
	 */
	public ResCourseCategoryBean getById(@Param("id")Integer id) throws DataBaseException;
	
	/**shenhaijun start*/
	
	/**
	 * Method name: getCourseCategorysByCompanyId <BR>
	 * Description: 根据公司id查询课程体系 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<ResCourseCategoryVo><BR>
	 */
	public List<ResCourseCategoryBean> getCourseCategorysByCompanyId(
			@Param("companyId")Integer companyId,@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取课程体系的子课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws DataBaseException  List<ResCourseCategoryBean><BR>
	 */
	public List<ResCourseCategoryBean> getChildCategorys(@Param("categoryId")Integer categoryId) throws DataBaseException;
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有课程体系 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws DataBaseException  List<ResCourseCategoryBean><BR>
	 */
	public List<ResCourseCategoryBean> getUpCategorys(@Param("categoryId")Integer categoryId) throws DataBaseException;
	
	/**
	 * Method name: getCategoryByCourseId <BR>
	 * Description: 根据课程id查询课程体系 <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws DataBaseException  ResCourseCategoryBean<BR>
	 */
	public ResCourseCategoryBean getCategoryByCourseId(@Param("courseId")Integer courseId) throws DataBaseException;
	
	/**
	 * Method name: getPulianCategorys <BR>
	 * Description: 获取普联的课程体系 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return
	 * @throws DataBaseException  List<ResCourseCategoryBean><BR>
	 */
	public List<ResCourseCategoryBean> getPulianCategorys(@Param("companyId")Integer companyId) throws DataBaseException;
	
	/**
	 * Method name: getFirstTypes <BR>
	 * Description: 获取一级课程体系和分类 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws DataBaseException  List<CourseTypeVo><BR>
	 */
	public List<CourseTypeVo> getFirstTypes(@Param("companyId")Integer companyId,
			@Param("subCompanyId")Integer subCompanyId) throws DataBaseException;
	
	/**
	 * Method name: getNextCategorys <BR>
	 * Description: 获取二级课程体系和三级课程体系列表  <BR>
	 * Remark: <BR>
	 * @param typeId
	 * @param categoryOrType
	 * @return
	 * @throws DataBaseException  List<CourseNextTypeVo><BR>
	 */
	public List<CourseNextTypeVo> getNextCategorys(@Param("typeId")Integer typeId) throws DataBaseException;
	
	/**
	 * Method name: getNextTypes <BR>
	 * Description: 获取二级课程分类和三级课程分类列表 <BR>
	 * Remark: <BR>
	 * @param typeId
	 * @return
	 * @throws DataBaseException  List<CourseNextTypeVo><BR>
	 */
	public List<CourseNextTypeVo> getNextTypes(@Param("typeId")Integer typeId) throws DataBaseException;
	
	/**
	 * Method name: getChildTypes <BR>
	 * Description: 获取分类下的所有直接子分类 <BR>
	 * Remark: <BR>
	 * @param typeId
	 * @return
	 * @throws DataBaseException  List<ResCourseTypeBean><BR>
	 */
	public List<ResCourseTypeBean> getChildTypes(@Param("typeId")Integer typeId) throws DataBaseException;
	
	/**shenhaijun end*/
	
}
