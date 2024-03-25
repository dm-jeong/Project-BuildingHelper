<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

<!-- chart, table css -->
<link href="<c:url value="/resources/css/expense_check.css"/>" rel="stylesheet">
<script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>

<title>관리비 조회</title>
</head>


<body>
	<%@ include file="module/header.jsp"%>


	<div class="jumbotron">
		<div class="container">
			<h1 class="display-5 text-center" style="margin: 40px" >관리비 납부 조회</h1>
		</div>
	</div>

	<div class="container">
 		<div class="col-12 col-lg-6 mx-auto">	
			<div class="row">
	   			<div class="card">
					<div class="card-header">
						<p class="card-title mb-0">${address.house_name } ${house.house_dong } ${house.house_hosu }</p>
					</div>
					<table class="table table-hover my-0 text-center">
						<thead>
							<tr>
								<th>년월일</th>
								<th>금액</th>
								<th>납부상태</th>
								<th>납부일</th>
							</tr>
						</thead>
						
						<tbody>
	
	
							<c:forEach items="${expense_check}" var="ec">
									<tr>
										<td>
										    <a href="<c:url value='/expense/month'/>?expense_year=${ec.expense_year}&expense_month=${ec.expense_month}"
										    	style="text-decoration:none;">
										        ${ec.expense_year}년 ${ec.expense_month}월분
										    </a>
										</td>
										<td >${ec.expense_price }원</td>
										<td >${ec.expense_state }</td>
										<td >${ec.expense_paid}</td>
									</tr>
							</c:forEach>
	
						</tbody>
					</table>
				</div>
			</div>	   								
			<div class="pagination text-center justify-content-center">
			    <c:if test="${page_maker.prev}">
			        <a class="page-link border-0" href="?page=${page_maker.start_page - 1}">이전</a>
			    </c:if>
			
			    <c:forEach begin="${page_maker.start_page}" end="${page_maker.end_page}" var="pageNum">
			        <c:choose>
			            <c:when test="${pageNum eq page_maker.cri.page}">
			                <strong class="page-link border-0 " style="width:38px;">${pageNum}</strong>
			            </c:when>
			            <c:otherwise>
			                <a class="page-link border-0 " style="width:38px;" href="?page=${pageNum}">${pageNum}</a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			
			    <c:if test="${page_maker.next}">
			        <a class="page-link border-0 " href="?page=${page_maker.end_page + 1}">다음</a>
			    </c:if>
			</div>

		</div>
	</div>
		<br>
		<%@ include file="module/footer.jsp"%>
</body>
</html>