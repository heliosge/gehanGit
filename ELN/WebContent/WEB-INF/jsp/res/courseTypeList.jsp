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
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- 右键菜单 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/smartMenu/smartMenu.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartMenu/smartMenu-min.js"></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
</style>

<script>
$(function(){
	initPage();
	
	initMenu();
	
	//initSmartMenu();
	
	initUpdateFormValidate();
	
	initAddFormValidate();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/selectResCourseList.action",
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
               /* {title: '课程编号', name: 'code', width:60, align:'center'}, */
               {title: '课程名称', name: 'name', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+item.id+'" >'+val+'</a>';
			   }},
 			   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "线下课程";
				   }
				}},
 			   {title: '课程体系', name: 'categoryName', width:60, align:'center'},
 			   {title: '课程分类', name: 'typeName', width:60, align:'center'},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="javascript:void(0);" onclick="changeOneType('+item.id+')" >分配分类</a>  ';
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
})

var contextMenu;
function initMenu(){
	contextMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
	    [
	     { id:'zTreeAddNode',text: '新增分类', click: add },
	     { id: 'zTreeModifyNode', text: '修改分类', click: modify },
	     { id: 'zTreeDeleteNode', text: '删除分类', click: del },
	     ]
	     });
}

function param(){
	var o = {};
	o.likeName = escapeForSql($("#name").val());
	//o.code = $("#code").val();
	o.type = $("#type").val();
	o.typeId = typeIds;
	return o;
}

function reset(){
	$("#name").val("");
	//$("#code").val("");
	$("#type").val("");
	typeIds=[];
	search();
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}


function initPage(){
	typeIds = [];
	typeId = '';
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId"}
			},
			check: {enable:  false},
			view: {
				showLine: false,
				showIcon: true
			},
			callback: {
				onRightClick: zTreeOnRightClick,
				onClick: zTreeOnClick
			}
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


var ids = [];
var artDialog;
function changeType(){
	ids = [];
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
    	$.each(selectRows, function(index, val){
    		ids.push(val.id);
    	});
    	artDialog = dialog({
	        url:"<%=request.getContextPath()%>/res/toChangeType.action",
	        title:"选择课程分类" ,
			height: 500,
			width: 400
			}).showModal();
	}else{
		dialog.alert("请选择课程。");
	}
}

var artDialog;
function changeOneType(id){
	ids = [];
	ids.push(id);
	artDialog = dialog({
		url:"<%=request.getContextPath()%>/res/toChangeType.action",
        title:"选择课程分类" ,
        lock:true,
        height: 500,
		width: 400
	}).showModal();
}

function search(){
	$('#exampleTable').mmGrid().load();
}


var typeIds = [];
var typeId = '';
var node;
function zTreeOnClick(event, treeId, treeNode) {
	node = treeNode;
	typeId = treeNode.id;
	typeIds = [];
	if(typeId != null){
		getChildNodes(treeNode);
	}
	search();
};

function zTreeOnRightClick(event, treeId, treeNode) {
    if (treeNode && !treeNode.noR) {
    	typeId = treeNode.id;
    	node = treeNode;
    	typeIds = [];
    	getChildNodes(treeNode);
    	search();
    	$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
        contextMenu.show({top: event.pageY, left: event.pageX});
    }else{
    	$.fn.zTree.getZTreeObj("treePage").cancelSelectedNode();
    	contextMenu.hide();
    	//contextMenu.show({top: event.pageY, left: event.pageX});
    }
}

function getChildNodes(treeNode){
	typeIds.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			typeIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			typeIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
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
		title: "新增课程分类",
		content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				addType();
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
	if(typeId == null){
		dialog.alert("不能修改根节点");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{typeId:typeId},
		url:"<%=request.getContextPath()%>/res/selectResCourseType.action",
		success:function(data){
			$(".n-yellow .msg-wrap").hide();
			$("#updateId").val(data[0].id);
			$("#updateName").val(data[0].name);
			$("#updateDescr").val(data[0].descr);
			$("#updateParentId").val(data[0].parentId);
			artDialog = dialog({
				title: "修改课程分类",
				content: $("#modify"),
		        width: 450,
		        height:300,
				 button: [
				          {
			              value: '确定',
			              callback: function () {
			            	  $('#updateForm').isValid(function(v) {
			            			if(v){
			            				updateType();
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
	if(typeId == ''){
		dialog.alert("请选择一个节点。");
		return;
	}
	if(node.children != undefined){
		dialog.alert("不能删除父分类。");
		return;
	}
	var rows = $('#exampleTable').mmGrid().rows();
	if(rows[0] != undefined){
		dialog.alert("不能删除该分类。");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:typeId},
		url:"<%=request.getContextPath()%>/res/deleteResCourseType.action",
		success:function(data){
			initPage();
			dialog.alert("删除成功");
		}
	});
	typeId = '';
	}

function addType(){
	//$('#addForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{parentId:typeId,
					name:$("#addName").val(),
					descr:$("#addDescr").val()},
				url:"<%=request.getContextPath()%>/res/insertResCourseType.action",
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
	//	} else {
	//		return false;
			//dialog.alert("表单验证不通过");
	//	}
	//});
	
}

function updateType(){
	//$('#updateForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{
					id:$("#updateId").val(),
					name:$("#updateName").val(),
					descr:$("#updateDescr").val()},
				url:"<%=request.getContextPath()%>/res/updateResCourseType.action",
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
	//	} else {
	//		return false;
			//dialog.alert("表单验证不通过");
	//	}
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
					data:{parentId:typeId,
						  name:$("#addName").val()},
					url:"<%=request.getContextPath()%>/res/selectResCourseType.action",
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
				rule : "length[~50]",
				msg : {
					length : "长度需小于等于50个字符"
				}
			},
			addName : {
				rule : "required;length[~10];checkName;",
				msg : {
					required : "分类名称不能为空",
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
				    url:"<%=request.getContextPath()%>/res/selectResCourseType.action",
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
					required : "分类名称不能为空",
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
	<!-- <h3>课程分类</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程分类</span>
	 </div>
   	<div class="course_tree" id="tree">
   		<h2>课程分类</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson">
        <div class="button_gr">
            <input type="button" value="更改课程分类" onclick="changeType()"/>
        </div>
        <div class="search_3">
        	<p>	
            	<!-- 课程编码：
                <input type="text" id="code"/> -->
            	 课程名称：
                <input type="text" id="name"/>
            	课程类型：
                <select id="type">
                    <option value="">全部</option>
                    <option value="1">线上课程</option>
                    <option value="2">直播课程</option>
                </select>
               
        	</p>
        	<input type="button" value="查询" class="btn_cx" onclick="search()"/>
			<input type="button" value="重置" class="btn_3" onclick="reset()"/>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        
    </div>
</div>
 <div style="display:none;width:300px;" id="add">
			<form id="addForm">	
				<div>	
				<table>
					<tr height="50px;">
						<td><span style="color:red">*</span>分类名称:</td>
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
							<td><span style="color:red">*</span>分类名称:</td>
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

</body>
</html>
