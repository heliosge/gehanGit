/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageGroupService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月5日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageGroupCondition;
import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.bean.vo.ManageGroupVo;

/**
 * class name:ManageGroupService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月5日
 * @author JFTT)Lu Yunlong
 */
public interface ManageGroupService {

	/**
	 * Method name: selectGroupCount <BR>
	 * Description: 群组数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectGroupCount(ManageGroupBeanVo vo)throws Exception;

	/**
	 * Method name: selectGroupList <BR>
	 * Description: 群组列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ManageCompanyBean><BR>
	 */
	List<ManageGroupBean> selectGroupList(ManageGroupBeanVo vo)throws Exception;

	/**
	 * Method name: insertGroup <BR>
	 * Description: 新增群组 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  Integer<BR>
	 */
	Integer insertGroup(ManageGroupBean bean)throws Exception;

	/**
	 * Method name: insertGroupAndStudent <BR>
	 * Description: 新增群组、学员关系 <BR>
	 * Remark: <BR>
	 * @param mgsBean
	 * @throws Exception  void<BR>
	 */
	void insertGroupAndStudent(ManageGroupStudentBean mgsBean) throws Exception;

	/**
	 * Method name: selectGroupById <BR>
	 * Description: 根据id查询群组 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  ManageGroupBean<BR>
	 */
	ManageGroupBean selectGroupById(Integer parseInt) throws Exception;

	/**
	 * Method name: selectGroupStudentCountByGroupId <BR>
	 * Description: 群组、学员数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  int<BR>
	 */
	int selectGroupStudentCountByGroupId(ManageGroupBeanVo vo) throws Exception;

	/**
	 * Method name: selectGroupStudentListByGroupId <BR>
	 * Description: 群组、学员列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<ManageGroupBean><BR>
	 */
	List<ManageGroupStudentBean> selectGroupStudentListByGroupId(ManageGroupBeanVo vo) throws Exception;
	
	/**
	 * Method name: selectGroupStudentListByGroupId <BR>
	 * Description: 群组、学员id列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return
	 * @throws Exception  List<ManageGroupBean><BR>
	 */
	List<String> selectGroupStudentIdListByGroupId(Integer vo) throws Exception;

	/**
	 * Method name: deleteGroupStudentByStudentId <BR>
	 * Description: 删除群组学员 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	void deleteGroupStudent(ManageGroupStudentBean bean) throws Exception;

	/**
	 * Method name: deleteGroup <BR>
	 * Description: 删除群组 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteGroup(Integer id) throws Exception;

	/**
	 * Method name: checkGroup <BR>
	 * Description: 验证群组人员 <BR>
	 * Remark: <BR>
	 * @param mgsBean
	 * @throws Exception  void<BR>
	 */
	void checkGroup(ManageGroupStudentBean mgsBean) throws Exception;

	/**
	 * Method name: updateGroup <BR>
	 * Description: 修改群组 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void updateGroup(ManageGroupBean bean) throws Exception;

	/**
	 * Method name: selectGroupCondition <BR>
	 * Description: 获取满足条件群组的条件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param groupId
	 * @return  Object<BR>
	 */
	List<ManageGroupCondition> selectGroupCondition(long parseLong);

	/**
	 * Method name: deleteGroupCondition <BR>
	 * Description: 删除群组条件 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteGroupCondition(Integer id);

	/**
	 * Method name: insertGroupCondition <BR>
	 * Description: 新增群组条件 <BR>
	 * Remark: <BR>
	 * @param condition  void<BR>
	 */
	void insertGroupCondition(ManageGroupCondition condition);


}
