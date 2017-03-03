<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>试卷 自由组卷</title>
<link rel="stylesheet" href="<c:url value="/css/sys.css"/>" />

<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>

<!-- zTree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3.5.15/js/jquery.ztree.exedit-3.5.min.js" ></script>

<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/niceValidateInclude.jsp"></jsp:include>
<script src="<c:url value="/js/layer/layer.js"/>"></script>

<script type="text/javascript">

//试题库
var zTree = null;
//云试题库
var zTree2 = null;

$(function(){
	
	$('#questionForm').validator({
		theme : 'yellow_right'
	});
	
	//zTree配置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                pIdKey: "parentId",
                idKey: "id",
                rootPid: null
            },
        },
        view: {
        	showTitle: true,
            selectedMulti: false,
			addDiyDom: addDiyDom
        }
    };
	
    //试题库
    $.ajax({
        type:"POST",
        data:null,
        url:'<c:url value="/exam/questionCategory/list.action"/>',
        success:function(categoryTree){
            //addIconInfo(categoryTree);
            zTree = $.fn.zTree.init($("#categoryTree"), setting, categoryTree);
            expandZTree(zTree, 1);
        }
    });
    
    /* <c:if test="${subCompanyId != 1}">
	  	//云试题库
	    $.ajax({
	        type:"POST",
	        //async:true,  //默认true,异步
	        data:null,
	        url:'<c:url value="/exam/questionCategory/listYun.action"/>',
	        success:function(categoryTree){
	            //addIconInfo(categoryTree);
	            zTree2 = $.fn.zTree.init($("#categoryTree2"), setting, categoryTree);
	            expandZTree(zTree2, 1);
	        }
	    });
	</c:if>   */
  
    //数tab切换
  	$(".tree_tab").click(function(){
  		
  		if($(this).hasClass("tree_tab_selected")){
  			
  		}else{
  			var forUl = $(this).attr("for");
  			
  			$(".tree_tab_div").hide();
  			$(".tree_tab").removeClass("tree_tab_selected");
  			$(this).addClass("tree_tab_selected");
  			$("#"+forUl).parent().show();
  			
  		}
  		
  	});	
});

