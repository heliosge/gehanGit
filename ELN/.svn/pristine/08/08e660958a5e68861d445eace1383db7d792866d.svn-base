<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进入考试</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<style type="text/css">
.button {
  display: block;
  width: 184px;
  height: 42px;
  line-height: 42px;
  color: #fff;
  background: #0184d2;
  text-align: center;
  font-size: 16px;
  float: right;
  margin-top: 20px;
  background-color: #0184d2;
}

</style>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var paramMap = eval('${paramMap}');
var id = paramMap[0].id;//考试信息ID
//var exam_type = paramMap[0].exam_type;//考试类型
//var exam_status = paramMap[0].exam_status;//考试状态
var exam_assign_id = paramMap[0].exam_assign_id;//考试记录ID
var test_times = paramMap[0].test_times;//考试次数
var is_anti_cheating = paramMap[0].is_anti_cheating;
var currentDate = '${curDate}';
//alert(currentDate);
$(function(){
	/* $("#userId").val(userId);
	$("#id").val(id);
	$("#exam_type").val(exam_type);
	$("#exam_status").val(exam_status); */
	$("#exam_assign_id").val(exam_assign_id);
	//$("#is_auto_marking").val(is_auto_marking);
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":id},
		dataType:"json",
		url: "<%=request.getContextPath()%>/myExamManage/getExamInfo.action",
		success:function(data){
			//alert(JSON.stringify(data));
			if(data != null){
				$("#title").text(data.title);
				$("#totalScore").text(data.totalScore);
				$("#display_mode").text(data.displayMode);
				$("#passScore").text(Math.round(data.totalScore*(data.passScorePercent/100)));
				$("#duration").text(data.duration);
				$("#allow_times").val(data.allowTimes);
				//1  整卷显示2  单题可逆3  单题不可逆
				var dM = "";
				if(data.displayMode == 1){
					dM = "整卷显示";
				}else if(data.displayMode == 2){
					dM = "单题可逆";
				}else if(data.displayMode == 3){
					dM = "单题不可逆";
				}
				$("#displayMode").text(dM);
				$("#beginTime").text(data.beginTime);
				$("#endTime").text(data.endTime);
				
				$("#notice").html(data.notice);
				
				/*倒计时  */
				//var curDate = new Date().getTime();
				var curDate = new Date(Date.parse(currentDate.replace(/-/g,"/"))).getTime();
				var beginTime = new Date(Date.parse(data.beginTime.replace(/-/g,"/"))).getTime();
				//var endTime = new Date(Date.parse($("#endTime").text().replace(/-/g,"/"))).getTime(); 
				//alert(beginTime+"+++"+endTime+"+++"+curDate);
				if(curDate < beginTime){
					timeCounter("timeCounter",(beginTime-curDate)/1000);
					$("#startButton").attr("disabled",true);
					$("#startButton").css("background","#dbdbdb");
				}else{
					$("#startButton").attr("disabled",false);
					$("#startButton").css("background","#0184d2");
				}
			}
	    }
	});
});
//var keyWord = [];
function startTest(){
	/* if($("#allow_times").val() <= test_times){
		alert(test_times + "+++"+$("#allow_times").val());
		dialog.alert("考试次数已达上限!");
		return;
	} */
	//var curDate = new Date().getTime();
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{"beginTime":$("#beginTime").text(),"endTime":$("#endTime").text()},
		url: "<%=request.getContextPath()%>/myExamManage/getExamStatus.action",
		success:function(data){
			if(data == 'proccessing'){
				$.ajax({
					type:"POST",
					async:false,  //默认true,异步
					data:{"exam_assign_id":exam_assign_id},
					url: "<%=request.getContextPath()%>/myExamManage/getTestTimes.action",
					success:function(data){
						if(data != null){
							if($("#allow_times").val() <= data.testTimes){
								dialog.alert("考试次数已达上限!");
							}else{
								if(is_anti_cheating == 1){
									if(data.isAttend == 0){
										dialog.alert("离开考试页面次数已达"+data.leaveTimes+"次，不能再参加本场考试！");
									}else{
										window.open("<%=request.getContextPath() %>/myExamManage/gotoExamTest.action?exam_assign_id="+$("#exam_assign_id").val(),"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
									}
								}else{
									window.open("<%=request.getContextPath() %>/myExamManage/gotoExamTest.action?exam_assign_id="+$("#exam_assign_id").val(),"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
								}
								
							}
						}
				    }
				});
			}else if(data == 'notStart'){
				dialog.alert("考试还未开始，请耐心等待!");
			}else{
				dialog.alert("考试已结束!");
			}
	    }
	});
	<%-- var curDate = new Date(Date.parse(currentDate.replace(/-/g,"/"))).getTime();
	var beginTime = new Date(Date.parse($("#beginTime").text().replace(/-/g,"/"))).getTime();
	var endTime = new Date(Date.parse($("#endTime").text().replace(/-/g,"/"))).getTime();
	//alert(curDate + "--------" + endTime);
	if(curDate < beginTime){
		dialog.alert("考试还未开始，请耐心等待!");
	}else{
		if(curDate < endTime){//考试进行中
			$.ajax({
				type:"POST",
				async:false,  //默认true,异步
				data:{"exam_assign_id":exam_assign_id},
				url: "<%=request.getContextPath()%>/myExamManage/getTestTimes.action",
				success:function(data){
					if(data != null){
						if($("#allow_times").val() <= data.testTimes){
							dialog.alert("考试次数已达上限!");
						}else{
							if(is_anti_cheating == 1){
								if(data.isAttend == 0){
									dialog.alert("离开考试页面次数已达"+data.leaveTimes+"次，不能再参加本场考试！");
								}else{
									window.open("<%=request.getContextPath() %>/myExamManage/gotoExamTest.action?exam_assign_id="+$("#exam_assign_id").val(),"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
								}
							}else{
								window.open("<%=request.getContextPath() %>/myExamManage/gotoExamTest.action?exam_assign_id="+$("#exam_assign_id").val(),"_blank","toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
							}
							
						}
					}
			    }
			});
		}else{
			dialog.alert("考试已结束!");
		}
	} --%>
}

/*验证考试时间  */
function valdateTime(){
	var curDate = new Date().getTime();
	//var curDate = new Date(Date.parse(currentDate.replace(/-/g,"/"))).getTime();
	var beginTime = new Date(Date.parse($("#beginTime").text().replace(/-/g,"/"))).getTime();
	var endTime = new Date(Date.parse($("#endTime").text().replace(/-/g,"/"))).getTime(); 
	//alert(curDate+"+++"+endTime);
	if(curDate < beginTime){
		$("#startButton").attr("disabled",true);
		$("#startButton").css("background","#dbdbdb");
	}else{
		if(curDate < endTime){//考试进行中
			$("#startButton").attr("disabled",false);
			$("#startButton").css("background","#0184d2");
		}else{
			$("#startButton").attr("disabled",true);
			$("#startButton").css("background","#dbdbdb");
		}
	}
}
/*监听考试时间  */
window.onload=function(){
	//setInterval(valdateTime,1)
};


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
		  $("#timeCounter").remove();
		  $("#startButton").attr("disabled",false);
		  $("#startButton").css("background","#0184d2");
		}
	};
})();

