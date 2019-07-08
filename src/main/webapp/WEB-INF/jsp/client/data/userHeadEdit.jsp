<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>个人后台管理 </title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/Ui/assets/css/app.css">


</head>

<body data-type="generalComponents">
<div class="tpl-block">

                    <div class="am-g">
                        <form class="tpl-form-body tpl-form-line" action="" enctype="multipart/form-data" method="post" id="forms">
                                        <div class="am-form tpl-form-line-form">               
                                 <div class="am-form-group">
                                    <label  class="am-u-sm-3 am-form-label">个人 <span class="tpl-form-line-small-title">图片</span></label>
                                    <div class="am-u-sm-9">

                           <div class="am-form-group am-form-file">
                                            <div class="tpl-form-file-img">
                                               <img alt="图片展示区域" id="myImg" src="${currentUser.userHeadimg }" height="100px",width="100px">
                                            </div>
                                        
    <i class="am-icon-cloud-upload"></i> 修改头像</button>
                                             <input type="file" id="file" name="files" accept="image/*" onchange="changImg(event)" >  
                                        </div>
                                        </div>
                                        </div>
                                               
                                <div class="am-form-group">
                                    <label  class="am-u-sm-3 am-form-label">个性签名 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="userPersonality" class="tpl-form-input" id="userPersonality" value="${currentUser.userPersonality }">
                                        <small>个性签名不超过20字。</small>
                                    </div>
                                </div>  
                                
                                
                                <div class="am-form-group">
                                    <label  class="am-u-sm-3 am-form-label">真实姓名 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="userRealName" class="tpl-form-input" id="userRealName" value="${currentUser.userRealName }">
                                        <small>真实姓名 不超过20字。</small>
                                    </div>
                                </div>   
                                
                          <div class="am-form-group" hidden="hidden">
                                    <label  class="am-u-sm-3 am-form-label">真实姓名 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="userId" class="tpl-form-input" id="userId" value="${currentUser.userId }" hidden="hidden"/>
                                        
                                    </div>
                                </div>   
                                        
                                    
                                               
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="save();">修改</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                                        
                                        
  <script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">
function save(){
	 var options = {   
	            type: 'POST',  
	            url: '${pageContext.request.contextPath}/core/v1/tClient/upload',
	            success:showResponse,    
	            dataType: 'json',  
	            error : function(xhr, status, err) {              
	                alert("操作失败");  
	            }  
	        };   
	        $("form").submit(function(){   
	            $(this).ajaxSubmit(options);   
	            return false;   //防止表单自动提交  
	        });  
}

/** 
* 保存后，执行回调 
* @param responseText 
* @param statusText 
* @param xhr 
* @param $form 
*/  
function showResponse(responseText, statusText, xhr, $form){      
  if(responseText.code == "1"){  
      /** 
      * 请求成功后的操作 
      */  
      alert("添加成功!"); 
     location.reload();
     
  } else if(responseText.code == "0"){  
      alert("名已存在，添加失败!");
      location.reload();
  }  else{
 	 alert("添加异常!");
  }   
}  

</script>
<script type="text/javascript">    
     function changImg(e){    
        for (var i = 0; i < e.target.files.length; i++) {    
            var file = e.target.files.item(i);    
            if (!(/^image\/.*$/i.test(file.type))) {    
                continue; //不是图片 就跳出这一次循环    
            }    
            //实例化FileReader API    
            var freader = new FileReader();    
            freader.readAsDataURL(file);    
            freader.onload = function(e) {    
                $("#myImg").attr("src",e.target.result);    
            }    
        }    
    }    
</script>    
</body>

</html>