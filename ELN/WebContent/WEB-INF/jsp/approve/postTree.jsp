<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">

html, body{
}


</style>

<script type="text/javascript">

$(function(){
	
	// 模型数据tree的set参数设置
	var treeSet = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
					isCopy: false,
					prev: false,
					next: false,
					inner: false,
					zIndex:1000
				}
			},
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId",
					rootPId: 0
				},
				key:{
					title: "name",
					name:"name"
				}
			},
			callback: {
				onClick: function(event, treeId, treeNode){
					$("#post").val(treeNode.id);
					$("#post").attr("showName",treeNode.name);
				}

			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom
			}
		};
	var dataArr = ${jsonArray};
	if(dataArr.length>0){
		$.fn.zTree.init($("#categoryTree"), treeSet,dataArr);//初始化模型树
		var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
		treeObj.expandAll(true);
	}else{
		$("#categoryTree").html("<span style='color:red'>无数据</span>")
	}

});

</script>
<style type="text/css">
	.text-p{margin: 10px; width:250px;
	margin: 10px;
	width: 250px;
	padding: 0 2px;
	height: 28px;
	color: #555;
	font: 12px Verdana;
	border: 1px solid #cacaca;
	line-height: 20px;
	vertical-align: middle;}	
	.tree{
		margin: 2px;
	}
	.mm-table{
	}
</style>
</head>

<body style="overflow-x:hidden;">
<input type="hidden" id='post' value='-1'/>	
<div>


</div>
<div style='height:350px'>
	<div class='tree ztree' id='categoryTree' style="height: 340px;overflow: auto;">
		无数据
	</div>
	<div id="errTit" style="position: absolute;bottom: 0px;margin-left: 5px;"> </div>
</div>
</body>
</html>
