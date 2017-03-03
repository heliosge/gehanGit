<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改考试</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 350px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;margin-left:13px;position: absolute;background-image:url("")}
	.button{ 
		width: 70px; height: 30px; background: #d60500; color: #fff; font-family: '微软雅黑'; text-align: center; border: none; margin-top: 10px;
	}
</style>
<script type="text/javascript">
var arrangeId = ${arrange.id};
var arrange = ${arrange};
var firstFlag = true;

$(function(){
	initGrid();
	initDepartmentTree();
	initPostTree();
})

function initGrid(){
	//表格
	var grid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/train/selectTrainArrangeUserList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        indexCol: true,  //索引列
        params: function(){
        	return  toParam();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
               {title: '姓名', name: 'name', width:60, align:'center'},
               {title: '部门', name: 'deptName', width:60, align:'center'},
 			   {title: '岗位', name: 'postName', width:60, align:'center'},
			   {title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
				 	return ' <a href="javascript:void(0);" onclick="deleteRow('+item.id+')" >删除</a>'
			   }}
           ]
    });
	
	grid.on("loadSuccess",function(e, data){
		if(firstFlag){
			firstFlag = false;
			if($('#exampleTable').mmGrid().rows()[0] != undefined){
				stuGridRows = $('#exampleTable').mmGrid().rows();
				insertPage(stuGridRows);
			}
		}
	});
}

function toParam(){
	var o = {};
	o.arrangeId = arrangeId;
	o.name = $("#name").val();
	o.deptName = $("#deptName").val();
	o.postName = $("#postName").val();
	o.passStatus = '2';
	return o;
}

function initDepartmentTree(){
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
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			$.fn.zTree.init($("#departmentTree"), setting, data.data);
			$.fn.zTree.getZTreeObj("departmentTree").expandAll(true);
		}
	});
}

function chooseDept(){
	dialog({
		title : "选择部门",
		width : 400,
		height : 350,
		content :$("#departmentDiv"),
		okValue : '确定',
		fixed:true,
		ok : function() {
			var deptId = '';
			$.each($.fn.zTree.getZTreeObj("departmentTree").getCheckedNodes(true), function(index, val){
				if(index == 0){
					deptId += val.id
				}else{
					deptId += ',' + val.id
				}
			});
			if(deptId == ''){
				return false;
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"deptId":deptId},
				url:"<%=request.getContextPath()%>/exam/exam/getPersonByDeptIds.action",
				success:function(data){
					addUserToGrid(data);
				}
			});
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
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
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"postId":postId},
				url:"<%=request.getContextPath()%>/exam/exam/getPersonByPost.action",
				success:function(data){
					addUserToGrid(data);
				}
			});
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();
}



/*选择群组  */
function selectGroup(){
	var selectGroup = $("#selectGroup");
	dialog({
		title : "选择群组",
		width : 730,
		height : 350,
		content :selectGroup,
		onshow: function() {
			setTimeout(initGroupGrid, 200);
		},
		okValue : '确定',
		fixed:true,
		ok : function() {
			var rowData = $('#groupTable').mmGrid().selectedRows();
			if(rowData.length == 0){
				dialog.alert("请选择群组！");
				return false;
			}else{
				var groupIds ="";
				for(var i=0;i<rowData.length;i++){
					groupIds += rowData[i].id+",";
				}
				$("#groupId").val(groupIds.substr(0,groupIds.length-1));
			}
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"groupId":$("#groupId").val()},
				url:"<%=request.getContextPath()%>/exam/exam/getPersonByGroupId.action",
				success:function(data){
					addUserToGrid(data);
				}
			});
		},
		cancelValue : '取消',
		cancel : function() {
		}
	}).showModal();		
}

function initGroupGrid() {
	$('#groupTable').mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : "<%=request.getContextPath()%>/exam/exam/getGroupBySubCompanyId.action",
		width : 'auto',
		height : '265px',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : function(){
			var param = new Object();
			param.name = $.trim($("#groupName").val());
			param.createUserName = $.trim($("#createUserName").val());
			return param;
		},
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '群组',name : 'name',align : 'center'}, 
		         {title : '创建人',name : 'createUserName',align : 'center'} 
		       ],
		plugins : [ $('#paginatorPaging3').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ]
	});
}

