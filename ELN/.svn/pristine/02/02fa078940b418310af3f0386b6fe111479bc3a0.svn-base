<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增计划</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<jsp:include page="../common/mmGridInclude.jsp"></jsp:include>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sys.css" />
<!-- ligerUI -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/LigerUI V1.2.5/skins/Aqua/css/ligerui-all.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/LigerUI V1.2.5/ligerui.min.js" ></script>
<!--dialog-->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
	.btn_3 {width: 68px;height: 25px;background: #D20001;border: none;color: white;float: right;}
	.lesson_add .add_gr .add_fr span{margin-left: 0px;}
	.lesson_add .add_gr { height:50px;}
	.lesson_add_2 .add_gr .add_fr {width: 850px;}
</style>

<script type="text/javascript">

	var firstFlag = true;

	function cancel(){
		history.back(-1);
	}
	
	$(function(){
		initGrid();
	})
	
	function initGrid(){
		//表格
		var grid = $('#exampleTable').mmGrid({
	    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
			url:"<%=request.getContextPath()%>/train/selectTrainArrangeUserListWithScore.action",
	    	width: $('#exampleTable').parent().width(),
	    	height: 'auto',
	    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
	    	showBackboard: false,
	        //multiSelect: true,
	        //checkCol: true,
	        indexCol: true,  //索引列
	        params: function(){
	        	return  toParam();
		    },
	     	cols: [{title: 'ID', name: 'user', width:30, hidden:true, renderer:function(val, item, rowIndex){
                return val.id;
            }},
	 			   {title: '用户名', name: 'user', width:50, align:'center', renderer:function(val, item, rowIndex){
                       return val.userName;
                   }},
	               {title: '姓名', name: 'user', width:50, align:'center', renderer:function(val, item, rowIndex){
                       return val.name;
                   }},
	               {title: '部门', name: 'user', width:50, align:'center', renderer:function(val, item, rowIndex){
                       return val.deptName;
                   }},
	 			   {title: '岗位', name: 'user', width:50, align:'center', renderer:function(val, item, rowIndex){
                    return val.postName;
                }},
                {title: '身份证', name: 'user', width:70, align:'center', renderer:function(val, item, rowIndex){
                    return val.idCard;
                }},
                   {title: '是否通过', name: 'isPass', width:30, align:'center', renderer:function(val, item, rowIndex){
                       if(val==1){
                          return "已通过";
                       }else{
                           return "未通过";
                       }
                }},
                {title: '成绩', name: 'score', width:30, align:'center', renderer:function(val, item, rowIndex){
                    if(val){
                        return val;
                    }else{
                        return 0;
                    }
                }},{title: '操作', name: 'id', width:90, align:'center', renderer:function(val, item, rowIndex){

                    var buttonStr ='';
                    var status =0;
                     status = ${arrange.status};
                    if(status==3){
                        buttonStr += '<a href="javascript:void(0);" onclick="exportWord('+item.user.id+')"  >导出档案</a>  ';
                     } else{
                        buttonStr += '<a href="javascript:void(0);"  style="color:#999;" >导出档案</a>  ';

                    }
                    return   buttonStr;
                }}
	           ]
	    });
		
		grid.on("loadSuccess",function(e, data){
			if(firstFlag){
				firstFlag = false;
				stuGridRows = $('#exampleTable').mmGrid().rows();
				insertPage(stuGridRows);
				}
			});
	}
	
	function toParam(){
		var o = {};
		o.arrangeId = ${arrange.id};
		o.name = $("#name").val();
		o.deptName = $("#deptName").val();
		o.postName = $("#postName").val();
		o.passStatus = 2;
		return o;
	}
	
	var stuGridRows = [];

	function search(){
		var rows = stuGridRows.slice(0);
		var userName = $("#userName").val();
		var name = $("#name").val();
		var postName = $("#postName").val();
		var deptName = $("#deptName").val();
		for(var index = rows.length-1; index>=0; index--){
			var item = rows[index];
			if(userName){
				if(item.userName == null || item.userName.indexOf(userName) == -1){
					rows.splice(index,1);
					continue;
				}
			}
			if(name){
				if(item.name == null || item.name.indexOf(name) == -1){
					//$('#exampleTable').mmGrid().removeRow(index);
					rows.splice(index,1);
					continue;
				}
			}
			if(postName){
				if(item.postName == null || item.postName.indexOf(postName) == -1){
					//$('#exampleTable').mmGrid().removeRow(index);
					rows.splice(index,1);
					continue;
				}
			}
			if(deptName){
				if(item.deptName == null || item.deptName.indexOf(deptName) == -1){
					//$('#exampleTable').mmGrid().removeRow(index);
					rows.splice(index,1);
					continue;
				}
			}
		}
		insertPage(rows);
	}
	
	function clearAll(){
		$("#userName").val('');
		$("#name").val('');
		$("#postName").val('');
		$("#deptName").val('');
		search();
	}
	
	function viewStage(obj,status){
		if(status == 1){
			$(obj).attr("onclick","viewStage(this,2)");
			$(obj).parent().next(".lesson_add_2").hide();
		}else{
			$(obj).attr("onclick","viewStage(this,1)");
			$(obj).parent().next(".lesson_add_2").show();
		}
	}
	
	var pageSize = 10;
	//插入分页插件
	function insertPage(data){
		$("#jquery_page").show();
		//插入分页插件
		$("#jquery_page").pagination(data.length, {
			 prev_text: '上一页', 
			 next_text: '下一页', 
			 items_per_page: pageSize, //每页显示的个数
			 num_display_entries: 10,  //中间显示的页数
			 current_page: 0,         //初始页
			 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
			 callback: function(page){
				 $('#exampleTable').mmGrid().load(data.slice(page*pageSize,(page+1)*pageSize));
			 }  
		});
	}

    <%--function exportWord(id){--%>
        <%--var   arrangeId;--%>
        <%--arrangeId=${arrange.id};--%>
        <%--if(!arrangeId||!id){--%>
            <%--dialog.alert("导出出错");--%>
          <%--return false;--%>
        <%--}--%>
        <%--$("input[name='arrangeId']").val(arrangeId);--%>
        <%--$("input[name='userId']").val(id);--%>
        <%--$("#form").submit();--%>
    <%--}--%>

    function exportWord(id){
        var   arrangeId;
        arrangeId=${arrange.id};
        if(!arrangeId||!id){
            dialog.alert("导出出错");
            return false;
        }
        $.ajax({
            type:"POST",
            async:false,  //默认true,异步
            data:{},
            url:"<%=request.getContextPath()%>/train/exportTrainRecord.action?arrangeId="+arrangeId+"&userId="+id,
            success:function(data){
                window.open(data);
            }
        });
    }

</script>
</head>
<body>

<div id="content" class="content">
	<!-- <h3>培训安排详情</h3> -->
	<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
		<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
		<span id="title_span" style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">培训安排详情</span>
	</div>
            <div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px"> 基本信息</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    <div class="lesson_add_2" style="width:1044px;">
            	<div class="add_gr" >
		        	<div class="add_fl">
		          	 	<em>计划开课：</em>
		            </div>
		            <div class="add_fr">
		            	<c:choose>
		                	<c:when test="${arrange.timeType==1 }">
		                		年度
		                	</c:when>
		                	<c:otherwise>
		                		月度
		                	</c:otherwise>
		                </c:choose>
		                &nbsp;&nbsp;${arrange.trainPlanName }
		            </div>
		        </div>
				        
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训名称：</em>
		            </div>
		            <div class="add_fr">
		            	${arrange.name }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>培训时间：</em>
		            </div>
		            <div class="add_fr">
		            	${arrange.beginTime } 至 ${arrange.endTime }
		            </div>
		        </div>
		        <div class="add_gr">
		        	<div class="add_fl">
		                <em>是否允许报名：</em>
		            </div>
		            <div class="add_fr">
		                <c:choose>
		                	<c:when test="${arrange.isJoin==1 }">
		                		是
		                	</c:when>
		                	<c:otherwise>
		                		否
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		         <div class="add_gr">
		        	<div class="add_fl">
		                <em>允许人数：</em>
		            </div>
		            <div class="add_fr">
		            	${arrange.maxJoinNum }
		            </div>
		        </div>
		         <div class="add_gr" style="height:auto;">
		        	<div class="add_fl">
		                <em>开放人群：</em>
		            </div>
		            <div class="add_fr">
		            <c:choose>
		                	<c:when test="${arrange.openCrowd==null || fn:length(arrange.openCrowd) == 0}">
		                		所有人
		                	</c:when>
		                	<c:otherwise>
		                		<c:forEach var="item" items="${arrange.openCrowd }" varStatus="status">
				            		${item.deptName };&nbsp;
				            	</c:forEach>
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		        <div class="add_gr" style="display:none;">
		        	<div class="add_fl">
		                <em>是否为推荐培训：</em>
		            </div>
		            <div class="add_fr">
		            	 <c:choose>
		                	<c:when test="${arrange.isRecommend==1 }">
		                		是
		                	</c:when>
		                	<c:otherwise>
		                		否
		                	</c:otherwise>
		                </c:choose>
		            </div>
		        </div>
		        <div class="add_gr" style="height:auto;">
		        	<div class="add_fl">
		                <em>适合人群：</em>
		            </div>
		            <div class="add_fr" style="word-wrap: break-word;">
		            	${arrange.fitCrowd }
		            </div>
		        </div>
		        <div class="add_gr">
			            <div class="add_fl">
			                <em>培训通过考试：</em>
			            </div>
			             <div class="add_fr">
			            	${arrange.afterClassExamName }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训通过成绩：</em>
			            </div>
			             <div class="add_fr">
			              <c:choose>
		                	<c:when test="${arrange.passPercent!=0 }">
		                		${arrange.passPercent }
		                	</c:when>
		                	</c:choose>
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训通过考试次数：</em>
			            </div>
			             <div class="add_fr">
			             	<c:choose>
		                	<c:when test="${arrange.aceAllowTimes!=0 }">
		                		${arrange.aceAllowTimes }
		                	</c:when>
		                	</c:choose>
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训通过考试时长：</em>
			            </div>
			             <div class="add_fr">
			             	<c:choose>
		                	<c:when test="${arrange.aceDuration!=0 }">
		                		${arrange.aceDuration }
		                	</c:when>
		                	</c:choose>
			            </div>
		            </div>
		         <div class="add_gr" style="height:auto;">
		        	<div class="add_fl">
		                <em>培训简介：</em>
		            </div>
		            <div class="add_fr" style="word-wrap: break-word;">
		            	${arrange.descr }
		            </div>
		        </div>
		         <div class="add_gr" style="height:auto;">
		        	<div class="add_fl">
		                <em>封面：</em>
		            </div>
		            <div class="add_fr">
		            	<img id="previewPath" src="${arrange.cover }" width="330px" height="220px"/>
		            </div>
		        </div>
		       </div>
		       
             <div class="tool_1" id="stage_div_1" style="width: 1044px;">
	            <span id="stage_title_1" style="padding-left:5px;width:200px">培训内容</span>
	            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
	            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
	            </a>
            </div>
		    	<div class="lesson_add_2" style="width:1044px;">
	        	<c:forEach var="item" items="${contents }" varStatus="status">
		        	 <div class="add_gr">
			        	<div class="add_fl">
			                <em>培训内容：</em>
			            </div>
			             <div class="add_fr">
			            	${item.content }
			            </div>
			    	</div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训讲师：</em>
			            </div>
			             <div class="add_fr">
			            	${item.teacherName }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>开课时间：</em>
			            </div>
			             <div class="add_fr">
			            	${item.beginTime } 至 ${item.endTime } 
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>签到时间：</em>
			            </div>
			             <div class="add_fr">
			            	${item.signBeginTime } 至 ${item.signEndTime } 
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训学时：</em>
			            </div>
			             <div class="add_fr">
			            	${item.period }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训学分：</em>
			            </div>
			             <div class="add_fr">
			            	${item.score }
			            </div>
		            </div>
		            <div class="add_gr">
			            <div class="add_fl">
			                <em>培训形式：</em>
			            </div>
			             <div class="add_fr">
			            	<c:choose>
			                	<c:when test="${item.trainType==1 }">
			                		在线培训
			                	</c:when>
			                	<c:otherwise>
			                		面授培训
			                	</c:otherwise>
		                	</c:choose>
			            </div>
		            </div>
		             <div class="add_gr">
			            <div class="add_fl">
			                <em>课程：</em>
			            </div>
			             <div class="add_fr">
			            	${item.courseName }
			            </div>
		            </div>
		            <c:choose>
	                	<c:when test="${item.trainType==1 }">
	                		
	                	</c:when>
	                	<c:otherwise>
	                		 <div class="add_gr">
					            <div class="add_fl">
					                <em>培训地点：</em>
					            </div>
					             <div class="add_fr">
					            	${item.place }
					            </div>
				            </div>
				            <div class="add_gr">
					            <div class="add_fl">
					                <em>培训通过成绩：</em>
					            </div>
					             <div class="add_fr">
					            	${item.passPercent }
					            </div>
				            </div>
				            <div class="add_gr">
					            <div class="add_fl">
					                <em>课后评估：</em>
					            </div>
					             <div class="add_fr">
					            	${item.afterClassTestName }
					            </div>
				            </div>
				             <div class="add_gr">
					            <div class="add_fl">
					                <em>课前预习：</em>
					            </div>
					             <div class="add_fr">
					            	文件：${item.beforeClassFileName }
					            </div>
				            </div>
				            <div class="add_gr">
					            <div class="add_fl">
					                &nbsp;
					            </div>
					             <div class="add_fr">
					            	课程：${item.beforeClassCourseName }
					            </div>
				            </div>
	                	</c:otherwise>
                	</c:choose>
	        	</c:forEach>
	        	</div>
	        	
	        	 <div class="tool_1" id="stage_div_1" style="width: 1044px;">
		            <span id="stage_title_1" style="padding-left:5px;width:200px">培训人员</span>
		            <a href="javascript:void(0)" style="background: rgb(0, 133, 254) none repeat scroll 0 0;height: 46px;line-height: 46px;margin-top: 0;text-align: center;width: 42px;" onclick="viewStage(this,1)" class="a_ss">
		            	<img style="margin-top: 18px;" src="<%=request.getContextPath()%>/images/img/ico_down.png">
		            </a>
            	</div>
            	<div class="lesson_add_2" style="width:1044px;padding-top: 0px;padding-bottom:0px;">
		        	<div class="search_3" style="width:1044px;">
				        	<p>	
				        		用户名：<input type="text" id="userName"/>
				            	姓名：<input type="text" id="name"/>
				                                                岗位：<input type="text" id="postName"/>
				            	部门：<input type="text" id="deptName"/>
				        	</p>
				            <input type="button" value="重置" onclick="clearAll();"/>
				        	<input type="button" value="查询" class="btn_cx" onclick="search();"/>
				
				    </div>
				   	<div id="exampleTable" style="margin-top:10px;width:100%;" ></div>
				   	<!--分页  -->
					<div id="jquery_page" style="margin-top: 10px; display: none; float: right;" class="pagination"></div>
			   	</div>
        		</div>
	             <div class="button_cz">
		        	 <input type="button" class="btn_2" value="返回" onclick="cancel()"/>
	        	</div>
<form id="form" action="<%=request.getContextPath()%>/train/exportTrainUser.action" method="post">
    <input type="hidden" value="" name="arrangeId"/>
    <input type="hidden" value="" name="userId"/>
</form>
</body>
</html>
