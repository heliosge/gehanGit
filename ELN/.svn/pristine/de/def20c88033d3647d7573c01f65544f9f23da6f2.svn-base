<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的知识</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.know .right_know .right_b ul li .li_active .right_active{background:none;}

.know .right_know .right_b ul li .li_active .right_active p{width: 200px;}
.know .right_know .right_b ul li .li_not .right_not p{width: inherit;}
.know .left_know .k_all .all_ul ul a{margin-right: 20px;}
</style>
<script>
var userId = '${USER_ID_LONG}';// current user
var isCloud=1;//isCloud:1:当前公司全部分类2：云知识
$(function() {
	getKnowledgeCategory(isCloud);// 知识分类
	getKnowledgeCount();// 知识数量统计
	getMyKnowledgeCount();// 我的知识数量
	getKnowledgeContributor();// 突出贡献者
	toClickFirst();
});

/**
 * 原型遗留样式
 */
function oldStyle() {
	$('#know').find('li').click(function() {
		$('#know').find('li').attr('class', '');
		$(this).attr('class', 'li_i');

	})
}
/**
 * 默认点中部门知识
 */
function toClickFirst() {
	$("#all_tb").children().eq(0).click();// 初始化点中部门知识
}
/**
 * 获取部门知识数据
 */
 
<%-- function getDepartmentKnowledge() {
	var result = null;
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getDepartmentKnowledge.action";
	$.ajax({
		type : "POST",
		async : false,
		url : urlStr,
		data : {
			"userId" : userId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				result = data.rtnDataList;
			}
		}
	});
	return result;
} --%>



/**
 * 获取推荐知识数据
 */
function getRecommendKnowledge() {
	var result = null;
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getRecommendKnowledge.action";
	$.ajax({
		type : "POST",
		async : false,
		url : urlStr,
		data : {
			"userId" : userId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				result = data.rtnDataList;
			}
		}
	});
	return result;
}
/**
 * 获取突出贡献者
 */
function getKnowledgeContributor() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeContributor.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initContributor(data.rtnDataList);
			}
		}
	});
}
/**
 * 生成 突出贡献者
 */
function initContributor(list) {
	var html = "";
	var conDiv = $("#know");
	conDiv.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var userId2 = list[i].createUserId;
			var userName = list[i].userName;
			var headImg = list[i].headImg;
			var orderNum = list[i].orderNum;
			var counts = list[i].counts;
			
			html += "<li class=\"\">";
			html += "<div class=\"li_active\">";
			html += "<div class=\"left_active\">";
			html += "<span>" + orderNum + "</span>";
			html += "</div>";
			html += "<div class=\"right_active\">";
			html += "<div style=\"float: left;margin-left: -55px;\"><img style=\"width:41px;height:41px;\" src=\""+ headImg + "\"></div>";
			html += "<p>" + userName + "<span>（" + counts + "篇）</span>";
			if(userId2==userId){//排行中为当前用户
				html += "<a href=\"javascript:void(0);\" onclick=\"seeKl(" + userId2
				+ ")\">查看我的知识</a>";
			}else{
				html += "<a href=\"javascript:void(0);\" onclick=\"seeKl(" + userId2+",'"+userName
						+ "')\">查看TA的知识</a>";
			}
			
			html += "</div>";
			html += "</div>";

			html += "<div class=\"li_not\">";
			html += "<div class=\"left_not\">";
			html += "<span>" + orderNum + "</span>";
			html += "</div>";
			html += "<div class=\"right_not\">";
			html += "<p>" + userName + "<span>（" + counts + "篇）</span></p>";
			html += "</div>";
			html += "</div>";

			html += "</li>";
		}
	}
	conDiv.html(html);
	oldStyle();
	// 默认点击第一个
	conDiv.find("li").eq(0).click();

}
/**
 * 查看TA的知识入口
 */
function seeKl(id) {
	if(id==userId){//为当前用户自己，跳转我的知识中心
		window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action";
	}else{
		window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeOthers.action?othersId="+id;
	}
}
/**
 * 获取我贡献的知识数量
 */
