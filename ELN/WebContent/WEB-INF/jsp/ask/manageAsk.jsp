<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问问管理</title>
<!-- 页面style和JS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- niceValidate -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- jQueryUI -->
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**问问整体*/
#askContent{width: 1066px;margin: 16px auto 0px;padding-bottom: 200px;clear: both;overflow: hidden;}

/**问问面包屑*/
#askContent > h3{background: transparent url("../images/img/ico_4.png") no-repeat scroll left 2px;
width: 1052px;padding-left: 20px;color: #3A3A3A;border-bottom: 1px solid #CCC;padding-bottom: 10px;
margin-bottom: 10px;}

/**问问分类树*/
#askTypeTree{overflow: auto;width: 250px;height: 530px;
border: 1px solid #333;float: left;font-family: "微软雅黑";}

/**问问列表*/
#askQuestionList{width: 780px;margin-left: 30px;float: left;padding-bottom: 100px;}

/**问问按钮*/
#askButtons{width: 780px;height: 80px;line-height: 80px;float: left;}

/**批量删除*/
#batchDeletion{background: #D60500 none repeat scroll 0%;color: #FFF;text-align: center;
width: 130px;height: 35px;border: medium none;border-radius: 2px;font-weight: bold;margin-right: 10px;}

/**更改分类*/
#changeType{background: #0085FE none repeat scroll 0% 0%;color: #FFF;text-align: center;
width: 130px;height: 35px;border: medium none;border-radius: 2px;font-weight: bold;margin-right: 10px;}

/**查询输入框*/
#searchContent{width: 758px;height: 80px;padding-left: 10px;font-family: "微软雅黑";
background: #FBFBFB none repeat scroll 0% 0%;padding-right: 10px;border: 1px solid #CCC;float: left;}

#searchContent p{line-height: 40px;float: left;color: #666;width:100%;}

.searchInput{width: 185px;height: 28px;border: 1px solid #CCC;}

/**搜索按钮*/
#searchButton{width: 60px;height: 28px;border: medium none;margin-top: 6px;
background: #D20001 none repeat scroll 0% 0%;color: #FFF;float:right;margin-right:10px;}

/**重置按钮*/
#resetButton{width: 60px;height: 28px;border: medium none;margin-top: 6px;
background: #0085FE none repeat scroll 0% 0%;color: #FFF;float:right;}

/**链接样式*/
.a_link{color:blue;}

/**分类树样式*/
.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;
float: left;font-family: '微软雅黑';}

.course_tree h2 {font-size: 14px;width: 250px;height: 30px;background: #333;color: white;text-align: center;
line-height: 30px;font-weight: normal;}

.ztree li span.button.noline_docu{display:none;}

.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}

.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
</style>

<script type="text/javascript">
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var searchTypeId;//问问分类id
var contextMenu;//右键菜单
var searchTypeIds = [];//问问分类列表
var node;//节点
var addModifyDialog;//dialog弹出框
var askIds = [];//问问id

/**
 * 页面加载完成
 */
$(function(){
	//初始化时间控件
	$("#askStartTime,#askEndTime").datepicker({
		changeMonth: true,
		changeYear: true
	});
	//初始化右键菜单
	initMenu();
	//初始化问问分类树
	initAskTypes();
	//初始化问题列表
	initAskQuestionsForMMGrid();
	//初始化添加分类验证
	initAddFormValidate();
	//初始化修改分类验证
	initUpdateFormValidate();
});

/**
 * 初始化右键菜单
 */
function initMenu(){
	contextMenu = $.ligerMenu({
		top : 100,
		left : 100,
		width :120,
		items : [{
			id : 'zTreeAddNode',
			text : '新增分类',
			click : addType
		},{
			id : 'zTreeModifyNode',
			text : '修改分类',
			click : modifyType
		},{
			id : 'zTreeDeleteNode',
			text : '删除分类',
			click : deleteType
		}]
	});
}

/**
 * 添加分类
 */
function addType(){
	$(".n-yellow .msg-wrap").hide();
	$("#addTypeName").val('');
	$("#addTypeDescr").val('');
	
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"searchTypeId":searchTypeId},
		url:'<%=request.getContextPath()%>/manageAsk/getTypeById.action',
		success:function(data){
			if(data){
				$("#addParentName").html(data.name);
			}else{
				$("#addParentName").html('');
			}
		}
	});
	
	addModifyDialog = dialog({
		title:'新增问问分类',
		content:$("#addDiv"),
		width:450,
		height:300,
		button:[{
			value:'确定',
			callback:function(){
				$("#addForm").isValid(function(v){
					if(v){
						addThisType();
					}
				});
				return false;
			}
		},{
			value:'取消',
			callback:function(){
				this.close();
			}
		}]
	}).showModal();
}

