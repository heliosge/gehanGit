<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>岗位关联管理</title>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />

<script type="text/javascript">
// zTree part begin
var zTree = null;

var courseGridRows =[];
$(function(){
	initPage();
});


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
	        //async:true,  //默认true,异步
	        data:null,
	        url:'<c:url value="/manageParam/selectManagePost.action"/>',
	        success:function(categoryTree){
	            addIconInfo(categoryTree);
	            zTree = $.fn.zTree.init($("#postTree"), setting, categoryTree);
	            //全部展开
	            zTree.expandAll(true);
	        }
	    });
}


function zTreeOnClick(event, treeId, treeNode) {
    selectCategory(treeNode);
}

function selectCategory(category) {
    clearSearchOptions(1);
    reloadmmGrid({'postId': category['id']});
}

function renderPost(id){
	if(!id){
		return;
	}
	
	$.ajax({
		   type:"POST",
	        //async:true,  //默认true,异步
	        contentType:"application/json; charset=utf-8",
	        data:{"id":id},
	        url:'<c:url value="/post/getPostPath.action"/>',
	        success:function(data){
	            $("#postName").text(data.name);
	            $("#postPath").text(data.path);
	        }
	})
}

// zTree part end

// mmGrid begin


$(document).ready(function(){
    var cols = [
                { title:'id', name:'id', align:'center', hidden:true},
                /**{ title:'课程编号', name:'code' , align:'center'},**/
                { title:'课程名称', name:'name' , align:'center'},
                { title:'类型', name:'courseType' , align:'center',
                	renderer: function(val, item, rowIndex) {
                        var str=""; 
                        if(val==2||val=='2'){
                        	str="必修";
                        }else{
                        	str="选修";
                        }
                        return str;
                    }},
                { title:'已学人数', name:'learningNum' , align:'center'},
                { title:'操作', name:'id' , align:'center',
                    renderer: function(val, item, rowIndex) {
                        var deleteStr = '<a href="javascript:void(0);" onclick="deleteCourse(' + val + ')">删除</a>';
                        return deleteStr;
                    }},
            ];
    
    $('#courseTable').mmGrid({
    	root : 'list',// 和后台数据保存列一致,对应MMGridPageVoBean rows
        cols: cols,
        url: '<c:url value="/post/courseList.action" />',
        params: getSearchOption,
        method: 'post',
        checkCol: true,
        multiSelect: true,
        fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
        showBackboard : false,
        width: 'auto',
        height: 'auto',
        plugins : [
                   $('#paginator').mmPaginator({
                	   style: 'plain',
                       totalCountName: 'totalRows',    //对应MMGridPageVoBean count
                       page: 1,                    //初始页
                       pageParamName: 'pageIndex',      //当前页数
                       limitParamName: 'pageSize', //每页数量
                       limit: 10,
                       limitList: [10, 20, 50, 100, 200],
                       totalCountLabel: '<span>共{0}条</span>',
                   })
               ],
    });
    $('#courseTable').mmGrid().on("loadSuccess",function(e, data){
    	
    	courseGridRows = data.list;
	
	});
});


function selectCourse(type){
	   if (zTree != null) {
	        var nodes = zTree.getSelectedNodes();
	        
	        if (nodes && nodes.length>0) {
	        	$("#courseType").val(type);
	        	artDialog = dialog({
	                title: "选择课程" ,
	                url:"<%=request.getContextPath()%>/post/listCourse.action",
	                lock:true,
	                height: 600,
	        		width: 1070
	        	}).showModal();
	        }else{
	        	dialog.alert("请先选择一个岗位");
	        	return;
	        }
	    }else{
	    	dialog.alert("岗位树错误！");
	    }
}

function savePostCourse(courseList){
	var post ={};
	if (zTree != null) {
        var nodes = zTree.getSelectedNodes();
        
        if (nodes && nodes.length>0) {
        	post.postId = nodes[0].id;
        	post.name=nodes[0].name;
        }else{
        	dialog.alert("请选择一个岗位");
        }
       }else{
    	   dialog.alert("岗位树出错");
       }
       post.list=[];
       var courseType = $("#courseType").val();
	$.each(courseList, function(index, val){
		var postCourse={};
		postCourse.courseId=val.id;
		postCourse.code=val.code;
		postCourse.name=val.name;
		postCourse.categoryId=val.categoryId;
	    postCourse.courseType=courseType;
		post.list.push(postCourse);
    });
	 $.ajax({
	        type:"POST",
	        //async:true,  //默认true,异步
	        contentType:"application/json; charset=utf-8",
	        data:JSON.stringify(post),
	        url:'<c:url value="/post/addPostCourse.action"/>',
	        success:function(data){
	            reloadmmGrid();
	        }
	    });
}

