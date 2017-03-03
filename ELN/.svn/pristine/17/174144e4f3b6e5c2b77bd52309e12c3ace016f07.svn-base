<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>获奖公示</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
.cp_5 .tab_1 .b_name .left_name{background: none;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var megagameId = '${megagameId}';// 当前大赛id
var matchId = '${matchId}';// 当前赛程id

$(function() {
	getAllMatchBymegagameId();
})

/**
 * 左侧tab样式控制 自定义
 */
function tabStyle() {
	$("#sc").find("li").click(function() {
				$("#sc").find("li").each(function() {
					$(this).removeAttr('class');
					$(this).find("img").remove();
				});
				$(this).attr('class', 'active_b');
				$(this).append("<img src=\"<%=request.getContextPath()%>/images/img/ico_21.png\" />");
	});
}

/**
 * 获取大赛所有赛程信息
 */
function getAllMatchBymegagameId() {
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAllMatchBymegagameId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initMatchTab(data.rtnDataList);
			}
		}
	});
}

function initMatchTab(list) {
	var matchUl = $("#sc");
	matchUl.empty();
	var html = "";
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var matchNum = list[i].orderNum;
			if (i == 0) {
				html += "<li class=\"active_b\" onclick=\"getAwareUser()\">最终获奖者<img src=\"<%=request.getContextPath()%>/images/img/ico_21.png\" /></li>";
			}
			html += "<li onclick=\"turnMatch(" + list[i].id + ")\" >赛程" + matchNum + "晋级者</li>"
		}
	}
	matchUl.html(html);
	matchUl.find("li").eq(0).click();
	tabStyle();
}
/**
 * 查看赛程下的晋级者
 */
function turnMatch(matchId){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getPromoteInfoByProcessId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"processId" : matchId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initPormoteInfo(data.rtnDataList);
			}
		}
	});
}
/**
 * 生成赛程下的晋级者信息
 */
function initPormoteInfo(list){
	var awareInfoDiv = $("#awareInfoDiv");
	var html ="";
	if(list){
		var len = list.length;
		for(var i = 0;i<len;i++){
			if (!list[i]) {
				continue;
			}
			var userName = list[i].userName;
			var score = list[i].score;
			var post = list[i].post;
			var departmentName = list[i].departmentName;
			var headImg = list[i].headImg;
			html += "<div class=\"b_name\">";
			html += "<div class=\"left_name\">";
			html += "<div style=\"float: left;margin-top: -125px;margin-left: 35px\"><img style=\"width:90px;height:90px;\" src=\""+headImg+"\"/></div>";
			html +="<div style=\"float: left;\">";
			html += "<p><span>获奖者：" + userName + "</span><span>成绩："+ score + "分</span></p>";
			html += "<p><em>部门：" + departmentName + "</em><em>岗位："+ post + "</em></p>";
			html += "</div>";
			html += "</div>";
			html += "</div>";
		}
	}
	awareInfoDiv.html(html);
}
/**
 * 获取最终大赛获奖者信息
 */
function getAwareUser() {
	var awards = getAwardsSetting();
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getShowWinAwards.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initAwardsInfo(awards, data.rtnDataList);
			}
		}
	});
}
/**
 * 生成最终获奖者信息
 */
function initAwardsInfo(awardsList, list) {
	var awareInfoDiv = $("#awareInfoDiv");
	var html = "<h5>企业竞争力大赛获奖名单（排名不分先后）</h5>";
	awareInfoDiv.empty();
	if (list) {
		var len = list.length;// 最终晋级的
		var alen = awardsList.length;// 奖项个数
		var index = 0;
		for ( var j = 0; j < alen; j++) {
			if (!awardsList[j]) {
				continue;
			}
			var awareName = awardsList[j].awardName;// 奖项名称
			var totalCount = awardsList[j].totalCount;// 奖项数量
			var count = 0;

			if (index < len) {
				for ( var i = index; i < len;) {
					if (!list[i]) {
						continue;
					}
					var userName = list[i].userName;
					var score = list[i].score;
					var post = list[i].post;
					var departmentName = list[i].departmentName;
					var headImg = list[i].headImg;
					count++;
					if (count <= totalCount) {
						html += "<h6>" + awareName + "</h6>";
						html += "<div class=\"b_name\">";
						html += "<div class=\"left_name\">";
						html += "<div style=\"float: left;margin-top: -125px;margin-left: 35px\"><img style=\"width:90px;height:90px;\" src=\""+headImg+"\"/></div>";
						html +="<div style=\"float: left;\">";
						html += "<p><span>获奖者：" + userName + "</span><span>成绩："+ score + "分</span></p>";
						html += "<p><em>部门：" + departmentName + "</em><em>岗位："+ post + "</em></p>";
						html += "</div>";
						html += "</div>";
						html += "</div>";
					} else {
						break;
					}
					i++;
					index = i;
				}
			} else {
				break;
			}
		}
	} else {
		html += "<h5>暂无数据</h5>";
	}
	awareInfoDiv.html(html);
}
/**
 * 获取奖项设置数据
 */
function getAwardsSetting() {
	var result = null;
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getAwardsSetting.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			async : false,
			data : {
				"megagameId" : megagameId
			},
			success : function(data) {
				if (data.rtnResult == "SUCCESS") {
					result = data.rtnDataList;
				}
			}
		});
		return result;
	}
</script>
</head>
<body>
	<div class="cp_5" id="cp_5" style="display: block;">
		<div class="left_ul" id="left_ul">
			<ul id="sc"></ul>
		</div>
		<div id="awareInfoDiv" class="tab_1 fr" style="width: 836px;"></div>
	</div>
</body>
</html>