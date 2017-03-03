<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的比赛</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">		
.notes_list .com_list ul li {
	width: 310px;
	height: 270px;
}
</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
//每页显示数目
var pageSizes = 6;
$(function(){
	getMegagameListCount(1);
})
/**
 * 获取大赛记录条数
 */
function getMegagameListCount(flag){
	var urlStr = "<%=request.getContextPath()%>/MyMegagame/getMegagameListCount.action";
	var searchName = "";
	var searchStatus = "";
	if(flag==1){//1代表查询,0代表重置
		searchName = escapeForSql($("#searchName").val());
		searchStatus = $("#select_1").val();
	}else{//重置搜索条件表单
		$("#searchName").val("");
		$("#select_1").val(0);
	}
	
	$.ajax({
		type:"POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"megagameName" :$.trim(searchName),
			"state" : searchStatus
		},
		success : function(data) {
			var url = "<%=request.getContextPath()%>/MyMegagame/getMegagameList.action";
			var searchObj = new Object();
			if(flag==1){//1代表查询,0代表重置
				searchObj.searchName = searchName;
				searchObj.searchStatus = searchStatus;
			}else{
				searchObj.searchName = "";
				searchObj.searchStatus = "";
			}
			insertPage(data,pageSizes,url,searchObj);
		}
	});
}
//插入分页插件
function insertPage(sum,pageSize,urlStr,searchObj){
	$("#jquery_page").show();
	//插入分页插件
	$("#jquery_page").pagination(sum, {
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
			   			"userId" : userId,
			   			"page":page+1,
			   			"pageSize":pageSize,
			   			"megagameName":$.trim(searchObj.searchName),
			   			"state":searchObj.searchStatus
			   		},
			  		dataType:"json",
			  		success:function(data){
			  			var datas = data.rtnDataList;
						initMegagames(datas);
			  		}
			   	});
		 }  
	});
}
/**
 * 生成大赛列表
 */
function initMegagames(lists){
	if(lists!=null){
		var megagamesDiv = $("#megagames_id");
		var len = lists.length;
		var htmls ="";
		var count = 0;
		for(var i=0;i<len;i++){
			if (!lists[i]) {
				continue;
			}
			if(count==0){//第一次
				htmls +="<ul>";
			}
			var id = lists[i].id;
			var name = lists[i].name;
			var imgPath = lists[i].coverImageForList;
			var status = lists[i].status;
			var processInfo = "";
			var currentProcess = lists[i].currentProcess;
			var countsProcess = lists[i].counts;
			var icoImg = "ico_16.png";
			var statusText="未开始";
			if(status==2){
				statusText="进行中";
				icoImg = "ico_17.png";
				processInfo = "第"+currentProcess+"赛程/共"+countsProcess+"个赛程";
			}else if(status==3){
				icoImg = "ico_18.png";
				statusText="已结束";
			}
			htmls +="<li onclick=\"goMegagame("+id+")\">";
			htmls +="<div class=\"pic_3\" style=\"margin-left: -10px;\"><img style=\"width: 340px;height: 270px;\" src=\""+imgPath+"\" /></div>";
			htmls +="<div class=\"zt\"><img src=\"<%=request.getContextPath()%>/images/img/"+ icoImg+ "\" /> <span>"+ statusText+ "</span></div>";
			htmls += "<div class=\"com_txt\"><h4><a href=\"javascript:void(0);\" onclick=\"goMegagame("+id+")\">" + name + "</a></h4><p style=\"color:red;\">"+ processInfo + "</p></div>";
			htmls += "</li>";
				count++;
				if (count % 3 == 0) {//一行三个
					htmls += "</ul>";
					count = 0;
				} else if (i == len - 1) {
					htmls += "</ul>";
				}
			}
			megagamesDiv.html(htmls);
		}
	}
/**
 * 跳转查看大赛页面
 */
function goMegagame(megagameId){
	window.location.href = "<%=request.getContextPath()%>/MyMegagame/toMegagameInfo.action?megagameId="+megagameId;
}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list fl">
			<!-- <h3>我的大赛</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的大赛</span>
			</div>
			<div class="search_1">
				<span>大赛名称：<input id="searchName" type="text" /></span>
				 <span>大赛状态： 
					 <select id="select_1">
							<option value="0" selected="selected">显示全部</option>
							<option value="1">未开始</option>
							<option value="2">进行中</option>
							<option value="3">已结束</option>
					</select>
				</span> 
				<input type="button" class="btn_5" value="查询" onclick="getMegagameListCount(1)" /> 
				<input type="button" class="btn_6" value="重置" onclick="getMegagameListCount(0)"/>
			</div>
			<div id="megagames_id" class="com_list clear"></div>
		</div>
		<!--分页  -->
		<div id="jquery_page" style="margin-top: 10px; display: none;float: right; " class="pagination"></div>
	</div>
	<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>