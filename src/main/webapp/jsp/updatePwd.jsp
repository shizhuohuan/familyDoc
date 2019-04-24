<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" type="text/css" href="../static/css/data/reset-min.css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet" type="text/css">
    <link href="../static/css/data/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<h3 class="demo" id="demo">修改密码</h3>
<form id="myform">
        <p><label for>新密码</label>
            <input data-progression type="text" data-helper="新密码" name="pwd">
        </p>
        <p><label for>确认新密码</label>
            <input data-progression type="text" data-helper="新密码" name="confirm_pwd">
        </p>
    <p><input type="button" id="commit" class="button" name value="修改" style="margin: auto">
    </p>
</form>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/data/js.js"></script>
<script type="text/javascript" src="../js/index/pwdUpdate.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
</body>
</html>