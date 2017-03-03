<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<style type="text/css">
    
</style>
</head>
<body>
<div id="course_all">
		<div class="notes_list">
			<!-- <h3 style="background: url(../images/img/ico_4.png) no-repeat left 2px;padding-left: 20px;">安全宣教管理</h3> -->
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">安全宣教管理</span>
			</div>
			<div class="ul_know" id="kn_ce">
				<ul id="ul_exam">
					<li class="li_a" onclick="tabClick(1)">微电影</li>
					<li onclick="tabClick(2)">宣传片</li>
					<li onclick="tabClick(3)">教育片</li>
				</ul>
			</div>
			<div>
				<div class="button_s">
					<a href="javascript:void(0);" id="sc_a"><input type="button" value="新增" onclick="toAdd()"/></a>
					 <a href="javascript:void(0);"><input type="button" value="批量删除" class="delete" onclick="deleteByIds()"/></a>
				</div>
					<form id="searchForm">
					<div class="search_5" style="height: auto;">
						<p>
							<span>标题：</span> <input type="text" id="title" name="title"/> 
							<span>创建时间：</span>
							
							<input type="text" id="startTime" name="startTime"/> <em>至</em> <input id="endTime" type="text" name="endTime"/>
							<input type="button" class="btn_5" value="查询" onclick="query()"/>
							
						</p>
					</div>
					</form>
					<div class="clear_both"></div>
					<div id="dataDiv">
				     	<table id="tableData"></table>
					  	<div id="paginator" style="text-align:right;"></div>
				     </div>
			</div>
		</div>
	</div>
	<div id="videoDialog" style="display: none;">
		<div id="videoDialogContent"></div>
		<div id="content"></div>
	</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<!-- 视屏播放 -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.js" charset="utf-8"></script> 
 
<script type="text/javascript">
var type = 1;
$(function(){
	initDates();
	initDataList();
});

(function oldStyle(){
	$('#ul_exam').find('li').click(
			function() {
				$('#ul_exam').find('li').attr('class', '');
				$(this).attr('class', 'li_a');
			})
})()

function initDataList(){
	$('#tableData').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/safetyEducation/getSafetyEducationList.action',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: true,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.title = $.trim($("#title").val());
	    	param.startTime = $("#startTime").val();
	    	param.endTime = $("#endTime").val();
	    	param.type = type;
	    	return param;
	    },
	    cols: [{title: 'id', name: 'id',hidden:true},
	           {title:'标题', name:'title', width:160,align:'center'},
	           {title:'创建时间', name:'createTime', width:80,align:'center',
	        	   renderer:function(val,item, rowIndex){
	        		   return getFormatDateByLong(val,'yyyy-MM-dd');
              		}
	          	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var id = item.id;
                		var filePath = item.filePath;
                		var descr = item.descr;
            			$("#content").html(descr);
                		var str = '';
                		var str1 = '<a href="javascript:void(0);" onclick="deleteByIds('+id+',1)">删除</a>&nbsp;';
                		var str2 = '<a href="javascript:void(0);" onclick="modify('+id+')">修改</a>&nbsp;';
                		var str3 = '<a href="javascript:void(0);"onclick="toSeeVideo(\''+filePath+'\')">查看</a>&nbsp;';
                		str += str1 + str2 + str3;
                		return str;
                	}	
	}] ,
	   plugins : [
	    	$('#paginator').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'page',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
}
function initDates(){
	initDatePicker("startTime");
	initDatePicker("endTime");
}
function initDatePicker(idName){
	$("#"+idName).datepicker({
		changeMonth: true,
        changeYear: true,
		dateFormat:'yy-mm-dd'
	});
};
/**
 * 查询
 */
function query(){
	$('#tableData').mmGrid().load({"page":1});
}
/**
 * 修改
 */
function modify(id){
	window.location.href = "<%=request.getContextPath()%>/safetyEducation/toSafetyAdd.action?id="+id+"&type="+type;
}
/**
 * tab页点击触发
 */
function tabClick(typeFlag){
	$("#searchForm")[0].reset();
	type = typeFlag;
	$('#tableData').mmGrid().load({"page":1});
}
/**
 * 新增
 */
function toAdd(){
	window.location.href = "<%=request.getContextPath()%>/safetyEducation/toSafetyAdd.action?type="+type;
}
/**
 * 查看
 */
function toSeeVideo(path){
	var emel = $("#videoDialog");
	dialog({
		width : 1100,
		height : 500,
		title : '视频播放',
		content : emel
	}).showModal();
	setTimeout(function(){
		initVideo(path);
	},200);
}
/**
 * 初始化video
 */
function initVideo(filePath){
	var flashvars={
			f:filePath,
			p:2,
			e:2,
			ht:'20'
			};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
	CKobject.embedSWF('<%=request.getContextPath()%>/js/ckplayer6.7_bak/ckplayer/ckplayer.swf','videoDialogContent','ckplayer_courseWarePlayer','1100','400',flashvars,params);	
}
/**
 * 数组多id删除
 */
function deleteByIds(ids,type){//type:1 删一个 2 批量删
	var idArr= [];
	if(type==1){
		idArr.push(ids);
	}else{
		var items = $("#tableData").mmGrid().selectedRows();
		
		if(items.length>0){
			$.each(items,function(index,item){
				idArr.push(item.id);
			});
		}else{
			dialog.alert("请选择数据!");
			return;
		}
	}
	dialog.confirm('确认删除？',function () {
		var urlStr = "<%=request.getContextPath()%>/safetyEducation/deleteByIds.action";
		$.ajax({
			type : "POST",	
			url : urlStr,
			data : {"ids":idArr},
			success : function(data) {
				dialog.alert("操作成功!");
				$("#tableData").mmGrid().load({"page":1});
			}
		});
    });
	
}
</script>
</html>