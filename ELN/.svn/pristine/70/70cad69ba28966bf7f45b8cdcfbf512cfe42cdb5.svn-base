<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习地图</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- FusionCharts -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/FusionCharts_XT/Charts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/FusionCharts_XT/Charts/FusionCharts.jqueryplugin.js"></script>
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
var itemsPerPage = 20;//每页显示条数
	
/**
 * 页面加载完成
 */
$(function(){
	//初始化我的所有晋升路径
	initMyPromotionPath();
});
	
/**
 * 初始化我的所有晋升路径
 */
function initMyPromotionPath(){
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
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.page = page;
				param.pageSize = itemsPerPage;
				//查询所有晋升路径
				$.ajax({
					type:'POST',
					async:true,//默认异步
					data:param,
					url:'<%=request.getContextPath()%>/studentLearnMapAction/getMyPromotionPath.action',
					success:function(data){
						if(data != null && data != ''){
							$("#promotionPathDiv").show();
							$("#promotionPathNoResult").hide();
							for(var i = 0; i < data.length; i++){
								var htmlStr = '';
								var dataStr = '';//FusionCharts使用
								var promotionPath = data[i];
								htmlStr += '<div class="map">';
								htmlStr += '<h4>';
								if(promotionPath.promotionState == 100){
									htmlStr += '<span>晋升完毕：'+promotionPath.name+'</span>';
								}else{
									htmlStr += '<span>晋升中：'+promotionPath.name+'</span>';
								}
								htmlStr += '<a href="javascript:void(0)" class="down" onclick="slideToggleStages('+promotionPath.id+');"></a>';
								htmlStr += '<a href="javascript:void(0)" class="fr" onclick="toRoadDetail();">发现更多</a>';
								if(promotionPath.isDisabled == 1){
									htmlStr += '<a href="javascript:void(0)" class="red fr">此路径已停用</a>';
								}
								htmlStr += '</h4>';
								//路径各阶段及当前所处阶段，使用FusionCharts
								if(i == 0){
									htmlStr += '<div id="stages'+promotionPath.id+'" style="width:1046px;height:400px;">';
								}else{
									htmlStr += '<div id="stages'+promotionPath.id+'" style="width:1046px;height:400px;display:none;">';
								}
								var innerParam = new Object();
								innerParam.promotionPathId = promotionPath.id;
								$.ajax({
									type:'POST',
									async:false,//此处为同步
									data:innerParam,
									url:'<%=request.getContextPath()%>/studentLearnMapAction/getPathStages.action',
									success:function(data){
										var stages = data;
										if(stages != null && stages != ''){
											dataStr += '[';
											for(var j = 0; j < stages.length; j++){
												var stage = stages[j];
												if(stage.id == promotionPath.currentStageId){
													dataStr += '{ "label":"'+stage.postName+'","value":"'+stage.orderNum+'","color":"#FFCC33","link":"JavaScript:toPostDetail('+stage.orderNum+','+stage.id+');"}';
												}else{
													dataStr += '{ "label":"'+stage.postName+'","value":"'+stage.orderNum+'","color":"#DDDDDD"}';
												}
												if(j < (stages.length - 1)){
													dataStr += ',';
												}
											}
											dataStr += ']';
										}
									}
								});
								htmlStr += '</div>';
								htmlStr += '</div>';
								$("#promotionPathContent").html(htmlStr);
								//初始化FusionCharts
								var dataStrJson = JSON.parse(dataStr);
								var myChart = new FusionCharts('<%=request.getContextPath()%>/js/FusionCharts_XT/Charts/Column3D.swf',
										'myChartId'+promotionPath.id+'','1000','350','0');
								myChart.setJSONData({
									"chart":{
										"caption" : "晋升路径",
										"xAxisName" : "岗位",
										"yAxisName" : "晋升阶段",
										"numberPrefix" : "第",
										"numberSuffix" : "阶段"
									},
									"data": dataStrJson
								});
								myChart.render("stages"+promotionPath.id+"");
							}
							
						}else{
							$("#promotionPathDiv").hide();
							$("#promotionPathNoResult").show();
						}
					}
				});
			}
	};
	var count = getMyPromotionPathCount();
	$("#promotionPathPager").pagination(count,opts);
}

/**
 * 获取我的所有晋升路径的数量
 */
function getMyPromotionPathCount(){
	var param = new Object();
	param.userId = userId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	var count = 0;
	//查询数量
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:param,
		url:'<%=request.getContextPath()%>/studentLearnMapAction/getMyPromotionPathCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 跳往岗位相关页面
 */
function toPostDetail(orderNum,stageId){
	window.location.href='<%=request.getContextPath()%>/studentLearnMapAction/toPostDetail.action?orderNum='+orderNum+'&stageId='+stageId;
}

/**
 * 路径阶段slideToggle
 */
function slideToggleStages(promotionPathId){
	$("#stages"+promotionPathId+"").slideToggle();
}

/**
 * 跳往路径详情页面
 */
function toRoadDetail(){
	window.location.href = '<%=request.getContextPath()%>/studentLearnMapAction/toRoadDetail.action';
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
	initMyPromotionPath();
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
	initMyPromotionPath();
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
	initMyPromotionPath();
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
	initMyPromotionPath();
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="notes_list fl">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学习地图</span>
			</div>
			<!-- <h3>学习地图</h3> -->
			
			<!-- 晋升路径展现div -->
			<div id="promotionPathDiv">
				<div id="promotionPathContent">
				</div>
			
				<!-- 分页栏 -->
				<div class="page clear">
					<div class="left_page fl">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="promotionPathPager" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 页面无结果显示用 -->
			<div id="promotionPathNoResult" style="display:none;text-align:center;margin-top:120px;">
				暂无结果
			</div>
		</div>
	</div>
</body>
</html>