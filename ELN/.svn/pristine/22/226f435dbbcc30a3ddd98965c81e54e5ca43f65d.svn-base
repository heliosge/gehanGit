<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的课程</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- 引入dateUtil.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

/**我的课程标题*/
.plan h3{margin-bottom:0px;}

/**学习状态tabDiv*/
#tabDiv{width:1036px;height:48px;padding-left:10px;border-bottom:1px solid #D4D2D2;}

/**学习状态tab*/
.tabStyle{width:80px;height:48px;line-height:48px;float:left;margin-bottom:2px;}

/**学习中tab*/
#studingState{color:red;}

</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var itemsPerPage = 20;//每页显示条数
var showType = 'pics';//展现形式（大图，列表），默认大图
var studyState = 1;//学习状态
	
/**
 * 页面加载完成
 */
$(function(){
	//初始化我的课程（图片展现）
	initMyCoursesForPics();
	//初始化我的课程（mmGrid展现）
	initMyCoursesForMMGrid();
});
	
/**
 * 初始化我的课程(图片展现)
 */
function initMyCoursesForPics(){
	var opts = {
			items_per_page:itemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){//page从0开始，指代当前页，node是分页的div
				//查询参数
				var param = new Object();
				param.userId = userId;
				param.learnProcess = studyState;
				param.searchContent = $("#searchContent").val();
				param.page = page;//当前页
				param.pageSize = itemsPerPage;//每页显示条数
				//异步查询
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseUserAction/getMyCoursesForPics.action',
					success:function(data){
						var htmlStr = '';
						if(data != null && data != ''){
							//展现图片
							$("#myCoursesPicsShow").show();
							$("#myCoursesNoResult").hide();
							//遍历结果
							for(var i = 0; i < data.length; i++){
								var course = data[i];
								if((i+1)%4 == 0){
									htmlStr += '<li class="last_li">';
								}else{
									htmlStr += '<li>';
								}
								htmlStr += '<div class="bg">';
								htmlStr += '<img src="'+course.frontImage+'" width="248px" height="173px" onclick="toCourseDetail('+course.id+')"/>';
								htmlStr += '<h4 title="'+course.name+'" class="courseNameStyle">'+course.name+'</h4>';
								/* htmlStr += '<span>平均评分'+course.averageScore+'</span>'; */
								htmlStr += '<span>学习进度'+course.learnProcessPercent+'%</span>';
								htmlStr += '<span>'+course.evaluatorNum+'人已评价</span>';
								htmlStr += '<span>'+course.studentNum+'人已学习</span>';
								htmlStr += '<br/>';
								if(studyState == 1){
									htmlStr += '<a href="javascript:void(0)" onclick="cancelStudy('+course.id+');">取消学习</a>';	
								}
								htmlStr += '</div>';
								htmlStr += '</li>';
							}
							$("#cs_list1").html(htmlStr);
						}else{
							//展现没有结果
							$("#myCoursesPicsShow").hide();
							$("#myCoursesNoResult").show();
						}
					}
				});
			}
	};
	var count = getMyCoursesCount();
	$("#myCoursesPagerForPics").pagination(count,opts);
}

/**
 * 获取我的课程数量
 */
function getMyCoursesCount(){
	//查询参数
	var param = new Object();
	param.userId = userId;
	param.learnProcess = studyState;
	param.searchContent = $("#searchContent").val();
	//总条数
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步，必须先查出总条数
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getMyCoursesCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 初始化我的课程（mmGrid展现）
 */
function initMyCoursesForMMGrid(){
	//查询参数
	var param = new Object();
	param.userId = userId;
	param.learnProcess = studyState;
	param.searchContent = $("#searchContent").val();
	
	$("#mmGridTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/courseUserAction/getMyCoursesForMMGrid.action',
		width : '1042px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols : [{
			title : 'id',
			name : 'id',
			hidden : true
		},{
			title : '课程名称',
			name : 'name',
			align : 'center',
			renderer : function(val, item, rowIndex){
				var str = '<a href="javascript:void(0);" onclick="toCourseDetail('+item.id+')">'+val+'</a>';
				return str;
			}
		},{
			title : '开始学习时间',
			name : 'startLearnTime',
			align : 'center'
		},{
			title : '最后学习时间',
			name : 'lastLearnTime',
			align : 'center'
		},{
			title : '学习时长',
			name : 'learnDuration',
			align : 'center'
		},{
			title : '学习进度',
			name : 'learnProcessPercent',
			align : 'center',
			renderer : function(val, item, rowIndex){
				return val+'%';
			}
		},{
			title : '任务类型',
			name : 'courseSource',
			align : 'center',
			renderer : function(val, item, rowIndex){
				var str = '';
				var sourceArr = val.split(",");
				if(sourceArr && sourceArr.length > 0){
					for(var i = 0; i < sourceArr.length; i++){
						if(sourceArr[i] == 1){
							str += '自选课程&nbsp;';
						}
						if(sourceArr[i] == 2){
							str += '岗位课程&nbsp;';
						}
						if(sourceArr[i] == 3){
							str += '群组课程&nbsp;';
						}
						if(sourceArr[i] == 4){
							str += '部门课程&nbsp;';
						}
					}
				}
				return str;
			}
		},{
			title : '操作',
			name : 'operation',
			align : 'center',
			renderer : function(val, item, rowIndex){
				var str = '';
				if(item.learnProcessPercent == 0){
					str += '<a href="javascript:void(0);" onclick="toCourseDetail('+item.id+')">开始学习</a>';
				}else{
					str += '<a href="javascript:void(0);" onclick="toCourseDetail('+item.id+')">继续学习</a>';
				}
				if(item.learnProcessPercent < 100){
					str += '&nbsp;<a href="javascript:void(0)" onclick="cancelStudy('+item.id+');">取消学习</a>';
				}
				return str;
			}
		}],
		plugins : [$("#mmGridPager").mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page',//当前页
			limitParamName : 'pageSize',//每页显示数量
			limitList : [20,40,100,200]
		})]
	});
	
	//隐藏或显示列表
	if(showType == 'pics'){
		$("#myCoursesMMGridShow").hide();
	}else{
		$("#myCoursesMMGridShow").show();
	}
}

