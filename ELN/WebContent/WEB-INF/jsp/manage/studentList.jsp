<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学员账号管理</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exhide-3.5.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- 右键菜单 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/smartMenu/smartMenu.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartMenu/smartMenu-min.js"></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: left;}
	.company_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.company_tree2 {overflow-y:auto;overflow-x:auto;width: 250px;height: 300px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.btn {
		background: #0085FE;color: white;text-align: center;width:100px;height: 35px;border: none;border-radius: 2px;font-weight: bold;margin-right: 10px;
		}
</style>

<script type="text/javascript">

var initUserId = '${initUserId}';
var isCompany = '${isCompany}';

var contextMenu;

$(function(){
	
	//initSmartMenu();
	
	contextMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
    [
    { id:'zTreeUp',text: '上移', click: up },
    { id:'zTreeAdd', text: '新增部门', click: add },
    { id:'zTreeAddSubCom', text: '新增下级单位', click: addSubCom },
    { id:'zTreeModify', text: '修改部门', click: modify },
    { id:'zTreeDel', text: '删除部门', click: del },
    { id:'zTreeDown', text: '下移', click: down }
    ]
    });
	
	//验证
	initUpdateFormValidate();
	initAddFormValidate();
	
	
	initPage();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/selectStudentList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
               {title: '姓名', name: 'id', width:60, align:'center',renderer:function(val, item, rowIndex){
            	   return '<a href="toStudentDetailtPage.action?id='+item.id+'" >'+item.name+'</a>'
               }},
               {title: '公司名称', name: 'subCompanyName', width:60, align:'center'},
 			   {title: '出生日期', name: 'birthDay', width:60, align:'center'},
			   {title: '状态', name: 'status', width:40, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }
				}},
			   {title: '角色', name: 'roleList', width:120, align:'center', renderer:function(val, item, rowIndex){
 				   var re = '';
 				   for(var i=0; i < val.length; i++){
 					  if(i == val.length-1){
 						 re += val[i].name;   
 					  }else{
 					 	 re += val[i].name + ',';   
 					  }
 				   }
 				   return re;
			   }},
				{title: '操作', name: 'id', width:140, align:'center', renderer:function(val, item, rowIndex){
				   var buttonStr ='';
				   if(initUserId == val){
					   buttonStr += '<a href="javascript:void(0);" style="color: #999;">冻结</a> <a href="javascript:void(0);" style="color: #999;" >修改</a>'
					   +' <a href="javascript:void(0);" style="color: #999;">删除</a> <a href="javascript:void(0);" style="color: #999;">重置密码</a>';
				   }else if(isCompany=='false' && item.isManager == 1){
					   buttonStr += '<a href="javascript:void(0);" style="color: #999;">冻结</a> <a href="javascript:void(0);" style="color: #999;" >修改</a>'
						   +' <a href="javascript:void(0);" style="color: #999;">删除</a> <a href="javascript:void(0);" style="color: #999;">重置密码</a>';
				   }else{
					   if(item.status == 1){
						   buttonStr += '<a href="javascript:void(0)" onclick="freeze('+item.id+')" >冻结</a>';
					   }else if(item.status == 2){
						   buttonStr += '<a href="javascript:void(0)" onclick="unfreeze('+item.id+')" >解冻</a>';
					   }
					   buttonStr += ' <a href="toUpdateStudentPage.action?id='+item.id+'" >修改</a>'
							   		+' <a href="javascript:void(0)" onclick="deleteRowOne('+item.id+')" >删除</a>'
					   				+' <a href="javascript:void(0)" onclick="resetOneUserPassword('+item.id+')" >重置密码</a>'
					   				+' <a href="javascript:void(0)" onclick="uploadPhoto_('+item.id+',\''+item.photo+'\')" >上传照片</a>';
				   }
				   return buttonStr;
			   }}
           ],
        plugins : [
        	$('#paginator11-1').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ]
    });
});

