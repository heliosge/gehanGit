<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员首页</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var realName = '${USER_NAME}';
var headPortrait = '${USER_BEAN.headPhoto}';//头像地址 
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var companyPeopleNum = 0;//公司人数
var courseNum = 0;//课程数量
var examNum = 0;//考试数量
var specialTopicNum = 0;//专题数量

/**
 * 页面加载完毕
 */
$(function(){
	//初始化我的积分、我的学时、待参与的考试
	initMyPoint();
	initMyStudyHours();
	initMyExamCount();
	//初始化精选课程
	initFeatureCourses();
	//初始化公司人数、课程数量、考试数量、专题数量
	initCompanyUserCount();
	initCourseCount();
	initExamCount();
	initSpecialTopicCount();
});
	
/**
 * 初始化我的积分
 */
function initMyPoint(){
	var param = new Object();
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getMyPoint.action',
		success:function(data){
			if(data != null && data != ''){
				$("#myPoint").html('我的积分（'+data+'）');
			}
		}
	});
}

/**
 * 初始化我的学时
 */
function initMyStudyHours(){
	var param = new Object();
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getMyStudyHours.action',
		success:function(data){
			if(data != null && data != ''){
				$("#myStudyHours").html('我的学时（'+data+'）');
			}
		}
	});
}

/**
 * 初始化我的待参与考试数量
 */
function initMyExamCount(){
	var param = new Object();
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getMyExamCount.action',
		success : function(data) {
			if (data != null && data != '') {
				$("#myExamCount").html('待参与的考试（' + data + '）');
			}
		}
	});
}

/**
 * 初始化精选课程
 */
function initFeatureCourses(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getFeatureCourses.action',
		success:function(data){
			if(data != null && data != ''){
				var courses = data;
				var htmlStr = '';
				for(var i = 0; i < courses.length; i++){
					var course = courses[i];
					//每行四个
					if((i+1)%4 == 0){
						htmlStr += '<li class="last">';
					}else{
						htmlStr += '<li>';
					}
					htmlStr += '<div onclick="toCourseDetail('+course.id+');"><a>';
					htmlStr += '<img width="220px" height="134px" src="'+course.frontImage+'" />';
					htmlStr += '</a></div>';
					htmlStr += '<div style="height:15px;text-align:center;margin:5px auto 2px auto;">';
					htmlStr += '<h4>';
					htmlStr += course.name;
					htmlStr += '</h4>';
					htmlStr += '</div>';
					htmlStr += '<div class="course_bottom">';
					htmlStr += '<a onclick="toCourseDetail('+course.id+');"><img src="<%=request.getContextPath()%>/images/img/ico_9.png"/>我要评价</a>';
					htmlStr += '<a class="fr">';
					htmlStr += '平均评分：';
					htmlStr += course.averageScore;
					htmlStr += '</a>';
					htmlStr += '</div>';
					htmlStr += '</li>';
				}
				$("#featureCoursesUl").html(htmlStr);
			}
		}
	});
}

/**
 * 眺望课程详情页面
 */
function toCourseDetail(courseId){
	window.location.href = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+courseId;
}

/**
 * 收藏课程
 */
function collectCourse(courseId){
	//判断课程是否被该学员收藏
	var param = new Object();
	param.courseId = courseId;
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/courseDetailAction/getCourseCollection.action',
		success:function(data){
			if(data){
				alert("该课程已被你收藏");
			}else{
				var innerParam = new Object();
				innerParam.courseId = courseId;
				innerParam.userId = userId;
				innerParam.collectTime = new Date();
				innerParam.updateTime = new Date();
				innerParam.companyId = companyId;
				innerParam.subCompanyId = subCompanyId;
				//保存
				$.ajax({
					type:'POST',
					async:false,//同步
					data:innerParam,
					url:'<%=request.getContextPath()%>/courseDetailAction/collectThisCourse.action',
					success:function(data){
						var thisData = data;
						if(thisData.rtnResult == 'SUCCESS'){
							alert("收藏成功！");
							//刷新精选课程
							initFeatureCourses();
						}else{
							alert("收藏失败...");
						}
					}
				});
			}
		}
	});
}

/**
 * 初始化公司人数
 */
function initCompanyUserCount(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getCompanyUserCount.action',
		success:function(data){
			if(data != null && data != ''){
				companyPeopleNum = data;
			}
		}
	});
}

/**
 * 初始化课程数量
 */
function initCourseCount(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getCourseCount.action',
		success:function(data){
			if(data != null && data != ''){
				courseNum = data;
			}
		}
	});
}

/**
 * 初始化考试数量
 */
function initExamCount(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getExamCount.action',
		success:function(data){
			if(data != null && data != ''){
				examNum = data;
				$("#thirP").html(examNum);
				$("#examNumDesc").html('考试数量');
			}
		}
	});
}

/**
 * 初始化专题数量
 */
function initSpecialTopicCount(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/studentInfoAction/getSpecialTopicCount.action',
		success:function(data){
			if(data != null && data != ''){
				specialTopicNum = data;
			}
		}
	});
}

