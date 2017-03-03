<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>课程商城--订单详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<style type="text/css">
.btn_1{width: 128px;height: 36px;margin-right: 24px;border-radius: 4px;border: none;color: #fff;
		font-weight: bold;background: url(../images/mall/img/bg_1.png) repeat-x;}
.content h3{margin: 0 auto; background: url(../images/mall/img/ico_4.png) no-repeat left 2px;width: 1052px;
		padding-left: 20px;color: #3a3a3a;border-bottom: 1px solid #cccccc;padding-bottom: 10px;margin-bottom: 10px;margin-top: 20px;}
</style>
</head>
<body>
<div class="content">
	<!-- <h3>订单详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">订单详情</span>
	</div>
    <div class="pay">
    	<h4 id="orderCode">订单编号：</h4>
    	<h4>购买信息：</h4>
        <table id="productInfo" width="100%" class="tabl_1">
        	<tr>
            	<th>课程名称：</th>
                <td></td>
                <th>课程价格：</th>
                <td></td>
            </tr>
            <tr>
            	<th>课程名称：</th>
                <td></td>
                <th>课程价格：</th>
                <td></td>
            </tr>
        </table>
        <h4>结算信息：</h4>
        <table id="payInfo" width="100%" class="tabl_1">
        	<tr>
            	<th>结算方式：</th>
                <td></td>
                <th>结算金额：</th>
                <td></td>
            </tr>
            <tr>
            	<th>下单时间：</th>
                <td></td>
                <th>付款时间：</th>
                <td></td>
            </tr>
        </table>
        <h4>个人信息：</h4>
        <table id="peopleInfo" width="100%" class="tabl_2">
        	<tr>
            	<th>购买人：</th>
                <td></td>
            </tr>
            <tr>
            	<th>联系方式：</th>
                <td></td>
            </tr>
            <tr>
            	<th>所属企业：</th>
                <td></td>
            </tr>
            <tr>
            	<th>备注：</th>
                <td></td>
            </tr>
        </table>
        <h4 id="invoiceTitle">发票信息:</h4>
        <table id="invoiceInfo" width="100%" class="tabl_2" style="display: none;">
        	<tr>
            	<th>发票抬头：</th>
                <td></td>
            </tr>
            <tr>
            	<th>收货地址：</th>
                <td> </td>
            </tr>
            <tr>
            	<th>邮编：</th>
                <td></td>
            </tr>
        </table>
       <!--  <h4>物流信息:</h4>
        <table id="logisticsInfo" width="100%" class="tabl_2">
        	<tr>
            	<th>物流公司：</th>
                <td></td>
            </tr>
            <tr>
            	<th>物流单号：</th>
                <td></td>
            </tr>
            <tr>
            	<th>发货时间：</th>
                <td></td>
            </tr>
            <tr>
            	<th>物流动态：</th>
                <td></td>
            </tr>
        </table> -->
        <h4>评价信息:<span id="evaluateInfo"></span></h4>
        <div class="btn_gr">
    	<input type="button" value="返回" class="btn_1" onclick="javascript:history.go(-1);" />
    	</div>
    </div>
</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript">
var orderId = '${orderId}';
$(function(){
	getOrderDetailsById();
});

function getOrderDetailsById(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getOrderDetailsById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"id":orderId},
		success : function(data) {
			if(data.rtnResult == "SUCCESS"){
				initContent(data.rtnData);
			}
		}
	});
}
/**
 * 生成页面信息
 */
function initContent(data){
	var html = '';
	if(data){
		var code = data.code;
		var commodityType = data.commodityType;// 订单类型  1课程，2专题
		var companyName = data.companyName;// 购买人所属企业
		var contactWay = data.contactWay; // 购买人联系方式
		var type = data.type; // 订单类型：1线上，2线下
		var typeStr;
		if(type==1){
			typeStr = '支付宝结算';
		}else{
			typeStr = '线下结算';
		}
		var status = data.status; // 状态：0待付款，1已付款待发货，2已发货，3已评价，4已失效,5已取消
		var createTime = data.createTime;
		createTime = getFormatDateByLong(createTime, "yyyy-MM-dd hh:mm:ss");
		var payTime = data.payTime; // 支付时间
		if(payTime){
			payTime = getFormatDateByLong(payTime, "yyyy-MM-dd hh:mm:ss");
		}else{
			payTime = '';
		}
		var price = data.price;
		var remarks = data.remarks;
		var userRealName = data.userRealName;
		var invoiceTitle = data.invoiceTitle;
		var invoiceAddress = data.invoiceAddress;
		var invoicePostcode = data.invoicePostcode;
		var isNeedInvoice = data.isNeedInvoice; // 是否需要发票：1需要2不需要
		var evaluation = data.evaluation;
		var relateProducts = data.relateProducts; // 订单中的商品信息
		if(relateProducts.length>0){
			$.each(relateProducts,function(index,item){
				var titleName = '';
				var productId = item.relateId; // 商品id
				var productPrice = item.price; // 商品价格
				var productCommodityType = item.commodityType; // 商品类型
				var productCommodityName = item.commodityName; // 商品名称
				html += '<tr>';
				if(productCommodityType == 1){
					titleName = '课程';
				}else{
					titleName = '专题';
				}
				html += '<th>'+titleName+'名称：</th>';
				html += '<td><a href="javascript:void(0);" onclick="goDetails('+productId+','+productCommodityType+')">'+productCommodityName+'</a></td>';
				html += '<th>'+titleName+'价格：</th>';
				html += '<td>'+productPrice+'</td>';
				html += '</tr>';
			});
		}
		
		$('#evaluateInfo').html(evaluation);
		$('#orderCode').html('订单编号：'+code);
		$('#productInfo').html(html);
		
		var table1Tr = $('#payInfo tr');
		var table2Tr = $('#peopleInfo tr');
		var table3Tr = $('#invoiceInfo tr');
		
		
		table1Tr.eq(0).find("td").eq(0).html(typeStr);
		table1Tr.eq(0).find("td").eq(1).html(price);
		table1Tr.eq(1).find("td").eq(0).html(createTime);
		table1Tr.eq(1).find("td").eq(1).html(payTime);
		
		
		table2Tr.eq(0).find("td").html(userRealName);
		table2Tr.eq(1).find("td").html(contactWay);
		table2Tr.eq(2).find("td").html(companyName);
		table2Tr.eq(3).find("td").html(remarks);
		
		if(isNeedInvoice == 1){
			$("#invoiceInfo").show();
			table3Tr.eq(0).find("td").html(invoiceTitle);
			table3Tr.eq(1).find("td").html(invoiceAddress);
			table3Tr.eq(2).find("td").html(invoicePostcode);
		}else{
			$("#invoiceTitle").html("发票信息：不需要发票");
		}
		
		
	}
}
/**
 * 跳转商品详情
 */
function goDetails(id,type){
	if(type == 1){
		window.location.href = '<%=request.getContextPath()%>/courseStore/toMallCoursetDetail.action?id='+id;
	}else{
		window.location.href = '<%=request.getContextPath()%>/courseStore/toTopicDetail.action?id='+id;
	}
}
</script>
</html>