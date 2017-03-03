<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<!-- mmGrid page -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style>
	.first_ca{margin-top:4px;}
	.continue{margin-top:4px;}
	.first_ca a{color:#333;}
</style>

<script type="text/javascript">
var courseId = ${courseId};
var sectionCount = ${sectionCount};
var sections;
var section;
var updateFlag = true;
var addSection = {};

$(function(){
	initSectionInfo();
	initExam();
	initCourseware();
	initValidate();
})

function initSectionInfo(){
	$(".n-yellow .msg-wrap").hide();
	$.ajax({
		type:"POST",
		async:false,  //默认true,异步
		data:{courseId:courseId},
		url:"<%=request.getContextPath()%>/res/selectSectionByCourseId.action",
		success:function(data){
			var beforeHtml = '';
			var afterHtml = '';
			for(var i = 1; i <= data.length; i++){
				if(i <= sectionCount){
					beforeHtml += '<h4 class="first_ca"><a href="javascript:void(0);" onclick="showSection('+i+')">第'+i+'章</a><a href="javascript:void(0);" ><img src="<%=request.getContextPath()%>/images/img/ico_delete.png" onclick="deleteSection('+i+')"/></a></h4>';
				}else{
					afterHtml += '<h4 class="first_ca"><a href="javascript:void(0);" onclick="showSection('+i+')">第'+i+'章</a><a href="javascript:void(0);" ><img src="<%=request.getContextPath()%>/images/img/ico_delete.png" onclick="deleteSection('+i+')"/></a></h4>';
				}
			}
			if(sectionCount > data.length){
				beforeHtml += '<h4 class="first_ca"><a href="javascript:void(0);" onclick="showSection('+sectionCount+')">第'+i+'章</a><a href="javascript:void(0);" style="margin-right:35px;">&nbsp;</a></h4>';
			}
			$("#addForm").before(beforeHtml);
			$("#addForm").after(afterHtml);
			sections = data;
			if(data.length >= sectionCount){
				section = data[sectionCount-1];
				updateFlag = true;
				fillSectionInfo();
			}else{
				section = {};
				updateFlag = false;
				if(sectionCount != 1){
					fillAddSectionInfo();
				}
			}
	    }
	});
}

function clearSection(){
	$("h4").remove();
	window.scrollTo(0,0);
}

function showSection(i){
	if(!updateFlag){
		//存储临时数据
		addSection.name = $("#name").val();
		addSection.descr = $("#descr").val();
		addSection.coursewares = $('#exampleTable').mmGrid().rows()[0] == undefined?[]:$('#exampleTable').mmGrid().rows();
		addSection.exams = $('#exampleTable-1').mmGrid().rows()[0] == undefined?[]:$('#exampleTable-1').mmGrid().rows();
		if(addSection.exams != []){
			addSection.examDurations = [];
			$.each($("input[name='examDuration']"), function(index, input){
				addSection.examDurations.push(($(input).val()==''?0:$(input).val()));
			});
			addSection.examTimesS = [];
			$.each($("input[name='examTimes']"), function(index, input){
				addSection.examTimesS.push(($(input).val()==''?0:$(input).val()));
			});
			addSection.passPercents = [];
			$.each($("input[name='passPercent']"), function(index, input){
				addSection.passPercents.push(($(input).val()==''?0:$(input).val()));
			});
		}
	}
	clearSection();
	sectionCount = i;
	initSectionInfo();
	if(updateFlag){
		$('#exampleTable').mmGrid().load();
		$('#exampleTable-1').mmGrid().load();
	}
	
}

//加载正常章节数据
function fillSectionInfo(){
	$("#name").val(section == undefined?'':section.name);
	$("#descr").val(section == undefined?'':section.descr);
}

//回填临时数据
function fillAddSectionInfo(){
	$("#name").val(addSection.name);
	$("#descr").val(addSection.descr);
	$('#exampleTable').mmGrid().load(addSection.coursewares);
	$('#exampleTable-1').mmGrid().load(addSection.exams);
	$.each($("input[name='examDuration']"), function(index, input){
		$(input).val(addSection.examDurations[index]);
	});
	$.each($("input[name='examTimes']"), function(index, input){
		$(input).val(addSection.examTimesS[index]);
	});
	$.each($("input[name='passPercent']"), function(index, input){
		$(input).val(addSection.passPercents[index]);
	});
	addSection = {};
}

function initCourseware(){
	var mmGrid = $('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        params: function(){
        	var o = {};
        	o.sectionId=section==undefined?'':section.id;
        	return o;
	    },
        url:"<%=request.getContextPath()%>/res/selectSectionAndCourseware.action",
        checkCol: true,
     	cols: [{title: 'ID', name: 'id', width:30, hidden:true},
               {title: '课件名称', name: 'name', width:60, align:'center'},
			   {title: '课件类型', name: 'type', width:60, align:'center', renderer:function(val, item, rowIndex){
				   if(val == 1){
					   return "标准课件";
				   } else if(val == 2){
					   return "普通课件";
				   } else if(val == 3){
					   return "视频课件";
				   }
				   return "";
			   }},
			   {title: '操作', name: 'id', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="javascript:void(0);" onclick="up('+val+')" >向上</a> <a href="javascript:void(0);" onclick="down('+val+')" >向下</a> <a href="javascript:void(0);" onclick="deleteCourseware('+val+')" >删除</a>';
			   }}
           ]
    });
	
	mmGrid.on("loadSuccess",function(e, data){
		coursewareGridRows = mmGrid.rows();
		if(section.id==null){
			$(".mmg-message").css("top","50px");
		}
	});
}

