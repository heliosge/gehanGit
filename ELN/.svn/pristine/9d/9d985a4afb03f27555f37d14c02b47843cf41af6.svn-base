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

<style type="text/css">
	.btn {background: #0085FE;color: white;text-align: center;width: 100%;height: 35px;}
	.course_tree {width: auto;overflow-y:auto;overflow-x:auto;min-width: 250px;height: 400px;border: 1px solid #333;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background:url("");}
	.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background:url("");}
</style>

<script>
$(function(){
	initPage();
	initCheckedPage();
	
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
			check: {enable:  true,chkboxType: { "Y": "s", "N": "" }},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageParam/selectManagePost.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function initCheckedPage(){
	var treeObj = $.fn.zTree.getZTreeObj("treePage");
	$.each(window.parent.postIds, function(index, val){
		var node = treeObj.getNodeByParam("id", val, null);
		if(node.parentId !=null){
			treeObj.checkNode(node,true,true);
		}else if(node.children == null){
			treeObj.checkNode(node,true,true);
		}
	});
}

function save(){
	window.parent.postIds = [];
	window.parent.$("#post").html('');
	$.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(true), function(index, val){
		window.parent.postIds.push(val.id);
		window.parent.$("#post").append("岗位："+val.name+"; ");
	});
	window.parent.artDialog.close();
}

</script>

</head>
<body>
	<div style="height:500px;">
   	<div class="course_tree">
        <ul id="treePage" class="ztree"></ul>
   		 <!-- <input type="button" value="确定" onclick="save()" class="btn"/> -->
   	</div>
   	<div class="ui-dialog-button" style="height:100px;">
   		<button type="button" onclick="save()" style="float:right;padding: 6px 12px;margin-top:40px;cursor:pointer;">确定</button>
   	</div>
     </div>
<script>
</script>

</body>
</html>
