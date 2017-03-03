<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>考试详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';// current user
var examId = '${examId}';//考试信息ID
$(function(){
	$.ajax({
		type : "POST",
		async:true,  //默认true,异步
		data:{"examId":examId},
		url : "<%=request.getContextPath()%>/exam/exam/getExam.action",
		success : function(data) {
			//alert(JSON.stringify(data));
			if(data != null){
				$("#title").text(data.title);
				$("#sjId").val(data.paperId);
				$("#sjName").text(data.paperName);
				if(data.displayMode == 1){
					$("#displayMode").text("整卷显示");
				}else if(data.displayMode == 1){
					$("#displayMode").text("单题可逆");
				}else{
					$("#displayMode").text("单题不可逆");
				}
				$("#duration").text(data.duration);
				$("#submitDuration").text(data.submitDuration);
				$("#allowTimes").text(data.allowTimes);
				$("#beginTime").text(data.beginTime);
				$("#endTime").text(data.endTime);
				$("#passScorePercent").text(data.passScorePercent);
				if(data.isScorePublic == 1){
					$("#isScorePublic").text("公开");
				}else{
					$("#isScorePublic").text("不公开");
				}
				if(data.isAnswerPublic == 1){
					$("#isAnswerPublic").text("公开");
				}else{
					$("#isAnswerPublic").text("不公开");
				}
				if(data.randomOrderMode != null){
					if(data.randomOrderMode == 1){
						$("#randomOrderMode").text("题目顺序");
					}else if(data.randomOrderMode == 2){
						$("#randomOrderMode").text("选项顺序");
					}else{
						$("#randomOrderMode").text("题目+选项顺序");
					}
				}
				if(data.isAutoMarking == 1){
					$("#isAutoMarking").text("是");
				}else{
					$("#isAutoMarking").text("否");
				}
				if(data.isAntiCheating == 1){
					$("#isAntiCheating").text("是");
				}else{
					$("#isAntiCheating").text("否");
				}
				$("#allowLeaveTimes").text(data.allowLeaveTimes);
				$("#pyrId").val(data.scoreMarker);
				$("#pyrName").text(data.scoreMarkerName);
				$("#notice").html(data.notice.replace(/\<br\/\>/g,"\n"));
			}
		}
	});
});

var total = 0;//总条数
var pageNum = 0;//总页数
var page = 1;//当前页
var pageSize = 10;//每页数量
var userList = [];//存放原始userList
var tempList = [];//存放当前页面查询的userList
var nowData = [];//存放当前mmgrid显示的userList
$(function(){
	initPersonListGrid();// 初始化人员列表
});

function initPersonListGrid(){
	$('#personListTable').mmGrid({
		cols : [ {title : 'id',name : 'id',hidden : true}, 
		         {title : '用户名',name : 'userName', width:100,align : 'center'}, 
		         {title : '姓名',name : 'name', width:100,align : 'center'}, 
		         {title : '性别',name : 'sex', width:50,align : 'center',
		        	 renderer:function(val,item, rowIndex){
	                		if(item.sex == 1){
	                			return '男';
	                		}else{
	                			return '女';
	                		}
	                	}
		         }, 
		         {title : '部门',name : 'deptName', width:100,align : 'center'},
		         {title : '岗位',name : 'postName', width:100,align : 'center'}, 
		         {title : '电子邮箱',name : 'email', width:250,align : 'center'}, 
		         {title : '联系电话',name : 'mobile', width:100,align : 'center'},
		         {title : '考试状态',name : 'field100', width:70,align : 'center', renderer:function(val, item, rowIndex){
	             		if(val && val !="0"){
	            			return '在考';
	            		}else{
	            			return '未考';
	            		}
            	  }}
		         ],
		url:"",
		width : 'auto',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : true,
		multiSelect : true,
		indexCol : true, // 索引列
		params : function(){
	    	var param = new Object();
	    	return param;
	    }/* ,
		plugins : [ $('#paginatorPaging3').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean
			// count
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [ 10, 20, 40, 50 ]
		}) ] */
	});
}

$(function(){
	$.ajax({
		type : "POST",
		async:true,  //默认true,异步
		data:{"relation_id":examId},
		url : "<%=request.getContextPath()%>/exam/exam/getAssignUserList.action",
		success : function(data) {
			//alert(JSON.stringify(data));
			if(data != null){
				userList = data;
				tempList = data;
				initList();
			}
		}
	});
});

/*初始化人员列表  */
function initList(){
	nowData = [];
	total = tempList.length;
	pageNum = Math.ceil(total/pageSize);
	var count = pageSize;
	if(tempList.length <= pageSize){
		count = tempList.length;
	}
	$("#total").text(total);
	$("#pageNum").text(pageNum);
	$("#nowPage").text(page);
	for(var i=0;i<count;i++){
		nowData.push(tempList[i]);
	}
	$('#personListTable').mmGrid().load(nowData);
	//userList = $('#personListTable').mmGrid().rows();
}

