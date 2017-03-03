<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<style type="text/css">
.arrawimg{float: right;position: relative;bottom: 10px;height: 30px;cursor: pointer;}
.buy_btns span{padding: 10px 20px 10px 20px;color: #fff;font-weight: bold;margin-right: 20px;cursor: pointer;position: relative;top: 20px;}
.buy_btns span:nth-child(1){background: #F5A743;}
.buy_btns span:nth-child(2){background: #D95627;}
.buy_btns span:nth-child(3){background: #D95627;}
.log_top{overflow: hidden;clear: both;min-height: 500px;}
.course_ev .course_pj{min-height: 35px;}
#detail .de_con .log .log_top .right_pj span{float: left;}
#detail .de_con .log .log_top .right_pj i ul li{border:none;}
#detail .de_con .log .log_top .right_pj i ul li:hover{box-shadow:none;}
.evaluDiv{clear: both;overflow: hidden;}
#detail .de_con .log .log_top .evaluDiv ul li{border:none;}
.s_price em{color:#ff7a03;}
.shop{float: right;position: relative;top: -14px;cursor: pointer;}
.shop i{padding: 2px 5px;background: #FEDA4E;border-radius: 20px;color: red;}
.invalid_btn{background:#ccc;}
</style>
<title>课程详情页面</title>
</head>
<body>
<div id="detail" style="padding-top:20px">
	<div class="head_de">
        <!-- <h3>课程详情</h3> -->
        <div class="shop">
           <img src="<%=request.getContextPath() %>/images/mall/img/ico_shop.png" onclick="goShoppingCar()"/>
           <i id="shopCount">0</i>
        </div>
        <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/> 
			<span style="position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">课程详情</span>
		</div>
        
    </div>
    <div class="de_con">
    	<div class="con_top">
        	<div style="float: right;">
        		<div id="c_head">
        			<h4></h4>
	        		<p id="star"></p>
	        		<p></p>
		            <p>编号：</p>
		            <p>分类：</p>
		            <p>价格：<span style="color: red;"></span></p>
        		</div>
	            <p class='buy_btns'>
	            	<span id="bt1" onclick="addShopCar(window.mallCourseId,1,this)">加入购物车</span>
	            	<span id="bt2" onclick="toBuy()">立即购买</span>
	            	<span id="bt3" onclick="addAlreadyBuy()" style="display: none;">加入到已购买课程</span>
	            </p>
        	</div>
        	<div>
        		<img id="t_img" style="width: 423px;height: 242px;" src="" />
        	</div>
        </div>
        <div class="log">
        	<div class="log_top" id="log">
            	<ul class="tabul">
                	<li><a>课程详情</a></li>
                    <li class="change"><a>课程目录</a></li>
                    <li><a>课程评价</a></li>
                </ul>
                <h5></h5>
                <div class="course_detail fl" >
            		<p id="content_1"></p>
            	</div>
            	<div class="course_log fl" id="content_2"></div>
            	<div class="course_ev fl">
            		<div id="evaluDiv" class="evaluDiv" style="display: none;">
							<div style="float: left;"><span style="float: left;">评分：</span>
								<span id="evaluStar" style="clear: both;overflow: hidden;"></span>
							</div>
							<div style="clear: both;"><textarea id="evalContent" rows="5" cols="50" ></textarea></div>
							<input id="starValue" type="hidden"/>
						<input type="button" value="我要评价" style="background: #f5a743 none repeat scroll 0 0;color: #fff;padding: 5px;"/>
					</div>	
	            	<div id="evaluContent"></div>
	            	 <!--分页  -->
					<div id="jquery_page" style="margin-top: 10px; display: none;float: right; " class="pagination"></div>
            	</div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="../common/jqueryInclude.jsp"></jsp:include>
<jsp:include page="../common/includeStar.jsp"></jsp:include>
<jsp:include page="../common/jqueryPaginatorInclude.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript">
var mallCourseId = '${id}';//商城课程id
var comId = '${comId}';// 当前公司的集团公司id
var subId = '${subId}'; // 不为空则代表当前是子公司，代表当前公司的id
var curCourseId;//课程id
var pageSize = 5;
var isFree = false;
var isBuyed = false; // 默认没有购买过
$(function(){
	getMailCourseDetailById();
	getEvaluationCount();
	checkEV();
	getShoppingCarCount();
	checkCourseIsBuyed();
});


/**
 * 验证当前课程是否已购买过
 */
function checkCourseIsBuyed(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/checkCourseIsBuyed.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"mallCourseId" : mallCourseId,
			"comId" : comId,
			"subId" : subId
		},
		success : function(data){
			if(data == 2){ // 自身买过
				isBuyed = true;
				// 置灰 购买等功能按钮
				$('#bt1').css({'background':'#ccc'}).removeAttr('onclick');
				$('#bt2').css({'background':'#ccc'}).removeAttr('onclick');
				$('#bt3').css({'background':'#ccc'}).removeAttr('onclick');
			}else if(data == 1){// 集团公司买过
				isBuyed = true;
				// 置灰 购买等功能按钮
				$('#bt1').css({'background':'#ccc'}).removeAttr('onclick');
				$('#bt2').css({'background':'#ccc'}).html('集团公司已购买').removeAttr('onclick');
				$('#bt3').css({'background':'#ccc'}).removeAttr('onclick');
			}
		}
	});
}

/**
 *
 * 免费课程 加入到已购买课程
 */
function addAlreadyBuy(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/addAlreadyBuy.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"mallCourseId" : mallCourseId
		},
		success : function(data){
			layer.alert('添加成功！');
		}
	});
}

/**
 * 获取购物车中数量
 */
 function getShoppingCarCount(){
	 var urlStr = "<%=request.getContextPath()%>/courseStore/getShoppingCarCount.action";
		$.ajax({
			type : "POST",
			url : urlStr,
			success : function(data){
				$('#shopCount').html(data);
			}
		});
 }

/**
 * 获取商城课程详情
 */
function getMailCourseDetailById(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getMailCourseDetailById.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		async :false,
		data : {
			"id" : mallCourseId
		},
		success : function(data){
			if(data.rtnResult == "SUCCESS"){
				initPage(data.rtnData);
			}
		}
	});
}
function toBuy(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toGenerateOrder.action?ids='+mallCourseId+'&productType='+1;
}
function initPage(data){
	if(data){
		var html = '';
		var name = data.name;
		var code = data.code;
		if(!code){
			code = '';
		}
		var categroyName = data.categroyName;
		var price = data.price;
		var cheapPrice = data.cheapPrice;
		if(cheapPrice == 0){
			isFree = true;
			$('#bt1').hide();
			$('#bt2').hide();
			$('#bt3').show();
		}
		price = Number(price).toFixed(2);
		cheapPrice = Number(cheapPrice).toFixed(2);
		var avgScore = data.avgScore;
		var evaluateNum = data.evaluateNum;
		var img = data.img;
		var courseId = data.courseId;
		curCourseId = courseId;
		html += '<h4>'+name+'</h4>';
		html += '<p id="star"></p>';
		html += '<p>（'+evaluateNum+'人已评价）</p>';
		// html += '<p>编号：'+code+'</p>';
		html += '<p>分类：'+categroyName+'</p>';
		html += '<p class="s_price">课程价格：&nbsp;<em>原价：</em><em style="text-decoration: line-through;">'+price+'</em>&nbsp;&nbsp;<em>优惠价：'+cheapPrice+'元</em></p>';
		getDetailsByCourseId(courseId);
		$("#c_head").html(html);
		$("#t_img").attr("src",img);
		var setting = {
				max : 5,
				value : avgScore,
				image : "../js/rater-star/star.gif",
				enabled : false
		};
		$("#star").rater(setting);
	}
}
/**
 * 跳转购物车
 */