function hideErrorInfo(){
	if($('#exampleTable').mmGrid().rows()[0] == undefined || $('#exampleTable-1').mmGrid().rows()[0] == undefined){
		$(".mmg-message").css("top","50px");
	}
}

function initExam(){
	var mmGrid = $('#exampleTable-1').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable-1').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
        params: function(){
        	var o = {};
        	o.sectionId=section==undefined?'':section.id;
        	return o;
	    },
        url:"<%=request.getContextPath()%>/res/selectSectionAndExam.action",
        checkCol: true,
     	cols: [{title: 'ID', name: 'examId', width:30, hidden:true},
               {title: '试卷名称', name: 'title', width:60, align:'center'},
               {title: '试卷库', name: 'categoryName', width:60, align:'center'},
               {title: '题型分布', name: 'content', width:60, align:'center', renderer:function(val, item, rowIndex){
            	   var re = '';
            	   if(item.count1 != 0){re += "主观题"+item.count1;}
            	   if(item.count2 != 0){re += "单选题"+item.count2;}
            	   if(item.count3 != 0){re += "多选题"+item.count3;}
            	   if(item.count4 != 0){re += "判断题"+item.count4;}
            	   if(item.count5 != 0){re += "填空题"+item.count5;}
            	   if(item.count6 != 0){re += "组合题"+item.count6;}
            	   if(item.count7 != 0){re += "多媒体题"+item.count7;}
				   return re;
			   }},
               {title: '试卷总分', name: 'totalScore', width:60, align:'center'},
               {title: '考试时长', name: 'examDuration', width:50, align:'center', renderer:function(val, item, rowIndex){
				   return "<input value='"+(item.examDuration==null?'':item.examDuration)+"' name='examDuration' style='width:30px;'/>";
			   }},
			   {title: '允许次数', name: 'examTimes', width:50, align:'center', renderer:function(val, item, rowIndex){
				   return "<input value='"+(item.examTimes==null?'':item.examTimes)+"' name='examTimes' style='width:30px;'/>";
			   }},
			   {title: '通过分数', name: 'passPercent', width:60, align:'center', renderer:function(val, item, rowIndex){
				   return "试卷总分的<input value='"+(item.passPercent==null?'':item.passPercent)+"' name='passPercent' style='width:30px;'/>%";
			   }},
			   {title: '操作', name: 'examId', width:120, align:'center', renderer:function(val, item, rowIndex){
				   return '<a href="javascript:void(0);" onclick="deleteExam('+val+')" >删除</a>';
			   }}
           ]
    });
	
	mmGrid.on("loadSuccess",function(e, data){
		examGridRows = mmGrid.rows();
		if(section.id==null){
			$(".mmg-message").css("top","50px");
		}
	});
}


function chooseCourseware(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/res/toChooseCourseware.action",
        title:"选择课件" ,
        height: 500,
		width: 1100
		}).showModal();
}
var artDialog;
function chooseExam(){
	artDialog = dialog({
        url:"<%=request.getContextPath()%>/res/toChooseExam.action",
        title:"选择试卷" ,
        height: 500,
		width: 1100
		}).showModal();
}

