/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: MallOrderDetailsPageVo.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-12-17        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.bean.vo;

import java.util.List;

import com.jftt.wifi.bean.MallOrderBean;
import com.jftt.wifi.bean.MallOrderRelateBean;

/**
 * class name:MallOrderDetailsPageVo <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015-12-17
 * @author JFTT)chenrui
 */
public class MallOrderDetailsPageVo extends MallOrderBean {
	//订单关联的商品信息
	private List<MallOrderRelateBean> relateProducts;

	public List<MallOrderRelateBean> getRelateProducts() {
		return relateProducts;
	}

	public void setRelateProducts(List<MallOrderRelateBean> relateProducts) {
		this.relateProducts = relateProducts;
	}
	
}