function goShoppingCar(){
	window.location.href = '<%=request.getContextPath()%>/courseStore/toBuyCar.action';
}

/**
 * 获取课程下的章节、课件、测试信息
 */
function getDetailsByCourseId(courseId){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getDetailsByCourseId.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"courseId" : courseId
		},
		success : function(data){
			if(data.rtnResult == "SUCCESS"){
				var result = data.rtnData;
				initContent(result);
			}
		}
	});
}
/**
 * 初始化课程详细信息及目录
 */
function initContent(data){
	if(data){
		var details = data.details;
		var sectionList = data.sectionList;
		var html = '';
		if(sectionList){
			$.each(sectionList,function(index,item){
				var sectionName = item.name;
				var sectionIndex = Number(index)+1;
				var coursewareList = item.coursewareList;
				var examList = item.examList;
				
				html += '<div class="last_capter">';
				html += '<h3>第'+sectionIndex+'章<span>'+sectionName+'</span>';
				html += '<img class="arrawimg" src="<%=request.getContextPath()%>/images/img/btn_1.png" />';
				html += '</h3>';
				if(coursewareList){
					$.each(coursewareList,function(index,item){
						var coursewareId = item.id;
						var coursewareName = item.name;
						var type = item.type;//课件类型
						var typeStr = '';
						if(type == 1){
							typeStr = '标准课件';
						}else if(type == 2){
							typeStr = '普通课件';
						}else if(type == 3){
							typeStr = '视频课件';
						}
	
						html += '<div name="cont_s">';
						html += '<h4>'+typeStr;
						html += '<span><a >'+coursewareName+'</a></span>';
						if(isFree){
							html += '<a onclick="seeContent('+coursewareId+','+type+',1)" class="continue">查看</a>';
						}else{
							html += '<a onclick="seeContent('+coursewareId+','+type+',1)" class="continue">预览</a>';
						}
						html += '</h4>';
						html += '</div>';
					});
				}
				
				if(examList){
					$.each(examList,function(index,item){
						var examId = item.examId;
						var title = item.title;
						var duration = item.duration;//考试时长
						var allowTimes = item.allowTimes;
	
						html += '<div name="cont_s">';
						html += '<h4>章节测试';
						html += '<strong><a >测试时长：'+duration+'分钟</a></strong>';
						html += '<b>测试次数：'+allowTimes+'次</b>';
						if(isFree){
							html += '<a onclick="seeContent('+examId+',null,2)" class="continue">查看</a>';
						}else{
							html += '<a onclick="seeContent('+examId+',null,2)" class="continue">预览</a>';
						}
						html += '</h4>';
						html += '</div>';
					});
				}
				html += '</div>';
			});
		}
		
		$("#content_1").html(details);
		$("#content_2").html(html);
		ActionScript();
	}
}

