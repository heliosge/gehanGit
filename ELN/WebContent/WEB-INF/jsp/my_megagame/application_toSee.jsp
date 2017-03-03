<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的报名-查看详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">

.cp_1 .left_cp1 {padding-top: 0px;}
.my_font {color: #e10000;font-weight: bold;font-size: 14px}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';
var id = '${id}';
$(function(){
	getMegagameInfo();
	getAwardsSetting();
	getMyApplicationInfoById();
});
/**
 * 获取点击详情信息
 */
function getMyApplicationInfoById(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMyApplicationInfoById.action";
	$.ajax({
		type : "POST",
		async:false,
		url : urlStr,
		data : {
			"id" : id
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initApplicationInfo(data.rtnData);
			}
		}
	});
}
/**
 * 生成报名详情
 */
function initApplicationInfo(data){
	var yj = $("#yj");
	var html="";
	var applyTime = "";//报名时间
	var status;//审批状态
	var statusText = "";//审批状态
	var refuseReason = "";//拒绝理由
	 if(data){
		applyTime = data.applyTime;
		applyTime = getFormatDateByLong(applyTime, "yyyy-MM-dd hh:mm:ss");
		status = data.approvalStatus;
		if(status==1){
			statusText = "待审批";
		}else if(status==2){
			statusText = "审批通过";
		}else if(status==3){
			statusText = "审批拒绝";
		}
		
		refuseReason = data.refuseReason;
	} 
	html +="<p>报名时间："+applyTime+"</p>";
	html +="<p>审批状态："+statusText+"</p>";
	if(status==3){//拒绝要有拒绝理由
		html +="<p>拒绝理由："+refuseReason+"</p>";
	}
	yj.empty();
	yj.html(html);
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
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initMegagameBase(data.rtnData);
			}
		}
	});
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
			html += "<img style=\"width: 54px;height: 110px;\" src=\"" + coverImage + "\"/></div>";
			html += "<div><h6>" + awardName + "</h6></div>";
			html += "<div class=\"my_font\"><span>" + prizeName + "</span></div>";
			html += "</div>";
			}
		}
		awardsDiv.html(html);
	}
	/**
	 * 初始化大赛基础信息html
	 */
	function initMegagameBase(data) {
		var baseInfoDiv = $("#baseInfoDiv");
		var com_title = $("#com_title");
		var megagameName = data.name;
		var createTime = data.createTime;
		createTime = getFormatDateByLong(createTime, "yyyy-MM-dd hh:mm:ss");
		var titleHtml = "";
		titleHtml += "<h4>" + megagameName + "</h4>";
		titleHtml += "<h5>创建时间：" + createTime + "</h5>";
		com_title.empty();
		com_title.html(titleHtml);
		var html = "";
		html += "<h4>比赛详情</h4>";
		html += "<p>" + data.detail + "</p>";
		html += "<h4>参赛资格</h4>";
		html += "<p>" + data.qualification + "</p>";
		html += "<h4>参赛流程</h4>";
		html += "<p>" + data.processDescription + "</p>";
		baseInfoDiv.empty(html);
		baseInfoDiv.html(html);
	}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list fl">
			<!-- <h3 class="score">详情</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">详情</span>
			</div>
			<div class="yj" id="yj">
				<p>报名时间：2015-8-8 12:00:00</p>
				<p>审批状态：审批拒绝</p>
				<p>拒绝理由：这里是拒绝理由这里是拒绝理由这里是拒绝理由这里是拒绝理由这里是拒绝理由</p>
			</div>
			<div class="com_title" id="com_title">
				<h4>大赛名称大赛名称</h4>
				<h5>创建时间：2015-8-7 12:00:00</h5>
			</div>
			<div class="tab_5" id="tab">
				<div class="cp_1" style="display: block;">
					<div class="left_cp1" id="baseInfoDiv"></div>

					<div class="right_cp1">
						<div class="jx" id="awardsDiv"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>