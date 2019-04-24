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
    <meta charset="UTF-8" />
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
    <title>家庭医院管理系统</title>
    <link rel="stylesheet" type="text/css" href="static/css/login/demo.css" />
    <link rel="stylesheet" type="text/css" href="static/css/login/style.css" />
    <link rel="stylesheet" type="text/css" href="static/css/login/animate-custom.css" />
</head>
<body>
<div class="container">
    <header>
        <h1><span>欢迎使用</span></h1>
    </header>
    <section>
        <div id="container_demo" >
            <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form  action="home/login" autocomplete="on">
                        <h1>Log in</h1>
                        <p>
                            <label for="username" class="uname" data-icon="u" >用户名</label>
                            <input id="username" name="username" required="required" type="text" />
                        </p>
                        <p>
                            <label for="password" class="youpasswd" data-icon="p">密码</label>
                            <input id="password" name="password" required="required" type="password" />
                        </p>
                        <p class="keeplogin">
                            <input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" />
                            <label for="loginkeeping">记住登录状态</label>
                        </p>
                        <p class="login button">
                            <input type="submit" value="Login" />
                        </p>
                        <p class="change_link">
                            <a href="#toregister" class="to_register">注册</a>
                        </p>
                    </form>
                </div>

                <div id="register" class="animate form">
                    <form  id="registForm" >
                        <h1> 注册 </h1>
                        <p>
                            <label for="usernamesignup" class="uname" data-icon="u">用户名</label>
                            <input id="usernamesignup" name="usernamesignup" required="required" type="text"  />
                        </p>
                        <p>
                            <label for="passwordsignup" class="youpasswd" data-icon="p">密码 </label>
                            <input id="passwordsignup" name="passwordsignup" required="required" type="password" />
                        </p>
                        <p>
                            <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">确认密码 </label>
                            <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" />
                        </p>
                        <div id="confirmPwdMsg" style="display: none;">
                            <span  style="color: red">两次密码不一致</span>
                        </div>
                        <p class="signin button">
                            <input id="registBtn" type="button" value="注册"/>
                        </p>
                        <p class="change_link">
                            已经注册过了 ?
                            <a href="#tologin" class="to_register"> 登录 </a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="js/login/login.js"></script>
</html>



