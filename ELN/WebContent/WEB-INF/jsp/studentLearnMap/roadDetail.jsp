<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发现更多</title>
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
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var postId = '${USER_BEAN.postId}';//岗位id
var postName = '${USER_BEAN.postName}';//岗位名称
var itemsPerPage = 5;//每页显示条数
	
/**
 * 页面加载完成
 */
$(function(){
	//初始化晋升路径详细
	initRoadDetails();
});
	
/**
 * 初始化晋升路径详细
 */
function initRoadDetails(){
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
				param.searchContent = $("#searchContent").val();
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//默认异步
					data:param,
					url:'<%=request.getContextPath()%>/studentLearnMapAction/getRoadDetails.action',
					success:function(data){
						if(data != null && data != ''){
							$("#roadDetailDiv").show();
							$("#roadDetailNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var roadDetail = data[i];
								htmlStr += '<div class="map">';
								htmlStr += '<h4>';
								htmlStr += '<span>'+roadDetail.name+'</span>';
								if(roadDetail.promotionState == 1){
									htmlStr += '<strong>晋升完毕</strong>';
								}else if(roadDetail.promotionState == 2){
									htmlStr += '<strong>晋升中</strong>';
								}
								htmlStr += '<a href="javascript:void(0)" class="down" onclick="slideToggleContent('+roadDetail.id+');"></a>';
								htmlStr += '</h4>';
								if(i == 0){
									htmlStr += '<div id="roadDetail'+roadDetail.id+'">';
								}else{
									htmlStr += '<div id="roadDetail'+roadDetail.id+'" style="display:none;">';
								}
								htmlStr += '<div class="more_1">';
								htmlStr += '<div class="more_txt">';
								htmlStr += '<p>'+roadDetail.orderNum+'阶段</p>';
								htmlStr += '<p>'+postName+'</p>';
								htmlStr += '</div>';
								htmlStr += '</div>';
								htmlStr += '<h5>';
								htmlStr += '<span>晋升进度：'+roadDetail.promotionPercent+'%</span>';
								htmlStr += '<a href="javascript:void(0)" onclick="toPostDetail('+roadDetail.orderNum+','+roadDetail.stageId+');">继续学习</a>';
								htmlStr += '</h5>';
								htmlStr += '</div>';
								htmlStr += '</div>';
							}
							$("#roadDetailContent").html(htmlStr);
						}else{
							$("#roadDetailDiv").hide();
							$("#roadDetailNoResult").show();
						}
					}
				});
			}
	};
	var count = getRoadDetailsCount();
	$("#roadDetailPager").pagination(count,opts);
}

/**
 * 获取晋升路径详细数量
 */
function getRoadDetailsCount(){
	var param = new Object();
	param.userId = userId;
	param.searchContent = $("#searchContent").val();
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnMapAction/getRoadDetailsCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * slideToggle具体内容
 */
function slideToggleContent(roadDetailId){
	$("#roadDetail"+roadDetailId+"").slideToggle();
}

/**
 * 跳往岗位相关页面
 */
function toPostDetail(orderNum,stageId){
	window.location.href='<%=request.getContextPath()%>/studentLearnMapAction/toPostDetail.action?orderNum='+orderNum+'&stageId='+stageId;
}

/**
 * 搜索
 */
function searchContent(){
	//刷新页面
	initRoadDetails();
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
	initRoadDetails();
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
	initRoadDetails();
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
	initRoadDetails();
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
	initRoadDetails();
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="notes_list fl">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">个人晋升路径</span>
			</div>
			<!-- <h3>个人晋升路径</h3> -->
			<div class="search_2">
				<input id="searchContent" type="text" /> 
				<a href="javascript:void(0)" onclick="searchContent();">搜索</a>
			</div>
			
			<!-- 路径详细展现 -->
			<div id="roadDetailDiv">
				<div id="roadDetailContent">
				</div>
			
				<!-- 分页栏 -->
				<div class="page clear">
					<div class="left_page fl">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="roadDetailPager" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 页面无结果显示用 -->
			<div id="roadDetailNoResult" style="display:none;text-align:center;margin-top:120px;">
				暂无结果
			</div>
		</div>
	</div>
</body>
</html>