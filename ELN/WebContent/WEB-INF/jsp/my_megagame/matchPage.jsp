<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<title>赛程详情</title>
<style type="text/css">
.search_1 span input{width:158px; height:30px; background:#fff; border:1px solid #cccccc;}
.search_1 span{ font-weight:bold; margin-right:10px;}
.grid1{margin-top: 60px;width: 1000px}
#select_1{text-indent: 0;}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';// 当前大赛id
var matchId = '${matchId}';// 当前赛程id
var megagameInfo = null;// 当前大赛信息
var checkResult; //验证报名审批结果
var allowTimes=0;
$(function() {
	getMegagameInfo();
	initMatchPlan();
	if(megagameInfo.needApproval==1){//需要报名才检查审批情况
		checkResult = checkJoinQualification();
		if(checkResult && checkResult.approvalStatus==2){
			initJoinListGrid();
		}
	}else{
		initJoinListGrid();
	}
})

/**
 * 获取大赛基础信息
 */
function getMegagameInfo() {
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameInfoById.action";
	$.ajax({
		type : "POST",
		async : false,
		url : urlStr,
		data : {
			"userId" : userId,
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				megagameInfo = data.rtnData;
			}
		}
	});
}

/**
 * 获取赛程安排信息
 */
function getMatchPlan(matchId) {
	var result = null;
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameProcessInfoById.action";
	$.ajax({
		type : "POST",
		async : false,
		url : urlStr,
		data : {
			"processId" : matchId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				result = data.rtnData;
			}
		}
	});
	return result;
}
/**
 * 进入考试
 */
 function gotoExam(examId){
	//验证当前时间是否允许考试
	var currentTime = new Date();
	var datass = getMatchPlan(matchId);
	var bTime = new Date(datass.beginTime);
	var eTime = new Date(datass.endTime);
	if(currentTime>=bTime && currentTime<=eTime){
		//获取当前大赛是否需要报名
		var needApproval = megagameInfo.needApproval;
		if(needApproval==1){//需要报名才去验证参赛资格
			//验证是否有资格参加考试
			if(checkResult && checkResult.approvalStatus==2){
				var assignInfo = getAssignInfoByUserExamId(examId);
				if(assignInfo){
					var times = assignInfo.testTimes;
					if(times<allowTimes){//验证考试次数是否有剩余
						addJoinNotes();
						window.open("<%=request.getContextPath()%>/myExamManage/gotoMatchTest.action?id="+examId);
					}else{
						window.parent.dialog.alert("当前考试次数已用尽");
					}
				}
			}else if(checkResult && checkResult.approvalStatus==1){
				window.parent.dialog.alert("报名审核中，请耐心等待！");
			}else if(checkResult && checkResult.approvalStatus==3){
				window.parent.dialog.alert("报名审核未通过，不能参加考试！");
			}else if(!checkResult){
				window.parent.dialog.alert("请先报名！");
			}
		}else{//不需要报名则直接参加考试
			//先插入一条exan_assign_info的信息
			var urlStr = "<%=request.getContextPath()%>/MyMegagame/addExamAssignInfoByMatch.action";
			$.ajax({
				type : "POST",
				url : urlStr,
				data : {
					"userId" : userId,
					"examId" : examId
				},
				success : function(data) {
					if (data.rtnResult == "SUCCESS") {
						var assignInfo = getAssignInfoByUserExamId(examId);
						var times = assignInfo.testTimes;
						if(times<allowTimes){
							addJoinNotes();
							window.open("<%=request.getContextPath()%>/myExamManage/gotoMatchTest.action?id="+examId);
						}else{
							window.parent.dialog.alert("当前考试次数已用尽");
						}
					}
				}
			});
		}
	}else{
		window.parent.dialog.alert("非规定时间不可参加考试!");
	}
}
/**
 * 验证参赛申请的审批结果
 */
function checkJoinQualification(){
	var result;
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/isApply.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {
			"userId" : userId,
			"megagameId" : megagameId
		},
		success : function(data) {
			result = data;
		}
	});
	return result;
}
/**
 * 添加参加人员记录信息
 */
function addJoinNotes(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/addJoinNotes.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {
			"userId" : userId,
			"megagameId" : megagameId,
			"matchId" : matchId
		},
		success : function(data) {
		}
	});
}
/**
 * 获取上一赛程的晋级状态
 */
function getLastPromoteInfo(num){
	var resultNum;
	num = num-1;//上一赛程的orderNum
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getLastPromoteInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {
			"megagameId" : megagameId,
			"orderNum" : num
		},
		success : function(data) {
			if(data!=-1){
				resultNum=data;
			}
		}
	});
	return resultNum;
}
/**
 * 生成赛程安排信息
 */
