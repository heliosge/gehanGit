<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账户信息</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<style type="text/css">
#content_i .left_nav{padding-top:0}
.pay{width: auto;}
.btn_1{width: 128px;
    height: 36px;
    margin-right: 24px;
    border-radius: 4px;
    border: none;
    color: #fff;
    font-weight: bold;
    background: url(../images/mall/img/bg_1.png) repeat-x;
    }
.pay .tabl_1 th{width: 60px;}
</style>
<script>
var orderId = '${orderId}';//当前订单id

$(function(){
	getOrderDetailByOrderId();
});
var artDialog;
function uploadHeadPhoto(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/toUploadPhoto.action",
        title:"选择头像",
        height: 250,
		width: 450
		}).showModal();
}
/**
 * 获取订单详情
 */
function getOrderDetailByOrderId(){
	var urlStr = "<%=request.getContextPath()%>/integralStore/getOrderDetailByOrderId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data:{
			"orderId" : orderId
			},
		success : function(data) {
			if(data.rtnResult=="SUCCESS"){
				initPage(data.rtnData);
			}
		}
	});
}
/**
 * 页面信息生成
 */
function initPage(data){
	if(data){
		var commodityName = data.commodityName;
		var commodityCount = data.commodityCount;
		var allIntegral = data.allIntegral;
		var createTime = data.createTime;
		createTime = getFormatDateByLong(createTime, "yyyy-MM-dd hh:mm:ss");
		var descr = data.descr;
		var userRealName = data.userRealName;
		var phoneNum = data.phoneNum;
		var address = data.address;
		var postcode = data.postcode;
		var logisticsCompanyId = data.logisticsCompanyId;
		var logisticsNumber = data.logisticsNumber;
		var logisticsTime = data.logisticsTime;
		var status = data.status;
		if(status==0){
			status = "未发货";
		}else if(status==1){
			status = "已发货";
		}else if(status==2){
			status = "已完成";
		}else{
			status = "";
		}
		var table1Tr = $("#table1 tr");
		table1Tr.eq(0).find("td").html(commodityName);
		table1Tr.eq(1).find("td").html(commodityCount+"件");
		table1Tr.eq(2).find("td").html('总计'+allIntegral+'积分');
		table1Tr.eq(3).find("td").html(createTime);
		table1Tr.eq(4).find("td").html(descr);
		table1Tr.eq(5).find("td").html(status);
		
		var table2Tr = $("#table2 tr");
		table2Tr.eq(0).find("td").html(userRealName);
		table2Tr.eq(1).find("td").html(phoneNum);
		table2Tr.eq(2).find("td").html(address);
		table2Tr.eq(3).find("td").html(postcode);
	}
}
</script>

</head>

<body>
<div id="content_i">
	<div class="left_nav">
		<img id="headPhoto" src="${USER_BEAN.headPhoto }" style="height:188px;width:256px;cursor:pointer;" onclick="uploadHeadPhoto()"/>
    	<h3>
        	<span style="margin-left:-15px;">${USER_NAME_STRING }</span>
        </h3>
        <h4>账户信息</h4>
        <ul  id="left_nav">
            <li><a href="#" class="active_3">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toPersonnalInfo.action">个人信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toChangePassword.action">修改密码</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyCertificate.action">我的证书</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMyReceiveNotice.action">站内信-收件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySendNotice.action">站内信-发件箱</a></li>
            <li><a href="<%=request.getContextPath()%>/manageUser/toMySystemNotice.action">站内信-系统消息</a></li>
            <li class="active_2"><a href="<%=request.getContextPath()%>/integralStore/toMyIntegealExchange.action">我兑换的商品</a></li>
        </ul>
    	
    </div>
    <div class="right_options" id="rop">
        <div class="content">
        	<h3>订单详情</h3>
    <div class="pay">
    	<h4>购买信息：</h4>
        <table id="table1" width="100%" class="tabl_1">
        	<tr>
            	<th>商品名称：</th>
                <td></td>
            </tr>
            <tr>
            	<th>兑换数量：</th>
                <td></td>
            </tr>
            <tr>
            	<th>兑换积分：</th>
                <td></td>
            </tr>
            <tr>
            	<th>订购时间：</th>
                <td></td>
            </tr>
            <tr>
            	<th>备注：</th>
                <td></td>
            </tr>
            <tr>
            	<th>订单状态：</th>
                <td></td>
            </tr>
        </table>
        <h4>收货信息：</h4>
        <table id="table2" width="100%" class="tabl_2">
        	<tr>
            	<th>收货人：</th>
                <td></td>
            </tr>
            <tr>
            	<th>收货人联系方式：</th>
                <td></td>
            </tr>
            <tr>
            	<th>所属地址：</th>
                <td></td>
            </tr>
            <tr>
            	<th>邮编：</th>
                <td></td>
            </tr>
        </table>
        <!-- <h4>物流信息:</h4>
        <table width="100%" class="tabl_2">
        	<tr>
            	<th>物流公司：</th>
                <td>顺丰快递</td>
            </tr>
            <tr>
            	<th>物流单号：</th>
                <td>
                    2873 377 323342
                </td>
            </tr>
            <tr>
            	<th>发货时间：</th>
                <td>2014.6.5 15:00:00</td>
            </tr>
            <tr>
            	<th>物流动态：</th>
                <td></td>
            </tr>
        </table> -->
        <div class="btn_gr">
    	<input type="button" value="返回" class="btn_1" onclick="javascript:history.go(-1);" />
    	</div>
    </div>
		</div>
     </div>
</div>
</body>

</html>
