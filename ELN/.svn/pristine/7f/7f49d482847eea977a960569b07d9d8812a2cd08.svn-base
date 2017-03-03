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
	font-size:14px;
	font-family:微软雅黑;
	margin-right:5px;
	margin-top:2px;
	cursor: pointer;
	border-radius:2px;
	border:none;
	padding:5px 10px;
	color: #ffffff;
	background-color: #0c9c92;
	float:right;
	margin-bottom: 5px;
}
#infoDiv{overflow: hidden;border: 1px solid #ccc;border-top: none;}
#infoDiv .leftDiv{
	float: left;
	width: 246px;
	margin-top: 20px;
	font-size: 14px;
	font-family: 微软雅黑;
	color: #0085fe;
}
#infoDiv .leftDiv ul{padding-left: 5px;}
#infoDiv .leftDiv ul li{
	margin: 3px 0px;
	cursor: pointer;
}

</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqmeter.min.js"></script>
<title>结果汇总</title>
<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';// current user
//修改时的回填参数
var iBean = ${iBean};
//var iBean_userList = ${iBean_userList};
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qType = ["","单","多","主","评"];
var html = "";
$(function(){
	if(iBean){
    	//alert(JSON.stringify(qBean.paperCategory));
    	//修改， 回填
    	$("#title").text(iBean.title);
    	$("#timePeriod").text(iBean.beginTime+" - " + iBean.endTime);
    	$("#aim").html(iBean.aim);
    	$("#isPublic").text(iBean.isPublic==0?"否":"是");
    }
	getResultDetail();
	//alert($("#questionnaireDiv").eq(0).height() );
});

