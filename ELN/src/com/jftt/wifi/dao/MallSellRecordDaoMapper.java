package com.jftt.wifi.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.MallSellRecordBean;
import com.jftt.wifi.bean.vo.MallSellRecordVo;

public interface MallSellRecordDaoMapper {

	
/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查记录 <BR>
	 * Remark: <BR>
	 * @param id  MallSellRecordBean<BR>
	 */
	public MallSellRecordBean getById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallSellRecordVo<BR>
	 */
	public MallSellRecordVo getDetailById(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: checkIsBuy <BR>
	 * Description: 检查是否已经购买课程<BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkIsBuy(@Param("courseId")Integer courseId,@Param("userId")Integer userId) throws Exception;
	
	/**
	 * Method name: selectMallSellRecordList <BR>
	 * Description: 查询课程购买记录 <BR>
	 * Remark: <BR>
	 * @param MallSellRecordVo  List<MallSellRecordVo><BR>
	 */
	
	public List<MallSellRecordVo> selectMallSellRecordList(MallSellRecordVo vo)throws Exception;
	
	/**
	 * Method name: selectMallSellRecordCount <BR>
	 * Description: 查询课程购买记录数目 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	
	public Integer selectMallSellRecordCount(MallSellRecordVo vo)throws Exception;
	
	/**
	 * Method name: getTotalMoney <BR>
	 * Description: 查询总金额 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	
	public BigDecimal getTotalMoney(MallSellRecordVo vo)throws Exception;
	
	/**
	 * Method name: getPayMoney <BR>
	 * Description: 查询已付款金额 <BR>
	 * Remark: <BR>
	 * @param vo  Integer<BR>
	 */
	
	public BigDecimal getPayMoney(MallSellRecordVo vo)throws Exception;
	

	/**
	 * Method name: insertMallSellRecord <BR>
	 * Description: 新增记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallSellRecord(MallSellRecordBean bean) throws Exception;

	/**
	 * Method name: updateMallSellRecord <BR>
	 * Description: 修改记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallSellRecord(MallSellRecordBean bean) throws Exception;

	/**
	 * Method name: deleteMallSellRecord <BR>
	 * Description: 删除记录<BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallSellRecord(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: pay <BR>
	 * Description: 付款 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void pay(@Param("id")Integer id,@Param("payUserId")Integer userId,@Param("payUserName")String payUserName) throws Exception;

	
	/**
	 * Method name: gathering <BR>
	 * Description: 收款 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void gathering(@Param("id")Integer id,@Param("gatheringUserId")Integer gatheringUserId,@Param("gatheringUserName")String gatheringUserName) throws Exception;


	

	/** zhangbocheng  end*/
	
}