/**
 * 添加该分类
 */
function addThisType(){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"parentId":searchTypeId,"name":$("#addTypeName").val(),"description":$("#addTypeDescr").val(),"companyId":companyId,"subCompanyId":subCompanyId},
		url:'<%=request.getContextPath()%>/manageAsk/addAskType.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("新增成功！");
				addModifyDialog.close().remove();
				initAskTypes();
			}else{
				dialog.alert("新增失败...");
			}
		}
	});
}

/**
 * 修改分类
 */
function modifyType(){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"searchTypeId":searchTypeId},
		url:'<%=request.getContextPath()%>/manageAsk/getEditAskType.action',
		success:function(data){
			if(data){
				$(".n-yellow .msg-wrap").hide();
				$("#updateTypeId").val(data.typeId);
				$("#updateTypeName").val(data.typeName);
				$("#updateTypeDescr").val(data.description);
				$("#updateTypeParentId").val(data.parentTypeId);
				$("#updateParentName").html(data.parentTypeName);
				
				addModifyDialog = dialog({
					title:'修改问问分类',
					content:$("#updateDiv"),
					width:450,
					height:300,
					button:[{
						value:'确定',
						callback:function(){
							$("#updateForm").isValid(function(v){
								if(v){
									updateThisType();
								}
							});
							return false;
						}
					},{
						value:'取消',
						callback:function(){
							this.close();
						}
					}]
				}).showModal();
				
			}else{
				dialog.alert("获取修改分类错误...");
			}
		}
	});
}

/**
 * 修改该分类
 */
function updateThisType(){
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"id":$("#updateTypeId").val(),
			"name":$("#updateTypeName").val(),
			"description":$("#updateTypeDescr").val()},
		url:'<%=request.getContextPath()%>/manageAsk/updateAskType.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("修改成功！");
				addModifyDialog.close().remove();
				initAskTypes();
			}else{
				dialog.alert("修改失败...");
			}
		}
	});
}

/**
 * 删除分类
 */
function deleteType(){
	//验证不能删除情况
	if(node.children != undefined){
		dialog.alert("不能删除父分类！");
		return;
	}
	if(searchTypeId == undefined){
		dialog.alert("请选择一个节点！");
		return;
	}
	var rows = $("#askForMMGridTable").mmGrid().rows();
	if(rows[0] != undefined){
		dialog.alert("不能删除该分类！");
		return;
	}
	//删除分类
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"id":searchTypeId},
		url:'<%=request.getContextPath()%>/manageAsk/deleteAskType.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("删除成功！");
				initAskTypes();
			}else{
				dialog.alert("删除失败...");
			}
		}
	});
	searchTypeId = '';
}

/**
 * 初始化问问分类树
 */
function initAskTypes(){
	searchTypeId = undefined;
	var setting = {
			data:{
				key:{url:'xUrl'},
				simpleData:{enable:true,pIdKey:'parentId'}
			},
			check:{enable:false},
			view:{
				showLine:false,
				showIcon:true
			},
			callback:{
				onRightClick:zTreeOnRightClick,
				onClick:zTreeOnClick
			}
	};
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"companyId":companyId,"subCompanyId":subCompanyId},
		url:'<%=request.getContextPath()%>/manageAsk/selectAskType.action',
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"),setting,data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

/**
 * 节点右击事件
 */
function zTreeOnRightClick(event, treeId, treeNode){
	if(treeNode && !treeNode.noR){
		searchTypeId = treeNode.id;
		searchTypeIds = [];
		node = treeNode;
		getChildNodes(treeNode);
		searchByType();
		$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
        contextMenu.show({top: event.pageY, left: event.pageX});
	}else{
		$.fn.zTree.getZTreeObj("treePage").cancelSelectedNode();
    	contextMenu.hide();
	}
}

/**
 * 节点点击事件
 */
function zTreeOnClick(event, treeId, treeNode){
	searchTypeId = treeNode.id;
	searchTypeIds = [];
	node = treeNode;
	getChildNodes(treeNode);
	searchByType();
}

