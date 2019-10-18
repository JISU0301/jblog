<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
			<c:import url="/WEB-INF/views/includes/blognav.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blogmenu.jsp" />
				<form:form modelAttribute="postVo" action="${pageContext.servletContext.contextPath }/${authUser.id }/postWrite" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="categoryNo">
				      				<c:forEach items="${categoryName }" var="vo" varStatus="status">
				      					<option value="${vo.no }">${vo.name }</option>
				      				</c:forEach>
				      			</select>
				      			<spring:hasBindErrors name="postVo">
				 					<c:if test='${errors.hasFieldErrors("title") }'>
										<p style="font-weight:bold; color:red; text-align:left; padding:0">
					  					<spring:message 
											code='${errors.getFieldError("title").codes[0] }' text='${errors.getFieldError("title").defaultMessage }' />
										</p>
			   						</c:if>
								</spring:hasBindErrors>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>