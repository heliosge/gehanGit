package com.jftt.wifi.dao;

import com.jftt.wifi.bean.MobileAppVersionBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 16-3-18.
 */
public interface MobileAppVersionDaoMapper {

    /**
     * Method name: getById <BR>
     * Description: 根据id获取app版本 <BR>
     * Remark: <BR>
     * @param id
     * @return  MallCourseCategoryBean<BR>
     */
    public MobileAppVersionBean getById(@Param("id")Integer id) throws Exception;


    /**
     * Method name: checkName <BR>
     * Description: 检查重名 <BR>
     * Remark: <BR>
     * @param bean  <BR>
     */
    public Integer checkName(MobileAppVersionBean bean) throws Exception;



    /**
     * Method name: deleteById <BR>
     * Description: 删除<BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
    public Integer deleteById(@Param("id")Integer id) throws Exception;



    /**
     * Method name: update <BR>
     * Description: 修改<BR>
     * Remark: <BR>
     * @param record  void<BR>
     */
    public void update(MobileAppVersionBean record) throws Exception;

    /**
     * Method name: insert<BR>
     * Description: 添加 <BR>
     * Remark: <BR>
     * @param record  void<BR>
     */
    public Integer insert(MobileAppVersionBean record) throws Exception;



    /**
     * Method name: publicVersion <BR>
     * Description: 发布<BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
    public Integer publicVersion(@Param("id")Integer id) throws Exception;


    /**
     * Method name: unPublicVersion <BR>
     * Description: 取消发布<BR>
     * Remark: <BR>
     * @param id  void<BR>
     */
    public Integer unPublicVersion(@Param("id")Integer id) throws Exception;



    /**
     * Method name: checkCode <BR>
     * Description: 检查编号重复 <BR>
     * Remark: <BR>
     * @param bean  void<BR>
     */
    public Integer checkCode(MobileAppVersionBean bean) throws Exception;


    /**
     * Method name: selectCount <BR>
     * Description: 查询数目 <BR>
     * Remark: <BR>
     * @param bean  List<BR>
     */
    public Integer selectCount(MobileAppVersionBean bean) throws Exception;

    /**
     * Method name: selectList <BR>
     * Description: 查询 <BR>
     * Remark: <BR>
     * @return  List<MobileAppVersionBean><BR>
     */
    public List<MobileAppVersionBean> selectList(MobileAppVersionBean bean) throws Exception;

    /**
     * 获取最新版本
     * @return
     * @throws Exception
     */
    public MobileAppVersionBean getNewVersion() throws Exception;
}
