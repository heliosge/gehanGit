package com.jftt.wifi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jftt.wifi.bean.MallCourseApproveBean;
import com.jftt.wifi.bean.vo.MallCourseApproveVo;


public interface MallCourseApproveDaoMapper {
	
/** zhangbocheng  start*/
	
	
	/**
	 * Method name: getById <BR>
	 * Description: 根据id查审核记录 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveBean<BR>
	 */
	public MallCourseApproveBean getById(@Param("id")Integer id) throws Exception;

	
	/**
	 * Method name: getDetailById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveVo<BR>
	 */
	public MallCourseApproveVo getDetailById(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: getDetailListById <BR>
	 * Description: 根据id查询记录详情 <BR>
	 * Remark: <BR>
	 * @param id  MallCourseApproveVo<BR>
	 */
	public List<MallCourseApproveVo> getDetailListById(@Param("id")Integer id) throws Exception;

	/**
	 * Method name: checkMallCourseApprove <BR>
	 * Description: 检查审核记录是否重复 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer checkMallCourseApprove(MallCourseApproveBean bean) throws Exception;
	
	/**
	 * Method name: insertMallCourseApprove <BR>
	 * Description: 新增审核记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public Integer insertMallCourseApprove(MallCourseApproveBean bean) throws Exception;

	/**
	 * Method name: updateMallCourseApprove <BR>
	 * Description: 修改审核记录 <BR>
	 * Remark: <BR>
	 * @param bean  void<BR>
	 */
	public void updateMallCourseApprove(MallCourseApproveBean bean) throws Exception;

	/**
	 * Method name: deleteMallCourseApprove <BR>
	 * Description: 删除审核记录 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void deleteMallCourseApprove(@Param("id")Integer id) throws Exception;

	

	
	/**
	 * Method name: doCheck <BR>
	 * Description: 审核 <BR>
	 * Remark: <BR>
	 * @param id  void<BR>
	 */
	public void doCheck(MallCourseApproveBean bean) throws Exception;

	
	/**
	 * Method name: selectMallCourseApproveList <BR>
	 * Description: 查询审核记录list <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public List<MallCourseApproveVo> selectMallCourseApproveList(MallCourseApproveVo vo) throws Exception;
	
	/**
	 * Method name: selectMallCourseApproveCount <BR>
	 * Description: 查询记录数目 <BR>
	 * Remark: <BR>
	 * @param vo  List<BR>
	 */
	public Integer selectMallCourseApproveCount(MallCourseApproveVo vo) throws Exception;
	
	
	
	
	/** zhangbocheng  end*/

}