//上传照片
function uploadPhoto_(userId,photo){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/toUploadPhoto_.action?userId="+userId+"&photo="+photo,
        title:"选择照片" ,
        height: 250,
		width: 450
		}).showModal();
}

//自动创建部门编码
function createDeptCode(parentId){
	var code = '';
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{parentId:parentId},
		url:"<%=request.getContextPath()%>/manageCompany/createDeptCode.action",
		success:function(data){
			code = data;
		}
	});
	return code;
}


function addIconInfo(data) {
    for (var idx in data) {
    	data[idx].id.indexOf("com") != 0 && deptIds.push(data[idx].id);
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
        data[idx].name = data[idx].name+(data[idx].code == null ? '' : " "+data[idx].code);
    }
}

function setFontCss(treeId, treeNode) {
	return treeNode.isSubCompany == 1 ? {color:"red"} : {};
};


function initPage(){
	deptIds = [];
	deptId = '';
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true,
				fontCss: setFontCss
			},
			callback: {
				onMouseUp: zTreeOnMouseUp,
				onClick: zTreeOnClick,
				onRightClick: function(event, treeId, treeNode){
					if (treeNode && !treeNode.noR) {
				        //zTree.selectNode(treeNode);
				        contextMenu.show({top: event.pageY, left: event.pageX});
				        return false;
				    }
				}
			}
	};
	
	var setting2 = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true,
				fontCss: setFontCss
			},
			callback: {
				onClick: zTreeOnClick2
			}
	};
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			$.fn.zTree.init($("#treePage"), setting, data.data);
			$.fn.zTree.init($("#treePage2"), setting2, data.data);
			//如果是集团公司,删除子公司的部门
			//var treeObj = $.fn.zTree.getZTreeObj("treePage");
			//for(var i = 0; i < data.subData.length; i++){
			//	var node = treeObj.getNodeByParam("id", data.subData[i], null);
			//	treeObj.hideNodes(node.children);
				//treeObj.removeNode(node, false);
			//}
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

var deptIds = [];
var deptId = '';
var node;
function zTreeOnClick(event, treeId, treeNode) {
	node = treeNode;
	deptId = treeNode.id;
	deptIds = [];
	if(treeNode.id.indexOf("com") == 0){
		deptId = '';
		//deptIds = [];
	}
	getChildNodes(treeNode);
	search();
};

function zTreeOnClick2(event, treeId, treeNode){
	deptId = treeNode.id;
	if(treeNode.id.indexOf("com") == 0){
		deptId = '';
	}
}

function zTreeOnMouseUp(event, treeId, treeNode){
	if(treeNode != null){
		zTreeOnClick(event, treeId, treeNode);
		$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
	}
}

function getChildNodes(treeNode){
	if(treeNode.id.indexOf("com") != 0){
		deptIds.push(treeNode.id); 
	}
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			deptIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			deptIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function param(){
	var o = {};
	o.userName = $("#userName").val();
	o.name = $("#name").val();
	o.status = $("#status").val();
	o.deptIds = deptIds;
	o.roleName = $("#roleName").val();
	o.photo = $("#photo").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function unfreeze(id){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			ids : param,
   			status : 1
   		},
   		url:"<%=request.getContextPath()%>/manageUser/freezeAndUnfreezeUser.action",
   		success:function(data){
   			if(data == 'SUCCESS'){
   				search();
   				dialog.alert("解冻成功。");
			}else if(data == 'FAIL'){
				dialog.alert("解冻失败。");
			}else if(data == 'ACCOUNT_OVER'){
				dialog.alert("正常用户数已达到上限，不能解冻该用户。");
			}
   	    }
   	});
}

