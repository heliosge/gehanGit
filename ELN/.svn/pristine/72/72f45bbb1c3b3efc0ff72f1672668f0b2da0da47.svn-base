<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习计划</title>
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

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

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
var planStatus = 1;
	
/**
 * 页面加载完成
 */
$(function(){
	//初始化学习计划（图片展现）
	initLearnPlanForPics();
	//初始化我的课程（mmGrid展现）
	initLearnPlanForMMGrid();
	$("#learnPlanMMGridShow").hide();
});
	
/**
 * 初始化学习计划（图片展现）
 */
function initLearnPlanForPics(){
	var opts = {
			items_per_page:itemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			callback:function(page,node){//page从0开始，指代当前页，node是分页的div
				//查询参数
				var param = new Object();
				param.userId = userId;
				param.learnProcess = planStatus;
				param.searchText = $("#searchText").val();
				param.page = page;//当前页
				param.pageSize = itemsPerPage;//每页显示条数
				//异步查询
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/studentLearnPlanAction/getLearnPlansForPics.action',
					success:function(data){
						var htmlStr = '';
						if(data != null && data != ''){
							//展现图片
							$("#learnPlanPicsShow").show();
							$("#learnPlanNoResult").hide();
							//遍历结果
							for(var i = 0; i < data.length; i++){
								var learnPlan = data[i];
								if((i+1)%4 == 0){
									htmlStr += '<li class="last_li" onclick="toLearnPlanDetail('+learnPlan.id+');">';
								}else{
									htmlStr += '<li onclick="toLearnPlanDetail('+learnPlan.id+');">';
								}
								htmlStr += '<div class="bg">';
								htmlStr += '<img src="'+learnPlan.coverImage+'" width="248px" height="173px"/>';
								htmlStr += '<h4 title="'+learnPlan.name+'" class="courseNameStyle">'+learnPlan.name+'</h4>';
								/* htmlStr += '<span class="pl_sp">'+learnPlan.type+'</span>'; */
								htmlStr += '<em>';
								htmlStr += '<span>';
								/* if(learnPlan.status == 1){
									htmlStr += '进行中';
								}else if(learnPlan.status == 2){
									htmlStr += '已完成';
								}else{
									htmlStr += '';
								} */
								htmlStr += learnPlan.studyPercent+'%';
								
								htmlStr += '</span>';
								htmlStr += '</em>';
								htmlStr += '</div>';
								htmlStr += '</li>';
							}
							$("#cs_list1").html(htmlStr);
						}else{
							//展现没有结果
							$("#learnPlanPicsShow").hide();
							$("#learnPlanNoResult").show();
						}
					}
				});
			}
	};
	var count = getLearnPlanCount();
	$("#learnPlanPagerForPics").pagination(count,opts);
}

/**
 * 获取学习计划的数量
 */
function getLearnPlanCount(){
	//查询参数
	var param = new Object();
	param.userId = userId;
	param.learnProcess = planStatus;
	param.searchText = $("#searchText").val();
	//总条数
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步，必须先查出总条数
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnPlanAction/getLearnPlanCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 获取学习计划（mmGrid展现）
 */
function initLearnPlanForMMGrid(){
	//查询参数
	var param = new Object();
	param.userId = userId;
	param.learnProcess = planStatus;
	param.searchText = $("#searchText").val();
	
	$("#mmGridTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/studentLearnPlanAction/getMyLearnPlansForMMGrid.action',
		width : '1042px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols:[{
			title : 'id',
			name : 'id',
			hidden : true
		},{
			title : '学习计划名称',
			name : 'name',
			align : 'center',
			renderer : function(val, item, rowIndex) {
				var str = '<a href="javascript:void(0)" onclick="toLearnPlanDetail('+item.id+');">'+val+'</a>';
				return str;
			}
		},
		/* {
			title : '计划类型',
			name : 'type',
			align : 'center'
		}, */
		{
			title : '计划状态',
			name : 'studyPercent',
			align : 'center',
			renderer : function(val, item, rowIndex) {
				return val + '%';
			}
		}],
		plugins:[$("#mmGridPager").mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page',//当前页
			limitParamName : 'pageSize',//每页显示数量
			limitList : [20,40,100,200]
		})]
	});
}

/**
 * 查询
 */
function search(){
	if(showType == 'pics'){
		$("#learnPlanMMGridShow").hide();
		//图片展现
		initLearnPlanForPics();
	}else{
		$("#learnPlanPicsShow").hide();
		$("#learnPlanNoResult").hide();
		$("#learnPlanMMGridShow").show();
		//列表展现
		var searchParam = new Object();
		searchParam.userId = userId;
		searchParam.learnProcess = planStatus;
		searchParam.searchText = $("#searchText").val();
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
 * 展现学习中的课程
 */
function showStudingCourses(){
	//清空搜索框
	$("#searchText").val('');
	//改变学习状态tab样式
	$("#studingState").css({'color':'red'});
	$("#completeState").css({'color':'black'});
	//设置学习状态为学习中
	planStatus = 1;
	//查询
	search();
}

/**
 * 展现已完成的课程
 */
function showCompleteCourses(){
	//清空搜索框
	$("#searchText").val('');
	//改变学习状态tab样式
	$("#completeState").css({'color':'red'});
	$("#studingState").css({'color':'black'});
	//设置学习状态为已完成
	planStatus = 2;
	//查询
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
 * 跳往学习计划详情页面
 */
function toLearnPlanDetail(learnPlanId){
	window.location.href = '<%=request.getContextPath()%>/studentLearnPlanAction/toLearnPlanDetail.action?learnPlanId='+learnPlanId;
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="plan">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学习计划</span>
			</div>
			<!-- <h3>学习计划</h3> -->
			<div id="tabDiv">
				<h4 class="tabStyle"><a id="studingState" onclick="showStudingCourses();">进行中</a></h4>
				<h4 class="tabStyle"><a id="completeState" onclick="showCompleteCourses();">已完成</a></h4>
			</div>
			<br/>
			<div id="planSearch1" class="search_1">
				<input id="searchText" type="text" class="txt_1" /> 
				<input type="button" class="btn_4" value="查询" onclick="search();" /> 
				<input type="button" id="showPicsButton" class="btn_5" value="大图" onclick="searchOfPics();"/> 
				<input type="button" id="showListButton" class="btn_6" value="列表" onclick="searchOfList();"/> 
				<!-- <span class="zt_1">计划状态：</span> 
				<input type="radio" name="planStatus" value="1" checked="checked" /> 
				<span>进行中</span>
				<input type="radio" name="planStatus" value="2" /> 
				<span>已完成</span> -->
			</div>
			
			<!-- 图片展现 -->
			<div id="learnPlanPicsShow">
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
					<div id="learnPlanPagerForPics" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 页面无结果显示用（图标展现） -->
			<div id="learnPlanNoResult" style="display:none;text-align:center;margin-top:120px;">
				暂无结果
			</div>
			
			<!-- mmGrid展现 -->
			<div class="clear_both"></div>
			<div id="learnPlanMMGridShow" style="padding-bottom:60px;">
				<table id="mmGridTable"></table>
				<div id="mmGridPager" style="text-align: right;"></div>
			</div>
		</div>
	</div>
</body>
</html>