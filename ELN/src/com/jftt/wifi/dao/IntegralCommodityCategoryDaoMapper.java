package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.vo.IntegralCommodityCategoryVo;



/**
 * class IntegralCommodityCategoryDaoMapper <BR>
 * class description: 商品分类dao <BR>
 * Remark: <BR>
 * @author zhangbocheng
 */
public interface IntegralCommodityCategoryDaoMapper {

	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取商品分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  IntegralCommodityCategoryBean<BR>
	 */
	public IntegralCommodityCategoryBean getById(@Param("id")Integer id) throws Exception;
	
	/**zhangbocheng start*/
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * @param IntegralCommodityCategoryBean  Integer<BR>
	 */
	public Integer checkName(IntegralCommodityCategoryBean bean) throws Exception;
	
	
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 删除【商品分类】 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteIntegralCommodityCategory(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: hasCommodity <BR>
	 * Description: 分类下是否有商品<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public Integer hasCommodity(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: updateIntegralCommodityCategory <BR>
	 * Description: 修改【商品分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public void updateIntegralCommodityCategory(IntegralCommodityCategoryBean record) throws Exception;

	/**
	 * Method name: insertIntegralCommodityCategory <BR>
	 * Description: 添加【商品分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	public Integer insertIntegralCommodityCategory(IntegralCommodityCategoryBean record) throws Exception;

	
	/**
	 * Method name: getIntegralCommodityCategorys <BR>
	 * Description: 查询商品分类 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<IntegralCommodityCategoryVo><BR>
	 */
	public List<IntegralCommodityCategoryVo> getIntegralCommodityCategorys() throws Exception;
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取商品分类的子商品分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<ResCourseCategoryVo><BR>
	 */
	public List<IntegralCommodityCategoryVo> getChildCategorys(@Param("categoryId")Integer categoryId) throws Exception;
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有商品分类<BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryBean> getUpCategorys(@Param("categoryId")Integer categoryId) throws Exception;


	/**zhangbocheng end*/
	
	
	/**chenrui start*/
	
	/**
	 * 获取积分商城分类
	 * Method name: getMallIntegralCategory <BR>
	 * Description: getMallIntegralCategory <BR>
	 * Remark: <BR>
	 * @return  List<IntegralCommodityCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryBean> getMallIntegralCategory() throws Exception;
	
	/**
	 * 根据id获取下级子分类
	 * Method name: getChildsByParentId <BR>
	 * Description: getChildsByParentId <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<IntegralCommodityCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryBean> getChildsByParentId(@Param("id")Integer categoryId) throws Exception;
	
	
	/**chenrui end*/
}
