<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>试题管理</title>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>

<!-- validator -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

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

<!-- layer -->
<script src="<c:url value="/js/layer/layer.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />

<script type="text/javascript">
// zTree part begin
var zTree = null;
var zTreeYun = null;

var ztreeJiTuanMap = {};

var zTreeMy = null;

//被选中的云题目
var selectedQes = new Object;

<c:if test="${containZTreeContextMenu}">
// ligerMenu
var contextMenu;
var validator;
</c:if>

$(function(){
    reloadZTree();

<c:if test="${containZTreeContextMenu}">
    contextMenu = $.ligerMenu({ top: 100, left: 100, width: 120, items:
    [
    { id:'zTreeAddNode',text: '新增分类', click: zTreeAddNode },
    { id: 'zTreeModifyNode', text: '修改分类', click: zTreeModifyNode },
    { id: 'zTreeDeleteNode', text: '删除分类', click: zTreeDeleteNode },
    ]
    });
    
    contextMenu2 = $.ligerMenu({ top: 100, left: 100, width: 120, items:
        [
        { id: 'zTreeModifyNode', text: '修改分类', click: zTreeModifyNode },
        { id: 'zTreeDeleteNode', text: '删除分类', click: zTreeDeleteNode },
        ]
    });
</c:if>
	
	//验证类名唯一
	$.validator.addMethod(
	    "onlyName",
	    function (value, element, param){
	    	/* var length = value.length; 
	    	var mobile = /^[0-9]{11}$/;
	    	return this.optional(element) || (length == param && mobile.test(value));  */
	    	
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
		            			return false;
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

	            	var children = parent.children;

	            	if(children){
							for(var i=0; i<children.length; i++){
								if(children[i].name == val && val != selectedNode.name){
									return false;
								}
							}
					}
	            	
	            	return true;
	            }else{
	            	return true;
	            }
        	}
        	
        	return true;
	    },
	    "已经存在"
	);
	
    validator = $('#editCategoryForm').validate({
        focusCleanup:true,
        ignore:null,
        rules: {
        	categoryName: {
        		required: true,
        		maxlength: 30,
        		onlyName: true
        	},
        	categoryDescription: {
        		maxlength: 200
        	}
        },
        messages: {
        	categoryName: {
        		required: '不能为空',
        		maxlength: '不能超过30字符',
        		onlyName: "此层级下，类名已经存在"
        	},
        	categoryDescription: {
        		maxlength: '不能超过200字符'
        	}
        }
    });
    
    //试题库导入
    var btuType = 0;
    $('.loadQuestionBtu').on('click', function() {
        
        //1：试题库导入  2:新增试题
        btuType = $(this).attr("btuType");
        $('#loadQuestion_div div').show();
        
    
        if(btuType == 3){
            $('#loadQuestion_div #loadQuestionIframe').height(300);
            $('#loadQuestion_div #loadQuestionIframe').attr("src", "<%=request.getContextPath()%>/exam/paper/paperFileInput.action");
            
            dialogTitle = "导入试题";
            dialogArea = ['500px', '340px'];
            
            $('#loadQuestion_div div').hide();
        }else if(btuType == 4){
            //下载模板
            window.location.href = "<%=request.getContextPath()%>/file/exam_paper_example.zip";
            return true;
        }
        
        loadQuestionDialog = layer.open({
            title: dialogTitle,
            type: 1,
            area: dialogArea,
            skin: 'layui-layer-rim',
            shadeClose: true, //点击遮罩关闭
            content: $('#loadQuestion_div')
        });
    });
    
    $('#loadQuestion_close').click(function () {
        layer.close(loadQuestionDialog);
    });
    
    $(".my_tab").click(function(){
    	
    	if($(this).hasClass("my_tab_selected")){
    		//已经被选中
    	}else{
    		//切换
    		$(".my_tab_selected").removeClass("my_tab_selected");
    		$(this).addClass("my_tab_selected");
    		
    		var forFlag = $(this).attr("for");
    		
    		$(".yun_ti").hide();
    		$(".yun_ti[for='"+forFlag+"']").show();
    		
    		var checkbox = $(".mmg-backboard").find("span:contains('操作')").prev();
    		
    		if(forFlag == "yun" || forFlag.indexOf("jiTuan") != -1){
    			//云库   集团
    			//$('#questionTable').mmGrid().hideCol("操作");
				
    			if(checkbox.prop("checked")){
    				//不显示操作
    				checkbox.click();
    			}
    			
    			$('#questionTable').mmGrid()._fullWidthRows();
    			//$('#questionTable').mmGrid().resize();
    		}else{
    			
    			if(!checkbox.prop("checked")){
    				//显示操作
    				checkbox.click();
    			}
    			
    			$(".mmg-backboard").find("span:contains('操作')").prev().prop("checked", true);
    			$('#questionTable').mmGrid()._fullWidthRows();
    			//$('#questionTable').mmGrid().resize();
    			//$('#questionTable').mmGrid()._setColsWidth();
    		}
    		
    		//刷新表格
    		reloadmmGrid(null, 1);
    	}
    });
});

