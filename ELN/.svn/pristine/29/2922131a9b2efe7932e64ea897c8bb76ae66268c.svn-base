<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分商品类目页</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<style type="text/css">
.sdh{position: relative;left: 220px;bottom: 204px;z-index: 2;}
.list_tb{min-height: 300px;width: 835px;}
.content_mall h3{background:none;margin-top:0;}
</style>
</head>
<body>
<div class="content_mall">
	<!-- <h3 style="background: url('../images/mall/img/ico_sp.png') 4px 0 no-repeat">积分商城</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">积分商城</span>
		</div>
		
		<div class="sidebar">
		  <div class="sidebar_top sidebar_top_tc" onclick="{categoryId='';getProductsByCategoryCount();}">全部产品分类</div>
		  <div class="sidebar_con" id="cate"></div>
		</div>
    <div class="mall_list">
    	<div class="list_top">
        	<input id="searchKey" type="text" class="input_m" onkeydown="if(event.keyCode==13){doSearch();}"/>
            <img src="<%=request.getContextPath() %>/images/mall/img/ico_search_1.png" class="img_4" onclick="doSearch()"/>
            <input id="qualificationBtn" type="button" value="只显示我有资格兑换的商品" class="button_1" onclick="onlyShow()"/>
          	<div id="orde" class="px">
                <a onclick="doOrderBy(1,this)">按积分</a>
                <a onclick="doOrderBy(2,this)" class="a_last">按热门</a>
            </div>
        </div>
        <div class="list_tb">
            <ul id="products"></ul>
    	</div>
    </div>
    <!--分页  -->
	<div id="jquery_page" style="margin-top: 10px; display: none;float: right; " class="pagination"></div>
</div>
</body>
 <jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
 <jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
 <script src="<%=request.getContextPath() %>/css/menu/js/menu.js"></script>
<link href="<%=request.getContextPath() %>/css/menu/css/css.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
var categoryId;
var searchName;
var isHaveQualification;//是否显示我有资格兑换的商品 1 只显示我有资格兑换
var orderType;//排序类型 1 积分 2热门
var orderRule = "desc";//排序规则  可选值：asc  desc（默认值） 
var pageSize = 9;
var categoryList;
$(function(){
	 //获取积分课程类别
	getMallIntegralCategory();
	getProductsByCategoryCount();
	
 });
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
function getMallIntegralCategory(){
var urlStr = "<%=request.getContextPath()%>/integralStore/getMallIntegralCategory.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		success : function(data) {
			categoryList = data.rtnDataList;
			initCategory();
		}
	});
}

function getProductsByCategoryCount(){
	 var params = new Object;
	 params.categoryId = categoryId;
	 params.name = $.trim(searchName);
	 params.isHaveQualification = isHaveQualification;
	 params.orderType = orderType;
	 params.orderRule = orderRule;
	 var urlStr = "<%=request.getContextPath()%>/integralStore/getProductsByCategoryCount.action";
	 	$.ajax({
	 		type : "POST",
	 		url : urlStr,
	 		data : params,
	 		success : function(data) {
	 			insertPage(data,params);
	 		}
	 	});
 }
 
//插入分页插件
 function insertPage(sum,params){
	var urlStr = "<%=request.getContextPath()%>/integralStore/getProductsByCategory.action";
	if(sum>pageSize){
		$("#jquery_page").show();
	}else{
		$("#jquery_page").hide();
	}
	//插入分页插件
	$("#jquery_page").pagination(sum, {
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			params.page = page+1;
			params.pageSize = pageSize;
			 $.ajax({
			  		type: "POST",
			  		url: urlStr,
			   		data:  params,
			  		dataType:"json",
			  		success:function(data){
			  			var datas = data.rtnDataList;
						initProducts(datas);
			  		}
			   	});
		 }  
	});
}
 
function initProducts(list){
 var html = '';
 var obj = $("#products");
 if(list){
	 $.each(list,function(index,item){
		 var id = item.id;
		 var p_name = item.name;
		 var integral = item.integral;
		 integral = parseInt(integral);
		 var coverImage = item.coverImage;
		 html += '<li onclick="toDetail('+id+')">';
		 html += '<div class="tb_top">';
		 html += '<img style="width:240px;height:151px;" src="'+coverImage+'" />';
		 html += '</div>';
		 html += '<p style="height: 10px;">'+p_name+'</p>';
		 html += '<p><b>兑换积分：'+integral+'分</b></p>';
	 });
 }
 obj.html(html);
}
/**
 * 根据类别加载信息
 */
function loadByCategroy(id){
	categoryId = id;
	getProductsByCategoryCount();
}
/**
 * 是否只显示我可以兑换的
 */
function onlyShow(){
	if(isHaveQualification==1){
		isHaveQualification = null;
		$('#qualificationBtn').val('只显示我有资格兑换的商品');
	}else{
		isHaveQualification = 1;
		$('#qualificationBtn').val('显示全部商品');
	}
	getProductsByCategoryCount();
}
/**
 * 跳转商品详细页面
 */
function toDetail(id){
	window.location.href = '<%=request.getContextPath()%>/integralStore/toProductDetail.action?id='+id;
}
/**
 * 排序处理
 */
var tmpOrderType;
function doOrderBy(type,obj){
	//箭头初始化
	$("#orde a").each(function(){
		$(this).css("background","url(<%=request.getContextPath() %>/images/mall/img/ico_dw1.png) no-repeat 60px center");
	});
	orderType = type;
	if(tmpOrderType == type){//点击的排序和上次点击的相同
		if(orderRule=="desc"){
			orderRule = "asc";
			$(obj).css("background","url(<%=request.getContextPath() %>/images/mall/img/ico_up1.png) no-repeat 60px center");
		}else{
			orderRule = "desc";
			$(obj).css("background","url(<%=request.getContextPath() %>/images/mall/img/ico_dw1.png) no-repeat 60px center");
		}
	}else{
		orderRule = "desc";
	}
	tmpOrderType = type;
	getProductsByCategoryCount();
}
function doSearch(){
	searchName = $("#searchKey").val();
	getProductsByCategoryCount();
}
 </script>
</html>