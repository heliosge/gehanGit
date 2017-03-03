<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看上传知识</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />

<link rel="stylesheet" href="<%=request.getContextPath()%>/js/smartMenu/smartMenu.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartMenu/smartMenu-min.js"></script>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/includeFlexpaper.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<style type="text/css">
.btnSubmit{width: 60px;height: 30px;color: #fff;background: #0c9c92}
.txtS{width: 134px;height: 32px}
#userInfoDiv{background: none}
.lesson_add .add_gr{
	height: 50px;
	line-height: 50px;
	float: none;
}
.basic{margin-top: 60px;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var knowledgeId = '${knowledgeId}';
var dDialog;//评价dialog
var createType;//知识创建的方式 1：上传的知识2：创建的知识
$(function() {
	getUploadingKnowledge();
	judgeEvaluate();
	judgeCollecDownload();
	$("#elseDiv").children().eq(0).click();
});
/**
 * 判断资源的收藏、下载状态
 */
function judgeCollecDownload() {
	var cd = $("#collect_download");
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/judgeCollecDownload.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"knowledgeId" : knowledgeId
		},
		success : function(data) {
			var collectCount = data.collectCount;
			if (collectCount != 0) {
				cd.children().eq(0).attr("disabled","disabled");
				cd.children().eq(0).html("已收藏");
			}
		}
	});
}
/**
 * 判断是否已评价
 */
function judgeEvaluate() {
	var pj = $("#pj");
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/judgeEvaluate.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"knowledgeId" : knowledgeId
		},
		success : function(data) {
			if (data != 0) {
				pj.attr("disabled","disabled");
				pj.val("已评价");
			}
		}
	});
}
/**
 * 获取人员及评分信息
 */
function getUploadingKnowledge() {
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getUploadingKnowledge.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async:false,
		data : {
			"knowledgeId" : knowledgeId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				var path = data.rtnData.filePath;
				var knowledgeText = data.rtnData.knowledgeText;
				createType = data.rtnData.uploadType;//知识的创建方式
				extendName= data.rtnData.extendName;//文件资源后缀
				if(createType==1){//上传的知识
					initFlexpaper(path);
				}else{//创建的知识
					initText(knowledgeText);
				}
				
				initBaseData(data.rtnData);
			}
		}
	});
}

//初始化基本信息
function initBaseData(param){
	var str = param.knowledgeDesc;
	 
	$("#knowledgeDesc").html(str.replace(/\n/g,"</br>"));
}

/**
 * 递归获取章节信息
 */
function getTest(text,str,chapterId,chapter,index){
	if(text){
		var h1Index = text.indexOf("<h1>");
		var h1EndIndex = text.indexOf("</h1>");
		var chapterName = text.substring(h1Index+4,h1EndIndex);
		chapterId.push("chapter"+index);
		chapter.push(chapterName);
		text = text.replace(/<h1>/,"<h1><a id=\"chapter"+index+"\"/>");
		index = index+1;
			
		h1EndIndex = text.indexOf("</h1>");
		str.push(text.substring(0,h1EndIndex+5));
		text = text.substring(h1EndIndex+5);
		if(text.indexOf("<h1>")!=-1){
			getTest(text,str,chapterId,chapter,index);
		}else{
			str.push(text);
		}
	}
}
/**
 * 生成创建的知识的展示文本信息
 */
 function initText(text){
	
	var textDiv = $("#documentViewer");
	
	//提取章节名称
	var html="";
	var str = [];
	var index=1;
	var chapter = [] ;
	var chapterId = [];
	getTest(text,str,chapterId,chapter,index);
	textDiv.css({border:"1px solid #B8CBCB",overflow:"auto"});
	for(var j=0;j<str.length;j++){
		html+=str[j];
	}
	textDiv.html(html);
	
	var menuStr="";
	for(var i=0;i<chapterId.length;i++){arguments
		menuStr +="{text: chapter["+i+"],func: function() {$(\"#\"+chapterId["+i+"])[0].scrollIntoView();}}";
		if(i!=chapterId.length-1){
			menuStr+=",";
		}
	}
	
	var menuData ="[["+menuStr+"]]";
	textDiv.smartMenu(eval(menuData));
}
/**
 * 生成页面信息
 */
function initRightInfo(datas) {
	var html1 = "";
	var html2 = "";
	var klNameDiv = $("#klNameDiv");
	var userInfoDiv = $("#userInfoDiv");
	var evaluateDiv = $("#evaluateDiv");
	if (datas) {
		var len = datas.length;
		var name = datas.knowledgeName;
		var createUserName = datas.createUserName;
		var createTime = datas.createTime;
		createTime = getFormatDateByLong(createTime, "yyyy-MM-dd hh:mm:ss")
		var avgScore = datas.avgScore;
		avgScore = Number(avgScore).toFixed(1);
		var evaluateCount = datas.evaluateCount;
		var headImg = datas.headImg;
		html1 += "<img style=\"float: left;width:43px;height:44px;\" src=\""+ headImg + "\">";
		html1 += "<h5>" + createUserName + "</h5>";
		html1 += "<p>贡献于" + createTime + "</p>";
		html2 += "<p style=\"float: left;\">";
		html2 += "<span>" + avgScore + "</span>";
		html2 += "<div id=\"star\" style=\"float: left;\"></div>";
		html2 += "<em style=\"font-weight: normal;color: #999;\">("
				+ evaluateCount + "人已评价)</em>";
		html2 += "</p>";
		klNameDiv.html(name);
	}
	userInfoDiv.empty();
	evaluateDiv.empty();
	userInfoDiv.html(html1);
	evaluateDiv.html(html2)
	var setting = {
		max : 5,
		value : avgScore,
		image : "../js/rater-star/star.gif",
		enabled : false
	};
	$("#star").rater(setting);
}

