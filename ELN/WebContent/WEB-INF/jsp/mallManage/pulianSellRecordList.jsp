<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业课程商城管理</title>
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
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
</style>



<script>

var categoryIds;
$(function(){
	//时间插件
	$("#beginTime, #endTime").datepicker({
		dateFormat:"yy-mm-dd"
	});	
	
	initPage();
	//initCompanyGrid();
	

	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/mall/manage/company/sellRecord/list.action",
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
             
 			   {title: '课程名称', name: 'courseName', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/mall/manage/toCourseDetailPage.action?id='+item.courseId+'" >'+val+'</a>';
			   }},
			   {title: '订单编号', name: 'orderCode', width:60, align:'center'},
 			   {title: '课程价格', name: 'price', width:30, align:'center'},
 			  {title: '购买企业', name: 'buyCompanyName', width:30, align:'center'},
 			  {title: '下单时间', name: 'createTime', width:80, align:'center'},
 			
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   buttonStr += '  <a href="#" onclick="detailOrder('+item.orderId+')" >订单详情</a>';
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
	
	$('#exampleTable').mmGrid().on("loadSuccess",function(e,data){
		renderInfo(data);
		
	})
	
	
})


function renderInfo(data){
	$("#totalRecord").text(data.total||0);
	$("#totalMoney").text(data.other.totalMoney||0);
}


function param(){
	var o = {};
	o.courseName = escapeForSql($("#courseName").val());
	o.orderCode = escapeForSql($("#orderCode").val());
	o.status = $("#status").val();
	o.beginTime=$("#beginTime").val();
	o.endTime=$("#endTime").val();
	o.categorys = categoryIds;
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

function initPage(){
	companyId = undefined;
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				onClick: zTreeOnClick
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/mall/manage/category/select.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
var categoryIds = [];
var categoryId;
var node;
function zTreeOnClick(event, treeId, treeNode) {
	categoryId = treeNode.id;
	node = treeNode;
	categoryIds = [];
	getChildNodes(treeNode);
	search();
};

function zTreeOnRightClick(event, treeId, treeNode) {
    if (treeNode && !treeNode.noR) {
    	categoryId = treeNode.id;
    	node = treeNode;
    	categoryIds = [];
    	getChildNodes(treeNode);
    	search();
    	$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
    
    	
    }else{
    	$.fn.zTree.getZTreeObj("treePage").cancelSelectedNode();
    	contextMenu.hide();
    	//contextMenu.show({top: event.pageY, left: event.pageX});
    }
}


function getChildNodes(treeNode){
	categoryIds.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function initCompanyGrid(){
	var mm = $('#companyTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageCompany/selectManageCompanyList.action",
    	width: $('#exampleTable').parent().width(),
    	height: '530px',
    	params: function(){
    		var o = {};
    		o.status = 1;
    		o.id = id;
    		o.name = $("#companyName").val();
    		o.id = null;
    		
    		return o;
    	}, 
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: false,
        //checkCol: true,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '企业名称', name: 'name', width:120, align:'center'}
           ]/* ,
        plugins : [
               	$('#paginator11-1').mmPaginator({
               		totalCountName: 'total',    //对应MMGridPageVoBean count
               		page: 1,                    //初始页
               		pageParamName: 'page',      //当前页数
               		limitParamName: 'pageSize', //每页数量
               		limitList: [10, 20, 40, 50]
               	})
               ] */
    });
	
	mm.on("loadSuccess",function(e, data){
		if(id == ''){
			id=data.rows[0].id;
			mm.select(0);
		}else{
			for(var j=0;j<data.rows.length;j++){
				if(data.rows[j].id == id){
					mm.select(j);
				}
			}
		}
		search();
	});
}
//批量付款
function pay(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	var ids=[];
	if(selectRows.length>0){
		$.each(selectRows, function(index, val){
    		ids.push(val.id);
    	});
		doPay(ids.join(","));
	}else{
		dialog.alert("请至少选择一个课程销售记录");
	}
}
//付款1件
function payOne(id){
	if(!id){
		return;
	}

	doPay(id);
}
//付款
function doPay(ids){
	
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{ids:ids},
			url:"<%=request.getContextPath()%>/mall/manage/sellRecord/pay.action",
			success:function(data){
				if(data=='SUCCESS'){
					search();
					
						dialog.alert("付款成功。");
					
					
				}else{
					
						dialog.alert("付款失败。");
					
					
				}
			}
		});
}


function deleteOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : id
	   		},
	   		url:"<%=request.getContextPath()%>/mall/manage/sellRecord/delete.action",
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
	    		url:"<%=request.getContextPath()%>/mall/manage/sellRecord/delete.action",
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


var ids = [];


function search(){
	$('#exampleTable').mmGrid().load({"pageIndex":1});
}

function clearOptions(){
	
	$("#courseName").val('');
	$("#orderCode").val('');
	$("#status").val('');
	$("#beginTime").val('');
	$("#endTime").val('');
	$("#beginPrice").val('');
	$("#endPrice").val('');
	search();
}





var artDialog;
function chooseCategory(){
	artDialog = dialog({
        title: "选择课程分类",
        url:"<%=request.getContextPath()%>/mall/manage/toChooseCategory.action",
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}


//导出记录到excel
function exportToExcel(){
var courseName = escapeForSql($("#courseName").val());
var orderCode = escapeForSql($("#orderCode").val());
var status = $("#status").val();
var beginTime=$("#beginTime").val();
var endTime=$("#endTime").val();
var categorys ='';

for(var i = 0;i<categoryIds.length;i++){
	categorys+="categorys[]="+categoryIds[i];
	if(i!=(categoryIds.length-1)){
		categorys+="&";
	}
}
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
if(courseName){
	paramStr+='courseName='+courseName+'&';
}
if(orderCode){
	paramStr+='orderCode='+orderCode+'&';
}
if(status!=undefined&&status!=''){
	paramStr+='status='+status+'&';
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
	paramStr+='endPrice='+endPrice+'&';
}
if(categorys){
	paramStr+=categorys;
}
window.open("<%=request.getContextPath()%>/mall/manage/exportCompanyRecord.action?"+paramStr);
		
}

function detailOrder(id){
	window.location.href="<%=request.getContextPath()%>/mall/manage/toOrderDetailPage.action?id="+id;
}
</script>

</head>
<body>

<div class="content">
	<!-- <h3>课程销售记录</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程销售记录</span>
	</div>
   	  	<div class="course_tree" id="tree">
   		<h2>课程分类</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson"  style="">
       <div class="lesson_tab" >
        	<ul>
            	<li class="li_this">普联课程销售记录</li>
                <li ><a href="<%=request.getContextPath()%>/mall/manage/toCompanySellRecordListPage.action" style="color:black;">企业课程销售记录</a></li>
               
            </ul>
        </div>
        <div class="button_gr" style="height: 40px;line-height: 60px;">
        <h4 >课程销售总金额：¥<span id="totalMoney"></span>   课程总销量：<span id="totalRecord"></span>个 </h4>
        </div>
        <div class="button_gr">
           <input type="button" value="导出记录" onclick="exportToExcel()"/>
        </div>
        <div class="search_3">
         	<p>   
        	   
                               下单时间：<input type="text" id="beginTime" name="beginTime"/> 至
                   <input type="text" id="endTime" name="endTime"/>
              
               课程价格：<input type="text" id="beginPrice" name="beginPrice"/> 至
                   <input type="text" id="endPrice" name="endPrice"/>
           </p>     
        
        

        </div>
        <div class="search_3">
       	<p>
            	 课程名称：
                <input type="text" id="courseName"/>
                	
            	订单编号：
                <input type="text" id="orderCode"/>
        
                                       
        	</p>
                   
            <input type="button" onclick="clearOptions()" value="重置">
        	<input type="button" value="查询" class="btn_cx" onclick="search()"/>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        
    </div>
        

    
</div>

</body>
</html>
