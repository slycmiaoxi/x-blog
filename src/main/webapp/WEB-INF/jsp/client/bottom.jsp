﻿<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Frame bottom</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
	<link href="<%=request.getContextPath()%>/css/common_style_blue.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		body{
			margin: 0;
		}
		img{
			vertical-align: inherit;
			border:0;
		}
		a:link, a:hover, a:visited {
			color: #A9DCFF;
			text-decoration: none;
		}
		#StatusBar {
			 background-color: #4386B7;
			border-top: 1px solid #FFFFFF;
			height: 19px;
			width: 100%;
		}
		#StatusBar #StatusBar_Links {
			color: #A9DCFF;
			float: left;
			font-family: "宋体";
			font-size: 12px;
			padding-left: 20px;
			padding-top: 3px;
		}
		#StatusBar #StatusBar_Right {
			color: #A9DCFF;
			float: right;
			font-family: "宋体";
			font-size: 12px;
			padding-right: 20px;
			padding-top: 4px;
		}
	</style>
</head>

<body> 

<div id="StatusBar">
	<!-- 链接 -->
    <div id="StatusBar_Links">
		<a href="#" target="_blank">个人论坛</a>，谢谢您的支持！
    </div>
	<!-- 右侧功能按钮 -->
    <div id="StatusBar_Right">
		<!-- 版本 -->
		<a href="javascript:void(0)">
			<img border="0" width="11" height="11" src="<%=request.getContextPath()%>/static/style/images/info.gif" />
			<img border="0" width="40" height="11" src="<%=request.getContextPath()%>/static/style/images/version.gif" />
		</a>
    </div>
</div>

</body>
</html>