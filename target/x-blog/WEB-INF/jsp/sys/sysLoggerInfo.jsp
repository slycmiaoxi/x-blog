<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理 </title>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/simpleAlert.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myAlert.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>

<body data-type="generalComponents">
<%@include file="common/head.jsp" %>


<div class="tpl-page-container tpl-page-header-fixed">
    <%@include file="common/nav.jsp" %>


    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            查看信息
        </div>
        <ol class="am-breadcrumb">
            <li><a href="#" class="am-icon-home">首页</a></li>
            <li><a href="#">日志</a></li>
            <li class="am-active">控制台</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    <span class="am-icon-code"></span> 控制台
                </div>
                <div class="tpl-portlet-input tpl-fz-ml">
                    <div class="portlet-input input-small input-inline">
                        <div class="input-icon right">
                        </div>
                    </div>
                </div>


            </div>
            <div class="tpl-block">
                <div class="am-g">
                    <div class="am-u-sm-12 am-u-md-6">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">

                                <button type="button" class="am-btn am-btn-default am-btn-danger am-btn-delete"><span
                                        class="am-icon-trash-o"></span> 批量删除
                                </button>

                                <button type="button" class="am-btn am-btn-default am-btn-secondary am-btn-fresh"><span
                                        class="am-icon-save"></span> 刷新
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <div class="am-form-group">

                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-3">
                        <div class="am-input-group am-input-group-sm">
                            <input type="text" class="am-form-field" readonly="readonly" placeholder="管理员操作页面">
                            <span class="am-input-group-btn">
            <button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
                    type="button"></button>
          </span>
                        </div>
                    </div>
                </div>
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-check"><input type="checkbox" class="tpl-table-fz-check"></th>
                                    <th class="table-id">ID</th>
                                    <th class="table-title">日志名称</th>
                                    <th class="table-title">类型</th>
                                    <th class="table-type">请求路径</th>
                                    <th class="table-author am-hide-sm-only">请求类型</th>
                                    <th class="table-date am-hide-sm-only">登录用户</th>

                                    <th class="table-date am-hide-sm-only">IP</th>
                                    <th class="table-date am-hide-sm-only">IP位置</th>
                                    <th class="table-date am-hide-sm-only">耗时(毫秒)</th>
                                    <th class="table-date am-hide-sm-only">创建时间</th>
                                    <th class="table-date am-hide-sm-only">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty TotalList}">
                                    <c:forEach var="admin" items="${requestScope.TotalList}" varStatus="i">
                                        <tr>
                                            <td><input type="checkbox" value="${admin.pkId}" id="ck"></td>
                                            <td>${i.index + 1}</td>
                                            <td><a href="#">${admin.loggerName}</a></td>


                                            <td class="am-hide-sm-only">${admin.loggerType}</td>
                                            <td class="am-hide-sm-only">${admin.requestUrl}</td>
                                            <td class="am-hide-sm-only">${admin.requestType}</td>
                                            <td class="am-hide-sm-only">${admin.userName}</td>
                                            <td class="am-hide-sm-only">${admin.visitorIp}</td>
                                            <td class="am-hide-sm-only">${admin.ipAddress}</td>
                                            <td class="am-hide-sm-only">${admin.requestTime}</td>
                                            <td class="am-hide-sm-only"><fmt:formatDate value="${admin.gmtCreate }"
                                                                                        pattern="yyyy-MM-dd hh:mm"/></td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <button class="am-btn am-btn-default am-btn-xs am-text-secondary"
                                                                data-toggle="modal" data-target="#editModal"
                                                                onclick="remove(${admin.pkId});return false;"><span
                                                                class="am-icon-pencil-square-o"></span> 删除
                                                        </button>

                                                    </div>
                                                </div>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </c:if>


                                </tbody>
                            </table>
                            <div class="am-u-lg-12">
                                <div class="am-cf">

                                    <div class="am-fr">
                                        <ul class="am-pagination tpl-pagination">
                                            <li class="am-disabled">总记录为 ${totalRecord} 条记录--</li>
                                            <li class="am-disabled">当前共有 ${totalCurrentRecord} 条记录--</li>
                                            <li class="am-active">目前是第${page.currentPage}/${page.totalPage}页</li>
                                            <c:if test="${page.hasPrePage}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/core/v1/tLoginLog/show/${1}">首页</a>
                                                </li>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/core/v1/tLoginLog/show/${page.currentPage -1}">上一页</a>
                                                </li>
                                            </c:if>
                                            <c:if test="!${page.hasPrePage}">
                                                <li> 首页</li>
                                                <li> 上一页</li>
                                            </c:if>
                                            <c:if test="${page.hasNextPage}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/core/v1/tLoginLog/show/${page.currentPage + 1}">下一页</a>
                                                </li>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/core/v1/tLoginLog/show/${page.totalPage}">尾页</a>
                                                </li>
                                            </c:if>
                                            <c:if test="!${page.hasNextPage}">
                                                <li> 下一页</li>
                                                <li>尾页</li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
                                <hr>
                            </div>

                    </div>

                </div>
            </div>
            <div class="tpl-alert"></div>
        </div>

    </div>

</div>


<script src="<%=request.getContextPath()%>/static/Ui/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/static/Ui/assets/js/app.js"></script>

<script src="<%=request.getContextPath()%>/js/simpleAlert.js"></script>
<script src="<%=request.getContextPath()%>/js/myAlert.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/query.js"></script>
<script type="text/javascript">
    $(".am-btn-fresh").click(function () {
        location.reload();
    });

    $(".am-btn-delete").click(function () {
        //是否选中
        var isChecked = $("input[type='checkbox']").is(':checked');
        if (!isChecked) {
            alert("必须选中一行进行操作!");
        }
        else {

            var ids = [];
            $('input:checkbox:checked').each(function (index, item) {
                ids.push($(this).val());
            });

            var dblChoseAlert = simpleAlert({
                "title": "是否真的要删除该条日志吗?",
                "content": "请按确定进行删除!",
                "buttons": {
                    "确定": function () {
                        dblChoseAlert.close();
                        $.ajax({
                            cache: false,
                            type: "DELETE",
                            url: '<%=request.getContextPath()%>/core/v1/tLoginLog/batchDelete',
                            data: {
                                "ids": ids.join(',')
                            },
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data.code == '1') {
                                    alert("删除成功");
                                }
                                else {
                                    alert("删除失败!");
                                }
                                location.reload().fadeIn('fast');
                            },
                            error: function (xhr, textStatus) {
                                alert('删除异常！')
                            }
                        });
                    },
                    "取消": function () {
                        dblChoseAlert.close();
                        location.reload();
                    }
                }
            });
        }
    });


</script>
<script type="text/javascript">
    function remove(e) {
        var dblChoseAlert = simpleAlert({
            "title": "是否真的要删除该条日志吗?",
            "content": "请按确定进行删除!",
            "buttons": {
                "确定": function () {
                    dblChoseAlert.close();
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: '<%=request.getContextPath()%>/core/v1/tLoginLog/delete',
                        data: {
                            "pkId": e
                        },
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            if (data.code == '1') {
                                alert("删除成功");
                            }
                            else {
                                alert("删除失败!");
                            }
                            location.reload().fadeIn('fast');
                        },
                        error: function (xhr, textStatus) {
                            alert('删除异常1')
                        }
                    });
                },
                "取消": function () {
                    dblChoseAlert.close();
                    location.reload();
                }
            }
        });
    }
</script>

</body>

</html>
