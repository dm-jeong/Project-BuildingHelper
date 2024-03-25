<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

<title>회원가입 페이지 세대원 전용</title>
</head>
<body>
	
	<%@ include file="./module/header.jsp"%>
	<div class="p-4">
		<div class="container">
			<form:form modelAttribute="Resident" method="post" class="text-center form-floating col-md-6 col-lg-4 mx-auto border p-3 box-shadow-light-gray rounded mt-4">
				<h3 class="text-center form-floating mb-4 mt-4">${register_house_name} <br> 세대원 회원가입 페이지</h3>
				<div class="form-floating mb-4">
					<form:input path="address.house_name" name="address_house" class="form-control mb-3" value="${register_house_name}" readonly="true"
						id="floatingInput" placeholder="아파트 이름" />
					<label for="floatingInput">공동주택 이름</label>
				</div>
				
				<div class="form-floating mb-4">
					<form:input path="house.house_dong" name="house_dong" class="form-control mb-3" value="${register_house_dong}" readonly="true"
						id="floatingInput" placeholder="동" />
					<label for="floatingInput">동</label>
				</div>		
				<div class="form-floating mb-4">
					<form:input path="house.house_hosu" name="house_hosu" class="form-control mb-3" value="${register_house_hosu}" readonly="true"
						id="floatingInput" placeholder="호수" />
					<label for="floatingInput">호수</label>
				</div>							
				<div class="form-floating mb-4">
					<form:input path="r_login_id" name="r_login_id" class="form-control mb-3" required="true"
						id="floatingInput" placeholder="아이디" />
					<label for="floatingInput">아이디</label>
				</div>
				<div class="form-floating mb-4">
					<form:password path="r_login_pw" name="r_login_pw" class="form-control mb-3" required="true"
						id="floatingInput" placeholder="비밀번호" />
					<label for="floatingInput">비밀번호</label>
				</div>
				<div class="form-floating mb-4">
					<form:input path="r_nickname" name="r_nickname" class="form-control mb-3" required="true"
						id="floatingInput" placeholder="닉네임" />
					<label for="floatingInput">닉네임</label>
				</div>
				<div class="form-floating mb-4">
					<form:input path="r_phone_number" name="r_phone_number" class="form-control mb-3" required="true" placeholder="010-1234-5678"
						id="floatingInput" />
					<label for="floatingInput">전화번호</label>
				</div>
				<div class="form-floating mb-4">
					<form:input path="r_email" name="r_email" class="form-control mb-3" required="true"
						id="floatingInput" placeholder="이메일" />
					<label for="floatingInput">이메일</label>
				</div>	
				
				<div class="form-floating mb-4">
					<button class="btn btn-primary col-12" type="submit" value="회원 가입"
						class="col-10 mt-2">회원 가입</button>
				</div>
			</form:form>
		</div>
	
	</div>
	<%@ include file="./module/footer.jsp"%>
</body>
</html>