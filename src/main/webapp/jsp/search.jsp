<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/23
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
    <!-- Styles -->
    <link href="../static/css/search/bootstrap.css" rel="stylesheet">
    <link href="../static/css/search/style.css" rel="stylesheet">
    <link rel='stylesheet' id='prettyphoto-css'  href="../static/css/search/prettyPhoto.css" type='text/css' media='all'>
    <link href="../static/css/search/fontello.css" type="text/css" rel="stylesheet">
    <!--[if lt IE 7]>
    <link href="../static/css/search/fontello-ie7.css" type="text/css" rel="stylesheet">
    <![endif]-->
    <!-- Google Web fonts -->
    <link href='http://fonts.googleapis.com/css?family=Quattrocento:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Patua+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
    <link href="../static/css/search/bootstrap-responsive.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- Favicon -->
    <link rel="shortcut icon" href="../static/images/search/favicon.ico">
    <!-- JQuery -->
    <script type="text/javascript" src="../static/js/search/jquery.js"></script>
    <!-- Load ScrollTo -->
    <script type="text/javascript" src="../static/js/search/jquery.scrollTo-1.4.2-min.js"></script>
    <!-- Load LocalScroll -->
    <script type="text/javascript" src="../static/js/search/jquery.localscroll-1.2.7-min.js"></script>
    <!-- prettyPhoto Initialization -->
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function(){
            $("a[rel^='prettyPhoto']").prettyPhoto();
        });
    </script>
</head>
<body>
<!--******************** NAVBAR ********************-->
<div class="navbar-wrapper">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <!-- /.container -->
        </div>
        <!-- /.navbar-inner -->
    </div>
    <!-- /.navbar -->
</div>
<!-- /.navbar-wrapper -->
<div id="top"></div>
<!-- ******************** HeaderWrap ********************-->
<div id="headerwrap">
    <header class="clearfix">
        <h1><span>HELLO!</span> <c:if test="${sessionScope.currenUser.identity == '3'}">您可以通过完善资料来使用本站的更多功能</c:if></h1>
        <div class="container">
            <div class="row">
                <div class="span12">
                    <form action="/familyDoc/search/searchAll">
                    <select id="searchType" name="type" style="width: 100px;height: 35px;text-align: center;margin: auto;background: ghostwhite">
                        <option value="quession" >网友提问</option>
                        <option value="doc">医生资料</option>
                    </select>
                    <input type="text" name="keyword" placeholder="请输入关键词" class="cform-text" size="40" title="search keword">
                    <input type="submit" value="搜索" class="cform-submit" id="searchEsBtn">
                    </form>
                </div>
            </div>
        </div>
    </header>
</div>
<!-- Loading the javaScript at the end of the page -->
<script type="text/javascript" src="../static/js/search/bootstrap.js"></script>
<script type="text/javascript" src="../static/js/search/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="../static/js/search/site.js"></script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>

</body>
</html>
