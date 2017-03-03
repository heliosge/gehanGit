<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<title>大赛首页</title>
<style type="text/css">
.my_font {color: #e10000;font-weight: bold;font-size: 14px}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';//当前大赛id
var megagameInfo = null;//当前大赛信息
$(function() {
	getMegagameInfo();
	initMegagameBase(megagameInfo);
	getAwardsSetting();
	initMatchPlan();
	JudgeApplication();
	getAllMatchPlan();
})

/**
 * 判断能否报名
 */
function JudgeApplication(){
	var toApply = $("#toApply");
	var megagameStatus = megagameInfo.status;//大赛状态
	var needApproval = megagameInfo.needApproval;//是否需要报名
	var html ="";
	toApply.empty();
	if(needApproval==1){
		if(megagameStatus==2 || megagameStatus==1){//进行中、未开始的大赛
			var urlStr = "<%=request.getContextPath()%>/MyMegagame/isApply.action";
			$.ajax({
				type : "POST",
				async:false,
				url : urlStr,
				data : {
					"userId" : userId,
					"megagameId" : megagameId
				},
				success : function(data) {
					if(data){
						var status = data.approvalStatus;
						if(status==1){//待审批
							html ="<h5 >已报名</h5>";
						}else if(status==2){
							html ="<h5 >审批通过</h5>";
						}else if(status==3){//审批拒绝
							html ="<h5 style=\"cursor: pointer;\" onclick=\"doApplication()\">我要报名</h5>";
						}
					}else{
						html ="<h5 style=\"cursor: pointer;\" onclick=\"doApplication()\">我要报名</h5>";
					}
					/* if(data==0){ //未报名
						html ="<h5 style=\"cursor: pointer;\" onclick=\"doApplication()\">我要报名</h5>";
					}else{//已报名
						html ="<h5 >已报名</h5>";
					} */
				}
			});
		}else if(megagameStatus==3){//已结束
			html ="<h5 style=\"background:#DFDFDF;\">报名已结束</h5>";
		}
	}
	toApply.html(html);
}
/**
 * 报名
 */
function doApplication(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/doApply.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"megagameId" : megagameId
		},
		success : function(data) {
			window.parent.dialog.alert("报名成功！");
			JudgeApplication();
		}
	});
	
}


 
/**
 * 获取大赛基础信息
 */
function getMegagameInfo() {
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameInfoById.action";
	$.ajax({
		type : "POST",
		async:false,
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
 * 初始化大赛基础信息html
 */
function initMegagameBase(data) {
	var baseInfoDiv = $("#baseInfoDiv");
	baseInfoDiv.empty();
	var html = "";
	html += "<h4>比赛详情</h4>";
	html += "<p>" + data.detail + "</p>";
	html += "<h4>参赛资格</h4>";
	html += "<p>" + data.qualification + "</p>";
	html += "<h4>参赛流程</h4>";
	html += "<p>" + data.processDescription + "</p>";
	html += "<div class=\"left_bottom1\">";
	html += "<h4>成绩公示</h4>";
	html += "<a href=\"#\"><input type=\"button\" value=\"更多\" onclick=\"toMoreScore()\"/></a>";
	html += getGradeInfoPublic(data);
	baseInfoDiv.html(html);
}


//获取赛程id
function getProcessId(datas) {
	var processId = null;
	if (datas.status == 2) {// 进行中
		processId = datas.curMatchId;
	} else if (datas.status == 3) {// 结束
		var urlStr = "<%=request.getContextPath()%>/MyMegagame/getLastMatchInfoBymegagameId.action";
		$.ajax({
			type : "POST",
			async:false,
			url : urlStr,
			data : {
				"megagameId" : megagameId
			},
			success : function(data) {
				if (data.rtnResult == "SUCCESS") {
					processId = data.rtnData.id;
				}
			}
		});
	}
	return processId;
}
/**
 * 获取成绩公示数据
 */
function getGradeInfoPublic(datas) {
	var results="";
	var processId = getProcessId(datas);
	if (processId != null) {
		var urlStr = "<%=request.getContextPath()%>/MyMegagame/getGradeInfoPublic.action";
		$.ajax({
			type : "POST",
			async : false,
			url : urlStr,
			data : {
				"userId" : userId,
				"processId" : processId,
				"page" : 1,
				"pageSize" : 3
			},
			success : function(data) {
					results = initGrade(data.rows);
			}
		});
	}
	return results;
}

/**
 * 生成成绩公示
 */
function initGrade(list) {
	var htmls = "";
	if (list) {
		var len = list.length;
		htmls += "<table width=\"100%\" class=\"tab_6\" style=\"float: left;\">";
		htmls += "<tr>";
		htmls += "<th width=\"20%\">姓名</th>";
		htmls += "<th width=\"20%\">岗位</th>";
		htmls += "<th width=\"20%\">部门</th>";
		htmls += "<th width=\"20%\">成绩</th>";
		htmls += "<th>当前排名</th>";
		htmls += "</tr>";
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var name = list[i].userName;
			var post = list[i].post;
			var departmentName = list[i].departmentName;
			var score = list[i].score;
			var ranking = i + 1;// 排名
			if(score==-1){//等于-1代表成绩还没有批阅
				score="-";
				ranking="-";
			}
			html = "<tr>";
			html += "<td>" + name + "</td>";
			html += "<td>" + post + "</td>";
			html += "<td>" + departmentName + "</td>";
			html += "<td>" + score + "</td>";
			html += "<td>" + ranking + "</td>";
			htmls += html;
		}
		htmls += "</table>";
	}
	return htmls;
}

/**
 * 成绩公示查看更多
 */
 function toMoreScore(){
	//$('#tab_1', parent.document).find("li[name='score']").click();
	window.parent.location.href ="<%=request.getContextPath()%>/MyMegagame/toMegagameInfo.action?megagameId="+megagameId+"&tabType="+1;
	//控制tab点中成绩公示
}

/**
 * 生成奖项设置
 */
function initAwards(list) {
	var html = "<h4>奖项设置</h4>";
	var awardsDiv = $("#awardsDiv");
	awardsDiv.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var awardName = list[i].awardName;// 奖项名称
			var prizeName = list[i].prizeName;// 奖品名称
			var coverImage = list[i].coverImage;// 奖品图片
			// 测试
			//coverImage = "/images/img/iphone.png";
			html += "<div class=\"jx_1\">";
			html += "<div style=\"float: right;width: 54px;height: 110px;\">";
			html += "<img src=\"" + coverImage+ "\" style=\"width:56px;height:112px;\"/></div>";
			html += "<div><h6>" + awardName + "</h6></div>";
			html += "<div class=\"my_font\"><span>" + prizeName+ "</span></div>";
			html += "</div>";
		}
	}
	awardsDiv.html(html);
}
/**
 * 获取奖项设置数据
 */
