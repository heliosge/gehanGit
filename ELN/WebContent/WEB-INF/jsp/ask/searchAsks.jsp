<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索问题</title>
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**框架*/
#askTypeDetailFrame{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**框架标题*/
#askTypeDetailFrame > h3{
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;font-size:14px;}

/**链接属性*/
a{text-decoration: none;cursor:pointer;}

/**返回链接*/
#askTypeDetailFrame > h3 > a{font-size:24px;color:#888888;}

/**分类标题*/
#typesDiv{margin-left: 20px;font-size: 14px;width: 1046px;height: 40px;}

#typeName{font-style: normal;}

/**搜索框*/
#searchDiv{margin-left: 20px;font-size: 14px;width: 1046px;height: 50px;
background-color:rgba(128, 128, 128, 0.21);}

#searchContent{width: 280px;height: 28px;margin-left: 10px;margin-top: 8px;}

#searchButton{width: 60px;height: 28px;border: medium none;margin-top: 6px;cursor: pointer;
background: #D60500 none repeat scroll 0% 0%;color: #FFF;}

#allAsksButton{width: 60px;height: 28px;border: medium none;margin-top: 6px;cursor: pointer;
background: #0085FE none repeat scroll 0% 0%;color: #FFF;}

.sortTypeStyle{width: 260px;height: 32px;float: right;font-size: 12px;margin-top: 8px;padding-right: 10px;}

.sortDesc{display: block;float: left;width: 85px;height: 32px;line-height: 32px;color: #666;
text-align: center;border: 1px solid #CCC;margin-right: -1px;
background: #FFF url("../images/img/arrow_down.png") no-repeat scroll 60px center;}

.sortAsc{display: block;float: left;width: 85px;height: 32px;line-height: 32px;color: #666;
text-align: center;border: 1px solid #CCC;margin-right: -1px;
background: #FFF url("../images/img/arrow_top.png") no-repeat scroll 60px center;}

/**展现列表*/
#askListDiv{width:1046px;margin-top:20px;margin-left:20px;font-size: 12px;}
</style>

<script type="text/javascript">
var searchInput = '${searchInput}';
var sortName = 'effective_time';
var sortOrder = 'desc';

/**
 * 页面加载完成
 */
$(function(){
	$("#searchContent").val(searchInput);
	//初始化该分类下的问问列表
	initTypeAsksForMMGrid();
});


/**
 * 初始化该分类下的问问列表
 */
function initTypeAsksForMMGrid(){
	//设置参数
	var param = {};
	param.searchContent = $("#searchContent").val();
	param.sortName = sortName;
	param.sortOrder = sortOrder;
	var nowDate = new Date();
	//mmGrid展现
	$("#askListTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/myAsk/getAsksForSearch.action',
		width : '1046px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : false, // 索引列
		params : param,
		cols:[{
			title:'id',
			name:'id',
			width:'0px',
			hidden:true
		},{
			title:'问题标题',
			name:'title',
			align:'center',
			width:600,
			lockWidth:true,
			renderer:function(val,item,rowIndex){
				var str = '';
				if(item.effectiveTime == null || item.effectiveTime >= nowDate){
					str = '<a href="javascript:void(0)" onclick="toAskDetail('+item.id+');">'+val+'</a>';
				}else{
					str = '<a href="javascript:void(0)" style="color:gray;">'+val+'</a>';
				}
				return str;
			}
		},{
			title:'提问时间',
			name:'createTime',
			align:'center',
			renderer:function(val,item,rowIndex){
				return getSmpFormatDateByLong(val);
			}
		},{
			title:'问题截止有效期',
			name:'effectiveTime',
			align:'center',
			renderer:function(val,item,rowIndex){
				if(val){
					return getSmpFormatDateByLong(val);
				}else{
					return null;
				}
			}
		},{
			title:'回答数量',
			name:'answerCount',
			align:'center'
		}],
		plugins : [$('#askListPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [10,20,50,100]
		})]
	});
}

/**
 * 跳转到问题详情页面
 */
function toAskDetail(askId){
	window.location.href = '<%=request.getContextPath()%>/myAsk/toAskDetail.action?askId='+askId;
}

/**
 * 根据条件查询
 */
function searchByCondition(){
	//设置参数
	var searchParam = {};
	searchParam.searchContent = $("#searchContent").val();
	searchParam.sortName = sortName;
	searchParam.sortOrder = sortOrder;
	//查询
	$("#askListTable").mmGrid().load(searchParam);
}

/**
 * 查询全部
 */
function searchAll(){
	$("#searchContent").val('');
	//设置参数
	var searchParam = {};
	searchParam.searchContent = $("#searchContent").val();
	searchParam.sortName = sortName;
	searchParam.sortOrder = sortOrder;
	//查询
	$("#askListTable").mmGrid().load(searchParam);
}

/**
 * 根据到期时间排序
 */
function sortByEffectiveTime(){
	//设置参数
	if(sortName == 'effective_time' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#byEffectiveTime").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#byEffectiveTime").attr("class","sortDesc");
	}
	sortName = 'effective_time';
	//查询
	searchByCondition();
}

/**
 * 根据提问时间查询
 */
function sortByCreateTime(){
	//设置参数
	if(sortName == 'create_time' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#byCreateTime").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#byCreateTime").attr("class","sortDesc");
	}
	sortName = 'create_time';
	//查询
	searchByCondition();
}

/**
 * 根据回答数排序
 */
function sortByAnswerCount(){
	//设置参数
	if(sortName == 'answer_count' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#byAnswerCount").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#byAnswerCount").attr("class","sortDesc");
	}
	sortName = 'answer_count';
	//查询
	searchByCondition();
}

/**
 * 返回
 */
function backLastPage(){
	window.history.go(-1);
}

</script>

</head>
<body>
	<div id="askTypeDetailFrame">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">搜索问题</span>
		</div>
		<!-- <h3><a href="javascript:void(0)" onclick="backLastPage();">&lt;</a>&nbsp;&nbsp;&nbsp;搜索问题</h3> -->
		<!-- 搜索框 -->
		<div id="searchDiv">
			<input type="text" id="searchContent"/>
			<input type="button" id="searchButton" value="查询" onclick="searchByCondition();"/>
			<input type="button" id="allAsksButton" value="全部" onclick="searchAll();">
			<div class="sortTypeStyle">
				<a id="byEffectiveTime" class="sortDesc" onclick="sortByEffectiveTime();">默认</a>
				<a id="byCreateTime" class="sortDesc" onclick="sortByCreateTime();">最新</a>
				<a id="byAnswerCount" class="sortDesc" onclick="sortByAnswerCount();">回答数</a>
			</div>
		</div>
		<!-- 列表展现 -->
		<div id="askListDiv">
			<table id="askListTable"></table>
			<div id="askListPager"></div>
		</div>
	</div>
</body>
</html>