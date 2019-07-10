<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>主页</title>
<%@include file="common/import.jsp"%>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="common/head.jsp"%>
		<div class="row">
			<div class="col-md-8">
					
				
				              <%-- 用户个人的帖子 --%>
				<c:if test="${!empty TotalList}">
				<c:forEach var="t" items="${TotalList }">					
						<div class="page-header">
							<h4>
							     <c:choose>
                         <c:when test="${t.blogState eq 1}"> 
                               <span class="label label-primary">置顶</span>
								<c:if test="${t.blogState eq 3}">
									<span class="label label-danger">精</span>
								</c:if>
                              </c:when>
                              <c:when test="${t.blogState eq 2}"> 
                              <span class="label label-danger">精</span>
                              </c:when>
                       <c:otherwise>  
                      </c:otherwise>
                             </c:choose>		
								<%@include file="common/articleData.jsp"%>
						</div>
					
				</c:forEach>
				</c:if>			
				<%-- 分页  --%>
				<div style="float: right;">
					 <ul class="am-pagination tpl-pagination">
                                         总记录为  ${totalRecord} 条记录--
                                        当前共有  ${totalCurrentRecord} 条记录--
                                       目前是第${page.currentPage}/${page.totalPage}页 
                                        <c:if test="${page.hasPrePage}">
													<a href="${pageContext.request.contextPath}/core/v1/tBlogInfo/userBlogAllShow/${1}/${fkUid}" >首页</a>
													<a href="${pageContext.request.contextPath}/core/v1/tBlogInfo/userBlogAllShow/${page.currentPage -1}/${fkUid}" >上一页</a>
												</c:if>
												<c:if test="!${page.hasPrePage}">												
													首页
						                        	上一页
											   </c:if>										
												<c:if test="${page.hasNextPage}">
													<a href="${pageContext.request.contextPath}/core/v1/tBlogInfo/userBlogAllShow/${page.currentPage + 1}/${userId}" >下一页</a>
													<a href="${pageContext.request.contextPath}/core/v1/tBlogInfo/userBlogAllShow/${page.totalPage}/${userId}" >尾页</a>
												</c:if>
												<c:if test="!${page.hasNextPage}">
													下一页
													尾页
												</c:if>
                                                                                  </ul>
				</div>

					

				<%@include file="common/foot.jsp"%>
			</div>
			<div class="col-md-4">
				<%-- 用户头像昵称 --%>
				<div style="padding-top: 120px;">
					<center>
						
								<c:choose>
							<c:when test="${empty currentUser }">	
								<img src="<c:url value="/images/none.gif"/>" class="img-circle"  data-toggle="modal" data-target="#myModal">
								<br><br>用户名：
								<a href="javascript:void(0);" data-toggle="modal" data-target="#myModal">未登录</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/user/manager/${currentUser.userId }" target="_blank">
									<img alt="${currentUser.nickName }" src="${currentUser.userHeadimg }" class="img-circle" style="width: 224px;height: 224px;">
								</a>
								<br><br>用户名：
								${currentUser.nickName}
								<br><a href="<c:url value="/core/v1/tUser/logout"/>">安全退出</a>
							</c:otherwise>
						</c:choose>
							
					</center>
				</div>
			</div>
		</div>
	</div>




</body>
</html>
