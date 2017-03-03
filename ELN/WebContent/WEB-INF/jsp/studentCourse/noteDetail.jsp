<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>笔记详情</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- 引入页面js -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/webhr.js"></script> --%>
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- 引入dateUtil.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

#courseDetail{width:1046px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var courseId = '${courseId}';
var itemsPerPage = 5;//每页显示条数
	
/**
 * 页面加载完成
 */
$(function(){
	//初始化课程详情
	initCourseDetail();
	//初始化笔记详情
	initNotesDetail();
});

/**
 * 初始化课程详情
 */
function initCourseDetail(){
	var param = new Object();
	param.courseId = courseId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/courseNoteAction/getCourseDetail.action',
		success:function(data){
			if(data){
				var htmlStr = '';
				if(data.code){
					htmlStr += ''+data.name+'[<span style="color:grey;">'+data.code+'</span>]';	
				}else{
					htmlStr += ''+data.name+'[<span style="color:grey;">标签暂无</span>]';
				}
				$("#courseDetail").html(htmlStr);
			}
		}
	});
}

/**
 * 初始化笔记详情
 */
function initNotesDetail(){
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
				param.courseId = courseId;
				param.userId = userId;
				param.companyId = companyId;
				param.subCompanyId = subCompanyId;
				param.page = page;
				param.pageSize = itemsPerPage;
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseNoteAction/getNotesDetail.action',
					success:function(data){
						if(data != null && data != ''){
							$("#notesDetailShow").show();
							$("#notesDetailNoResult").hide();
							var htmlStr = '';
							for(var i = 0; i < data.length; i++){
								var noteDetail = data[i];
								htmlStr += '<div style="padding-top:10px;padding-bottom:10px;">';
								htmlStr += '<div style="float:left;">';
								htmlStr += '<img src="'+noteDetail.userPhoto+'" width="32px" height="33px"/>';
								htmlStr += '</div>';
								htmlStr += '<span style="float:left;color:#2894FF;">'+noteDetail.userName+'</span>';
								htmlStr += '<span style="float:left;color:gray;padding-left:5px;">'+getSmpFormatDateByLong(noteDetail.createTime)+'</span>';
								htmlStr += '<span style="float:left;padding-left:5px;">';
								htmlStr += '<a href="javascript:void(0)" onclick="deleteNote('+noteDetail.id+')">删除</a> ';
								htmlStr += '<a href="javascript:void(0)" onclick="updateNote('+noteDetail.id+')">修改</a>';
								htmlStr += '</span>';
								htmlStr += '<br/>';
								htmlStr += '<div style="padding-top:5px;padding-bottom:5px;width:1064px;word-wrap:break-word;">';
								htmlStr += noteDetail.content;
								htmlStr += '</div>';
								htmlStr += '<hr style="height:1px;border:none;border-top:1px dashed #C0C0C0;"/>';
								htmlStr += '</div>';
							}
							$("#notesDetailContent").html(htmlStr);
						}else{
							$("#notesDetailShow").hide();
							$("#notesDetailNoResult").show();
						}
					}
				});
			}
	};
	var count = getNotesDetailCount();
	$("#notesDetailPager").pagination(count,opts);
}

/**
 * 获取笔记详情数量
 */
function getNotesDetailCount(){
	var param = new Object();
	param.courseId = courseId;
	param.userId = userId;
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseNoteAction/getNotesDetailCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 删除该条笔记
 */
function deleteNote(noteId){
	var d = dialog({
		title:'删除笔记',
		content:'你确认要删除该条笔记吗？删除后将无法恢复',
		okValue:'确认',
		ok:function(){
			var param = new Object();
			param.noteId = noteId;
			$.ajax({
				type:'POST',
				async:false,//同步
				data:param,
				url:'<%=request.getContextPath()%>/courseNoteAction/deleteNote.action',
				success:function(data){
					if(data.rtnResult == 'SUCCESS'){
						alert("删除成功");
						//刷新页面
						initNotesDetail();
					}else{
						alert("删除失败");
					}
				}
			});
		},
		cancelValue:'取消',
		cancel:function(){}
	});
	d.show();
}



/**
 * 修改该条笔记
 */
function updateNote(noteId){
	var d = dialog({
		title:'修改笔记',
		width:400,
		height:200,
		content:$("#editDialog"),
		okValue:'修改',
		ok:function(){
			var editText = $("#editText").val();
			if(editText != null && editText.trim() != ''){
				saveNote(noteId);
			}else{
				alert("笔记内容不能为空...");
				return false;
			}
		},
		cancelValue:'取消',
		cancel:function(){}
	});
	d.showModal();
	//初始化数据
	initEditDialog(noteId);
}

/**
 * 初始化当前笔记内容
 */
function initEditDialog(noteId){
	$.ajax({
		type:'POST',
		async:true,//默认true,
		data:{noteId:noteId},
		url:'<%=request.getContextPath()%>/courseNoteAction/getEditDialog.action',
		success:function(data){
			if(data != null && data != ''){
				$("#editText").val(data.content);
			}
		}
	});
}

/**
 * 保存修改的笔记
 */
function saveNote(noteId){
	var param = new Object();
	param.noteId = noteId;
	param.content = $("#editText").val();
	$.ajax({
		type:'POST',
		async:false,//同步
		data:param,
		url:'<%=request.getContextPath()%>/courseNoteAction/saveNote.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				alert("修改笔记成功！");
				//刷新笔记列表
				initNotesDetail();
			}else{
				alert("修改笔记失败...");
			}
		}
	});
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
	initNotesDetail();
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
	initNotesDetail();
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
	initNotesDetail();
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
	initNotesDetail();
}
</script>
</head>
<body>
	
	<div id="course_all">
		<div class="notes_list fl">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">笔记详情</span>
			</div>
			<!-- <h3>我的笔记</h3> -->
			
			<!-- 课程信息 -->
			<div>
				<h4 id="courseDetail"></h4>
			</div>
			
			<div id="notesDetailShow">
				<!-- 笔记详情 -->
				<div id="notesDetailContent" style="padding-top:10px;padding-bottom:5px;">
				</div>
			
				<!-- 分页栏 -->
				<div class="page clear">
					<div class="left_page fl">
						<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
						<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
						<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
						<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
					</div>
					<div id="notesDetailPager" class="right_page fr">
					</div>
				</div>
			</div>
			
			<!-- 页面无结果显示用 -->
			<div id="notesDetailNoResult" style="display:none;text-align:center;margin-top:120px;">
				暂无结果
			</div>
		</div>	
	</div>
	
	<!-- 修改笔记对话框 -->
	<div id="editDialog" style="height: auto; display: none;">
		<textarea id="editText" style="width:400px;height:200px;"></textarea>
	</div>
</body>	
</html>