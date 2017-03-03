<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<link href="<%=request.getContextPath() %>/css/menu/css/css.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<style type="text/css">
.rater-star{bottom: 4px;left: 15px;}
.list_ta .rater-star li{border: none;}
.list_ta .rater-star li:hover{box-shadow:none;}
.content_mall h3{background:none;margin-top:0px;}
.sidebar{max-height: 385px;}
.sidebar_popup{z-index: 999999}
.sidebar_top{height: 40px;}
</style>
</head>
<body>
<div class="content_mall">
		<!-- <h3>课程商城</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程商城</span>
		</div>
		<div>
			<div class="sidebar">
			  <div class="sidebar_top sidebar_top_tc" onclick="{categoryId='';getProductsByCategoryCount();}">全部产品分类</div>
			  <div class="sidebar_con" id="cate"></div>
			</div>
			<div style="float: right;width: 820px;">
	           <ul id="zt_img"></ul>
	        </div>
	        <span class="more_sp"><a onclick="toMoreTopic()">更多专题</a></span>
		</div>
</div>
<div class="list_ta">
	<h3>精品课程<a onclick="toMoreCourse()">more</a></h3>
    <ul id="jp_course" style="width: 1072px;"></ul>
    <h3>免费课程<a onclick="toMoreCourse()">more</a></h3>
    <ul id="mf_course" style="width: 1072px;"></ul>
    <h3>最新课程<a onclick="toMoreCourse()">more</a></h3>
    <ul id="zx_course" style="width: 1072px;"></ul>
</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
 <script src="<%=request.getContextPath() %>/css/menu/js/menu.js"></script>
 <jsp:include page="../common/includeStar.jsp"></jsp:include>
 <jsp:include page="../common/includeSlides.jsp" />
<script type="text/javascript">
var categoryId;
var categoryList;
$(function(){
	getMallIntegralCategory();
	
	getCourseTopic();
	//最新
	getBoutiqueCourse($("#zx_course"),0);
	//精品
	getBoutiqueCourse($("#jp_course"),1);
	//免费
	getBoutiqueCourse($("#mf_course"),2);
	
});

function slidesJsAction(){
	 $("#zt_img").bxSlider({auto:true});
}
/**
 * 获取banner的信息
 */
function getCourseTopic(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getCourseTopic.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
	 			initBannerImage(data.rtnDataList);
 			}
 		}
 	});
}


function getMallIntegralCategory(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getMallCourseCategory.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		success : function(data) {
			categoryList = data.rtnDataList;
			initCategory();
		}
	});
}

function bindAction(){
	 $(".sidebar").hoverDelay(function() {
	        $("#sidebar_box").show();
	        $(".sidebar_top s").addClass("s_down");
	    },
	    function() {
	        $("#sidebar_box").hide();
	        $(".sidebar_top s").removeClass("s_down");
	    });
	    $(".sidebar_item dd").hoverDelay(function() {
	        $(this).find("h3").addClass("sidebar_focus");
	        $(this).find(".sidebar_popup").show(0);
	    },
	    function() {
	        $(this).find("h3").removeClass("sidebar_focus");
	        $(this).find(".sidebar_popup").hide(0);
	    });
}

