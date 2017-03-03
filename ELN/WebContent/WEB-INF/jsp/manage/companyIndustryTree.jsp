<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
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
<!-- 右键菜单 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/smartMenu/smartMenu.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartMenu/smartMenu-min.js"></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.ztree li span.button.noline_docu{display：none;}
	.course_tree {overflow-y:auto;overflow-x:auto;width: 300px;height:530px;border: 1px solid #333;font-family: '微软雅黑';text-align:center;margin-left:300px;}
	.course_tree h2 {font-size: 14px;width: 98%;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
</style>

<script>
$(function(){
	initPage();
	initSmartMenu();
	//验证
	initUpdateFormValidate();
	initAddFormValidate();
	
})

function initPage(){
	industryId = '';
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
				onClick: zTreeOnClick,
				onMouseUp: zTreeOnMouseUp
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/login/initIndustry.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

var industryId = '';
function zTreeOnClick(event, treeId, treeNode) {
	industryId = treeNode.id;
};

function zTreeOnMouseUp(event, treeId, treeNode){
	if(treeNode != null){
		zTreeOnClick(event, treeId, treeNode);
		$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
	}
}

function initSmartMenu(){
	$("#tree").smartMenu( [[{
        text: "新增分类",
        func: add
    }, {
        text: "修改分类",
        func: modify
    }, {
        text: "删除分类",
        func:del
    }]]);
}
var artDialog;
function add(){
	$(".n-yellow .msg-wrap").hide();
	$("#addName").val('');
	$("#addDescr").val('');
	artDialog = dialog({ 
        title: "新增行业分类",
        content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				addIndustry();
	            			}
	            			});
	            	  return false;
	              }
		          },{
		              value: '取消',
		              callback: function () {
		            	 this.close();
		              }
		          }
		      ]
    }).showModal();
}


function modify(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:industryId},
		url:"<%=request.getContextPath()%>/login/initIndustry.action",
		success:function(data){
			$(".n-yellow .msg-wrap").hide();
			$("#updateId").val(data[0].id);
			$("#updateName").val(data[0].name);
			$("#updateDescr").val(data[0].descr);
			artDialog = dialog({ 
				 title: "修改行业分类",
		        content: $("#modify"),
		        width: 450,
		        height:300,
				 button: [
				          {
			              value: '确定',
			              callback: function () {
			            	  $('#updateForm').isValid(function(v) {
			            			if(v){
			            				updateIndustry();
			            			}
			            		});
			            	  return false;
			              }
				          },{
				              value: '取消',
				              callback: function () {
				            	 this.close();
				              }
				          }
				      ]
		    }).showModal();
		}
	});
}

function del(){
	if(industryId == ''){
		dialog.alert("请选择一个节点。");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:industryId},
		url:"<%=request.getContextPath()%>/manageParam/deleteManageIndustry.action",
		success:function(data){
			initPage();
			dialog.alert("删除成功");
		}
	});
}

function cancel(){
	dialog.hide()
}

function addIndustry(){
	//$('#addForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{parentId:industryId,
					name:$("#addName").val(),
					descr:$("#addDescr").val()},
				url:"<%=request.getContextPath()%>/manageParam/addManageIndustry.action",
				success:function(data){
					initPage();
					artDialog.close().remove();
					dialog.alert("新增成功");
				}
			});
	//	} else {
	//		return false;
			//dialog.alert("表单验证不通过");
	//	}
	//});
}

function updateIndustry(){
	//$('#updateForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{
					id:$("#updateId").val(),
					name:$("#updateName").val(),
					descr:$("#updateDescr").val()},
				url:"<%=request.getContextPath()%>/manageParam/updateManageIndustry.action",
				success:function(data){
					initPage();
					artDialog.close().remove();
					dialog.alert("修改成功");
				}
			});
		//} else {
		//	return false;
			//dialog.alert("表单验证不通过");
		//}
		//});
}

/**
 * 表单验证
 */
function initAddFormValidate() {
	$('#addForm').validator({
		rules : {
			checkName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{parentId:industryId,
						  name:$("#addName").val()},
					url:"<%=request.getContextPath()%>/login/initIndustry.action",
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
		theme : 'yellow_right',
		msgStyle:"margin-top:10px;",
		fields : {
			addDescr : {
				rule : "length[~51]",
				msg : {
					length : "长度需小于等于50个字符"
				}
			},
			addName : {
				rule : "required;length[~10];checkName",
				msg : {
					required : "行业分类名称不能为空",
					length : "长度需小于等于10个字符"
				}
			}
		}
	});
}

function initUpdateFormValidate() {
	$('#updateForm').validator({
		rules : {
			checkSameName:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{parentId:$("#updateParentId").val(),
						  name:$("#updateName").val()},
				    url:"<%=request.getContextPath()%>/login/initIndustry.action",
					success:function(data){
						var flag = true;
						if(data.length > 0 && data[0].id != $("#updateId").val()){
							flag = false;
						}
						str =  flag || "已存在相同名称";
					}
				});
				return str;
			}
		},
		theme : 'yellow_right',
		msgStyle:"margin-top:10px;",
		fields : {
			updateDescr : {
				rule : "length[~50]",
				msg : {
					length : "长度需小于等于50个字符"
				}
			},
			updateName : {
				rule : "required;length[~10];checkSameName;",
				msg : {
					required : "行业分类名称不能为空",
					length : "长度需小于等于10个字符"
				}
			}
		}
	});
}
</script>

</head>
<body>
	<div class="content">
	<!-- <h3>行业分类</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">企业分类</span>
	 </div>
   	<div class="course_tree" id="tree">
        <ul id="treePage" class="ztree" style=""></ul>
   	</div>
     </div>
     <div style="display:none;width:300px;" id="add">
			<form id="addForm">	
				<div>	
				<table>
					<tr height="50px;">
						<td><span style="color:red">*</span>名称:</td>
						<td><input type="text" style="width:200px;height:30px;" id="addName" name="addName"/></td>
					</tr>
					<tr style="margin-top:5px;">
						<td>描述:</td>
						<td><textarea style="width:200px;height:60px;"  id="addDescr" name="addDescr"></textarea></td>
					</tr>
				</table>
		        </div>
		</form>
	</div>
	
	<div style="display:none;width:300px;" id="modify">
			<form id="updateForm">
				<div>	
					<table>
						<tr height="50px;">
							<td><span style="color:red">*</span>名称:</td>
							<td>
								<input type="text" style="width:200px;height:30px;" id="updateName" name="updateName"/>
				                <input type="hidden" id="updateId"/>
				                <input type="hidden" id="updateParentId"/>
			                </td>
						</tr>
						<tr>
							<td>描述:</td>
							<td><textarea style="width:200px;height:60px;"  id="updateDescr" name="updateDescr"></textarea></td>
						</tr>
					</table>
		         </div>
		</form>
	</div>
	
<script>
</script>

</body>
</html>
