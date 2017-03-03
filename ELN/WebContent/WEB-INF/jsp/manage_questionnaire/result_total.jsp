<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.optionTypeClass{
	font-size: 14px;
  	font-family: 微软雅黑;
  	font-weight: bold;
  	margin-right: 5px;
  	border-radius: 2px;
  	border: none;
  	background-color: #def3df;
  	padding: 2px 5px;
  	color: #6bbb34;
}
.btn{
	/* font-size:14px;
	font-family:微软雅黑; */
	margin-right:5px;
	margin-top:2px;
	cursor: pointer;
	border-radius:2px;
	border:none;
	padding:4px 8px;
	color: #ffffff;
	background-color: #d60500;
	/* float:right; */
	margin-bottom: 5px;
}
.outer-therm{margin:2px 0px;}

</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>
<title>调查结果统计</title>
<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';// current user
//修改时的回填参数
var iBean = ${iBean};
var backType = '${backType}';
//var iBean_userList = ${iBean_userList};
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qType = ["","单","多","主","评"];
var html = "";

/*头部tab点击事件  */
$(function(){
	$('#ul_exam').find('li').click(function(){
		$('#ul_exam').find('li').attr('class','');
		$(this).attr('class','li_a');
		setDisplay($(this).attr("id"));
	})
});

function setDisplay(type){
	if(type == 1){
		$("#resultDiv").css("display","block");
		$("#assginListDiv").css("display","none");
	}else{
		$("#assginListDiv").css("display","block");
		$("#resultDiv").css("display","none");
		getAssignList();
	}
}
/*从其它页面返回到该页面，需要处理tab点击事件  */
$(function(){
	if(backType == 'detail'){
		$('#ul_exam').find('li[id=2]').click();
	}else{
		setDisplay(1);
	}
	getResultDetail();
});

function getResultDetail(){
	$("#questionnaireDiv").html('<span style="font-size:20px;">正在加载中，请稍后...<span>');
	   $.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":iBean.id},
		url: "<%=request.getContextPath()%>/questionnaire/getResultDetail.action",
		success:function(data){
			if(data != null){
				html = '';
				for(var i=0;i<data.length;i++){
					appendQuestion(data[i]);
				}
				$("#questionnaireDiv").html(html);
				initMeterAll();
				for(var i=0;i<data.length;i++){
					var qId = data[i].id;
					var qType = data[i].type;
					if(qType == 4){
						//alert(data[i].id);
						initStar(qId,data[i].starAverage);
					}
				}
			}else{
				dialog.alert("操作失败！");
			}
	    }
	}); 
}

function getAssignList(){
	$('#investigationTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/questionnaire/getUserAnswerList.action',
		width: '1065px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.type = 1;
	    	param.id = iBean.id;
	    	param.username = escapeForSql($.trim($("#username").val()));
	    	param.department = $("#department").val();
	    	param.status = $("#status").val();
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'姓名', name:'name', width:100,align:'center'},
	           {title:'状态', name:'status', width:170,align:'center',
	        	   renderer:function(val,item, rowIndex){
            		  if(item.status == 2){
            			   return "已提交";
            		   }else{
            			   return "未提交";
            		   } 
              		}   
	           },
	           {title:'部门', name:'department', width:200,align:'center'},
               {title:'操作',name:'operation', width:80,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str = '';
                		var str_detail = '<a href="javascript:void(0);" onclick="view('+ rowIndex +')" >详情</a>&nbsp;';
                		//var str_detail = '<input type="button" class="btn" onclick="view('+ rowIndex +')"  value="详情"/>&nbsp;&nbsp;';
                		var str_export = '<a href="javascript:void(0);" onclick="exportTotal('+ item.id +')" >导出数据</a>&nbsp;';
                		//var str_export = '<input type="button" class="btn" onclick="exportTotal('+ item.id +')" value="导出数据"/>';
                		return str_detail + str_export;	  
                	}	
	}] ,
	   plugins : [
	    	$('#paginator11-1').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'pageNo',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
}

