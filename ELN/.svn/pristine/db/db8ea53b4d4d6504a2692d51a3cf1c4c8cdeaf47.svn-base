<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政策法规管理列表</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>

<style type="text/css">
.ui-dialog-body{padding:0px;}
input[name='timeliness']{position: relative;top: 2px;}
#add_cateDialog {font-size: 16px;text-align: center;}
#add_cateDialog p{margin-top: 20px;}
#mod_cateDialog {font-size: 16px;text-align: center;}
#mod_cateDialog p{margin-top: 20px;}
.search_2{width: 840px;}
</style>
</head>
<body>
<div class="content">
	<!-- <h3>政策法规管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">政策法规管理</span>
	</div>
	<div style="width: 200px;float: left;overflow-x: auto;min-height: 500px;">
		<ul id="treePage" class="ztree"></ul>
	</div>
	<div style="float: right;width: 860px;">
		<div class="btn_gr">
	     	<input type="button" class="btn_2" value="新增法规" onclick="window.location.href='<%=request.getContextPath()%>/policiesRules/toManageRulesAdd.action'"/>
	        <input type="button" class="btn_2" value="批量删除" onclick="deleteByIds()"/>
    	</div>
	    <form id="form">
			<div class="search_2">
		    	<p>法规名称：
		            <input type="text" id="name" name="name"/>
		        </p>
		        <p>发布时间：
		            <input type="text" id="publishStartTime" name="publishStartTime"/>
		            <span>至</span>
		            <input type="text" id="publishEndTime" name="publishEndTime"/>
		        </p>
		       <p>文号：
		            <input type="text" id="referenceNumber" name="referenceNumber"/>
		        </p>
			</div>
		    <div class="search_2" style="margin-top:-1px;">
		    	<p>
		    		时效性:&nbsp;&nbsp;<input type="checkbox" name="timeliness" value="1"><span>有效</span><input type="checkbox" name="timeliness" value="2"><span>废止</span>
		    	</p>
		    	
		    	<p>&nbsp;&nbsp;施行时间：
		            <input type="text" id="executeStartTime" name="executeStartTime"/>
		            <span>至</span>
		            <input type="text" id="executeEndTime" name="executeEndTime"/>
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
</div>

<div style="display: none;" id="add_cateDialog">
	<p>
		<span>父类别名称:&nbsp;&nbsp;</span><input id='parent_catename' type="text" readonly="readonly"/>
	</p>
	
	<p>
		<span>分类名称:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" id="add_cateName"/>
	</p>
	<p>
		<input type="button" onclick="doAddCate()" value="确定" style="width: 60px;height: 30px;">
	</p>
</div>
<div style="display: none;" id="mod_cateDialog">
	<p>
		<span>分类名称:&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" id="mod_cateName"/>
	</p>
	<p>
		<input type="button" onclick="doModCate()" value="确定" style="width: 60px;height: 30px;">
	</p>
</div>


</body>


<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<!-- zTree -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js" ></script>
<script type="text/javascript">
var cur_categoryId;
var cur_categoryName;
var menuTree;
var selectCategoryIds;
$(function(){
	initDates();
	initDataList();
	initCategoryTree();
});

