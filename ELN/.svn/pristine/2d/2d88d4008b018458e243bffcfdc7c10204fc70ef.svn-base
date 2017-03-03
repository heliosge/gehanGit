/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: CourseStoreServiceImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015年11月17日        | JFTT)chenrui    | original version
 */
package com.jftt.wifi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.config.AlipayConfig;
import com.jftt.wifi.action.ApproveManageAction;
import com.jftt.wifi.bean.CourseEvaluationBean;
import com.jftt.wifi.bean.MallCourseBean;
import com.jftt.wifi.bean.MallCourseCategoryBean;
import com.jftt.wifi.bean.MallOrderBean;
import com.jftt.wifi.bean.MallOrderRelateBean;
import com.jftt.wifi.bean.MallSellRecordBean;
import com.jftt.wifi.bean.MallTopicBean;
import com.jftt.wifi.bean.MallTopicCourseBean;
import com.jftt.wifi.bean.ManageCompanyBean;
import com.jftt.wifi.bean.ManageDepartmentBean;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.ResCourseBean;
import com.jftt.wifi.bean.ResCoursewareBean;
import com.jftt.wifi.bean.ResSectionBean;
import com.jftt.wifi.bean.ResSectionExamBean;
import com.jftt.wifi.bean.ShoppingCartBean;
import com.jftt.wifi.bean.exam.ExamScheduleBean;
import com.jftt.wifi.bean.vo.CommonPagingVo;
import com.jftt.wifi.bean.vo.CourseCaVo;
import com.jftt.wifi.bean.vo.CourseDetailsPageVo;
import com.jftt.wifi.bean.vo.MallCoursePageVo;
import com.jftt.wifi.bean.vo.MallOrderDetailsPageVo;
import com.jftt.wifi.bean.vo.MallSectionExamVo;
import com.jftt.wifi.bean.vo.MallTopicDetailVo;
import com.jftt.wifi.bean.vo.ResSectionBeanVo;
import com.jftt.wifi.bean.vo.SearchOrderVo;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.CourseEvaluationDaoMapper;
import com.jftt.wifi.dao.MallCourseCategoryDaoMapper;
import com.jftt.wifi.dao.MallCourseDaoMapper;
import com.jftt.wifi.dao.MallOrderDaoMapper;
import com.jftt.wifi.dao.MallOrderRelateDaoMapper;
import com.jftt.wifi.dao.MallSellRecordDaoMapper;
import com.jftt.wifi.dao.MallTopicDaoMapper;
import com.jftt.wifi.dao.ManageDepartmentDaoMapper;
import com.jftt.wifi.dao.ResCourseDaoMapper;
import com.jftt.wifi.dao.ResCoursewareDaoMapper;
import com.jftt.wifi.dao.ResSectionDaoMapper;
import com.jftt.wifi.dao.ResSectionExamDaoMapper;
import com.jftt.wifi.dao.ShoppingCartDaoMapper;
import com.jftt.wifi.exception.database.DataBaseException;
import com.jftt.wifi.service.CourseStoreService;
import com.jftt.wifi.service.ManageCompanyService;
import com.jftt.wifi.service.ManageUserService;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.MyExcelHelp;

/**
 * class name:CourseStoreServiceImpl <BR>
 * class description: <BR>
 * Remark: <BR>
 * @version 1.00 2015年11月17日
 * @author JFTT)chenrui
 */
@Service
public class CourseStoreServiceImpl implements CourseStoreService {
	private static Logger logger = Logger.getLogger(CourseStoreServiceImpl.class);
	
	@Autowired
	private MallTopicDaoMapper mallTopicDaoMapper;
	@Autowired
	private MallCourseDaoMapper mallCourseDaoMapper;
	@Autowired
	private MallCourseCategoryDaoMapper mallCourseCategoryDaoMapper;
	@Autowired
	private ShoppingCartDaoMapper shoppingCartDaoMapper;
	@Autowired
	private MallOrderDaoMapper mallOrderDaoMapper;
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private MallOrderRelateDaoMapper mallOrderRelateDaoMapper;
	@Autowired
	private CourseEvaluationDaoMapper courseEvaluationDaoMapper;
	@Autowired
	private ManageCompanyService manageCompanyService;
	@Autowired
	private ManageDepartmentDaoMapper manageDepartmentDaoMapper;
	@Autowired
	private ResCourseDaoMapper resCourseDaoMapper;
	@Autowired
	private MallSellRecordDaoMapper mallSellRecordDaoMapper;
	@Autowired
	private ResSectionDaoMapper resSectionDaoMapper;
	@Autowired
	private ResCoursewareDaoMapper resCoursewareDaoMapper;
	@Autowired
	private ResSectionExamDaoMapper resSectionExamDaoMapper;
	
