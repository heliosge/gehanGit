<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>
$(function(){
	initPage();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/certificate/selectCertificateList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol:true,
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
     	       {title: '证书名称', name: 'name', width:60, align:'center'},
               {title: '获得人员', name: 'receiveUserName', width:60, align:'center'},
 			   {title: '部门', name: 'deptName', width:60, align:'center'},
 			   {title: '岗位', name: 'postName', width:60, align:'center'},
 			   {title: '理论成绩/实操成绩', name: '', width:60, align:'center', renderer:function(val, item, rowIndex){
 				   return (item.theoryScore==null?'':item.theoryScore)+"/"+(item.operateScore==null?'':item.operateScore);
 			   }},
 			   {title: '发证日期', name: 'sendDate', width:60, align:'center'},
 			   {title: '换证日期', name: 'changeDate', width:60, align:'center'},
			   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
				   var buttonStr = '<a href="toUpdateCerPage.action?id='+item.id+'" >修改</a>  <a href="#" onclick="deleteOne('+item.id+')" >删除</a>';
				   return "<div class='span_btus' >" + buttonStr + "</div>";
			   }}
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
})

function param(){
	var o = {};
	o.name = $("#name").val();
	o.categoryIds = categoryIds;
	o.receiveName = $("#receiveName").val();
	return o;
}

function initPage(){
	
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
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			$.fn.zTree.init($("#treePage"), setting, data.data);
			//如果是集团公司删除子公司的部门
			/* var treeObj = $.fn.zTree.getZTreeObj("treePage");
			for(var i = 0; i < data.subData.length; i++){
				var node = treeObj.getNodeByParam("id", data.subData[i], null);
				treeObj.removeNode(node, false);
			} */
		}
	});
	
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function deleteOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		var param = [];
	   	param.push(id);
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : param
	   		},
	   		url:"<%=request.getContextPath()%>/certificate/deleteCertificate.action",
	   		success:function(data){
	   			search();
	   			dialog.alert("删除成功。");
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
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/certificate/deleteCertificate.action",
	    		success:function(data){
	    			search();
	    			dialog.alert("删除成功。");
	    	    }
	    	});
		});
	}else{
		dialog.alert('请先选择数据！');
	}
}


var ids = [];

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function toInsertCerPage(){
	window.location.href="<%=request.getContextPath()%>/certificate/toInsertCerPage.action";
}

var categoryIds = [];

function zTreeOnClick(event, treeId, treeNode) {
	categoryIds = [];
	getChildNodes(treeNode);
	search();
};

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

function downloadTemplate(){
	$("#form").submit();
}

//证书
var artDialog;
function importFile(){
	artDialog = dialog({
		 url:"<%=request.getContextPath()%>/certificate/importFile.action",
	        title:"导入证书" ,
			height: 250,
			width: 450
	}).showModal();
}

function downloadTemplate(){
	$("#form").submit();
}

function exportExcel(){
	$("#form_").submit();
}
</script>

</head>
<body>

<div class="content">
	<!-- <h3>证书管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">证书管理</span>
	 </div>
   	<div class="course_tree">
   		<h2>公司组织结构</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson">
        <div class="button_gr">
        	<input type="button" value="新增" class="btn_4" onClick="toInsertCerPage()"/>
        	<input type="button" value="批量导入" onclick="importFile()"/>
            <!-- <a class="btn_1" href="/upload/template/certificateTemplate.xls">下载模板</a> -->
            <input type="button" class="btn_1" value="下载模板" onclick="downloadTemplate()"/>
            <input type="button" value="导出" onclick="exportExcel()"/>
            <input type="button" value="删除" onclick="deleteRows()"/>
        </div>
        <form id="form" action="<%=request.getContextPath()%>/teacher/downloadTemplate.action" method="post">
    	<input type="hidden" value="template/导入证书模板.xls" name="path"/>
 	    <input type="hidden" value="导入证书模板.xls" name="fileName"/>
    </form>
        <form id="form_" action="<%=request.getContextPath()%>/certificate/exportExcel.action" method="post">
        <div class="search_3">
        	<p>	
            	证书名称：
                <input type="text" id="name" name="name"/>
                	获得人员：
                <input type="text" id="receiveName" name="receiveName"/>
               
        	</p>
        	<input type="button" value="查询" class="btn_cx" onclick="search_()"/>

        </div>
        </form>
        <div style="width: 780px;float: left;">
				<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
				<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
     	</div> 
        
    </div>
        
     
</div>
<script>
</script>

</body>
</html>