/**
 * 获取子节点
 */
function getChildNodes(treeNode){
	searchTypeIds.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			searchTypeIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

/**
 * 获取子节点（不添加）
 */
function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			searchTypeIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

/**
 * 设置树节点样式
 */
function addIconInfo(data){
	for(var idx in data){
		data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
		data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
		data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
	}
}

/**
 * 初始化问题列表
 */
function initAskQuestionsForMMGrid(){
	var param = new Object();
	param.searchTypeId = searchTypeId;
	param.searchTitle = $("#searchTitle").val();
	param.searchAsker = $("#searchAsker").val();
	param.searchAskStartTime = $("#askStartTime").val();
	param.searchAskEndTime = $("#askEndTime").val();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	
	$("#askForMMGridTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/manageAsk/getManageAsks.action',
		width : $("#askForMMGridDiv").width(),
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : false, // 索引列
		params : param,
		cols : [{
			title:'id',
			name:'id',
			width:'0px',
			hidden:true
		},{
			title:'typeId',
			name:'typeId',
			width:'0px',
			hidden:true 
		},{
			title:'isTop',
			name:'isTop',
			width:'0px',
			hidden:true 
		},{
			title:'topCount',
			name:'topCount',
			width:'0px',
			hidden:true
		},{
			title:'标题',
			name:'title',
			renderer:function(val,item,rowIndex){
				var str = '';
				str += '<div title="'+val+'" style="float:left;width:100px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;text-align:center;"><a href="javascript:void(0)" class="a_link" onclick="toAskDetail('+item.id+');">'+val+'</a></div>';
				if(item.isTop == 1){
					str += '<div style="float:left;width:30px;color:#D20001;text-align:center;">置顶</div>';
				}
				return str;
			}
		},{
			title:'提问者',
			name:'askerName',
			align:'center'
		},{
			title:'问问分类',
			name:'typeName',
			align:'center'
		},{
			title:'提问时间',
			name:'askTime',
			align:'center',
			renderer:function(val,item,rowIndex){
				return getSmpFormatDateByLong(val);
			}
		},{
			title:'操作',
			name:'operation',
			align:'center',
			renderer:function(val,item,rowIndex){
				var str = '<a href="javascript:void(0)" class="a_link" onclick="dealAsk('+item.id+');">处理</a>&nbsp;';
				str += '<a href="javascript:void(0)" class="a_link" onclick="deleteAsk('+item.id+');">删除</a>&nbsp;<a href="javascript:void(0)" class="a_link" onclick="changeAskType('+item.id+');">更改分类</a>&nbsp;';
				if(item.isTop == 1){
					str += '<a href="javascript:void(0)" class="a_link" onclick="unTopAsk('+item.id+');">取消置顶</a>';
				}else if(item.isTop == 2){
					str += '<a href="javascript:void(0)" class="a_link" onclick="topAsk('+item.id+','+item.topCount+');">置顶</a>';
				}
				return str;
			}
		}],
		plugins : [$('#askForMMGridPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [20,50,100,200]
		})]
	});
}

/**
 * 跳往处理问问页面
 */
function dealAsk(askId){
	window.location.href = '<%=request.getContextPath()%>/manageThematicAsk/toDealWithThematicAsk.action?id='+askId;
}

/**
 * 置顶问问
 */
function topAsk(askId,topCount){
	//验证置顶条数不能超过10条
	if(topCount >= 10){
		dialog.alert("最多可以置顶10条");
		return;
	}
	
	var param = new Object();
	param.askId = askId;
	
	$.ajax({
		type:'POST',
		async:true,//默认同步
		data:param,
		url:'<%=request.getContextPath()%>/manageAsk/topAsk.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("置顶成功！");
				//刷新列表
				$("#askForMMGridTable").mmGrid().load();
			}else{
				dialog.alert("置顶失败...");
			}
		}
	});
}

/**
 * 取消置顶问问
 */
function unTopAsk(askId){
	var param = new Object();
	param.askId = askId;
	
	$.ajax({
		type:'POST',
		async:true,//默认同步
		data:param,
		url:'<%=request.getContextPath()%>/manageAsk/unTopAsk.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("取消置顶成功！");
				//刷新列表
				$("#askForMMGridTable").mmGrid().load();
			}else{
				dialog.alert("取消置顶失败...");
			}
		}
	});
}

