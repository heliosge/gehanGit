<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>成绩总览</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>
<script type="text/javascript">
var userId = '${USER_ID_LONG}';//登录用户ID
var paramMap = ${paramMap};
var id = paramMap[0].id;//考试信息ID
var assign_id = paramMap[0].exam_assign_id;//考试记录ID
var is_score_public = paramMap[0].is_score_public;//考试是否公开0-否、1-是

var temp = 0;
$(function(){
	$.ajax({
		type:"POST",
		async:true,  //默认true,异步
		data:{"id":assign_id},
		url: "<%=request.getContextPath()%>/myExamManage/getGradeOtherInfo.action",
		success:function(data){
			if(data != null){
				$("#title").text(data.title);
				$("#totalScore").text("总分："+data.totalScore);
				$("#passScore").text("及格分："+data.passScore);
				$("#duration").text("考试时长："+data.duration);
			}
	    }
	});
	
	$('#examTable').mmGrid({
		root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:'<%=request.getContextPath()%>/myExamManage/getAllGrade.action',
		width: '1046px',
		height: 'auto',
		fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
		showBackboard: false,
	    checkCol: false,
	    multiSelect: true,
	    indexCol: true,  //索引列
	    params:function(){
	    	var param = new Object();
	    	if(is_score_public == 0){//不公开，只能看到自己的成绩
	    		param.user_id = userId;
	    	}
	    	param.relation_id = id;
	    	param.is_passed = $("#is_passed").val();
	    	param.name = $.trim($("#name").val());
	    	param.post = $.trim($("#post").val());
	    	param.department = $.trim($("#department").val());
	    	return param;
	    },
	    cols: [{title: 'ID', name: 'id', hidden:true},
	          {title: '姓名', name: 'userName', width:50,align:'center'}, 
	          {title: '岗位', name: 'post', width:50,align:'center'}, 
	          {title: '部门', name: 'departmentName', width:50,align:'center'}, 
	          {title: '是否参与考试', name: 'testTimes', width:150,align:'center', 
	        	  renderer:function(val,item, rowIndex){
			      	if(item.testTimes > 0){
			      		return "是";
			      	}else{
			      		return "否";
			      	}
       	   		}
	          }, 
	           {title:'成绩', name:'score', width:250,align:'center'},
	           {title:'是否通过', name:'isPassed', width:50,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.isPassed == 1){
   			      			return "是";
	   			      	}else{
	   			      		return "否";
	   			      	}
              		}
               },
               {title:'排名',name:'rownum', width:150,align:'center',
            	   renderer:function(val,item, rowIndex){
            		   if(item.rownum == 0){
   			      			return temp+1;
	   			      	}else{
	   			      		temp = val;
	   			      		return val;
	   			      	}
              		}   
               }
              	
		] ,
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
});

//查询
function query(){
	$('#examTable').mmGrid().load({"page":1});
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

//返回我的考试列表
function backList(){
	window.location.href = "<%=request.getContextPath()%>/myExamManage/gotoMyExam.action";
}

</script>
</head>
<body>
<div id="course_all">
	<div class="notes_list" style="padding-bottom: 0px;">
    	<!-- <h3>成绩总览</h3> -->
    	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">成绩总览</span>
		</div>
        <div class="py_top">
          	<h4 id="title"></h4>
            <!-- <em>考试详情</em> -->
         </div>
         <div class="ts">
                <span id="totalScore"></span>
                <span id="passScore"></span>
                <span id="duration"></span>
         </div>  

        <div class="search_1">
                     <span>姓名：<input type="text" id="name" name="name"/></span>
                     <span>岗位：<input type="text" id="post" name="post"/></span>
                     <span>部门：<input type="text" id="department" name="department"/></span>
                     <span>是否参与考试：
                     	<select id="is_passed" name="is_passed">
                        	<option selected="selected" value="-1">显示全部</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                     </span>
                    <input type="button" class="btn_5 fr" value="查询" onclick="query();" style="margin-right:10px;" />
        </div>
        <!-- <div class="tab_1">
        	<table cellspacing="0" width="100%">
            	<tr>
                	<th>姓名</th>
                    <th>岗位</th>
                    <th>部门</th>
                    <th>是否参与考试</th>
                    <th>成绩</th>
                    <th>是否通过</th>
                    <th>排名</th>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
               <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
                </tr>
                <tr>
                	<td>张三</td>
                    <td>数媒中心总监</td>
                    <td>数媒中心</td>
                    <td>是</td>
                    <td>10</td>
                    <td>否</td>
                    <td>3</td>
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
    
</body>
</html>