var coursewareGridRows;
function coursewareSearch(){
	var rows = $('#exampleTable').mmGrid().rows();
	for(var i = rows.length-1; i >= 0; i--){
		$('#exampleTable').mmGrid().removeRow(i);
	}
	$('#exampleTable').mmGrid().addRow(coursewareGridRows);
	rows = $('#exampleTable').mmGrid().rows();
	var type = $("#coursewareType").val();
	var name = $("#coursewareName").val();
	for(var index = rows.length-1; index>=0; index--){
		var item = rows[index];
		if(!type && !name){
			
		}else if(type){
			if(item.type != type){
				$('#exampleTable').mmGrid().removeRow(index);
			}
		}else if(name){
			if(item.name.indexOf(name) == -1){
				$('#exampleTable').mmGrid().removeRow(index);
			}
		}else{
			if(item.name.indexOf(name) != -1){
				if(item.type != type){
					$('#exampleTable').mmGrid().removeRow(index);
				}
			}else{
				$('#exampleTable').mmGrid().removeRow(index);
			}
		}
	}
	hideErrorInfo();
}

var swapItems = function(arr, index1, index2) {
    arr[index1] = arr.splice(index2, 1, arr[index1])[0];
    return arr;
};

function up(val){
	var rows = $('#exampleTable').mmGrid().rows();
	for(var i = 0; i < rows.length; i++){
		if(i > 0){
			if(val == rows[i].id){
				swapItems(rows, i, i-1);
				$('#exampleTable').mmGrid().load(rows);
				break;
			}
		}
	}
}

function down(val){
	var rows = $('#exampleTable').mmGrid().rows();
	for(var i = rows.length-1; i >= 0 ; i--){
		if(i < rows.length-1){
			if(val == rows[i].id){
				swapItems(rows, i, i+1);
				$('#exampleTable').mmGrid().load(rows);
				break;
			}
		}
	}
}

var examGridRows;
function examSearch(){
	var rows = $('#exampleTable-1').mmGrid().rows();
	for(var i = rows.length-1; i >= 0; i--){
		$('#exampleTable-1').mmGrid().removeRow(i);
	}
	$('#exampleTable-1').mmGrid().addRow(examGridRows);
	rows = $('#exampleTable-1').mmGrid().rows();
	var name = $("#examName").val();
	for(var index = rows.length-1; index>=0; index--){
		var item = rows[index];
		if(name){
			if(item.title.indexOf(name) == -1){
				$('#exampleTable-1').mmGrid().removeRow(index);
			}
		}
	}
	hideErrorInfo();
}

function deleteExams(){
	var rows = $('#exampleTable-1').mmGrid().rows();
	var selectRows = $('#exampleTable-1').mmGrid().selectedRows();
	for(var i = rows.length-1; i>=0; i--){
		$.each(selectRows, function(index, val){
			if(rows[i].examId == val.examId){
				$('#exampleTable-1').mmGrid().removeRow(i);
			}
		});
	}
	examGridRows = $('#exampleTable-1').mmGrid().rows();
	hideErrorInfo();
}

function deleteCoursewares(){
	var rows = $('#exampleTable').mmGrid().rows();
	var selectRows = $('#exampleTable').mmGrid().selectedRows();
	for(var i = rows.length-1; i>=0; i--){
		$.each(selectRows, function(index, val){
			if(rows[i].id == val.id){
				$('#exampleTable').mmGrid().removeRow(i);
			}
		});
	}
	coursewareGridRows = $('#exampleTable').mmGrid().rows();
	hideErrorInfo();
}

function deleteExam(id){
	var rows = $('#exampleTable-1').mmGrid().rows();
	$.each(rows, function(index, val){
		if(id == val.examId){
			$('#exampleTable-1').mmGrid().removeRow(index);
		}
	});
	examGridRows = $('#exampleTable-1').mmGrid().rows();
	hideErrorInfo();
}

function deleteCourseware(id){
	var rows = $('#exampleTable').mmGrid().rows();
	$.each(rows, function(index, val){
		if(id == val.id){
			$('#exampleTable').mmGrid().removeRow(index);
		}
	});
	coursewareGridRows = $('#exampleTable').mmGrid().rows();
	hideErrorInfo();
}

