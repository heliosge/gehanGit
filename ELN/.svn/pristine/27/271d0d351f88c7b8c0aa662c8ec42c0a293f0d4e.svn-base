<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城课程订单管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />

<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
</style>

<script>


$(function(){
	//时间插件
	$("#beginTime, #endTime").datepicker({
		dateFormat:"yy-mm-dd"
	});	

	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/mall/manage/topic/order/list.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol: true,  //索引列
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
     	      {title:'订单编号',name:'code',width:30,align:'center'},
 			   {title: '专题名称', name: 'names', width:60, align:'center', renderer:function(val, item, rowIndex){
 				   var valStr=val;
 				   var htmlStr='';
 				   if(valStr&&valStr.length>10){
 					  valStr = valStr.substring(0,10)+'...';
 				   }
 				  htmlStr+='<span title="'+val+'">'+valStr+'</span>';
				   /**
 				   var nameArr = val.split(',');
 				   var ids=item.relateIds.split(',');
 				   for(var i=0;i<ids.length;i++){
 					  htmlStr+='<a href="<%=request.getContextPath()%>/mall/manage/toTopicDetailPage.action?id='+ids[i]+'" >'+nameArr[i]+'</a>';
 				   }**/
 				   
				   return htmlStr;
			   }},
			   {title:'订单价格',name:'price',width:25,align:'center'},
 			   {title: '买家', name: 'userRealName', width:30, align:'center'},
 			   {title: '下单时间', name: 'createTime', width:70, align:'center'},
 			   {title: '状态', name: 'status', width:25, align:'center',renderer:function(val,item,rowIndex){
 				 var str='';
 				 if(val==0){
 					str='待付款' ;
 				 }else if(val==1){
 					str='已付款' ;
 				 }else if(val==2){
 					str='已发货' ;
 				 }else if(val==3){
 					str='已评价' ;
 				 }else if(val==4){
 					str='已失效' ;
 				 }else if(val==5){
 					str='已取消' ;
 				 }
 				 return str;
 			     }},
 			     {title: '发票状态', name: 'invoiceStatus', width:30, align:'center',renderer:function(val,item,rowIndex){
 				 var str='';
 				 if(item.isNeedInvoice==1){
 					 if(val==0){
 	 					str='待邮寄' ;
 	 				 }else if(val==1){
 	 					str='已邮寄' ;
 	 				 }else if(val==2){
 	 					str='已签收' ;
 	 				 } 
 				 }else{
 					 str='不需要发票';
 				 }
 				 return str;
 			  }},
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(item.type==2&&val==0){
						   buttonStr += '<a href="javascript:void(0)" onclick="delivery('+item.id+')" >发货</a>  ';
					   }else{
						   buttonStr += '<a href="javascript:void(0)" style="color:gray" >发货</a>  ';
					   }
					   
					   if((val==2||val==3)&&item.invoiceStatus==0&&item.isNeedInvoice==1){
						   buttonStr += '<a href="javascript:void(0)" onclick="postInvoice('+item.id+')" >邮寄发票</a>  ';
					   }else{
						   buttonStr += '<a href="javascript:void(0)" style="color:gray" >邮寄发票</a>  ';
					   }
					   buttonStr += '<a href="javascript:void(0)" onclick="detailOrder('+item.id+')" >详情</a>  ';
					   
					   if(val==0&&item.type==2){
						   buttonStr += '<a href="javascript:void(0)" onclick="doInvalid('+item.id+')" >失效</a>  ';
					   }else{
						   buttonStr += '<a href="javascript:void(0)" style="color:gray" >失效</a>  ';
					   }
				
					   return "<div class='span_btus' >" + buttonStr + "</div>";
				   }}
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'pageIndex',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
	initAddFormValidate();
	getLogisticsCompany();
	
})






function param(){
	var o = {};
	o.name = escapeForSql($("#name").val());
	o.code = escapeForSql($("#code").val());
	o.userRealName=$("#userRealName").val();
	o.status = $("#status").val();
	o.invoiceStatus = $("#invoiceStatus").val();
	o.beginTime=$("#beginTime").val();
	o.endTime=$("#endTime").val();
	o.beginPrice=$("#beginPrice").val();
	o.endPrice=$("#endPrice").val();
	if(o.beginPrice&&!/^(0|0\.[1-9]|0\.[0-9][1-9]|[1-9]\d{0,6}(\.[0-9][1-9]?)?)$/.test(o.beginPrice)){
		dialog.alert("请输入正确的起始价格");
		return false;
	}
	if(o.endPrice&&!/^(0|0\.[1-9]|0\.[0-9][1-9]|[1-9]\d{0,6}(\.[0-9][1-9]?)?)$/.test(o.endPrice)){
		dialog.alert("请输入正确的结束价格");
		return false;
	}
	return o;
}







function deleteOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : id
	   		},
	   		url:"<%=request.getContextPath()%>/mall/manage/order/delete.action",
	   		success:function(data){
	   			if(data == 'SUCCESS'){
	   				search();
		   			dialog.alert("删除成功。");
				}else{
					dialog.alert("删除失败。");
				}
	   	    }
	   	});
	});
}

function deleteRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		param.push(val.id);
	    	});
	    	var ids = param.join(",");
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : ids
	    		},
	    		url:"<%=request.getContextPath()%>/mall/manage/order/delete.action",
	    		success:function(data){
	    			if(data == 'SUCCESS'){
	    				search();
		    			dialog.alert("删除成功。");
					}else{
						dialog.alert("删除失败。");
					}
	    	    }
	    	});
		});
	}else{
		dialog.alert('请先选择数据！');
	}
}



function search(){
	$('#exampleTable').mmGrid().load({"pageIndex":1});
}

function clearOptions(){
	
	$("#name").val('');
	$("#code").val('');
	$("#status").val('');
	$("#invoiceStatus").val('');
	$("#beginTime").val('');
	$("#endTime").val('');
	$("#beginPrice").val('');
	$("#endPrice").val('');
	search();
}


//发货
function delivery(id){
	if(!id){
		return;
	}
	dialog.confirm("确认发货吗?",function(obj){
		//var item=$('#exampleTable').mmGrid().row(rowIndex);
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{id:id},
			url:"<%=request.getContextPath()%>/mall/manage/order/delivery.action",
			success:function(data){
				if(data=='SUCCESS'){
					search();
					dialog.alert("发货成功。");
				}else{
					dialog.alert("发货失败。");
				}
			}
		});
		
	});
	
}
//失效
function doInvalid(id){
	if(!id){
		return;
	}
dialog.confirm("确认置为失效吗?",function(obj){
	
	
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{id:id},
			url:"<%=request.getContextPath()%>/mall/manage/order/doInvalid.action",
			success:function(data){
				if(data=='SUCCESS'){
					search();
					dialog.alert("置为失效成功。");
				}else{
					dialog.alert("置为失效失败。");
				}
			}
		});
});
}

var artDialog;

function postInvoice(id){
	if(!logisticsCompany||logisticsCompany.length<=0){
		getLogisticsCompany();
		if(!logisticsCompany||logisticsCompany.length<=0){
			dialog.alert("系统中不存在物流公司，请添加");
			return;
		}
	}
	renderCompany();
	$(".n-yellow .msg-wrap").hide();
	$("#logisticsCompanyId").val(logisticsCompany[0].id);
	$("#logisticsNumber").val('');
    //清除验证
    $('#addForm').validator("cleanUp");
	artDialog = dialog({ 
        title: "邮寄发票",
        content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				doPostInvoice(id);
	            			}
	            		});
	            	  return false;
	              }
		          },{
		              value: '取消',
		              callback: function () {
		            	 this.close();
		              }
		          }
		      ]
    }).showModal();
	
}




function doPostInvoice(id){
	//$('#addForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{id:id,
					logisticsCompanyId:$("#logisticsCompanyId").val(),
					logisticsNumber:$("#logisticsNumber").val()},
				url:"<%=request.getContextPath()%>/mall/manage/order/postInvoice.action",
				success:function(data){
					if(data&&data == 'SUCCESS'){
						artDialog.close().remove();
						dialog.alert("邮寄成功");
						search();
					}else{
						dialog.alert("邮寄失败");
					}
				}
			});
	//	} else {
	//		return false;
			//dialog.alert("表单验证不通过");
	//	}
	//});
}

/**
 * 表单验证
 */
function initAddFormValidate() {
	$('#addForm').validator({
		rules : {
			checkNum : [ /^\d{0,30}$/, '请输入有效数字' ]
		},
		theme : 'yellow_right',
		msgStyle:"margin-top:10px;",
		fields : {
			logisticsCompanyId : {
				rule : "required",
				msg : {
					required : "请选择一个物流公司"
				}
			},
			logisticsNumber : {
				rule : "required;length[~30];checkNum;",
				msg : {
					required : "快递单号不能为空",
					length : "长度需小于等于30个字符"
				}
			}
		}
	});
}

var logisticsCompany;
function getLogisticsCompany(){
	$.ajax({
		type:"POST",
		async:false,
		data:{},
		url:"<%=request.getContextPath()%>/integral/commodity/manage/company/list.action",
		success:function(data){
			logisticsCompany=data;
		}
	});

}

