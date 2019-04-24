<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <c:if test="${sessionScope.currenUser.identity != '1'}">
        <p><label for>姓名</label>
            <input data-progression type="text" data-helper="姓名" name="relName" value="${userData.relName}">
        </p>
        <p><label for>身份证号码</label>
            <input data-progression type="text" data-helper="身份证号码" name="idcard" value="${userData.idcard}">
        </p>
        <p class="left"><label for>年龄</label>
            <input data-progression type="text" data-helper="年龄" name="age" value="${userData.age}">
        </p>
        <p class="right"><label for>职业</label>
            <input data-progression type="text" data-helper="职业" name="profession" value="${userData.profession}">
        </p>
        <p><label for>现居地址</label>
            <input data-progression type="text" data-helper="现居地址" name="address" value="${userData.address}">
        </p>
        <p class="left"><label for>是否已婚</label>
            <select name="marray" >
                <option value="-1" >请选中</option>
                <option value="1" <c:if test="${userData.marray == '1'}">selected="selected"</c:if> >是</option>
                <option value="0" <c:if test="${userData.marray == '0'}">selected="selected"</c:if>  >否</option>
            </select>
        </p>
        <p class="right"><label for>性别</label>
            <select name="sex" >
                <option value="-1" >请选中</option>
                <option value="1" <c:if test="${userData.sex == '1'}">selected="selected"</c:if> >男</option>
                <option value="0" <c:if test="${userData.sex == '0'}">selected="selected"</c:if> >女</option>
            </select>
        </p>
        <p><label for>药物过敏</label>
            <textarea  name="allergic" data-helper="Either the helper text or progress bar can be turned off"><c:out value="${userData.allergic}"></c:out></textarea>
        </p>
    </c:if>
    <c:if test="${sessionScope.currenUser.identity == '1'}">
        <p><label for>姓名</label>
            <input data-progression type="text" data-helper="姓名" name="relName" value="${userData.relName}">
        </p>
        <p><label for>身份证号码</label>
            <input data-progression type="text" data-helper="身份证号码" name="idcard" value="${userData.idcard}">
        </p>
        <p class="left"><label for>年龄</label>
            <input data-progression type="text" data-helper="年龄" name="age" value="${userData.age}">
        </p>
        <p class="right"><label for>所属医院</label>
            <input data-progression type="text" data-helper="所属医院" name="hospital" value="${userData.hospital}">
        </p>
        <p><label for>所在地址</label>
            <input data-progression type="text" data-helper="所在地址" name="address" value="${userData.address}">
        </p>
        <p class="left"><label for>所属部门</label>
            <select name="dptId" >
                <option value="-1" >请选中</option>
                <c:forEach var="dpt"   items="${dpt}" varStatus="status">
                    <option value="${dpt.id}" <c:if test="${userData.dptId == dpt.id}">selected="selected"</c:if> ><c:out value="${dpt.dpName}"></c:out></option>
                </c:forEach>
            </select>
        </p>
        <p class="right"><label for>性别</label>
            <select name="sex" >
                <option value="-1" >请选中</option>
                <option value="1" <c:if test="${userData.sex == '1'}">selected="selected"</c:if> >男</option>
                <option value="0" <c:if test="${userData.sex == '0'}">selected="selected"</c:if> >女</option>
            </select>
        </p>
        <p><label for>职称</label>
            <input data-progression type="text" data-helper="职称" name="level" value="${userData.level}">
        </p>
        <p><label for>个人简介</label>
            <textarea name="produce" data-helper="个人简介"><c:out value="${userData.produce}"></c:out></textarea>
        </p>
    </c:if>
    <p><input type="button" id="commit" class="button" name value="提交修改" style="margin: auto">
    </p>
</form>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/data/js.js"></script>
<script type="text/javascript" src="../js/index/myData.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
</body>
</html>