// Used by paper_file_input.jsp begin
// 以下函数是paper_file_input.jsp试题导入成功后调用的函数
// 通过编写自己的版本，就不需要修改上述文件的实现了
// 在上述文件的处理逻辑发生变更的时候，需要对应修改这里的内容（增减函数等）
function showQuestion() {
	// do nothing
}
function showQuestionInfo() {
	reloadAllComponents();
}
// Used by paper_file_input.jsp end

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
            showTitle: true,
            addDiyDom: addDiyDom
        },
        callback: {
<c:if test="${containZTreeContextMenu}">
            onRightClick: zTreeOnRightClick,
</c:if>
            onClick: zTreeOnClick
        }
    };
    
    <c:forEach var="company" items="${companyList}" varStatus="status" >
		<c:choose>
			<c:when test="${company.id == USER_BEAN.subCompanyId}" ></c:when>
			<c:when test="${company.name == '云'}" >
				//云试题库
			    $.ajax({
			        type:"POST",
			        async:false,  //默认true,异步
			        data:null,
			        url:'<c:url value="/exam/questionCategory/listYun.action"/>',
			        success:function(categoryTree){
			            //addIconInfo(categoryTree);
			            zTreeYun = $.fn.zTree.init($("#categoryTreeYun"), setting, categoryTree);
			            expandZTree(zTreeYun, 1);
			        }
			    });
			</c:when>
			<c:otherwise>
				//集团试题库
			    $.ajax({
			        type:"POST",
			        async:false,  //默认true,异步
			        data: {"subCompanyId":"${company.id}"},
			        url:'<c:url value="/exam/questionCategory/listJiTuanSubCompany.action"/>',
			        success:function(categoryTree){
			            //addIconInfo(categoryTree);
			            var thisTree = $.fn.zTree.init($("#categoryTreeJiTuan_${company.id}"), setting, categoryTree);
			            
			            ztreeJiTuanMap["treeJiTuan_${company.id}"] = thisTree;
			            
			            expandZTree(thisTree, 1);
			        }
			    });
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	 $.ajax({
	        type:"POST",
	        //async:true,  //默认true,异步
	        data:null,
	        url:'<c:url value="/exam/questionCategory/list.action"/>',
	        success:function(categoryTree){
	            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
	            expandZTree(zTree, 1);
				
	            if($("._left_tree .yun_ti").length > 1){
	            	//我的试题库，用于加入到我的试题库
	          		setting.callback.onClick = null;
	          		zTreeMy = $.fn.zTree.init($("#categoryTreeMy"), setting, categoryTree);
	            	expandZTree(zTreeMy, 1);
	            }
	        }
	    });
}

function zTreeOnClick(event, treeId, treeNode) {
    selectCategory(treeNode);
}

function selectCategory(category) {
	//alert(JSON.stringify(category));
    clearSearchOptions();
    reloadmmGrid({'categoryId': category['id']}, 1);
}