function getMyKnowledgeCount() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getMyKnowledgeCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data :{"userId":userId},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				var html = "<p>我贡献了<span>" + data.count + "</span>篇知识</p>";
				var mineDiv = $("#mine");
				mineDiv.empty();
				mineDiv.html(html);
			}
		}
	});
}
/**
 * 获取知识统计
 */
function getKnowledgeCount() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data :{"userId":userId},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				initKnowledgeCount(data.rtnDataList);
			}
		}
	});
}

/**
 * 生成知识统计
 */
function initKnowledgeCount(list) {
	var html = "";
	var rightDiv = $("#right_t");
	rightDiv.empty();
	var allCount = 0;
	var articleCount = 0;
	var documentCount = 0;
	var videoCount = 0;
	if (list && list.length > 0) {
		allCount = list[0].allCount;
		articleCount = list[0].articleCount;// 文章
		documentCount = list[0].documentCount;// 文档
		videoCount = list[0].videoCount;// 视频
	}
	html += "<h5>共有" + allCount + "篇知识</h5>";
	/* html += "<div class=\"t_1\">";
	html += "<p>" + videoCount + "</p>";
	html += "<p>视频</p>";
	html += "</div>"; */
	html += "<div class=\"t_1\">";
	html += "<p>" + documentCount + "</p>";
	html += "<p>文档</p>";
	html += "</div>";
	html += "<div class=\"t_1\">";
	html += "<p>" + articleCount + "</p>";
	html += "<p>文章</p>";
	html += "</div>";

	rightDiv.html(html);

}
/**
 * 获取知识分类数据
 */
function getKnowledgeCategory(isCloud) {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getKnowledgeCategory.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data:{
			"userId":userId,
			"isCloud":isCloud
			},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
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
	var allDiv = $("#all_ul");
	html += "<ul>";
	allDiv.empty();
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
			html += "<li>";
			html += "<h4><a href=\"javascript:void(0);\" onclick=\"loadKlByCategory("+ id + ")\">" + name + "</a></h4>";
			html += "<div style=\"line-height: 1.8;\">";
				
			if (len2 && len2 > 0) {
				for ( var j = 0; j < len2; j++) {
					var childName = childCategory[j].categoryName;
					id = childCategory[j].categoryId;
					html += "<a href=\"javascript:void(0);\" onclick=\"loadKlByCategory("+ id + ")\">" + childName + "</a>";
					html += "&nbsp;";
					if(j!=0 && j%6==0){
						html +="<br/>";
					}
				}
			} else {
				html += "";
			}
			html += "</div>";
			html += "</li>";
		}
		html += "</ul>";
	}
	allDiv.html(html);
}
/**
 * 部门、推荐知识切换
 */
function switchKl(type, obj) {
	$("#all_tb").children().each(function(i) {
		$(this).removeClass("all_3");
	});
	$(obj).addClass("all_3");

	var datas = null;
	if (type == 0) {// 部门知识
		datas = getDepartmentKnowledge();
	} else {// 推荐知识
		datas = getRecommendKnowledge();
	}
	var html = "";
	var klDiv = $("#klDiv");
	klDiv.empty();
	if (datas) {
		var len = datas.length;
		html += "<ul>";
		for ( var i = 0; i < len; i++) {
			var knowledgeId = datas[i].knowledgeId;
			var score = datas[i].avgScore;
			var knowledgeName = datas[i].knowledgeName;
			var extendName = datas[i].extendName;
			var imgIco = "<%=request.getContextPath()%>/images/img/";
			if(!extendName){//当为null的时候默认给txt图标
				imgIco += "txt.png";
			}else if (extendName.startWith("doc") || extendName.startWith("docx")){
				imgIco += "word.png";
			} else if (extendName.startWith("pdf")) {
				imgIco += "pdf.png";
			} else if (extendName.startWith("xls") || extendName.startWith("xlsx")) {
				imgIco += "excel.png";
			} else if (extendName.startWith("ppt") || extendName.startWith("pptx")) {
				imgIco += "ppt.png";
			} else if (extendName.startWith("txt")) {
				imgIco += "txt.png";
			}
			html += "<li>";
			html += "<p><img src=\""
					+ imgIco
					+ "\" /> <span>"
					+ score
					+ "分</span> <span><a href=\"javascript:void(0)\" onclick=\"toKlDetails("
					+ knowledgeId + ")\">" + knowledgeName + "</a></span></p>";
			html += "</li>";
		}
		html += "</ul>";
	}
	klDiv.html(html);
}
/**
 * 根据类别查看知识资源
 */
