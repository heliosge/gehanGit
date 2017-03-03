<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />
<title>订单生成</title>
<style type="text/css">
.not_need{padding: 10px;background-color: #ddd;}
.red_star{color: red;}
/*下单窗口样式*/
.pay .tabl_2 td select{width: 130px;}
.btn_div{color: #fff;font-size: 16px;font-weight: bold;margin-top: 50px;text-align: center;}
.btn_div span{padding: 10px 20px 10px 20px;cursor: pointer;}
.btn_div span:nth-child(1){background: #FE4E7C;margin-right: 20px;}
.btn_div span:nth-child(2){background: #d8d8d8;}
/*以下为支付窗口样式*/
.ab_1{width: 300px;background: #F2F2F2;padding: 15px;text-align: center;}
.abc_1{margin-top: 30px;margin-bottom: 20px;}
.abc_1 span{margin-right: 20px;}
.ab_2{position: relative;top: 20px;right: 180px;color: #bbb;}
table tr{height: 50px;}
.abc_hide{display: none;}
.c_btn{padding: 10px 20px 10px 20px;color: #fff;font-weight: bold;cursor: pointer;}
.c_btn:nth-child(1){background: #FE4E7C}
.c_btn:nth-child(2){background: #d8d8d8}
.c_btn:nth-child(3){background: #FE4E7C}
</style>
</head>
<body>
<!-- dialog 结算页面 -->
<div id="payDialog" class="content">
		<form id="addForm">
		    <div class="pay">
		    	<h4>购买信息：</h4>
		        <table id="productTable" width="100%" class="tabl_1">
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
		       
		        <h4><a class="red_star">*&nbsp;</a>请正确填写个人信息：</h4>
		        <table width="100%" class="tabl_2">
		        	<tr>
		            	<th><div style="width: 100px;"><a class="red_star">*&nbsp;</a>购买人：</div></th>
		                <td><input type="text" id="buyPeople" name="buyPeople"/></td>
		            </tr>
		            <tr>
		            	<th><div style="width: 100px;"><a class="red_star">*&nbsp;</a>联系方式：</div></th>
		                <td><input type="text" id="phone" name="phone"/></td>
		            </tr>
		            <tr>
		            	<th><div style="width: 100px;"><a class="red_star">*&nbsp;</a>所属企业：</div></th>
		                <td><input type="text" name="asCompany" id="asCompany" readonly="readonly"/></td>
		            </tr>
		            <tr>
		            	<th><div style="width: 100px;">备注：</div></th>
		                <td><textarea rows="5" cols="100" id="remark" name="remark"></textarea></td>
		            </tr>
		        </table>
		        <h4><a class="red_star">*&nbsp;</a>结算方式：
		         <span>
		        	<input type="radio" name="payType" value="1" checked="checked"/>线上结算&nbsp;&nbsp;
		        	<input type="radio" name="payType" value="2"/>线下结算
		        </span>
		       	</h4>
		        <h4><a class="red_star">*&nbsp;</a>发票信息:
		        	 <span>
			        	<input type="radio" name="isNeedInvoice" value="1" />需要发票&nbsp;&nbsp;
			        	<input type="radio" name="isNeedInvoice" value="2" checked="checked"/>不需要发票
		       		 </span>
		        </h4>
		        <table id="fpxx" width="100%" class="tabl_2" style="display: none;">
		        	<tr>
		            	<th><a class="red_star">*&nbsp;</a>发票抬头：</th>
		                <td><input type="text" id="invoiceHead" name="invoiceHead" disabled="disabled"/></td>
		            </tr>
		            <tr>
		            	<th><a class="red_star">*&nbsp;</a>收货地址：</th>
		                <td>
	                		<span>
								<select id="province" name="province"  disabled="disabled">
								</select>
							</span>
							
							<span>
								<select id="city" name="city"  disabled="disabled">
								</select>
							</span>
							
							<span>
								<select id="area" name="area"  disabled="disabled">
								</select>
							</span>
							<input type="text" id="detailAddress" name="detailAddress"  disabled="disabled"/>
		                 </td>
		            </tr>
		            <tr>
		            	<th><a class="red_star">*&nbsp;</a>邮编：</th>
		                <td><input type="text" id="postcode" name="postcode"  disabled="disabled"/></td>
		            </tr>
		        </table>
		        <div class="btn_div">
					<span onclick="sendOrder()">确认购买</span>
					<span onclick="history.go(-1)">返回</span>
				</div>
		    </div>
	    </form>
</div>
<!-- 支付页面 -->
<div id="payliDialog" style="width: 500px;margin: 0 auto;display: none;">
		<form id="payForm">
			<div class="ab_1"><span>应付金额：</span><span id="needToPay" style="color: red;font-weight: bold;"></span></div>
			<div class="abc_1">
				支付方式：<input type="radio" name="type" checked="checked" value="1"/>支付宝
			</div>
			<div class="abc_1">
				<span class="c_btn">确认支付</span>
				<span class="c_btn" onclick="{layer.closeAll();}">返回</span>
				<span class="c_btn" onclick="{window.open('<%=request.getContextPath()%>/courseStore/toBuyRecords.action')}">进入课程购买记录页面</span>
			</div>
		</form>
	</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript">
var ids = '${ids}';
var curCompanyName = '${USER_BEAN.companyName}';
$("#asCompany").val(curCompanyName);
var productType = '${productType}';//1课程2专题
var needBuyProductsList;
$(function(){
	initValidate();
	initProvince();
	getProductInfoByIds();
});

function getProductInfoByIds(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getProductInfoByIds.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data : {
 			"ids":ids,
 			"type":productType
 		},
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
 				needBuyProductsList = data.rtnDataList;
 				initContent(data.rtnDataList);
 			}
 		}
 	});
}
/**
 * 生成结算页面数据
 */
function initContent(list){
	if(list.length>0){
		var html = '';
		$.each(list,function(index,item){
			var productName = item.name;
			var cheapPrice = item.cheapPrice;
			cheapPrice = Number(cheapPrice).toFixed(2);
			html += '<tr>';
			if(productType==1){
				html += '<th>课程名称：</th>';
				
			}else{
				html += '<th>专题名称：</th>';
			}
			html += '<td>'+productName+'</td>';
			html += '<th>课程价格：</th>';
			html += '<td>' + cheapPrice + '</td>';
			html += '</tr>';
		});
		$('#productTable').html(html);
	}
}


/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right_effect',
		rules : {
			checkPhone:[/^1[358]\d{9}$/,'手机号码格式不正确！'],
			checkPostCode:[ /^[1-9][0-9]{5}$/, '邮政编码格式不正确！' ],
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		fields : {
			buyPeople : {
				rule : "required;length[~30]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			phone : {
				rule : "required;length[~30];checkPhone",
				msg : {
					required : "不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			asCompany : {
				rule : "required",
				msg : {
					required : "不能为空",
				}
			},
			remark : {
				rule : "length[~2000]",
				msg : {
					length : "长度需小于等于2000个字符"
				}
			},
			invoiceHead : {
				rule : "required;length[~30]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			province : {
				rule : "required;",
				msg : {
					required : "不能为空"
				}
			},
			city : {
				rule : "required;",
				msg : {
					required : "不能为空"
				}
			},
			area : {
				rule : "required;",
				msg : {
					required : "不能为空"
				}
			},
			detailAddress : {
				rule : "required;length[~200]",
				msg : {
					required : "不能为空",
					length : "长度需小于等于200个字符"
				}
			},
			postcode : {
				rule : "required;length[6];checkPostCode",
				msg : {
					required : "不能为空",
					length : "6位数字",
				}
			}
		}
	});
}

/**
 * 订单生成
 */
function sendOrder(){
	
	$('#addForm').isValid(function(v){
	    if (v) {
	    	var params = new Object;
			//参数获取
	    	var priceSum = 0;
	    	var idsArr = [];
	    	$.each(needBuyProductsList,function(index,item){
	    		var productId = item.id;
	    		var cheapPrice = item.cheapPrice;
	    		idsArr.push(productId);
	    		priceSum = Number(priceSum) + Number(cheapPrice);
	    	});
	    	var userRealName = $.trim($("#buyPeople").val());
	    	var phone = $.trim($("#phone").val());
	    	var postcode = $.trim($("#postcode").val());
	    	var province = $("#province").children("option:selected").text();
	    	var city = $("#city").children("option:selected").text();
	    	var area = $("#area").children("option:selected").text();
	    	var detailAddress = $("#detailAddress").val();
	    	var remark = $("#remark").val();
	    	var invoiceHead = $.trim($("#invoiceHead").val());
	    	var companyName = $("#asCompany").val();
	    	var address = province +"-"+ city +"-"+ area +" "+ detailAddress;//总地址
	    	var type;
	    	var isNeedInvoice;
	    	$("input[name='payType']").each(function(){
	    		if(this.checked){
	    			type = $(this).val();
	    		}
	    	});
	    	$("input[name='isNeedInvoice']").each(function(){
	    		if(this.checked){
	    			isNeedInvoice = $(this).val();
	    		}
	    	});
	    	
	    	//参数封装
	    	params.commodityType = productType;//当前商品类型
	    	params.price = priceSum;
	    	params.userRealName = userRealName;
	    	params.invoiceTitle = invoiceHead;
	    	params.invoicePhone = phone;
	    	params.contactWay = phone;
	    	params.invoiceAddress = address;
	    	params.invoicePostcode = postcode;
	    	params.type = type;
	    	params.isNeedInvoice = isNeedInvoice;
	    	params.remarks = remark;
	    	params.companyName = companyName;
	    	params.productIds = idsArr.join();
	    	layer.confirm('是否提交订单？', {
				    btn: ['确定','取消'] //按钮
				}, 
				function(){
					var urlStr = "<%=request.getContextPath()%>/courseStore/addMallOrder.action";
			     	$.ajax({
			     		type : "POST",
			     		url : urlStr,
			     		data : params,
			     		success : function(data) {
			     			if(data.rtnResult == "SUCCESS"){
			     				if(type == 2){// 线下支付
			     		    		// 提交订单后 直接进入课程购买页面
			     					layer.closeAll();
			     		    		window.open('<%=request.getContextPath()%>/courseStore/toBuyRecords.action');
				     				if(parent.layer){
				     					parent.layer.closeAll();
				     				}
			     		    		return;
			     		    	}
			     				var result  = data.rtnData;
			     				var orderId = result.id;// 订单id
			     				window.open('<%=request.getContextPath()%>/courseStore/toAlipay.action?orderId='+orderId);
			     				layer.closeAll();
			     				if(parent.layer){
			     					parent.layer.closeAll();
			     				}
			     			}else{
			     				layer.alert('订单生成失败');
			     			}
			     		}
			     	});
				}, 
				function(index){
					layer.closeAll();
				}
			);
	    }
	});
}

 
function initProvince(){
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		url:"<%=request.getContextPath()%>/login/initCity.action",
   		success:function(data){
   			var re = "<option value=''>请选择</option>";
   			for(var i=0;i<data.length; i++){
   				if(data[i].parent_id == 0){
   					re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
   				}
   			}
   			$("#province").html(re);
   	    }
   	});
}

function initCity(provinceId){
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			provinceId : provinceId
   		},
   		url:"<%=request.getContextPath()%>/login/initCity.action",
   		success:function(data){
   			var re = '<option value="">请选择</option>';
   			for(var i=0;i<data.length; i++){
   				re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
   			}
   			$("#city").html(re);
   	    }
   	});
}

