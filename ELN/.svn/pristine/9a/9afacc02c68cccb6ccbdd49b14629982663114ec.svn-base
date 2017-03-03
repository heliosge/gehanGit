<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看企业人员</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- dialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.ztree{overflow: auto;height: 520px;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: left;}
	.company_tree {width: 250px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
</style>

<script type="text/javascript">
$(function(){
	
	initPage();
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/selectCompanyUserList.action",
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        //checkCol: true,
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '姓名', name: 'name', width:60, align:'center'},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
 			   {title: '公司名称', name: 'subCompanyName', width:60, align:'center'},
 			   {title: '部门', name: 'deptName', width:60, align:'center'},
			   {title: '岗位', name: 'postName', width:60, align:'center'},
			   {title: '联系电话', name: 'mobile', width:60, align:'center'},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }
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
				onClick: zTreeOnClick
			}
	};
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectAllCompanyCategory.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

var companyId;
var deptIds;
function zTreeOnClick(event, treeId, treeNode) {
	companyId = '';
	deptIds = [];
	if(treeNode.id.indexOf("com") == 0){
		companyId = treeNode.id.replace("com_","");
		deptIds = [];
	}else{
		companyId = '';
		getChildNodes(treeNode);
	}
	search();
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

function param(){
	var o = {};
	o.userName = $("#userName").val();
	o.name = $("#name").val();
	o.mobile = $("#mobile").val();
	o.sex = $("#sex").val();
	o.status = $("#status").val();
	o.companyId = companyId;
	o.deptIds = deptIds;
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

<div class="content">
	<!-- <h3>查看企业人员</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看企业人员</span>
	</div>
    <div class="content_2">
        <div class="company_tree">
		      <ul id="treePage" class="ztree"></ul>
        </div>
          <div class="right_lesson">
	              <div class="search_3">
        			<p>	  	
	                	用户名：
	                    <input type="text" id="userName"/>
	                   	 姓名：
	                    <input type="text" class="input_1" id="name"/>
	                    	联系电话
	                    <input type="text" id="mobile"/>
	                    </p>
	                </div>
	               <div class="search_3">
	                    <p>
	                	性别：
	                    <select id="sex">
	                    	<option value="">全部</option>
	                    	<option value="1">男</option>
	                    	<option value="2">女</option>
	                    </select>
	                   	 状态：
	                    <select id="status">
	                    	<option value="">全部</option>
	                    	<option value="1">正常</option>
	                    	<option value="2">冻结</option>
	                    </select>
	                    </p>
	                    <input type="button" value="查询" class="btn_3" style="width:68px;" onclick="search_()"/>
	        </div>
	        	<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
				<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
			</div>
			
            
            
            
            
   </div>
   </div>
</body>
</html>
