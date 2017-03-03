<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程中心</title>
<!-- 引入页面style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<!-- jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<!-- jquery_paginator -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_paginator/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/jquery_paginator/pagination.css">
<!-- mmGrid -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
<!-- dateUtil -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateUtil.js"></script>
<!-- 菜单js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/course_menu.js"></script>
<!-- artDialog -->
<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>

<style type="text/css">
img {border: medium none;vertical-align: top;}

/**企业课程体系*/
/* #Ul_1 li{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;} */

/**普连课程体系*/
/* #pulianCategory li{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;} */

/**课程名称*/
.courseNameStyle{text-overflow: ellipsis;white-space: nowrap;overflow: hidden;}

/**降序排序*/
.sortDesc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9down.png) no-repeat 75px center;}

/**升序排序*/
.sortAsc{display:block; float:left; width:85px; height:32px; line-height:32px; color:#666666; 
text-align:center; border:1px solid #cccccc; margin-right:-1px; 
background:#fff url(../images/img/bg_9.png) no-repeat 75px center;}

/**整体展现课程样式*/
#companyCoursesDiv{padding-top:40px;}

/**图片展现样式*/
#picsShow{min-height:600px;height:auto;}

/**无结果样式*/
#noResults{min-height:600px;height:auto;}

/**列表展现样式*/
#listShow{min-height:600px;height:auto;padding-bottom:60px;}

/**分类菜单样式*/
#course_all .select_cr .course_cs{position: absolute;z-index: 1000;width: 210px;height:40px;
background-color: #DD0500;padding-left:0px;}

/**菜单标题样式*/
#course_all .select_cr .course_cs h2{
background: transparent url("<%=request.getContextPath()%>/images/type_menu/icon-dropdown.jpg") no-repeat scroll 122px center;
cursor: pointer;width: 194px;height: 38px;line-height: 38px;color: #FFF;padding-left: 16px;
font-size: 16px;font-family: "微软雅黑";}

/**菜单ul样式*/
.prosul {padding-left: 3px;width: 207px;margin-top: 2px;min-height:414px;height:auto;
background-color: rgb(221, 5, 0);}

/**菜单li样式*/
.prosul > li {line-height: 50px;height: 50px;padding-left: 10px;}

.prosul > li a.ti {font-size: 14px;font-family: "微软雅黑";color: #FFF;}

.prosul > li > em{float: right;color: white;padding-right: 20px;font-size:14px;}

/**li中div样式*/
.prosul .prosmore {text-align: left;}

.prosmore {padding: 15px 15px 15px 15px;position: absolute;z-index: 999;left: 210px;top: 38px;
background-color: #FFF;border-width: 2px 2px 2px 0px;border-style: solid solid solid none;
border-color: #DD0500 #DD0500 #DD0500 -moz-use-text-color;-moz-border-top-colors: none;
-moz-border-right-colors: none;-moz-border-bottom-colors: none;-moz-border-left-colors: none;
border-image: none;width: 600px;height:auto;}
 
.prosahover{background-color:#FFF;color:#DD0500;}

.prosul li.prosahover a{color: #636363;}
.prosul li.prosahover a.ti{color: #DD0500;}

.prosul li.prosahover > em{color: #DD0500;}
 
/**div每个li*/
.prosmore > ul > li > a{width:22%;float:left;}

.prosmore > ul > li > ul{width:80%;float:left;}
 
.prosmore > ul > li > ul > li{float:left;padding-left:20px;}

/**菜单样式*/
.hide{display: none}

.prosul li.nochild a:hover{color: #fff}
</style>

<script type="text/javascript">
var userId = '${USER_ID_LONG}';
var postId = '${USER_BEAN.postId}';
var companyId = '${USER_BEAN.companyId}';
var subCompanyId = '${USER_BEAN.subCompanyId}';
var sortName = 'create_time';//排序类型（创建时间，平均评价，学习人数），默认创建时间
var sortOrder = 'desc';//排序顺序（降序，升序），默认降序
var showType = 'pics';//展现形式（大图，列表），默认大图
var searchCategoryId = '';//查询的课程体系id（分类id），默认是空
var searchCategoryType = '';//课程体系还是课程分类（1：课程体系，2：课程分类）
var searchContent = '';//查询输入框内容，默认是空
var itemsPerPage = 20;//每页显示条数
var deptId = '${USER_BEAN.deptId}';//部门id

//普连公司参数
/* var pulianCompanyId = 1;
var pulianSortName = 'create_time';
var pulianSortOrder = 'desc';
var pulianShowType = 'pics';
var pulianSearchCategoryId = '';
var pulianSearchContent = '';
var pulianItemsPerPage = 20; */
	
/**
 * 页面加载完成
 */
$(function(){
	//初始化课程体系
	//initCourseCategory();
	//初始化课程体系和分类
	initCourseTypes();
	//查询课程（图片）
	getSearchResults();
	//初始化课程列表
	initCourseListGrid();
	
	//初始化云平台课程体系
	//initPulianCourseCategory();
	//$("#pulianCategory").hide();
	//初始化云平台课程（图片展现）
	//initPulianCoursesForPics();
	//初始化云平台课程（列表展现）
	//initPulianCoursesForList();
	
	//隐藏云平台div
	//$("#pulianCoursesDiv").hide();
});

/**
 * 初始化课程体系（根节点课程体系）
 */
function initCourseCategory(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getCourseCategorys.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				var htmlStr = '<li id="categoryAll" class="active" onclick="searchByCategory()">全部</li>';
				var categorys = data.rtnData;
				for(var i = 0; i < categorys.length; i++){
					var category = categorys[i];
					htmlStr += '<li id="category'+category.id+'" title="'+category.name+'" onclick="searchByCategory('+category.id+')">'+category.name+'</li>';
				}
				$("#Ul_1").html(htmlStr);
			}
		}
	});
}

/**
 * 按照课程体系查询课程
 */
function searchByCategory(thisSearchCategoryId){
	//修改课程体系选中样式
	$('#Ul_1').find('li').removeClass('active');
	if(thisSearchCategoryId == null || thisSearchCategoryId == ''){
		$("#categoryAll").addClass('active');
	}else{
		$("#category"+thisSearchCategoryId+"").addClass('active');
	} 
	//根据课程体系id查询子课程体系
	var param = new Object();
	param.categoryId = thisSearchCategoryId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getChildCategorys.action',
		success:function(data){
			if(data != null && data.length > 0){
				var categoryStr = '<li id="backCategorys" class="active" onclick="backUpCategory('+thisSearchCategoryId+');">返回</li>';
				for(var i = 0; i < data.length; i++){
					var category = data[i];
					categoryStr += '<li id="category'+category.id+'" title="'+category.name+'" onclick="searchByCategory('+category.id+')">'+category.name+'</li>';
				}
				$("#Ul_1").html(categoryStr);
			}else{
				//获取选中的课程体系id
				searchCategoryId = thisSearchCategoryId;
				//置空查询输入框
				$("#searchContent").val('');
				searchContent = '';
				//将排序设为默认
				sortName = 'create_time';
				sortOrder = 'desc';
				search();
			}
		}
	});
}

