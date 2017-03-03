<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参与考试</title>
<style type="">
 .timer{padding-left: 5px;color: red;font-size: 18px;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<!--日期控件  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/css/flick/jquery-ui.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.4.custom/js/jquery.ui.datepicker-zh-CN.js" ></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var is_passed = null;
$(function(){
	$("#userId").val(userId);
	$('#examTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/myExamManage/getMyExamList.action',
		width: '1046px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	param.user_id = $("#userId").val();
	    	param.exam_status = $("#examStatus").val();
	    	param.title = escapeForSql($.trim($("#title").val()));
	    	param.begin_time = $("#beginTime").val();
	    	param.end_time = $("#endTime").val();
	    	param.is_passed = is_passed;
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	           {title:'考试名称', name:'title', width:170,align:'center'},
	           {title:'考试开始时间', name:'beginTime', width:200,align:'center',
            	   renderer:function(val,item, rowIndex){
               		return item.beginTime + "-" + item.endTime;
               	}
               },
// 	           {title:'考试结束时间', name:'endTime', width:110,align:'center'},
               {title:'成绩是否公开',name:'isScorePublic', width:80,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isScorePublic == 0){
            			   return "不公开"; 
            		   }else{
            			   return "公开";
            		   }
              			
              		}
              	},
               {title:'考试状态',name:'examStatus', width:50,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.examState == 'BEFORE_START'){
            			   return "未开始"; 
            		   }else if((item.examStatus == 1 || item.examStatus == 2) && item.examState == 'PROCESSING'){
            			   return "进行中";
            		   }else if((item.examStatus == 3 || item.examStatus == 4) && item.examState == 'PROCESSING'){
            			   return "已提交";
            		   }else if((item.examStatus == 3 || item.examStatus == 4)&& item.examState == 'FINISHED'){
            			   return "已结束";
            		   }else if((item.examStatus == 1 || item.examStatus == 2) && item.examState == 'FINISHED'){
            			   return "已过期";
            		   }else{
            			   return "-";
            		   }
              		}
              	},
              	/* {title:'个人考试状态',name:'examStatus', width:50,align:'center',
             	   renderer:function(val,item, rowIndex){
             		   if(item.examStatus == 1){
             			   return "未开始"; 
             		   }else if(item.examStatus == 2){
             			   return "进行中";
             		   }else if(item.examStatus == 3){
             			   return "已提交";
             		   }else if(item.examStatus == 4){
             			   return "已结束";
             		   }else{
             			   return "-";
             		   }
               		}
               	}, */
               {title:'是否通过',name:'isPassed', width:45,align:'center',
            	   renderer:function(val,item, rowIndex){
            		  /* if(item.examStatus != 1){
            			 if(item.isScorePublished == 1){
	               			   if(item.isPassed == 0){
	                   			   return "未通过"; 
	                   		   }else if(item.isPassed == 1){
	                   			   return "已通过";
	                   		   }else{
	                   			   return "未通过";
	                   		   }
	               		   }else{
	               			   return "-";
	               		   } 
            		  }else{
            			  return "-";
            		  } */
            		   if(item.isScorePublished == 1){//如果成绩发布
            			   if(item.isPassed == 0){
                   			   return "未通过"; 
                   		   }else if(item.isPassed == 1){
                   			   return "已通过";
                   		   }else{
                   			   	//如果考试已过期
                   				if((item.examStatus == 1 || item.examStatus == 2) && item.examState == 'FINISHED'){
	             				   return "未通过";
	             			   	}else{
	             			   		return "-";
	             			   	}
                   		   }
            		   }else{
            			   return "-";
            		   }
              		}
              	},
               {title:'已参与次数/总次数',name:'testTimes', width:40,align:'center',
              		renderer:function(val,item, rowIndex){
             		   return item.testTimes + "/" + item.allowTimes;
                	}
              	},
               {title:'得分/通过分数/总分',name:'passScore', width:100,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   //if(item.examStatus != 1){
            			   if(item.isScorePublished == 1){
                			   return item.score+"/"+item.passScore+"/"+item.totalScore;
                		   }else{
                			   return "-/"+item.passScore+"/"+item.totalScore;
                		   }
            		   /* }else{
            			   return "-/"+item.passScore+"/"+item.totalScore;
            		   } */
               	}
               },
               {title:'名次',name:'rownum', width:30,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   /* if(item.rownum == null){
            			   return "-/"+item.totalnum;
            		   }else{ */
            			   if(item.isScorePublished == 1){
            				   return item.rownum+"/"+item.totalnum;
                		   }else{
                			   return "-/"+item.totalnum;
                		   }
            		   /* } */
                  	}
              	},
               {title:'操作',name:'operation', width:150,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str = '';
                		var str_enter = '<a href="javascript:void(0);" onclick="enterExam('+ rowIndex +')" >进入考试</a>&nbsp;';
                		var str_detail = '<a href="javascript:void(0);" onclick="examDetail('+ rowIndex +')" >答题详情</a>&nbsp;';
                		var str_detail2 = '<a href="javascript:void(0);" onclick="examDetail('+ rowIndex +')" >试题详情</a>&nbsp;';
                		var str_grade = '<a href="javascript:void(0);" onclick="checkGrade('+ rowIndex +')" >成绩总览</a>';
                		/* str = '<div id="timeDiv'+item.id+'" style="display:none;">距离考试还有：<br/><span class="timer" id="timeCounter'+item.id+'"></span></div>'; */
                		if(item.examState == 'PROCESSING' || item.examState == 'BEFORE_START'){
                			str += str_enter;
                   		}	
                   		if(item.examState == 'FINISHED' && (item.examStatus == 3 || item.examStatus == 4)){
                   			str += str_detail;
                   		}
                   		if(item.examState == 'FINISHED' && (item.examStatus == 1 || item.examStatus == 2)){
                   			str += str_detail2;
                   		}
                   		/* && item.isMarked == 1  */
                   		if(item.examState == 'FINISHED' && item.isScorePublished == 1){
                   			str += str_grade;
                   		}
                   		return str;	  
                	}	
	}] ,
	   plugins : [
	    	$('#paginator11-1').mmPaginator({
	    		totalCountName: 'total',    //对应MMGridPageVoBean count
	    		page: 1,                    //初始页
	    		pageParamName: 'page',      //当前页数
	    		limitParamName: 'pageSize', //每页数量
	    		limitList: [10, 20, 40, 50]
	    	})
	    ] 
	});
})

