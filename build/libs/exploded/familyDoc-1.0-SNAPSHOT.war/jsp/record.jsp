<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人资料</title>
    <link rel="stylesheet" type="text/css" href="../static/css/data/reset-min.css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet" type="text/css">
    <link href="../static/css/data/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<h3 class="demo" id="demo">个人资料</h3>
<form id="myform">
    <p><label for>主诉</label>
        <textarea name="talk" data-helper="主诉" placeholder="请详细说明您的症状、感受"></textarea>
    </p>
    <p><label for>现病史</label>
        <textarea name="currentIll" data-helper="现病史" placeholder="请填写您的起病情况、患病的时间、病情演变、症状特点"></textarea>
    </p>
    <p class="left"><label for>科室</label>
        <select name="dpt">
            <option value="-1">请选中</option>
        </select>
    </p>
    <p class="right" ><label for>医生</label>
        <select name="doc">
            <option value="-1">请选择科室</option>
        </select>
    </p>
    <span style="color: red">说明：</span><br>
    <p>
        1.发病情况。发病的时间、地点环境，发病的缓急，发病的诱因或原因，主要症状的部位、性质、持续时间和程度。<br><br>
        2.病情演变和诊断治疗经过。<br><br>
        3.一般症状。如患病后的精神状态、恶寒、寒战、发热、出汗(自汗或盗汗)、头身有无不适，以及体力、食欲、食量、睡眠、大小便、体重等的改变。<br>
    </p>
    <p><input type="button" id="commit" class="button" name value="提交" style="margin: auto">
    </p>
</form>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/data/js.js"></script>
<script type="text/javascript" src="../js/index/record.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
</body>
</html>