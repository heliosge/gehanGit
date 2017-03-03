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
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.notes_list .com_list ul li {
	width: 310px;
}
.notes_list .com_list .operate a{width:60px}
.notes_list .zt span{width:45px}
.notes_list .com_list .operate{width:340px}
.my_a{text-align:center}
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
	var urlStr = "<%=request.getContextPath()%>/megagameManage/getContestVoListCount.action";
	var searchName = "";
	var searchStatus = "";
	if(flag==1){//1代表查询,0代表重置
		searchName = $("#searchName").val();
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
			var url = "<%=request.getContextPath()%>/megagameManage/getContestVoList.action";
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
	var megagamesDiv = $("#megagames_id");
	if(lists && lists.length>0){
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
			var currentProcess = lists[i].currentProcess;//非id 赛程序号1、2、3
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
			}else if(status==4){
				statusText="未发布";
			}
			htmls +="<li>";
			htmls +="<div class=\"pic_3\" style=\"margin-left: -10px;\" ><img onclick=\"goMegagame("+id+")\" style=\"width: 340px;height: 270px;\" src=\""+imgPath+"\" /></div>";
			htmls +="<div class=\"zt\"><img src=\"<%=request.getContextPath()%>/images/img/"+ icoImg+ "\" /> <span>"+ statusText+ "</span></div>";
			htmls += "<div class=\"com_txt\"><h4><a href=\"javascript:void(0);\" onclick=\"goMegagame("+id+")\">" + name + "</a></h4><p style=\"color:red;\">"+ processInfo + "</p></div>";
			htmls +="<div class=\"operate\" style=\"margin-left: -10px;\">";
			if(status==4){
				htmls +="<a href=\"javascript:void(0);\" class=\"my_a\" onclick=\"doPublish("+id+")\">发布</a>";
			}
			htmls +="<a href=\"javascript:void(0);\" class=\"my_a\" onclick=\"doModify("+id+")\">修改</a>";
			htmls +="<a href=\"javascript:void(0);\" class=\"my_a\" onclick=\"doDelete("+id+")\">删除</a>";
			htmls +="<a href=\"javascript:void(0);\" class=\"my_a\" onclick=\"toSeeDetail("+id+")\">查看</a>";	
			htmls +="<a href=\"javascript:void(0);\" class=\"my_a\" onclick=\"toInfoPublish("+id+")\">资讯发布</a>";
			if(currentProcess!=countsProcess && status==2){//进行中的赛程且不是处在最后一个赛程
				htmls +="<a class=\"my_a\" href=\"javascript:void(0);\" style=\"width:80px;margin-left:10px \" onclick=\"toOpenNext("+id+")\">开启下一赛程</a>";
			}
			if(currentProcess==countsProcess){//当最后一个赛程的时候可以手动结束比赛
				htmls +="<a class=\"my_a\" href=\"javascript:void(0);\" style=\"width:80px;margin-left:10px \" onclick=\"toGameOver("+id+")\">结束比赛</a>";
			}
			htmls +="</div>";
			/* htmls +="<div class=\"operate\">";
			if(currentProcess!=countsProcess && status==2){//进行中的赛程且不是处在最后一个赛程
				htmls +="<a href=\"javascript:void(0);\" onclick=\"toOpenNext("+id+")\">赛程</a>";
			}
			htmls +="</div>"; */
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
		}else{
			megagamesDiv.empty();
			$("#jquery_page").hide();
		}
	}
/**
 * 大赛发布
 */
var pubkSet = true;
function doPublish(id){
	if(!pubkSet){
		return;
	}
	pubkSet = false;
	//先判断是否有大赛信息
	var urlStr = "<%=request.getContextPath()%>/megagameManage/getMatchCounts.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async : false,
		data : {
			"megagameId" : id,
		},
		success : function(data) {
			if(data==0){
				dialog.alert("请先添加赛程信息！");
				pubkSet = true;
			}else{
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/megagameManage/doPublish.action",
					data : {
						"megagameId" : id,
					},
					success : function(data) {
						dialog.alert("发布成功");
						pubkSet = true;
						getMegagameListCount(1);
					}
				});
			}
		}
	});
}
	
/**
 * 资讯发布
 */
 function toInfoPublish(id){
	 window.location.href = "<%=request.getContextPath()%>/megagameManage/gotoPublishNews.action?contestId="+id;
}
/**
 * 开启下一赛程
 */
 function toOpenNext(id){
	 var urlStr = "<%=request.getContextPath()%>/megagameManage/openNext.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			data : {
				"id" : id,
			},
			success : function(data) {
				dialog.alert("操作成功");
				getMegagameListCount(1);
			}
		});
}
/**
 * 结束比赛
 */
function toGameOver(id){
	var d = dialog({
	    title: '提示',
	    content: '是否要结束比赛？',
	    okValue: '确定',
	    ok: function () {
	    	var urlStr = "<%=request.getContextPath()%>/megagameManage/openNext.action";
	 		$.ajax({
	 			type : "POST",
	 			url : urlStr,
	 			data : {
	 				"id" : id,
	 			},
	 			success : function(data) {
	 				dialog.alert("操作成功");
	 			}
	 		});
	 		getMegagameListCount(2);
	    },
	    cancelValue: '取消',
	    cancel: function () {
	    }
	});
	d.show();
}
/**
 * 查看大赛详细
 */
function toSeeDetail(id){
	 window.location.href = "<%=request.getContextPath()%>/megagameManage/toSeeCompetition.action?megagameId="+id;
}
/**
 * 删除大赛
 */
function doDelete(id){
	var urlStr = "<%=request.getContextPath()%>/megagameManage/deleteContest.action";
	var d = dialog({
	    title: '提示',
	    content: '确认删除？',
	    okValue: '确定',
	    ok: function () {
	    	$.ajax({
	    		type : "POST",
	    		url : urlStr,
	    		data : {
	    			"id" : id,
	    		},
	    		success : function(data) {
	    			getMegagameListCount(1);
	    		}
	    	});
	    },
	    cancelValue: '取消',
	    cancel: function () {
	    }
	});
	d.show();
}
/**
 * 跳转查看大赛页面
 */
function goMegagame(megagameId){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toSeeCompetition.action?megagameId="+megagameId;
}
/**
 * 新增大赛
 */
function addCompetition(){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMegagame.action?operateType=1";
}

/**
 * 修改大赛
 */
function doModify(id){
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toAddMegagame.action?operateType=2&megagameId="+id;
}
</script>
</head>
<body>
	<div id="course_all">
		<div class="notes_list fl">
			<!-- <h3>大赛管理</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">大赛管理</span>
			</div>
			<a href="#"><input type="button" value="新增大赛" class="btn_add" onclick="addCompetition()"/></a>
			<div class="search_1">

				<span>大赛名称：<input id="searchName" type="text" /></span>
				 <span>大赛状态： 
					 <select id="select_1">
							<option value="1,2,3,4" selected="selected">显示全部</option>
							<option value="4">未发布</option>
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
		<div id="jquery_page" style="margin-top: 10px; display: none; float: right;" class="pagination"></div>
	</div>
	<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>