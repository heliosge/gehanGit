<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程商品类目页</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<style type="text/css">
.list_top{width: 900px;}
.list_tb{width: 850px;min-height: 300px;}
.list_tb .rater-star{position: relative;bottom: 4px;left: 15px;}
.list_tb .rater-star li{border: none;}
.list_tb .rater-star li:hover{box-shadow:none;}
.list_tb ul li{height: 240px;}
.content_mall{width: 1146px;}
.content_mall h3{margin-top:0;background: none;}
.shop:HOVER{text-decoration: none;}
.shop i{padding: 2px 5px;background: #FEDA4E;border-radius: 20px;color: red;position: relative;top: -20px;}
</style>
</head>
<body>
<div class="content_mall">
	<!-- <h3 style="background: url('../images/mall/img/ico_sp.png') 4px 0 no-repeat">课程商城</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程商城</span>
	</div>
	<div class="sidebar">
	  <div class="sidebar_top sidebar_top_tc" onclick="{categoryId='';getProductsByCategoryCount();}">全部产品分类</div>
	  <div class="sidebar_con" id="cate"></div>
	</div>
    <div class="mall_list">
    	<div class="list_top">
        	<input id="searchKey" type="text" class="input_m" onkeydown="if(event.keyCode==13){doSearch();}"/>
            <img src="<%=request.getContextPath() %>/images/mall/img/ico_search_1.png" class="img_4" style="cursor: pointer;" onclick="doSearch()"/>
            <input id="qualificationBtn" type="button" value="只显示我未购买的课程" class="button_1" onclick="onlyShowNotBuy()"/>
            <a class="shop">
            	<img src="<%=request.getContextPath() %>/images/mall/img/ico_shop.png" onclick="goShoppingCar()"/>
            	<i id="shopCount">0</i>
            </a>
            <a style="position: relative;left: 20px;"><input type="checkbox" style="position: relative;top: 3px;">只看免费</a>
          	<div id="orde" class="px">
                <a onclick="doOrderBy(2,this)">按销量</a>
                <a onclick="doOrderBy(3,this)" class="a_last">按评价</a>
            </div>
        </div>
        
        <div class="list_tb">
            <ul id="products">
                <li>
                    <div class="tb_top">
                     	<img src="<%=request.getContextPath() %>/images/mall/img/bg_mall_1.png" />   
                    </div>
                   <p>
                    	<span>视频处理</span>
                        <strong>￥100.00</strong>
                    </p>
                    <p>
                    	<b>0.0</b>
                        <a href="#"><img src="img/star.png" /></a>
                        <span class="c_pj">（0人已评价）</span>
                    </p>

                </li>
            </ul>
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
 <jsp:include page="../common/includeStar.jsp"></jsp:include>
 <script type="text/javascript">
var categoryId = '${categoryId}'; // 如果不为空 代表从首页跳转过来
var searchName;
var isBuy;//是否显示我未购买的商品 1  1 显示 
var showFree;//是否只显示免费课程  1是 
var orderType = 1;//排序类型 1 时间 2销量 3评价
var orderRule = "desc";//排序规则  可选值：asc  desc（默认值） 
var pageSize = 9;
var categoryList;
$(function(){
	 //获取课程类别
	getMallIntegralCategory();
	getProductsByCategoryCount();
	$("input[type='checkbox']").change(function(){
		if(this.checked==true){
			showFree = 1;
		}else{
			showFree = null;
		}
		getProductsByCategoryCount();
	});
	getShoppingCarCount();
	
 });
/**
 * 获取购物车中数量
 */
 function getShoppingCarCount(){
	 var urlStr = "<%=request.getContextPath()%>/courseStore/getShoppingCarCount.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			success : function(data){
				$('#shopCount').html(data);
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
/**
 * 跳转购物车
 */
function goShoppingCar(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toBuyCar.action';
}
function getProductsByCategoryCount(){
 var params = new Object;
 params.categoryId = categoryId;
 params.name = $.trim(searchName);
 params.isBuy = isBuy;
 params.showFree = showFree;
 params.orderType = orderType;
 params.orderRule = orderRule;
 var urlStr = "<%=request.getContextPath()%>/courseStore/getCourseByCategoryCount.action";
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
	var urlStr = "<%=request.getContextPath()%>/courseStore/getCourseByCategory.action";
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
 var obj = $("#products");
 obj.empty();
 if(list){
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
		 var starId = 'star'+id;
		 var avgScore = item.avgScore;
		 avgScore = Number(avgScore).toFixed(1);
		 var evaluateNum = item.evaluateNum;
		 var coverImage = item.img;
		 html += '<li onclick="toDetail('+id+')">';
		 html += '<div class="tb_top">';
		 html += '<img style="width:240px;height:151px;" src="'+coverImage+'" />';
		 html += '</div>';
		 if(cheapPrice == 0){//免费
			 html += '<p><span title="'+titleName+'">'+name+'</span><strong>免费</strong></p>';
		 }else{
			 html += '<p><span title="'+titleName+'">'+name+'</span><strong>精品&nbsp;￥'+cheapPrice+'</strong></p>';
		 }
		 html += '<p><b>'+avgScore+'</b>';
		 html += '<span id="'+starId+'"></span></p>';
		 html += '<p class="c_pj" style="color:#9a9899;">（'+evaluateNum+'人已评价）</p>';
		 
		 obj.append(html);
		 var setting = {
					max : 5,
					value : avgScore,
					image : "../js/rater-star/star.gif",
					enabled : false
				};
		$("#" + starId + "").rater(setting);
	 });
 }
 
}
/**
 * 根据类别加载信息
 */
function loadByCategroy(id){
	categoryId = id;
	getProductsByCategoryCount();
}
/**
 * 是否只显示我未购买的
 */
function onlyShowNotBuy(){
	if(isBuy==1){
		isBuy = null;
		$('#qualificationBtn').val('只显示我未购买的课程');
	}else{
		isBuy = 1;
		$('#qualificationBtn').val('显示全部课程');
	}
	getProductsByCategoryCount();
}
/**
 * 跳转商品详细页面
 */
function toDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toMallCoursetDetail.action?id='+id;
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