/*倒计时  */
var timeCounter = (function() {
	var int;
	//var total = (beginTime-curDate)/1000;
	return function(elemID,total) {
	 obj = document.getElementById(elemID);
	 /* var s = (total%60) < 10 ? ('0' + total%60) : total%60;
	 var h = total/3600 < 10 ? ('0' + parseInt(total/3600)) : parseInt(total/3600);
	 var m = (total-h*3600)/60 < 10 ? ('0' + parseInt((total-h*3600)/60)) : parseInt((total-h*3600)/60); */
	 var d = parseInt(total / 60 / 60 / 24, 10);//计算剩余的天数  
	 var h = parseInt(total / 60 / 60 % 24, 10);//计算剩余的小时数  
	 var m = parseInt(total / 60 % 60, 10);//计算剩余的分钟数  
	 var s = parseInt(total % 60, 10);//计算剩余的秒数 
	 obj.innerHTML = d + ' 天 ' + h + ' 时 ' + m + ' 分 ' + s + ' 秒 ';
	 total--;
	 int = setTimeout("timeCounter('" + elemID + "',"+total+")", 1000);
	 if(total < 0) {
		  clearTimeout(int);
		  //var id = elemID.substr(11);
		}
	};
})();

/*进入考试  */
function enterExam(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	/* var curDate = new Date().getTime();
	var beginTime = new Date(Date.parse(rowData.beginTime.replace(/-/g,"/"))).getTime();
	if(curDate < beginTime){
	 dialog({
			title : "提示",
			width : 200,
			height : 50,
			content :$("#timeDiv"+rowData.id),
			okValue : '确定',
			fixed:true,
			ok : function() {
				
			}
		}).showModal();
		timeCounter("timeCounter"+rowData.id,(beginTime-curDate)/1000);
	}else{ */
		if(rowData.testTimes >= rowData.allowTimes){
			dialog.alert("考试次数已达上限!");
		}else{
			window.location = "<%=request.getContextPath() %>/myExamManage/gotoEnterExam.action?id="+rowData.id
								+"&exam_assign_id="+rowData.examAssignId+"&is_anti_cheating="+rowData.isAntiCheating;
		}
	/* } */
}