/**
 * 返回上一级课程体系
 */
function backUpCategory(thisSearchCategoryId){
	var param = new Object();
	param.categoryId = thisSearchCategoryId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getUpCategorys.action',
		success:function(data){
			if(data != null && data.length > 0){
				var newCategoryId = '';
				var categoryStr = '<li id="backCategorys" class="active">返回</li>';
				for(var i = 0; i < data.length; i++){
					var category = data[i];
					newCategoryId = category.parentId;
					categoryStr += '<li id="category'+category.id+'" title="'+category.name+'" onclick="searchByCategory('+category.id+')">'+category.name+'</li>';
				}
				$("#Ul_1").html(categoryStr);
				$("#backCategorys").addClass("active");
				$("#backCategorys").click(function(){
					backUpCategory(newCategoryId);
				});
			}else{
				initCourseCategory();
			}
		}
	});
}

/**
 * 初始化课程体系和分类
 */
function initCourseTypes(){
	var param = new Object();
	param.companyId = companyId;
	param.subCompanyId = subCompanyId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getFirstTypes.action',
		success:function(data){
			if(data){
				var htmlStr = '';
				for(var i = 0; i < data.length; i++){
					var categoryType = data[i];
					htmlStr += '<li>';
					htmlStr += '<em>&gt;</em>';
					htmlStr += '<a href="javascript:void(0)" class="ti" onclick="getCoursesByType('+categoryType.id+','+categoryType.categoryOrType+');">'+categoryType.name+'</a>';
					
					//根据一级课程体系id查询二级课程体系和三级课程体系列表
					var innerParam = new Object();
					innerParam.typeId = categoryType.id;
					innerParam.categoryOrType = categoryType.categoryOrType;
					$.ajax({
						type:'POST',
						async:false,//此处为同步
						data:innerParam,
						url:'<%=request.getContextPath()%>/courseUserAction/getNextTypes.action',
						success:function(data){
							var nextTypes = data;
							if(nextTypes != null && nextTypes.length > 0){
								htmlStr += '<div class="prosmore hide">';
								htmlStr += '<ul>';
								for(var j = 0; j < nextTypes.length; j++){
									var nextType = nextTypes[j];
									htmlStr += '<li>';
									htmlStr += '<a onclick="getCoursesByType('+nextType.nextId+','+nextType.nextCategoryOrType+');">'+nextType.nextName+'<em>&gt;</em></a>';
									if(nextType.thirdTypes != null && nextType.thirdTypes.length > 0){
										htmlStr += '<ul>';
										for(var k = 0; k < nextType.thirdTypes.length; k++){
											var thirdType = (nextType.thirdTypes)[k];
											if(thirdType.name){
												htmlStr += '<li><a onclick="getCoursesByType('+thirdType.id+','+thirdType.categoryOrType+')">'+thirdType.name+'</a></li>';
											}
										}
										htmlStr += '</ul>';
									}
									htmlStr += '</li>';
								}
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
						}
					});
					
					htmlStr += '</li>';
				}
				$("#proinfo").html(htmlStr);
				//菜单特效
				$("#proinfo").coursemenu();
			}
		}
	});
}

