<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>问题详情</title>
    <link rel="stylesheet" href="../static/css/quession/style.css">
    <link rel="stylesheet" href="../static/css/quession/comment.css">
    <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
</head>
<body>
<div class="commentAll">
    <div class="reviewArea clearfix">
        <h2 class="text-info">
            <small><c:out value="${quession.title}"></c:out></small>
        </h2>
        <p>
            <small><c:out value="${quession.userId}"></c:out>&nbsp;&nbsp;&nbsp;<c:out value="${quession.createTime}"></c:out></small>
        </p>
        <p class="text-muted"><strong><c:out value="${quession.content}"></c:out></strong></p>

    </div>
    <input type="hidden" value="${quession.id}" id="currenQsId">
    <!--评论区域 begin-->
    <c:if test="${sessionScope.currenUser.identity == '1' }">
        <hr>
        <div class="reviewArea clearfix">
        <textarea class="content comment-input" onkeyup="keyUP(this)"></textarea>
            <a href="javascript:;" class="plBtn" data-value="${quession.id}">评论</a>
        </div>
    </c:if>
    <!--评论区域 end-->
    <!--回复区域 begin-->
    <div class="comment-show">
        <input type="hidden" value="${sessionScope.currenUser.id}" id="currenUserId">
        <div class="comment-show-con clearfix">
            <c:if test="${empty answer}">
                <span style="margin: auto;color: red" id="noAnswer">暂无回答</span>
            </c:if>
            <c:forEach var="answer" items="${answer}" varStatus="status">
                <div class="comment-show-con-list pull-left clearfix" id="${answer.id}">
                    <div class="pl-text clearfix">
                        <a href="javascript:docDetail('${answer.userId}')" class="comment-size-name"><c:out value="${answer.docData}"></c:out> : </a>
                        <br>
                        <span class="my-pl-con"><c:out value="${answer.content}"></c:out> </span>
                    </div>
                    <div class="date-dz">
                        <span class="date-dz-left pull-left comment-time"><c:out value="${answer.createTime}"></c:out> </span>
                        <div class="date-dz-right pull-right comment-pl-block">
                            <c:if test="${sessionScope.currenUser.id == answer.userId}">
                                <a href="javascript:deleteAnswer('${answer.id}');" class="date-dz-pl pl-hf hf-con-block pull-left">删除</a>
                            </c:if>
                            <c:if test="${sessionScope.currenUser.id != answer.userId}">
                                <a href="javascript:contactDoc('${answer.userId}');" class="date-dz-pl pl-hf hf-con-block pull-left">联系医生</a>
                            </c:if>
                        </div>
                    </div>
                    <div class="hf-list-con"></div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!--回复区域 end-->
    <button type="button" class="btn btn-primary" onclick="javascrip:history.go(-1);" style="margin: auto">返回</button>
</div>
<div id="docDialog" title="医生资料" style="display: none">
    <h3 style="text-align: center" id="docH3"></h3><br>
    <p class="text-primary" style="text-align: center" ><strong><span id="docSex"></span>&nbsp;&nbsp;&nbsp;<span id="docAge"></span>岁</strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="docHospital"></span>&nbsp;&nbsp;&nbsp;<span id="docDpt"></span></strong></p>
    <p class="text-primary" style="text-align: center" ><strong><span id="docAddress"></span></strong></p><br>
    <p class="text-info" style="text-align: center" ><strong><span id="docProduce"></span></strong></p>
    <hr>
    <button type="button" id="chatDoc" class="btn btn-primary" style="margin-left: 40%;">联系医生</button>
</div>
<script type="text/javascript" language="javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../static/js/quession/jquery.flexText.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="../static/css/dialog/sweet-alert.css">
<script src="../static/js/dialog/sweet-alert.min.js"></script>
<script type="text/javascript" src="../js/index/quessionDetail.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
</body>
</html>
