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

</style>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<title>查看问卷</title>
<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';// current user
//修改时的回填参数
var assignId = ${assignId};
var qId = ${qId};
//var iBean_userList = ${iBean_userList};
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qType = ["","单","多","主","评"];
var html = "";
$(function(){
	$("#assignId").val(assignId);
	getQuestions();
});

/*获取问卷信息  */
function getQuestions(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"qId":qId, "assignId":assignId},
		url: "<%=request.getContextPath()%>/train/getAnswerDetail.action",
		success:function(data){
			if(data != null){
				html = '';
				for(var i=0;i<data.length;i++){
					appendQuestion(data[i]);
				}
				$("#questionnaireDiv").append(html);
				for(var i=0;i<data.length;i++){
					var qId = data[i].id;
					var qType = data[i].type;
					if(qType == 4){
						initStar(qId,data[i].answer);
					}
				}
				
			}else{
				dialog.alert("操作失败！");
			}
	    }
	});
}

$(function(){
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
	    var answer = questionBean.answer;
	    //拼接问题
	    html += '<div class="question_div" questionId="'+id+'" questionType="'+type+'" isRequired="'+isRequired+'">';
	    //隐藏域
	    html += '<input id="question'+id+'" name="questionId" type="hidden" value="'+id+'"/>';
	    html += '<input id="ans_'+id+'" name="answer" type="hidden" value=""/>';
	  	//题目状态标识 1-已完成 2-未完成
	  	if(isRequired == 1){
	  		html += '<input type="hidden" id="state_'+id+'" value="2" />';
	  	}else{
	  		html += '<input type="hidden" id="state_'+id+'" value="1" />';
	  	}
	    html += '<div>';
	    html += '<span><input type="button" class="optionTypeClass" value="'+qType[type]+'"/></span>';
	    if(isRequired == 1){
	    	html += '<span style="color:red;">*</span>';
	    }
		html += '<span class="question_no">'+(q_index++)+'</span>. ';
	    html += '<span class="question_content">'+content+'</span>';
	    html += '<span class="question_png" >';
	    html += '</span>';
	    html += '</div>';
	    html += concatOptions(id,type,isRequired,options,answer);
	    html += '</div>';
		//统计问题类型数量
		$("#type"+type).text(parseInt($("#type"+type).text())+1);
	}
}

/*拼接选项 */
function concatOptions(questionId,type,isRequired,options,answer){
    var optionHtml = '<div id="optionDiv" class="option_div">';
    if(type == 3){//主观题
    	if(answer != '-' && answer != null){
    		optionHtml += '<textarea style="width:300px;height:100px;" name="option_'+questionId
			+'" disabled="disabled">'+answer+'</textarea>';
    	}else{
    		optionHtml += '<textarea style="width:300px;height:100px;" name="option_'+questionId
			+'" disabled="disabled"></textarea>';
    	}
    }else if(type == 1){//单选题
        for(var i=0;i<options.length;i++){
        	if(options[i].id == answer){
        		optionHtml += '<p><input type="radio" name="option_'+questionId
                +'" disabled="disabled" checked="checked" value="'+options[i].id+'"/>'
                +'<span>'+optionName[i+1]+'.'+'</span>'
                +'<span>'
                +setNull(options[i].content)+'</span></p>';
        	}else{
        		optionHtml += '<p><input type="radio" name="option_'+questionId
                +'" disabled="disabled" value="'+options[i].id+'"/>'
                +'<span>'+optionName[i+1]+'.'+'</span>'
                +'<span>'
                +setNull(options[i].content)+'</span></p>';
        	}
        }
    }else if(type == 2){//多选题
    	var ans = [];
    	if(answer != null){
    		ans = answer.split(",");
    	}
        for(var i=0;i<options.length;i++){
        	if(ans.contains(options[i].id)){
        		optionHtml += '<p><input type="checkbox" name="option_'+questionId
            	+'" disabled="disabled" checked="checked" value="'+options[i].id+'"/>'
            	+'<span>'+optionName[i+1]+'.'+'</span>'
	            +'<span>'
	            +setNull(options[i].content)+'</span></p>';
        	}else{
        		optionHtml += '<p><input type="checkbox" name="option_'+questionId
            	+'" disabled="disabled" value="'+options[i].id+'"/>'
            	+'<span>'+optionName[i+1]+'.'+'</span>'
	            +'<span>'
	            +setNull(options[i].content)+'</span></p>';
        	}
            
        }
    }else{//评星题
        optionHtml += '<div qId="'+questionId+'" id="star'+questionId+'" style="margin-left: 10px;"></div>';
    }
    optionHtml += '</div>';
    return optionHtml;
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
	history.back(-1);
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
	<!-- <h3 style="background-image: none;padding-left: 0px;font-size: 16px;color: #010101;border-bottom: 1px solid #000000;padding-left: 8px;">查看问卷</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看问卷</span>
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
    </div>
    <form id="questionForm">
    	<input type="hidden" id="assignId" name="assignId" value="">
	    <div style="border: 1px solid #ccc;padding-bottom: 5px;">
	    	<div id="questionnaireDiv" class="q_m2" style="padding-left: 5px;">
	    		
	    	</div>
	    </div>
    </form>
    <div class="" style="padding-top: 15px;">
          <input type="button" value="返回" class="buttonClass" style="background-color: #999999" onclick="backList()" />
     </div>
</div>

	
</body>
</html>
