<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的笔记</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jqueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;width:330px;}
	
/**降序排序*/
.sortDesc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9down.png) no-repeat 75px center;}

/**升序排序*/
.sortAsc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9.png) no-repeat 75px center;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var itemsPerPage = 20;//每页显示条数
var sortName = 'note_date';//排序名称
var sortOrder = 'desc';//排序顺序
	
/**
 * 页面加载完成
 */
$(function(){
	$("#createTimeStart,#createTimeEnd").datepicker({
		changeMonth: true,
		changeYear: true
	});
	//初始化我的笔记
	initMyNotes();
});

/**
 * 初始化课程笔记
 */
function initMyNotes(){
	var opts = {
			items_per_page:itemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			num_display_entries:2,//连续分页主体部分显示的分页条目数
			callback:function(page,node){
				var param = new Object();
				param.userId = userId;
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.courseName = $("#courseName").val();
				param.createTimeStart = $("#createTimeStart").val();
				param.createTimeEnd = $("#createTimeEnd").val();
				param.sortName = sortName;
				param.sortOrder = sortOrder;
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,
					data:param,
					url:'<%=request.getContextPath()%>/courseNoteAction/getMyNotes.action',
					success:function(data){
						if(data != null && data != ''){
							$("#myNotesShow").show();
							$("#myNotesNoResult").hide();
							//展现内容
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var myNote = data[i];
								if((i+1)%2 == 0){
									htmlStr += '<li class="last_li" onclick="toNoteDetail('+myNote.courseId+');">';
								}else{
									htmlStr += '<li onclick="toNoteDetail('+myNote.courseId+');">';
								}
								htmlStr += '<div class="pic_2">';
								htmlStr += '<img src="'+myNote.courseFrontImage+'" width="132px" height="93px"/>';
								htmlStr += '</div>';
								htmlStr += '<div class="log_txt">';
								htmlStr += '<h4 title="'+myNote.courseName+'" class="courseNameStyle">'+myNote.courseName+'</h4>';
								if(myNote.courseCode){
									htmlStr += '<p>['+myNote.courseCode+']</p>';
								}else{
									htmlStr += '<p>[标签暂无]</p>';
								}
								htmlStr += '<span>我的笔记数:<strong>'+myNote.noteCount+'</strong></span>';
								htmlStr += '<span style="margin-right: -156px;">'+getSmpFormatDateByLong(myNote.noteDate)+'</span>';
								htmlStr += '</div>';
								htmlStr += '</li>';
							}
							$("#myNotesUl").html(htmlStr);
						}else{
							$("#myNotesShow").hide();
							$("#myNotesNoResult").show();
						}
					}
				});
			}
	};
	var count = getMyNotesCount();
	$("#myNotesPager").pagination(count,opts);
}

/**
 * 获取课程笔记数量
 */
function getMyNotesCount(){
	var param = new Object();
	param.userId = userId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	param.courseName = $("#courseName").val();
	param.createTimeStart = $("#createTimeStart").val();
	param.createTimeEnd = $("#createTimeEnd").val();
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步，必须先查出总条数
		data:param,
		url:'<%=request.getContextPath()%>/courseNoteAction/getMyNotesCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 查询
 */
function search(){
	$("#searchButton").attr("class","btn_5");
	$("#resetButton").attr("class","btn_6");
	//查询
	initMyNotes();
}

/**
 * 重置
 */
function reset(){
	$("#searchButton").attr("class","btn_6");
	$("#resetButton").attr("class","btn_5");
	//重置
	$("#courseName").val('');
	$("#createTimeStart").val('');
	$("#createTimeEnd").val('');
	//查询
	initMyNotes();
}

/**
 * 根据笔记时间排序
 */
function sortByNoteDate(){
	if(sortName == 'note_date' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByNoteDate").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByNoteDate").attr("class","sortDesc");
	}
	sortName = 'note_date';
	//查询
	initMyNotes();
}

/**
 * 根据笔记条数排序
 */
function sortByNoteCount(){
	if(sortName == 'note_count' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByNoteCount").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByNoteCount").attr("class","sortDesc");
	}
	sortName = 'note_count';
	//查询
	initMyNotes();
}

/**
 * 每页显示20条数据
 */
function changePagesizeOfTwenty(){
	$("#pageSizeTwenty").addClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 20;
	initMyNotes();
}

/**
 * 每页显示40条数据
 */
function changePagesizeOfFourty(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").addClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 40;
	initMyNotes();
}

/**
 * 每页显示100条数据
 */
function changePagesizeOfOneHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").addClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 100;
	initMyNotes();
}

/**
 * 每页显示200条数据
 */
function changePagesizeOfTwoHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").addClass("first");
	itemsPerPage = 200;
	initMyNotes();
}

/**
 * 跳转到笔记详情页面
 */
function toNoteDetail(courseId){
	window.location.href = '<%=request.getContextPath()%>/courseNoteAction/toNoteDetail.action?courseId='+courseId;
}
</script>
</head>
<body>

	<div id="course_all">
		<div class="notes_list fl">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的笔记</span>
			</div>
			<!-- <h3>我的笔记</h3> -->
			<div class="search_1 fl">
				<span>课程名称：<input id="courseName" type="text" /></span> 
				<span>
					笔记时间：
					<input id="createTimeStart" type="text" />
					至
					<input id="createTimeEnd" type="text" />
				</span> 
				<input id="searchButton" type="button" class="btn_5" value="查询" onclick="search();"/> 
				<input id="resetButton" type="button" class="btn_6" value="重置" onclick="reset();"/>
				<div class="sort_1 fr">
					<a id="sortByNoteDate" class="sortDesc" href="javascript:void(0)" onclick="sortByNoteDate();">按笔记时间</a> 
					<a id="sortByNoteCount" class="sortDesc" href="javascript:void(0)" onclick="sortByNoteCount();">按笔记条数</a>
				</div>
			</div>
			
			<!-- 有结果展示 -->
			<div id="myNotesShow">
				<div class="notes_log fl">
					<ul id="myNotesUl">
					</ul>
				</div>
				<!-- 分页栏 -->
				<div class="page clear">
					<div class="left_page fl">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="myNotesPager" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 页面无结果显示用 -->
			<div id="myNotesNoResult" style="display:none;text-align:center;margin-top:120px;">
				暂无结果
			</div>
		</div>
	</div>
</body>
</html>