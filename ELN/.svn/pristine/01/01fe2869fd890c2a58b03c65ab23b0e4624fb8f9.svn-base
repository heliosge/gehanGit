<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>学习计划添加人员</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<style type="text/css">
    .ztree li span.button.noline_docu{width:0px;}
    .ztree li span.button.noline_open{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
    .ztree li span.button.noline_close{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
    .tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 450px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
    .tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<style type="text/css">

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}
</style>
<style type="text/css">
	.ztree{overflow: auto;height: 520px;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: left;}
	.company_tree {width: 250px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
    .button{
        width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; margin-top: 10px;
    }
</style>

<script type="text/javascript">

var planId = ${planId};
$(function(){


    initPostTree();

    	//表格
    	$('#exampleTable').mmGrid({
        	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    		url:"<%=request.getContextPath()%>/learnPlan/getLearnPlanAssignmentList.action",
        	width: 'auto',
        	height: 'auto',
        	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
        	showBackboard: false,
            checkCol: true,
            multiSelect: true,
            indexCol: true,  //索引列
            params:function(){
            	var param = new Object();
            	
            	param.userName = $.trim($("#userName").val());
            	param.planId=planId;
            	
            	return param;
            },
            cols: [{title: '姓名', name: 'name', width:30},
    			   {title: '用户名', name: 'userName', width:50},
    			   {title: '部门', name: 'depName', width:50},
    			   {title: '岗位', name: 'postName', width:40},
    			   {title: '操作', name: 'id', width:60, renderer:function(val, item, rowIndex){
    				   var buttonStr = "<a href='javascript:void(0);' onclick='deleteRowOne("+val+")'>删除</a>";
    				   
    				   return buttonStr ;
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
    
//上一步
function goLast(){
		window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanStageEdit.action?planId="+planId;
	
}
//返回
function goBack(){
	dialog.confirm('确认返回吗？', function(){ 
		
	    	window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
	    
	});
}

//初始化人员库部门树
function initUserTree(){

	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: userTreeOnClick
			}
	};
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
            addIconInfo(data.data,1);
			$.fn.zTree.init($("#popUserTreePage"), setting, data.data);
			//如果是集团公司删除子公司的部门
			var treeObj = $.fn.zTree.getZTreeObj("popUserTreePage");
//			for(var i = 0; i < data.subData.length; i++){
//				var node = treeObj.getNodeByParam("id", data.subData[i], null);
//				treeObj.removeNode(node, false);
//			}
            $.fn.zTree.getZTreeObj("popUserTreePage").expandAll(true);
		}
	});

}
var deptIds=[];
function addIconInfo(data,flag) {
    for (var idx in data) {
        if(flag){
            data[idx].id.indexOf("com") != 0 && deptIds.push(data[idx].id);
        }
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function userTreeOnClick(event, treeId, treeNode) {
	deptIds = [];
	if(treeNode.id.indexOf("com") == 0){
		deptIds = [];
	}else{
		getChildNodes(treeNode);
	}
	searchUser();
};

function getChildNodes(treeNode){
	deptIds.push(treeNode.id); 
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

//初始化人员库表
function initUserGrid() {
	// 封装参数
	var usermmGrid=$('#popUserTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/selectStudentList.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	return  toParam();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '姓名', name: 'name', width:60, align:'center'},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
 			   {title: '电子邮箱', name: 'deptName', width:60, align:'center'},
			   {title: '联系电话', name: 'mobile', width:60, align:'center'},
			   {title: '岗位', name: 'postName', width:60, align:'center'},
 			   {title: '部门', name: 'deptName', width:60, align:'center'},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }
				}}
           ],
           plugins : [
                  	$('#paginatorPaging2').mmPaginator({
                  		totalCountName: 'total',    //对应MMGridPageVoBean count
                  		page: 1,                    //初始页
                  		pageParamName: 'page',      //当前页数
                  		limitParamName: 'pageSize', //每页数量
                  		limitList: [10, 20, 40, 50]
                  	})
                  ]
    });
//    usermmGrid.on("loadSuccess",function(e, data){
//        if(window.parent.stuGridRows[0] != undefined){
//            $.each(window.parent.stuGridRows,function(index, val){
//                $.each($('#exampleTable').mmGrid().rows(), function(index_, val_){
//                    if(val.id == val_.id){
//                        mmGrid.select(index_);
//                    }
//                })
//            })
//        }
//    });
}
function toParam(){
	var o = {};
	o.userName = $("#popUserName").val();
	o.name = $("#popName").val();
	o.deptIds = deptIds;
	o.status = 1;
	return o;
}
function searchUser(){
	$('#popUserTable').mmGrid().load();
}


//从人员库中选择~
function selectUser(){
	var selectPeople = $("#selectPeople");
	initUserTree();
	initUserGrid();

	dialog(
			{
				title : "选择人员",
				width : 980,
				height : 350,
				locked:true,
				content : selectPeople,
				okValue : '确定',
				ok : function() {
					var rows = $('#popUserTable').mmGrid().selectedRows();
					if(rows.length==0){
						dialog.alert("请选择数据！");
						return;
					}
					var idArr =[];
					var ids={};
					$.each(rows, function(index, val){
						idArr.push(val.id);
					});
					ids=idArr.join(',');
					addUser(ids);
				},
				cancelValue : '取消',
				cancel : function() {
				}
			}).showModal();
	}
	
function addUser(ids){
	var param={};
	param.planId=planId;
	param.ids=ids;
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:param,
		url:"<%=request.getContextPath()%>/learnPlan/addLearnPlanUser.action",
		success:function(data){
			
			
			 if(data =="SUCCESS"){
				 dialog.alert('添加成功！');
 				search();
 			}else{
 				dialog.alert('添加失败！');
 			}
	    }
	});
	
	
	
}

