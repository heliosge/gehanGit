<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分商品管理</title>
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
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.course_tree {width: 98%;overflow-y:auto;overflow-x:auto;width: 250px;height:400px;border: 1px solid #333;font-family: '微软雅黑';text-align:center;}
	.course_tree h2 {font-size: 14px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")
</style>

<script>
$(function(){
	initPage();
	
})
function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
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
		url:"<%=request.getContextPath()%>/mall/manage/category/select.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
var categoryIds=[];
var categoryNames=[];
function getChildNodes(treeNode){
	categoryIds.push(treeNode.id); 
	categoryNames.push(treeNode.name);
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			categoryNames.push(treeNode.children[i].name);
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			categoryNames.push(treeNode.children[i].name);
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function zTreeOnClick(event, treeId, treeNode) {
	console.info(treeNode.id);
	categoryIds=[];
	categoryNames=[];
	if(treeNode.id == null){
		dialog.alert("公司节点不可选择");
		return false;
	}
	getChildNodes(treeNode);
	window.parent.$("#categoryId").val(treeNode.id);
	window.parent.$("#categoryName").val(treeNode.name);
	window.parent.categoryIds =categoryIds;
	window.parent.categoryId =treeNode.id;
	var categoryNamesStr = categoryNames.join(",")
	window.parent.$("#categoryNames").val(categoryNames);
};

function save(){
	window.parent.artDialog.close();
}

</script>

</head>
<body>
<!-- 	<div>
	
   	<div class="course_tree">
        <ul id="treePage" class="ztree" style=""></ul>
        <input type="button" value="确定" onclick="save()" class="btn"/>
   	</div>
    </div> -->
    
    <div>
   	<div class="course_tree">
   		<h2>商品分类</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
   	<div class="ui-dialog-button" style="height:100px;margin-bottom:10px;">
   		<button type="button" onclick="save()" style="float:right;padding: 6px 12px;margin-top:40px;cursor:pointer;">确定</button>
   	</div>
     </div>
<script>
</script>

</body>
</html>
