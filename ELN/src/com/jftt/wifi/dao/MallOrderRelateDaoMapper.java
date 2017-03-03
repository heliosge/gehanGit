package com.jftt.wifi.dao;

import com.jftt.wifi.bean.MallOrderRelateBean;

public interface MallOrderRelateDaoMapper {

	MallOrderRelateBean selectByPrimaryKey(Integer id);
	/**chenrui start*/
	
	/**
	 * 新增记录
	 * Method name: addMallOrderRelate <BR>
	 * Description: addMallOrderRelate <BR>
	 * Remark: <BR>
	 * @param rBean
	 * @throws Exception  void<BR>
	 */
	void addMallOrderRelate(MallOrderRelateBean rBean) throws Exception;

	/**chenrui end*/
}