</script>
</head>

<body>
<form id="form" target="_blank">
	<!-- <input type="hidden" id="userId" name="userId"/>
	<input type="hidden" id="id" name="id"/>
	<input type="hidden" id="exam_type" name="exam_type"/> -->
	<input type="hidden" id="allow_times" name="allow_times"/>
	<input type="hidden" id="exam_assign_id" name="exam_assign_id"/>
	<!-- <input type="hidden" id="is_auto_marking" name="is_auto_marking"/>
	<input type="hidden" id="display_mode" name="display_mode"/> -->
</form>
<div class="enter_e">
	<div class="enter_top">
    	<div class="enter_left">
        	<h3 id="title"></h3>
           <!-- <p>考试总分：<span id="totalScore"></span>通过分数：<span id="passScore"></span></p>
            	<p>考试时长：<span id="duration"></span>显示方式：<span id="displayMode"></span></p> -->
            <table>
            	<tr>
            		<td>考试总分：</td>
            		<td><span id="totalScore"></span></td>
            		<td>通过分数：</td>
            		<td><span id="passScore"></span></td>
            	</tr>
            	<tr>
            		<td>考试时长：</td>
            		<td><span id="duration"></span></td>
            		<td>显示方式：</td>
            		<td><span id="displayMode"></span></td>
            	</tr>
            </table>
        </div>
        <div class="enter_right">
        	<p>考试时间：<span id="beginTime"></span>至<span id="endTime"></span></p>
           <!--  <a href="javascript:void(0);" onclick="startTest();">开始考试</a> -->
           	<p><span id="timeCounter" style="font-family: 微软雅黑;font-size: 19px;color:red;"></span></p>
           <button id="startButton" type="button" class="button" onclick="startTest();">开始考试 </button>
        </div>
    </div>
    <div class="before_exam">
    	<h5 style="font-size: 16px;">考试须知：</h5>
        <p style="text-indent: 0em;"><span id="notice"></span></p>
    	<!-- <p>Kaoqianxuzhimoban</p>
    	<p>学员请仔细阅读考前须知：</p>
        <p>1、禁止携带通信设备进入场地</p>
        <p>2、请考生在考试前半小时进入场地</p>
        <p>3、考生在拿到答题卡后，请正确填写考试相关信息</p> -->
    </div>

</div>
</body>
</html>
