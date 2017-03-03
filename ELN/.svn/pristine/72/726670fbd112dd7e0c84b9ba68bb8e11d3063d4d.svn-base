<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>试卷管理</title>

<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- jQuery UI dataPicker addon -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/addon/jquery-ui-timepicker-addon.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/addon/jquery-ui-timepicker-addon.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4/addon/jquery-ui-timepicker-zh-CN.js" ></script>

<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>

<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script src="<c:url value="/js/layer/layer.js"/>"></script>

<script type="text/javascript">
//zTree part begin
var zTree = null;

<c:if test="${containZTreeContextMenu}">
// ligerMenu
var contextMenu;
</c:if>

$(function(){
	
    reloadZTree();

    contextMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
    [
    { id:'zTreeAddNode',text: '新增分类', click: zTreeAddNode },
    { id: 'zTreeModifyNode', text: '修改分类', click: zTreeModifyNode },
    { id: 'zTreeDeleteNode', text: '删除分类', click: zTreeDeleteNode },
    ]
    });
    
    //时间插件
	$("#paper_create_time_start, #paper_create_time_end").datepicker({
		dateFormat:"yy-mm-dd",
		maxDate: 0,
		changeMonth: true,
        changeYear: true
	});
	/* $("#paper_create_time_start, #paper_create_time_end").datetimepicker({
		dateFormat:"yy-mm-dd", 
	    timeFormat: "HH:mm:ss",
	    maxDate: 0
	}); */
    
	var cols = [
                { title:'id', name:'id', align:'center', hidden:true},
                { title:'试卷名称', name:'title' , align:'center', width:250,
                    renderer: function(val, item, rowIndex){
                    	var content = val;
                    	if (val.length > 21) {
                    		content = val.substring(0, 21) + "…";
                    	}
                        return '<a href="<%=request.getContextPath()%>/exam/paper/pageDetail.action?paperId='+item.id+'" target="_blank"  class="td_a">' + content + '</a>';
                    }},
                { title:'可用/禁用', name:'isEnabled' , align:'center', width:60,
                    renderer: function(val) {
                        return val ? "可用" : "禁用";
                    }},
                { title:'试卷库分类', name:'categoryFullName' , align:'center', width:180,renderer: function(val, item, rowIndex){
                	var content = val;
                	if (val.length > 22) {
                		content = val.substring(0, 22) + "…";
                	}
                    return "<span title='"+val+"' >" + content + "</span>";
                }},
                { title:'最后更新时间', name:'updateTime' , align:'center', width:130, renderer: function(val, item, rowIndex){
                	if (val) {
                		val = val.substring(0, 19);
                	}
                    return val;
                }},
                { title:'操作', name:'id' , align:'center', width:70,
                    renderer: function(val, item, rowIndex) {
                        var deleteStr = '<a href="javascript:void(0);" onclick="deleteQuestion(' + val + ')">删除</a>';
                        var modifyStr = '<a href="javascript:void(0);" onclick="modifyQuestion(' + val + ')">修改</a>';
                        return deleteStr + "&nbsp;&nbsp;" + modifyStr;
                    }},
            ];
    
    $('#questionTable').mmGrid({
    	root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
        cols: cols,
        url: '<c:url value="/exam/paper/voList.action" />',
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
                       totalCountName: 'total',    //对应MMGridPageVoBean count
                       page: 1,                    //初始页
                       pageParamName: 'pageNo',      //当前页数
                       limitParamName: 'pageSize', //每页数量
                       limit: 10,
                       limitList: [10, 20, 50, 100, 200],
                       totalCountLabel: '<span>共{0}条</span>',
                   })
               ],
    });
    
    
    $('#addForm').validator({
		theme : 'yellow_right',
		//msgStyle:"margin-top: 10px;",
		rules: {
	        onlyName: function(element, param, field) {
	        	
	        	var type = $('#categoryId').val();
	        	var val = $.trim($(element).val());
	        	
	        	if(type == ""){
	        		//增加  同一级别下无重复
	        		var selectedNode = getSelectedZTreeNode();
		            if (selectedNode) {
		            	var children = selectedNode.children;
		            	
		            	if(children){
		            		for(var i=0; i<children.length; i++){
			            		if(children[i].name == val){
			            			return '已经存在';
			            		}
			            	}
		            	}
		            	
		            	return true;
		            }else{
		            	return true;
		            }
	        	}else{
	        		//修改
	        		var selectedNode = getSelectedZTreeNode();
		            if (selectedNode) {
		            	var parent = zTree.getNodeByParam("id", selectedNode.parentId, null);
		            	//alert(JSON.stringify(parent));
		            	var children = parent.children;
		            	if(children){
								for(var i=0; i<children.length; i++){
									if(children[i].name == val && val != selectedNode.name){
										return '已经存在';
									}
								}
						}
		            	return true;
		            }else{
		            	return true;
		            }
	        	}
	        	
	        	return true;
	        }
	    },
		fields : {
			categoryName : {
				rule : "required;length[~31];onlyName",
				msg : {
					required : "名称不可为空",
					length : "名称最多30个字符",
					onlyName: "此层级下，类名已经存在"
				}
				//msgStyle :"margin-top:0px;"
			},
			categoryDescription : {
				rule : "required;length[~201]",
				msg : {
					required : "描述不可为空",
					length : "描述最多200个字符"
				}
				//msgStyle :"margin-top:0px;"
			}
		}
	});
    
});