/**
 * 根据查询输入框查询
 */
function searchByInput(){
	searchTypeId = '';
	search();
}

/**
 * 根据分类查询
 */
function searchByType(){
	$("#searchTitle").val('');
	$("#searchAsker").val('');
	$("#askStartTime").val('');
	$("#askEndTime").val('');
	search();
}

/**
 * 查询
 */
function search(){
	var searchParam = new Object();
	searchParam.searchTitle = $("#searchTitle").val();
	searchParam.searchAsker = $("#searchAsker").val();
	searchParam.askStartTime = $("#askStartTime").val();
	searchParam.askEndTime = $("#askEndTime").val();
	searchParam.searchTypeId = searchTypeId;
	
	$("#askForMMGridTable").mmGrid().load(searchParam);
}

/**
 * 重置
 */
function reset(){
	$("#searchTitle").val('');
	$("#searchAsker").val('');
	$("#askStartTime").val('');
	$("#askEndTime").val('');
	searchTypeId = '';
	
	$("#askForMMGridTable").mmGrid().load();
}

/**
 * 删除问问
 */
function deleteAsk(askId){
	dialog.confirm("确认删除吗？",function(obj){
		var param = {"askId":askId};
		$.ajax({
			type:'POST',
			async:true,//默认异步
			data:param,
			url:'<%=request.getContextPath()%>/manageAsk/deleteAsk.action',
			success:function(data){
				if(data.rtnResult=='SUCCESS'){
					dialog.alert("删除成功！");
					//刷新页面
					$("#askForMMGridTable").mmGrid().load();
				}else{
					dialog.alert("删除失败...");
				}
			}
		});
	});
}

/**
 * 批量删除问问
 */
function batchDeleteAsks(){
	//验证选中数据
	var selectRows = $("#askForMMGridTable").mmGrid().selectedRows();
	if(selectRows.length <= 0){
		dialog.alert("请先选择数据！");
		return;
	}
	
	dialog.confirm("确认要删除吗？",function(obj){
		var ids = [];
		$.each(selectRows,function(index,val){
			ids.push(val.id);
		});
		
		$.ajax({
			type:'POST',
			async:true,//默认异步
			data:{"ids":ids},
			url:'<%=request.getContextPath()%>/manageAsk/batchDeleteAsks.action',
			success:function(data){
				if(data.rtnResult=='SUCCESS'){
					dialog.alert("删除成功！");
					//刷新页面
					$("#askForMMGridTable").mmGrid().load();
				}else{
					dialog.alert("删除失败...");
				}
			}
		});
	});
}

/**
 * 批量更改问问分类
 */
function batchChangeAskType(){
	//验证选中数据
	var selectRows = $('#askForMMGridTable').mmGrid().selectedRows();
	if(selectRows.length <= 0){
		dialog.alert("请先选择数据");
		return;
	}
	//批量更改问问分类
	askIds = [];
	$.each(selectRows,function(index,val){
		askIds.push(val.id);
	});
	addModifyDialog = dialog({
		url:'<%=request.getContextPath()%>/manageAsk/toChangeAskType.action',
		title:'选择问问分类',
		lock:true,
		height:500,
		width:400
	}).showModal();
}

/**
 * 更改问问分类
 */
function changeAskType(askId){
	askIds.push(askId);
	addModifyDialog = dialog({
		url:'<%=request.getContextPath()%>/manageAsk/toChangeAskType.action',
		title:'选择问问分类',
		lock:true,
		height:500,
		width:400
	}).showModal();
}

/**
 * 初始化添加分类验证
 */