function freeze(id){
	var param = [];
   	param.push(id);
   	$.ajax({
   		type:"POST",
   		async:true,  //默认true,异步
   		data:{
   			ids : param,
   			status : 2
   		},
   		url:"<%=request.getContextPath()%>/manageUser/freezeAndUnfreezeUser.action",
   		success:function(data){
   			if(data == 'SUCCESS'){
   				search();
   				dialog.alert("冻结成功。");
			}else{
				dialog.alert("冻结失败。");
			}
   	    }
   	});
}

function deleteRowOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		var param = [];
	   	param.push(id);
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : param
	   		},
	   		url:"<%=request.getContextPath()%>/manageUser/delManageUser.action",
	   		success:function(data){
	   			if(data == 'SUCCESS'){
	   				search();
	   				dialog.alert("删除成功。");
				}else if(data == 'FAIL'){
					dialog.alert("删除失败。");
				}else{
					dialog.alert(data);
				}
	   	    }
	   	});
	});
}

function deleteRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认删除吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		if(initUserId != val.id){
		    		param.push(val.id);
	    		}
	    	});
	    	
	    	if(param.length == 0){
	    		dialog.alert("集团管理员不能删除。");
	    		return;
	    	}
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/manageUser/delManageUser.action",
	    		success:function(data){
	    			if(data == 'SUCCESS'){
		   				search();
		   				dialog.alert("删除成功。");
					}else if(data == 'FAIL'){
						dialog.alert("删除失败。");
					}else{
						dialog.alert(data);
					}
	    	    }
	    	});
		})
	}else{
		dialog.alert('请先选择数据！');
	}
}

function resetOneUserPassword(id){
	dialog.confirm("确认重置密码吗?",function(obj){
		var param = [];
	   	param.push(id);
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : param
	   		},
	   		url:"<%=request.getContextPath()%>/manageUser/resetPassword.action",
	   		success:function(data){
	   			if(data == 'SUCCESS'){
	   				search();
	   				dialog.alert("密码重置成功。");
				}else{
					dialog.alert("密码重置失败。");
				}
	   	    }
	   	});
	  })
}

function insertStudent(){
	if(deptId == undefined || deptId.indexOf("com_") != -1){
		dialog.alert("请选择部门。");
		return;
	}
	window.location.href="<%=request.getContextPath()%>/manageUser/toInsertStudentPage.action?deptId="+deptId;
}

function downloadTemplate(){
	$("#path").val("template/导入学员模板.xls");
	$("#fileName").val("导入学员模板.xls");
	$("#form").submit();
}

function downloadDeptTemplate(){
	$("#path").val("template/导入部门模板.xls");
	$("#fileName").val("导入部门模板.xls");
	$("#form").submit();
}

function initSmartMenu(){
	$("#tree").smartMenu( [[{
        text: "上移",
        func:up
    },{
        text: "新增部门",
        func: add
    }, {
        text: "新增下级单位",
        func:addSubCom
    }, {
        text: "修改部门",
        func: modify
    }, {
        text: "删除部门",
        func:del
    }, {
        text: "下移",
        func:down
    }]], {
		"beforeShow": function(){
			alert($(this).attr("class"));
		}
	});
}

function up(){
	if(node.getPreNode() == null){
		dialog.alert("该节点不可上移。");
		return;	
	}
	if(deptId == ''){
		dialog.alert("请先选中一个节点。");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:deptId},
		url:"<%=request.getContextPath()%>/manageDepartment/upDept.action",
		success:function(data){
			if(data == 'SUCCESS'){
				initPage();
				dialog.alert("操作成功");
			}else{
				dialog.alert("操作失败");
			}
		}
	});
}

function down(){
	if(node.getNextNode() == null){
		dialog.alert("该节点不可下移。");
		return;
	}
	if(deptId == ''){
		dialog.alert("请先选中一个节点。");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:deptId},
		url:"<%=request.getContextPath()%>/manageDepartment/downDept.action",
		success:function(data){
			if(data == 'SUCCESS'){
				initPage();
				dialog.alert("操作成功");
			}else{
				dialog.alert("操作失败");
			}
		}
	});
}

