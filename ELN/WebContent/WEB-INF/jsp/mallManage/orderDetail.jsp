<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall.css" />
<style type="text/css">
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.close{color: black;
    font-size: 15px;
    height: 10px;
    position: absolute;
    right: -5px;
    text-decoration: none;
    top: -45px;
    width: 20px;}
    
    .close:hover{color:white}
</style>
<script>
var order={};
var id;
order= ${order};
var topicList=[];
var courseList=[];
var showImageCount=0;

$(function() {
	if(order&&order.id){
   		
   		id=order.id;
   		getRelateList();
   		//回填值
   		$("#code").text(order.code);
   		$("#createTime").text(order.createTime);
   		if(order.type==1){
   			$("#type").text("线上交易");
   		}else{
   			$("#type").text("线下交易");
   		}
   		$("#companyName").text(order.companyName);
   		$("#remarks").text(order.remarks);
   		
  
   		
   		if(order.status==0){
   			$("#status").text("待付款");
   		}else if(order.status==1){
   			$("#status").text("待发货");
   		}else if(order.status==2){
   			$("#status").text("已发货");
   		}else if(order.status==3){
   			$("#status").text("已评价");
   		}else if(order.status==4){
   			$("#status").text("已失效");
   		}else{
   			$("#status").text("已取消");
   		}
   		if(order.isNeedInvoice==1){
   			$("#invoiceTitle").text(order.invoiceTitle);
   			$("#invoicePhone").text(order.invoicePhone);
   			$("#invoiceAddress").text(order.invoiceAddress);
   			$("#invoicePostcode").text(order.invoicePostcode);
   			$("#invoiceInfoDiv").show();
   		}
   		if(order.invoiceStatus==1){
   			$("#invoiceStatus").text("已邮寄");
   			$("#logisticsCompanyName").text(order.logisticsCompanyName);
   			$("#logisticsNumber").text(order.logisticsNumber);
   			$("#logisticsTime").text(order.logisticsTime);
   			var logisticsDetail = getLogisticsDetail();
   			$("#logisticsDetail").text(logisticsDetail);
   			$("#logisticsDiv").show();
   		}else if(order.invoiceStatus==2) {
   			$("#invoiceStatus").text("已签收");
   			$("#logisticsCompanyName").text(order.logisticsCompanyName);
   			$("#logisticsNumber").text(order.logisticsNumber);
   			$("#logisticsTime").text(order.logisticsTime);
   			var logisticsDetail = getLogisticsDetail();
   			$("#logisticsDetail").text(logisticsDetail);
   			$("#logisticsDiv").show();
   		}else{
   			if(order.isNeedInvoice==1){
   				$("#invoiceStatus").text("待邮寄");
   			}else{
   				$("#invoiceStatus").text("不需要发票");
   			}
   			
   		}
   		
   		$("#userRealName").text(order.userRealName);
   		$("#contactWay").text(order.contactWay);
   		$("#address").html(order.address);
   		$("#postcode").text(order.postcode);
   		
   	 
   	}else{
   		cancel();
   	}
	
});


//获取课程或者专题list
function getRelateList(){
	
	if(order&&order.commodityType){
		if(order.commodityType==1){//课程
		$.ajax({
			type:"POST",
			async:false,
			data:{
				id:order.id,
				commodityType:1
			},
			url:"<%=request.getContextPath()%>/mall/manage/order/getRelateList.action",
			success:function(data){
				if(data&&data.length>0){
					courseList =data;
					renderCourse();
				}else{
					cancel();
				}
			}
		});
		
		}else if(order.commodityType==2){
			$.ajax({
				type:"POST",
				async:false,
				data:{
					id:order.id,
					commodityType:2
				},
				url:"<%=request.getContextPath()%>/mall/manage/order/getRelateList.action",
				success:function(data){
					if(data&&data.length>0){
						topicList =data;
						renderTopic();
					}else{
						cancel()
					}
				}
			})
		}else{
			cancel();
		}
	}
}

//渲染课程
function renderCourse(){
	var htmlStr='';
	htmlStr+='<table width="100%" class="tabl_1" id="courseTab">';
	$.each(courseList,function(index,item){
		htmlStr +='<tr>'+
                      '<th>课程名称：</th>'+
                      '<td >'+'<a href="<%=request.getContextPath()%>/mall/manage/toCourseDetailPage.action?id='+item.id+'" >'+item.name+'</a>'+'</td>'+
                      '<th>课程价格：</th>'+
                      '<td >'+item.price+'</td>'+
                      '<th style="width:180px">课程优惠价格：</th>'+
                      '<td >'+item.cheapPrice+'</td>'+
                      '<th>课程所属：</th>'+
                      '<td >'+item.companyName+'</td>'+
                   '</tr>';
	});
	htmlStr+='</table>';
	var $commodityDiv = $('#commodityDiv');
	$commodityDiv.append(htmlStr);
	$commodityDiv.show();
}

