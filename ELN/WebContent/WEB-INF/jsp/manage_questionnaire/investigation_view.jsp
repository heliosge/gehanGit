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
<title>新增调查</title>
<script type="text/javascript">
//zhangchen start
var userId = '${USER_ID_LONG}';// current user
//修改时的回填参数
var iBean = ${iBean};
var iBean_userList = ${iBean_userList};
var optionName = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var qType = ["","单","多","主","评"];
var html = "";
$(function(){
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
    	//按组织架构，需要初始化人员
    	if(iBean.intendType == 2){
    		//加载人员数据
    		if(iBean_userList){
    			initList(iBean_userList);
    		}
    	}else{
    		$("#userList").append("全员");
    	}
    }
});

/*生成人员列表  */
function initList(tempList){
	$("#userList").empty();
	for(var i=0;i<tempList.length;i++){
		$("#userList").append(tempList[i].name);
		if(i != tempList.length-1){
			$("#userList").append(", ");
		}
	}
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
	    html += '<div class="question_div" questionId="'+id+'" questionType="'+type+'" isRequired="'+isRequired+'">';
	    html += '<div>';
	    html += '<span><input type="button" class="optionTypeClass" value="'+qType[type]+'"/></span>';
		html += '<span class="question_no">'+(q_index++)+'</span>. ';
	    html += '<span class="question_content">'+content+'</span>';
	    html += '<span class="question_png" >';
	    html += '</span>';
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
    	optionHtml += '<textarea style="width:300px;height:100px;" disabled="disabled" ></textarea>';
    }else if(type == 1){//单选题
        for(var i=0;i<options.length;i++){
            optionHtml += '<p><input type="radio" disabled="disabled"/><span>'+optionName[i+1]+'.'+'</span><span>'+setNull(options[i].content)+'</span></p>';
        }
    }else if(type == 2){//多选题
        for(var i=0;i<options.length;i++){
            optionHtml += '<p><input type="checkbox" disabled="disabled"/><span>'+optionName[i+1]+"."+'</span><span>'+setNull(options[i].content)+'</span></p>';
        }
    }else{//评星题
        optionHtml += '<img src="<%=request.getContextPath()%>/images/star.png" style="width:100px;"/>';
    }
    optionHtml += '</div>';
    return optionHtml;
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

//返回调查列表
function backList(){
	window.location.href = "<%=request.getContextPath()%>/questionnaire/gotoInvestigationList.action";
}

/**
 * 导出word
 */
function exportDoc(){
	//var form = $("#exportDocForm");
	//form.submit();
	window.location.href = '<%=request.getContextPath() %>/questionnaire/exportDoc.action?id=' +iBean.id;
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
<div class="content" style="padding-bottom: 0px;">
	<!-- <h3>查看调查</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="backList();"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看调查</span>
	</div>
	<div id="investigationDiv">
		<div style="text-align: center;padding-bottom: 5px;font-size: 18px;font-family: 微软雅黑;">
			<span id="title"></span>
			<input type="button" onclick="window.print();" value="打印" class="btn" style="background-color: #d60500;"/>
			<input type="button" id="exportDoc" value="导出Word" class="btn" style="background-color: #d60500;" onclick="exportDoc();"/>
		</div>
		<div class="lesson_add_2" style="margin-bottom: 0px;padding-bottom: 0px;">
	    	<div class="add_gr">
	        	<div class="add_fl">
	                <em>参与者：</em>
	            </div>
	            <div class="add_fr">
	            	<span id="userList"></span>
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
    <div style="border: 1px solid #ccc;padding-bottom: 5px;">
    	<div id="questionnaireDiv" class="q_m2" style="padding-left: 5px;">
    		
    	</div>
    </div>
    <div class="" style="padding-top: 15px;">
          <input type="button" value="返回" class="buttonClass" style="background-color: #0085fe;" onclick="backList()" />
     </div>
</div>

	
</body>
</html>
