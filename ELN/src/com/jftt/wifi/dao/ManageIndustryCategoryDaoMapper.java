package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageIndustryCategoryBean;
import com.jftt.wifi.service.ManageIndustryCategoryService;

public interface ManageIndustryCategoryDaoMapper {
    /**
     * Method name: delete <BR>
     * Description: 删除【企业分类】 <BR>
     * Remark: <BR>
     * @param id
     */
    void delete(@Param("id")Integer id) throws Exception;

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
     * Method name: update <BR>
     * Description: 修改【企业分类】 <BR>
     * Remark: <BR>
     * @param record
     */
    void update(ManageIndustryCategoryBean record) throws Exception;

	/**
	 * Method name: selectOamIndustryByTopic <BR>
	 * Description: 获取专题推广【企业分类】 <BR>
	 * Remark: <BR>
	 * @param topicId
	 * @return
	 * @throws Exception  List<ManageIndustryCategoryService><BR>
	 */
	List<ManageIndustryCategoryService> selectOamIndustryByTopic(@Param("topicId")String topicId) throws Exception;

	/**
	 * Method name: selectByName <BR>
	 * Description: 根据名称获取【企业分类】 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return
	 * @throws Exception  ManageIndustryCategoryBean<BR>
	 */
	ManageIndustryCategoryBean selectByName(@Param("name")String name) throws Exception;
}