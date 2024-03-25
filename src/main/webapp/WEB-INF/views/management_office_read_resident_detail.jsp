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
<script type="text/javascript" src="<c:url value="/resources/js/move_resident.js"/>"></script>
<title>세대원 정보 조회</title>
<style>
    th {
        white-space: nowrap;
    }
</style>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="container ">
		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-6">
				<h1 class="text-center">${temp_house.getHouse_dong()}동 ${temp_house.getHouse_hosu()}호<br> 세대원 정보 조회</h1>
			</div>
		</div>

		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-6">
				<div class="table-responsive">
					<table class="table">
		    			<thead class="table-responsive">
					      <tr>
					        <th scope="col">#</th>
					        <th scope="col" class="">아이디</th>
					        <th scope="col">닉네임</th>
					        <th scope="col">세대원 구분</th>
					        <th scope="col" class="d-none d-md-table-cell">세대원 상태 변경</th>
					        <th scope="col" class="d-md-none text-center">세대원<br>상태 변경</th>
					        <th scope="col" class="d-none d-md-table-cell">미거주 변경</th>
					        <th scope="col" class="d-md-none text-center">미거주<br>변경</th>				        
					      </tr>
					    </thead>
					    <c:forEach items="${list_resident_detail}" var="resident_detail" varStatus="i">
						    <tbody>
						      <tr>
						        <th scope="row">${i.index+1}</th> <!-- 0부터 시작하니까 1을 더해줍니다. -->
						        <td>${resident_detail.getR_login_id()}</td>
						        <td>${resident_detail.getR_nickname()}</td>
						        <td>${resident_detail.getR_category()}</td>
						        <td><button type="button" class="btn btn-warning" onclick="update_category('${resident_detail.getR_login_id()}', '${resident_detail.getR_category()}')">상태 변경</button></td>
						        		        
						        <td><button type="button" class="btn btn-danger" onclick="move_resident_by_office('${resident_detail.getR_login_id()}')">미거주</button></td>			        
						      </tr>
						    </tbody>
						</c:forEach>
					</table>
					<div class="col-md-12 mx-auto d-flex justify-content-end mb-4">
							<a href="<c:url value="/management_office/management_office_read_resident_by_dong?dong=${temp_house.getHouse_dong()}"/>"><button class="btn btn-primary" type="button">뒤로 가기</button></a>
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