<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程购买记录</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<style type="text/css">
.ui-dialog-body{padding:0px;}
.btnSubmit{width: 60px;height: 30px;color: #fff;background: #d20001}
</style>
</head>
<body>
<div class="content">
	<!-- <h3>课程购买记录</h3> -->
	 <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程购买记录</span>
	</div>
    <div class="btn_gr">
     	<input type="button" class="btn_2" value="导出记录" onclick="exportMes()"/>
        <input type="button" class="btn_2" value="批量删除" onclick="deleteByIds()"/>
    </div>
    <form id="form">
		<div class="search_2">
	    	<p>订单编号：
	            <input type="text" id="orderCode" name="orderCode"/>
	        </p>
	    	<p>课程名称：
	            <input type="text" id="courseName" name="courseName"/>
	        </p>
	       <p>课程价格：
	            <input type="text" id="priceFrom" name="priceFrom"/>
	            <span>至</span>
	            <input type="text" id="priceTo" name="priceTo"/>
	        </p>
		</div>
	    <div class="search_2" style="margin-top:-1px;">
	    	<p>下单时间：
	            <input type="text" id="orderCreateStartTime" name="orderCreateStartTime"/>
	            <span>至</span>
	            <input type="text" id="orderCreateEndTime" name="orderCreateEndTime"/>
	        </p>
	    	<p>购买状态：
	    		<select id="buyStatus">
	    		<option value="">全部状态</option>
	    		<option value="0">待付款</option>
	    		<option value="2">已发货</option>
	    		<option value="3">已评价</option>
	    		<option value="4">已失效</option>
	    		<option value="5">已取消</option>
	    		</select>
	        </p>
	        <p>发票状态：
	    		<select id="invoiceStatus">
		    		<option value="">全部状态</option>
		    		<option value="0">待邮寄</option>
		    		<option value="1">已邮寄</option>
	    		</select>
	        </p>
	     	<input type="button" value="重置" onclick="doReset();"/>
	        <input type="button" value="查询" class="btn_cx" onclick="query();" />
		</div>
	</form>
	<div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="tableData"></table>
	  	<div id="paginator" style="text-align:right;"></div>
     </div>
</div>
<div id="evluateDialog" style="display: none;">
		<div>
			<div style="margin-top: 30px;"><p>我要评价：</p><textarea rows="8" cols="30" id="evaluateContent"></textarea></div>	
        	<p style="margin-top: 30px;"><input class="btnSubmit" type="button" value="提交" onclick="giveEvaluate()"/></p>
		</div>
     </div>
<form id="exportForm"></form>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	initDates();
	initDataList();
});


/**
 * 重置
 */
function doReset(){
	$("#form")[0].reset();
	$("input[name='timeliness']").each(function(){
		$(this)[0].checked = false;
	});
	$('#tableData').mmGrid().load({"page":1});
}
/**
 * 查询
 */
