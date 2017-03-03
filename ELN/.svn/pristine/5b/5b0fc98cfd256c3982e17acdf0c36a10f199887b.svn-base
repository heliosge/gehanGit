package com.jftt.wifi.dao;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.ShoppingCartBean;

public interface ShoppingCartDaoMapper {
	/**chenrui start*/
	
	ShoppingCartBean selectByPrimaryKey(Integer id);

	/**
	 * 添加购物车信息
	 * Method name: checkIsExist <BR>
	 * Description: checkIsExist <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  ShoppingCartBean<BR>
	 */
	ShoppingCartBean checkIsExist(ShoppingCartBean params) throws Exception;

	void updateRecord(ShoppingCartBean params) throws Exception;

	void insertRecord(ShoppingCartBean params) throws Exception;

	/**
	 * 移除购物车记录
	 * Method name: removeShoppingRecord <BR>
	 * Description: removeShoppingRecord <BR>
	 * Remark: <BR>
	 * @param params  void<BR>
	 */
	void removeShoppingRecord(@Param("ids")String ids) throws Exception;

	void removeShoppingRecordByIdAndType(@Param("userId")String userId,@Param("productIds")String productIds, 
			@Param("orderType")int orderType) throws Exception;

	Integer getShoppingCarCount(@Param("userId")String userId) throws Exception;
	
	/**chenrui end*/
}