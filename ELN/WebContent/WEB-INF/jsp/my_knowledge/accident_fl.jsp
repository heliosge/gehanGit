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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.kn_fl .right_fl .fl_gw p{line-height:25px;}
.span_cth{cursor: pointer;}
.head_span{font-weight: bold;padding-left: 5px;cursor: pointer;}
.head_span_p{font-weight: bold;padding-left: 5px;}

.accident_tree {overflow-y:auto;overflow-x:auto;width: 250px;min-height: 530px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
.accident_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
.ztree li span.button.noline_docu{display:none;}
.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}

h3 {
    background: url(<%=request.getContextPath()%>/images/img/ico_4.png) no-repeat left 2px;
    width: 1052px;
    padding-left: 20px;
    color: #3a3a3a;
    border-bottom: 1px solid #cccccc;
    padding-bottom: 10px;
    margin-bottom: 10px;
}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var categoryId = '-2';
var isCloud = '1';
var orderType="asc";

var tempCategory = null;//中间数据存储
var timeFlag = false;//按时间升降排序标识
var evFlag = false;//按评价升降排序标识
var hotFlag = false;//按热门升降排序标识
var types=1;//排序类型（1时间2评价3热门）
var isAccident = 1;

//每页显示数目
var pageSizes = 5;

$(function() {
	initPage();
	//getCategoryInfoById();
	getKnowledgeByCategoryCount();// 默认时间排序

});

var setting = {
		data: {
			key: {url: "xUrl", name:"categoryName"},
			simpleData: {enable: true, pIdKey: "parentId", idKey: "categoryId", }
		},
		check: {enable:  false},
		view: {
			showLine: false,
			showIcon: true,
			selectedMulti: true
		},
		callback: {
			onClick: zTreeOnClick
		}
};

var pageList;

function initPage(){
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/knowledgeLibraryInfo/getAccidentCategory.action",
		success:function(data){
			if (data.rtnResult == "SUCCESS") {
				pageList = data.rtnDataList
				addIconInfo(pageList);
				$.fn.zTree.init($("#treePage"), setting, pageList);
				}
			}
		});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

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
			"isAccident" : isAccident,
			"name" : $("#name").val(),
			isCloud : isCloud
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
			"isAccident" : isAccident,
			"name" : $("#name").val(),
			isCloud : isCloud
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
			"isAccident" : isAccident,
			"name" : $("#name").val(),
			isCloud : isCloud
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
					"isAccident" : isAccident,
					"name" : $("#name").val(),
					isCloud : isCloud
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
 * 获取对应分类的知识资源
 */
 
function loadKlByCategory(id) {
	categoryId = id;
	getKnowledgeByCategoryCount();
	//getCategoryInfoById();
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

function zTreeOnClick(event, treeId, treeNode) {
	loadKlByCategory(treeNode.categoryId);
};

function search(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{name:$("#categoryName").val()},
		url:"<%=request.getContextPath()%>/knowledgeLibraryInfo/getAccidentCategory.action",
		success:function(data){
			if (data.rtnResult == "SUCCESS") {
				addIconInfo(data.rtnDataList);
				$.fn.zTree.init($("#treePage"), setting, data.rtnDataList);
				}
			}
		});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}


</script>
</head>
<body>

<div id="content">
		<!-- <h3>事故案例分析</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">事故案例分析</span>
		</div>
		<div style="width:1066px;">
			<div id="categoryDiv" class="left_fl accident_tree" >
				 <h2>事故分类</h2>
				 <div>
	        		<input type="text" id="categoryName" style="width:170px;height:30px;">
	        		<input style="background: #d60500;color: #fff;width:70px;height:30px;" type="button" value="查询" onclick="search()"/>
	        	 </div>
				 <ul id="treePage" class="ztree" style=""></ul>
			</div>
			<div class="right_fl fl">
				<div class="search_3" style="width: 735px;">
	        		<p>	
		            	案例名称：
		                <input type="text" id="name"/>
	        		</p>
       				<input type="button" value="查询" class="btn_cx" onclick="getKnowledgeByCategoryCount()"/>
        		</div>
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