//渲染专题
function renderTopic(){
	var htmlStr='';
	var tempStr='';
	$.each(topicList,function(index,item){
		htmlStr+='<h4 id="topicTitle">专题名称：<span>'+item.name+
		    '</span> 专题编号：<span>'+item.code+'</span> 包含课程：<span>'+item.courseList.length+'</span></h4>'+
    	'<table width="100%" class="tabl_1" >';
    	
    	tempStr = getCourseListByTopic(index,item);
    	htmlStr+=tempStr;
    	htmlStr+='</table>';
	});
	var $commodityDiv = $('#commodityDiv');
	$commodityDiv.append(htmlStr);
	$commodityDiv.show();
}

//根据专题查询课程
function getCourseListByTopic(index,topic){
	var htmlStr='';
	$.each(topic.courseList,function(index,item){
		htmlStr +='<tr>'+
          '<th>课程名称：</th>'+
          '<td >'+'<a href="<%=request.getContextPath()%>/mall/manage/toCourseDetailPage.action?id='+item.id+'" >'+item.name+'</a>'+'</td>'+
          '<th>课程价格：</th>'+
          '<td >'+item.price+'</td>'+
          '<th style="width:180px">课程优惠价格：</th>'+
          '<td >'+item.cheapPrice+'</td>'+
          '<th>课程所属：</th>'+
          '<td >'+item.companyName+'</td>'+
       '</tr>';
	})
	return htmlStr;
}

function getLogisticsDetail(){
	return "暂无物流信息";
}








	
function cancel(){
	history.go(-1);
	//window.location.href="<%=request.getContextPath()%>/mall/manage/toOrderListPage.action";
	}
	




</script>


</head>
<body>
<div class="content">
	<!-- <h3 id="top_page_name">详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="top_page_name" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">详情</span>
	</div>
	<div class="pay">
    	<h4> 订单编号：<span id="code"></span>      订单状态：<span id="status"></span> 发票状态：<span id="invoiceStatus"></span>
    	</h4>
    	<h4>购买信息：</h4>
    	<div id="commodityDiv" style="display:none">
    	<!--  <h4 id="topicTitle">专题名称：<span></span> 专题编号：<span></span> 包含课程：<span></span></h4>
    	<table width="100%" class="tabl_1" id="courseTab">
        <tr>
            	<th>课程名称：</th>
                <td id="name"></td>
                <th>课程价格：</th>
                <td id="price"></td>
                <th>课程所属：</th>
                <td id="companyName"></td>
            </tr>
            
        </table>-->
    	</div>
        <h4>结算信息：</h4>
        <table width="100%" class="tabl_1">
        	<tr>
            	<th style="width:12%">结算方式：</th>
                <td id="type"></td>
            </tr>
          <tr>
            	<th  style="width:12%">下单时间：</th>
                <td id="createTime"></td>
            </tr>
            
          
        </table>
        
        
             <h4>个人信息：</h4>
        <table width="100%" class="tabl_1">
        	<tr>
            	<th  style="width:12%">购买人：</th>
                <td id="userRealName"></td>
            </tr>
          <tr>
            	<th  style="width:12%">联系方式：</th>
                <td id="contactWay"></td>
            </tr>
              <tr>
            	<th  style="width:12%">所属企业：</th>
                <td id="companyName"></td>
            </tr>
              <tr>
            	<th  style="width:12%">备注：</th>
                <td id="remarks"></td>
            </tr>
          
        </table>
        
        <div  id="invoiceInfoDiv" style="display:none">
                   <h4>发票信息：</h4>
        <table width="100%" class="tabl_1">
        	<tr>
            	<th  style="width:12%">发票抬头：</th>
                <td id="invoiceTitle"></td>
            </tr>
            <tr>
            	<th  style="width:12%">联系方式：</th>
                <td id="invoicePhone"></td>
            </tr>
          <tr>
            	<th  style="width:12%">收货地址：</th>
                <td id="invoiceAddress"></td>
            </tr>
              <tr>
            	<th  style="width:12%">邮编：</th>
                <td id="invoicePostcode"></td>
            </tr>
          
        </table>
        </div>
     
        
       <div id="logisticsDiv" style="display:none">
        <h4>物流信息：</h4>
        <table width="100%" class="tabl_2">
        	<tr>
            	<th  style="width:12%">物流公司：</th>
            	<td id="logisticsCompanyName"></td>
            </tr>
           <tr>
            	<th  style="width:12%">物流单号：</th>
                <td id="logisticsNumber"></td>
            </tr>
            <tr>
            	<th  style="width:12%">发货时间：</th>
                <td id="logisticsTime"></td>
            </tr>
            <!--  
            <tr>
            	<th>物流动态：</th>
                <td id="logisticsDetail"></td>
            </tr>-->
        </table>
        
       </div>
        <div class="btn_gr">
    	<input type="button" value="返回" class="btn_1" onclick="javascript:history.go(-1);" />
    	</div>
    </div>
</div> 
<script>
</script>
</body>
</html>