	/**chenrui start*/
	/**]
	 * 获取首页banner商城专题信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getCourseTopic() <BR>
	 * Method name: getCourseTopic <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallTopicBean> getCourseTopic() throws Exception {
		return mallTopicDaoMapper.getCourseTopic();
	}
	/**
	 * 获取精品课程
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getBoutiqueCourse() <BR>
	 * Method name: getBoutiqueCourse <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallCoursePageVo> getBoutiqueCourse(String type) throws Exception {
		return mallCourseDaoMapper.getBoutiqueCourse(type);
	}
	/**
	 * 获取专题列表
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getMallTopicList() <BR>
	 * Method name: getMallTopicList <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallTopicBean> getMallTopicList(CommonPagingVo pageVo) throws Exception {
		return mallTopicDaoMapper.getMallTopicList(pageVo);
	}
	@Override
	public int getMallTopicListCount() throws Exception {
		return mallTopicDaoMapper.getMallTopicListCount();
	}
	/**
	 *  获取专题及其下课程的详细信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getMallTopicDetailsById(java.lang.String) <BR>
	 * Method name: getMallTopicDetailsById <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public MallTopicDetailVo getMallTopicDetailsById(String id) throws Exception {
		return mallTopicDaoMapper.getMallTopicDetailsById(id);
	}
	/**
	 * 获取商品课程分类
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getMallCourseCategory() <BR>
	 * Method name: getMallCourseCategory <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallCourseCategoryBean> getMallCourseCategory() throws Exception {
		return mallCourseCategoryDaoMapper.getMallCourseCategory();
	}
	/**
	 * 根据商城课程id获取课程详情
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getMailCourseDetailById() <BR>
	 * Method name: getMailCourseDetailById <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public MallCoursePageVo getMailCourseDetailById(String id) throws Exception {
		return mallCourseDaoMapper.getMailCourseDetailById(id);
	}
	/**
	 * 根据商城课程分类 获取课程信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getCourseByCategory(com.jftt.wifi.bean.vo.CourseCaVo) <BR>
	 * Method name: getCourseByCategory <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallCoursePageVo> getCourseByCategory(CourseCaVo param) throws Exception {
		String type = param.getOrderType();
		List<MallCoursePageVo> result = null;
		
		List<String> results = new ArrayList<String>();
		String categoryId = param.getCategoryId();//当前点击的分类id
		if(categoryId!=null && !categoryId.isEmpty()){
			logger.debug("===========递归获取子分类=============");
			returnCategoryIds(Integer.parseInt(categoryId), results);
		}
		String childIds = "";
		if(results.size()>0){
			for(String rs : results){
				childIds += rs + ",";
			}
			childIds = childIds.substring(0, childIds.length()-1);
		}
		logger.debug("==========得到处理后的子分类id->"+childIds+"=============");
		param.setChildCategoryIds(childIds);
		if("1".equals(type)){//按时间排序
			result =  mallCourseDaoMapper.getCourseByCategoryOrderDefault(param);
		}else if("2".equals(type)){//按销量排序
			result =  mallCourseDaoMapper.getCourseByCategoryOrderSaleNum(param);
		}else if("3".equals(type)){//按评价排序
			result =  mallCourseDaoMapper.getCourseByCategoryOrderEvaluate(param);
		}
		
		return result;
	}
	@Override
	public int getCourseByCategoryCount(CourseCaVo param) throws Exception {
		List<String> results = new ArrayList<String>();
		String categoryId = param.getCategoryId();//当前点击的分类id
		if(categoryId!=null && !categoryId.isEmpty()){
			returnCategoryIds(Integer.parseInt(categoryId), results);
		}
		String childIds = "";
		if(results.size()>0){
			for(String rs : results){
				childIds += rs + ",";
			}
			childIds = childIds.substring(0, childIds.length()-1);
		}
		param.setChildCategoryIds(childIds);
		return mallCourseDaoMapper.getCourseByCategoryCount(param);
	}
	
	private void returnCategoryIds(int categoryId,List<String> results) throws Exception{
		List<MallCourseCategoryBean> cbean  = mallCourseCategoryDaoMapper.getChildsByParentId(categoryId);
		if(cbean.size()>0){//说明有子分类
			results.add(categoryId+"");
			for(MallCourseCategoryBean be : cbean){
				int tmpId = be.getId();
				returnCategoryIds(tmpId,results);
			}
		}else{
			results.add(categoryId+"");
		}
	}
	/**
	 * 获取当前用户购物车中信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getShoppingCartInfo(java.lang.String) <BR>
	 * Method name: getShoppingCartInfo <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallCoursePageVo> getShoppingCartInfo(String userId,String searchName,String productType) throws Exception {
		List<MallCoursePageVo> list =  mallCourseDaoMapper.getShoppingCartInfo(userId,searchName,productType);
		for(MallCoursePageVo li : list){
			int companyId = li.getCompanyId();
			int subCompanyId = li.getCompanyId();
			String companyName = "";
			if(companyId == subCompanyId){
				ManageCompanyBean company = manageCompanyService.selectCompanyById(subCompanyId);
				if(company != null){
					companyName = company.getName();
				}
			}else{
				ManageDepartmentBean subCompany = manageDepartmentDaoMapper.getManageDepartmentById(subCompanyId);
				if(subCompany!=null){
					companyName = subCompany.getName();
				}
			}
			li.setCompanyName(companyName);
		}
		return list;
	}
	/**
	 * 根据课程id获取课程详细信息，级联获取课程下的章节课件考试信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getDetailsByCourseId(java.lang.String) <BR>
	 * Method name: getDetailsByCourseId <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public CourseDetailsPageVo getDetailsByCourseId(String courseId) throws Exception {
		
		return mallCourseDaoMapper.getDetailsByCourseId(courseId);
	}
	/**
	 * 添加购物车信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#addShoppingRecord(com.jftt.wifi.bean.ShoppingCartBean) <BR>
	 * Method name: addShoppingRecord <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  <BR>
	 */
	@Override
	public void addShoppingRecord(ShoppingCartBean params) throws Exception {
		//先判断当前需要加入的记录是否已存在购物车中，如果没有 则插入一条记录 ，如果有则不新增数据，更新数量
		ShoppingCartBean cartBean = shoppingCartDaoMapper.checkIsExist(params);
		if(cartBean!=null){
			params.setId(cartBean.getId());
			params.setCounts(cartBean.getCounts()+1);
			shoppingCartDaoMapper.updateRecord(params);
		}else{
			shoppingCartDaoMapper.insertRecord(params);
		}
	}
	/**
	 * 生成订单
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#addMallOrder(com.jftt.wifi.bean.MallOrderBean) <BR>
	 * Method name: addMallOrder <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param orderBean
	 * @throws Exception  <BR>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public MallOrderBean addMallOrder(MallOrderBean orderBean) throws Exception {
		MallOrderBean result = null;
		//从参数中处理下最终在sql中处理的参数
		//生成唯一订单编号
		String HeadString = "DD"; 
		if(orderBean.getCommodityType()==2){//专题
			HeadString += "ZT";
		}else{
			HeadString += "KC";
		}
		String nowDate =  new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		// 生成一个随机尾数  1-1000
		int randNum = (int) (Math.random()*1000);
		String code = HeadString + nowDate + randNum;
		orderBean.setCode(code);
		
		//插入订单表记录
		mallOrderDaoMapper.addMallOrder(orderBean);
		//此时的orderBean中的id为上面执行insert后的返回的记录id
		int commodityType = orderBean.getCommodityType();//1课程 2专题
		int orderId = orderBean.getId();
		MallOrderRelateBean rBean = null;
		String productIds = orderBean.getProductIds();//逗号拼接
		String idsArr[] = productIds.split(",");
		//根据当前订单携带的产品信息，插入相关的订单关联表记录
		for(String tmpId :idsArr){
			rBean = new MallOrderRelateBean();
			String cname;
			BigDecimal cpice;
			//根据产品id查出对应的名称和价格
			if(commodityType==1){//查课程
				MallCourseBean cBean = mallCourseDaoMapper.selectByPrimaryKey(Integer.parseInt(tmpId));
				cname = cBean.getCourseName();
				cpice = cBean.getCheapPrice();
			}else{//查专题
				MallTopicBean tBean = mallTopicDaoMapper.selectByPrimaryKey(Integer.parseInt(tmpId));
				cname = tBean.getName();
				cpice = tBean.getCheapPrice();
			}
			rBean.setOrderId(orderId);
			rBean.setRelateId(Integer.parseInt(tmpId));
			rBean.setCommodityName(cname);
			rBean.setPrice(cpice);
			rBean.setCommodityType(commodityType);
			mallOrderRelateDaoMapper.addMallOrderRelate(rBean);
		}
		//查询订单信息，返回订单信息
		result = mallOrderDaoMapper.selectByPrimaryKey(orderId);
		return result;
	}
	
	/**
	 * 移除购物车记录
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#removeShoppingRecord(com.jftt.wifi.bean.ShoppingCartBean) <BR>
	 * Method name: removeShoppingRecord <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @throws Exception  <BR>
	 */
	@Override
	public void removeShoppingRecord(String ids) throws Exception {
		shoppingCartDaoMapper.removeShoppingRecord(ids);
	}
	/**
	 * 获取购买记录
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getBuyRecords(com.jftt.wifi.bean.vo.SearchOrderVo) <BR>
	 * Method name: getBuyRecords <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param params
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<MallOrderBean> getBuyRecords(SearchOrderVo params) throws Exception {
		return mallOrderDaoMapper.getBuyRecords(params);
	}
	
	@Override
	public int getBuyRecordsCount(SearchOrderVo params) throws Exception {
		return mallOrderDaoMapper.getBuyRecordsCount(params);
	}
	/**
	 * 更改订单状态
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#updateMallOrderStatus(java.lang.String, java.lang.String) <BR>
	 * Method name: updateMallOrderStatus <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @param status
	 * @throws Exception  <BR>
	 */
	@Override
	public void updateMallOrderStatus(String orderId, String status) throws Exception {
		mallOrderDaoMapper.updateMallOrderStatus(orderId,status);
	}
	/**
	 * 获取课程评价信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getMallEvaluationCount(java.lang.String, java.lang.String) <BR>
	 * Method name: getMallEvaluationCount <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param courseId
	 * @return
	 * @throws Exception  <BR>
	 */
	
