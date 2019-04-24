<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>提问</title>
    <link rel="stylesheet" type="text/css" href="../static/css/data/reset-min.css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet" type="text/css">
    <link href="../static/css/data/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<h3 class="demo" id="demo">提问</h3>
<form id="myform">
    <p><label for>咨询标题（必填）</label>
        <input data-progression type="text" data-helper="标题" name="title" placeholder="一句话描述您想要提问的问题，便于医生快速关注您的提问">
    </p>
    <p><label for>疾病介绍（必填）</label>
        <textarea  type="text" data-helper="内容" id="content"
                   placeholder="请详细描述患者的主要症状、发病时间、治疗经过、病情变化、想得到怎样的帮助（10-500个字）"></textarea>
    </p>
    <p ><label for>指定科室（选填）</label>
        <select id="dptId" >
            <option value="" >请选中</option>
            <c:forEach var="dpt"   items="${dpt}" varStatus="status">
                <option value="${dpt.id}"><c:out value="${dpt.dpName}"></c:out></option>
            </c:forEach>
        </select>
    </p>
    <p>
        <span style="color: red">示例：</span><br>

        咨询标题：胸痛、胸闷、伴大汗怎么回事？<br><br>

        症状描述：10小时前突然出现胸痛、胸闷、伴大汗、疼痛向左肩背部放射。<br><br>

        治疗经过：含服硝酸甘油后症状持续不缓解。<br><br>

        检查情况：心率104次/分，心尖部可闻及舒张期奔马律，心电图v1~4导联可见病理性Q波，T波双向。</p>
    <p><input type="button" id="commit" class="button" name value="提交" style="margin: auto">
    </p>
</form>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/data/js.js"></script>
<script type="text/javascript" src="../js/index/quession.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
</body>
</html>