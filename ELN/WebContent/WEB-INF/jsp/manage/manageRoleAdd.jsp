<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>


<style type="text/css">
	.ztree li span.button.noline_docu{display:none;}
	.page_div .ztree li span.button.noline_open{z-index:999;margin-left: 13px;position: absolute;background:url("");}
	.page_div .ztree li span.button.noline_close{z-index:999;margin-left: 13px;position: absolute;background:url("");}
	.td {text-align: center;padding-left: 16px;height: 36px;border: 1px solid#EAEAEA;padding-right: 16px;font-size:14px;}
	.page_div {width: 250px;height: auto;min-height:500px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.page_div_1 {width: 200px;height: auto;min-height:500px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.page_div_1 h2 {font-size: 14px;width: 200px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.page_div_1 .ztree li span.button.noline_open{z-index:999;margin-right: -18px;position: absolute;background:url("");}
	.page_div_1 .ztree li span.button.noline_close{z-index:999;margin-right: -18px;position: absolute;background:url("");}
</style>

<script type="text/javascript">
var userId = ${USER_ID_LONG};
var pageList;

$(function(){
	initPage();
	initValidate();
});

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}


function initPage(){
	//获取所有权限页面
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable: true},
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
		data:{"userId":userId},
		url: "<%=request.getContextPath()%>/index/getPageList.action",
		success:function(data){
			//添加根节点
			var o = {};
			o.id = 0;
			o.name = "全部权限";
			o.parentId = null;
			data.push(o);
			addIconInfo(data);
			pageList = data;
			$.fn.zTree.init($("#treePage"), setting, data);
			$.fn.zTree.getZTreeObj("treePage").expandAll(true);
		}
	});
	
}


function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = {};
			param.descr = $("#descr").val();
			param.name = $("#name").val();
			
			var chooseTreeObj = $.fn.zTree.getZTreeObj("chooseTreePage");
			if(chooseTreeObj == null){
				dialog.alert("请至少选中一个页面资源。");
				return;
			}
			var arrayParam = [];
			$.each(chooseTreeObj.getNodesByParam("parentId", 0, null), function(index, val){
				arrayParam.push(val.id);
				var childNodes = chooseTreeObj.getNodesByParam("parentId", val.id, val);
				$.each(childNodes, function(index, childVal){
					arrayParam.push(childVal.id);
				});
			});
			param.pageIds = arrayParam;
			if(arrayParam.length == 0){
				dialog.alert("请至少选中一个页面资源。");
				return;
			}
			
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:param,
				url:"<%=request.getContextPath()%>/manageRole/addManageRole.action",
				success:function(data){
					if(data == 'SUCCESS'){
						dialog.alert("新增角色成功。",function(){
							cancel();
						});
					}else{
						dialog.alert("新增角色失败。");
					}
				}
			});
		}else{
			dialog.alert("表单验证失败！");
		}
	 });
	
}

function choosePage(){
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
	$.fn.zTree.init($("#chooseTreePage"), setting, pageList);
	var chooseTreeObj = $.fn.zTree.getZTreeObj("chooseTreePage");
	//chooseTreeObj.expandAll(true);
	//删除未选中的节点
	$.each($.fn.zTree.getZTreeObj("treePage").getCheckedNodes(false), function(index, val){
		chooseTreeObj.removeNode(chooseTreeObj.getNodeByParam("id", val.id, null), false);
	});
	//删除根节点
	//chooseTreeObj.removeNode(chooseTreeObj.getNodeByParam("id", "0", null), false);
	$.fn.zTree.getZTreeObj("chooseTreePage").expandAll(true);
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/manageRole/roleList.action";
}

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checkName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{name:element.value},
					url:"<%=request.getContextPath()%>/manageRole/getManageRoleByName.action",
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		msgStyle:"margin-top:10px;",
		fields : {
			name : {
				rule : "required;length[~30];checkName;",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			descr : {
				rule : "required;length[~200]",
				msg : {
					required : "描述不能为空",
					length : "长度需小于等于200个字符"
				}
			}
		}
	});
}



</script>

</head>
<body>

<div class="content">
	<!-- <h3>新增角色</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">新增角色</span>
	</div>
	<form id="addForm">
	<div class="lesson_add">
        <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>角色名称：</em>
            </div>
             <div class="add_fr">
            	<input type="text"  id="name" name="name"/>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
        		<span>*</span>
                <em>角色描述：</em>
            </div>
             <div class="add_fr">
            	<textarea id="descr" name="descr"></textarea>
            </div>
    	</div>
        <div class="add_gr_1">
        	<div class="add_fl">
                <em>分配权限：</em>
            </div>
             <div class="add_fr">
	        	 <table>
	               	<tr>
	               		<td>
	                		<div class="page_div">
	                			<h2>全部功能模块</h2>
	                			<ul id="treePage" class="ztree" style=""></ul>
	                		</div>
	                	</td>
	               		<td style="width:150px;text-align:center;">
	                		<div style="cursor: pointer;" onclick="choosePage()">
					    		<img class="img " src="<%=request.getContextPath() %>/images/img/u143.png">
					    	</div>
		    			</td>
	               		<td>
	               			<div class="page_div_1">
	               				<h2>已分配功能模块</h2>
	               				<ul id="chooseTreePage" class="ztree" style=""></ul>
	               			</div>
	               		</td>
	               	</tr>
		          </table>
	          </div>
        </div>
        <div class="button_cz">
        	<input type="button" value="保存" onclick="save()"/>
            <input type="button" value="返回" class="back" onclick="cancel()"/>
        </div>
      </div>
      </form>
   </div>
</body>
</html>
