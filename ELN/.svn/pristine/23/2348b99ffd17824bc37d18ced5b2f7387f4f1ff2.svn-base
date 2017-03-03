<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>编辑学习计划阶段</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!-- validation -->
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
<!-- 时间处理 -->
<jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>
<style type="text/css">

.param_table td{
	padding: 2px;
}
.param_table .input_0{
	width: 200px;
}

.need_span{color:red;font-size:13px;}
.validation_span{color:red;font-size:13px;}
</style>

<script type="text/javascript">

var planId = ${planId};
var stageList = ${stageList};
var learnPlan = ${learnPlan};
var beginTime,endTime;
if(learnPlan&&learnPlan.beginTime&&learnPlan.endTime){
    beginTime=  learnPlan.beginTime.replace(/-/g,"/");
    endTime=  learnPlan.endTime.replace(/-/g,"/");
}
var newStage={};
var tempStage={};
  tempStage.planId=planId;
  newStage.planId=planId;
var stageInfoHtml='';
$(function(){
 
	
	//验证名称(中文，数字，字母)
	$.validator.addMethod(
	    "onlyName",
	    function (value, element){
	    	var withOutChinese = /^([\u4E00-\u9FA5]|\w)*[^_]$/;
	    	return this.optional(element) || withOutChinese.test(value); 
	    },
	    "请输入正确的名称"
    );
	//验证数字(0-999)
	$.validator.addMethod(
	    "onlyNumber",
	    function (value, element){
	    	var num = /^(0|([1-9]\d{0,2}))$/;
	    	return this.optional(element) || num.test(value); 
	    },
	    "请输入正确的数字"
    );
	
	//开始验证     
	$('form').validate({ 
		   focusCleanup:true,    
		   ignore: "", 
	    /**//* 设置验证规则 */    
	    rules: {     
	    	name: {     
	            required:true,
	            rangelength:[2, 30],
	             remote:{  
	                 url:"<%=request.getContextPath()%>/learnPlan/checkLearnPlanStageName.action",  
	                 type:"post",  
	                 dataType:"text",  
	                 data:{name:function(){
	                	  return $.trim($("#name").val());},
	                	  id:function(){
		                	  return $.trim($("#id").val());},
		                	  planId:planId},  
	                 dataFilter: function(data, type) {  
	                 	if(data=="SUCCESS"){
	                 		return true;
	                 	}else{
	                 		return false;
	                 	}
	                 }
	              },
	              onlyName:true
	           
	        },
	        
	        lecturers: {     
	        	maxlength:2000
	           
	        },
	        learnTime:{
	        	required:true,
	        	onlyNumber:true
	        },
	        learnScore:{
	        	required:true,
	        	onlyNumber:true
	        },
	     
	        description: {
	        	maxlength:1000
	        }
	    },      
	    /**//* 设置错误信息 */    
	    messages: {     
	    	name: {         
	        	required:"阶段名称不可为空",
	        	rangelength:"阶段名称必须在2-30个字符之间",
	        	remote:"阶段名称已经存在",
	            onlyName:"请输入正确的阶段名称(中文,数字,字母)"
	        },
	      
	        learnTime:{
	        	required:"学时不可为空",
	        	onlyNumber:"请输入正确的数字(0-999)"
	        },
	        learnScore:{
	        	required:"学分不可为空",
	        	onlyNumber:"请输入正确的数字(0-999)"
	        },
	        lecturers: {         
	        	maxlength:"添加的讲师过多"
	        },
	        description: {     
	            maxlength:"最多1000个字符"
	        }
	    },      
	    /**//* 设置验证触发事件 */
	    onsubmit:true,
	    /**//* 设置错误信息提示DOM */    
	   
	    submitHandler: function (){
	    	//alert("sunmit");
	    	save();
	    }
	}); 
   	renderStages();
	renderCourseList({});
	
	if(stageList==null||stageList.length==0){
		addStage();
	}else{
		viewStage(stageList.length);
	}
});

