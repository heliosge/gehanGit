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

var contextMenu ;
$(function(){
	
	initPage();
	
	
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/res/selectBuyResCourseList.action",
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
               /**{title: '课程编号', name: 'code', width:60, align:'center'}, **/
 			   {title: '课程名称', name: 'name', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="<%=request.getContextPath()%>/res/toCourseDetail.action?courseId='+item.id+'" >'+val+'</a>';
			   }},
 			   {title: '课程类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "线上课程 ";
				   }else if(val == 2){
					   return "直播课程";
				   }
				}},
 			   {title: '课程体系', name: 'categoryName', width:60, align:'center'},
				   {title: '操作', name: 'status', width:120, align:'center', renderer:function(val, item, rowIndex){
					   
					   var buttonStr ='';
					   if(val == 1&&item.mallStatus== 4){
						   buttonStr += '<a href="javascript:void(0)" onclick="release('+item.id+','+item.type+')" >发布</a>  ';
					   }else if(val == 2){
						  /*  if(item.shareStatus == 1 || item.shareStatus == 3 || item.shareStatus == 4 || item.shareStatus == 6 ){
							   buttonStr += '<a href="#" onclick="share('+item.id+','+item.shareStatus+')" >共享</a>  ';
						   } */
						   if(item.isFeatured == 1){
							   buttonStr += '<a href="javascript:void(0)" onclick="featureAndUnFeature('+item.id+',2)" >选为精选</a>  ';
						   }else{
							   buttonStr += '<a href="javascript:void(0)" onclick="featureAndUnFeature('+item.id+',1)" >取消精选</a>  ';
						   }
					   }
					   if(item.mallStatus==3){
						   buttonStr += '<a href="toEditBuyResCoursePage.action?id='+item.id+'" >加入到课程体系</a>  '; 
					   }else{
						   buttonStr += '<a href="toEditBuyResCoursePage.action?id='+item.id+'" >修改</a>  ';
					   }
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
	o.code = $("#code").val();
	o.type = $("#type").val();
	o.mallStatus = $("#mallStatus").val();
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
            	<li><a href="<%=request.getContextPath()%>/res/toResCourseListPage.action" style="color:black;">企业课程</a></li>
            	<li class="li_this">购买的课程</li>
                <%-- <li><a href="<%=request.getContextPath()%>/res/toResCloundCourseListPage.action" style="color:black;">云平台课程</a></li>
               <c:choose>
	              	<c:when test="${USER_BEAN.companyId != 1 }">
                		<li><a href="<%=request.getContextPath()%>/res/toResShareCourseListPage.action" style="color:black;">共享记录</a></li>
		            </c:when>
              	</c:choose> --%>
            </ul>
        </div>
        <div class="button_gr">
            <input type="button" value="更改课程体系" onclick="changeCategory()"/>
        </div>
        <div class="search_3">
        	<p>	
        	<!--  
            	课程编号：
                <input type="text" id="code"/>-->
            	 课程名称：
                <input type="text" id="name"/>
            	是否加入课程体系：
                <select id="mallStatus">
                    <option value="">全部</option>
                    <option value="3">未加入</option>
                    <option value="4">已加入</option>
                </select>
               
        	</p>
        	<input type="button" value="查询" class="btn_cx" onclick="search_()"/>

        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        
    </div>
        
     
</div>

 

</body>
</html>
