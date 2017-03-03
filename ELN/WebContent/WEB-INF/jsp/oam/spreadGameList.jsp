<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专题管理</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
.notes_list .com_list ul li {
	width: 310px;
}
.btn_div{
	width: 100%;height: 36px;position: absolute;bottom: 0;left: 0;line-height: 36px;padding-left: 10px;text-align: center;
}
.btn_div .btn {
background: #0085FE;color: white;text-align: center;width: 90%;height: 35px;border: none;border-radius: 2px;font-weight: bold;margin-right: 10px;
}
</style>
<body>

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
		$("#select_1").val("2,3");
		searchStatus = "2,3";
	}
	
	$.ajax({
		type:"POST",
		url : urlStr,
		data : {
			"userId" : userId,
			"megagameName" :searchName,
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
				searchObj.searchStatus = "2,3";
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
			   			"megagameName":searchObj.searchName,
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
			}
			htmls +="<li>";
			htmls +="<div class=\"pic_3\" style=\"margin-left: -10px;\"><img style=\"width: 332px;height: 270px;\" src=\""+imgPath+"\" /></div>";
			htmls +="<div class=\"zt\"><img src=\"<%=request.getContextPath()%>/images/img/"+ icoImg+ "\" /> <span>"+ statusText+ "</span></div>";
			htmls += "<div class=\"com_txt\"><h4><a href=\"javascript:void(0);\" onclick=\"goMegagame("+id+")\">" + name + "</a></h4><p>"+ processInfo + "</p></div>";
			htmls +="<div class=\"btn_div\">";
			if(lists[i].isSpread == 2){
				htmls +="<input type='button' class='btn' style='margin-left: -10px;width: 332px;' value='推广' onclick='spread("+id+")'/>";
			}else{
				htmls +="<input type='button' class='btn' style='background:red;margin-left: -10px;width: 332px;' value='取消推广' onclick='cancelSpread("+id+")'/>";
			}
			htmls +="</div>";
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
	//window.location.href = "<%=request.getContextPath()%>/MyMegagame/toMegagameInfo.action?megagameId="+megagameId;
	window.location.href = "<%=request.getContextPath()%>/megagameManage/toSeeCompetition.action?megagameId="+megagameId;
}
	
function spread(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			id:id
		},
		url:"<%=request.getContextPath()%>/oam/spreadGame.action",
		success:function(data){
			getMegagameListCount(1);
			dialog.alert("推广成功。");
		}
	});
}

function cancelSpread(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			id:id
		},
		url:"<%=request.getContextPath()%>/oam/cancelSpreadGame.action",
		success:function(data){
			getMegagameListCount(1);
			dialog.alert("取消推广成功。");
		}
	});
}
</script>

<div class="content">
	<!-- <h3>推广大赛管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">推广大赛管理</span>
	 </div>
    <div class="lesson_tab"  style="width:1066px;float: left;">
        	<ul>
            	<li><a href="<%=request.getContextPath()%>/oam/toOamTopicListPage.action" style="color:black;">推广专题管理</a></li>
                <li><a href="<%=request.getContextPath()%>/oam/toOamSpreadCourseListPage.action" style="color:black;">推广课程管理</a></li>
                <li class="li_this">推广大赛管理</li>
            </ul>
	</div>
	<div id="course_all">
		<div class="notes_list fl">
			<div class="search_1">

				<span>大赛名称：<input id="searchName" type="text" /></span>
				 <span>大赛状态： 
					 <select id="select_1">
							<option value="1,2,3" selected="selected">显示全部</option>
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
   </div>
</body>
</html>