function addUserToGrid(data){
	//var rows = $('#exampleTable').mmGrid().rows();
	if(data != null){
		if(stuGridRows[0] != undefined){
			$.each(stuGridRows,function(index, val){
				for(var i = data.length-1; i >= 0; i--){
					if(val.id == data[i].id){
						data.splice(i,1);
						break;
					}
				}
			})
		}
		if(data.length > 0){
			$.each(data,function(index, val){
				stuGridRows.push(val);
			})
		}
		insertPage(stuGridRows);
	}
}

function query(){
	$('#exampleTable').mmGrid().load();
}

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

//选择人员
var artDialog;
function pointStudent(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/train/chooseStu.action",
        title:"选择人员" ,
        height: 500,
		width: 1100
		}).showModal();
}

var stuGridRows = [];

function deleteRow(id){
	//var rows = stuGridRows;
	for(var index = stuGridRows.length-1; index>=0; index--){
		if(id == stuGridRows[index].id){
			stuGridRows.splice(index,1);
			break;
		}
	}
	insertPage(stuGridRows);
}

//批量删除
function deleteRows(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(selectRows.length > 0){
		dialog.confirm("确认删除吗?",function(obj){
			for(var i = stuGridRows.length-1; i>=0; i--){
				for(var index = selectRows.length-1; index >= 0; index--){
					if(stuGridRows[i].id == selectRows[index].id){
						stuGridRows.splice(i,1);
						break;
					}
				}
				/* $.each(selectRows, function(index, val){
					if(stuGridRows[i].id == val.id){
						stuGridRows.splice(i,1);
					}
				}); */
			}
			insertPage(stuGridRows);
		});
	}else{
		dialog.alert("请选中人员。");
	}
}

//查询
function search(){
	var rows = stuGridRows.slice(0);
	var userName = $("#userName").val();
	var name = $("#name").val();
	var postName = $("#postName").val();
	var deptName = $("#deptName").val();
	for(var index = rows.length-1; index>=0; index--){
		var item = rows[index];
		if(userName){
			if(item.userName == null || item.userName.indexOf(userName) == -1){
				rows.splice(index,1);
				continue;
			}
		}
		if(name){
			if(item.name == null || item.name.indexOf(name) == -1){
				//$('#exampleTable').mmGrid().removeRow(index);
				rows.splice(index,1);
				continue;
			}
		}
		if(postName){
			if(item.postName == null || item.postName.indexOf(postName) == -1){
				//$('#exampleTable').mmGrid().removeRow(index);
				rows.splice(index,1);
				continue;
			}
		}
		if(deptName){
			if(item.deptName == null || item.deptName.indexOf(deptName) == -1){
				//$('#exampleTable').mmGrid().removeRow(index);
				rows.splice(index,1);
				continue;
			}
		}
	}
	insertPage(rows);
}

function clearAll(){
	$("#userName").val('');
	$("#name").val('');
	$("#postName").val('');
	$("#deptName").val('');
	search();
}

function hideErrorInfo(){
	if($('#exampleTable').mmGrid().rows()[0] == undefined){
		$(".mmg-message").css("top","50px");
	}
}

//保存
function save(){
	var o = {};
	o.arrangeId = arrangeId;
	o.userIds = [];
	$.each(stuGridRows,function(index,val){
		if(val != undefined){
			o.userIds.push(val.id);
		}
	})
	/* if(arrange.o.userIds.length > arrange.maxJoinNum){
		dialog.alert("参训人员数量大于培训允许的参训人员数量，请重新选择人员。");
		return;
	} */
	$.ajax({
   		type:"POST",
   		async:false,  //默认true,异步
   		data:o,
   		url:"<%=request.getContextPath()%>/train/updateTrainArrangeUser.action",
   		success:function(data){
   			if(data == 'SUCCESS'){
   				dialog.alert("保存成功。",function(){
   					history.go(-3);
   					//window.location.href="<%=request.getContextPath()%>/train/updateTrainArrangeUser.action";
   				})
   			}else{
   				dialog.alert("保存失败");
   			}
   	    }
   	});
}

