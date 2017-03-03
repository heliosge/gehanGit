package com.jftt.wifi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.MallIntegralProVo;
import com.jftt.wifi.bean.vo.IntegralCommodityVo;



/**
 * class IntegralCommodityDaoMapper <BR>
 * class description: 积分商品dao <BR>
 * Remark: <BR>
 * @author zhangbocheng
 */
public interface IntegralCommodityDaoMapper {
	
	/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	public IntegralCommodityBean getById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	public IntegralCommodityVo getDetailById(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: checkIntegralCommodityCode <BR>
	 * Description: 检查积分商品编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkCode(IntegralCommodityBean bean) throws Exception;
	
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
	public void deleteIntegralCommodity(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putawayById(@Param("id")Integer id,@Param("putawayUserId")Integer userId) throws Exception;

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void putoutById(@Param("id")Integer id) throws Exception;

	
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


	
	
	
	
	/** zhangbocheng  end*/
	
	/**chenrui start*/
	
	
	/**
	 * 积分商品类目页获取商品信息
	 * Method name: getProductsByCategory <BR>
	 * Description: getProductsByCategory <BR>
	 * Remark: <BR>默认库存量排序
	 * @param param
	 * @return  List<IntegralCommodityBean><BR>
	 */
	public List<IntegralCommodityBean> getProductsByCategory(MallIntegralProVo param) throws Exception;

	public int getProductsByCategoryCount(MallIntegralProVo param) throws Exception;

	/**
	 * 按积分排序
	 * Method name: getProductsByCategoryOrderByIntegral <BR>
	 * Description: getProductsByCategoryOrderByIntegral <BR>
	 * Remark: <BR>
	 * @param param
	 * @return  List<IntegralCommodityBean><BR>
	 */
	public List<IntegralCommodityBean> getProductsByCategoryOrderByIntegral(MallIntegralProVo param) throws Exception;

	/**
	 * 按热门排序
	 * Method name: getProductsByCategoryOrderByHot <BR>
	 * Description: getProductsByCategoryOrderByHot <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<IntegralCommodityBean><BR>
	 */
	public List<IntegralCommodityBean> getProductsByCategoryOrderByHot(MallIntegralProVo param) throws Exception;


	/**
	 * 获取兑你喜欢的数据
	 * Method name: getYourLike <BR>
	 * Description: getYourLike <BR>
	 * Remark: <BR>
	 * @return  List<IntegralCommodityBean><BR>
	 */
	public List<IntegralCommodityBean> getYourLike() throws Exception;

	/**
	 * 更新库存和销量
	 * Method name: updateStock <BR>
	 * Description: updateStock <BR>
	 * Remark: <BR>
	 * @param coid
	 * @param cocount
	 * @throws Exception  void<BR>
	 */
	public void updateStock(@Param("coid")Integer coid, @Param("cocount")Integer cocount) throws Exception;


	public List<Map> getArea(Map<String, Object> map) throws Exception;


	/**
	 * 获取当前用户可用总积分
	 * Method name: getCurrentUserIntegral <BR>
	 * Description: getCurrentUserIntegral <BR>
	 * Remark: <BR>
	 * @param userId  void<BR>
	 */
	public HashMap<String, Object> getCurrentUserIntegral(@Param("userId")String userId) throws Exception;

	/**
	 * 更新用户积分
	 * Method name: updateUserIntegral <BR>
	 * Description: updateUserIntegral <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param allIntegral  void<BR>
	 */
	public void updateUserIntegral(@Param("userId")int userId, @Param("allIntegral")Integer allIntegral);
	
	
	/**chenrui end*/
}