function seeContent(id,courseWareType,type){
	//这里先判断是否有购买过 
	var urlStr = "<%=request.getContextPath()%>/courseStore/judgeIsBuy.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"courseId" : curCourseId
		},
		success : function(data){
			if(data>0){//说明已购买
				isFree = true;
			}
			//type : 1代表课件  2代表测试
			var str = '';
			if(type==1){// 课件
				str = '<%=request.getContextPath()%>/res/toCoursewareDetail.action?courseWareId='+id+'&courseWareType='+courseWareType+'&isFree='+isFree;
			}else if(type==2){// 测试
				str = '<%=request.getContextPath()%>/myExamManage/gotoMatchTest.action?id='+id+'&isFree='+isFree;
			}
			 window.open(str);
		}
	});
}
/**
 * 获取评价信息
 */
function getEvaluationCount(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getEvaluationCount.action";
	$.ajax({
		type : "POST",
		url : urlStr,
		data : {
			"courseId" : curCourseId
		},
		success : function(data){
			insertPage(data.rtnData);
		}
	});
}

//插入分页插件
function insertPage(sum){
	var urlStr = "<%=request.getContextPath()%>/courseStore/getEvaluation.action";
	if(sum>pageSize){
		$("#jquery_page").show();
	}
	//插入分页插件
	$("#jquery_page").pagination(sum, {
		 prev_text: '上一页', 
		 next_text: '下一页', 
		 items_per_page: pageSize, //每页显示的个数
		 num_display_entries: 10,  //中间显示的页数
		 current_page: 0,         //初始页
		 num_edge_entries: 2,     //两侧显示的首尾分页的条目数
		 callback: function(page){
			var params = new Object;
			params.courseId = curCourseId;
			params.page = page+1;
			params.pageSize = pageSize;
			 $.ajax({
			  		type: "POST",
			  		url: urlStr,
			   		data:  params,
			  		dataType:"json",
			  		success:function(data){
			  			var list = data.rtnDataList;
						initEvaluation(list);
			  		}
			   	});
		 }  
	});
}

