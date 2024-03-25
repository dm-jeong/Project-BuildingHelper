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
<script type="text/javascript" src="<c:url value="/resources/js/move_resident.js"/>"></script>

<title>우리집 세대원 조회 페이지</title>
<style>
    /* 글자가 다음 줄로 넘아가지 않도록하는 CSS */
    th {
        white-space: nowrap;
    }
    
    .phone_number {
    	white-space: nowrap;
    }
</style>
</head>
<body>
	<%@ include file="./module/header.jsp"%>

	<div class="container">
		
		
			<h3>${r_house_name}</h3>
			<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-10">
				<h1>우리집 세대원 조회 페이지</h1>
			<div class="table-responsive">
			<table class="table">
				<thead class="table-responsiev">
				<tr>
					<th scope="col" >아이디</th>
					<th scope="col" >닉네임</th>
					<th scope="col" >세대원 구분</th>
					<th scope="col" >전화번호</th>
					<th scope="col" class="d-none d-md-table-cell">이메일</th>
					<th scope="col" >세대원 미거주 상태로 변경</th>
				</tr>
				<c:forEach items="${list_of_my_house_resident}" var="my_house_resident">
					<tr>
						<td>
							<p>${my_house_resident.r_login_id}</p>
						</td>
						<td>
							<p>${my_house_resident.r_nickname}</p>
						</td>
						<td>
							<p>${my_house_resident.r_category}</p>
						</td>
						<td>
							<p class="phone_number">${my_house_resident.r_phone_number}</p>
						</td>
						<td class="d-none d-md-table-cell">
							<p>${my_house_resident.r_email}</p>
						</td>
						<td>
							<input type="button" onclick="move_resident('${my_house_resident.r_login_id}')" value="미거주 상태로 변경" class="btn btn-primary">
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			</div>
		</div>
	</div>	

	<%@ include file="./module/footer.jsp"%>
</body>
</html>