function initEvaluateContent(list) {
	var html = "";
	var area = $("#area");
	area.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var evaluateContent = list[i].evaluateContent;
			html += "<p>" + evaluateContent + "</p>";
		}
	}
	area.html(html);
}
/**
 * 相关推荐、猜你喜欢 的资源生成
 */
function initElseResource(list) {
	var html = "";
	var area = $("#area");
	area.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			if (!list[i]) {
				continue;
			}
			var id = list[i].knowledgeId;
			var name = list[i].knowledgeName;
			html += "<p style=\"cursor: pointer;\" onclick=\"toLookKl(" + id
					+ ")\">" + name + "</p>";
		}
	}
	area.html(html);
}
function initFlexpaper(filePath){
	var fp = new FlexPaperViewer(	
			 '<%=request.getContextPath()%>/js/flexPaper/FlexPaperViewer',
			 'documentViewer', { config : {
			 SwfFile : escape(filePath),
			 Scale : 0.6, 
			 ZoomTransition : 'easeOut',
			 ZoomTime : 0.5,
			 ZoomInterval : 0.2,
			 FitPageOnLoad : true,
			 FitWidthOnLoad : false,
			 FullScreenAsMaxWindow : false,
			 ProgressiveLoading : false,
			 MinZoomSize : 0.2,
			 MaxZoomSize : 5,
			 SearchMatchAll : false,
			 InitViewMode : 'Portrait',
			 PrintPaperAsBitmap : false,
			 
			 ViewModeToolsVisible : true,
			 ZoomToolsVisible : true,
			 NavToolsVisible : true,
			 CursorToolsVisible : true,
			 SearchToolsVisible : true,
			 localeChain: 'en_US'
			 }});
}
/**
 * 跳转知识
 */
function toLookKl(id) {
	window.location.href = "<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeLookUp.action?knowledgeId="+ id;
}
</script>
</head>
<body>
<div class="content" style='padding-bottom: 0px;'>
<!-- 	<h3>查看详情</h3>
 -->	
 <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
	<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
	<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看详情</span>
 </div>
 <div class="lesson_add">
		
		<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>知识名称</em>
            </div>
             <div class="add_fr">
            	 <label id="knowledgeName">${klJSON.knowledgeName}</label>
            </div>
    	</div>
    	<div class="add_gr" style='height: 650px;'>
        	<div class="add_fl">
            	<span>*</span>
                <em>知识内容</em>
            </div>
             <div class="add_fr" style="width: 720px;height: 650px;">
            	<div id="documentViewer" class="flexpaper_viewer" style="width:720px;height:650px;display:block;overflow: auto;"></div>
            </div>
    	</div>
    <!--  <div class="kn_upl">
     	<div class="left_upl">
        	<h2 id="klNameDiv"></h2>
            <div>
            	<div id="documentViewer" class="flexpaper_viewer" style="width:720px;height:650px;display:block;overflow: auto;"></div>
            </div>
        </div>
     </div> -->
     </div> 
     <div  class="basic lesson_add" >
	    <h5>基本信息</h5>		
     	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>知识分类</em>
            </div>
             <div class="add_fr">
            	 <label id="knowledgeCategory">${klJSON.categoryName}</label>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>是否开放</em>
            </div>
             <div class="add_fr">
              <label>
             	<c:choose>
             		<c:when test="${klJSON.isOpen==1}">
             			是
             		</c:when>
             		<c:otherwise>
             			否
             		</c:otherwise>
             	</c:choose>
            	</label>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>允许下载</em>
            </div>
             <div class="add_fr">
            	<label>
	             	<c:choose>
	             		<c:when test="${klJSON.isDownload==1}">
	             			是
	             		</c:when>
	             		<c:otherwise>
	             			否
	             		</c:otherwise>
	             	</c:choose>
            	</label>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>精彩推荐</em>
            </div>
             <div class="add_fr">
            	 <label>
	             	<c:choose>
	             		<c:when test="${klJSON.isRecommend==1}">
	             			是
	             		</c:when>
	             		<c:otherwise>
	             			否
	             		</c:otherwise>
	             	</c:choose>
            	</label>
            </div>
    	</div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>标签</em>
            </div>
             <div class="add_fr">
            	 <label id="tags">${klJSON.tags}</label>
            </div>
    	</div>
    	<div class="add_gr" style="height: auto;display: inline-block;">
        	<div class="add_fl">
                <em>知识描述:</em>
            </div>
             <div class="add_fr" style="padding-bottom: 10px;">
            	 <label id="knowledgeDesc" style="line-height: normal;">
            	 </label>
            </div>
    	</div>
        <%--  <c:if test="${klJSON.companyId!=1}">
	     	<div class="add_gr">
	        	<div class="add_fl">
	                <em>是否共享</em>
	            </div>
	             <div class="add_fr">
	            	 <label>
		             	<c:choose>
			             	<c:when test="${klJSON.companyId!=klJSON.subCompanyId}">
			             		<c:choose>
				             		<c:when test="${klJSON.shareStatus==1}">
				             			否
				             		</c:when>
				             		<c:otherwise>
				             			是
				             		</c:otherwise>
			             		</c:choose>
			             	</c:when>
			             	<c:otherwise >
			             		<c:choose>
				             		<c:when test="${klJSON.shareStatus==4}">
				             			否
				             		</c:when>
				             		<c:otherwise>
				             			是
				             		</c:otherwise>
			             		</c:choose>
			             	</c:otherwise>
		             	</c:choose>
	            	</label>
	            </div>
	    	</div>
	    </c:if> --%>
     </div>
</div>
</body>
</html>