/*查看  */
function view(rowIndex){
	var rowData = $('#investigationTable').mmGrid("row", rowIndex);
	window.location = "<%=request.getContextPath() %>/myQuestionnaire/toUserAnswerDetail.action?id="+rowData.investigationId+"&assignId="+rowData.id+"&backType=resultList";
}

/*导出  */
function exportTotal(id){
	window.location.href = "<%=request.getContextPath() %>/myQuestionnaire/exportDoc.action?assignId="+id;
	
}

/*调查结果统计导出word  */
function exportResultDoc(){
	window.location.href = "<%=request.getContextPath() %>/questionnaire/exportResultDoc.action?id="+iBean.id;
}

$(function(){
	//初始化调查基本信息
	if(iBean){
    	$("#title").text(iBean.title);
    	$("#timePeriod").text(iBean.beginTime+" - " + iBean.endTime);
    	$("#aim").html(iBean.aim);
    	var state = iBean.state;
    	if(state == 1){
    		$("#state").text("未开始");
    	}else if(state == 2){
    		$("#state").text("进行中");
    	}else{
    		$("#state").text("已结束");
    	}
    	$("#intendNum").html(iBean.intendNum);
    	$("#totalNum").html(iBean.totalNum);
    }
});

var q_index=1;
/*新增问题  */
function appendQuestion(questionBean){
	if(questionBean != null){
		var id = questionBean.id;
		var isRequired = questionBean.isRequired;
		var content = questionBean.content;
	    var options = questionBean.options;
	    var type = questionBean.type;
	    //拼接问题
	    html += '<div class="question_div" questionId="'+id+'" questionType="'+type+'" isRequired="'+isRequired+'">';
	    html += '<div>';
	    html += '<span><input type="button" class="optionTypeClass" value="'+qType[type]+'"/></span>';
		html += '<span class="question_no">'+(q_index++)+'</span>. ';
	    html += '<span class="question_content">'+content+'</span>';
	    if(type == 3 || type == 4){//主观、评星
	    	html += '<span class="question_png">答案（' + questionBean.totalAnswerNum;
		    html += '）</span>';
	    }
	    html += '</div>';
	    html += concatOptions(id,type,options,questionBean);
	    html += '</div>';
		//统计问题类型数量
		$("#type"+type).text(parseInt($("#type"+type).text())+1);
	}
}