/**
 * 根据分类查询课程
 */
function getCoursesByType(typeId,categoryOrType){
	searchCategoryId = typeId;
	searchCategoryType = categoryOrType;
	//清空查询输入框中的值
	searchContent = '';
	//隐藏菜单项
	$("#proinfo").hide();
	$("#courseType").click();
	search();
}


/**
 * 根据条件查询课程（图片展现）
 */
function getSearchResults(){
	var opts = {
			items_per_page:itemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			callback:function(page,node){//page从0开始，指代当前页，node是分页的div
				//查询参数
				var param = new Object();
				param.companyId = companyId;//公司id
				param.subCompanyId = subCompanyId;//子公司id
				param.userId = userId;//用户id
				param.postId = postId;//岗位id
				param.searchCategoryId = searchCategoryId;//课程体系id（分类id）
				param.searchCategoryType = searchCategoryType;//体系还是分类
				param.searchContent = searchContent;//查询输入框
				param.sortName = sortName;//按什么排序
				param.sortOrder = sortOrder;//排序顺序（降序、升序）
				param.page = page;//当前页
				param.pageSize = itemsPerPage;//每页显示条数
				param.deptId = deptId;//部门id
				//异步查询
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseUserAction/getCoursesByConditions.action',
					success:function(data){
						var htmlStr = '';
						if(data != null && data != ''){
							$("#picsShow").show();
							$("#noResults").hide();
							for(var i = 0; i < data.length; i++){
								var course = data[i];
								if((i+1)%4 == 0){
									htmlStr += '<li class="last_li" onclick="toCourseDetail('+course.id+')">';
								}else{
									htmlStr += '<li onclick="toCourseDetail('+course.id+')">';
								}
								htmlStr += '<div class="bg">';
								htmlStr += '<img src="'+course.frontImage+'" width="248px" height="173px"/>';
								htmlStr += '<h4 title="'+course.name+'" style="" class="courseNameStyle">'+course.name+'</h4>';
								htmlStr += '<span>平均评分'+course.averageScore+'</span>';
								htmlStr += '<span>'+course.evaluatorNum+'人已评价</span>';
								htmlStr += '<span>'+course.studentNum+'人已学习</span>';
								htmlStr += '</div>';
								htmlStr += '</li>';
							}
						    $("#cs_list").html(htmlStr);
						}else{
							$("#picsShow").hide();
							$("#noResults").show();
						}
					}
				});
			}
	};
	//分页查询
	var count = getSearchResulltsCount();
	$("#picsPager").pagination(count,opts);
}

/**
 * 根据条件查询课程数目
 */
function getSearchResulltsCount(){
	//查询参数
	var param = new Object();
	param.companyId = companyId;//公司id
	param.subCompanyId = subCompanyId;//子公司id
	param.userId = userId;//用户id
	param.postId = postId;//岗位id
	param.searchCategoryId = searchCategoryId;//课程体系
	param.searchCategoryType = searchCategoryType;//体系还是分类
	param.searchContent = searchContent;//查询输入框
	param.deptId = deptId;//部门id
	//总条数
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步，必须先查出总条数
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getSearchResulltsCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 初始化课程列表mmGrid
 */
function initCourseListGrid(){
	var param = new Object();
	param.companyId = companyId;//公司id
	param.subCompanyId = subCompanyId;//子公司id
	param.userId = userId;//用户id
	param.postId = postId;//岗位id
	param.searchCategoryId = searchCategoryId;//课程体系
	param.searchCategoryType = searchCategoryType;//体系还是分类
	param.searchContent = searchContent;//查询输入框
	param.sortName = sortName;//按什么排序
	param.sortOrder = sortOrder;//降序升序
	param.deptId = deptId;//部门id
	
	$("#listShowTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/courseUserAction/getCoursesForMMGrid.action',
		width : '1042px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols:[{
			title : 'id',
			name : 'id',
			hidden : true
		},{
			title : '课程名称',
			name : 'name',
			align : 'center',
			renderer : function(val, item, rowIndex) {
				var str = '<a href="javascript:void(0);" onclick="toCourseDetail('+item.id+')">'+val+'</a>';
				return str;
			}
		},{
			title : '创建时间',
			name : 'createTime',
			align : 'center',
			renderer : function(val, item, rowIndex) {
				return getSmpFormatDateByLong(val);
			}
		},{
			title : '评分',
			name : 'averageScore',
			align : 'center'
		},{
			title : '评价人数',
			name : 'evaluatorNum',
			align : 'center'
		},{
			title : '学习人数',
			name : 'studentNum',
			align : 'center'
		}],
		plugins : [ $('#listPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [20,40,100,200]
		}) ]
	});
	
	//隐藏或显示列表
	if(showType == 'pics'){
		$("#listShow").hide();
	}else{
		$("#listShow").show();
	}
}