function loadKlByCategory(id) {
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeFl.action?categoryId="+ id+"&isCloud="+isCloud;
}
/**
 * 点击查看知识详情
 */
function toKlDetails(knowledgeId) {
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+ knowledgeId;
}

/**
 * 跳转我的知识中心
 */
 function toMyKnowledeg(){
	 window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action";
}
/**
 * 查看全部知识
 */
 function toSeeAll(){
	 window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeFl.action";
}
/**
 * 搜索知识
 */
function doSearch(){
	var knowledgeName = $("#kl_name").val();
	var extendName ="";
	var extendNameObj = $("input[name='ra_1']");
	extendNameObj.each(function(i){
		if($(this)[0].checked){
			extendName = $(this).val();
		}
	});
	//window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeSearch.action?knowledgeName="+encodeURI(encodeURI(knowledgeName))+"&extendName="+extendName;
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeSearch.action?knowledgeName="+encodeURI(encodeURI(knowledgeName))+"&extendName="+extendName;
}
/**
 * 跳转知识上传页面
 */
function toUploda(){
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toUpload.action";
}

/**
 * 按下
 */
function key_down(key){
	if(key==13){
		doSearch();
	}
}
/**
 * tab - 全部分类
 */
function toAllCategroy(){
	isCloud = 1;
	$("#all_tab").attr("class","all_1");
	$("#cloud_tab").attr("class","all_3");
	getKnowledgeCategory(isCloud);
}
/**
 * tab - 云知识
 */
function toCloudKl(){
	isCloud = 2;
	$("#all_tab").attr("class","all_3");
	$("#cloud_tab").attr("class","all_1");
	getKnowledgeCategory(isCloud);
} 
</script>
</head>

<body>
	<div id="content_i">
		<div class="know">
			<!-- <h4>知识库首页</h4> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">知识库首页</span>
			</div>
			<div class="left_know">
				<div class="search_4">
					<div class="sch_top">
						<input type="text" class="txt_2" id="kl_name" onkeydown="key_down(event.keyCode);"/> <input type="button" value="查询" onclick="doSearch()"/> 
						<a href="javascript:void(0);" class="up_l" onclick="toUploda()">我要上传知识</a>
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
				<div class="k_all">
					<div class="head_k">
						<a id="all_tab" href="javascript:void(0);" class="all_1" onclick="toAllCategroy()">全部分类</a>
						<a class="all_1" style="background: #ffffff;width: 2px;"></a>
						<a id="cloud_tab" href="javascript:void(0);" class="all_3" onclick="toCloudKl()">云知识</a>
						<a href="javascript:void(0);" class="all_2" onclick="loadKlByCategory('')">查看全部</a>
						 
					</div>
					<div id="all_ul" class="all_ul"></div>
				</div>
				<div class="k_all" id="k_all">
					<div class="head_k">
						<div class="all_tb" id="all_tb">
							<!-- <a href="javascript:void(0)" onclick="switchKl(0,this)">部门知识</a> -->
							<a href="javascript:void(0)" onclick="switchKl(1,this)">推荐知识</a>
						</div>
						<a href="javascript:void(0)" class="all_2" onclick="toSeeAll()">查看全部</a>
					</div>
					<div id="klDiv" class="all_ul"></div>
				</div>

			</div>
			<div class="right_know">
				<div id="right_t" class="right_t"></div>
				<div class="my_m">
					<div id="mine" class="mine"></div>
					<a href="javascript:void(0)" onclick="toMyKnowledeg()">我的知识中心</a>
				</div>
				<div class="right_b">
					<h5>突出贡献者</h5>
					<ul id="know">
						<li class="li_i">
							<div class="li_active">
								<div class="left_active">
									<span>1</span>
								</div>
								<div class="right_active">
								<div style="float: left;margin-left: -55px;"><img src="<%=request.getContextPath() %>/images/img/bg_18.png"></div>
									<p>张筱雨<span>（1篇）</span></p>
									<a href="#">查看TA的知识</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>

		</div>
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>