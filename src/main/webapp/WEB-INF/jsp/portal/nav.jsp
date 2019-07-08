<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <style>
        /*改变下拉框中a标签样式*/
        .open li a {
            background-color: #222222;
            color: #9D9D9D;
        }

        /*改变鼠标悬浮在a标签上时的样式*/
        .open li a:hover {
            background-color: #222222;
            color: #fff;
        }

        /*覆盖导航标签和下拉菜单间的间隙*/
        .open * {
            background-color: #222;
        }
    </style>
    <script>
        //鼠标悬浮于导航栏中的特定标签时,显示下拉框(不用点击)
        $(function () {
            var $dropdownli = $('li.dropdown');
            $dropdownli.mouseover(function () {
                $(this).addClass('open');
            }).mouseout(function () {
                $(this).removeClass('open');
            });
        });
    </script>
    <title>Document</title>
</head>
<body>
<!--  navbar-inverse  默认色反转       navbar-fixed-top navbar固定在顶部 -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <!-- padding margin  -->
    <div class="container">
        <!-- 导航栏 头部信息 -->
        <div class="navbar-header">
            <!-- 页面响应式 -->
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">Toggle navgation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">导航栏</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/tLinkInfo/portal">首页</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">技术交流
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/core/v1/tBlogInfo/blogWriteShow">发帖·</a></li>
                        <li><a href="${pageContext.request.contextPath}/core/v1/tMessageInfo/show">留言</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">休闲灌水
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/core/v1/tBlogInfo/blogAllShow/1">逛贴评论</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">版主交流
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/core/v1/tAlgotithmsInfo/frontShow">算法建模</a></li>
                        <li><a href="${pageContext.request.contextPath}/core/v1/dynamic/show">在线编程</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">人工智能
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/rob/robbot">图灵机器人</a></li>
                    </ul>
                </li>

                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">注入论坛<span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/login.html">登录</a></li>
                        <li><a href="${pageContext.request.contextPath}/regist.html">注册</a></li>
                        <li><a href="${pageContext.request.contextPath}/forget.html">忘记密码</a></li>
                        <li><a href="${pageContext.request.contextPath}/core/v1/tUser/show/1">后台管理</a></li>
                    </ul>
                </li>

                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">关于<span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/aboutMe.html">吧主信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/photoMe.html">吧主相册</a></li>
                        <li><a href="#">关于本站</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/core/v1/tClient/index">个人后台</a></li>
            </ul>
            <form action="${pageContext.request.contextPath}/core/v1/tBlogInfo/searchShow" method="post"
                  class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" name="key" class="form-control" placeholder="按帖子标题和内容检索">
                </div>
                <button type="sumbit" class="btn btn-primary">本站搜索</button>
            </form>
        </div>
    </div>
</nav>


</body>
</html>