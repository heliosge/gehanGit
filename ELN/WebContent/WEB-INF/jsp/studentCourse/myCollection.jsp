<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jqueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

/**降序排序*/
.sortDesc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9down.png) no-repeat 75px center;}

/**升序排序*/
.sortAsc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9.png) no-repeat 75px center;}

.notes_list{padding-bottom:10px;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var sortName = 'collect_time';//排序名称
var sortOrder = 'desc';//排序顺序
var itemsPerPage = 20;//每页显示条数
	
/**
 * 页面加载完成
 */
$(function(){
	$("#collectTimeStart,#collectTimeEnd").datepicker({
		changeMonth: true,
		changeYear: true
	});
	//初始化收藏课程
	initCollectCourses();
});
	
/**
 * 初始化收藏课程
 */
function initCollectCourses(){
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
				param.userId = userId;
				param.courseName = $("#courseName").val();
				param.collectTimeStart = $("#collectTimeStart").val();
				param.collectTimeEnd = $("#collectTimeEnd").val();
				param.sortName = sortName;
				param.sortOrder = sortOrder;
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseCollectionAction/getCollectCourses.action',
					success:function(data){
						if(data != null && data != ''){
							$("#collectCoursesDiv").show();
							$("#collectCoursesNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var collectCourse = data[i];
								if((i+1)%4 == 0){
									htmlStr += '<li class="last_li">';
								}else{
									htmlStr += '<li>';
								}
								htmlStr += '<div class="bg">';
								htmlStr += '<a href="javascript:void(0)" onclick="toCourseDetail('+collectCourse.id+');">';
								htmlStr += '<img src="'+collectCourse.frontImage+'" width="248px" height="173px"/>';
								htmlStr += '</a>';
								htmlStr += '<h4 title="'+collectCourse.name+'" class="courseNameStyle">'+collectCourse.name+'</h4>';
								htmlStr += '<span>平均评分'+collectCourse.averageScore+'</span>';
								htmlStr += '<span>'+collectCourse.evaluatorNum+'人已评价</span>';
								htmlStr += '<span>'+collectCourse.studentNum+'人已学习</span>';
								htmlStr += '<br/>';
								htmlStr += '<span class="cancel">';
								htmlStr += '<a href="javascript:void(0)" onclick="cancelCollection('+collectCourse.id+');">';
								htmlStr += '<img src="<%=request.getContextPath()%>/images/img/ico_10.png" />取消收藏';
								htmlStr += '</a>';
								htmlStr += '</span>';
								htmlStr += '</div>';
								htmlStr += '</li>';
							}
							$("#collectCoursesUl").html(htmlStr);
						}else{
							$("#collectCoursesDiv").hide();
							$("#collectCoursesNoResult").show();
						}
					}
				});
			}
	};
	var count = getCollectCoursesCount();
	$("#collectCoursesPager").pagination(count,opts);
}

/**
 * 获取收藏课程数量
 */
function getCollectCoursesCount(){
	var param = new Object();
	param.userId = userId;
	param.courseName = $("#courseName").val();
	param.collectTimeStart = $("#collectTimeStart").val();
	param.collectTimeEnd = $("#collectTimeEnd").val();
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步，数量必须先获得
		data:param,
		url:'<%=request.getContextPath()%>/courseCollectionAction/getCollectCoursesCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 查询
 */
function search(){
	$("#searchButton").attr("class","btn_5");
	$("#resetButton").attr("class","btn_6");
	//查询
	initCollectCourses();
}

/**
 * 重置
 */
function reset(){
	$("#searchButton").attr("class","btn_6");
	$("#resetButton").attr("class","btn_5");
	//清空输入框
	$("#courseName").val('');
	$("#collectTimeStart").val('');
	$("#collectTimeEnd").val('');
	//查询
	initCollectCourses();
}

/**
 * 根据收藏时间排序
 */
function orderByCollectTime(){
	if(sortName == 'collect_time' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#orderByCollectTime").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#orderByCollectTime").attr("class","sortDesc");
	}
	sortName = 'collect_time';
	//查询
	initCollectCourses();
}

/**
 * 每页显示20条数据
 */
function changePagesizeOfTwenty(){
	$("#pageSizeTwenty").addClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 20;
	initCollectCourses();
}

/**
 * 每页显示40条数据
 */
function changePagesizeOfFourty(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").addClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 40;
	initCollectCourses();
}

/**
 * 每页显示100条数据
 */
function changePagesizeOfOneHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").addClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 100;
	initCollectCourses();
}

/**
 * 每页显示200条数据
 */
function changePagesizeOfTwoHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").addClass("first");
	itemsPerPage = 200;
	initCollectCourses();
}

/**
 * 取消收藏
 */
function cancelCollection(courseId){
	var d = dialog({
		title:'取消收藏',
		content:'你确认要取消收藏该课程吗？',
		okValue:'确认',
		ok:function(){
			var param = new Object();
			param.userId = userId;
			param.courseId = courseId;
			$.ajax({
				type:'POST',
				async:false,//同步
				data:param,
				url:'<%=request.getContextPath()%>/courseCollectionAction/cancelCollection.action',
				success:function(data){
					if(data.rtnResult == 'SUCCESS'){
						alert("取消收藏成功！");
						//刷新收藏
						initCollectCourses();
					}else{
						alert("取消收藏失败...");
					}
				}
			});
		},
		cancelValue:'取消',
		cancel:function(){
			
		}
	});
	d.show();
}

/**
 * 跳转到课程详情页面
 */
function toCourseDetail(courseId){
	window.location.href = '<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId='+courseId;
}
</script>
</head>
<body>

	<div id="course_all" style="padding-top:10px;">
		<div class="notes_list">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的收藏</span>
			</div>
			<!-- <h3>我的收藏</h3> -->
			<div class="search_1">
				<span>课程名称：<input id="courseName" type="text" /></span> 
				<span>收藏时间：<input id="collectTimeStart" type="text" />至<input id="collectTimeEnd" type="text" /></span> 
				<input id="searchButton" type="button" class="btn_5" value="查询" onclick="search();"/> 
				<input id="resetButton" type="button" class="btn_6" value="重置" onclick="reset();"/>
				<div class="sort_1 fr">
					<a id="orderByCollectTime" class="sortDesc" href="javascript:void(0)" onclick="orderByCollectTime();">按收藏时间</a> 
				</div>
			</div>
		</div>
		
		<!-- 收藏课程列表 -->
		<div id="collectCoursesDiv">
			<div class="course_list1 clear">
				<ul id="collectCoursesUl">
				</ul>
			</div>
		
			<!-- 分页栏 -->
			<div class="page clear">
				<div class="left_page fl">
					<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
					<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
					<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
					<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
				</div>
				<div id="collectCoursesPager" class="right_page fr">
				</div>
			</div>
		</div>
		
		<!-- 页面无结果显示用 -->
		<div id="collectCoursesNoResult" style="display:none;text-align:center;margin-top:120px;">
			暂无结果
		</div>
	</div>
</body>
</html>