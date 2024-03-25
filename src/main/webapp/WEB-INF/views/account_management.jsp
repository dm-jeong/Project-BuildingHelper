<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>계정관리</title>
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/account_management_go_back.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/two_password_same_check.js"/>"></script>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="container">
		<form:form modelAttribute="resident" method="post" onsubmit="return compare_pw()" class="text-center form-floating col-md-6 col-lg-6 mx-auto border p-2 box-shadow-light-gray rounded mt-4">			
			<h3 class="text-center form-floating col-md-6 col-lg-6 mx-auto mb-4 ">계정 정보 변경</h3>
			
			<div class="form-floating mb-4 ">
				<form:input path="r_login_id" class="form-control focus-ring focus-ring-danger border rounded-2" name="id" required="true" value="${r_login_id}" readonly="true" />			
				<label for="floatingInput">아이디(변경 불가)</label>
			</div>
			<div class="form-floating mb-4">
				<form:password path="r_login_pw" class="form-control" id="password1" placeholder="PassWord" required="true"/>
				<label for="floatingInput">새 비밀번호</label>
			</div>
			<div class="form-floating mb-4">
				<form:password path="" class="form-control" id="password2" placeholder="PassWord" required="true" />
				<label for="floatingInput">새 비밀번호 확인</label>
			</div>
			
			<div class="form-floating mb-4">
				<form:input path="r_nickname" name="nickname" class="form-control " placeholder="newNickname" required="true" value="${r_nickname}"/>
				<label for="floatingInput">변경할 닉네임</label>
			</div>
			<div class="form-floating mb-4">
				<form:input path="r_phone_number" name="phone_number" class="form-control " placeholder="PassWord" required="true" value="${r_phone_number}"/>
				<label for="floatingInput">전화번호 변경</label>
			</div>
			<div class="form-floating mb-4">
				<form:input path="r_email" name="email" class="form-control " placeholder="email" required="true" value="${r_email}"/>
				<label for="floatingInput">이메일 변경</label>
			</div>			
			<div class="form-floating mb-4">
				<button class="btn btn-primary col-12 mb-2" type="submit" value="개인 정보 수정" class="col-10">확인</button>
				<button class="btn btn-secondary col-12 mb-2" onclick="account_management_go_back();" value="개인 정보 수정" class="col-10">취소</button>
			</div>
		</form:form>
	</div>
	<%@ include file="./module/footer.jsp"%>
	<script type="text/javascript"
		src="<c:url value="/resources/js/two_password_same_check.js"/>"></script>
</body>
</html>