function add(){
	//验证选中的节点是不是子企业
	$(".n-yellow .msg-wrap").hide();
	isSub = 2;
	$("#addName").val('');
	$("#addCode").val(createDeptCode(deptId));
	$("#addDescr").val('');
	artDialog = dialog({ 
        title: "新增部门",
        content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				addDept();
	            				artDialog.close().remove();
	            			}
	            		});
	            	  return false;
	              }
		          },{
		              value: '继续新增',
		              callback: function () {
		            	  $('#addForm').isValid(function(v) {
		            			if(v){
		            				keepAddDept();
		            				$("#addName").val('');
		            				$("#addCode").val(createDeptCode(deptId));
		            				$("#addDescr").val('');
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

function addSubCom(){
	$(".n-yellow .msg-wrap").hide();
	isSub = 1;
	$("#addName").val('');
	$("#addCode").val(createDeptCode(deptId));
	$("#addDescr").val('');
	artDialog = dialog({ 
        title: "新增分公司",
        content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				addDept();
	            			}
	            		});
	            	  return false;
	              }
		          },{
		              value: '继续新增',
		              callback: function () {
		            	  $('#addForm').isValid(function(v) {
		            			if(v){
		            				keepAddDept();
		            				$("#addName").val('');
		            				$("#addCode").val(createDeptCode(deptId));
		            				$("#addDescr").val('');
		            			}
		            		});
		            	  return false;
		              }
			          }
		          ,{
		              value: '取消',
		              callback: function () {
		            	 this.close();
		              }
		          }
		      ]
    }).showModal();
}

function modify(){
	if(deptId == ''){
		dialog.alert("不能修改根节点");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{deptId:deptId},
		url:"<%=request.getContextPath()%>/manageDepartment/selectManageDept.action",
		success:function(data){
			$(".n-yellow .msg-wrap").hide();
			$("#updateId").val(data[0].id);
			$("#updateCode").val(data[0].code);
			$("#updateName").val(data[0].name);
			$("#updateDescr").val(data[0].descr);
			artDialog = dialog({ 
		        title: "修改部门",
		        content: $("#modify"),
		        width: 450,
		        height:300,
				 button: [
				          {
			              value: '确定',
			              callback: function () {
			            	  $('#updateForm').isValid(function(v) {
			            			if(v){
			            				updateDept();
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
	if(deptId == undefined){
		dialog.alert("请选择一个节点。");
		return;
	}
	if(node.children != undefined){
		dialog.alert("不能删除父部门。");
		return;
	}
	var rows = $('#exampleTable').mmGrid().rows();
	
	if(rows[0] != undefined){
		dialog.alert("不能删除该部门。");
		return;
	}
	dialog.confirm("确认删除吗?",function(obj){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{id:deptId},
			url:"<%=request.getContextPath()%>/manageDepartment/deleteManageDepartment.action",
			success:function(data){
				initPage();
				dialog.alert("删除成功");
			}
		});
		deptId='';
	})
}

function keepAddDept(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{parentId:deptId,
			isSubCompany :isSub,
			code:$("#addCode").val(),
			name:$("#addName").val(),
			descr:$("#addDescr").val()},
		url:"<%=request.getContextPath()%>/manageDepartment/addManageDepartment.action",
		success:function(data){
			if(data == 'SUCCESS'){
				//dialog.alert("新增成功");
			}else{
				dialog.alert("新增失败");
			}
		}
	});
}

function addDept(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{parentId:deptId,
			isSubCompany :isSub,
			code:$("#addCode").val(),
			name:$("#addName").val(),
			descr:$("#addDescr").val()},
		url:"<%=request.getContextPath()%>/manageDepartment/addManageDepartment.action",
		success:function(data){
			if(data == 'SUCCESS'){
				initPage();
				artDialog.close().remove();
				dialog.alert("新增成功");
			}else{
				dialog.alert("新增失败");
			}
		}
	});
	
}

function updateDept(){
	//$('#updateForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{
					id:$("#updateId").val(),
					code:$("#updateCode").val(),
					name:$("#updateName").val(),
					descr:$("#updateDescr").val()},
				url:"<%=request.getContextPath()%>/manageDepartment/updateManageDepartment.action",
				success:function(data){
					if(data == 'SUCCESS'){
						initPage();
						artDialog.close().remove();
						dialog.alert("修改成功");
					}else{
						dialog.alert("修改失败");
					}
				}
			});
		//	return true;
		//} else {
		//	return false;
			//dialog.alert("表单验证不通过");
		//}
	//});
}

var artDialog;
//导入学员
function importFile(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/importFile.action",
        title:"导入学员" ,
		height: 250,
		width: 450
		}).showModal();
}

