<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.0/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


    <title>发帖</title>
    <style type="text/css">
        body {
            padding-top: 10px;
            padding-bottom: 40px;
            background-color: #F8F8FF;
            font-family: microsoft yahei;
        }
    </style>

    <script type="text/javascript">

    </script>
</head>
<body>
<%@include file="common/nav.jsp" %>
<br/><br/><br/><br/>
<shiro:guest>目前是游客身份，您未登录,还不能进行发帖,留言，评论</shiro:guest>
<shiro:authenticated>尊敬的<shiro:principal/>，您已登录</shiro:authenticated>

<TABLE WIDTH="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0" align="center">
    <tr height="20">
        <td height="24" colspan="2">&nbsp;</td>
    </tr>
    <TR>
        <td valign="top">

            <br>

        </td>
        <TD COLSPAN="2" align="center" valign="top">
            <table width="100%"
                   style="BORDER-RIGHT:#cccccc 1px solid; BORDER-TOP:#cccccc 1px solid; BORDER-LEFT:#cccccc 1px solid; BORDER-BOTTOM:#cccccc 1px solid; BACKGROUND-COLOR:#f5f5f5"
                   CellPadding="0" CellSpacing="0">
                <tr height="30">
                    <td background="${pageContext.request.contextPath}/images/l-bg3.jpg">&nbsp;&nbsp;&nbsp;<font
                            color="#cccccc"><b>发表文章</b></font></td>
                </tr>
                <c:choose>
                <c:when test="${!empty currentUser }">
                <tr height="20">
                    <td>
                        &nbsp;&nbsp;&nbsp;标题：
                        <div>
                            &nbsp;&nbsp;&nbsp;<input type="text" name="blogTitle" Width="600px" Style="inputcss"
                                                     autofocus="autofocus"/>
                        </div>
                    </td>
                </tr>
                <tr height="20">
                    <td>
                        &nbsp;&nbsp;&nbsp;标签：
                        <div>
                            &nbsp;&nbsp;&nbsp;<input type="text" name="blogLable" Width="600px" Style="inputcss"/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>
                        &nbsp;&nbsp;&nbsp;内容：
                        <script id="editor" type="text/plain" style="width:100%;height:200px;"></script>
                        </td>
                        </tr>
                        <tr height = "20" >
                            <td>
                            <input
                        type = "button"
                        value = "添加评论"
                        style = "float: right;"
                        class
                        = "am-btn-save"/ >
                            </td >
                            </tr >
                            </c:when>
                            <c:otherwise>
                            <center >
                            <h3> 登陆后才可发帖
                            </h3 >
                            </center >
                            </c:otherwise>
                            </c:choose>
                            </table >
                            </td >
                            </tr >
                            </table >


                            <script
                        src = "${pageContext.request.contextPath}/js/jquery-3.1.0.min.js" ></script>
                        <script src="${pageContext.request.contextPath}/js/bootstrap-3.3.0/js/bootstrap.min.js"></script>

                        <script type="text/javascript" charset="UTF-8"
                                src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
                        <script type="text/javascript" charset="UTF-8"
                                src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
                        <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
                        <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
                        <script type="text/javascript" charset="UTF-8"
                                src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
                        <script type="text/javascript">
                            //实例化编辑器
                            //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                            var ue = UE.getEditor('editor');
                        </script>

                        <script type="text/javascript">
                            $(".am-btn-save").click(function () {
                                var blogContent = UE.getEditor('editor').getContent();
                                var blogTitle = $("[name='blogTitle']").val();
                                var blogLable = $("[name='blogLable']").val();
                                if (!blogContent || !blogTitle || !blogLable) {
                                    alert("标题或者标签或者内容不能为空!");
                                    return false;
                                } else {
                                    $.ajax({
                                        cache: false,
                                        type: "POST",
                                        url: '${pageContext.request.contextPath}/core/v1/tBlogInfo/add',
                                        data: {
                                            "blogContent": blogContent,
                                            "blogTitle": blogTitle,
                                            "blogLable": blogLable
                                        },
                                        dataType: "json",
                                        async: false,
                                        success: function (data) {
                                            if (data.code == 1) {
                                                alert("success");
                                                window.location.href="${pageContext.request.contextPath}/core/v1/tBlogInfo/blogAllShow/1";
                                            }
                                            else {
                                                alert("添加失败!");
                                            }

                                        }

                                    });

                                }
                            })

                        </script>
</body>
</html>