//根据 条件获得 题型
function getSelectedQuestion(){
	$("#question_info_div_2 tbody .sj_alert").hide();
	
	var backData = {"flag":true};
	
	//验证试题库
	var questionCategoryIdList = $("#question_info_div_2 tbody input[name='questionCategoryId']");
	for(var i=0; i<questionCategoryIdList.length; i++){
		
		var zhi = $(questionCategoryIdList[i]).val();
		
		if(zhi == null || zhi == ""){
			dialog.alert("随机试卷的试题库不可为空!");
			
			backData.flag = false;
			return backData;
		}
	}
	
	//数量与分值只能是正整数
	var reg = /^[1-9]\d*|0$/; 
	var numScoreList = $("#question_info_div_2 tbody").find("input[name='rule_num'], input[name='rule_score']");
	
	for(var i=0; i<numScoreList.length; i++){
		if(!reg.test($.trim($(numScoreList[i]).val()))){
			//alert("数量与分值只能填写0或整数!");
			dialog.alert("数量与分值只能填写0或整数!");
			
			backData.flag = false;
			return backData;
		}
	}
	
	var typeAndCategoryCheck = {"check_flag":true};
	//验证 题型与试卷库的 组合不能重复
	var trs = $("#question_info_div_2 tbody tr");
	for(var i=0; i<trs.length; i++){
		
		var this_questionDisplayType = $(trs[i]).find("select[name='questionDisplayType']").val();
		var this_questionCategoryId = $(trs[i]).find("input[name='questionCategoryId']").val();
		
		var typeAndCategory = this_questionDisplayType + "_" + this_questionCategoryId;
		
		if(typeAndCategoryCheck[typeAndCategory]){
			$(trs[i]).find("td").eq(1).find(".n-msg").text("此题型与题库的组合已经重复").parent().parent().show();
			
			typeAndCategoryCheck["check_flag"] = false;
		}else{
			typeAndCategoryCheck[typeAndCategory] = typeAndCategory;
		}
	}

	if(typeAndCategoryCheck["check_flag"] == false){
		backData.flag = false;
		return backData;
	}
	
	var numCheckList = [];
	var scoreList = [];  //分数设置  key: 题型_试题库_难度
	
	//1主观题，2单选题，3多选题，4判断题，5填空题，6组合题，7多媒体题
	var numCheckListMap = {
	        'q_1':'SUBJECTIVE',
	        'q_2':'SINGLE_CHOICE',
	        'q_3':'MULTI_CHOICE',
	        'q_4':'DETERMINE',
	        'q_5':'FILL_BLANK',
	        'q_6':'GROUP',
	        'q_7':'MULTIMEDIA'
	};
	
	$("#question_info_div_2 tbody tr").each(function(index, val){
		
		//数量验证bean
		var numCheckObj = [];
		
		var this_questionDisplayType = $(val).find("select[name='questionDisplayType']").val();
		var this_questionCategoryId = $(val).find("input[name='questionCategoryId']").val();
		
		var levelList = $(val).find("td:eq(2) div");
		for(var i=0; i<levelList.length; i++){
			
			var level = $(levelList[i]).find("input[name='rule_level']").val();
			var num = parseInt($(levelList[i]).find("input[name='rule_num']").val());
			var score = $(levelList[i]).find("input[name='rule_score']").val();
			
			numCheckObj.push({"difficultyLevel":level, "count":num, "score":score });
			
			for(var k=0; k<num; k++){
				scoreList.push({"score": score});
			}
			
		}
				
		numCheckList.push({"displayType":numCheckListMap["q_"+this_questionDisplayType], "categoryId":this_questionCategoryId, "difficultyCountList":numCheckObj});
		//numCheckList["autoQuestionGroupList["+index+"]"] = {"displayType":numCheckListMap["q_"+this_questionDisplayType], "categoryId":this_questionCategoryId, "difficultyCountList":numCheckObj};
	});
	
	backData.scoreList = scoreList;
	
	//验证数量 对应题型数量 不能超过 数据库中的数量
	var numCheckFlag = true;
	$.ajax({
        type:"POST",
        async:false,  //默认true,异步
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(numCheckList),
        url: "<%=request.getContextPath()%>/exam/paper/difficultyLevelCountListAll.action",
        success:function(data){
	    	//alert(JSON.stringify(data));
	    	
	    	for(var i=0; i<data.length; i++){
	    		//一种题型数量数据
	    		var difficultyLevelList = data[i];
	    		
	    		//数据库数据
				var dataMap = {};
				for(var j=0; j<difficultyLevelList.length; j++){
					
					var level = difficultyLevelList[j].difficultyLevel;
					var num = difficultyLevelList[j].count;
					//var score = difficultyLevelList[j].score;
					
					dataMap["level_" + level] = num;
				}
	    		
	    		//易:1 中:2 难:3  页面上的数据
	    		var levelList = $("#question_info_div_2 tbody tr").eq(i).find("td:eq(2) div");
				var showText = "";
				var levelMap = {"level_1":"易", "level_2":"中", "level_3":"难"};
				//本题型的检测情况
				var thisCheckNumLevel = true;
				
				for(var j=0; j<levelList.length; j++){
					
					if(showText == ""){
						//var selectEdType = $(levelList[j]).find("select[name='questionDisplayType']").val();
						//showText += "对应题库的 " + $(levelList[j]).find("select[name='questionDisplayType'] option[value='"+selectEdType+"']").text() + " 其中";
						showText = "题目数量超过了对应题库，题型的数量";
					}
					
					var level = parseInt($(levelList[j]).find("input[name='rule_level']").val());
					var num = parseInt($(levelList[j]).find("input[name='rule_num']").val());
					//var score = parseInt($(levelList[j]).find("input[name='rule_score']").val());
					
					if(dataMap["level_" + level]){
						//对应题库，对应类型，对应难度，有这样的试题
						
						if(dataMap["level_" + level] < num){
							//数据库中的试题数量 比 用户填写的少
							numCheckFlag = false;
							
							//本题型的检测情况
							thisCheckNumLevel = false;
						}
						
						showText += " " + levelMap["level_" + level] + " 最多：" + dataMap["level_" + level] + "题";
						
					}else{
						
						//数据库数量为0
						if(num > 0){
							//数据库中的试题数量 比 用户填写的少
							numCheckFlag = false;
							
							//本题型的检测情况
							thisCheckNumLevel = false;
						}
						
						showText += " " + levelMap["level_" + level] + " 最多：0题";
					}
				}
				
				if(thisCheckNumLevel == false){
					//数量验证没有通过
					$("#question_info_div_2 tbody tr").eq(i).find("td:eq(2) .sj_alert .n-msg").text(showText).parent().parent().show();
				}else{
					$("#question_info_div_2 tbody tr").eq(i).find("td:eq(2) .sj_alert .n-msg").text("").parent().parent().hide();
				}
	    	}
       	}
	});
	
	if(numCheckFlag == false){
		backData.flag = false;
		return backData;
	}
	
	//获取试题ID
	$.ajax({
        type:"POST",
        async:false,  //默认true,异步
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(numCheckList),
        url: "<%=request.getContextPath()%>/exam/paper/autoGenerateQuestionListAll.action",
        success:function(data){
	    	//alert(JSON.stringify(data));
	    	//$("body").append(JSON.stringify(data));
	    	if(data){
	    		backData.list = data;
	    		
	    	}
       	}
	});
	
	return backData;
}