//返回
function goBack(){
	dialog.confirm('确认返回吗？', function(){ 
		
	    	window.location.href = "<%=request.getContextPath()%>/learnPlan/toLearnPlanListPage.action";
	    
	});
}
//下一步
function goNext(){

		window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanAssignment.action?planId="+planId;

    	
    
}
//上一步
function goLast(){

		window.location.href = "<%=request.getContextPath()%>/learnPlan/learnPlanEdit.action?id="+planId;

}
//渲染页面
function renderStages(){
	if(stageList==null||stageList.length==0){
		
		return;
	}
	$mainDiv = $("#main_div");
	$(".tool_1").remove();
	$("#editForm").hide();
	var addTittleStr="";
	for(var i = 0;i<stageList.length;i++){
		var htmlStr='<div class="tool_1" id="stage_div_'+(i+1)+'"  >'+
		    '<span id="stage_title_'+(i+1)+'" style="padding-left:5px;width:200px" > 第'+(i+1)+'阶段:'+stageList[i].name+'</span>'+
		    '<a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;"'+ 
		    'onclick="viewStage('+(i+1)+')" class="a_ss"><img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png"/></a>';
		    if(i!=0){
		    	htmlStr+='<a href="javascript:void(0)"  onclick="doUp('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/ico_tool_1.png" class="img_1" /></a>';
		    }
	    	if(i!=(stageList.length-1)){
	    		htmlStr+='<a href="javascript:void(0)"  onclick="doDown('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/ico_tool_1.png" class="img_2" /></a>';
	    	}
	        
	    	htmlStr+='<a href="javascript:void(0)" onclick="doDelete('+(i+1)+')"><img src="<%=request.getContextPath()%>/images/img/ico_tool_1.png" class="img_3" /></a>'+
	        '</div>';
		 addTittleStr+=htmlStr;
	}
	
	$mainDiv.append(addTittleStr);
	cleanForm();
}

function newStageDiv(){
	var i=stageList?stageList.length:0;
	$mainDiv = $("#main_div");
		var htmlStr='<div class="tool_1" id="stage_div_0"  >'+
		    '<span id="stage_title_0" style="padding-left:5px"> 第'+(i+1)+'阶段</span>'+
	        '</div>';
	$mainDiv.append(htmlStr);
}
//增加阶段
function addStage(){
	
	var oldIndex = $("#index").val();
	//var newStage = fillStage();
	var oldStage = stageList[oldIndex-1];
	$("#editForm").show();
	/*if(oldStage!=newStage){
		dialog.confirm('是否放弃现在编辑的内容？', function(){ 
			
				var $StageDiv = $("#stage_div_0");
				if($StageDiv.length==0){
					newStageDiv();
					$StageDiv = $("#stage_div_0");
				}else{
					$StageDiv.show();
				}
				var $editForm= $("#editForm");
				$StageDiv.after($editForm);
				courseListReload(newStage.courseList);
				cleanForm();
				$("#index").val(0);
		    
		});
	}else{*/
		var $StageDiv = $("#stage_div_0");
		if($StageDiv.length==0){
			newStageDiv();
			$StageDiv = $("#stage_div_0");
		}else{
			$StageDiv.show();
		}
		var $editForm= $("#editForm");
		$StageDiv.after($editForm);
		cleanForm();
		if(tempStage){
			newStage=tempStage;
		}
		if(!newStage.courseList){
			newStage.courseList=[];
		}
		courseListReload(newStage.courseList);
		$("#id").val(newStage.id);
		$("#name").val(newStage.name);
		$("#lecturers").val(newStage.lecturers);
		$("#learnScore").val(newStage.learnScore);
		$("#learnTime").val(newStage.learnTime);
		$("#description").val(newStage.description);
	    
		$("#index").val(0);
	//}

}
//清除form数据
function cleanForm(){
	$("#id").val("");
	$("#index").val("");
	$("#name").val("");
	$("#lecturers").val("");
	$("#learnScore").val("");
	$("#learnTime").val("");
	$("#description").val("");
	$('form').validate().resetForm()
	newStage={};
}
//填充stage
function fillStage(stage){
	if(!stage){
		var stage={};
	}
	
	stage.id=$("#id").val();
	stage.name=$("#name").val();
	stage.lecturers=$("#lecturers").val();
	stage.learnScore=$("#learnScore").val();
	stage.learnTime=$("#learnTime").val();
	stage.description=$("#description").val();
	return stage;
}
//查看阶段
function viewStage(i){

	var oldIndex = $("#index").val();
	if(oldIndex==i){
		$("#editForm").toggle();
		return;
	}
	/*newStage = fillStage();
	var oldStage={};
	if(oldIndex!=null){
		oldStage = stageList[oldIndex-1];
	}
	if(newStage!=null&&(newStage.name||newStage.courses||newStage.learnTime||newStage.learnScore)){
		dialog.confirm('是否放弃现在编辑的内容？', function(){ 
			
				var $StageDiv = $("#stage_div_"+i);
				var $editForm= $("#editForm");
				$StageDiv.after($editForm);
				$("#stage_div_0").hide();
				$("#editForm").show();
				renderStage(i);
		    
		});
	   
	}else{*/
		
	if(oldIndex==0&&newStage){
		tempStage=newStage;
		fillStage(tempStage);
	}
	var $StageDiv = $("#stage_div_"+i);
	var $editForm= $("#editForm");
	$StageDiv.after($editForm);
	$("#stage_div_0").hide();
	$("#editForm").show();
	renderStage(i);
	//}
}