function initArea(cityId){
	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			"cityId" : cityId
   		},
   		url:"<%=request.getContextPath()%>/integralStore/initArea.action",
   		success:function(data){
   			var re = '<option value="">请选择</option>';
   			for(var i=0;i<data.length; i++){
   				re += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
   			}
   			$("#area").html(re);
   	    }
   	});
}
/**
 * 省市切换变动
 */
(function bindProvinceCityChange(){
	$("#province").change(function(){
		$("#city").empty();
		$("#area").empty();
		initCity($("#province").val());
	});
	$("#city").change(function(){
		$("#area").empty();
		initArea($("#city").val());
	});
})()
function removeValidate(){
	$('#fpxx input').each(function(){
		$(this).attr("disabled","disabled");
	});
	$('#fpxx select').each(function(){
		$(this).attr("disabled","disabled");
	});
}

function addValidate(){
	$('#fpxx input').each(function(){
		$(this).removeAttr("disabled");
	});
	$('#fpxx select').each(function(){
		$(this).removeAttr("disabled");
	});
}

(function(){
	//此处为加载弹出层中的页面切换效果 
	$("input[name='isNeedInvoice']").change(function(){
		var theVal = $(this).val();
		if(theVal==1){//需要发票
			$("#fpxx").show();
			addValidate();
		}else{//不需要发票
			removeValidate();
			$("#fpxx").hide();
		}
	});
})()
</script>
</html>