/*拼接选项 */
function concatOptions(questionId,type,options,questionBean){
    var optionHtml = '<div id="optionDiv" class="option_div">';
    if(type == 3){//主观题
    	var anList = questionBean.answerList;
    	optionHtml += '<div style="border-top:1px solid #ccc;">';
    	for(var i=0;i<anList.length;i++){
    		optionHtml += '<div style="border:1px solid #ccc;border-top:none;padding: 10px;">';
    		optionHtml += '<p>';
    		optionHtml += '<img style="width:30px;height:30px;" src="'+anList[i].headPhoto+'"/><span style="color:#6595EB;">'+anList[i].userName+'</span>&nbsp';
    		optionHtml += '<span style="color:#ccc;">';
    		optionHtml += '【<span>'+anList[i].deptName+'</span>&nbsp<span>'+anList[i].postName+'</span>】';
    		optionHtml += '&nbsp&nbsp<span>'+anList[i].submitTime+'</span>';
    		optionHtml += '</span>';
    		optionHtml += '<p>'+setNull(anList[i].userAnswer)+'</p>';
    		optionHtml += '</p>';
    		optionHtml += '</div>';
    	}
    	optionHtml += '</div>';
    	//optionHtml += '<textarea style="width:300px;height:100px;" disabled="disabled" ></textarea>';
    }else if(type == 1){//单选题
    	optionHtml += '<table width="90%">';
        for(var i=0;i<options.length;i++){
            /* optionHtml += '<p><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p>';
            optionHtml += '<div style="wi"><div id="jqMeter_div'+options[i].id+'" total="'+questionBean.totalAnswerNum
            				+'" selectNum="'+options[i].selectNum+'"></div>';
            optionHtml += '<div style="position: relative;bottom: 20px;left: 220px;">'+options[i].selectNum + '人</div></div>'; */
        	optionHtml += '<tr>';
        	optionHtml += '<td style="width:40%;"><p><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p></td>';
        	optionHtml += '<td style="width:40%;"><div style="inline-block;"><div id="jqMeter_div'+options[i].id+'" total="'+questionBean.totalAnswerNum
            				+'" selectNum="'+options[i].selectNum+'"></div></div></td>';
            optionHtml += '<td style="width:10%;"><div>'+options[i].selectNum + '人</td>';
        	optionHtml += '</tr>';
        }
        optionHtml += '</table>';
    }else if(type == 2){//多选题
    	optionHtml += '<table width="90%">';
        for(var i=0;i<options.length;i++){
            /* optionHtml += '<p><span>'+optionName[i+1]+"."+'</span><span>'+setNull(options[i].content)+'</span></p>';
            optionHtml += '<div style="wi"><div id="jqMeter_div'+options[i].id+'" total="'+questionBean.totalAnswerNum
			+'" selectNum="'+options[i].selectNum+'"></div>';
            optionHtml += '<div style="position: relative;bottom: 20px;left: 220px;">'+options[i].selectNum + '人</div></div>'; */
        	optionHtml += '<tr>';
        	optionHtml += '<td style="width:40%;"><p><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p></td>';
        	optionHtml += '<td style="width:40%;"><div style="inline-block;"><div id="jqMeter_div'+options[i].id+'" total="'+questionBean.totalAnswerNum
            				+'" selectNum="'+options[i].selectNum+'"></div></div></td>';
            optionHtml += '<td style="width:10%;"><div>'+options[i].selectNum + '人</td>';
        	optionHtml += '</tr>';
        }
        optionHtml += '</table>';
    }else{//评星题
    	optionHtml += '<div qId="'+questionId+'" id="star'+questionId+'" ma="'+questionBean.starAverage+'" style="margin-left: 10px;"></div>';
    }
    optionHtml += '</div>';
    return optionHtml;
}

/**
 * 初始化进度条
 	params: {0}初始div的id名 String 
 			{1}进度条总值  String
 			{2}进度条当前值 String
 */
 function initMeter(objIdName,par1,par2){
	$('#'+objIdName).jQMeter({
		goal:par1,//进度条比例总数
		raised:par2,//进度条
		meterOrientation:'horizontal',//横向vertical、纵向horizontal
		width:'200px',
		height:'15px',
		margin:'0px 0px',
		bgColor:'#D1D1D1',//背景色
		barColor:'#6BBB34',//进度条色
		displayTotal:false,//默认true 是否显示进度百分比
		animationSpeed:0//初始化速度 默认2000  单位milliseconds
	});
}
function initMeterAll(){
	$("div[id^=jqMeter_div]").each(function(index,element){
		initMeter($(this).attr("id"),$(this).attr("total"),$(this).attr("selectNum"));
	});
}

/*初始化评分控件  */
function initStar(qId,answer){
	var setting = {
			max : 5,
			value : answer,
			image : "../js/rater-star/star.gif",
			enabled : false
		};
	$("#star"+qId).rater(setting);
}

//查询
function query(){
	$('#investigationTable').mmGrid().load({"pageNo":1});
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#investigationTable').mmGrid().load();
	}else{
		$('#investigationTable').mmGrid().load({"pageNo":1});		
	}
}

/*判空  */
function setNull(title){
    if(title == null){
        return "暂无";
    }
    return title;
}

//返回调查列表
function backList(){
	window.location.href = "<%=request.getContextPath()%>/questionnaire/toResultList.action";
}

/**
 * 导出word
 */
