<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 <base href="<%=basePath%>">
    <title>系统错误</title>
    <style>.message{color:red;}</style>
</head>
<body>
 
服务器发生异常，<h2><a href="${pageContext.request.contextPath}/tLinkInfo/portal">点我，返回到主页面</a></h2>

</body>
<script type="text/javascript">
</script>
</html>