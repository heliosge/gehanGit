<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>岗位体系管理</title>
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

<style type="text/css">
	.tree {overflow-y:auto;overflow-x:auto;width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
	.ztree li span.button.noline_docu{display:none;}
	.ztree li span.button.noline_open{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
	.ztree li span.button.noline_close{z-index:999;position: relative;margin-right: -18px;background-image:url("")}
</style>
<script type="text/javascript">
// zTree part begin
var zTree = null;


$(function(){
    reloadZTree();
});

function reloadZTree() {
    //zTree配置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                pIdKey: "parentId",
                idKey: "id",
                rootPid: null
            },
            // key: {
            //     url: "aa"
            // }
        },
        view: {
            selectedMulti: false,
            showTitle: false,
            showLine: false,
            dblClickExpand: true,
            // addHoverDom: addHoverDom,
            // removeHoverDom: removeHoverDom
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

    // var categoryTree = ${categoryTree};
    // //alert(categoryTree.length);
    // categoryTree.unshift({id:0, name:"所有菜单"});

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

function addIconInfo(categoryTree) {
    for (var idx in categoryTree) {
        categoryTree[idx]['icon'] = '<c:url value="/images/img/ico_txt.png" />';
        categoryTree[idx]['iconOpen'] = '<c:url value="/images/img/ico_sq.png" />';
        categoryTree[idx]['iconClose'] = '<c:url value="/images/img/ico_zk.png" />';
    }
}

function zTreeOnClick(event, treeId, treeNode) {
    selectCategory(treeNode);
}

function selectCategory(category) {
    reloadmmGrid({'postId': category['id']});
}



// zTree part end

// mmGrid begin


$(document).ready(function(){
    var cols = [
                { title:'id', name:'id', align:'center', hidden:true},
                /**{ title:'课程编号', name:'code' , align:'center'},**/
                { title:'课程名称', name:'name' , align:'center'},
                { title:'已学人数', name:'learningNum' , align:'center'},
               
            ];
    
    $('#courseTable').mmGrid({
    	root : 'list',// 和后台数据保存列一致,对应MMGridPageVoBean rows
        cols: cols,
        url: '<c:url value="/post/courseList.action" />',
        params: getSearchOption,
        method: 'post',
        checkCol: false,
        multiSelect: false,
        fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
        showBackboard : false,
        width: 'auto',
        height: '440px',
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
    

});









function removeNullElements(data) {
    for (var key in data) {
        if (data[key] == null) {
            delete data[key];
        }
    }
}

function getSearchOption() {
    var searchOption = {
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

function reloadmmGrid(otherOptions) {
    var searchOption = getSearchOption();
    searchOption['pageIndex'] = 1;
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

function save(){
	 if (zTree != null) {
	        var nodes = zTree.getSelectedNodes();

	        if (nodes && nodes.length>0) {
	        	if($('#courseTable').mmGrid().rowsLength()<=0){
		        	dialog.alert('岗位没有关联课程！');
		        	return;
		        }
	        	var node =nodes[0];
	        	var param={};
	        	param.postId = node.id;
	        	param.pathId = window.parent.$("#pathId").val();
	        	$.ajax({
	    		type:"POST",
	    		async:true,  //默认true,异步
	    		data:param,
	    		//dataType:"json",
	    		url:'<c:url value="/map/checkPathStage.action" />',
	    		success:function(data){
	    			if(data == "SUCCESS"){
	    				window.parent.addStage(nodes[0].id,nodes[0].name,$("#learnScoreSum").val());
	    				window.parent.artDialog.close();
	    				
	    			}else{
	    				dialog.alert('路径下已存在该岗位！');
	    			}
	    		
			    	
	    	    }
	    	});
	        	
	        }else{
	        	dialog.alert("请先选择一个岗位");
	        	return;
	        }
	    }else{
	    	dialog.alert("岗位树错误！");
	    }
	

}
// mmGrid end
</script>

</head>

<body>
<div class="content" style="margin-top: 0px;padding-bottom:0px;">
 <div class="content_2">
        <div class="tree">
           <h2>岗位列表</h2>
          <ul id="postTree" class="ztree"></ul>
        </div>
        <div class="right_lesson" >
          <div class="search_3">

            	<input type="button" value="确定" class="btn_cx" onclick="save();" />
               
        
        </div>
         <div id="courseTable" style="margin-top:10px;width:100%"></div>
             <div id="paginator" align="right" style="margin-top:10px;" ></div>

    </div>
    </div>
    </div>

</body>
</html>