	@Override
	public List<CourseEvaluationBean> getEvaluation(String userId, String courseId, String pageSize, long fromNum) throws Exception {
		List<CourseEvaluationBean> list = courseEvaluationDaoMapper.getMallEvaluation( userId, courseId, pageSize, fromNum);
		for(CourseEvaluationBean cb : list){
			int uid = cb.getUserId();
			ManageUserBean ub = manageUserService.findUserByIdCondition(uid+"");
			cb.setUserName(ub.getName());
			cb.setHeadImg(ub.getHeadPhoto());
		}
		return list;
	}
	@Override
	public int getMallEvaluationCount(String userId, String courseId) throws Exception {
		return courseEvaluationDaoMapper.getMallEvaluationCount(userId,courseId);
	}
	/**
	 * 根据ids获取对应的商品信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getProductInfoByIds(java.lang.String) <BR>
	 * Method name: getProductInfoByIds <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param ids
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public List<HashMap<String, Object>> getProductInfoByIds(String ids,String type) throws Exception {
		return mallCourseDaoMapper.getProductInfoByIds(ids,type);
	}
	/**
	 * 删除购买记录
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#deleteBuyRecordsByIds(java.lang.String) <BR>
	 * Method name: deleteBuyRecordsByIds <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param ids
	 * @throws Exception  <BR>
	 */
	@Override
	public void deleteBuyRecordsByIds(String ids) throws Exception {
		mallOrderDaoMapper.deleteBuyRecordsByIds(ids);
	}
	/**
	 * 评价订单
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#giveOrderEvaluate(java.lang.String) <BR>
	 * Method name: giveOrderEvaluate <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param contents
	 * @throws Exception  <BR>
	 */
	@Override
	public void giveOrderEvaluate(String id,String contents) throws Exception {
		mallOrderDaoMapper.giveOrderEvaluate(id,contents);
	}
	
