<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>学习计划管理</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sys.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/teacher.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxSessionOut.js" ></script>
    <!-- mmGrid -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmGrid.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmGrid.js" ></script>
    <!-- mmGrid page -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/mmGrid/mmPaginator.js" ></script>
    <!-- artDialog -->
    <jsp:include page="../common/includeArtDialog.jsp"></jsp:include>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common_util.js"></script>
    <!-- 时间处理 -->
    <jsp:include page="../common/dateUtilInclude.jsp"></jsp:include>
    <jsp:include page="../common/jqueryUIInclude.jsp"></jsp:include>


    <script type="text/javascript">


        $(function(){

            initStage();

            //表格
            $('#exampleTable').mmGrid({
                root:'rows',// 和后台数据保存列一致,对应MMGridPageVoBean rows
                url:"<%=request.getContextPath()%>/learnPlan/getLearnPlanStudentProcess.action",
                width: 'auto',
                height: 'auto',
                fullWidthRows: true, //表格第一次加载数据时列伸展，自动充满表格
                showBackboard: false,
                checkCol: true,
                multiSelect: true,
                indexCol: true,  //索引列
                params:function(){
                    var param = new Object();
                    param.userName = $.trim($("#userName").val());
                    param.name = $.trim($("#name").val());
                    param.stageId = $.trim($("#stageId").val());
                    param.depName = $("#depName").val();
                    param.postName = $("#postName").val();
                    param.status = $("#status").val();
                    param.planId=<%=request.getAttribute("planId")%>
                    return param;
                },
                cols: [
                    {title: '用户名', name: 'userName', width:30},
                    {title: '姓名', name: 'name', width:30},
                    {title: '部门', name: 'depName', width:50},
                    {title: '岗位', name: 'postName', width:50},
                    {title: '所处阶段', name: 'stageName', width:40},
                    {title: '当前阶段完成/指派任务', name: 'totalCourse', width:60,renderer:function(val, item, rowIndex){
                        return item.endCourse +"/"+item.totalCourse;
                    }},
                    {title: '当前阶段进度', name: 'totalCourse', width:30,renderer:function(val,item,rowIndex){
                        if(!item.totalCourse||!item.endCourse){
                            return 0+"%";
                        }
                        return (item.endCourse*100/item.totalCourse)+"%";
                    }}
                    ,
                    {title: '是否完成', name: 'status', width:40,renderer:function(val,item,rowIndex){
                        if(val==2){
                            return "已完成";
                        }else{
                            return "未完成"
                        }
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
        });


        //查询
        function search(page){
            if(page){
                $('#exampleTable').mmGrid().load({"page":page});
            }
            else{
                $('#exampleTable').mmGrid().load();
            }

        }
        //重置
        function clearAll(){
            $("#depName").val("");
            $("#userName").val("");
            $("#name").val("");
            $("#postName").val("");
            $("#status").val("");
            $("#stageId").val("");
            search(1);
        }




        function initStage(){
            var stageList = ${stageList}
            if(stageList&&stageList.length>0){
                var $stageId = $('#stageId');
                var htmlStr ='<option value="">全部</option>';
                for(var i =0;i<stageList.length;i++){
                    htmlStr += '<option value="'+stageList[i].id+'">'+stageList[i].name+'</option>'

                }
                $stageId.append(htmlStr)
            }
        }

    </script>
</head>
<body style="overflow-x:hidden;">

<div class="content">
    <!-- <h3>学习计划管理</h3> -->
    <div style="padding-bottom: 5px;border-bottom: 1px solid #cccccc; margin-bottom: 10px;">
        <img src="<%=request.getContextPath() %>/images/back.png" style="cursor: pointer;" onclick="javascript:history.go(-1);"/>
        <span style="  position: relative;top: 5px; color: #3a3a3a; font-size: 16px; font-weight: bold;">查看学员进度</span>
    </div>


    <div class="search_2" style="width:1042px">
        <p>
            用户名：
            <input type="text" id="userName" name="userName" />
            姓名：
            <input type="text" id="name" name="name" />

            部门：<input id="depName"  type="text" />
            岗位：<input id="postName" type="text" />

        </p>
    </div>
    <div class="search_2" style="width:1042px">
        <p>
            所处阶段：
            <select id="stageId" style="width:230px">
            </select>
            是否完成：
            <select id="status" name="status" >
                <option value="">全部</option>
                <option value="1">未完成</option>
                <option value="2">已完成</option>
            </select>


        </p>
        <input type="button" value="重置" onclick='clearAll()'>
        <input type="button" value="查询" class="btn_cx" onclick='search(1)'>

    </div>

    <div id="exampleTable" class="tab_1">

    </div>
    <div id="paginator11-1" align="right" style="margin-right:10px;" ></div>

</div>

<div id="add_iframe" style="display:none;width:100%;height:100%;">
    <iframe frameborder="0" src="" style="width:100%;height:100%;" ></iframe>
</div>

</body>
</html>