//填充数据
function renderStage(i){
	if(!i||!stageList||!stageList[i-1]){
		return;
	}
	newStage=stageList[i-1];
	if(!stageList[i-1].courseList||stageList[i-1].courseList.length==0){
	
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'stageId':stageList[i-1].id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/learnPlan/getLearnPlanStageCourseList.action",
			success:function(data){
			  if(data.rows){
				  stageList[i-1].courseList=data.rows; 
				  newStage.courseList=data.rows;
				  courseListReload(data.rows);
			
			  }
			 
		    }
		});
	}else{
		courseListReload(newStage.courseList);
		
	}
	
	_renderStage(newStage,i);
	
}
function _renderStage(stage,i){
	if(!stage){
		return;
	}
	cleanForm();
	$("#id").val(stage.id);
	$("#index").val(i);
	$("#name").val(stage.name);
	$("#lecturers").val(stage.lecturers);
	$("#learnScore").val(stage.learnScore);
	$("#learnTime").val(stage.learnTime);
	$("#description").val(stage.description);
}

function renderCourseList(stage){
	//表格
	$('#courseTable').mmGrid({
	    items:stage.courseList,
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        //checkCol: true,
        multiSelect: false,
        indexCol: false,  //索引列
        cols: [
               /**{title: '课程编号', name: 'courseCode', width:30}, **/
			   {title: '课程名称', name: 'courseName', width:40},
			   {title: '学习开始时间', name: 'beginTime', width:40,
		        	  renderer:function(val,item, rowIndex){
					       return  '<input class="time" id="beginTime_'+rowIndex+'" name="beginTime_'+rowIndex+'" style="width:145px" onchange="beginTimeChange('+rowIndex+')" type="text" value="'+(val||'')+'"  />';
		       	   }},
		       {title: '学习结束时间', name: 'endTime', width:40,
			        	  renderer:function(val,item, rowIndex){
	
						       return '<input class="time" id="endTime_'+rowIndex+'" name="endTime_'+rowIndex+'" style="width:145px" onchange="endTimeChange('+rowIndex+')" type="text" value="'+(val||'')+'" />';;
			       	   }},
			   {title: '操作', name: 'courseId', width:40, renderer:function(val, item, rowIndex){
				   var disableStr=""
				   if(item.isDisabled == 1){
					   disableStr= "允许学习";
					   
				   }else{
					   disableStr= "禁止学习";
				   }             
				   var buttonStr = "";
				  
					   buttonStr+="<a href='javascript:void(0);' onclick='doDisable("+rowIndex+")'>"+disableStr+"</a>";
				   
					   
				   buttonStr+=  "<a href='javascript:void(0);' onclick='doDeleteCourse("+rowIndex+")'>删除</a>";
				   
				   return  buttonStr ;
			   }}
           ]
    });
	
	$('#courseTable').mmGrid().on("loadSuccess",function(e,data){

		//时间插件

		$(".time").datetimepicker({
			changeMonth: true,
	        changeYear: true,
			dateFormat:"yy-mm-dd",
			minDate: new Date(beginTime),
            maxDate:new Date(endTime)
		});
		
	})
}


