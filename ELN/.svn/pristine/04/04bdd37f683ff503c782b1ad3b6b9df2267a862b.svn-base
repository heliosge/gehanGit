package com.jftt.wifi.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jftt.wifi.bean.BaseMetaBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.BaseMetaService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;


/**
 * class name:LoginAction <BR>
 * class description: 元数据管理 <BR>
 * Remark: <BR>
 * @version 1.00 2013-12-13
 * @author JFTT)WANGJIAN
 */
@Controller
@RequestMapping(value="baseMeta")
public class BaseMetaAction {
	
	private static Logger log = Logger.getLogger(BaseMetaAction.class);
	
	@Resource(name="baseMetaService")
	private BaseMetaService baseMetaService;
	
	/**
	 * 到元数据管理页面
	 */
	@RequestMapping(value="metaList")
	public String metaList(HttpServletRequest request){
		
		List<BaseMetaBean> metaList = baseMetaService.getAllBaseMeta();
		
		request.setAttribute("metaList", JsonUtil.getJson4JavaList(metaList));
		
		return "meta/metaList";
	}
	
	/**
	 * 增加元数据
	 */
	@RequestMapping(value="addMeta")
	@ResponseBody
	public Object addMeta(BaseMetaBean baseMetaBean){
		
		try {
			
			baseMetaService.addMeta(baseMetaBean);
			
			return baseMetaBean;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * 修改元数据
	 */
	@RequestMapping(value="updateMeta")
	@ResponseBody
	public String updateMeta(BaseMetaBean baseMetaBean){
		
		try {
			
			baseMetaService.updateMeta(baseMetaBean);
			
			return Constant.AJAX_SUCCESS;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * 删除元数据
	 */
	@RequestMapping(value="delMeta")
	@ResponseBody
	public String delMeta(long id){
		
		try {
			
			baseMetaService.deleteMeta(id);
			
			return Constant.AJAX_SUCCESS;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Constant.AJAX_FAIL;
	}
	
	/**
	 * Method name: getMetaList <BR>
	 * Description: 获取元数据 <BR>
	 * Remark: <BR>
	 * @param code
	 * @return  Object<BR>
	 */
	@RequestMapping(value="getMetaList")
	@ResponseBody
	public Object getMetaList(String code){
		List<BaseMetaBean> base = baseMetaService.getMetaByKey(code);
		return base;
	}
	@ResponseBody
	@RequestMapping(value="getValueByKey")
	public String getValueByKey(String code){
		return baseMetaService.getValueByKey(code);
	}
}
