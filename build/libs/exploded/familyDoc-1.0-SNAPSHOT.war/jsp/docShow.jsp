<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/20
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../static/js/translucent/style.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="../static/js/translucent/jquery-translucent.js"></script>
</head>
<body>
<div id="mydialog">

</div>
</body>
<script>
    $("#mydialog").translucent({
        titleGroundColor:"#5396BA",
        backgroundColor:"#ffffff",
        titleFontColor:"#ffffff",
        titleFontSize:14,
        opacity:1,
        zIndex:100,
        textHtml:'<span>我的弹窗demo</span>',
        close:function ($dom) {
            alert("确定要关闭吗？")
        }
    });
</script>
</html>
