package com.jftt.wifi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.jftt.wifi.bean.ManageNoticeBean;
import com.jftt.wifi.bean.vo.ManageNoticeVo;

public interface ManageNoticeDaoMapper {
	
    /**
     * Method name: deleteById <BR>
     * Description: 删除 <BR>
     * Remark: <BR>
     * @param vo
     * @throws Exception  void<BR>
     */
    void deleteById(ManageNoticeVo vo) throws Exception;

    /**
     * Method name: insert <BR>
     * Description: 新增 <BR>
     * Remark: <BR>
     * @param record
     * @throws Exception  void<BR>
     */
    void insert(ManageNoticeBean record) throws Exception;

    /**
     * Method name: select <BR>
     * Description: 查询 <BR>
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
	 * Method name: updateNoticeReadStatus <BR>
	 * Description: 修改状态 <BR>
	 * Remark: <BR>
	 * @param id
	 * @throws Exception  void<BR>
	 */
	void updateNoticeReadStatus(@Param(value="id")int id) throws Exception;

	/**
	 * Method name: selectSendNoticeCount <BR>
	 * Description: 查询发件箱数量 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  int<BR>
	 */
	int selectSendNoticeCount(ManageNoticeVo vo);

	/**
	 * Method name: selectSendNotice <BR>
	 * Description: 查询发件箱 <BR>
	 * Remark: <BR>
	 * @param vo
	 * @return  List<ManageNoticeBean><BR>
	 */
	List<ManageNoticeBean> selectSendNotice(ManageNoticeVo vo);

}