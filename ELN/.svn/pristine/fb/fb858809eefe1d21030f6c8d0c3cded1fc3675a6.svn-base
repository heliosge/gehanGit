<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
</style>

<script>
var isSub = ${isSub};
var userId = ${USER_ID_LONG};

var contextMenu ;
$(function(){
	initMenu();
	
	initPage();
	
	initAllSubCompany();
	
	//initSmartMenu();
	
	initAddFormValidate();
	
	initUpdateFormValidate();
	
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
        indexCol: true,  //索引列
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               /* {title: '课程编号', name: 'code', width:60, align:'center'}, */
               {title: '公司名称', name: 'companyName', width:60, align:'center'},
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
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(userId == item.createUserId){
						   if(val == 1){
							   buttonStr += '<a href="javascript:void(0)" onclick="release('+item.id+','+item.type+')" >发布</a>  ';
						   }else if(val == 2){
							  /*  if(item.shareStatus == 1 || item.shareStatus == 3 || item.shareStatus == 4 || item.shareStatus == 6 ){
								   buttonStr += '<a href="#" onclick="share('+item.id+','+item.shareStatus+')" >共享</a>  ';
							   } */
							   buttonStr += '<a href="javascript:void(0)" onclick="cancelRelease('+item.id+')" >取消发布</a>  ';
							   if(item.isFeatured == 1){
								   buttonStr += '<a href="javascript:void(0)" onclick="featureAndUnFeature('+item.id+',2)" >选为精选</a>  ';
							   }else{
								   buttonStr += '<a href="javascript:void(0)" onclick="featureAndUnFeature('+item.id+',1)" >取消精选</a>  ';
							   }
						   }
						   buttonStr += '<a href="toUpdateResCoursePage.action?id='+item.id+'" >修改</a>';
						   //buttonStr += '<a href="toUpdateResCoursePage.action?id='+item.id+'" >修改</a>  <a href="#" onclick="deleteOne('+item.id+')" >删除</a>';
						   return "<div class='span_btus' >" + buttonStr + "</div>";
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
})

function initAllSubCompany(){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:param,
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			for(var i = 0; i < data.data.length; i++){
				if(data.data[i].isSubCompany == 1){
					$("#subCompany").append('<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>');
				}
			}
		}
	});
}

function initMenu(){
	contextMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
	    [
	     { id:'zTreeAddNode',text: '新增体系', click: add },
	     { id: 'zTreeModifyNode', text: '修改体系', click: modify },
	     { id: 'zTreeDeleteNode', text: '删除体系', click: del },
	     ]
	     });
}

function featureAndUnFeature(id, isFeatured){
	var param = {};
	param.id=id;
	param.isFeatured=isFeatured;
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:param,
		url:"<%=request.getContextPath()%>/res/featurAndUnFeaturResCourse.action",
		success:function(data){
			if(data == 'SUCCESS'){
				search();
				dialog.alert("操作成功。");
			}else{
				dialog.alert("操作失败。");
			}
		}
	});
}


function param(){
	var o = {};
	o.likeName = escapeForSql($("#name").val());
	//o.code = $("#code").val();
	o.type = $("#type").val();
	o.categoryId = categoryIds;
	o.mallStatus = 1;
	o.subCompanyId = $("#subCompany").val();
	return o;
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}


function initPage(){
	categoryId = undefined;
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
				//onRightClick: zTreeOnRightClick,
				onRightClick: zTreeOnRightClick,
				onClick: zTreeOnClick
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
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

function release(id, type){
	var flag = true;
	//根据课程id获取课程章节
	if(type == 1){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{courseId:id},
			url:"<%=request.getContextPath()%>/res/selectSectionByCourseId.action",
			success:function(data){
				if(data.length > 0){
				}else{
					flag = false;
				}
			}
		});
	}
	if(flag){
		$.ajax({
			type:"POST",
			async:false,  //默认true,异步
			data:{
				id:id
			},
			url:"<%=request.getContextPath()%>/res/releaseResCourse.action",
			success:function(data){
				if(data == 'SUCCESS'){
					search();
					dialog.alert("发布成功。");
				}else{
					dialog.alert("发布失败。");
				}
			}
		});
	}else{
		dialog.alert("课程没有章节，不能发布。");
	}
}

/* 取消发布 */
function cancelRelease(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			id:id
		},
		url:"<%=request.getContextPath()%>/res/cancelReleaseResCourse.action",
		success:function(data){
			if(data == 'SUCCESS'){
				search();
				dialog.alert("取消发布成功。");
			}else{
				dialog.alert("取消发布失败。");
			}
		}
	});
}

