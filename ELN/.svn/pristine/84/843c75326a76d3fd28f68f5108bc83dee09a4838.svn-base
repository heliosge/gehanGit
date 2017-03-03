<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<style type="text/css">
.notes_list h3 {
	background: url(../images/img/ico_19.png) no-repeat left -2px;
	padding-left: 36px;
	width: 1010px;
	padding-bottom: 10px;
	font-size: 16px; color : #010101; border-bottom : 1px solid #000000;
	margin-bottom: 20px;
	color: #010101;
	border-bottom: 1px solid #000000;
}
.notes_list .zx p{
	width: 1046px;
    height: 36px;
    border-bottom: 1px dotted #cccccc;
    line-height: 36px;
    text-indent: 1em;
    background: url(../images/img/ico_22.png) no-repeat left 10px;
}
.notes_list .zx p {
	cursor: pointer;
}
.notes_list .zx p a img{vertical-align:baseline;}
.notes_list .zx em{
    float: right;
    color: #999999;
}
.search_5{height: auto;}
</style>
</head>
<body>
<div id="course_all">
		<div class="notes_list">
			<!-- <h3>安全宣教管理</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的安全宣教</span>
			</div>
			<div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1)">微电影</li>
					<li onclick="tabClick(2)">宣传片</li>
					<li onclick="tabClick(3)">教育片</li>
				</ul>
			</div>
			<div>
					<form id="searchForm">
					<div class="search_5">
						<p>
							<span>标题：</span> <input type="text" id="title" name="title"/> 
							<span>创建时间：</span>
							
							<input type="text" id="startTime" name="startTime"/> <em>至</em> <input id="endTime" type="text" name="endTime"/>
							<input type="button" class="btn_5" value="查询" onclick="query()"/>
							
						</p>
					</div>
					</form>
					<div class="clear_both"></div>
						<div id="reLsit" class="zx" style="padding-top: 20px;">
						</div>
						<div id="jquery_page" style="margin-top: 10px; display: none;float: right;" class="pagination"></div>
					</div>
		</div>
	</div>
	<div id="videoDialog" style="display: none;"></div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 视屏播放 -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.js" charset="utf-8"></script> 
<script type="text/javascript">
var type = 1;
//每页显示数目
var pageSizes = 10;
$(function(){
	initDates();
	stu_getSafetyEducationListCount();
});

(function oldStyle(){
	$('#ul_exam').find('li').click(
		function() {
			$('#ul_exam').find('li').attr('class', '');
			$(this).attr('class', 'li_a');
		})
})()

function initDates(){
	initDatePicker("startTime");
	initDatePicker("endTime");
}
function initDatePicker(idName){
	$("#"+idName).datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
};
/**
 * 查询
 */
function query(){
	stu_getSafetyEducationListCount();
}
/**
 * tab页点击触发
 */
function tabClick(typeFlag){
	$("#searchForm")[0].reset();
	type = typeFlag;
	stu_getSafetyEducationListCount();
}
/**
 * 新增
 */
function toAdd(){
	window.location.href = "<%=request.getContextPath()%>/safetyEducation/toSafetyAdd.action?type="+type;
}
/**
 * 查看
 */
function toSeeVideo(path){
	var emel = $("#videoDialog");
	dialog({
		width : 1000,
		height : 500,
		title : '视频播放',
		content : emel
	}).showModal();
	setTimeout(function(){
		initVideo(path);
	},200);
}
function stu_getSafetyEducationListCount(){
	var param = new Object();
	param.title = $.trim($("#title").val());
	param.startTime = $("#startTime").val();
	param.endTime = $("#endTime").val();
	param.type = type;
	var urlStr = "<%=request.getContextPath()%>/safetyEducation/stu_getSafetyEducationListCount.action";
	$.ajax({
		type:"POST",
		url : urlStr,
		data :param,
		success : function(data) {
			var url = "<%=request.getContextPath()%>/safetyEducation/stu_getSafetyEducationList.action";
			insertPage(data,pageSizes,url,param);
		}
	});
}

//插入分页插件
function insertPage(sum,pageSize,urlStr,param){
	$("#jquery_page").show();
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
			 param.page = page+1;
			 param.pageSize = pageSize;
			 $.ajax({
			  		type: "POST",
			  		url: urlStr,
			   		data:param,
			  		dataType:"json",
			  		success:function(data){
			  			initList(data.rtnDataList);
			  		}
			   	});
		 }  
	});
}

function initList(list){
	var html = '';
	var obj = $("#reLsit");
	if(list){
		$.each(list,function(index,item){
			var id = item.id;
			var title = item.title;
			var filePath = item.filePath;
			var createTime = item.createTime;
			var isNew = item.isNew;
			var newimgStr = "<img src='<%=request.getContextPath()%>/images/newico.png'";
			createTime = getFormatDateByLong(createTime,'yyyy-MM-dd hh:mm:ss');
			html +='<p><a javascript:void(0); onclick="toSeeVideo(\''+filePath+'\')">'+title;
			if(isNew==1){
				html+=newimgStr;
			}
			html +='</a><em>'+createTime+'</em></p>';
		});
	}
	obj.html(html);
}

/**
 * 初始化video
 */
function initVideo(filePath){
	var flashvars={
			f:filePath,
			p:2,
			e:2,
			ht:'20'
			};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
	CKobject.embedSWF('<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.swf','videoDialog','ckplayer_courseWarePlayer','1000','500',flashvars,params);	
}
</script>
</html>