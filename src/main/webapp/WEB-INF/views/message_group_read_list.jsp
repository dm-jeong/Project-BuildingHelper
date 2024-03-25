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
<title>단체 메시지 확인 페이지</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
		
	<div class="container">
		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-6">
				<div class="table-responsive">
					<table class="table">
		    			<thead class="table-responsive">
					      <tr>
					        <th scope="col">보낸이</th>
					        <th scope="col">메시지 제목</th>
					        <th scope="col">보낸 시간</th>
					      </tr>
					    </thead>
					    <c:forEach items="${list_of_msg_group}" var="msg_group">
						    <tbody>
						      <tr>
						        <td>${msg_group.getMsg_group_sender_r_category()}</td>
						        <td><a href="<c:url value="/resident/message/read_group_message_detail?msg_group_id=${msg_group.getMsg_group_id()}"/>">${msg_group.getMsg_group_title()}</a></td>    
						      	<td><p>${msg_group.getMsg_sent_time()} </p></td>
						      </tr>
						    </tbody>
						</c:forEach>
					</table>
					<div class="mx-auto d-flex justify-content-end">
							<a href="<c:url value="/resident/message"/>"><button class="btn btn-primary" type="button">뒤로 가기</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="./module/footer.jsp"%>
</body>
</html>