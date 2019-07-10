<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<script  src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/js/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}${pageContext.request.contextPath}/static/codemirror/addon/merge/merge.css" />
<link href="${pageContext.request.contextPath}/static/codemirror/theme/3024-night.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/codemirror/theme/erlang-dark.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/codemirror/lib/codemirror.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/static/codemirror/mode/shell/shell.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/codemirror/mode/perl/perl.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/codemirror/mode/python/python.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/codemirror/addon/selection/active-line.js"></script>
<script src="${pageContext.request.contextPath}/static/codemirror/addon/display/fullscreen.js"></script>
<script src="${pageContext.request.contextPath}/static/codemirror/addon/fold/fold_6in1.min.js"></script>

<script src="${pageContext.request.contextPath}/static/codemirror/addon/mode/loadmode.js"></script>
<script src="${pageContext.request.contextPath}/static/codemirror/lib/codemirror.js" type="text/javascript"></script>