/*function initGroup(){
	$('#popGroupTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageGroup/selectManageGroupList.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: true,
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.name = $.trim($("#groupName").val());
        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'id', width:60, hidden:true},
               {title: '组名称', name: 'name', width:60},
			   {title: '描述', name: 'desparment', width:60}
			
			   
           ],
        plugins : [
        	$('#paginatorPaging3').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 40, 50]
        	})
        ] 
    });
}*/
/*function searchGroup(){
	$('#popGroupTable').mmGrid().load({"page":1});
}*/
var artDialog;
//选择群组
function selectGroup(){
	artDialog = dialog({
        title: "选择群组" ,
        url:"<%=request.getContextPath()%>/learnPlan/listGroup.action",
        lock:true,
        height: 600,
		width: 750
	}).showModal();
	
}

function addUserByGroup(ids){
var param={};
param.planId=planId;
param.ids=ids;
$.ajax({
	type:"POST",
	async:true,  //默认true,异步
	data:param,
	url:"<%=request.getContextPath()%>/learnPlan/addLearnPlanUserByGroup.action",
	success:function(data){
		
		
		 if(data =="SUCCESS"){
			 dialog.alert('添加成功！');
				search();
			}else{
				dialog.alert('添加失败！');
			}
		
    }
});



}

/**
 * 选择部门
 */
function selectDep() {
	var dep_tree = $("#dep_tree");
	initDep();
	dialog(
			{
				title : "选择部门",
				width : 300,
				height : 300,
				content : dep_tree,
				okValue : '确定',
				ok : function() {
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
					addUserByDep(ids);
				},
				cancelValue : '取消',
				cancel : function() {
				}
			}).showModal();

}
/**
 * 初始化选择部门的信息
 */
function initDep() {
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

function initPostTree(){
    var setting = {
        data: {
            key: {url: "xUrl"},
            simpleData: {enable: true, pIdKey: "parentId" }
        },
        check: {enable:  true,
            chkboxType: { "Y": "s", "N": "" }
        },
        view: {
            fontCss : function(){

            },//个性化设置ztree的节点高亮效果
            showLine: false,
            showIcon: true
        }
    };
    $.ajax({
        type:"POST",
        async:false,  //默认true,异步
        url:"<%=request.getContextPath()%>/manageParam/selectManagePost.action",
        success:function(data){
            addIconInfo(data);
            $.fn.zTree.init($("#postTree"), setting, data);
            $.fn.zTree.getZTreeObj("postTree").expandAll(true);
        }
    });
}

function choosePost(){
    dialog({
        title : "选择岗位",
        width : 400,
        height : 350,
        content :$("#postDiv"),
        okValue : '确定',
        fixed:true,
        ok : function() {
            var postId = '';
            $.each($.fn.zTree.getZTreeObj("postTree").getCheckedNodes(true), function(index, val){
                if(index == 0){
                    postId += val.id
                }else{
                    postId += ',' + val.id
                }
            });
            if(postId == ''){
                return false;
            }
            var param={};
            param.planId=planId;
            param.ids=postId;
            $.ajax({
                type:"POST",
                async:false,  //默认true,异步
                data:param,
                url:"<%=request.getContextPath()%>/learnPlan/addLearnPlanUserByPost.action",
                success:function(data){
                    if(data=='SUCCESS'){
                        dialog.alert('添加成功！');
                       search();
                    }  else{
                        dialog.alert('添加失败！');
                    }

                }
            });
        },
        cancelValue : '取消',
        cancel : function() {
        }
    }).showModal();
}


function addUserByDep(ids){
var param={};
param.planId=planId;
param.ids=ids;
$.ajax({
	type:"POST",
	async:true,  //默认true,异步
	data:param,
	url:"<%=request.getContextPath()%>/learnPlan/addLearnPlanUserByDep.action",
	success:function(data){
		
		
		 if(data =="SUCCESS"){
			 dialog.alert('添加成功！');
				search();
			}else{
				dialog.alert('添加失败！');
			}
		
    }
});



}

    //查询
    function search(page){
    	if(page){
    		$('#exampleTable').mmGrid().load({"page":page});
    	}else{
    		$('#exampleTable').mmGrid().load();
    	}
    	
    }
    //重置
    function clearAll(){
    	$("#userName").val("");
    	search(1);
    }

    //删除一行
    function deleteRow(){
    	
    	//被选中的一行
    	var selectRows = $('#exampleTable').mmGrid().selectedRows();
    	
    	if(selectRows.length > 0){
    		dialog.confirm('确认删除吗？', function(){ 
    		
    				var param = [];
    		    	$.each(selectRows, function(index, val){
    		    		param.push({"name":"ids", "value":val.id});
    		    	});
    		    	
    		    	$.ajax({
    		    		type:"POST",
    		    		async:true,  //默认true,异步
    		    		data:param,
    		    		url:"<%=request.getContextPath()%>/learnPlan/deleteLearnPlanAssignment.action",
    		    		success:function(data){
    		    			search();
    		    	    }
    		    	});
    		    
    		});
    	}else{
    		dialog.alert('请先选择数据！');
    	}
    }

    //删除一行
    function deleteRowOne(id){
    	
    	dialog.confirm('确认删除吗？', function(){ 
           	
    			var param = [];
    			param.push({"name":"ids", "value":id});
    	    	
    	    	$.ajax({
    	    		type:"POST",
    	    		async:true,  //默认true,异步
    	    		data:param,
    	    		url:"<%=request.getContextPath()%>/learnPlan/deleteLearnPlanAssignment.action",
    	    		success:function(data){
    	    			if(data == "SUCCESS"){
    	    				dialog.alert('删除成功！');
    	    				search();
    	    			}else{
    	    				dialog.alert('删除失败！');
    	    			}
    	    	    }
    	    	});
    	    
    	});
    }

//保存用户方法
function save(){
	var param = new Object();
	param.name = $.trim($("#name").val());
	param.type = $.trim($("#type").val());
	param.beginTime = $.trim($("#beginTime").val());
	param.endTime = $.trim($("#endTime").val());
	param.description = $.trim($("#description").val());
	param.coverImage = $.trim($("#coverImage").val());
	
	
	var urlStr = "<%=request.getContextPath()%>/learnPlan/addLearnPlan.action";
	
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:param,
		dataType:"json",
		url:urlStr,
		success:function(data){
			if(data == "SUCCESS"){
				dialog.alert('保存成功！');
			}else{
				dialog.alert('保存失败！');
			}
			
	    }
	});
}