	/**
	 * 根据订单id获取订单详细信息
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getOrderDetailsById(java.lang.String) <BR>
	 * Method name: getOrderDetailsById <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param id
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public MallOrderDetailsPageVo getOrderDetailsById(String id) throws Exception {
		return mallOrderDaoMapper.getOrderDetailsById(id);
	}
	
	/**
	 * 导出购买记录
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#exportBuyRecords(java.lang.String) <BR>
	 * Method name: exportBuyRecords <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param userId
	 * @throws Exception  <BR>
	 */
	@Override
	public HSSFWorkbook exportBuyRecords(String userId) throws Exception {
		List<MallOrderBean> list = mallOrderDaoMapper.exportBuyRecords(userId);
		int maxRow = list.size();
		return writeExcel("购买记录",maxRow, 7, list);
	}
	/**
	 * 获取购物车中商品数量
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#getShoppingCarCount() <BR>
	 * Method name: getShoppingCarCount <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public Integer getShoppingCarCount(String userId) throws Exception {
		return shoppingCartDaoMapper.getShoppingCarCount(userId);
	}
	/**
	 * 
	 * @author JFTT)chenrui
	 * Method name: writeExcel <BR>
	 * Description: 写入Excel文件 <BR>
	 * Remark: <BR>  void<BR>
	 */
	public  HSSFWorkbook writeExcel(String sheetName,int maxRow, int maxCol, List<MallOrderBean> dataList) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName);
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");

		HSSFDataFormat dataFormat = workbook.createDataFormat();
		// 单元格数据样式准备设置
		HSSFCellStyle leftStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_LEFT);// 左
		HSSFCellStyle centerStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_CENTER);//中
		HSSFCellStyle rightStyle = MyExcelHelp.createCellStyle(workbook, font, CellStyle.ALIGN_RIGHT);// 右
		HSSFCellStyle wrapStyle = MyExcelHelp.createWrapCellStyle(workbook, font, CellStyle.ALIGN_LEFT, true);// 单元格内容自动换行
		HSSFCellStyle dateStyle = MyExcelHelp.createFormatCellStyle(workbook, font, CellStyle.ALIGN_RIGHT, dataFormat, MyExcelHelp.DATE_FORMAT);
		HSSFCellStyle moneyStyle = MyExcelHelp.createFormatCellStyle(workbook, font, CellStyle.ALIGN_RIGHT, dataFormat,MyExcelHelp. MONEY_FORMAT);
		int rowIndex = 0;
		// 标题行
		HSSFRow row = sheet.createRow(rowIndex);
		int j = 0;
		
		MyExcelHelp.createStringCell(row, 0, centerStyle, "订单编号");
		sheet.setColumnWidth(0, 20 * 256);
		MyExcelHelp.createStringCell(row, 1, centerStyle, "商品名称");
		sheet.setColumnWidth(1, 10 * 256);
		MyExcelHelp.createStringCell(row, 2, centerStyle, "订单价格");
		sheet.setColumnWidth(2, 10 * 256);
		MyExcelHelp.createStringCell(row, 3, centerStyle, "下单时间");
		sheet.setColumnWidth(3, 20 * 256);
		MyExcelHelp.createStringCell(row, 4, centerStyle, "商品类型");
		sheet.setColumnWidth(4, 10 * 256);
		MyExcelHelp.createStringCell(row, 5, centerStyle, "购买状态");
		sheet.setColumnWidth(5, 10 * 256);
		MyExcelHelp.createStringCell(row, 6, centerStyle, "发票状态");
		sheet.setColumnWidth(6, 10 * 256);
		for (j = 0; j < maxRow; j++) {// 控制行
			row = sheet.createRow(++rowIndex);
			
			MallOrderBean bean = dataList.get(j);
			String code = bean.getCode();
			String names = bean.getProductNames();
			BigDecimal price = bean.getPrice();
			Date date = bean.getCreateTime();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			int type = bean.getCommodityType();
			String typeStr = "";
			if(type==1){
				typeStr = "课程";
			}else{
				typeStr = "专题";
			}
			int status = bean.getStatus();
			String statusStr = "";
			switch (status) {
			case 0:
				statusStr = "待付款";
				break;
			case 1:
				statusStr = "待发货";
				break;
			case 2:
				statusStr = "已发货";
				break;
			case 3:
				statusStr = "已评价";
				break;
			case 4:
				statusStr = "已失效";
				break;
			case 5:
				statusStr = "已取消";
				break;
			default:
				break;
			}
			int invoiceStatus = bean.getInvoiceStatus();
			String invoiceStatusStr = "";
			if(invoiceStatus == 1){
				invoiceStatusStr = "已邮寄";
			}else{
				invoiceStatusStr = "待邮寄";
			}
			int colIndex = 0;
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, code);
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle, names);
			MyExcelHelp.createStringCell(row, colIndex++, moneyStyle, price+"");
			MyExcelHelp.createStringCell(row, colIndex++, wrapStyle,dateStr);
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,typeStr);
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle, statusStr);
			MyExcelHelp.createStringCell(row, colIndex++, centerStyle,invoiceStatusStr);
		}
		return workbook;
	}
	/**
	 * 支付宝支付完成后，需要做些事情
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#behindApplyDoSomething(java.lang.String, java.lang.String) <BR>
	 * Method name: behindApplyDoSomething <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param orderId
	 * @param code  <BR>
	 * @throws Exception 
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void behindApplyDoSomething(HttpServletRequest request,String orderId) throws Exception {
		/**
		 * 主要目标：支付后需要将对应的课程信息同步到 课程表 以及课程销售记录表
		 */
		//取得当前支付的订单及购买的商品信息
		MallOrderDetailsPageVo orderDetails = getOrderDetailsById(orderId);
		int orderType = orderDetails.getCommodityType();
		int curOrderId = orderDetails.getId();
		List<MallOrderRelateBean> rbean = orderDetails.getRelateProducts();
		String productIds = "";
		if(orderType == 1){//代表是课程订单
			//同步数据到课程表
			for(MallOrderRelateBean rs : rbean){
				int relateMallCourseId = rs.getRelateId();// 当前的商城课程id
				productIds += relateMallCourseId + ",";
				excuteElse(request, curOrderId, relateMallCourseId);
			}
		}else if(orderType == 2){//专题订单
			for(MallOrderRelateBean rs : rbean){
				int relateMallTopicId = rs.getRelateId();// 当前的商城专题id
				productIds += relateMallTopicId + ",";
				//通过专题id获取 专题关联的课程
				List<MallTopicCourseBean> topicCourseList = mallTopicDaoMapper.getByTopicId(relateMallTopicId);
				for(MallTopicCourseBean topC : topicCourseList){
					int courseId = topC.getMallCourseId();// 专题关联的课程id
					excuteElse(request, curOrderId, courseId);
				}
			}
		}
		productIds = productIds.substring(0,productIds.length()-1);
		
		// 删除对应购物车记录
		String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
		shoppingCartDaoMapper.removeShoppingRecordByIdAndType(userId,productIds,orderType);
		
	}
	private void excuteElse(HttpServletRequest request, int curOrderId, int mallCourseId) throws DataBaseException, Exception {
		MallCourseBean mallCbean = mallCourseDaoMapper.selectByPrimaryKey(mallCourseId);
		if(mallCbean != null){
			BigDecimal price = mallCbean.getPrice();//课程价格
			BigDecimal cheapPrice = mallCbean.getCheapPrice(); // 课程优惠价格
			int courseId = mallCbean.getCourseId();// 获取到课程id
			// 先获取当前的课程信息
			ResCourseBean rcBean = resCourseDaoMapper.getById(courseId);
			int tmpCompanyId = rcBean.getCompanyId();
			int tmpSubCompanyId = rcBean.getSubCompanyId();
			String courseName = rcBean.getName();//课程名称
			ManageUserBean userBean = (ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
			int companyId = 0;
			int subCompanyId = 0;
			if(userBean != null){
				companyId = userBean.getCompanyId();
				subCompanyId = userBean.getSubCompanyId();
				rcBean.setCompanyId(companyId);
				rcBean.setSubCompanyId(subCompanyId);
			}
			rcBean.setMallStatus(3);
			// 同步当前的这条课程数据为用户已购买的课程
			String tmpCode = rcBean.getCode() + UUID.randomUUID();
			rcBean.setCode(tmpCode);
			resCourseDaoMapper.courseSynchronization(rcBean);
			int newCourseId = rcBean.getId();// 返回同步后的课程id
			CourseDetailsPageVo vo = getDetailsByCourseId(courseId+""); // param: 课程id ; return: 当前课程下的所有章节list,章节list中包含章节下对应的课件及测试
			List<ResSectionBeanVo> sectionList = vo.getSectionList(); 
			for(ResSectionBeanVo resSectionBeanVo : sectionList){// 当前课程下的章节
				// 章节表数据录入
				String sectionName = resSectionBeanVo.getName();
				String sectionDescr = resSectionBeanVo.getDescr();
				ResSectionBean addSectionBean = new ResSectionBean();
				addSectionBean.setCourseId(newCourseId);
				addSectionBean.setName(sectionName);
				addSectionBean.setDescr(sectionDescr);
				resSectionDaoMapper.addSection(addSectionBean);
				int newSectionId = addSectionBean.getId(); // 返回新录入的章节id
				//执行课件同步操作
				List<ResCoursewareBean> couserwareList = resSectionBeanVo.getCoursewareList();
				for(ResCoursewareBean couserwareVo: couserwareList){ // 遍历课件信息
					couserwareVo.setCompanyId(companyId); //更换当前的课件为购买人公司
					couserwareVo.setSubCompanyId(subCompanyId);
					resCoursewareDaoMapper.insert(couserwareVo); // 同步课件数据
					int newCoursewareId = couserwareVo.getId(); // 返回新录入的课件id
					resCoursewareDaoMapper.inserRelate(newSectionId,newCoursewareId); // 关系表录入
				}
				//执行测试同步操作
				List<MallSectionExamVo> examList = resSectionBeanVo.getExamList();
				for(MallSectionExamVo examVo: examList){ // 遍历测试信息
					int examDuration = examVo.getDuration();
					int times = examVo.getAllowTimes();
					int passPercent = examVo.getPassScorePercent();
					int examId = examVo.getId(); 
					ResSectionExamBean addSebean = new ResSectionExamBean();
					addSebean.setExamId(examId);
					addSebean.setSectionId(newSectionId);
					addSebean.setExamDuration(examDuration);
					addSebean.setExamTimes(times);
					addSebean.setPassPercent(passPercent);
					resSectionExamDaoMapper.inserRelate(addSebean); // 关系表录入
				}
			}
			//同时需要加入当前课程信息到对应的课程销售记录表中
			MallSellRecordBean msBean = new MallSellRecordBean();
			msBean.setCourseId(mallCourseId);
			msBean.setOrderId(curOrderId);
			msBean.setPrice(cheapPrice);
			msBean.setCourseName(courseName);
			msBean.setStatus(1);
			msBean.setBuyUserId(Integer.parseInt(userBean.getId()));
			if(rcBean != null){
				msBean.setCompanyId(tmpCompanyId);
				msBean.setSubCompanyId(tmpSubCompanyId);
			}
			//新增销售记录数据
			mallSellRecordDaoMapper.insertMallSellRecord(msBean);
		}
	}
	
	/**
	 * 验证当前课程是否已购买
	 */
	@Override
	public int checkCourseIsBuyed(String userId, String mallCourseId,String comId,String subId) throws Exception {
		 // 1代表集团公司已购买过   2代表本身已购买过
		if(subId!=null && !subId.isEmpty()){// 代表当前是子公司在操作，子公司需要判断集团公司是否已购买
			Integer checkResult1 = mallCourseDaoMapper.checkBuyByCompany(mallCourseId,comId);
			if(checkResult1>0){ // 集团公司买过
				return 1;
			}
		}
		int count = mallCourseDaoMapper.checkCourseIsBuyed(userId,mallCourseId);// 测试当前用户是否有购买过
		if(count >0){
			return 2;
		}
		return 0;
	}
	/**
	 * 免费课程直接添加到 已购买课程
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#addAlreadyBuy(java.lang.String, com.jftt.wifi.bean.ManageUserBean) <BR>
	 * Method name: addAlreadyBuy <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param mallCourseId
	 * @param userBean
	 * @throws Exception  <BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addAlreadyBuy(String mallCourseId, ManageUserBean userBean) throws Exception {
		MallCourseBean mallCbean = mallCourseDaoMapper.selectByPrimaryKey(Integer.parseInt(mallCourseId));
		if(mallCbean != null){
			BigDecimal price = mallCbean.getPrice();//课程价格
			BigDecimal cheapPrice = mallCbean.getCheapPrice(); // 课程优惠价格
			int courseId = mallCbean.getCourseId();// 获取到课程id
			// 先获取当前的课程信息
			ResCourseBean rcBean = resCourseDaoMapper.getById(courseId);
			String courseName = rcBean.getName();//课程名称
			int companyId = 0;
			int subCompanyId = 0;
			if(userBean != null){
				companyId = userBean.getCompanyId();
				subCompanyId = userBean.getSubCompanyId();
				rcBean.setCompanyId(companyId);
				rcBean.setSubCompanyId(subCompanyId);
			}
			rcBean.setMallStatus(3);
			// 同步当前的这条课程数据为用户已购买的课程
			String tmpCode = rcBean.getCode() + UUID.randomUUID();
			rcBean.setCode(tmpCode);
			resCourseDaoMapper.courseSynchronization(rcBean);
			int newCourseId = rcBean.getId();// 返回同步后的课程id
			CourseDetailsPageVo vo = getDetailsByCourseId(courseId+""); // param: 课程id ; return: 当前课程下的所有章节list,章节list中包含章节下对应的课件及测试
			List<ResSectionBeanVo> sectionList = vo.getSectionList(); 
			for(ResSectionBeanVo resSectionBeanVo : sectionList){// 当前课程下的章节
				// 章节表数据录入
				String sectionName = resSectionBeanVo.getName();
				String sectionDescr = resSectionBeanVo.getDescr();
				ResSectionBean addSectionBean = new ResSectionBean();
				addSectionBean.setCourseId(newCourseId);
				addSectionBean.setName(sectionName);
				addSectionBean.setDescr(sectionDescr);
				resSectionDaoMapper.addSection(addSectionBean);
				int newSectionId = addSectionBean.getId(); // 返回新录入的章节id
				//执行课件同步操作
				List<ResCoursewareBean> couserwareList = resSectionBeanVo.getCoursewareList();
				for(ResCoursewareBean couserwareVo: couserwareList){ // 遍历课件信息
					couserwareVo.setCompanyId(companyId); //更换当前的课件为购买人公司
					couserwareVo.setSubCompanyId(subCompanyId);
					resCoursewareDaoMapper.insert(couserwareVo); // 同步课件数据
					int newCoursewareId = couserwareVo.getId(); // 返回新录入的课件id
					resCoursewareDaoMapper.inserRelate(newSectionId,newCoursewareId); // 关系表录入
				}
				//执行测试同步操作
				List<MallSectionExamVo> examList = resSectionBeanVo.getExamList();
				for(MallSectionExamVo examVo: examList){ // 遍历测试信息
					int examDuration = examVo.getDuration();
					int times = examVo.getAllowTimes();
					int passPercent = examVo.getPassScorePercent();
					int examId = examVo.getId(); 
					ResSectionExamBean addSebean = new ResSectionExamBean();
					addSebean.setExamId(examId);
					addSebean.setSectionId(newSectionId);
					addSebean.setExamDuration(examDuration);
					addSebean.setExamTimes(times);
					addSebean.setPassPercent(passPercent);
					resSectionExamDaoMapper.inserRelate(addSebean); // 关系表录入
				}
			}
		}
	}
	/**
	 *  判断当前课程该用户所在公司是否已购买
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#judgeIsBuy(java.lang.String, int, int) <BR>
	 * Method name: judgeIsBuy <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param companyId
	 * @param subCompanyId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int judgeIsBuy(String courseId, int companyId, int subCompanyId) throws Exception {
		
		return resCourseDaoMapper.judgeIsBuy(courseId,companyId,subCompanyId);
	}
	
	
	/**
	 *  验证当前专题是否已购买过
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#checkTopicIsBuyed(java.lang.String, java.lang.String) <BR>
	 * Method name: checkTopicIsBuyed <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param userId
	 * @param mallTopicId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public int checkTopicIsBuyed(String userId, String mallTopicId) throws Exception {
		
		return mallOrderDaoMapper.checkTopicIsBuyed(userId,mallTopicId);
	}
	/**
	 * 检测当前用户针对该课程的评价权限  return: type(0:不可评价 1：可评价 2：已评价过)
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#checkUserCanEvaluate(java.lang.String, java.lang.String) <BR>
	 * Method name: checkUserCanEvaluate <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws Exception  <BR>
	 */
	@Override
	public Object checkUserCanEvaluate(String courseId, String userId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		int type = 0;
		String curEvaluate = null;
		// 此处检测是否有购买过
		int count = mallOrderDaoMapper.checkUserCanEvaluate(courseId,userId);
		if(count>0){// 有购买记录存在
			type = 1;
		}
		// 此处检测是否已评价过
		MallCourseBean mbean = mallCourseDaoMapper.selectByPrimaryKey(Integer.parseInt(courseId));
		if(mbean != null){
			int tcourseId = mbean.getCourseId();
			CourseEvaluationBean cBean = courseEvaluationDaoMapper.checkAlreadyEvaluate(userId,tcourseId);
			if(cBean!=null){
				type = 2;
				curEvaluate = JsonUtil.getJsonString4JavaPOJO(cBean);
			}
		}
		result.put("type", type);
		result.put("curEvaluate", curEvaluate);
		return result;
	}
	
	/**
	 * 课程评价
	 * @Override
	 * @see com.jftt.wifi.service.CourseStoreService#giveEvaluate(com.jftt.wifi.bean.CourseEvaluationBean) <BR>
	 * Method name: giveEvaluate <BR>
	 * Description: <BR>
	 * Remark: <BR>
	 * @param courseEvaluationBean  <BR>
	 * @throws Exception 
	 */
	@Override
	public void giveEvaluate(CourseEvaluationBean courseEvaluationBean) throws Exception {
		courseEvaluationDaoMapper.giveEvaluate(courseEvaluationBean);
	}
	/**
	 * 支付宝结算完成后业务处理
	 * Method name: dealwithAfterPay <BR>
	 * Description: dealwithAfterPay <BR>
	 * Remark: <BR>
	 * @param request
	 * @param orderId  void<BR>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void dealwithAfterPay(HttpServletRequest request, String orderId) throws Exception {
		// 更新订单状态为 已付款待发货,更新支付之间
		mallOrderDaoMapper.updateStatusAndPayTime(orderId);
		System.out.println("------------------订单状态更新成功------------------");
		// 同步课程信息
		behindApplyDoSomething(request, orderId);
		System.out.println("------------------同步课程成功------------------");
	}
	
	
	/**chenrui end*/
}