<c:if test="${containZTreeContextMenu}">
function zTreeOnRightClick(event, treeId, treeNode) {
	
	if($("._left_tree .yun_ti").length > 1){
		
		var forStr = $(".my_tab_selected").attr("for");
		
		//云试题库 集团库  不可右键修改
		if(forStr == "yun" || forStr.indexOf("jiTuan") != -1){
			return false;
		}
		
		if(treeNode.getParentNode() && treeNode.getParentNode().getParentNode()){
			contextMenu2.show({top: event.pageY, left: event.pageX});
			return false;
		}
    }
	
    if (treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);
        contextMenu.show({top: event.pageY, left: event.pageX});
        return false;
    }
}

function getSelectedZTreeNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        return nodes[0];
    }
    return null;
}

function reloadAllComponents() {
    reloadZTree();
    clearSearchOptions();
    reloadmmGrid(null, 1);
}

function zTreeAddNode() {
    var selectedNode = getSelectedZTreeNode();
    if (selectedNode) {
        $('#categoryId').val('');
        /* $('#categoryParentName').val(selectedNode.name); */
        $('#editCategoryBlock').find("td").eq(1).text(selectedNode.name);
        $('#categoryParentId').val(selectedNode['id']);
        $('#categoryName').val('');
        $('#categoryDescription').val('');
        $('#categorySaveBtn').attr("onclick", "addCategory();");
        
        // Clear validation msgs from previous operations
        validator.resetForm();
        
        dialog({
            title: '新增分类',
            content: $("#editCategoryBlock"),
            okValue: '保存',
            ok: function() {
			    if (validator.form()) {
			        addCategory();
			        this.close();
			    }
			    return false;
            },
            cancelValue: '取消',
            cancel: function() {}
        }).showModal();
    }
}

function zTreeModifyNode() {
    var selectedNode = getSelectedZTreeNode();
    if (!selectedNode.id) {
    	layer.alert('不可以修改公司节点(根节点)！');
    	return false;
    }
    if (selectedNode) {
        $('#categoryId').val(selectedNode['id']);
        //$('#categoryParentName').val(selectedNode.getParentNode().name);
        $('#categoryParentId').val('');
        $('#editCategoryBlock').find("td").eq(1).text(selectedNode.getParentNode().name);
        $('#categoryName').val(selectedNode['name']);
        $('#categoryDescription').val(selectedNode['description']);
        $('#categorySaveBtn').attr("onclick", "modifyCategory();");
        
        // Clear validation msgs from previous operations
        validator.resetForm();
        
        dialog({
            title: '修改分类',
            content: $("#editCategoryBlock"),
            okValue: '保存',
            ok: function() {
                if (validator.form()) {
                    modifyCategory();
                    this.close();
                }
                return false;
            },
            cancelValue: '取消',
            cancel: function() {}
        }).showModal();
    }
}

function zTreeDeleteNode() {
    var selectedNode = getSelectedZTreeNode();
    if (!selectedNode.id) {
        layer.alert('不可以删除公司节点(根节点)！');
        return false;
    }
    if (selectedNode) {
        dialog.confirm('确认删除吗？', function() {
            param = {'categoryId':selectedNode['id']};
            $.ajax({
                type:"POST",
                async:false,  //默认true,异步
                data: param,
                url:'<c:url value="/exam/questionCategory/manage/delete.action"/>',
                success:function(data){
                    if (data == 'SUCCESS') {
                        reloadAllComponents();
                    } else {
                        dialog.alert('请先删除本题库下的试题及子题库！');
                    }
                }
            });
        });
    }
}

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

function addCategory() {
    $.ajax({
        type:"POST",
        async:false,  //默认true,异步
        data:getCategoryInfo(),
        url:'<c:url value="/exam/questionCategory/manage/add.action"/>',
        success:function(data){
            if (data == 'SUCCESS') {
            	dialog.alert('保存成功!');
                reloadAllComponents();
            } else {
                dialog.alert('保存失败!');
            }
        }
    });
}
function modifyCategory() {
    $.ajax({
        type:"POST",
        async:false,  //默认true,异步
        data:getCategoryInfo(),
        url:'<c:url value="/exam/questionCategory/manage/modify.action"/>',
        success:function(data){
            if (data == 'SUCCESS') {
                dialog.alert('保存成功!');
                reloadAllComponents();
            } else {
                dialog.alert('保存失败!');
            }
        }
    });
}
</c:if>
// zTree part end

