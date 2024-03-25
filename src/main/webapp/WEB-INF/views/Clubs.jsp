<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>동아리 홈페이지</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="jumbotron">
		<div class="container py-5">
			<h1 class="text-center display-6">동아리 활동</h1>
		</div>
	</div>
	<div class="container py-2">
	    <div class="row align-items-center">
	    	<div class="col-auto">
	    		<a class="nav-link" href="<c:url value="/clubs/add"/>"><button type="button" class="btn btn-secondary">등록하기</button></a>
	    	</div>
	    </div>
	</div>
	<div class="container py-2">
		<div class="row g-4">
			<div class="col-lg-12">
				<div class="row g-4 align-items-center">
					<c:forEach var="post" items="${posts}">
						<c:choose>
							<c:when test="${sessionScope.house.address_id eq post.c_address_id}"> <!-- 사용자의 address_id와 동일할 경우 -->
								<div class="col-md-6 col-lg-6 col-xl-4" style="height: 380px;">
									<div class="rounded position-relative h-100">
										<div class="custom-img" style="overflow: hidden; border-radius: 10px 10px 0 0; height: 170px;">
											<img src="<c:url value='/resources/images/${post.c_file_name}'/>" class="card-img-top" alt="club image">
										</div>
										<div class="p-4 border border-secondary border-top-0 rounded-bottom d-flex flex-column justify-content-between" style="height: 210px;">
											<h4>${post.c_title}</h4>
											<c:choose>
											    <c:when test="${fn:length(post.c_description) <= 50}">
											        <p>${post.c_description}</p>
											    </c:when>
											    <c:otherwise>
											        <p>${fn:substring(post.c_description, 0, 50)}...</p>
											    </c:otherwise>
											</c:choose>
											<p>설립일 : ${post.c_time}</p>
											<div class="d-flex align-items-center">
												<div class="me-auto">
													<a href="/buildingHelper/clubs/clubboard?c_id=${post.c_id}"  class="btn btn-secondary" role="button">게시판</a>
												</div>
												<div class="gap-2">
													<c:if test="${sessionScope.resident.r_login_id eq post.c_writer_id}"> <!-- 사용자의 id와 작성자의 id가 동일할 경우 -->
														<a href="clubs/update?id=${post.c_id}" class="btn btn-success" role="button">수정</a>
													</c:if>
													<c:if test="${sessionScope.resident.r_category eq '관리소' or sessionScope.resident.r_login_id eq post.c_writer_id}">
													<!-- 사용자의 category가 관리소이거나 id가 작성자의 id와 동일할 경우 -->
														<a href="javascript:delete_confirm('${post.c_id}')" class="btn btn-danger" role="button">삭제</a>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./module/footer.jsp"%>
</body>
<script> <%-- 삭제 여부 확인 --%>
	function delete_confirm(c_id)
	{
		if(confirm("삭제하시겠습니까?") == true) location.href="/buildingHelper/clubs/delete?c_id=" + c_id;
		else return;
	}
</script>
</html>
