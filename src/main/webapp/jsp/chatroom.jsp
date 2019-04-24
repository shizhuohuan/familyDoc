<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<html>
<head>
    <base href="<%=basePath%>">
    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>聊天室</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="/familyDoc/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/familyDoc/ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/familyDoc/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/familyDoc/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body data="/familyDoc">
<div id="message">
</div>
<div class="container-fluid">
    <div class="row" style="margin:0px auto; width:1050px;">
        <div class="col-md-8" style="margin:0px auto; width:1050px;">
            <div class="panel panel-primary" >
                <div class="panel-heading">聊天----<span id="contactName"><c:out value="${recName}" ></c:out></span></div>
                <div id="msg" class="panel-body">
                </div>
                <div class="panel-footer">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <script id="editor" type="text/plain" style="width:1024px;height:200px;margin: auto;"/>
        </div>
    </div>
</div>
<script type="text/plain" id="myEditor">

</script>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <p class="text-right">
                <button onclick="sendMsg();" class="btn btn-success" style="margin-right: 22%;"> 发送</button>
            </p>
        </div>
    </div>
</div>
</body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="static/js/quession/jquery.flexText.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/dialog/example.css">
<link rel="stylesheet" type="text/css" href="static/css/dialog/sweet-alert.css">
<script src="static/js/dialog/sweet-alert.min.js"></script>
<script type="text/javascript" src="js/index/chatRoom.js"></script>
<script type="text/javascript">
    var ue = UE.getEditor('editor');
    var websocket = null;

    var sendId = '${sessionScope.currenUser.id}';
    var recId = '${recId}';
    // var sendId = '1';
    // var recId = '2';
    var recName = '${recName}';
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://127.0.0.1:8080/familyDoc/websocket/"+ sendId +"/" + recId);
    }
    else {
        alert("对不起！你的浏览器不支持webSocket")
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };
    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        setMessageInnerHTML("<span style='color:grey;'>-------------进入聊天室------------</span>");
        getHistory();
    };
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        var myDate = CurentTime();
        setMessageInnerHTML(recName + "    " + myDate);
        setMessageInnerHTML(event.data);
    };
    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("<span style='color:grey;'>-------------断开连接------------</span>");
    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，
    // 防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    };

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        innerHTML = "" + innerHTML;
        if(innerHTML.indexOf("<img")!=-1){
            innerHTML = innerHTML.slice(0,innerHTML.indexOf("<img")+4) + " style='width:50%;height:50%;' " + innerHTML.slice(innerHTML.indexOf("<img")+5);
        }
        $("#msg").append(innerHTML + "<br>");
    };

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    function sendMsg() {
        var msg = ue.getContent();
        websocket.send(msg);
        ue.setContent('');
        var myDate = CurentTime();
        setMessageInnerHTML("<strong>我    " + myDate  + "</strong>");
        setMessageInnerHTML(msg);
    }

    function CurentTime()
    {
        var now = new Date();

        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日

        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();           //秒

        var clock = year + "-";

        if(month < 10)
            clock += "0";

        clock += month + "-";

        if(day < 10)
            clock += "0";

        clock += day + " ";

        if(hh < 10)
            clock += "0";

        clock += hh + ":";
        if (mm < 10) clock += '0';
        clock += mm + ":";

        if (ss < 10) clock += '0';
        clock += ss;
        return(clock);
    }

</script>
</html>