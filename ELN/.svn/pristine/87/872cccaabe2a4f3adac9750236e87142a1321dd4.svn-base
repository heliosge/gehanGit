<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>按分类查看知识</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.kn_fl .right_fl .fl_gw p{line-height:25px;}
.span_cth{cursor: pointer;margin-right: 15px}
.head_span{font-weight: bold;padding-left: 5px;cursor: pointer;}
.head_span_p{font-weight: bold;padding-left: 5px;}
.kn_fl .left_fl h4{width: 280px}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var categoryId = '${categoryId}';
var isCloud = '${isCloud}';
var orderType="asc";

var tempCategory = null;//中间数据存储
var timeFlag = false;//按时间升降排序标识
var evFlag = false;//按评价升降排序标识
var hotFlag = false;//按热门升降排序标识
var types=1;//排序类型（1时间2评价3热门）

//每页显示数目
var pageSizes = 5;

$(function() {
	getKnowledgeCategory(isCloud);// 知识分类
	getCategoryInfoById();
	getKnowledgeByCategoryCount();// 默认时间排序

});

/**
 * 根据分类类别获取知识信息数目(默认按时间升序)
 */
function getKnowledgeByCategoryCount() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeByCategoryCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"categoryId" : categoryId,
			"orderType" : orderType,
			"isAccident" : 0,
			"isCloud":isCloud
		},
		success : function(data) {
			insertPage(data, pageSizes);
		}
	});
}
/**
 * 根据分类类别 =>按评价 获取知识信息数目
 */
function getKnowledgeOrderByEvaluateCount() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeOrderByEvaluateCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"categoryId" : categoryId,
			"orderType" : orderType,
			"isAccident" : 0,
			"isCloud":isCloud
		},
		success : function(data) {
			insertPage(data, pageSizes);
		}
	});
}

/**
 * 根据分类类别 =>按热门 获取知识信息数目
 */
function getKnowledgeOrderByHotCount() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeOrderByHotCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"categoryId" : categoryId,
			"orderType" : orderType,
			"isAccident" : 0,
			"isCloud":isCloud
		},
		success : function(data) {
			insertPage(data, pageSizes);
		}
	});
}
// 插入分页插件
function insertPage(sum, pageSize) {
	// 默认时间
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeByCategory.action";
	if (types == 2) {// 评价
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeOrderByEvaluate.action";
	} else if (types == 3) {// 热门
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeOrderByHot.action";
	}
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
					"categoryId" : categoryId,
					"page" : page + 1,
					"pageSize" : pageSize,
					"orderType" : orderType,
					"isAccident" : 0,
					"isCloud":isCloud
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
 * 查看知识详细内容
 */
 function toSeeKl(knowledgeId){
	 window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+ knowledgeId;
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
			if (extendName && extendName.startWith("doc")) {
				imgIco += "word.png";
			} else if (extendName && extendName.startWith("pdf")) {
				imgIco += "pdf.png";
			} else if (extendName && extendName.startWith("xls")) {
				imgIco += "excel.png";
			} else if (extendName && extendName.startWith("ppt")) {
				imgIco += "ppt.png";
			} else if (extendName && extendName.startWith("txt")) {
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
 * 根据类别id获取类别信息
 */
function getCategoryInfoById() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getCategoryInfoById.action";
	$.ajax({
		type : "POST",
		data : {
			"categoryId" : categoryId
		},
		url : urlStr,
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				var htmlHead = "";
				var html = "";
				var headDiv = $("#headDiv");
				headDiv.empty();
				var spiltStr = "<span class=\"head_span_p\">></span>";
				htmlHead += "<span class=\"head_span\" onclick=\"loadKlByCategory('')\">全部分类 </span> ";
				var results = data.rtnData;
				if(results){
					var childList = results.childCategory;//子级
					var subList = results.subCategory;//父级
					var parentCategoryName = "";
					var currentCategoryName = "";
					if(subList){
						var len = subList.length;
						for(var q=len-1;q>=0;q--){//倒序 生成分类小导航
							htmlHead += spiltStr;
							parentCategoryId = subList[q].categoryId;
							parentCategoryName =subList[q].categoryName;
							htmlHead += "<span class=\"head_span\" onclick=\"loadKlByCategory("+parentCategoryId+")\" >"+parentCategoryName+"</span>";
						}
					}
					//加上当前类别
					currentCategoryId = results.categoryId;
					currentCategoryName = results.categoryName;
					htmlHead += spiltStr;
					htmlHead += "<span class=\"head_span\" onclick=\"loadKlByCategory("+currentCategoryId+")\">"+currentCategoryName+"</span>";
					
					//生成当前类别下的子分类信息
					html += "<div class=\"fl_gw fl\" style=\"overflow-y: scroll;overflow-x: hidden;\">";
					html += "<p style=\"text-align: left;\">";
					if(childList){
						var len2 = childList.length;
						for(var i=0;i<len2;i++){
							var cId = childList[i].categoryId;//分类id
							var cName =  childList[i].categoryName;
							html += "<span class=\"span_cth\" onclick=\"loadKlByCategory("+cId+")\">"+cName+"</span>&nbsp;";
							if(i!=0 && i%6==0){
								html +="<br/>";
							}
						}
					}
					html += "</div>";
					html += "</p>";
				}else{
					var lens = tempCategory.length;
					html += "<div class=\"fl_gw fl\">";
					html += "<p>";
					for(var k=0;k<lens;k++){
						var cId = tempCategory[k].categoryId;  //分类id
						var cName =  tempCategory[k].categoryName;
						html += "<span class=\"span_cth\" onclick=\"loadKlByCategory("+cId+")\">"+cName+"</span>&nbsp;&nbsp;";
					}
					html += "</div>";
					html += "</p>";
				}
				headDiv.html(htmlHead+html);
			}
		}
	});
}
/**
 * 获取知识分类数据
 */
