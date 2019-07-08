<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>搜索结果</title>
<%@include file="common/import.jsp"%>
	<script type="text/javascript">
	$(function(){	
		var key='${key}';
		var recordCount='${recordCount}';
		 var content=
				"  <tr>"
				 +" 	<td>"
					+"  	<span class='glyphicon glyphicon-tag' aria-hidden='true'></span>"
					  +"	<a href='' target='_blank'>"			  
					  +"	</a>"
					  +"<br/><br/>	<div style='float: left;'>"				  		 
					 +" 	</div>"
					+"</td>"
				  +"</tr>";  
			 
			 $.ajax({
					cache: false,
					type: "POST",
					url: '${pageContext.request.contextPath}/core/v1/tBlogInfo/search ',
					 dataType:"json",
					 data: {
			   				"key" : key,
			   				"recordCount" : recordCount
			   			},
					 async: false,
					success: function(data) {
						 $.each(data,function (index,e){
							 if(index==0){
							 $(".table-hover").html(content); 
							 }
							 else{
								 $(".table-hover").append(content);
							 }
							 $(".table-hover tr:last td a").attr("href","${pageContext.request.contextPath}/core/v1/tCommentInfo/showEsSearch/"+e.blogItem);
							 $(".table-hover tr:last td a").html(e.blogTitle);
							 $(".table-hover tr:last td div:first").html(e.blogContent);
								 
						});  
					}

				});
				
	
	})
	

	</script>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="common/head.jsp"%><br>
		<h4>查询关键字:"${key }",共查到${recordCount}篇帖子！</h4>
		<table class="table table-hover">
	
		</table>
		<%@include file="common/foot.jsp"%>
	</div>

</body>
</html>
