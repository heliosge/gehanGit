<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>元数据管理</title>
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

.ztree li button.switch {visibility:hidden; width:1px;}
.ztree li button.switch.roots_docu {visibility:visible; width:16px;}
.ztree li button.switch.center_docu {visibility:visible; width:16px;}
.ztree li button.switch.bottom_docu {visibility:visible; width:16px;}

.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.ztree li span.button.edit {margin-right:2px; background-position:-110px -48px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.remove {margin-right:2px; background-position:-109px -64px; vertical-align:top; *vertical-align:middle}

/* .ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;} */

</style>

<script type="text/javascript">
//所以元数据
var allMetaList = ${metaList};
var zTree = null;
var checkNode = null;  //焦点节点

$(function(){
	
	allMetaList.unshift({id:0, name:"所有菜单", metaKey:"root", metaValue:"元数据管理",descr:"元数据管理"});
	
	$.each(allMetaList, function(index, val){
		val.title = val.metaKey + " " + val.descr;
	});
	
	var setting = {
		data: {
			key: {
				name: "descr",
				title: "title"
			},
			simpleData: {
				enable: true,
				pIdKey: "parentId"
			}
		},
		check: {
			enable: false
		},
		view: {
			selectedMulti: false,
			//showTitle: false,
			dblClickExpand: false,
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom
		}
	};
	zTree = $.fn.zTree.init($("#metaTree"), setting, allMetaList);
});

//鼠标移上图标
function addHoverDom(treeId, treeNode) {
	
	var sObj = $("#" + treeNode.tId + "_span");
	
	if($("#op_"+treeNode.id).length > 0){
		return;
	}
	
	var str = "<div id='op_"+treeNode.id+"' class='op_div'>";
	
	//增加子菜单
	str += "<span class='button add' title='增加子菜单' onclick='addNode("+treeNode.id+")' ></span>";
	
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
	//alert(parentId+" 下增加子菜单");
	
	$(".node_table").find("span").text("");
	
	var broNodes = zTree.getNodesByParam("parentId", parentId, null);
	if(broNodes.length > 0 && parentId !=0 ){
		//如果有兄弟节点，key不让再输入
		$("#key").val(broNodes[0].metaKey);
		$("#key").attr("readonly", "readonly");
	}else{
		$("#key").val("");
		$("#key").removeAttr("readonly");
	}
	
	$("#value").val("");
	$("#descr").val("");
	
	$("#parentId").val(parentId);
	
	$("#saveBtu").attr("onclick", "save()");
	
	$.ligerDialog.open({ 
		title: "增加元数据",
		target: $("#addNode"),
		width: 450
	});
}

function save(){
	
	//alert("保存元数据");
	
	$(".node_table span").text("");
	var key = $.trim($("#key").val());
	var value = $.trim($("#value").val());
	var descr = $.trim($("#descr").val());
	
	var flag = true;
	if(key == ""){
		$("#key").parent().next().find("span").text("key不可为空");
		flag = false;
	}else{
		if(key.length > 20){
			$("#key").parent().next().find("span").text("key最多20个字符");
			flag = false;
		}
	}
	if(value == ""){
		$("#value").parent().next().find("span").text("值不可为空");
		flag = false;
	}
	if(descr == ""){
		$("#descr").parent().next().find("span").text("描述不可为空");
		flag = false;
	}
	
	if(flag){
		//验证通过
		//alert("验证通过");
		
		var param = {};
		param.metaKey = key;
		param.metaValue = value;
		param.descr = descr;
		param.deleteFlag = 0;
		param.parentId = $("#parentId").val();
		
		var nodes = zTree.getNodesByParam("parentId", param.parentId, null);
		//新增节点在最后，要确定他的orderIndex
		param.orderIndex = 0;
		for(var i=0; i<nodes.length; i++){
			if(nodes[i].orderIndex > param.orderIndex){
				param.orderIndex = nodes[i].orderIndex;
			}
		}
		param.orderIndex = param.orderIndex + 1;
		
		$.ajax({
    		type:"POST",
    		async: false,  //默认true,异步
    		data: param,
    		url: "<%=request.getContextPath()%>/baseMeta/addMeta.action",
    		success:function(data){
		    	//增加成功
		    	//alert(JSON.stringify(data));
		    	if(data){
		    		data.title = data.metaKey + " " + data.descr;
		    		zTree.addNodes(checkNode, data);
		    	}
		    	
    	    }
    	});
		
		$.ligerDialog.hide();
	}
}

//修改
function aditNode(id, tId){
	//
	var node = zTree.getNodeByTId(tId);
	
	//清空验证信息
	$(".node_table").find("span").text("");
	
	//key不让改
	$("#key").val(node.metaKey);
	$("#key").attr("readonly", "readonly");
	
	$("#value").val(node.metaValue);
	$("#descr").val(node.descr);
	
	$("#parentId").val(node.parentId);
	
	$("#saveBtu").attr("onclick", "edit('"+tId+"')");
	
	$.ligerDialog.open({ 
		title: "修改菜单",
		target: $("#addNode"),
		width: 450
	});
}

function edit(tId){
	//
	var node = zTree.getNodeByTId(tId);
	
	$(".node_table span").text("");
	var key = $.trim($("#key").val());
	var value = $.trim($("#value").val());
	var descr = $.trim($("#descr").val());
	
	var flag = true;
	if(key == ""){
		$("#key").parent().next().find("span").text("key不可为空");
		flag = false;
	}else{
		if(key.length > 20){
			$("#key").parent().next().find("span").text("key最多20个字符");
			flag = false;
		}
	}
	if(value == ""){
		$("#value").parent().next().find("span").text("值不可为空");
		flag = false;
	}
	if(descr == ""){
		$("#descr").parent().next().find("span").text("描述不可为空");
		flag = false;
	}
	
	if(flag){
		//验证通过
		//alert("验证通过");
		node.metaKey = key;
		node.metaValue = value;
		node.descr = descr;
		
		$.ajax({
    		type:"POST",
    		async: false,  //默认true,异步
    		data: node,
    		url: "<%=request.getContextPath()%>/baseMeta/updateMeta.action",
    		success:function(data){
		    	//增加成功
		    	//alert(JSON.stringify(data));
		    	if(data == "SUCCESS"){
	    			
		    		zTree.updateNode(node, false);
	   		 	}
    	    }
    	});
		
		$.ligerDialog.hide();
	}
}

function delNode(id, tId){
	//alert(tId);
	$.ligerDialog.confirm('确认删除吗？', function(yes){ 
		if(yes){
			$.ajax({
	    		type:"POST",
	    		async:false,  //默认true,异步
	    		data: "id="+id,
	    		url: "<%=request.getContextPath()%>/baseMeta/delMeta.action",
	    		success:function(data){
    				if(data == "SUCCESS"){
    			
    					zTree.removeNode(zTree.getNodeByTId(tId));
    	   		 	}
	    		}
	    	});
	    }
	});
}

//关闭弹窗口
function closeWin(){
	$.ligerDialog.hide();
}

</script>
</head>
<body style="">

	<div class="top_page_name">元数据管理</div>
	
	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;">
	
	<div style="padding:10px 20px 20px 20px;">
		
		<ul id="metaTree" class="ztree"></ul>
		
	</div>
	
	<div id="addNode" style="display:none;">
		<table class="node_table" cellpadding="0" cellspacing="0" style="margin-left:10px;margin-right:10px;">
			<tr>
				<td align="right">key：</td>
				<td align="left">
					<input type="text" id="key" class="input_0"  />
				</td>
				<td>
					<span style="color:red;margin-left:10px;"></span>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="height:5px;"></td>
			</tr>
			<tr id="urlTr">
				<td align="right">值：</td>
				<td align="left">
					<input type="text" id="value" class="input_0" />
				</td>
				<td>
					<span style="color:red;margin-left:10px;" ></span>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="height:5px;"></td>
			</tr>
			<tr id="urlTr">
				<td align="right">描述：</td>
				<td align="left">
					<input type="text" id="descr" class="input_0" />
				</td>
				<td>
					<span style="color:red;margin-left:10px;" ></span>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="height:10px;"></td>
			</tr>
		</table>
		<div align="center" style="">
			<button class="btu_0" style="margin-right:5px;" id="saveBtu" onclick="save()" >保存</button>
			<button class="btu_0" style="margin-right:10px;"  onclick="closeWin()" >取消</button>
		</div>
		
		<input type="hidden" id="parentId" />
		
	</div>
	
	</div>
</body>
</html>
