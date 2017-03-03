<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mall/mall.css" />

<title>购物车</title>
<style type="text/css">
.s_tab{height: 30px;color: #fff;}
.s_tab span{padding: 10px 15px 10px 15px;background: #d8d8d8;cursor: pointer;margin: 0;}
.s_tab span:nth-child(1){background: #FA9D55;}
</style>
</head>
<body>
	<div style="width: 1046px;  margin: 0 auto;padding-top: 16px">
    <!-- <h4>购物车</h4> -->
     <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">购物车</span>
	</div>
</div>
<div class="gw_list">
	<div class="list_sc">
    	<a onclick="getShoppingCartInfo()"><img src="<%=request.getContextPath() %>/images/mall/img/ico_search_1.png"/></a>
    	<input id="searchName" type="text" onkeydown="{if(event.keyCode==13){getShoppingCartInfo()}}" onfocus="if(this.value == '搜索商品名称'){this.value = '';}" onblur="if(this.value == ''){this.value = '搜索商品名称';}" value="搜索商品名称" />
    </div>
    <div class="s_tab">
		<span style="margin-right: -6px;" onclick="switchTab(1,this)">课程</span>
		<span onclick="switchTab(2,this)">专题</span>
    </div>
	<div class="list_sp">
    	<ul class="sp_1">
        	<li>
            	<input type="checkbox" name="all"/>
                <span>全选</span>
            </li>
            <li>
            	<span>商品名称</span>
            </li>
            <li>
            	<span>商品编号</span>
            </li>
            <li>
            	<span>商品价格</span>
            </li>
            <li class="last_3">
            	<span>操作</span>
            </li>
        </ul>
        <div id="content" style="padding-top:0;">
        	<%-- <p class="dp">
        	<input type="checkbox" />
            <span>店铺:</span>
            <b>这里是课程提供商的名字</b>
            <img src="<%=request.getContextPath() %>/images/mall/img/ico_24.png" />
	        </p>
	        <ul class="sp_2">
	        	<li class="li_fir">
	            	<input type="checkbox" />
	            </li>
	            <li class="li_2">
	            	<img src="<%=request.getContextPath() %>/images/mall/img/bg_buy.png" />
	            	<span>机械设计</span>
	            </li>
	            <li>
	            	<span>kmgt5220</span>
	            </li>
	            <li>
	            	<strong>95.00</strong>
	            </li>
	            <li class="last_3">
	            	<span><a href="#">删除</a></span>
	            </li>
	        </ul> --%>
        </div>
        
        <p class="dp_1">
        	<input type="checkbox" name="all"/>
            <span>全选</span>
            <a onclick="removeCarInfo(null,2)">删除</a>
            <a onclick="removeCarInfo(null,3)">清空失效商品</a>
            <input id="payBtn" type="button" value="去付款" class="fk" />
            <strong>共有<b id="counts">0</b>件商品，总计：<em id="price_sum">￥0.00</em></strong>
        </p>
    </div>
</div>

</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript">
var tabType=1;
$(function(){
	getShoppingCartInfo();
	
});
/**
 * 获取当前用户的购物车信息
 */
function getShoppingCartInfo(){
	var searchName = $("#searchName").val();
	if(searchName  =='搜索商品名称'){
		searchName = '';
	}
	var urlStr = "<%=request.getContextPath()%>/courseStore/getShoppingCartInfo.action";
 	$.ajax({
 		type : "POST",
 		async : false,
 		url : urlStr,
 		data : {
 			"productType":tabType,
 			"searchName":searchName},
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
 				initContent(data.rtnDataList);
 			}
 		}
 	});
}

/**
 * 生成购物车信息
 */
function initContent(list){
	var html = '';
	if(list && list.length>0){
		var tmpCompanyId; // 临时记录当前的课程所属企业
		$.each(list,function(index,item){
			var id = item.id; // 购物车主键
			var productId = item.productId; // 商城课程id
			var productName = item.name;
			var titleProductName = productName;
			if(productName && productName.length > 10){
				productName = productName.substring(0,10);
				productName +='...';
			}
			var code = item.code;
			var titleCode = code;
			if(code && code.length > 10){
				code = code.substring(0,10);
				code +='...';
			}
			var productType = item.productType; // 商品类型 1课程 2专题
			var cheapPrice = item.cheapPrice;
			cheapPrice = Number(cheapPrice).toFixed(2);
			var img = item.img;
			var companyId = item.companyId; // 当前的课程所属企业id
			var subCompanyId = item.subCompanyId; // 当前的课程所属企业id
			var companyName = item.companyName; // 当前的课程所属企业名称
			var isEffective = item.isEffective; // 1有效 2失效
			if(subCompanyId != tmpCompanyId){ // 第一次的时候需要加入所属企业信息头部
				tmpCompanyId = subCompanyId;
				if(companyId == 1){
					companyName = "安培在线";
				}
				html += '<p class="dp">';
				html += '<input type="checkbox" name="head_'+tmpCompanyId+'"/>';
				html += '<span>商家:</span>';
				html += '<b>'+companyName+'</b>';
				html += '<img src="<%=request.getContextPath() %>/images/mall/img/ico_24.png" />';
				html += '</p>';
			}
			
			if(isEffective!=1){//失效产品
				html += '<ul class="sp_2" name="notEffect_'+id+'">';
				html += '<li class="li_fir">';
				html += '<span class="not_need">失效</span>';
				html += '</li>';
				html += '<li class="li_2">';
				html += '<img style="width:108px;height:80px;" src="'+img+'" />';
				html += '<span title="'+titleProductName+'">'+productName+'</span>';
				html += '</li>';
				html += '<li>';
				html += '<span title="'+titleCode+'">'+code+'</span>';
				html += '</li>';
				html += '<li>';
				html += '<strong>'+cheapPrice+'</strong>';
				html += '</li>';
				html += '<li class="last_3">';
				html += '<span><a onclick="removeCarInfo('+id+')">删除</a></span>';
				html += '</li>';
				html += '</ul>';
			}else{
				html += '<ul class="sp_2">';
				html += '<li class="li_fir">';
				html += '<input type="checkbox" name="product_'+tmpCompanyId+'" value="'+id+'"/>';
				html += '<input type="hidden" name="productId" value="'+productId+'"/>';
				html += '<input type="hidden" name="cheapPrice" value="'+cheapPrice+'"/>';
				html += '</li>';
				html += '<li onclick="toCourseDetail('+productId+')" class="li_2">';
				html += '<img style="width:108px;height:80px;" src="'+img+'" />';
				html += '<span title="'+titleProductName+'">'+productName+'</span>';
				html += '</li>';
				html += '<li>';
				html += '<span title="'+titleCode+'">'+code+'</span>';
				html += '</li>';
				html += '<li>';
				html += '<strong>'+cheapPrice+'</strong>';
				html += '</li>';
				html += '<li class="last_3">';
				html += '<span><a onclick="removeCarInfo('+id+',1)">删除</a></span>';
				html += '</li>';
				html += '</ul>';
			}
			
		});
	}
	$("#content").empty();
	$("#content").html(html);
	bindCheckChange();
}
function switchTab(type,theObj){
	//切换时 去掉全选勾选
	$('input[name="all"]').each(function(){
		this.checked = false;
	});
	//重置付款按钮 和数量、金额统计
	$("#counts").html(0);
	$("#price_sum").html('￥0.00');
	
	tabType = type;
	$(".s_tab span").each(function(){
		$(this).css("background","#d8d8d8");
	});
	$(theObj).css("background","#FA9D55");
	getShoppingCartInfo();
}
function bindCheckChange(){
	$("input[type='checkbox']").change(function(){
		var aName = $(this).attr("name");
		var checkedStatus = this.checked;
		//根据已定的规则 判断当前勾选中的为哪种复选框
		if(aName.startWith('head')){//
			var comId = aName.split('_')[1];//获取到当前head为哪家企业
			$('input[name="product_'+comId+'"]').each(function(){
				this.checked = checkedStatus;
			});
		}else if(aName=='all'){
			$('input[type="checkbox"]').each(function(){
				this.checked = checkedStatus;
			});
		}
		pulicCountSum();
	});
}
function pulicCountSum(){
	//这里每次都会去检测当前勾选中的产品的数量和价格
	var allChecked = $('input[name^="product_"]:checked');
	var count = allChecked.size();// 获取当前勾选中的商品数量
	var priceSum = 0;
	allChecked.each(function(){
		var tmpPrice = $(this).parent().find('input[name="cheapPrice"]').val();
		priceSum = Number(priceSum) + Number(tmpPrice);
	});
	priceSum = Number(priceSum).toFixed(2);
	$("#counts").html(count);
	$("#price_sum").html('￥'+priceSum);
	
	//这里控制付款按钮的样式和事件
	if(count>0){
		$('#payBtn').css('background','#FE4E7C');
		$('#payBtn').unbind('click',goGenerateOrder);
		$('#payBtn').bind('click',goGenerateOrder);
	}else{
		$('#payBtn').css('background','#d8d8d8');
		$('#payBtn').unbind('click',goGenerateOrder);
	}
}
/**
 * 跳转下单页面
 */
function goGenerateOrder(){
	var ids = [];
	var allChecked = $('input[name^="product_"]:checked');
	allChecked.each(function(){
		var productId = $(this).parent().find('input[name="productId"]').val();// 商品id
		ids.push(productId);
	});
	window.location.href = '<%=request.getContextPath()%>/courseStore/toGenerateOrder.action?ids='+ids.join()+'&productType='+tabType;
}
/**
 * 购物车记录移除
 */
function removeCarInfo(id,opType){// opType ：1 单删  2批量删 3 清空失效
	var idsArr = [];
	if(opType == 1){//单个删除
		idsArr.push(id);
	}else if(opType == 2){// 批量删除
		var allChecked = $('input[name^="product_"]:checked');
		var len = allChecked.size();
		if(len==0){
			layer.alert('请先选择数据！');
			return ;
		}else{
			allChecked.each(function(){
				var tmpId = $(this).val();
				idsArr.push(tmpId);
			});
		}
	}else if(opType == 3){// 清空失效
		//获取失效的记录
		var ulCole = $("ul[name^='notEffect_']");
		if(ulCole.size()==0){
			layer.alert('当前商品类目下无失效宝贝！');
			return;
		}
		ulCole.each(function(){
			var name = $(this).attr("name");
			var tmpid = name.split("_")[1];
			idsArr.push(tmpid);
		});
	}
	
	var index1 = layer.confirm('确认移除?',{icon: 0, title:'提示'},function(index){
		layer.close(index1);
		var urlStr = "<%=request.getContextPath()%>/courseStore/removeShoppingRecord.action";
	 	$.ajax({
	 		type : "POST",
	 		url : urlStr,
	 		data : {
	 			"ids":idsArr.join()
	 		},
	 		success : function(data) {
	 			if(data.rtnResult == "SUCCESS"){
	 				getShoppingCartInfo();
	 				pulicCountSum();
	 				//去除全选状态
	 				$('input[name="all"]').each(function(){this.checked = false;});
	 			}
	 		}
	 	});
	});
}

/**
 * 跳转商城课程详情页面
 */
function toCourseDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toMallCoursetDetail.action?id='+id;
}

/**
 * 方法扩展
 */
(function(){
	String.prototype.startWith = function(str) {
		var reg = new RegExp("^" + str);
		return reg.test(this);
	}

	String.prototype.endWith = function(str) {
		var reg = new RegExp(str + "$");
		return reg.test(this);
	}
})()

</script>
</html>