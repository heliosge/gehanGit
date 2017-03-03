<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加课程</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js" ></script>
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

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<style type="text/css">
/* mmGrid begin */
/*.tab_3 .tr1{ background:#2c2c2c; width:1046px; height:36px;}*/
.mmGrid .mmg-headWrapper .mmg-head th{ background:#2c2c2c;}
/*.tab_3 .tr1:hover{background:#2c2c2c;}*/
.mmGrid .mmg-headWrapper .mmg-head th:hover{background:#2c2c2c;}
/* mmGrid end */
	.tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>
<script>
$(function(){
	initPage();
	
	//表格
	var grid =$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/post/selectResCourseList.action",
		width: $('#exampleTable').width(),
    	height: '445px',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               /**{title: '课程编号', name: 'code', width:60, align:'center'}, **/
 			   {title: '课程名称', name: 'name', width:90, align:'center'},
 			   {title: '课程类型', name: 'type', width:90, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "直播课程";
				   }
				}},
 			   {title: '课程体系', name: 'categoryName', width:60, align:'center'}
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
	grid.on("loadSuccess",function(e, data){
		var rows = grid.rows();
		for(var i=0;i<window.parent.courseGridRows.length;i++){
			
			if(window.parent.courseGridRows[0] != undefined){
				if(rows[0] != undefined){
					for(var j=0;j<rows.length;j++){
						if(rows[j].id==window.parent.courseGridRows[i].courseId){
							grid.select(j);
						}
					}
				}
			}
		}
	});
})

function param(){
	var o = {};
	o.name = $("#name").val();
	o.code = $("#code").val();
	o.type = $("#type").val();
	var courseType=window.parent.$("#courseType").val();
	if(courseType==1){
		courseType=2;
	}else if(courseType==2){
		courseType=1;
	}
	o.courseType=courseType;
	o.categoryId = categoryIds;
	o.status = 2;
	nodes=window.parent.zTree.getSelectedNodes();
	o.postId = nodes[0].id;
	return o;
}

function initPage(){
	var setting = {
			data: {
				key: {url: "xUrl"},
				simpleData: {enable: true, pIdKey: "parentId" }
			},
			check: {enable:  false},
			callback: {
				onClick: zTreeOnClick
			}
	};
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
		success:function(data){
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
}








var ids = [];

function search(){
	$('#exampleTable').mmGrid().load();
}



var categoryIds = [];

function zTreeOnClick(event, treeId, treeNode) {
	categoryIds = [];
	getChildNodes(treeNode);
	search();
};

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
function save(){
	var rows = $('#exampleTable').mmGrid().selectedRows();
	if(rows.length==0){
		dialog.alert("请选择数据");
		return;
	}
	
	window.parent.savePostCourse(rows);
	window.parent.artDialog.close();
}
</script>

</head>
<body>

<div class="content" style="margin-top: 0px;padding-bottom:0px;">
 <div class="content_2">
        <div class="tree">
            <h2>课程体系</h2>
        	<ul id="treePage" class="ztree" ></ul>
        </div>
        <div class="right_lesson" >
          <div class="search_3">
        	<p>	
        	<!--  
            	课程编号：
                <input type="text" id="code"/>-->
            	 课程名称：
                <input type="text" id="name"/>
            	课程类型：
                <select id="type">
                    <option value="">全部</option>
                    <option value="1">线上课程</option>
                    <option value="2">直播课程</option>
                </select>
               
        	</p>
        	<input type="button" value="查询"  onclick="search()"/>
           <input type="button" value="确定" class="btn_cx" onclick="save()"/>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

    </div>
    </div>
    </div>

<script>
</script>

</body>
</html>
