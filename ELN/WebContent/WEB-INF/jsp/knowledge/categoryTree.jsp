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

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<style type="text/css">

html, body{
}
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:98%;height:30px;padding-top: 0px;border-bottom: 1px dotted #ccc;}
.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
.ztree li a span.button.switch {visibility:visible}
.ztree.showIcon li a span.button.switch {visibility:visible;}
.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}

.ztree li span.button{	background: url("") 0 0 no-repeat;
}
.ztree li span.button.center_close,.ztree li span.button.root_close,.ztree li span.button.roots_close,.ztree li span.button.bottom_close  {
	background: url(<%=request.getContextPath()%>/images/img/ico_zk.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}
.ztree li span.button.center_open,.ztree li span.button.root_open,.ztree li span.button.roots_open,.ztree li span.button.bottom_open{
	background: url(<%=request.getContextPath()%>/images/img/ico_sq.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}
.ztree li span.button.ico_docu{
	background: url(<%=request.getContextPath()%>/images/img/ico_txt.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}

.ztree li span.button.ico_close{
	display: none;
}
.ztree li span.button.ico_open{
	display: none;
}
.ztree li ul.line{
	background: url("");
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
					idKey: "categoryId",
					pIdKey: "parentId",
					rootPId: 0
				},
				key:{
					title: "categoryName",
					name:"categoryName"
				}
			},
			callback: {
				onClick: function(event, treeId, treeNode){
					$("#categoryId").val(treeNode.categoryId);
					$("#categoryId").attr("showName",getZTreePathNameWithRootNode(treeNode,"categoryName"));
				}
				
			},
			view: {
				fontCss : function(){
					
				},//个性化设置ztree的节点高亮效果
				showTitle: true,
				addDiyDom: addDiyDom
			}
		};
	
	$.fn.zTree.init($("#categoryTree"), treeSet,${categoryList});//初始化模型树
	var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	var o_nodes = treeObj.getNodes();//.getNodesByParam("id", 1, null);
	/* if (o_nodes.length>0) {
		treeObj.expandNode(o_nodes[0], true, false, true);
	} */
	function zTreeOnAsyncSuccess(){
		var categoryId =${categoryId};
		if(categoryId!=0){
			var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
			var nodes  = treeObj.getNodeByParam("categoryId",categoryId,null);
			treeObj.selectNode(nodes[0]);
		}
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
<input type="hidden" id='categoryId' value='-1'/>	
<div style='height: 448px;overflow: auto;border: 1px solid;'>
	<div class='tree ztree' id='categoryTree'>
	
	</div>
</div>
</body>
</html>