/**
 * 查询课程
 */
function search(){
	if(showType == 'pics'){
		$("#listShow").hide();
		getSearchResults();
	}else{
		$("#picsShow").hide();
		$("#noResults").hide();
		$("#listShow").show();
		//加载mmGrid
		var searchParam = new Object();
		searchParam.companyId = companyId;//公司id
		searchParam.subCompanyId = subCompanyId;//子公司id
		searchParam.searchCategoryId = searchCategoryId;//课程体系
		searchParam.searchCategoryType = searchCategoryType;//体系还是分类
		searchParam.searchContent = searchContent;//查询输入框
		searchParam.sortName = sortName;//按什么排序
		searchParam.sortOrder = sortOrder;//降序升序 
		$("#listShowTable").mmGrid().load(searchParam);
	}
}	

/**
 * 按输入框内容查询
 */
function searchByContent(){
	searchContent = $("#searchContent").val();
	//清空体系（分类）
	searchCategoryId = '';
	searchCategoryType = '';
	search();
}

/**
 * 展现形式为大图
 */
function showByPics(){
	$("#showByPicsButton").attr("class","btn_5");
	$("#showByListButton").attr("class","btn_6");
	showType = 'pics';
	search();
}

/**
 * 展现形式为列表
 */
function showBylist(){
	$("#showByPicsButton").attr("class","btn_6");
	$("#showByListButton").attr("class","btn_5");
	showType = 'list';
	search();
}

/**
 * 按创建时间排序
 */
function sortByTime(){
	if(sortName == 'create_time' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByTime").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByTime").attr("class","sortDesc");
	}
	sortName = 'create_time';
	search();
}

/**
 * 按平均评分排序
 */
function sortByEvaluate(){
	if(sortName == 'average_score' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByEvaluate").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByEvaluate").attr("class","sortDesc");
	}
	sortName = 'average_score';
	search();
}

/**
 * 按学习人数排序
 */
function sortByStudentNum(){
	if(sortName == 'student_num' && sortOrder == 'desc'){
		sortOrder = 'asc';
		$("#sortByStudentNum").attr("class","sortAsc");
	}else{
		sortOrder = 'desc';
		$("#sortByStudentNum").attr("class","sortDesc");
	}
	sortName = 'student_num';
	search();
}

/**
 * 每页显示20条数据
 */
function changePagesizeOfTwenty(){
	$("#pageSizeTwenty").addClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 20;
	search();
}

/**
 * 每页显示40条数据
 */
function changePagesizeOfFourty(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").addClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 40;
	search();
}

/**
 * 每页显示100条数据
 */
function changePagesizeOfOneHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").addClass("first");
	$("#pageSizeTwoHundred").removeClass("first");
	itemsPerPage = 100;
	search();
}

/**
 * 每页显示200条数据
 */
function changePagesizeOfTwoHundred(){
	$("#pageSizeTwenty").removeClass("first");
	$("#pageSizeFourty").removeClass("first");
	$("#pageSizeOneHundred").removeClass("first");
	$("#pageSizeTwoHundred").addClass("first");
	itemsPerPage = 200;
	search();
}
	
/**
 * 跳转到课程详情页面
 */
function toCourseDetail(courseId){
	window.location.href = "<%=request.getContextPath()%>/courseDetailAction/toCourseDetail.action?courseId="+courseId+"&backFlag=1";
}

/**普联云平台课程start*/

/**
 * 展示普联课程（根节点课程体系）
 */
function initPulianCourseCategory(){
	var param = new Object();
	param.companyId = pulianCompanyId;
	$.ajax({
		type:'POST',
		async:true,//异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getCoursePulianCategorys.action',
		success:function(data){
			if(data.rtnResult == 'SUCCESS'){
				var htmlStr = '<li id="pulianCategoryAll" class="active" onclick="searchByPulianCategory()">全部</li>';
				var categorys = data.rtnData;
				for(var i = 0; i < categorys.length; i++){
					var category = categorys[i];
					htmlStr += '<li id="pulianCategory'+category.id+'" title="'+category.name+'" onclick="searchByPulianCategory('+category.id+')">'+category.name+'</li>';
				}
				$("#pulianCategory").html(htmlStr);
			}
		}
	});
}

