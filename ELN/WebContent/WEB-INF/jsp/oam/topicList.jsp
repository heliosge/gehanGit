<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专题管理</title>
</head>
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
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- 右键菜单 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/smartMenu/smartMenu.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/smartMenu/smartMenu-min.js"></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}

	.recUserStyle{
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		font-weight:normal;
	}
</style>

<script src="<%=request.getContextPath()%>/js/webhr.js"></script>
<body>

<script type="text/javascript">
$(function(){
	initPage();
	
	initDatepicker();
	
	initSmartMenu();
	
	initAddFormValidate();
	
	initUpdateFormValidate();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/oam/selectOamTopicList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        //indexCol: true,  //索引列
        params: function(){
	    	return toParam();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '专题编号', name: 'no', width:60, align:'center'},
 			   {title: '专题名称', name: 'name', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return "<a href='toOamTopicDetailPage.action?id="+item.id+"' >"+val+"</a>";
				   }},
			   {title: '创建时间', name: 'createTime', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return getSmpFormatDateByLong(val, false);
			   }},
			   {title: '是否推广', name: 'isSpread', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "推广";
				   }else{
					   return "不推广";
				   }
			   }},
			   {title: '企业类型', name: 'industryList', width:120, align:'center', renderer:function(val, item, rowIndex){
				   var s = '';
				   $.each(val,function(index,industry){
					   if(index == val.length-1){
						   s += industry.name;
					   }else{
						   s += industry.name+",";
					   }
				   })
				   return '<h4 class="recUserStyle" title="'+s+'">'+s+'</h4>';;
			   }},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   var buttonStr = '';
				   if(item.isSpread == 1){
					   buttonStr += '<a href="#" onclick="cancelSpread('+val+')" >取消推广</a>  ';
				   }else{
					   buttonStr += '<a href="#" onclick="spread('+val+')" >推广</a>  ';
				   }
				   buttonStr += '<a href="toUpdateOamTopicPage.action?id='+val+'" >修改</a>  <a href="#" onclick="deleteRowOne('+val+')" >删除</a>  ';
				   
				   return "<div class='span_btus' >" + buttonStr + "</div>";
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

function deleteRowOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		var ids = [];
		ids.push(id);
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{
				ids:ids
			},
			url:"<%=request.getContextPath()%>/oam/deleteOamTopic.action",
			success:function(data){
				if(data == 'SUCCESS'){
					query();
					dialog.alert("删除成功");
				}else{
					dialog.alert("删除失败");
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
	    		param.push(val.id);
	    	});
	    	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:{
	    			ids : param
	    		},
	    		url:"<%=request.getContextPath()%>/oam/deleteOamTopic.action",
	    		success:function(data){
	    			if(data == 'SUCCESS'){
						query();
						dialog.alert("删除成功");
					}else{
						dialog.alert("删除失败");
					}
	    	    }
	    	});
		});
	}else{
		dialog.alert('请先选择数据！');
	}
}

function spread(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			id:id
		},
		url:"<%=request.getContextPath()%>/oam/spreadOamTopic.action",
		success:function(data){
			if(data == 'SUCCESS'){
				query();
				dialog.alert("推广成功");
			}else{
				dialog.alert("推广失败");
			}
		}
	});
}

function cancelSpread(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			id:id
		},
		url:"<%=request.getContextPath()%>/oam/cancelSpreadOamTopic.action",
		success:function(data){
			if(data == 'SUCCESS'){
				query();
				dialog.alert("取消推广成功");
			}else{
				dialog.alert("取消推广失败");
			}
		}
	});
}

function reset(){
	$("#no").val("");
	$("#name").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	$("#isSpread").val("");
	query();
}

function toParam(){
	var o = {};
	o.no = $("#no").val();
	o.name = $("#name").val();
	o.startTime = $("#startTime").val();
	o.endTime = $("#endTime").val();
	o.isSpread = $("#isSpread").val();
	o.categoryIdList = categoryIdList;
	o.industryName = $("#industryName").val();
	return o;
}

function query(){
	$('#exampleTable').mmGrid().load({"page":1});
}