//删除
function deleteQuestion(id) {
    
    dialog.confirm('确认删除吗？', function(){
    	
    	var index = layer.msg('删除中…', {icon:16, time:0});
    	
    	var idList = [];
        idList.push(id);
        deleteQuestions(idList);
        
        layer.close(index);
    });
}

//批量删除
function deleteSelectedQuestions() {
    //被选中的一行
    var selectRows = $('#questionTable').mmGrid().selectedRows();
    
    if(selectRows.length > 0){
        
        dialog.confirm('确认删除吗？', function(){
        	
        	var index = layer.msg('删除中…', {icon:16, time:0});
        	
        	var idList = [];
            $.each(selectRows, function(index, val){
                idList.push(val.id);
            });

            deleteQuestions(idList);
            
            layer.close(index);
        });
    }else{
        dialog.alert('请先选择数据！');
    }
}

//修改试卷
function modifyQuestion(id){
	window.location.href = "<%=request.getContextPath()%>/exam/paper/pageModify.action?id="+id;
}

function deleteQuestions(idList) {
    $.ajax({
        type:"POST",
        //async:true,  //默认true,异步
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(idList),
        url:'<c:url value="/exam/paper/delete.action"/>',
        success:function(data){
            reloadmmGrid();
        }
    });
}

//表格查询参数
function getSearchOption() {
    var searchOption = {
        "title":escapeForSql(nib('#paper_title')),
        "isEnabled":nib('#isEnabled')
    };
    
    if(nib('#paper_create_time_start')){
    	searchOption["createTimeBegin"] = nib('#paper_create_time_start') + " 00:00:00";
    }
    
    if(nib('#paper_create_time_end')){
    	searchOption["createTimeEnd"] = nib('#paper_create_time_end') + " 23:59:59";
    }
    
    if (zTree != null) {
        var nodes = zTree.getSelectedNodes();
        if (nodes && nodes.length>0) {
            searchOption['categoryId'] = nodes[0]['id'];
        }
    }
    removeNullElements(searchOption);
    
    return searchOption;
}

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
            showTitle: true,
            selectedMulti: false,
			addDiyDom: addDiyDom
            // addHoverDom: addHoverDom,
            // removeHoverDom: removeHoverDom
        },
        callback: {
            onRightClick: zTreeOnRightClick,
            onClick: zTreeOnClick
        }
    };

    // var categoryTree = ${categoryTree};
    // //alert(categoryTree.length);
    // categoryTree.unshift({id:0, name:"所有菜单"});

    $.ajax({
        type:"POST",
        //async:false,  //默认true,异步
        data:null,
        url:'<%=request.getContextPath()%>/exam/paperCategory/list.action',
        success:function(categoryTree){
        	//$("body").append(JSON.stringify(categoryTree));
        	//alert(JSON.stringify(categoryTree));
        	//categoryTree.push({"id":null, parentId:"00", name:"试卷库分类"});
		    //categoryTree.push({"id":null, "name":"公司"});
        	
            //addIconInfo(categoryTree);
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            //全部展开
            zTree.expandAll(true);
            
            //默认选中第一个数节点
            //zTree.checkNode(zTree.getNodeIndex(0), true, true);
        }
    });
}

