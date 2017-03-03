<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错题集</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/json2.js"></script>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var optionName = ["","A","B","C","D","E","F"];
var isOrNo = ["错误","正确"];
$(function(){
	$("#userId").val(userId);
	$('#examTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/myExamManage/getWrongCard.action',
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
	    	param.content = escapeForSql($.trim($("#questionContent").val()));
	    	param.type = $("#type").val();
	    	param.wrongType = $("#wrongType").val();
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'wrongId',hidden:true},
	           {title:'题干', name:'content', width:250,align:'center', 
	        	   renderer:function(val,item, rowIndex){
				       return "<a class='question_img' href='javascript:void(0);' onclick='viewWrongQuestion("+rowIndex+")'>"+val+"</a>";
	        	   }
               },
	           {title:'题型', name:'type', width:50,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isMultimedia == 1){
            			   return "多媒体题";
            		   }else{
            			   if(item.type == 1){
                			   return "主观题"; 
                		   }else if(item.type == 2){
                			   return "单选题";
                		   }else if(item.type == 3){
                			   return "多选题";
                		   }else if(item.type == 4){
                			   return "判断题";
                		   }else if(item.type == 5){
                			   return "填空题";
                		   }else if(item.type == 6){
                			   return "组合题";
                		   } 
            		   }
              		}
               },
               {title:'我的答案',name:'myAnswer', width:150,align:'center',
            	   renderer:function(val,item, rowIndex){
	               		return setAnswer(item.type,item.myAnswer);
	               }
              	},
               {title:'正确答案',name:'correctAnswer', width:150,align:'center',
            	   renderer:function(val,item, rowIndex){
	               		return setAnswer(item.type,item.correctAnswer);
	               }
               },
               {title:'所属考试',name:'title', width:150,align:'center'},
               {title:'操作',name:'operation', width:155,align:'center',
                	renderer:function(val,item, rowIndex){
                		var str_enter = '<a href="javascript:void(0);" onclick="deleteWrong('+ item.wrongId +','+item.answerId+')" >移出错题集</a>&nbsp;';
                		return str_enter;
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

//设置正确答案
function setAnswer(type,answer){
	if(type == 2){//单选
		return optionName[answer];
	}else if(type == 3){//多选
		var ua = '';
		if(answer != '-'){
			cArray = answer.split(",");
			for(var i=0;i<cArray.length;i++){
				var num = cArray[i];
				ua += optionName[num];
				if(i!=cArray.length - 1){
					ua += ',';
				}
			}
		}
		return ua;
	}else if(type == 1 || type == 5){//主观题、填空题
		return answer.replace(/\#\#\*\*\#\#/g,",");
	}else if(type == 4){//判断题
		return isOrNo[answer];
	}else{
		return "略";
	}
}

/*移出错题集  */
function deleteWrong(wrongId,answerId){
	dialog.confirm('确认移出吗？', function(){
		$.ajax({
			type:"POST",
			async:true,  //默认true,异步
			data:{"id":wrongId,"answer_id":answerId},
			url: "<%=request.getContextPath()%>/myExamManage/deleteWrongCard.action",
			success:function(data){
				if(data == "SUCCESS"){
					dialog.alert("操作成功！", function (){
						search(true);
					});
				}else{
					dialog.alert("操作失败！");
				}
		    }
		});
	});
}

/*查看错题详情  */
function viewWrongQuestion(rowIndex){
	var rowData = $('#examTable').mmGrid("row", rowIndex);
	//alert("考试ID：" + rowData.id + ", 人员ID："+rowData.userId + ", 试卷ID：" + rowData.paperId + ", 问题ID："+rowData.questionId);
	window.location = '<%=request.getContextPath()%>/myExamManage/gotoWrongCardDetail.action?wrong_id='+rowData.wrongId;
	<%-- window.open('<%=request.getContextPath()%>/myExamManage/gotoWrongCardDetail.action?wrong_id='+rowData.wrongId,
			'_blank', 'height=100, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no'); --%>
}

//查询
function query(){
	$('#examTable').mmGrid().load({"page":1});
	/*,"content":escapeForSql($.trim($("#questionContent").val())),"type":$("#type").val(),"wrongType":$("#wrongType").val()  */
}

//查询
function search(notOnePage){
	if(notOnePage){
		//不从第一页开始
		$('#examTable').mmGrid().load();
	}else{
		$('#examTable').mmGrid().load({"page":1});		
	}
}

//重置
function clearAll(){
	$("#questionContent").val("");
    $('#type').val("0");
    query();
}

/*头部tab点击事件  */
<%-- $(function(){
	$('#ul_exam').find('li').click(function(){
		$('#ul_exam').find('li').attr('class','');
		$(this).attr('class','li_a');
		//$("#wrongType").val($(this).attr("id"));
		if($(this).attr("id") == 2){
			window.location = '<%=request.getContextPath()%>/myExamManage/gotoSimulateWrongCard.action';
		}else if($(this).attr("id") == 3){
			window.location = '<%=request.getContextPath()%>/myExercise/gotoExerciseWrongList.action';
		}
	})
}); --%>
</script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<body>
<input type="hidden" id="userId" name="userId"/>
<input type="hidden" id="wrongType" name="wrongType" value="1"/>
<div id="course_all">
	<div class="notes_list" style="padding-bottom: 0px;">
    	<!-- <h3>错题集</h3> -->
       <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">错题集</span>
		</div>
        <div class="search_1">
        	
                    <span>题干：<input type="text" id="questionContent" name="questionContent" class="tg" /></span>
                     <span>题型
                     <select id="type" name="type">
                     	<option value="0" selected="selected">显示全部</option>
                        <option value="2">单选题</option>
                        <option value="3">多选题</option>
                        <option value="1">主观题</option>
                        <option value="7">多媒体题</option>
                        <option value="4">判断题</option>
                        <option value="5">填空题</option>
                        <option value="6">组合题</option>
                     </select>
                     </span>
                     <div class="cx">
                    	<input type="button" class="btn_5" onclick="query();" value="查询" />
                    	<input type="button" class="btn_6" onclick="clearAll();" value="重置" />
                     </div>
                  
            
        </div>
       <!--  <div class="tab_1">
        	<table cellspacing="0" width="100%">
            	<tr>
                	<th width="254px;"><input type="checkbox" />题干</th>
                    <th width="96px;">题型</th>
                    <th width="144px;">我的答案</th>
                    <th width="144px;">正确答案</th>
                    <th width="204px;">所属考试</th>
                    <th>操作</th>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                <tr>
                	<td><input type="checkbox" />个体压力过大通常会有哪些表现？</td>
                    <td>单选</td>
                    <td>A</td>
                    <td>B</td>
                    <td>个人压力管理</td>
                    <td><a href="#" class="tk_out">移出错题集</a></td>
                </tr>
                
               
                
               
               
            
            </table>
        
        </div> -->
     </div>
     <div class="clear_both"></div>
     <div id="dataDiv">
     	<table id="examTable"></table>
	  	<div id="paginator11-1" style="text-align:right;"></div>
     </div>
        <!-- <div class="page clear">
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
    <script type="text/javascript">
	    $(function(){
			$('#ul_exam').find('li').click(function(){
				$('#ul_exam').find('li').attr('class','');
				$(this).attr('class','li_a');
				})
		})
    </script>
</body>
</html>
