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
<script type="text/javascript"
	src="<c:url value="/resources/js/send_message.js"/>"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>메시지 전송 페이지</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="p-4">
		<div class="container">
			<div class="col-md-6 col-lg-4 mx-auto">
				<form:form modelAttribute="Msg" method="post" class="text-center form-floating border p-3 box-shadow-light-gray rounded">
					<h3 class="text-center form-floating mb-4 mt-4">메시지 전송</h3>
					<div class="form-floating mb-4">
						<form:input path="msg_sender_house_dong" class="form-control focus-ring focus-ring-danger border rounded-2"
							name="msg_sender_house_dong" id="msg_sender_house_dong" placeholder="sender_dong" 
							value="${house.getHouse_dong()}" readonly="true" />
						<label for="msg_sender_house_dong">보내는 동</label>
					</div>
					
					<div class="form-floating mb-4">
						<form:input path="msg_sender_house_hosu" class="form-control focus-ring focus-ring-danger border rounded-2"
							name="msg_sender_house_hosu" id="msg_sender_house_hosu" placeholder="sender_hosu" 
							 value="${house.getHouse_hosu()}" readonly="true" />
						<label for="msg_sender_house_hosu">보내는 호</label>
					</div>
					
					<div class="form-floating mb-4">
						<form:input path="msg_title" class="form-control border rounded-2"
							name="msg_title" id="msg_title" placeholder="msg_title" />
						<label for="msg_title">메시지 제목</label>
					</div>
					<div class="d-flex justify-content-start ps-1"><p>메시지 내용</p></div>
					<div class="form-group mb-3">
						<form:textarea path="msg_content" id="msg_content" rows="10" class="form-control"/>
					</div>		
					<div class="form-floating mb-4">
						<form:input path="msg_receiver_house_dong" class="form-control border rounded-2"
							name="msg_receiver_house_dong" id="msg_receiver_house_dong" placeholder="msg_receiver_house_dong" />
						<label for="msg_receiver_house_dong">받는 동</label>
					</div>
					<div class="form-floating mb-4">
						<form:input path="msg_receiver_house_hosu" class="form-control border rounded-2"
							name="msg_receiver_house_hosu" id="msg_receiver_house_hosu" placeholder="msg_receiver_house_hosu" />
						<label for="msg_receiver_house_hosu">받는 호</label>
					</div>				
					<div class="form-floating mb-2">
						<input type="button" class="btn btn-primary col-12 mb-2" value="메시지 전송" data-id="${Msg}" onclick="send_message(event, this)" />
					</div>
					
				</form:form>
			</div>
				<div class="col-md-6 col-lg-4 mx-auto d-flex justify-content-end">
					<a href="<c:url value="/resident/message"/>"><button class="btn btn-primary" type="button">뒤로 가기</button></a>
				</div>
				
				
		</div>
	</div>
					
	<%@ include file="./module/footer.jsp"%>
</body>
</html>