/**
 * 取消学习
 */
function cancelStudy(courseId){
	dialog.confirm("取消学习后，该课程的学习进度、发布的笔记和问题（已有回复的问题将被保留）、评分和评论将不被保留且无法找回，确定要取消学习吗？",function(obj){
		$.ajax({
			type:'POST',
			async:false,//此处为同步
			data:{"courseId":courseId,"userId":userId},
			url:'<%=request.getContextPath()%>/courseUserAction/cancelStudy.action',
			success:function(data){
				if(data.rtnResult == 'SUCCESS'){
					dialog.alert("取消学习成功！");
					search();
				}else{
					dialog.alert("取消学习失败...");
				}
			}
		});
	});
}

/**
 * 查询
 */
function search(){
	if(showType == 'pics'){
		$("#myCoursesMMGridShow").hide();
		//图片展现
		initMyCoursesForPics();
	}else{
		$("#myCoursesPicsShow").hide();
		$("#myCoursesNoResult").hide();
		$("#myCoursesMMGridShow").show();
		//列表展现
		var searchParam = new Object();
		searchParam.userId = userId;
		searchParam.learnProcess = studyState;
		searchParam.searchContent = $("#searchContent").val();
		$("#mmGridTable").mmGrid().load(searchParam);
	}
}

/**
 * 查询结果大图展现
 */
function searchOfPics(){
	$("#showPicsButton").attr("class","btn_5");
	$("#showListButton").attr("class","btn_6");
	showType = 'pics';
	search();
}

/**
 * 查询结果列表展现
 */
function searchOfList(){
	$("#showPicsButton").attr("class","btn_6");
	$("#showListButton").attr("class","btn_5");
	showType = 'list';
	search();
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
	search();
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
	search();
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
	search();
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
	search();
}

/**
 * 跳转到课程详情页面
 */
function toCourseDetail(courseId){
	window.location.href = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+courseId+"&backFlag=2";
}

/**
 * 展现学习中的课程
 */
function showStudingCourses(){
	//清空搜索框
	$("#searchContent").val('');
	//改变学习状态tab样式
	$("#studingState").css({'color':'red'});
	$("#completeState").css({'color':'black'});
	//设置学习状态为学习中
	studyState = 1;
	//查询
	search();
}

/**
 * 展现已完成的课程
 */
function showCompleteCourses(){
	//清空搜索框
	$("#searchContent").val('');
	//改变学习状态tab样式
	$("#completeState").css({'color':'red'});
	$("#studingState").css({'color':'black'});
	//设置学习状态为已完成
	studyState = 2;
	//查询
	search();
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="plan">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的课程</span>
			</div>
			<!-- <h3>我的课程</h3> -->
			<div id="tabDiv">
				<h4 class="tabStyle"><a id="studingState" onclick="showStudingCourses();">学习中</a></h4>
				<h4 class="tabStyle"><a id="completeState" onclick="showCompleteCourses();">已完成</a></h4>
			</div>
			<br/>
			<div id="search_1" class="search_1">
				<input id="searchContent" type="text" class="txt_1" /> 
				<input type="button"class="btn_4" value="查询" onclick="search();" /> 
				<input id="showPicsButton" type="button" class="btn_5"value="大图" onclick="searchOfPics();" /> 
				<input id="showListButton" type="button" class="btn_6" value="列表" onclick="searchOfList();" /> 
			</div>
			
			<!-- 图片展现 -->
			<div id="myCoursesPicsShow">
				<div class="course_list1 clear">
					<ul id="cs_list1"></ul>
				</div>
				<!-- 图片分页 -->
				<div class="page clear">
					<div class="left_page fl">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="myCoursesPagerForPics" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 页面无结果显示用（图标展现） -->
			<div id="myCoursesNoResult" style="display:none;text-align:center;margin-top:120px;">
				暂无结果
			</div>
			
			<!-- mmGrid展现 -->
			<div class="clear_both"></div>
			<div id="myCoursesMMGridShow" style="padding-bottom:60px;">
				<table id="mmGridTable"></table>
				<div id="mmGridPager" style="text-align: right;"></div>
			</div>
		</div>
	</div>
</body>
</html>