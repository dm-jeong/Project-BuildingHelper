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

<title>메시지 상세 내용 페이지</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="p-4">
		<div class="container">
				<form:form modelAttribute="msg_group" method="post" class="text-center form-floating col-md-6 col-lg-4 mx-auto border p-3 box-shadow-light-gray rounded">
				<h3 class="text-center form-floating mb-4 mt-4">단체 메시지 읽기</h3>
				<div class="form-floating mb-4">
					<form:input path="msg_group_sender_r_category" class="form-control focus-ring focus-ring-danger border rounded-2"
						name="msg_group_sender_r_category" id="msg_group_sender_r_category" placeholder="r_category" 
						value="${msg_group.getMsg_group_sender_r_category()}" readonly="true" />
					<label for="r_category">보낸 이</label>
				</div>
				
				<div class="form-floating mb-4">
					<form:input path="msg_group_title" class="form-control focus-ring focus-ring-danger border rounded-2"
						name="msg_group_title" id="msg_group_title" placeholder="msg_title" 
						value="${msg_group.getMsg_group_title()}" readonly="true" />
					<label for="msg_title">메시지 제목</label>
				</div>
				<div class="d-flex justify-content-start ps-1"><p>메시지 내용</p></div>
				<div class="form-group mb-3">
					<form:textarea path="msg_group_content" id="msg_group_content" rows="10" class="form-control focus-ring focus-ring-danger border rounded-2" readonly="true"/>
				</div>		
			</form:form>
			<div class="col-md-6 col-lg-4 mx-auto d-flex justify-content-end">
					<a href="<c:url value="/resident/message/read_group_message"/>"><button class="btn btn-primary" type="button">뒤로 가기</button></a>
			</div>
		</div>
	</div>

	<%@ include file="./module/footer.jsp"%>
</body>
</html>