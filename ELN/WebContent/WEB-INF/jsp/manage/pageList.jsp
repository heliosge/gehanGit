<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>页面管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<style type="text/css">
.op_div{display:inline;}
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.ztree li span.button.edit {margin-right:2px; background-position:-110px -48px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.remove {margin-right:2px; background-position:-110px -64px; vertical-align:top; *vertical-align:middle}

.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}

.input_0{width: 240px;}
</style>

<script type="text/javascript">

var zTree = null;
var checkNode = null;

$(function(){
	
	var pageList = ${pageList};
	//dialog.alert(pageList.length);
	pageList.unshift({id:0, name:"所有菜单"});
	
	//zTree配置
	var setting = {
		data: {
			simpleData: {
				enable: true,
				pIdKey: "parentId"
			},
			view: {
				showLine: false,
				showIcon: true
			},
			key: {
				url: "aa"
			}
		},
		view: {
			selectedMulti: false,
			showTitle: false,
			dblClickExpand: false,
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom
		}
	};
	
	zTree = $.fn.zTree.init($("#pageList"), setting, pageList);
	
	//全部展开
	zTree.expandAll(true);
});

//鼠标移上图标
function addHoverDom(treeId, treeNode) {
	
	var sObj = $("#" + treeNode.tId + "_span");
	
	if($("#op_"+treeNode.id).length > 0){
		return;
	}
	
	var str = "<div id='op_"+treeNode.id+"' class='op_div'>";
	
	if(treeNode.levelType == 0 || treeNode.id == 0){
		//只有跟节点，一级菜单可以增加子菜单
		str += "<span class='button add' title='增加子菜单' onclick='addNode("+treeNode.id+")' ></span>";
	}
	//删除，没有子节点，可以删除
	var nodes = zTree.getNodesByParam("parentId", treeNode.id, treeNode);
	if(nodes == null || nodes.length == 0){
		str += "<span class='button remove' title='删除菜单' onclick=\"delNode("+treeNode.id+", '"+treeNode.tId+"')\" ></span>";
	}
	//升级，除了跟节点，都可以升级
	if(treeNode.id != 0){
		str += "<span class='button edit' title='修改菜单' onclick=\"aditNode("+treeNode.id+", '"+treeNode.tId+"')\" ></span>";
	}
	
	str += "</div>";

	sObj.after(str);
	
	checkNode = treeNode;
};

//移除图标
function removeHoverDom(treeId, treeNode) {
	$("#op_"+treeNode.id).remove();
};

//增加子菜单
function addNode(parentId){
	//dialog.alert(parentId+" 下增加子菜单");
	
	$(".node_table").find("span").text("");
	$("#menu_name").val("");
	$("#menu_url").val("");
	$("#menu_url").show();
	$("#urlTr").show();
	
	$("#menu_parentId").val(parentId);
	
	$("#saveBtu").attr("onclick", "save()");
	
	$.ligerDialog.open({ 
		title: "增加菜单",
		target: $("#addNode"),
		width: 450
	});
}

//修改
function aditNode(id, tId){
	//一级菜单，只能改名称
	var node = zTree.getNodeByTId(tId);
	
	if(node.levelType == 0){
		$("#urlTr").hide();
		$("#menu_url").val("");
	}else{
		$("#urlTr").show();
		$("#menu_url").val(node.url);
	}
	
	$("#menu_name").val(node.name);
	$("#menu_parentId").val(node.parentId);
	
	$("#saveBtu").attr("onclick", "edit('"+tId+"')");
	
	$.ligerDialog.open({ 
		title: "修改菜单",
		target: $("#addNode"),
		width: 450
	});
}