//选择试题库
function selectQuestionCategory(obj){
	
	 var index = layer.open({
         title:'选择题库',
         type: 1,
         area: ['300px', '320px'],
         skin: 'layui-layer-rim',
         shadeClose: true, //点击遮罩关闭
         content: $('#categoryTreeContainer')
     });
	 
	 $("#closeCategoryTree, #selectCategory").unbind("click");

     $('#closeCategoryTree').click(function () {
         layer.close(index);
     });

     $('#selectCategory').click(function () {
    	 
    	 var category = null;
    	 
    	 var forUl = $(".tree_tab_selected").attr("for");
    	    
   	    if(forUl == "categoryTree"){
   	    	//ztree
   	    	if (zTree != null) {
   		        category = zTree.getSelectedNodes();
   	    	}
   	    }else{
   	    	//ztree2
   	    	if (zTree2 != null) {
   	    		category = zTree2.getSelectedNodes();
   	    	}
   	    }
    	 
    	 if (category && category.length>0) {
    		 category = category[0];
    	 }
    	 
         if (!category) {
             dialog.alert("请先选择数据");
             return;
         }else if(category && category.id == null){
         	layer.alert('不可以选择公司节点(根节点)');
        	return;
         }
         
         $(obj).prev().prev().val(category.id);
         var fullName = getZTreePathName(category);
         $(obj).prev().val(fullName);
         $(obj).prev().attr('title', fullName);
         
         layer.close(index);
     });
}

//增加题型
function addPaperType(){
	$("#question_info_div_2 table tbody").append($("#question_info_div_2 table tfoot").html());
}

//删除题型
function delPaperType(obj){
	
	$(obj).parent().parent().remove();
}

</script>
<style type="text/css">

/* ztree begin */
.ztree{padding:3px;max-height:440px;overflow-y:scroll;overflow-x:auto;}
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:98%;height:30px;padding-top: 0px;border-bottom: 1px dotted #ccc;}
.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
.ztree li a span.button.switch {visibility:visible}
.ztree.showIcon li a span.button.switch {visibility:visible;}
.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}

.ztree li span.button{	background: url("") 0 0 no-repeat;}
.ztree li span.button.center_close,.ztree li span.button.root_close,.ztree li span.button.roots_close,.ztree li span.button.bottom_close  {
	background: url(<%=request.getContextPath()%>/images/img/ico_zk.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}
.ztree li span.button.center_open,.ztree li span.button.root_open,.ztree li span.button.roots_open,.ztree li span.button.bottom_open{
	background: url(<%=request.getContextPath()%>/images/img/ico_sq.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}
.ztree li span.button.ico_docu{
	background: url(<%=request.getContextPath()%>/images/img/ico_txt.png) 0 0 no-repeat;
	vertical-align: middle;
	margin-top: 0px;
}

.ztree li span.button.ico_close{
	display: none;
}
.ztree li span.button.ico_open{
	display: none;
}
.ztree li ul.line{
	background: url("");
}
/* ztree end */
ul.ztree{
	height: 200px;
	overflow-y:scroll;overflow-x:auto;
}

#question_info_div_2{

}
#question_info_div_2 table{
	border-left: 1px solid #CDCDCD;
	border-top: 1px solid #CDCDCD;
	margin-top: 10px;
}
#question_info_div_2 table td{
	border-right: 1px solid #CDCDCD;
	border-bottom: 1px solid #CDCDCD;
	padding: 2px 2px;
	line-height: 30px;
}
#question_info_div_2 table td input{
	height: 30px;
}

