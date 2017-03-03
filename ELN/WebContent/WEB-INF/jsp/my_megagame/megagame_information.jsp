<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>大赛资讯</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.fr{float:left}
.pagination{margin-top: 30px;float: right;}
.backBtn{width:132px;height:32px;color:#fff;background:#0198e7;text-align:center}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
//每页显示数目
var pageSizes = 10;
var megagameId = '${megagameId}';// 当前大赛id
$(function() {
	getMatchMessageListCount();
})
function getMatchMessageListCount(){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMatchMessageListCount.action";
	$.ajax({
		type:"POST",
		url : urlStr,
		data : {
			"megagameId" : megagameId
		},
		success : function(data) {
			var url = "<%=request.getContextPath()%>/MyMegagame/getMatchMessageList.action";
			var params = new Object;
			params.megagameId = megagameId;
			insertPage(data,pageSizes,url,params);
		}
	});
}

//插入分页插件
function insertPage(sum,pageSize,urlStr,params){
	//插入分页插件
	$("#jquery_page").pagination(sum, {
		link_to:"javascript:void(0);",
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			 $.ajax({
			  		type: "POST",
			  		url: urlStr,
			   		data:  {
			   			"page":page+1,
			   			"pageSize":pageSize,
			   			"megagameId":params.megagameId
			   		},
			  		dataType:"json",
			  		success:function(data){
			  			initInformation(data.rtnDataList);
			  		}
			   	});
		 }  
	});
}
/**
 * 生成比赛资讯信息
 */
function initInformation(list){
	var infoDiv  = $("#infoDiv");
	infoDiv.empty();
	var html="";
	if(list && list.length>0){
		$("#jquery_page").show();
		$("#head_title").show();
		var len = list.length;
		for(var i=0;i<len;i++){
			if (!list[i]) {
				continue;
			}
			var infoId =list[i].id;//资讯id
			var title = list[i].title;
			var publicTime = getFormatDateByLong(list[i].publishTime,"yyyy-MM-dd");
			html +="<p>";
			html +="<a href=\"javascript:void(0);\" onclick=\"openInfo("+infoId+")\">"+title+"</a><em>"+publicTime+"</em>";
			html +="</p>";
		}
	}else{
		$("#head_title").hide();
		$("#jquery_page").hide();
	}
	infoDiv.html(html);
}
/**
 * 查看资讯详情
 */
function openInfo(infomationId){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMatchMessageById.action";
	$.ajax({
		type:"POST",
		url : urlStr,
		data : {
			"messageId" : infomationId
		},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				$("#fwb").show();
				$("#cp_7").hide();
				var content = data.rtnData.content;
				$("#content").html(content);
			}
		}
	});
}
/**
 * 资讯查看返回
 */
function toBack(){
	$("#fwb").hide();
	$("#cp_7").show();
}
</script>
</head>
<body>
	<div class="cp_5" id="cp_7" style="display: block;" >
		<div class="zx fr" id="zx_1">
			<h5 id="head_title" style="display: none;">比赛资讯</h5>
			<div id="infoDiv"></div>
			<div id="jquery_page" style="margin-top: 10px; display: none;" class="pagination"></div>
		</div>
	</div>
	<div id="fwb" style="display: none;">
		<div id="content"></div>
		<input type="button" value="返回" class="backBtn" onclick="toBack()"/>
	</div>
</body>
</html>