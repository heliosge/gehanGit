<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
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

<style type="text/css">
	.course_tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{z-index:999;position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")
</style>

<script>
$(function(){
	initPage();
	
	//表格
	var grid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/mall/manage/course/list.action",
    	width: $('#exampleTable').parent().width(),
    	height: '400',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               /**{title: '课程编号', name: 'code', width:60, align:'center'},**/
 			   {title: '课程名称', name: 'name', width:60, align:'center'},
 			   {title: '课程分类', name: 'categoryName', width:60, align:'center'},
 			   {title: '课程价格', name: 'price', width:60, align:'center'},
 			   {title: '优惠价格', name: 'cheapPrice', width:60, align:'center'}/* ,
 			   {title: '课程体系', name: 'categoryName', width:60, align:'center'},
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(val == 1){
						   buttonStr += '<a href="#" onclick="release('+item.id+')" >发布</a>  ';
					   }else if(val == 2){
						   buttonStr += '<a href="#" onclick="share('+item.id+')" >共享</a>  ';
					   }
					   buttonStr += '<a href="toUpdateResCoursePage.action?id='+item.id+'" >修改</a>  <a href="#" onclick="deleteOne('+item.id+')" >删除</a>';
					   return "<div class='span_btus' >" + buttonStr + "</div>";
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
	
	grid.on("loadSuccess",function(e, data){
		var rows = grid.rows();
		for(var i=0;i<window.parent.courseList.length;i++){
			if(window.parent.courseList[0] != undefined){
				if(rows[0] != undefined){
					for(var j=0;j<rows.length;j++){
						if(rows[j].id==window.parent.courseList[i].id){
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
	o.categorys = categoryIds;
	o.status = 1;
	o.isAll=1;
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
		url:"<%=request.getContextPath()%>/mall/manage/category/select.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}


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
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	if(!window.parent.courseList){
		window.parent.courseList =[];
	}
	var courseList = window.parent.courseList;
	if(selectRows&&selectRows.length>0){
	for(var i=0;i<selectRows.length;i++){
		if(courseList&&courseList.length>0){
			for(var j = 0;j<courseList.length;j++){
				if(courseList[j].id==selectRows[i].id){
					break;
				}
				if(j==(courseList.length-1)){
					window.parent.courseList.push(selectRows[i]);
				}
			}
		}else{
			window.parent.courseList.push(selectRows[i]);
		}
	}
	}
	
	window.parent.$('#exampleTable').mmGrid().load(window.parent.courseList);
	window.parent.priceChange();
	window.parent.artDialog.close();
}
</script>

</head>
<body>

<div class="content" style="margin-top: 0;padding-bottom: 0;">
   	<div class="course_tree">
   		<h2>课程体系</h2>
        <ul id="treePage" class="ztree" style=""></ul>
   
   	</div>
    <div class="right_lesson">
        <div class="search_3">
        	<p>	
            	<!-- 课程编号：
                <input type="text" id="code"/> -->
            	 课程名称：
                <input type="text" id="name"/>
           
        	</p>
        	<input type="button" value="查询" class="btn_3" onclick="search()"/>
			<input type="button" value="确定" class="btn_cx" onclick="save()"/>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        
    </div>
        
     
</div>
<script>
</script>

</body>
</html>
