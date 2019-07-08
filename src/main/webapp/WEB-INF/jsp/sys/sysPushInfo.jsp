<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!doctype html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理 </title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/static/Ui/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/static/Ui/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/app.css">
</head>

<body data-type="generalComponents">
<%@include file="common/head.jsp" %>


    <div class="tpl-page-container tpl-page-header-fixed">
        <%@include file="common/nav.jsp" %>

        <div class="tpl-content-wrapper">
            <div class="tpl-content-page-title">
                录入信息
            </div>
            <ol class="am-breadcrumb">
                <li><a href="#" class="am-icon-home">首页</a></li>
                <li><a href="#">消息</a></li>
                <li class="am-active">推送</li>
            </ol>
            <div class="tpl-portlet-components">
                <div class="portlet-title">
                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 信息录入
                    </div>
                


                </div>

                <div class="tpl-block">

                    <div class="am-g">
                        <div class="tpl-form-body tpl-form-line">
                            <div class="am-form tpl-form-line-form">
                                <div class="am-form-group">
                                    <label  class="am-u-sm-3 am-form-label">推送消息 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" class="tpl-form-input" id="msg"  placeholder="请输入消息">
                                        <small>请填写要推送的信息。</small>
                                    </div>
                                </div>

                              


                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success am-btn-algotype">提交</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


            </div>


        </div>

    </div>



    <script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
   <script src="<%=request.getContextPath()%>/js/jquery.form.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/Ui/assets/js/amazeui.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/Ui/assets/js/iscroll.js"></script>
    <script src="<%=request.getContextPath()%>/static/Ui/assets/js/app.js"></script>
    
    <script type="text/javascript">
    
    $(".am-btn-algotype").click(function(){
    	var msg=$("#msg").val();
    	if(!msg){
    		alert("消息推送不能为空!");
    		clean();
    	}
    	else{
    		 $.ajax({
 	   			cache: false,
 	   			type: "POST",
 	   			url: '<%=request.getContextPath()%>/core/v1/activemq/topicSender',
 	   			data: {
 	   				"msg" : msg
 	   			},
 	   			 dataType:"json",
 	   			 async: false,
 	   			success: function(data) {
 	   				if(data.code=='1'){ 
 	   					alert("推送成功");
 	   					clean();
 	   					}
 	   				else{
 	   					alert("推送失败!");
 	   				}}   		
 	   			});  
    	}
    })
    
    function clean(){
    	$("#msg").val("");
    }
    
    </script>
</body>

</html>
