<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改问问分类</title>
<!-- 页面style和JS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<!-- jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
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

<style type="text/css">
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
var typeId;//分类id

/**
 * 页面加载完成
 */
$(function(){
	//初始化问问分类树
	initThisAskTypes();
});

/**
 * 初始化问问分类树
 */
function initThisAskTypes(){
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
				onClick:zTreeOnClick
			}
	};
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:{"companyId":window.parent.companyId,"subCompanyId":window.parent.subCompanyId},
		url:'<%=request.getContextPath()%>/manageAsk/selectAskType.action',
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#thisTreePage"),setting,data);
		}
	});
	$.fn.zTree.getZTreeObj("thisTreePage").expandAll(true);
}

/**
 * 鼠标点击事件
 */
function zTreeOnClick(event, treeId, treeNode){
	typeId = treeNode.id;
}

/**
 * 树结构图标替换
 */
function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

/**
 * 保存分类更改
 */
function saveChange(){
	if(typeId == undefined){
		dialog.alert("请选中分类");
		return;
	}
	var param = {};
	param.askIds = window.parent.askIds;
	param.typeId = typeId;
	$.ajax({
		type:'POST',
		async:false,//此处为同步
		data:param,
		url:'<%=request.getContextPath()%>/manageAsk/changeType.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				dialog.alert("问问分类修改成功！");
			}else{
				dialog.alert("问问分类修改失败...");
			}
		}
	});
	window.parent.search();
	window.parent.addModifyDialog.close();
}

/**
 * 取消分类修改
 */
function cancleChange(){
	window.parent.addModifyDialog.close();
}

</script>

</head>
<body>
	<div>
		<div class="course_tree">
			<h2>问问分类</h2>
			<ul id="thisTreePage" class="ztree"></ul>
		</div>
		<div class="ui-dialog-button" style="height:100px;margin-bottom:10px;">
			<button type="button" onclick="cancleChange();" style="float:right;padding: 6px 12px;margin-bottom:10px;">取消</button>
			<button type="button" onclick="saveChange();" style="float:right;padding: 6px 12px;margin-bottom:10px;">确定</button>
		</div>
	</div>
</body>
</html>