input[type="button"]{
	padding:6px 6px;background:#D60500;color:#fff;border:none;
}

.n-arrow{display: none;}


.tree_tab{
	font-size:14px; 
	width:50%; 
	background:#333333; 
	color:#fff; 
	text-align:center; 
	height:30px; 
	line-height:30px; 
	font-weight:normal;
	display:inline-block;
	cursor:pointer;
}

.tree_tab_selected{
	background:#fff; 
	color:#333; 
	cursor:auto;
}

</style>
</head>

<body >


<div style="width:100%;padding:0px 0px;margin:10px 0px 0px 0px;">


	<div id="question_info_div_2" class="add_fr" style="margin-left:10px;"  >
        
        <input type="button" value="新增题型" onclick="addPaperType();" />
        
        <form id="questionForm" action="">
    	<table style="width:820px;">
    		<tbody>
    			<tr>
    				<td style="width:150px;">
    					<span>题型：</span>
    					<select name="questionDisplayType" style="width:90px;" >
		                   <option value="1" selected="selected">主观题</option>
		                   <option value="2">单选题</option>
		                   <option value="3">多选题</option>
		                   <option value="4">判断题</option>
		                   <option value="5">填空题</option>
		                   <option value="6">组合题</option>
		                   <option value="7">多媒体题</option>
		               </select>	
    				</td>
    				<td style="width:280px;" valign="middle">
    					
    					<span class="msg-box n-right sj_alert" style="margin-left:0px;margin-top:-30px;display:none;">
							<span class="msg-wrap n-error" role="alert">
								<span class="n-arrow"><b>◆</b><i>◆</i></span>
								<span class="n-icon"></span>
								<span class="n-msg"></span>
							</span>
						</span>
						
    					<span style="" >试题库：</span>
    					<input type="hidden" name="questionCategoryId" />
           				<input type="text" class="paperCategoryName" style="width:135px;" readonly="readonly" />
           				<input type="button" value="选择试题库" class="xz" onclick="selectQuestionCategory(this)" />
           				
    				</td>
    				<td>
    				
    					<span class="msg-box n-right sj_alert" style="margin:5px 0px 30px -130px;display:none;">
							<span class="msg-wrap n-error" role="alert">
								<!-- <span class="n-arrow"><b>◆</b><i>◆</i></span> -->
								<span class="n-icon"></span>
								<span class="n-msg"></span>
							</span>
						</span>
	
    					<div>
    						<span style="">难度：易</span>
                			<input type="hidden" style="width:40px;height:24px;" name="rule_level" value="1" />	
                			<span style="margin-left:10px;">数量：</span>
    						<input type="text" style="width:40px;height:24px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
    						<span style="margin-left:10px;">分值：</span>
    						<input type="text" style="width:40px;height:24px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	
    					</div>
    					<div>
    						<span style="">难度：中</span>
                			<input type="hidden" style="width:40px;height:24px;" name="rule_level" value="2" />	
                			<span style="margin-left:10px;">数量：</span>
    						<input type="text" style="width:40px;height:24px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
    						<span style="margin-left:10px;">分值：</span>
    						<input type="text" style="width:40px;height:24px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	
    					</div>
    					<div>
    						<span style="">难度：难</span>
                			<input type="hidden" style="width:40px;height:24px;" name="rule_level" value="3" />	
                			<span style="margin-left:10px;">数量：</span>
    						<input type="text" style="width:40px;height:24px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />		
    						<span style="margin-left:10px;">分值：</span>
    						<input type="text" style="width:40px;height:24px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />		
    					</div>
    				</td>
    				<td style="width:50px;">
    					<input type="button" value="删除" class="btn_cx" onclick="delPaperType(this);" />
    				</td>
    			</tr>
    		</tbody>
			<tfoot style="display: none;">
      			<tr>
      				<td style="width:150px;">
      					<span>题型：</span>
      					<select name="questionDisplayType" style="width:90px;" >
		                     <option value="1" selected="selected">主观题</option>
		                     <option value="2">单选题</option>
		                     <option value="3">多选题</option>
		                     <option value="4">判断题</option>
		                     <option value="5">填空题</option>
		                     <option value="6">组合题</option>
		                     <option value="7">多媒体题</option>
		                 </select>	
      				</td>
      				<td style="width:290px;" valign="middle">
      					
      					<span class="msg-box n-right sj_alert" style="margin-left:0px;margin-top:-30px;display:none;">
							<span class="msg-wrap n-error" role="alert">
								<span class="n-arrow"><b>◆</b><i>◆</i></span>
								<span class="n-icon"></span>
								<span class="n-msg"></span>
							</span>
						</span>
										
      					<span style="" >试题库：</span>
      					<input type="hidden" name="questionCategoryId" />
           				<input type="text" class="paperCategoryName" style="width:135px;" readonly="readonly" />
           				<input type="button" value="选择试题库" class="xz" onclick="selectQuestionCategory(this)" />
      				</td>
        				<td>
        				
	        				<span class="msg-box n-right sj_alert" style="margin:5px 0px 30px -130px;display:none;">
								<span class="msg-wrap n-error" role="alert">
									<!-- <span class="n-arrow"><b>◆</b><i>◆</i></span> -->
									<span class="n-icon"></span>
									<span class="n-msg"></span>
								</span>
							</span>
					
        					<div>
        						<span style="">难度：易</span>
			                    <input type="hidden" style="width:40px;height:24px;" name="rule_level" value="1" />	
			                    <span style="margin-left:10px;">数量：</span>
        						<input type="text" style="width:40px;height:24px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
        						<span style="margin-left:10px;">分值：</span>
        						<input type="text" style="width:40px;height:24px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	
        					</div>
        					
        					<div>
        						<span style="">难度：中</span>
                    			<input type="hidden" style="width:40px;height:24px;" name="rule_level" value="2" />	
                    			<span style="margin-left:10px;">数量：</span>
        						<input type="text" style="width:40px;height:24px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />	
        						<span style="margin-left:10px;">分值：</span>
        						<input type="text" style="width:40px;height:24px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />	
        					</div>
        					<div>
        						<span style="">难度：难</span>
                    			<input type="hidden" style="width:40px;height:24px;" name="rule_level" value="3" />	
                    			<span style="margin-left:10px;">数量：</span>
        						<input type="text" style="width:40px;height:24px;" name="rule_num" value="1" onchange="setTotalByShuiJi()" />		
        						<span style="margin-left:10px;">分值：</span>
        						<input type="text" style="width:40px;height:24px;" name="rule_score" value="1" onchange="setTotalByShuiJi()" />		
        					</div>
        				</td>
        				<td style="width:50px;">
        					<input type="button" value="删除" class="btn_cx" onclick="delPaperType(this);" />
        				</td>
        			</tr>
        		</tfoot>
        	</table>
        	</form>
    </div>

	<!-- 试题库树 -->
    
    <div id="categoryTreeContainer" class="" style="width:100%;display:none;" >
   		<%-- <c:if test="${subCompanyId != 1}">
   			<h2 for="categoryTree" class="tree_tab tree_tab_selected" style="" >试题库</h2>
	   		<h2 for="categoryTree2" class="tree_tab" style="margin-left:-6px;" >云试题库</h2>
	        <div class="tree_tab_div">
	        	<ul id="categoryTree" class="ztree"></ul>
	        </div>
	        <div class="tree_tab_div" style="display:none;">
	        	<ul id="categoryTree2" class="ztree"></ul>
	        </div>
   		</c:if> --%>
   		<%-- <c:if test="${subCompanyId == 1}"> --%>
   			<h2 for="categoryTree" class="tree_tab tree_tab_selected" style="background:#333333;color:#FFF;width:100%;" >试题库</h2>
	        <div class="tree_tab_div">
	        	<ul id="categoryTree" class="ztree"></ul>
	        </div>
   		<%-- </c:if> --%>
   		<div align="center" style="margin-top:10px;">
        	<input id="closeCategoryTree" type="button" value="取消" style="padding:10px 12px;background:#FFF;color:#333;border:1px solid #C6C6C6;border-radius:3px;font-weight:bold;" />
			<input id="selectCategory" type="button" value="选择" style="padding:10px 12px;background:#428bca;color:#fff;border:none;border-radius:3px;font-weight:bold;margin-left:10px;" />
        </div>
   	</div>
    
</div>
</body>
</html>
