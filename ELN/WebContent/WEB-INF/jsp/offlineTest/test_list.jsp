<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>线下考试</title>

<link rel="stylesheet" href="<c:url value="/css/page.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>

<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script src="<c:url value="/js/layer/layer.js"/>"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<!-- 上传插件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxupload.js" ></script>

<script type="text/javascript">

$(function(){
	
	$("#time_start, #time_end").datetimepicker({
		dateFormat:"yy-mm-dd", 
	    timeFormat: "HH:mm:ss",
	    changeMonth: true,
        changeYear: true
	});
	
	//表格
	$('#questionTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/offlineTest/getOfflineTest.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        checkCol: true,
        multiSelect: true,
        indexCol: true,  //索引列
        nowrap: true,
        params:function(){
        	var param = new Object();
        	
        	param.name = $.trim($("#title").val());
        	
        	var startTime = $.trim($("#time_start").val());
        	if(startTime == ""){
        		param.start_time = "0000-00-00 00:00:00";
        	}else{
        		param.start_time = startTime;
        	}
        	
        	var endTime = $.trim($("#time_end").val());
        	if(endTime == ""){
        		param.end_time = "9999-12-31 23:59:59";
        	}else{
        		param.end_time = endTime;
        	}

        	return param;
        },
        cols: [{title: 'ID', name: 'id', width:60, hidden:true},
			   {title: '考试名称', name: 'name', width:80, renderer:function(val, item, rowIndex){
			       
			       return '<a href="javascript:;" onclick="showResults(' + item.id + ')">'+val+'</a>';
			   }},
			   {title: '总分', name: 'total_score', width:80, lockWidth:true},
			   {title: '及格分数', name: 'pass_score', width:80, lockWidth:true},
			   {title: '考试时间', name: 'id', width:260, lockWidth:true, renderer:function(val, item, rowIndex){
			       
			       return item.start_time.substring(0, 19) + " ~ " + item.end_time.substring(0, 19);
			   }},
			   {title: '操作', name: 'id', width:60, lockWidth:true, renderer:function(val, item, rowIndex){
				   
				   var deleteStr = '<a href="javascript:;" onclick="deleteTest(' + val + ')">删除</a>';
                   var modifyStr = '<a href="javascript:;" onclick="modifyTest(' + val + ')">修改</a>';
                   return modifyStr + "&nbsp;&nbsp;" + deleteStr;
			   }}
           ],
        plugins : [
        	$('#paginator').mmPaginator({
        		totalCountName: 'total',    //对应MMGridPageVoBean count
        		page: 1,                    //初始页
        		pageParamName: 'page',      //当前页数
        		limitParamName: 'pageSize', //每页数量
        		limitList: [10, 20, 30, 50]
        	})
        ]
    });
	
	addByExcel();
});

function showResults(id){
	//查看考试人员信息
	
	//记录 当前 查询条件，页码，每页数量，返回时，直接到此页
	//var opt = $('#questionTable').mmGrid().opts;
	//params = $.extend(opt.params(), opt.plugins[0].params());
	//alert(JSON.stringify(params));
	
	window.location.href = "<%=request.getContextPath()%>/offlineTest/testResultList.action?id="+id;
}

function modifyTest(id){
	//修改
	window.location.href = "<%=request.getContextPath()%>/offlineTest/testAddPage.action?id="+id;
}

function deleteTestMore(){
	//批量删除
	//被选中的一行
    var selectRows = $('#questionTable').mmGrid().selectedRows();
    
    if(selectRows.length > 0){
        
    	var idList = [];
        $.each(selectRows, function(index, val){
            idList.push(val.id);
        });

        deleteTest(idList);
        
    }else{
        dialog.alert('请先选择数据！');
    }
}