/**
 * 改变页脚第一个按钮
 */
function changeFirstAClass(){
	$("#firstA").attr("class","thir_a");
	$("#secA").attr("class","sec_a");
	$("#thirA").attr("class","sec_a");
	$("#forthA").attr("class","forth_a");
	//设置参数
	$("#firstP").html(companyPeopleNum);
	$("#companyNumDesc").html('公司人数');
	//其他标签清空
	$("#secP").html('');
	$("#courseNumDesc").html('');
	$("#thirP").html('');
	$("#examNumDesc").html('');
	$("#forthP").html('');
	$("#specialTopicNumDesc").html('');
}

/**
 * 改变页脚第二个按钮
 */
function changeSecAClass(){
	$("#firstA").attr("class","first_a");
	$("#secA").attr("class","thir_a");
	$("#thirA").attr("class","sec_a");
	$("#forthA").attr("class","forth_a");
	//其他标签清空
	$("#firstP").html('');
	$("#companyNumDesc").html('');
	$("#thirP").html('');
	$("#examNumDesc").html('');
	$("#forthP").html('');
	$("#specialTopicNumDesc").html('');
	//设置参数
	$("#secP").html(courseNum);
	$("#courseNumDesc").html('课程数量');
}

/**
 * 改变页脚第三个按钮
 */
function changeThirAClass(){
	$("#firstA").attr("class","first_a");
	$("#secA").attr("class","sec_a");
	$("#thirA").attr("class","thir_a");
	$("#forthA").attr("class","forth_a");
	//其他标签清空
	$("#firstP").html('');
	$("#companyNumDesc").html('');
	$("#secP").html('');
	$("#courseNumDesc").html('');
	$("#forthP").html('');
	$("#specialTopicNumDesc").html('');
	//设置参数
	$("#thirP").html(examNum);
	$("#examNumDesc").html('考试数量');
}

/**
 * 改变页脚第四个按钮
 */
function changeForthAClass(){
	$("#firstA").attr("class","first_a");
	$("#secA").attr("class","sec_a");
	$("#thirA").attr("class","sec_a");
	$("#forthA").attr("class","thir_a");
	//其他标签清空
	$("#firstP").html('');
	$("#companyNumDesc").html('');
	$("#secP").html('');
	$("#courseNumDesc").html('');
	$("#thirP").html('');
	$("#examNumDesc").html('');
	//设置参数
	$("#forthP").html(specialTopicNum);
	$("#specialTopicNumDesc").html('专题数量');
}

/**
 * 跳转到我的考试页面
 */
function toTests(){
	window.location.href = '<%=request.getContextPath()%>/myExamManage/gotoMyExam.action';
}
</script>
</head>
<body>

	<div id="content_i">
		<div class="content_top">
			<div class="pic">
				<img id="headPortrait" src="${USER_BEAN.headPhoto }" title="头像" style="width: 140px;height: 140px;"/>
			</div>
			<div class="info">
				<h3 id="realName">${USER_NAME}</h3>
				<!-- <span>关注0</span> <span class="last">粉丝0</span> -->
				<ul>
					<li id="myPoint" style="cursor:default">我的积分（0）</li>
					<li id="myStudyHours" style="cursor:default">我的学时（0）</li>
					<li><a id="myExamCount" href="javascript:void(0)" onclick="toTests();">待参与的考试（0）</a></li>
					<!-- <li style="cursor:default">待参与的调查（0）</li> -->
				</ul>
			</div>
		</div>
		<!-- <div class="search">
			<h3>搜一搜</h3>
			<input type="text" value="全站搜课程/学习/知识" /> <a href="#" onclick="searchAll();">搜索</a>
		</div> -->
		<%-- <div>
			<iframe style="width:100%;height:380px;border:0px;" scrolling="no" src="<%=request.getContextPath()%>/oam/toBarIndex.action" ></iframe>
		</div> --%>

		<div class="pic_1">
			<div class="course">
				<h3>精选课程</h3>
				<div class="course_list">
					<ul id="featureCoursesUl">
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 统计数据（人数、课程数量、考试数量、专题数量） -->
		<div class="foot_1">
			<a id="firstA" class="first_a" onclick="changeFirstAClass();">
				<p id="firstP" style="text-indent: 1em;"></p>
				<p id="companyNumDesc"></p>
			</a> 
			<a id="secA" class="sec_a" onclick="changeSecAClass();">
				<p id="secP" style="text-indent: 1em;"></p>
				<p id="courseNumDesc"></p>
			</a> 
			<a id="thirA" class="thir_a" onclick="changeThirAClass();">
				<p id="thirP" style="text-indent: 1em;"></p>
				<p id="examNumDesc"></p>
			</a> 
			<a id="forthA" class="forth_a" onclick="changeForthAClass();">
				<p id="forthP" style="text-indent: 1em;"></p>
				<p id="specialTopicNumDesc"></p>
			</a> 
			<!-- <a class="last_a"></a> -->
		</div>
	</div>
</body>
</html>