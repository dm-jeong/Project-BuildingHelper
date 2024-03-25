<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>Thank you</title>
</head>
<body>
	<%@ include file="./module/header.jsp" %>
	<div class="jumbotron">
		<div class="container py-5">
			<h1 class="text-center display-6">신청 완료</h1>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-center">
            <div class="col-lg-10">
				<div class="alert alert-danger">
		        	<h2>신청이 완료되었습니다.</h2>
		        	<p class="mb-0">승인이 완료되면 관리사무소로 와주세요.</p>
		        </div>
		        <p>물품 : ${shareItem.name}</p>
				<p>반납시간은 <b>${shareItem.s_return_time}</b>입니다.</p>
				<div class="d-flex gap-2">
					<a href="<c:url value="/shareitems"/>" class="btn btn-secondary mb-4">목록으로</a>
					<a href="<c:url value="/shareitems/rental/confirm"/>" class="btn btn-secondary mb-4">확인페이지</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./module/footer.jsp" %>
</body>
</html>