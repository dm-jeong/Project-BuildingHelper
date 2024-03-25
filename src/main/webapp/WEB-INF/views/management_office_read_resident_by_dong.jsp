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
	<div class="container ">
		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-4">
				<h1 class="text-center">${r_house_name} 관리사무소의 ${dong}동 <br>세대원 정보 조회</h1>
			</div>
		</div>

		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-6">
				<div class="table-responsive">
					<table class="table">
		    			<thead class="table-responsive">
					      <tr>
					        <th scope="col">호</th>
					      </tr>
					    </thead>
					    <c:forEach items="${list_resident_hosu_read_by_office}" var="resident_hosu" varStatus="i">
						    <tbody>
						      <tr>
						        <td>
						        	<a href="<c:url value="/management_office/management_office_read_resident_detail?dong=${resident_hosu.getHouse_dong()}&hosu=${resident_hosu.getHouse_hosu()}"/>">
						        	${resident_hosu.getHouse_hosu()}호
						        	</a>
						        </td>
						      </tr>
						      <!-- More table rows -->
						    </tbody>
						</c:forEach>
					</table>
					<div class="col-12 col-md-12 mx-auto d-flex justify-content-end mb-4">
							<a href="<c:url value="/management_office/management_office_read_resident/"/>"><button class="btn btn-primary" type="button">뒤로 가기</button></a>
					</div>						
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./module/footer.jsp"%>
<!-- 							<td> -->
<%-- 								<input type="button" onclick="update_category('${resident_office_read.r_login_id}', '${resident_office_read.r_category}')" value="Update Category" class="btn btn-primary"> --%>
<!-- 							</td> -->	 
</body>
</html>