//树的图标
function addIconInfo(categoryTree) {
    for (var idx in categoryTree) {
        categoryTree[idx]['icon'] = '<c:url value="/images/img/ico_txt.png" />';
        categoryTree[idx]['iconOpen'] = '<c:url value="/images/img/ico_sq.png" />';
        categoryTree[idx]['iconClose'] = '<c:url value="/images/img/ico_zk.png" />';
    }
}

//获取树的被选中节点
function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
}

//树的右击
function zTreeOnRightClick(event, treeId, treeNode) {
    if (treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);
        contextMenu.show({top: event.pageY, left: event.pageX});
        return false;
    }
}

//树的右击, 菜单方法  新增
function zTreeAddNode() {
    var selectedNode = getSelectedZTreeNode();
    if (selectedNode) {
        $('#categoryId').val('');
        $('#categoryParentId').val(selectedNode['id']);
        $('#editCategoryBlock').find("td").eq(1).text(selectedNode['name']);
        $('#categoryName').val('');
        $('#categoryDescription').val('');
        $('#categorySaveBtn').attr("onclick", "addCategory();");
        
        //清除验证
        $('#addForm').validator("cleanUp");
        
        dialog({
            title: '新增分类',
            content: $("#editCategoryBlock"),
            okValue: '保存',
            ok: function () {
            	
            	var thisDialog = this;
            	
            	$('#addForm').isValid(function(v) {
        			if(v){
        				//thisDialog.title('保存中…');
						var index = layer.msg('保存中…', {icon:16, time:0});
        				
						addCategory();
						layer.close(index);
                        thisDialog.close().remove();
						
        			}
        		});
            	
            	return false;
            },
            cancelValue: '取消',
            cancel: function () {}
        }).showModal();
    }
}

//树的右击, 菜单方法  新增
function addCategory() {
    $.ajax({
        type:"POST",
        async:false,  //默认true,异步
        data:getCategoryInfo(),
        url:'<%=request.getContextPath()%>/exam/paperCategory/manage/add.action',
        success:function(data){
            if (data == 'SUCCESS') {
                reloadAllComponents();
            }
        }
    });

}

//获得节点参数
function getCategoryInfo() {
    var category = {
        "id":nib('#categoryId'),
        "parentId":nib('#categoryParentId'),
        "name":nib('#categoryName'),
        "description":nib('#categoryDescription'),
    };
    removeNullElements(category);
    return category;
}

//清理节点
function removeNullElements(data) {
    for (var key in data) {
        if (data[key] == null) {
            delete data[key];
        }
    }
}

//树的右击, 菜单方法  修改
function zTreeModifyNode() {
    var selectedNode = getSelectedZTreeNode();
    if (selectedNode) {
        $('#categoryId').val(selectedNode['id']);
        $('#categoryParentId').val('');
        
        //alert(selectedNode['parentId']);
        var parentNode = zTree.getNodeByParam("id", selectedNode['parentId'], null);
        $('#editCategoryBlock').find("td").eq(1).text(parentNode['name']);
        
        $('#categoryName').val(selectedNode['name']);
        $('#categoryDescription').val(selectedNode['description']);
        $('#categorySaveBtn').attr("onclick", "modifyCategory();");
        
        //清除验证
        $('#addForm').validator("cleanUp");
        
        dialog({
            title: '修改分类',
            content: $("#editCategoryBlock"),
            okValue: '保存',
            ok: function () {
            	
            	var thisDialog = this;
            	
            	$('#addForm').isValid(function(v) {
        			if(v){
        				//thisDialog.title('保存中…');
        				var index = layer.msg('保存中…', {icon:16, time:0});
						
        				modifyCategory();
        				
        				layer.close(index);
                        thisDialog.close().remove();
        			}
        		});
            	
            	return false;
            },
            cancelValue: '取消',
            cancel: function () {}
        }).showModal();
    }
}