/*获取问卷信息  */
function getResultDetail(){
	$("#questionnaireDiv").html('<span style="font-size:20px;">正在加载中，请稍后...<span>');
	   $.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":iBean.id},
		url: "<%=request.getContextPath()%>/questionnaire/getResultDetail.action",
		success:function(data){
			if(data != null){
				appendLeftContent(data);
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

/*生成左侧题填干信息  */
function appendLeftContent(qList){
	$("#contentLi").html("");
	for(var i=0;i<qList.length;i++){
		var type = qList[i].type;
		var content = "";
		content += '<li onclick="findQuestion('+qList[i].id+');">';
		content += '<span><input type="button" class="optionTypeClass" value="'+qType[type]+'"/></span>';
		content += '<span>' + qList[i].content;
		content += '</span>';
		content += '</li>';
		$("#contentLi").append(content);
	}
}

/*问题定位  */
function findQuestion(qid){
	document.getElementById('div_'+qid).scrollIntoView(true);
}

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
	    html += '<div class="question_div" id="div_'+id+'" style="width:800px;border-bottom: none;" questionId="'+id+'" questionType="'+type+'" isRequired="'+isRequired+'">';
	    html += '<div style="background-color:#eee;padding: 5px;">';
	    //html += '<span><input type="button" class="optionTypeClass" value="'+qType[type]+'"/></span>';
		html += '<span class="question_no">'+(q_index++)+'</span>. ';
	    html += '<span class="question_content">'+content+'（'+qType[type]+'）</span>';
    	//html += '<span class="question_png">（' + qType[type];
	    //html += '）</span>';
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
    	optionHtml += '<div>';
    	for(var i=0;i<anList.length;i++){
    		optionHtml += '<div style="border-bottom:1px solid #ccc;padding: 10px;">';
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
        	optionHtml += '<tr>';
        	optionHtml += '<td style="width:40%;"><p><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p></td>';
        	optionHtml += '<td style="width:10%;"><div>'+options[i].selectNum + '人</td>';
        	optionHtml += '<td style="width:40%;"><div style="inline-block;"><div id="jqMeter_div'+options[i].id+'" total="'+questionBean.totalAnswerNum
            				+'" selectNum="'+options[i].selectNum+'"></div></div></td>';
        	optionHtml += '<td style="width:10%;"><div>'+options[i].selectPercent + '%</td>';
        	optionHtml += '</tr>';
        }
        optionHtml += '</table>';
    }else if(type == 2){//多选题
    	optionHtml += '<table width="90%">';
    	 for(var i=0;i<options.length;i++){
         	optionHtml += '<tr>';
         	optionHtml += '<td style="width:40%;"><p><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p></td>';
         	optionHtml += '<td style="width:10%;"><div>'+options[i].selectNum + '人</td>';
         	optionHtml += '<td style="width:40%;"><div style="inline-block;"><div id="jqMeter_div'+options[i].id+'" total="'+questionBean.totalAnswerNum
             				+'" selectNum="'+options[i].selectNum+'"></div></div></td>';
         	optionHtml += '<td style="width:10%;"><div>'+options[i].selectPercent + '%</td>';
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



//获得单选按钮选中的值
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}
/*判空  */
function setNull(title){
    if(title == null){
        return "暂无";
    }
    return title;
}

//jquery获取复选框值 
function jqchk(name){ 
	 var chk_value ='';
	$('input[name="'+name+'"]:checked').each(function(){ 
		chk_value = chk_value + $(this).val() + ","; 
	}); 
	if(chk_value != ''){
		return chk_value.substr(0,chk_value.length-1);
	}
	return chk_value;
}

//获得单选按钮选中的值
function GetRadioValue(RadioName){
    var obj;    
    obj=document.getElementsByName(RadioName);
    if(obj!=null){
        var i;
        for(i=0;i<obj.length;i++){
            if(obj[i].checked){
                return obj[i].value;            
            }
        }
    }
    return null;
}

//判断数组中是包含某个元素 通用方法
Array.prototype.contains = function (element){
	for (var i=0; i<this.length;i++){
		if (this[i] == element){ 
			return true; 
		} 
	} 
	return false; 
};

//返回调查汇总统计页面
function backList(){
	window.location.href = "<%=request.getContextPath()%>/myQuestionnaire/toList.action";
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
<input type="hidden" id="deptId"/>
<div class="content" style="padding-bottom: 0px;">
	<!-- <h3 style="background-image: none;padding-left: 0px;font-size: 16px;color: #010101;border-bottom: 1px solid #000000;padding-left: 8px;">结果汇总</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backList();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">结果汇总</span>
	</div>
	<div id="investigationDiv">
		<div style="text-align: center;padding-bottom: 5px;font-size: 18px;font-family: 微软雅黑;">
			<span id="title"></span>
			<!-- <input type="button" value="打印" class="btn"/>
			<input type="button" value="展出Word" class="btn"/> -->
		</div>
		<div class="lesson_add_2" style="margin-bottom: 0px;padding-bottom: 0px;">
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
    		<span>问卷详情：</span>
    		单选题：<span id="type1" style="color:red;font-weight: bold;">0</span>&nbsp;
    		多选题：<span id="type2" style="color:red;font-weight: bold;">0</span>&nbsp;
    		主观题：<span id="type3" style="color:red;font-weight: bold;">0</span>&nbsp;
    		评星题：<span id="type4" style="color:red;font-weight: bold;">0</span>
    	</div>
    	<div style="float: right;padding-right:30px;">
    		结果是否公开：<span style="color:red;" id="isPublic"></span>
    	</div>
    </div>
    <div style="clear:both;"></div>
   	<div id="infoDiv">
    	<div id="qContentDiv">
    		<div class="leftDiv">
	    		<ul id="contentLi">
		    		<!-- <li>世界多李呈媛啊</li>
		    		<li>世界多李呈媛啊</li>
		    		<li>世界多李呈媛啊</li>
		    		<li>世界多李呈媛啊</li> -->
		    		
	    		</ul>
    		</div>
    	</div>
	    <div style="float:right;width:800px">
	    	<div id="questionnaireDiv" class="q_m2">
	    		
	    	</div>
	    </div>
    </div>
	<div style="clear:both;"></div>
    <div class="" style="padding-top: 10px;padding-bottom:15px">
          <input type="button" value="返回" class="buttonClass" style="background-color: #999999" onclick="backList()" />
     </div>
</div>

	
</body>
</html>
