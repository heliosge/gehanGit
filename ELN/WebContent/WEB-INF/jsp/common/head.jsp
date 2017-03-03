<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script>
<title>参与考试</title>
</head>
<body>

	<div id="header_i">
		<div class="head_i">
			<ul id="nav">
				<li><a
					href="<%=request.getContextPath()%>/studentInfoAction/toStudentIndex.action"><span>首页</span></a>
					<ul></ul></li>
				<li><a
					href="<%=request.getContextPath()%>/courseUserAction/toAllCourses.action"><span>课程</span></a>
					<ul>
						<a href="<%=request.getContextPath()%>/courseUserAction/toAllCourses.action">选课中心</a>
						<a href="<%=request.getContextPath()%>/courseUserAction/toMyCourses.action">我的课程</a>
						<a href="<%=request.getContextPath()%>/courseNoteAction/toCourseNote.action">我的笔记</a>
						<a href="<%=request.getContextPath()%>/courseQuestionAction/toMyQuestions.action">我的问答</a>
						<a href="<%=request.getContextPath()%>/courseCollectionAction/toMyCollection.action">我的收藏</a>
						<a href="<%=request.getContextPath()%>/studentLearnMapAction/toMyMap.action">我的地图</a>
						<a href="<%=request.getContextPath()%>/studentLearnPlanAction/toLearnPlan.action">学习计划</a>
						<a href="<%=request.getContextPath()%>/studentGroupAction/toJoinGroup.action">加入群组</a>
					</ul></li>
				<li class="focus"><a href="join_exam.html"><span>考试</span></a>
					<ul>
						<a href="<%=request.getContextPath()%>/myExamManage/gotoMyExam.action"
							class="active">参与考试</a>
						<a href="<%=request.getContextPath()%>/myExamManage/gotoWrongCard.action">错题集</a>
						<a href="<%=request.getContextPath()%>/myExamManage/gotoExamSimulate.action">模拟考试</a>
					</ul></li>
				<li><a href="#"><span>培训</span></a>
					<ul></ul></li>
				<li><a href="#"><span>问卷</span></a>
					<ul></ul></li>
				<li><a href="#"><span>更多</span><img
						src="<%=request.getContextPath()%>/images/img/ico_5.png" /></a>
					<ul>
						<a href="<%=request.getContextPath() %>/MyMegagame/toMyCompetition.action" class="active">我的比赛</a>
						<a href="<%=request.getContextPath() %>/MyMegagame/toMegagameCheck.action">批阅大赛</a>
						<a href="<%=request.getContextPath() %>/MyMegagame/toMyApplications.action">我的报名</a>
					</ul>
				</li>

			</ul>
			<div class="tools fr">
				<a href="#" class="tool_1"></a> <a
					href="<%=request.getContextPath()%>/login/loginPage.action" class="tool_2"></a> <a
					href="#" class="tool_3"></a>
			</div>
		</div>
	</div>
</body>
</html>