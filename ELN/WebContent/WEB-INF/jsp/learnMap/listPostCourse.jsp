<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>岗位课程</title>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>

<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>


<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>

<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />

<script type="text/javascript">

var postId = ${postId};
// mmGrid begin


$(document).ready(function(){
	
    var cols = [
                { title:'id', name:'id', align:'center', hidden:true},
                /**{ title:'课程编号', name:'code' , align:'center'},**/
                { title:'课程名称', name:'name' , align:'center'},
                { title:'已学人数', name:'learningNum' , align:'center'},
               
            ];
    
    $('#exampleTable').mmGrid({
    	root : 'list',// 和后台数据保存列一致,对应MMGridPageVoBean rows
        cols: cols,
        url: '<c:url value="/post/courseList.action" />',
        params: {'postId':postId},
        method: 'post',
        checkCol: false,
        multiSelect: false,
        fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
        showBackboard : false,
        width: 'auto',
        height: '440px',
        plugins : [
                   $('#paginator11-1').mmPaginator({
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



function reloadmmGrid(otherOptions) {
    var searchOption = {};
    searchOption.postId =postId;
    searchOption['pageIndex'] = 1;
    if (otherOptions != null) {
        for (var key in otherOptions) {
            searchOption[key] = otherOptions[key];
        }
    }
    removeNullElements(searchOption);
    $('#exampleTable').mmGrid().load(searchOption);
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
.mmGrid .mmg-headWrapper .mmg-head th{ background:#2c2c2c; height:36px;width:360px}
/*.tab_3 .tr1:hover{background:#2c2c2c;}*/
.mmGrid .mmg-headWrapper .mmg-head th:hover{background:#2c2c2c;}
/* mmGrid end */
	.tree {width: 250px;height: 530px;border: 1px solid #333;float: left;font-family: '微软雅黑';}
	.tree h2 {font-size: 14px;width: 250px;background: #333;color: white;text-align: center;height: 30px;line-height: 30px;font-weight: normal;}
</style>
</head>

<body>
<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div  class='dialog-content'>

        <div class="tab_3" style="width:720px">
          <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        </div>
        
</div>
</body>
</html>
