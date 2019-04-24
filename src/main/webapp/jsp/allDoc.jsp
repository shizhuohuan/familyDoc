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
    <title>搜索</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/dptDetail/nprogress.css">
    <link rel="stylesheet" type="text/css" href="../static/css/dptDetail/style.css">
    <link rel="stylesheet" type="text/css" href="../static/css/dptDetail/font-awesome.min.css">
    <script src="../static/js/dptDetail/jquery-2.1.4.min.js"></script>
    <script src="../static/js/dptDetail/nprogress.js"></script>
    <script src="../static/js/dptDetail/jquery.lazyload.min.js"></script>
    <!--[if gte IE 9]>
    <script src="../static/js/dptDetail/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="../static/js/dptDetail/html5shiv.min.js" type="text/javascript"></script>
    <script src="../static/js/dptDetail/respond.min.js" type="text/javascript"></script>
    <script src="../static/js/dptDetail/selectivizr-min.js" type="text/javascript"></script>
    <![endif]-->
</head>
<body class="user-select">
<header class="header">
    <nav class="navbar navbar-default" id="navbar">
        <div class="container">
            <div class="collapse navbar-collapse" id="header-navbar">

            </div>
        </div>
        </div>
    </nav>
</header>
<section class="container">
    <div class="content-wrap">
        <div class="content" id="docContent">
            <div class="title">
                <h3>医生信息</h3>
            </div>
            <c:if test="${empty allDoc}">
                <img src="../static/images/noData.jpg">
            </c:if>
            <c:forEach var="doc" items="${allDoc}" varStatus="status">
                <article class="excerpt excerpt-1" style="">
                    <div style="margin-left: -130px ">
                        <h2><a href="javascript:docDetail('${doc.userId}')"><c:out value="${doc.relName}" escapeXml="false"/></a>
                        </h2>
                        <p class="meta">
                            <time class="time"><i class="glyphicon glyphicon-time"></i>
                                <c:if test="${doc.sex == '1'}">男</c:if>  <c:if test="${doc.sex == '0'}">女</c:if></time>
                            <span class="comment" title="年龄" target="_blank">
                                <i class="glyphicon glyphicon-comment"></i> <c:out value="${doc.age}" escapeXml="false"/>岁
                            </span>
                        </p>
                        <p class="meta">
                            <time class="time"><i class="glyphicon glyphicon-time"></i>
                                <c:out value="${doc.hospital}" escapeXml="false"/></time>
                            <span class="comment" title="职称" target="_blank">
                                <i class="glyphicon glyphicon-comment"></i> <c:out value="${doc.level}" escapeXml="false"/>
                            </span>
                        </p>
                        <p class="note"><c:out value="${doc.address} " escapeXml="false"/></p>
                        <hr>
                        <p class="note"><c:out value="${doc.produce}" escapeXml="false"/></p>
                    </div>
                </article>
            </c:forEach>
        </div>
    </div>
    <aside class="sidebar">
        <div class="widget widget-tabs">

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane contact active" id="notice">
                    <br><br>
                    <h2>命中数量:
                        <span id="hitCount"><c:out value="${fn:length(allDoc)}"/></span>
                    </h2>
                </div>
            </div>
        </div>
        <div class="widget widget_search">
            <form class="navbar-form" action="" method="post">
                <div class="input-group">
                    <input type="text" id="docKeyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off">
                    <span class="input-group-btn">
		<button class="btn btn-default btn-search" id="docSearch" type="button">搜索</button>
		</span> </div>
            </form>
        </div>
    </aside>
</section>
<div id="docSearchDialog" title="医生资料" style="display: none">
    <h3 style="text-align: center" id="docH3"></h3><br>
    <p class="text-primary" style="text-align: center" ><strong><span id="docSex"></span>&nbsp;&nbsp;&nbsp;<span id="docAge"></span>岁</strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="docHospital"></span>&nbsp;&nbsp;&nbsp;<span id="docDpt"></span></strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="docAddress"></span></strong></p><br>
    <p class="text-info" style="text-align: center" ><strong><span id="docProduce"></span></strong></p>
    <hr>
    <button type="button" id="chatDoc" class="btn btn-primary" style="margin-left: 40%;">联系医生</button>
</div>
<script type="text/javascript" src="../js/index/search.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
</body>
</html>