</script>
</head>
<body style="">
<div class="content">
	<h3>安排课程</h3>
    <div class="make_sc">
    	<div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty2.png" />
            <span class="span_3">1</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect2.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect2.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty2.png" />
            <span class="span_3">2</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect2.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect1.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png" />
            <span>3</span>
        </div>
        <div class="bt">
        	<span class="bt_2">创建计划基本信息</span>
            <span class="bt_2">新增计划阶段与内容</span>
            <span class="bt_4" >选择计划人员</span>
        </div>
    </div>
    <div class="button_cz" style="margin:30px 0 3px 0; padding:0;">
    	<input type="button" onclick="selectUser()"  value="从人员库中选择"/>
        <input type="button" onclick="selectDep()" value="选择部门" />
        <input type="button" onclick="choosePost()"  value="选择岗位" />
        <input type="button" onclick="selectGroup()"  value="选择群组" />
        <input type="button" onclick="deleteRow()" class="back"  value="批量删除" />

    </div>
    
	<div class="search_3 fl" style="width:1046px">
    	<p>姓名：
                     <input type="text" id="userName"  />

        </p>
        <input type="button" value="查询" onclick="search(1)" class="btn_cx" style="float:left;" />

    </div>
	<div id="exampleTable" class="tab_1">
      
     </div>
   <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

      <div class="button_cz fl" style="margin-top:20px; margin-left:0;">
      		<input type="button" onclick="goLast()"  value="上一步" />
            <input type="button" onclick="goBack()" value="返回" class="back" />
			
      </div>
     </div>
     		<!-- 弹出部门树 start -->
			<div>
				<div class="dep_tree" id="dep_tree">
       			 <ul id="treePage" class="ztree" style="height:auto"></ul>
   				</div>
			</div>
			<!-- 弹出部门树 end -->
			
			<!-- dialog 选择人员 start -->
	<div id="selectPeople" style="display: none;">
	
		<div class="company_tree" style="float: left;">
        	<ul id="popUserTreePage" class="ztree" style=""></ul>
   		</div>
   		<div class="search_3 fl" style="float: right;width: 650px;border: none">
	        	<p>	
	            	用户名： <input id="popUserName" type="text">
	               	姓名： <input id="popName" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="searchUser()">
        </div>
        
   		<div style="width: 650px;float: right;">
   			
   			<table id="popUserTable"></table>
			<div id="paginatorPaging2" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
<div class="course_tree" style="display:none;" id="postDiv">
    <h2>岗位</h2>
    <ul id="postTree" class="ztree" style=""></ul>
</div>
	<!-- dialog end -->

			

</body>
</html>