// mmGrid begin
var toDisplayTypeStr = function(val) {
	var displayTypeMap = {
			1: "主观题",
			2: "单选题",
			3: "多选题",
			4: "判断题",
			5: "填空题",
			6: "组合题",
			7: "多媒体题",
	};
	return displayTypeMap[val];
};

var toDifficultyLevelStr = function(val) {
    var map = {
            1: "易",
            2: "中",
            3: "难",
    };
    return map[val];
};

function limitLength(str, item, rowIndex, maxLength) {
	var _maxLength = maxLength;
	if (!_maxLength) {
		_maxLength = 10;
	}
	var result = str.length > _maxLength ? content = str.substring(0, _maxLength) + "…" : str;
	return '<span title="'+str+'">'+result+'</span>';
}

$(document).ready(function(){
    var cols = [
                { title:'id', name:'id', align:'center', hidden:true},
                /* {title: '<input type="checkbox" class="my_checkAll" >', name: 'id', width: 22, align: 'center' ,lockWidth: true, checkCol: true, renderer:function(val, item, rowIndex){
              	   
                	return '<input type="checkbox" class="my_mmg_check" value="'+val+'" >';
                }}, */
                { title:'题干', name:'content' , align:'center', width: 200,
                    renderer: function(val, item, rowIndex){
                    	var $title = $("<div>"+val+"</div>");
                    	$title.find("img").after(" 图片 ").remove();
                    	//alert($title.html());
                    	
                        return '<a href="<c:url value="/exam/question/toDetail.action?questionId=' + item['id'] + '"/>" class="td_a" target="_blank" title="'+$title.html()+'" >' + val + '</a>';
                    }},
                { title:'题型', name:'displayType' , align:'center', width: 50, lockWidth:true, renderer: toDisplayTypeStr},
                { title:'难度', name:'difficultyLevel' , align:'center', width: 30, lockWidth:true, renderer: toDifficultyLevelStr},
                { title:'可用/禁用', name:'isEnabled' , align:'center', width: 55, lockWidth:true,
                    renderer: function(val) {
                        return val ? "可用" : "禁用";
                    }},
                { title:'试题库分类', name:'categoryFullName' , align:'center', width: 100, renderer: function(val, item, rowIndex){
                    return '<span  title="'+val+'" >' + val + '</span>';
                }},
                { title:'操作', name:'id' , align:'center', width: 68, lockWidth:true,
                    renderer: function(val, item, rowIndex) {
                        var deleteStr = '<a href="javascript:void(0);" onclick="deleteQuestion(' + val + ')">删除</a>';
                        var modifyStr = '<a href="<c:url value="/exam/question/toModify.action?questionId=' + val + '"/>">修改</a>';
                        return deleteStr + modifyStr;
                    }}
            ];
    
    $('#questionTable').mmGrid({
    	root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
        cols: cols,
        url: '<c:url value="/exam/question/${listSource}.action" />',
        params: getSearchOption,
        method: 'post',
        checkCol: true,
        multiSelect: true,
        fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
        showBackboard : false,
        nowrap: true,
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
                       limitList: [10, 20, 50, 100, 200]
                       //totalCountLabel: '<span>共{0}条</span>',
                   })
               ],
    });
    
    /* $('#questionTable').mmGrid().on('loadSuccess', function(e, data){
        //加载数据成功后触发
        //分页，数据加载后，取消全选
        //$(".mmGrid .my_checkAll").prop('checked', false);
        
        //云试题库
		if($(".my_tab_selected[for='yun']").length > 0){
			$(".my_mmg_check").each(function(index, val){
				var questionId = $(val).attr("value");
				if(selectedQes["questionId_" + questionId]){
					$(val).prop('checked', true);
				};
			});
	    	
	    	if($(".my_mmg_check").length == $(".my_mmg_check:checked").length){
	    		//全选
	    		$(".mmGrid .my_checkAll").prop('checked', true);
	    	}else{
	    		$(".mmGrid .my_checkAll").prop('checked', false);
	    	}	
		}
     });
    
  	//checkbox 全选
	$(".mmGrid .my_checkAll").change(function(){
		
		//云试题库
		if($(".my_tab_selected[for='yun']").length > 0){
			if($(this).prop('checked')){
				//全选
				$(".my_mmg_check").prop('checked', true);
				
				$(".my_mmg_check:checked").each(function(index, val){
					var questionId = $(val).attr("value");
					selectedQes["questionId_" + questionId] = {"id":questionId};
				});
				
			}else{
				
				$(".my_mmg_check:checked").each(function(index, val){
					var questionId = $(val).attr("value");
					delete selectedQes["questionId_" + questionId];
				});
				
				//全选取消
				$(".my_mmg_check").prop('checked', false);
			}	
		}
	});
  	
  	//选择试题
  	$('#questionTable').mmGrid().on("click", ".my_mmg_check", function(){
		
  		//云试题库
		if($(".my_tab_selected[for='yun']").length > 0){
			var questionId = $(this).attr("value");
			
			if($(this).prop('checked')){
				selectedQes["questionId_" + questionId] = {"id":questionId};
			}else{
				delete selectedQes["questionId_" + questionId];
			}	
		}
	}); */
  	
});