function toParam(){
	var param = {};
	param.name = $("#name").val();
	param.descr = $("#descr").val();
	param.courseId = courseId;
	//课件参数
	var coursewareIds = [];
	var coursewareRows = $('#exampleTable').mmGrid().rows();
	if(coursewareRows[0] != undefined){
		$.each(coursewareRows, function(index, val){
			coursewareIds.push(val.id);
		});
	}
	param.coursewareIds = coursewareIds;
	//试卷参数
	var examIds = [];
	var examRows = $('#exampleTable-1').mmGrid().rows();
	if(examRows[0] != undefined){
		$.each(examRows, function(index, val){
			examIds.push(val.examId);
		});
		param.examIds = examIds;
		var examDurations = [];
		$.each($("input[name='examDuration']"), function(index, input){
			examDurations.push(($(input).val()==''?0:$(input).val()));
		});
		param.examDurations = examDurations;
		var examTimesS = [];
		$.each($("input[name='examTimes']"), function(index, input){
			examTimesS.push(($(input).val()==''?0:$(input).val()));
		});
		param.examTimesS = examTimesS;
		var passPercents = [];
		$.each($("input[name='passPercent']"), function(index, input){
			passPercents.push(($(input).val()==''?0:$(input).val()));
		});
		param.passPercents = passPercents;
	}
	return param;
}

function save(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = toParam();
			if(param.coursewareIds.length == 0){
				dialog.alert("平台课件不能为空");
				return;
			}
			if(param.examIds != undefined){
				var flag = false;
				$.each(param.examDurations, function(index, val){
					if(val ==0){
						flag = true;
					}
				});
				if(flag){
					dialog.alert("考试时长不能为0。");
					return;
				}
				flag = false;
				$.each(param.examTimesS, function(index, val){
					if(val ==0){
						flag = true;
					}
				});
				if(flag){
					dialog.alert("允许次数不能为0。");
					return;
				}
				flag = false;
				$.each(param.passPercents, function(index, val){
					if(val > 100 || val ==0){
						flag = true;
					}
				});
				if(flag){
					dialog.alert("通过比例必须在1~100之间");
					return;
				}
			}
			var url = '';
			if(updateFlag){
				param.id = section.id;
				url = "<%=request.getContextPath()%>/res/updateResSection.action";
			}else{
				url = "<%=request.getContextPath()%>/res/insertResSection.action";
			}
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:param,
				url:url,
				success:function(data){
					if(data == 'SUCCESS'){
						//dialog.alert("操作成功。",function(){initSectionInfo();});
						dialog.alert("操作成功。",function(){cancel();});
					}else{
						dialog.alert("操作失败。");
					}
					
			    }
			});
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
}

function next(){
	$('#addForm').isValid(function(v) {
		if(v){
			var param = toParam();
			if(param.coursewareIds.length == 0){
				dialog.alert("平台课件不能为空");
				return;
			}
			/* if(param.examIds == undefined){
				dialog.alert("试卷不能为空");
				return;
			} */
			if(param.examIds != undefined){
				var flag = false;
				$.each(param.examDurations, function(index, val){
					if(val ==0){
						flag = true;
					}
				});
				if(flag){
					dialog.alert("考试时长不能为0。");
					return;
				}
				flag = false;
				$.each(param.examTimesS, function(index, val){
					if(val ==0){
						flag = true;
					}
				});
				if(flag){
					dialog.alert("允许次数不能为0。");
					return;
				}
				flag = false;
				$.each(param.passPercents, function(index, val){
					if(val > 100 || val ==0){
						flag = true;
					}
				});
				if(flag){
					dialog.alert("通过比例必须在1~100之间");
					return;
				}
			}
			var url = '';
			if(updateFlag){
				param.id = section.id;
				url = "<%=request.getContextPath()%>/res/updateResSection.action";
			}else{
				url = "<%=request.getContextPath()%>/res/insertResSection.action";
			}
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:param,
				url:url,
				success:function(data){
					if(data == 'SUCCESS'){
						clearSectionInfo();
						sectionCount = sectionCount + 1;
						showSection(sectionCount);
					}else{
						dialog.alert("操作失败。");
					}
					
			    }
			});
		}else{
			//dialog.alert("表单验证不通过");
		}
	});
}

