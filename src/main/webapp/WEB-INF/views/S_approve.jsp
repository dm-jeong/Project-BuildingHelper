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
			<h1 class="text-center display-6">승인/반납</h1>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-center">
            <div class="col-lg-10">
				<h2 class="alert alert-danger">진행 대기 물품</h2>
				<table class="table table-bordered">
			        <thead class="thead-dark">
			            <tr>
			                <th class="align-middle">분류</th>
			                <th class="align-middle">물품</th>
			                <th class="align-middle">신청세대</th>
			                <th class="align-middle">신청날짜</th>
			                <th class="align-middle">반납날짜</th>
			                <th class="align-middle">승인여부</th>
			                <th class="align-middle">행동</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="item" items="${s_items_request}">
			            	<c:if test="${sessionScope.house.address_id eq item.s_address_id}">
				                <tr>
				                    <td class="align-middle">${item.s_category}</td>
				                    <td class="align-middle">${item.name}</td>
				                    <td class="align-middle">${item.resident_id}</td>
				                    <td class="align-middle">${item.s_start_time}</td>
				                    <td class="align-middle">${item.s_return_time}</td>
				                    <td class="align-middle" style="color: ${item.s_approval ? 'blue' : 'red'}">
									    ${item.s_approval ? '승인완료' : '승인대기중'}
									</td>
				                    <td class="d-flex justify-content-center align-items-center gap-1">
				                    	<c:choose>
										    <c:when test="${item.s_approval == false}">
										        <form action="<c:url value='/shareitems/rental/approve/${item.share_item_id}'/>" method="post" class="d-flex justify-content-center align-items-center m-0">
												    <input type="submit" value="승인" class="btn btn-primary">
												</form>
										        <form action="<c:url value='/shareitems/rental/return/${item.share_item_id}'/>" method="post" class="d-flex justify-content-center align-items-center m-0">
										            <input type="submit" value="거절" class="btn btn-danger">
										        </form>
										    </c:when>
										    <c:otherwise>
										        <form action="<c:url value='/shareitems/rental/return/${item.share_item_id}'/>" method="post" class="d-flex justify-content-center align-items-center m-0">
										            <input type="submit" value="반납" class="btn btn-success">
										        </form>
										    </c:otherwise>
										</c:choose>
									</td>
				                </tr>
			                </c:if>
		            	</c:forEach>
			        </tbody>
			    </table>
				<a href="<c:url value="/shareitems"/>" class="btn btn-secondary mb-4">목록으로</a>
			</div>
		</div>
	</div>
	<%@ include file="./module/footer.jsp" %>
</body>
</html>