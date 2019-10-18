<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> <!-- spring에서 제공하는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> <!-- form태그 라이브러리 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>

<script>

$(function(){
	$("#id").change(function(){
		$("#btn-check-id").show();
		$("#img-checked").hide();
	});	

$("#btn-check-id").click(function(){
		var id = $("#id").val();
		if(id == ""){
			return;
		}
		
		// ajax 통신
		$.ajax({
			url: "/api/user/checkid?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result == "fail"){
					console.error(response.message);
					return;
				}
				
				if(response.data == true){
					alert("이미 존재하는 아이디입니다.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#btn-check-id").hide();
				$("#img-checked").show();
			},
			error: function(xhr, error) {
	            console.error("error:"+error);
	         }
		});
	});
});
	
</script>



</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<form:form 
			modelAttribute="userVo" 
			class="join-form"
			id="join-form" 
			method="post"
			action="${pageContext.servletContext.contextPath }/user/join">
			
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<spring:hasBindErrors name="userVo">
				<c:if test='${errors.hasFieldErrors("name") }'>
				<p style="font-weight:bold; color:red; text-align:left; padding-left:0">
							<spring:message code='${errors.getFieldError("name").codes[0] }' text='${errors.getFieldError("name").defaultMessage }'/>
							</p>
						</c:if>
					</spring:hasBindErrors>

			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" />
			<input id="btn-check-id" type="button" value="id 중복체크">
			<img id="img-checked" style="display: none;" src="${pageContext.request.contextPath }/assets/images/check.png">
			<spring:hasBindErrors name="userVo"><!-- Controller에서 Error가 있으면 값이 셋팅, 없으면 다음 줄 실행 -->
				<c:if test='${errors.hasFieldErrors("id") }'> <!-- name 변수에 Error가 있는지 확인 -->
					<p style="font-wigth:bold; color:red; text-align:left; padding:2px 0 0 0">
						<spring:message code='${errors.getFieldError("id").codes[0] }' text='${errors.getFieldError("id").defaultMessage }' /> <!-- spring:message는 messages.properties의 값을 출력해주는 기능 -->
					</p>
				</c:if>
			</spring:hasBindErrors>
			
			
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
