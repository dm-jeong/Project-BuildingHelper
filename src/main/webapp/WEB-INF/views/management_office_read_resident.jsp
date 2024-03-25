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
<script type="text/javascript" src="<c:url value="/resources/js/update_category.js"/>"></script>
<title>세대원 정보 조회</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div>
		<div class="container ">
			<div class="mx-auto">
				<div class="d-flex justify-content-center mt-2">
					<div class="col-12 col-md-6">
						<h1 class="text-center">${r_house_name} 관리사무소의 세대원 정보 조회</h1>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-center my-4">
				<div class="col-12 col-md-6">
					<div class="table-responsive">
						<table class="table">
			    			<thead class="table-responsive ">
						      <tr>
						        <th scope="col">동</th>
						      </tr>
						    </thead>
						    <c:forEach items="${list_resident_dong_read_by_office}" var="resident_dong" varStatus="i">
							    <tbody>
							      <tr>
							        <td><a href="<c:url value="/management_office/management_office_read_resident_by_dong?dong=${resident_dong.getHouse_dong()}"/>">${resident_dong.getHouse_dong()}동</a></td>    
							      </tr>
							      <!-- More table rows -->
							    </tbody>
							</c:forEach>
						</table>		
					</div>
				</div>
			</div>
		</div>
	</div>	


	<%@ include file="./module/footer.jsp"%>	 
</body>
</html>