function initAddFormValidate(){
	$("#addForm").validator({
		rules:{
			checkName:function(element,param,field){
				var str;
				$.ajax({
					type:'POST',
					async:false,//此处为同步
					data:{"parentId":searchTypeId,"name":$("#addTypeName").val(),"companyId":companyId,"subCompanyId":subCompanyId},
					url:'<%=request.getContextPath()%>/manageAsk/selectAskType.action',
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;	
						}
						str = flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		theme:'yellow_right',
		msgStyle:'margin-top:10px;',
		fields:{
			addTypeDescr:{
				rule:'length[~50]',
				msg:{
					length:'长度小于等于50个字符'
				}
			},
			addTypeName:{
				rule:'required;length[~10];checkName;',
				msg:{
					required:'分类名称不能为空',
					length:'长度小于等于10个字符'
				}
			}
		}
	});
}

/**
 * 初始化修改分类验证
 */
function initUpdateFormValidate(){
	$("#updateForm").validator({
		rules:{
			checkSameName:function(element,param,field){
				var str;
				$.ajax({
					type:'POST',
					async:false,//此处为异步
					data:{"parentId":$("#updateTypeParentId").val(),"name":$("#updateTypeName").val(),"companyId":companyId,"subCompanyId":subCompanyId},
					url:'<%=request.getContextPath()%>/manageAsk/selectAskType.action',
					success:function(data){
						var flag = true;
						if(data.length > 0 && data[0].id != $("#updateTypeId").val()){
							flag = false;
						}
						str = flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		theme:'yellow_right',
		msgStyle:'margin-top:10px;',
		fields:{
			updateTypeDescr:{
				rule:'length[~50]',
				msg:{
					length:'长度小于等于50个字符'
				}
			},
			updateTypeName:{
				rule:'required;length[~10];checkSameName;',
				msg:{
					required:'分类名称不能为空',
					length:'长度小于等于10个字符'
				}
			}
		}
	});
}

/**
 * 跳转到问问详情页面
 */
function toAskDetail(askId){
	window.location.href='<%=request.getContextPath()%>/myAsk/toAskDetail.action?askId='+askId;
}
</script>

</head>
<body>
	<div id="askContent">
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">问问管理</span>
		</div>
		<!-- <h3>问问管理</h3> -->
		<!-- 问问分类树 -->
		<div id="askTypeTree" class="course_tree">
			<h2>问问分类</h2>
			<ul id="treePage" class="ztree"></ul>
		</div>
		<!-- 问问列表 -->
		<div id="askQuestionList">
			<!-- 按钮 -->
			<div id="askButtons">
				<input type="button" id="batchDeletion" value="批量删除" onclick="batchDeleteAsks();"/>
				<input type="button" id="changeType" value="更改分类" onclick="batchChangeAskType();"/>
			</div>
			<!-- 搜索框 -->
			<div id="searchContent">
				<p>
					标题：
					<input type="text" id="searchTitle" class="searchInput"/>
					提问者：
					<input type="text" id="searchAsker" class="searchInput">
					<br/>
					提问时间：
					<input type="text" id="askStartTime" class="searchInput">
					至
					<input type="text" id="askEndTime" class="searchInput">
					<!-- 查询和重置按钮 -->
					<input type="button" id="resetButton" value="重置" onclick="reset();"/>
					<input type="button" id="searchButton" value="查询" onclick="searchByInput();"/>
				</p>
			</div>
			<!-- mmGrid展现 -->
			<div class="clear_both"></div>
			<div id="askForMMGridDiv">
				<table id="askForMMGridTable"></table>
				<!-- 分页栏 -->
				<div id="askForMMGridPager" style="float:right;"></div>
			</div>
		</div>
	</div>
	
	<!-- 添加输入框 -->
	<div id="addDiv" style="display:none;width:300px;">
		<form id="addForm">
			<div>
				<table>
					<tr>
						<td style="text-align: right;">上级：</td>
						<td id="addParentName"></td>
					</tr>
					<tr>
						<td style="text-align: right;"><span style="color:red;">*</span>名称：</td>
						<td><input type="text" id="addTypeName" name="addTypeName" style="width:200px;height:30px;margin-top: 10px;"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">描述：</td>
						<td><textarea id="addTypeDescr" name="addTypeDescr" style="width:200px;height:60px;margin-top: 10px;"></textarea></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
	<!-- 更新输入框 -->
	<div id="updateDiv" style="display:none;width:300px;">
		<form id="updateForm">
			<div>
				<table>
					<tr>
						<td style="text-align: right;">上级：</td>
						<td id="updateParentName"></td>
					</tr>
					<tr>
						<td style="text-align: right;"><span style="color:red;">*</span>名称：</td>
						<td>
							<input type="text" id="updateTypeName" name="updateTypeName" style="width:200px;height:30px;margin-top: 10px;"/>
							<input type="hidden" id="updateTypeId"/>
							<input type="hidden" id="updateTypeParentId"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">描述：</td>
						<td><textarea id="updateTypeDescr" name="updateTypeDescr" style="width:200px;height:60px;margin-top: 10px;"></textarea></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
</body>
</html>