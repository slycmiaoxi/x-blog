<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="zwlfn" uri="http://slycmiaoxi/tags/functions" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理 </title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/static/Ui/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed"
          href="<%=request.getContextPath()%>/static/Ui/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/app.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/simpleAlert.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myAlert.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>

<body data-type="generalComponents">
<%@include file="common/head.jsp" %>


<div class="tpl-page-container tpl-page-header-fixed">
    <%@include file="common/nav.jsp" %>


    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            在线实时监控
        </div>
        <ol class="am-breadcrumb">
            <li><a href="#" class="am-icon-home">首页</a></li>
            <li><a href="#">监控台</a></li>
            <li class="am-active"></li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    <span class="am-icon-code"></span>
                    当前在线人数：${activesSessionCount}人&nbsp;&nbsp;
                    <c:if test="${not empty msg}">
                        <div class="message">${msg}</div>
                    </c:if>
                </div>
                <div class="tpl-portlet-input tpl-fz-ml">
                    <div class="portlet-input input-small input-inline">
                        <div class="input-icon right">
                        </div>
                    </div>
                </div>


            </div>
            <div class="tpl-block">
                <div class="am-g">
                    <div class="am-u-sm-12 am-u-md-6">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button type="button" class="am-btn am-btn-default am-btn-secondary am-btn-fresh"><span
                                        class="am-icon-save"></span> 刷新
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <div class="am-form-group">

                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <div class="am-input-group am-input-group-sm">
                            <input type="text" class="am-form-field" readonly="readonly" placeholder="管理员操作页面">
                            <span class="am-input-group-btn">
            <button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
                    type="button"></button>
          </span>
                        </div>
                    </div>
                </div>
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>

                                    <th class="table-title">用户名</th>
                                    <th class="table-type">主机地址</th>
                                    <th class="table-author am-hide-sm-only">最后访问时间</th>
                                    <th class="table-date am-hide-sm-only">已强制退出</th>
                                    <th class="table-date am-hide-sm-only">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${activesSessionList}" var="session">
                                    <tr>
                                        <td>${zwlfn:principal(session)} </td>
                                        <td>${session.host}</td>
                                        <td><fmt:formatDate value="${session.lastAccessTime}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${zwlfn:isForceLogout(session) ? '是' : '否'}</td>
                                        <td>
                                            <div class="am-btn-toolbar">
                                                <div class="am-btn-group am-btn-group-xs">
                                                    <c:if test="${not zwlfn:isForceLogout(session)}">
                                                        <a href="${pageContext.request.contextPath}/core/v1/userOnline/${session.id}/forceLogout">强制退出</a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                            <div class="am-cf">


                            </div>
                            <hr>

                        </form>
                    </div>

                </div>
            </div>
            <div class="tpl-alert"></div>
        </div>

    </div>

</div>



<script src="<%=request.getContextPath()%>/static/Ui/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/static/Ui/assets/js/app.js"></script>

<script src="<%=request.getContextPath()%>/js/simpleAlert.js"></script>
<script src="<%=request.getContextPath()%>/js/myAlert.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/query.js"></script>

<script type="text/javascript">
    $(".am-btn-fresh").click(function () {
        location.reload();
    });
</script>

</body>

</html>
