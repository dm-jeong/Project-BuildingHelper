<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>물품 상세 정보</title>
</head>
<body>
	<%@ include file="./module/header.jsp" %>
	<div class="jumbotron">
		<div class="container py-5">
			<h1 class="text-center display-6">물품 정보</h1>
		</div>
	</div>
	<div class="container-fluid py-5 mb-5">
		<div class="container py-5">
			<div class="row g-4 mb-5">
				<div class="col-lg-8 col-xl-9">
					<div class="row g-4 d-flex align-items-center justify-content-start">
					    <div class="custom-img col-lg-6 d-flex align-items-center justify-content-center mb-4">
						    <c:choose>
							    <c:when test="${share_item.getS_file_name() == null}">
							    	<img src="<c:url value='/resources/images/${share_item.s_file_name}'/>" class="img-fluid rounded" alt="Image"/> 
							    </c:when>
							    <c:otherwise>
							    	<img src="<c:url value="/resources/images/${share_item.s_file_name}"/>" class="img-fluid rounded" alt="Image"/>
							    </c:otherwise>
						    </c:choose>
					    </div>
					    <div class="col-lg-6">
					        <h4 class="fw-bold mb-4">${share_item.name}</h4>
					        <p class="mb-4"><b>분류</b> : ${share_item.s_category}</p>
					        <p class="mb-4"><b>설명</b> : ${share_item.s_description}</p>
					        <p class="mb-4"><b>상태</b> : 
					            <c:choose>
					                <c:when test="${share_item.s_condition == '대여가능'}">
					                    <span style="color: blue;">${share_item.s_condition}</span>
					                </c:when>
					                <c:otherwise>
					                    <span style="color: red;">${share_item.s_condition}</span>
					                </c:otherwise>
					            </c:choose>
					        </p>
					        <div class="d-flex gap-2">
					            <p><a href="<c:url value="/shareitems"/>" class="btn btn-secondary mb-4">돌아가기</a></p>
					            <c:choose>
					                <c:when test="${sessionScope.resident.r_category == '관리소'}">
					                    <p><a href="<c:url value="/shareitems/update?id=${share_item.share_item_id}"/>" class="btn btn-success mb-4">수정</a></p>
					                    <p><a href="<c:url value="javascript:deleteConfirm('${share_item.share_item_id}')"/>" class="btn btn-danger mb-4">삭제</a></p>
					                </c:when>
					                <c:when test="${(sessionScope.resident.r_category == '세대원' || sessionScope.resident.r_category == '동대표') and share_item.s_condition == '대여가능'}">
					                    <p><a href="<c:url value='/shareitems/rental/request/${share_item.share_item_id}'/>" class="btn btn-primary mb-4">대여 신청</a></p>
					                </c:when>
					            </c:choose>
					        </div>
					    </div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<h2 class="text-center display-6 mt-5 mb-5">다른 ${share_item.s_category}</h2>
		<div class="container">
		    <c:choose>
		        <c:when test="${not empty final_list}">
		            <div class="row">
		                <c:forEach items="${final_list}" var="share_item">
		                    <div class="col-md-4 col-lg-4 col-xl-3" style="height: 380px;">
		                        <div class="rounded position-relative h-100">
		                            <div class="custom-img" style="overflow: hidden; border-radius: 10px 10px 0 0; height: 170px;">
		                                <c:choose>
		                                    <c:when test="${share_item.getS_file_name() == null}">
		                                        <img src="<c:url value='/resources/images/${share_item.s_file_name}'/>" class="img-fluid w-100 rounded-top" style="object-fit: cover;"/>
		                                    </c:when>
		                                    <c:otherwise>
		                                        <img src="<c:url value='/resources/images/${share_item.s_file_name}'/>" class="img-fluid w-100 rounded-top" style="object-fit: cover;"/>
		                                    </c:otherwise>
		                                </c:choose>
		                            </div>
		                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${share_item.s_category}</div>
		                            <div class="p-4 border border-secondary border-top-0 rounded-bottom d-flex flex-column justify-content-between" style="height: 210px;">
		                                <div>
		                                    <h4>${share_item.name}</h4>
		                                    <p>${share_item.s_description}</p>
		                                </div>
		                                <div class="d-flex justify-content-between align-items-center">
		                                    <c:choose>
		                                        <c:when test="${share_item.s_condition == '대여가능'}">
		                                            <h5><span style="color: blue;">${share_item.s_condition}</span></h5>
		                                        </c:when>
		                                        <c:otherwise>
		                                            <h5><span style="color: red;">${share_item.s_condition}</span></h5>
		                                        </c:otherwise>
		                                    </c:choose>
		                                    <a href="<c:url value='/shareitems/shareitem?id=${share_item.share_item_id}'/>" class="btn btn-secondary"role="button">상세 정보</a>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </c:forEach>
		            </div>
		        </c:when>
		        <c:otherwise>
		            <h4>존재하지 않습니다.</h4>
		        </c:otherwise>
		    </c:choose>
		</div>
	</div>
	<hr>
	<%@ include file="./module/footer.jsp" %>
</body>
<script>
	function deleteConfirm(id)
	{
		if(confirm("삭제하시겠습니까?") == true) location.href="./delete?id=" + id;
		else return;
	}
</script>
</html>
