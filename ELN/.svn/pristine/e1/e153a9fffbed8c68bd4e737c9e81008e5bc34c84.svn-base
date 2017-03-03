/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageIndustryCategoryServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月11日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.dao.ManageIndustryCategoryDaoMapper;
import com.jftt.wifi.service.ManageIndustryCategoryService;

/**
 * class name:ManageIndustryCategoryServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月11日
 * @author JFTT)Lu Yunlong
 */
@Service("manageIndustryCategoryService")
public class ManageIndustryCategoryServiceImpl implements
		ManageIndustryCategoryService {
	
	@Autowired
	ManageIndustryCategoryDaoMapper manageIndustryCategoryDaoMapper;

	/**
	 * @Override
	 * @see com.jftt.wifi.action.ManageIndustryCategoryService#delete(java.lang.Integer) <BR>
	 * Method name: delete <BR>
	 * Description: 删除 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void delete(Integer id) throws Exception {
		manageIndustryCategoryDaoMapper.delete(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.action.ManageIndustryCategoryService#insert(com.jftt.wifi.bean.ManageIndustryCategoryBean) <BR>
	 * Method name: insert <BR>
	 * Description: 新增【企业分类】 <BR>
	 * Remark: <BR>
	 * @param record
	 * @throws Exception  <BR>
	*/
	@Override
	public void insert(ManageIndustryCategoryBean record) throws Exception {
		manageIndustryCategoryDaoMapper.insert(record);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.action.ManageIndustryCategoryService#select(java.util.Map) <BR>
	 * Method name: select <BR>
	 * Description: 查询【企业分类】 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManageIndustryCategoryBean> select(Map<String, Object> param)
			throws Exception {
		return manageIndustryCategoryDaoMapper.select(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.action.ManageIndustryCategoryService#update(com.jftt.wifi.bean.ManageIndustryCategoryBean) <BR>
	 * Method name: update <BR>
	 * Description: 修改【企业分类】 <BR>
	 * Remark: <BR>
	 * @param record
	 * @throws Exception  <BR>
	*/
	@Override
	public void update(ManageIndustryCategoryBean record) throws Exception {
		manageIndustryCategoryDaoMapper.update(record);
	}

	@Override
	public ManageIndustryCategoryBean selectByName(String name)
			throws Exception {
		return manageIndustryCategoryDaoMapper.selectByName(name);
	}

}
