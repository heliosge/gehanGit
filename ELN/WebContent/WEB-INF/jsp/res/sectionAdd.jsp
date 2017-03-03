<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
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

<script type="text/javascript">
var courseId = ${courseId};
var sectionCount = ${sectionCount};

$(function(){
	initExam();
	initCourseware();
	initValidate();
})

function initCourseware(){
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
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
				   return '<a href="javascript:void(0);" onclick="deleteCourseware('+val+')" >删除</a>';
			   }}
           ]
    });
}

function initExam(){
	$('#exampleTable-1').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
    	width: $('#exampleTable-1').parent().width(),
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
        multiSelect: true,
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
}


function chooseCourseware(){
	artDialog=dialog({
        url:"<%=request.getContextPath()%>/res/toChooseCourseware.action",
        title:"选择课件" ,
        height: 500,
		width: 1100
		}).showModal();
}
var artDialog;
function chooseExam(){
	artDialog=dialog({
        url:"<%=request.getContextPath()%>/res/toChooseExam.action",
        title:"选择试卷" ,
        height: 500,
		width: 1100
		}).showModal();
}

function coursewareSearch(){
	var rows = $('#exampleTable').mmGrid().rows();
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
}

function examSearch(){
	var rows = $('#exampleTable-1').mmGrid().rows();
	var name = $("#examName").val();
	for(var index = rows.length-1; index>=0; index--){
		var item = rows[index];
		if(name){
			if(item.title.indexOf(name) == -1){
				$('#exampleTable-1').mmGrid().removeRow(index);
			}
		}
	}
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
}

function deleteExam(id){
	var rows = $('#exampleTable-1').mmGrid().rows();
	$.each(rows, function(index, val){
		if(id == val.examId){
			$('#exampleTable-1').mmGrid().removeRow(index);
		}
	});
}

function deleteCourseware(id){
	var rows = $('#exampleTable').mmGrid().rows();
	$.each(rows, function(index, val){
		if(id == val.id){
			$('#exampleTable').mmGrid().removeRow(index);
		}
	});
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
			dialog.alert($(input).val());
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
			if(param.examIds == undefined){
				dialog.alert("试卷不能为空");
				return;
			}
			var flag = false;
			$.each(param.passPercents, function(index, val){
				if(val > 100){
					flag = true;
				};
			});
			if(flag){
				dialog.alert("通过比例不能大于100");
				return;
			}
			$.ajax({
				type:"POST",
				async:true,  //默认true,异步
				data:param,
				url:"<%=request.getContextPath()%>/res/insertResSection.action",
				success:function(data){
					if(data == 'SUCCESS'){
						dialog.alert("操作成功。");
						$("#save").attr("disabled","disabled");
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
	if($("#save").attr("disabled")!='$("#save").attr("disabled"'){
		save();
	}
	window.location.href="<%=request.getContextPath()%>/res/toInsertResSectionPage.action?courseId="+courseId+"&sectionCount="+sectionCount;
	}

function cancel(){
	window.location.href="<%=request.getContextPath()%>/res/toResCourseListPage.action";
}

function prev(){
	if(sectionCount == 1){
		window.location.href="<%=request.getContextPath()%>/res/toUpdateResCoursePage.action?id="+courseId;
	}else{
		window.location.href="<%=request.getContextPath()%>/res/toUpdateResSectionPage.action?date="+new Date()
		+"&courseId="+courseId+"&sectionCount="+(sectionCount-2);
	}
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
					length : "长度需小于等于30个字符"
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
    <h4 class="first_ca">第${sectionCount }章<a href="#"></a></h4>
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
			<div id="exampleTable" style="margin-top:10px;width:100%" ></div>
        </div>
        <div class="test">
            <p>
            <span>*</span>
            <em>章节课件：</em>
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
   
    <h5 class="continue"><a href="#" id="next" onclick="next();">继续添加章节</a></h5>
    <div class="button_gr" style=" padding-left:250px;">
                <input type="button" value="保存" class="ly_bt1" onclick="save()" id="save"/>
                <input type="button" value="上一步" class="ly_bt1" onclick="prev()"/>
                <input type="button" value="返回" class="btn_4" onclick="cancel();"/>
            </div>
 </form>
         
</div>
<script>
</script>
</body>
</html>