function addQuestion() {
    var selectedNode = getSelectedZTreeNode();
    if (!selectedNode || !selectedNode.id) {
        layer.alert('请选择题库！不可以向公司节点(根节点)添加试题！');
        return false;
    }
	location.href = '<c:url value="/exam/question/toAdd.action"/>' + '?categoryId=' + selectedNode.id;
}

function deleteQuestion(id) {
    dialog.confirm('确认删除吗？', function(){
        var idList = [];
        idList.push(id);
        deleteQuestions(idList);
    });
}

function deleteSelectedQuestions() {
    //被选中的一行
    var selectRows = $('#questionTable').mmGrid().selectedRows();
    
    if(selectRows.length > 0){
        dialog.confirm('确认删除吗？', function(){
            var idList = [];
            $.each(selectRows, function(index, val){
                idList.push(val.id);
            });

            deleteQuestions(idList);
        });
    }else{
        dialog.alert('请先选择数据！');
    }
}

function deleteQuestions(idList) {
    $.ajax({
        type:"POST",
        //async:true,  //默认true,异步
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(idList),
        url:'<c:url value="/exam/question/delete.action"/>',
        success:function(data){
            reloadmmGrid();
        }
    });
}

function clearSearchOptions() {
    var idList = ['#questionContent', '#questionDisplayType', '#difficultyLevel', '#isEnabled'];
    for (var idx in idList) {
        $(idList[idx]).val('');
    }
    
    reloadmmGrid(null, 1);
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
        "content":escapeForSql(nib('#questionContent')),
        "questionDisplayType":nib('#questionDisplayType'),
        "difficultyLevel":nib('#difficultyLevel'),
        "isEnabled":nib('#isEnabled'),
    };
    
  	//云试题库
	if($(".my_tab_selected[for='yun']").length > 0){
		
		if (zTreeYun != null) {
	        var nodes = zTreeYun.getSelectedNodes();
	        if (nodes && nodes.length>0) {
	            searchOption['categoryId'] = nodes[0]['id'];
	        }
    	}
    	
    	searchOption["subCompanyId"] = 1;
    	searchOption["companyId"] = 1;
    	
	}else if($(".my_tab_selected[for^='jiTuan']").length > 0){
		
		//ztreeJiTuanMap["treeJiTuan_${company.id}"] 
		var subCompanyId = $(".my_tab_selected").attr("for").split("_")[1];
		
		//集团试题库
		if (ztreeJiTuanMap["treeJiTuan_"+subCompanyId]  != null) {
	        var nodes = ztreeJiTuanMap["treeJiTuan_"+subCompanyId].getSelectedNodes();
	        if (nodes && nodes.length>0) {
	            searchOption['categoryId'] = nodes[0]['id'];
	        }
    	}
    	
    	searchOption["subCompanyId"] = subCompanyId;
	}else{
	
		//我的试题库
	   	if (zTree != null) {
	        var nodes = zTree.getSelectedNodes();
	        if (nodes && nodes.length>0) {
	            searchOption['categoryId'] = nodes[0]['id'];
	        }
	   	}
	}
    
    removeNullElements(searchOption);
    return searchOption;
}