function initEvaluation(list){
	var evaluContent = $('#evaluContent');
	evaluContent.empty();
	if(list){
		$.each(list,function(index,item){
			var html = '';
			var id = item.id;
			var uname = item.userName;
			var headImg = item.headImg;
			var content = item.content;
			var score = item.score;
			html += '<div class="course_pj">';
			html += '<div class="left_pj">';
			html += '<img class="btnimg" style="width: 30px;" src="'+headImg+'" />';
			html += '</div>';
			html += '<div class="right_pj">';
			html += '<p>';
			html += '<span>'+uname+'</span>';
			html += '<i id="star_'+id+'"></i>';
			html += '</p>';
			html += '<p>'+content+'</p>';
			html += '</div></div>';
			evaluContent.append(html);
			var setting = {
					max : 5,
					value : score,
					image : "../js/rater-star/star.gif",
					enabled : false
				};
			$("#star_" + id+ "").rater(setting);
		});
	}
}

/**
 * 加入购物车
 */
function addShopCar(id,type,obj){//type:1 课程 2专题     obj:当前的点击按钮
	var urlStr = "<%=request.getContextPath()%>/courseStore/addShoppingRecord.action";
	var param = new Object;
	param.productId = id;
	param.type = type;
	param.counts = 1;
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data :param,
 		success : function(data) {
 			if(data.rtnResult == "SUCCESS"){
 				layer.tips('加入购物车成功！',$(obj));
 				getShoppingCarCount();
 			}
 		}
 	});
}
function ActionScript(){
	$('#log').find('.tabul li').click(function(){
		$('#log').find('.tabul li').attr('class','');
		$('#log>div').css('display','none');
		$(this).attr('class','change');
		$('#log>div').eq($(this).index()).css('display','block');
	});
	
	$('.arrawimg').on('click',function(){
		$(this).parent().siblings().slideToggle();
	});
}

/**
 * 检测当前用户是否能评价当前课程 
 */
function checkEV(){
	var urlStr = "<%=request.getContextPath()%>/courseStore/checkUserCanEvaluate.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data:{
 			"courseId" : mallCourseId
 		},
 		success : function(data) {
 			var resultType = data.type;
 			var curEvaluate = data.curEvaluate;
 			var setting;
			var btn = $('#evaluDiv input[type="button"]');
				if(!isFree && resultType==0){// 不可评价
	 				$('#evaluDiv').hide();
	 				return ; 
	 			}else if((!isFree && resultType==1) || (isFree && resultType==0)){ // 可评价
	 				$('#evaluDiv').show();
	 				btn.bind('click',submitEvaluate);
	 				setting = {
	 	 					max : 5,
	 	 					image : "../js/rater-star/star.gif",
	 	 					after_click : function(ret) {
	 	 						$("#starValue").val(ret.value);
	 	 					}
	 	 				};
	 	 			$("#evaluStar").rater(setting);
	 			}else if((!isFree && resultType==2) || (isFree && resultType==2)){ // 已评价
	 				btn.val('已评价');
	 				btn.unbind('click');
	 				btn.css({'background':'#F5A743','color':'#fff'});
	 				if(curEvaluate){
	 	 				curEvaluate = $.parseJSON(curEvaluate);
	 	 			}else{
	 	 				return;
	 	 			}
	 				var content = curEvaluate.content;
	 				var score = curEvaluate.score;
	 				$('#evaluDiv').show();
	 				setting = {
	 						max : 5,
	 						value : score,
	 						image : "../js/rater-star/star.gif",
	 						enabled : false
	 				};
	 				$("#evaluStar").rater(setting);
	 				
	 				$("#evalContent").attr("readonly","readonly");
	 				$("#evalContent").val(content);
	 			}
			}
 	});
}

function submitEvaluate(){
	var param = new Object;
	param.courseId = curCourseId;
	param.score = $('#starValue').val();
	param.content = $("#evalContent").val();
	
	var urlStr = "<%=request.getContextPath()%>/courseStore/giveEvaluate.action";
 	$.ajax({
 		type : "POST",
 		url : urlStr,
 		data :param,
 		success : function(data) {
 			getEvaluationCount();
 			checkEV();
 			// 更新当前课程信息
 			getMailCourseDetailById();
 		}
 	});
}
</script>
</html>