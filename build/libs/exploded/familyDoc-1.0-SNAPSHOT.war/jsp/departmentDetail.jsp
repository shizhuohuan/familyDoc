<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/17
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>科室详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/dptDetail/nprogress.css">
    <link rel="stylesheet" type="text/css" href="../static/css/dptDetail/style.css">
    <link rel="stylesheet" type="text/css" href="../static/css/dptDetail/font-awesome.min.css">
    <link rel="apple-touch-icon-precomposed" href="../static/images/dptDetail/icon.png">
    <link rel="shortcut icon" href="../static/images/dptDetail/favicon.ico">
    <script src="../static/js/dptDetail/jquery-2.1.4.min.js"></script>
    <script src="../static/js/dptDetail/nprogress.js"></script>
    <script src="../static/js/dptDetail/jquery.lazyload.min.js"></script>
    <!--[if gte IE 9]>
    <script src="../static/js/dptDetail/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="../static/js/dptDetail/html5shiv.min.js" type="text/javascript"></script>
    <script src="../static/js/dptDetail/respond.min.js" type="text/javascript"></script>
    <script src="../static/js/dptDetail/selectivizr-min.js" type="text/javascript"></script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script>window.location.href = 'upgrade-browser.html';</script>
    <![endif]-->
    <script src="../js/index/department.js"></script>
</head>
<body class="user-select">
<header class="header">
    <nav class="navbar navbar-default" id="navbar">
        <div class="container">
            <div class="collapse navbar-collapse" id="header-navbar">
                <h2><c:out value="${department.dpName}"/>：</h2>
                <p class="note" style="font-size: 20px"><c:out value="${department.produce}"/></p>
            </div>
        </div>
        </div>
    </nav>
</header>
<section class="container">
    <div class="content-wrap">
        <div class="content">
            <div class="title">
                <h3>网友提问</h3>
            </div>
            <c:if test="${empty quessionOfDpt}">
                <img src="../static/images/noData.jpg">
            </c:if>
            <c:forEach var="qs" items="${quessionOfDpt}" varStatus="status">
                <article class="excerpt excerpt-1" style="">
                    <div style="margin-left: -130px ">
                        <h2><a href="/familyDoc/quession/quessionDetail?qid=${qs.id}" title="${qs.title}"><c:out value="${qs.title}"/></a>
                        </h2>
                        <p class="meta">
                            <time class="time"><i class="glyphicon glyphicon-time"></i> <c:out
                                    value="${qs.createTime}"/></time>
                            <span class="comment" title="评论" target="_blank">
                                <i class="glyphicon glyphicon-comment"></i> 评论<c:out value="${fn:length(qs.answer)}"/>
                            </span>
                        </p>
                        <p class="note"><c:out value="${qs.content}"/></p>
                    </div>
                </article>
            </c:forEach>
        </div>
    </div>
    <aside class="sidebar">
        <div class="widget widget_hot">
            <h3>科室医生</h3>
            <c:if test="${empty docOfDpt}">
                <img src="../static/images/noData.jpg">
            </c:if>
            <ul>
                <c:forEach var="doc" items="${docOfDpt}" varStatus="status">
                    <li>
                        <a title="${doc.id}" href="javascript:docDetail('${doc.userId}');">
                        <span class="text"><c:out value="${doc.relName}"></c:out></span><span class="muted">
                        <i class="glyphicon glyphicon-time"></i><c:if test="${doc.sex == '1'}">男</c:if><c:if test="${doc.sex == '0'}">女</c:if>
			</span><span class="muted"><i class="glyphicon glyphicon-eye-open"></i><c:out value="${doc.age}"></c:out>岁</span></a>
                        <p style="margin-left: 10px">医院：<c:out value="${doc.hospital}"></c:out></p>
                        <p style="margin-left: 10px">职称：<c:out value="${doc.level}"></c:out></p></li>
                    <hr>
                </c:forEach>
            </ul>
        </div>
    </aside>
</section>
<div id="mydialog" title="医生资料" style="display: none">
    <h3 style="text-align: center" id="docH3"></h3><br>
    <p class="text-primary" style="text-align: center" ><strong><span id="docSex"></span>&nbsp;&nbsp;&nbsp;<span id="docAge"></span>岁</strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="docHospital"></span>&nbsp;&nbsp;&nbsp;<span id="docDpt"></span></strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="docAddress"></span></strong></p><br>
    <p class="text-info" style="text-align: center" ><strong><span id="docProduce"></span></strong></p>
    <hr>
    <button type="button" id="chatDoc" class="btn btn-primary" style="margin-left: 40%;" data-docId="">联系医生</button>
</div>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
</body>
<script>

</script>
</html>
