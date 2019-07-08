<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="zh-CN">
<head>

    <%@include file="import.jsp" %>
    <!-- <meta http-equiv="Content-Type" content="text/html;charset=utf-8" /> -->
    <title> java在线运行</title>
</head>
<body>
<div class="container">
<div class="row" style="padding-top: 10px">
<div class="col-md-12">
<nav class="navbar navbar-default">
<div class="container-fluid">
<!-- Collect the nav links, forms, and other content for toggling -->
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<ul class="nav navbar-nav">
<li class="active"><a class="navbar-brand" href="tLinkInfo/portal">返回首页</a></li>
</ul>
</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>
</div>
</div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span6">输入java代码:&nbsp;&nbsp;&nbsp;
            <div class="row-fluid">
                <form>
				<textarea id="code" name="code">/*java code running online: Don't input Chinese*/
public class Main{
     public static void main(String []args){
        System.out.println("Hello World.My first java code running online.");
     }
}</textarea>
                </form>
            </div>
        </div>
        <div class="span6"><a id="run" class="btn btn-success" target="result_iframe"
                              data-options="plain:true,iconCls:'icon-compile'">运行(ctrl+e)</a> &nbsp;(运行结果)
            <strong id="test"></strong>
            <ul class="nav pull-right">
                <li id="fat-menu" class="dropdown">
                    <select>
                        <option><a href="#">Java</a></option>
                    </select>
            </ul>

            <div class="row-fluid">
                <iframe id="result_iframe" width="100%" height="280">
                </iframe>
            </div>
        </div>
    </div>
</div>

<div>java在线运行平台使用说明:
    <p>1,在上方输入java代码.全屏编辑代码:F11; 四个缩进:Tab,保存代码:ctrl+s</p>
    <p>2,点击右上的"运行"按键,即可看到对应的java源码在线运行后的结果.</p>
    <p>3,必须要有main函数.</p>
</div>

<script>
    var ifchange = false;
    var mode = 'htmlmixed';
    CodeMirror.modeURL = "${pageContext.request.contextPath}/static/codemirror/mode/%N/%N.js";
    var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
        styleActiveLine: true,
        lineNumbers: true,
        matchBrackets: true,
        lineWrapping: true,
        foldGutter: true,
        gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"],
        extraKeys: {
            "F11": function (cm) {
                cm.setOption("fullScreen", !cm.getOption("fullScreen"));
            },
            "Esc": function (cm) {
                if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
            },
            "Ctrl-Q": function (cm) {
                cm.foldCode(cm.getCursor());
            }
        }

    });
    editor.on('change', function (cm) {
        ifchange = true;
        $('#save').removeClass('disabled');
    });
    editor.foldCode(CodeMirror.Pos(8, 0));
    editor.setOption("mode", mode);
    CodeMirror.autoLoadMode(editor, mode);

    $('#title').change(function () {
        ifchange = true;
        $('#save').removeClass('disabled');
    });
</script>

<script>
    $(function () {
        $('#run').click(function () {
            var data = editor.getValue();
            $("#test").text("请耐心等待!");
            $.post("http://localhost:8080/core/v1/dynamic/compile", {'code': data}, function (result) {
                alert(result);
                alert(result.code);
                if(result.code == 1) {
                   $("#test").text("");
                   $($("#result_iframe")[0].contentWindow.document.body).html(result.obj);
               }else{
                   alert(result.msg);
               }
            });
        });
    })
</script>

</body>
</html>
