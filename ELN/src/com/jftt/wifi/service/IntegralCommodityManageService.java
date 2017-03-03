package com.jftt.wifi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.LogisticsCompanyBean;
import com.jftt.wifi.bean.vo.IntegralCommodityCategoryVo;
import com.jftt.wifi.bean.vo.IntegralCommodityOrderVo;
import com.jftt.wifi.bean.vo.IntegralCommodityVo;


/**
 * class IntegralCommodityService <BR>
 * class description: 积分商城Service <BR>
 * Remark: <BR>
 * @author zhangbocheng
 */
public interface IntegralCommodityManageService {

	
	/**zhangbocheng start*/
	/**category */
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取商品分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  IntegralCommodityCategoryBean<BR>
	 */
	public IntegralCommodityCategoryBean getCommodityCategoryById(Integer id) throws Exception;
	
	
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 * IntegralCommodityCategoryBean  Integer<BR>
	 */
	public Integer checkIntegralCommodityCategoryName(IntegralCommodityCategoryBean bean) throws Exception;
	
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 删除【商品分类】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	public Integer deleteIntegralCommodityCategory(Integer id) throws Exception;

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
	 * @return  List<IntegralCommodityCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryVo> getIntegralCommodityCategorys() throws Exception;
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取商品分类的子商品分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryVo> getChildCategorys(Integer categoryId) throws Exception;
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有商品分类<BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryBean> getUpCategorys(Integer categoryId) throws Exception;

	
	
	
	/**IntegralCommodity */
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	public IntegralCommodityBean getCommodityById(Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	public IntegralCommodityVo getCommodityDetailById(Integer id) throws Exception;

	

	/**
	 * Method name: checkIntegralCommodityCode <BR>
	 * Description: 检查积分商品编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkIntegralCommodityCode(IntegralCommodityBean bean) throws Exception;
	
	/**
	 * Method name: insertIntegralCommodity <BR>
	 * Description: 新增积分商品 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertIntegralCommodity(IntegralCommodityBean bean) throws Exception;

	/**
	 * Method name: updateIntegralCommodity <BR>
	 * Description: 修改积分商品 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateIntegralCommodity(IntegralCommodityBean bean) throws Exception;

	/**
	 * Method name: deleteIntegralCommodity <BR>
	 * Description: 删除积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteIntegralCommodity(Integer id) throws Exception;

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(Integer id,Integer userId) throws Exception;

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putoutById(Integer id) throws Exception;

	
	/**
	 * Method name: selectIntegralCommodityList <BR>
	 * Description: 查询积分商品 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<IntegralCommodityVo> selectIntegralCommodityList(IntegralCommodityVo vo) throws Exception;
	
	/**
	 * Method name: selectIntegralCommodityCount <BR>
	 * Description: 查询积分商品数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectIntegralCommodityCount(IntegralCommodityVo vo) throws Exception;
	
	
	
	
	/** IntegralCommodityOrder */
	

	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderBean<BR>
	 */
	public IntegralCommodityOrderBean getCommodityOrderById(Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderVo<BR>
	 */
	public IntegralCommodityOrderVo getCommodityOrderDetailById(Integer id) throws Exception;

	
	/**
	 * Method name: insertIntegralCommodityOrder <BR>
	 * Description: 新增积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertIntegralCommodityOrder(IntegralCommodityOrderBean bean) throws Exception;

	/**
	 * Method name: updateIntegralCommodityOrder <BR>
	 * Description: 修改积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateIntegralCommodityOrder(IntegralCommodityOrderBean bean) throws Exception;

	/**
	 * Method name: deleteIntegralCommodityOrder <BR>
	 * Description: 删除积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteIntegralCommodityOrder(Integer id) throws Exception;

	
	/**
	 * Method name: post <BR>
	 * Description:  发货<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void post(IntegralCommodityOrderBean bean) throws Exception;

	
	
	
	/**
	 * Method name: selectIntegralCommodityOrderList <BR>
	 * Description: 查询积分商品订单 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<IntegralCommodityOrderVo> selectIntegralCommodityOrderList(IntegralCommodityOrderVo vo) throws Exception;
	
	
	/**
	 * Method name: selectIntegralCommodityOrderCount <BR>
	 * Description: 查询积分商品订单数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectIntegralCommodityOrderCount(IntegralCommodityOrderVo vo) throws Exception;
	
	
	
	
    /** 物流公司logisticsCompany */
	

	/**
	 * Method name: getById <BR>
	 * Description: 根据id查公司 <BR>
	 * Remark: <BR>
	 * @param id  LogisticsCompanyBean<BR>
	 */
	public LogisticsCompanyBean getLogisticsCompanyById(Integer id) throws Exception;

	

	/**
	 * Method name: checLogisticsCompanykname <BR>
	 * Description: 检查名称重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkLogisticsCompanyName(LogisticsCompanyBean bean) throws Exception;
	
	/**
	 * Method name: insertLogisticsCompany <BR>
	 * Description: 新增物流公司 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertLogisticsCompany(LogisticsCompanyBean bean) throws Exception;

	/**
	 * Method name: updateLogisticsCompany <BR>
	 * Description: 修改 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateLogisticsCompany(LogisticsCompanyBean bean) throws Exception;

	/**
	 * Method name: deleteLogisticsCompany<BR>
	 * Description: 删除 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteLogisticsCompany(Integer id) throws Exception;

	

	/**
	 * Method name: selectLogisticsCompany <BR>
	 * Description: 查询<BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<LogisticsCompanyBean> selectLogisticsCompany() throws Exception;
	

	
	
	/**zhangbocheng end*/
	
}