function importDeptFile(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/manageUser/importDeptFile.action",
        title:"导入部门" ,
		height: 250,
		width: 450
		}).showModal();
}

/**
 * 表单验证
 */
function initAddFormValidate() {
	$('#addForm').validator({
		rules : {
			checkCode:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{code:element.value},
					url:"<%=request.getContextPath()%>/manageDepartment/selectManageDept.action",
					success:function(data){
						var flag = true;
						if(data.length > 0){
							flag = false;
						}
						str =  flag || "已存在相同编码";
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
				rule : "required;length[~30];",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			addCode : {
				rule : "required;length[~30];checkCode;",
				msg : {
					required : "编码不能为空",
					length : "长度需小于等于30个字符"
				}
			}
		}
	});
}

function initUpdateFormValidate() {
	$('#updateForm').validator({
		rules : {
			checkCode:function(element,param,field){
				var str;
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{code:element.value},
					url:"<%=request.getContextPath()%>/manageDepartment/selectManageDept.action",
					success:function(data){
						var flag = true;
						if(data.length > 0 && data[0].id != $("#updateId").val()){
							flag = false;
						}
						str =  flag || "已存在相同编码";
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
				rule : "required;length[~30]",
				msg : {
					required : "名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			updateCode : {
				rule : "required;length[~30];checkCode;",
				msg : {
					required : "编码不能为空",
					length : "长度需小于等于30个字符"
				}
			}
		}
	});
}

function toUpdateDeptDiv(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认批量修改部门吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		if(initUserId != val.id){
		    		param.push(val.id);
	    		}
	    	});
	    	
	    	if(param.length == 0){
	    		dialog.alert("集团管理员不能修改。");
	    		return;
	    	}
			artDialog = dialog({ 
		        title: "批量修改部门",
		        content: $("#tree2Div"),
		        width: 300,
		        height:300,
				 button: [
				          {
			              value: '确定',
			              callback: function () {
			            	  updateDeptRows();
			              }
				          },{
				              value: '取消',
				              callback: function () {
				            	 this.close();
				              }
				          }
				      ]
		    }).showModal();
		})
	}else{
		dialog.alert('请先选择数据！');
	}
}

function updateDeptRows(){
	 /*if(selectRows.length > 0){
		dialog.confirm("确认批量修改部门吗?",function(obj){
			var param = [];
	    	$.each(selectRows, function(index, val){
	    		if(initUserId != val.id){
		    		param.push(val.id);
	    		}
	    	});
	    	
	    	if(param.length == 0){
	    		dialog.alert("集团管理员不能修改。");
	    		return;
	    	} */
	    	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	    	var param = [];
	    	$.each(selectRows, function(index, val){
	    		if(initUserId != val.id){
		    		param.push(val.id);
	    		}
	    	});
	    	
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			deptId : deptId,
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/manageUser/updateManageUserRows.action",
	    		success:function(data){
	    			if(data == 'SUCCESS'){
		   				search();
		   				dialog.alert("修改成功。");
					}else{
						dialog.alert("修改失败。");
					}
	    	    }
	    	});
		/* })
	}else{
		dialog.alert('请先选择数据！');
	} */
}

