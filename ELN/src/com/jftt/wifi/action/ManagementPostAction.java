/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: PostManagementAction.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-8-12        | JFTT)hetianrui    | original version
 */
package com.jftt.wifi.action;

        import java.util.List;

        import javax.annotation.Resource;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;

        import org.apache.log4j.Logger;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

        import com.jftt.wifi.bean.MMGridPageVoBean;
        import com.jftt.wifi.bean.ManageUserBean;
        import com.jftt.wifi.bean.ResCourseBean;
        import com.jftt.wifi.bean.exam.vo.QuestionListItemVo;
        import com.jftt.wifi.bean.vo.CoursePromotionPath;
        import com.jftt.wifi.bean.vo.CourseVo;
        import com.jftt.wifi.bean.vo.PostCourseVo;
        import com.jftt.wifi.bean.vo.PostVo;
        import com.jftt.wifi.common.Constant;
        import com.jftt.wifi.service.ManageUserService;
        import com.jftt.wifi.service.ManagementPostService;
        import com.jftt.wifi.service.ResService;
        import com.jftt.wifi.util.Pagination;

/**
 * class name:PostManagementAction <BR>
 * class description: 岗位体系管理 <BR>
 * Remark: <BR>
 * @version 1.00 2015-8-12
 * @author JFTT)HeTianrui
 */
@Controller
@RequestMapping(value="post")
public class ManagementPostAction {

    private static Logger logger = Logger.getLogger(ManagementPostAction.class);

    @Resource
    private ManagementPostService postManagementServ;

    @Resource(name="resService")
    private ResService resService;

    @Resource(name="manageUserService")
    private ManageUserService manageUserService;

    /**
     * Method name: gotoDeptIndex <BR>
     * Description: 岗位体系管理 <BR>
     * Remark: <BR>
     * @return  String<BR>
     */
    @RequestMapping("/toManage")
    public String gotoDeptIndex() {
        return "learnMap/postManage";
    }
    /**
     * Method name: queryPromotionPathByCondition <BR>
     * Description: 默认晋升晋升路径 <BR>
     * Remark: <BR>
     * @param path
     * @param model
     * @param request
     * @return  String<BR>
     */
    @RequestMapping("/prompath/list")
    public String queryPromotionPathByCondition(@ModelAttribute("path")CoursePromotionPath path,ModelMap model,HttpServletRequest request){
        try {
            ManageUserBean userBean =(ManageUserBean) request.getSession().getAttribute(Constant.SESSION_USERBEAN);
            logger.info("USERID=/="+userBean.getId()+"====Go queryPromotionPathByCondition====");
            if(null!=userBean){
                path.setCompanyId(userBean.getCompanyId());
                Pagination<CoursePromotionPath> page = postManagementServ.queryPromotionPathByCondition(path);
                model.addAttribute("page", page);
            }
        } catch (Exception e) {
            logger.error("查询晋升路径异常!", e);
        }
        return "post/prompath_list";
    }
    /**
     * Method name: queryCourseBycategoryId <BR>
     * Description: 根据课程体系查找课程 <BR>
     * Remark: <BR>
     * @return  String<BR>
     */
    @RequestMapping(value="courseList")
    @ResponseBody
    public Object queryCourseBycategoryId(PostCourseVo postCourseVo,HttpServletRequest request){
        try {
            String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
            logger.info("USERID=/="+userId+"====Go queryCourseBycategoryId====");
            Pagination<PostCourseVo> page = postManagementServ.queryCourseBycondition(postCourseVo);
            return page;
        } catch (Exception e) {
            logger.error("查找课程异常!", e);
        }
        return Constant.AJAX_FAIL;
    }
    /**
     * Method name: addPostCourseRelation <BR>
     * Description: 新增岗位关联课程关系 <BR>
     * Remark: <BR>
     * @param request  void<BR>
     */
    @RequestMapping(value="/addPostCourse")
    @ResponseBody
    public String addPostCourseRelation(@RequestBody PostVo post,HttpServletRequest request)
    {
        String result = Constant.AJAX_SUCCESS;
        try {
            String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
            logger.info("USERID=/="+userId+"====Go addPostCourseRelation====");
            logger.info(post.getPostId());
            postManagementServ.addPostCourseRelation(post);
        } catch (Exception e) {
            result = Constant.AJAX_FAIL;
            logger.error("新增岗位关联课程异常!", e);
        }
        return result;
    }
    /**
     * Method name: delPostCourseRelation <BR>
     * Description: 删除岗位关联课程 <BR>
     * Remark: <BR>
     * @param request  <BR>
     */
    @RequestMapping(value="delete/postcourse")
    @ResponseBody
    public Object delPostCourseRelation(HttpServletRequest request)
    {
        String result = Constant.AJAX_SUCCESS;
        try {
            String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
            logger.info("USERID=/="+userId+"====Go delPostCourseRelation====");
            String ids=request.getParameter("ids");
            boolean r=postManagementServ.delPostCourseRelation(ids);
            if(!r){
                return false;
            }
        } catch (Exception e) {
            result = Constant.AJAX_FAIL;
            logger.error("删除岗位关联课程异常!", e);
        }
        return result;
    }
    /**
     * Method name: showPostCourseRelation <BR>
     * Description: 查找岗位关联课程 <BR>
     * Remark: <BR>
     * @param postCourseVo
     * @param model
     * @param request  <BR>
     */
    @RequestMapping(value="/postcourse/list")
    public Object showPostCourseRelation(@ModelAttribute("course")PostCourseVo postCourseVo,ModelMap model,HttpServletRequest request)
    {
        try {
            String userId = (String) request.getSession().getAttribute(Constant.SESSION_USERID_LONG);
            logger.info("USERID=/="+userId+"====Go queryCourseBycategoryId====");
            Pagination<PostCourseVo> page = postManagementServ.queryCourseBycondition(postCourseVo);
            List<PostCourseVo> list =page.getList();
            MMGridPageVoBean<PostCourseVo> re = new MMGridPageVoBean<PostCourseVo>();
            re.setTotal(page.getTotalRows());
            re.setRows(list);
            return re;
        } catch (Exception e) {
            logger.error("查找岗位关联课程异常!", e);
        }
        return Constant.AJAX_FAIL;
    }


    /**
     *
     * @return
     */
    @RequestMapping(value="listCourse")
    public String listCourse(){
        return "learnMap/listCourse";
    }


    /**
     * Method name: selectResCourseList <BR>
     * Description: 查询课程 <BR>
     * Remark: <BR>
     * @param request
     * @param vo
     * @return  Object<BR>
     */
    @RequestMapping("selectResCourseList")
    @ResponseBody
    public Object selectResCourseList(HttpServletRequest request, CourseVo vo){
        MMGridPageVoBean<ResCourseBean> re = new MMGridPageVoBean<ResCourseBean>();
        try {
            HttpSession session = request.getSession();
            String id = (String) session.getAttribute(Constant.SESSION_USERID_LONG);
            ManageUserBean user = manageUserService.findUserById(id);
            vo.setCompanyId(user.getCompanyId());
            vo.setSubCompanyId(user.getSubCompanyId());
            vo.setCategoryIds(request.getParameterValues("categoryId[]"));
            vo.setTypeIds(request.getParameterValues("typeId[]"));
            vo.setFromNum((vo.getPage() - 1) * vo.getPageSize());
            List<ResCourseBean> result = resService.selectCourseListByPostId(vo);
            int size = resService.selectCourseListCountByPostId(vo);
            re.setRows(result);
            re.setTotal(size);
            return re;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
