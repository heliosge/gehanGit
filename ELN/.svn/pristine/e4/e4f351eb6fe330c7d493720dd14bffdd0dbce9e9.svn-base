<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专题管理</title>
</head>
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
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.course_tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{width:0px;}
	.ztree li span.button.noline_open{position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_sq.png")}
	.ztree li span.button.noline_close{position: absolute;background-image:url("<%=request.getContextPath()%>/images/img/ico_zk.png")}
</style>

<script src="<%=request.getContextPath()%>/js/webhr.js"></script>
<body>

<script type="text/javascript">
$(function(){
	initPage();
	
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
        params: function(){
        	return  param();
	    },
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课程编号', name: 'code', width:60, align:'center'},
 			   {title: '课程名称', name: 'name', width:60, align:'center'},
 			   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "直播课程";
				   }
				}},
 			   {title: '课程体系', name: 'categoryName', width:60, align:'center'},
 			  {title: '是否推广', name: 'isSpread', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "推广 ";
				   }else if(val == 2){
					   return "不推广";
				   }
				}},
				   {title: '操作', name: 'isSpread', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(val == 1){
						   buttonStr += '<a href="#" onclick="cancelSpread('+item.id+')" >取消推广</a>  ';
					   }else if(val == 2){
						   buttonStr += '<a href="#" onclick="spread('+item.id+')" >推广</a>  ';
					   }
					   buttonStr += '<a href="<%=request.getContextPath()%>/res/toUpdateResCoursePage.action?id='+item.id+'" >修改</a>';
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
})

function param(){
	var o = {};
	o.name = $("#name").val();
	o.code = $("#code").val();
	o.type = $("#type").val();
	o.status = 2;
	o.categoryId = categoryIds;
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
		url:"<%=request.getContextPath()%>/res/selectResCourseCategory.action",
		success:function(data){
			addIconInfo(data);
			$.fn.zTree.init($("#treePage"), setting, data);
		}
	});
	
	$.fn.zTree.getZTreeObj("treePage").expandAll(true);
}


function spread(id){
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{
			id:id
		},
		url:"<%=request.getContextPath()%>/oam/spreadResCourse.action",
		success:function(data){
			search();
			dialog.alert("推广成功。");
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
		url:"<%=request.getContextPath()%>/oam/cancelSpreadResCourse.action",
		success:function(data){
			search();
			dialog.alert("取消推广成功。");
		}
	});
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

</script>

<div class="content">
	<!-- <h3>推广课程管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">推广课程管理</span>
	 </div>
    <div class="lesson_tab"  style="width:1066px;float: left;margin-bottom: 10px;">
        	<ul>
            	<li><a href="<%=request.getContextPath()%>/oam/toOamTopicListPage.action" style="color:black;">推广专题管理</a></li>
                <li class="li_this">推广课程管理</li>
                <li><a href="<%=request.getContextPath()%>/oam/toOamSpreadGameListPage.action" style="color:black;">推广大赛管理</a></li>
            </ul>
	</div>
    <div class="content_2">
        <div class="course_tree">
            <h2>课程体系</h2>
        	<ul id="treePage" class="ztree" style=""></ul>
        </div>
        <div class="right_lesson">
            <div class="search_3" style="margin-top:-1px;">
                <p>	
            	课程编码：
                <input type="text" id="code"/>
            	 课程名称：
                <input type="text" id="name"/>
            	课程类型：
                <select id="type">
                    <option value="">全部</option>
                    <option value="1">线上课程</option>
                    <option value="2">直播课程</option>
                </select>
               
        	</p>
        	<input type="button" value="查询" class="btn_cx" onclick="search()"/>
    
            </div>
            
            <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
			<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
            
        </div>
            
   </div>
   </div>
</body>
</html>
