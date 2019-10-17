<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blognav.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${view.postView.title }</h4>
					<p>
						<c:if test='${empty view.postView }'>
							<span style="font-size:1rem;font-weight:bold">해당 카테고리에는 포스트가 없습니다.</span>
						</c:if>
						${view.postView.contents }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${view.postList }" var="poster" varStatus="status">
					<li><a href="${pageContext.servletContext.contextPath }/${blogInfo.id}/${poster.categoryno }/${poster.postno}">${poster.title}</a>
					<span>${poster.reg_date}</span></li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${blogInfo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${view.categoryList }" var="categoryVo"  varStatus="status">
					<li><a href="${pageContext.servletContext.contextPath }/${categoryVo.blog_id }/${categoryVo.no }">${categoryVo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2019
			</p>
		</div>
	</div>
</body>
</html>