function query(){
	var priceFrom = $.trim($('#priceFrom').val());
	var priceTo = $.trim($('#priceTo').val());
	if(!$.isNumeric(priceFrom) && priceFrom!=''){
		$('#tableData').mmGrid().load([]);
		return;
	}
	if(!$.isNumeric(priceTo) && priceTo!=''){
		$('#tableData').mmGrid().load([]);
		return;
	}
	
	$('#tableData').mmGrid().load({"page":1});
}
function initDataList(){
	$('#tableData').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/courseStore/getBuyRecords.action',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: true,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var params = new Object;
	    	params.orderCode = $.trim($('#orderCode').val());
	    	params.courseName = $.trim($('#courseName').val());
	    	params.priceFrom = $.trim($('#priceFrom').val());
	    	params.priceTo = $.trim($('#priceTo').val());
	    	params.orderCreateStartTime = $.trim($('#orderCreateStartTime').val());
	    	params.orderCreateEndTime = $.trim($('#orderCreateEndTime').val());
	    	params.buyStatus = $.trim($('#buyStatus').val());
	    	params.invoiceStatus = $.trim($('#invoiceStatus').val());
	    	return params;
	    },
	    cols: [{title: 'ID', name: 'id',hidden:true},
	           {title:'订单编号', name:'code', width:160,align:'center',lockWidth:true},
	           {title:'商品名称', name:'productNames', width:80,align:'center',
	        	   renderer:function(val,item, rowIndex){
	        		   var tmpName = val;
	        		   if(val.length>10){
	        			   val = val.substring(0,11);
	        			   val += '...';
	        		   }
	        		   return '<span title="'+tmpName+'">'+val+'</span>';
              		}
	           },
	          	{title:'商品类型', name:'commodityType', width:80,align:'center',lockWidth:true,
	        	   renderer:function(val,item, rowIndex){
            		   if(val == 1){
            			   return "课程"; 
            		   }else if(val == 2){
            			   return "专题";
            		   }
              		}
	          	},
               {title:'订单价格',name:'price', width:80,align:'center',lockWidth:true},
               {title:'下单时间',name:'createTime', width:120,align:'center',lockWidth:true,
	          		renderer:function(val,item, rowIndex){
	          			return getFormatDateByLong(val,'yyyy-MM-dd hh:mm:ss');
	              	}
	          	},
               {title:'购买状态',name:'status', width:45,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(val == 0){
            			   return "待付款"; 
            		   }else if(val == 1){
            			   return "待发货";
            		   }else if(val == 2){
            			   return "已发货";
            		   }else if(val == 3){
            			   return "已评价";
            		   }else if(val == 4){
            			   return "已失效";
            		   }else if(val == 5){
            			   return "已取消";
            		   }
              		}
              	},
              	{title:'发票状态',name:'invoiceStatus', width:45,align:'center',
              		 renderer:function(val,item, rowIndex){
		 				 var str='';
		 				 if(item.isNeedInvoice==1){
							if(val != 1){
							      return "待邮寄";
							}else if(val == 1){
							      return "已邮寄";
							}
                             // 可能这部分才是正确的，取自：courseOrderList.jsp
		 					 // if(val==0){
		 	 				// 	str='待邮寄' ;
		 	 				//  }else if(val==1){
		 	 				// 	str='已邮寄' ;
		 	 				//  }else if(val==2){
		 	 				// 	str='已签收' ;
		 	 				//  } 
		 				 }else{
		 					 str='不需要发票';
		 				 }
		 				
		 				 return str;
              		 }
               	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var id = item.id;
                		var code = item.code;
                		var status = item.status;
                		var str = '';
                		var str1 = '';
                		var str2 = '';
                		var str3 = '';
                		var str4 = '';
                		var str5 = '';
                		if(status==2){//已发货的才能评价
	                		str1 = '<a href="javascript:void(0)"; onclick="evaluateD('+id+')">评价</a>&nbsp;';
                		}
                		if(status==0){//未付款的才允许取消
                			str2 = '<a href="javascript:void(0)"; onclick="doCancel('+id+')">取消订单</a>&nbsp;';
	                		str5 = '<a href="javascript:void(0)";" onclick="gotoPay('+id+')">付款</a>&nbsp;';
                		}
                		str3 = '<a href="javascript:void(0)"; onclick="deleteByIds('+id+',1)">删除订单</a>&nbsp;';
                		str4 = '<a href="javascript:void(0)";" onclick="seeDetail('+id+')">订单详情</a>&nbsp;';
                		str = str5 + str1 + str2 + str3 + str4;
                		return str;
                	}	
	}] ,
	   plugins : [
	    	$('#paginator').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'page',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
}

function gotoPay(orderId){
	window.open('<%=request.getContextPath()%>/courseStore/toAlipay.action?orderId='+orderId+'&isAgian=1');
}
function initDates(){
	initDatePicker("orderCreateStartTime");
	initDatePicker("orderCreateEndTime");
}
function initDatePicker(idName){
	$("#"+idName).datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
};
/**
 * 订单详情
 */
function seeDetail(id){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toOrderDetails.action?orderId='+id;
}
/**
 * 评价订单
 */
 var ddDialog;
 var curDdId;
function evaluateD(id){
	curDdId = id;
	var emel = $("#evluateDialog");
	ddDialog = dialog({
		width : 362,
		height : 300,
		title : '评价',
		content : emel
	}).showModal();
}
function giveEvaluate(){
	var contents = $("#evaluateContent").val();
	var urlStr = "<%=request.getContextPath()%>/courseStore/giveOrderEvaluate.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"id":curDdId,"contents":contents},
		success : function(data) {
			dialog.alert("操作成功!",function(){
				ddDialog.close();
				$('#tableData').mmGrid().load({"page":1});
			});
		}
	});
}

/**
 * 取消订单
 */
function doCancel(id){
	dialog.confirm('确认取消？',function () {
		var urlStr = "<%=request.getContextPath()%>/courseStore/updateMallOrderStatus.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			data : {"orderId":id,"status":5},
			success : function(data) {
				dialog.alert("操作成功!");
				$("#tableData").mmGrid().load({"page":1});
			}
		});
    });
}
/**
 * 数组多id删除
 */
function deleteByIds(ids,type){//type:1 删一个 else： 批量删
	var idArr= [];
	if(type==1){
		idArr.push(ids);
	}else{
		var items = $("#tableData").mmGrid().selectedRows();
		
		if(items.length>0){
			$.each(items,function(index,item){
				idArr.push(item.id);
			});
		}else{
			dialog.alert("请选择数据!");
			return;
		}
	}
	dialog.confirm('确认删除？',function () {
		var urlStr = "<%=request.getContextPath()%>/courseStore/deleteBuyRecordsByIds.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			data : {"ids":idArr.join()},
			success : function(data) {
				dialog.alert("操作成功!");
				$("#tableData").mmGrid().load({"page":1});
			}
		});
    });
	
}


function exportMes(){
	var forms = $("#exportForm");
	forms.attr("action","<%=request.getContextPath()%>/courseStore/exportBuyRecords.action");
	forms[0].submit();
}
</script>
</html>
