package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ExpandParamBean;
import com.jftt.wifi.bean.ManageExpandFieldBean;

public interface ManageExpandFieldDaoMapper {
    /**
     * Method name: delete <BR>
     * Description: 删除【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param id
     * @return  int<BR>
     */
	void delete(@Param("id")Integer id) throws Exception;

    /**
     * Method name: insert <BR>
     * Description: 添加【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param record
     * @return  int<BR>
     */
	void insert(ManageExpandFieldBean record) throws Exception;

    /**
     * Method name: select <BR>
     * Description: 查询【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param companyId
     * @return  List<ManageExpandFieldBean><BR>
     */
    List<ManageExpandFieldBean> select(Map<String, Object> param) throws Exception;

    /**
     * Method name: update <BR>
     * Description: 修改【人员扩展字段】 <BR>
     * Remark: <BR>
     * @param record
     * @return  int<BR>
     */
    void update(ManageExpandFieldBean record) throws Exception;
    
    /**
     * Method name: queryCompanyParamList <BR>
     * Description: 查找公司参数列表 <BR>
     * Remark: <BR>
     * @param companyId
     * @return  List<ExpandParamBean><BR>
     */
    public List<ExpandParamBean> queryCompanyParamList(@Param("companyId")int companyId,@Param("language")String language)throws Exception;
    
    /**
     * Method name: queryParamList <BR>
     * Description: 查找参数列表 <BR>
     * Remark: <BR>
     * @param language
     * @return
     * @throws Exception  List<ManageExpandFieldBean><BR>
     */
    public List<ManageExpandFieldBean> queryParamList(@Param("language")String language)throws Exception;
    
    
    /**
     * Method name: deleteUserConfig <BR>
     * Description: 删除用户参数信息的配置 <BR>
     * Remark: <BR>
     * @param companyId  void<BR>
     */
    public void deleteUserConfig(ExpandParamBean expandParamBean) throws Exception;
    
    public void deleteUserProperty(ExpandParamBean expandParamBean) throws Exception;
    /**
     * Method name: insertUserConfig <BR>
     * Description: 保存用户参数信息的配置 <BR>
     * Remark: <BR>
     * @param expandParamBean
     * @throws Exception  void<BR>
     */
    public void insertUserConfig(ExpandParamBean expandParamBean)throws Exception;

	/**
	 * Method name: selectManageExpandFieldCount <BR>
	 * Description: 扩展字段数量 <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  int<BR>
	 */
	int selectManageExpandFieldCount()throws Exception;

	/**
	 * Method name: deleteUserConfigPropertu <BR>
	 * Description: 删除扩展字段和企业的关系 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	void deleteUserConfigPropertu(@Param("id")Integer id);
    
    
}