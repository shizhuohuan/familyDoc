<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/16
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>科室分类</title>
    <link href="../static/css/department/zzsc.css" rel="stylesheet" type="text/css" />
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<header>
    <script type="text/javascript">
        $(function(){
            var tabNum=$(".important").find("li");
            var contentNum=$(".contents").children();
            var timer;
            $(tabNum).each(function(index){
                $(this).hover(function(){
                    var that=$(this)
                    timer=window.setTimeout(function(){
                        $(contentNum).eq(index).css({"display":"block"});
                        $(contentNum).eq(index).siblings().css({"display":"none"});
                        that.find("a").css({"background":"#FFF","color":"#fff"});
                        that.find("strong").show();
                        that.siblings().find("strong").hide();
                        that.siblings().find("a").css({"background":"#fff","color":"black"});
                    },100)
                },function(){
                    window.clearTimeout(timer);
                })
            })
        })
    </script>

    <div class="w1200">
        <div class="title">
            <h1><span>科室</span>分类</h1>
        </div>
        <ul class="important cl" >
            <li class="rcsp_2">
                <a><span></span></a>
                <p>内科</p>
                <strong><i></i></strong>
            </li>
            <li class="rcsp_2">
                <a><span></span></a>
                <p>普外科</p>
                <strong style="display:none"><i></i></strong>
            </li>
            <li class="rcsp_2">
                <a><span></span></a>
                <p>骨科</p>
                <strong style="display:none"><i></i></strong>
            </li>
            <li class="rcsp_2">
                <a><span></span></a>
                <p>外科</p>
                <strong style="display:none"><i></i></strong>
            </li>
        </ul>
        <div class="contents">
            <div class="cl">
                <div class="important_r">
                    <div class="icon_top cl"><i class="i_left"></i><i class="i_right"></i></div>
                    <div class="study" id="dpt_1">
                        <span style="font-weight:bold;font-size:20px;margin-left:30px">
                            <c:forEach var="dpt1"   items="${dpt_1}" varStatus="status">
                                <a href="/familyDoc/department/dptDetail?dptId=${dpt1.id}"><c:out value="${dpt1.dpName}"></c:out></a>&nbsp;&nbsp;&nbsp;
                                <c:if test="${ status.count%3 == 0 }"><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                            </c:forEach>
                        </span>
                    </div>
                    <div class="icon_top cl" style="padding-top:0px;"><i class="i_left1"></i><i class="i_right1"></i></div>
                </div>
            </div>
            <div class="cl" style="display: none">
                <div class="important_r">
                    <div class="icon_top cl"><i class="i_left"></i><i class="i_right"></i></div>
                    <div class="study"  id="dpt_2">
                        <span style="font-weight:bold;font-size:20px;margin-left:30px">
                            <c:forEach var="dpt2"   items="${dpt_2}" varStatus="status">
                                 <a href="/familyDoc/department/dptDetail?dptId=${dpt2.id}"><c:out value="${dpt2.dpName}"></c:out> </a>&nbsp;&nbsp;&nbsp;
                                <c:if test="${ status.count%3 == 0 }"><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                            </c:forEach>
                        </span>
                    </div>
                    <div class="icon_top cl" style="padding-top:0px;"><i class="i_left1"></i><i class="i_right1"></i></div>
                </div>
            </div>
            <div class="cl" style="display: none">
                <div class="important_r">
                    <div class="icon_top cl"><i class="i_left"></i><i class="i_right"></i></div>
                    <div class="study"  id="dpt_3">
                        <span style="font-weight:bold;font-size:20px;margin-left:10px">
                            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <c:forEach var="dpt3"   items="${dpt_3}" varStatus="status">
                                 <a href="/familyDoc/department/dptDetail?dptId=${dpt3.id}"><c:out value="${dpt3.dpName}"></c:out> </a>&nbsp;&nbsp;&nbsp;
                                <c:if test="${ status.count%3 == 0 }"><br><br></c:if>
                            </c:forEach>
                        </span>
                    </div>
                    <div class="icon_top cl" style="padding-top:0px;"><i class="i_left1"></i><i class="i_right1"></i></div>
                </div>
            </div>
            <div class="cl" style="display: none">
                <div class="important_r">
                    <div class="icon_top cl"><i class="i_left"></i><i class="i_right"></i></div>
                    <div class="study"  id="dpt_4">
                        <span style="font-weight:bold;font-size:20px;margin-left:20px">
                            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <c:forEach var="dpt4"   items="${dpt_4}" varStatus="status">
                                 <a href="/familyDoc/department/dptDetail?dptId=${dpt4.id}"><c:out value="${dpt4.dpName}"></c:out> </a>&nbsp;&nbsp;&nbsp;
                                <c:if test="${ status.count%4 == 0 }"><br><br></c:if>
                            </c:forEach>
                        </span>
                    </div>
                    <div class="icon_top cl" style="padding-top:0px;"><i class="i_left1"></i><i class="i_right1"></i></div>
                    <!--div class="study"><a class="know" href="">对客户管理不了解？>点击了解</a></div-->
                </div>
            </div>
        </div>
    </div>
</header>
</body>
<script src="../js/index/department.js"></script>
</html>
