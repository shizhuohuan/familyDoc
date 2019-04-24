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
    <title>问题广场</title>
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
        <div class="content" id="quessionContent">
            <div class="title">
                <h3>网友提问</h3>
            </div>
            <c:if test="${empty allQuession}">
                <img src="../static/images/noData.jpg">
            </c:if>
            <c:forEach var="qs" items="${allQuession}" varStatus="status">
                <article class="excerpt excerpt-1" style="">
                    <div style="margin-left: -130px ">
                        <h2><a href="/familyDoc/quession/quessionDetail?qid=${qs.id}" ><c:out value="${qs.title}" escapeXml="false"/></a>
                        </h2>
                        <p class="meta">
                            <time class="time"><i class="glyphicon glyphicon-time"></i> <c:out
                                    value="${qs.createTime}"/></time>
                            <span class="comment" title="评论" target="_blank">
                                <i class="glyphicon glyphicon-comment"></i> 评论<c:out value="${fn:length(qs.answer)}"/>
                            </span>
                        </p>
                        <p class="note"><c:out value="${qs.content}" escapeXml="false"/></p>
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
                    <h2>问题总数:<span id="quessionCount">
                        <c:out value="${fn:length(allQuession)}"/></span>
                    </h2>
                </div>
            </div>
        </div>
        <div class="widget widget_search">
            <form class="navbar-form" action="" method="post">
                <div class="input-group">
                    <input type="text" id="quessionKeyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off">
                    <span class="input-group-btn">
		                <button class="btn btn-default btn-search" id="quessionSearch" type="button">搜索</button>
		            </span>
                </div>
            </form>
        </div>
    </aside>
</section>
<script type="text/javascript" src="../js/index/quessionSearch.js"></script>
</body>
</html>
