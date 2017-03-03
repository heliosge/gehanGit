<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程管理</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
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
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: absolute;background:url("")}
	.ztree li span.button.noline_close{z-index:999;position: absolute;background:url("")}
	.tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 450px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>

<script>
$(function(){
	
	initTree();
	
	//表格
	var grid = $('#exampleTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/selectExamList.action",
    	width: $('#exampleTable').parent().width(),
    	height: '400',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        checkCol: true,
        params: function(){
        	var param = {};
        	param.name = $("#name").val();
        	param.categoryIds = categoryIds;
	    	return param;
	    },
     	cols: [{title: 'ID', name: 'examId', width:30, hidden:true},
               {title: '试卷名称', name: 'title', width:60, align:'center'},
               {title: '试卷库', name: 'categoryName', width:60, align:'center'},
               {title: '题型分布', name: 'content', width:180, align:'center', renderer:function(val, item, rowIndex){
            	   var re = '';
            	   if(item.count1 != 0){re += "主观题"+item.count1;}
            	   if(item.count2 != 0){re += "单选题"+item.count2;}
            	   if(item.count3 != 0){re += "多选题"+item.count3;}
            	   if(item.count4 != 0){re += "判断题"+item.count4;}
            	   if(item.count5 != 0){re += "填空题"+item.count5;}
            	   if(item.count6 != 0){re += "组合题"+item.count6;}
            	   if(item.count7 != 0){re += "多媒体题"+item.count7;}
				   return re;
			   }},
               {title: '试卷总分', name: 'totalScore', width:30, align:'center'}
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
		for(var i=0;i<window.parent.examGridRows.length;i++){
			if(window.parent.examGridRows[0] != undefined){
				if(rows[0] != undefined){
					for(var j=0;j<rows.length;j++){
						if(rows[j].examId==window.parent.examGridRows[i].examId){
							grid.select(j);
						}
					}
				}
			}
		}
	});
});

function initTree(){
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
		url:"<%=request.getContextPath()%>/exam/paperCategory/list.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
			$.fn.zTree.getZTreeObj("treePage").expandAll(true);
		}
	});
}

var categoryIds;
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

function addIconInfo(data) {
    for (var idx in data) {
        data[idx]['icon'] = '<%=request.getContextPath()%>/images/img/ico_txt.png';
        data[idx]['iconOpen'] = '<%=request.getContextPath()%>/images/img/ico_sq.png';
        data[idx]['iconClose'] = '<%=request.getContextPath()%>/images/img/ico_zk.png';
    }
}

function param(){
	var o = {};
	o.name = $("#name").val();
	o.createUserName = $("#createUserName").val();
	return o;
}

function search(){
	$('#exampleTable').mmGrid().load();
}

function save(){
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	var parentRows = window.parent.$('#exampleTable-1').mmGrid().rows();
	if(parentRows[0] != undefined){
		for(var j=0;j<parentRows.length;j++){
			for(var i=selectRows.length-1;i>=0;i--){
				if(selectRows[i].examId == parentRows[j].examId){
					selectRows.splice(i,1);
					break;
				}
			}
		}
	}
	window.parent.$('#exampleTable-1').mmGrid().addRow(selectRows);
	window.parent.examGridRows = window.parent.$('#exampleTable-1').mmGrid().rows();
	window.parent.artDialog.close();
}

</script>

</head>
<body>
<div class="content" style="margin-top: 0px;padding-bottom:0px;">
 <div class="content_2">
        <div class="tree">
            <h2>试卷库</h2>
        	<ul id="treePage" class="ztree" ></ul>
        </div>
        <div class="right_lesson" >
           <div class="search_3" style="margin-top:-1px;">
                <p>	试卷名称：
                    <input type="text" id="name"/>
            	</p>
        <input type="button" value="查询" class="btn_3" onclick="search()"/>
        <input type="button" value="确定" class="btn_cx" onclick="save()"/>
    
            </div>
    <div id="exampleTable" style="margin-top:10px;width:100%;display:none;" ></div>
    <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

    </div>
    </div>
    </div>
<script>
</script>

</body>
</html>