function modifyCategory() {
    $.ajax({
        type:"POST",
        async:false,  //默认true,异步
        data:getCategoryInfo(),
        url:'<%=request.getContextPath()%>/exam/paperCategory/manage/modify.action',
        success:function(data){
            if (data == 'SUCCESS') {
                reloadAllComponents();
            }
        }
    });

}

//树的右击, 菜单方法  删除
function zTreeDeleteNode() {
    var selectedNode = getSelectedZTreeNode();
    if (!selectedNode.id) {
        layer.alert('不可以删除公司节点(根节点)！');
        return false;
    }
    if (selectedNode) {

        dialog.confirm('确认删除吗？', function(){

            var index = layer.msg('保存中…', {icon:16, time:0});

            param = {'categoryId':selectedNode['id']};
            $.ajax({
                type:"POST",
                async:false,  //默认true,异步
                data: param,
                url:'<%=request.getContextPath()%>/exam/paperCategory/manage/delete.action',
                success:function(data){
                    if (data == 'SUCCESS') {
                        reloadAllComponents();
                        layer.close(index);
                    } else {
                        layer.close(index);
                        dialog.alert("请先删除本卷库下的试卷及子卷库！");
                    }
                }
            });

        });
    }
}

//数的左击
function zTreeOnClick(event, treeId, treeNode) {
    selectCategory(treeNode);
}

//数的左击
function selectCategory(category) {
	//表格查询重置 
    clearSearchOptions();
	// 重新查询表格
    reloadmmGrid({'categoryId': category['id']}, 1);
}

//重新加载表格
function reloadmmGrid(otherOptions, page) {
    var searchOption = getSearchOption();
    
    if(page){
    	searchOption['pageNo'] = page;
    }
    
    if (otherOptions) {
        for (var key in otherOptions) {
            searchOption[key] = otherOptions[key];
        }
    }
    removeNullElements(searchOption);
    $('#questionTable').mmGrid().load(searchOption);
}

//表格查询重置
function clearSearchOptions() {
    var idList = ['#paper_title', '#paper_create_time_start', '#paper_create_time_end', '#isEnabled'];
    for (var idx in idList) {
        $(idList[idx]).val('');
    }
    reloadmmGrid(null, 1);
}

//所有数据重置
function reloadAllComponents() {
	//树重置
    reloadZTree();
  	//表格查询重置 
    clearSearchOptions();
    //重新查询表格
    reloadmmGrid(null, 1);
}

//获得文本框值得方法
function nullIfBlank(inputId) {
    var val = $.trim($(inputId).val());
    if (val.length == 0) {
        return null;
    }
    return val;
}
var nib = nullIfBlank;

//新增试卷
function addPaper(){
	window.location.href = "<%=request.getContextPath()%>/exam/paper/pageAdd.action";
}
</script>
<style type="text/css">

/* ztree begin */
.ztree{padding:3px;}
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:98%;height:30px;padding-top: 0px;border-bottom: 1px dotted #ccc;}
.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
.ztree li a span.button.switch {visibility:visible}
.ztree.showIcon li a span.button.switch {visibility:visible;}
.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}