//课程列表重载
function courseListReload(items){

	$('#courseTable').mmGrid().load(items);
}
//阶段名称更改
function nameChange(){

	var index = $("#index").val();
	var name = $("#name").val();
	if(index==0){
		var i =stageList?stageList.length:0;
		if(name||name!=''){
			$("#stage_title_0").text("第"+(i+1)+"阶段："+name);
		}
	}else{
		if(name||name!=''){
			$("#stage_title_"+index).text("第"+(index)+"阶段："+name);
		}
	}
	
}
//时间更改
function beginTimeChange(i){
	if(i==null){
		return;
	}
		newStage.courseList[i].beginTime=$("#beginTime_"+i).val();
	
}
function endTimeChange(i){
	if(i==null){
		return;
	}

		newStage.courseList[i].endTime=$("#endTime_"+i).val();
	
}
//删除阶段
function doDelete(i){
	
	if(i==0){
	   return;	
	}
	var id = stageList[i-1].id;
	_doDelete(id,i);

}
function _doDelete(id,i){
	if(!id){
		return;
	}
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{'id':id},
		dataType:"json",
		url:"<%=request.getContextPath()%>/learnPlan/deleteLearnPlanStage.action",
		success:function(data){
			if(data == "SUCCESS"){
				$("#stage_div_"+i).remove();
				stageList.splice(i-1,1);
				renderStages();
				dialog.alert('删除成功！');
				
			}else{
				dialog.alert('删除失败！');
			}
	    	
	    }
	});
}
//上移
function doUp(i){
	if(i==0||i==1){
       return;	
		 }
		var id = stageList[i-1].id;
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'id':id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/learnPlan/upLearnPlanStage.action",
			success:function(data){
				if(data == "SUCCESS"){
					var stage=stageList[i-1];
					var upStage=stageList[i-2];
					stageList.splice(i-1,1,upStage);
					stageList.splice(i-2,1,stage);
					cleanForm();
					renderStages();
				}else{
					dialog.alert('上移失败！');
				}
		    	
		    }
		});
}

//下移
function doDown(i){
	
	if(i==0||i==stageList.length){
       return;	
		 }
		var id = stageList[i-1].id;
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'id':id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/learnPlan/downLearnPlanStage.action",
			success:function(data){
				if(data == "SUCCESS"){
					var stage=stageList[i-1];
					var downStage=stageList[i];
					stageList.splice(i-1,1,downStage);
					stageList.splice(i,1,stage);
					cleanForm();
					renderStages();
				}else{
					dialog.alert('下移失败！');
				}
		    	
		    }
		});
}
var artDialog;
//选择课程
function selectCourses(){
	artDialog = dialog({
        title: "选择课程" ,
        url:"<%=request.getContextPath()%>/learnPlan/listCourse.action",
        lock:true,
        height: 600,
		width: 1070
	}).showModal();
	
	}
function addCourse(courses){
	if(!courses||courses.length==0){
		return;
	}
	var lecturers= $("#lecturers").val();
	var lecturerArr=[];
	 if(lecturers!=null&&lecturers.length>0){
		 lecturerArr= lecturers.split(",");
     }
	for(var i=0;i<courses.length;i++){
		var result=false;
		if(newStage.courseList&&newStage.courseList.length>0){
		     for(var j=0;j<newStage.courseList.length;j++){
		        	if(newStage.courseList[j].courseId==courses[i].id){
		        		result=true;
		        		break;
		        	}
		        }
		     if(result){
		    	 continue;
		     }
		     newStage.courseList.push(toNewCourse(courses[i]));
		}else{
			newStage.courseList.push(toNewCourse(courses[i]));
		}
		 if(checkTeacher(courses[i].teacherName,lecturerArr)){
	    		lecturerArr.push(courses[i].teacherName);
		    }
		if(i==(courses.length-1)){
			$("#lecturers").val(lecturerArr.join(','));
		}
	}
	
		courseListReload(newStage.courseList)
		$("#courses").val(newStage.courseList.length);
		
	
	
}

function toNewCourse(course){
	if(!course){
		return;
	}
	var newCourse={};
	newCourse.courseName=course.name;
	newCourse.courseId=course.id;
	newCourse.courseCode=course.code;
	newCourse.beginTime=learnPlan.beginTime;
	newCourse.endTime=learnPlan.endTime;
	newCourse.isDisabled=0;
	newCourse.stageId=$("#id").val();
	return newCourse;
}
//选择老师
function selectTeachers(){
	artDialog = dialog({
        title: "选择讲师" ,
        url:"<%=request.getContextPath()%>/learnPlan/listTeacher.action",
        lock:true,
        height: 600,
		width: 800
	}).showModal();
	

}
function addTeacher(names,ids){
	if(names==null||names.length==0){
		return;
	}
	var lecturers= $("#lecturers").val();
	var lecturerArr=[];
	 if(lecturers!=null&&lecturers.length>0){
		 lecturerArr= lecturers.split(",");
     }
	 for(var i in names){
		 if(checkTeacher(names[i],lecturerArr)){
			 lecturerArr.push(names[i]);
		 }
		 if(i==(names.length-1)){
			 $("#lecturers").val(lecturerArr.join(','));
		 }
	 }
	
	
}


