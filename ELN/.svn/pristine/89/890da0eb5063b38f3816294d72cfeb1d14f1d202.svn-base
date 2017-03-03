<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>我的资讯</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

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


<jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
<jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css"/>

<style type="text/css">
.tab_2 th, td{
	height:auto;
}
</style>

<script type="text/javascript">

//var listTeacherBean = ${listTeacherBean}; //部门

$(function(){
	//表格
	$('#exampleTable').mmGrid({
    	root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
		url:"<%=request.getContextPath()%>/information/getInfoListByMap.action",
    	width: 'auto',
    	height: 'auto',
    	fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
    	showBackboard: false,
 		checkCol: false,
        multiSelect: true,
        indexCol: false,  //索引列
        params:function(){
        	var param = new Object();
        	param.roleType = 2;//1代表管理端的列表   2 代表学员端的列表 
        	param.userName = $.trim($("[name='userName']").val());
        	param.infoName = escapeForSql($.trim($("[name='infoName']").val()));
        	if($.trim($("[name='beginTime']").val())!=""){
	        	param.beginTime= $.trim($("[name='beginTime']").val());
        	}
        	if($.trim($("[name='endTime']").val())){
        		param.endTime= $.trim($("[name='endTime']").val());
        	}

        	return param;
        },
        cols: [{title: 'ID', align:'center',name: 'infoId', width:60, hidden:true},
			   {title: '资讯名称', align:'center',name: 'infoName', width:50,renderer:function(val, item, rowIndex){
				   
				   return "<span onclick='detail("+item.infoId+")'><a href='javascript:void'>"+val+"</a></span>";
			   }},
			   {title: '创建人', align:'center',name: 'userName', width:50},
			   {title: '创建时间', align:'center',name: 'createTime', width:50,renderer:function(val, item, rowIndex){
				   var newDate = new Date(val);
				   return formatDate(newDate);
			   }}
           ],
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
	
	  //格式化日期,
    function formatDate(date,format){
      var paddNum = function(num){
        num += "";
        return num.replace(/^(\d)$/,"0$1");
      }
      //指定格式字符
      var cfg = {
         yyyy : date.getFullYear() //年 : 4位
        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
        ,d  : date.getDate()   //日 : 如果1位的时候不补0
        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
        ,hh :  paddNum(date.getHours())  //时
        ,mm :  paddNum(date.getMinutes()) //分
        ,ss : paddNum(date.getSeconds()) //秒
      }
      format || (format = "yyyy-MM-dd hh:mm:ss");
      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    } 
	  
    function initDatepicker() {
    	$("#beginTime").datepicker({
    		dateFormat : 'yy-mm-dd',
   		  changeMonth: true,
   	      changeYear: true,
   			 onClose: function( selectedDate ) {
   		        $( "#endTime" ).datepicker( "option", "minDate", selectedDate );
   		      }
    	});

    	$("#endTime").datepicker({
    		dateFormat : 'yy-mm-dd',
    		changeMonth: true,
     	      changeYear: true,
   			 onClose: function( selectedDate ) {
   		        $( "#beginTime" ).datepicker( "option", "maxDate", selectedDate );
   		      }
    	});
    }
    initDatepicker();
});

//查询
function search(){
	$('#exampleTable').mmGrid().load({"page":1});
}

//重置
function reset(){
	
	$("[name='userName']").val("")
	$("[name='infoName']").val("");
    $("[name='beginTime']").val("");
    $("[name='endTime']").val("");
    search()
}


//详情
function detail(id){
	
	 if(id){
	   	location = "<%=request.getContextPath()%>/information/toStuDetail.action?infoId="+id;
	 }
}

</script>
</head>
<body style="overflow-x:hidden;">
 <div id="content_i" class='content'>	
	<div>
		<!-- <h3 style="background: none;">我的资讯</h3> -->
		<div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
			<img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="history.go(-1);"/> 
			<span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">我的资讯</span>
		</div>
	</div>

	<div class="search_2">
    	<p>
        	资讯名称：
            <input type="text" name='infoName'>
            创建人：
            <input type="text" name='userName'>
            创建时间：
           	<input type="text" id='beginTime' name='beginTime'>
           	至
           	<input type="text" id='endTime' name='endTime'>

        </p>
        <input type="button" value="重置" onclick='reset()'>
        <input type="button" value="查询" class="btn_cx" onclick='search()'>

    </div>
	<div class="first_div" style="border-right:none;border-bottom:none;border-left:none;margin:0px">
			
		<div style="margin:3px 10px 10px 10px;"></div>
		
		<div id="exampleTable" style="margin-top:10px;" ></div>
		<div id="paginator11-1" align="right" style="margin-right:10px;" ></div>
	</div>
</div>
<div id="add_iframe" style="display:none;width:100%;height:100%;">
	<iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>
	
</body>
</html>
