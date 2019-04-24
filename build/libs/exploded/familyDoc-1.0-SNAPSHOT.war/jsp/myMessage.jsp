<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/21
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <title>我的消息</title>

    <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.css">

    <script type="text/javascript" language="javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" language="javascript" src="http://cdn.datatables.net/1.10-dev/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" language="javascript" src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>
</head>
<body>
<!--第二步：添加如下 HTML 代码-->
<div style="width: 90%;margin: auto">
    <table id="messageTable" class="table table-display table-bordered">
    </table>
</div>

</body>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
<script type="text/javascript" src="../js/index/myMessage.js"></script>
</html>
