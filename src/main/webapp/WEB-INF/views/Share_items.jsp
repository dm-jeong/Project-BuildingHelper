<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>공유 물품 목록</title>
</head>
<body>
	<%@ include file="./module/header.jsp" %>
	<div class="jumbotron">
		<div class="container py-5">
			<h1 class="text-center display-6">공유 물품 목록</h1>
		</div>
	</div>
	<div class="container py-2">
	    <div class="row align-items-center">
	    	<c:choose>
	        	<c:when test="${sessionScope.resident.r_category == '관리소'}">
	            	<div class="col-auto">
	                	<a class="nav-link" href="<c:url value="/shareitems/rental/approve"/>"><button type="button" class="btn btn-secondary">승인/반납</button></a>
	                </div>
	                <div class="col-auto">
	                    <a class="nav-link" href="<c:url value="/shareitems/add"/>"><button type="button" class="btn btn-primary">등록하기</button></a>
	                </div>
	            </c:when>
	            <c:when test="${sessionScope.resident.r_category == '세대원' || sessionScope.resident.r_category == '동대표'}">
	                <div class="col-auto">
	                    <a class="nav-link" href="<c:url value="/shareitems/rental/confirm"/>"><button type="button" class="btn btn-secondary">신청확인하기</button></a>
	                </div>
	            </c:when>
	        </c:choose>
	    </div>
	</div>
	<div class="container py-2">
		<div class="row g-4">
			<div class="col-lg-3">
				<div class="row g-4">
					<section class="col-lg-12">
						<div class="mb-3">
							<h4>카테고리</h4>
							<ul class="list-unstyled" style="line-height: 40px;">
								<li class="d-flex">
									<a class="nav-link" href="<c:url value='/shareitems/'/>">모두(All Categories)</a>
								</li>
								<c:forEach items="${categories}" var="category">
									<c:if test="${sessionScope.house.address_id eq category.s_address_id}">
										<li class="d-flex">
											<a class="nav-link" href="<c:url value='/shareitems/${category.s_category}'/>">${category.s_category}</a>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</section>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="row g-4 align-items-center">
					<c:forEach items="${s_item_list}" var="share_item">
						<c:choose>
							<c:when test="${sessionScope.house.address_id eq share_item.s_address_id}">
								<div class="col-md-6 col-lg-6 col-xl-4" style="height: 380px;">
									<div class="rounded position-relative h-100">
										<div class="custom-img" style="overflow: hidden; border-radius: 10px 10px 0 0; height: 170px;">
									    	<c:choose>
									        	<c:when test="${share_item.getS_file_name() == null}">
									            	<img src="<c:url value='/resources/images/${share_item.s_file_name}'/>" class="img-fluid w-100 rounded-top" style="object-fit: cover;"/>
									            </c:when>
									            <c:otherwise>
									            	<img src="<c:url value="/resources/images/${share_item.s_file_name}"/>" class="img-fluid w-100 rounded-top" style="object-fit: cover;"/>
									            </c:otherwise>
									        </c:choose>
									    </div>
									    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${share_item.s_category}</div>
									    <div class="p-4 border border-secondary border-top-0 rounded-bottom d-flex flex-column justify-content-between" style="height: 210px;">
											<div>
											    <h4>${share_item.name}</h4>
											    <c:choose>
												    <c:when test="${fn:length(share_item.s_description) <= 55}">
												        <p>${share_item.s_description}</p>
												    </c:when>
												    <c:otherwise>
												        <p>${fn:substring(share_item.s_description, 0, 55)}...</p>
												    </c:otherwise>
												</c:choose>
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
											    <a href="<c:url value='/shareitems/shareitem?id=${share_item.share_item_id}'/>" class="btn btn-secondary" role="button">상세 정보</a>
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
	<hr>
	<%@ include file="./module/footer.jsp" %>
</body>
</html>