/**
 * 初始化普联课程（图片展现）
 */
function initPulianCoursesForPics(){
	var opts = {
			items_per_page:pulianItemsPerPage,//每页显示条数
			prev_text:'<',
			next_text:'>',
			prev_show_always:false,//不一直显示上一页
			next_show_always:false,//不一直显示下一页
			num_edge_entries:2,//两侧显示的首尾分页的条目数
			callback:function(page,node){//page从0开始，指代当前页，node是分页的div
				//查询参数
				var param = new Object();
				param.companyId = pulianCompanyId;//普连id
				param.searchCategoryId = pulianSearchCategoryId;//课程体系
				param.searchContent = pulianSearchContent;//查询输入框
				param.sortName = pulianSortName;//按什么排序
				param.sortOrder = pulianSortOrder;//排序顺序（降序、升序）
				param.page = page;//当前页
				param.pageSize = pulianItemsPerPage;//每页显示条数
				//异步查询
				$.ajax({
					type:'POST',
					async:true,//异步
					data:param,
					url:'<%=request.getContextPath()%>/courseUserAction/getPulianCoursesForPics.action',
					success:function(data){
						var htmlStr = '';
						if(data != null && data != ''){
							$("#pulianPicsShow").show();
							$("#pulianNoResults").hide();
							for(var i = 0; i < data.length; i++){
								var course = data[i];
								if((i+1)%4 == 0){
									htmlStr += '<li class="last_li" onclick="toCourseDetail('+course.id+')">';
								}else{
									htmlStr += '<li onclick="toCourseDetail('+course.id+')">';
								}
								htmlStr += '<div class="bg">';
								htmlStr += '<img src="'+course.frontImage+'" width="248px" height="173px"/>';
								htmlStr += '<h4 title="'+course.name+'" class="courseNameStyle">'+course.name+'</h4>';
								htmlStr += '<span>平均评分'+course.averageScore+'</span>';
								htmlStr += '<span>'+course.evaluatorNum+'人已评价</span>';
								htmlStr += '<span>'+course.studentNum+'人已学习</span>';
								htmlStr += '</div>';
								htmlStr += '</li>';
							}
						    $("#pulian_cs_list").html(htmlStr);
						}else{
							$("#pulianPicsShow").hide();
							$("#pulianNoResults").show();
						}
					}
				});
			}
	};
	//分页查询
	var count = getPulianCoursesCount();
	$("#pulianPicsPager").pagination(count,opts);
}

/**
 * 获取普连的课程数量
 */
function getPulianCoursesCount(){
	//查询参数
	var param = new Object();
	param.companyId = pulianCompanyId;//普联id
	param.searchCategoryId = pulianSearchCategoryId;//课程体系
	param.searchContent = pulianSearchContent;//查询输入框
	//总条数
	var count = 0;
	$.ajax({
		type:'POST',
		async:false,//同步，必须先查出总条数
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getPulianCoursesCount.action',
		success:function(data){
			count = data;
		}
	});
	return count;
}

/**
 * 展示普连课程（列表展现）
 */
function initPulianCoursesForList(){
	var param = new Object();
	param.companyId = pulianCompanyId;//普连id
	param.searchCategoryId = pulianSearchCategoryId;//课程体系
	param.searchContent = pulianSearchContent;//查询输入框
	param.sortName = pulianSortName;//按什么排序
	param.sortOrder = pulianSortOrder;//降序升序
	
	$("#pulianListShowTable").mmGrid({
		root : 'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url : '<%=request.getContextPath()%>/courseUserAction/getPulianCoursesForMMGrid.action',
		width : '1042px',
		height : 'auto',
		fullWidthRows : true, // 表格第一次加载数据时列伸展，自动充满表格
		showBackboard : false,
		checkCol : false,
		multiSelect : true,
		indexCol : true, // 索引列
		params : param,
		cols:[{
			title : 'id',
			name : 'id',
			hidden : true
		},{
			title : '课程名称',
			name : 'name',
			align : 'center',
			renderer : function(val, item, rowIndex) {
				var str = '<a href="javascript:void(0);" onclick="toCourseDetail('+item.id+')">'+val+'</a>';
				return str;
			}
		},{
			title : '创建时间',
			name : 'createTime',
			align : 'center',
			renderer : function(val, item, rowIndex) {
				return getSmpFormatDateByLong(val);
			}
		},{
			title : '评分',
			name : 'averageScore',
			align : 'center'
		},{
			title : '评价人数',
			name : 'evaluatorNum',
			align : 'center'
		},{
			title : '学习人数',
			name : 'studentNum',
			align : 'center'
		}],
		plugins : [ $('#pulianListPager').mmPaginator({
			totalCountName : 'total', // 对应MMGridPageVoBean total
			page : 1, // 初始页
			pageParamName : 'page', // 当前页数
			limitParamName : 'pageSize', // 每页数量
			limitList : [20,40,100,200]
		}) ]
	});
	
	//隐藏或显示列表
	if(pulianShowType == 'pics'){
		$("#pulianListShow").hide();
	}else{
		$("#pulianListShow").show();
	}
}

