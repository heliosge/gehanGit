<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<title>Insert title here</title>
<style type="text/css">
.title_s{width: 1046px;}
.title_s p{margin-bottom: 20px;}
.title_s span{color: #0066CC;margin-right: 60px;}
.title_s span a{cursor: text;}
</style>
</head>
<body>
<div id="content_i">
     <div class="kn_upl">
     	<div class="left_upl">
        	<h2 id="tname"></h2>
            <div class="do">
            	<a onclick="downloads()">下载</a>
            </div>
            <div class="title_s">
            	<p>
            		<span>发布单位：<a id="fbdw"></a></span>
	            	<span>发布时间：<a id="fb_time"></a></span>
	            	<span>施行时间：<a id="sx_time"></a></span>
	            	<span>文号：<a id="wh"></a></span>
            	</p>
            	<p>
            		<span>关键字：<a id="keys"></a></span>
            		<span>时效性：<a  id="sxx"></a></span>
            	</p>
            </div>
            <div>
            	<div id="documentViewer" class="flexpaper_viewer" style="width:720px;height:650px;display:block;overflow: auto;"></div>
            </div>
        </div>
     </div>   
</div>
<form id="hide_form">
	<div style="display: none;">
		<input id="downId" type="hidden" name="downId"/>
	</div>
</form>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeFlexpaper.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var curid = '${curid}';//当前资源id
$(function() {
	getDetailById();
});
/**
 * 获取基本信息
 */
function getDetailById(){
	var urlStr = "<%=request.getContextPath()%>/policiesRules/getById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"id" : curid
		},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				var result = data.rtnData;
				var id = result.id;
				var name = result.name;
				var publishEnterprise = result.publishEnterprise;
				var publishTime = result.publishTime;
				publishTime = getFormatDateByLong(publishTime,'yyyy-MM-dd');
				var executeTime = result.executeTime;
				executeTime = getFormatDateByLong(executeTime,'yyyy-MM-dd');
				var referenceNumber = result.referenceNumber;
				var keyWords = result.keyWords;
				var timeliness = result.timeliness==1?"有效":"废止";
				var filePath = result.filePath;
				$("#tname").html(name);
				$("#fbdw").html(publishEnterprise);
				$("#fb_time").html(publishTime);
				$("#sx_time").html(executeTime);
				$("#wh").html(referenceNumber);
				$("#keys").html(keyWords);
				$("#sxx").html(timeliness);
				initFlexpaper(filePath);
			}
		}
	});
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

function downloads(){
	$("#hide_form").attr("action","<%=request.getContextPath()%>/policiesRules/downUploads.action");
	$("#downId").val(curid);
	$("#hide_form")[0].submit();
}
</script>
</html>