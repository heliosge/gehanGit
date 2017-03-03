<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>首页</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>

<style type="text/css">
	*{margin:0;padding:0;}
	.con{margin:16px auto;width:1048px;}
	#home-menu:after{content:" "; display:block; height:0; clear:both;}
	#home-menu{*zoom:1;width:100%;overflow:hidden;}
	#home-menu .m-l,
	#home-menu .m-c{float:left;}
	#home-menu .m-r{float:right;}
	#home-menu .m-l,
	#home-menu .m-r{width:256px;}
	#home-menu .m-c{width:516px;}
	#home-menu .m-l{margin-right:10px;}
	#home-menu a{display:block;height:180px;color:#fff;text-decoration:none;position:relative;transition: all .3s;-moz-transition: all .3s;-webkit-transition: all .3s;-o-transition: all .3s;}
	#home-menu .link-01{background:#67c4ff;margin-bottom:10px;}
	#home-menu .link-01:hover{background:#4cb9ff;}
	#home-menu .link-02{background:#ff6867;margin-bottom:10px;}
	#home-menu .link-02:hover{background:#ff4040;}
	#home-menu .link-03{background:#bd67fe;margin-bottom:10px;}
	#home-menu .link-03:hover{background:#b552ff;}
	#home-menu .link-04{background:#8f67fe;}
	#home-menu .link-04:hover{background:#8053ff;}
	#home-menu .link-05{background:#ff6600;}
	#home-menu .link-05:hover{background:#ff4800;}
	#home-menu .link-06{background:#01d42d;}
	#home-menu .link-06:hover{background:#00be28;}
	#home-menu .link-07{background:#fedd42;}
	#home-menu .link-07:hover{background:#ffd300;}
	#home-menu .link-08{background:#f0179d;}
	#home-menu .link-08:hover{background:red;}
	#home-menu .link-02,
	#home-menu .link-03,
	#home-menu .link-05,
	#home-menu .link-06{width:253px;}
	#home-menu .link-02,
	#home-menu .link-05{float:left;}
	#home-menu .link-03,
	#home-menu .link-06{float:right;}
	#home-menu .m-c div{margin-bottom:10px;clear:both;padding-left:336px;background:url(<%=request.getContextPath()%>/images/img/home-menu-pic02.jpg) 0 0 no-repeat;}
	#home-menu a strong{position:absolute;left:20px;bottom:20px;font:18px/18px "微软雅黑";}
	#home-menu .link-04 strong{right:20px;bottom:40px;text-align:center;}
	#home-menu a span{display:block;position:absolute;top:50px;left:50%;margin-left:-26px;width:52px;height:52px;background:url(<%=request.getContextPath()%>/images/img/home-menu-icon.png) no-repeat;}
	#home-menu a span i{position:absolute;right:-10px;top:-10px;display:block;background:#f00;border:2px solid #fff;color:#fff;text-align:center;font-style:normal;font-size:12px;line-height:20px;padding:0 6px;-moz-border-radius:20px;-o-border-radius:20px;border-radius:20px;}
	#home-menu .link-01 span{background-position:-104px 0;}
	#home-menu .link-02 span{background-position:-52px 0;}
	#home-menu .link-03 span{background-position:-104px 0;}
	#home-menu .link-04 span{background-position:-156px 0;}
	#home-menu .link-05 span{background-position:0 -52px;}
	#home-menu .link-06 span{background-position:-52px -52px;}
	#home-menu .link-07 span{background-position:-104px -52px;}
	#home-menu .pic{width:100%;height:370px;}
	#home-menu .m-l .pic{margin-top:10px;background:url(<%=request.getContextPath()%>/images/img/home-menu-pic01.jpg) 0 0 no-repeat;}
	#home-menu .m-r .pic{margin-bottom:10px;background:url(<%=request.getContextPath()%>/images/img/home-menu-pic03.jpg) 0 0 no-repeat;}
</style>

<script type="text/javascript">

</script>
</head>

<body style="background-color: #e0eaeb">
	<div class="con">
        <div id="home-menu">
            <div class="m-l">
            	
                 <a href="<%=request.getContextPath()%>/courseUserAction/toMyCourses.action" class="link-01"><span><i>${courseCount }</i></span><strong>我的课程</strong></a>
                <%-- <a href="<%=request.getContextPath()%>/knowledgeLibraryInfo/toAccident.action" class="link-08"><span><i>${accidentCount }</i></span><strong>事故案例分析</strong></a> --%>
                <a href="<%=request.getContextPath()%>/studentLearnPlanAction/toLearnPlan.action" class="link-08"><span><i>${studyPlanCount }</i></span><strong>学习计划</strong></a>
                <div class="pic" style="height:180px;">
                	<img src="<%=request.getContextPath()%>/images/img/home-menu-pic01.jpg" width="100%" height="180px"/>
                </div>
            </div>
            <div class="m-c">
            	<a href="<%=request.getContextPath()%>/myExercise/gotoMyExercise.action" class="link-02"><span><i>${lxCount }</i></span><strong>自我练习</strong></a>
                <a href="<%=request.getContextPath()%>/myExamManage/gotoMyExam.action" class="link-03"><span><i>${examCount }</i></span><strong>我的考试</strong></a>
                <div>
                    <a href="<%=request.getContextPath()%>/MyMegagame/toMyCompetition.action" class="link-04"><span><i>${gameCount }</i></span><strong>我的比赛</strong></a>
                </div>
                <a href="<%=request.getContextPath()%>/courseQuestionAction/toMyQuestions.action" class="link-05"><span><i>${questionCount }</i></span><strong>我的问问</strong></a>
                <%-- <a href="<%=request.getContextPath()%>/studentGroupAction/toJoinGroup.action" class="link-06"><span><i>${groupCount }</i></span><strong>我的群组</strong></a> --%>
            	<a href="<%=request.getContextPath()%>/train/toMyTrain.action" class="link-06"><span><i>${trainCount }</i></span><strong>我的培训</strong></a>
            </div>
            <div class="m-r">
                <div class="pic"></div>
                <a href="<%=request.getContextPath()%>/knowledgeLibraryInfo/toKnowledgeCenter.action" class="link-07"><span><i>${knowledgeCount }</i></span><strong>我的知识</strong></a>
            </div>
        </div>
    </div>
	
</body>

</html>