function renderCompany(){
	if(!logisticsCompany||logisticsCompany.length<=0){
		return;
	}
	var $logisticsCompany =  $("#logisticsCompanyId");
	var htmlStr='';
	for(var i =0;i<logisticsCompany.length;i++){
		htmlStr+='<option value="'+logisticsCompany[i].id+'">'+logisticsCompany[i].name+'</option>';
	}
	$logisticsCompany.empty();
	$logisticsCompany.append(htmlStr);
}


function detailOrder(id){
	window.location.href="<%=request.getContextPath()%>/mall/manage/toOrderDetailPage.action?id="+id;
}

//导出记录到excel
function exportToExcel(){
var name = escapeForSql($("#name").val());
var code = escapeForSql($("#code").val());
var status = $("#status").val();
var invoiceStatus = $("#invoiceStatus").val();
var beginTime=$("#beginTime").val();
var endTime=$("#endTime").val();

var beginPrice=$("#beginPrice").val();
var endPrice=$("#endPrice").val();
if(beginPrice&&!/^(0|0\.[1-9]|0\.[0-9][1-9]|[1-9]\d{0,6}(\.[0-9][1-9]?)?)$/.test(beginPrice)){
	dialog.alert("请输入正确的起始价格");
	return false;
}
if(endPrice&&!/^(0|0\.[1-9]|0\.[0-9][1-9]|[1-9]\d{0,6}(\.[0-9][1-9]?)?)$/.test(endPrice)){
	dialog.alert("请输入正确的结束价格");
	return false;
}
var paramStr='';
if(name){
	paramStr+='name='+name+'&';
}
if(code){
	paramStr+='code='+code+'&';
}
if(status!=undefined&&status!=''){
	paramStr+='status='+status+'&';
}
if(invoiceStatus!=undefined&&invoiceStatus!=''){
	paramStr+='invoiceStatus='+invoiceStatus+'&';
}
if(beginTime){
	paramStr+='beginTime='+beginTime+'&';
}
if(endTime){
	paramStr+='endTime='+endTime+'&';
}
if(beginPrice){
	paramStr+='beginPrice='+beginPrice+'&';
}
if(endPrice){
	paramStr+='endPrice='+endPrice;
}

window.open("<%=request.getContextPath()%>/mall/manage/exportTopicOrder.action?"+paramStr);
		
}

</script>

</head>
<body>

<div class="content">
	<!-- <h3>专题订单管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">专题订单管理</span>
	 </div>
       <div class="button_gr" style="height: auto;line-height: normal;padding-bottom: 5px;">
        	<input type="button" value="导出记录" class="btn_4" onClick="exportToExcel()"/>
        </div>
        <div class="search_3" style=" width: 1044px;">
        <p>
                                          订单编号                
                 <input type="text" id="code"/>
                                         专题名称：
                <input type="text" id="name"/>
                                        订单价格<input type="text" id="beginPrice" name="beginPrice"/> 至
                   <input type="text" id="endPrice" name="endPrice"/>
                </p>
        </div>
        <div class="search_3" style=" width: 1044px;">
        	<p>	
            	 
                                      
                                     
         
        	 
                             下单时间：<input type="text" id="beginTime" name="beginTime"/> 至
                   <input type="text" id="endTime" name="endTime"/>
                    	订单状态：
                <select id="status">
                    <option value="">全部</option>
                    <option value="0">待付款</option>
                     <option value="2">已发货</option>
                      <option value="3">已评价</option>
                       <option value="4">已失效</option>
                        <option value="5">已取消</option>
                </select>
                 	发票状态：
                <select id="invoiceStatus">
                    <option value="">全部</option>
                    <option value="0">待邮寄</option>
                    <option value="1">已邮寄</option>
                     <option value="2">已签收</option>
                </select></p>
            <input type="button" onclick="clearOptions()" value="重置">
        	<input type="button" value="查询" class="btn_cx" onclick="search()"/>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        
    
        
     
</div>
<div style="display:none;width:300px;" id="add"  align="left">
 	<form id="addForm">
				<div>	
				<table >
					<tr height="50px;">
						<td><span style="color:red">*</span>物流公司:</td>
						<td>
						<select style="width:200px;height:30px;" id="logisticsCompanyId">
						</select>
						
						</td>
					</tr>
					<tr>
						<td><span style="color:red">*</span>快递单号:</td>
						<td><input type="text" style="width:200px;height:30px;"  id="logisticsNumber" name="logisticsNumber"/></td>
					</tr>
				</table>
		        </div>
	</form>	     
</div>

 
	

</body>
</html>