function exportDoc(){
	//var form = $("#exportDocForm");
	//form.submit();
	window.location.href = '<%=request.getContextPath() %>/questionnaire/exportDoc.action?id=' +iBean.id;
}

//重置
function clearAll(){
	$("#username").val("");
	$("#department").val("");
    $('#status').val("");
    search(true);
}

//转义特殊字符以供sql查询的like语句使用
function escapeForSql(str) {
	var s = str ? str.trim() : str;
	return s ? s.replace(/\\/g, '\\\\').replace(/([%_])/g, '\\$1') : s;
}
//zhangchen end 
	
	
</script>
<style type="text/css">
.lesson_add_2 .add_gr {
  width: 1066px;
  height: auto;
  line-height: 40px;
  float: left;
  margin-bottom: 10px;
}
</style>
</head>
<body>
<div class="content">
	<!-- <h3>调查结果统计</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backList();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">调查结果统计</span>
	</div>
	<div id="investigationDiv">
		<div class="lesson_add_2" style="margin-bottom: 0px;padding-bottom: 0px;padding-top:0px;">
	    	<div class="add_gr">
	        	<div class="add_fl">
	                <em>调查名称：</em>
	            </div>
	            <div class="add_fr">
	            	<span id="title"></span>
	            </div>
	        </div>
	    	<div class="add_gr">
	        	<div class="add_fl">
	                <em>调研时间：</em>
	            </div>
	            <div class="add_fr">
	            	<span id="timePeriod"></span>
	            </div>
	        </div>
	        <div class="add_gr">
	        	<div class="add_fl">
	                <em>调查目的：</em>
	            </div>
	            <div class="add_fr">
	            	<span id="aim"></span>
	            </div>
	        </div>
	    </div>
    </div>
    <!--问卷问题 start  -->
    <div style="clear:both;"></div>
    <div style="background-color: #eee;  height: 30px;  line-height: 30px;  padding-left: 10px;  border: 1px solid #ccc;border-top: 0px;">
    	<div id="typeTotalDiv" style="float:left;">
    		<span>调查状态：</span><span id="state" style="color:red;font-weight: bold;">0</span>&nbsp;
    		<span>安排人数：</span>
    			<span id="intendNum" style="color:red;font-weight: bold;">0</span>
    			<span style="color:red;font-weight: bold;">/</span>
    			<span id="totalNum" style="color:red;font-weight: bold;">0</span>
    	</div>
    </div>
    <div class="notes_list">
    	<div class="ul_exam">
        	<ul id="ul_exam">
            	<li class="li_a" id="1">调查结果统计</li>
                <li id="2">员工答卷明细</li>
            </ul>
        </div>
        <div id="resultDiv">
	        <div>
	        	<input type="button" id="exportDoc" value="导出数据" class="btn" style="float:right;" onclick="exportResultDoc();"/>
	        </div>
	        <div style="clear:both;"></div>
	        <div id="questionnaireDiv" class="q_m2">
	        	
	        </div>
	     </div>
        <div id="assginListDiv" style="display:none;">
	        <div class="search_2 fl">
				<p>姓名：<input type="text" id="username" name="username"/>
	               	部门：<input type="text" id="department" name="department"/>
	              	状态：
	               <select id="status" name="status" style="width:80px;">
	                   	<option value="0">全部</option>
	                   	<option value="1">未提交</option>
	                   	<option value="2">已提交</option>
	               </select>
	           </p>
	           <input type="button" onclick="clearAll();" value="重置" />
	           <input type="button" class="btn_cx" onclick="query();" value="查询" />
		    </div>
		    <div class="clear_both"></div>
	        <div id="dataDiv">
	        	<table id="investigationTable"></table>
			  	<div id="paginator11-1" style="text-align:right;"></div>
	        </div>
        </div>
    </div>
    <div class="" style="padding-top: 15px;">
          <input type="button" value="返回" class="buttonClass" style="background-color: #0085fe;" onclick="backList()" />
     </div>
</div>

	
</body>
</html>
