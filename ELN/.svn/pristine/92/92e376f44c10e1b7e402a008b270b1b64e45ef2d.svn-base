/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageParamServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月31日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jftt.wifi.bean.ExpandParamBean;
import com.jftt.wifi.bean.ManageExpandFieldBean;
import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.bean.ManagePostBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.ManageExpandFieldDaoMapper;
import com.jftt.wifi.dao.ManageIndustryCategoryDaoMapper;
import com.jftt.wifi.dao.ManagePostDaoMapper;
import com.jftt.wifi.service.ManageParamService;

/**
 * class name:ManageParamServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月31日
 * @author JFTT)Lu Yunlong
 */
@Service(value="manageParamService")
public class ManageParamServiceImpl implements ManageParamService {
	
	@Resource(name="manageExpandFieldDaoMapper")
	private ManageExpandFieldDaoMapper manageExpandFieldDaoMapper; 
	
	@Resource(name="manageIndustryCategoryDaoMapper")
	private ManageIndustryCategoryDaoMapper manageIndustryCategoryDaoMapper; 
	
	@Resource(name="managePostDaoMapper")
	private ManagePostDaoMapper managePostDaoMapper; 
	

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#delete(java.lang.Integer) <BR>
	 * Method name: 删除【人员扩展字段】  <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteManageExpandField(Integer id) throws Exception {
		manageExpandFieldDaoMapper.delete(id);
		manageExpandFieldDaoMapper.deleteUserConfigPropertu(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#insertManageExpandField(com.jftt.wifi.bean.ManageExpandFieldBean) <BR>
	 * Method name: 添加【人员扩展字段】 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public void insertManageExpandField(ManageExpandFieldBean record) throws Exception {
		List<ManageExpandFieldBean> list = manageExpandFieldDaoMapper.select(new HashMap<String, Object>());
		List<String> expandField = new ArrayList<String>();
		for( int i=1; i <= 100; i++){
			expandField.add(i+"");
		}
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getOriginalFiled() != Integer.parseInt(expandField.get(i))){
					record.setOriginalFiled(Integer.parseInt(expandField.get(i)));
					manageExpandFieldDaoMapper.insert(record);
					break;
				}else{
					if(i == list.size() - 1){
						record.setOriginalFiled(Integer.parseInt(expandField.get(i+1)));
						manageExpandFieldDaoMapper.insert(record);
					}	
				}
			}
		}else{
			record.setOriginalFiled(1);
			manageExpandFieldDaoMapper.insert(record);
		}
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#selectManageExpandField(java.lang.Integer) <BR>
	 * Method name: 查询【人员扩展字段】 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManageExpandFieldBean> selectManageExpandField(Map<String, Object> param)
			throws Exception {
		return manageExpandFieldDaoMapper.select(param);
	}
	
	@Override
	public int selectManageExpandFieldCount()
			throws Exception {
		return manageExpandFieldDaoMapper.selectManageExpandFieldCount();
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#updateManageExpandField(com.jftt.wifi.bean.ManageExpandFieldBean) <BR>
	 * Method name: 修改【人员扩展字段】 <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param record
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateManageExpandField(ManageExpandFieldBean record) throws Exception {
		manageExpandFieldDaoMapper.update(record);
	}

	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#inserManagePost(com.jftt.wifi.bean.ManagePostBean) <BR>
	 * Method name: inserManagePost <BR>
	 * Description: 新增岗位 <BR>
	 * Remark: <BR>
	 * @param post
	 * @throws Exception  <BR>
	*/
	@Override
	public void inserManagePost(ManagePostBean post) throws Exception {
		managePostDaoMapper.insertManagePost(post);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#deleteManagePost(java.lang.Integer) <BR>
	 * Method name: deleteManagePost <BR>
	 * Description: 删除岗位 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  <BR>
	*/
	@Override
	public void deleteManagePost(Integer id) throws Exception {
		managePostDaoMapper.deleteManagePost(id);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#updateManagePost(com.jftt.wifi.bean.ManagePostBean) <BR>
	 * Method name: updateManagePost <BR>
	 * Description: 修改岗位 <BR>
	 * Remark: <BR>
	 * @param post
	 * @throws Exception  <BR>
	*/
	@Override
	public void updateManagePost(ManagePostBean post) throws Exception {
		managePostDaoMapper.updateManagePost(post);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#selectManagePost(java.util.Map) <BR>
	 * Method name: selectManagePost <BR>
	 * Description: 获取岗位 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManagePostBean> selectManagePost(Map<String, Object> param)
			throws Exception {
		return managePostDaoMapper.selectManagePost(param);
	}

	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#selectManagePostById(java.lang.Integer) <BR>
	 * Method name: selectManagePostById <BR>
	 * Description: 根据id获取岗位 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public ManagePostBean selectManagePostById(Integer id) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		List<ManagePostBean> list = selectManagePost(param);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * @Override
	 * @see com.jftt.wifi.service.ManageParamService#selectManagePostByParentId(java.lang.Integer) <BR>
	 * Method name: selectManagePostByParentId <BR>
	 * Description: 获取所有子岗位 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	*/
	@Override
	public List<ManagePostBean> selectManagePostByParentId(Integer id) throws Exception {
		List<ManagePostBean> result = new ArrayList<ManagePostBean>();
//		List<ManagePostBean> result
//		for()
		
		return null;
	}

	
	/*liucaibao begin*/
	
    /**
     * @Override
     * @see com.jftt.wifi.service.ManageParamService#queryCompanyParamList(int) <BR>
     * Method name: 查找公司的参数列表<BR>
     * Description: please write your description <BR>
     * Remark: <BR>
     * @param companyId
     * @return  <BR>
    */
    public List<ExpandParamBean> queryCompanyParamList(int companyId,String language) throws Exception{
    	
    	
    	return manageExpandFieldDaoMapper.queryCompanyParamList(companyId,language);
    }
    /**
     * @Override
     * @see com.jftt.wifi.service.ManageParamService#queryParamList(java.lang.String) <BR>
     * Method name: 查找可用的参数列表 <BR>
     * Description: please write your description <BR>
     * Remark: <BR>
     * @param language
     * @return  <BR>
    */
    public List<ManageExpandFieldBean> queryParamList(String language)throws Exception{
    	
    	return manageExpandFieldDaoMapper.queryParamList(language);
    }
    
    /**
     * @Override
     * @see com.jftt.wifi.service.ManageParamService#saveUserParam(java.util.List, int) <BR>
     * Method name: saveUserParam <BR>
     * Description: 保存用户参数信息 <BR>
     * Remark: <BR>
     * @param paramList
     * @param companyId
     * @throws Exception  <BR>
    */
	@Transactional(rollbackFor=Exception.class)
    public void saveUserParam(List<ExpandParamBean> paramList) throws Exception{
    	
    	//删除当前所有的必填或者非必填字段
		manageExpandFieldDaoMapper.deleteUserConfig(paramList.get(0));
    	for(ExpandParamBean xpBean:paramList){
    		if(xpBean.getPropertyId()!=0){
    			//删除此公司配置的参数信息
    			manageExpandFieldDaoMapper.deleteUserProperty(xpBean);
    			manageExpandFieldDaoMapper.insertUserConfig(xpBean);
    		}
    	}
    }
	
	/*liucaibao end*/

}
