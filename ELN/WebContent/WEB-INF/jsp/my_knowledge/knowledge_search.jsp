<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>知识-搜索</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />

<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var search_extendName = '${extendName}';// 搜索的知识文件后缀类型
var search_name = '${knowledgeName}';// 搜索的知识名称
search_name = $.trim(search_name);
var types=1;//排序类型（1时间2评价3热门）
var timeFlag = false;//按时间升降排序标识
var evFlag = false;//按评价升降排序标识
var hotFlag = false;//按热门升降排序标识
var orderType="asc";
//每页显示数目
var pageSizes = 5;

$(function() {
	initSearch();
	doSearch();
});	
/**
 * 初始化搜索条件
 */
function initSearch(){
	$("#searchName").val(search_name);
	$("input[name='ra_1']").each(function(i){
		if($(this).val()==search_extendName){
			$(this)[0].checked=true;
		}
	});
}
/**
 * 执行搜索
 */
function doSearch(){
	search_name = $("#searchName").val();
	$("input[name='ra_1']").each(function(i){
		if($(this)[0].checked){
			search_extendName = $(this).val();
		}
	});
	getSearchKnowledgeCount();
}


/**
 * 默认按时间
 */
function getSearchKnowledgeCount(){
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getSearchKnowledgeCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"extendName" : search_extendName,
			"knowledgeName" : escapeForSql($.trim(search_name))
		},
		success : function(data) {
			insertPage(data, pageSizes);
		}
	});
}
/**
 * 按评价
 */
function getSearchKnowledgeByEvaluateCount(){
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getSearchKnowledgeByEvaluateCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"extendName" : search_extendName,
			"knowledgeName" : search_name
		},
		success : function(data) {
			insertPage(data, pageSizes);
		}
	});
}
/**
 * 按热门
 */
function getSearchKnowledgeByHotCount(){
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getSearchKnowledgeByHotCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"extendName" : search_extendName,
			"knowledgeName" : search_name
		},
		success : function(data) {
			insertPage(data, pageSizes);
		}
	});
}
//插入分页插件
function insertPage(sum, pageSize) {
	// 默认时间
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getSearchKnowledge.action";
	if (types == 2) {// 评价
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getSearchKnowledgeByEvaluate.action";
	} else if (types == 3) {// 热门
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getSearchKnowledgeByHot.action";
	}
	$("#jquery_page").show();
	// 插入分页插件
	$("#jquery_page").pagination(sum, {
		prev_text : '上一页',
		next_text : '下一页',
		items_per_page : pageSize, // 每页显示的个数
		num_display_entries : 10, // 中间显示的页数
		current_page : 0, // 初始页
		num_edge_entries : 2, // 两侧显示的首尾分页的条目数
		callback : function(page) {
			$.ajax({
				type : "POST",
				url : urlStr,
				data : {
					"userId" : userId,
					"extendName" : search_extendName,
					"knowledgeName" : search_name,
					"page" : page + 1,
					"pageSize" : pageSize,
					"orderType" : orderType
				},
				dataType : "json",
				success : function(data) {
					results = data.rtnDataList;
					initKnowledgeList(results);
				}
			});
		}
	});
}
/**
 * 生成知识信息列表
 */
function initKnowledgeList(list) {
	var html = "";
	var contentDiv = $("#contentDiv");
	contentDiv.empty();
	if (list && list.length > 0) {
		$("#orderDiv").show();
		$("#jquery_page").show();
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var knowledgeId = list[i].knowledgeId;
			var knowledgeName = list[i].knowledgeName;
			var evaluateCount = list[i].evaluateCount;// 评价人数
			var avgScore = list[i].avgScore;// 平均评分
			var createTime = list[i].createTime;// 贡献时间
			createTime = getFormatDateByLong(createTime, "yyyy-MM-dd hh:mm:ss");
			var createUserName = list[i].createUserName;// 贡献人
			var extendName = list[i].extendName == null ? "txt"
					: list[i].extendName;// 文件后缀
			var starId = "star_" + knowledgeId;
			var imgIco = "";
			if (extendName.startWith("doc")) {
				imgIco += "word.png";
			} else if (extendName.startWith("pdf")) {
				imgIco += "pdf.png";
			} else if (extendName.startWith("xls")) {
				imgIco += "excel.png";
			} else if (extendName.startWith("ppt")) {
				imgIco += "ppt.png";
			} else if (extendName.startWith("txt")) {
				imgIco += "txt.png";
			} else {
				imgIco += "txt.png";
			}
			html += "<div class=\"fl_txt fl\">";
			html += "<p style=\"float: left;\"><img src=\"<%=request.getContextPath()%>/images/img/"
					+ imgIco + "\" />";
			html += "<a href=\"javascript:void(0);\" onclick=\"toSeeKl("+knowledgeId+")\">" + knowledgeName + "</a>";
			html += "<em>" + Number(avgScore).toFixed(1) + "</em>";
			html += "<div style=\"float: left;\" id=\"" + starId + "\"></div>";
			html += "<strong style=\"font-weight: normal;color: #999;\">（"
					+ evaluateCount + "人已评价）</strong>";
			html += "</p>";
			html += "<h6>" + createUserName + " 贡献于" + createTime + "</h6>";
			contentDiv.append(html);
			html = "";
			var setting = {
				max : 5,
				value : avgScore,
				image : "../js/rater-star/star.gif",
				enabled : false
			};
			$("#" + starId + "").rater(setting);
		}
	} else {// 无数据隐藏 排序和分页
		$("#orderDiv").hide();
		$("#jquery_page").hide();
	}
}

