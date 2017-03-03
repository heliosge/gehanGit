package com.jftt.wifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jftt.wifi.bean.IntegralCommodityBean;
import com.jftt.wifi.bean.IntegralCommodityCategoryBean;
import com.jftt.wifi.bean.IntegralCommodityOrderBean;
import com.jftt.wifi.bean.LogisticsCompanyBean;
import com.jftt.wifi.bean.vo.IntegralCommodityCategoryVo;
import com.jftt.wifi.bean.vo.IntegralCommodityOrderVo;
import com.jftt.wifi.bean.vo.IntegralCommodityVo;
import com.jftt.wifi.dao.IntegralCommodityCategoryDaoMapper;
import com.jftt.wifi.dao.IntegralCommodityDaoMapper;
import com.jftt.wifi.dao.IntegralCommodityOrderDaoMapper;
import com.jftt.wifi.dao.LogisticsCompanyDaoMapper;
import com.jftt.wifi.service.IntegralCommodityManageService;
@Service
public class IntegralCommodityManageServiceImpl implements
		IntegralCommodityManageService {
	
	@Autowired
	private IntegralCommodityCategoryDaoMapper integralCommodityCategoryDaoMapper;
	@Autowired
	private IntegralCommodityDaoMapper integralCommodityDaoMapper;
	@Autowired
	private IntegralCommodityOrderDaoMapper integralCommodityOrderDaoMapper;
	@Autowired
	private LogisticsCompanyDaoMapper logisticsCompanyDaoMapper;

	/**zhangbocheng start*/
	/**category */
	/**
	 * Method name: getById <BR>
	 * Description: 根据id获取商品分类 <BR>
	 * Remark: <BR>
	 * @param id
	 * @return  IntegralCommodityCategoryBean<BR>
	 */
	@Override
	public IntegralCommodityCategoryBean getCommodityCategoryById(Integer id) throws Exception{
		return integralCommodityCategoryDaoMapper.getById(id);
	}
	
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 检查分类重名 <BR>
	 * Remark: <BR>
	 *  IntegralCommodityCategoryBean  Integer<BR>
	 */
	@Override
	public Integer checkIntegralCommodityCategoryName(IntegralCommodityCategoryBean bean) throws Exception{
		return integralCommodityCategoryDaoMapper.checkName(bean);
	}
	
	
	
	/**
	 * Method name: deleteIntegralCommodityCategory <BR>
	 * Description: 删除【商品分类】 <BR>
	 * Remark: <BR>
	 * @param parseInt  void<BR>
	 */
	@Override
	public Integer deleteIntegralCommodityCategory(Integer id) throws Exception{
		if(!hasCommodity(id)&&!hasChild(id)){
			integralCommodityCategoryDaoMapper.deleteIntegralCommodityCategory(id);
			return 1;
		}else{
			return 0;
		}
		
	}
	
	private boolean hasCommodity(Integer id) throws Exception{
		return integralCommodityCategoryDaoMapper.hasCommodity(id)>0;
	}
	
	private boolean hasChild(Integer id) throws Exception{
		return integralCommodityCategoryDaoMapper.getChildCategorys(id).size()>0;
	}

	/**
	 * Method name: updateIntegralCommodityCategory <BR>
	 * Description: 修改【商品分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	@Override
	public void updateIntegralCommodityCategory(IntegralCommodityCategoryBean record) throws Exception{
		integralCommodityCategoryDaoMapper.updateIntegralCommodityCategory(record);
	}

	/**
	 * Method name: insertIntegralCommodityCategory <BR>
	 * Description: 添加【商品分类】 <BR>
	 * Remark: <BR>
	 * @param record  void<BR>
	 */
	@Override
	public Integer insertIntegralCommodityCategory(IntegralCommodityCategoryBean record) throws Exception{
		return integralCommodityCategoryDaoMapper.insertIntegralCommodityCategory(record);
	}

	
	/**
	 * Method name: getIntegralCommodityCategorys <BR>
	 * Description: 查询商品分类 <BR>
	 * Remark: <BR>
	 * @param companyId
	 * @return  List<IntegralCommodityCategoryBean><BR>
	 */
	@Override
	public List<IntegralCommodityCategoryVo> getIntegralCommodityCategorys() throws Exception{
		
		List<IntegralCommodityCategoryVo> list =integralCommodityCategoryDaoMapper.getIntegralCommodityCategorys();
		if(list!=null&&list.size()>0){
			for(IntegralCommodityCategoryVo vo:list){
				vo.setType(1);
				vo.setChildren(getChildCategorysByVo(vo));
			}
		}
		
		return list;
	}
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取商品分类的子商品分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	public List<IntegralCommodityCategoryVo> getChildCategorysByVo(IntegralCommodityCategoryVo vo) throws Exception{
		
		List<IntegralCommodityCategoryVo> list = integralCommodityCategoryDaoMapper.getChildCategorys(vo.getId());
		if(list!=null&&list.size()>0){
			for(IntegralCommodityCategoryVo childVo :list){
				childVo.setType(vo.getType()+1);
				childVo.setChildren(getChildCategorysByVo(childVo));
			}
		}
		
		return list;
	}
	
	/**
	 * Method name: getChildCategory <BR>
	 * Description: 获取商品分类的子商品分类 <BR>
	 * Remark: <BR>
	 * @param categoryId
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	@Override
	public List<IntegralCommodityCategoryVo> getChildCategorys(Integer categoryId) throws Exception{
		
		List<IntegralCommodityCategoryVo> list = integralCommodityCategoryDaoMapper.getChildCategorys(categoryId);
		if(list!=null&&list.size()>0){
			for(IntegralCommodityCategoryVo childVo :list){
				childVo.setChildren(getChildCategorys(childVo.getId()));
			}
		}
		
		return list;
	}
	
	/**
	 * Method name: getUpCategorys <BR>
	 * Description: 获取上一级的所有商品分类<BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  List<ResCourseCategoryBean><BR>
	 */
	@Override
	public List<IntegralCommodityCategoryBean> getUpCategorys(Integer categoryId) throws Exception{
		return integralCommodityCategoryDaoMapper.getUpCategorys(categoryId);
	}

	
	
	
	/**IntegralCommodity */
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	@Override
	public IntegralCommodityBean getCommodityById(Integer id) throws Exception{
		return integralCommodityDaoMapper.getById(id);
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityBean<BR>
	 */
	@Override
	public IntegralCommodityVo getCommodityDetailById(Integer id) throws Exception{
		return integralCommodityDaoMapper.getDetailById(id);
	}

	
	/**
	 * Method name: checkIntegralCommodityCode <BR>
	 * Description: 检查积分商品编号重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkIntegralCommodityCode(IntegralCommodityBean bean) throws Exception{
		return integralCommodityDaoMapper.checkCode(bean);
	}
	
	
	/**
	 * Method name: insertIntegralCommodity <BR>
	 * Description: 新增积分商品 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer insertIntegralCommodity(IntegralCommodityBean bean) throws Exception{
		integralCommodityDaoMapper.insertIntegralCommodity(bean);
		return bean.getId();
	}

	/**
	 * Method name: updateIntegralCommodity <BR>
	 * Description: 修改积分商品 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void updateIntegralCommodity(IntegralCommodityBean bean) throws Exception{
		integralCommodityDaoMapper.updateIntegralCommodity(bean);
	}

	/**
	 * Method name: deleteIntegralCommodity <BR>
	 * Description: 删除积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteIntegralCommodity(Integer id) throws Exception{
		integralCommodityDaoMapper.deleteIntegralCommodity(id);
	}

	
	/**
	 * Method name: putawayById <BR>
	 * Description: 上架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void putawayById(Integer id,Integer userId) throws Exception{
		integralCommodityDaoMapper.putawayById(id,userId);
	}

	
	/**
	 * Method name: putoutById <BR>
	 * Description: 下架积分商品 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void putoutById(Integer id) throws Exception{
		integralCommodityDaoMapper.putoutById(id);
	}

	
	/**
	 * Method name: selectIntegralCommodityList <BR>
	 * Description: 查询积分商品 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public List<IntegralCommodityVo> selectIntegralCommodityList(IntegralCommodityVo vo) throws Exception{
		return integralCommodityDaoMapper.selectIntegralCommodityList(vo);
	}
	
	/**
	 * Method name: selectIntegralCommodityCount <BR>
	 * Description: 查询积分商品数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public Integer selectIntegralCommodityCount(IntegralCommodityVo vo) throws Exception{
		return integralCommodityDaoMapper.selectIntegralCommodityCount(vo);
	}
	
	
	
	
	/** IntegralCommodityOrder */
	

	/**
	 * Method name: getById <BR>
	 * Description: 根据id查积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderBean<BR>
	 */
	@Override
	public IntegralCommodityOrderBean getCommodityOrderById(Integer id) throws Exception{
		return integralCommodityOrderDaoMapper.getById(id);
	}

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查积分商品 <BR>
	 * Remark: <BR>
	 * @param id  IntegralCommodityOrderVo<BR>
	 */
	@Override
	public IntegralCommodityOrderVo getCommodityOrderDetailById(Integer id) throws Exception{
		return integralCommodityOrderDaoMapper.getDetailById(id);
	}

	
	/**
	 * Method name: insertIntegralCommodityOrder <BR>
	 * Description: 新增积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer insertIntegralCommodityOrder(IntegralCommodityOrderBean bean) throws Exception{
		return integralCommodityOrderDaoMapper.insertIntegralCommodityOrder(bean);
	}

	/**
	 * Method name: updateIntegralCommodityOrder <BR>
	 * Description: 修改积分商品订单 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void updateIntegralCommodityOrder(IntegralCommodityOrderBean bean) throws Exception{
		integralCommodityOrderDaoMapper.updateIntegralCommodityOrder(bean);
	}

	/**
	 * Method name: deleteIntegralCommodityOrder <BR>
	 * Description: 删除积分商品订单 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteIntegralCommodityOrder(Integer id) throws Exception{
		integralCommodityOrderDaoMapper.deleteIntegralCommodityOrder(id);
	}

	
	/**
	 * Method name: postById <BR>
	 * Description:  发货<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void post(IntegralCommodityOrderBean bean) throws Exception{
		integralCommodityOrderDaoMapper.post(bean);
	}

	
	
	
	/**
	 * Method name: selectIntegralCommodityOrderList <BR>
	 * Description: 查询积分商品订单 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public List<IntegralCommodityOrderVo> selectIntegralCommodityOrderList(IntegralCommodityOrderVo vo) throws Exception{
		return integralCommodityOrderDaoMapper.selectIntegralCommodityOrderList(vo);
	}
	
	
	/**
	 * Method name: selectIntegralCommodityOrderCount <BR>
	 * Description: 查询积分商品订单数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	@Override
	public Integer selectIntegralCommodityOrderCount(IntegralCommodityOrderVo vo) throws Exception{
		return integralCommodityOrderDaoMapper.selectIntegralCommodityOrderCount(vo);
	}
	
	
	
	
	
    /** 物流公司logisticsCompany */
	

	/**
	 * Method name: getById <BR>
	 * Description: 根据id查公司 <BR>
	 * Remark: <BR>
	 * @param id  LogisticsCompanyBean<BR>
	 */
	@Override
	public LogisticsCompanyBean getLogisticsCompanyById(Integer id) throws Exception{
		return logisticsCompanyDaoMapper.getById(id);
	}

	

	/**
	 * Method name: checLogisticsCompanykname <BR>
	 * Description: 检查名称重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer checkLogisticsCompanyName(LogisticsCompanyBean bean) throws Exception{
		return logisticsCompanyDaoMapper.checkName(bean);
	}
	
	/**
	 * Method name: insertLogisticsCompany <BR>
	 * Description: 新增物流公司 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public Integer insertLogisticsCompany(LogisticsCompanyBean bean) throws Exception{
		return logisticsCompanyDaoMapper.insertLogisticsCompany(bean);
	}

	/**
	 * Method name: updateLogisticsCompany <BR>
	 * Description: 修改 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	@Override
	public void updateLogisticsCompany(LogisticsCompanyBean bean) throws Exception{
		logisticsCompanyDaoMapper.updateLogisticsCompany(bean);
	}

	/**
	 * Method name: deleteLogisticsCompany<BR>
	 * Description: 删除 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	@Override
	public void deleteLogisticsCompany(Integer id) throws Exception{
		logisticsCompanyDaoMapper.deleteLogisticsCompany(id);
	}

	

	/**
	 * Method name: selectLogisticsCompany <BR>
	 * Description: 查询<BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<LogisticsCompanyBean> selectLogisticsCompany() throws Exception{
		return logisticsCompanyDaoMapper.selectLogisticsCompany();
	}
	
	
	
	
	/**zhangbocheng end*/

}
