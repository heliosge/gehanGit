<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>试卷 插入试题</title>

<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<script type="text/javascript">

//被选中的题目
var selectedQes = new Object;

var zTree = null;
var zTree2 = null;

$(function(){
	
	//已经选择的试题 回填
	parent.$(".q_m div[questionId]").each(function(index, val){
		
		var questionId = $(val).attr("questionId");
		
		var questionScore = $(val).find(".question_no_score");
		var score = 0;
		var scoreDetail = [];
		
		if(questionScore.length == 1){
			//一般题型
			score = $(questionScore).val();
		}else{
			//组合题
			
			$(val).find(".question_no_group_score").each(function(index2, val2){
				
				var thisScore = parseInt($(val2).val());
				
				score += thisScore;
				scoreDetail.push(thisScore);
			});
		}
		
		if(scoreDetail && scoreDetail.length > 0){
			selectedQes["questionId_" + questionId] = {"id":questionId, "score":score, "scoreDetail":scoreDetail.join(",")};
		}else{
			selectedQes["questionId_" + questionId] = {"id":questionId, "score":score};
		}
	});
	
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
            }
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
        url:'<c:url value="/exam/questionCategory/list.action"/>',
        success:function(categoryTree){
            //addIconInfo(categoryTree);
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            expandZTree(zTree, 1);
        }
    });
    
    /* $.ajax({
        type:"POST",
        //async:true,  //默认true,异步
        data:null,
        url:'<c:url value="/exam/questionCategory/listYun.action"/>',
        success:function(categoryTree){
            //addIconInfo(categoryTree);
            zTree2 = $.fn.zTree.init($("#categoryTree2"), setting, categoryTree);
            expandZTree(zTree2, 1);
        }
    }); */
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
    clearSearchOptions();
    reloadmmGrid({'categoryId': category['id']});
}

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

$(document).ready(function(){
    var cols = [
                { title:'id', name:'id', align:'center', hidden:true},
                {title: '<input type="checkbox" class="my_checkAll" >', name: 'id', width: 22, align: 'center' ,lockWidth: true, checkCol: true, renderer:function(val, item, rowIndex){
             	   
                	return '<input type="checkbox" class="my_mmg_check" value="'+val+'" >';
                }},
                { title:'题干', name:'content' , align:'center',  width: 200,
                    renderer: function(val, item, rowIndex){
                    	var $title = $("<div>"+val+"</div>");
                    	$title.find("img").after(" 图片 ").remove();
                    	
                        return '<a href="<c:url value="/exam/question/toDetail.action?questionId=' + item['id'] + '"/>" class="td_a" target="_blank" title="'+$title.html()+'" >' + val + '</a>';
                    }},
                { title:'题型', name:'displayType' , align:'center', width: 50, lockWidth:true, renderer: toDisplayTypeStr},
                { title:'难度', name:'difficultyLevel' , align:'center', width: 30, lockWidth:true,  renderer: toDifficultyLevelStr},
                { title:'可用/禁用', name:'isEnabled' , align:'center', width: 55, lockWidth:true,
                    renderer: function(val) {
                        return val ? "可用" : "禁用";
                    }},
                { title:'试题库分类', name:'categoryFullName' , align:'center' , width: 100, renderer: function(val, item, rowIndex){
                  	var content = val;
                  	if (val.length > 14) {
                  		content = val.substring(0, 14) + "…";
                  	}
                    return "<span title='"+val+"' >" + content + "</span>";
                }}
            ];
    
    var myGrid = $('#questionTable').mmGrid({
    	root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
        cols: cols,
        url: '<c:url value="/exam/question/voList.action" />',
        params: getSearchOption,
        method: 'post',
        //checkCol: true,
        //multiSelect: true,
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
                       limitList: [10, 20, 50, 100, 200],
                       totalCountLabel: '<span>共{0}条</span>',
                   })
               ],
    });
    
    myGrid.on('loadSuccess', function(e, data){
        //加载数据成功后触发
        //分页，数据加载后，取消全选
        //$(".mmGrid .my_checkAll").prop('checked', false);
        
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
    	
     });
    
  	//checkbox 全选
	$(".mmGrid .my_checkAll").change(function(){

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
	});
  	
  	//选择试题
  	$(".mmGrid").on("click", ".my_mmg_check", function(){
		//alert($(this).prop('checked'));
		var questionId = $(this).attr("value");
		
		if($(this).prop('checked')){
			selectedQes["questionId_" + questionId] = {"id":questionId};
		}else{
			delete selectedQes["questionId_" + questionId];
		}
		
		//alert(JSON.stringify(selectedQes));
	});
  	
  	//数tab切换
  	$(".tree_tab").click(function(){
  		
  		if($(this).hasClass("tree_tab_selected")){
  			
  		}else{
  			var forUl = $(this).attr("for");
  			
  			$(".tree_tab_div").hide();
  			$(".tree_tab").removeClass("tree_tab_selected");
  			$(this).addClass("tree_tab_selected");
  			$("#"+forUl).parent().show();
  			
  			reloadmmGrid();
  		}
  		
  	});
});

