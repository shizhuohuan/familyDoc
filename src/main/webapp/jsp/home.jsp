<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>jQuery后台左侧伸缩菜单代码 - 站长素材</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="static/css/nav.css">
<link rel="stylesheet" type="text/css" href="static/font/iconfont.css">
<script type="text/javascript" charset="utf-8" src="static/font/iconfont.css"></script>
<script type="text/javascript" charset="utf-8" src="static/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="static/js/nav.js"></script>

</head>
<body>

<div class="nav">
	<div class="nav-top">
		<div id="mini" style="border-bottom:1px solid rgba(255,255,255,.1)"><img src="static/images/mini.png" ></div>
	</div>
	<ul>
		<li class="nav-item">
			<a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>网站配置</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="javascript:showUrl('chatroom')"><span>网站设置</span></a></li>
				<li><a href="chatroom"><span>友情链接</span></a></li>
				<li><a href="javascript:;"><span>分类管理</span></a></li>
				<li><a href="javascript:;"><span>系统日志</span></a></li>
			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="my-icon nav-icon icon_2"></i><span>文章管理</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="javascript:;"><span>站内新闻</span></a></li>
				<li><a href="javascript:;"><span>站内公告</span></a></li>
				<li><a href="javascript:;"><span>登录日志</span></a></li>
			</ul>
		</li>
		<li class="nav-item">
			<a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>订单管理</span><i class="my-icon nav-more"></i></a>
			<ul>
				<li><a href="javascript:;"><span>订单列表</span></a></li>
				<li><a href="javascript:;"><span>打个酱油</span></a></li>
				<li><a href="javascript:;"><span>也打酱油</span></a></li>
			</ul>
		</li>
	</ul>
</div>

<div id="content">

</div>
</body>
<script type="text/javascript">
	function showUrl(o) {
        $.ajax({
            type: "GET",
            url: getRootPath_web() +"/"+ o,
            success: function(data) {
                $('#content').append(data);
            }
        });
    }

    function getRootPath_web() {
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    }

</script>
</html>