/**
 * 查询课程
 */
function searchOfPulian(){
	if(pulianShowType == 'pics'){
		$("#pulianListShow").hide();
		initPulianCoursesForPics();
	}else{
		$("#pulianPicsShow").hide();
		$("#pulianNoResults").hide();
		$("#pulianListShow").show();
		//加载mmGrid
		var searchParam = new Object();
		searchParam.companyId = pulianCompanyId;//普联id
		searchParam.searchCategoryId = pulianSearchCategoryId;//课程体系
		searchParam.searchContent = pulianSearchContent;//查询输入框
		searchParam.sortName = pulianSortName;//按什么排序
		searchParam.sortOrder = pulianSortOrder;//降序升序 
		$("#pulianListShowTable").mmGrid().load(searchParam);
	}
}


/**
 * 按照课程体系查询课程（普连）
 */
function searchByPulianCategory(thisSearchCategoryId){
	//修改课程体系选中样式
	$('#pulianCategory').find('li').removeClass('active');
	if(thisSearchCategoryId == null || thisSearchCategoryId == ''){
		$("#pulianCategoryAll").addClass('active');
	}else{
		$("#pulianCategory"+thisSearchCategoryId+"").addClass('active');
	} 
	//根据课程体系id查询子课程体系
	var param = new Object();
	param.categoryId = thisSearchCategoryId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getChildCategorys.action',
		success:function(data){
			if(data != null && data.length > 0){
				var categoryStr = '<li id="backPulianCategorys" class="active" onclick="backUpPulianCategory('+thisSearchCategoryId+');">返回</li>';
				for(var i = 0; i < data.length; i++){
					var category = data[i];
					categoryStr += '<li id="pulianCategory'+category.id+'" title="'+category.name+'" onclick="searchByPulianCategory('+category.id+')">'+category.name+'</li>';
				}
				$("#pulianCategory").html(categoryStr);
			}else{
				//获取选中的课程体系id
				pulianSearchCategoryId = thisSearchCategoryId;
				//置空查询输入框
				$("#pulianSearchContent").val('');
				pulianSearchContent = '';
				//将排序设为默认
				pulianSortName = 'create_time';
				pulianSortOrder = 'desc';
				searchOfPulian();
			}
		}
	});
}

/**
 * 返回上一级课程体系（普连）
 */
function backUpPulianCategory(thisSearchCategoryId){
	var param = new Object();
	param.categoryId = thisSearchCategoryId;
	$.ajax({
		type:'POST',
		async:true,//默认异步
		data:param,
		url:'<%=request.getContextPath()%>/courseUserAction/getUpCategorys.action',
		success:function(data){
			if(data != null && data.length > 0){
				var newCategoryId = '';
				var categoryStr = '<li id="backPulianCategorys" class="active">返回</li>';
				for(var i = 0; i < data.length; i++){
					var category = data[i];
					newCategoryId = category.parentId;
					categoryStr += '<li id="pulianCategory'+category.id+'" title="'+category.name+'" onclick="searchByPulianCategory('+category.id+')">'+category.name+'</li>';
				}
				$("#pulianCategory").html(categoryStr);
				$("#backPulianCategorys").addClass("active");
				$("#backPulianCategorys").click(function(){
					backUpPulianCategory(newCategoryId);
				});
			}else{
				initPulianCourseCategory();
			}
		}
	});
}

/**
 * 按输入框内容查询（普连）
 */
function searchByPulianContent(){
	pulianSearchContent = $("#pulianSearchContent").val();
	searchOfPulian();
}

/**
 * 展现形式为大图（普连）
 */
function showByPulianPics(){
	$("#showByPulianPicsButton").attr("class","btn_5");
	$("#showByPulianListButton").attr("class","btn_6");
	pulianShowType = 'pics';
	searchOfPulian();
}
	
/**
 * 展现形式为列表（普连）
 */
function showByPulianlist(){
	$("#showByPulianPicsButton").attr("class","btn_6");
	$("#showByPulianListButton").attr("class","btn_5");
	pulianShowType = 'list';
	searchOfPulian();
}

/**
 * 按创建时间排序（普连）
 */
function sortByPulianTime(){
	if(pulianSortName == 'create_time' && pulianSortOrder == 'desc'){
		pulianSortOrder = 'asc';
		$("#sortByPulianTime").attr("class","sortAsc");
	}else{
		pulianSortOrder = 'desc';
		$("#sortByPulianTime").attr("class","sortDesc");
	}
	pulianSortName = 'create_time';
	searchOfPulian();
}