function getKnowledgeCategory(isCloud) {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeCategory.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async : false,
		data : {
			"userId" : userId,
			"isCloud" : isCloud
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				tempCategory = data.rtnDataList;
				initCategory(data.rtnDataList);
			}
		}
	});
}

/**
 * 生成知识分类页面
 */
function initCategory(list) {
	var html = "";
	var categoryDiv = $("#categoryDiv");
	html += "<h4>知识分类</h4>";
	categoryDiv.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var id = list[i].categoryId;
			var name = list[i].categoryName;
			var childCategory = list[i].childCategory;
			var len2 = childCategory.length;
			html += "<div class=\"fl_one\">";
			html += "<h5><a href=\"javascript:void(0);\" onclick=\"loadKlByCategory("
					+ id + ")\">" + name + "</a></h5>";
			html += "<p style=\"line-height:1.5;\">";
			if (len2 && len2 > 0) {
				for ( var j = 0; j < len2; j++) {
					var childName = childCategory[j].categoryName;
					id = childCategory[j].categoryId;
					html += "<a href=\"javascript:void(0);\" onclick=\"loadKlByCategory("
							+ id + ")\">" + childName + "</a>";
					html += "<br/>";
				}
			} else {
				html += "";
			}
			html += "</p>";
			html += "</div>";
		}
	}
	categoryDiv.html(html);
}
/**
 * 获取对应分类的知识资源
 */
 
function loadKlByCategory(id) {
	categoryId = id;
	getKnowledgeByCategoryCount();
	getCategoryInfoById();
}

/**
 * 排序
 */
function orderByType(type,obj) {
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
		
		getKnowledgeByCategoryCount();
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
		getKnowledgeOrderByEvaluateCount();
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
		getKnowledgeOrderByHotCount();
	}
}
</script>
</head>
<body>

<div id="content_i">
		<div class="kn_fl" style="width: auto;">
			<div id="categoryDiv" class="left_fl" style="text-align:center;overflow-y: scroll;overflow-x: hidden;width:280px;height: 550px;margin-left: -100px;"></div>
			<div class="right_fl fl">
				<div id="headDiv"></div>
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