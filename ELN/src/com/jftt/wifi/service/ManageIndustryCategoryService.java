/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ManageIndustryCategoryService.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年8月11日        | JFTT)luyunlong    | original version
 */
package com.jftt.wifi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageIndustryCategoryBean;

/**
 * class name:ManageIndustryCategoryService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月11日
 * @author JFTT)Lu Yunlong
 */
public interface ManageIndustryCategoryService {
	 /**
     * Method name: delete <BR>
     * Description: 删除【企业分类】 <BR>
     * Remark: <BR>
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * Method name: insert <BR>
     * Description: 添加【企业分类】 <BR>
     * Remark: <BR>
     * @param record
     */
    void insert(ManageIndustryCategoryBean record) throws Exception;

    /**
     * Method name: select <BR>
     * Description: 查询【企业分类】 <BR>
     * Remark: <BR>
     * @return  List<ManageExpandFieldBean><BR>
     */
    List<ManageIndustryCategoryBean> select(Map<String, Object> param) throws Exception;
    
    /**
     * Method name: select <BR>
     * Description: 查询【企业分类】 <BR>
     * Remark: <BR>
     * @return  List<ManageExpandFieldBean><BR>
     */
    ManageIndustryCategoryBean selectByName(String name) throws Exception;

    /**
     * Method name: update <BR>
     * Description: 修改【企业分类】 <BR>
     * Remark: <BR>
     * @param record
     */
    void update(ManageIndustryCategoryBean record) throws Exception;
}
