<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人后台</title>
</head>
	<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
		<frame src="<%=request.getContextPath()%>/core/v1/tClient/top" scrolling="no" noresize />
		<frameset cols="178px,*">
			<frame noresize src="<%=request.getContextPath()%>/core/v1/tClient/left" scrolling="yes" />
			<frame noresize name="right" src="<%=request.getContextPath()%>/core/v1/tClient/right" scrolling="yes" />
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="<%=request.getContextPath()%>/core/v1/tClient/bottom" />
	</frameset>
	<noframes>
		<body>
			你的浏览器不支持框架布局，推荐你使用<a href="http://www.firefox.com.cn/download/" style="text-decoration: none;">火狐浏览器</a>,
			<a href="http://www.google.cn/intl/zh-CN/chrome/" style="text-decoration: none;">谷歌浏览器</a>
		</body>
	</noframes>
</html>