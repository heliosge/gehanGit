
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
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<style type="text/css">
	.btn {background: #0085FE;color: white;text-align: center;width: 100%;height: 35px;}
	.course_tree {width: 98%;height: auto;border: 1px solid #333;font-family: '微软雅黑';text-align:center;}
	.course_tree h2 {font-size: 14px;width: 98%;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>
$(function(){
	initPage();
	
})

function initPage(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  true},
			callback: {
				beforeClick: function(treeId, treeNode, clickFlag){return false;}
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			$.fn.zTree.init($("#treePage"), setting, data.data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function save(){
	var nodes=$.fn.zTree.getZTreeObj("treePage").getCheckedNodes(true);
	if(nodes==null||nodes.length==0){
		dialog.alert("请选择数据！");
		return;
	}
	var idArr =[];
	var ids={};
	$.each(nodes, function(index, val){
		if(val.parentId){
			idArr.push(val.id);
		}
	});
	ids=idArr.join(',');
	window.parent.addUserByDep(ids);
	window.parent.$.ligerDialog.close();
	window.parent.$(".l-dialog,.l-window-mask").css("display","none");

}

</script>

</head>
<body>
	<div>
   	<div class="course_tree">
        <ul id="treePage" class="ztree" style=""></ul>
   		 <input type="button" value="确定" onclick="save()" class="btn"/>
   	</div>
     </div>
<script>
</script>

</body>
</html>

