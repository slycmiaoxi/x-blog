<%@ page language="java"  pageEncoding="UTF-8"%>  
   
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
    <head>  
     <base href="<%=basePath%>">
        <title>权限</title>    
    </head>  
      
    <body>   
<div class="error">对不起，您必须先登录或者不是管理员没有权限登录相关页面或者执行相关操作</div>
<h2><a href="${pageContext.request.contextPath}/tLinkInfo/portal">点我，返回到主页面</a></h2>
    </body>  

    </html>    