function exportStudent(){
	$("#deptIds").val(deptIds);
	$("#form_").submit();
}

</script>

</head>


<body>

<div class="content">
	<!-- <h3>学员账号管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">学员账号管理</span>
	</div>
	<div class="btn_gr">
    	<input type="button" class="btn_1" value="新增" onclick="insertStudent()"/>
        <input type="button" class="btn_2" value="批量导入" onclick="importFile()"/>
      <!--  <a class="btn_1" href="/upload/template/userTemplate.xls">下载模板</a> -->
       <input type="button" class="btn_1" value="下载模板" onclick="downloadTemplate()"/>
        <input type="button" class="btn_1" value="批量删除" onclick="deleteRows()"/>
        <input type="button" class="btn_1" value="批量修改学员部门" onclick="toUpdateDeptDiv()"/>
        <input type="button" class="btn_2" value="批量导入部门" onclick="importDeptFile()"/>
        <input type="button" class="btn_1" value="下载部门模板" onclick="downloadDeptTemplate()" style="margin-right:0px;"/>
    </div>
    <!-- <div class="btn_gr">
    	<input type="button" class="btn_1" value="导出" onclick="exportStudent()"/>
    </div> -->
    <form id="form" action="<%=request.getContextPath()%>/teacher/downloadTemplate.action" method="post">
    	<input type="hidden" value="template/导入学员模板.xls" name="path" id="path"/>
 	    <input type="hidden" value="导入学员模板.xls" name="fileName" id="fileName"/>
    </form>
        <div class="company_tree" id="tree" >
		      <ul id="treePage" class="ztree" ></ul>
        </div>
         <div class="right_lesson">
         <form id="form_" action="<%=request.getContextPath()%>/manageUser/exportStudent.action" method="post">
        <div class="search_3">
        	<p>
        	<input type="hidden" id="deptIds" name="deptIds"/>
           	用户名：<input type="text" id="userName" name="userName"/>
	                    姓名：<input type="text" class="input_1" id="name" name="name"/>
	                      角色：<input type="text" class="input_1" id="roleName" name="roleName"/>
	                    状态：<select id="status" name="status">
                 	<option value="">全部</option>
                 	<option value="1" selected>正常</option>
                 	<option value="2">冻结</option>
                </select>
                </p>
	      	<!-- <input type="button" value="查询" class="btn_3" style="width:68px;" onclick="search_()"/> -->
	        </div>
	      <div class="search_3">
        	<p>
	                    是否有照片：<select id="photo" name="photo">
                 	<option value="">全部</option>
                 	<option value="1">是</option>
                 	<option value="2">否</option>
                </select>
                </p>
	      	<input type="button" value="查询" class="btn_3" style="width:68px;" onclick="search_()"/>
	        </div>
	        </form>
	    <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>   	
       </div>     
   </div>
   
   <div style="display:none;width:300px;" id="add">
		<form id="addForm">
				<div>	
				<table>
					<tr height="40px;">
						<td><span style="color:red">*</span>编码:</td>
						<td><input type="text" style="width:200px;height:30px;" id="addCode" name="addCode"/></td>
					</tr>
					<tr height="40px;">
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
						<tr height="40px;">
							<td><span style="color:red">*</span>编码:</td>
							<td>
								<input type="text" style="width:200px;height:30px;" id="updateCode" name="updateCode"/>
				                <input type="hidden" id="updateId"/>
			                </td>
						</tr>
						<tr height="40px;">
							<td><span style="color:red">*</span>名称:</td>
							<td>
								<input type="text" style="width:200px;height:30px;" id="updateName" name="updateName"/>
				                <input type="hidden" id="updateId"/>
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
	
	<div style="display:none;width:300px;" id="tree2Div">
		<div class="company_tree2" id="tree2" >
		      <ul id="treePage2" class="ztree" ></ul>
        </div>
	</div>
</body>
</html>