function initCategory(){
	var html = "";
	var cateObj = $("#cate");
	if(categoryList){
		$.each(categoryList,function(index,item){
			var id = item.id;
			var parentId = item.parentId;
			var name = item.name;
			if(!parentId || parentId==0){
				html +='<dl class="sidebar_item">';
				html += '<dd>';
				html += '<h3 class=""><a onclick="loadByCategroy('+id+')">'+name+'</a></h3><s></s>';
				// 以下三个变量用来控制 当只有一级节点的时候，不显示二级的div
				var flf= true;
				var tmpHtmlStart = '<div class="sidebar_popup dis1" style="display: none;"><div class="sidebar_popup_class clearfix">';
				var tmpHtmlEnd = '</div></div>';
				$.each(categoryList,function(index2,item2){
					var id2 = item2.id;
					var parentId2 = item2.parentId;
					var name2 = item2.name;
					if(parentId2 == id){
						if(flf){ // 控制只允许加一次
							html += tmpHtmlStart;
							flf = false;
						}
						html += '<div class="sidebar_popup_item"> <strong><a onclick="loadByCategroy('+id2+')">'+name2+'</a></strong>';
						html +='<p>';
						$.each(categoryList,function(index3,item3){
							var id3 = item3.id;
							var parentId3 = item3.parentId;
							var name3 = item3.name;
							if(parentId3 == id2){
								html += '<span class="linesbg"><a onclick="loadByCategroy('+id3+')">'+name3+'</a></span>';
							}
						});
								html +='</p></div>';
					}
					
				});
				if(!flf){// 代表加过一次 那么需要结尾标签
					html += tmpHtmlEnd;
				}
				html +='</dd></dl>';
			}
		});
	}
	cateObj.html(html);
	bindAction();
}
/**
 * 根据类别加载信息
 */
function loadByCategroy(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toCoursetList.action?categoryId='+id;
}
function initBannerImage(list){
	var topic_img = $("#zt_img");
	if(list && list.length>0){
		var htmlImg = '';
		$.each(list,function(index,item){
			var id = item.id;
			var bannerImage = item.bannerImage;
			htmlImg += '<li onclick="toTopicDetail('+id+')"><img style="width:820px;height:379px;" src="'+bannerImage+'"/></li>';
		});
		topic_img.html(htmlImg);
		slidesJsAction();
	}
}
/**
 * 跳转专题详情
 */
function toTopicDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toTopicDetail.action?id='+id;
}
function toMoreTopic(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toTopicList.action';
}
/**
 * 跳转课程详情页
 */
function toCourseDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toMallCoursetDetail.action?id='+id;
}

function toMoreCourse(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toCoursetList.action';
}
/**
 * 获取课程
 */
function getBoutiqueCourse($obj,type){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getBoutiqueCourse.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data : {"type":type},
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
 				initProducts($obj,data.rtnDataList,type);
 			}
 		}
 	});
}
/**
 * 生成页面
 */
function initProducts($ulObj,list,type){
	$ulObj.empty();
	
	if(list && list.length>0){
		$.each(list,function(index,item){
			var html = '';
			var id = item.id;
			var name = item.name;
			var titleName = name;
			if(name && name.length>8){
				name = name.substring(0,8);
				name += '...';
			}
			var cheapPrice = item.cheapPrice;
			cheapPrice = Number(cheapPrice).toFixed(2);
			var avgScore = item.avgScore;
			avgScore = Number(avgScore).toFixed(1);
			var evaluateNum = item.evaluateNum;
			var img = item.img;
			html += '<li onclick=toCourseDetail('+id+');>';
			html += '<div class="tb_top">';
			html += '<img style="width:240px;height:151px;" src="'+img+'" />';
			html += '</div>';
			if(cheapPrice == 0){//免费
				 html += '<p><span title="'+titleName+'">'+name+'</span><strong>免费</strong></p>';
			 }else{
				 html += '<p><span title="'+titleName+'">'+name+'</span><strong>精品&nbsp;￥'+cheapPrice+'</strong></p>';
			 }
			html += '<p>';
			html += '<b>'+avgScore+'</b>';
			html += '<span id="star_'+type+'_'+id+'"></span>';
			html += '<span class="c_pj">（'+evaluateNum+'人已评价）</span>';
			html += '</p>';
			html += '</li>';
			$ulObj.append(html);
			var setting = {
					max : 5,
					value : avgScore,
					image : "../js/rater-star/star.gif",
					enabled : false
			};
			$("#star_"+type+"_"+id).rater(setting);
		});
	}
	
}
</script>
</html>