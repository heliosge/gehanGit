<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.optionTypeClass{
	font-size: 14px;
  	font-family: 微软雅黑;
  	margin-right: 5px;
  	border-radius: 2px;
  	border: none;
  	background-color: #def3df;
  	padding: 2px 5px;
  	color: #6b9c92;
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
<title>填写问卷</title>
<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';// current user
//修改时的回填参数
var iBean = ${iBean};
var assignId = ${assignId};
//var iBean_userList = ${iBean_userList};
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qType = ["","单","多","主","评"];
var html = "";
$(function(){
	$("#assignId").val(assignId);
	getQuestions();
});

function getQuestions(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":iBean.id},
		url: "<%=request.getContextPath()%>/questionnaire/getQuestions.action",
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
						initStar(qId);
					}
				}
			}else{
				dialog.alert("操作失败！");
			}
	    }
	});
}

$(function(){
	if(iBean){
    	//alert(JSON.stringify(qBean.paperCategory));
    	//修改， 回填
    	$("#title").text(iBean.title);
    	$("#timePeriod").text(iBean.beginTime+" - " + iBean.endTime);
    	$("#aim").html(iBean.aim);
    	$("#isPublic").text(iBean.isPublic==0?"否":"是");
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
	    html += concatOptions(id,type,isRequired,options);
	    html += '</div>';
		//统计问题类型数量
		$("#type"+type).text(parseInt($("#type"+type).text())+1);
	}
}

/*拼接选项 */
function concatOptions(questionId,type,isRequired,options){
    var optionHtml = '<div id="optionDiv" class="option_div">';
    if(type == 3){//主观题
    	optionHtml += '<textarea style="width:300px;height:100px;" name="option_'+questionId
    				+'" onblur="setCheckAnswer(this,'+questionId+','+type+','+isRequired+');"></textarea>';
    }else if(type == 1){//单选题
        for(var i=0;i<options.length;i++){
            optionHtml += '<p><input type="radio" name="option_'+questionId
            +'" onclick="setCheckAnswer(this,'+questionId+','+type+','+isRequired+');" value="'+options[i].id+'"/>'
            +'<span>'+optionName[i+1]+'.'+'</span>'
            +'<span>'
            +setNull(options[i].content)+'</span></p>';
        }
    }else if(type == 2){//多选题
        for(var i=0;i<options.length;i++){
            optionHtml += '<p><input type="checkbox" name="option_'+questionId
            	+'" onclick="setCheckAnswer(this,'+questionId+','+type+','+isRequired+');" value="'+options[i].id+'"/>'
            	+'<span>'+optionName[i+1]+'.'+'</span>'
	            +'<span>'
	            +setNull(options[i].content)+'</span></p>';
        }
    }else{//评星题
        optionHtml += '<div qId="'+questionId+'" id="star'+questionId+'" style="margin-left: 10px;"></div>';
    }
    optionHtml += '</div>';
    return optionHtml;
}

/*设置用户答案  */
function setCheckAnswer(obj,questionId,type,isRequired){
	$("#state_"+questionId).val(1);
	if(type == 1){//单选题
		$("#ans_"+questionId).val($(obj).val());
	}else if(type == 2){//多选题
		var an = jqchk('option_'+questionId);
		if(an == ''){
			if(isRequired == 1){
				$("#state_"+questionId).val(2);
			}else{
				$("#state_"+questionId).val(1);
			}
		}
		$("#ans_"+questionId).val(an);
		
	}else if(type == 3){//主观题
		$("#ans_"+questionId).val(($.trim($(obj).val())));
		if($.trim($("#ans_"+questionId).val()) == ''){
			if(isRequired == 1){
				$("#state_"+questionId).val(2);
			}else{
				$("#state_"+questionId).val(1);
			}
		}
	}else if(type == 4){//评星题
		//$("#ua_"+questionId).val($(obj).val());
	}
	
}

/*初始化评分控件  */
function initStar(qId){
	var setting = {
			max : 5,
			image : "../js/rater-star/star.gif",
			after_click : function(ret) {
				$("#ans_"+qId).val(ret.value);
				$("#state_"+qId).val(1);
			}
	};
	//$("div[id^=star]").each(function(index,element){
		$("#star"+qId).rater(setting);
	//});
}


/*返回  */
function goBack(){
	window.location = "<%=request.getContextPath()%>/questionnaire/gotoInvestigationList.action";
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

/*提交试卷  */
function submitQuestionnaire(){
	var comFlag = true;
	$("input[id^='state_']").each(function(index,element){
		if($.trim($(this).val()) == 2){
			comFlag = false;
		}
	});
	if(!comFlag){
		dialog.alert("还有未完成的问题");
	}else{
		dialog.confirm('确认提交吗？', function(){
			var d2 = dialog({
			    content: '正在提交，请等待...'
			});
			d2.showModal();
			$("input[id^='ans_']").each(function(index,element){
				if($.trim($(this).val()) == ''){
					$(this).val("-");
				}
			});
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data: $("#questionForm").serialize(),
				url: "<%=request.getContextPath()%>/myQuestionnaire/submitQuestionnaire.action",
				success:function(data){
					if(data != null && data != 'FAIL'){
						d2.close();
						dialog.alert("提交成功！", function (){
							backList();
						});
					}else{
						d2.close();
						dialog.alert("提交失败！");
					}
			    } 
			});
		});
	}
	
}

//返回调查列表
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
<div class="content">
	<!-- <h3>查看调查</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backList();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看调查</span>
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
    <form id="questionForm">
    	<input type="hidden" id="assignId" name="assignId" value="">
	    <div style="border: 1px solid #ccc;padding-bottom: 5px;">
	    	<div id="questionnaireDiv" class="q_m2" style="padding-left: 5px;">
	    		
	    	</div>
	    </div>
    </form>
    <div class="" style="padding-top: 15px;">
          <input type="button" value="提交" class="buttonClass" style="background-color: #dd0500" onclick="submitQuestionnaire()" />
          <input type="button" value="返回" class="buttonClass" style="background-color: #0085fe" onclick="backList()" />
     </div>
</div>

	
</body>
</html>