//清除页面的数据
function clearSectionInfo(){
	$("#name").val('');
	$("#descr").val('');
	$('#exampleTable').mmGrid().load([]);
	$('#exampleTable-1').mmGrid().load([]);
}
	
function deleteSection(i){
	dialog.confirm("确认删除吗?",function(obj){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{sectionId:sections[i-1].id},
			url:"<%=request.getContextPath()%>/res/deleteResSection.action",
			success:function(data){
				if(data == 'SUCCESS'){
					dialog.alert("删除章节成功。",function(){
						if(sectionCount >= i && sectionCount > 1){
							showSection(sectionCount-1);
						}else{
							showSection(sectionCount);
						}
						//initSectionInfo();
						//$('#exampleTable').mmGrid().load();
						//$('#exampleTable-1').mmGrid().load();
					});
				}else{
					dialog.alert("删除章节失败。");
				}
		    }
		});
	});
}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/res/toResCourseListPage.action";
}

function prev(){
	window.location.href="<%=request.getContextPath()%>/res/toUpdateResCoursePage.action?id="+courseId;
}

/**
 * 表单验证
 */
function initValidate() {
	$('#addForm').validator({
		theme : 'yellow_right',
		rules : {
			checknum : [ /^-?\d*\.?\d*$/, '请输入有效数字' ]
		},
		msgStyle:"margin-top:10px;",
		fields : {
			name : {
				rule : "required;length[~30]",
				msg : {
					required : "章节名称不能为空",
					length : "长度需小于等于30个字符"
				}
			},
			descr : {
				rule : "required;length[~200]",
				msg : {
					required : "章节介绍不能为空",
					length : "长度需小于等于200个字符"
				}
			}
		}
	});
}
</script>


</head>
<body>

<div class="content">
	<h3>课程章节</h3>
    <form id="addForm">
    <div class="capter">
    	<div class="ca_top">
        	<p>
            <span>*</span>
            <em>章节名称：</em>
            <input type="text" id="name" name="name"/>
            </p>
            <p>
            <span>*</span>
            <em>章节介绍：</em>
            <textarea id="descr" name="descr"></textarea>
            </p>
            <p>
            <span>*</span>
            <em>章节课件：</em>
            </p>
        </div>
        <div class="choose_c">
        	<div class="button_gr">
                <input type="button" value="选择平台课件" class="ly_bt1" onclick="chooseCourseware()"/>
                <input type="button" value="批量删除" class="btn_4" onclick="deleteCoursewares()"/>
            </div>
            <div class="search_2 fl">
                <p>课件类型：
                    <select id="coursewareType">
                        <option value="">全部</option>
                        <option value="1">标准课件</option>
                        <option value="2">普通课件</option>
                        <option value="3">视频课件</option>
                    </select>
                    课件名称：
                    <input type="text" id="coursewareName"/>
                </p>
                <input type="button" value="查询" class="btn_cx" onclick="coursewareSearch()"/>
			</div>
        </div>
        <div id="exampleTable" style="margin-top:10px;width:100%" ></div>
        <div class="test">
            <p>
            <em>章节试卷：</em>
            </p>
            
		</div>
    	<div class="button_gr">
                <input type="button" value="选择试卷" class="ly_bt1" onclick="chooseExam()"/>
                <input type="button" value="批量删除" class="btn_4" onclick="deleteExams()"/>
            </div>
            <div class="search_2 fl">
                <p>
                   	 试卷名称：
                    <input type="text" id="examName"/>
                </p>
                <input type="button" value="查询" class="btn_cx" onclick="examSearch()"/>
			</div>
			 <div id="exampleTable-1" style="margin-top:10px;width:100%" ></div>
    </div>
    </form>  
    <h5 class="continue"><a href="javascript:void(0);" onclick="next();">下一章</a>
   							<a href="javascript:void(0);" style="margin-right:35px;">&nbsp;</a></h5>
    <div class="button_gr" style=" padding-left:250px;">
                <input type="button" value="保存" class="ly_bt1" onclick="save()"/>
                <input type="button" value="上一步" class="ly_bt1" id="last" onclick="prev()"/>
                <input type="button" value="返回" class="btn_4" onclick="cancel();"/>
            </div>

       
</div>
<script>
</script>
</body>
</html>
