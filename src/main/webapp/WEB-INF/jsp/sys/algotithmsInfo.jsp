<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>算法建模输入</title>
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
</head>
<body data-type="index">
<%@include file="common/head.jsp" %>


<div class="tpl-page-container tpl-page-header-fixed">
    <%@include file="common/nav.jsp" %>


    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title" style="text-align: center;vertical-align: middle;">
            算法建模输入平台

        </div>

        <ol class="am-breadcrumb">
            <li><a href="#" class="am-icon-home">首页</a></li>
            <li><a href="#">算法</a></li>
            <li class="am-active">算法建模</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    <span class="am-icon-code"></span> 录入信息
                </div>
                <div class="tpl-portlet-input tpl-fz-ml">
                    <div class="portlet-input input-small input-inline">
                        <div class="input-icon right">

                        </div>
                    </div>
                </div>


            </div>
            <div class="tpl-block ">

                <div class="am-g tpl-amazeui-form">


                    <div class="am-u-sm-12 am-u-md-9">
                        <form class="am-form am-form-horizontal">
                            <div class="am-form-group">
                                <label class="am-u-sm-3 am-form-label">算法标题 / Title</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="algotithmsTitle" name="algotithmsTitle"
                                           placeholder="算法标题 / Title">
                                    <small></small>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-sm-3 am-form-label">算法 <span
                                        class="tpl-form-line-small-title">类型</span></label>
                                <div class="am-u-sm-9">
                                    <select name="algotithmsTypeId" data-am-selected="{searchBox: 1}" id="algotithmsTypeId">
                                        <c:forEach items="${requestScope.algoTypeList}" var="type">
                                            <option value='${type.id}'>${type.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-sm-3 am-form-label">算法描述/ Description</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="algotithmsDescription" name="algotithmsDescription"
                                           placeholder="算法描述/ Description">
                                    <small>简要描述一下</small>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-sm-3 am-form-label">输入描述 / Expression</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="inputExpression" name="inputExpression"
                                           placeholder="输入描述 / Expression">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="outputExpression" class="am-u-sm-3 am-form-label">输出描述 / Expression</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="outputExpression" name="outputExpression"
                                           placeholder="输出描述 / Expression">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="sampleInput" class="am-u-sm-3 am-form-label">输入样例 / Input</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="sampleInput" name="sampleInput" placeholder="输入样例 / Input">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="sampleOut" class="am-u-sm-3 am-form-label">输入样例 / Input</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="sampleOut" name="sampleOut" placeholder="输出样例 / Out">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="funcName" class="am-u-sm-3 am-form-label">函数名 / Func</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="funcName" name="funcName" placeholder="函数名 / Func">
                                </div>
                            </div>


                            <div class="am-form-group">
                                <label for="algotithmsCode" class="am-u-sm-3 am-form-label">核心算法 / Code</label>
                                <div class="am-u-sm-9">
                                    <textarea id="algotithmsCode" name="algotithmsCode"
                                              placeholder="核心算法 / Code"></textarea>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="controllerCode" class="am-u-sm-3 am-form-label">算法交互 / testCode</label>
                                <div class="am-u-sm-9">
                                    <textarea id="controllerCode" name="controllerCode"
                                              placeholder="算法交互 / testCode"></textarea>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <button type="submit" class="am-btn am-btn-primary am-btn-algo">提交算法</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- 引用控制层插件样式 -->


<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.form.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/Ui/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/static/Ui/assets/js/iscroll.js"></script>
<script src="<%=request.getContextPath()%>/static/Ui/assets/js/app.js"></script>
<script type="text/javascript">
    $(".am-btn-algo").click(function () {
        /*  $("form:first").submit(); */
        var options = {
            type: 'POST',
            url: '${pageContext.request.contextPath}/core/v1/tAlgotithmsInfo/add',
            success: showResponse,
            dataType: 'json',
            error: function (xhr, status, err) {
                alert("不要越权^_^");
            }
        };
        $("form").submit(function () {
            $(this).ajaxSubmit(options);
            return false;   //防止表单自动提交
        });
    })

    /**
     * 保存后，执行回调
     * @param responseText
     * @param statusText
     * @param xhr
     * @param $form
     */
    function showResponse(responseText, statusText, xhr, $form) {
        if (responseText.code == "1") {
            /**
             * 请求成功后的操作
             */
            alert("添加成功!");
            location.reload();

        } else if (responseText.code == "0") {
            alert("添加失败!");
            location.reload();
        } else {
            alert("添加异常!");
        }
    }


</script>
</body>