<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进入比赛（考试）-进行中</title>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<style type="text/css">
.my_font {
	color: #e10000;
	font-weight: bold;
	font-size: 14px
}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';// 当前大赛id
var megagameInfo = null;//当前大赛信息
var tabType = '${tabType}';//跳转标识：1代表成绩公示的跳转

$(function() {
	getMegagameInfo();
	getAllMatchBymegagameId();
	if(tabType=="1"){//控制iframe大赛首页中成绩公示更多按钮的跳转到父页面的成绩公示
		$('#tab_1').find("li[name='score']").click();
		return ;
	}
	$("#myFrame").attr("src","<%=request.getContextPath()%>/MyMegagame/toMegagameHome.action?megagameId="+megagameId);
})
/**
 * tab样式控制 自定义
 */
function tabStyle(){
	$("#tab_1").find("li").click(function(){
		$("#tab_1").find("li").each(function(){
			$(this).removeAttr('class');
		});
		$(this).attr('class','active_c');
	});
}
/**
 * 获取大赛所有赛程信息
 */
function getAllMatchBymegagameId(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAllMatchBymegagameId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initTabInfo(data.rtnDataList);
			}
		}
	});
}
function toHome(){
	$("#myFrame").attr("src","<%=request.getContextPath()%>/MyMegagame/toMegagameHome.action?megagameId="+megagameId);
}
/**
 * 生成tab
 */
function initTabInfo(list) {
	var html = "";
	var tab1Div = $("#tab_1");
	tab1Div.empty();
	if (list) {
		var len = list.length;
		var textArr = [ "", "一", "二", "三" ];
		html += "<li onclick=\"toHome()\" class=\"active_c\">大赛首页</li>";
		if(megagameInfo.status==2){//大赛进行中
			for ( var i = 0; i < len; i++) {
				if (!list[i]) {
					continue;
				}
				if (list[i].orderNum) {
					html += "<li onclick=\"toMatch(" + list[i].id + ")\">赛程"+ textArr[list[i].orderNum] + "</li>";
				}
			}
			html += "<li name=\"score\" onclick=\"toPublicScore(" + megagameId + ")\">成绩公示</li>";
		}else if(megagameInfo.status==3){//已结束
			html += "<li onclick=\"toPublicAward(" + megagameId + ")\">获奖公示</li>";
			html += "<li name=\"score\" onclick=\"toPublicScore(" + megagameId + ")\">成绩公示</li>";
		}
		html += "<li onclick=\"toInformation(" + megagameId + ")\">比赛资讯</li>";
	}
	tab1Div.html(html);
	tabStyle();
}
/**
 * 跳转赛程tab
 */
function toMatch(matchId){
	$("#myFrame").attr("src","<%=request.getContextPath()%>/MyMegagame/toMatchPage.action?matchId="+matchId+"&megagameId="+megagameId);
}
/**
 * 跳转成绩公示
 */
function toPublicScore(){
	$("#myFrame").attr("src","<%=request.getContextPath()%>/MyMegagame/toMegagameScore.action?megagameId="+megagameId);
}
/**
 * 跳转资讯
 */
function toInformation(){
	$("#myFrame").attr("src","<%=request.getContextPath()%>/MyMegagame/toMegagameInformation.action?megagameId="+megagameId);
}
/**
 * 跳转获奖公示
 */
function toPublicAward(){
	$("#myFrame").attr("src","<%=request.getContextPath()%>/MyMegagame/toMegagameAware.action?megagameId="+megagameId);
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
</script>
</head>
<body>
<div id="course_all">
            <div class="notes_list fl">
            	<!-- <h3 class="score">查看比赛</h3> -->
            	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
					<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
					<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看比赛</span>
				</div>
                <div  class="tab_5"  id="tab" style="height: 800px;width: 1060px">
                	<ul id="tab_1"></ul>
                	<iframe id="myFrame" width="100%" height="100%" src="" frameborder="0"></iframe>
        		</div>
        		</div>
</div>
</body>
</html>