//获得选中的 试题ID 数组
function getSelectedQuestion(){

	var k = 0;
	var backData = {idList:[], scoreList:[]};
	
	//alert(JSON.stringify(selectedQes));
	for(var key in selectedQes){
		//var keyStr = "questionIdList["+k+"]";
		backData.idList.push(selectedQes[key]["id"]);
		
		backData.scoreList.push(selectedQes[key]);
		
		k++;
	}
	
	return backData;
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

function modifyQuestion(id) {
    // var rowData = $('#questionTable').mmGrid('row', rowIndex);
    alert(JSON.stringify(id));
}

function showQuestionDetail(id) {
    // var rowData = $('#questionTable').mmGrid('row', rowIndex);
    //alert(JSON.stringify(id));
}

function clearSearchOptions() {
    var idList = ['#questionContent', '#questionDisplayType', '#difficultyLevel'];
    for (var idx in idList) {
        $(idList[idx]).val('');
    }
    
    reloadmmGrid();
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
    
    var forUl = $(".tree_tab_selected").attr("for");
    
    if(forUl == "categoryTree"){
    	//ztree
    	if (zTree != null) {
	        var nodes = zTree.getSelectedNodes();
	        if (nodes && nodes.length>0) {
	            searchOption['categoryId'] = nodes[0]['id'];
	        }
    	}
    }else{
    	//ztree2
    	if (zTree2 != null) {
	        var nodes = zTree2.getSelectedNodes();
	        if (nodes && nodes.length>0) {
	            searchOption['categoryId'] = nodes[0]['id'];
	        }
    	}
    	
    	searchOption["subCompanyId"] = 1;
    }
    
    removeNullElements(searchOption);
    return searchOption;
}

function reloadmmGrid(otherOptions) {
    var searchOption = getSearchOption();
    searchOption['pageNo'] = 1;
    if (otherOptions != null) {
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
// mmGrid end


</script>
<style type="text/css">

/* ztree begin */
.ztree{padding:3px;max-height:440px;overflow-y:scroll;overflow-x:auto;}
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

.tree_tab{
	font-size:14px; 
	width:100px; 
	background:#333333; 
	color:#fff; 
	text-align:center; 
	height:30px; 
	line-height:30px; 
	font-weight:normal;
	display:inline-block;
	cursor:pointer;
}

.tree_tab_selected{
	background:#fff; 
	color:#333; 
	cursor:auto;
}

.td_a img{
	margin:2px;width:50px;height:50px;
}
</style>
</head>

<body >


<div style="margin:10px 10px 0px 10px;">

   	<div class="" style="width:200px;border:1px solid #333333; float:left; font-family:'微软雅黑';" >
   		<%-- <c:if test="${subCompanyId != 1}">
   			<h2 for="categoryTree" class="tree_tab tree_tab_selected" style="" >试题库</h2>
	   		<h2 for="categoryTree2" class="tree_tab" style="margin-left:-4px;" >云试题库</h2>
	        <div class="tree_tab_div">
	        	<ul id="categoryTree" class="ztree"></ul>
	        </div>
	        <div class="tree_tab_div" style="display:none;">
	        	<ul id="categoryTree2" class="ztree"></ul>
	        </div>
   		</c:if> --%>
   		<%-- <c:if test="${subCompanyId == 1}"> --%>
   			<h2 for="categoryTree" class="tree_tab tree_tab_selected" style="width:200px;background:#333333;color:#FFF;" >试题库</h2>
	        <div class="tree_tab_div">
	        	<ul id="categoryTree" class="ztree"></ul>
	        </div>
   		<%-- </c:if> --%>
   	</div>

    <div class="right_lesson" style="width:610px;padding-bottom:10px;">

        <div class="search_3" style="width:100%;">
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
            <input type="button" value="查询" class="btn_cx" onclick="reloadmmGrid()" />

        </div>
        <div class="search_3" style="margin-top:-1px;width:100%;display:none;">
            <p> 
                可用/禁用：
                <select id="isEnabled">
                    <option value="">显示全部</option>
                    <option value="1" selected="selected" >可用</option>
                    <option value="0" >禁用</option>
                </select>
               
            </p>
            

        </div>
        
        <div style="clear:both;" > </div>
        <div class="" style="width:632px;">
            <div id="questionTable"></div>
            <div id="paginator" align="right" style="margin-right:10px;" ></div>
        </div>
        
    </div>
	
</div>
</body>
</html>
