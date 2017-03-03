/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamAssignInfoConditionBean.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015-7-29        | JFTT)zhangchen    | original version
 */
package com.jftt.wifi.bean;

import java.util.List;

/**
 * @author JFTT)zhangchen
 * class name:ExamAssignInfoConditionBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2015-7-29
 */
public class ExamQueryConditionBean {
	private int id;//考试信息ID
	private int exam_type;//考试种类：1普通考试，2赛程考试，3模拟考试，4学习地图测试，5课程章节测试（实际由课程部分管理），6培训通过考试
	private int exam_status;//考试状态1：未开始2：未提交3：已提交4：已批阅
	private String title;//考试名称
	private String begin_time;//考试开始时间
	private String end_time;//考试结束时间
	private Integer is_passed;//是否通过 0-未通过 1-已通过
	private int user_id;//用户ID
	private int relation_id;//考试信息ID
	private int is_guoqi;//考试是否过期 0-未过期 1-已过期
	private String content;//题干
	private int type;//题型：1主观题，2单选题，3多选题，4判断题，5填空题，6组合题
	private int is_multimedia;//是否为多媒体题：1：是，0：不是
	private int question_id;//问题ID
	private int exam_assign_id;//考试记录ID
	private int duration;//考试时长
	private int submit_duration;//允许最快交卷时间
	private int is_auto_marking;//是否自动批阅(包含主观题时是否自动批阅)1：是，0：不是
	private int display_mode;//试卷显示方式1  整卷显示2  单题可逆3  单题不可逆
	private String option_id;//选项ID
	private int is_score_public;//成绩是否公开：1：是，0：否
	private int organizing_mode;//1：标准组卷 2：随机组卷
	private int paper_id;//试卷ID
	private int is_anti_cheating;//是否开启防作弊
	private int allow_leave_times;//允许离开考试次数
	private int is_published;//是否发布
	private int is_answer_public;//答案是否公开
	private int is_marked;//考试是否批阅完成
	private int remaining_time;//考试剩余时间
	private String wrongType;//错题类型1、正式考试 2、模拟考试 3、练习
	//用户信息
	private String name;//用户姓名
	private String department;//部门
	private String post;//职位
	private List<ManageUserBean> userList;
	
	private Integer companyId;
	
	private int course_recorder_id;//课程记录ID
	//分页参数
	private int pageSize;//页面总数
	private int fromNum;//起始

	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getWrongType() {
		return wrongType;
	}
	public void setWrongType(String wrongType) {
		this.wrongType = wrongType;
	}
	public int getRemaining_time() {
		return remaining_time;
	}
	public void setRemaining_time(int remaining_time) {
		this.remaining_time = remaining_time;
	}
	public int getCourse_recorder_id() {
		return course_recorder_id;
	}
	public void setCourse_recorder_id(int course_recorder_id) {
		this.course_recorder_id = course_recorder_id;
	}
	public int getExam_status() {
		return exam_status;
	}
	public void setExam_status(int exam_status) {
		this.exam_status = exam_status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Integer getIs_passed() {
		return is_passed;
	}
	public void setIs_passed(Integer is_passed) {
		this.is_passed = is_passed;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}
	public int getIs_guoqi() {
		return is_guoqi;
	}
	public void setIs_guoqi(int is_guoqi) {
		this.is_guoqi = is_guoqi;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExam_assign_id() {
		return exam_assign_id;
	}
	public void setExam_assign_id(int exam_assign_id) {
		this.exam_assign_id = exam_assign_id;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFromNum() {
		return fromNum;
	}
	public void setFromNum(int fromNum) {
		this.fromNum = fromNum;
	}
	public int getIs_multimedia() {
		return is_multimedia;
	}
	public void setIs_multimedia(int is_multimedia) {
		this.is_multimedia = is_multimedia;
	}
	public int getExam_type() {
		return exam_type;
	}
	public void setExam_type(int exam_type) {
		this.exam_type = exam_type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getIs_auto_marking() {
		return is_auto_marking;
	}
	public void setIs_auto_marking(int is_auto_marking) {
		this.is_auto_marking = is_auto_marking;
	}
	public int getDisplay_mode() {
		return display_mode;
	}
	public void setDisplay_mode(int display_mode) {
		this.display_mode = display_mode;
	}
	public String getOption_id() {
		return option_id;
	}
	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}
	public int getIs_score_public() {
		return is_score_public;
	}
	public void setIs_score_public(int is_score_public) {
		this.is_score_public = is_score_public;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public List<ManageUserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<ManageUserBean> userList) {
		this.userList = userList;
	}
	public int getOrganizing_mode() {
		return organizing_mode;
	}
	public void setOrganizing_mode(int organizing_mode) {
		this.organizing_mode = organizing_mode;
	}
	public int getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}
	public int getIs_anti_cheating() {
		return is_anti_cheating;
	}
	public void setIs_anti_cheating(int is_anti_cheating) {
		this.is_anti_cheating = is_anti_cheating;
	}
	public int getAllow_leave_times() {
		return allow_leave_times;
	}
	public void setAllow_leave_times(int allow_leave_times) {
		this.allow_leave_times = allow_leave_times;
	}
	public int getIs_published() {
		return is_published;
	}
	public void setIs_published(int is_published) {
		this.is_published = is_published;
	}
	public int getIs_answer_public() {
		return is_answer_public;
	}
	public void setIs_answer_public(int is_answer_public) {
		this.is_answer_public = is_answer_public;
	}
	public int getIs_marked() {
		return is_marked;
	}
	public void setIs_marked(int is_marked) {
		this.is_marked = is_marked;
	}
	public int getSubmit_duration() {
		return submit_duration;
	}
	public void setSubmit_duration(int submit_duration) {
		this.submit_duration = submit_duration;
	}
	
}
