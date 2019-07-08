<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link href="${pageContext.request.contextPath}/static/404/css/jq22.css" rel="stylesheet" type="text/css" />

 
</head>
<body>
<!-- 代码 开始 -->
<div class="fullScreen" id="fullScreen">
    <img class="rotating" src="${pageContext.request.contextPath}/static/404/images/spaceman.svg" />
    <div class="pagenotfound-text">
    <h2>对不起，您访问页面已丢失</h2>
    <h2><a href="${pageContext.request.contextPath}/tLinkInfo/portal">点我，返回到主页面</a></h2>
    </div>
    <canvas id="canvas2d"></canvas>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/404/js/jq22.js"></script>
<!-- 代码 结束 -->

</body>
</html>