function edit(tId){
	//一级菜单，只能改名称
	var node = zTree.getNodeByTId(tId);
	
	$(".node_table span").text("");
	var name = $.trim($("#menu_name").val());
	
	var flag = true;
	if(name == ""){
		$("#menu_name").parent().next().find("span").text("名称不可为空");
		flag = false;
	}else{
		if(name.length > 10){
			$("#menu_name").parent().next().find("span").text("名称最多10个字符");
			flag = false;
		}
	}
	
	if(node.levelType == 0){
		//只验证名称
	}else{
		var url = $.trim($("#menu_url").val());
		
		if(url == ""){
			$("#menu_url").parent().next().find("span").text("URL不可为空");
			flag = false;
		}
	}
	
	if(flag){
		//验证通过
		//dialog.alert("验证通过");
		node.name = name;
		
		if(node.levelType != 0){
			node.url = $.trim($("#menu_url").val());
		}
		
		$.ajax({
    		type:"POST",
    		async: false,  //默认true,异步
    		data: node,
    		url: "<%=request.getContextPath()%>/managePage/updateManagePageById.action",
    		success:function(data){
		    	//增加成功
		    	//dialog.alert(JSON.stringify(data));
		    	zTree.updateNode(node, false);
    	    }
    	});
		
		$.ligerDialog.hide();
	}
}

function save(){
	
	$(".node_table span").text("");
	var name = $.trim($("#menu_name").val());
	var url = $.trim($("#menu_url").val());
	
	var flag = true;
	if(name == ""){
		$("#menu_name").parent().next().find("span").text("名称不可为空");
		flag = false;
	}else{
		if(name.length > 10){
			$("#menu_name").parent().next().find("span").text("名称最多10个字符");
			flag = false;
		}
	}
	/* if(url == ""){
		$("#menu_url").parent().next().find("span").text("URL不可为空");
		flag = false;
	} */
	
	if(flag){
		//验证通过
		//dialog.alert("验证通过");
		
		var param = {};
		param.name = name;
		param.url = url;
		param.deleteFlag = 0;
		param.parentId = $("#menu_parentId").val();
		
		var nodes = zTree.getNodesByParam("parentId", param.parentId, null);
		//新增节点在最后，要确定他的levelType,levelIndex
		param.levelIndex = 0;
		for(var i=0; i<nodes.length; i++){
			if(nodes[i].levelIndex > param.levelIndex){
				param.levelIndex = nodes[i].levelIndex;
			}
		}
		param.levelIndex = param.levelIndex + 1;
		if(checkNode.levelType == null){
			param.levelType = 0
		}else{
			param.levelType = checkNode.levelType + 1;
		}
		
		//dialog.alert(JSON.stringify(param));
		
		$.ajax({
    		type:"POST",
    		async: false,  //默认true,异步
    		data: param,
    		url: "<%=request.getContextPath()%>/managePage/addManagePage.action",
    		success:function(data){
		    	//增加成功
		    	//dialog.alert(JSON.stringify(data));
		    	zTree.addNodes(checkNode, data);
    	    }
    	});
		
		$.ligerDialog.hide();
	}
}

function delNode(id, tId){
	//dialog.alert(tId);
	$.ligerDialog.confirm('确认删除吗？', function(yes){ 
		if(yes){
			$.ajax({
	    		type:"POST",
	    		async:false,  //默认true,异步
	    		data: "id="+id,
	    		url: "<%=request.getContextPath()%>/managePage/delManagePageById.action",
	    		success:function(data){
    				if(data == "OK"){
    			
    					zTree.removeNode(zTree.getNodeByTId(tId));
    	   		 	}
	    		}
	    	});
	    }
	});
}

function closeWin(){
	$.ligerDialog.hide();
}

</script>
</head>
<body style="">

	<div style="margin:10px;">
		<ul id="pageList" class="ztree"></ul>
	</div>
	
	
	<div id="addNode" style="display:none;">
		<table class="node_table" cellpadding="0" cellspacing="0" style="margin-left:10px;margin-right:10px;">
			<tr>
				<td align="right">名称：</td>
				<td align="left">
					<input type="text" id="menu_name" class="input_0" />
				</td>
				<td>
					<span style="color:red;margin-left:10px;"></span>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="height:5px;"></td>
			</tr>
			<tr id="urlTr">
				<td align="right">URL：</td>
				<td align="left">
					<input type="text" id="menu_url" class="input_0" />
				</td>
				<td>
					<span style="color:red;margin-left:10px;" ></span>
				</td>
			</tr>
		</table>
		<div align="right" style="margin-top:10px;">
			<button class="btu_0" style="margin-right:5px;" id="saveBtu" onclick="save()" >保存</button>
			<button class="btu_0" style="margin-right:10px;"  onclick="closeWin()" >取消</button>
		</div>
		
		<input type="hidden" id="menu_parentId" />
		
	</div>
	
</body>
</html>
