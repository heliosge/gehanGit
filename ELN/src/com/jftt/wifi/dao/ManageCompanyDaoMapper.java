package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;







import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.vo.ManageCompanyVo;

public interface ManageCompanyDaoMapper {
	
    /**
     * Method name: deleteCompany <BR>
     * Description: deleteCompany <BR>
     * Remark: <BR>
     * @param id
     * @return
     * @throws Exception  int<BR>
     */
    int deleteCompany(Integer id) throws Exception;


    int updateCompanyBaseInfo(ManageCompanyBean record) throws Exception;

	int selectCompanyCount(ManageCompanyVo vo) throws Exception;

	List<ManageCompanyBean> selectCompanyList(ManageCompanyVo vo) throws Exception;

	void freezeAndUnfreezeManageCompany(@Param("id")Integer id, @Param("status")String status) throws Exception;

	void updateCompanyResInfo(ManageCompanyBean bean) throws Exception;

	void insertCompany(ManageCompanyBean bean) throws Exception;

	void insertCompanyActiveCode(@Param("email")String email, @Param("activeCode")String activeCode) throws Exception;


	int checkActiveCode(@Param("key")String key, @Param("activeCode")String activeCode)throws Exception;

	List<Map> selectCity(Map<String, Object> map)throws Exception;
	
	Integer getCompanyIdByName(@Param("name")String name)throws Exception;


	/**
	 * Method name: selectManageCompanyCapacityList <BR>
	 * Description: 租户企业容量列表  <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ManageCompanyBean><BR>
	 */
	List<ManageCompanyBean> selectManageCompanyCapacityList(ManageCompanyVo vo);


	/**
	 * Method name: addCapacity <BR>
	 * Description: 扩容 <BR>
	 * Remark: <BR>
	 * @param param  void<BR>
	 */
	void addCapacity(Map<String, Object> param);


	/**
	 * Method name: selectTotalCapacity <BR>
	 * Description: 获取所有企业文件占用的空间 <BR>
	 * Remark: <BR>
	 * @return  double<BR>
	 */
	double selectTotalCapacity();
	
}