function deleteCourse(id) {
    dialog.confirm('确认删除吗？', function(){
        var idList = [];
        idList.push(id);
        deleteCourses(idList);
    });
}

function deleteSelectedCourses() {
    //被选中的一行
    var selectRows = $('#courseTable').mmGrid().selectedRows();
    
    if(selectRows.length > 0){
        dialog.confirm('确认删除吗？', function(){
            var idList = [];
            $.each(selectRows, function(index, val){
                idList.push(val.id);
            });

            deleteCourses(idList);
        });
    }else{
        dialog.alert('请先选择数据！');
    }
}

function deleteCourses(idList) {
    $.ajax({
        type:"POST",
        //async:true,  //默认true,异步
        //contentType:"application/json; charset=utf-8",
        data:{"ids":idList.join(",")},
        url:'<c:url value="/post/delete/postcourse.action"/>',
        success:function(data){
        	if(data==false){
        		dialog.alert("删除后，岗位关联的课程总分小于路径阶段的学分");
        	}
            reloadmmGrid();
        }
    });
}




function clearSearchOptions(type) {
  
    $('#name').val('');
    $('#type').val('');
    if(!type){
    	reloadmmGrid(null,1);
    }
    
}

function removeNullElements(data) {
    for (var key in data) {
        if (data[key] == null) {
            delete data[key];
        }
    }
}

function getSearchOption() {
    var searchOption = {
        "name":nib('#name'),
        "courseType":nib('#type')

    };
    if (zTree != null) {
        var nodes = zTree.getSelectedNodes();
        if (nodes && nodes.length>0) {
            searchOption['postId'] = nodes[0]['id'];
        }
    }
    removeNullElements(searchOption);
    return searchOption;
}

function reloadmmGrid(otherOptions,page) {
    var searchOption = getSearchOption();
    if(page){
    	 searchOption['pageIndex'] = page;
    }
   
    if (otherOptions != null) {
        for (var key in otherOptions) {
            searchOption[key] = otherOptions[key];
        }
    }
    removeNullElements(searchOption);
    $('#courseTable').mmGrid().load(searchOption);
}

function nullIfBlank(inputId) {
    var val = $.trim($(inputId).val());
    if (val.length == 0) {
        return null;
    }
    return val;
}
var nib = nullIfBlank;
// mmGrid end
</script>
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
<style type="text/css">
	.course_tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.course_tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
</style>
</head>

<body>
<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content">
	<!-- <h3>岗位关联管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">岗位关联管理</span>
	</div>
   		<div class="course_tree" id="tree" >
   		<h2  >岗位列表</h2>
         <ul id="postTree" class="ztree " >
        </ul>   
   	</div>

    <div class="right_lesson">
    <!--  
      <div class="gl_road">
        	<p>
            	<span id="postName"></span>
                <em id="postPath">默认晋升路径：经理</em>
            </p>
        </div>
     -->
          <div class="button_gr">
        	<input type="button" value="关联必修课程" onclick="selectCourse(2)" class="btn_4" />
        	<input type="button" value="关联选修课程" onclick="selectCourse(1)" class="btn_4" />
            <input type="button" value="批量删除" onclick="deleteSelectedCourses();" />
            <input type="hidden" id ="courseType"/>
        </div>

        <div class="search_3">
            <p> 
                课程名称：
                <input type="text" id="name" />
      
                类型：
                <select id="type">
                    <option value="">显示全部</option>
                    <option value="1">选修</option>
                    <option value="2">必修</option>
                
                </select>
         
            </p>
            <input type="button" value="重置" onclick="clearSearchOptions()" />
            <input type="button" value="查询" class="btn_cx" onclick="reloadmmGrid(null,1)" />

        </div>
        <div class="tab_3">
            <div id="courseTable"></div>
             <div id="paginator" align="right" style="margin-top:10px;" ></div>
        </div>
        
    </div>


</div>
</body>
</html>
