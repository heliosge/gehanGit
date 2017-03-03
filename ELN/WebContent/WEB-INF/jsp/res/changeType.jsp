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
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.course_tree {width: 98%;overflow-y:auto;overflow-x:auto;width: 250px;height:400px;border: 1px solid #333;font-family: '微软雅黑';text-align:center;}
	.course_tree h2 {font-size: 14px;width: 100%;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{margin-left:13px;z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{margin-left:13px;z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
</style>

<script>
$(function(){
	initPage();
	initChoosenPage();
})

function initChoosenPage(){
	var param = {};
	if(window.parent.ids.length == 1){
		param.courseId = window.parent.ids[0];
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:param,
			url:"<%=request.getContextPath()%>/res/selectCourseTypeByCourseId.action",
			success:function(data){
				var treeObj = $.fn.zTree.getZTreeObj("treePage");
				if(data != null){
					$.each(data, function(index, val){
						var node = treeObj.getNodeByParam("id", val.type_id, null);
						treeObj.checkNode(node,true,true);
					});
				}
			}
		});
	}
}

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
			check: {enable:  true,chkboxType: { "Y": "", "N": "" }},
			view: {
				showLine: false,
				showIcon: true
			}/* ,
			callback: {
				onClick: zTreeOnClick
			} */
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/res/selectResCourseType.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}
var typeId;

function zTreeOnClick(event, treeId, treeNode) {
	typeId = treeNode.id;
};

function save(){
	var param = {};
	param.ids = window.parent.ids;
	param.typeIds = [];
	$.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(true), function(index, val){
		if(val.id != null){
			param.typeIds.push(val.id);
		}
	});
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:param,
		url:"<%=request.getContextPath()%>/res/changeType.action",
		success:function(data){
			dialog.alert("课程分类修改成功。");
		}
	});
	window.parent.search();
	window.parent.artDialog.close();
}

function cancle(){
	window.parent.artDialog.close();
}

</script>

</head>
<body>
	<div>
   	<div class="course_tree">
   		<h2>课程分类</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
   	<div class="ui-dialog-button" style="height:100px;margin-bottom:10px;">
   		<button type="button" onclick="cancle()" style="float:right;padding: 6px 12px;margin-bottom:10px;">取消</button>
   		<button type="button" onclick="save()" style="float:right;padding: 6px 12px;margin-bottom:10px;">确定</button>
   	</div>
     </div>
<script>
</script>

</body>
</html>
