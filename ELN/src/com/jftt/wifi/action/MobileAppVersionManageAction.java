package com.jftt.wifi.action;

import ch.ethz.ssh2.Session;
import com.jftt.wifi.bean.ManageUserBean;
import com.jftt.wifi.bean.MobileAppVersionBean;
import com.jftt.wifi.bean.MMGridPageVoBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.service.MobileAppVersionManageService;
import com.jftt.wifi.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 移动端版本管理
 * Created by Administrator on 16-3-18.
 */
@Controller
@RequestMapping("mobileAppVersion")
public class MobileAppVersionManageAction {

    private static Logger log = Logger.getLogger(MobileAppVersionManageAction.class);

    @Resource(name="mobileAppVersionManageService")
    private MobileAppVersionManageService mobileAppVersionManageService;


    /**
     * 跳转到管理页
     * @param request
     * @return
     */
    @RequestMapping("toManagePage")
    public String appVersionManagePage(HttpServletRequest request){

        return "mobileAppVersion/mobileAppVersionList";
    }

    /**
     * 跳转到版本详情页
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("toDetailPage")
    public String toAppVersionDetailPage(HttpServletRequest request,Integer id){
        try {
            MobileAppVersionBean bean =         mobileAppVersionManageService.getById(id);
            request.setAttribute("mobileAppVersion", bean);
        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            request.setAttribute("mobileAppVersion",null);
        }
        return "mobileAppVersion/mobileAppVersionDetail";
    }

    /**
     * 跳转到新建或修改版本
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("toEditPage")
    public String toAppVersionEditPage(HttpServletRequest request,Integer id){
        try {
            if(id!=null){
                MobileAppVersionBean bean =         mobileAppVersionManageService.getById(id);
                request.setAttribute("mobileAppVersion", JsonUtil.getJson4JavaObject(bean));
            }else {
                request.setAttribute("mobileAppVersion",JsonUtil.getJson4JavaObject(null));
            }

        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
        }
        return "mobileAppVersion/mobileAppVersionEdit";
    }


    /**
     * 查询移动端app版本列表
     * @param request
     * @param bean
     * @return
     */

    @RequestMapping("selectList")
    @ResponseBody
    public Object selectList (HttpServletRequest request , MobileAppVersionBean bean){
        MMGridPageVoBean<MobileAppVersionBean> re = new MMGridPageVoBean<MobileAppVersionBean>();
        try{
            log.info(bean);
            log.info(bean.getName());
            log.info(bean.getCode());
            bean.setPageIndex((bean.getPageIndex()-1)*bean.getPageSize());
            List<MobileAppVersionBean> list = mobileAppVersionManageService.selectList(bean);
            Integer count = mobileAppVersionManageService.selectCount(bean);
            re.setRows(list);
            re.setTotal( count);

        }catch (Exception e){
            log.warn(MobileAppVersionManageAction.class, e);
        }
        return re;
    }

    /**
     * Method name: checkName <BR>
     * Description: 检查重名<BR>
     * Remark: <BR>
     * @param request
     * @param bean
     * @return  Object<BR>
     */
    @RequestMapping(value="checkName")
    @ResponseBody
    public Object checkName(HttpServletRequest request, MobileAppVersionBean bean){

        try {

            int size = mobileAppVersionManageService.checkName(bean);
            if(size<=0){
                return Constant.AJAX_SUCCESS;
            }else{
                return Constant.AJAX_FAIL;
            }
        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            return Constant.AJAX_FAIL;
        }
    }

    /**
     * Method name: checkCode <BR>
     * Description: 检查编号是否重复<BR>
     * Remark: <BR>
     * @param request
     * @param bean
     * @return  Object<BR>
     */
    @RequestMapping(value="checkCode")
    @ResponseBody
    public Object checkCode(HttpServletRequest request, MobileAppVersionBean bean){

        try {

            int size = mobileAppVersionManageService.checkCode(bean);
            if(size<=0){
                return Constant.AJAX_SUCCESS;
            }else{
                return Constant.AJAX_FAIL;
            }
        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            return Constant.AJAX_FAIL;
        }
    }

    /**
     * 上传文件  s
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("upload")
    @ResponseBody
    public String uploadFile(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file){
        String uploadResult = null;
        try {

            uploadResult =mobileAppVersionManageService.uploadFile(file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("recordCode", -1);
            return param.toString();
        }

        return uploadResult;//d.getswfPath();
    }

    /**
     * Method name: insert <BR>
     * Description: 新建版本<BR>
     * Remark: <BR>
     * @param request
     * @param bean
     * @return  Object<BR>
     */
    @RequestMapping(value="insert")
    @ResponseBody
    public Object insert(HttpServletRequest request,  @RequestBody MobileAppVersionBean bean){

        try {
            HttpSession session = request.getSession();
            ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
            if(user==null||user.getCompanyId()==null){
                return Constant.AJAX_FAIL;
            }

            bean.setType(0);
            bean.setCreateUserId(Integer.parseInt(user.getId()));
            mobileAppVersionManageService.insert(bean);
                return Constant.AJAX_SUCCESS;

        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            return Constant.AJAX_FAIL;
        }
    }


    /**
     * Method name: update <BR>
     * Description: 修改版本<BR>
     * Remark: <BR>
     * @param request
     * @param bean
     * @return  Object<BR>
     */
    @RequestMapping(value="update")
    @ResponseBody
    public Object update(HttpServletRequest request, @RequestBody  MobileAppVersionBean bean){

        try {
            HttpSession session = request.getSession();
            ManageUserBean user = (ManageUserBean)session.getAttribute(Constant.SESSION_USERBEAN);
            if(user==null||user.getCompanyId()==null){
                return Constant.AJAX_FAIL;
            }
            bean.setUpdateUserId(Integer.parseInt(user.getId()));
            mobileAppVersionManageService.update(bean);
            return Constant.AJAX_SUCCESS;

        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            return Constant.AJAX_FAIL;
        }
    }

    /**
     * Method name: deleteById <BR>
     * Description: 删除版本<BR>
     * Remark: <BR>
     * @param request
     * @param
     * @return  Object<BR>
     */
    @RequestMapping(value="deleteById")
    @ResponseBody
    public Object deleteById(HttpServletRequest request){

        try {
            String[] ids = request.getParameterValues("ids");
            for(String id : ids){

                mobileAppVersionManageService.deleteById(Integer.parseInt(id));
            }

            return Constant.AJAX_SUCCESS;

        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            return Constant.AJAX_FAIL;
        }
    }

    /**
     * Method name: publicById <BR>
     * Description: 发布版本<BR>
     * Remark: <BR>
     * @param request
     * @param id
     * @return  Object<BR>
     */
    @RequestMapping(value="publicById")
    @ResponseBody
    public Object publicById(HttpServletRequest request, Integer id){

        try {

            mobileAppVersionManageService.publicVersion(id);
            return Constant.AJAX_SUCCESS;

        } catch (Exception e) {
            log.warn(MobileAppVersionManageAction.class, e);
            return Constant.AJAX_FAIL;
        }
    }


}