/*答卷详情  */
function examDetail(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	window.open("<%=request.getContextPath() %>/myExamManage/gotoExamDetail.action?exam_assign_id="+rowData.examAssignId);
}

/*成绩总览  */
function checkGrade(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	window.location = '<%=request.getContextPath() %>/myExamManage/gotoGradeDetail.action?exam_assign_id='+rowData.examAssignId;
}

function jqchk(){ //jquery获取复选框值 
	 var chk_value ='';
	$('input[name="subcheck"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//查询
function query(){
	var chk_value = jqchk();
	if(chk_value == '0' || chk_value == '1'){
		is_passed = chk_value;
	}else{
		is_passed = null;
	}
	$('#examTable').mmGrid().load({"page":1});
	/*,"title":escapeForSql($.trim($("#title").val())),
									"begin_time":$("#beginTime").val(),"end_time":$("#endTime").val(),
									"is_passed":is_passed,"exam_status":$("#examStatus").val()  */
}

$(document).ready(function() {     
	$("#beginTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
 		//maxDate: da,
	});
	$("#endTime").datepicker({
		dateFormat:'yy-mm-dd',
		changeMonth: true,
        changeYear: true
		//minDate: 0,   
		//maxDate: da,
	});
}); 

//重置
function clearAll(){
	$("#beginTime").val("");
	$("#endTime").val("");
	$("#title").val("");
    $('input[name="subcheck"]').prop("checked","");
    query();
}

/*头部tab点击事件  */
$(function(){
	var examStatus = $("#examStatus").val();
	$('#ul_exam').find('li[id='+examStatus+']').addClass('li_a');
	$('#ul_exam').find('li[id!='+examStatus+']').removeClass();
	$('#ul_exam').find('li').click(function(){
		$('#ul_exam').find('li').attr('class','');
		$(this).attr('class','li_a');
		$("#examStatus").val($(this).attr("id"));
		query();
	})
});

function MillisecondToDate(msd) {
    var time = parseFloat(msd) / 1000;
    if (null != time && "" != time) {
        if (time > 60 && time < 60 * 60) {
            time = parseInt(time / 60.0) + "分钟" + parseInt((parseFloat(time / 60.0) -
                parseInt(time / 60.0)) * 60) + "秒";
        }else if (time >= 60 * 60 ) {
            time = parseInt(time / 3600.0) + "小时" + parseInt((parseFloat(time / 3600.0) -
                parseInt(time / 3600.0)) * 60) + "分钟" +
                parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
                parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60) + "秒";
        }else {
            time = parseInt(time) + "秒";
        }
    }
    return time;
}

Date.prototype.Format = function(formatStr)   {   
    var str = formatStr;   
    var Week = ['日','一','二','三','四','五','六'];  
  
    str=str.replace(/yyyy|YYYY/,this.getFullYear());   
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   
  
    str=str.replace(/MM/,this.getMonth()>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));   
    str=str.replace(/M/g,(this.getMonth()+1));   
  
    str=str.replace(/w|W/g,Week[this.getDay()]);   
  
    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
    str=str.replace(/d|D/g,this.getDate());   
  
    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
    str=str.replace(/h|H/g,this.getHours());   
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
    str=str.replace(/m/g,this.getMinutes());   
  
    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
    str=str.replace(/s|S/g,this.getSeconds());   
  
    return str;   
}   