var pageSize = 10;
//插入分页插件
function insertPage(data){
	$("#jquery_page").show();
	//插入分页插件
	$("#jquery_page").pagination(data.length, {
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			 $('#exampleTable').mmGrid().load(data.slice(page*pageSize,(page+1)*pageSize));
		 }  
	});
}

//群组搜索
function doGroupSearch(){
	var name = $.trim($("#groupName").val());
	var createUserName = $.trim($("#createUserName").val());
	$('#groupTable').mmGrid().load({"page":1,"name":name,"createUserName":createUserName});
}
</script>
</head>
<body>
<div class="content">
	<!-- <h3>选择参训人员</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">选择参训人员</span>
	</div>
			<div class="make_sc">
	    	<div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png">
	            <span class="span_2">1</span>
	        </div>
	        <div class="connect_3">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	        </div>
	        <div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png">
	            <span class="span_2">2</span>
	        </div>
	        <div class="connect_3">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_connect.png">
	            <img src="<%=request.getContextPath()%>/images/img/ico_connect1.png">
	        </div>
	        <div class="sc_tap1">
	        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png">
	            <span >3</span>
	        </div>
	        <div class="bt" style="margin-left:-5px;">
	        	<span style="margin-right:308px;">基本信息</span>
	            <span>培训内容</span>
	            <span style="margin-left: 308px;color: #0085ff;">参与人员</span>
	        </div>
    	</div>
    <div class="button_cz" style="margin:30px 0 0 0; padding:0;">
        	<input type="button" value="授权部门" onclick="chooseDept();"/>
            <input type="button" value="授权群组"  onclick="selectGroup();"/>
            <input type="button" value="授权人员"  onclick="pointStudent();"/>
            <input type="button" value="授权岗位" onclick="choosePost();"/>
            <input type="button" value="批量删除" class="back"  onclick="deleteRows();"/>
    </div>
    <div class="search_3" style="width:1044px; margin-top:20px;">
        	<p>	
        		用户名：<input type="text" id="userName"/>
            	姓名：<input type="text" id="name"/>
                                                岗位：<input type="text" id="postName"/>
            	部门：<input type="text" id="deptName"/>
        	</p>
            <input type="button" value="重置" onclick="clearAll();"/>
        	<input type="button" value="查询" class="btn_cx" onclick="search();"/>

    </div>
   	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
   	<!--分页  -->
	<div id="jquery_page" style="margin-top: 10px; display: none; float: right;" class="pagination"></div>
    <input type="button" class="button" value="保存" onclick="save()"/>
</div>

	<!-- dialog2 end -->
	<div class="course_tree" style="display:none;" id="departmentDiv">
		<h2>部门</h2>
       	<ul id="departmentTree" class="ztree" style=""></ul>
  	</div>
  	
  	<div id="selectGroup" style="display: none;">
  		<input type="hidden" id="groupId"/>
   		<div class="search_3 fl" style="width: 700px;border: none">
	        	<p>	
	            	群组名： <input id="groupName" type="text">
	               	创建人： <input id="createUserName" type="text">
	        	</p>
	            <input type="button" value="查询" class="btn_cx" onclick="doGroupSearch()">
        </div>
        <div class="clear_both"></div>
   		<div style="width: 720px;">
   			<table id="groupTable"></table>
			<div id="paginatorPaging3" style="text-align: right;margin-right: 10px;"></div>
   		</div>
	</div>
	
	<div class="course_tree" style="display:none;" id="postDiv">
		<h2>岗位</h2>
       	<ul id="postTree" class="ztree" style=""></ul>
  	</div>
  	
</body>
</html>