function initDatepicker() {
	$("#startTime").datepicker({
		dateFormat : 'yy-mm-dd',
 		  changeMonth: true,
 	      changeYear: true
	});

	$("#endTime").datepicker({
		dateFormat : 'yy-mm-dd',
		  changeMonth: true,
	      changeYear: true
	});
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
		url:"<%=request.getContextPath()%>/oam/selectOamTopicCategory.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

var categoryIdList = [];
var categoryId;
var node;
function zTreeOnClick(event, treeId, treeNode) {
	categoryId = treeNode.id;
	node = treeNode;
	categoryIdList = [];
	getChildNodes(treeNode);
	query();
};

function zTreeOnMouseUp(event, treeId, treeNode){
	if(treeNode != null){
		zTreeOnClick(event, treeId, treeNode);
		$.fn.zTree.getZTreeObj("treePage").selectNode(treeNode);
	}
}

function getChildNodes(treeNode){
	categoryIdList.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIdList.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIdList.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function toInsertTopicPage(){
	window.location.href="<%=request.getContextPath()%>/oam/toInsertOamTopicPage.action";
}

function initSmartMenu(){
	$("#tree").smartMenu( [[{
        text: "新增体系",
        func: add
    }, {
        text: "修改体系",
        func: modify
    }, {
        text: "删除体系",
        func:del
    }]]);
}

var dialog;

function add(){
	$("#addName").val('');
	$("#addDescr").val('');
	hideValidate();
	artDialog = dialog({ 
        title: "新增专题体系",
        content: $("#add"),
        width: 450,
        height:300,
		 button: [
		          {
	              value: '确定',
	              callback: function () {
	            	  $('#addForm').isValid(function(v) {
	            			if(v){
	            				addCategory();
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

function hideValidate(){
	$(".n-yellow .msg-wrap").hide();
}

var artDialog;

function modify(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{categoryId:categoryId},
		url:"<%=request.getContextPath()%>/oam/selectOamTopicCategory.action",
		success:function(data){
			hideValidate();
			$("#updateId").val(data[0].id);
			$("#updateName").val(data[0].name);
			$("#updateDescr").val(data[0].descr);
			$("#updateParentId").val(data[0].parentId);
			artDialog = dialog({ 
		        title: "修改专题体系",
		        content: $("#modify"),
		        width: 450,
		        height:300,
				 button: [
				          {
			              value: '确定',
			              callback: function () {
			            	  $('#updateForm').isValid(function(v) {
			            			if(v){
			            				updateCategory();
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
	if(categoryId == undefined){
		dialog.alert("请选择一个节点。");
		return;
	}
	if(node.children != undefined){
		dialog.alert("不能删除父体系。");
		return;
	}
	var rows = $('#exampleTable').mmGrid().rows();
	if(rows[0] != undefined){
		dialog.alert("不能删除该体系。");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{id:categoryId},
		url:"<%=request.getContextPath()%>/oam/deleteOamTopicCategory.action",
		success:function(data){
			initPage();
			dialog.alert("删除成功");
		}
	});
	categoryId='';
}


function addCategory(){
	//$('#addForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{parentId:categoryId,
					name:$("#addName").val(),
					descr:$("#addDescr").val()},
				url:"<%=request.getContextPath()%>/oam/insertOamTopicCategory.action",
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
		//} else {
			//dialog.alert("表单验证不通过");
		//}
	//});
}

function updateCategory(){
	$(".n-yellow .msg-wrap").hide();
	//$('#updateForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{
					id:$("#updateId").val(),
					name:$("#updateName").val(),
					descr:$("#updateDescr").val()},
				url:"<%=request.getContextPath()%>/oam/updateOamTopicCategory.action",
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
		//} else {
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
					data:{parentId:categoryId,
						  name:$("#addName").val()},
					url:"<%=request.getContextPath()%>/oam/selectOamTopicCategory.action",
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
					required : "体系名称不能为空",
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
				    url:"<%=request.getContextPath()%>/oam/selectOamTopicCategory.action",
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
				rule : "length[~51]",
				msg : {
					length : "长度需小于等于50个字符"
				}
			},
			updateName : {
				rule : "required;length[~11];checkSameName;",
				msg : {
					required : "体系名称不能为空",
					length : "长度需小于等于10个字符"
				}
			}
		}
	});
}

</script>

<div class="content">
	<!-- <h3>专题管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">专题管理</span>
	 </div>
    <div class="lesson_tab" style="width:1066px;float: left;margin-bottom: 10px;">
        	<ul>
            	<li class="li_this">推广专题管理</li>
                <li> <a href="<%=request.getContextPath()%>/oam/toOamSpreadCourseListPage.action" style="color:black;">推广课程管理</a></li>
                <li> <a href="<%=request.getContextPath()%>/oam/toOamSpreadGameListPage.action" style="color:black;">推广大赛管理</a></li>
            </ul>
	</div>
    <div class="content_2">
        <div class="tree"  id="tree">
            <h2>专题分类</h2>
       		<ul id="treePage" class="ztree" style=""></ul>
        </div>
        <div class="right_lesson">
            <form id="search" name="search" method="post" action="">
	            <div class="button_gr">
	                <input type="button" value="新增专题" class="btn_4" onclick="toInsertTopicPage()"/>
	                <input type="button" value="批量删除" onclick="deleteRows()"/>
	            </div>
	            <div class="search_3">
	                <p>	
	                   	 专题编号：
	                    <input type="text" name="no" id="no"/>
	                   	  专题名称：
	                    <input type="text" name="name" id="name"/>
	                                                              企业类型：
	                    <input type="text" name="industryName" id="industryName"/>
	                   
	                </p>
	    
	            </div>
            </form>
            <div class="search_3" style="margin-top:-1px;">
                <p>	
                   	 创建时间：
                    <input type="text" name="startTime" id="startTime"/>
                    <span>至</span>
                    <input type="text" name="endTime" id="endTime"/>
                  	  是否推广：
                    <select name="isSpread" id="isSpread">
                        <option value="">显示全部</option>
                        <option value="1">推广</option>
                        <option value="2">不推广</option>
                    </select>
                   
                </p>
                <input type="button" value="重置"  onclick="reset()"/>
                <input type="button" value="查询" class="btn_cx" onclick="query()"/>
    
            </div>
            
            <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
			<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        </div>
   </div>
   </div>
   
    <div style="display:none;width:300px;" id="add">
 	<form id="addForm">
				<div>	
				<table>
					<tr height="50px;">
						<td><span style="color:red">*</span>体系名称:</td>
						<td><input type="text" style="width:200px;height:30px;" id="addName" name="addName"/></td>
					</tr>
					<tr>
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
							<td><span style="color:red">*</span>体系名称:</td>
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
