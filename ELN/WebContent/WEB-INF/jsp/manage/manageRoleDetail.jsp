<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>




<style type="text/css">
	.ztree li span.button.noline_docu{width:0px;}
	.ztree li span.button.noline_open{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.page_div {width: 250px;height: auto;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script type="text/javascript">
var userId = ${USER_ID_LONG};
var role = ${role};
var pageList;

$(function(){
	fillBaseInfo();
	initChoosenPage();
});

function fillBaseInfo(){
	$("#name").val(role.name);
	$("#descr").html(role.descr);
}


function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}


function initChoosenPage(){
	//获取所有权限页面
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable: false},
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
		async:true,  //默认true,异步
		data:{"roleId":role.id},
		url: "<%=request.getContextPath()%>/manageRole/getManagePageByRole.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#chooseTreePage"), setting, data);
			$.fn.zTree.getZTreeObj("chooseTreePage").expandAll(true);
		}
	});
}




function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageRole/roleList.action";
}



</script>

</head>
<body>

<div class="content">
	<!-- <h3>角色详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">角色详情</span>
	</div>
	<div class="lesson_add">
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>角色名称：</em>
            </div>
             <div class="add_fr">
            	${role.name }
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>角色描述：</em>
            </div>
             <div class="add_fr">
            	${role.descr }
            </div>
    	</div>
        <div class="add_gr_1">
        	<div class="add_fl">
                <em>分配权限：</em>
            </div>
             <div class="add_fr">
	        	 <div class="page_div">
        				<h2>已分配功能模块</h2>
        				<ul id="chooseTreePage" class="ztree" style=""></ul>
        			</div>
	          </div>
        </div>
        <div class="button_cz">
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
      </div>
   </div>
</body>
</html>
