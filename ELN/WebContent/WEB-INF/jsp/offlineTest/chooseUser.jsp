<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择学员</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
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
	
.span_btu{
	display: inline-block;
	background-color: #02B8EA;
	font-size: 12px;
	padding: 0px 5px;
	cursor: pointer;
	border-radius: 2px;
	min-width: 50px;
	line-height: 30px;
	text-align: center;
	color: #FFFFFF;
}

</style>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
		initPage();
		initMMGrid();
		
});


function initMMGrid(){
	var mmGrid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/manageUser/selectStudentList.action",
    	width: $('#exampleTable').parent().width(),
    	height: '400px',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //multiSelect: true,
        //checkCol: true,
        params: function(){
        	return  toParam();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '姓名', name: 'name', width:60, align:'center'},
 			   {title: '用户名', name: 'userName', width:60, align:'center'},
 			   {title: '电子邮箱', name: 'email', width:60, align:'center'},
			   {title: '联系电话', name: 'mobile', width:60, align:'center'},
			   {title: '岗位', name: 'postName', width:60, align:'center'},
 			   {title: '部门', name: 'deptName', width:60, align:'center'},
			   {title: '状态', name: 'status', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "正常";
				   }else if(val == 2){
					   return "冻结";
				   }
				}},
				{title: '操作', name: 'id', width:60, align:'center', renderer:function(val, item, rowIndex){
				   
					return '<span onclick="selectUser('+rowIndex+')" class="span_btu" style="background-color:#E53E38;" >选择</span>';
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
	
}

function toParam(){
	var o = {};
	o.userName = $("#userName").val();
	o.name = $("#name").val();
	o.deptIds = deptIds;
	return o;
}

function selectUser(index){
	
	var data = $('#exampleTable').mmGrid().row(index);
	
	//alert(JSON.stringify(data));
	parent.setUser(data);
}

function search(){
	$('#exampleTable').mmGrid().load({"page":1});
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
				onClick: zTreeOnClick
			}
	};
	
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/manageCompany/selectCompanyCategory.action",
		success:function(data){
			addIconInfo(data.data);
			$.fn.zTree.init($("#treePage"), setting, data.data);
			//如果是集团公司删除子公司的部门
			var treeObj = $.fn.zTree.getZTreeObj("treePage");
			/* for(var i = 0; i < data.subData.length; i++){
				var node = treeObj.getNodeByParam("id", data.subData[i], null);
				treeObj.removeNode(node, false);
			} */
		}
	});
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}

var deptIds;
function zTreeOnClick(event, treeId, treeNode) {
	deptIds = [];
	if(treeNode.id.indexOf("com") == 0){
		deptIds = [];
	}else{
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
	

</script>

</head>
<body>

<div class="content" style="margin-top: 0px;padding-bottom:0px;">
 <div class="content_2">
        <div class="tree">
            <h2>公司组织结构</h2>
        	<ul id="treePage" class="ztree" ></ul>
        </div>
        <div class="right_lesson" >
            <div class="search_3" style="margin-top:-1px;">
               <p>用户名：
            <input type="text" id="userName"/>
           	 姓名：
            <input type="text" id="name"/>
			<!-- 岗位：
            <input type="text" id="postId"/>
          	 部门：
            <input type="text" id="deptId"/> -->
        </p>
        <input type="button" value="查询" class="btn_3" onclick="search()"/>
        <!-- <input type="button" value="确定" class="btn_cx" onclick="save()"/> -->
    
            </div>
    <div id="exampleTable" style="margin-top:10px;width:100%;display:none;" ></div>
    <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

    </div>
    </div>
    </div>
</body>
</html>