/**
 * 排序
 */
/* function orderByType(type,obj) {
	if (type == 1) {// 按时间
		types = 1;
		if (timeFlag) {
			orderType = "asc";
			timeFlag = false;
			$(obj).parent().css("background","url(../images/img/bg_9.png) no-repeat 60px center");
		} else {
			orderType = "desc";
			timeFlag = true;
			$(obj).parent().css("background","url(../images/img/bg_9down.png) no-repeat 60px center");
		}
		getSearchKnowledgeCount();
	} else if (type == 2) {// 按评价
		types = 2;
		if (evFlag) {
			orderType = "asc";
			evFlag = false;
			$(obj).parent().css("background","url(../images/img/bg_9.png) no-repeat 60px center");
		} else {
			orderType = "desc";
			evFlag = true;
			$(obj).parent().css("background","url(../images/img/bg_9down.png) no-repeat 60px center");
		}
		getSearchKnowledgeByEvaluateCount();
	} else if (type == 3) {// 按热门
		types = 3;
		if (hotFlag) {
			orderType = "asc";
			hotFlag = false;
			$(obj).parent().css("background","url(../images/img/bg_9.png) no-repeat 60px center");
		} else {
			orderType = "desc";
			hotFlag = true;
			$(obj).parent().css("background","url(../images/img/bg_9down.png) no-repeat 60px center");
		}
		getSearchKnowledgeByHotCount();
	}
} */

/**
 * 查看知识详细内容
 */
 function toSeeKl(knowledgeId){
	 window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+ knowledgeId;
}
/**
 * 按下
 */
function key_down(key){
	if(key==13){
		doSearch();
	}
}
</script>
</head>
<body >
	<div id="content_i">
		<div class="know">
			<!-- <h4>知识</h4> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">知识</span>
			</div>
			<div class="left_know">
				<div class="search_4" style="width: 1026px;">
					<div class="sch_top">
						<input type="text" class="txt_2" id="searchName" onkeydown="key_down(event.keyCode);"/> <input type="button" value="查询" onclick="doSearch()"/>
					</div>
					<div class="sch_bot">
						<input type="radio" name="ra_1" value="" checked="checked"/> <span>全选</span> 
						<input type="radio" name="ra_1" value="doc"/> <span>doc</span> 
						<input type="radio" name="ra_1" value="docx"/> <span>docx</span> 
						<input type="radio" name="ra_1" value="ppt"/> <span>ppt</span> 
						<input type="radio" name="ra_1" value="pptx"/> <span>pptx</span> 
						<input type="radio" name="ra_1" value="xls"/> <span>xls</span> 
						<input type="radio" name="ra_1" value="xlsx"/> <span>xlsx</span> 
						<input type="radio" name="ra_1" value="txt"/> <span>txt</span> 
						<input type="radio" name="ra_1" value="pdf"/> <span>pdf</span>
					</div>
				</div>

			</div>
			<div class="right_fl_1 fl">
				<div id="orderDiv">
					<div class="sor_1">
						<a href="javascript:void(0);" onclick="orderByType(1,this)">按时间</a>
					</div>
					<div class="sor_1">
						<a href="javascript:void(0);" onclick="orderByType(2,this)">按评价</a>
					</div>
					<div class="sor_1">
						<a href="javascript:void(0);" onclick="orderByType(3,this)">按热门</a>
					</div>
				</div>
				<div id="contentDiv"></div>
				<div id="jquery_page" style="margin-top: 20px; margin-left: 60px; float: right; display: none;" class="pagination"></div>
			</div>
		</div>
	</div>
</body>
</html>