<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习计划详情</title>
<!-- 引入页面style-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var learnPlanId = '${learnPlanId}';//学习计划id
	
/**
 * 页面加载完毕
 */
$(function(){
	//初始化学习计划详情
	initLearnPlanDetails();
});
	
/**
 * 初始化学习计划详情
 */
function initLearnPlanDetails(){
	//查询参数
	var param = new Object();
	param.learnPlanId = learnPlanId;
	param.userId = userId;
	
	$.ajax({
		type:'POST',
		async:true,
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnPlanAction/getLearnPlanStages.action',
		success:function(data){
			var htmlStr = '';
			if(data != null && data != ''){
				var stages = data;
				var allCompleted = true;//学习计划状态
				for(var i = 0; i < stages.length; i++){
					var stage = stages[i];
					if(stage.process != 100){
						allCompleted = false;
					}
					htmlStr += '<div class="plan_1">';
					htmlStr += '<h4>'+stage.name+'</h4>';
					htmlStr += '<div class="pl_jd">';
					htmlStr += '<span>阶段'+(i+1)+'</span>';
					htmlStr += '<em style="margin-left:85px;">学习进度<strong>'+stage.process+'%</strong></em>';
					htmlStr += '<a onclick="slideToggleOfStage('+stage.id+');">';
					htmlStr += '<img src="<%=request.getContextPath()%>/images/img/btn_1.png" />';
					htmlStr += '</a>';
					htmlStr += '</div>';
					
					//根据该学习计划阶段查询所有课程
					var innerParam = new Object();
					innerParam.learnPlanStageId = stage.id;
					innerParam.userId = userId;
					$.ajax({
						type:'POST',
						async:false,//同步，必须先查出来
						data:innerParam,
						url:'<%=request.getContextPath()%>/studentLearnPlanAction/getLearnPlanStageCourses.action',
						success:function(data){
							if(data != null && data != ''){
								htmlStr += '<div id="odiv_'+stage.id+'">';
								htmlStr += '<table width="1000px;">';
								var stageCourses = data;
								for(var j = 0; j < stageCourses.length; j++){
									var stageCourse = stageCourses[j];
									
									if(stageCourse.name== null){
										stageCourse.name = "";
									}
									
									htmlStr += '<tr>';
									//htmlStr += '<td width="150px;" height="50px;">'+(j+1)+'、'+stageCourse.code+'</td>';
									htmlStr += '<td width="300px;" height="50px;">'+(j+1)+'、'+stageCourse.name+'</td>';
									htmlStr += '<td width="400px;" height="50px;">';
									if(stageCourse.beginTime != null && stageCourse.beginTime != ''){
										htmlStr += getSmpFormatDateByLong(stageCourse.beginTime);
									}else{
										htmlStr += '&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									htmlStr += ' <strong>~</strong> ';
									if(stageCourse.endTime != null && stageCourse.endTime != ''){
										htmlStr += getSmpFormatDateByLong(stageCourse.endTime);
									}else{
										htmlStr += '&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									htmlStr += '</td>';
									htmlStr += '<td>';
									htmlStr += '<a href="javascript:void(0)" onclick="toCourseDetail('+stageCourse.id+');">';
									if(stageCourse.learnProcess == null || stageCourse.learnProcess == ''){
										htmlStr += '开始学习';
									}else if(stageCourse.learnProcess == 1){
										htmlStr += '继续学习';
									}else if(stageCourse.learnProcess == 2){
										htmlStr += '学习结束';
									}
									htmlStr += '</a>';
									htmlStr += '</td>'
									htmlStr += '</tr>';
								}
								htmlStr += '</table>';
								htmlStr += '</div>';
							}
						}
					});
					
					htmlStr += '</div>';
				}
				$("#course_all").append(htmlStr);
				if(allCompleted == true){
					//修改学习计划状态
					var planParam = new Object();
					planParam.learnPlanId = learnPlanId;
					planParam.userId = userId;
					$.ajax({
						type:'POST',
						async:false,//同步，必须先查出来
						data:planParam,
						url:'<%=request.getContextPath()%>/studentLearnPlanAction/updateLearnPlanStatus.action',
						success:function(data){
							//不做任何处理
						}
					});
				}
			}
		}
	});
	
}

/**
 * div隐藏和显示
 */
function slideToggleOfStage(stageId){
	$('#odiv_'+stageId+'').slideToggle();
}

/**
 * 跳往课程详情页面
 */
function toCourseDetail(courseId){
	window.location.href = '<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+courseId;
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="plan">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学习计划详情</span>
			</div>
			<!-- <h3>学习计划</h3> -->
		</div>
	</div>
</body>
</html>