function checkTeacher(name,lecturerArr){
	if(!name){
		return false;
	}
	if(!lecturerArr||lecturerArr.length==0){
		return true;
	}
	
	for(var j in lecturerArr){
		if(lecturerArr[j]==name){
			return false;
		 }
		if(j==(lecturerArr.length-1)){
			return true;
		}
	}
}

	
//禁止或允许学习课程
function doDisable(i){
	if(i==null){
		return;
	}
	var item = $('#courseTable').mmGrid().row(i);
	var index =$('#index').val();
	if(!item){
		return ;
	}
	if(item&&!item.id){
		var isDisabled = newStage.courseList[i].isDisabled;
		if(isDisabled==0){
			newStage.courseList[i].isDisabled=1;
		}else{
			newStage.courseList[i].isDisabled=0;
		}
		courseListReload(newStage.courseList);
	}else{
		var isDisabled = stageList[(index-1)].courseList[i].isDisabled;
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'id':stageList[(index-1)].courseList[i].id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/learnPlan/disableLearnPlanStageCourse.action",
			success:function(data){
				if(data == "SUCCESS"){
					if(isDisabled==0){
						stageList[(index-1)].courseList[i].isDisabled=1;
						newStage.courseList[i].isDisabled=1;
					}else{
						stageList[(index-1)].courseList[i].isDisabled=0;
						newStage.courseList[i].isDisabled=0;
					}
					courseListReload(newStage.courseList);
				}else{
		
				}
		    	
		    }
		})
	}
	
}

//删除课程
function doDeleteCourse(i){
	if(i==null){
		return;
	}
	var index = $("#index").val();
	if(index==0){
		newStage.courseList.splice(i,1);
		courseListReload(newStage.courseList);
	}else if(!newStage.courseList[i].id){
		
			newStage.courseList.splice(i,1);	
			courseListReload(newStage.courseList);
		
	}
	else{
		
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{'id':stageList[(index-1)].courseList[i].id},
			dataType:"json",
			url:"<%=request.getContextPath()%>/learnPlan/deleteLearnPlanStageCourse.action",
			success:function(data){
				if(data == "SUCCESS"){
					
					stageList[(index-1)].courseList.splice(i,1);
					newStage.courseList.splice(i,1);
					courseListReload(newStage.courseList);
				}else{
		
				}
		    	
		    }
		})
	}
	
}

function beforeSave(){
	$("form").submit();
}

//保存
function save(){
     var isValidate = false;
	newStage.id=$.trim($("#id").val());
	newStage.name = $.trim($("#name").val());
	newStage.lecturers = $.trim($("#lecturers").val());
	newStage.learnTime = $.trim($("#learnTime").val());
	newStage.learnScore = $.trim($("#learnScore").val());
	newStage.description = $.trim($("#description").val());
	newStage.planId=planId;
	if(!newStage.courseList||newStage.courseList.length==0){
		$("#span_kczy").show();
		
		return false;
	}else{
		$("#span_kczy").hide();
		
	}
	$.each(newStage.courseList,function(index,item){
		if(!item.beginTime&&!item.endTime){
			dialog.alert(item.courseName+" 开始时间和结束时间不能都为空");
			return false;
		}else if(item.beginTime&&item.endTime){
			if(item.beginTime>item.endTime){
				dialog.alert(item.courseName+" 开始时间不能晚于结束时间");
				return false;
			}
		}
		if(index==(newStage.courseList.length-1)){
			isValidate=true;
		}
	});
	if(isValidate){
		delete newStage.orderNum;
		var urlStr = "<%=request.getContextPath()%>/learnPlan/addLearnPlanStage.action";
		
		if(newStage&&newStage.id){
			//修改
			urlStr = "<%=request.getContextPath()%>/learnPlan/updateLearnPlanStage.action";
			
		}
		
				doSave(urlStr);
			
	
	}

}