</script>
</head>
<body>
<div id="timeDiv" style="display:none;">
	距离考试还有：<br/>
	<span class="timer" id="timeCounter"></span></div>
<input type="hidden" name="userId" id="userId"/>
<input type="hidden" name="examStatus" id="examStatus" value="0"/>
<div id="course_all">
	<div class="notes_list" style="padding-bottom: 0px;">
    	<!-- <h3>我的考试</h3> -->
    	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的考试</span>
		</div>
         <div class="ul_exam">
        	<ul id="ul_exam">
            	<li class="li_a" id="0">全部</li>
                <li id="1">未开始</li>
                <li id="2">进行中</li>
                <li id="3">已提交</li>
                <li id="4">已结束</li>
                <li id="5">已过期</li>
            
            </ul>
        </div>
        <div class="search_1">
        	
                    <span>
                    	考试时间：<input type="text" id="beginTime" name="beginTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                    	至 <input type="text" id="endTime" name="endTime" style="width:80px;height:25px;" value='' readonly="readonly"/>
                    </span>
                     <span>考试名称：<input type="text" id="title" name="title"/></span>
                     <!-- <span>考试状态
                     <select id="examStatus" name="examStatus" style="width:80px;">
                     	<option value="0" selected="selected">显示全部</option>
                        <option value="1">未开始</option>
                        <option value="2">未提交</option>
                        <option value="3">已提交</option>
                        <option value="4">已批阅</option>
                        <option value="5">已过期</option>
                     </select>
                     </span> -->
                     <strong>是否通过：是<input type="checkbox" name="subcheck" value="1"/>否<input type="checkbox" name="subcheck" value="0"/></strong>
                    <input type="button" class="btn_5" onclick="query();" value="查询" />
                    <input type="button" class="btn_6" onclick="clearAll();" value="重置" />
                  
            
        </div>
        <!-- <div class="tab_1">
        	<table cellspacing="0" width="100%">
            	<tr>
                	<th width="66px;">考试名称</th>
                    <th width="250px;">考试时间</th>
                    <th width="98px;">成绩是否公开</th>
                    <th width="93px;">个人考试状态</th>
                    <th width="68px;">是否通过</th>
                    <th width="64px;">考试次数</th>
                    <th width="130px;">得分/通过分数/总分</th>
                    <th width="62px;">名次</th>
                    <th>操作</th>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
                <tr>
                	<td class="ex_name">测试发布</td>
                    <td>2015-6-10 12:30至2015-6-10 12:30</td>
                    <td>是</td>
                    <td>进行中</td>
                    <td>否</td>
                    <td>0/99</td>
                    <td>--/60-100</td>
                    <td>--/199</td>
                    <td><a href="#" class="first_a">进入考试</a><a href="#">答卷详情</a><a href="#">成绩总览</a></td>
                </tr>
            
            </table>
        
        </div> -->
     </div>
     <div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="examTable"></table>
	  	<div id="paginator11-1" style="text-align:right;"></div>
     </div>
       <!--  <div class="page clear">
     	<div class="left_page fl">
        	<a href="#" class="first">20</a>
            <a href="#">40</a>
            <a href="#">100</a>
            <a href="#">200</a>
        </div>
        <div class="right_page fr">
        	<span>共254条</span>
            <a href="#" class="r_first">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">></a>
            <span class="span_1">去第<input type="text" />页</span>
            <input type="button" name="tz" value="跳转" class="btn_2"/>            
        </div>
     </div> -->
    </div>
	 <%@include file="../common/footer.jsp" %>
</body>
</html>
