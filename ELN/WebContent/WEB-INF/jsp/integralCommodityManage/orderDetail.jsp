<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分商品兑换详情</title>
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

var showImageCount=0;

$(function() {
	if(order&&order.id){
   		
   		id=order.id;
   		//回填值
   		$("#name").text(order.name);
   		$("#commodityCount").text(order.commodityCount);
   		$("#createTime").text(order.createTime);
   		$("#allIntegral").text(order.allIntegral);
   		$("#stock").text(order.stock);
   		$("#descr").html(order.descr);
   		$("#allIntegral").text(order.allIntegral);
   		$("#stock").text(order.stock);
   		$("#descr").html(order.descr);
   		if(order.status==1){
   			$("#status").text("已发货");
   			$("#logisticsCompanyName").text(order.logisticsCompanyName);
   			$("#logisticsNumber").text(order.logisticsNumber);
   			$("#logisticsTime").text(order.logisticsTime);
   			var logisticsDetail = getLogisticsDetail();
   			$("#logisticsDetail").text(logisticsDetail);
   			$("#logisticsDiv").show();
   		}else if(order.status==2){
   			$("#status").text("已完成");
   			$("#logisticsCompanyName").text(order.logisticsCompanyName);
   			$("#logisticsNumber").text(order.logisticsNumber);
   			$("#logisticsTime").text(order.logisticsTime);
   			var logisticsDetail = getLogisticsDetail();
   			$("#logisticsDetail").text(logisticsDetail);
   			$("#logisticsDiv").show();
   		}else{
   			$("#status").text("未发货");
   		}
   		
   		$("#userRealName").text(order.userRealName);
   		$("#phoneNum").text(order.phoneNum);
   		$("#address").html(order.address);
   		$("#postcode").text(order.postcode);
   		
   	 
   	}else{
   		history.go(-1);
   		//window.location.href="<%=request.getContextPath()%>/integral/commodity/manage/toCommodityListPage.action";
   	}
	
});

function getLogisticsDetail(){
	return "暂无物流信息";
}








	
function cancel(){
	history.go(-1);
	//window.location.href="<%=request.getContextPath()%>/integral/commodity/manage/toOrderListPage.action";
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
    	<h4>订单详情：</h4>
        <table width="100%" class="tabl_1">
        	<tr>
            	<th>商品名称：</th>
                <td id="name"></td>
            </tr>
            <tr>
            	<th>兑换数量：</th>
                <td id="commodityCount"></td>
            </tr>
            <tr>
            	<th>兑换积分：</th>
                <td id="allIntegral"></td>
            </tr>
            <tr>
            	<th>订购时间：</th>
                <td id="createTime"></td>
            </tr>
            <tr style="height:100px">
            	<th>备注：</th>
                <td id="descr" >课程名称1</td>
            </tr>
            <tr>
            	<th>订单状态：</th>
                <td id="status">课程名称1</td>
            </tr>
        </table>
        <h4>收货信息：</h4>
        <table width="100%" class="tabl_1">
        	<tr>
            	<th>收货人：</th>
                <td id="userRealName"></td>
            </tr>
          <tr>
            	<th>收货人联系方式：</th>
                <td id="phoneNum"></td>
            </tr>
            <tr>
            	<th>收货地址：</th>
                <td id="address"></td>
            </tr>
            <tr>
            	<th>邮编：</th>
                <td id="postcode"></td>
            </tr>
          
        </table>
       <div id="logisticsDiv" style="display:none">
        <h4>物流信息：</h4>
        <table width="100%" class="tabl_2">
        	<tr>
            	<th>物流公司：</th>
                <td id="logisticsCompanyName"></td>
            </tr>
           <tr>
            	<th>物流单号：</th>
                <td id="logisticsNumber"></td>
            </tr>
            <tr>
            	<th>发货时间：</th>
                <td id="logisticsTime"></td>
            </tr>
            <!--  
            <tr>
            	<th>物流动态：</th>
                <td id="logisticsDetail"></td>
            </tr>
            -->
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