function doSave(urlStr){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:JSON.stringify(newStage),
		contentType:"application/json;charset=utf-8",
		dataType:"json",
		url:urlStr,
		success:function(data){
			if(data&&data.result&&data.result == "SUCCESS"){
				if(!newStage.id&&!data.id){
					dialog.alert('保存失败！');
					return;
				}
				if(data.id){
					newStage.id=data.id;
				}
				delete newStage.courseList;
				stageList.push(newStage);
				cleanForm();
				tempStage={};
				$("#editForm").hide();
				renderStages();
				addStage();
				
			}else if(data=="SUCCESS"){
				delete newStage.courseList;
				stageList[($("#index").val()-1)]=newStage;
				cleanForm();
				$("#editForm").hide();
				renderStages();
				addStage();
			}
			else{
				dialog.alert('保存失败！');
			}
	    	
	    }
	});
}
</script>
</head>
<body style="">
<div class="content">
	<!-- <h3>安排课程</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">安排课程</span>
	 </div>
    <div class="make_sc">
    	<div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty2.png" />
            <span class="span_3">1</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect2.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect1.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty.png" />
            <span>2</span>
        </div>
        <div class="connect_3">
        	<img src="<%=request.getContextPath()%>/images/img/ico_connect1.png" />
            <img src="<%=request.getContextPath()%>/images/img/ico_connect.png" />
        </div>
        <div class="sc_tap1">
        	<img src="<%=request.getContextPath()%>/images/img/ico_ty1.png" />
            <span class="span_2">3</span>
        </div>
        <div class="bt">
        	<span class="bt_2">创建计划基本信息</span>
            <span class="bt_3">新增计划阶段与内容</span>
            <span class="last_bt">选择计划人员</span>
        </div>
        
       
        
        
    </div>
    <div id="main_div">
    </div>
	<div id="editForm" class="lesson_add_2" style="display:none">
	<form>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>阶段名称：</em>
            </div>
            <div class="add_fr">
            	<input id="name" name="name" type="text" /><span class="validation_span"></span>
            	<input id="index" name="index" type="hidden"/>
            	<input id="id" name="id" type="hidden"/>
            </div>
        </div>
    	<div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>阶段内容：</em>
            </div>
            <div class="add_fr">
            	<input type="button" name="selectCourse" onclick="selectCourses()"  value="选择课程资源"/>
            	<input type="hidden" id ="courses" name="courses"/>
            	<span id="span_kczy" class="msg-box n-right" for="times" style="display: none;">
						<span class="msg-wrap n-error" role="alert">
							<span class="n-arrow"><b>◆</b><i>◆</i></span>
							<span class="n-icon"></span>
							<span class="n-msg">课程资源不能为空</span>
						</span>
				</span>
            </div>
        </div>
        <div class="add_gr" style="height:auto">
        <div id ="courseTable" class="tab_1">
        </div>
        </div>
        <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
        <div class="add_gr">
        	<div class="add_fl">
                <em>课程讲师：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="lecturers" name="lecturers" style="width:135px;" />
				<input type="button" name="selectTeacher" onclick="selectTeachers()"  value="选择讲师"/>
                <span class="validation_span"></span>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>计划学时：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="learnTime" name="learnTime" style="width:135px;"/>
                <span class="validation_span"></span>
            </div>
        </div>
         <div class="add_gr">
        	<div class="add_fl">
            	<span>*</span>
                <em>可获学分：</em>
            </div>
            <div class="add_fr">
            	<input type="text" id="learnScore" name="learnScore" style="width:135px;"/>
                <span class="validation_span"></span>
            </div>
        </div>
       <div class="add_gr_1" style="margin-bottom:20px;">
        	<div class="add_fl">
                <em>阶段描述：</em>
            </div>
            <div class="add_fr">
            	<textarea id="description" name="description"></textarea>
                <em>最多1000个字符</em>
                <span class="validation_span"></span>
            </div>
        </div>
        <div class="button_cz fl" style="margin-top:20px; float: right;margin-right: 365px;">
        <input type="button" onclick="beforeSave()" value="保存" />
        </div>
       </form>
        
    </div>
    
    <div class="add_sc">
    	<div onclick="beforeSave(1);" class="add_sc1">
    		<img src="<%=request.getContextPath()%>/images/img/add_sc.png" />
        	<span>新增计划阶段</span>
        </div>
    
    </div>
      <div class="button_cz fl" style="margin-top:20px; margin-left:0;">
      		
            <input type="button" onclick="goLast()" value="上一步" class="back" />
            <input type="button" onclick="goNext()" value="下一步" class="back" />
			<input type="button" onclick="goBack()" value="取消" class="cancel"/>

    </div>
        
</div>

</body>
</html>
