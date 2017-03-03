<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看上传知识</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/smartMenu/smartMenu.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartMenu/smartMenu-min.js"></script>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/includeFlexpaper.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<style type="text/css">
.btnSubmit{width: 60px;height: 30px;color: #fff;background: #d60500}
.txtS{width: 134px;height: 32px}
#userInfoDiv{background: none}
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
				var name = data.rtnData.knowledgeName;
				var knowledgeText = data.rtnData.knowledgeText;
				createType = data.rtnData.uploadType;//知识的创建方式
				extendName= data.rtnData.extendName;//文件资源后缀
				var isDownload = data.rtnData.isDownload;
				if(isDownload==0){ //不允许下载
					$("#a_dowm").hide();
				}
				if(createType==1){//上传的知识
					initFlexpaper(path);
				}else{//创建的知识
					initText(knowledgeText);
				}
				initRightInfo(data.rtnData);
			}
		}
	});
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
	for(var i=0;i<chapterId.length;i++){
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
/**
 * 初始化评价弹出
 */
function evaluateDialog() {
	var emel = $("#dialog");
	dDialog = dialog({
		width : 362,
		height : 300,
		title : '评价',
		zIndex : 99999,
		content : emel
	});
	dDialog.showModal();

	var setting = {
		max : 5,
		image : "../js/rater-star/star.gif",
		after_click : function(ret) {
			$("#starValue").val(ret.value);
		}
	};
	$("#dialogStar").rater(setting);
}
/**
 * 提交评价信息
 */
function giveEvaluate() {
	var evalueScore = $("#starValue").val();
	var evalueContent = $("#evalueContent").val();
	if (!evalueScore) {
		dialog.alert("评分不能为空！");
		return;
	}
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/giveEvaluate.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"fromUserId" : userId,
			"resourceId" : knowledgeId,
			"scoreLevel" : evalueScore,
			"evaluateContent" : evalueContent
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				dDialog.close();
				judgeEvaluate();
				getUploadingKnowledge();
				$("#elseDiv").children().eq(0).click();
			}
		}
	});
}
/**
 * 收藏、下载操作
 */
function collctDownload(type) {
	var downloadCount=0;
	if(type==2){//下载
		var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/judgeCollecDownload.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			async:false,
			data : {
				"userId" : userId,
				"knowledgeId" : knowledgeId
			},
			success : function(data) {
				downloadCount = data.downloadCount;
			}
		});
	}
	if(2==type && 2==createType){ //创建的知识
		$("#hide_form").attr("action","<%=request.getContextPath()%>/knowledgeLibraryInfo/downCreateKl.action");
		$("#klId").val(knowledgeId);
		$("#hide_form")[0].submit();
	}else if(2==type && 1==createType){
		$("#hide_form").attr("action","<%=request.getContextPath()%>/knowledgeLibraryInfo/downUploadKl.action");
		$("#klId").val(knowledgeId);
		$("#hide_form")[0].submit();
	}
	if(downloadCount>0){
		return;
	}
	
	
	//收藏下载表插入记录
	var urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/addCollectAndDownload.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"resourceId" : knowledgeId,
			"operateType" : type
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				if(type==1){
				 dialog.alert("操作成功！");
				}
				judgeCollecDownload();
			}
		}
	});
}
/**
 * 1评论按钮、2相关推荐、3猜你喜欢
 */
function doElse(doType, obj) {
	var html = "";
	var urlStr = "";
	var elseDiv = $("#elseDiv");
	elseDiv.children().each(function() {
		$(this).css("background", "#dddddd");
	});
	$(obj).css("background", "#d20001");
	if (doType == 1) {
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getEvaluates.action";
	} else if (doType == 2) {
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getRecommends.action";
	} else if (doType == 3) {
		urlStr = "<%=request.getContextPath()%>/knowledgeLibraryInfo/getGuessLike.action";
	}
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"knowledgeId" : knowledgeId
		},
		success : function(data) {
			if (data.rtnResult == "SUCCESS") {
				if (doType == 1) {// 评论
					initEvaluateContent(data.rtnDataList);
				} else {// 资源
					initElseResource(data.rtnDataList);
				}
			}
		}
	});
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
			 Scale : 1.2, 
			 ZoomTransition : 'easeOut',
			 ZoomTime : 0.5,
			 ZoomInterval : 0.2,
			 FitPageOnLoad : true,
			 FitWidthOnLoad : true,
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
<div id="content_i">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">知识详情</span>
		</div>
     <div class="kn_upl">
     	<div class="left_upl">
        	<h2 id="klNameDiv"></h2>
            <div id="collect_download" class="do">
            	<a href="javascript:void(0);"><span onclick="collctDownload(1)">收藏</span></a>
	            	<a id="a_dowm" href="javascript:void(0);" onclick="collctDownload(2)">下载</a>
			    	<!-- <input type="hidden" value="" name="path"/>
			 	    <input type="hidden" value="" name="fileName"/> -->
            </div>
            
            <div>
            	<div id="documentViewer" class="flexpaper_viewer" style="width:720px;height:650px;display:block;overflow: auto;"></div>
            </div>
        </div>
        
        <div class="right_upl fl">
        	<div class="right_pj">
            	<input type="button" value="我要评价" id="pj" onclick="evaluateDialog()"/>
            </div>
            
            <div id="userInfoDiv" class="right_txt" style="margin-left: -50px;"></div>
            <div class="right_bo">
            	<div id="evaluateDiv"></div>
                <div class="ck_pj" style="float: left;" id="elseDiv">
                	<a href="javascript:void(0);" onclick="doElse(1,this)">评论</a>
                    <a href="javascript:void(0);" onclick="doElse(2,this)">相关推荐</a>
                    <a href="javascript:void(0);" onclick="doElse(3,this)">猜你喜欢</a>
                </div>
                <div id="area" class="ck_pj" style="overflow: auto;">
                	 <p>不错</p>
                   	 <p>很好</p>
                </div>
            </div>
        </div>
     </div>
     <div id="dialog" style="display: none;">
     	<div>
     		<div style="float: left;"><span style="color: red;">*</span><span>评分：</span></div>
			<div id="dialogStar" style="margin-left: 70px;"></div>
			<input id="starValue" type="hidden"/>
		</div>
		<div>
			<p style="margin-top: 30px;">我要评价：<input id="evalueContent" class="txtS" type="text" /></p>	
        	<p style="margin-top: 30px;"><input class="btnSubmit" type="button" value="提交" onclick="giveEvaluate()"/></p>
		</div>
     </div>
     <form style="display: none;" id="hide_form">
     	<input id="klId" name="klId"/>
     </form>
</div>
</body>
</html>