function initMatchPlan() {
	var data = getMatchPlan(matchId);
	var state = data.matchState;
	var examId = data.examId;
	allowTimes = data.allowTimes;
	var orderNum = data.orderNum;
	var isPromote;
	if(orderNum!=1){ //非第一赛程时,获取上一赛程的晋级信息
		isPromote = getLastPromoteInfo(orderNum);
	}
	var matchDiv = $("#matchDiv");
	
	matchDiv.empty();
	var html = "";
	if (data) {
		html = "<div class=\"sc_2\">";
		html += "<h4>赛程安排</h4>";
		html += "<p>考试时长：" + data.duration + "分钟</p>";
		html += "<p>考试次数：" + data.allowTimes + "</p>";
		html += "<p>考试时间：" + getFormatDateByLong(data.beginTime, "yyyy-MM-dd hh:mm") + "至"
				+ getFormatDateByLong(data.endTime, "yyyy-MM-dd hh:mm") + "</p>";
		if (state == 2) { // 正在进行中的赛程
			if(orderNum>1){
				if(isPromote && isPromote==1){
					html += "<a href=\"javascript:void(0);\" class=\"enter\" onclick=\"gotoExam("+examId+")\">进入考试</a>";
				}
			}else{
				html += "<a href=\"javascript:void(0);\" class=\"enter\" onclick=\"gotoExam("+examId+")\">进入考试</a>";
			}
		}
		html += "</div>";
	}
	matchDiv.html(html);
	if (state != 1) {// 非未开始
		$("#joinListDiv").show();
	} else {// 未开始
		$("#joinListDiv").hide();
	}
}
/**
 * 用户id+考试id =>获取参加考试信息
 */
function getAssignInfoByUserExamId(examId){
	 var result;
	 var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAssignInfoByUserExamId.action";
		$.ajax({
			type : "POST",
			async:false,
			url : urlStr,
			data : {
				"userId" : userId,
				"examId" : examId
			},
			success : function(data) {
				result = data;
			}
		});
		return result;
}
/**
 * 生成参赛人员信息grid
 */
function initJoinListGrid() {

	$('#joinUserTable').mmGrid(
					{
						root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
						url : "<%=request.getContextPath()%>/MyMegagame/getJoinListByProcessId.action",
						width : '980px',
						height : 'auto',
						fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
						showBackboard : false,
						checkCol : false,
						multiSelect : true,
						indexCol : true,  // 索引列
						params : function(){
							// 封装参数
							var param = new Object();
							var name = $("#name").val();
							var department = $("#department").val();
							var post = $("#post").val();
							var isPromote = $("#select_1").val();
							param.userId = userId;
							param.megagameId = megagameId;
							param.processId = matchId;
							param.name = name;
							param.department = department;
							param.post = post;
							param.isPromote = isPromote;
							
							return param;
						},
						cols : [ {
							title : 'id',
							name : 'id',
							hidden : true
						}, {
							title : '姓名',
							name : 'userName',
							align : 'center'
						}, {
							title : '岗位',
							name : 'post',
							align : 'center'
						}, {
							title : '部门',
							name : 'departmentName',
							align : 'center'
						}, {
							title : '成绩',
							name : 'score',
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								if (val == -1) {
									str = "-";
								}else{
									str = val;
								}
								return str;
							}
						}, {
							title : '当前排名',
							name : 'rownum',
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								if (val == -1) {
									str = "-";
								}else{
									
									str = val;
								}
								return str;
							}
						}, {
							title : '是否晋级',
							name : 'isPromote',
							align : 'center',
							renderer : function(val, item, rowIndex) {
								var str = "";
								if (val == 0) {
									str = "未晋级";
								} else if (val == 1) {
									str = "已晋级";
								}
								return str;
							}
						} ],
						plugins : [ $('#paginatorPaging').mmPaginator({
							totalCountName : 'total', // 对应MMGridPageVoBean
							// count
							page : 1, // 初始页
							pageParamName : 'page', // 当前页数
							limitParamName : 'pageSize', // 每页数量
							limitList : [ 10, 20, 40, 50 ]
						}) ]
					});
}
/**
 * 搜索
 */
function doSearch() {
	$('#joinUserTable').mmGrid().load({"page":1});
}
/**
 * 重置
 */
function doReset() {
	$("#name").val("");
	$("#department").val("");
	$("#post").val("");
	$("#select_1").val(0);

	$('#joinUserTable').mmGrid().load({"page":1});
}
</script>
</head>
<body>
	<div id="matchDiv" class="cp_2" style="display: block;">
		<div class="sc_2">
			<h4>赛程安排</h4>
			<p>考试时长：3分钟</p>
			<p>考试次数：3</p>
			<p>考试时间：2015-06-20 9：00至2015-07-20 17：00</p>
		</div>
	</div>
	
	<div id="joinListDiv" class="cp_2" style="display: block;">
		<h4 class="cp_list">参赛列表</h4>
		<div class="search_1">
			<span>姓名：<input id="name" type="text" /></span> 
			<span>部门：<input id="department" type="text" /></span> 
			<span>岗位：<input id="post" type="text" /></span> 
			<span>是否晋级： <select id="select_1">
					<option value="" selected="selected">显示全部</option>
					<option value="1">已晋级</option>
					<option value="0">未晋级</option>
			</select>
			</span> <input type="button" class="btn_5" value="查询" onclick="doSearch()"/>
			 <input type="button" class="btn_6" value="重置" onclick="doReset()"/>
		</div>
		
		<div class="grid1">
				<table id="joinUserTable"></table>
				<div id="paginatorPaging" style="text-align: right;margin-right: 20px;"></div>
		</div>
	</div>
</body>
</html>