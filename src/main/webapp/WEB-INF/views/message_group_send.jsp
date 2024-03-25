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
<script type="text/javascript" src="<c:url value="/resources/js/send_group_message.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>그룹 메시지 전송 페이지</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="p-4">
		<div class="container">
			<form:form modelAttribute="Msg_group" method="post" class="text-center form-floating col-md-6 col-lg-4 mx-auto border p-3 box-shadow-light-gray rounded">
				<% Resident resident = (Resident) session.getAttribute("resident"); 
					if(resident.getR_category().equals("관리소")) { %>
					<h2>관리소 단체 메시지 전송</h2>
					<% } else if(resident.getR_category().equals("동대표")) {%>
					<h2>동대표 단체 메시지 전송</h2>
					<% } %> 
				<div class="form-floating mb-4">
					<form:input path="msg_group_title" class="form-control border rounded-2"
						name="msg_group_title" id="msg_group_title" placeholder="msg_group_title" />
					<label for="floatingInput">메시지 제목</label>
				</div>
				
				<div class="d-flex justify-content-start ps-1"><p>메시지 내용</p></div>
				<div class="form-group mb-3">
					<form:textarea path="msg_group_content" id="msg_group_content" rows="10" class="form-control"/>
				</div>
				
				<div class="form-floating mb-2">
					<input type="button" class="btn btn-primary col-12 mb-2" value="메시지 전송" data-id="${Msg_group}" onclick="send_group_message(event, this)" />
				</div>
			</form:form>
			<div class="col-md-6 col-lg-4 mx-auto d-flex justify-content-end">
					<a href="<c:url value="/resident/message"/>"><button class="btn btn-primary" type="button">뒤로 가기</button></a>
			</div>
		</div>
	</div>
	<%@ include file="./module/footer.jsp"%>
</body>
</html>