<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>图灵机器人</title>
</head>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
/*通配所有标签*/
body {
	background: #eee;
}

.title {
	width: 100%;
	height: 63px;
	background: rgba(0, 0, 0, 0.2);
	text-align: center;
	line-height: 60px;
	color: #FFFFFF;
}

.box {
	width: 800px;
	height: 585px;
	background: rgba(255, 255, 255, .7);
	margin: 100px auto 0;
}

.head {
	width: 100%;
	height: 60px;
	background: #ddd;
	text-align: center;
	line-height: 60px;
}

.content {
	width: 770px;
	height: 465px;
	background: #fff;
	padding: 15px;
	overflow: auto;
}

.send {
	width: 100%;
	height: 60px;
	background: #ddd;
}

#input {
	width: 700px;
	height: 60px;
	border: none;
	background-color: #ccc;
	text-indent: 20px;
	font-size: 16px;
}

#btn {
	width: 100px;
	height: 60px;
	float: right;
	border: none;
	cursor: pointer;
	outline: none;
}

#btn:hover {
	background: #FFFFCC;
}

.message {
	width: 100%;
	height:;
	overflow: hidden;
	/*防止并排显示，溢出隐藏清除浮动*/
	margin-bottom: 10px;
}

.message span {
	width: 40px;
	height: 45px;
	background-image: url('${pageContext.request.contextPath}/images/rot.png');
	display: block;
	float: left;
}

.message p {
	padding: 10px;
	background-color: blanchedalmond;
	width: 200px;
	float: left;
	margin-left: 10px;
	border-radius: 8px;
}

.my span {
	background: url('${pageContext.request.contextPath}/images/my.png');
	float: right;
}

.my p {
	float: right;
	margin-right: 10px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<body>
	<div class="title">图灵机器人</div>
	<div style="float:right;"><a href="tLinkInfo/portal" style="color: blue;text-decoration: underline;">点我?返回首页^_^</a></div>
	<div class="box">
		<div class="head">吧主的机器女友小美</div>
		<div class="content">
			<div class="message rot">
				<span></span>
				<p>我是吧主的机器女友^_^</p>

			</div>
		</div>
		<div class="send">
			<input id="input" name="info" type="text" maxlength="30" /> <input
				id="btn" type="button" value="发送" onclick="chat() " /> <span
				id="likebullshit" style="color:#F00"></span>
		</div>

	</div>
</body>
<script type="text/javascript">
	function chat() {
		//$("#input").attr("value","");//清空输入框,此方法无效
		var info = $("#input").val(); //输入的值
		if (info == null || info == "" || info == undefined) { //提示语句
			$("#likebullshit").html("请输入聊天语句哦！");
		} else {
			$("#likebullshit").html("");
		}
		$(".content").append("<div class='message my'><span></span><p>" + info + "</p></div>");
		$(".content").scrollTop(99999999); //自动滚屏
		$.ajax({
			url : "${pageContext.request.contextPath}/servlet/robbot", //访问路径
			type : "post", //访问方式
			data : {
				"info" : info
			}, //传入服务端的数据
			/* 				dataType:"json",//数据类型
							contentType:"application/json;charset=utf-8",//编码格式 */
			success : function(result) { //回调函数
				//console.log(result);
				result = $.parseJSON(result);
				//alert(info +"   "+ result.text);
				$(".content").append("<div class='message rot'><span></span><p>" + result.text + "</p></div>");
				$(".content").scrollTop(99999999); //自动滚屏
				var url = 'http://fanyi.baidu.com/gettts?lan=zh&text=' + result.text + '&spd=5&source=web';
				var obj = $("<audio src=" + url + " autoplay></audio>");
				$("audio").remove();
				$("body").append(obj);
			}
		});
		$("#input").val("");
		$("#input").focus();
	}
	
	//回车触发按钮按下，优化客户体验度
	$(document).keypress(function(e) {
		// 回车键事件  
		if (e.which == 13) {
			$('input[type="button"]').click();
		}
	});
</script>
</html>