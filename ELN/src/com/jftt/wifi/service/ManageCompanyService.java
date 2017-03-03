/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageCompanyService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月24日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;

/**
 * class name:ManageCompanyService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月24日
 * @author JFTT)Lu Yunlong
 */
public interface ManageCompanyService {
	
	
	
	
	/**
	 * Method name: selectCompanyCount <BR>
	 * Description: 根据条件查询数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectCompanyCount(ManageCompanyVo vo) throws Exception;
	 /**
	 * Method name: selectCompanyList <BR>
	 * Description: 根据条件查询租户公司 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<ManageCompanyBean><BR>
	 */
	List<ManageCompanyBean> selectCompanyList(ManageCompanyVo vo) throws Exception;
	 
	 /**
	 * Method name: selectCompanyById <BR>
	 * Description: 根据Id查询租户公司 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  ManageCompanyBean<BR>
	 */
	ManageCompanyBean selectCompanyById(Integer id) throws Exception;
	
	
	/**
	 * Method name: deleteCompany <BR>
	 * Description: 删除企业 <BR>
	 * Remark: <BR>
	 * @param parseInt
	 * @throws Exception  void<BR>
	 */
	void deleteCompany(Integer parseInt) throws Exception;
	
	/**
	 * Method name: insertCompany <BR>
	 * Description: 注册企业 <BR>
	 * Remark: <BR>
	 * @param company
	 * @throws Exception  void<BR>
	 */
	void insertCompany(ManageCompanyBean company) throws Exception;
	
	
	/**
	 * Method name: freezeAndUnfreezeManageCompany <BR>
	 * Description: 冻结企业 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @throws Exception  void<BR>
	 */
	void freezeAndUnfreezeManageCompany(Integer id, String status) throws Exception;
	
	/**
	 * Method name: updateCompany <BR>
	 * Description: 修改企业 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	
	void updateCompanyBaseInfo(ManageCompanyBean bean) throws Exception ;
	
	/**
	 * Method name: updateCompanyResInfo <BR>
	 * Description: 修改企业权限信息 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void updateCompanyResInfo(ManageCompanyBean bean, String[] pageIds) throws Exception ;
	
	/**
	 * Method name: insertUserByCompany <BR>
	 * Description: 根据新增企业新建用户 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	void insertUserByCompany(ManageCompanyBean bean, String[] pageIds) throws Exception ;
	
	
	/**
	 * Method name: insertCompanyActiveCode <BR>
	 * Description: 插入邮件激活码 <BR>
	 * Remark: <BR>
	 * @param email  void<BR>
	 * @param phone 
	 */
	void insertCompanyActiveCode(String email, String phone) throws Exception ;
	
	/**
	 * Method name: checkActiveCode <BR>
	 * Description: 验证邮件激活码 <BR>
	 * Remark: <BR>
	 * @param company
	 * @return
	 * @throws Exception  int<BR>
	 */
	int checkActiveCode(String key, String activeCode) throws Exception ;
	/**
	 * Method name: selectCity <BR>
	 * Description: 获取城市 <BR>
	 * Remark: <BR>
	 * @param map
	 * @return
	 * @throws Exception  Object<BR>
	 */
	List<Map> selectCity(Map<String, Object> map) throws Exception ;
	
	/**
	 * Method name: selectManageCompanyCapacityList <BR>
	 * Description: 租户企业容量列表 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ManageCompanyBean><BR>
	 */
	List<ManageCompanyBean> selectManageCompanyCapacityList(ManageCompanyVo vo);
	
	/**
	 * Method name: addCapacity <BR>
	 * Description: 为企业扩容 <BR>
	 * Remark: <BR>
	 * @param param  void<BR>
	 */
	void addCapacity(Map<String, Object> param);
	
	/**
	 * Method name: exportExcel <BR>
	 * Description: 导出企业容量excel <BR>
	 * Remark: <BR>
	 * @param result
	 * @return  HSSFWorkbook<BR>
	 */
	HSSFWorkbook exportExcel(List<ManageCompanyBean> result);
	
	/**
	 * Method name: selectTotalCapacity <BR>
	 * Description: 查询所有公司文件占用的空间 <BR>
	 * Remark: <BR>
	 * @return  double<BR>
	 */
	double selectTotalCapacity();
	
    //zhangbocheng mobile start
	/**
	 * Method name: getCompanyIdByName <BR>
	 * Description: 根据公司名获得id <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  Object<BR>
	 */
	Integer getCompanyIdByName(String name) throws Exception ;
	
	
	
	
}