//删除
function deleteTest(id){
	dialog.confirm('确认删除吗？', function(){
		
		layer.msg('删除中…', {icon:16, time:0});
		
		var param = "";
		
		if($.isArray(id)){
			//数组 批量 删除
			param = "id=" + id.join("&id=");
			
		}else{
			param = "id="+id;
		}
		
		$.ajax({
    		type:"POST",
    		async: true,  //默认true,异步
    		data: param,
    		url: "<%=request.getContextPath()%>/offlineTest/testDelete.action",
    		error: function(XMLHttpRequest, textStatus, errorThrown) {
    			dialog.alert("删除失败!");
            },
    		success:function(data){
    			if(data == "SUCCESS"){
    				
    				layer.closeAll();
    				dialog.alert("删除成功!");
    				
    				reloadmmGrid();
					
    			}else{
    				
    				dialog.alert("删除失败!");
    			}
    			
    	    }
    	});
		
    });
}

//查询
function reloadmmGrid(page){
	
	//时间判断
	var startTime = $.trim($("#time_start").val());
	var endTime = $.trim($("#time_end").val());
	
	if(startTime !="" && endTime !=""){
		
		if(getTimeInt(startTime) > getTimeInt(endTime)){
			dialog.alert("考试开始时间不可以大于结束时间！");
			return false;
		}
	}
	
	if(page){
		$('#questionTable').mmGrid().load({"page": page});
	}else{
		$('#questionTable').mmGrid().load();
	}
}

//重置
function clearSearchOptions(){
	$("#title").val("");
	$("#time_start").val("");
	$("#time_end").val("");
	
	reloadmmGrid(1);
}

function getTimeInt(time){
	
	var times = time.split(" ");
	
	var ymd = times[0].split("-").join("");
	var hms = times[1].split(":").join("");
	
	return parseInt(ymd + hms, 10);
}

//跳转到录入考试页面
function toTestAddPage(){
	
	window.location.href = "<%=request.getContextPath()%>/offlineTest/testAddPage.action";
}

function downloadMB(){
	//下载模板
	window.location.href = "<%=request.getContextPath()%>/file/offline_test_example.zip";
}

function addByExcel(){
	
	//上传
	var button = $('#btnUp');
    new AjaxUpload(button, {
    	action: "<%=request.getContextPath()%>/offlineTest/addByExcel.action",
        //data: {"fileSize": "1000"}, 
        dataType:'json', 
        name: 'file',  
        onSubmit: function (file, ext) {
            if (!(ext && /^(xlsx)$/.test(ext))) {
            	dialog.alert("请上传xlsx文件!");
                return false;
            }
            layer.msg('正在上传,请稍候...', {icon: 16, time: 0, shade: [0.8, '#393D49']});
        },
        onComplete: function (file, response) {
            //file 本地文件名称，response 服务器端传回的信息
            //alert(response);
            
            layer.closeAll();
            
            if(response.indexOf("SUCCESS") >= 0){
    			dialog.alert("导入成功!");
    			reloadmmGrid(1);
    		}else{
    			dialog.alert("导入失败!");
    		}
        }
    });
}

</script>
<style type="text/css">
</style>
</head>

<body>

<!-- todo delete -->
<div id="showTempResult" style="width:300px;">
</div>

<div class="content" style="margin-top:20px;padding-bottom:10px;">

	<!-- <h3>线下考试</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
		<span  style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">线下考试</span>
	</div>
    <div class="right_lesson" style="padding-bottom:0px;margin-left:0px;width:100%;">
        <div class="button_gr" style="height:30px;line-height:30px;width:100%;">
            <input type="button" value="录入考试" onclick="toTestAddPage();" />
            <input id="btnUp" class="upload_div" type="button" value="批量录入" />
            <input type="button" value="下载模板" onclick="downloadMB();" />
            <input type="button" value="批量删除" onclick="deleteTestMore();" />
        </div>
        <div class="search_3" style="margin-top:16px;width:1044px;">
            <p> 
               	考试名称：
                <input type="text" id="title" />
                                考试时间：
                <input type="text" id="time_start"  />
                                至：
               	<input type="text" id="time_end"  />
            </p>
            <input type="button" value="重置" onclick="clearSearchOptions()" />
            <input type="button" value="查询" class="btn_cx" onclick="reloadmmGrid(1)" />
        </div>
        
        <div style="clear:both;" ></div>
        
        <div class="" style="" >
            <div id="questionTable"></div>
            <div id="paginator" align="right" style="margin-top:10px;" ></div>
        </div>
        
        
    </div>
	
</div>
</body>
</html>