function deleteOne(id){
	dialog.confirm("确认删除吗?",function(obj){
		var param = [];
	   	param.push(id);
	   	$.ajax({
	   		type:"POST",
	   		async:true,  //默认true,异步
	   		data:{
	   			ids : param
	   		},
	   		url:"<%=request.getContextPath()%>/res/deleteResCourse.action",
	   		success:function(data){
	   			if(data == 'SUCCESS'){
	   				search();
		   			dialog.alert("删除成功。");
				}else{
					dialog.alert("删除失败。");
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
	    		url:"<%=request.getContextPath()%>/res/deleteResCourse.action",
	    		success:function(data){
	    			if(data == 'SUCCESS'){
	    				search();
		    			dialog.alert("删除成功。");
					}else{
						dialog.alert("删除失败。");
					}
	    	    }
	    	});
		});
	}else{
		dialog.alert('请先选择数据！');
	}
}


var ids = [];
function changeCategory(){
	ids = [];
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
    	$.each(selectRows, function(index, val){
    		ids.push(val.id);
    	});
    	artDialog = dialog({
	        url:"<%=request.getContextPath()%>/res/toChangeCategory.action",
	        title:"选择课程体系" ,
			height: 500,
			width: 400
			}).showModal();
	}else{
		dialog.alert("请选择课程。");
	}
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function search_(){
	$('#exampleTable').mmGrid().load({"page":1});
}

function toInsertCourse(){
	window.location.href="<%=request.getContextPath()%>/res/toInsertResCoursePage.action";
}

var categoryIds = [];
var categoryId;
var node;
function zTreeOnClick(event, treeId, treeNode) {
	categoryId = treeNode.id;
	node = treeNode;
	categoryIds = [];
	getChildNodes(treeNode);
	search();
};

function zTreeOnRightClick(event, treeId, treeNode) {
	if(isSub == 1){
		return;
	}
    if (treeNode && !treeNode.noR) {
    	categoryId = treeNode.id;
    	node = treeNode;
    	categoryIds = [];
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
	categoryIds.push(treeNode.id); 
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
}

function getChildNodesNotAddThis(treeNode){
	if(treeNode.children == undefined){
	}else{
		for(var i = 0; i < treeNode.children.length; i++){
			categoryIds.push(treeNode.children[i].id); 
			getChildNodesNotAddThis(treeNode.children[i]);
		}
	}
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

function add(){
	$(".n-yellow .msg-wrap").hide();
	$("#addName").val('');
	$("#addDescr").val('');
	artDialog = dialog({ 
        title: "新增课程体系",
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

var artDialog;

function modify(){
	if(categoryId == null){
		dialog.alert("不能修改根节点");
		return;
	}
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{categoryId:categoryId},
		url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
		success:function(data){
			$(".n-yellow .msg-wrap").hide();
			$("#updateId").val(data[0].id);
			$("#updateName").val(data[0].name);
			$("#updateDescr").val(data[0].descr);
			$("#updateParentId").val(data[0].parentId)
			artDialog = dialog({ 
		        title: "修改课程体系",
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
	if(node.children != undefined){
		dialog.alert("不能删除父体系。");
		return;
	}
	if(categoryId == undefined){
		dialog.alert("请选择一个节点。");
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
		url:"<%=request.getContextPath()%>/res/deleteResCourseCategory.action",
		success:function(data){
			if(data == 'SUCCESS'){
				initPage();
				dialog.alert("删除成功");
			}else{
				dialog.alert("删除失败");
			}
			
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
				url:"<%=request.getContextPath()%>/res/insertResCourseCategory.action",
				success:function(data){
					if(data == 'SUCCESS'){
						initPage();
						artDialog.close().remove();
						dialog.alert("新增成功");
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

function updateCategory(){
	//$('#updateForm').isValid(function(v) {
	//	if(v){
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{
					id:$("#updateId").val(),
					name:$("#updateName").val(),
					descr:$("#updateDescr").val()},
				url:"<%=request.getContextPath()%>/res/updateResCourseCategory.action",
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
					data:{parentId:categoryId,
						  name:$("#addName").val()},
					url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
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
					url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
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
					required : "体系名称不能为空",
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
	<!-- <h3>课程管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程管理</span>
	 </div>
   	<div class="course_tree" id="tree">
   		<h2>课程体系</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson">
    	<div class="lesson_tab">
        	<ul>
            	<li class="li_this">企业课程</li>
            	 <li><a href="<%=request.getContextPath()%>/res/toBuyResCourseListPage.action" style="color:black;">购买的课程</a></li>
                <%-- <li><a href="<%=request.getContextPath()%>/res/toResCloundCourseListPage.action" style="color:black;">云平台课程</a></li>
                <c:choose>
	              	<c:when test="${USER_BEAN.companyId != 1 }">
                		<li><a href="<%=request.getContextPath()%>/res/toResShareCourseListPage.action" style="color:black;">共享记录</a></li>
		            </c:when>
              	</c:choose> --%>
            </ul>
        </div>
        <div class="button_gr">
        	<input type="button" value="新增课程" class="btn_4" onClick="toInsertCourse()"/>
            <input type="button" value="批量删除" onclick="deleteRows()"/>
            <input type="button" value="更改课程体系" onclick="changeCategory()"/>
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
                    <option value="2">线下课程</option>
                </select>
                	公司名称：
                <select id="subCompany">
                    <option value="">全部</option>
                </select>
               
        	</p>
        	<input type="button" value="查询" class="btn_cx" onclick="search_()"/>

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