/**
 * 按平均评分排序（普连）
 */
function sortByPulianEvaluate(){
	if(pulianSortName == 'average_score' && pulianSortOrder == 'desc'){
		pulianSortOrder = 'asc';
		$("#sortByPulianEvaluate").attr("class","sortAsc");
	}else{
		pulianSortOrder = 'desc';
		$("#sortByPulianEvaluate").attr("class","sortDesc");
	}
	pulianSortName = 'average_score';
	searchOfPulian();
}

/**
 * 按学习人数排序（普连）
 */
function sortByPulianStudentNum(){
	if(pulianSortName == 'student_num' && pulianSortOrder == 'desc'){
		pulianSortOrder = 'asc';
		$("#sortByPulianStudentNum").attr("class","sortAsc");
	}else{
		pulianSortOrder = 'desc';
		$("#sortByPulianStudentNum").attr("class","sortDesc");
	}
	pulianSortName = 'student_num';
	searchOfPulian();
}

/**
 * 每页显示20条数据
 */
function changePulianPagesizeOfTwenty(){
	$("#pulianPageSizeTwenty").addClass("first");
	$("#pulianPageSizeFourty").removeClass("first");
	$("#pulianPageSizeOneHundred").removeClass("first");
	$("#pulianPageSizeTwoHundred").removeClass("first");
	pulianItemsPerPage = 20;
	searchOfPulian();
}

/**
 * 每页显示40条数据
 */
function changePulianPagesizeOfFourty(){
	$("#pulianPageSizeTwenty").removeClass("first");
	$("#pulianPageSizeFourty").addClass("first");
	$("#pulianPageSizeOneHundred").removeClass("first");
	$("#pulianPageSizeTwoHundred").removeClass("first");
	pulianItemsPerPage = 40;
	searchOfPulian();
}

/**
 * 每页显示100条数据
 */
function changePulianPagesizeOfOneHundred(){
	$("#pulianPageSizeTwenty").removeClass("first");
	$("#pulianPageSizeFourty").removeClass("first");
	$("#pulianPageSizeOneHundred").addClass("first");
	$("#pulianPageSizeTwoHundred").removeClass("first");
	pulianItemsPerPage = 100;
	searchOfPulian();
}

/**
 * 每页显示200条数据
 */
function changePulianPagesizeOfTwoHundred(){
	$("#pulianPageSizeTwenty").removeClass("first");
	$("#pulianPageSizeFourty").removeClass("first");
	$("#pulianPageSizeOneHundred").removeClass("first");
	$("#pulianPageSizeTwoHundred").addClass("first");
	pulianItemsPerPage = 200;
	searchOfPulian();
}

/**普联云平台课程end*/

/**
 * 展示企业课程
 */
function showCompanyCourses(){
	$("#companyCourses").css("color","red");
	$("#pulianCourses").css("color","black");
	$("#companyCoursesDiv").show();
	$("#pulianCoursesDiv").hide();
	$("#Ul_1").show();
	$("#pulianCategory").hide();
}

/**
 * 展示云平台课程
 */
function showPulianCourses(){
	$("#companyCourses").css("color","black");
	$("#pulianCourses").css("color","red");
	$("#companyCoursesDiv").hide();
	$("#pulianCoursesDiv").show();
	$("#Ul_1").hide();
	$("#pulianCategory").show();
}

/**
 * 查询全部课程
 */
