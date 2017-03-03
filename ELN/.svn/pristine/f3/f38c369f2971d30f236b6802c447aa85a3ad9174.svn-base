<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<title>专题详情页面</title>
<style type="text/css">
.shop{float: right;position: relative;top: -14px;cursor: pointer;}
.shop i{padding: 2px 5px;background: #FEDA4E;border-radius: 20px;color: red;}
</style>
</head>
<body>
<div class="special">
	<!-- <h3>查看专题</h3> -->
	<div class="shop">
           <img src="<%=request.getContextPath() %>/images/mall/img/ico_shop.png" onclick="goShoppingCar()"/>
           <i id="shopCount">0</i>
    </div>
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看专题</span>
	</div>
    <div class="sp_list">
    	<div class="left_list">
        	<img id="thisTopciImg" style="width:247px;height:176px;" src="<%=request.getContextPath()%>/images/mall/img/head_sp.png" />
            <span>
            	<input id="add_c" onclick="addShopCar(window.curTopicId,2,this)" type="button" value="加入购物车" class="gw"/>
                <input id="now_nuy" type="button" value="立即购买" class="gm" onclick="toBuy()"/>
            </span>	
            <h4 id="thisTopciName"></h4>
            <p id="thisTopciDescr"></p>
        </div>
        <div class="right_list">
        	<div id="thisRightInfo" class="list_t">
                <h2></h2>
                <span>
                    <em>0门课程</em>
                    <i></i>
                    <i>原价:<b></b>元，</i>
                    <i>优惠价：元</i>
                </span>
            </div>
            <ul id="thisSubCourseList">
            	<%-- <li>
                	<img src="<%=request.getContextPath()%>/images/mall/img/bg_sp-1.png" />
                    <div class="lis_txt">
                    	<h4>这里是课程名称</h4>
                        <p>系统支持标准的多媒体课件，员工通过平台可以随时随地实现学习与获取知识。平台整合教学资源，实现课程学习、测试、模拟考试等多种教学手段的整合，极大地增强了学习的趣味性、生动性、实用性，提高了安全生产培训效果</p>
                        <span>
                        	<em>课程价格：20元</em>
                            <input type="button" value="单门课程购买" class="gm"/>
                            <input type="button" value="加入购物车" class="gw" />
                            
                        </span>
                    </div>
                </li> --%>
            </ul>
        </div>
	</div>
</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript">
var curTopicId = '${id}';//当前专题id
$(function(){
	getMallTopicDetailsById();
	getShoppingCarCount();
	checkTopicIsBuyed();
});
/**
 * 获取专题详情信息
 */
function getMallTopicDetailsById(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getMallTopicDetailsById.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data :{"id":curTopicId},
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
	 			initPage(data.rtnData);
 			}
 		}
 	});
}
/**
 * check 当前专题是否已购买
 */
function checkTopicIsBuyed(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/checkTopicIsBuyed.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"mallTopicId" : curTopicId
		},
		success : function(data){
			if(data>0){
				$('#now_nuy').css("background","#ccc").removeAttr("onclick");
				$('#add_c').css("background","#ccc").removeAttr("onclick");
			}
		}
	});
}

/**
 * 验证当前课程是否已购买过
 */
function checkCourseIsBuyed(mallCourseId){
	var result = false;
	var urlStr = "<%=request.getContextPath()%>/courseStore/checkCourseIsBuyed.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"mallCourseId" : mallCourseId
		},
		success : function(data){
			if(data>0){
				result = true;
			}
		}
	});
	return result;
}

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
function initPage(data){
	if(data){
		var html1 = '';
		var html = '';
		var name = data.name;
		var price = data.price;
		var cheapPrice = data.cheapPrice;
		var coverImage = data.coverImage;
		var descr = data.descr;
		var courseList = data.courseList;
		var len = courseList.length;
		html1 += '<h2>'+name+'</h2>';
		html1 += '<span>';
		html1 += '<em>'+len+'门课程</em>';
		if(price!=0){
			html1 += '<i>精品专题</i>';
		}else{
			html1 += '<i>免费专题</i>';
		}
		price = Number(price).toFixed(2);
		html1 += '<i>原价:<b>'+price+'</b>，</i>';
		cheapPrice = Number(cheapPrice).toFixed(2);
		html1 += '<i>优惠价：'+cheapPrice+'元</i>';
		html1 += '</span>';
		if(courseList.length>0){
			$.each(courseList,function(index,item){
				var courseId = item.id;
				var isBuyed = checkCourseIsBuyed(courseId);
				var courseName = item.name;
				var coursePrice = item.price;
				var courseCheapPrice = item.cheapPrice;
				coursePrice = Number(coursePrice).toFixed(2);
				courseCheapPrice = Number(courseCheapPrice).toFixed(2);
				var courseImg = item.img;
				var courseDescr = item.descr;
				var slen =  courseDescr.length;
				if(slen > 100){
					courseDescr = courseDescr.substring(0,100);
					courseDescr += '...';
				}
				html += '<li>';
				html += '<img onclick="toCourseDetail('+courseId+')" style="width:247px;height:173px;" src="'+courseImg+'"/>';
				html += '<div class="lis_txt">';
				html += '<h4>'+courseName+'</h4>';
				html += '<p style="height: 80px;">'+courseDescr+'</p>';
				html += '<span>';
				html += '<p>课程价格：&nbsp;<em>原价：</em><em style="text-decoration: line-through;">'+coursePrice+'</em>&nbsp;&nbsp;<em>优惠价：'+courseCheapPrice+'元</em></p>';
				if(isBuyed){
					html += '<input type="button" value="单门课程购买" class="gm" style="background:#ccc"/>';
					html += '<input type="button" value="加入购物车" class="gw" style="background:#ccc"/>';
				}else{
					html += '<input onclick="goBuyOne('+courseId+')" type="button" value="单门课程购买" class="gm"/>';
					html += '<input onclick="addShopCar('+courseId+',1,this)" type="button" value="加入购物车" class="gw" />';
				}
				html += '</span>';
				html += '</div>';
				html += '</li>';
			});
		}
		$("#thisTopciImg").attr("src",coverImage);
		$("#thisTopciName").html(name);
		$("#thisTopciDescr").html(descr);
		$("#thisRightInfo").html(html1);
		$("#thisSubCourseList").html(html);
	}
}

/**
 * 跳转商品详细页面
 */
function toCourseDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toMallCoursetDetail.action?id='+id;
}
/**
 * 加入购物车
 */
function addShopCar(id,type,obj){//type:1 课程 2专题     obj:当前的点击按钮
	var urlStr = "<%=request.getContextPath()%>/courseStore/addShoppingRecord.action";
	var param = new Object;
	param.productId = id;
	param.type = type;
	param.counts = 1;
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data :param,
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
 				layer.tips('加入购物车成功！',$(obj));
 				getShoppingCarCount();
 			}
 		}
 	});
}
/**
 * 单个购买课程
 */
function goBuyOne(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toGenerateOrder.action?ids='+id+'&productType='+1;
}
/**
 * 购买专题
 */
function toBuy(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toGenerateOrder.action?ids='+curTopicId+'&productType='+2;
}

/**
 * 跳转购物车
 */
function goShoppingCar(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toBuyCar.action';
}
</script>
</html>