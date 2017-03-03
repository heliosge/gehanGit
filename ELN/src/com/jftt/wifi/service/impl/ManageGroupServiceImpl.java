/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageGroupServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月6日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ManageGroupBean;
import com.jftt.wifi.bean.ManageGroupCondition;
import com.jftt.wifi.bean.ManageGroupStudentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageGroupBeanVo;
import com.jftt.wifi.dao.ManageGroupDaoMapper;
import com.jftt.wifi.service.ManageGroupService;
import com.jftt.wifi.service.ManageUserService;

/**
 * class name:ManageGroupServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月6日
 * @author JFTT)Lu Yunlong
 */
@Service("manageGroupService")
public class ManageGroupServiceImpl implements ManageGroupService {
	
	@Resource(name="manageGroupDaoMapper")
	private ManageGroupDaoMapper manageGroupDaoMapper;

	@Resource(name="manageUserService")
	private ManageUserService manageUserService;
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#selectGroupCount(com.jftt.wifi.bean.vo.ManageGroupVo) <BR>
	 * Method name: selectGroupCount <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	 */

	@Override
	public int selectGroupCount(ManageGroupBeanVo vo)  throws Exception {
		return manageGroupDaoMapper.selectGroupCount(vo);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#selectGroupList(com.jftt.wifi.bean.vo.ManageGroupVo) <BR>
	 * Method name: selectGroupList <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  <BR>
	 * @throws Exception 
	 */

	@Override
	public List<ManageGroupBean> selectGroupList(ManageGroupBeanVo vo) throws Exception {
		List<ManageGroupBean> list = manageGroupDaoMapper.selectGroupList(vo);
		return list;
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#insertGroup(com.jftt.wifi.bean.ManageGroupBean) <BR>
	 * Method name: insertGroup <BR>
	 * Description: 新增群组 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public Integer insertGroup(ManageGroupBean bean) throws Exception {
		manageGroupDaoMapper.insertGroup(bean);
		return bean.getId();
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#insertGroupAndStudent(com.jftt.wifi.bean.ManageGroupStudentBean) <BR>
	 * Method name: insertGroupAndStudent <BR>
	 * Description: 新增群组、学员的关系 <BR>
	 * Remark: <BR>
	 * @param mgsBean
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertGroupAndStudent(ManageGroupStudentBean mgsBean)
			throws Exception {
		manageGroupDaoMapper.insertGroupAndStudent(mgsBean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#selectGroupById(java.lang.Integer) <BR>
	 * Method name: selectGroupById <BR>
	 * Description: please 根据id查询群组 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @return  <BR>
	 * @throws Exception 
	*/
	@Override
	public ManageGroupBean selectGroupById(Integer id) throws Exception {
		ManageGroupBeanVo vo = new ManageGroupBeanVo();
		vo.setId(id);
		return selectGroupList(vo).get(0);
	}

	@Override
	public int selectGroupStudentCountByGroupId(ManageGroupBeanVo vo)
			throws Exception {
		return manageGroupDaoMapper.selectGroupStudentCountByGroupId(vo);
	}

	@Override
	public List<ManageGroupStudentBean> selectGroupStudentListByGroupId(
			ManageGroupBeanVo vo) throws Exception {
		return manageGroupDaoMapper.selectGroupStudentListByGroupId(vo);
	}
	
	@Override
	public List<String> selectGroupStudentIdListByGroupId(
			Integer id) throws Exception {
		List<String> list = new ArrayList<String>();
		ManageGroupBeanVo vo = new ManageGroupBeanVo();
		vo.setId(id);
		List<ManageGroupStudentBean> studentList = manageGroupDaoMapper.selectGroupStudentListByGroupId(vo);
		for(ManageGroupStudentBean bean : studentList){
			list.add(bean.getStudentId().toString());
		}
		return list;
	}

	@Override
	public void deleteGroupStudent(ManageGroupStudentBean bean)
			throws Exception {
		manageGroupDaoMapper.deleteGroupStudent(bean);		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteGroup(Integer id) throws Exception {
		manageGroupDaoMapper.deleteGroup(id);
		//删除群组人员
		ManageGroupStudentBean bean = new ManageGroupStudentBean();
		bean.setGroupId(id);
		manageGroupDaoMapper.deleteGroupStudent(bean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#checkGroup(com.jftt.wifi.bean.ManageGroupStudentBean) <BR>
	 * Method name: checkGroup <BR>
	 * Description: 验证群组人员 <BR>
	 * Remark: <BR>
	 * @param mgsBean
	 * @throws Exception  <BR>
	*/
	@Override
	public void checkGroup(ManageGroupStudentBean mgsBean) throws Exception {
		manageGroupDaoMapper.checkGroup(mgsBean);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#updateGroup(com.jftt.wifi.bean.ManageGroupBean) <BR>
	 * Method name: updateGroup <BR>
	 * Description: p修改群组 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateGroup(ManageGroupBean bean) throws Exception {
		manageGroupDaoMapper.updateGroup(bean);
	}

	/**
	 * Method name: selectGroupCondition <BR>
	 * Description: 获取满足条件群组的条件 <BR>
	 * Remark: <BR>
	 * @param request
	 * @param groupId
	 * @return  Object<BR>
	 */
	@Override
	public List<ManageGroupCondition> selectGroupCondition(long groupId) {
		return manageGroupDaoMapper.selectGroupCondition(groupId);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#deleteGroupCondition(java.lang.Integer) <BR>
	 * Method name: deleteGroupCondition <BR>
	 * Description: 删除群组条件 <BR>
	 * Remark: <BR>
	 * @param id  <BR>
	*/
	@Override
	public void deleteGroupCondition(Integer id) {
		manageGroupDaoMapper.deleteGroupCondition(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageGroupService#insertGroupCondition(com.jftt.wifi.bean.ManageGroupCondition) <BR>
	 * Method name: insertGroupCondition <BR>
	 * Description: 新增群组条件 <BR>
	 * Remark: <BR>
	 * @param condition  <BR>
	*/
	@Override
	public void insertGroupCondition(ManageGroupCondition condition) {
		manageGroupDaoMapper.insertGroupCondition(condition);		
	}

}
