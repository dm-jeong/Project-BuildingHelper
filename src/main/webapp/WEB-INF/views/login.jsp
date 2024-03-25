<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>login page</title>
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>
<style>

</style>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="p-4 m-4">
		<div class="container">
			<form:form modelAttribute="Resident" method="post" class="text-center form-floating col-md-6 col-lg-4 mx-auto border p-3 box-shadow-light-gray rounded">
				<h3 class="text-center form-floating mb-4 mt-4">로그인</h3>
				<div class="form-floating mb-4">
					<form:input path="r_login_id" class="form-control mb-3"
						id="floatingInput-id" placeholder="ID" />
					<label for="floatingInput">아이디</label>
				</div>
				<div class="form-floating mb-4">
					<form:password path="r_login_pw" class="form-control "
						id="floatingInput-pw" placeholder="PassWord" />
					<label for="floatingInput">비밀번호</label>
				</div>
				<div class="form-floating mb-4">
					<button class="btn btn-primary col-12" type="submit" value="로그인"
						class="col-10 mt-2">로그인</button>
					<p class="text-center">${login_fail}</p>
					<p class="text-center">회원가입은 거주하시는 건물의 관리사무소에 문의해주세요.</p>
				</div>
			</form:form>
		</div>
	</div>
	<%@ include file="./module/footer.jsp"%>
</body>
</html>