<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位详情</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

#postDescr{width: 1046px;min-height: 44px;height: auto;margin-top: 10px;margin-left: 10px;font-size: 14px;
text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var postId = '${thisPostId}';//岗位id
var itemsPerPage = 5;//每页显示条数
var orderNum = '${orderNum}';//当前阶段序列
var stageId = '${stageId}';//当前阶段id
	
/**
 * 页面加载完毕
 */
$(function(){
	//初始化岗位相关
	initPostDetail();
	//初始化阶段测试
	initStageTest();
	//初始化岗位课程
	initPostCourses();
});
	
/**
 * 初始化岗位相关
 */
function initPostDetail(){
	$.ajax({
		type:'POST',
		async:true,//默认true
		data:{"postId":postId},
		url:'<%=request.getContextPath()%>/studentLearnMapAction/getPostById.action',
		success:function(data){
			if(data){
				$("#postH4").html(data.name);
				$("#postDescr").html(data.descr);
				$("#postDescr").attr("title",data.descr);
			}
		}
	});
}
	
/**
 * 初始化阶段测试
 */
function initStageTest(){
	var param = new Object();
	param.stageId = stageId;
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnMapAction/getStageTest.action',
		success:function(data){
			if(data != null && data != ''){
				$("#stageTestTitle").html('【'+data.title+'】');
				$("#passScore").html('及格分：'+data.passScore+'');
				$("#thisScore").html('考试分：'+data.thisScore+'');
				if(data.isPassed == null || data.isPassed == '' || data.isPassed == 0){
					$("#isPassed").html('未通过');
				}else if(data.isPassed == 1){
					$("#isPassed").html('已通过');
				}
				$("#toStageTestLink").click(function(){
					toStageTest(userId,data.id);
				});
			}
		}
	});
}

/**
 * 跳往阶段测试（此处examId为考试id，不是试卷id）
 */
function toStageTest(userId,examId){
	var param = new Object();
	param.examId = examId;
	param.userId = userId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnMapAction/saveAssignInfo.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				window.open("<%=request.getContextPath() %>/myExamManage/gotoMatchTest.action?id="+examId,"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
			}else{
				alert("保存学习地图考试分配信息失败");
			}
		}
	});
	
}

/**
 * 初始化岗位课程
 */
function initPostCourses(){
	var opts = {
			items_per_page:itemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){
				var param = new Object();
				param.postId = postId;
				param.userId = userId;
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//默认异步
					data:param,
					url:'<%=request.getContextPath()%>/studentLearnMapAction/getPostCourses.action',
					success:function(data){
						if(data != null && data != ''){
							$("#postCoursesDiv").show();
							$("#postCoursesNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var postCourse = data[i];
								htmlStr += '<tr>';
								htmlStr += '<td width="20%">《'+postCourse.name+'》</td>';
								htmlStr += '<td width="20%">'+postCourse.categoryName+'</td>';
								if(postCourse.courseType == 1){
									htmlStr += '<td width="20%">选修</td>';
								}else if(postCourse.courseType == 2){
									htmlStr += '<td width="20%">必修</td>';
								}
								if(postCourse.learnProcess == 1){
									htmlStr += '<td width="20%">未完成</td>';
								}else if(postCourse.learnProcess == 2){
									htmlStr += '<td width="20%">已完成</td>';
								}
								htmlStr += '<td><a href="javascript:void(0)" onclick="toCourseDetail('+postCourse.id+');">详情</a></td>';
								htmlStr += '</tr>';
							}
							$("#postCoursesTable").html(htmlStr);
						}else{
							$("#postCoursesDiv").hide();
							$("#postCoursesNoResult").show();
						}
					}
				});
			}
	};
	var count = getPostCoursesCount();
	$("#postCoursesPager").pagination(count,opts);
}

/**
 * 获取岗位课程数量
 */
function getPostCoursesCount(){
	var param = new Object();
	param.postId = postId;
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnMapAction/getPostCoursesCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 跳转到课程详情
 */
function toCourseDetail(courseId){
	window.location.href = '<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+courseId;
}

/**
 * 每页显示20条数据
 */
function changePagesizeOfTwenty(){
	//修改样式
	$("#pageSizeTwenty").addClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	//修改pageSize
	itemsPerPage = 20;
	//查询
	initPostCourses();
}

/**
 * 每页显示40条数据
 */
function changePagesizeOfFourty(){
	//修改样式
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").addClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	//修改pageSize
	itemsPerPage = 40;
	//查询
	initPostCourses();
}

/**
 * 每页显示100条数据
 */
function changePagesizeOfOneHundred(){
	//修改样式
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").addClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	//修改pageSize
	itemsPerPage = 100;
	//查询
	initPostCourses();
}

/**
 * 每页显示200条数据
 */
function changePagesizeOfTwoHundred(){
	//修改样式
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").addClass("first");
	//修改pageSize
	itemsPerPage = 200;
	//查询
	initPostCourses();
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="notes_list fl">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">岗位详情</span>
			</div>
			<!-- <h3 id="postNameH3"></h3> -->
			<div class="tst">
				<h4 id="postH4"></h4>
				<div id="postDescr"></div>
			</div>
			<div class="tst">
				<h4>阶段测试</h4>
				<h5>
					<span id="stageTestTitle" style="font-size:14px;"></span> 
					<span id="passScore" style="padding-left:80px;font-size:14px;">及格分：</span>
					<span id="thisScore" style="padding-left:80px;font-size:14px;">考试分：</span>
					<span id="isPassed" style="padding-left:80px;font-size:14px;"></span>
					<a id="toStageTestLink" href="javascript:void(0)" class="fr" style="font-size:14px;">进入</a>
				</h5>
			</div>
			<div class="cs_list">
				<h4>课程</h4>
				<div class="border"></div>
				
				<!-- 岗位课程展现div -->
				<div class="clear_both"></div>
				<div id="postCoursesDiv">
					<table id="postCoursesTable" width="100%" class="tab_2">
					</table>
					
					<!-- 分页栏 -->
					<div class="page clear">
						<div class="left_page fl">
							<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
							<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
							<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
							<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
						</div>
						<div id="postCoursesPager" class="right_page fr">
						</div>
					</div>
				</div>
				
				<!-- 页面无结果显示用 -->
				<div id="postCoursesNoResult" style="display:none;text-align:center;margin-top:120px;">
					暂无结果
				</div>
			</div>
		</div>
	</div>
</body>
</html>