/*设置每页显示条数  */
function setPageSize(obj){
	nowData = [];
	page = 1;
	//pageSize = size;
	pageSize = $(obj).val();
	$('div[id=leftDiv]').find('li[id=size'+pageSize+']').removeClass().addClass("class02");
	$('div[id=leftDiv]').find('li[id!=size'+pageSize+']').removeClass().addClass("class01");
	$('#personListTable').mmGrid().removeRow();
	search_grid();
	initList(tempList);
}

/*跳转至第几页  */
function gotoPage(type){
	var toPage;
	if(type){
		toPage = parseInt(page)+ parseInt(type);
	}else{
		toPage = $.trim($("#toPage").val());
	}
	nowData = [];
	search_grid();
	total = tempList.length;
	//alert(toPage + "-----" + page);
	if(toPage != page && toPage > 0){//排除当前页面
		pageNum = Math.ceil(tempList.length/pageSize);//总页数
		//alert(toPage + "-----" + pageNum);
		if(toPage <= pageNum){//只有跳转页小于等于总页数时，才跳转，否则无反应
			page = toPage;
			var from = (toPage-1)*pageSize;
			var end = parseInt(from) + parseInt(pageSize-1);
			var lastPageCount = total%pageSize;
			if(toPage == pageNum){
				if(lastPageCount != 0){
					end = parseInt(from) + parseInt(lastPageCount-1);
				}
			}
			//alert(pageNum+"-"+from+"-"+end);
			for(var i=from;i<end+1;i++){
				nowData.push(tempList[i]);
			}
			$('#personListTable').mmGrid().load(nowData);
			$("#nowPage").text(page);
		}
	}
}

/*上一页  */
function gotoPrePage(){
	gotoPage(-1);
}
/*下一页  */
function gotoNextPage(){
	gotoPage(1);
	
}

/*查询  */
function query(){
	page = 1;
	search_grid();
	initList(tempList);
}

//考试人员列表搜索
function search_grid(){
	tempList = [];
	var searchName = $.trim($("#name").val());
	var searchPostName = $.trim($("#post").val());
	var searchDeptName = $.trim($("#department").val());
	var len = userList.length;
	var flagIndex;
	for(var i=0;i<len;i++){
		var flag1=true;
		var flag2=true;
		var flag3=true;
		flagIndex =0;
		var name = userList[i].name;
		var postName = userList[i].postName;
		var deptName = userList[i].deptName;
		if(searchName!='' && searchName){
			if(!name){
				$('#personListTable').mmGrid().load(tempList);
				continue;
			}
			if(name.indexOf(searchName)!=-1){
				flag1=true;
			}else{
				flag1=false;
			}
		}else{
			flagIndex= flagIndex+1;
		}
		if(searchPostName!='' && searchPostName){
			if(!postName){
				$('#personListTable').mmGrid().load(tempList);
				continue;
			}
			if(postName.indexOf(searchPostName)!=-1){
				flag2=true;
			}else{
				flag2=false;
			}
		}else{
			flagIndex= flagIndex+1;
		}
		if(searchDeptName!='' && searchDeptName){
			if(!deptName){
				$('#personListTable').mmGrid().load(tempList);
				continue;
			}
			if(deptName.indexOf(searchDeptName)!=-1){
				flag3=true;
			}else{
				flag3=false;
			}
		}else{
			flagIndex= flagIndex+1;
		}
		if(flag1 && flag2 && flag3){
			tempList.push(userList[i]);
		}
	}
	if(flagIndex==3){
		//initList(tempList);
		//$('#personListTable').mmGrid().load(userList);
		tempList = userList;
	}else{
		//initList(tempList);
		//$('#personListTable').mmGrid().load(results);	
	}
}

/**
 * 重置
 */
function clearAll(){
	page = 1;
	$("#name").val("");
	$("#post").val("");
	$("#department").val("");
	tempList = userList;
	initList();
	//$('#personListTable').mmGrid().load(userList);
	//当修改时，需要重新加载数据
}

/*预览试卷  */
function viewPaper(){
	window.open("<%=request.getContextPath()%>/myExamManage/gotoViewPaper.action?id="+examId);
}
/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/exam/exam/gotoExamList.action";
}


