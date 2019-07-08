<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap-3.3.0/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog.css">
    <title>算法平台</title>
    <style type="text/css">
        .col-md-8 {
            background-color: #E4F0F8;
        }

        .col-md-4 {
            color: #7CA9ED;
        }

        font {
            font-size: large;
        }

    </style>
</head>

<body>
<div class="container">
    <div class="row" style="float:left;"><a href="${pageContext.request.contextPath}/tLinkInfo/portal"
                                            style="color: blue;text-decoration: underline;">点我?返回首页^_^</a></div>
    <br/><br/>

    <!-- Split button -->
    <div class="row" align="center">
        <c:forEach items="${requestScope.algotithmsList}" var="algotithms">
            <div class="btn-group">
                <button type="button" class="btn btn-info">${algotithms.value}</button>
                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${algotithms.object}" var="algoList">
                        <li><a href="javascript:getAlgoByIndex('${algoList.funcIndex}')"
                               class="am-btn-info-algo">${algoList.algotithmsTitle}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <h3 align="center"></h3>
    </div>
    <div class="row">
        <div class="col-md-4" align="center"><font>Problem Description</font></div>
        <br/>
        <div class="col-md-8 Description" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-md-4" align="center"><font>Input</font></div>
        <br/>
        <div class="col-md-8 Input" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-md-4" align="center"><font>Output</font></div>
        <br/>
        <div class="col-md-8 Output" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-md-4" align="center"><font>Sample Input</font></div>
        <br/>
        <div class="col-md-8 SampleInput" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-md-4" align="center"><font>Sample Output</font></div>
        <br/>
        <div class="col-md-8 SampleOutput" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>

    <div class="row">
        <div class="col-md-4" align="center"><font>Input some things</font></div>
        <br/>
        <div class="col-md-7" style="margin-left: 15%;"><input type="text" class="form-control" placeholder="Text input"
                                                               id="yourInput">
            <br/>
            <button type="submit" class="btn btn-success btn-alog-submiit">（提交输入）submit</button>
        </div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-md-4" align="center"><font>the result</font></div>
        <br/>
        <div class="col-md-8 result" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>

    <div class="row">
        <div class="col-md-4" align="center"><font>code</font></div>
        <br/>
        <div class="col-md-8 Code" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-md-4" align="center"><font>testCode</font></div>
        <br/>
        <div class="col-md-8 TestCode" style="margin-left: 15%;"></div>
    </div>
    <br/>
    <br/>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js//bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var globalFlag = false; //控制页面回调后无反应
    var funcName;
    /**
     * 得到算法详情
     */
    function getAlgoByIndex(id) {
        $.ajax({
            cache: false,
            type: "POST",
            url: '${pageContext.request.contextPath}/core/v1/tAlgotithmsInfo/getAlgotithmsDetail ',
            data: {
                "funcIndex": id
            },
            dataType: "json",
            async: false,
            success: function (result) {
                var data = result.obj;
                $("h3").text(data.algotithmsTitle);
                $(".Description").text(data.algotithmsDescription);
                $(".Input").text(data.inputExpression);
                $(".Output").text(data.outputExpression);
                $(".SampleInput").text(data.sampleInput);
                $(".SampleOutput").text(data.sampleOut);
                $(".Code").html(data.algotithmsCode);
                $(".TestCode").html(data.controllerCode);
                $("#yourInput").val("");
                $(".result").text("");
                globalFlag = true;
                funcName = data.funcName;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }

        });
    }

    //提交算法输入，返回运行结果
    $(".btn-alog-submiit").click(function () {

        var yourInput = $("#yourInput").val();

        if (globalFlag) {
            $.ajax({
                cache: false,
                type: "POST",
                url: '${pageContext.request.contextPath}/core/v1/algorithms/' + funcName,
                data: {
                    "yourInput": yourInput
                },
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data == "error") {
                        alert("您输入的格式不正确");
                    }
                    else {
                        $(".result").text(data);
                        globalFlag = false;
                    }
                }
            });
        }
        else {
            alert("您必须先选中下拉框中的某一个算法!");
        }

    })


    //修改错乱格式
    function changeShowState(result) {
        result = result.replace(new RegExp("<", "g"), "&lt").replace(new RegExp(">", "g"), "&gt");
        result = result.replace(/&gt/g, ">");
        result = result.replace(/\n/g, "<br/>");
        result = result.replace(/\n\r/g, "<br/>");
        result = result.replace(/\r\n/g, "<br/>");
        result = result.replace(/\t/g, "    ");
        result = result.replace(" ", " ");
        result = result.replace("\"", "\\" + "\"");
        return result;
    }


</script>

</body>
</html>


　

