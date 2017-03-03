/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageParamService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年7月31日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ExpandParamBean;
import com.jftt.wifi.bean.ManageExpandFieldBean;
import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.bean.ManagePostBean;

/**
 * class name:ManageParamService <BR>
 * class description: 参数配置 <BR>
 * Remark: <BR>
 * @version 1.00 2015年7月31日
 * @author JFTT)Lu Yunlong
 */
public interface ManageParamService {
	
	 /**
     * Method name: delete <BR>
     * Description: 删除【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param id
     * @return  int<BR>
     */
    void deleteManageExpandField(Integer id) throws Exception;

    /**
     * Method name: insert <BR>
     * Description: 添加【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param record
     * @return  int<BR>
     */
    void insertManageExpandField(ManageExpandFieldBean record) throws Exception;

    /**
     * Method name: select <BR>
     * Description: 查询【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param companyId
     * @return  List<ManageExpandFieldBean><BR>
     */
    List<ManageExpandFieldBean> selectManageExpandField(Map<String, Object> param) throws Exception;
    
    /**
     * Method name: selectManageExpandFieldCount <BR>
     * Description: 查询【人员扩展字段】数量 <BR>
     * Remark: <BR>
     * @return
     * @throws Exception  int<BR>
     */
    int selectManageExpandFieldCount() throws Exception;

    /**
     * Method name: update <BR>
     * Description: 修改【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param record
     * @return  int<BR>
     */
    void updateManageExpandField(ManageExpandFieldBean record) throws Exception;
    
    /**
     * Method name: inserManagePost <BR>
     * Description: 新增岗位 <BR>
     * Remark: <BR>
     * @param post
     * @throws Exception  void<BR>
     */
    void inserManagePost(ManagePostBean post)  throws Exception;
    
    /**
     * Method name: deleteManagePost <BR>
     * Description: 删除岗位 <BR>
     * Remark: <BR>
     * @param id
     * @throws Exception  void<BR>
     */
    void deleteManagePost(Integer id) throws Exception;
    
    /**
     * Method name: updateManagePost <BR>
     * Description: 修改岗位 <BR>
     * Remark: <BR>
     * @param post
     * @throws Exception  void<BR>
     */
    void updateManagePost(ManagePostBean post) throws Exception;
    
    /**
     * Method name: selectManagePost <BR>
     * Description: 查询岗位 <BR>
     * Remark: <BR>
     * @return
     * @throws Exception  List<ManagePostBean><BR>
     */
    List<ManagePostBean> selectManagePost(Map<String, Object> param) throws Exception;
    
    /**
     * Method name: selectManagePostById <BR>
     * Description: 根据id查询岗位 <BR>
     * Remark: <BR>
     * @return
     * @throws Exception  List<ManagePostBean><BR>
     */
    ManagePostBean selectManagePostById(Integer id) throws Exception;
    
    /**
     * Method name: selectManagePostByParentId <BR>
     * Description: 根据id查询所有子岗位 <BR>
     * Remark: <BR>
     * @param id
     * @return
     * @throws Exception  List<ManagePostBean><BR>
     */
    List<ManagePostBean> selectManagePostByParentId(Integer id) throws Exception;
    
    
    
    
    /**
     * Method name: 查找公司的参数列表
     * Description: queryCompanyParamList <BR>
     * Remark: <BR>
     * @param companyId
     * @return  List<ExpandParamBean><BR>
     */
    public List<ExpandParamBean> queryCompanyParamList(int companyId,String language) throws Exception;
    /**
     * Method name: 查找可用的参数列表
     * Description: queryParamList <BR>
     * Remark: <BR>
     * @param language
     * @return  List<ManageExpandFieldBean><BR>
     */
    public List<ManageExpandFieldBean> queryParamList(String language)throws Exception;
    
    /**
     * Method name: saveUserParam <BR>
     * Description: 保存用户的参数配置 <BR>
     * Remark: <BR>
     * @param paramList  void<BR>
     */
    public void saveUserParam(List<ExpandParamBean> paramList) throws Exception;
    
    
    
}
