package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.PoliciesRulesBean;
import com.jftt.wifi.bean.vo.PoliciesRulesSearchVo;
/**
 * 政策法规表
 * class name:PoliciesRulesDaoMapper <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月13日
 * @author JFTT)chenrui
 */
public interface PoliciesRulesDaoMapper {

    PoliciesRulesBean selectByPrimaryKey(Integer id);
    
    /**
     * 新增政策法规
     */
	public void add(PoliciesRulesBean paramVo) throws Exception;
	/**
	 * 修改
	 * Method name: updateById <BR>
	 * Description: updateById <BR>
	 * Remark: <BR>
	 * @param paramVo
	 * @throws Exception  void<BR>
	 */
	public void updateById(PoliciesRulesBean paramVo) throws Exception;
	/**
	 * 获取政策法规列表
	 * Method name: getPoliciesRulesList <BR>
	 * Description: getPoliciesRulesList <BR>
	 * Remark: <BR>
	 * @param params
	 * @return  List<PoliciesRulesBean><BR>
	 */
	List<PoliciesRulesBean> getPoliciesRulesList(PoliciesRulesSearchVo params) throws Exception;

	int getPoliciesRulesListCount(PoliciesRulesSearchVo params) throws Exception;
	/**
	 * 发布 
	 * Remark: <BR>
	 * @param id
	 */
	void doPublish(@Param("id")String id) throws Exception;

	void doAbolish(@Param("id")String id)throws Exception;

	void deleteByIds(@Param("ids")String[] ids)throws Exception;
	
	int stu_getPoliciesRulesListCount(PoliciesRulesSearchVo params)throws Exception;

	List<PoliciesRulesBean> stu_getPoliciesRulesList(PoliciesRulesSearchVo params) throws Exception;

	List<PoliciesRulesBean> getAllByElastisearch();

}