function initCategoryTree(){
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
				onRightClick: zTreeOnRightClick,
				onClick: zTreeOnClick
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/policiesRules/getCategorys.action",
		success:function(data){
			var treedata = data.rtnDataList;
			$.fn.zTree.init($("#treePage"), setting, treedata);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
/**
 * 点击树节点
 */
function zTreeOnClick(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("treePage");
	var nodes = treeObj.transformToArray(treeObj.getSelectedNodes());
	var tmpIds = [];
	$.each(nodes,function(index,item){
		tmpIds.push(item.id);
	});
	selectCategoryIds = tmpIds.join();
	doReset();
};


function zTreeOnRightClick(event, treeId, treeNode){
	cur_categoryId = treeNode.id;
	cur_categoryName = treeNode.name;
	if(menuTree){
		menuTree.hide();
	}
	if(!treeNode.parentId){
		menuTree = $.ligerMenu({ top: 100, left: 100, width: 120, items:
		    [
		     	{ id:'zTreeAddNode',text: '新增分类', click: addCate }
		     ]
		});
		menuTree.show({top: event.pageY, left: event.pageX});
	}else{
		menuTree = $.ligerMenu({ top: 100, left: 100, width: 120, items:
		    [
		     	{ id:'zTreeAddNode',text: '新增分类', click: addCate },
		     	{ id:'zTreeModNode',text: '修改分类', click: modifyCate },
		     	{ id:'zTreeDelNode',text: '删除分类', click: delCate }
		     ]
		});
		menuTree.show({top: event.pageY, left: event.pageX});
	}
}

function addCate(){
	// 清除
	$('#parent_catename').val('');
	$('#add_cateName').val('');
	
	//赋值
	$('#parent_catename').val(cur_categoryName);
	
	layer.open({
	    type: 1,
	    skin: 'layui-layer-rim', //加上边框
	    area: ['420px', '240px'], //宽高
	    content: $("#add_cateDialog")
	});
}
function doAddCate(){
	var param = new Object;
	param.parentId = cur_categoryId;
	var name_a = $.trim($('#add_cateName').val());
	if(!name_a){ // 为空
		layer.alert('名称不能为空');
		return ;
	}
	param.name = name_a;
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/policiesRules/addCategory.action",
		data:param,
		success:function(data){
			layer.closeAll();
			// 刷新树
			initCategoryTree();
		}
	});
}
function modifyCate(){
	$('#mod_cateName').val(cur_categoryName);
	layer.open({
	    type: 1,
	    skin: 'layui-layer-rim', //加上边框
	    area: ['420px', '240px'], //宽高
	    content: $("#mod_cateDialog")
	});
}
function doModifyCate(){
	
	var name_a = $.trim($('#add_cateName').val());
	if(!name_a){ // 为空
		layer.alert('名称不能为空');
		return ;
	}
	// 刷新树
	initCategoryTree();
}
function doModCate(){
	var param = new Object;
	param.id = cur_categoryId;
	var name_a = $.trim($('#mod_cateName').val());
	if(!name_a){ // 为空
		layer.alert('名称不能为空');
		return ;
	}
	param.name = name_a;
	
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/policiesRules/modifyCategory.action",
		data:param,
		success:function(data){
			layer.closeAll();
			// 刷新树
			initCategoryTree();
		}
	});
}
function delCate(){
	layer.confirm('是否删除？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/policiesRules/delCategory.action",
			data:{'categoryId':cur_categoryId},
			success:function(data){
				layer.closeAll();
				// 刷新树
				initCategoryTree();
			}
		});
	}, function(){
		
	});
}
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
	$('#tableData').mmGrid().load({"page":1});
}
function initDataList(){
	$('#tableData').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/policiesRules/getPoliciesRulesList.action',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: true,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.name = $.trim($("#name").val());
	    	param.category = selectCategoryIds;
	    	param.publishStartTime = $("#publishStartTime").val();
	    	param.publishEndTime = $("#publishEndTime").val();
	    	param.referenceNumber = $.trim($("#referenceNumber").val());
	    	var arr = [];
	    	$("input[name='timeliness']:checked").each(function(){
	    		arr.push($(this).val());
	    	});
	    	
	    	param.timeliness = arr.join(",");
	    	param.executeStartTime = $.trim($("#executeStartTime").val());
	    	param.executeEndTime = $.trim($("#executeEndTime").val());
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id',hidden:true},
	           {title:'法规名称', name:'name', width:160,align:'center'},
	           {title:'所属分类', name:'categoryName', width:60,align:'center'},
            {title:'上传时间',name:'createTime', width:80,align:'center',
                renderer:function(val,item, rowIndex){
                    return getFormatDateByLong(val,'yyyy-MM-dd');
                }
            },
	           {title:'发布时间', name:'publishTime', width:80,align:'center',
	        	   renderer:function(val,item, rowIndex){
	        		   return getFormatDateByLong(val,'yyyy-MM-dd');
              		}
	          	},
               {title:'施行时间',name:'executeTime', width:80,align:'center',
	          		renderer:function(val,item, rowIndex){
	          			return getFormatDateByLong(val,'yyyy-MM-dd');
	              	}
	          	},
               {title:'文号',name:'referenceNumber', width:80,align:'center'},
               {title:'时效性',name:'timeliness', width:45,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(val == 1){
            			   return "有效"; 
            		   }else if(val == 2){
            			   return "废止";
            		   }
              		}
              	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var id = item.id;
                		var timeliness = item.timeliness;
                		var isPublish = item.isPublish;
                		/*
                			3、所有发布后的法规可可进行废止、修改、删除操作；
							4、对于已经废止的法规，可进行删除；
							5、对于未发布的法规，学员前台看不见，普联管理员可进行发布、修改、删除操作；
                		*/
                		var str = '';
                		var str1 = '<a href="javascript:void(0);" onclick="doPublish('+id+')">发布</a>&nbsp;';
                		if(isPublish==1){//未发布的
                			str1 = '';
                		}
                		var str2 = '<a href="javascript:void(0);" onclick="doAbolish('+id+')">废止</a>&nbsp;';
                		var str3 = '<a href="javascript:void(0);" onclick="modify('+id+')">修改</a>&nbsp;';
                		if(timeliness==2){//废止的
                			str1 = '';
                			str2 = '';
                			str3 = '';
                		}
                		var str4 = '<a href="javascript:void(0);" onclick="deleteByIds('+id+',1)">删除</a>&nbsp;';
                		str += str1 + str2 + str3 + str4;
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
function initDates(){
	initDatePicker("publishStartTime");
	initDatePicker("publishEndTime");
	initDatePicker("executeStartTime");
	initDatePicker("executeEndTime");
}
function initDatePicker(idName){
	$("#"+idName).datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
};

function modify(id){
	window.location.href = "<%=request.getContextPath()%>/policiesRules/toManageRulesAdd.action?id="+id;
}
/**
 * 发布
 */
function doPublish(id){
	var urlStr = "<%=request.getContextPath()%>/policiesRules/doPublish.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"id":id},
		success : function(data) {
			dialog.alert("操作成功!");
			$("#tableData").mmGrid().load();
		}
	});
}
/**
 * 废止
 */
function doAbolish(id){
	var urlStr = "<%=request.getContextPath()%>/policiesRules/doAbolish.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {"id":id},
		success : function(data) {
			dialog.alert("操作成功!");
			$("#tableData").mmGrid().load();
		}
	});
}
/**
 * 数组多id删除
 */
function deleteByIds(ids,type){//type:1 删一个 2 批量删
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
		var urlStr = "<%=request.getContextPath()%>/policiesRules/deleteByIds.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			data : {"ids":idArr},
			success : function(data) {
				dialog.alert("操作成功!");
				$("#tableData").mmGrid().load({"page":1});
			}
		});
    });
	
}
</script>
</html>
