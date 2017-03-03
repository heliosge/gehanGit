<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择人员</title>

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
	.company_tree {overflow-y:auto;overflow-x:auto;width: 220px;height: 450px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.company_tree2 {overflow-y:auto;overflow-x:auto;width: 220px;height: 300px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.btn {
		background: #0085FE;color: white;text-align: center;width:100px;height: 35px;border: none;border-radius: 2px;font-weight: bold;margin-right: 10px;
		}
</style>

<script type="text/javascript">

var initUserId = '${initUserId}';
var isCompany = '${isCompany}';

var idInputId = "${idInputId}";
var nameInputId = "${nameInputId}";

//被选中的题目
var selectedQes = new Object;

function okSelect(){
	//确定
	var ids = [];
	var names = [];
	
	for(var key in selectedQes){
		
		ids.push(selectedQes[key].id);
		names.push(selectedQes[key].name);
	}
	
	parent.$("#"+idInputId).val(ids.join(","));
	parent.$("#"+nameInputId).val(names.join(","));
	
	parent.layer.closeAll();
}

$(function(){

	initPage();
	
	if(parent.$("#"+idInputId).val()){
		
		var ids = parent.$("#"+idInputId).val().split(",");
		var names = parent.$("#"+nameInputId).val().split(",");
		
		for(var i=0; i<ids.length; i++){
			
			selectedQes["user_" + ids[i]] = {"id":ids[i], "name":names[i]};
		}
	}
	
	//表格
	var myGrid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/selectStudentList.action",
    	width: 690,
    	height: 380,
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        //checkCol: true,
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
     	      {title: '<input type="checkbox" class="my_checkAll" >', name: 'id', width: 22, align: 'center' ,lockWidth: true, checkCol: true, renderer:function(val, item, rowIndex){
            	   
              	 return '<input type="checkbox" class="my_mmg_check" value="'+val+'" userName="'+item.name+'" >';
               }},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
               {title: '姓名', name: 'name', width:60, align:'center'},
               {title: '公司名称', name: 'subCompanyName', width:60, align:'center'},
 			   //{title: '出生日期', name: 'birthDay', width:60, align:'center'},
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
			   }}
				/* {title: '操作', name: 'id', width:140, align:'center', renderer:function(val, item, rowIndex){
				   var buttonStr ='';
				   
				   return buttonStr;
			   }} */
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
	
	myGrid.on('loadSuccess', function(e, data){
        //加载数据成功后触发
        //分页，数据加载后，取消全选
        //$(".mmGrid .my_checkAll").prop('checked', false);
        
    	$(".my_mmg_check").each(function(index, val){
			var questionId = $(val).attr("value");
			if(selectedQes["user_" + questionId]){
				$(val).prop('checked', true);
			};
		});
    	
    	if($(".my_mmg_check").length == $(".my_mmg_check:checked").length){
    		//全选
    		$(".mmGrid .my_checkAll").prop('checked', true);
    	}else{
    		$(".mmGrid .my_checkAll").prop('checked', false);
    	}
    	
     });
    
  	//checkbox 全选
	$(".mmGrid .my_checkAll").change(function(){

		if($(this).prop('checked')){
			//全选
			$(".my_mmg_check").prop('checked', true);
			
			$(".my_mmg_check:checked").each(function(index, val){
				var questionId = $(val).attr("value");
				var name = $(val).attr("userName");
				selectedQes["user_" + questionId] = {"id":questionId, "name":name};
			});
			
		}else{
			
			$(".my_mmg_check:checked").each(function(index, val){
				var questionId = $(val).attr("value");
				delete selectedQes["user_" + questionId];
			});
			
			//全选取消
			$(".my_mmg_check").prop('checked', false);
		}
	});
  	
  	//选择试题
  	$(".mmGrid").on("click", ".my_mmg_check", function(){
		//alert($(this).prop('checked'));
		var questionId = $(this).attr("value");
		
		if($(this).prop('checked')){
			var name = $(this).attr("userName");
			selectedQes["user_" + questionId] = {"id":questionId, "name":name};
		}else{
			delete selectedQes["user_" + questionId];
		}
		
		//alert(JSON.stringify(selectedQes));
	});
});


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
				onClick: zTreeOnClick
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
		deptIds = [];
	}else{
		getChildNodes(treeNode);
	}
	search_();
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

</script>

</head>


<body>

<div style="padding:0;margin:0;margin:5px;">

        <div class="company_tree" id="tree" >
			<ul id="treePage" class="ztree" ></ul>
        </div>
        <div class="right_lesson" style="margin-left:10px;width:670px;height:100%;padding-bottom:0px;">
        <div class="search_3" style="width:670px;">
        	<p>
           	用户名：<input type="text" id="userName"/>
	                    姓名：<input type="text" class="input_1" id="name"/>
	           <!-- 角色：<input type="text" class="input_1" id="roleName"/> -->
	                   <!--  状态：<select id="status">
                 	<option value="">全部</option>
                 	<option value="1">正常</option>
                 	<option value="2">冻结</option>
                </select> -->
                </p>
                
            <input type="button" value="确定" class="btn_3" style="width:68px;background-color:#0E85FE;margin-left:8px;" onclick="okSelect()"/>
	      	<input type="button" value="查询" class="btn_3" style="width:68px;" onclick="search_()"/>
	      	
	        </div>
	      <!-- <div class="search_3">
        	<p>
	                    是否有照片：<select id="photo">
                 	<option value="">全部</option>
                 	<option value="1">是</option>
                 	<option value="2">否</option>
                </select>
                </p>
	      	<input type="button" value="查询" class="btn_3" style="width:68px;" onclick="search_()"/>
	        </div> -->
	    <div id="exampleTable" style="" ></div>
		<div id="paginator11-1" align="right" style="float:right;" ></div>   	
       </div>     
   </div>

</body>
</html>