.ztree li span.button{	background: url("") 0 0 no-repeat;}
.ztree li span.button.center_close,.ztree li span.button.root_close,.ztree li span.button.roots_close,.ztree li span.button.bottom_close  {
	background: url(<%=request.getContextPath()%>/images/img/ico_zk.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}
.ztree li span.button.center_open,.ztree li span.button.root_open,.ztree li span.button.roots_open,.ztree li span.button.bottom_open{
	background: url(<%=request.getContextPath()%>/images/img/ico_sq.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}
.ztree li span.button.ico_docu{
	background: url(<%=request.getContextPath()%>/images/img/ico_txt.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}

.ztree li span.button.ico_close{
	display: none;
}
.ztree li span.button.ico_open{
	display: none;
}
.ztree li ul.line{
	background: url("");
}
/* ztree end */

.n-arrow{display: none;}

.node_table td{
	padding:2px;
}
</style>
</head>

<body>

<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content" style="margin-top:20px;padding-bottom:10px;">
	<!-- <h3>试卷管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">试卷管理</span>
	</div>
   	<div class="" style="width:230px;border:1px solid #333333; float:left; font-family:'微软雅黑';" >
   		<h2 style="font-size:14px; width:230px; background:#333333; color:#fff; text-align:center; height:30px; line-height:30px; font-weight:normal;" >试卷库分类</h2>
        <ul id="categoryTree" class="ztree" >
        </ul>   
   	</div>

    <div class="right_lesson" style="padding-bottom:0px;">
        <!-- <div class="cs_list">
           <h4>我的试卷</h4>
           <div class="border" style="width:688px;"></div>
        </div> -->
        <div class="button_gr" style="height:30px;line-height:30px;">
            <input type="button" value="新增试卷" onclick="addPaper();" />
            <input type="button" value="批量删除" onclick="deleteSelectedQuestions();" />
        </div>
        <div class="search_3" style="margin-top:16px;">
            <p> 
                试卷名称：
                <input type="text" id="paper_title" />
                创建时间：
                <input type="text" id="paper_create_time_start" />
                至：
               	<input type="text" id="paper_create_time_end" />
            </p>
            <input type="button" value="重置" onclick="clearSearchOptions()" />
            <input type="button" value="查询" class="btn_cx" onclick="reloadmmGrid('', 1)" />

        </div>
        <div class="search_3" style="margin-top:-1px;">
            <p> 
                可用/禁用：
                <select id="isEnabled">
                    <option value="">显示全部</option>
                    <option value="1">可用</option>
                    <option value="0">禁用</option>
                </select>
               
            </p>
            

        </div>
        
        <div style="clear:both;" > </div>
        <div class="" style="" >
            <div id="questionTable"></div>
            <div id="paginator" align="right" style="margin-top:10px;" ></div>
        </div>
        
        
    </div>
	
	<div id="editCategoryBlock" style="display:none;" align="left">
		<form id="addForm" >
           <table class="node_table" cellpadding="0" cellspacing="0" style="width:400px;">
           	   <tr>
                   <td align="right" style="width:80px;">父节点：</td>
                   <td align="left">
                       
                   </td>
               </tr>
               <tr>
                   <td align="right">名称：</td>
                   <td align="left">
                       <input type="text" id="categoryName" name="categoryName" class="input_0" style="width:140px;" maxlength="30" />
                   </td>
                   <td>
                       <span style="color:red;margin-left:10px;"></span>
                   </td>
               </tr>
               <tr>
                   <td colspan="3" style="height:5px;"></td>
               </tr>
               <tr id="descTr">
                   <td align="right">描述：</td>
                   <td align="left">
                       <!-- <input type="text" id="categoryDescription" name="categoryDescription" class="input_0" style="width:140px;" /> -->
                       <textarea id="categoryDescription" name="categoryDescription" class="input_0" style="width:140px;height:100px;"  ></textarea>
                   </td>
                   <td>
                       <span style="color:red;margin-left:10px;" ></span>
                   </td>
               </tr>
           </table>
        </form>
           <!-- <div align="right" style="margin-top:10px;">
               <button class="btu_0" style="margin-right:5px;" id="categorySaveBtn">保存</button>
               <button class="btu_0" style="margin-right:10px;"  onclick="closeWin()" >取消</button>
           </div> -->
           
           <input type="hidden" id="categoryId" />
           <input type="hidden" id="categoryParentId" />
       </div>
</div>
</body>
</html>