function searchAllCourses(){
	if(showType == 'pics'){
		searchCategoryId = '';
		searchCategoryType = '';
		searchContent = '';
		getSearchResults();
	}else{
		$("#listShowTable").mmGrid().load();
	}
}
</script>
</head>
<body>
	
	<div id="course_all">
		<div class="select_cr">
			<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
				<img src="/ELN/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"> 
				<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">选课中心</span>
			</div>
			<!-- <h3>全部课程</h3> -->
			<div class="course_cs">
				<!-- <h4 style="width:80px;"><a id="companyCourses" onclick="showCompanyCourses();" style="color:red;">企业课程</a></h4>
				<h4 style="width:80px;padding-left:30px;"><a id="pulianCourses" onclick="showPulianCourses();">云平台课程</a></h4>
				<ul id="Ul_1" style="width: 1036px;"></ul>
				<ul id="pulianCategory" style="width: 1036px;"></ul> -->
			
				<h2 id="courseType"><a onclick="searchAllCourses();">全部课程分类</a></h2>
				<ul id="proinfo" class="prosul clearfix" style="display:none;">
					<!-- <li>
						<em>&gt;</em>
					    <a href="javascript:void(0)" class="ti">施工违法项</a>
					    <div class="prosmore hide">
					       <ul>
					       	 <li>
					       	 	<a>施工标准<em>&gt;</em></a>
					       	 	<ul>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 	</ul>
					       	 </li>
					       	 <li>
					       	 	<a>违法施工<em>&gt;</em></a>
					       	 	<ul>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 	</ul>
					       	 </li>
					       	 <li>
					       	 	<a>施工优化<em>&gt;</em></a>
					       	 	<ul>
					       	 		<li><a>施工管理标准1</a></li>
					       	 		<li><a>施工管理标准1</a></li>
					       	 	</ul>
					       	 </li>
					       </ul>
					     </div>
				     </li> -->
				</ul>
				
			</div>
			
			<!-- 公司课程 -->
			<div id="companyCoursesDiv">
				<div class="search_1">
					<input id="searchContent" type="text" class="txt_1" /> 
					<input type="button" class="btn_4" value="查询" onclick="searchByContent();" /> 
					<input id="showByPicsButton" type="button" class="btn_5" value="大图" onclick="showByPics();" /> 
					<input id="showByListButton" type="button" class="btn_6" value="列表" onclick="showBylist();" />
					<div class="sort_1 fr">
						<a id="sortByTime" class="sortDesc" onclick="sortByTime();">按时间</a> 
						<a id="sortByEvaluate" class="sortDesc" onclick="sortByEvaluate();">按评分</a> 
						<a id="sortByStudentNum" class="sortDesc" onclick="sortByStudentNum();">按学习人数</a>
					</div>
				</div>
			
				<!-- 图片展现 -->
				<div id="picsShow">
					<div class="course_list1 clear">
						<ul id="cs_list">
						</ul>
					</div>
					<div class="page clear">
						<!-- 每页显示数量栏 -->
						<div class="left_page fl">
							<a id="pageSizeTwenty" onclick="changePagesizeOfTwenty();" class="first">20</a> 
							<a id="pageSizeFourty" onclick="changePagesizeOfFourty();">40</a> 
							<a id="pageSizeOneHundred" onclick="changePagesizeOfOneHundred();">100</a>
							<a id="pageSizeTwoHundred" onclick="changePagesizeOfTwoHundred();">200</a>
						</div>
						<!-- 分页栏 -->
						<div id="picsPager" class="right_page fr">
						</div>
					</div>
				</div>
			
				<!-- 页面无结果显示（图片展现） -->
				<div id="noResults" style="display: none; text-align: center; margin-top: 120px;">
					暂无结果
				</div>
			
				<!-- mmGrid展现 -->
				<div class="clear_both"></div>
				<div id="listShow">
					<table id="listShowTable"></table>
					<!-- 每页显示数量栏 -->
					<div id="listPager" style="text-align: right;"></div>
				</div>
			</div>
			
			<!-- 普联课程 -->
			<!-- <div id="pulianCoursesDiv">
				<div class="search_1">
					<input id="pulianSearchContent" type="text" class="txt_1" /> 
					<input type="button" class="btn_4" value="查询" onclick="searchByPulianContent();" /> 
					<input id="showByPulianPicsButton" type="button" class="btn_5" value="大图" onclick="showByPulianPics();" /> 
					<input id="showByPulianListButton" type="button" class="btn_6" value="列表" onclick="showByPulianlist();" />
					<div class="sort_1 fr">
						<a id="sortByPulianTime" class="sortDesc" onclick="sortByPulianTime();">按时间</a> 
						<a id="sortByPulianEvaluate" class="sortDesc" onclick="sortByPulianEvaluate();">按评分</a> 
						<a id="sortByPulianStudentNum" class="sortDesc" onclick="sortByPulianStudentNum();">按学习人数</a>
					</div>
				</div>
			
				图片展现
				<div id="pulianPicsShow">
					<div class="course_list1 clear">
						<ul id="pulian_cs_list">
						</ul>
					</div>
					<div class="page clear">
						每页显示数量栏
						<div class="left_page fl">
							<a id="pulianPageSizeTwenty" onclick="changePulianPagesizeOfTwenty();" class="first">20</a> 
							<a id="pulianPageSizeFourty" onclick="changePulianPagesizeOfFourty();">40</a> 
							<a id="pulianPageSizeOneHundred" onclick="changePulianPagesizeOfOneHundred();">100</a>
							<a id="pulianPageSizeTwoHundred" onclick="changePulianPagesizeOfTwoHundred();">200</a>
						</div>
						分页栏
						<div id="pulianPicsPager" class="right_page fr">
						</div>
					</div>
				</div>
			
				页面无结果显示（图片展现）
				<div id="pulianNoResults" style="display: none; text-align: center; margin-top: 120px;">
					暂无结果
				</div>
			
				mmGrid展现
				<div class="clear_both"></div>
				<div id="pulianListShow">
					<table id="pulianListShowTable"></table>
					每页显示数量栏
					<div id="pulianListPager" style="text-align: right;"></div>
				</div>
			</div> -->
			
		</div>
	</div>
</body>
</html>