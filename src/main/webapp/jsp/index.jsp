<%--
  Created by IntelliJ IDEA.
  User: zsd
  Date: 2018/8/19
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>家庭医院管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/common/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/common/plugins/style/admin.css" media="all">

    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>

                <li class="layui-nav-item" lay-unselect>
                     <a href="javascript:;" layadmin-event="refresh" title="刷新">
                         <i class="layui-icon layui-icon-refresh-3"></i>
                     </a>
                 </li>

            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <li class="layui-nav-item">
                    <a href="">我</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:quit();">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <dl class="layui-nav-child">
                        <a lay-href="<%=request.getContextPath()%>/verification/exit">退出</a>
                    </dl>
                </li>

                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="goods.jsp">
                    <span>家庭医院</span>
                </div>
                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <li data-name="home" class="layui-nav-item ">
                        <a lay-href="/familyDoc/jsp/search.jsp" lay-tips="首页" lay-direction="2" id="defaultPage">
                            <i class="layui-icon layui-icon-app"></i>
                            <cite>首页</cite>
                        </a>

                    </li>
                    <li data-name="home" class="layui-nav-item ">
                        <a lay-href="/familyDoc/department/getAllDpt" lay-tips="科室分类" lay-direction="2">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>科室分类</cite>
                        </a>

                    </li>
                    <li data-name="home" class="layui-nav-item ">
                        <a href="javascript:;" lay-tips="问题" lay-direction="2">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>问题</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console">
                                <a lay-href="/familyDoc/quession/allQuession">问题广场</a>
                            </dd>
                            <c:if test="${sessionScope.currenUser.identity!='3'}">
                                <dd data-name="console">
                                    <a lay-href="/familyDoc/quession/askQuession">提问</a>
                                </dd>
                                <dd data-name="console">
                                    <a lay-href="/familyDoc/jsp/myQuession.jsp">我的问题</a>
                                </dd>
                            </c:if>
                        </dl>
                    </li>
                    <c:if test="${sessionScope.currenUser.identity!= '3'}">
                        <li data-name="home" class="layui-nav-item ">
                            <a href="javascript:;" lay-tips="病历" lay-direction="2">
                                <i class="layui-icon layui-icon-home"></i>
                                <cite>病历</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <c:if test="${sessionScope.currenUser.identity!= '1'}">
                                    <dd data-name="console">
                                        <a lay-href="/familyDoc/jsp/record.jsp">填写病历</a>
                                    </dd>
                                </c:if>
                                <dd data-name="console">
                                    <a lay-href="/familyDoc/jsp/myRecord.jsp">我的病历</a>
                                </dd>
                            </dl>
                        </li>
                    </c:if>
                    <li data-name="home" class="layui-nav-item ">
                        <a href="javascript:;" lay-tips="我的" lay-direction="2">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>我的</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <c:if test="${sessionScope.currenUser.identity!= '3'}">
                                <dd data-name="console">
                                    <a lay-href="/familyDoc/jsp/myMessage.jsp">我的消息</a>
                                </dd>
                            </c:if>
                            <dd data-name="console">
                                <a lay-href="/familyDoc/home/myData">个人资料</a>
                            </dd>
                            <dd data-name="console">
                                <a lay-href="/familyDoc/jsp/updatePwd.jsp">修改密码</a>
                            </dd>
                            <c:if test="${sessionScope.currenUser.identity!= '1'}">
                                <dd data-name="console">
                                    <a lay-href="/familyDoc/jsp/docRegist.jsp">成为医生</a>
                                </dd>
                            </c:if>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="main" lay-attr="main" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="/familyDoc/jsp/search.jsp" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/common/plugins/layui/layui.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/login/index.js"></script>
<script>
    layui.config({
        base: '<%=request.getContextPath()%>/common/plugins/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');

    function quit() {
        top.location.href = "http://localhost:8080/familyDoc/";
    }
</script>
</body>
</html>



