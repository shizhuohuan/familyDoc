<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>个人资料</title>
    <link rel="stylesheet" type="text/css" href="../static/css/data/reset-min.css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet" type="text/css">
    <link href="../static/css/data/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<h3 id="demo" style="text-align: center;"><c:out value="${record.userId}"></c:out>-病历单</h3>
<hr style="border: 1px solid #B3D7FF">
<form id="myform">
    <p><label for>主诉</label>
        <span id="talk"><c:out value="${record.talk}"></c:out></span>
    </p>
    <hr style="border: 1px solid #B3D7FF">
    <p><label for>现病史</label>
        <span id="currentIll"><c:out value="${record.currentIll}"></c:out></span>
    </p>
    <hr style="border: 1px solid #B3D7FF">
    <p class="left"><label for>科室</label>
        <span id="dpt"><c:out value="${record.deptId}"></c:out></span>
    </p>
    <p class="right"><label for>医生</label>
        <span id="doc"><c:out value="${record.docId}"></c:out></span>
    </p>
    <hr style="border: 1px solid #B3D7FF">
    <p><input type="button" id="detail" class="button" name value="详细资料" style="margin: auto">
    <c:if test="${sessionScope.currenUser.id == userId}">
        <p><label for>诊断</label>
            <c:if test="${empty record.diagnose}">暂无回答</c:if>
            <c:out value="${record.diagnose}"></c:out>
        </p>
        <hr style="border: 1px solid #B3D7FF">
        <p><label for>建议</label>
            <c:if test="${empty record.diagnose}">暂无回答</c:if>
            <c:out value="${record.docAdvice}"></c:out>
        </p>
        <hr style="border: 1px solid #B3D7FF">
        <p><label for>备注</label>
            <c:if test="${empty record.diagnose}">暂无回答</c:if>
           <c:out value="${record.remark}"></c:out>
        </p>
        <hr style="border: 1px solid #B3D7FF">
    </c:if>
    <c:if test="${sessionScope.currenUser.id == docId}">
        <input type="hidden" value="${record.id}" name="rid">
        <p><label for>诊断</label>
            <textarea name="diagnose" data-helper="诊断"><c:out value="${record.diagnose}"></c:out></textarea>
        </p>
        <p><label for>建议</label>
            <textarea name="docAdvice" data-helper="建议"><c:out value="${record.docAdvice}"></c:out></textarea>
        </p>
        <p><label for>备注</label>
            <textarea name="remark" data-helper="备注"><c:out value="${record.remark}"></c:out></textarea>
        </p>
        <p class="left"><input type="button" id="commit" class="button" name value="提交" style="margin: auto">
        </p>
    </c:if>
    <p class="right"><input type="button" id="back" class="button" name value="返回" style="margin: auto">
    </p>
</form>
<div id="detailDialog" title="详细资料" style="display: none">
    <h4 style="text-align: center" id="docH3"><c:out value="${userData.relName}"></c:out></h4>
    <p class="text-primary" style="text-align: center" ><strong><span id="pSex"><c:if test="${userData.sex == '0'}">女</c:if><c:if test="${userData.sex == '1'}">男</c:if></span>&nbsp;&nbsp;&nbsp;
        <span id="pAge"><c:out value="${userData.age}"></c:out></span>岁</strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="pMarray"><c:if test="${userData.marray == '0'}">已婚</c:if><c:if test="${userData.marray == '1'}">未婚</c:if></span>
    </strong>  </p>
    <p  class="text-primary" style="text-align: center"><strong><span id="pProfession">职业：<c:out value="${userData.profession}"></c:out></span></strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="pAddress">地址：<c:out value="${userData.address}"></c:out></span></strong></p>
    <p class="text-info" style="text-align: center" ><strong>药物过敏：<span id="allergic"><c:out value="${userData.allergic}"></c:out></span></strong></p>
</div>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/data/js.js"></script>
<script type="text/javascript" src="../js/index/recordDetail.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</body>
</html>