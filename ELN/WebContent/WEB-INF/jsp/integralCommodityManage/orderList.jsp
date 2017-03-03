<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品兑换管理</title>
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

	initAddFormValidate();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/integral/commodity/manage/order/list.action",
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
 			   {title: '商品名称', name: 'name', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/integral/commodity/manage/toCommodityDetailPage.action?id='+item.commodityId+'" >'+val+'</a>';
			   }},
			   {title:'购买者',name:'userRealName',width:30,align:'center'},
 			   {title: '兑换积分', name: 'allIntegral', width:60, align:'center'},
 			   {title: '兑换时间', name: 'createTime', width:60, align:'center'},
 			   {title: '状态', name: 'status', width:60, align:'center',renderer:function(val,item,rowIndex){
 				 var str='';
 				 if(val==1){
 					str='已发货' ;
 				 }else if (val==0){
 					str='未发货' ;
 				 }else if (val==2){
 					str='已完成' ;
 				 }
 				 return str;
 			  }},
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(val == 0){
						   buttonStr += '<a href="javascript:void(0)" onclick="post('+item.id+')" >发货</a>  ';

					   }else{
						   buttonStr+='<a href="javascript:void(0)" style="color:gray">发货</a>';
					   }
					   buttonStr+='<a href="<%=request.getContextPath()%>/integral/commodity/manage/toOrderDetailPage.action?id='+item.id+'" >详情</a>';
					   
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
	
	getLogisticsCompany();
})






function param(){
	var o = {};
	o.name = escapeForSql($("#name").val());
	o.userRealName=$("#userRealName").val();
	o.status = $("#status").val();
	o.beginTime=$("#beginTime").val();
	o.endTime=$("#endTime").val();
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
	   		url:"<%=request.getContextPath()%>/integral/commodity/manage/order/delete.action",
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
	    		url:"<%=request.getContextPath()%>/integral/commodity/manage/order/delete.action",
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
	$("#userRealName").val('');
	$("#status").val('');
	$("#beginTime").val('');
	$("#endTime").val('');
	search();
}





var artDialog;

function post(id){
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
        title: "发货",
        content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				doPost(id);
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




function doPost(id){
	//$('#addForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{id:id,
					logisticsCompanyId:$("#logisticsCompanyId").val(),
					logisticsNumber:$("#logisticsNumber").val()},
				url:"<%=request.getContextPath()%>/integral/commodity/manage/order/post.action",
				success:function(data){
					if(data&&data == 'SUCCESS'){
						artDialog.close().remove();
						dialog.alert("发货成功");
						search();
					}else{
						dialog.alert("发货失败");
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
		htmlStr+='<option value="'+logisticsCompany[i].id+'">'+logisticsCompany[i].name+'</option>'
	}
	$logisticsCompany.empty();
	$logisticsCompany.append(htmlStr);
}



</script>

</head>
<body>

<div class="content">
	<!-- <h3>商品兑换管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">商品兑换管理</span>
	</div>
    
       <!--  <div class="button_gr">
      
        </div> -->
        <div class="search_3" style=" width: 1044px;">
        	<p>	
            	
            	 商品名称：
                <input type="text" id="name"/>
                                         购买人：
                <input type="text" id="userRealName"/>
         
        	  	状态：
                <select id="status">
                    <option value="">全部</option>
                    <option value="0">未发货</option>
                    <option value="1">已发货</option>
                    <option value="2">已完成</option>
                </select>
                              兑换时间：<input type="text" id="beginTime" name="beginTime"/> 至
                   <input type="text" id="endTime" name="endTime"/></p>
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