</script>
</head>
<body>
<div class="content">
	<!-- <h3>考试详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="goBack();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">考试详情</span>
	</div>
	<div class="lesson_add_2">
    	<div class="add_gr">
        	<div class="add_fl">
                <em>考试名称：</em>
            </div>
            <div class="add_fr">
            	<span id="title"></span>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
                <em>选择试卷：</em>
            </div>
            <div class="add_fr">
            	<input type="hidden" id="sjId" name="sjId">
                <span id="sjName"></span>
                <input type="button" value="预览试卷" class="xz" onclick="viewPaper();"/>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>显示模式：</em>
            </div>
            <div class="add_fr">
                <span id="displayMode">整卷显示</span>
            </div>
        </div>
       
         <div class="add_gr">
        	<div class="add_fl">
                <em>考试时长：</em>
            </div>
             <div class="add_fr">
             	<span id="duration"></span>
                <span>分钟</span>
            </div>
    	</div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>允许最快交卷时间：</em>
            </div>
             <div class="add_fr">
             	<span id="submitDuration"></span>
                <span>分钟</span>
            </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>考试次数：</em>
            </div>
             <div class="add_fr">
             	<span id="allowTimes"></span>
             </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>考试时间：</em>
            </div>
             <div class="add_fr">
             	<span id="beginTime"></span>
                <span>至</span>
             	<span id="endTime"></span>
             </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>及格分：</em>
            </div>
             <div class="add_fr">
                <span>总分的</span>
             	<span id="passScorePercent"></span>%
             </div>
    	</div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>答案是否公开：</em>
            </div>
            <div class="add_fr">
            	<span id="isAnswerPublic"></span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>成绩是否公开：</em>
            </div>
            <div class="add_fr">
            	<span id="isScorePublic"></span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>随机打乱：</em>
            </div>
            <div class="add_fr">
                <span id="randomOrderMode"></span>
            </div>
        </div>
		<div class="add_gr">
        	<div class="add_fl">
                <em>是否自动批阅：</em>
            </div>
            <div class="add_fr">
            	<span id="isAutoMarking"></span>
            </div>
        </div>
		<div class="add_gr">
        	<div class="add_fl">
                <em>是否开启防作弊：</em>
            </div>
            <div class="add_fr">
            	<span id="isAntiCheating"></span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>允许离开页面的次数：</em>
            </div>
            <div class="add_fr">
            	<span id="allowLeaveTimes"></span>
            </div>
        </div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>批阅人信息：</em>
            </div>
            <div class="add_fr">
            	<input  type="hidden" id="pyrId"/>
            	<span id="pyrName"></span>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
                <em>考前须知：</em>
            </div>
            <div class="add_fr">
            	<span id="notice"></span>
            </div>
        </div>
    </div>
    <div class="search_3" style="width:1044px; margin-top:20px;">
        	<p>	
            	姓名：<input type="text" id="name" name="name"/>
                                            岗位：<input type="text" id="post" name="post"/>
            	部门：<input type="text" id="department" name="department"/>
        	</p>
            <input type="button" value="重置" onclick="clearAll();"/>
        	<input type="button" value="查询" class="btn_cx" onclick="query();"/>

    </div>
    <div class="clear_both"></div>
      <div style="width:1066px;margin-bottom:30px;overflow:auto;" id="personListDiv">
 		<table id="personListTable"></table>
	<div id="paginator" class="paginator">
		<!-- <div id="leftDiv">
			<ul>
				<li id="size10" class="class02" onclick="setPageSize(10);">10</li>
				<li id="size20" class="class01" onclick="setPageSize(20);">20</li>
				<li id="size50" class="class01" onclick="setPageSize(50);">50</li>
				<li id="size100" class="class01" onclick="setPageSize(100);">100</li>
				<li id="size200" class="class01" onclick="setPageSize(200);">200</li>
			</ul>
		</div> -->
		<div id="rightDiv">
			<span>共 <span class="span_01" id="total"></span> 条</span>
			<span>当前第 <span class="span_01" id="nowPage"></span> 页</span>
			<span>共 <span class="span_01" id="pageNum"></span> 页</span>
			<span><span class="span_02" onclick="gotoPrePage();">上一页</span></span>
			<span><span class="span_02" onclick="gotoNextPage();">下一页</span></span>
			<span>
				<select id="pageSize" style="height:22px;" onchange="setPageSize(this);">
					<option value="10">每页10条</option>
					<option value="20">每页20条</option>
					<option value="40">每页40条</option>
					<option value="50">每页50条</option>
				</select>
			</span>
			<!-- <span>去第 <input type="text" id="toPage" style="width:20px;"/> 页</span>
			<span class="span_02" onclick="gotoPage();">跳转</span> -->
		</div>
	</div>
 	</div>
      <div class="button_cz fl" style="margin-top:20px; margin-left:0; padding:0; width:1046px;">
            <input type="button" value="返回" class="back" onclick="goBack();"/>
    </div>
</div>
</body>
</html>