function reloadmmGrid(otherOptions, page) {
    var searchOption = getSearchOption();
    //searchOption['pageNo'] = 1;
    
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

function nullIfBlank(inputId) {
    var val = $.trim($(inputId).val());
    if (val.length == 0) {
        return null;
    }
    return val;
}
var nib = nullIfBlank;


//加入我的题库
function addToMyQuestion(){
	
	//被选中行
    var selectRows = $('#questionTable').mmGrid().selectedRows();
	if(selectRows.length == 0){
		dialog.alert("请先选择要导入自己题库的试题!");
    	return false;
	}
	
	//弹窗选择题库
	dialog({
        title: '保存到我的题库',
        content: $("#categoryTreeContainerMy"),
        okValue: '保存',
        ok: function() {
            
        	var nodes = zTreeMy.getSelectedNodes();
            if (nodes && nodes.length>0) {
            	
            	var selectNode = nodes[0];
            	//alert(JSON.stringify(selectNode));
            	if(selectNode.id != null){
            		var param = "categoryId=" + selectNode.id;
                	
                	for(var i=0; i<selectRows.length; i++){
                		param += "&questionId=" + selectRows[i].id;
                	}
                	
                	$.ajax({
                        type:"POST",
                        async:false,  //默认true,异步
                        data: param,
                        url:'<c:url value="/exam/question/addByYun.action"/>',
                        success:function(data){
                            //alert(data);
                            if(data == "SUCCESS"){
                            	dialog.alert("保存成功!");
                            }else{
                            	dialog.alert("保存失败!");
                            }
                        }
                    });
            	}else{
            		dialog.alert('请选择题库！不可以向公司节点(根节点)添加试题！');
            	}
               
            }else{
            	dialog.alert("请先选择一个自己的试题库!");
            	return false;
            }
        },
        cancelValue: '取消',
        cancel: function() {}
    }).showModal();
}

function exportWord(){
	//导出试题
	var searchOption = getSearchOption();
	
	$("#exportWordForm input").val("");
	
	$("#exportWordForm input[name='content']").val(searchOption.content);
	$("#exportWordForm input[name='questionDisplayType']").val(searchOption.questionDisplayType);
	$("#exportWordForm input[name='difficultyLevel']").val(searchOption.difficultyLevel);
	$("#exportWordForm input[name='isEnabled']").val(searchOption.isEnabled);
	$("#exportWordForm input[name='categoryId']").val(searchOption.categoryId);
	
	$("#exportWordForm").submit();
}

// mmGrid end
</script>
<style type="text/css">
.tab_question_list{ width:780px;float:left;}
.tab_question_list .tr1{ background:#2c2c2c; width:1046px; height:36px;}
.tab_question_list .tr1:hover{background:#2c2c2c;}
/*.tab_question_list tr:hover{ background:#ffffef;}*/
/*.tab_question_list th{ text-align:center; padding-left:12px; height:36px; border:1px solid #eaeaea;color:#666666; padding-right:12px; color:#fff;}
.tab_question_list td{ text-align:center; padding-left:12px; height:36px; border:1px solid #eaeaea;color:#666666; padding-right:12px;}*/
.tab_question_list th{ text-align:center; padding-left:12px; height:36px; color:#666666; padding-right:12px; color:#fff;}
.tab_question_list td{ text-align:center; padding-left:12px; height:36px; color:#666666; padding-right:12px;}
.tab_question_list .td_1{ color:#003792;}
/*.tab_question_list input{float:left;}*/
.tab_question_list td a{ color:#003792;  margin-right:10px; line-height:12px;}
.tab_question_list .last{ border:none;}
.tab_question_list a:hover{ color:#d20001;}
.tab_question_list .td_2 { text-align:left; color:#003792;}
.tab_question_list .td_a{ display:inline; color:#003792;}

.td_a img{
	margin:2px;width:70px;height:70px;
}

._left_tree{ width:250px; border:1px solid #333333; float:left; font-family:'微软雅黑';}
._left_tree h2{ font-size:14px; width:250px; background:#333333; color:#fff; text-align:center; height:30px; line-height:30px; font-weight:normal;}
._left_tree #categoryTree{ height:520px; overflow:auto;}
/* ztree begin */
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

.ztree li span.button{  background: none;
}
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
    background: none;
}
/* ztree end */


.my_tab{
	padding:10px 24px;cursor:pointer;
}
.my_tab_selected{
	border:1px solid #CCC;border-top:2px solid #0186D1;cursor:auto;
	border-bottom: none;
	z-index: 99;background-color: #FFF;
	padding-bottom: 11px;
	color: #0186D1;
	font-weight: bold;
}

</style>
</head>

<body>
<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content" style="margin-top:20px;padding-bottom:30px;">
	<!-- <h3>试题管理</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">试题管理</span>
	</div>
   	<div class="_left_tree">
		
		<c:forEach var="company" items="${companyList}" varStatus="status" >
   			<c:choose>
   				<c:when test="${company.id == USER_BEAN.subCompanyId}" >
   					<div class="yun_ti" for="my">
			   			<h2>我的试题库分类</h2>
				        <ul id="categoryTree" class="ztree">
				        </ul>
			   		</div>
   				</c:when>
   				<c:when test="${company.name == '云'}" >
   					<div class="yun_ti" for="yun" style="display:none;">
			   			<h2>云试题库分类</h2>
				        <ul id="categoryTreeYun" class="ztree">
				        </ul>
		   			</div>
   				</c:when>
   				<c:otherwise>
   					<div class="yun_ti" for="jiTuan_${company.id}" style="display:none;">
			   			<h2>${company.name}试题库分类</h2>
				        <ul id="categoryTreeJiTuan_${company.id}" class="ztree">
				        </ul>
		   			</div>
   				</c:otherwise>
   			</c:choose>
		</c:forEach>
		
		<c:if test="${containZTreeContextMenu}">
	        <div id="editCategoryBlock" style="display:none;font-family: 微软雅黑;width:450px;">
	            <form id="editCategoryForm" action="">
		            <table class="node_table" cellpadding="0" cellspacing="0" style="margin-left:10px;margin-right:10px;">
	                    <tr>
	                        <td align="right">上级题库：</td>
	                        <td align="left">
	                            <!-- <input type="text" id="categoryParentName" class="input_0" style="width:315px;border:1px solid rgb(169, 169, 169);" disabled="disabled" /> -->
	                        </td>
	                        <td>
	                            <span style="color:red;margin-left:10px;"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="3" style="height:5px;"></td>
	                    </tr>
		                <tr>
		                    <td align="right">题库名称：</td>
		                    <td align="left">
		                        <input type="text" id="categoryName" name="categoryName" class="input_0" maxlength="30" style="width:315px;" />
		                    </td>
		                    <td>
		                        <span style="color:red;margin-left:10px;"></span>
		                    </td>
		                </tr>
		                <tr>
		                    <td colspan="3" style="height:5px;"></td>
		                </tr>
		                <tr id="descTr">
		                    <td align="right">题库描述：</td>
		                    <td align="left">
		                        <textarea id="categoryDescription" name="categoryDescription" class="input_0" style="height:200px;width:315px;" maxlength="200"></textarea>
		                    </td>
		                    <td>
		                        <span style="color:red;margin-left:10px;" ></span>
		                    </td>
		                </tr>
		            </table>
	            </form>
            
            <input type="hidden" id="categoryId" />
            <input type="hidden" id="categoryParentId" />
        </div>
	</c:if>
   
   	</div>

    <div class="right_lesson">
        <div class="cs_list" style="margin-top:12px;">
           
     		<c:forEach var="company" items="${companyList}" varStatus="status" >
     			<c:choose>
     				<c:when test="${company.id == USER_BEAN.subCompanyId}" >
     					<span class="my_tab my_tab_selected" for="my" style="margin-left:-5px;">我的试题</span>
     				</c:when>
     				<c:when test="${company.name == '云'}" >
     					<span class="my_tab" for="yun" style="" >${company.name}试题</span>
     				</c:when>
     				<c:otherwise>
     					<span class="my_tab" for="jiTuan_${company.id}" style="" >${company.name}试题</span>
     				</c:otherwise>
     			</c:choose>
           	</c:forEach>
            <div style="border-bottom:1px solid #CCC;margin-top:10px;"></div>
        </div>
        
        <div>
	        <c:forEach var="company" items="${companyList}" varStatus="status" >
     			<c:choose>
     				<c:when test="${company.id == USER_BEAN.subCompanyId}" >
     					<div class="button_gr yun_ti" for="my" >
				            <input type="button" value="新增试题" class="btn_4" onclick="addQuestion()" />
				            <input type="button" value="导入试题" class="btn_4 loadQuestionBtu" btuType="3" />
				            <input type="button" value="下载模板" class="btn_4 loadQuestionBtu" btuType="4" />
				            <input type="button" value="导出试题" class="btn_4" btuType="5" onclick="exportWord()" />
				            <input type="button" value="批量删除" onclick="deleteSelectedQuestions();" />
				        </div>
     				</c:when>
     				<c:when test="${company.name == '云'}" >
     					<div class="button_gr yun_ti" for="yun" style="display:none;">
				            <input type="button" value="加入我的题库" class="btn_4" onclick="addToMyQuestion()" style="margin-top:22px;" />
				        </div>
     				</c:when>
     				<c:otherwise>
     					<div class="button_gr yun_ti" for="jiTuan_${company.id}" style="display:none;">
				        	<input type="button" value="加入我的题库" class="btn_4" onclick="addToMyQuestion()" style="margin-top:22px;" />
				        </div>
     				</c:otherwise>
     			</c:choose>
           	</c:forEach>
		</div>
		
		<div class="search_3">
	            <p> 
	                题干：
	                <input type="text" id="questionContent" />
	                题型：
	                <select id="questionDisplayType">
	                    <option value="">显示全部</option>
	                    <option value="SUBJECTIVE">主观题</option>
	                    <option value="SINGLE_CHOICE">单选题</option>
	                    <option value="MULTI_CHOICE">多选题</option>
	                    <option value="DETERMINE">判断题</option>
	                    <option value="FILL_BLANK">填空题</option>
	                    <option value="MULTIMEDIA">多媒体题</option>
	                    <option value="GROUP">组合题</option>
	                </select>
	                难度：
	                <select id="difficultyLevel">
	                    <option value="">显示全部</option>
	                    <option value="1">易</option>
	                    <option value="2">中</option>
	                    <option value="3">难</option>
	                </select>
	            
	                
	               
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
	        
	        <div class="tab_question_list">
	            <div id="questionTable"></div>
	            <div id="paginator" align="right" style="margin-right:10px;" ></div>
	        </div>
    </div>

    <!-- 试题库导入 loadQuestion -->
    <div id="loadQuestion_div" style="display:none;">
        <iframe id="loadQuestionIframe" name="loadQuestionIframeName" frameborder="0" style="width:100%;height:400px;" src="" ></iframe>
        <div align="center" style="padding-top:10px;">
            <input id="loadQuestion_close" type="button" value="取消" style="padding:10px 12px;background:#FFF;color:#333;border:1px solid #C6C6C6;border-radius:3px;font-weight:bold;" />
            <input id="loadQuestion_ok" type="button" value="确认" style="padding:10px 12px;background:#428bca;color:#fff;border:none;border-radius:3px;font-weight:bold;margin-left:10px;" />
        </div>
    </div>
    
    <div id="categoryTreeContainerMy" class="" style="width:300px;display:none;height:320px;" >
       <ul id="categoryTreeMy" class="ztree" style="max-height:320px;overflow-y:scroll;overflow-x:auto;" ></ul>
   	</div>
	
	<form id='exportWordForm' action='<%=request.getContextPath() %>/exam/question/exportToWord.action' method='post' style='display:none;' >
		<input name="content" />
		<input name="questionDisplayType" />
		<input name="difficultyLevel" />
		<input name="isEnabled" />
		<input name="categoryId" />
	</form>
	
</div>
</body>
</html>
