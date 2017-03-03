package com.jftt.wifi.service;

import java.util.List;

import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.vo.ManageNoticeVo;

/**
 * class ManageNoticeService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015年8月5日
 * @author JFTT)Lu Yunlong
 */
public interface ManageNoticeService {
	
	/**
	 * Method name: insertNotice <BR>
	 * Description: 新增消息 <BR>
	 * Remark: <BR>
	 * @param bean
	 * @throws Exception  void<BR>
	 */
	public void insertNotice(ManageNoticeBean bean) throws Exception;
	
	/**
	 * Method name: deleteNotice <BR>
	 * Description: 删除消息 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	public void deleteNotice(ManageNoticeVo id) throws Exception;
	
	  /**
	 * Method name: select <BR>
	 * Description: 查询站内信 <BR>
	 * Remark: <BR>
	 * @param param
	 * @return
	 * @throws Exception  List<ManageNoticeBean><BR>
	 */
	List<ManageNoticeBean> select(ManageNoticeVo param) throws Exception;
	    
	    /**
	     * Method name: selectCount <BR>
	     * Description: 查询数量 <BR>
	     * Remark: <BR>
	     * @param param
	     * @return
	     * @throws Exception  int<BR>
	     */
	 int selectCount(ManageNoticeVo param) throws Exception;

		/**
		 * Method name: selectById <BR>
		 * Description: 根据id查询站内信 <BR>
		 * Remark: <BR>
		 * @param id
		 * @return
		 * @throws Exception  ManageNoticeBean<BR>
		 */
		ManageNoticeBean selectById(Integer id) throws Exception;

		/**
		 * Method name: updateNoticeReadStatus <BR>
		 * Description: 修改站内信读取状态 <BR>
		 * Remark: <BR>
		 * @param id  void<BR>
		 */
		public void updateNoticeReadStatus(int id) throws Exception ;

		/**
		 * Method name: selectSendNoticeCount <BR>
		 * Description: 查询发件箱数量 <BR>
		 * Remark: <BR>
		 * @param vo
		 * @return  int<BR>
		 */
		public int selectSendNoticeCount(ManageNoticeVo vo);

		/**
		 * Method name: selectSendNotice <BR>
		 * Description: 查询发件箱 <BR>
		 * Remark: <BR>
		 * @param vo
		 * @return  List<ManageNoticeBean><BR>
		 */
		public List<ManageNoticeBean> selectSendNotice(ManageNoticeVo vo);
	

}