function getAwardsSetting() {
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAwardsSetting.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initAwards(data.rtnDataList);
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
			"processId" : matchId,
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
 * 生成大赛首页的赛程安排
 */
function initMatchPlan() {
	//获取赛程安排数据
	var data = getMatchPlan(megagameInfo.curMatchId);
	var matchPlanDiv = $("#matchPlanDiv");
	matchPlanDiv.empty();
	var html = "";
	if (data != null) {
		html += "<h4>赛程安排</h4>";
		html += "<a href=\"#\"><input type=\"button\" value=\"更多\" onclick=\"toMoreMatch()\"/></a>";
		html += "<p>考试时长：" + data.duration + "分钟</p>";
		html += "<p>考试次数：" + data.allowTimes + "</p>";
		html += "<p>考试时间："+ getFormatDateByLong(data.beginTime, "yyyy-MM-dd hh:mm") + "至"
				+ getFormatDateByLong(data.endTime, "yyyy-MM-dd hh:mm")+"</p>";
		html += "<p>晋级人数：" + data.promotionNumber + "人</p>";
	}
	matchPlanDiv.html(html);
}

/**
 * 获取所有赛程安排
 */
function getAllMatchPlan(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameProcessInfo.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initAllMatchPlan(data.rtnDataList);
			}
		}
	});
}
/**
 * 生成更多赛程安排
 */
function initAllMatchPlan(list){
	var html="";
	var moreMatch = $("#moreMatch");
	var divStr1 = "<div class=\"cp_2\" style=\"display:block\">";
	var divStr2 = "<div class=\"sc_2\" style=\"display:block\">";
	var divEndStr = "</div>";
	if(list){
		var len = list.length;
		for(var i=0;i<len;i++){
			var orderNum = list[i].orderNum;
			var allowTimes = list[i].allowTimes;
			var duration = list[i].duration;
			var beginTime = list[i].beginTime;
			beginTime = getFormatDateByLong(beginTime, "yyyy-MM-dd hh:mm");
			var endTime = list[i].endTime;
			endTime = getFormatDateByLong(endTime, "yyyy-MM-dd hh:mm");
			html +=divStr1;
			html +="<h3>赛程"+orderNum+"</h3>";
			html +=divStr2;
			html +="<h4>赛程安排</h4>";
			html +="<p>考试时长："+duration+"分钟</p>";
			html +="<p>考试次数："+allowTimes+"</p>";
			html +="<p>考试时间："+beginTime+"至"+endTime+"</p>";
			html +=divEndStr;
			html +=divEndStr;
		}
			html +="<div><input type=\"button\" value=\"返回\" class=\"back_1\" onclick=\"toBack();\"/></div>";
			moreMatch.html(html);
	}
}
/**
 * 赛程安排 查看更多
 */
function toMoreMatch(){
	$("#cp1").hide();
	$("#moreMatch").show();
}

function toBack(){
	$("#cp1").show();
	$("#moreMatch").hide();
}
</script>
</head>
<body>
	<div class="cp_1" id="cp1" style="display: block;">
		<div id="baseInfoDiv" class="left_cp1"></div>
		<div class="right_cp1">
			<div id="toApply"></div>
			<div id="awardsDiv" class="jx"></div>
			<div id="matchPlanDiv" class="sc_1"></div>
		</div>
	</div>
	
	<div id="moreMatch" style="display: none"></div>	
</body>
</html>